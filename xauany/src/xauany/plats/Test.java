package xauany.plats;

import java.util.Map;

import xauany.AbstractPlatProcess;

/**
 * 开发测试平台
 */
public class Test extends AbstractPlatProcess<xbean.TestUserInfo>	{

	@Override
	protected void doLogin(gnet.AuAnyLoginArg arg, gnet.AuAnyLoginRes res) throws Exception {
		
		res.errcode = gnet.AuAnyLoginRes.ERR_SUCCEED;
	}

	private static class PGetUserID extends xauany.PGetUserID<xbean.TestUserInfo> {
		public PGetUserID(int plattype, String useridentity) {
			super(plattype, useridentity);
		}

		@Override
		protected xbean.TestUserInfo xdbGetUserInfo() {
			return xtable.Testusers.get(useridentity);
		}

		@Override
		protected long getUserID(xbean.TestUserInfo userInfo) {
			return userInfo.getUserid();
		}

		@Override
		protected xbean.TestUserInfo xdbNewUserInfo(long userid, long userinfoid) {
			xbean.TestUserInfo uinfo = xbean.Pod.newTestUserInfo();
			uinfo.setUserid(userid);
			uinfo.setUserinfoid(userinfoid);
			
			xtable.Testusers.insert(useridentity, uinfo);
			
			return uinfo;
		}
		
	}
	
	@Override
	protected xauany.PGetUserID<xbean.TestUserInfo> newPGetUserID(int plattype, String userIdentity) {
		return new PGetUserID(plattype, userIdentity);
	}

	@Override
	protected Long selectUserid(String userIdentity) {
		return xtable.Testusers.selectUserid(userIdentity);
	}

	@Override
	protected byte[] doOrderCallback(Map<String, String> params) throws Exception {
		// 开发测试平台暂不支持支付回调
		return null;
	}

}
