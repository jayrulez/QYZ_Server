package gs;

import cfg.CfgMgr;
import cfg.Const;
import cfg.buff.EndCondition;
import cfg.buff.SetAbnormalState;
import cfg.buff.SkillEffect;
import cfg.character.ModelType;
import cfg.cmd.action.*;
import cfg.ectype.ClimbTowerEctype;
import cfg.ectype.FloorInfo;
import cfg.fight.AbilityType;
import cfg.fight.Relation;
import cfg.skill.*;
import cfg.skill.Heal;
import cfg.tips.TipsCode;
import lx.gs.skill.FSkill;

import java.util.*;

public class ConfigCheck {

    public static void main(String[] args) throws Exception {

    }

    public static void check() {
        checkBuff();
        checkSkill();
		checkEctype();
		checkWorld();
        checkAllDrop();
        checkTips();
    }

    private static void checkTips() {
        for (int tipsCode : TipsCode.enums) {
            if(!CfgMgr.tipscontent.containsKey(tipsCode)){
                error("tips not exist, code : %d", tipsCode);
            }
        }
    }

    static void validSkill(cfg.skill.Skilldmg dcfg, cfg.skill.SkillAction sa) {
        int hitPointOffset = 0;
        for (cfg.skill.Action action : sa.actions) {
            if (action instanceof cfg.skill.Attack) {
                final cfg.skill.Attack attack = (cfg.skill.Attack) action;
                if (attack.id < 0 || attack.id >= dcfg.hitpoints.size()) {
                    error("###skillid:%d  actionname:%s attack id:%d can't find skilldmg",
                            dcfg.id, dcfg.actionname, attack.id);
                }
                final HitPointAction hitPointAction =  dcfg.hitpoints.get(attack.id);
                switch (hitPointAction.getTypeId()) {
                    case Heal.TYPEID:
                    case NormalAttack.TYPEID:
                    case BuffAttack.TYPEID:
                        break;
                    default:
                        error("###skillid:%d  actionname:%s attack id:%d is attack. hitpoint can't be %s",
                                dcfg.id, dcfg.actionname, attack.id, hitPointAction);
                }
            } else if (action instanceof cfg.skill.FlyWeapon) {
                if (dcfg.hitpoints.size() != 1 || !(dcfg.hitpoints.get(0) instanceof cfg.skill.NormalAttack)) {
                    error("###skillid:%d  actionname:%s  FlyWeapon must has only one NormalAttack in skilldmg",
                            dcfg.id, dcfg.actionname);
                }
            } else if (action instanceof cfg.skill.Bomb) {
                hitPointOffset += sa.attacklists_id.get(((Bomb) action).attacklistid).attacks.size();
                if (dcfg.hitpoints.size() < hitPointOffset) {
                    error("###skillid:%d  actionname:%s  Bomb. skilldmg's hitpoint num is not enough",
                            dcfg.id, dcfg.actionname);
                }
            }
        }
    }

    static void checkNotContainsRemoveByNextAttack(int skillid, int buffid) {
        if(buffid <= 0) return;
        final cfg.buff.Buff buff = CfgMgr.buff.get(buffid);
        for(cfg.buff.EffectInfo ei : buff.effects) {
            if(ei.endconditiontype == EndCondition.BY_NEXT_ATTACK) {
                error("skillid:%s buffid:%s is succbuff, can't contains effect whoes endtype==BY_NEXT_ATTACK", skillid, buffid);
                break;
            }
        }
    }
    
    static void checkSkill() {
        for(cfg.skill.Skilldmg dcfg : CfgMgr.skilldmg.values()) {
            final HashSet<Integer> links = new HashSet<>();
            final int skillid = dcfg.id;
            links.add(skillid);
            cfg.skill.Skilldmg ccfg = dcfg;
            while(ccfg.nextskillid > 0) {
                if(!links.add(ccfg.nextskillid)) {
                    error("skillid:%s next skillid chain is ring!", skillid);
                }
                ccfg = CfgMgr.skilldmg.get(ccfg.nextskillid);
            }

            for(cfg.skill.HitPointAction hcfg : dcfg.hitpoints) {
                switch (hcfg.getTypeId()) {
                    case NormalAttack.TYPEID: {
                        if(hcfg.target != Relation.ENEMY)
                            error("skillid:%s NormalAttack's target type can't be:%s", skillid, hcfg.target);
                        final NormalAttack a = (NormalAttack)hcfg;
                        checkNotContainsRemoveByNextAttack(skillid, a.action.succbuffid);
                        break;
                    }
                    case Heal.TYPEID: {
                        if(hcfg.target == Relation.ENEMY)
                            error("skillid:%s NormalAttack's target type can't be:%s", skillid, hcfg.target);
                        final Heal a = (Heal)hcfg;
                        checkNotContainsRemoveByNextAttack(skillid, a.action.succbuffid);
                        break;
                    }
                    case BuffAttack.TYPEID: {

                        break;
                    }
                    default:
                        error("skillid:%s unknown hitpoint type:%s", skillid, hcfg);
                }
            }
        }
		for(cfg.skill.SkillLvlupCost scfg : CfgMgr.skilllvlupcost.values()) {
			final int nextSkillid = scfg.nextskillid;
			if(nextSkillid > 0) {
				if(!CfgMgr.skilllvlupcost.containsKey(nextSkillid)) {
					error("skillid:%s nextskillid:%s can't find int skilllvupcost", scfg.skillid, nextSkillid);
				}
			}
		}

		for(cfg.monster.SkillModelBind scfg : CfgMgr.skillmodelbind.values()) {
			if(scfg.skills.size() != new HashSet<>(scfg.skills).size()) {
				error("skillmodelbind model:%s has duplicate skillids:%s!", scfg.modelname, scfg.skills);
			}
		}

    	for(cfg.skill.ModelActions s : CfgMgr.modelactions.values()) {
			final String modelName = s.modelname;
			for(cfg.skill.SkillAction a : s.skillactions.values()) {
				for(cfg.skill.HitZone hz : a.hitzones) {
					if(hz.shape < cfg.skill.HitZone.RECT || hz.shape > cfg.skill.HitZone.TRIANGLE)
						error("model:%s action:%s unknown hitzone.shape:%d", modelName, a.actionname, hz.shape);
//                    if(hz.maxtarget <= 0)
//                        error("model:%s action:%s invalid maxtarget:%s", modelName, a.actionname, hz.maxtarget);
				}
			}

    	}

		for(cfg.role.Profession pcfg : CfgMgr.profession.values()) {
			final cfg.skill.CareerSkillList ccfg = CfgMgr.careerskilllist.get(pcfg.faction);
			if(ccfg == null) {
				error("profession:%s can't find in careerskilllist", pcfg.name);
				continue;
			}
			for(String modelName : Arrays.asList(pcfg.modelname, pcfg.modelname2)) {
				final cfg.skill.ModelActions mcfg = CfgMgr.modelactions.get(modelName);
				if(mcfg == null) {
					error("profession:%s modelname:%s can't find", pcfg.faction, modelName);
					continue;
				}
				final cfg.skill.SkillAction saction = FSkill.getSkillAction(mcfg, pcfg.talismanactionname);
				if(saction == null) {
					error("profession:%s modelname:%s fabao action:%s can't find in ModelActions",
							pcfg.faction, modelName, pcfg.talismanactionname);
				}

				final List<Integer> skillids = new ArrayList<>(ccfg.skilllist);
				skillids.add(ccfg.normalskillid);

				final Set<Integer> evolveSkillids = new HashSet<>(skillids);
				for(int skillid : skillids) {
					while(true) {
						final cfg.skill.SkillLvlupCost dcfg = CfgMgr.skilllvlupcost.get(skillid);
						if(dcfg != null && dcfg.nextskillid > 0) {
							skillid = dcfg.nextskillid;
							if(!evolveSkillids.add(skillid)) {
                                error("skillid:%s next skillid chain is ring!", skillid);
                            }
						} else {
							break;
						}
					}
				}
				for(int skillid : evolveSkillids) {
					final cfg.skill.Skilldmg dcfg = CfgMgr.skilldmg.get(skillid);
					if(dcfg != null) {
                        final cfg.skill.SkillAction sa = FSkill.getSkillAction(mcfg, dcfg.actionname);
                        if (sa == null) {
                            error("profession:%s  skillid:%s actionname:%s can't find in ModelActions.skillactions",
                                    pcfg.name, skillid, dcfg.actionname);
                            continue;
                        }
                        validSkill(dcfg, sa);
                    } else {
                        final cfg.skill.PassiveSkill scfg = CfgMgr.passiveskill.get(skillid);
                        if(scfg == null) {
                            error("profession:%s  skillid:%s  can't find skilldmg or passiveskill", pcfg.name, skillid);
                            continue;
                        }
                    }
                    if(dcfg == null ||!dcfg.isnormal) {
                        if(!CfgMgr.skilllvlupcost.containsKey(skillid)) {
                            error("profession:%s  skillid:%s  can't find int skilllvlupcost", pcfg.name, skillid);
                        }
                    }
				}
			}
		}
		for(cfg.monster.Monster mcfg : CfgMgr.monster.values()) {
			final cfg.skill.ModelActions acfg = CfgMgr.modelactions.get(mcfg.modelname);
			if (acfg == null) {
				error("monster:%s name:%s model:%s can't find in config:modelaction", mcfg.id, mcfg.name, mcfg.modelname);
				continue;
			}
			final cfg.monster.SkillModelBind scfg = CfgMgr.skillmodelbind.get(mcfg.modelname);
			if (scfg == null) {
				error("monster:%s name:%s model:%s can't find in config:skillmodelbind", mcfg.id, mcfg.name, mcfg.modelname);
				continue;
			}
			for (int skillid : scfg.skills) {
				final cfg.skill.Skilldmg dcfg = CfgMgr.skilldmg.get(skillid);
				//if (dcfg.actiontype != ActionType.IMMEDIATELY) continue;
				//if (dcfg != null) {
                if(dcfg == null) continue;
				final cfg.skill.SkillAction sa = FSkill.getSkillAction(acfg, dcfg.actionname);
				if (sa == null) {
					error("monster:%s name:%s skillid:%s actionname:%s can't find in ModelActions.skillactions",
							mcfg.id, mcfg.name, skillid, dcfg.actionname);
					continue;
				}
				validSkill(dcfg, sa);
			}
			//}
		}
        final cfg.skill.ModelActions FABAO_MODEL_ACTIONS = CfgMgr.modelactions.get(CfgMgr.model.values().stream()
                .filter(m -> m.modeltype == ModelType.Talisman).findFirst().get().modelname);

		for(cfg.talisman.TalismanSkill tcfg: CfgMgr.talismanskill.values()) {
			for(int skillid : tcfg.skillid) {
				final cfg.skill.Skilldmg dcfg = CfgMgr.skilldmg.get(skillid);
				if(dcfg == null) {
					error("fabaoid:%s skillid:%d can't find in skilldmg", tcfg.id, skillid);
					continue;
				}
				final cfg.skill.SkillAction sa = FSkill.getSkillAction(FABAO_MODEL_ACTIONS, dcfg.actionname);
				if(sa == null) {
					error("fabaoid:%s name:%s skillid:%s actionname:%s can't find in fabao modelactions",
							tcfg.id, tcfg.name, skillid, dcfg.actionname);
				} else {
				    validSkill(dcfg, sa);
                }
			}
		}

		for(cfg.pet.PetBasicStatus pcfg : CfgMgr.petbasicstatus.values()) {
			final cfg.skill.ModelActions acfg = CfgMgr.modelactions.get(pcfg.modelname);
			final cfg.pet.PetSkill scfg = CfgMgr.petskill.get(pcfg.id);
			if(scfg == null) {
				error("pet:%s name:%s model:%s can't find in config:petskill", pcfg.id, pcfg.name, pcfg.modelname);
				continue;
			}
			final List<Integer> skills = new ArrayList<>(scfg.skilllist);
            skills.addAll(scfg.awakeskill);
			for(int skillid : skills) {
				final cfg.skill.Skilldmg dcfg = CfgMgr.skilldmg.get(skillid);
				if(dcfg == null) {
					//error("pet:%s name:%s skillid:%s can't find in skilldmg", pcfg.id, pcfg.name, skillid);
                    final cfg.skill.PassiveSkill bcfg = CfgMgr.passiveskill.get(skillid);
                    if(bcfg == null) {
                        error("petid:%s  skillid:%s  can't find skilldmg or passiveskill", pcfg.id, skillid);
                    }
				} else {
                    //if(dcfg.actiontype != ActionType.IMMEDIATELY) continue;
                    final cfg.skill.SkillAction sa = FSkill.getSkillAction(acfg, dcfg.actionname);
                    if (sa == null) {
                        error("pet:%s name:%s skillid:%s actionname:%s can't find in ModelActions.skillactions",
                                pcfg.id, pcfg.name, skillid, dcfg.actionname);
                        continue;
                    }
                    validSkill(dcfg, sa);
                }
                if(dcfg == null ||!dcfg.isnormal) {
                    if(!CfgMgr.skilllvlupcost.containsKey(skillid)) {
                        error("petid:%s  skillid:%s  can't find int skilllvlupcost", pcfg.id, skillid);
                    }
                }
			}
		}
    }

	private static void checkEctype() {
		for(cfg.ectype.ClimbTowerEctype e : CfgMgr.climbtowerectype.values()) {
			final cfg.ectype.EctypeBasic b = CfgMgr.ectypebasic.get(e.id);
			cfg.map.EctypeRegionSet rs = CfgMgr.ectyperegionset.get(b.regionsetid);
			switch (e.getTypeId()) {
				case ClimbTowerEctype.TYPEID: {
					for(FloorInfo fi : e.floors) {
						if(fi.regionid > 0 && !rs.regions_id.containsKey(fi.regionid)) {
							error("ectypeid:%d floor:%d regionid:%d can't find in regionsetid:%d", e.id, fi.id, fi.regionid, b.regionsetid);
						}
					}
					break;
				}
			}
		}
		for(cfg.ectype.StoryLayout scfg : CfgMgr.storylayout.values()) {
            final cfg.ectype.EctypeBasic b = CfgMgr.ectypebasic.get(scfg.id);
            cfg.map.EctypeRegionSet rs = CfgMgr.ectyperegionset.get(b.regionsetid);
            for(cfg.ectype.Layout layout : scfg.layouts) {
                for(cfg.ectype.Passage passage : layout.exits) {
                    if(!rs.regions_id.containsKey(passage.curveid)) {
                        error("storylayout:%s layoutid:%s exitid:%d curveid:%s can't find in regionset", scfg.id, layout.id, passage.id, passage.curveid);
                    }
                }
                for(cfg.ectype.Passage passage : layout.enters) {
                    if(!rs.regions_id.containsKey(passage.curveid)) {
                        error("storylayout:%s layoutid:%s entryid:%d curveid:%s can't find in regionset", scfg.id, layout.id, passage.id, passage.curveid);
                    }
                }
            }
        }
	}

	private static void checkWorld() {
		for(cfg.map.WorldMap w : CfgMgr.worldmap.values()) {
			cfg.map.EctypeRegionSet rs = CfgMgr.ectyperegionset.get(w.regionsetid);
			for(cfg.map.Portal p : w.portals) {
				if(!rs.regions_id.containsKey(p.srcregionid)) {
					error("worldmapid:%d regionid:%d can't find in regionsetid:%d", w.id, p.srcregionid, w.regionsetid);
				}
			}
		}
	}

    private static void checkBuff() {
        final Map<Long, Integer> groupidPriorityid2Id = new HashMap<>();
        for(cfg.buff.Effect effect : CfgMgr.effect.values()) {
            if(effect.groupid != Const.NULL) {
                final long uid = ((long)effect.groupid << 32) + effect.priority;
                if(groupidPriorityid2Id.containsKey(uid) && effect.priority == 1) {
                    error("effect groupid:%s priority:%s duplicate! effectid1:%s effectid2:%s", effect.groupid, effect.priority, effect.id, groupidPriorityid2Id.get(uid));
                }
                groupidPriorityid2Id.put(uid, effect.id);
            }
            if(effect instanceof SkillEffect) {
                final SkillEffect e = (SkillEffect)effect;
                if(e.buffid > 0) {
                    cfg.buff.Buff buff = CfgMgr.buff.get(e.buffid);
                    if(buff.target != Relation.ENEMY) {
                        for(cfg.buff.EffectInfo ei:  buff.effects) {
                            cfg.buff.Effect se = CfgMgr.effect.get(ei.effectid);
                            if(se instanceof SetAbnormalState) {
//                                error("skilleffect:%s target:%s buffid:%s can't contains SetAbnormalState effect:%d.",
//                                        e.id, buff.target, e.buffid, se.id);
                            }
                        }
                    }
                }
            }
        }
        for(cfg.buff.Buff buff : CfgMgr.buff.values()) {
            if(buff.target != Relation.ENEMY) {
                for(cfg.buff.EffectInfo ei:  buff.effects) {
                    cfg.buff.Effect se = CfgMgr.effect.get(ei.effectid);
                    if(se.isharmful && se instanceof SetAbnormalState) {
//                        error("buffid:%s target:%s can't contains SetAbnormalState effect:%d.",
//                                buff.id, buff.target, se.id);
                    }
                }
            }
        }

        for(cfg.fight.State state : CfgMgr.state.values()) {
            if(state.abilities.size() != AbilityType.enums.size()) {
                error("state id:%s abilities size:%s != AbilityType.enums.size:%s", state.id, state.abilities.size(), AbilityType.enums.size());
            }
        }
    }

    private static void checkDrop(cfg.cmd.action.Bonus bonus, List<Integer> ds) {
        final List<Integer> dropSets = new ArrayList<>(ds);
        switch (bonus.getTypeId()) {
            case CareerBonus.TYPEID: {
                checkDrop(((CareerBonus)bonus).bonus, dropSets);
                break;
            }
            case MultiBonus.TYPEID: {
                for(cfg.cmd.action.Bonus b : ((MultiBonus)bonus).bonuss) {
                    checkDrop(b, dropSets);
                }
                break;
            }
            case RandomBonus.TYPEID: {
                for(cfg.cmd.action.BonusRandomInfo b : ((RandomBonus)bonus).bonuss) {
                    checkDrop(b.bonus, dropSets);
                }
                break;
            }
            case RepeatBonus.TYPEID: {
                checkDrop(((RepeatBonus)bonus).bonus, dropSets);
                break;
            }
            case CopyBonus.TYPEID: {
                checkDrop(((CopyBonus)bonus).bonus, dropSets);
                break;
            }
            case Drop.TYPEID: {
                final int dropid = ((Drop)bonus).dropid;
                final cfg.bonus.Drop dcfg = CfgMgr.drop.get(dropid);
                if(dropSets.contains(dropid)) {
                    dropSets.add(dropid);
                    error("circle drop list:" + dropSets);
                    return;
                } else {
                    dropSets.add(dropid);
                }
                checkDrop(dcfg.droplist, dropSets);
            }
        }
    }

    private static void checkAllDrop() {
        for(cfg.bonus.Drop dcfg : CfgMgr.drop.values()) {
           checkDrop(dcfg.droplist, new ArrayList<>(Arrays.asList(dcfg.id)));
        }
    }

    private static boolean hasError = false;
    public static void error(String fmt, Object...params) {
    	System.out.println(String.format(fmt, params));
    	hasError = true;
    	//throw new RuntimeException(String.format(fmt, params));
    }
}
