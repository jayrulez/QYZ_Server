package lx.gs.family;

import cfg.active.EventNum;
import gs.AProcedure;
import lx.gs.cmd.FAction;
import lx.gs.cmd.FCmd.Context;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.family.msg.CRaiseGodAnimal;
import lx.gs.family.msg.SRaiseGodAnimal;
import lx.gs.logger.By;

public class PRaiseGodAnimal extends AProcedure<CRaiseGodAnimal> {

	public PRaiseGodAnimal(CRaiseGodAnimal p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SRaiseGodAnimal result = new SRaiseGodAnimal();
		result.raisetype = param.raisetype;

		xbean.Family family = FFamily.getFamilyByRoleId(roleid);
		xbean.FamilyGodAnimal animal = family.getActivity().getGodanimalinfo().get(param.animalid);

		//配置的获取要根据元宝还是金币喂养
		cfg.family.BossFeed conf = cfg.CfgMgr.bossfeed.get(param.raisetype);
		if(conf == null) return error(ErrorCode.PARAM_ERROR);
		
		//检查次数限制以及花费
		ErrorCode err = FCondition.checkByReflection(roleid, conf, 1, By.Family_RaiseAnimal, cfg.cmd.ConfigId.FAMILY_FEED, conf.feedid);
		if(!err.ok()) return error(err);
		
		//家族建设度以及个人帮贡都是增加的，通过action实现
		Context ctx = FAction.processByReflection(roleid, conf, By.Family_RaiseAnimal); 
		family.setTotalbanggong(family.getTotalbanggong() + conf.familycontribution.amount);
		if(ctx.errcode.err()) {
			return error(ctx.errcode);
		}
		
		animal.setExp(animal.getExp() + conf.exp); //经验值
		
		result.animal = FFamily.makeProtocolFamilyAnimal(animal);
        FDailyActivity.addActiveScores(roleid, EventNum.FEEDBOSS);
		response(result);
		return true;
	}

}
