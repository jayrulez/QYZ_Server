package lx.gs.family;

import cfg.active.EventNum;
import gs.AProcedure;
import lx.gs.cmd.FAction;
import lx.gs.cmd.FCmd.Context;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.family.msg.CFamilyPray;
import lx.gs.family.msg.FamilyLogReport;
import lx.gs.family.msg.SFamilyPray;
import lx.gs.logger.By;

public class PFamilyPray extends AProcedure<CFamilyPray> {

	public PFamilyPray(CFamilyPray p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SFamilyPray result = new SFamilyPray();
		result.burntype = param.burntype;
		
		xbean.Family family = FFamily.getFamilyByRoleId(roleid);

		cfg.family.Pray conf = cfg.CfgMgr.pray.get(param.burntype);
		if(conf == null) return error(ErrorCode.PARAM_ERROR);
		
		ErrorCode err = FCondition.checkByReflection(roleid, conf, 1, By.Family_Pray, cfg.cmd.ConfigId.FAMILY_PRAY, conf.prayid);
		if(err.err()){
			return error(err);
		}

		Context ctx = FAction.processByReflection(roleid, conf, By.Family_Pray);
		if(ctx.errcode.err()) {
			return error(ctx.errcode);
		}
		family.setTotalbanggong(family.getTotalbanggong() + conf.familycontribution.amount);
        FFamily.addFamilyLog(family, roleid, FamilyLogReport.TYPE_PRAY, conf.familycapital.buildv);
        FDailyActivity.addActiveScores(roleid, EventNum.PRAY);
        response(result);
		return true;
	}

}
