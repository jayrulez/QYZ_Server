package lx.gs.talisman;

import cfg.CfgMgr;
import cfg.Const;
import cfg.bag.BagType;
import cfg.fight.AttackType;
import cfg.fight.AttrId;
import cfg.item.EItemBindType;
import cfg.item.ItemExperience;
import cfg.talisman.GetBuff;
import cfg.talisman.Property;
import cfg.talisman.TalismanBasic;
import cfg.talisman.TalismanFeed;
import com.goldhuman.Common.Marshal.Marshal;
import common.AttrUtils;
import common.ErrorCode;
import common.Utils;
import lx.gs.bag.AbstractBag;
import lx.gs.bag.BodyTalismanBag;
import lx.gs.bag.FBag;
import lx.gs.bag.TalismanBag;
import lx.gs.bag.msg.SItemBind;
import lx.gs.event.EventModule;
import lx.gs.event.TalismanCombatPowerChangeEvent;
import lx.gs.event.TalismanLevelUpEvent;
import lx.gs.event.TalismanStarLevelUpEvent;
import lx.gs.idgen.FIdGen;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.role.FRoleAttr;
import map.msg.XChangeTalisman;
import xbean.RoleTalismanBag;
import xbean.Talisman;
import xdb.Transaction;
import xtable.Roleinfos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static common.AttrUtils.newFinalAttrs;
import static common.AttrUtils.newRawAttrs;

public final class FTalisman {
    public final static int TALISMAN_NORMAL_MAX_LEVEL = CfgMgr.talismanexp.size();
    public final static int TALISMAN_STAR_MAX_LEVEL = CfgMgr.talismanevlove.size();

    private static Talisman createTalisman(int modelId) {
        cfg.talisman.TalismanBasic talismanconf = CfgMgr.talismanbasic.get(modelId);
        if(talismanconf == null){
            throw new IllegalArgumentException("Talisman config id not found in config. " + modelId);
        }
        xbean.Talisman newTalisman = xbean.Pod.newTalisman();
        newTalisman.setModelid(modelId);
        newTalisman.setWuxingtype(TalismanFeed.DEFAULT_WUXING);
        newTalisman.setIsbind(talismanconf.bindtype.bindtype != EItemBindType.NOT_BOUND);
        newTalisman.setAwakelevel(0);
        newTalisman.setNormallevel(1);
        newTalisman.setStarlevel(1);
        newTalisman.getSkills().putAll(CfgMgr.talismanskill.get(modelId).skillid.stream().collect(Collectors.toMap(skillid -> skillid, skillid -> 1)));
        return newTalisman;
    }

    public static boolean isRawTalisman(xbean.Talisman talisman) {
        return talisman.getAwakelevel() == 0 && talisman.getNormallevel() == 1
                && talisman.getStarlevel() == 1 && talisman.getWuxingvalue() == 0;
    }

    public static int randomLuckyType() {
        double r = common.Utils.random01() * TalismanModule.INSTANCE.totalLuckProbability;
        for (TalismanFeed f : CfgMgr.talismanfeed.values()) {
            if ((r -= f.probability) < 0)
                return f.id;
        }
        return TalismanFeed.DEFAULT_LUCK_ID;
    }

    /**
     * 将法宝星阶经验加到某个法宝上法宝提升星阶等级,有等级限制,如果合法，返回升阶后的等级，否则返回错误码
     *
     * @param roleid
     * @param addExp
     * @return
     */
    public static void addStarExp(long roleid, xbean.Talisman talisman, int addExp, int bagType) {
        final int oldExp = talisman.getStarexp();
        int newExp = oldExp + addExp;
        final int oldLvl = talisman.getStarlevel();
        int newLevel = oldLvl;

        final int roleLevel = xtable.Roleinfos.selectLevel(roleid);

        while (newLevel < TALISMAN_STAR_MAX_LEVEL) {
            final cfg.talisman.TalismanEvlove ecfg = CfgMgr.talismanevlove.get(newLevel);
            if (ecfg.levellimit > roleLevel || ecfg.requireexp > newExp) break;
            newExp -= ecfg.requireexp;
            newLevel++;
        }

        if (newExp != oldExp) {
            talisman.setStarexp(newExp);//星阶经验没有上限
        }
        if (newLevel != oldLvl) {
            talisman.setStarlevel(newLevel);
            FTalisman.syncCombatPower(roleid, bagType, talisman.getPos());
            EventModule.INSTANCE.broadcastEvent(new TalismanStarLevelUpEvent(roleid, talisman.getModelid(), talisman.getStarlevel()));
        }
    }

    public static void addNormalExp(long roleid, xbean.Talisman talisman, long addExp, int bagType) {
        final long oldExp = talisman.getNormalexp();
        long newExp = oldExp + addExp;
        final int oldLevel = talisman.getNormallevel();
        int newLevel = oldLevel;
        while (newLevel < Math.min(Roleinfos.selectLevel(roleid), FTalisman.TALISMAN_NORMAL_MAX_LEVEL)) {
            int needExp = Utils.selfOrMax(CfgMgr.talismanexp.get(newLevel).requireexp);
            if (needExp > newExp) break;
            newExp -= needExp;
            newLevel++;
        }

        if(newExp != oldExp){
            talisman.setNormalexp(newExp);
        }
        if (newLevel != oldLevel) {
            talisman.setNormallevel(newLevel);
            FTalisman.syncCombatPower(roleid, bagType, talisman.getPos());
            if(bagType == BagType.TALISMAN_BODY){
                EventModule.INSTANCE.broadcastEvent(new TalismanLevelUpEvent(roleid));
            }
        }
    }

    private final static int WuXings[] = new int[]{AttackType.METAL, AttackType.WOOD, AttackType.WATER, AttackType.FIRE, AttackType.EARTH};

    public static int randomWuXingType() {
        return WuXings[common.Utils.random().nextInt(WuXings.length)];
    }

    public static void setLuckyType(xbean.RoleTalismanBag bag, int luckType) {
        bag.setLuckytype(luckType);
        bag.setLuckywashtimes(0); //每次转运后，将在该转运类型下的洗练次数归0
    }

    /**
     * 根据运势的具体配置生成暴击倍数，如果返回1，表示没有产生暴击,暴击是一个整数
     *
     * @return
     */
    public static int genCriticalTimes(TalismanFeed conf) {
        return conf.criticalrate > common.Utils.random01() ?
                Math.round((float) common.Utils.randomRange(conf.criticallowerbound, conf.criticalupperbound))
                : 1;
    }

    public static int genNewWuxingValue(float oldWuxingValue, int criticalTimes, TalismanFeed conf) {
        return (int) (oldWuxingValue + criticalTimes * common.Utils.randomRange(conf.lowerbound, conf.upperbound));
    }

    public static long calcTotalNormalExp(xbean.Talisman talisman) {
        return talisman.getNormalexp() + TalismanModule.normalLevelNeedTotalExp[talisman.getNormallevel()];
    }

    public static int calcTotalStarExp(xbean.Talisman talisman) {
        return talisman.getStarexp() + TalismanModule.starLevelNeedTotalExp[talisman.getStarlevel()];
    }

    public static int calcTotalRawTalisman(xbean.Talisman talisman) {
        int num = 1; // 包括自己本身一个
        final cfg.talisman.TalismanAwake acfg = CfgMgr.talismanawake.get(talisman.getModelid());
        for (int level = 0; level < talisman.getAwakelevel(); level++) {
            num += acfg.awakeinfo.get(level).talismancost;
        }
        return num;
    }

    public static void syncProp(long roleId) {
        Map<Integer, Float> map = getTalismanProp(getEquipedTalisman(roleId));
        FRoleAttr.updateGroup(roleId, "talisman", map);
    }

    public static void syncCombatPower(long roleId, int bagtype, int pos) {
        Talisman talisman = getTalisman(roleId, bagtype, pos);
        int combatPower = getTalismanCombatPower(talisman);
        Transaction.tsendWhileCommit(roleId, new SSyncTalismanCombatPower(bagtype, pos, combatPower));
    }

    public static int getTalismanCombatPower(Talisman talisman){
        if(talisman == null) {
            return 0;
        }
        Map<Integer, Float> map = getTalismanProp(talisman);
        float[] finalAttrs = convert2FinalProp(map);
        int sum = FRoleAttr.calcCombatPower(finalAttrs, calcTotalSkillLevel(talisman));
        sum = (int) (sum * CfgMgr.battlepower.talismanrate * (1 + talisman.getAwakelevel() * CfgMgr.battlepower.talismanawakebonus));
        return sum;
    }

    public static float[] convert2FinalProp(Map<Integer, Float> prop){
        final float[] rawAttrs = newRawAttrs();
        prop.forEach((integer, aFloat) -> rawAttrs[integer] += aFloat);

        final float[] finalAttrs = newFinalAttrs();
        for(int i = 0 ; i < finalAttrs.length ; i++)
            AttrUtils.calcAttr(rawAttrs, finalAttrs, i, null);

        return finalAttrs;
    }

    public static Map<Integer, Float> getTalismanProp(Talisman talisman){
        Map<Integer, Float> attrs = new HashMap<>();
        if(talisman == null){
            return attrs;
        }
        final int modelid = talisman.getModelid();
        final cfg.talisman.TalismanBasic model = CfgMgr.talismanbasic.get(modelid);
        final cfg.fight.BasicAttr b = model.attr;
        final cfg.pet.Maturerate m = model.maturerate;
        final float levelRate = CfgMgr.talismanexp.get(talisman.getNormallevel()).maturerate;
        final float evolveRate = CfgMgr.talismanevlove.get(talisman.getStarlevel()).maturerate;
        final float multiRate = levelRate * evolveRate;
        // 基础属性
        common.Utils.addValue(attrs, AttrId.HP_FULL_VALUE, b.hp + m.hpmaturerate * multiRate);
        common.Utils.addValue(attrs, AttrId.MP_FULL_VALUE, b.mp + m.mpmaturerate * multiRate);
        common.Utils.addValue(attrs, AttrId.ATTACK_VALUE_MIN, b.attackvaluemin + m.minatkmaturerate * multiRate);
        common.Utils.addValue(attrs, AttrId.ATTACK_VALUE_MAX, b.attackvaluemax + m.maxatkmaturerate * multiRate);
        common.Utils.addValue(attrs, AttrId.DEFENCE, b.defence + m.defmaturerate * multiRate);
        common.Utils.addValue(attrs, AttrId.HIT_RATE, b.hitrate + m.hitmaturerate * multiRate);
        common.Utils.addValue(attrs, AttrId.HIT_RESIST_RATE, b.hitresistrate + m.hitresistmaturerate * multiRate);
        // 五行洗炼属性
        common.Utils.addValue(attrs, AttrId.ADDITIONAL_DAMAGE, (float) talisman.getWuxingvalue());
        // 觉醒属性
        AttrUtils.addValue(attrs, getAwakeProp(talisman.getModelid(), talisman.getAwakelevel()));
        return attrs;
    }

    private static Map<Integer, Float> getAwakeProp(int modelId, int awakeLevel){
        Map<Integer, Float> ret = new HashMap<>();
        for (int i = 1; i <= awakeLevel; i++) {
            CfgMgr.talismanawake.get(modelId).awakeinfo.get(i - 1).effect.stream()
                    .filter(awakeEffect -> awakeEffect.getTypeId() == Property.TYPEID)
                    .forEach(awakeEffect -> ((Property)awakeEffect).gainability
                            .forEach(prop -> ret.put(prop.propertytype, prop.value)));
        }
        return ret;
    }

    public static List<Integer> getAwakeBuff(int modelId, int awakeLevel){
        List<Integer> ret = new ArrayList<>();
        for (int i = 1; i <= awakeLevel; i++) {
            CfgMgr.talismanawake.get(modelId).awakeinfo.get(i - 1).effect.stream()
                    .filter(awakeEffect -> awakeEffect.getTypeId() == GetBuff.TYPEID)
                    .forEach(awakeEffect -> ret.add(((GetBuff)awakeEffect).buffid));
        }
        return ret;
    }

    public static void onEquipTalismanChange(long roleid) {
        syncToMap(roleid);
        syncProp(roleid);
        EventModule.INSTANCE.broadcastEvent(new TalismanCombatPowerChangeEvent(roleid, getTalismanCombatPower(getEquipedTalisman(roleid))));
    }

    public static void syncToMap(long roleId){
        final xbean.Talisman talisman = getEquipedTalisman(roleId);
        final XChangeTalisman msg = new XChangeTalisman();
        if (talisman != null) {
            msg.talismanmodelid = talisman.getModelid();
            msg.skills.putAll(talisman.getSkills());
            msg.buffs.addAll(getAwakeBuff(talisman.getModelid(), talisman.getAwakelevel()));
        }
        FMap.dispatchMessageInProcedure(roleId, msg);
    }

    public static int calcTotalSkillLevel(long roleid) {
        final Talisman talisman = getEquipedTalisman(roleid);
        return talisman != null ? calcTotalSkillLevel(talisman) : 0;
    }

    private static int calcTotalSkillLevel(Talisman talisman) {
        return talisman.getSkills().values().stream().mapToInt(t -> t).sum();
    }

    public static Marshal convert(Talisman talisman) {
        lx.gs.talisman.Talisman msg = new lx.gs.talisman.Talisman();
        msg.talismanid = talisman.getTalismanid();
        msg.pos = talisman.getPos();
        msg.modelid = talisman.getModelid();
        msg.isbind = talisman.getIsbind() ? Const.TRUE : Const.FALSE;
        msg.normalexp = talisman.getNormalexp();
        msg.normallevel = talisman.getNormallevel();
        msg.starexp = talisman.getStarexp();
        msg.starlevel = talisman.getStarlevel();
        msg.wuxingtype = talisman.getWuxingtype();
        msg.wuxingvalue = talisman.getWuxingvalue();
        msg.awakelevel = talisman.getAwakelevel();
        msg.skills.putAll(talisman.getSkills());
        msg.combatpower = getTalismanCombatPower(talisman);
        return msg;
    }

    public static BodyTalismanBag getBodyTalismanBag(long roleid) {
        return new BodyTalismanBag(roleid);
    }

    public static TalismanBag getTalismanBag(long roleid) {
        RoleTalismanBag talismanBag = getDbBag(roleid);
        return new TalismanBag(roleid, BagType.TALISMAN, talismanBag.getCapacity());
    }

    public static RoleTalismanBag getDbBag(long roleid) {
        return xtable.Roletalismanbag.get(roleid);
    }

    public static Talisman getTalisman(long roleid, int bagtype, int pos) {
        if (bagtype != BagType.TALISMAN && bagtype != BagType.TALISMAN_BODY) {
            return null;
        }
        AbstractBag<Talisman> bag = FBag.getBag(roleid, bagtype);
        return bag.getByPosition(pos);
    }

    public static void getRecycleItems(long normalExp, int starExp, Map<Integer, Integer> itemBagItems, Map<Integer, Integer> talismanBagItems) {
        for (cfg.talisman.RecycleExp r : cfg.CfgMgr.talismanrecycle.expitemid) {
            final long itemExp = ((cfg.cmd.action.TalismanExp) ((ItemExperience) CfgMgr.itembasic.get(r.itemkey)).effect).amount;
            int num = (int) (normalExp / itemExp);
            if (num > 0) {
                itemBagItems.put(r.itemkey, num);
                normalExp = normalExp % itemExp;
            }
        }
        for (cfg.talisman.RecycleExp r : CfgMgr.talismanrecycle.qualitytalismanid) {
            final int talismanexp = CfgMgr.talismanbasic.get(r.itemkey).qualityexp;
            int num = starExp / talismanexp;
            if (num > 0) {
                talismanBagItems.put(r.itemkey, num);
                starExp = starExp % talismanexp;
            }
        }
    }

    public static ErrorCode recycle(long roleid, int bagType, int pos) {
        if (!(bagType == BagType.TALISMAN || bagType == BagType.TALISMAN_BODY)) {
            return ErrorCode.PARAM_ERROR;
        }
        AbstractBag<Talisman> talismanBag = FBag.getBag(roleid, bagType);
        xbean.Talisman talisman = FBag.deleteItemByPos(talismanBag, pos, By.Talisman_Recycle);

        long normalExp = calcTotalNormalExp(talisman);
        int starExp = calcTotalStarExp(talisman);

        STalismanRecycle sTalismanRecycle = new STalismanRecycle();
        sTalismanRecycle.bagtype = bagType;
        sTalismanRecycle.pos = pos;

        getRecycleItems(normalExp, starExp, sTalismanRecycle.expitems, sTalismanRecycle.staritems);
        sTalismanRecycle.staritems.put(talisman.getModelid(), calcTotalRawTalisman(talisman));

        for (Map.Entry<Integer, Integer> entry : sTalismanRecycle.expitems.entrySet()) {
            if(!FBag.addItemToBag(roleid, BagType.ITEM, entry.getKey(), entry.getValue(), true, By.GuiYuan)){
                return ErrorCode.BAG_FULL;
            }
        }
        for (Map.Entry<Integer, Integer> entry : sTalismanRecycle.staritems.entrySet()) {
            if(!FBag.addItemToBag(roleid, BagType.TALISMAN, entry.getKey(), entry.getValue(), true, By.GuiYuan)){
                return ErrorCode.BAG_FULL;
            }
        }

        Transaction.tsendWhileCommit(roleid, sTalismanRecycle);
        return ErrorCode.OK;
    }

    public static Talisman getEquipedTalisman(long roleid) {
        return getBodyTalismanBag(roleid).getEquipedTalisman();
    }

    public static TalismanBasic getModelById(int modelid) {
        return CfgMgr.talismanbasic.get(modelid);
    }

    public static Talisman createTalisman(long roleId, int modelId, Boolean isBind, Long expireTime) {
        Talisman talisman = createTalisman(modelId);
        talisman.setTalismanid(FIdGen.allocItemId(roleId));
        if (isBind != null) talisman.setIsbind(isBind);
        if (expireTime != null) talisman.setExpiretime(expireTime);
        return talisman;
    }

    public static void bindTalisman(long roleId, int bagType, Talisman talisman){
        if(!talisman.getIsbind()){
            talisman.setIsbind(true);
            Transaction.tsendWhileCommit(roleId, new SItemBind(bagType, talisman.getPos()));
        }
    }
}
