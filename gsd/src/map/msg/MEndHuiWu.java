
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MEndHuiWu__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MEndHuiWu extends __MEndHuiWu__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6715172;

	public int getType() {
		return 6715172;
	}

	public int result; // 负输,正赢
	public int profession;
	public int roundindex;
	public int battleindex;

	public MEndHuiWu() {
	}

	public MEndHuiWu(int _result_, int _profession_, int _roundindex_, int _battleindex_) {
		this.result = _result_;
		this.profession = _profession_;
		this.roundindex = _roundindex_;
		this.battleindex = _battleindex_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(profession);
		_os_.marshal(roundindex);
		_os_.marshal(battleindex);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		roundindex = _os_.unmarshal_int();
		battleindex = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MEndHuiWu) {
			MEndHuiWu _o_ = (MEndHuiWu)_o1_;
			if (result != _o_.result) return false;
			if (profession != _o_.profession) return false;
			if (roundindex != _o_.roundindex) return false;
			if (battleindex != _o_.battleindex) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += profession;
		_h_ += roundindex;
		_h_ += battleindex;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(roundindex).append(",");
		_sb_.append(battleindex).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(MEndHuiWu _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = result - _o_.result;
		if (0 != _c_) return _c_;
		_c_ = profession - _o_.profession;
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

