package lx.gs.gm;

import common.TaskQueue;
import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import gnet.link.Onlines;
import lx.gs.arena.FArena;
import lx.gs.leaderboard.LeaderBoardModule;

/**
 * Created by huangqiang on 2016/4/8.
 */

@Module(comment="排行榜模块")
public class Rank {

    @Cmd(comment = "刷新排行榜")
    public Object refreshBoard() {
        TaskQueue.getNormalExecutor().submit(() -> {
            LeaderBoardModule.INSTANCE.refreshServerBords();
            for (gnet.link.Role role : Onlines.getInstance().roles()) {
                LeaderBoardModule.INSTANCE.onDayOver(role.getRoleid());
            }
        });
        return "succ";
    }

    @Cmd(comment = "设置对手名单")
    public Object setArenaOpponent(@Param(name="rank", comment = "对手排名") int rank) {
        final long roleid = GmSession.current().getRoleid();
        FArena.setChallengeRanks(roleid, rank);
        return "succ";
    }
}
