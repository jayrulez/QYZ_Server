package map.agent;

import common.ErrorCode;
import map.ai.AI;
import map.ai.AIFactory;
import map.map.GameMap;
import map.map.MapMgr;
import map.msg.PlayerBuilder;
import xdb.Trace;

/**
 * Created by huangqiang on 2016/2/24.
 */
public class FakePlayer extends Player {
    private final AI ai;
    public FakePlayer(GameMap map, PlayerBuilder b, AIFactory.Builder ai) {
        super(map, b);

        this.ai = ai.acfg != null ? AIFactory.create(this, ai) : null;
    }

    public AI getAI() {
        return ai;
    }

    @Override
    protected void updateWhileAlive(long now) {
        super.updateWhileAlive(now);
        if(this.ai != null && !isDead() && canAI() && !inAIIdle()) {
            ai.update(now);
        }
    }
}
