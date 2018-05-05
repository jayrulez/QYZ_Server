
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEndHuiWu__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEndHuiWu extends __SEndHuiWu__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6713746;

	public int getType() {
		return 6713746;
	}

	public int result;
	public int roundindex;
	public int battleindex;

	public SEndHuiWu() {
	}

	public SEndHuiWu(int _result_, int _roundindex_, int _battleindex_) {
		this.result = _result_;
		this.roundindex = _roundindex_;
		this.battleindex = _battleindex_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(roundindex);
		_os_.marshal(battleindex);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		roundindex = _os_.unmarshal_int();
		battleindex = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEndHuiWu) {
			SEndHuiWu _o_ = (SEndHuiWu)_o1_;
			if (result != _o_.result) return false;
			if (roundindex != _o_.roundindex) return false;
			if (battleindex != _o_.battleindex) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += roundindex;
		_h_ += battleindex;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(roundindex).append(",");
		_sb_.append(battleindex).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEndHuiWu _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = result - _o_.result;
		if (0 != _c_) return _c_;
		_c_ = roundindex - _o_.roundindex;
		if (0 != _c_) return _c_;
		_c_ = battleindex - _o_.battleindex;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

