package lx.gs.map.attackcity;

import cfg.CfgMgr;
import cfg.ectype.AttackCityStage;
import common.ErrorCode;
import gnet.link.Onlines;
import lx.gs.activity.ActivityModule;
import lx.gs.activity.SimpleWeeklyActivity;
import lx.gs.map.FMap;
import lx.gs.map.msg.SChangeAttackCityStage;
import lx.gs.map.msg.SGetAttackCityInfo;
import map.msg.XCreateAttackCity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQiang on 2016/5/19.
 */
public enum AttackCityModule implements gs.Module, gs.RoleLoginListener {
    INSTANCE;

    public final AttackCityMatcher matcher = new AttackCityMatcher(CfgMgr.attackcity);

    private int stage;
    private final Map<Long, Long> player2Ectypeid = new HashMap<>();



    @Override
    public void start() {
        stage = AttackCityStage.CLOSED;

        final cfg.ectype.AttackCity acfg = CfgMgr.attackcity;
        final List<SimpleWeeklyActivity.WeeklyWork> works = new ArrayList<>();
        works.add(new SimpleWeeklyActivity.WeeklyWork(acfg.signuptime.begintime, this::onSignupBegin));
        works.add(new SimpleWeeklyActivity.WeeklyWork(acfg.opentime.begintime, this::onBattleBegin));
        works.add(new SimpleWeeklyActivity.WeeklyWork(acfg.opentime.endtime, this::onBattleEnd));
        ActivityModule.INSTACNE.addSimpleWeeklyActivity(works);
    }


    private boolean isOpen() {
        return stage != AttackCityStage.CLOSED;
    }

    public long getPlayerEctypeMapid(long roleid) {
        synchronized (this) {
            return player2Ectypeid.getOrDefault(roleid, 0L);
        }
    }

    public void setPlayerEctypeMapid(long roleid, long mapid) {
        synchronized (this) {
            player2Ectypeid.put(roleid, mapid);
        }
    }

    public ErrorCode enroll(AttackCityMatcher.Team team) {
        synchronized (this) {
            if(stage != AttackCityStage.SIGNUP)
                return ErrorCode.ATTACK_CITY_SIGNUP_NOT_BEGIN;
            return matcher.addTeam(team);
        }
    }

    public void getPlayerInfo(long roleid, SGetAttackCityInfo msg) {
        synchronized (this) {
            msg.stage = stage;
            if(isOpen()) {
                if(player2Ectypeid.containsKey(roleid) || matcher.inEnroll(roleid)) {
                    msg.signup = 1;
                } else {
                    msg.signup = 0;
                }
            } else {
                msg.signup = 0;
            }
        }
    }

    private void changeStage(int stage) {
        this.stage = stage;
        Onlines.getInstance().broadcast(new SChangeAttackCityStage(stage));
    }

    public void onSignupBegin() {
        synchronized (this) {
            player2Ectypeid.clear();
            changeStage(AttackCityStage.SIGNUP);
            matcher.reset();
        }
    }

    private void onNewCatelogTeam(AttackCityMatcher.Team team) {
        final XCreateAttackCity msg = new XCreateAttackCity();
        msg.serverid = gs.Utils.getServerId();
        msg.levelsectionid = team.gid;
        msg.ectypeid = CfgMgr.attackcity.id;
        msg.roleids.addAll(team.members);
        FMap.createMapNotInProcedure(gs.Utils.getServerId(), msg, new FMap.CreateMapCallback<XCreateAttackCity>() {
            @Override
            public void onSucc(XCreateAttackCity builder, long mapid) {
                builder.roleids.forEach(roleid -> setPlayerEctypeMapid(roleid, mapid));
            }
        });
    }

    public void onBattleBegin() {
        synchronized (this) {
            changeStage(AttackCityStage.OPEN);
            matcher.doCatelog(this::onNewCatelogTeam);
            matcher.reset();
        }
    }

    public void onBattleEnd() {
        synchronized (this) {
            changeStage(AttackCityStage.CLOSED);
            player2Ectypeid.clear();
        }
    }

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        final SGetAttackCityInfo msg = new SGetAttackCityInfo();
        getPlayerInfo(roleid, msg);
        xdb.Transaction.tsendWhileCommit(roleid, msg);
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {

    }
}
