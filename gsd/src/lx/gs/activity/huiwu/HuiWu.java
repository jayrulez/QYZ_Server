package lx.gs.activity.huiwu;

import cfg.CfgMgr;
import cfg.Const;
import cfg.achievement.CounterType;
import cfg.huiwu.BattleState;
import cfg.huiwu.RoundStage;
import cfg.huiwu.Stage;
import cfg.role.EProfessionType;
import common.Bonus;
import common.Time;
import gnet.link.Onlines;
import lx.gs.achievement.FAchievement;
import lx.gs.activity.Activity;
import lx.gs.activity.ActivityModule;
import lx.gs.activity.WeeklyTimeCaculator;
import lx.gs.activity.huiwu.msg.*;
import lx.gs.bonus.FBonus;
import lx.gs.mail.FMail;
import lx.gs.map.FMap;
import lx.gs.rank.RankVector;
import map.msg.XCreateHuiWu;
import xdb.Trace;
import xtable.Roleinfos;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by huangqiang on 2016/3/19.
 */
public class HuiWu extends Activity{
    public static HuiWu Instance;

    private final cfg.huiwu.HuiWu hcfg;
    public HuiWu(cfg.huiwu.HuiWu hcfg) {
        super(0, common.Time.calcWeekMilliseconds(hcfg.enrollend) - 3600 * 1000L); // 报名结束前一小时
        this.hcfg = hcfg;

        this.intervalOpenTimeCalculators.add(new WeeklyTimeCaculator(0, 0, 0, 0, 86400 * 7 - 1));
        this.afterOpenWorks.add(new Work(Time.calcWeekMilliseconds(hcfg.enrollopen), this::onEnrollOpen));
        this.afterOpenWorks.add(new Work(Time.calcWeekMilliseconds(hcfg.enrollend), this::onEnrollEnd));
        this.afterOpenWorks.add(new Work(Time.calcWeekMilliseconds(hcfg.preselectopen1), this::onPreSelectionOpen1));
        this.afterOpenWorks.add(new Work(Time.calcWeekMilliseconds(hcfg.preselectend1), this::onPreSelectionEnd1));
        this.afterOpenWorks.add(new Work(Time.calcWeekMilliseconds(hcfg.preselectopen2), this::onPreSelectionOpen2));
        this.afterOpenWorks.add(new Work(Time.calcWeekMilliseconds(hcfg.preselectend2), this::onPreSelectionEnd2));


        long startTime = common.Time.calcWeekMilliseconds(hcfg.battleopen);
        this.afterOpenWorks.add(new Work(startTime, this::onBattleBegin));
        for (cfg.huiwu.BattleTimeControler btc : hcfg.battletime) {
            afterOpenWorks.add(new Work(startTime, HuiWu.this::onRoundBegin));
            startTime += btc.wait * 1000L;
            afterOpenWorks.add(new Work(startTime, HuiWu.this::onRoundFightBegin));
            startTime += btc.battle * 1000L;
            afterOpenWorks.add(new Work(startTime, HuiWu.this::onRoundRestBegin));
            startTime += btc.relax * 1000L;
        }
        afterOpenWorks.add(new Work(startTime, HuiWu.this::onBattleEnd));

        Instance = this;
    }

    @Override
    public void onLoad() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm curTermInfo = FHuiWu.getCurTermInfo();
                if(curTermInfo == null) {
                    final xbean.HuiWuCurTerm newTermInfo = xbean.Pod.newHuiWuCurTerm();
                    newTermInfo.setTermid(0);
                    newTermInfo.setOpentime(0);
                    newTermInfo.setEndtime(Const.NULL);
                    newTermInfo.setStage(Stage.END_BATTLE);
                    FHuiWu.setNewTermInfo(newTermInfo);
                    scheduleNext();
                    xdb.Trace.info("huiwu.onLoad first termid:0");
                } else {
                    scheduleNext();
                    final int stage = curTermInfo.getStage();
                    if(getOpenTime() == curTermInfo.getOpentime() && stage < Stage.END_ENROLL) {
                        // 停服后重启,如果还处于报名阶段
                        // 啥也不做
                        // 除了可能添加了新职业外
                        if(stage > Stage.BEFORE_ENROLL) {
                            final Map<Integer, xbean.HuiWuProfessionTerm> professionTerms = curTermInfo.getTerminfobyprofession();
                            EProfessionType.enums.stream().forEach(profession -> {
                                if(!professionTerms.containsKey(profession)) {
                                    final xbean.HuiWuProfessionTerm term = xbean.Pod.newHuiWuProfessionTerm();
                                    term.setProfession(profession);
                                    professionTerms.put(profession, term);
                                }
                            });
                        }

                    } else {
                        curTermInfo.setStage(Stage.END_BATTLE);
                    }
                }
                return true;
            }
        }.call();
    }

    @Override
    protected void onOpen() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                final int stage = info.getStage();
                xdb.Trace.info("huiwu.onOpen old termid:{} stage:{}", info.getTermid(), stage);
                if(stage != Stage.END_BATTLE ) {
                    xdb.Trace.info("huiwu.onOpen stage:{} != END_BATTLE, skip.", stage);
                    return false;
                }
                final xbean.HuiWuCurTerm newTermInfo = xbean.Pod.newHuiWuCurTerm();

                newTermInfo.setTermid(info.getTermid() + 1);
                newTermInfo.getContinuouschampions().putAll(info.getContinuouschampions());
                newTermInfo.setStage(Stage.BEFORE_ENROLL);
                newTermInfo.setRoundindex(0);
                newTermInfo.setRoundstage(RoundStage.NULL);
                newTermInfo.setOpentime(getOpenTime());
                newTermInfo.setEndtime(Const.NULL);

                newTermInfo.setGuessendtime(Time.getTimeInfo().weekZeroTime + Time.calcWeekMilliseconds(hcfg.battleopen));
                FHuiWu.setNewTermInfo(newTermInfo);
                xdb.Trace.info("huiwu.open new termid:{}", newTermInfo.getTermid());
                return true;
            }
        }.call();
    }

    private void onEnrollOpen() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                xdb.Trace.info("huiwu.onEnrollOpen termid:{} stage:{}", info.getTermid(), info.getStage());
                if(info.getStage() != Stage.BEFORE_ENROLL) {
                    xdb.Trace.info("huiwu.onEnrollOpen stage:{} != BEFORE_ENROLL, skip.", info.getStage());
                    return false;
                }
                info.setStage(Stage.BEGIN_ENROLL);
                final Map<Integer, xbean.HuiWuProfessionTerm> professionTerms = info.getTerminfobyprofession();
                professionTerms.clear();

                info.setRoundindex(0);
                for(int profession : EProfessionType.enums) {
                    final xbean.HuiWuProfessionTerm term = xbean.Pod.newHuiWuProfessionTerm();
                    term.setProfession(profession);
                    professionTerms.put(profession, term);
                }
                final STermInfo msg = new STermInfo();
                msg.termid = info.getTermid();
                msg.stage = info.getStage();
                broadcast(msg);
                return true;
            }
        }.call();
    }

    private void onEnrollEnd() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                xdb.Trace.info("huiwu.onEnrollEnd termid:{} stage:{}", info.getTermid(), info.getStage());
                if(info.getStage() != Stage.BEGIN_ENROLL) {
                    xdb.Trace.info("huiwu.onEnrollEnd stage:{} != BEGIN_ENROLL, skip.", info.getStage());
                    return false;
                }
                info.setStage(Stage.END_ENROLL);

                broadcast(new STermStageChange(info.getTermid(), info.getStage()));
                return true;
            }
        }.call();
    }

    private void onPreSelectionOpen1() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                xdb.Trace.info("huiwu.onPreSelectionOpen1 termid:{} stage:{}", info.getTermid(), info.getStage());
                if(info.getStage() != Stage.END_ENROLL) {
                    xdb.Trace.info("huiwu.onPreSelectionOpen1 stage:{} != END_ENROLL, skip.", info.getStage());
                    return false;
                }
                info.setStage(Stage.BEGIN_PRESELECT1);
                for(xbean.HuiWuProfessionTerm professionTerm : info.getTerminfobyprofession().values()) {
                    final Set<Long> enrollRoleids = professionTerm.getEnrollroleids();
                    final RankVector rankVector = new RankVector(hcfg.preselect1num, false);
                    enrollRoleids.forEach(roleid -> rankVector.update(roleid, xtable.Roleattrs.selectTotalcombatpower(roleid)));

                    final Set<Long> preselect1Roleids = rankVector.getRanks().stream().map(r -> r.id).collect(Collectors.toSet());
                    for(long roleid : preselect1Roleids) {
                        xdb.Transaction.texecuteWhileCommit(new xdb.Procedure() {
                            @Override
                            protected boolean process() {
                                final map.msg.Bonus bonus = new map.msg.Bonus();
                                FBonus.genBonus(roleid, hcfg.preselectaward, 1, Bonus.BindType.BIND, bonus);
                                FMail.addMail(roleid, cfg.huiwu.HuiWu.HUIWU_PRESELECTION1_SUCC_MAILID, bonus);
                                return true;
                            }
                        });
                    }

                    // 向被淘汰玩家发送通知
                    for(long roleid : enrollRoleids) {
                        if(!preselect1Roleids.contains(roleid)) {
                            xdb.Transaction.texecuteWhileCommit(new xdb.Procedure() {
                                @Override
                                protected boolean process() {
                                    FMail.addMail(roleid, cfg.huiwu.HuiWu.HUIWU_PRESELECTION1_FAIL_MAILID);
                                    return true;
                                }
                            });
                        }
                    }
                    professionTerm.getPreselection1roleids().addAll(preselect1Roleids);
                    enrollRoleids.clear();
                    xdb.Trace.info("preselectionopen1. roleids:{}", preselect1Roleids);
                }
                broadcast(new STermStageChange(info.getTermid(), info.getStage()));
                return true;
            }
        }.call();
    }

    private void onPreSelectionEnd1() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                xdb.Trace.info("huiwu.onPreSelectionEnd1 termid:{} stage:{}", info.getTermid(), info.getStage());
                if(info.getStage() != Stage.BEGIN_PRESELECT1) {
                    xdb.Trace.info("huiwu.onPreSelectionEnd1 stage:{} != BEGIN_PRESELECT1, skip.", info.getStage());
                    return false;
                }
                info.setStage(Stage.END_PRESELECT1);
                broadcast(new STermStageChange(info.getTermid(), info.getStage()));
                return true;
            }
        }.call();
    }

    private void onPreSelectionOpen2() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                xdb.Trace.info("huiwu.onPreSelectionOpen2 termid:{} stage:{}", info.getTermid(), info.getStage());
                if(info.getStage() != Stage.END_PRESELECT1) {
                    xdb.Trace.info("huiwu.onPreSelectionOpen2 stage:{} != END_PRESELECT1, skip.", info.getStage());
                    return false;
                }
                info.setStage(Stage.BEGIN_PRESELECT2);
                for(xbean.HuiWuProfessionTerm professionTerm : info.getTerminfobyprofession().values()) {
                    final List<Long> preselect1Roleids = professionTerm.getPreselection1roleids();
                    final RankVector rankVector = new RankVector(hcfg.preselect1num, false);
                    preselect1Roleids.forEach(roleid -> rankVector.update(roleid, xtable.Roleattrs.selectTotalcombatpower(roleid)));

                    final ArrayList<RankVector.Record> records = rankVector.getRanks();
                    final int totalnum = records.size();

                    // 取排名前几位的保送玩家
                    final int priornum = hcfg.preselect2priornum;
                    final int luckynum = hcfg.preselect2luckynum;
                    final int reservernum = priornum + luckynum;
                    final int preselect2num = hcfg.preselect2num;
                    final Random random = common.Utils.random();



                    // 筛选
                    if (preselect2num < totalnum) {
                        // 取幸运玩家
                        for (int i = priornum; i < reservernum; i++) {
                            final int idx = i + random.nextInt(totalnum - i);
                            Collections.swap(records, i, idx);
                        }

                        for (int endidx = totalnum, needKickoutNum = totalnum - preselect2num; needKickoutNum > 0; ) {
                            final int size = endidx - reservernum;
                            for (int i = reservernum; i < size / 2 + reservernum && needKickoutNum > 0; i++, needKickoutNum--) {
                                // 胜利者移到 i 位置,失败者移到endidx-1位置
                                final int idx1 = i + random.nextInt(endidx - i);
                                final RankVector.Record r1 = records.get(idx1);
                                Collections.swap(records, i, idx1);
                                --endidx;
                                final int idx2 = i + 1 + random.nextInt(endidx - i);
                                final RankVector.Record r2 = records.get(idx2);
                                Collections.swap(records, endidx, idx2);
                                final long winner;
                                final long loser;
                                if (random.nextDouble() < (double) (r1.value) / (r1.value + r2.value)) {
                                    winner = r1.id;
                                    loser = r2.id;
                                } else {
                                    Collections.swap(records, i, endidx);
                                    winner = r2.id;
                                    loser = r1.id;
                                }
                                xdb.Transaction.texecuteWhileCommit(new xdb.Procedure() {
                                    @Override
                                    protected boolean process() {
                                        FMail.addMail(loser, cfg.huiwu.HuiWu.HUIWU_PRESELECTION2_FAIL_MAILID, Arrays.asList(Roleinfos.selectName(winner)));
                                        return true;
                                    }
                                });
                            }
                        }
                    }

                    // 预选出结果
                    final int finalSize = Math.min(totalnum, preselect2num);
                    final List<Long> preselect2Roleids = rankVector.getRanks().subList(0, finalSize)
                            .stream().map(r -> r.id).collect(Collectors.toList());
                    // 给进入预选的玩家发奖
                    for(long roleid : preselect2Roleids) {
                        xdb.Transaction.texecuteWhileCommit(new xdb.Procedure() {
                            @Override
                            protected boolean process() {
                                FMail.addMail(roleid, cfg.huiwu.HuiWu.HUIWU_PRESELECTION2_SUCC_MAILID);
                                return true;
                            }
                        });
                    }

                    //if(finalSize <= hcfg.specialranks.size()) {
                    Collections.shuffle(preselect2Roleids, random);
//                    } else {
//                        // 为特殊排名的玩家安排特殊位置
//                        int originRank = 0;
//                        final ArrayList<Integer> suffleSpeicalRanks = new ArrayList<>(hcfg.specialranks);
//                        Collections.shuffle(suffleSpeicalRanks, random);
//                        for(int swapSpecialRank : suffleSpeicalRanks) {
//                            if(swapSpecialRank < finalSize) {
//                                Collections.swap(preselect2Roleids, originRank, swapSpecialRank);
//                            }
//                            originRank++;
//                        }
//                    }

                    professionTerm.getPreselection2roleids().addAll(preselect2Roleids);
                    professionTerm.getPreselection2roleidbeguessnums().putAll(preselect2Roleids.stream().collect(Collectors.toMap(rid -> rid, rid-> 0)));

                    // 清除已经不再需要的 筛选阶段的玩家列表
                    preselect1Roleids.clear();
                }
                broadcast(new STermStageChange(info.getTermid(), info.getStage()));
                return true;
            }
        }.call();
    }

    private void onPreSelectionEnd2() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                xdb.Trace.info("huiwu.onPreSelectionEnd2 termid:{} stage:{}", info.getTermid(), info.getStage());
                if(info.getStage() != Stage.BEGIN_PRESELECT2) {
                    xdb.Trace.info("huiwu.onPreSelectionEnd2 stage:{} != BEGIN_PRESELECT2, skip.", info.getStage());
                    return false;
                }
                info.setStage(Stage.END_PRESELECT2);
                final int roundIndex = 1;
                info.setRoundindex(roundIndex);
                final int remain = hcfg.preselect2num >> roundIndex;
                for(xbean.HuiWuProfessionTerm professionTerm : info.getTerminfobyprofession().values()) {
                    final xbean.HuiWuRound roundInfo = xbean.Pod.newHuiWuRound();
                    final List<xbean.HuiWuBattle> battles = roundInfo.getBattles();
                    final List<Long> preselection2roleids = professionTerm.getPreselection2roleids();
                    final int totalnum = preselection2roleids.size();
                    int index = 0;
                    for(int bid = 0, n = totalnum - remain ; bid < n ; bid++) {
                        final xbean.HuiWuBattle battle = xbean.Pod.newHuiWuBattle();
                        battle.setIndex(index++);
                        battle.setRoleid1(preselection2roleids.get(bid * 2));
                        battle.setRoleid2(preselection2roleids.get(bid * 2 + 1));
                        battle.setState(BattleState.NULL);
                        xdb.Trace.info("huiwu.round:{} battleid:{} battlepair:{}", roundIndex, bid, battle);
                        battles.add(battle);
                    }
                    for(int bid = Math.max(0, 2 * (totalnum - remain)) ; bid < totalnum ; bid++) {
                        final xbean.HuiWuBattle battle = xbean.Pod.newHuiWuBattle();
                        battle.setIndex(index++);
                        battle.setRoleid1(preselection2roleids.get(bid));
                        battle.setRoleid2(0);
                        battle.setState(BattleState.WIN);
                        battles.add(battle);
                        xdb.Trace.info("huiwu.round:{} battleid:{} default win pair:{}", roundIndex, bid, battle);
                    }
                    xdb.Trace.info("Huiwu.onPreSelectionEnd2 roundindex:{} battles:{}", roundIndex, battles);
                    professionTerm.getRounds().put(roundIndex, roundInfo);
                }
                broadcast(new STermStageChange(info.getTermid(), info.getStage()));
                return true;
            }
        }.call();
    }

    private long getWinner(xbean.HuiWuBattle battle) {
        return battle.getState() == BattleState.WIN ? battle.getRoleid1() : battle.getRoleid2();
    }

    private void onBattleBegin() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                xdb.Trace.info("huiwu.onBattleBegin termid:{} stage:{}", info.getTermid(), info.getStage());
                final int curStage = info.getStage();
                if (curStage != Stage.END_PRESELECT2) {
                    xdb.Trace.info("huiwu.onPreSelectionEnd2 stage:{} != END_PRESELECT2, skip.", curStage);
                    return false;
                }

                info.setStage(Stage.BEGIN_BATTLE);
                broadcast(new STermStageChange(info.getTermid(), info.getStage()));
                return true;
            }
        }.call();


    }

    private void broadcast(xio.Protocol proto) {
        Onlines.getInstance().broadcast(proto);
    }

    private void onRoundBegin() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                info.setRoundstage(RoundStage.ROUND_PREPARE);
                final int termid = info.getTermid();
                final int roundIndex = info.getRoundindex();
                xdb.Trace.info("huiwu.onRoundPrepare termid:{} round:{}", termid, roundIndex);
                broadcast(new SRoundStage(termid, roundIndex, info.getRoundstage()));

                // 通知参赛玩家参加下轮比赛
                final long battleBeginTime = System.currentTimeMillis() + hcfg.battletime.get(roundIndex - 1).wait * 1000L;
                info.setBattlebegintime(battleBeginTime);
                final List<Long> roleids = new ArrayList<>();
                for(xbean.HuiWuProfessionTerm professionTerm : info.getTerminfobyprofession().values()) {
                    final int profession = professionTerm.getProfession();
                    professionTerm.getRounds().get(roundIndex).getBattles().stream().forEach(battle -> {
                        final long roleid1 = battle.getRoleid1();
                        if(battle.getState() == BattleState.NULL) {
                            final long roleid2 = battle.getRoleid2();
                            roleids.add(battle.getRoleid1());
                            roleids.add(battle.getRoleid2());
                            final XCreateHuiWu builder = new XCreateHuiWu();
                            builder.ectypeid = CfgMgr.huiwu.ectypeid;
                            builder.serverid = gs.Utils.getServerId();
                            builder.profession = profession;
                            builder.roundindex = roundIndex;
                            builder.battleindex = battle.getIndex();
                            builder.player1 = FMap.createFakePlayerBuilder(roleid1, 0);
                            builder.player2 = FMap.createFakePlayerBuilder(roleid2, 0);

                            {
                                final SAttendNextBattle msg = new SAttendNextBattle();
                                msg.round = roundIndex;
                                msg.battlebegintime = battleBeginTime;
                                msg.opponent = xtable.Roleinfos.selectName(roleid2);
                                msg.opponentcombatpower = xtable.Roleattrs.selectTotalcombatpower(roleid2);
                                xdb.Transaction.tsendWhileCommit(roleid1, msg);
                            }

                            {
                                final SAttendNextBattle msg = new SAttendNextBattle();
                                msg.round = roundIndex;
                                msg.battlebegintime = battleBeginTime;
                                msg.opponent = xtable.Roleinfos.selectName(roleid1);
                                msg.opponentcombatpower = xtable.Roleattrs.selectTotalcombatpower(roleid1);
                                xdb.Transaction.tsendWhileCommit(roleid2, msg);
                            }
                            xdb.Transaction.texecuteWhileCommit(() ->
                                FMap.createMapInProcedure(gs.Utils.getServerId(), builder, (b, mapid) -> {
                                    new xdb.Procedure() {
                                        @Override
                                        protected boolean process() {
                                            FHuiWu.getCurTermInfo().getTerminfobyprofession().get(profession).getRounds().get(b.roundindex)
                                                    .getBattles().get(b.battleindex).setMapid(mapid);
                                            Trace.info("Huiwu.onRoundBegin createMap profession:{} round:{} battle:{} mapid:{} roleid1:{} roleid2:{}",
                                                    profession, b.roundindex, b.battleindex, mapid, roleid1, roleid2);
                                            return true;
                                        }
                                    }.execute();
                            }));
                        } else {
                            {
                                final SAttendNextBattle msg = new SAttendNextBattle();
                                msg.round = roundIndex;
                                msg.battlebegintime = battleBeginTime;
                                xdb.Transaction.tsendWhileCommit(roleid1, msg);
                            }
                        }
                    });
                }
                return true;
            }
        }.call();
    }

    private void onRoundFightBegin() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                info.setRoundstage(RoundStage.ROUND_FIGHT);
                final int termid = info.getTermid();
                final int roundIndex = info.getRoundindex();
                xdb.Trace.info("huiwu.onRoundFightBegin termid:{} round:{}", termid, roundIndex);
                broadcast(new SRoundStage(termid, roundIndex, info.getRoundstage()));
                /*
                for(Map.Entry<Integer, xbean.HuiWuProfessionTerm> e : info.getTerminfobyprofession().entrySet()) {
                    final int profession = e.getKey();
                    final xbean.HuiWuProfessionTerm professionTerm = e.getValue();
                    final xbean.HuiWuRound roundInfo = professionTerm.getRounds().get(roundIndex);
                    int bid = 0;
                    for(xbean.HuiWuBattle battle : roundInfo.getBattles()) {
                        final long roleid1 = battle.getRoleid1();
                        final long roleid2 = battle.getRoleid2();
                        xdb.Trace.info("huiwu.onRoundFightBegin profession:{} battleid:{} roleid1:{} roleid2:{} result:{}",
                                profession, bid, roleid1, roleid2, battle.getState());
                        ++bid;
                        if(battle.getState() != BattleState.NULL) continue;
                        // huiwu 里全重新设置camp
                        xdb.Transaction.texecuteWhileCommit(new xdb.Procedure() {
                            @Override
                            protected boolean process() {
                                Lockeys.lock(xtable.Roleinfos.getTable(), Arrays.asList(roleid1, roleid2));
                                PlayerBuilder p1 = FMap.createFakePlayerBuilder(roleid1, 0);
                                PlayerBuilder p2 = FMap.createFakePlayerBuilder(roleid2, 0);
                                MapClient.sendToMap(battle.getMapid(), new XHuiWuFightBegin(p1, p2));
                                return true;
                            }
                        });
                    }
                }
                */

                return true;
            }
        }.call();
    }

    private void onRoundRestBegin() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                info.setRoundstage(RoundStage.ROUND_REST);

                final int termid = info.getTermid();
                final int oldRoundIndex = info.getRoundindex();
                broadcast(new SRoundStage(termid, oldRoundIndex, info.getRoundstage()));

                xdb.Trace.info("huiwu.onBattleEnd termid:{} round:{}", oldRoundIndex);

                final int newRoundIndex = oldRoundIndex + 1;
                final int remain = hcfg.preselect2num >> newRoundIndex;
                if(remain > 0) {
                    info.setRoundindex(newRoundIndex);
                } else {
                    xdb.Trace.info("huiwu.onRoundRest. last round:{}", oldRoundIndex);
                }
                final cfg.cmd.action.Bonus roundLoserBonus = hcfg.battleaward.get(oldRoundIndex - 1).award;
                for(xbean.HuiWuProfessionTerm professionTerm : info.getTerminfobyprofession().values()) {
                    final xbean.HuiWuRound roundInfo = xbean.Pod.newHuiWuRound();
                    professionTerm.getRounds().put(newRoundIndex, roundInfo);
                    final List<xbean.HuiWuBattle> newBattles = roundInfo.getBattles();
                    final List<xbean.HuiWuBattle> oldBattles = professionTerm.getRounds().get(oldRoundIndex).getBattles();
                    xdb.Trace.info("Huiwu.onRoundRestBegin before result. round:{} battles:{}", oldRoundIndex, oldBattles);
                    oldBattles.stream().filter(battle -> battle.getState() == BattleState.NULL).forEach(battle -> {
                        battle.setState(common.Utils.randomRange(0, 2) == 0 ? BattleState.WIN : BattleState.LOSE);
                        Trace.warn("huiwu.onRoundRestBegin. round:{} battle:{} result is NULL, random result",
                                oldRoundIndex, battle);
                    });
                    xdb.Trace.info("Huiwu.onRoundRestBegin after result. round:{} battles:{}", oldRoundIndex, oldBattles);
                    oldBattles.forEach(battle -> {
                        final long winner = getWinner(battle);
                        xdb.Transaction.texecuteWhileCommit(new xdb.Procedure() {
                            @Override
                            protected boolean process() {
                                FMail.addMail(winner, cfg.huiwu.HuiWu.HUIWU_BATTLE_WIN_MAILID);
                                return true;
                            }
                        });

                        final long roleid2 = battle.getRoleid2();
                        // roleid2 != 0 表示非轮空的回合
                        if(roleid2 != 0) {
                            final long loser = battle.getState() == BattleState.WIN ? roleid2 : battle.getRoleid1();
                            xdb.Transaction.texecuteWhileCommit(new xdb.Procedure() {
                                @Override
                                protected boolean process() {
                                    final map.msg.Bonus bonus = new map.msg.Bonus();
                                    FBonus.genBonus(loser, roundLoserBonus, 1, Bonus.BindType.BIND, bonus);
                                    FMail.addMail(loser, cfg.huiwu.HuiWu.HUIWU_BATTLE_FAIL_MAILID, bonus, Arrays.asList(Roleinfos.selectName(winner)));
                                    return true;
                                }
                            });
                        }
                    });

                    if(remain > 0) {
                        final int prevSize = oldBattles.size();
                        int index = 0;
                        for (int bid = 0, n = prevSize - remain; bid < n; bid++) {
                            final xbean.HuiWuBattle battle = xbean.Pod.newHuiWuBattle();
                            battle.setIndex(index++);
                            battle.setRoleid1(getWinner(oldBattles.get(bid * 2)));
                            battle.setRoleid2(getWinner(oldBattles.get(bid * 2 + 1)));
                            battle.setState(BattleState.NULL);
                            xdb.Trace.info("huiwu.round:{} battleid:{} battlepair:{}", newRoundIndex, bid, battle);
                            newBattles.add(battle);
                        }
                        //for (int bid = Math.max(0, 2 * remain - prevSize); bid < prevSize; bid++) {
                        for (int bid = Math.max(0, 2 * (prevSize - remain)); bid < prevSize; bid++) {
                            final xbean.HuiWuBattle battle = xbean.Pod.newHuiWuBattle();
                            battle.setIndex(index++);
                            battle.setRoleid1(getWinner(oldBattles.get(bid)));
                            battle.setRoleid2(0);
                            battle.setState(BattleState.WIN);
                            xdb.Trace.info("huiwu.round:{} battleid:{} default win pair:{}", newRoundIndex, bid, battle);
                            newBattles.add(battle);
                        }
                        xdb.Trace.info("Huiwu.onRoundRestBegin roundindex:{} battles:{}", newRoundIndex, newBattles);
                    } else if(!oldBattles.isEmpty()){
                        if(professionTerm.getChampion() == 0) {
                            final long champion = getWinner(oldBattles.get(0));
                            xdb.Transaction.texecuteWhileCommit(new xdb.Procedure() {
                                @Override
                                protected boolean process() {
                                    professionTerm.setChampion(champion);
                                    final map.msg.Bonus bonus = new map.msg.Bonus();
                                    FBonus.genBonus(champion, common.Utils.getLast(hcfg.battleaward).award, Bonus.BindType.BIND, bonus);
                                    FMail.addMail(champion, cfg.huiwu.HuiWu.HUIWU_CHAMPION_MAILID, bonus);
                                    return true;
                                }
                            });
                        } else {
                            Trace.error("Huiwu.onRoundRestBegin roundindex:{} has set champion.", oldRoundIndex);
                        }
                    } else {
                        Trace.error("Huiwu.onRoundRestBegin roundindex:{} previouBattle is empty. not champion", oldRoundIndex);
                    }
                }

                return true;
            }
        }.call();
    }

    public boolean setBattleResult(int profession, int roundindex, int battleid, boolean win) {
        xdb.Trace.info("huiwu.setBattleResult profession:{} battleid:{} win:{}",
                profession, battleid, win);
        final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
        if (info.getStage() != Stage.BEGIN_BATTLE || info.getRoundstage() != RoundStage.ROUND_FIGHT) {
            xdb.Trace.error("huiwu.setBattleResult curstate:{} != BEGIN_BATTLE or roundstage:{} != ROUND_FIGHT",
                    info.getStage(), info.getRoundstage());
            return false;
        }
        final xbean.HuiWuBattle battle = info.getTerminfobyprofession().get(profession).getRounds().get(roundindex)
                .getBattles().get(battleid);
        if (battle.getState() != BattleState.NULL) {
            xdb.Trace.error("huiwu.setBattleResult state:{} != NULL", battle.getState());
            return false;
        }
        final long roleid1 = battle.getRoleid1();
        final long roleid2 = battle.getRoleid2();
        final String rolename1 = Roleinfos.selectName(roleid1);
        final String rolename2 = Roleinfos.selectName(roleid2);
        battle.setState(win ? BattleState.WIN : BattleState.LOSE);
        FAchievement.addCounter(win ? roleid1 : roleid2, CounterType.PVP_HUIWU_WIN, 1);
        xdb.Transaction.tsendWhileCommit(roleid1, new SBattleResult(rolename2, win ? 1 : 0));
        xdb.Transaction.tsendWhileCommit(roleid2, new SBattleResult(rolename1, win ? 0 : 1));
        return true;
    }

    private void onBattleEnd() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                Trace.info("Huiwu.onBattleEnd begin");
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                if(info.getStage() != Stage.BEGIN_BATTLE) return false;
                info.setStage(Stage.END_BATTLE);
                info.setRoundstage(RoundStage.NULL);

                final xbean.HuiWuPriviousTerm priviousTerm = xbean.Pod.newHuiWuPriviousTerm();
                final int termid = info.getTermid();
                priviousTerm.setTermid(termid);
                priviousTerm.setOpentime(info.getOpentime());
                priviousTerm.setEndtime(System.currentTimeMillis());

                final Map<Long, Integer> oldContinueWinCounts = info.getContinuouschampions();
                final HashMap<Long, Integer> newContinueWinCounts = new HashMap<>();
                final Map<Integer, xbean.HuiWuChampion> championRecords = priviousTerm.getChampions();
                info.getTerminfobyprofession().entrySet().forEach(e -> {
                    final xbean.HuiWuProfessionTerm professionTerm = e.getValue();
                    final long champion = professionTerm.getChampion();
                    if(champion == 0)
                        return;
                    final xbean.HuiWuChampion newChapion = xbean.Pod.newHuiWuChampion();
                    newChapion.setRoleid(champion);
                    newChapion.setWorshipnum(0);
                    championRecords.put(e.getKey(), newChapion);
                    final int newContinueWinCount = oldContinueWinCounts.getOrDefault(champion, 0) + 1;
                    if(newContinueWinCount >= hcfg.continuitytimes) {
                        xdb.Transaction.texecuteWhileCommit(new xdb.Procedure() {
                            @Override
                            protected boolean process() {
                                final map.msg.Bonus bonus = new map.msg.Bonus();
                                FBonus.genBonus(champion, hcfg.continuityaward, 1, Bonus.BindType.BIND, bonus);
                                FMail.addMail(champion, cfg.huiwu.HuiWu.HUIWU_CONTINUE_CHAMPION_MAILID, bonus);
                                return true;
                            }
                        });
                    } else {
                        newContinueWinCounts.put(champion, newContinueWinCount);
                    }
                    xdb.Transaction.texecuteWhileCommit(() -> {
                        ActivityModule.huiwuChampinionNotifys.put(e.getKey(), new ActivityModule.HuiWuChapinionNotifyRecord(champion));
                    });
                });
                oldContinueWinCounts.clear();
                oldContinueWinCounts.putAll(newContinueWinCounts);
                xtable.Huiwupriviousterms.insert(termid, priviousTerm);

                broadcast(new STermStageChange(info.getTermid(), info.getStage()));
                Trace.info("Huiwu.onBattleEnd end");
                return true;
            }
        }.call();
    }


    @Override
    protected void onClose() {

    }

    public void setStage(int stage) {
        switch (stage) {
            case Stage.BEFORE_ENROLL: {
                onOpen();
                break;
            }
            case Stage.BEGIN_ENROLL: {
                onEnrollOpen();
                break;
            }
            case Stage.END_ENROLL: {
                onEnrollEnd();
                break;
            }
            case Stage.BEGIN_PRESELECT1: {
                onPreSelectionOpen1();
                break;
            }
            case Stage.END_PRESELECT1: {
                onPreSelectionEnd1();
                break;
            }
            case Stage.BEGIN_PRESELECT2: {
                onPreSelectionOpen2();
                break;
            }
            case Stage.END_PRESELECT2: {
                onPreSelectionEnd2();
                break;
            }
            case Stage.BEGIN_BATTLE: {
                onBattleBegin();
                break;
            }
            case Stage.END_BATTLE: {
                onBattleEnd();
                break;
            }
        }
    }

    public void setRoundStage(int stage) {
        switch (stage) {
            case RoundStage.ROUND_PREPARE: {
                onRoundBegin();
                break;
            }
            case RoundStage.ROUND_FIGHT: {
                onRoundFightBegin();
                break;
            }
            case RoundStage.ROUND_REST: {
                onRoundRestBegin();
                break;
            }
        }
    }
}
