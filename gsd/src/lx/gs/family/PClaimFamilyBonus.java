package lx.gs.family;

import xdb.Lockeys;
import cfg.cmd.ConfigId;
import gs.AProcedure;
import lx.gs.cmd.FAction;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.family.msg.CClaimFamilyBonus;
import lx.gs.family.msg.SClaimFamilyBonus;
import lx.gs.logger.By;

public class PClaimFamilyBonus extends AProcedure<CClaimFamilyBonus> {

	public PClaimFamilyBonus(CClaimFamilyBonus p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		xbean.Family family = FFamily.getFamilyByRoleId(roleid);
		if(family == null) return error(ErrorCode.NOT_IN_FAMILY);

		//查看奖励的配置和家族目前的等级是否匹配
		cfg.bonus.FamilyBonus bonusconf = cfg.CfgMgr.familybonus.get(param.claimlevel);
		if(bonusconf == null){
			return error(ErrorCode.PARAM_ERROR);
		}
		//检查，并领取奖励
		final ErrorCode ret = FCondition.checkByReflection(roleid, bonusconf, 1, By.Family_Level_Bonus, ConfigId.FAMILY_LEVEL_BONUS, param.claimlevel);
		if(ret.err()){
			return error(ret);
		}
		FAction.processByReflection(roleid, bonusconf, By.Family_Level_Bonus);

		response(new SClaimFamilyBonus(param.claimlevel));
		return true;
	}

}
