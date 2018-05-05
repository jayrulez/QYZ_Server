
package lx.gs.chat.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SReportPlayer__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SReportPlayer extends __SReportPlayer__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573073;

	public int getType() {
		return 6573073;
	}

	public long bereportid; // 被举报的玩家的roleid

	public SReportPlayer() {
	}

	public SReportPlayer(long _bereportid_) {
		this.bereportid = _bereportid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bereportid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bereportid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SReportPlayer) {
			SReportPlayer _o_ = (SReportPlayer)_o1_;
			if (bereportid != _o_.bereportid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)bereportid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bereportid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SReportPlayer _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(bereportid - _o_.bereportid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

