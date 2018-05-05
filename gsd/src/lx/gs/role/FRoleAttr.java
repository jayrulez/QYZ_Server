package lx.gs.role;

import cfg.CfgMgr;
import cfg.fight.AttrId;
import common.AttrUtils;
import lx.gs.amulet.FAmulet;
import lx.gs.skill.FSkill;
import lx.gs.talisman.FTalisman;
import xtable.Roleattrs;

import java.util.Map;

import static common.AttrUtils.addValue;
import static common.AttrUtils.newRawAttrs;

/**
 * Created by huangqiang on 2016/1/28.
 */
public class FRoleAttr {
    public static xbean.RoleAttr get(long roleid) {
        return xtable.Roleattrs.get(roleid);
    }

    public static int calcCombatPower(float[] finalattrs, int totalSkillLevel) {
        final cfg.fight.BattlePower c = CfgMgr.battlepower;
        final int combatPower = (int)(
                (finalattrs[AttrId.HP_FULL_VALUE] * c.hp
                + finalattrs[AttrId.MP_FULL_VALUE] * c.mp
                + finalattrs[AttrId.ATTACK_VALUE_MIN] * c.minatk
                + finalattrs[AttrId.ATTACK_VALUE_MAX] * c.maxatk
                + finalattrs[AttrId.DEFENCE] * c.defence
                + finalattrs[AttrId.HIT_RATE] * c.hit
                + finalattrs[AttrId.HIT_RESIST_RATE] * c.hitresist
                + finalattrs[AttrId.ADDITIONAL_DAMAGE] * c.addtionaldamage
                )
               *(1 +
                      (finalattrs[AttrId.CRIT_VALUE] + finalattrs[AttrId.CRIT_RESIST_VALUE])
                      *(finalattrs[AttrId.CRIT_RATE] + finalattrs[AttrId.CRIT_RESIST_RATE]) * c.crit)
               *(1 +
                        (finalattrs[AttrId.EXCELLENT_VALUE] + finalattrs[AttrId.EXCELLENT_RESIST_VALUE])
                       *(finalattrs[AttrId.EXCELLENT_RATE] + finalattrs[AttrId.EXCELLENT_RESIST_RATE]) * c.excellent)
               *(1 + finalattrs[AttrId.LUCKY_VALUE] * c.luck)
               *(1 + (finalattrs[AttrId.ABNORMAL_HIT_RATE] + finalattrs[AttrId.ABNORMAL_RESIST_RATE]) * c.abnormal)
               *(1 + totalSkillLevel * c.skilllevel)
        );
        return combatPower;
    }

    public static int getRoleCombatPower(long roleId){
        xbean.RoleAttr info = Roleattrs.select(roleId);
        return info.getTotalcombatpower();
    }

    public static void updateRoleCombatPower(long roleid) {
        updateRoleCombatPower(get(roleid));
    }

    public static void updateRoleCombatPower(xbean.RoleAttr info) {
        final float[] rawAttrs = newRawAttrs();
        for(xbean.GroupAttr groupAttr : info.getGroupattrs().values()) {
            addValue(rawAttrs, groupAttr.getAttrs());
        }
        AttrUtils.fill(info.getRawattrs(), rawAttrs);

        final float[] finalAttrs = AttrUtils.calcFinalAttrs(rawAttrs);
        AttrUtils.fill(info.getFinalattrs(), finalAttrs);

        final long roleid = info.getRoleid();
        final int totalSkillLevel = FSkill.calcSpecialTotalSkillLevel(roleid) + FTalisman.calcTotalSkillLevel(roleid)
                + common.Utils.sumInt(FAmulet.getPrfsSkillAddInfo(roleid).values());
        final int roleCombatPower = calcCombatPower(finalAttrs, totalSkillLevel);
        if(roleCombatPower != info.getRolecombatpower()) {
            final int newTotalCombatPower = info.getPetcombatpower() + roleCombatPower;
            info.setTotalcombatpower(newTotalCombatPower);
            info.setRolecombatpower(roleCombatPower);
//            xdb.Trace.info("updateRoleCombatPower. roleid:{} rolecombatpower:{} totalcombatpower:{}", info.getRoleid(), roleCombatPower, newTotalCombatPower);
        }
    }

    public static void updatePetCombatPower(xbean.RoleAttr info, int newCombatPower) {
        if(info.getPetcombatpower() != newCombatPower) {
            final int newTotalCombatPower = info.getRolecombatpower() + newCombatPower;
            info.setTotalcombatpower(newTotalCombatPower);
            info.setPetcombatpower(newCombatPower);
//            xdb.Trace.info("updatePetCombatPower. roleid:{} rolecombatpower:{} totalcombatpower:{}", info.getRolecombatpower(), newCombatPower, newTotalCombatPower);
        }
    }

    public static void updateGroupAndResetHPMP(long roleid, String group, Map<Integer, Float> attrs, boolean resetHPMP) {
        updateGroup(roleid, group, attrs);
        if(resetHPMP) {
            final xbean.RoleAttr info = xtable.Roleattrs.get(roleid);
            info.setResethpmp(1);
        }
    }

    public static void updateGroup(long roleid, String group, Map<Integer, Float> attrs) {
        final xbean.RoleAttr info = xtable.Roleattrs.get(roleid);
        xbean.GroupAttr groupAttr = info.getGroupattrs().get(group);
        if(groupAttr == null) {
            groupAttr = xbean.Pod.newGroupAttr();
            info.getGroupattrs().put(group, groupAttr);
        }
        AttrUtils.fill(groupAttr.getAttrs(), attrs);
    }
}
