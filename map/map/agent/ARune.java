package map.agent;

import map.MapUtils;
import map.map.GameMap;
import map.msg.AgentBuilder;
import map.msg.SNearbyRuneEnter;
import xio.Protocol;

/**
 * Created by live106 on 2016/5/10.
 */
public final class ARune extends Agent {
    public final int runeid;

    public static class Builder {
        public GameMap map;
        public AgentBuilder b;
        public int runeid;
    }

    public ARune(Builder b) {
        super(b.map, b.b);
        this.runeid = b.runeid;
    }

    @Override
    public String getName() {
        return "ARune:" + runeid;
    }

    @Override
    public Protocol createSelfEnter() {
        final SNearbyRuneEnter re = new SNearbyRuneEnter();
        re.agentid = getAid();
        re.position = MapUtils.p2m(position);
        re.orient = MapUtils.p2m(orient);
        re.runeid = runeid;
        return re;
    }

    @Override
    public void update(long now) {

    }
}
