package xauany;

import gnet.AuAnyLoginRes;

import org.w3c.dom.Element;

import xauany.code.FCode;
import xauany.utils.HttpConfig;

import java.util.Map;

/**
 * 抽象平台处理类，实现各个平台共性的代码
 *
 */
public abstract class AbstractPlatProcess<T> implements PlatProcess{
	private int type;
	private String name;
	private String orderCallbackPath;
	private String appid;
	private String appkey;
	private HttpConfig httpConfig = new HttpConfig();
	
	@Override
	public void init(Element ele) {
		this.type = Integer.decode(getAttr(ele, "type"));
		this.name = getAttr(ele, "name");
		this.orderCallbackPath = getAttr(ele, "orderCallbackPath");
		this.appid = getAttr(ele, "appid");
		this.appkey = getAttr(ele, "appkey");
		
		this.httpConfig.findConfig(ele);
		
		subInit(ele);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("class = ").append(getClass().getName());
		sb.append(", type = ").append(type);
		sb.append(", name = ").append(name);
		sb.append(", orderCallbackPath = ").append(orderCallbackPath);
		sb.append(", appid = ").append(appid);
		sb.append(", appkey = ").append(appkey);
		
		return sb.toString();
	}
	
	/**
	 * 从element中get属性，如果获取不到，则抛异常
	 * @param ele
	 * @param attrName
	 * @return
	 */
	protected String getAttr(Element ele, String attrName){
		String value = ele.getAttribute(attrName);
		if(value == null || value.isEmpty()){
			throw new IllegalArgumentException(getClass().getName() + "." + attrName + " is NULL");
		}
		
		return value;
	}
	
	/**
	 * 从element中get属性，可能会返回null
	 * @param ele
	 * @param name
	 * @return
	 */
	protected String tryGetAttr(Element ele, String name){
		return ele.getAttribute(name);
	}
	
	/**
	 * 该方法由子类覆盖，实现各个平台的差异化配置
	 * @param ele
	 */
	protected void subInit(Element ele){
		
	}
	
	@Override
	public int getType() {
		return type;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getOrderCallbackPath() {
		return orderCallbackPath;
	}
	
	protected String getAppid() {
		return appid;
	}

	protected String getAppkey() {
		return appkey;
	}

	protected HttpConfig getHttpConfig() {
		return httpConfig;
	}

	@Override
	public void login(gnet.AuAnyLoginArg arg, gnet.AuAnyLoginRes res) {
		if(xdb.Trace.isDebugEnabled()){
			xdb.Trace.debug(getClass().getName() + ".login" + arg);
		}
		
		try{
			doLogin(arg, res);
			
			//登陆成功,返回用户id等信息
			if(res.errcode == gnet.AuAnyLoginRes.ERR_SUCCEED){
				final String userIdentity = new String(arg.user_identity.getBytes(), getCharset());
				final PGetUserID<T> pGetUserID = newPGetUserID(getType(), userIdentity);
				if (pGetUserID.call()) {
					res.userid = pGetUserID.get();
					
					if (xdb.Trace.isDebugEnabled()){
						xdb.Trace.debug(getClass().getName() + ".onLoginSucceed userIdentity = " + userIdentity + " userid = " + pGetUserID.get());
					}
				} 
				else{
					res.errcode = gnet.AuAnyLoginRes.ERR_STORE;
				}
			}
		}
		catch(Exception e){
			xdb.Trace.error(getClass().getName() + ".login", e);
			res.errcode = AuAnyLoginRes.ERR_PLAT_EXCEPTION;
		}
		
		if(res.errcode == AuAnyLoginRes.ERR_SUCCEED){
			if(FCode.isUseActivationCodeLogin(FCode.getUserPlatformByUserid(res.userid))){
				long xdbuserid = PNewUser.toXdbUserID(res.userid);
				xbean.UserActivationCode useractivationcode = xtable.Useractivationcodes.select(xdbuserid);
				if(useractivationcode == null || !useractivationcode.getAll().containsKey(FCode.CODE_TYPE_LOGIN)){
					res.errcode = AuAnyLoginRes.ERR_NEED_ACTIVATE;
				}
			}
		}
		//AU 渠道监控
		if(res.errcode == AuAnyLoginRes.ERR_SUCCEED){
			Logger.trace("USER LOGIN SUCCESS,PLATFORM=" + getName());
		}
		else{
			Logger.trace("USER LOGIN ERROR,PLATFORM=" + getName() + ",ERROR=" + res.errcode);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	protected String getCharset(){
		return Config.OCTETS_CHARSET_UTF8;
	}
	
	/**
	 * 子类实现具体的登陆验证功能
	 * @param arg
	 * @param res
	 */
	protected abstract void doLogin(gnet.AuAnyLoginArg arg, gnet.AuAnyLoginRes res) throws Exception;
	
	/**
	 * 子类实现具体的获取用户Id的Procedure
	 * @param plattype
	 * @param userIdentity
	 * @return
	 */
	protected abstract PGetUserID<T> newPGetUserID(int plattype, String userIdentity);
	
	/**
	 * 从Xdb中select出userid
	 * @param userIdentity
	 * @return
	 */
	protected abstract Long selectUserid(String userIdentity);
	
	@Override
	public byte[] orderCallBack(Map<String, String> params) {
		try {
			return doOrderCallback(params);
		}
		catch(Exception e){
			xdb.Trace.error(getClass().getName() + ".orderCallBack", e);
			return null;
		}
	}
	
	protected abstract byte[] doOrderCallback(Map<String, String> params) throws Exception;
	
	protected boolean insertOrderAndNotifyGs(xdb.Procedure pInsertOrder, PInsertUncompletedOrder pInsertUncompletedOrder){
		boolean result = new xdb.Procedure(){
			
			@Override
			protected boolean process(){
				if(pInsertOrder.call()){
					return pInsertUncompletedOrder.call();
				}
				
				return false;
			}
			
		}.call();
		
		if(result){
			OrderNotifyMgr.INSTANCE.notifyOrderInfo2Gs(pInsertUncompletedOrder.getGsOrderid());
		}
		
		return result;
	}
	
}
