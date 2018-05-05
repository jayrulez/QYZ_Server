package map.agent;

import cfg.CfgMgr;
import cfg.fight.StateType;
import map.MapUtils;
import map.ai.AI;
import map.ai.ArenaPetAI;
import map.ai.PetAI;
import map.buff.Buff;
import map.buff.effect.InternEffcteIds;
import map.map.Arena;
import map.map.GameMap;
import map.map.HuiWu;
import map.msg.*;
import pathfinding.Vector3;
import xio.Protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangqiang on 2016/3/2.
 */
public class APet extends Fighter {
    public static class Builder  {
        public GameMap map;
        public Player master;
        public PetBuilder b;
    }

    private AI ai;
    private final long ownerid;
    private final int petkey;
    private int level;
    private int starLevel;
    private int awakeLevel;
    private int skinid;
    private final Player owner;
    private final cfg.pet.PetBasicStatus pcfg;
    private final boolean canRevive;

    private final boolean hasAi;

    private final Vector3 defaultRelateOwnerPosition;
    private final double defaultRelateRadius;

    private final List<Integer> awakeBuffids = new ArrayList<>();

    private final Map<Integer, Buff> awakeBuffs = new HashMap<>();
    public APet(Builder pb) {
        super(pb.map, pb.b.basic);
        this.owner = pb.master;
        final PetBuilder b = pb.b;
        this.petkey = b.basic.basic.subtype;
        this.pcfg = CfgMgr.petbasicstatus.get(this.petkey);
        this.ownerid = b.ownerid;
        this.level = b.level;
        this.starLevel = b.starlevel;
        this.awakeLevel = b.awakelevel;
        this.skinid = b.skinid;
        this.awakeBuffids.addAll(b.buffs);
        this.defaultRelateOwnerPosition = MapUtils.m2p(b.defaultrelateownerposition);
        this.defaultRelateRadius = this.defaultRelateOwnerPosition.getXZMagnitude();
        this.canRevive = pb.map.canPetRevive();
        this.hasAi = true;//b.serverai != 0;

        this.stateMgr.setState(InternEffcteIds.ENTER_PROTECT, StateType.ENTER_PROTECT, 3000L);
        this.attrMgr.init(b.attrs);
        this.skillMgr.init(pcfg.modelname, b.skills);
        this.installAwakeBuffs();
        this.attrMgr.initHpMp(b.hp, b.mp);
        // 副本内,如果人死了,伙伴会离开人继续战斗,
        this.ai = hasAi ? (pb.map.isEctype() ? new ArenaPetAI(this) : new PetAI(this)) : null;
        xdb.Trace.debug("createApet. owner:{} pet:{} ai:{}", owner, this, ai);
    }

    @Override
    public String toString() {
        return String.format("APet{aid:%s petkey:%s position:%s}", getAid(), getPetkey(), getPosition());
    }

    public int getPetkey() {
        return petkey;
    }

    @Override
    public final int getLevel() {
        return this.level;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getName() {
        return pcfg.name;
    }

    public final boolean isMyTeamMate(Fighter other) {
        return owner.isMyTeamMate(other);
    }

    public Vector3 getDefaultRelateOwnerPosition() {
        return defaultRelateOwnerPosition;
    }

    public double getDefaultRelateRadius() {
        return defaultRelateRadius;
    }
//    public void setDefaultRelateOwnerPosition(Vector2 defaultRelateOwnerPosition) {
//        this.defaultRelateOwnerPosition = defaultRelateOwnerPosition;
//    }

    public void onBeAttackByOther(Fighter other) {
        owner.onBeAttackByOther(other);
    }

    public void onAttackOther(Fighter other) {
        owner.onAttackOther(other);
    }

    public PetMapContext fillMapContext(PetMapContext ctx) {

        return ctx;
    }

    @Override
    protected void updateWhileDead(long now) {
        super.updateWhileDead(now);
        if(canRevive && deathTime + (long)(CfgMgr.petconfig.deadcd * 1000L) <= now) {
            revive(getPosition().isSubXZMagnitudeInRadius(owner.getPosition(), CfgMgr.petconfig.followfarradius) ?
                    owner.calcPositionByOrientAndRelatePosition(getPosition()) : getPosition(), getOrient());
        }
    }

    @Override
    public void onDead(Fighter attacker) {
        super.onDead(attacker);

        this.awakeBuffs.clear();
        this.ai = null;
    }

    @Override
    public void onRevive(Vector3 position, Vector3 orient) {
        super.onRevive(position, orient);
        ai = hasAi ? new PetAI(this) : null;
        installAwakeBuffs();
    }

    private void installAwakeBuffs() {
        this.awakeBuffs.clear();
        this.awakeBuffids.forEach(buffid -> {
            this.awakeBuffs.put(buffid, Buff.installNotSkillHitPointBuff(this, buffid));
        });
    }

    @Override
    public Protocol createSelfEnter() {
        final SNearbyPetEnter re = new SNearbyPetEnter();
        re.agentid = getAid();
        re.petkey = petkey;
        re.owenrid = ownerid;
        re.level = level;
        re.starlevel = starLevel;
        re.awakelevel = awakeLevel;
        re.skinid = skinid;
        super.fillFighterCommon(re.fightercommon);
        return re;
    }

    @Override
    protected void updateWhileAlive(long now) {
        super.updateWhileAlive(now);
        if(this.ai != null && canAI() && !isDead()) {
            ai.update(now);
        }
    }

    public void process(SChangePetStarLevel msg) {
        this.starLevel = msg.level;
        broadcastToNearby(msg);
    }

    public void process(SChangePetAwakeLevel msg) {
        this.awakeLevel = msg.level;
        broadcastToNearby(msg);
    }

    public void process(SChangePetSkin msg) {
        this.skinid = msg.skinid;
        broadcastToNearby(msg);
    }

    public void process(XChangePetAttr msg) {
        this.attrMgr.changeAttrs(msg.attr, false);
    }

    public void process(XChangePetLevel msg) {
        this.level = msg.level;
        this.attrMgr.resetHPMP();
        broadcastToNearby(msg);
    }


    public void process(XChangePetSkill msg) {
        this.skillMgr.upgrade(msg.skillid, msg.skilllevel);
    }

    public void process(XAddPetBuff msg) {
        this.awakeBuffids.add(msg.buffid);
        this.awakeBuffs.put(msg.buffid, Buff.installNotSkillHitPointBuff(this, msg.buffid));
    }
}
