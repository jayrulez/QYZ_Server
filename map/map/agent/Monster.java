package map.agent;

import cfg.CfgMgr;
import cfg.Const;
import cfg.map.Reason;
import cfg.monster.MonsterType;
import common.AttrUtils;
import map.map.GameMap;
import map.ai.AI;
import map.ai.AIFactory;
import map.msg.FighterBuilder;
import map.msg.SNearbyMonsterEnter;

import java.util.stream.Collectors;

public final class Monster extends Fighter {
	public static class Builder  {
		public GameMap map;
        public FighterBuilder b;
        public cfg.monster.Monster mcfg;
		public AIFactory.Builder ai;
	}
	public Monster(Builder b) {
		super(b.map, b.b);

		this.mcfg = b.mcfg;
        attrMgr.setAlwaysBroadcast(b.mcfg.monstertype == MonsterType.BOSS);
		final float[] rawattrs = AttrUtils.newRawAttrs();
		common.AttrUtils.convert(b.mcfg.attr, rawattrs);
		this.attrMgr.init(AttrUtils.convert(rawattrs));

		final cfg.monster.SkillModelBind scfg = CfgMgr.skillmodelbind.get(mcfg.modelname);
		this.skillMgr.init(mcfg.modelname, scfg.skills.stream().collect(Collectors.toMap(id -> id, id -> 1)));
        this.attrMgr.initHpMp(Const.NULL, Const.NULL);
		this.ai = b.ai.acfg != null ? AIFactory.create(this, b.ai) : null;
	}

	private final cfg.monster.Monster mcfg;
	private final AI ai;

	@Override
	public String toString() {
		return String.format("Monster{aid:%s monsterid:%s position:%s orient:%s}", getAid(), getMonsterId(), getPosition(), getOrient());
	}
	
	public int getMonsterId() {
		return mcfg.id;
	}

    @Override
    public String getName() {
        return mcfg.name;
    }

    @Override
	public final int getLevel() {
		return mcfg.level;
	}

	@Override
	public void onDead(Fighter attacker) {
		super.onDead(attacker);
        schedule(() -> map.leave(this, Reason.DEAD), cfg.monster.Monster.MONSTER_LEAVE_DELAY_WHEN_DEAD * 1000L);
	}

	@Override
	protected void updateWhileAlive(long now) {
        if(!inAIIdle()) {
            super.updateWhileAlive(now);
            if (ai != null && canAI() && !isDead()) {
                ai.update(now);
            }
        }
	}

	@Override
	public xio.Protocol createSelfEnter() {
		final SNearbyMonsterEnter re = new SNearbyMonsterEnter();
		re.agentid = getAid();
		re.monsterid = mcfg.id;
		fillFighterCommon(re.fightercommon);
		return re;
	}

}
