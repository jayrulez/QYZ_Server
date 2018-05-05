package lx.gs.bag;

import cfg.CfgMgr;
import cfg.bag.BagType;
import cfg.cmd.condition.Condition;
import cfg.tips.LocationType;
import common.ErrorCode;
import gs.Utils;
import lx.gs.bag.msg.SChange;
import lx.gs.bag.msg.SMove;
import lx.gs.bag.msg.SSort;
import lx.gs.bag.msg.SSyncCapacity;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.role.FRole;
import lx.gs.tips.FTips;
import cfg.tips.TipsCode;
import xdb.Transaction;
import xtable.Roleinfos;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jin Shuai
 */
public abstract class StorageBag<T> extends AbstractBag<T> {
    public StorageBag(long roleid, int bagType, int capacity) {
        super(roleid, bagType, capacity + CfgMgr.bagconfig.get(bagType).initcapacity);
        stackable = CfgMgr.bagconfig.get(bagType).stackable;
    }

    private boolean stackable;

    public abstract int getMaxStack(T t);

    protected abstract Comparator<T> getComparator();

    public abstract void addCapacity(int add);

    /**
     * 物品
     */
    public abstract long getId(T item);

    public abstract boolean isBind(T item);

    public abstract int getPrice(T item);

    public abstract long getExpiretime(T item);

    protected abstract int getQuality(T t);

    /**
     * 可堆叠包裹需要重写此方法, 不可堆叠包裹不会调用此方法
     */
    public void setNum(T slot, int num) {
        throw new UnsupportedOperationException("bagtype : " + bagType + ", Unsupported setNum");
    }

    /**
     * 可堆叠包裹需要重写此方法, 不可堆叠包裹不会调用此方法
     */
    protected T splitCopy(T item, int num) {
        throw new UnsupportedOperationException("bagtype : " + bagType + ", Unsupported split");
    }

    private void updateNum(T item, int newNum) {
        setNum(item, newNum);
        syncNum(item);
    }

    private void syncNum(T item) {
        xdb.Transaction.tsendWhileCommit(roleid, new SChange(bagType, getPosition(item), getNum(item)));
    }

    /**
     * 包裹
     */
    protected boolean add(T item) {
        if (stackable) {
            item = stackItem(item);
            if (item == null) {
                return true;
            }
        }
        final int newEmptyPos = allocEmptyPosition();
        if (newEmptyPos == AbstractBag.INVALID_POS) {
            return false;
        }
        putByPos(newEmptyPos, item);
        return true;
    }

    private T stackItem(T newItem) {
        final int maxOverlay = getMaxStack(newItem);
        int num = getNum(newItem);

        assert (num > 0);
        assert (num <= maxOverlay);
        for (T item : getItems()) {
            if (!canStack(item, newItem)) {
                continue;
            }
            int currNum = getNum(item);
            int remainStack = maxOverlay - currNum;
            if (remainStack <= 0) {
                continue;
            }
            int splitNum = remainStack >= num ? num : remainStack;
            updateNum(item, currNum + splitNum);
            num -= splitNum;
            if (num == 0) {
                return null;
            }
            setNum(newItem, num);
        }
        return newItem;
    }

    /**
     * 不考虑是否超过最大堆叠
     */
    public boolean canStack(T a, T b) {
        return stackable && a != null && b != null && getModelId(a) == getModelId(b) && isBind(a) == isBind(b);
    }

    protected boolean addItem(int modelId, int num, Boolean isBind, Long expireTime) {
        List<T> items = createItem(modelId, num, isBind, expireTime);
        if (Utils.isNull(items)) return false;
        for (T item : items) {
            if (!add(item)) {
                return false;
            }
        }
        return true;
    }

    protected abstract List<T> createItem(int modelId, int num, Boolean isBind, Long expireTime);

    protected List<T> createItem(int modelId, int num, boolean isBind) {
        return createItem(modelId, num, isBind, null);
    }

    protected boolean deleteBindFirst(int modelId, int count, Collection<T> costBindItemList) {
        List<T> itemList = getItemByModelId(modelId);
        Collections.sort(itemList, (o1, o2) -> Boolean.compare(isBind(o1), isBind(o2)) * -1);
        for (T item : itemList) {
            int num = getNum(item);
            int pos = getPosition(item);
            if (num <= count) {
                deleteByPosition(pos);
                count -= num;
            } else {
                deleteByPosition(pos, count);
                count = 0;
            }
            if (isBind(item) && costBindItemList != null) {
                costBindItemList.add(item);
            }
            if (count == 0){
                return true;
            }
        }
        return false;
    }

    protected boolean delete(int modelId, int count, Boolean bindType) {
        for (T item : getItemByModelId(modelId, bindType)) {
            int num = getNum(item);
            int pos = getPosition(item);
            if (num >= count) {
                deleteByPosition(pos, count);
                return true;
            }
            deleteByPosition(pos);
            count -= num;
        }
        return false;
    }

    protected T deleteByPosition(int pos, int deleteNum) {
        if ((!stackable && deleteNum != 1) || deleteNum < 1) {
            throw new IllegalArgumentException("param error, pos = " + pos + ", deleteNum = " + deleteNum);
        }
        final T item = getByPosition(pos);
        if (item == null)
            return null;
        final int remain = getNum(item) - deleteNum;
        if (remain < 0) {
            throw new IllegalArgumentException("delete num error, curr num = " + getNum(item) + ", delete num = " + deleteNum);
        } else if (remain == 0) {
            deleteByPosition(pos);
        } else {
            updateNum(item, remain);
        }
        return item;
    }

    public int allocEmptyPosition() {
        for (int i = 1; i <= capacity; i++) {
            if (getByPosition(i) == null) {
                return i;
            }
        }
        return INVALID_POS;
    }

    /**
     * 整理背包
     */
    public void sort() {
        if (stackable) {
            compactBag(false);
            notifyChange();
        }
        List<T> list = getItems();
        Collections.sort(list, getComparator());

        SSort sSort = new SSort();
        sSort.bagtype = bagType;

        int position = 1;
        for (T t : list) {
            int oldPos = getPosition(t);
            if (oldPos != position) {
                sSort.itempos.put(oldPos, position);
            }
            position++;
        }
        if (!sSort.itempos.isEmpty()) {
            Transaction.tsendWhileCommit(roleid, sSort);
        }

        position = 1;
        for (T t : list) {
            int oldPos = getPosition(t);
            if (oldPos != position) {
                deleteByPosition(oldPos);
                T swapItem = putByPos(position, t);
                if (swapItem != null) {
                    putByPos(oldPos, swapItem);
                }
            }
            position++;
        }
        clearChange();
    }

    private void compactBag(boolean isIgnoreBind) {
        for (int i = 1; i <= capacity; i++) {
            T t1 = getByPosition(i);
            if (t1 == null) continue;
            int maxStack = getMaxStack(t1);
            int currNum = getNum(t1);
            int remainStack = maxStack - currNum;
            if (remainStack <= 0) continue;
            for (int j = i + 1; j <= capacity; j++) {
                T t2 = getByPosition(j);
                if (t2 == null || getModelId(t1) != getModelId(t2) || (!isIgnoreBind && isBind(t1) != isBind(t2))) {
                    continue;
                }
                int num2 = getNum(t2);
                if (num2 <= remainStack) {
                    remainStack -= num2;
                    deleteByPosition(j);
                } else {
                    updateNum(t2, num2 - remainStack);
                    remainStack = 0;
                    break;
                }
            }
            if (maxStack - currNum != remainStack) {
                updateNum(t1, maxStack - remainStack);
            }
        }
    }

    public ErrorCode unlockGrid(int unlockNum) {
        int remain = CfgMgr.bagconfig.get(bagType).maxcapacity - capacity;
        if (unlockNum <= 0 || unlockNum > remain) {
            return ErrorCode.PARAM_ERROR;
        }
        Condition condition = CfgMgr.bagconfig.get(bagType).unlockgridcost;
        ErrorCode errorCode = FCondition.checkA(roleid, condition, unlockNum, By.Bag_Cell_Open, 0, 0);
        if (errorCode.ok()) {
            capacity += unlockNum;
            addCapacity(unlockNum);
            Transaction.tsendWhileCommit(roleid, new SSyncCapacity(bagType, capacity));
        }
        return errorCode;
    }

    public ErrorCode split(int pos, int splitnumber) {
        T item = getByPosition(pos);
        int totalNum = getNum(item);
        if (!stackable || splitnumber <= 0 || splitnumber > totalNum) {
            return ErrorCode.PARAM_ERROR;
        }
        int emptyPos = allocEmptyPosition();
        if (emptyPos == INVALID_POS) {
            return ErrorCode.BAG_FULL;
        }
        if (totalNum == splitnumber) {
            return ErrorCode.OK;
        }

        if (deleteByPosition(pos, splitnumber) == null) {
            return ErrorCode.ITEM_NUMBER_NOT_ENOUGH;
        }
        putByPos(emptyPos, splitCopy(item, splitnumber));
        return ErrorCode.OK;
    }

    public List<T> getItemByModelId(int itemModelId) {
        return getItemByModelId(itemModelId, null);
    }

    public List<T> getItemByModelId(int itemModelId, Boolean isBind) {
        return getItems().stream()
                .filter(t -> getModelId(t) == itemModelId && (isBind == null || isBind(t) == isBind))
                .collect(Collectors.toList());
    }

    public int countItem(int modelId) {
        return countItem(modelId, null);
    }

    public int countItem(int modelId, Boolean isBind) {
        return countItem(modelId, isBind, null);
    }

    public int countItemByQuality(int quality) {
        return countItem(null, null, quality);
    }

    private int countItem(Integer modelId, Boolean isBind, Integer quality) {
        return getItems().stream()
                .filter(t -> (modelId == null || getModelId(t) == modelId) && (isBind == null || isBind(t) == isBind) && (quality == null || getQuality(t) == quality))
                .mapToInt(t -> getNum(t))
                .sum();
    }

    public ErrorCode batchSell(HashSet<Integer> posSet) {
        int goldSum = 0;
        Map<Integer, Integer> logItem = new HashMap<>();
        for (int pos : posSet) {
            T item = deleteByPosition(pos);
            if (item == null) {
                return ErrorCode.ITEM_NOT_FOUND;
            }
            logItem.put(getModelId(item), getNum(item));
            goldSum += getPrice(item) * getNum(item);
        }
        FRole.addXuNiBi(roleid, xtable.Roleinfos.get(roleid), goldSum, By.Item_Sell);
        FLogger.costitem(roleid, logItem, By.Item_Sell);
        return ErrorCode.OK;
    }

    public ErrorCode sell(int pos, int sellnum) {
        T item = deleteByPosition(pos, sellnum);
        if (item == null) {
            return ErrorCode.ITEM_NOT_FOUND;
        }
        if (sellnum <= 0 || sellnum > getNum(item)) {
            return ErrorCode.ITEM_NUMBER_NOT_ENOUGH;
        }
        int goldSum = Math.multiplyExact(getPrice(item), sellnum);
        FRole.addXuNiBi(roleid, xtable.Roleinfos.get(roleid), goldSum, By.Item_Sell);
        FLogger.costitem(roleid, Roleinfos.get(roleid), getModelId(item), sellnum, By.Item_Sell);
        FBag.broadcastDeleteEvent(roleid, bagType);
        return ErrorCode.OK;
    }

    public boolean transfer(int pos) {
        T item = deleteByPosition(pos);

        StorageBag<T> destBag = FBag.getStorageBag(roleid, BagModule.INSTANCE.getAssociatedBagOrDepot(this.bagType));
        int destPos = destBag.allocEmptyPosition();

        if (destPos == AbstractBag.INVALID_POS) {
            Transaction.tsend(roleid, FTips.create(LocationType.ALERT, isDepot() ? TipsCode.BAG_FULL : TipsCode.DEPOT_FULL));
            return false;
        }
        destBag.putByPos(destPos, item);

        // 不用自动同步，清理掉变化槽位
        destBag.clearChange();
        this.clearChange();

        SMove sMove = new SMove();
        sMove.srcbagtype = bagType;
        sMove.srcpos = pos;
        sMove.destbagtype = destBag.bagType;
        sMove.destpos = destPos;
        Transaction.tsendWhileCommit(roleid, sMove);
        return true;
    }

    public boolean isDepot(){
        return bagType == BagType.DEPOT_EQUIP
                || bagType == BagType.DEPOT_FRAGMENT
                || bagType == BagType.DEPOT_ITEM
                || bagType == BagType.DEPOT_TALISMAN;
    }

}
