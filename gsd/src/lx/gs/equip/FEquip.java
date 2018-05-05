package lx.gs.equip;

import cfg.CfgMgr;
import cfg.Const;
import cfg.bag.BagType;
import cfg.equip.Accessory;
import cfg.equip.AccessoryProperty;
import cfg.equip.MainEquip;
import cfg.item.EItemBindType;
import cfg.item.EItemType;
import common.ErrorCode;
import common.Utils;
import lx.gs.bag.AbstractBag;
import lx.gs.bag.BodyEquipBag;
import lx.gs.bag.EquipBag;
import lx.gs.bag.FBag;
import lx.gs.bag.msg.SItemBind;
import lx.gs.idgen.FIdGen;
import lx.gs.map.FMap;
import lx.gs.role.FRoleAttr;
import lx.gs.role.msg.EquipInfo;
import map.msg.EquipBrief;
import map.msg.SChangeEquip;
import xbean.AccessoryProp;
import xbean.AccessoryWashResult;
import xbean.NormalEquip;
import xbean.Pod;
import xdb.Transaction;
import xtable.Roleequipbag;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jin Shuai
 */
public class FEquip {

    public static EquipBag getEquipBag(long roleId) {
        xbean.RoleEquipBag equipBag = getDbBag(roleId);
        return new EquipBag(roleId, BagType.EQUIP, equipBag.getCapacity());
    }

    public static xbean.RoleEquipBag getDbBag(long roleId) {
        return xtable.Roleequipbag.get(roleId);
    }

    public static BodyEquipBag getBodyBag(long roleId) {
        return new BodyEquipBag(roleId);
    }

    public static boolean isAccessory(xbean.Equip equip) {
        return isAccessory(getEquipModel(equip));
    }

    public static boolean isAccessory(cfg.equip.Equip model) {
        int equipType = model.type;
        return equipType == EItemType.BANGLE
                || equipType == EItemType.RING
                || equipType == EItemType.NECKLACE;
    }

    public static boolean isNormalEquip(xbean.Equip equip) {
        return isNormalEquip(getEquipModel(equip));
    }

    public static boolean isNormalEquip(cfg.equip.Equip model) {
        int equipType = model.type;
        return equipType == EItemType.WEAPON
                || equipType == EItemType.CLOTH
                || equipType == EItemType.HAT
                || equipType == EItemType.SHOE;
    }

    public static cfg.equip.Equip getEquipModel(xbean.Equip equip) {
        return equip == null ? null : getEquipModel(equip.getModelid());
    }

    public static cfg.equip.Equip getEquipModel(int modelId) {
        return CfgMgr.equip.get(modelId);
    }

    public static lx.gs.equip.Equip convert(xbean.Equip equip) {
        lx.gs.equip.Equip equipProto = new lx.gs.equip.Equip();
        equipProto.equipid = equip.getEquipid();
        equipProto.modelid = equip.getModelid();
        equipProto.isbind = equip.getIsbind() ? Const.TRUE : Const.FALSE;
        equipProto.expiretime = equip.getExpiretime();
        equipProto.position = equip.getPosition();
        equipProto.accessory.mainprop.addAll(equip.getAccessory().getMainprop().stream().map(FEquip::dbProp2Proto).collect(Collectors.toList()));
        equipProto.accessory.extraprop.addAll(equip.getAccessory().getExtraprop().stream().map(FEquip::dbProp2Proto).collect(Collectors.toList()));
        equipProto.normalequip.anneallevel = equip.getNormalequip().getAnneallevel();
        equipProto.normalequip.perfuselevel = equip.getNormalequip().getPerfuselevel();
        return equipProto;
    }

    public static PropInfo dbProp2Proto(AccessoryProp accessoryProp) {
        return new PropInfo(accessoryProp.getKey(), accessoryProp.getVal());
    }

    /**
     * 根据提供的装备key创建响应的xdb Bean对象
     *
     * @return
     */

    private static xbean.Equip createEquip(int modelId) {
        cfg.equip.Equip model = cfg.CfgMgr.equip.get(modelId);
        if (null == model)
            throw new IllegalArgumentException("equip config id not found in config. " + modelId);
        if (model.level < 0)
            throw new IllegalArgumentException("equip config initlevel error. itemid=" + modelId);
        xbean.Equip e = xbean.Pod.newEquip();
        e.setModelid(modelId);
        e.setPosition(AbstractBag.INVALID_POS);
        e.setIsbind(model.bindtype.bindtype != EItemBindType.NOT_BOUND);
        if (isAccessory(model)) {
            genAccessoryProp(e);
        }
        return e;
    }

    public static xbean.Equip createEquip(long roleId, int modelId, Boolean isBind, Long expiretime) {
        xbean.Equip e = createEquip(modelId);
        e.setEquipid(FIdGen.allocItemId(roleId));
        if (isBind != null) e.setIsbind(isBind);
        if (expiretime != null) e.setExpiretime(expiretime);
        return e;
    }

    public static xbean.Equip createEquip(long roleId, int modelId, boolean isBind) {
        return createEquip(roleId, modelId, isBind, null);
    }

    /**
     * 根据属性池配置的属性权重，随机获取一个属性给饰品
     */
    public static void genAccessoryProp(xbean.Equip equip) {
        if (!isAccessory(equip)) {
            return;
        }
        Accessory model = (Accessory) getEquipModel(equip);

        List<AccessoryProp> mainProp = equip.getAccessory().getMainprop();
        addProp(mainProp, model.mainproperty, model.rankweight);
        addProp(mainProp, model.mainproperty2, model.rankweight);

        List<AccessoryProp> extraProp = equip.getAccessory().getExtraprop();
        for (int i = 0; i < Accessory.VICE_PROPERTY_NUM; i++) {
            addProp(extraProp, model.viceproperty, model.rankweight);
        }
    }

    private static void addProp(List<AccessoryProp> prop, List<Integer> propPoolIdList, List<Integer> propLevelRates) {
        List<AccessoryProperty> propList = new ArrayList<>();
        propPoolIdList.forEach(integer -> propList.addAll(CfgMgr.accessorygenerate.get(integer).propertylist));

        List<Integer> rateList = new ArrayList<>();
        propList.forEach(accessoryProperty -> rateList.add(accessoryProperty.weight));
        int index = common.Utils.getRandomIndex(rateList);

        cfg.equip.AccessoryProperty findProp = propList.get(index);
        float propVal = genPropVal(findProp.minvalue, findProp.maxvalue, propLevelRates);

        AccessoryProp addProp = Pod.newAccessoryProp();
        addProp.setKey(findProp.propertyid);
        addProp.setVal(propVal);

        prop.add(addProp);
    }

    /**
     * * 生成属性对应的值，该值的生成过程参考配置的属性档权重，以及配置的属性最大值最小值
     * 1）把最大值和最小值之间的值平均分为n个属性档
     * 2）根据属性档的权重随机选择其中一档设置为该属性的值
     *
     * @param min
     * @param max
     * @param weights
     * @return
     */
    private static float genPropVal(float min, float max, List<Integer> weights) {
        int index = common.Utils.getRandomIndex(weights);
        index++;
        return min + (max - min) / weights.size() * index;
    }

    public static void updateEquipRoleAttr(long roleid) {
        final Map<Integer, Float> attrs = new HashMap<>();
        final Map<Integer, Integer> suitNums = new HashMap<>();

        getBodyBag(roleid).getItems().stream()
                .forEach(equip -> {
                    if(isNormalEquip(equip)){
                        common.Utils.addValue(attrs, getNormalEquipProp(equip));
                        final int suitid = EquipModule.INSTANCE.getEquipSuit(equip.getModelid());
                        if (suitid != 0) {
                            common.Utils.addValue(suitNums, suitid, 1);
                        }
                    } else if(isAccessory(equip)){
                        equip.getAccessory().getMainprop().forEach(prop ->
                                common.Utils.addValue(attrs, prop.getKey(), prop.getVal()));
                        equip.getAccessory().getExtraprop().forEach(prop ->
                                common.Utils.addValue(attrs, prop.getKey(), prop.getVal()));
                    }
                });

        suitNums.forEach((k, v) ->
                CfgMgr.equipsuits.get(k).suitsbonus.stream()
                        .filter(data -> data.amountlimit <= v)
                        .forEach(data -> common.AttrUtils.addAttrs(attrs, data.propertydata))
        );
        FRoleAttr.updateGroup(roleid, "equip", attrs);
    }

    public static List<EquipInfo> genEquipInfo(long roleid, ArrayList<EquipInfo> ret) {
        Roleequipbag.getTable().select(roleid, roleEquipBag -> {
            for (xbean.Equip equip : roleEquipBag.getEquiponbodymap().values()) {
                final EquipInfo e = new EquipInfo();
                e.modelid = equip.getModelid();
                e.accessory.mainprop.addAll(equip.getAccessory().getMainprop().stream().map(FEquip::dbProp2Proto).collect(Collectors.toList()));
                e.accessory.extraprop.addAll(equip.getAccessory().getExtraprop().stream().map(FEquip::dbProp2Proto).collect(Collectors.toList()));
                e.normalequip.anneallevel = equip.getNormalequip().getAnneallevel();
                e.normalequip.perfuselevel = equip.getNormalequip().getPerfuselevel();
                ret.add(e);
            }
            return roleEquipBag;
        });
        return ret;
    }

    public static Map<Integer, Float> getNormalEquipProp(xbean.Equip e) {
        final Map<Integer, Float> result = new HashMap<>();
        if (!isNormalEquip(e))
            return result;
        final int modelId = e.getModelid();
        final int annealLevel = e.getNormalequip().getAnneallevel();
        final int perfuseLevel = e.getNormalequip().getPerfuselevel();

        //model属性
        common.AttrUtils.addAttrs(result, ((MainEquip) getEquipModel(modelId)).property);

        //获取炼器等级附加的值
        CfgMgr.equipanneal.get(modelId).adddata.forEach(data -> Utils.addValue(result, data.addpropertyid, (float) data.addvalues.get(annealLevel)));
        //获取灌注等级附加的值
        CfgMgr.equipappend.get(modelId).adddata.forEach(data -> Utils.addValue(result, data.addpropertyid, (float) data.addvalues.get(perfuseLevel)));

        //获取bonus附加的值
        CfgMgr.annealbonus.get(modelId).bonus.stream()
                .filter(data -> data.bonuslevel <= annealLevel)
                .forEach(data -> common.AttrUtils.addAttrs(result, data.bonusvalue));
        return result;
    }


    public static ArrayList<EquipBrief> getEquipBrief(long roleid, ArrayList<EquipBrief> briefs) {
        Roleequipbag.getTable().select(roleid, info -> {
            for (xbean.Equip equip : info.getEquiponbodymap().values()) {
                if (isNormalEquip(equip)) {
                    briefs.add(new EquipBrief(equip.getModelid(), equip.getNormalequip().getAnneallevel(), equip.getNormalequip().getPerfuselevel()));
                }
            }
            return info;
        });
        return briefs;
    }

    public static void onBodyChange(long roleid) {
        updateEquipRoleAttr(roleid);
        FMap.dispatchMessageInProcedure(roleid, new SChangeEquip(getEquipBrief(roleid, new ArrayList<>())));
    }

    public static void clearWash(AccessoryWashResult lastwashrecord) {
        if (lastwashrecord != null) {
            lastwashrecord.setOldpropindex(Const.NULL);
            lastwashrecord.getNewprop().setKey(0);
            lastwashrecord.getNewprop().setVal(0);
        }
    }

    public static xbean.Equip getEquip(long roleid, int bagtype, int pos) {
        if (!(bagtype == BagType.EQUIP_BODY || bagtype == BagType.EQUIP)) {
            return null;
        }
        AbstractBag<xbean.Equip> bag = FBag.getBag(roleid, bagtype);
        return bag.getByPosition(pos);
    }

    public static boolean applyWash(xbean.Equip equip) {
        if (!isAccessory(equip)) return false;

        AccessoryWashResult lastWash = equip.getAccessory().getLastwashrecord();
        int oldpropindex = lastWash.getOldpropindex();
        AccessoryProp newProp = lastWash.getNewprop();

        if(!equip.getIsbind() && lastWash.getNeedbind()){
            equip.setIsbind(true);
        }

        List<AccessoryProp> propList = equip.getAccessory().getExtraprop();
        if (oldpropindex == Const.NULL || propList.get(oldpropindex) == null) return false;

        AccessoryProp updateProp = propList.get(oldpropindex);
        updateProp.setKey(newProp.getKey());
        updateProp.setVal(newProp.getVal());
        clearWash(lastWash);
        return true;
    }

    public static int getMaxPerfuseLevel(cfg.equip.Equip model) {
        return CfgMgr.enhanceconfig.get(model.level).appendlimit;
    }

    public static int getMaxAnnealLevel(cfg.equip.Equip model) {
        return Math.min(CfgMgr.enhanceconfig.get(model.level).anneallimit, cfg.equip.Equip.MAX_ANNEAL_LEVEL);
    }

    public static void bindOnEquip(long roleId, xbean.Equip equip, int bagType, int pos) {
        if(!equip.getIsbind()){
            equip.setIsbind(true);
            Transaction.tsendWhileCommit(roleId, new SItemBind(bagType, pos));
        }
    }

    public static ErrorCode swapEquipProp(xbean.Equip e1, xbean.Equip e2, boolean isSwapAnnel, boolean isSwapPerfuse){
        cfg.equip.MainEquip model1 = (MainEquip) getEquipModel(e1);
        cfg.equip.MainEquip model2 = (MainEquip) getEquipModel(e2);
        if(model1.type != model2.type
                || (model1.professionlimit.profession != model2.professionlimit.profession
                        && model1.professionlimit.profession != Const.NULL
                        && model2.professionlimit.profession != Const.NULL)
                ){
            return ErrorCode.PARAM_ERROR;
        }

        NormalEquip equip1 = e1.getNormalequip();
        NormalEquip equip2 = e2.getNormalequip();

        //交换两个装备的炼器等级和追加等级
        if(isSwapAnnel){
            if(!canSwapAnneal(e1,e2)){
                return ErrorCode.EQUIP_ANNEAL_NOT_VALID;
            }
            int temp = equip1.getAnneallevel();
            equip1.setAnneallevel(equip2.getAnneallevel());
            equip2.setAnneallevel(temp);
        }
        if(isSwapPerfuse){
            if(!canSwapPerfuse(e1,e2)){
                return ErrorCode.EQUIP_PERFUSE_NOT_VALID;
            }
            int temp = equip1.getPerfuselevel();
            equip1.setPerfuselevel(equip2.getPerfuselevel());
            equip2.setPerfuselevel(temp);
        }
        return ErrorCode.OK;
    }

    public static boolean canSwapAnneal(xbean.Equip e1, xbean.Equip e2){
        int currLevel1 = e1.getNormalequip().getAnneallevel();
        int currLevel2 = e2.getNormalequip().getAnneallevel();

        int maxLevel1 = getMaxAnnealLevel(getEquipModel(e1));
        int maxLevel2 = getMaxAnnealLevel(getEquipModel(e2));
        return currLevel1 <= maxLevel2 && currLevel2 <= maxLevel1;
    }
    public static boolean canSwapPerfuse(xbean.Equip e1, xbean.Equip e2){
        int currLevel1 = e1.getNormalequip().getPerfuselevel();
        int currLevel2 = e2.getNormalequip().getPerfuselevel();

        int maxLevel1 = getMaxPerfuseLevel(getEquipModel(e1));
        int maxLevel2 = getMaxPerfuseLevel(getEquipModel(e2));
        return currLevel1 <= maxLevel2 && currLevel2 <= maxLevel1;
    }
}
