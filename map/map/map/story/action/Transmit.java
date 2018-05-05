package map.map.story.action;

import cfg.ectype.PathFinding;
import map.MapUtils;
import map.agent.Agent;
import map.agent.Player;
import map.map.story.AbstractStory;
import map.msg.Vector3;
import xdb.Trace;

/**
 * Created by huangqiang on 2016/2/3.
 */
public class Transmit extends CfgAction<cfg.ectype.Transmit> {
    public Transmit(cfg.ectype.Transmit mcfg) {
        super(mcfg);
    }

    @Override
    public boolean run() {
        final AbstractStory map = (AbstractStory)vm.host;
        map.beginNotEndClientAction(mcfg.actionid);

        for(Agent agent : map.getAgents()) {
            if(agent.isPlayerOrFakePlayer()) {
                final Player player = agent.asPlayerOrFakePlayer();
                Trace.info("Transmit. player:{} to position:({},{})", player, mcfg.position.x, mcfg.position.y);
                final pathfinding.Vector3 newPos = MapUtils.c2p(mcfg.position);
                player.addServerMoveDistance(newPos.getSubXZMagnitude(player.getPosition()));
                player.getMoveMgr().stopAtPosition(MapUtils.c2p(mcfg.position), MapUtils.createOrient(mcfg.rotation));
                player.updateNearbyPlayer();
            }
        }
        return false;
    }
}
