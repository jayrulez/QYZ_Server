package xauany;

public class PNewUser extends xdb.Procedure {
	private static long BASE_USER_ID = 4096;
	private final int plattype;
	private final String useridentity;
	private long 	userid;
	private long 	userinfoid;
	
	public PNewUser(int plattype, String useridentity) {
		super();
		this.plattype = plattype;
		this.useridentity = useridentity;
	}

	@Override
	protected boolean process() throws Exception {
		final xbean.UserInfo userinfo = xbean.Pod.newUserInfo();
		userinfo.setPlattype(plattype);
		userinfo.setUseridentity(useridentity);
		
		userinfoid = xtable.Users.insert(userinfo) ;
		userid = userinfoid / BASE_USER_ID;

		xdb.Trace.info("PNewUser plattype = " + plattype + " useridentity = " + useridentity + " userinfoid = " + userinfoid + " userid = " + userid);
		return true;
	}
	
	public long getUserID() {
		return userid;
	}
	
	public long getUserInfoID() {
		return userinfoid;
	}
	
	public static long toXdbUserID( long userid) {
		return userid * PNewUser.BASE_USER_ID + Config.getInstance().getServerid();
	}
}
