package lx.gs.bag;

import cfg.bag.BagType;
import com.goldhuman.Common.Marshal.Marshal;
import lx.gs.talisman.FTalisman;
import xbean.Talisman;

/**
 * @author Jin Shuai
 */
public class BodyTalismanBag extends BodySlotBag<Talisman> {
    private static final int CAPACITY = 1;
    public static final int SINGLE_POS_INDEX = 1;

    public BodyTalismanBag(long roleId) {
        super(roleId, BagType.TALISMAN_BODY, CAPACITY);
    }

    @Override
    public Marshal convert(Talisman item) {
        return FTalisman.convert(item);
    }

    public boolean load(StorageBag<Talisman> srcBag, int srcPos) {
        return super.load(SINGLE_POS_INDEX, srcBag, srcPos);
    }

    public boolean unload(StorageBag<Talisman> destBag) {
        return super.unload(SINGLE_POS_INDEX, destBag);
    }

    @Override
    public void setPosition(Talisman item, int pos) {
        item.setPos(pos);
    }

    @Override
    public int getPosition(Talisman item) {
        return item.getPos();
    }


    public Talisman getEquipedTalisman(){
        return getByPosition(SINGLE_POS_INDEX);
    }

    @Override
    public int getModelId(Talisman item) {
        return item.getModelid();
    }
}
