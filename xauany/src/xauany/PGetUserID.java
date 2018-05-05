package xauany;

/**
 * 从Xdb中通过平台账号获取userid的抽象类
 * 由子类实现具体的xdb相关操作
 * @param <T>
 */
public abstract class PGetUserID<T> extends xdb.Procedure{
	protected final int plattype;
	protected final String useridentity;
	
	private long userid;
	
	public PGetUserID(int plattype, String useridentity) {
		super();
		this.plattype = plattype;
		this.useridentity = useridentity;
	}

	@Override
	protected boolean process() throws Exception {
		T userInfo = xdbGetUserInfo();
		if(userInfo == null){
			final PNewUser p = new PNewUser(plattype, useridentity);
			if(!p.call()){
				return false;
			}

			userInfo = xdbNewUserInfo(p.getUserID(), p.getUserInfoID());
		}
		
		userid = getUserID(userInfo);
		
		if(xdb.Trace.isDebugEnabled()){
			xdb.Trace.debug(getClass().getName() + ".PGetUserID useridentity = " + useridentity + " userid = " + userid);
		}
		
		return true;
	}
	
	public long get(){
		return userid;
	}
	
	/**
	 * 从xdb中获取用户信息
	 * @return
	 */
	protected abstract T xdbGetUserInfo();
	
	/**
	 * 获取userId
	 * @param userInfo
	 * @return
	 */
	protected abstract long getUserID(T userInfo);
	
	/**
	 * 新建一个用户信息，并且存到xdb中，同时返回
	 * @param userid
	 * @return
	 */
	protected abstract T xdbNewUserInfo(long userid, long userinfoid);
	
}
