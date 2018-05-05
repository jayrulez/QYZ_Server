package lx.gs.gm;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.ectype.AttackCityStage;
import cfg.ectype.GuardTowerZone;
import common.ErrorCode;
import gm.GmCmdResult;
import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.arena.FArena;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.map.MapModule;
import lx.gs.map.RoleContext;
import lx.gs.map.TeamFightModule;
import lx.gs.map.attackcity.AttackCityModule;
import lx.gs.map.guardtower.GuardTowerModule;
import lx.gs.map.msg.SChangeClimbTower;
import lx.gs.map.msg.SChangeTeamFight;
import lx.gs.map.teamspeed.TeamSpeedModule;
import lx.gs.rank.FRank;
import lx.gs.storynote.FStoryNote;
import lx.gs.storynote.StoryNoteModule;
import lx.gs.storynote.msg.SActiveNote;
import map.msg.*;
import xbean.StoryNoteChapter;
import xdb.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by huangqiang on 2015/12/31.
 */
@Module(comment="地图模块GM命令")
public class Map {
    private GmCmdResult error(String fmt, Object... params) {
        return GmCmdResult.error(String.format(fmt, params));
    }

    @Cmd(comment = "进入副本")
    public Object openEctype(@Param(name="ectypeid", comment="副本id")int ectypeid) {
        final long roleid = GmSession.current().getRoleid();
        FMap.openOnePlayerEctypeInProcedure(roleid, ectypeid);
        return "";
    }

    @Cmd(comment = "当前位置生成一个怪物")
    public String createMonster(@Param(name="monsterid", comment = "怪物id")int monsterid,
                              @Param(name="num", comment = "个数")int num) {
        final long roleid = GmSession.current().getRoleid();
        FMap.dispatchMessageInProcedure(roleid, new XCreateMonsterAtPlayerPosition(monsterid, num));
        return "succ";
    }

    @Cmd(comment = "设置剧情副本章节星数")
    public String setChapterSectionStar(@Param(name="chapterid", comment = "章")int chapterid,
                                      @Param(name="sectionid", comment="节") int sectionid,
                                      @Param(name="star", comment = "星数")int star) {
        final long roleid = GmSession.current().getRoleid();

        final MEndStoryEctype msg = new MEndStoryEctype();
        msg.ectypeid = MapModule.chapterEctypes.get(chapterid).get(sectionid - 1).id;
        msg.star = star;
        FMap.process(roleid, msg);
        return "succ";
    }

    @Cmd(comment = "设置天下会武 参数")
    public String setTeamFightTodayWinNum(@Param(name="winnum", comment = "胜利次数")int winnum,
                                          @Param(name="score", comment = "周积分") int score) {
        final long roleid = GmSession.current().getRoleid();
        final xbean.TeamFightInfo info = FMap.getEctype(roleid).getTeamfight();
        info.setTodaywinnum(winnum);
        info.setWeekscore(score);
        xdb.Transaction.tsendWhileCommit(roleid, new SChangeTeamFight(FMap.convert(info)));
        return "succ";
    }

    @Cmd(comment = "开启天下会武")
    public String openTeamFight() {
        TeamFightModule.INSTANCE.open();
        return "succ";
    }

    @Cmd(comment = "进入天下会武副本")
    public String enterTeamFightEctype() {
        final long roleid = GmSession.current().getRoleid();
        final XCreateTeamFight msg = new XCreateTeamFight();
        msg.levelgroupid = 0;
        msg.serverid = gs.Utils.getServerId();
        msg.ectypeid = CfgMgr.teamfight.id;
        msg.team1.add(new TeamFightMember(roleid, xtable.Roleinfos.selectProfession(roleid)));
        FMap.openEctypeInProcedure(roleid, gs.Utils.getServerId(), msg);
        return "succ";
    }

    @Cmd(comment = "进入《血战青云》")
    public Object openGuardTowerEctype(@Param(name="zoneid", comment="血战青云zoneid")int zoneid) {
        final long roleid = GmSession.current().getRoleid();
        ErrorCode err = FCondition.checkByReflection(roleid, CfgMgr.guardtower, 1, By.Open_Ectype, ConfigId.GUARD_TOWER_ECTYPE, 0);
        if (err.err()) {
            return error("不满足进入副本条件，等级或者次数不足！");
        }
        GuardTowerZone zone = CfgMgr.guardtower.zones_zoneid.get(zoneid);
        final XCreateGuardTowerEctype msg = new XCreateGuardTowerEctype();
        msg.ectypeid = zone.ectypeid;
        msg.serverid = gs.Utils.getServerId();
        msg.roles.put(roleid, xtable.Roleinfos.selectProfession(roleid));
        msg.zoneid = zoneid;

        FMap.openEctypeInProcedure(msg.roles.keySet(), msg.serverid, msg);
        return "";
    }

    @Cmd(comment = "设置《血战青云》参数 eg. setguardtoweroption 1,2 表示1波怪物，组队需要2人")
    public Object setGuardTowerOption(@Param(name="options", comment="血战青云参数：[怪物波数int],<组队人数int>")String options) {
        String[] opts = options.split(",");
        if (opts.length > 0) {
            try {
                int wave = Integer.parseInt(opts[0]);
                if (wave <= 0) {
                    return error("怪物波数需要正整数");
                }
                GuardTowerModule.maxWave = wave;
            } catch (NumberFormatException e) {
                return error("怪物波数需要正整数");
            }
        }
        if (opts.length > 1) {
            try {
                int num = Integer.parseInt(opts[1]);
                if (num <= 0) {
                    return error("组队人数需要正整数");
                }
                GuardTowerModule.matchMemberNum = num;
//                GuardTowerModule.INSTANCE.startMatcherManager();
            } catch (NumberFormatException e) {
                return error("组队人数需要正整数");
            }
        }
        return "guardtower's options : " + GuardTowerModule.maxWave + " " + GuardTowerModule.matchMemberNum;
    }
    @Cmd(comment = "设置妖兽攻城阶段")
    public Object setAttackCityStage(@Param(name="stage", comment = "cfg.ectype.AttackCityStage")int stage) {
        switch (stage) {
            case AttackCityStage.CLOSED: {
                AttackCityModule.INSTANCE.onBattleEnd();
                break;
            }
            case AttackCityStage.SIGNUP: {
                AttackCityModule.INSTANCE.onSignupBegin();
                break;
            }
            case AttackCityStage.OPEN: {
                AttackCityModule.INSTANCE.onBattleBegin();
                break;
            }
        }
        return "succ";
    }

    @Cmd(comment = "开启序章副本")
    public Object openPrologueEctype() {
        final long roleid = GmSession.current().getRoleid();
        FMap.openOnePlayerEctypeInProcedure(roleid, MapModule.PROLOGUE_ECTYPE_ID);
        return "succ";
    }

    @Cmd(comment = "开启鸿蒙争霸")
    public Object openTeamSpeed() {
        TeamSpeedModule.INSTANCE.open4Test();
        return true;
    }

    @Cmd(comment = "激活所有风华录")
    public Object activeAllStoryNote() {
        final long roleid = GmSession.current().getRoleid();
        final java.util.Map<Integer, StoryNoteChapter> chapters = FStoryNote.get(roleid).getChapters();
        CfgMgr.storynote.forEach((chapterid, storyNote) -> {
            StoryNoteChapter chapter = chapters.get(chapterid);
            if(chapter == null) {
                chapter = xbean.Pod.newStoryNoteChapter();
                chapters.put(chapterid, chapter);
            }
            final Set<Integer> notes = chapter.getNotes();
            storyNote.noteinfo_noteid.forEach((noteid, ncfg) -> {
                if(!notes.contains(noteid)){
                    notes.add(noteid);
                    Transaction.tsendWhileCommit(roleid, new SActiveNote(chapterid, noteid));
                }
            });
        });
        StoryNoteModule.INSTANCE.updateRoleAttr(roleid);
        return true;
    }

    @Cmd(comment = "设置地图广播模式 0为详尽模式,1为中等模式,其余为粗略模式")
    public Object setMode(@Param(name="mode", comment = "模式")int mode) {
        final long roleid = GmSession.current().getRoleid();
        FMap.dispatchMessageInProcedure(roleid, new CSetMessgeMode(mode));
        return "succ";
    }

    @Cmd(comment = "重置幻月洞府层数,需要重新登陆客户端")
    public Object setClimbTowerFloor(@Param(name="floor", comment = "层数")int floor) {
        final long roleid = GmSession.current().getRoleid();
        xbean.RoleEctype info = FMap.getEctype(roleid);
        final xbean.ClimbTowerInfo towerInfo = xbean.Pod.newClimbTowerInfo();
        towerInfo.setMaxfloorid(floor);
        info.getClimbtowers().put(MapModule.CLIMB_TOWER_ECTYPE_ID, towerInfo);
        xdb.Transaction.tsend(roleid, new SChangeClimbTower(MapModule.CLIMB_TOWER_ECTYPE_ID, FMap.convert(towerInfo)));
        return "succ";
    }

    @Cmd(comment = "单人开启天下会武,匹配机器人对手和队友")
    public Object openTeamFightWithRobot(@Param(name="groupid", comment = "等级组id") int groupid) {
        final long roleid = GmSession.current().getRoleid();
        final XCreateTeamFight msg = new XCreateTeamFight();
        msg.ectypeid = CfgMgr.teamfight.id;
        msg.serverid = gs.Utils.getServerId();
        msg.levelgroupid = groupid;

        List<Long> roleids = new ArrayList<>();
        roleids.add(roleid);
        final FRank.XSwapRank rank = FArena.getSwapRank();
        roleids.addAll(common.Utils.getMutiRandom(1, 100, 5).stream().map(r -> rank.getIdByRank(r)).collect(Collectors.toList()));
        for(int i = 0; i < roleids.size() ;i++) {
            final long mid = roleids.get(i);
            MapModule.players.putIfAbsent(mid, new RoleContext(mid).load(FMap.get(mid)));
            (i < 3 ? msg.team1 : msg.team2).add(new TeamFightMember(mid, xtable.Roleinfos.selectProfession(mid)));
        }
        final int createAtServerid = gs.Utils.getServerId();
        FMap.openEctypeInProcedure(roleids, createAtServerid, msg);
        return "succ";
    }
}
