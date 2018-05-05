package map.agent;

import cfg.skill.*;
import map.MapUtils;
import map.msg.SSkillAttack;
import map.msg.SSkillCastBomb;
import map.skill.AttackType;
import map.skill.HitPointHandler;
import map.skill.NormalAttackHandler;
import map.skill.Skill;
import pathfinding.Vector3;

import java.util.*;
import java.util.stream.Collectors;

import static map.skill.Skill.calcRadius;

/**
 * Created by HuangQiang on 2016/5/31.
 */
public class Bomb extends SpawnObject<cfg.skill.Bomb> {

    private final Skilldmg dcfg;
    private final SkillAction scfg;
    private final int hitPointOffset;
    private final List<Attack> hitPoints;

    private final Vector3 direction;
    private final Vector3 basePosition;

    public Bomb(Skill skill, cfg.skill.Bomb ocfg, int hitPointOffset) {
        super(skill, ocfg);

        this.dcfg = skill.getDmgCfg();
        this.scfg = skill.getSkillCfg();
        this.direction = skill.getDirection();

        this.hitPointOffset = hitPointOffset;
        this.hitPoints = new ArrayList<>(this.scfg.attacklists_id.get(ocfg.attacklistid).attacks);
        this.basePosition = (this.ocfg.totarget && this.skill.getActiveTarget() != null
                ? this.skill.getActiveTarget().getPosition() : caster.getPosition())
                .plus(new Vector3(ocfg.offsetx, ocfg.offsety, ocfg.offsetz).rotateXZ(direction));

        final SSkillCastBomb re = new map.msg.SSkillCastBomb();
        final Fighter attacker = skill.getAttacker();
        re.skillid = skill.getSkillid();
        re.attackerid = attacker.getAid();
        re.objectid = ocfg.id;
        re.direction = MapUtils.p2m(skill.getDirection());
        re.position = MapUtils.p2m(basePosition);

        attacker.broadcastToNearby(re);
    }

    @Override
    public String toString() {
        return String.format("Bomb{caster:%s skillid:%s objectid:%s}", skill.getAttacker(), skill.getSkillid(), ocfg.id);
    }

    public List<Fighter> getTargets(Vector3 basePosition, cfg.skill.HitZone hitZone, int relation) {
        final List<Fighter> targets = new ArrayList<>();
        this.map.foreachNearbyFighterInAgentMap(basePosition,
                calcRadius(hitZone) + 4/*+ defencer.getBodyRadius() * 2 */,
                target -> {
                    if((hitZone.maxtarget == cfg.Const.NULL || targets.size() < hitZone.maxtarget)
                            && target.isActive() && !target.isDead() && target.canBeattacked()
                                && skill.getAttacker().getMap().checkTargetType(caster, target, relation)
                                && Skill.inHitZone(hitZone, 1, basePosition, direction, target)) {
                            targets.add(target);
                        }
                    });
        return targets;
    }

    @Override
    public boolean onUpdate(long now) {

        for(Iterator<Attack> it = hitPoints.iterator() ; it.hasNext() ; ) {
            final Attack attack = it.next();
            if(attack.timeline * 1000 <= this.aliveTime) {
                it.remove();
                final HitPointAction hap = this.dcfg.hitpoints.get(attack.id + hitPointOffset);
                final List<Fighter> targets = getTargets(basePosition, this.scfg.hitzones_id.get(attack.hitzoneid), hap.target);
                final List<map.buff.effect.SkillEffect> effects = skill.getEffects().stream().filter(e -> e.getCfg().hitpoint.contains(attack.id)).collect(Collectors.toList());
                HitPointHandler.doAction(skill, attack, targets, hap, effects, AttackType.SWAPN_OBJECT);
            }
        }
        return !hitPoints.isEmpty();
    }
}
