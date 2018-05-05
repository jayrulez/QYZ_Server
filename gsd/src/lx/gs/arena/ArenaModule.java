package lx.gs.arena;

import cfg.CfgMgr;
import cfg.bonus.RankType;
import gnet.link.Onlines;
import xdb.Xdb;
import xtable.Roleinfos;

import java.util.concurrent.TimeUnit;

/**
 * Created by huangqiang on 2016/2/25.
 */
public enum ArenaModule implements gs.Module, gs.LevelUpListener, gs.RoleLoginListener {
    INSTANCE;

    public final static int RANK_SIZE = CfgMgr.rank.get(RankType.ARENA).ranksize;

    @Override
    public void start() {
        Xdb.getInstance().getExecutor().scheduleAtFixedRate(() -> {
            final long now = System.currentTimeMillis();
            for (gnet.link.Role role : Onlines.getInstance().roles()) {
                new xdb.Procedure() {
                    @Override
                    protected boolean process() {
                        FArena.refreshShengWang(role.getRoleid(), now);
                        return true;
                    }
                }.execute();
            }
        }, CfgMgr.arenaconfig.shengwangrefreshinterval, CfgMgr.arenaconfig.shengwangrefreshinterval, TimeUnit.SECONDS);
    }

    @Override
    public void onLevelUp(long roleid, int oldLevel, int newLevel) {
        final int openLevel = CfgMgr.arenaconfig.openlevel;
        if (oldLevel < openLevel && openLevel <= newLevel) {
            final xbean.RoleArena info = FArena.get(roleid);
            if (!info.getOpen()) {
                FArena.doOpen(roleid, info);
                xdb.Transaction.tsendWhileCommit(roleid, FArena.createSInfo(roleid, info));
            }
        }
    }

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        final xbean.RoleArena info = FArena.get(roleid);
        if (!info.getOpen()) {
            if (Roleinfos.selectLevel(roleid) >= CfgMgr.arenaconfig.openlevel)
                FArena.doOpen(roleid, info);
        } else {
            FArena.refreshShengWang(roleid, System.currentTimeMillis());
        }
        xdb.Transaction.tsendWhileCommit(roleid, FArena.createSInfo(roleid, info));
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {

    }
}
