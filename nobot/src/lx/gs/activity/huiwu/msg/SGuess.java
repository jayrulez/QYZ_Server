
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGuess__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGuess extends __SGuess__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577902;

	public int getType() {
		return 6577902;
	}

	public int profession;
	public long target;
	public int beguessnum;

	public SGuess() {
	}

	public SGuess(int _profession_, long _target_, int _beguessnum_) {
		this.profession = _profession_;
		this.target = _target_;
		this.beguessnum = _beguessnum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(profession);
		_os_.marshal(target);
		_os_.marshal(beguessnum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		profession = _os_.unmarshal_int();
		target = _os_.unmarshal_long();
		beguessnum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGuess) {
			SGuess _o_ = (SGuess)_o1_;
			if (profession != _o_.profession) return false;
			if (target != _o_.target) return false;
			if (beguessnum != _o_.beguessnum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += profession;
		_h_ += (int)target;
		_h_ += beguessnum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(profession).append(",");
		_sb_.append(target).append(",");
		_sb_.append(beguessnum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SGuess _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = profession - _o_.profession;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(target - _o_.target);
		if (0 != _c_) return _c_;
		_c_ = beguessnum - _o_.beguessnum;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

