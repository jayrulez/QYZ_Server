
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEatRune__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEatRune extends __SEatRune__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6709281;

	public int getType() {
		return 6709281;
	}

	public int runeid;
	public long runeagentid;
	public int result; // 0 失败 1成功

	public SEatRune() {
	}

	public SEatRune(int _runeid_, long _runeagentid_, int _result_) {
		this.runeid = _runeid_;
		this.runeagentid = _runeagentid_;
		this.result = _result_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(runeid);
		_os_.marshal(runeagentid);
		_os_.marshal(result);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		runeid = _os_.unmarshal_int();
		runeagentid = _os_.unmarshal_long();
		result = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEatRune) {
			SEatRune _o_ = (SEatRune)_o1_;
			if (runeid != _o_.runeid) return false;
			if (runeagentid != _o_.runeagentid) return false;
			if (result != _o_.result) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += runeid;
		_h_ += (int)runeagentid;
		_h_ += result;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(runeid).append(",");
		_sb_.append(runeagentid).append(",");
		_sb_.append(result).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEatRune _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = runeid - _o_.runeid;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(runeagentid - _o_.runeagentid);
		if (0 != _c_) return _c_;
		_c_ = result - _o_.result;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

