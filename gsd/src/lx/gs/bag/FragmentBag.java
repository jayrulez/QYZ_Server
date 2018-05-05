package lx.gs.bag;

import cfg.bag.BagType;
import cfg.equip.Equip;
import com.goldhuman.Common.Marshal.Marshal;
import lx.gs.equip.FEquip;
import lx.gs.fragment.FFragment;
import lx.gs.idgen.FIdGen;
import xbean.Fragment;
import xbean.Pod;
import xbean.RoleFragmentBag;
import xbean.RoleFragmentDepot;
import xtable.Rolefragmentdepot;
import xtable.Roleinfos;

import java.util.Comparator;
import java.util.List;

/**
 * @author Jin Shuai
 */
public class FragmentBag extends StorageBag<Fragment> {

    /**
     * 根据策划配置的排序规则，对碎片背包进行排序，可根据碎片的属性依次进行排序
     * 3)	装备碎片整理排序优先级：
     * 1完成状态（已有碎片数大于等于合成所需碎片数>已有碎片数小于等于合成所需碎片数，）
     * 2门派（本门派>其他门派（默认顺序1青云门，2天音寺，3鬼王宗）
     * 3品质（红>橙>紫>蓝>绿）
     * 3等级（90>75>60>….每15一档）
     * 4部位（手镯>项链>左戒>右戒>武器>衣服>帽子>鞋）
     * <p>
     * 先根据碎片累积的个数和碎片整合需要的个数做一个是否完成状态的判断，完成为1，未完成为0，
     *
     * @return
     */
    private Comparator<Fragment> SORT_COMPARATOR = (fragment1, fragment2) -> {
        cfg.equip.Fragment model1 = FFragment.getModelById(fragment1.getModelid());
        cfg.equip.Fragment model2 = FFragment.getModelById(fragment2.getModelid());
        boolean isCanCompound1 = fragment1.getCount() >= model1.number;
        boolean isCanCompound2 = fragment2.getCount() >= model2.number;
        if (isCanCompound1 && !isCanCompound2) return -1;
        if (!isCanCompound1 && isCanCompound2) return 1;
        int professionId = Roleinfos.selectProfession(roleid);
        int prfsOrder1 = model1.professionlimit.profession;
        int prfsOrder2 = model2.professionlimit.profession;
        if (prfsOrder1 != prfsOrder2)
            return prfsOrder1 == professionId ? -1 : prfsOrder2 == professionId ? 1 : Integer.compare(prfsOrder1, prfsOrder2);
        if (model1.quality != model2.quality) return Integer.compare(model1.quality, model2.quality) * -1;
        if (model1.level != model2.level) return Integer.compare(model1.level, model2.level) * -1;
        Equip equipModel1 = FEquip.getEquipModel(model1.equipID);
        Equip equipModel2 = FEquip.getEquipModel(model2.equipID);
        if(equipModel1 != null || equipModel2 != null){
            return equipModel1 == null ? 1
                    : (equipModel2 == null ? -1
                        : Integer.compare(BagModule.INSTANCE.getPosOnBody(equipModel1.type), BagModule.INSTANCE.getPosOnBody(equipModel2.type)));
        }
        return 0;
    };

    public FragmentBag(long roleid, int bagType, int capacity) {
        super(roleid, bagType, capacity);
    }

    @Override
    public void addCapacity(int add) {
        switch (bagType){
            case BagType.FRAGMENT:{
                RoleFragmentBag bag = FFragment.getDbBag(roleid);
                bag.setCapacity(bag.getCapacity() + add);
                break;
            }
            case BagType.DEPOT_FRAGMENT:{
                RoleFragmentDepot bag = Rolefragmentdepot.get(roleid);
                bag.setCapacity(bag.getCapacity() + add);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public long getId(Fragment item) {
        return item.getFragmentid();
    }

    @Override
    public int getMaxStack(Fragment item) {
        return FFragment.getMaxStack(FFragment.getModelById(item.getModelid()));
    }

    @Override
    public int getModelId(Fragment item) {
        return item.getModelid();
    }

    @Override
    public int getNum(Fragment item) {
        return item.getCount();
    }

    @Override
    public void setNum(Fragment item, int num) {
        if(num < 1 || num > getMaxStack(item)){
            throw new IllegalArgumentException("set wrong num : " + num + ", item modelid : " + item.getModelid());
        }
        item.setCount(num);
    }

    @Override
    public boolean isBind(Fragment item) {
        return item.getIsbind();
    }

    @Override
    public int getPosition(Fragment item) {
        return item.getPosition();
    }

    @Override
    public void setPosition(Fragment item, int pos) {
        item.setPosition(pos);
    }

    @Override
    protected Comparator<Fragment> getComparator() {
        return SORT_COMPARATOR;
    }

    @Override
    protected Fragment splitCopy(Fragment item, int num) {
        Fragment newFragment = Pod.newFragment();
        newFragment.setModelid(item.getModelid());
        newFragment.setPosition(INVALID_POS);
        newFragment.setCount(num);
        newFragment.setExpiretime(item.getExpiretime());
        newFragment.setIsbind(item.getIsbind());
        newFragment.setFragmentid(FIdGen.allocItemId(roleid));
        return newFragment;
    }

    @Override
    public Marshal convert(Fragment item) {
        return FFragment.convert(item);
    }

    @Override
    public int getPrice(Fragment item) {
        return FFragment.getModelById(item.getModelid()).prize;
    }

    @Override
    public long getExpiretime(Fragment item) {
        return item.getExpiretime();
    }

    @Override
    protected int getQuality(Fragment fragment) {
        return FFragment.getModelById(fragment.getModelid()).quality;
    }

    @Override
    protected List<Fragment> createItem(int modelId, int num, Boolean isBind, Long expireTime) {
        return FFragment.createFragment(roleid, modelId, num, isBind, expireTime);
    }
}
