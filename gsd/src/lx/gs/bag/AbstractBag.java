package lx.gs.bag;

import cfg.Const;
import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;
import gnet.link.Onlines;
import gs.Utils;
import lx.gs.bag.msg.SSyncCapacity;
import lx.gs.bag.msg.SSyncItems;
import xdb.Procedure;
import xdb.Transaction;

import java.util.*;

/**
 * @author Jin Shuai
 */
public abstract class AbstractBag<T> {
    public final static int INVALID_POS = Const.NULL;

    protected final long roleid;
    protected int capacity;
    protected int bagType;
    private final Map<Integer, T> items;
    protected Set<Integer> changePos = new HashSet<>();

    public AbstractBag(long roleid, int bagType, int capacity) {
        this.roleid = roleid;
        this.capacity = capacity;
        this.bagType = bagType;
        this.items = getDbData();
        /**
         * 将有变化的格子与客户端同步，在当前事务结束的时候会调用
         * 无需再手动调用 notifyChange()方法
         */
        Transaction.texecuteWhileCommit(new Procedure() {
            //重写此方法是为了保证物品变化协议先发送到客户端
            @Override
            public void execute() {
                call();
            }

            @Override
            protected boolean process() throws Exception {
                /** 注意：不用属性items中的数据，因为是items数据是在旧的事务中获取的，用getDbData()重新获取一遍 */
                if (Utils.isNotNull(changePos)) {
                    Onlines.getInstance().send(roleid, getSyncProto(getDbData()));
                }
                return true;
            }
        });
    }

    public abstract Marshal convert(T item);

    public abstract int getPosition(T item);

    public abstract void setPosition(T item, int pos);

    public abstract int getNum(T item);

    public abstract int getModelId(T item);

    private Map<Integer, T> getDbData() {
        return FBag.getDbData(roleid, bagType);
    }

    public int getBagType() {
        return bagType;
    }

    protected boolean isValidPos(int pos){
        return pos >= 1 && pos <= capacity;
    }

    public boolean clear() {
        for (int i = 1; i <= capacity; i++) {
            deleteByPosition(i);
        }
        return true;
    }

    protected T deleteByPosition(int pos) {
        if(!isValidPos(pos)){
            throw new IllegalArgumentException("wrong pos = " + pos + ", capactiy = " + capacity);
        }
        final T item = items.remove(pos);
        if (item != null) {
            changePos.add(pos);
            setPosition(item, INVALID_POS);
        }
        return item;
    }

    public T putByPos(int pos, T newItem) {
        if(!isValidPos(pos)){
            throw new IllegalArgumentException("wrong pos = " + pos + ", capactiy = " + capacity);
        }
        if (getByPosition(pos) == newItem) {
            return newItem;
        }
        setPosition(newItem, pos);
        final T oldItem = items.put(pos, newItem);
        if (oldItem != null) setPosition(oldItem, INVALID_POS);
        changePos.add(pos);
        return oldItem;
    }

    public List<T> getItems() {
        List<T> ret = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            T item = items.get(i);
            if (item != null) {
                ret.add(item);
            }
        }
        return ret;
    }

    public T getByPosition(int pos) {
        if (!isValidPos(pos)) return null;
        return items.get(pos);
    }

    protected int emptyGridNum() {
        int num = 0;
        for (int i = 1; i <= capacity; i++) {
            if(getByPosition(i) == null){
                num++;
            }
        }
        return num;
    }

    /**
     * 如果不需要自动同步，用其他协议同步的话，需要调用此方法避免无用的同步
     */
    protected void clearChange() {
        changePos.clear();
    }

    /**
     * 同步背包
     */
    public void syncBag() {
        syncBagCapacity();
        syncBagItems();
    }

    /**
     * 同步背包容量
     */
    public void syncBagCapacity() {
        xdb.Transaction.tsendWhileCommit(roleid, new SSyncCapacity(bagType, capacity));
    }

    /**
     * 同步背包物品
     */
    public void syncBagItems() {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= capacity; i++) {
            if(getByPosition(i) != null){
                set.add(i);
            }
        }
        if(set.isEmpty()){
            set.add(1); // 登录时客户端需要接收一次这个协议来初始化NEW状态设置
        }
        changePos.addAll(set);
    }

    /**
     * 用来在事务中调用
     */
    public void notifyChange() {
        if (Utils.isNotNull(changePos)) {
            SSyncItems sSyncItems = getSyncProto(items);
            Transaction.tsendWhileCommit(roleid, sSyncItems);
        }
    }

    private SSyncItems getSyncProto(Map<Integer, T> dbItemMap){
        SSyncItems sCover = new SSyncItems();
        sCover.bagtype = this.bagType;
        changePos.forEach(pos -> {
            if (isValidPos(pos)) {
                sCover.iteminfo.put(pos, serializeByPos(dbItemMap, pos));
            }
        });
        clearChange();
        return sCover;
    }

    private Octets serializeByPos(Map<Integer, T> dbItemMap, int pos) {
        T item = dbItemMap.get(pos);
        if (item == null) {
            return new Octets(0);
        }
        return convert(item).marshal(new OctetsStream());
    }

    public Octets serializeByPos(int pos) {
        T item = getByPosition(pos);
        if (item == null) {
            return new Octets(0);
        }
        return convert(item).marshal(new OctetsStream());
    }

}
