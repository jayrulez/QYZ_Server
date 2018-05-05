package lx.gs.pickcard;


import cfg.cmd.action.Bonus;
import cfg.lottery.HighLotteryDetail;
import common.ErrorCode;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.pickcard.msg.PickBonusInfo;
import lx.gs.pickcard.msg.SPickCardByType;
import lx.gs.pickcard.msg.SPickcardTimes;

import java.util.List;

public final class FPickCard {

    public static xbean.PickCardInfo get(long roleid){
        xbean.PickCardInfo result = xtable.Pickcardinfos.get(roleid);
        if(result == null){
            result = xbean.Pod.newPickCardInfo();
            xtable.Pickcardinfos.insert(roleid, result);
        }
        return result;
    }

    /**
     * 找到本次抽卡次数对应的具体奖励
     * @param conf
     * @param times
     * @return
     */
    public static Bonus findBonusDetail(cfg.lottery.HighLottery conf, int times){
//        xdb.Trace.info("current pick time = {}", times);
        List<HighLotteryDetail> lists = conf.lotterylist;
        int size = lists.size();
        int timesLimitIndex = 0;
        if(lists.get(size - 1).lotterycount <= times){//如果超过最大配置
            timesLimitIndex = size - 1;
        }else {
            for (int i = 0; i < size; i++) {
                if (times >= lists.get(i).lotterycount) {
                    continue;
                } else {
                    timesLimitIndex = i - 1;
                    break;
                }
            }
        }
        int bonusIndex = 0;
        for(int i = timesLimitIndex ; i >= 0 ; i--){//找到最能被整除最大的
            if(times % lists.get(i).requiremultiple == 0){
                bonusIndex = i;
                break;
            }
        }
//        xdb.Trace.info("bonus index = {}", bonusIndex);
        return lists.get(bonusIndex).bonuslist;
    }

    public static void addBonusPerPick(SPickCardByType response){
        PickBonusInfo bi = new PickBonusInfo();
        bi.issplit = 0;
        response.pickbonus.add(bi);
    }

    public static boolean isCanFreePick(long roleid, cfg.cmd.condition.Condition condition, int cfgid){//判断能否免费金币单抽
        ErrorCode ret = FCondition.checkA(roleid, condition, 1, By.Pick_Card, cfg.cmd.ConfigId.PICKCARD, cfgid);
        return !ret.err();
    }
	
    public static void sendPickCardTimes(long roleid, xbean.PickCardInfo info){
        SPickcardTimes response = new SPickcardTimes();
        response.huobanhigh = 10 - info.getHuobanhighyuanbao() % 10;
        response.huobanlow = 10 - info.getHuobanlowyuanbao() % 10;
        response.fabaohigh = 10 - info.getFabaoyuanbao() % 10;
        response.fabaolow = 10 - info.getFabaoxunibi() % 10;
        xdb.Transaction.tsendWhileCommit(roleid, response);
    }
}
