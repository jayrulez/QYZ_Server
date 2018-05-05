
package gnet.link;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __AnnounceUserOnline__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class AnnounceUserOnline extends __AnnounceUserOnline__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65548;

	public int getType() {
		return 65548;
	}

	public long userid;
	public com.goldhuman.Common.Octets username;
	public gnet.PlatType plattype;
	public com.goldhuman.Common.Octets deviceid;
	public com.goldhuman.Common.Octets os;
	public com.goldhuman.Common.Octets peer;
	public com.goldhuman.Common.Octets platform;

	public AnnounceUserOnline() {
		username = new com.goldhuman.Common.Octets();
		plattype = new gnet.PlatType();
		deviceid = new com.goldhuman.Common.Octets();
		os = new com.goldhuman.Common.Octets();
		peer = new com.goldhuman.Common.Octets();
		platform = new com.goldhuman.Common.Octets();
	}

	public AnnounceUserOnline(long _userid_, com.goldhuman.Common.Octets _username_, gnet.PlatType _plattype_, com.goldhuman.Common.Octets _deviceid_, com.goldhuman.Common.Octets _os_, com.goldhuman.Common.Octets _peer_, com.goldhuman.Common.Octets _platform_) {
		this.userid = _userid_;
		this.username = _username_;
		this.plattype = _plattype_;
		this.deviceid = _deviceid_;
		this.os = _os_;
		this.peer = _peer_;
		this.platform = _platform_;
	}

	public final boolean _validator_() {
		if (!plattype._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(userid);
		_os_.marshal(username);
		_os_.marshal(plattype);
		_os_.marshal(deviceid);
		_os_.marshal(os);
		_os_.marshal(peer);
		_os_.marshal(platform);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		userid = _os_.unmarshal_long();
		username = _os_.unmarshal_Octets();
		plattype.unmarshal(_os_);
		deviceid = _os_.unmarshal_Octets();
		os = _os_.unmarshal_Octets();
		peer = _os_.unmarshal_Octets();
		platform = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AnnounceUserOnline) {
			AnnounceUserOnline _o_ = (AnnounceUserOnline)_o1_;
			if (userid != _o_.userid) return false;
			if (!username.equals(_o_.username)) return false;
			if (!plattype.equals(_o_.plattype)) return false;
			if (!deviceid.equals(_o_.deviceid)) return false;
			if (!os.equals(_o_.os)) return false;
			if (!peer.equals(_o_.peer)) return false;
			if (!platform.equals(_o_.platform)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)userid;
		_h_ += username.hashCode();
		_h_ += plattype.hashCode();
		_h_ += deviceid.hashCode();
		_h_ += os.hashCode();
		_h_ += peer.hashCode();
		_h_ += platform.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(userid).append(",");
		_sb_.append("B").append(username.size()).append(",");
		_sb_.append(plattype).append(",");
		_sb_.append("B").append(deviceid.size()).append(",");
		_sb_.append("B").append(os.size()).append(",");
		_sb_.append("B").append(peer.size()).append(",");
		_sb_.append("B").append(platform.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

