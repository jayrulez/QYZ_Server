package lx.gs.logger;

import gnet.link.Role;

public class POnLinkBroken extends xdb.Procedure{
	private gnet.link.Role role;
	private int reason;
	
	public POnLinkBroken(Role role, int reason) {
		super();
		this.role = role;
		this.reason = reason;
	}
	
	@Override
	protected boolean process(){
		long userid = role.getUserid();
//		FLogger.logout(userid, reason);
		
		xtable.Onlineusers.remove(userid);
		
		return true;
	}
	
}
