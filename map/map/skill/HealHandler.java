package map.skill;

import cfg.skill.*;
import common.Utils;
import map.MapUtils;
import map.agent.Agent;
import map.agent.AttrMgr;
import map.agent.Fighter;
import map.agent.Player;
import map.buff.Buff;
import map.buff.effect.SkillEffect;
import map.msg.AttackResult;
import map.msg.HealResult;
import map.msg.SSkillAttack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by HuangQiang on 2016/5/17.
 */
public class HealHandler extends HitPointHandler<cfg.skill.Heal> {
    public HealHandler(Skill skill, long startTime, Attack action, HitZone hitZone, cfg.skill.Heal hitpointAction) {
        super(skill, startTime, action, hitZone, hitpointAction);
    }

    @Override
    public boolean process(long now) {
        final List<Fighter> targets = skill.getTargets(hitpointAction.target, hitZone, extraHitNum);
        doAction(skill, action, targets, hitpointAction, effects, AttackType.SKILL);
        return false;
    }

}
