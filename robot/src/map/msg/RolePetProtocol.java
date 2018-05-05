
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __RolePetProtocol__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class RolePetProtocol extends __RolePetProtocol__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6699649;

	public int getType() {
		return 6699649;
	}

	public int petkey;
	public gnet.ProtocolData proto;

	public RolePetProtocol() {
		proto = new gnet.ProtocolData();
	}

	public RolePetProtocol(int _petkey_, gnet.ProtocolData _proto_) {
		this.petkey = _petkey_;
		this.proto = _proto_;
	}

	public final boolean _validator_() {
		if (!proto._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(petkey);
		_os_.marshal(proto);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		petkey = _os_.unmarshal_int();
		proto.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RolePetProtocol) {
			RolePetProtocol _o_ = (RolePetProtocol)_o1_;
			if (petkey != _o_.petkey) return false;
			if (!proto.equals(_o_.proto)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += petkey;
		_h_ += proto.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(petkey).append(",");
		_sb_.append(proto).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

