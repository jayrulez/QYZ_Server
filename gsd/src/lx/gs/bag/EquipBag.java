package lx.gs.bag;

import cfg.Const;
import cfg.bag.BagType;
import cfg.equip.MainEquip;
import com.goldhuman.Common.Marshal.Marshal;
import lx.gs.equip.FEquip;
import xbean.Equip;
import xbean.RoleEquipBag;
import xbean.RoleEquipDepot;
import xtable.Roleequipdepot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jin Shuai
 */
public class EquipBag extends StorageBag<Equip> {

    /**
     * 2)	装备整理排序优先级：1门派（本门派>其他门派（默认顺序1青云门，2天音寺，3鬼王宗）
     * 3品质（红>橙>紫>蓝>绿）
     * 2等级（90>75>60>….每15一档）
     * 4部位（手镯>项链>左戒>右戒>武器>衣服>帽子>鞋）
     */
    private Comparator<Equip> SORT_COMPARATOR = (equip1, equip2) -> {
        xbean.RoleInfo info = xtable.Roleinfos.get(roleid);
        int professionId = info.getProfession();
        cfg.equip.Equip model1 = FEquip.getEquipModel(equip1);
        cfg.equip.Equip model2 = FEquip.getEquipModel(equip2);
        int prfsOrder1 = FEquip.isNormalEquip(model1) ? ((MainEquip) model1).professionlimit.profession : professionId;
        prfsOrder1 = prfsOrder1 == Const.NULL ? professionId : prfsOrder1;
        int prfsOrder2 = FEquip.isNormalEquip(model2) ? ((MainEquip) model2).professionlimit.profession : professionId;
        prfsOrder2 = prfsOrder2 == Const.NULL ? professionId : prfsOrder2;
        if (prfsOrder1 != prfsOrder2){
            return prfsOrder1 == professionId ? -1 : prfsOrder2 == professionId ? 1 : Integer.compare(prfsOrder1, prfsOrder2);
        }
        if (model1.quality != model2.quality) {
            return Integer.compare(model1.quality, model2.quality) * -1;
        }
        if (model1.level != model2.level){
            return Integer.compare(model1.level, model2.level) * -1;
        }
        return Integer.compare(BagModule.INSTANCE.getPosOnBody(model1.type), BagModule.INSTANCE.getPosOnBody(model1.type));
    };

    public EquipBag(long roleid, int bagType, int capacity) {
        super(roleid, bagType, capacity);
    }

    @Override
    public int getMaxStack(Equip item) {
        return 1;
    }

    @Override
    public int getNum(Equip item) {
        return 1;
    }

    @Override
    public void addCapacity(int add) {
        switch (bagType){
            case BagType.EQUIP:{
                RoleEquipBag bag = FEquip.getDbBag(roleid);
                bag.setCapacity(bag.getCapacity() + add);
                break;
            }
            case BagType.DEPOT_EQUIP:{
                RoleEquipDepot bag = Roleequipdepot.get(roleid);
                bag.setCapacity(bag.getCapacity() + add);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public long getId(Equip equip) {
        return equip.getEquipid();
    }

    @Override
    public int getModelId(Equip equip) {
        return equip.getModelid();
    }

    @Override
    public boolean isBind(Equip equip) {
        return equip.getIsbind();
    }

    @Override
    public int getPosition(Equip equip) {
        return equip.getPosition();
    }

    @Override
    public void setPosition(Equip equip, int pos) {
        equip.setPosition(pos);
    }

    @Override
    protected Comparator<Equip> getComparator() {
        return SORT_COMPARATOR;
    }

    @Override
    public Marshal convert(xbean.Equip equip) {
        return FEquip.convert(equip);
    }

    @Override
    protected List<Equip> createItem(int modelId, int num, Boolean isBind, Long expireTime) {
        List<Equip> ret = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            ret.add(FEquip.createEquip(roleid, modelId, isBind, expireTime));
        }
        return ret;
    }

    @Override
    public int getPrice(Equip equip) {
        return FEquip.getEquipModel(equip).prize;
    }

    @Override
    public long getExpiretime(Equip item) {
        return item.getExpiretime();
    }

    @Override
    protected int getQuality(Equip equip) {
        return FEquip.getEquipModel(equip).quality;
    }

}
