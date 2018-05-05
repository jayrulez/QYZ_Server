package xauany;

import java.util.Map;

/**
 * 平台处理接口
 */
public interface PlatProcess {
	
	/**
	 * 用xml配置初始化平台基本信息
	 * @param ele
	 */
	void init(org.w3c.dom.Element ele);
	
	int getType();
	
	/**
	 * 平台的名称
	 * @return
	 */
	String getName();
	
	/**
	 * 支付回调的路径
	 * 支付回调的URL：http://ip:port/getOrderCallbackPath()
	 * @return
	 */
	String getOrderCallbackPath();
	
	/**
	 * 用户登录
	 * @param arg
	 * @param res
	 */
	void login(gnet.AuAnyLoginArg arg, gnet.AuAnyLoginRes res);
	
	/**
	 * 平台支付完成后的回调接口
	 * @param params 参数
	 * @return
	 */
	byte[] orderCallBack(Map<String, String> params);
	
}
