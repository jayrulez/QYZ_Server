package map.skill;

import cfg.skill.Attack;
import cfg.skill.BuffAttack;
import cfg.skill.HitZone;
import common.Utils;
import map.MapUtils;
import map.agent.Agent;
import map.agent.AttrMgr;
import map.agent.Fighter;
import map.buff.Buff;
import map.msg.SSkillAttack;

import java.util.HashSet;
import java.util.List;

/**
 * Created by HuangQiang on 2016/5/17.
 */
public class BuffAttackHandler extends HitPointHandler<BuffAttack> {
    public BuffAttackHandler(Skill skill, long startTime, Attack action, HitZone hitZone, BuffAttack hitpointAction) {
        super(skill, startTime, action, hitZone, hitpointAction);
    }

    @Override
    public boolean process(long now) {
        final List<Fighter> targets = skill.getTargets(hitpointAction.target, hitZone, this.extraHitNum);
        doAction(skill, action, targets, hitpointAction, effects, AttackType.SKILL);
        return false;
    }
}
