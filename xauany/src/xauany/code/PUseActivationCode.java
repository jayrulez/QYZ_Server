package xauany.code;

import gnet.NotifyUseActivationCode;
import xauany.PNewUser;
import xauany.code.PVerifyLoginActivationCode.VerifyResult;
import xdb.Trace;

public class PUseActivationCode extends xdb.Procedure{
	private gnet.UseActivationCode param;
	
	public PUseActivationCode(gnet.UseActivationCode param) {
		super();
		this.param = param;
	}
	
	@Override
	protected boolean process(){
        Trace.info("UseActivationCode. userid:{} roleid:{} code:{}", param.userid, param.roleid, param.code);
        long xdbuserid = PNewUser.toXdbUserID(param.userid);
		VerifyResult verifyRes = PVerifyLoginActivationCode.commonVerify(xdbuserid, param.code, false);
		
		NotifyUseActivationCode res = new NotifyUseActivationCode();
		res.userid = param.userid;
		res.roleid = param.roleid;
		
		res.err.code = verifyRes.err.code;
		if(verifyRes.err.code != gnet.ActivationCodeErr.ERR_SUCCESS){
			res.send(param.getConnection());
			return false;
		}
		
		//共享的激活码不记录使用状态
		if(verifyRes.activationCodeSet.getIsshared() != 1){
			verifyRes.activationCode.setStatus(xbean.ActivationCode.STATE_ALLOCATE);
			verifyRes.activationCode.setUsetime(System.currentTimeMillis());
		}
		res.code = param.code;
		res.codetype = verifyRes.activationCodeSet.getType();
		
		res.send(param.getConnection());
		return true;
	}
	
	
}
