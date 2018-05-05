package lx.gs.skill;

import cfg.CfgMgr;
import sun.util.resources.cldr.sk.CurrencyNames_sk;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public enum SkillModule implements gs.Module, gs.RoleLoginListener, gs.LevelUpListener {
	INSTANCE;

    public final static class SkillExtraInfo {
        public final int skillid;
        public final int baseEvolveSkillid;
        public final int totalLevel; // 从最低级别技能进阶到此技能需要升级次数
        public int profession;
        public SkillExtraInfo(int skillid, int baseid, int totalLevel) {
            this.skillid = skillid;
            this.baseEvolveSkillid = baseid;
            this.totalLevel = totalLevel;
        }
    }
	public static final HashMap<Integer, SkillExtraInfo> skillid2BaseEvolveSkillid = new HashMap<>();

	public void prepareConf() {

		final HashSet<Integer> baseEvolveSkillids = new HashSet<>(CfgMgr.skilllvlupcost.keySet());
		// 计算所有技能的基础进阶等级技能
		for(cfg.skill.SkillLvlupCost scfg : CfgMgr.skilllvlupcost.values()) {
			if(scfg.nextskillid > 0)
				baseEvolveSkillids.remove(scfg.nextskillid);
		}

		for(int baseid : baseEvolveSkillids) {
			int curSkillid = baseid;
            int totalLevel = 0;
			do {
				skillid2BaseEvolveSkillid.put(curSkillid, new SkillExtraInfo(curSkillid, baseid, totalLevel));
                final cfg.skill.SkillLvlupCost ccfg = CfgMgr.skilllvlupcost.get(curSkillid);
				curSkillid = ccfg.nextskillid;
                totalLevel += ccfg.skilllvlupdata.size();
			} while(curSkillid > 0);
		}

        for(cfg.skill.Skilldmg scfg : CfgMgr.skilldmg.values()) {
            skillid2BaseEvolveSkillid.putIfAbsent(scfg.id, new SkillExtraInfo(scfg.id, scfg.id, 0));
        }

        for(cfg.skill.PassiveSkill scfg : CfgMgr.passiveskill.values()) {
            skillid2BaseEvolveSkillid.putIfAbsent(scfg.id, new SkillExtraInfo(scfg.id, scfg.id, 0));
        }

		CfgMgr.careerskilllist.values().forEach(careerSkillList -> {
			careerSkillList.skilllist.forEach(skillid -> skillid2BaseEvolveSkillid.get(skillid).profession = careerSkillList.career);
		});


	}

	public void start() {
        prepareConf();
	}
	
	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		FSkill.checkNewAvaliableSkills(roleid, false);
		xdb.Transaction.tsend(roleid, FSkill.createSInfo(roleid));
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {


	}

	@Override
	public void onLevelUp(long roleid, int oldLevel, int newLevel) {
		if(FSkill.checkNewAvaliableSkills(roleid, true)) {
			xdb.Transaction.tsend(roleid, FSkill.createSInfo(roleid));
		}
	}
}
