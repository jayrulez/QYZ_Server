
package lx.gs.family.msg;

import lx.gs.family.PRequestJoinFamily;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRequestJoinFamily__ extends xio.Protocol { }

/** 申请加入家族
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRequestJoinFamily extends __CRequestJoinFamily__ {
	@Override
	protected void process() {
		new PRequestJoinFamily(this).validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578257;

	public int getType() {
		return 6578257;
	}

	public long familyid; // 家族id

	public CRequestJoinFamily() {
	}

	public CRequestJoinFamily(long _familyid_) {
		this.familyid = _familyid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familyid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familyid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRequestJoinFamily) {
			CRequestJoinFamily _o_ = (CRequestJoinFamily)_o1_;
			if (familyid != _o_.familyid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)familyid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(familyid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRequestJoinFamily _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(familyid - _o_.familyid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

