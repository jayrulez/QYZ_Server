package lx.gs.pickcard;

import cfg.CfgMgr;
import cfg.item.EItemBindType;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.pickcard.msg.CScoreExchange;
import lx.gs.pickcard.msg.SScoreExchange;

public class PScoreExchange extends AProcedure<CScoreExchange> {
	public PScoreExchange(CScoreExchange param){
		super(param);
	}

	@Override
	protected boolean doProcess() throws Exception {
		cfg.lottery.Exchange conf = CfgMgr.gradeexchange.get(param.exchangetyep).exchangelist.get(param.id);
		int cfgId = 0;
		if(param.exchangetyep == cfg.lottery.PickType.FA_BAO){
			cfgId = cfg.cmd.ConfigId.EXCHANGE_FABAO;
		}else if (param.exchangetyep == cfg.lottery.PickType.HUO_BAN) {
			cfgId= cfg.cmd.ConfigId.EXCHANGE_HUOBAN;
		}else {
			return error(ErrorCode.PARAM_ERROR);
		}
		final ErrorCode err = FCondition.checkByReflection(roleid, conf, 1, By.Pick_Card, cfgId, param.id);
		if(err.err())
			return error(err);
	
		final map.msg.Bonus bonus = new map.msg.Bonus();
		if(!FBonus.genAndAddBonus(roleid, conf.item, common.Bonus.BindType.BIND, bonus, By.Pick_Card)){
            return error(ErrorCode.BAG_FULL);
        }
        FLogger.exchange(roleid, FBonus.getRoleInfo(roleid), param.exchangetyep, param.id);
		SScoreExchange response = new SScoreExchange();
		response.exchangetyep = param.exchangetyep;
		response.id = param.id;
		response(response);
		return true;
	}
	
}
