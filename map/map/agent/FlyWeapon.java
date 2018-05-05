package map.agent;

import cfg.CfgMgr;
import cfg.skill.NormalAttack;
import map.MapUtils;
import map.buff.Buff;
import map.buff.effect.SkillEffect;
import map.msg.SFlyWeaponAttack;
import map.msg.SSkillCastFlyWeapon;
import map.skill.AttackType;
import map.skill.NormalAttackHandler;
import map.skill.Skill;
import xdb.Trace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/5/31.
 */
public class FlyWeapon extends SpawnObject<cfg.skill.FlyWeapon> {
    private final HashSet<Long> hitAgents = new HashSet<>();

    private pathfinding.Vector3 position;
    private cfg.skill.TraceCurve ccfg;

    private final cfg.skill.NormalAttack hitPointAction;

    private boolean end;
    private List<SkillEffect> effects;
    public FlyWeapon(Skill skill, cfg.skill.FlyWeapon ocfg) {
        super(skill, ocfg);
        this.ccfg = CfgMgr.tracecurve.get(ocfg.tracecurveid);
        this.effects = skill.getEffects().stream().filter(e -> e.getCfg().hitpoint.contains(0)).collect(Collectors.toList());

        this.position = skill.getAttacker().getPosition();
        this.end = false;

        this.hitPointAction = (NormalAttack)skill.getDmgCfg().hitpoints.get(0);

        final SSkillCastFlyWeapon re = new map.msg.SSkillCastFlyWeapon();
        final Fighter attacker = skill.getAttacker();
        re.skillid = skill.getSkillid();
        re.attackerid = attacker.getAid();
        re.objectid = ocfg.id;
        re.direction = MapUtils.p2m(skill.getDirection());

        caster.broadcastToNearby(re);
        //Trace.info("FlyWeapon:{} create", this);
    }

    @Override
    public String toString() {
        return String.format("skillid:%s objectid:%s caster:%s position:%s speed:%.2f",
                skill.getSkillid(), ocfg.id, this.caster, this.position, this.ccfg.velocity);
    }

    @Override
    public boolean onUpdate(long now) {
        this.position = this.position.plus(skill.getDirection().scaleXZ(this.ccfg.velocity * map.getDeltaTime() / 1000));
        //Trace.info("FlyWeapon:{} onUpdate", this);

        final SFlyWeaponAttack re = new map.msg.SFlyWeaponAttack();
        final Fighter attacker = skill.getAttacker();
        re.skillid = skill.getSkillid();
        re.attackerid = attacker.getAid();
        re.objectid = ocfg.id;

        final List<Buff> buffMayEndByAttack = new ArrayList<>();
        try {
            this.map.foreachNearbyFighterInAgentMap(this.position, ocfg.bulletradius + 3, target -> {
                if (end || !target.isActive() || target.isDead() || !caster.isRelationCamp(target, hitPointAction.target))
                return;
                final double dis = position.getSubXZMagnitude(target.getPosition());
                if (dis <= ocfg.bulletradius + target.getBodyRadius() && hitAgents.add(target.getAid())) {
                    if (!ocfg.passbody) {
                        end = true;
                    }
                    re.attacks.add(NormalAttackHandler.doAttack(AttackType.SWAPN_OBJECT, buffMayEndByAttack, this.effects, skill.getAttacker(),
                            skill.getDefencer(), skill.getSkillLevel(), hitPointAction.action));
                }
            });
        } finally {
            buffMayEndByAttack.forEach(Buff::removeEffectEndByAttack);
        }
        if(!re.attacks.isEmpty()) {
            final Agent hiton = this.map.getAgent(re.attacks.get(0).defencerid);
            if(hiton != null) {
                hiton.broadcastToNearby(re);
            }
        }
        return !end;
    }
}
