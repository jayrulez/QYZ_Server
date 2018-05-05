package lx.gs.activity.huiwu;

import cfg.CfgMgr;
import cfg.Const;
import cfg.huiwu.RoundStage;
import cfg.huiwu.Stage;
import lx.gs.activity.huiwu.msg.BattleRole;
import lx.gs.activity.huiwu.msg.SAttendNextBattle;
import lx.gs.activity.huiwu.msg.STermInfo;
import lx.gs.cmd.FCondition;
import lx.gs.error.FError;
import lx.gs.logger.By;
import xtable.Roleinfos;

/**
 * Created by huangqiang on 2016/3/22.
 */
public class FHuiWu {

    public final static int HUIWU_CUR_TERM_KEY = 0;
    public static xbean.HuiWuCurTerm getCurTermInfo() {
        return xtable.Huiwucurterms.get(HUIWU_CUR_TERM_KEY);
    }

    public static void setNewTermInfo(xbean.HuiWuCurTerm info) {
        xtable.Huiwucurterms.getTable().put(HUIWU_CUR_TERM_KEY, info);
    }

    public static void checkAttend(long roleid) {
        FError.check(FCondition.checkA(roleid, CfgMgr.huiwu.requirelevel, 1, By.HuiWu_Enroll, Const.NULL, Const.NULL));
    }

    public static BattleRole createBattleRole(long roleid) {
        final BattleRole role = new BattleRole();
        if(roleid > 0) {
            role.name = Roleinfos.selectName(roleid);
        }
        return role;
    }

    public static void sendSTermInfo(long roleid) {
        final STermInfo re = new STermInfo();
        final xbean.HuiWuCurTerm termInfo = getCurTermInfo();
        final int termid = termInfo.getTermid();
        final int stage = termInfo.getStage();
        final int roundIndex = termInfo.getRoundindex();
        final int roundStage = termInfo.getRoundstage();
        re.termid = termid;
        re.stage = stage;
        re.roundindex = roundIndex;
        re.roundstage = roundStage;
        re.opentime = termInfo.getOpentime();
        re.guessendtime = termInfo.getGuessendtime();
        re.guessroleid = termInfo.getGuess().getOrDefault(roleid, 0L);
        xdb.Transaction.tsendWhileCommit(roleid, re);
        if(termid > 0 && stage >= Stage.BEGIN_ENROLL) {
            final xbean.HuiWuProfessionTerm professionTerm = termInfo.getTerminfobyprofession().get(Roleinfos.selectProfession(roleid));
            re.hasenroll = professionTerm.getEnrollroleids().contains(roleid) ? 1 : 0;
            if(stage == Stage.BEGIN_BATTLE && roundStage == RoundStage.ROUND_PREPARE) {
                for(xbean.HuiWuBattle battle : professionTerm.getRounds().get(roundIndex).getBattles()) {
                    final long roleid1 = battle.getRoleid1();
                    final long roleid2 = battle.getRoleid2();
                    if(roleid1 == roleid || roleid2 == roleid) {
                        final SAttendNextBattle msg = new SAttendNextBattle();
                        msg.round = roundIndex;
                        msg.battlebegintime = termInfo.getBattlebegintime();
                        final long opponentid = roleid == roleid1 ? roleid2 : roleid1;
                        if(opponentid != 0) {
                            msg.opponent = xtable.Roleinfos.selectName(opponentid);
                            msg.opponentcombatpower = xtable.Roleattrs.selectTotalcombatpower(opponentid);
                        }
                        xdb.Transaction.tsendWhileCommit(roleid, msg);
                    }
                }
            }
        }
    }
}
