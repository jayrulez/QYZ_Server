package xauany.plats;

import java.util.HashMap;
import java.util.Map;

import gnet.AuAnyLoginArg;
import gnet.AuAnyLoginRes;

import org.json.JSONObject;
import org.w3c.dom.Element;

import xauany.AbstractPlatProcess;
import xauany.Logger;
import xauany.PInsertUncompletedOrder;
import xauany.PlatManager;
import xauany.utils.HttpUtils;
import xauany.utils.Utils;

import com.goldhuman.Common.Octets;

/**
 * 老虎onesdk
 */
public class Onesdk extends AbstractPlatProcess<xbean.OnesdkUserInfo> {
	private String appsecret;
	private String checkTokenUrl = "http://api.dev.laohu.com/user/sdkCheckToken";
	
	@Override
	protected void subInit(Element ele){
		this.appsecret = getAttr(ele, "appsecret");
		this.checkTokenUrl = getAttr(ele, "checkTokenUrl");
	}

	@Override
	protected void doLogin(AuAnyLoginArg arg, AuAnyLoginRes res) throws Exception{
		final String userIdentity = new String(arg.user_identity.getBytes(), getCharset());
		final String token = new String(arg.token.getBytes(), getCharset());

		String sign = Utils.digestMd5(userIdentity + token + getAppid() + appsecret);
		
		Map<String, String> params = new HashMap<>();
		params.put("userIdentity", userIdentity);
		params.put("token", token);
		params.put("appId", getAppid());
		params.put("sign", sign);
		
		StringBuilder checkurlSb = new StringBuilder();
		checkurlSb.append(checkTokenUrl).append("?");
		for(Map.Entry<String, String> entry : params.entrySet()){
			checkurlSb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		checkurlSb.deleteCharAt(checkurlSb.length() - 1);
		
		String chekurl = checkurlSb.toString();
		if(xdb.Trace.isDebugEnabled())
			xdb.Trace.debug("checkTokenUrl = " + chekurl);
		String result = HttpUtils.getRequest1(chekurl, getHttpConfig());
		JSONObject json = new JSONObject(result);
		Object code = json.get("code");
		
		res.errcode = gnet.AuAnyLoginRes.ERR_INVALID;
		if("0".equals(String.valueOf(code))){
			res.errcode = gnet.AuAnyLoginRes.ERR_SUCCEED;
		}
	}
	
	private static class PGetUserID extends xauany.PGetUserID<xbean.OnesdkUserInfo> {
		public PGetUserID(int plattype, String useridentity) {
			super(plattype, useridentity);
		}

		@Override
		protected xbean.OnesdkUserInfo xdbGetUserInfo() {
			return xtable.Onesdkusers.get(useridentity);
		}

		@Override
		protected long getUserID(xbean.OnesdkUserInfo userInfo) {
			return userInfo.getUserid();
		}

		@Override
		protected xbean.OnesdkUserInfo xdbNewUserInfo(long userid, long userinfoid) {
			xbean.OnesdkUserInfo uinfo = xbean.Pod.newOnesdkUserInfo();
			uinfo.setUserid(userid);
			uinfo.setUserinfoid(userinfoid);
			
			xtable.Onesdkusers.insert(useridentity, uinfo);
			
			return uinfo;
		}
		
	}

	@Override
	protected xauany.PGetUserID<xbean.OnesdkUserInfo> newPGetUserID(int plattype, String userIdentity) {
		return new PGetUserID(plattype, userIdentity);
	}
	
	@Override
	protected Long selectUserid(String userIdentity){
		return xtable.Onesdkusers.selectUserid(userIdentity);
	}
	
	@Override
	protected byte[] doOrderCallback(Map<String, String> params) throws Exception{
		JSONObject succJson = new JSONObject();
		succJson.put("code", 0);
		
		//充值失败  不存入数据库
		final String payStatus = params.get("payStatus");
		if(!"1".equals(payStatus)){
			//xdb.Trace.debug("Onesdk.httpCallBack checkVarsSign payStatus =" + payStatus);
			Logger.trace("Onesdk.httpCallBack checkVarsSign payStatus =" + payStatus);
			return null;
		}
		
		String serverId = params.get("serverId");
		
		String[] orderedParamKeys = new String[]{"amount", "appId", "appOrder", "ext", "generalOrder", "payStatus", "serverId", "t", "userIdentity", "securityKey"};
		//String[] mayEmptyKeys = new String[]{"serverId", "ext"};
		
		StringBuilder original = new StringBuilder();
		for(String paramKey : orderedParamKeys){
			String value = params.get(paramKey);
			if(value != null){
				original.append(value);
			}
		}
		original.append(this.appsecret);
		
		String sign = Utils.digestMd5(original.toString());
		if(!sign.equalsIgnoreCase(params.get("sign"))){
			/*if( xdb.Trace.isDebugEnabled())
				xdb.Trace.debug( "Onesdk.httpCallBack checkVarsSign error");*/
			Logger.trace("Onesdk.httpCallBack checkVarsSign error");
			return null;
		}
		
		String userIdentity = params.get("userIdentity");
		String generalOrder = params.get("generalOrder");
		String appOrder = params.get("appOrder");
		
		Long userId = selectUserid(userIdentity);
		if (userId == null) {
			Logger.trace("Onesdk.httpCallBack selectUserid null");
			return null;
		}

		final Octets octetsvars = PlatManager.marshalVars(params);
		
		PInsertOrder pInsertOrder = new PInsertOrder(generalOrder, appOrder, userIdentity, octetsvars, params);
		int serverid = Integer.decode(serverId);
		PInsertUncompletedOrder pInsertUncompletedOrder = new PInsertUncompletedOrder(appOrder, serverid, getType(), generalOrder, userId, octetsvars.getBytes());
		
		boolean result = insertOrderAndNotifyGs(pInsertOrder, pInsertUncompletedOrder);
		if(result){
			Logger.trace("Onesdk.httpCallBack insertOrderAndNotifyGs SUCCESS");
			return succJson.toString().getBytes();
		}
		Logger.trace("Onesdk.httpCallBack insertOrderAndNotifyGs ERROR");
		return null;
	}
	
	private static class PInsertOrder extends xdb.Procedure {
		private final String platOrderid;
		private final String gsOrderid;
		private final String userIdentity;
		private final Octets vars;
		private final Map<String, String> varsmap;
		
		public PInsertOrder(String platOrderid, String gsOrderid, String userIdentity, Octets vars, Map<String, String> varsmap) {
			this.platOrderid = platOrderid;
			this.gsOrderid = gsOrderid;
			this.userIdentity = userIdentity;
			this.vars = vars;
			this.varsmap = varsmap;
		}

		@Override
		protected boolean process() throws Exception {
			if(null != xtable.Onesdkorderinfos.get(platOrderid)){
				xtable.Onesdkorderinfos.remove(platOrderid);
			}
			
			xbean.OnesdkOrderInfo info = xbean.Pod.newOnesdkOrderInfo();
			info.setCreatetime(System.currentTimeMillis());
			info.setGsorderid(gsOrderid);
			info.setUseridentity(userIdentity);
			info.setVarsCopy(vars.getBytes());
			
			xtable.Onesdkorderinfos.insert(platOrderid, info);
			
			if(xdb.Trace.isInfoEnabled()){
				xdb.Trace.info("Onesdk.PInsertOrder: platOrderid=" + platOrderid + ", gsOrderid=" + info.getGsorderid() + ", vars=" + varsmap);
			}
			
			String oldPlatOrderid = xtable.Onesdkordergs2plat.get(info.getGsorderid());
			if(null != oldPlatOrderid) {
				if (oldPlatOrderid.equals(platOrderid)) {
					return true;// 重复不再插入
				}

				xtable.Onesdkordergs2plat.remove(info.getGsorderid());
				// 打日志，万一出现这种情况
				xdb.Trace.error("Onesdk.PInsertOrder: same gsOrderid= " + info.getGsorderid() + " diff platOrderid =" + "old:" + oldPlatOrderid + ",new:" + platOrderid);
			}
			xtable.Onesdkordergs2plat.insert(info.getGsorderid(), platOrderid);
			return true;
		}
	}

}
