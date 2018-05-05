package lx.gs.bag;

import cfg.CfgMgr;
import cfg.bag.BagType;
import cfg.item.ItemBasic;
import com.goldhuman.Common.Marshal.Marshal;
import lx.gs.idgen.FIdGen;
import lx.gs.item.FItem;
import xbean.Item;
import xbean.Pod;
import xbean.RoleItemBag;
import xbean.RoleItemDepot;
import xtable.Roleitemdepot;

import java.util.Comparator;
import java.util.List;

/**
 * @author Jin Shuai
 */
public class ItemBag extends StorageBag<Item> {

    public static final Comparator<Item> SORT_COMPARATOR = (item1, item2) -> {
        ItemBasic model1 = CfgMgr.itembasic.get(item1.getModelid());
        ItemBasic model2 = CfgMgr.itembasic.get(item2.getModelid());
        int quality1 = model1.quality;
        int quality2 = model2.quality;
        if (quality1 != quality2) return Integer.compare(quality1, quality2) * -1;
        return Integer.compare(model1.level, model2.level) * -1;
    };

    public ItemBag(long roleid, int bagType, int capacity) {
        super(roleid, bagType, capacity);
    }

    @Override
    public long getId(Item item) {
        return item.getItemid();
    }

    @Override
    public int getMaxStack(Item item) {
        return FItem.getMaxStack(FItem.getModelById(getModelId(item)));
    }

    @Override
    public int getModelId(Item item) {
        return item.getModelid();
    }

    @Override
    public int getNum(Item item) {
        return item.getCount();
    }

    @Override
    public void setNum(Item item, int num) {
        if(num < 1 || num > getMaxStack(item)){
            throw new IllegalArgumentException("set wrong num : " + num + ", item modelid : " + item.getModelid());
        }
        item.setCount(num);
    }

    @Override
    public boolean isBind(Item item) {
        return item.getIsbind();
    }

    @Override
    public int getPosition(Item item) {
        return item.getPosition();
    }

    @Override
    public void setPosition(Item item, int pos) {
        item.setPosition(pos);
    }

    @Override
    protected Comparator<Item> getComparator() {
        return SORT_COMPARATOR;
    }

    @Override
    public void addCapacity(int add) {
        switch (bagType){
            case BagType.ITEM:{
                RoleItemBag bag = FItem.getDbBag(roleid);
                bag.setCapacity(bag.getCapacity() + add);
                break;
            }
            case BagType.DEPOT_ITEM:{
                RoleItemDepot bag = Roleitemdepot.get(roleid);
                bag.setCapacity(bag.getCapacity() + add);
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected Item splitCopy(Item item, int num) {
        Item newItem = Pod.newItem();
        newItem.setModelid(item.getModelid());
        newItem.setPosition(INVALID_POS);
        newItem.setCount(num);
        newItem.setExpiretime(item.getExpiretime());
        newItem.setIsbind(item.getIsbind());
        newItem.setItemid(FIdGen.allocItemId(roleid));
        return newItem;
    }

    @Override
    public Marshal convert(Item item) {
        return FItem.convert(item);
    }

    @Override
    public int getPrice(Item item) {
        return FItem.getModelById(item.getModelid()).prize;
    }

    @Override
    public long getExpiretime(Item item) {
        return item.getExpiretime();
    }

    @Override
    protected int getQuality(Item item) {
        return FItem.getModelById(item.getModelid()).quality;
    }

    @Override
    protected List<Item> createItem(int modelId, int num, Boolean isBind, Long expireTime) {
        return FItem.createItem(roleid, modelId, num, isBind, expireTime);
    }
}
