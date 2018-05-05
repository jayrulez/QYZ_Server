package map.agent;

import map.MapUtils;
import map.map.GameMap;
import map.msg.AgentBuilder;
import map.msg.SNearbyNPCEnter;
import xio.Protocol;

public final class ANPC extends Agent {
	public static class Builder {
		public GameMap map;
		public AgentBuilder b;
		public cfg.npc.NPC ncfg;
	}

	public ANPC(Builder b) {
		super(b.map, b.b);
		this.ncfg = b.ncfg;
	}

	@Override
	public String toString() {
		return String.format("NPC{aid:%s npcid:%s position:%s orient:%s}", getAid(), getNpcId(), getPosition(), getOrient());
	}

    @Override
    public String getName() {
        return ncfg.name;
    }

    public final int getNpcId() {
		return ncfg.id;
	}

	private final cfg.npc.NPC ncfg;


	@Override
	public void update(long now) {

	}

	@Override
	public Protocol createSelfEnter() {
		final SNearbyNPCEnter re = new SNearbyNPCEnter();
		re.agentid = getAid();
		re.npcid = ncfg.id;
		re.position = MapUtils.p2m(position);
		re.orient = MapUtils.p2m(orient);
		return re;
	}

}
