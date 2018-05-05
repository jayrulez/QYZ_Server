
package lx.gs.chat.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBeSilentNotify__ extends xio.Protocol { }

/** 被举报达到次数后通知玩家被禁言
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBeSilentNotify extends __SBeSilentNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577159;

	public int getType() {
		return 6577159;
	}

	public long silentendtime; // 禁言结束时间

	public SBeSilentNotify() {
	}

	public SBeSilentNotify(long _silentendtime_) {
		this.silentendtime = _silentendtime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(silentendtime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		silentendtime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBeSilentNotify) {
			SBeSilentNotify _o_ = (SBeSilentNotify)_o1_;
			if (silentendtime != _o_.silentendtime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)silentendtime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(silentendtime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SBeSilentNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(silentendtime - _o_.silentendtime);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

