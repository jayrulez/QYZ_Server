
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAcceptMM__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAcceptMM extends __SAcceptMM__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556581;

	public int getType() {
		return 6556581;
	}

	public int result;
	public int mmtype;
	public lx.gs.friend.msg.RoleShowInfo mmroleinfo;

	public SAcceptMM() {
		mmroleinfo = new lx.gs.friend.msg.RoleShowInfo();
	}

	public SAcceptMM(int _result_, int _mmtype_, lx.gs.friend.msg.RoleShowInfo _mmroleinfo_) {
		this.result = _result_;
		this.mmtype = _mmtype_;
		this.mmroleinfo = _mmroleinfo_;
	}

	public final boolean _validator_() {
		if (!mmroleinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(mmtype);
		_os_.marshal(mmroleinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		mmtype = _os_.unmarshal_int();
		mmroleinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAcceptMM) {
			SAcceptMM _o_ = (SAcceptMM)_o1_;
			if (result != _o_.result) return false;
			if (mmtype != _o_.mmtype) return false;
			if (!mmroleinfo.equals(_o_.mmroleinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += mmtype;
		_h_ += mmroleinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(mmtype).append(",");
		_sb_.append(mmroleinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

