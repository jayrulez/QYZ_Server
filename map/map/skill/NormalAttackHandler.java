package map.skill;

import cfg.fight.AttrId;
import cfg.skill.Attack;
import cfg.skill.HitZone;
import cfg.skill.NormalAttack;
import common.Utils;
import map.MapUtils;
import map.agent.Agent;
import map.agent.AttrMgr;
import map.agent.Fighter;
import map.agent.Player;
import map.buff.Buff;
import map.buff.effect.SkillEffect;
import map.buff.factor.Context;
import map.buff.factor.Factor;
import map.buff.factor.Prioritys;
import map.msg.AttackResult;
import map.msg.SSkillAttack;
import xdb.Trace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by HuangQiang on 2016/5/17.
 */
public class NormalAttackHandler extends HitPointHandler<cfg.skill.NormalAttack> {

    public NormalAttackHandler(Skill skill, long startTime, Attack action, HitZone hitZone, NormalAttack hitpointAction) {
        super(skill, startTime, action, hitZone, hitpointAction);
    }

    @Override
    public boolean process(long now) {
		final List<Fighter> targets = skill.getTargets(hitpointAction.target, hitZone, this.extraHitNum);
        doAction(skill, action, targets, hitpointAction, effects, AttackType.SKILL);
		return false;
    }



}
