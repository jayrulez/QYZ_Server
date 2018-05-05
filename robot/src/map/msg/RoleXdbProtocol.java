
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __RoleXdbProtocol__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class RoleXdbProtocol extends __RoleXdbProtocol__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6703965;

	public int getType() {
		return 6703965;
	}

	public long roleid;
	public gnet.ProtocolData proto;

	public RoleXdbProtocol() {
		proto = new gnet.ProtocolData();
	}

	public RoleXdbProtocol(long _roleid_, gnet.ProtocolData _proto_) {
		this.roleid = _roleid_;
		this.proto = _proto_;
	}

	public final boolean _validator_() {
		if (!proto._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(proto);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		proto.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleXdbProtocol) {
			RoleXdbProtocol _o_ = (RoleXdbProtocol)_o1_;
			if (roleid != _o_.roleid) return false;
			if (!proto.equals(_o_.proto)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += proto.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(proto).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

