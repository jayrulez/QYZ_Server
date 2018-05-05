
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAttemptPropose__ extends xio.Protocol { }

/** 判断是否满足结婚条件
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAttemptPropose extends __CAttemptPropose__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576050;

	public int getType() {
		return 6576050;
	}

	public long beproposedroleid; // 被求婚的id

	public CAttemptPropose() {
	}

	public CAttemptPropose(long _beproposedroleid_) {
		this.beproposedroleid = _beproposedroleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(beproposedroleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		beproposedroleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAttemptPropose) {
			CAttemptPropose _o_ = (CAttemptPropose)_o1_;
			if (beproposedroleid != _o_.beproposedroleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)beproposedroleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(beproposedroleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAttemptPropose _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(beproposedroleid - _o_.beproposedroleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

