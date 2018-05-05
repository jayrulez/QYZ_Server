
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAcceptFailNotify__ extends xio.Protocol { }

/** 同意申请失败，该玩家已加入其他家族
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAcceptFailNotify extends __SAcceptFailNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574270;

	public int getType() {
		return 6574270;
	}

	public long acceptid;

	public SAcceptFailNotify() {
	}

	public SAcceptFailNotify(long _acceptid_) {
		this.acceptid = _acceptid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(acceptid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		acceptid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAcceptFailNotify) {
			SAcceptFailNotify _o_ = (SAcceptFailNotify)_o1_;
			if (acceptid != _o_.acceptid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)acceptid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(acceptid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SAcceptFailNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(acceptid - _o_.acceptid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

