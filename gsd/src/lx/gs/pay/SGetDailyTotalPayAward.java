
package lx.gs.pay;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetDailyTotalPayAward__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetDailyTotalPayAward extends __SGetDailyTotalPayAward__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555409;

	public int getType() {
		return 6555409;
	}

	public int dailytotalpaystatus;

	public SGetDailyTotalPayAward() {
	}

	public SGetDailyTotalPayAward(int _dailytotalpaystatus_) {
		this.dailytotalpaystatus = _dailytotalpaystatus_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(dailytotalpaystatus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		dailytotalpaystatus = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetDailyTotalPayAward) {
			SGetDailyTotalPayAward _o_ = (SGetDailyTotalPayAward)_o1_;
            return dailytotalpaystatus == _o_.dailytotalpaystatus;
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += dailytotalpaystatus;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(dailytotalpaystatus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SGetDailyTotalPayAward _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = dailytotalpaystatus - _o_.dailytotalpaystatus;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

