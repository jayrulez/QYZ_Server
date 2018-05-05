
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __Response__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class Response extends __Response__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 103;

	public int getType() {
		return 103;
	}

	public com.goldhuman.Common.Octets user_identity;
	public com.goldhuman.Common.Octets token;
	public gnet.PlatType plattype; // 在混合平台时plattype用来区分具体平台
	public com.goldhuman.Common.Octets deviceid;
	public com.goldhuman.Common.Octets os;
	public com.goldhuman.Common.Octets platform;

	public Response() {
		user_identity = new com.goldhuman.Common.Octets();
		token = new com.goldhuman.Common.Octets();
		plattype = new gnet.PlatType();
		deviceid = new com.goldhuman.Common.Octets();
		os = new com.goldhuman.Common.Octets();
		platform = new com.goldhuman.Common.Octets();
	}

	public Response(com.goldhuman.Common.Octets _user_identity_, com.goldhuman.Common.Octets _token_, gnet.PlatType _plattype_, com.goldhuman.Common.Octets _deviceid_, com.goldhuman.Common.Octets _os_, com.goldhuman.Common.Octets _platform_) {
		this.user_identity = _user_identity_;
		this.token = _token_;
		this.plattype = _plattype_;
		this.deviceid = _deviceid_;
		this.os = _os_;
		this.platform = _platform_;
	}

	public final boolean _validator_() {
		if (!plattype._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(user_identity);
		_os_.marshal(token);
		_os_.marshal(plattype);
		_os_.marshal(deviceid);
		_os_.marshal(os);
		_os_.marshal(platform);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		user_identity = _os_.unmarshal_Octets();
		token = _os_.unmarshal_Octets();
		plattype.unmarshal(_os_);
		deviceid = _os_.unmarshal_Octets();
		os = _os_.unmarshal_Octets();
		platform = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Response) {
			Response _o_ = (Response)_o1_;
			if (!user_identity.equals(_o_.user_identity)) return false;
			if (!token.equals(_o_.token)) return false;
			if (!plattype.equals(_o_.plattype)) return false;
			if (!deviceid.equals(_o_.deviceid)) return false;
			if (!os.equals(_o_.os)) return false;
			if (!platform.equals(_o_.platform)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += user_identity.hashCode();
		_h_ += token.hashCode();
		_h_ += plattype.hashCode();
		_h_ += deviceid.hashCode();
		_h_ += os.hashCode();
		_h_ += platform.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("B").append(user_identity.size()).append(",");
		_sb_.append("B").append(token.size()).append(",");
		_sb_.append(plattype).append(",");
		_sb_.append("B").append(deviceid.size()).append(",");
		_sb_.append("B").append(os.size()).append(",");
		_sb_.append("B").append(platform.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

