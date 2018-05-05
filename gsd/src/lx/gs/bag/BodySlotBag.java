package lx.gs.bag;

import cfg.bag.BagType;
import lx.gs.event.EquipOnBodyEvent;
import lx.gs.event.EventModule;

/**
 * @author Jin Shuai
 */
public abstract class BodySlotBag<T> extends AbstractBag<T> {

    public BodySlotBag(long roleid, int bagType, int capacity) {
        super(roleid, bagType, capacity);
    }

    @Override
    public int getNum(T item){
        return 1;
    }

    @Override
    public void syncBagCapacity() {
        // fix value, do nothing
    }

    public boolean load(int pos, StorageBag<T> srcBag, int srcPos) {
        if (pos < 1 || pos > capacity)
            throw new IllegalArgumentException(String.format("param error : param { pos = %d }, capacity = %d", pos, capacity));

        T loadItem = srcBag.deleteByPosition(srcPos);
        if (loadItem == null) throw new IllegalArgumentException("onLoad item is null");

        T oldItem = putByPos(pos, loadItem);
        if (oldItem != null) {
            srcBag.putByPos(srcPos, oldItem);
        }
        if(bagType == BagType.EQUIP_BODY){
            EventModule.INSTANCE.broadcastEvent(new EquipOnBodyEvent(roleid));
        }
        return true;
    }

    public boolean unload(int pos, StorageBag<T> destBag) {
        if (pos < 1 || pos > capacity)
            throw new IllegalArgumentException(String.format("param error : param { pos = %d }, capacity = %d", pos, capacity));

        T item = deleteByPosition(pos);

        if (item == null) throw new IllegalArgumentException("unload item is null");

        final int emptyPos = destBag.allocEmptyPosition();
        if(emptyPos == AbstractBag.INVALID_POS){
            return false;
        }
        destBag.putByPos(emptyPos, item);
        return true;
    }

}
