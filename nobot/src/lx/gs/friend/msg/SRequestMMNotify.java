
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SRequestMMNotify__ extends xio.Protocol { }

/** 别人申请加脉脉的通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SRequestMMNotify extends __SRequestMMNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563938;

	public int getType() {
		return 6563938;
	}

	public int reqmmtype;
	public int mmtype;
	public lx.gs.friend.msg.RoleShowInfo mmroleinfo;

	public SRequestMMNotify() {
		mmroleinfo = new lx.gs.friend.msg.RoleShowInfo();
	}

	public SRequestMMNotify(int _reqmmtype_, int _mmtype_, lx.gs.friend.msg.RoleShowInfo _mmroleinfo_) {
		this.reqmmtype = _reqmmtype_;
		this.mmtype = _mmtype_;
		this.mmroleinfo = _mmroleinfo_;
	}

	public final boolean _validator_() {
		if (!mmroleinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(reqmmtype);
		_os_.marshal(mmtype);
		_os_.marshal(mmroleinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		reqmmtype = _os_.unmarshal_int();
		mmtype = _os_.unmarshal_int();
		mmroleinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SRequestMMNotify) {
			SRequestMMNotify _o_ = (SRequestMMNotify)_o1_;
			if (reqmmtype != _o_.reqmmtype) return false;
			if (mmtype != _o_.mmtype) return false;
			if (!mmroleinfo.equals(_o_.mmroleinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += reqmmtype;
		_h_ += mmtype;
		_h_ += mmroleinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(reqmmtype).append(",");
		_sb_.append(mmtype).append(",");
		_sb_.append(mmroleinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

