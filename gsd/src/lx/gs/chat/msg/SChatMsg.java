
package lx.gs.chat.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChatMsg__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChatMsg extends __SChatMsg__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568747;

	public int getType() {
		return 6568747;
	}

	public long lasttalktime; // 上次发言时间
	public long silentendtime; // 禁言结束时间
	public int leftreporttime; // 剩余举报次数

	public SChatMsg() {
	}

	public SChatMsg(long _lasttalktime_, long _silentendtime_, int _leftreporttime_) {
		this.lasttalktime = _lasttalktime_;
		this.silentendtime = _silentendtime_;
		this.leftreporttime = _leftreporttime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(lasttalktime);
		_os_.marshal(silentendtime);
		_os_.marshal(leftreporttime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		lasttalktime = _os_.unmarshal_long();
		silentendtime = _os_.unmarshal_long();
		leftreporttime = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChatMsg) {
			SChatMsg _o_ = (SChatMsg)_o1_;
			if (lasttalktime != _o_.lasttalktime) return false;
			if (silentendtime != _o_.silentendtime) return false;
			if (leftreporttime != _o_.leftreporttime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)lasttalktime;
		_h_ += (int)silentendtime;
		_h_ += leftreporttime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lasttalktime).append(",");
		_sb_.append(silentendtime).append(",");
		_sb_.append(leftreporttime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SChatMsg _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(lasttalktime - _o_.lasttalktime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(silentendtime - _o_.silentendtime);
		if (0 != _c_) return _c_;
		_c_ = leftreporttime - _o_.leftreporttime;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

