package xauany.code;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import gnet.ActivationCodeErr;
import xauany.Config;
import xauany.PNewUser;

public class PVerifyLoginActivationCode extends xdb.Procedure{
	private gnet.VerifyLoginActivationCodeArg arg;
	private gnet.VerifyLoginActivationCodeRes res;
	
	public PVerifyLoginActivationCode(gnet.VerifyLoginActivationCodeArg arg,
			gnet.VerifyLoginActivationCodeRes res) {
		super();
		this.arg = arg;
		this.res = res;
	}
	
	@Override
	protected boolean process() throws UnsupportedEncodingException{
		res.userid = arg.userid;
		long xdbuserid = PNewUser.toXdbUserID(res.userid);
		String codestr = new String(arg.code.getBytes(), Config.OCTETS_CHARSET_UTF8);
		
		VerifyResult verifyRes = commonVerify(xdbuserid, codestr, true);
		
		res.err.code = verifyRes.err.code;
		if(verifyRes.err.code != gnet.ActivationCodeErr.ERR_SUCCESS){
			return false;
		}
		
		//共享的激活码不记录使用状态
		if(verifyRes.activationCodeSet.getIsshared() != 1){
			verifyRes.activationCode.setStatus(xbean.ActivationCode.STATE_CONFIRM);
			verifyRes.activationCode.setUsetime(System.currentTimeMillis());
		}
		
		xbean.UserActivationCode useractivationcode = xtable.Useractivationcodes.get(xdbuserid);
		if(useractivationcode == null){
			useractivationcode = xbean.Pod.newUserActivationCode();
			xtable.Useractivationcodes.insert(xdbuserid, useractivationcode);
		}
		final Map<Integer, Long> usedCodes = useractivationcode.getAll();
		if(usedCodes.containsKey(FCode.CODE_TYPE_LOGIN)){
			res.err.code = gnet.ActivationCodeErr.ERR_HAS_ALEADY_ACTIVATED;
			return false;
		}
		else{
			usedCodes.put(FCode.CODE_TYPE_LOGIN, verifyRes.code);
		}

		res.err.code = 0;
		return true;
	}
	
	
	static class VerifyResult{
		gnet.ActivationCodeErr err = new gnet.ActivationCodeErr();
		long code;
		xbean.ActivationCode activationCode;
		xbean.ActivationCodeSet activationCodeSet;
	}
	
	static VerifyResult commonVerify(long xdbuserid, String codestr, boolean isLogin){
		VerifyResult res = new VerifyResult();
		if(!Config.getInstance().isActiveCodeOpen()){
			res.err.code = gnet.ActivationCodeErr.ERR_FUNCTION_IS_CLOSED;
			return res;
		}
		if(!CodeUtils.isValid(codestr)){
			res.err.code = gnet.ActivationCodeErr.ERR_FORMATE_INVALID;
			return res;
		}
		final long code;
        try {
            code = CodeUtils.decode(codestr);
        }  catch (Exception e) {
            res.err.code = ActivationCodeErr.ERR_FORMATE_INVALID;
            return res;
        }
		
		xbean.ActivationCode activationCode = xtable.Activationcodes.get(code);
		if(activationCode == null){
			res.err.code = gnet.ActivationCodeErr.ERR_INVALID;
			return res;
		}
		xbean.ActivationCodeSet activationCodeSet = xtable.Activationcodesets.get(activationCode.getType());
		if(isLogin){
			if(!activationCodeSet.getIslogin()){
				res.err.code = gnet.ActivationCodeErr.ERR_INVALID;
				return res;
			}
		}
		else{
			if(activationCodeSet.getIslogin()) {
				res.err.code = gnet.ActivationCodeErr.ERR_INVALID;
				return res;
			}
		}
		if(activationCode.getStatus() != xbean.ActivationCode.STATE_NEW){
			res.err.code = gnet.ActivationCodeErr.ERR_CODE_IS_USED;
			return res;
		}
		if(activationCodeSet.getExpiratetime() > 0 && System.currentTimeMillis() >= activationCodeSet.getExpiratetime()){
			res.err.code = gnet.ActivationCodeErr.ERR_CODE_IS_EXPIRATED;
			return res;
		}
		if(System.currentTimeMillis() < activationCodeSet.getOpentime()){
			res.err.code = gnet.ActivationCodeErr.ERR_CODE_IS_NOT_OPEN;
			return res;
		}
		//empty表示无平台限制
		if(!activationCodeSet.getPlatformset().isEmpty()){
			xbean.UserInfo userinfo = xtable.Users.get(xdbuserid);
			String useridentity = userinfo.getUseridentity();
			//Onesdk的useridentity规则为 平台ID_唯一ID
			String[] platform_identity_pair = useridentity.split("_");
			int platform = 0;
			if(platform_identity_pair.length == 2){
				try{
					platform = Integer.parseInt(platform_identity_pair[0]);
				}catch(Exception e){
					xdb.Trace.error(e);
				}
			}
			if(platform == 0 || !activationCodeSet.getPlatformset().contains(platform)){
				res.err.code = gnet.ActivationCodeErr.ERR_PLATFORM_NOT_MATCH;
				return res;
			}
		}
		
		res.err.code = gnet.ActivationCodeErr.ERR_SUCCESS;
		res.code = code;
		res.activationCode = activationCode;
		res.activationCodeSet = activationCodeSet;
		return res;
	}
	
	
}
