package lx.gs.family;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.msg.CGetFamilyLog;
import lx.gs.family.msg.SGetFamilyLog;
import xbean.FamilyLogReport;

import java.util.List;

public class PGetFamilyLog extends AProcedure<CGetFamilyLog> {

	public PGetFamilyLog(CGetFamilyLog p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SGetFamilyLog result = new SGetFamilyLog();
		result.familyid = param.familyid;
        final long familyid = xtable.Rolefamily.selectCurrentfid(roleid);
		List<FamilyLogReport> logs = xtable.Family.selectLogs(familyid);
		for(int i = logs.size() - 1; i >= 0; i--){
			result.logs.add(FFamily.makeProtocolFamilyLog(logs.get(i)));
		}
		response(result);
		return true;
	}

}
