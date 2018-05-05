package lx.gs.family;

import gs.AProcedure;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.family.msg.CUpLevelMall;
import lx.gs.family.msg.SUpLevelMall;
import lx.gs.family.msg.SUpLevelMallNotify;
import lx.gs.logger.By;

public class PUpLevelMall extends AProcedure<CUpLevelMall> {

	public PUpLevelMall(CUpLevelMall p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SUpLevelMall result = new SUpLevelMall();
		
		
		xbean.Family family = FFamily.getFamilyByRoleId(roleid);

		if(!FFamily.isFamilyLeader(roleid, family)){
			return error(ErrorCode.ONLY_CHIEF_VICECHIEF_CAN_ACTION);
		}
		
		if(family.getMalllevel()>= family.getFlevel()){
			return error(ErrorCode.FAMILY_LEVEL_TOO_LOW);
		}
		//cost
		cfg.family.FamilyShop conf = cfg.CfgMgr.familyshop.get(family.getMalllevel());		
		ErrorCode err = FCondition.checkByReflection(roleid, conf, By.Family_UpMallLevel);
		if(!err.ok()) return error(err);
		
		family.setMalllevel(family.getMalllevel()+1);
		
		//日志
		FFamily.addFamilyLog(family, roleid, lx.gs.family.msg.FamilyLogReport.TYPE_UPLEVEL_MALL, family.getMalllevel());
		
		//广播
		SUpLevelMallNotify notify = new SUpLevelMallNotify();
		notify.family = FFamily.makeProtocolFamilyBasicInfo(family);
		FFamily.multicastAllFamily(family, notify);
		
		response(result);
		return true;
	}

}
