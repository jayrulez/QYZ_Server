package lx.gs.arena;

import cfg.CfgMgr;
import cfg.Const;
import cfg.achievement.CounterType;
import cfg.active.EventNum;
import cfg.arena.ArenaRankStep;
import cfg.arena.ChallengeType;
import cfg.bonus.RankType;
import cfg.currency.CurrencyType;
import cfg.fight.CampType;
import gnet.link.Onlines;
import lx.gs.achievement.FAchievement;
import lx.gs.arena.msg.ChallangeInfo;
import lx.gs.arena.msg.SChallenge;
import lx.gs.arena.msg.SGetChallenge;
import lx.gs.arena.msg.SNewFightReport;
import lx.gs.bonus.FBonus;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.event.ArenaWinEvent;
import lx.gs.event.EventModule;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;
import lx.gs.rank.FRank;
import lx.gs.role.FRole;
import lx.gs.system.FSystem;
import map.msg.PlayerBuilder;
import map.msg.SEndArenaEctype;
import xdb.Lockeys;
import xdb.Transaction;
import xtable.Roleinfos;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by huangqiang on 2016/2/25.
 */
public final class FArena {

    public static xbean.RoleArena get(long roleid) {
        xbean.RoleArena info = xtable.Rolearena.get(roleid);
        if (info == null) {
            info = xbean.Pod.newRoleArena();
            info.setOpen(false);
            info.setHaswin(false);
            xtable.Rolearena.insert(roleid, info);
        }
        return info;
    }

    public static boolean doOpen(long roleid, xbean.RoleArena info) {
        assert (!info.getOpen());
        info.setOpen(true);
        info.setLastupdateshengwangtime(System.currentTimeMillis());
        refreshChallengeRanks(info, 0);
        return false;
    }

    public static int getRank(long roleid) {
        return getSwapRank().getRankById(roleid);
    }

    public static FRank.XSwapRank getSwapRank() {
        return FRank.getSwapRank(RankType.ARENA, false);
    }

    public static lx.gs.arena.msg.SInfo createSInfo(long roleid, xbean.RoleArena info) {
        final lx.gs.arena.msg.SInfo re = new lx.gs.arena.msg.SInfo();
        final xbean.DailyArena daily = FLimit.getDaily(roleid).getArena();
        re.isopen = common.Utils.toByte(info.getOpen());
        re.rank = getRank(roleid);
        re.challengesuccnum = daily.getChallengesuccnum();
        re.obtainrewards.addAll(daily.getObtainrewards());
        info.getFightreports().forEach(r -> re.reports.add(convertReport(r)));
        return re;
    }


    public static void setChallengeRanks(long roleid, int rank) {
        final xbean.RoleArena info = get(roleid);
        if(info.getHaswin()) {
            final List<Integer> challengeRanks = info.getChallengeranks();
            final List<Long> challengeRobots = info.getChallengerobots();
            challengeRanks.clear();
            challengeRobots.clear();
            challengeRanks.add(rank);
            xdb.Transaction.tsendWhileCommit(roleid, FArena.createSGetChallenge(info));
        }
    }

    public final static int CHALLENGE_NUM = 3;

    private static boolean addNotExitAndExcludeSelf(List<Integer> ranks, int excludeRank, int low, int high) {
        final int rank = common.Utils.randomRange(low, high);
        if(rank != excludeRank && !ranks.contains(rank)) {
            ranks.add(rank);
            return true;
        }
        return false;
    }

    public static void refreshChallengeRanks(xbean.RoleArena info, int rank) {
        final List<Integer> challengeRanks = info.getChallengeranks();
        final List<Long> challengeRobots = info.getChallengerobots();
        challengeRanks.clear();
        challengeRobots.clear();
        // 还没赢过任何一场每次随机机器人对手
        if (!info.getHaswin()) {
            List<Long> outRankRobots = FSystem.get().getOutrankrobots();
            challengeRanks.addAll(common.Utils.getMutiRandom(ArenaModule.RANK_SIZE * 3 /2, ArenaModule.RANK_SIZE * 2, CHALLENGE_NUM));
            challengeRobots.addAll(common.Utils.getMutiRandom(0, outRankRobots.size(), CHALLENGE_NUM).stream().map(idx -> outRankRobots.get(idx)).collect(Collectors.toList()));
            return;
        }
        rank = rank > 0 ? rank : info.getFakerank();

        final ArenaRankStep rankStep = getRankStep(rank);
        int trycount = 10;
        for(cfg.arena.ArenaRivalBound rival : rankStep.rivalbounds) {
            if(rival.low != Const.NULL) {
                if(rival.up != Const.NULL) {
                    while(trycount-- > 0 && rival.up <= rival.low && !addNotExitAndExcludeSelf(challengeRanks, rank, rival.up, rival.low + 1));
                } else {
                    while(trycount-- > 0 && rank < rival.low && !addNotExitAndExcludeSelf(challengeRanks, rank, rank + 1, rival.low + 1));
                }
            } else {
                if(rival.up != Const.NULL) {
                    while(trycount-- > 0 && rival.up < rank && !addNotExitAndExcludeSelf(challengeRanks, rank, rival.up, rank));
                } else {
                    throw new RuntimeException("Arena rankstep:" + rankStep.upbound + " 's bounds are both null!");
                }
            }
        }

        xdb.Trace.debug("FArena.refreshChallengeRanks ranks:{}", challengeRanks);
//      旧版逻辑，以防万一，注释处理
//        if(rank <= 0)
//            rank = getSwapRank().size() + 1;
//        final List<Integer> challengeRanks = info.getChallengeranks();
//        challengeRanks.clear();
//        if(rank > 3) {
//            final cfg.arena.ArenaRankStep step = getRankStep(rank);
//            int lastMaxRank = rank;
//            for(int i = 1 ; i <= 3 ; i++) {
//                final int max = Math.min(rank - i * step.step + step.randrange, lastMaxRank - 1);
//                final int min = Math.max(rank - i * step.step - step.randrange, 4 - i);
//                while((lastMaxRank = common.Utils.randomRange(min, max + 1)) == rank);
//                challengeRanks.add(lastMaxRank);
//            }
//            Collections.swap(challengeRanks, 0, 2);
//        } else {
//            for(int i = 1 ; i <= 3 ; i++) {
//                if(i != rank)
//                    challengeRanks.add(i);
//            }
//            challengeRanks.add(common.Utils.randomRange(4, 11));
//        }
//        xdb.Trace.info("FArena.refreshChallengeRanks ranks:{}", challengeRanks);
    }

    public static long getOpponentByRank(xbean.RoleArena info, int rank) {
        if(info.getHaswin()) {
            return getSwapRank().getIdByRank(rank);
        } else {
            final List<Integer> challengeRanks = info.getChallengeranks();
            final List<Long> challengeRobots = info.getChallengerobots();
            for(int i = 0 ; i < challengeRanks.size() ; i++) {
                if(challengeRanks.get(i) == rank)
                    return challengeRobots.get(i);
            }
            return 0;
        }
    }

    public static int getOpponentRankById(xbean.RoleArena info, long id) {
        if(info.getHaswin()) {
            return getSwapRank().getRankById(id);
        } else {
            final List<Integer> challengeRanks = info.getChallengeranks();
            final List<Long> challengeRobots = info.getChallengerobots();
            for(int i = 0 ; i < challengeRanks.size() ; i++) {
                if(challengeRobots.get(i) == id)
                    return challengeRanks.get(i);
            }
            return 0;
        }
    }

    public static void addFightReport(long roleid, xbean.RoleArena info, xbean.ArenaFightReport report) {
        final List<xbean.ArenaFightReport> reports = info.getFightreports();
        info.getFightreports().add(report);
        if (reports.size() > CfgMgr.arenaconfig.maxreportnum) {
            reports.remove(0);
        }
        xdb.Transaction.tsendWhileCommit(roleid, new SNewFightReport(convertReport(report)));
    }

    private static cfg.arena.ArenaRankStep getRankStep(int rank) {
        cfg.arena.ArenaRankStep scfg = null;
        for(cfg.arena.ArenaRankStep step : CfgMgr.arenaconfig.ranksteps) {
            if(rank >= step.upbound)
                scfg =step;
            else
                break;
        }
        return scfg != null ? scfg :common.Utils.getLast(CfgMgr.arenaconfig.ranksteps);
    }

    public static lx.gs.arena.msg.FightReport convertReport(xbean.ArenaFightReport report) {
        final lx.gs.arena.msg.FightReport re = new lx.gs.arena.msg.FightReport();
        re.challengetype = report.getChallengetype();
        re.fighttime = report.getFighttime();
        re.oldrank = report.getOldrank();
        re.newrank = report.getNewrank();
        re.opponentname = report.getOpponentname();
        re.succ = report.getSucc();
        return re;
    }

    public static lx.gs.arena.msg.ChallangeInfo convertChallenge(int rank, long robotid) {
        final lx.gs.arena.msg.ChallangeInfo re = new lx.gs.arena.msg.ChallangeInfo();
        re.rank = rank;
        final long roleid = robotid == 0 ? getSwapRank().getIdByRank(rank) : robotid;
        final lx.gs.role.msg.RoleShowInfo1 info = re.roleinfo;
        FRole.genRoleShowInfo(roleid, info);
        FPet.genPetBriefs(roleid, re.pets);
        return re;
    }

    public static SGetChallenge createSGetChallenge(xbean.RoleArena info) {
        final SGetChallenge re = new SGetChallenge();
        fill(re.challengeranks, info);
        return re;
    }

    private static void fill(List<ChallangeInfo> challangeInfos, xbean.RoleArena info) {
        final List<Integer> challengeRanks = info.getChallengeranks();
        final List<Long> challengeRobots = info.getChallengerobots();
        if(challengeRobots.size() < challengeRanks.size()) {
            for(Integer rank : challengeRanks) {
                challangeInfos.add(convertChallenge(rank, 0));
            }
        } else {
            for(int i = 0 ; i < challengeRanks.size() ; i++) {
                challangeInfos.add(convertChallenge(challengeRanks.get(i), challengeRobots.get(i)));
            }
        }
    }

    public static PlayerBuilder createFakePlayerBuilder(long roleid) {
        return FMap.createFakePlayerBuilder(roleid, CampType.ENEMY);
    }

    public static int getShengWangIncreateRate(int rank) {
        cfg.arena.ArenaShengwang acfg = null;
        for (cfg.arena.ArenaShengwang c : CfgMgr.arenaconfig.shengwangsteplist) {
            if (c.minrank > rank) break;
            acfg = c;
        }
        return acfg != null ? acfg.addshengwang : 0;
    }

    static void refreshShengWang(long roleid, long now) {
        refreshShengWang(roleid, FArena.get(roleid), now);
    }

    static void refreshShengWang(long roleid, xbean.RoleArena info, long now) {
        final int rank = getRank(roleid);
        if (rank > 0) {
            final long add =  ((now - info.getLastupdateshengwangtime()) * getShengWangIncreateRate(rank)) / CfgMgr.arenaconfig.shengwangrefreshinterval / 1000;
            if (add > 0) {
                FRole.addCurrency(roleid, CurrencyType.ShengWang, add, By.Arena_Refresh);
                info.setLastupdateshengwangtime(now);
            }
        } else {
            info.setLastupdateshengwangtime(now);
        }
    }

    private static cfg.arena.ArenaAward getNormalReward(int level) {
        return CfgMgr.arenaconfig.awardlist.stream().filter(e -> e.maxlvl >= level).findFirst().orElse(null);
    }

    public static boolean endChallenge(long roleid, long opponent, int retcode) {
        Lockeys.lock(xtable.Rolearena.getTable(), Arrays.asList(roleid, opponent));
        final xbean.RoleArena self = get(roleid);
        final xbean.RoleArena oppo = get(opponent);
        if (self.getBechallenging() == 0) {
            xdb.Trace.error("Arena.endChallenge role not in challenge. roleid:{} opponent:{}", roleid, opponent);
            return false;
        }

        final boolean succ = (retcode == 0);
        self.setBechallenging(0);
        oppo.setBechallenging(0);

        final boolean isRobot = oppo.getIsrobot();
        final long now = System.currentTimeMillis();

        final int orank = getOpponentRankById(self, opponent);
        if(orank <= 0)
            return false;

        final SEndArenaEctype re = new SEndArenaEctype();
        re.errcode = retcode;

        final int sNewRank;
        final int oNewRank;
        boolean rankUp = false;
        final FRank.XSwapRank swapRank = getSwapRank();
        final int srank;
        if(succ) {
            if(self.getHaswin()) {
                srank = swapRank.getRankById(roleid);
                if(srank <= 0) {
                    sNewRank = orank;
                    oNewRank = srank;
                    swapRank.swap(roleid, opponent);
                    rankUp = true;
                } else if(srank > orank){
                    sNewRank = orank;
                    oNewRank = srank;
                    refreshShengWang(roleid, self, now);
                    if(!isRobot) {
                        refreshShengWang(opponent, oppo, now);
                    }
                    swapRank.swap(roleid, opponent);
                    rankUp = true;
                } else {
                    sNewRank = srank;
                    oNewRank = orank;
                }
            } else {
                srank = 0;
                self.setHaswin(true);
                self.setFakerank(orank);
                sNewRank = orank;
                oNewRank = 0;
                rankUp = true;
            }
            if(rankUp) {
                int bestRank = self.getBestrank();
                if(bestRank <= 0)
                    bestRank = Integer.MAX_VALUE;
                if(sNewRank < bestRank) {
                    self.setBestrank(sNewRank);
                    boolean newReward = false;
                    for(cfg.bonus.RankBonusList bonus : CfgMgr.arenaconfig.rankbonus) {
                        final int requirerank = bonus.requirerank;
                        if(requirerank < bestRank && requirerank >= sNewRank) {
                            FBonus.genBonus(roleid, bonus.bonuslist, 1, common.Bonus.BindType.BIND, re.bonus);
                            newReward = true;
                        }
                    }
                    if(newReward) {
                        FBonus.addBonusAlwaysSucc(roleid, re.bonus, By.Arena_Rank);
                    }
                }
            }
            final xbean.DailyArena dailyArena = FLimit.getDaily(roleid).getArena();
            final int newSuccNum = dailyArena.getChallengesuccnum() + 1;
            dailyArena.setChallengesuccnum(newSuccNum);

            refreshChallengeRanks(self, sNewRank);

            final xbean.RoleArena selfData = self.toData();
            Transaction.texecuteWhileCommit(() -> {
                new xdb.Procedure() {
                    @Override
                    protected boolean process() {
                        final SChallenge sChallenge = new SChallenge();
                        sChallenge.newrank = sNewRank;
                        sChallenge.challengesuccnum = newSuccNum;
                        fill(sChallenge.challengeranks, selfData);
                        xdb.Transaction.tsend(roleid, sChallenge);
                        return true;
                    }
                }.execute();
            });

            EventModule.INSTANCE.broadcastEvent(new ArenaWinEvent(roleid));
            FDailyActivity.addActiveScores(roleid, EventNum.ARENA); //only win can add scores
            FAchievement.addCounter(roleid, CounterType.PVP_1V1_WIN, 1);
        } else {
            srank = sNewRank = swapRank.getRankById(roleid);
            oNewRank = orank;
        }

        final xbean.ArenaFightReport sreport = xbean.Pod.newArenaFightReport();
        sreport.setFighttime(now);
        sreport.setChallengetype(ChallengeType.CHALLENGE);
        sreport.setOldrank(srank);
        sreport.setNewrank(sNewRank);
        sreport.setSucc(common.Utils.toByte(succ));
        sreport.setOpponentname(xtable.Roleinfos.selectName(opponent));
        addFightReport(roleid, self, sreport);

        if (!isRobot) {
            final xbean.ArenaFightReport oreport = xbean.Pod.newArenaFightReport();
            oreport.setFighttime(now);
            oreport.setChallengetype(ChallengeType.BECHALLENGE);
            oreport.setOldrank(orank);
            oreport.setNewrank(oNewRank);
            oreport.setSucc(common.Utils.toByte(!succ));
            oreport.setOpponentname(xtable.Roleinfos.selectName(roleid));
            addFightReport(opponent, oppo, oreport);
        }


        re.newrank = sNewRank;
        final cfg.arena.ArenaAward normalReward = getNormalReward(Roleinfos.selectLevel(roleid));
        if (normalReward != null) {
            final map.msg.Bonus bonus = new map.msg.Bonus();
            FBonus.genAndAddBonusAlwaysSucc(roleid, (succ ? normalReward.winaward : normalReward.loseaward), common.Bonus.BindType.BIND, bonus, By.Arena_Challenge_Success);
            common.Bonus.merge(re.bonus, bonus);
        }

        Onlines.getInstance().send(roleid, re);
        return true;
    }
}
