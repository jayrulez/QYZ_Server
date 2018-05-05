package lx.gs.logger;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;
import gnet.link.AnnounceUserOnline;
import xdb.Trace;

import java.nio.charset.Charset;

public class PAnnounceUserOnline extends xdb.Procedure{
	private AnnounceUserOnline param;
	
	public PAnnounceUserOnline(AnnounceUserOnline param) {
		super();
		this.param = param;
	}


	@Override
	protected boolean process() throws MarshalException {
		xbean.OnlineUser onlineuser = xtable.Onlineusers.get(param.userid);
		if(onlineuser == null){
			onlineuser = xbean.Pod.newOnlineUser();
			xtable.Onlineusers.add(param.userid, onlineuser);
		}


		onlineuser.setUsername(new String(param.username.getBytes(), Charset.forName("UTF-8")));
		onlineuser.setPlattype(param.plattype.plat);
		onlineuser.setDeviceid(new String(param.deviceid.getBytes(), Charset.forName("UTF-8")));
		onlineuser.setOs(new String(param.os.getBytes(), Charset.forName("UTF-8")));
		onlineuser.setPeer(new String(param.peer.getBytes(), Charset.forName("UTF-8")));
		onlineuser.setPlatform(new String(param.platform.getBytes(), Charset.forName("UTF-8")));
		onlineuser.setLogintime(System.currentTimeMillis());
		return true;
	}
}
