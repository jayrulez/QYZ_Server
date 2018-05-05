package lx.gs.leaderboard;

import cfg.CfgMgr;
import cfg.Const;
import cfg.bonus.RankType;
import cfg.tips.LocationType;
import cfg.tips.TipsCode;
import common.TaskQueue;
import gnet.link.Onlines;
import gnet.link.Role;
import gs.DayOverListener;
import gs.Module;
import gs.RoleDayOverListener;
import gs.RoleLoginListener;
import lx.gs.event.*;
import lx.gs.family.FFamily;
import lx.gs.leaderboard.msg.SLatestLeaderBoard;
import lx.gs.leaderboard.msg.SRoleRanking;
import lx.gs.tips.FTips;
import xdb.Procedure;
import xdb.Transaction;
import xdb.Xdb;
import xtable.Family;
import xtable.Roleinfos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 排行榜
 *
 * @author Jin Shuai
 */
public enum  LeaderBoardModule implements Module, RoleLoginListener, DayOverListener, RoleDayOverListener {
    INSTANCE;

    private SLatestLeaderBoard sLatestBoard = new SLatestLeaderBoard();
    private List<Integer> rankTypeList = new ArrayList<>();
    final Map<Integer, Long> championRoleIds = new ConcurrentHashMap<>();
    final Map<Integer, Integer> broadcastBoards = new HashMap<>();
    //存放家族榜的临时数据，
    public volatile static Map<Integer, Long> familyRankMap = new HashMap<>();


    @Override
    public void start() {
        RankType.enums.stream()
                .filter(integer -> integer != RankType.ARENA)
                .forEach(integer -> rankTypeList.add(integer));
        broadcastBoards.put(RankType.LEVEL, TipsCode.LEVEL_CHAMPION_LOGIN);
        broadcastBoards.put(RankType.COMBAT_POWER, TipsCode.COMBATPOWER_CHAMPION_LOGIN);
        broadcastBoards.put(RankType.FABAO, TipsCode.TALISMAN_CHAMPION_LOGIN);
        broadcastBoards.put(RankType.PET, TipsCode.PET_CHAMPION_LOGIN);
        broadcastBoards.put(RankType.FAMILY, TipsCode.FAMILY_CHAMPION_LOGIN);
        broadcastBoards.put(RankType.XUNIBI, TipsCode.XUNIBI_CHAMPION_LOGIN);
        broadcastBoards.put(RankType.FLOWER, TipsCode.FLOWER_CHAMPION_LOGIN);

        registerListeners();
        refreshServerBords();
    }

    @Override
    public void onDayOver() {
        FLeaderBoard.recordYesterdayRanking();
    }

    @Override
    public void onDayOver(long roleId) {
        FLeaderBoard.syncYesterdayRanking(roleId);
    }

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        refreshClientBoards(roleid);
        broadcastIfChampion(roleid);
    }

    private void broadcastIfChampion(long roleid) {
        broadcastBoards.entrySet().forEach(entry -> {
            int type = entry.getKey();
            if(championRoleIds.getOrDefault(type, (long) Const.NULL) == roleid ){
                if(type == RankType.FAMILY){
                    String familyName = Family.selectFamilyname(FFamily.getRoleFamilyInfo(roleid).getCurrentfid());
                    familyName = familyName == null ? "" : familyName;
                    FTips.send(roleid, LocationType.CENTER_SCROLL, entry.getValue(), familyName, Roleinfos.selectName(roleid));
                } else {
                    FTips.send(roleid, LocationType.CENTER_SCROLL, entry.getValue(), Roleinfos.selectName(roleid));
                }
            }
        });
    }

    public void refreshServerBords() {
        rankTypeList.stream()
                .forEach(type -> TaskQueue.getScheduleExecutor().scheduleWithFixedDelay(
                        () -> refreshBroadcast(type), 0, CfgMgr.rank.get(type).refreshrate, TimeUnit.SECONDS));
    }

    private void refreshBroadcast(int type){
        // 一定要确定方法不在事务里面调用，否则下面这行代码会造成死锁
        try {
            Xdb.getInstance().getCheckpointMBean().checkpoint(0);
        } catch (InterruptedException e) {
        }
        FLeaderBoard.refreshBoardByType(type);
        sLatestBoard.latestbord.put(type, FLeaderBoard.getBordByType(type));

        for (Role role : Onlines.getInstance().roles()) {
            SLatestLeaderBoard board = new SLatestLeaderBoard();
            board.latestbord.put(type, sLatestBoard.latestbord.get(type));
            role.send(board);

            SRoleRanking sRoleRanking = new SRoleRanking();
            sRoleRanking.info.put(type, FLeaderBoard.getRankingById(role.getRoleid(), type));
            role.send(sRoleRanking);
        }
    }

    private void refreshClientBoards(long roleid){
        Transaction.tsendWhileCommit(roleid, sLatestBoard);
        FLeaderBoard.syncRanking(roleid);
        FLeaderBoard.syncYesterdayRanking(roleid);
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {
    }

    private void registerListeners() {
        EventModule.INSTANCE.registerListener(EventType.LEVEL_UP, event -> {
            LevelUpEvent subEvent = event.cast();
            FLeaderBoard.recordByType(RankType.LEVEL, event.roleId, subEvent.currLevel);
        });
        EventModule.INSTANCE.registerListener(EventType.COMBATPOWER_CHANGE, event -> {
            CombatPowerChangeEvent subEvent = event.cast();
            FLeaderBoard.recordByType(RankType.COMBAT_POWER, event.roleId, subEvent.currVal);
        });
        EventModule.INSTANCE.registerListener(EventType.PET_COMBATPOWER_CHANGE, event -> {
            PetCombatPowerChangeEvent subEvent = event.cast();
            FLeaderBoard.recordByType(RankType.PET, event.roleId, subEvent.currVal);
        });
        EventModule.INSTANCE.registerListener(EventType.TALISMAN_COMBATPOWER_CHANGE, event -> {
            TalismanCombatPowerChangeEvent subEvent = event.cast();
            FLeaderBoard.recordByType(RankType.FABAO, event.roleId, subEvent.currVal);
        });
        EventModule.INSTANCE.registerListener(EventType.FAMILY_BUILD, event -> {
            FamilyBuildEvent subEvent = event.cast();
            FLeaderBoard.recordByType(RankType.FAMILY, subEvent.familyId, subEvent.currVal);
        });
        EventModule.INSTANCE.registerListener(EventType.XUNIBI_ADD, event -> {
            XunibiAddEvent subEvent = event.cast();
            FLeaderBoard.recordByType(RankType.XUNIBI, event.roleId, subEvent.currVal);
        });
        EventModule.INSTANCE.registerListener(EventType.CLIMB_TOWER_UP, event -> {
            ClimbTowerUpEvent subEvent = event.cast();
            if(subEvent.useTime <= 1){
                return;
            }
            FLeaderBoard.recordClimbTower(event.roleId, subEvent.floor, subEvent.useTime);
        });
        EventModule.INSTANCE.registerListener(EventType.RECEIVE_FLOWER, event -> {
            ReceiveFlowerEvent subEvent = event.cast();
            FLeaderBoard.recordByType(RankType.FLOWER, event.roleId, subEvent.currVal);
        });
        EventModule.INSTANCE.registerListener(EventType.FAMILY_DISBAND, event -> {
            FamilyDisbandEvent subEvent = event.cast();
            FLeaderBoard.removeRecord(subEvent.familyId, RankType.FAMILY);
            Transaction.texecuteWhileCommit(() -> {
                FFamily.FamilyName2id.remove(subEvent.familyFullName);
                final Map<Integer, Long> newFamilyRanks = new HashMap<>(familyRankMap);
                //reorder after delete a family
                for (Map.Entry<Integer, Long> e : newFamilyRanks.entrySet()) {
                    if (e.getValue() == subEvent.familyId) {
                        final int n = newFamilyRanks.size();
                        for (int i = e.getKey(); i < n; i++) {
                            newFamilyRanks.put(i, newFamilyRanks.get(i + 1));
                        }
                        newFamilyRanks.remove(n);
                        break;
                    }
                }
                familyRankMap = newFamilyRanks;
            });
        });
        EventModule.INSTANCE.registerListener(EventType.FAMILY_CREATE, event -> {
            FamilyCreateEvent subEvent = event.cast();
            FLeaderBoard.recordByType(RankType.FAMILY, subEvent.familyId, 0);
            Transaction.texecuteWhileCommit(() -> {
                final Map<Integer, Long> newFamilyRanks = new HashMap<>(familyRankMap);
                newFamilyRanks.put(newFamilyRanks.size() + 1, subEvent.familyId);
                familyRankMap = newFamilyRanks;
                FFamily.FamilyName2id.put(subEvent.familyFullName, subEvent.familyId);
            });
        });
    }

    private void doRefreshByType(int type){
        // 一定要确定方法不在事务里面调用，否则下面这行代码会造成死锁
        try {
            Xdb.getInstance().getCheckpointMBean().checkpoint(0);
        } catch (InterruptedException e) {
        }
        new Procedure(){
            @Override
            protected boolean process() throws Exception {
                FLeaderBoard.refreshBoardByType(type);
                return true;
            }
        }.call();
    }

    public List<Integer> getRankType() {
        return rankTypeList;
    }
}
