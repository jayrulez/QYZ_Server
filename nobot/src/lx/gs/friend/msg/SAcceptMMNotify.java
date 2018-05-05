
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAcceptMMNotify__ extends xio.Protocol { }

/** 别人接受我为脉脉关系的通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAcceptMMNotify extends __SAcceptMMNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563788;

	public int getType() {
		return 6563788;
	}

	public int mmtype;
	public lx.gs.friend.msg.RoleShowInfo mmroleinfo;

	public SAcceptMMNotify() {
		mmroleinfo = new lx.gs.friend.msg.RoleShowInfo();
	}

	public SAcceptMMNotify(int _mmtype_, lx.gs.friend.msg.RoleShowInfo _mmroleinfo_) {
		this.mmtype = _mmtype_;
		this.mmroleinfo = _mmroleinfo_;
	}

	public final boolean _validator_() {
		if (!mmroleinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(mmtype);
		_os_.marshal(mmroleinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		mmtype = _os_.unmarshal_int();
		mmroleinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAcceptMMNotify) {
			SAcceptMMNotify _o_ = (SAcceptMMNotify)_o1_;
			if (mmtype != _o_.mmtype) return false;
			if (!mmroleinfo.equals(_o_.mmroleinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += mmtype;
		_h_ += mmroleinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mmtype).append(",");
		_sb_.append(mmroleinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

