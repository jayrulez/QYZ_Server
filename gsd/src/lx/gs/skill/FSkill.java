package lx.gs.skill;

import cfg.CfgMgr;
import cfg.role.EProfessionType;
import common.ErrorCode;
import lx.gs.achievement.FAchievement;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.map.MapModule;
import lx.gs.role.FRoleAttr;
import lx.gs.skill.msg.SEvolveSkill;
import lx.gs.skill.msg.SInfo;
import lx.gs.skill.msg.SUpgradeSkill;
import lx.gs.skill.msg.SkillInfo;
import map.msg.XUpgradeSkill;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class FSkill {
	
	/*
	 * 
	 * 技能养成(升级与进阶)部分
	 * 
	 */
	
	public static xbean.RoleSkill get(long roleid) {
		xbean.RoleSkill info = xtable.Roleskill.get(roleid);
		if(info == null) {
			info = xbean.Pod.newRoleSkill();
			xtable.Roleskill.insert(roleid, info);
		}
		return info;
	}
	
	public static SInfo createSInfo(long roleid) {
		final xbean.RoleSkill info = get(roleid);
		final SInfo re = new SInfo();
		
		for(Map.Entry<Integer, xbean.SkillInfo> e : info.getSkills().entrySet()) {
			final SkillInfo si = new SkillInfo();
			si.skillid = e.getKey();
			
			final xbean.SkillInfo xsi = e.getValue();
			si.level = xsi.getLevel();
			re.skills.add(si);
		}
		re.equipskillpositions.putAll(info.getEquipskillpositions());
		return re;
	}
	
	public static int getBaseEvolveSkillid(int skillid) {
		return SkillModule.skillid2BaseEvolveSkillid.get(skillid).baseEvolveSkillid;
	}

	public static cfg.skill.SkillAction getSkillAction(cfg.skill.ModelActions mcfg, String actionName) {
		final cfg.skill.SkillAction action = mcfg.skillactions.get(actionName);
		if(action != null)
			return action;
		return !mcfg.basemodelname.isEmpty() ? getSkillAction(CfgMgr.modelactions.get(mcfg.basemodelname), actionName) : null;
	}
	
	public static boolean isActive(int skillid) {
		return CfgMgr.skilldmg.containsKey(skillid);
	}

    public static int calcSpecialTotalSkillLevel(long roleid) {
        return get(roleid).getSkills().entrySet().stream().mapToInt(s -> s.getValue().getLevel() + SkillModule.skillid2BaseEvolveSkillid.get(s.getKey()).totalLevel).sum();
    }

	public static boolean checkNewAvaliableSkills(long roleid, boolean updateMap) {
		final xbean.RoleSkill info = get(roleid);
		final Map<Integer, xbean.SkillInfo> skills = info.getSkills();
		final Map<Integer, Integer> positions = info.getEquipskillpositions();
		
		final xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
		final int level = role.getLevel();
		
		final Set<Integer> curSkillids = info.getSkills().keySet().stream().map(skillid -> getBaseEvolveSkillid(skillid))
				.collect(Collectors.toSet());
		final cfg.skill.CareerSkillList pcfg = CfgMgr.careerskilllist.get(role.getProfession());
		boolean add = false;
		final int MAX_EQUIP_SKILL_NUM = cfg.skill.Const.MAX_EQUIP_SKILL_NUM;
		for(Integer skillid : pcfg.skilllist) {
			if(!curSkillids.contains(skillid)) {
				final cfg.skill.SkillLvlupCost ccfg = CfgMgr.skilllvlupcost.get(skillid);
				if(ccfg == null) continue;
				if(ccfg.skilllvlupdata.get(0).requirelvl <= level) {
					add = true;
					final xbean.SkillInfo si = xbean.Pod.newSkillInfo();
					si.setLevel(1);
					if(updateMap) {
						FMap.dispatchMessageInProcedure(roleid, new XUpgradeSkill(skillid, 1));
					}
					skills.put(skillid, si);
					if(isActive(skillid) && positions.size() < MAX_EQUIP_SKILL_NUM) {
						// 自动加入装备技能槽
						for(int i = 1 ; i <= MAX_EQUIP_SKILL_NUM ; i++) {
							if(!positions.containsValue(i)) {
								positions.put(skillid, i);
								break;
							}
						}
					}
				}
			}
		}
        if(add)
            FRoleAttr.updateRoleCombatPower(roleid);
		return add;
	}

	public static ErrorCode equipActiveSkill(long roleid, HashMap<Integer, Integer> equipActiveSkills) {
		if(equipActiveSkills.size() > cfg.skill.Const.MAX_EQUIP_SKILL_NUM)
			return ErrorCode.EXCEED_MAX_EQUIP_SKILL_NUM;
		final xbean.RoleSkill info = get(roleid);
		final Map<Integer, xbean.SkillInfo> skills = info.getSkills();
		final HashSet<Integer> usedPosition = new HashSet<>();
		for(Map.Entry<Integer, Integer> e : equipActiveSkills.entrySet()) {
			final Integer skillid = e.getKey();
			if(!skills.containsKey(skillid))
				return ErrorCode.NOT_INT_SKILL_LIST;
			if(!usedPosition.add(skillid))
				return ErrorCode.DUPLICATE_EQUIP_POSITION;
			if(!isActive(skillid))
				return ErrorCode.CANNOT_EQUIP_PASSIVE_SKILL;
		}
		final Map<Integer, Integer> positions = info.getEquipskillpositions();
		positions.clear();
		positions.putAll(equipActiveSkills);
		return ErrorCode.OK;
	}


	public static ErrorCode evolve(long roleid, int skillid, SEvolveSkill re) {
		final xbean.RoleSkill info = get(roleid);
		final Map<Integer, xbean.SkillInfo> skills = info.getSkills();
		final xbean.SkillInfo si = skills.remove(skillid);
		if(si == null)
			return ErrorCode.NOT_INT_SKILL_LIST;
		final cfg.skill.SkillLvlupCost curcfg = CfgMgr.skilllvlupcost.get(skillid);
		if(si.getLevel() < curcfg.skilllvlupdata.size())
			return ErrorCode.CUR_LEVEL_CANNOT_EVOLVE;
		final int newSkillid = curcfg.nextskillid;
		if(newSkillid == 0)
			return ErrorCode.NOT_NEXT_EVOLVE_SKILL;
		final cfg.skill.SkillLvlupCost nextcfg = CfgMgr.skilllvlupcost.get(newSkillid);
		
		final cfg.skill.SkillLvlupData costcfg = nextcfg.skilllvlupdata.get(0);
		
		final xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
		if(role.getLevel() < costcfg.requirelvl)
			return ErrorCode.NOT_ENOUGH_LEVEL;
		final ErrorCode err = FCondition.checkByReflection(roleid, costcfg, By.Skill_Upgrade);
		if(err.err())
			return err;
		si.setLevel(1);
		skills.put(newSkillid, si);
		final Map<Integer, Integer> positions = info.getEquipskillpositions();
		final Integer oldPos = positions.remove(skillid);
		if(oldPos != null) {
			positions.put(newSkillid, oldPos);
		}
		re.newskillid = newSkillid;
        FRoleAttr.updateRoleCombatPower(roleid);
		return ErrorCode.OK;
	}

	public static ErrorCode upgrade(long roleid, int skillid, SUpgradeSkill re) {
		final xbean.RoleSkill info = get(roleid);
		final Map<Integer, xbean.SkillInfo> skills = info.getSkills();
		final xbean.SkillInfo si = skills.get(skillid);
		if(si == null)
			return ErrorCode.NOT_INT_SKILL_LIST;
		final cfg.skill.SkillLvlupCost curcfg = CfgMgr.skilllvlupcost.get(skillid);
		final int maxLevel = curcfg.skilllvlupdata.size();
		final int newLevel = si.getLevel() + 1;
		if(newLevel > maxLevel)
			return ErrorCode.CANNOT_UPGRADE_MAX_LEVEL;
		final cfg.skill.SkillLvlupData costcfg = curcfg.skilllvlupdata.get(newLevel - 1);
		final xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
		if(role.getLevel() < costcfg.requirelvl)
			return ErrorCode.NOT_ENOUGH_LEVEL;
		
		final ErrorCode err = FCondition.checkByReflection(roleid, costcfg, By.Skill_Upgrade);
		if(err.err())
			return err;
		si.setLevel(newLevel);
		re.newlevel = newLevel;
        FRoleAttr.updateRoleCombatPower(roleid);
		{
			// 不是不想用Listener
			// 有时候实在太烦人了
			// 技能成就
			final cfg.skill.CareerSkillList ecfg = CfgMgr.careerskilllist.get(role.getProfession());
			if(ecfg.skilllist.size() == skills.size()
				&& skills.values().stream().mapToInt(s -> s.getLevel()).min().getAsInt() == newLevel) {
				FAchievement.updateCounter(roleid, cfg.achievement.CounterType.SKILL_LEVEL, newLevel);
			}
		}
		return ErrorCode.OK;
	}

	public static int getPrfsIdBySkillId(int skillid) {
		return SkillModule.skillid2BaseEvolveSkillid.get(skillid).profession;
	}
}
