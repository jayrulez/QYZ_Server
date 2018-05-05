
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __ForwardMultiClients__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class ForwardMultiClients extends __ForwardMultiClients__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 173;

	public int getType() {
		return 173;
	}

	public java.util.HashSet<Long> roleids;
	public gnet.ProtocolData proto;

	public ForwardMultiClients() {
		roleids = new java.util.HashSet<Long>();
		proto = new gnet.ProtocolData();
	}

	public ForwardMultiClients(java.util.HashSet<Long> _roleids_, gnet.ProtocolData _proto_) {
		this.roleids = _roleids_;
		this.proto = _proto_;
	}

	public final boolean _validator_() {
		if (!proto._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(roleids.size());
		for (Long _v_ : roleids) {
			_os_.marshal(_v_);
		}
		_os_.marshal(proto);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			roleids.add(_v_);
		}
		proto.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ForwardMultiClients) {
			ForwardMultiClients _o_ = (ForwardMultiClients)_o1_;
			if (!roleids.equals(_o_.roleids)) return false;
			if (!proto.equals(_o_.proto)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += roleids.hashCode();
		_h_ += proto.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleids).append(",");
		_sb_.append(proto).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

