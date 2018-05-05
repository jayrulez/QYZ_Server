package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;

public final class DeliverClient extends xio.ManagerByCreator{
	private static DeliverClient instance;
	
	
	public DeliverClient() {
		DeliverClient.instance = this;
	}
	
	public static DeliverClient getInstance() {
		return instance;
	}

	public boolean sendByGsToAuany(long userid, long roleid, xio.Protocol msg) {
        final GsToAuany re = new GsToAuany();
        re.userid = userid;
        re.roleid = roleid;
        re.ptype = msg.getType();
        re.pdata = new OctetsStream().marshal(msg);
        return re.send(this);
    }
}
