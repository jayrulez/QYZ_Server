package lx.gs.leaderboard;

import cfg.CfgMgr;
import cfg.Const;
import cfg.bonus.RankType;
import common.RefObject;
import gnet.link.Onlines;
import gs.FixedPriorityQueue;
import lx.gs.event.EventModule;
import lx.gs.event.RankingRefreshEvent;
import lx.gs.family.FFamilyModule;
import lx.gs.leaderboard.msg.BoardEntry;
import lx.gs.leaderboard.msg.BoardInfo;
import lx.gs.leaderboard.msg.SRoleRanking;
import lx.gs.leaderboard.msg.SYesterdayRanking;
import lx.gs.logger.FLogger;
import xbean.BoardRecordEntry;
import xbean.Pod;
import xdb.Procedure;
import xdb.Trace;
import xtable.Family;
import xtable.Leaderboardrecord;
import xtable.Leaderboards;
import xtable.Roleinfos;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jin Shuai
 */
public class FLeaderBoard {

    protected static void recordByType(int type, long id, long val1) {
        if (type == RankType.CLIMB_TOWER) {
            throw new IllegalArgumentException("call method recordClimbTower()");
        }
        Map<Integer, BoardRecordEntry> recordMap = getRecordById(id).getRecords();
        if (!recordMap.containsKey(type)) {
            BoardRecordEntry entry = Pod.newBoardRecordEntry();
            entry.setUpdatetime(System.currentTimeMillis());
            recordMap.putIfAbsent(type, entry);
        }
        BoardRecordEntry entry = recordMap.get(type);
        if (val1 > entry.getVal1()) {
            entry.setVal1(val1);
            entry.setUpdatetime(System.currentTimeMillis());
        }
    }

    protected static void recordClimbTower(long roleId, long val1, int val2) {
        int type = RankType.CLIMB_TOWER;
        Map<Integer, BoardRecordEntry> recordMap = getRecordById(roleId).getRecords();
        if (!recordMap.containsKey(type)) {
            BoardRecordEntry entry = Pod.newBoardRecordEntry();
            entry.setUpdatetime(System.currentTimeMillis());
            recordMap.putIfAbsent(type, entry);
        }
        BoardRecordEntry entry = recordMap.get(type);
        if (val2 <= 0) {
            Trace.error("climb tower time can't less than 1, roleid:{}, floor:{}, time:{}", roleId, val1, val2);
            return;
        }
        if (val1 > entry.getVal1() || (entry.getVal1() == val1 && val2 < entry.getVal2())) {
            entry.setVal1(val1);
            entry.setVal2(val2);
            entry.setUpdatetime(System.currentTimeMillis());
        }
    }

    private static xbean.BoardRecord getRecordById(long id) {
        xbean.BoardRecord record = Leaderboardrecord.get(id);
        if (record == null) {
            record = Pod.newBoardRecord();
            Leaderboardrecord.add(id, record);
        }
        return record;
    }

    private static xbean.BoardInfo getDbBoardByType(int type) {
        xbean.BoardInfo boardInfo = Leaderboards.get(type);
        if (boardInfo == null) {
            boardInfo = Pod.newBoardInfo();
            Leaderboards.add(type, boardInfo);
        }
        return boardInfo;
    }

    protected static BoardInfo getBordByType(int type) {
        BoardInfo boardInfo = new BoardInfo();
        new Procedure() {
            @Override
            protected boolean process() throws Exception {
                int entryNum = CfgMgr.rank.get(type).showsize;
                for (xbean.BoardEntry entry : Leaderboards.select(type).getLatestboard().values()) {
                    BoardEntry boardEntry = convert(entry);
                    if (type == RankType.FAMILY) {
                        boardEntry.id = Family.selectChiefid(entry.getId());
                    }
                    boardInfo.info.add(boardEntry);
                    if (boardInfo.info.size() >= entryNum) {
                        break;
                    }
                }
                return true;
            }
        }.call();
        return boardInfo;
    }

    private static BoardEntry convert(xbean.BoardEntry entry) {
        return new BoardEntry(entry.getId(), entry.getName(), entry.getVal1(), entry.getVal2());
    }

    protected static void refreshBoardByType(int type) {
        new Procedure() {
            @Override
            protected boolean process() throws Exception {
                Map<Long, BoardRecordEntry> history = new HashMap<>();
                Leaderboardrecord.getTable().walk((aLong, record) -> {
                    BoardRecordEntry recordEntry = record.getRecords().get(type);
                    if (recordEntry != null) {
                        history.put(aLong, recordEntry);
                    }
                    return true;
                });
                xbean.BoardInfo boardInfo = getDbBoardByType(type);
                boardInfo.setLastupdatetime(System.currentTimeMillis());

                int rankNum = CfgMgr.rank.get(type).ranksize;

                /** 小值在前 */
                Comparator<CompareObj> comparator = (o1, o2) -> {
                    int ret = Long.compare(o1.val1, o2.val1); //值小的在前面（战力，等级等）
                    if (ret == 0 && type == RankType.CLIMB_TOWER) { //爬塔榜还需要计算时间
                        ret = Integer.compare(o1.val2, o2.val2) * -1; // 时间用的多的在前面
                    }
                    return ret != 0 ? ret : Long.compare(o1.updateTime, o2.updateTime) * -1; // 记录晚的在前面
                };
                FixedPriorityQueue<CompareObj> compareQueue = new FixedPriorityQueue<>(rankNum, comparator);

                List<xbean.BoardEntry> boardData = new LinkedList<>();
                Map<Long, CompareObj> compareObjMap = new HashMap<>();
                history.forEach((id, record) -> {
                    CompareObj compareObj = new CompareObj(record);
                    if (rankNum >= history.size() || compareQueue.offer(compareObj)) {
                        xbean.BoardEntry boardEntry = Pod.newBoardEntry();
                        boardEntry.setRanking(0);
                        boardEntry.setName("");
                        boardEntry.setId(id);
                        boardEntry.setVal1(record.getVal1());
                        boardEntry.setUpdatetime(record.getUpdatetime());
                        boardEntry.setVal2(record.getVal2());

                        boardData.add(boardEntry);
                        compareObjMap.put(boardEntry.getId(), compareObj);
                    }
                });

                Map<Integer, xbean.BoardEntry> latestboard = boardInfo.getLatestboard();
                Map<Long, Integer> roleRank = boardInfo.getRolerank();
                latestboard.clear();
                roleRank.clear();

                int ranking = 1;
                Collections.sort(boardData, (o1, o2) -> comparator.compare(compareObjMap.get(o1.getId()), compareObjMap.get(o2.getId())) * -1);
                for (xbean.BoardEntry entry : boardData) {
                    entry.setRanking(ranking);
                    latestboard.put(ranking, entry);
                    roleRank.put(entry.getId(), ranking);
                    ranking++;
                }
                if (type == RankType.FAMILY) {
                    LeaderBoardModule.familyRankMap = boardInfo.getLatestboard().entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().getId()));
                }
                return true;
            }
        }.call();
        // 先把名次排好，在把名字一一取出来再放进去，以免死锁
        Map<Long, String> id2Name = new HashMap<>();
        new Procedure() {
            @Override
            protected boolean process() throws Exception {
                Leaderboards.select(type).getRolerank().keySet().forEach(aLong ->
                        id2Name.put(aLong, type == RankType.FAMILY
                                ? Family.selectFamilyname(aLong)
                                : Roleinfos.selectName(aLong)));
                return true;
            }
        }.call();
        new Procedure() {
            @Override
            protected boolean process() throws Exception {
                getDbBoardByType(type).getLatestboard().values()
                        .forEach(entry -> entry.setName(id2Name.getOrDefault(entry.getId(), "")));
                return true;
            }
        }.call();
    }

    public static void syncRanking(long roleid) {
        SRoleRanking sRoleRanking = new SRoleRanking();
        LeaderBoardModule.INSTANCE.getRankType().forEach(type -> sRoleRanking.info.put(type, getRankingById(roleid, type)));
        Onlines.getInstance().send(roleid, sRoleRanking);
    }

    public static void syncYesterdayRanking(long roleid) {
        SYesterdayRanking ret = new SYesterdayRanking();
        LeaderBoardModule.INSTANCE.getRankType().forEach(type -> ret.info.put(type, getYesterdayRanking(roleid, type)));
        Onlines.getInstance().send(roleid, ret);
        EventModule.INSTANCE.broadcastEvent(new RankingRefreshEvent(roleid, ret.info));
    }

    public static int getRankingById(long id, int rankType) {
        RefObject<Integer> ret = new RefObject<>(Const.NULL);
        new Procedure() {
            @Override
            protected boolean process() throws Exception {
                ret.value = Leaderboards.select(rankType).getRolerank().getOrDefault(id, Const.NULL);
                return true;
            }
        }.call();
        return ret.value;
    }

    public static int getYesterdayRanking(long id, int rankType) {
        RefObject<Integer> ret = new RefObject<>(Const.NULL);
        new Procedure() {
            @Override
            protected boolean process() throws Exception {
                ret.value = getDbBoardByType(rankType).getYesterdayrank().getOrDefault(id, Const.NULL);
                return true;
            }
        }.call();
        return ret.value;
    }

    public static void removeRecord(long id, int rankType) {
        getRecordById(id).getRecords().remove(rankType);
    }

    public static Map<Integer, xbean.BoardEntry> getLatestboard(int rankType) {
        return getDbBoardByType(rankType).getLatestboard();
    }

    /**
     * 获取家族排名
     *
     * @param from
     * @param length 长度
     * @return
     */
    public static List<Long> getFamilyIdByRanking(int from, int length) {
        final Map<Integer, Long> ranks = LeaderBoardModule.familyRankMap;
        List<Long> ret = new ArrayList<>();
        for (int i = from, n = Math.min(from + length-1, ranks.size()); i <= n ; i++) {
            ret.add(ranks.get(i));
        }
        return ret;
    }

    public static void recordYesterdayRanking() {
        LeaderBoardModule.INSTANCE.championRoleIds.clear();
        LeaderBoardModule.INSTANCE.getRankType().forEach(type -> {
            RefObject<Long> id = new RefObject<Long>(Long.valueOf(Const.NULL));
            new Procedure() {
                @Override
                protected boolean process() throws Exception {
                    xbean.BoardInfo boardInfo = getDbBoardByType(type);
                    if(LeaderBoardModule.INSTANCE.broadcastBoards.containsKey(type)){
                        xbean.BoardEntry boardEntry = boardInfo.getLatestboard().get(1);
                        if(boardEntry != null){
                            id.value = boardEntry.getId();
                        }
                    }
                    Map<Long, Integer> yest = boardInfo.getYesterdayrank();
                    yest.clear();
                    yest.putAll(boardInfo.getRolerank());
                    return true;
                }
            }.call();
            if(type == RankType.FAMILY && id.value != Const.NULL){
                new Procedure() {
                    @Override
                    protected boolean process() throws Exception {
                        Long temp = Family.selectChiefid(id.value);
                        id.value = temp == null ? Const.NULL : temp;
                        return true;
                    }
                }.call();
            }
            if(id.value != Const.NULL){
                LeaderBoardModule.INSTANCE.championRoleIds.put(type, id.value);
            }
        });

        // 给运营记录日志
        recordLog();
    }

    private static void recordLog() {
        try {
            final int logCount = 100;

            LeaderBoardModule.INSTANCE.getRankType().stream()
                    .forEach(type -> new Procedure() {
                        @Override
                        protected boolean process() throws Exception {
                            xbean.BoardInfo boardInfo = Leaderboards.select(type);
                            Map<Integer, xbean.BoardEntry> latest = boardInfo.getLatestboard();
                            boardInfo.getYesterdayrank().forEach((id, rank) -> {
                                if (rank > 0 && rank <= logCount) {
                                    if (type == RankType.FAMILY) {
                                        FLogger.familyRank(id, rank);
                                    } else {
                                        FLogger.nomarlRank(id, Roleinfos.select(id), type, rank, latest.get(rank).getVal1());
                                    }
                                }
                            });
                            return true;
                        }
                    }.call());
        } catch (Exception e){
            Trace.error("record leaderboard log error", e);
        }
    }


    static class CompareObj {
        private long val1;
        private int val2;
        private long updateTime;

        public CompareObj(BoardRecordEntry record) {
            this.val1 = record.getVal1();
            this.val2 = record.getVal2();
            this.updateTime = record.getUpdatetime();
        }
    }

}
