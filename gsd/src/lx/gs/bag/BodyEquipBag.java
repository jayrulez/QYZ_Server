package lx.gs.bag;

import cfg.bag.BagType;
import cfg.item.EItemType;
import com.goldhuman.Common.Marshal.Marshal;
import lx.gs.equip.FEquip;
import xbean.Equip;

/**
 * @author Jin Shuai
 */
public class BodyEquipBag extends BodySlotBag<Equip> {
    public static final int RIGHT_RING_POS = 6;
    private static final int CAPACITY = 8;

    public BodyEquipBag(long roleId) {
        super(roleId, BagType.EQUIP_BODY, CAPACITY);
    }

    @Override
    public void setPosition(Equip item, int pos) {
        item.setPosition(pos);
    }

    @Override
    public Marshal convert(Equip item) {
        return FEquip.convert(item);
    }

    @Override
    public int getPosition(Equip item) {
        return item.getPosition();
    }

    public int getPosByEquipType(int equipType){
        int destPos = BagModule.INSTANCE.getPosOnBody(equipType);
        if(equipType == EItemType.RING
                && getByPosition(destPos) != null
                && getByPosition(RIGHT_RING_POS) == null
                ){
            // 装载戒指时，如果左边戒指位有物品，右边戒指位没物品，就装载到右边，其他都装载到左边
            destPos = RIGHT_RING_POS;
        }
        return destPos;
    }

    @Override
    public int getModelId(Equip item) {
        return item.getModelid();
    }
}
