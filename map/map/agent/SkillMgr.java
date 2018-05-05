package map.agent;

import cfg.CfgMgr;
import cfg.fight.Relation;
import cfg.skill.*;
import common.ErrorCode;
import map.buff.Buff;
import map.buff.effect.SkillEffect;
import map.map.GameMap;
import map.msg.SSkillInterrupt;
import map.skill.Skill;
import pathfinding.Vector3;
import xdb.Trace;

import java.util.*;
import java.util.stream.Collectors;

public final class SkillMgr {

    public final static class SkillInfo {
		public final cfg.skill.SkillAction skillAction;
		public final cfg.skill.Skilldmg skilldmg;
		public final int endAttackTime;
        public final int target;
		public SkillInfo(cfg.skill.Skilldmg skilldmg, cfg.skill.SkillAction skillAction, int target, int endAttackTime) {
			this.skilldmg = skilldmg;
			this.skillAction = skillAction;
			this.endAttackTime = endAttackTime;
            this.target = target;
		}
	}

	private final static class ActiveSkill {
		public int curIndex;
		public int level;
		public long activeTime;
		public int expirecd;
		public final int skillid;
        public double skillRange;
		public final List<SkillInfo> skills = new ArrayList<>();
		public ActiveSkill(int skillid, int level, long activeTime) {
			this.skillid = skillid;
			this.level = level;
            this.activeTime = activeTime;
		}
	}

	private final static class Location {
		public final ActiveSkill activeSkill;
		public final int index;
		public Location(ActiveSkill activeSkill, int index) {
			this.activeSkill = activeSkill;
			this.index = index;
		}
	}

	private final static class PassiveSkill {
        public final int level;
		public final map.buff.Buff buff;
		public PassiveSkill(int level, map.buff.Buff buff) {
            this.level = level;
			this.buff = buff;
		}
	}
	
	private final Fighter self;

	private cfg.skill.ModelActions macfg;
	private cfg.skill.SkillAction roleFabaoSkillAction;
	private Map<Integer, Integer> roleSkills;
	private Map<Integer, Integer> fabaoSkills;
	private Map<Integer, Integer> amulets;

	private final Map<Integer, ActiveSkill> activeSkills = new HashMap<>();
	private final Map<Integer, Location> activeSkillLocations = new HashMap<>();

	private final Map<Integer, PassiveSkill> passiveSkills = new HashMap<>();

    private final List<SkillEffect> extraEffects = new ArrayList<>();

	private Skill curPerformSkill;
	private double normalAttackDistance;
	public SkillMgr(Fighter self) {
		this.self = self;
		this.curPerformSkill = null;
	}
	
	public void init(String modelName, String fabaoActionName, Map<Integer, Integer> roleSkills, Map<Integer, Integer> fabaoSkills, Map<Integer, Integer> amulets, Map<Integer, Long> skillCds) {
		this.macfg = CfgMgr.modelactions.get(modelName);
		this.roleFabaoSkillAction = Skill.getSkillAction(macfg, fabaoActionName);
		this.roleSkills = roleSkills;
		this.fabaoSkills = fabaoSkills;
		this.amulets = amulets;

		addSkills(roleSkills, skillCds);
		addSkills(fabaoSkills, skillCds);
		this.normalAttackDistance = calcNormalAttackDistance();
	}

    public void fillSkills(HashMap<Integer, Integer> skills) {
        activeSkills.forEach((id, s) -> skills.put(id, s.level));
        passiveSkills.forEach((id, s) -> skills.put(id, s.level));
    }

    public void fillContext(Map<Integer, Long> skills) {
        final long now = self.getNow();
        for(ActiveSkill as : activeSkills.values()) {
            if(as.activeTime > now)
                skills.put(as.skillid, as.activeTime);
        }
    }

	private float calcNormalAttackDistance() {
		return activeSkills.values().stream().flatMap(e -> e.skills.stream()).filter(s -> s.skilldmg.isnormal).findFirst()
				.map(s -> s.skillAction.attackrange).orElse(1.0f);
	}

	public void init(String modelName, Map<Integer, Integer> roleSkills) {
		init(modelName, "", roleSkills, Collections.emptyMap(), Collections.emptyMap(), Collections.emptyMap());
	}
	
	private void addSkills(Map<Integer, Integer> skills, Map<Integer, Long> activeTimes) {
		skills.entrySet().forEach(e -> addSkill(e.getKey(), e.getValue(), activeTimes.getOrDefault(e.getKey(), 0L)));
	}

	private void addSkill(int skillid, int level, long activeTime) {
		if(Skill.isActive(skillid)) {
			addActiveSkill(skillid, level, activeTime);
		} else {
			addPassiveSkill(skillid, level);
		}
	}

	private void addActiveSkill(int skillid, int level, long activeTime) {
		final ActiveSkill as = new ActiveSkill(skillid, level, activeTime);
		as.curIndex = 0;
		as.activeTime = 0;

		for(int curSkillid = skillid, index = 0 ; curSkillid > 0 ; index++) {
			final cfg.skill.Skilldmg dcfg = CfgMgr.skilldmg.get(curSkillid);
			final cfg.skill.SkillAction scfg = Skill.getSkillAction(dcfg.actiontype != ActionType.TALISMAN ? macfg : map.MapModule.FABAO_MODEL_ACTIONS
				, dcfg.actionname);
			final int endAttackTime =  (int)((dcfg.actiontype != ActionType.TALISMAN ?
					scfg.endattackingtime : this.roleFabaoSkillAction.endattackingtime) * 1000);

			as.skills.add(new SkillInfo(dcfg, scfg, dcfg.hitpoints.get(0).target, endAttackTime));
			activeSkillLocations.put(curSkillid, new Location(as, index));
			curSkillid = dcfg.nextskillid;
		}
		final SkillInfo si = as.skills.get(0);
		as.expirecd = (int)(si.skilldmg.cd * 1000) - 100;// 100ms预留给网络抖动的
        as.skillRange = si.skillAction.attackrange;
		activeSkills.put(skillid, as);
	}

	private ActiveSkill removeActiveSkill(int skillid) {
		final ActiveSkill as = activeSkills.remove(skillid);
		if(as != null) {
			as.skills.forEach(s -> activeSkillLocations.remove(s.skilldmg.id));
		}
		return as;
	}

	private void addPassiveSkill(int skillid, int level) {
		passiveSkills.put(skillid, new PassiveSkill(level, Buff.installPassiveSkillBuff(self, level, CfgMgr.passiveskill.get(skillid).passivebuffid)));
	}

	private void removePassiveSkill(int skillid) {
		final PassiveSkill ps = passiveSkills.remove(skillid);
		if(ps != null) {
			ps.buff.uninstall();
		}
	}

	public boolean isCurSkillFinished() {
		return curPerformSkill == null || curPerformSkill.isEnd();
	}

	public long getCurSkillDuration() {
	    return curPerformSkill != null ? (long)(curPerformSkill.getSkillCfg().endattackingtime * 1000) : 0;
    }

    public final double getMaxAvailableSkillDistance() {
        final long now = self.getNow();
        double maxDistance = normalAttackDistance;
        for(ActiveSkill as : activeSkills.values()) {
            if((as.curIndex > 0 || as.activeTime <= now) && as.skillRange > maxDistance) {
                maxDistance = as.skillRange;
            }
        }
        return maxDistance;
    }

    public void interruptCurSkill(boolean bySelfSkill) {
        final Skill cur = curPerformSkill;
        if(cur != null) {
            curPerformSkill = null;
            final long now = self.getNow();
            if(!bySelfSkill && now < cur.getEndTime()) {
                cur.interrupt(now);
                self.broadcastToNearby(new SSkillInterrupt(cur.getSkillid()));
            }
        }
    }

	public int getBestActiveSkill(Fighter target, double distance) {
		final long now = self.getNow();
		int chooseSkillid = -1;
		float chooseSkillCd = -1000;
        final GameMap gameMap = self.getMap();
		for(ActiveSkill as : activeSkills.values())
			if(as.curIndex > 0 || as.activeTime <= now) {
				final SkillInfo si = as.skills.get(as.curIndex);
				if(gameMap.checkTargetType(self, target, si.target) && si.skillAction.attackrange >= distance && si.skilldmg.cd > chooseSkillCd
						&& ((si.skilldmg.isnormal && self.canNormalattack()) || (!si.skilldmg.isnormal && self.canSkillattack()))) {
					chooseSkillid = si.skilldmg.id;
					chooseSkillCd = si.skilldmg.cd;
				}
			}
		return chooseSkillid;
	}

    public static final class SkillData {
        public long activeTime;
        public cfg.skill.SkillAction skillAction;
        public cfg.skill.Skilldmg skilldmg;
        public int target;
    }

    public SkillData[] getBestSkills() {
        final SkillData[] bestSkillsByRelation = new SkillData[Relation.enums.size()];
        final long now = self.getNow();
        for(ActiveSkill as : activeSkills.values()) {
            final SkillInfo si = as.skills.get(as.curIndex);
            SkillData choose = bestSkillsByRelation[si.target];
            final long activeTime = as.curIndex > 0 ? 0 : as.activeTime;
            if(choose == null || choose.activeTime > activeTime || (choose.skilldmg.isnormal && !si.skilldmg.isnormal && activeTime <= now)) {
                choose = new SkillData();
                choose.activeTime = activeTime;
                choose.skillAction = si.skillAction;
                choose.skilldmg = si.skilldmg;
                choose.target = si.target;
                bestSkillsByRelation[si.target] = choose;
            }
        }
        return bestSkillsByRelation;
    }

    public int getAvaliableSkill() {
        final long now = self.getNow();
        int chooseSkillid = -1;
        float chooseSkillCd = -1000;
        for(ActiveSkill as : activeSkills.values())
            if(as.curIndex > 0 || as.activeTime <= now) {
                final SkillInfo si = as.skills.get(as.curIndex);
                if(si.skilldmg.cd > chooseSkillCd
                        && ((si.skilldmg.isnormal && self.canNormalattack()) || (!si.skilldmg.isnormal && self.canSkillattack()))) {
                    chooseSkillid = si.skilldmg.id;
                    chooseSkillCd = si.skilldmg.cd;
                }
            }
        return chooseSkillid;
    }


    public void addSkillEffect(SkillEffect effect) {
        this.extraEffects.add(effect);
    }

    public void removeSkillEffect(SkillEffect effect) {
        this.extraEffects.remove(effect);
    }

	private Location findSkill(int skillid) {
		return activeSkillLocations.get(skillid);
	}

	public ErrorCode performSkill(Skill.Builder param) {
		final int skillid = param.skillid;
		final Location location = findSkill(skillid);
		if(location == null)
			return ErrorCode.SKILL_UNAVALIABLE;
		final ActiveSkill activeSkill = location.activeSkill;
		final int index = location.index;
		final long now = self.getNow();
		if(index == 0) {
			if(activeSkill.activeTime > now)
				return ErrorCode.SKILL_NOT_COLDDOWN;
		} else if(index != activeSkill.curIndex){
			return ErrorCode.SKILL_UNAVALIABLE;
		}
		final SkillInfo si = activeSkill.skills.get(index);
		final cfg.skill.SkillAction scfg = si.skillAction;
		final cfg.skill.Skilldmg dcfg = si.skilldmg;
		if((dcfg.isnormal && !self.canNormalattack())
				|| (!dcfg.isnormal && !self.canSkillattack()))
			return ErrorCode.SKILL_FORBID;

        final Fighter defencer = param.defencer;
        final double modelScale = 1 + self.getAttrMgr().getModelScale();
		if(scfg.needtarget) {
			if(defencer == null)
				return ErrorCode.SKILL_NEED_TARGET;
			if(self.getPosition().getSubXZMagnitude(defencer.getPosition()) > scfg.attackrange * modelScale) {
				Trace.error("SkillPerform OutRange. attacker:{} defencer:{}", self, defencer);
				return ErrorCode.SKILL_OUT_ATTACK_RANGE;
			}
			if(!self.getMap().checkTargetType(self, defencer, si.target))
				return ErrorCode.INVALID_SKILL_TARGET;
			param.direction = defencer.getPosition().sub(self.getPosition());
		}

		if(param.direction.getXZSquare() < 1e-4)
			param.direction = Vector3.ONE;

		// 护符技能等级加成
		final int skillLevel = activeSkill.level + amulets.getOrDefault(Skill.getBaseEvolveSkillid(skillid), 0);
		final int cost = (int)(dcfg.cost + dcfg.costperlvl * skillLevel);
		if(cost > 0 && !self.getAttrMgr().checkCostMpBySkill(cost))
            return ErrorCode.SKILL_PERFORM_MP_NOT_ENOUGH;

		interruptCurSkill(true);
		curPerformSkill = new Skill(dcfg, scfg, skillLevel, modelScale, si.endAttackTime,
                extraEffects.stream().filter(e -> e.getCfg().skillid == skillid).collect(Collectors.toList()),
                self, defencer,  param.direction);
		if(index == 0) {
			activeSkill.activeTime = now + activeSkill.expirecd;
		}
		activeSkill.curIndex = index + 1 < activeSkill.skills.size() ? index + 1 : 0;
		return ErrorCode.OK;
	}

	public void update(long now) {
        if(self.isDead()) return;
        final Skill cur = curPerformSkill;
		if(cur != null) {
			cur.update(now);
			if(cur.isEnd(now))
				curPerformSkill = null;
		}
	}

	public void evolve(int oldskillid, int newskillid, int level) {
		Trace.debug("SkillMgr.evolve {} oldskillid:{} newskillid:{} level:{}", self.getAid(), oldskillid, newskillid, level);
		if(Skill.isActive(oldskillid)) {
			assert(Skill.isActive(newskillid));
			final ActiveSkill as = removeActiveSkill(oldskillid);
			addActiveSkill(newskillid, level, as != null ? as.activeTime : 0);
		} else {
			assert(!Skill.isActive(newskillid));
			removePassiveSkill(oldskillid);
			addPassiveSkill(newskillid, level);
		}
		final Integer oldLevel = roleSkills.remove(oldskillid);
		assert(oldLevel != null);
		final Integer oldLevel2 = roleSkills.put(newskillid, level);
		assert(oldLevel2 == null);
	}

	public void upgrade(int skillid, int level) {
		Trace.debug("SkillMgr.upgrade {} skillid:{} level:{}", self.getAid(), skillid, level);
		roleSkills.put(skillid, level);
		if(Skill.isActive(skillid)) {
			final ActiveSkill as = activeSkills.get(skillid);
			if(as != null) {
				as.level = level;
			} else {
				addActiveSkill(skillid, level, 0);
			}
		} else {
			removePassiveSkill(skillid);
			addPassiveSkill(skillid, level);
		}
		roleSkills.put(skillid, level);
	}

	public void changeFabaoSkills(Map<Integer, Integer> newSkills) {
		for(Map.Entry<Integer, Integer> e : newSkills.entrySet()) {
			final int skillid = e.getKey();
			final Integer oldLevel = this.fabaoSkills.get(skillid);
			final int newLevel = e.getValue();
			if(oldLevel == null) {
				addActiveSkill(skillid, newLevel, 0);
			} else if(newLevel != oldLevel) {
				activeSkills.get(skillid).level = newLevel;
			}
		}
		for(Map.Entry<Integer, Integer> e : this.fabaoSkills.entrySet()) {
			final int skillid = e.getKey();
			if(!newSkills.containsKey(skillid)) {
				removeActiveSkill(skillid);
			}
		}
		this.fabaoSkills = newSkills;
	}

	public void changeAmulets(Map<Integer, Integer> amulets) {
		this.amulets = amulets;
	}

	public void onDead() {
        curPerformSkill = null;
        activeSkills.values().forEach(s -> {
            s.curIndex = 0;
            s.activeTime = 0;
        });
	}

	public void onRevive() {

	}

}
