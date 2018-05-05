
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Octets;
import robot.Client;
import robot.Ctx;

import java.nio.charset.StandardCharsets;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __Challenge__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class Challenge extends __Challenge__ {

    public Octets to(String s) {
        return Octets.wrap(s.getBytes(StandardCharsets.UTF_8));
    }
	@Override
	protected void process() {
		final Ctx ctx = (Ctx)getContext();
        final Response re = new Response();
        re.user_identity = to(ctx.account);
        re.token = Client.token;
        re.plattype.plat = PlatType.TEST;
        re.deviceid = to("huangqiang.pc");
        re.os = to("os");
        re.platform = to("windows");
        re.send(getConnection());
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 101;

	public int getType() {
		return 101;
	}

	public int version; // 0代表不检查版本
	public gnet.PlatType plattype;
	public int serverid;
	public gnet.ServerLoad serverload;

	public Challenge() {
		plattype = new gnet.PlatType();
		serverload = new gnet.ServerLoad();
	}

	public Challenge(int _version_, gnet.PlatType _plattype_, int _serverid_, gnet.ServerLoad _serverload_) {
		this.version = _version_;
		this.plattype = _plattype_;
		this.serverid = _serverid_;
		this.serverload = _serverload_;
	}

	public final boolean _validator_() {
		if (!plattype._validator_()) return false;
		if (!serverload._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(version);
		_os_.marshal(plattype);
		_os_.marshal(serverid);
		_os_.marshal(serverload);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		version = _os_.unmarshal_int();
		plattype.unmarshal(_os_);
		serverid = _os_.unmarshal_int();
		serverload.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Challenge) {
			Challenge _o_ = (Challenge)_o1_;
			if (version != _o_.version) return false;
			if (!plattype.equals(_o_.plattype)) return false;
			if (serverid != _o_.serverid) return false;
			if (!serverload.equals(_o_.serverload)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += version;
		_h_ += plattype.hashCode();
		_h_ += serverid;
		_h_ += serverload.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(version).append(",");
		_sb_.append(plattype).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(serverload).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(Challenge _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = version - _o_.version;
		if (0 != _c_) return _c_;
		_c_ = plattype.compareTo(_o_.plattype);
		if (0 != _c_) return _c_;
		_c_ = serverid - _o_.serverid;
		if (0 != _c_) return _c_;
		_c_ = serverload.compareTo(_o_.serverload);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

