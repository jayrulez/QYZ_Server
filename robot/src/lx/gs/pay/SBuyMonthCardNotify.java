
package lx.gs.pay;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBuyMonthCardNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBuyMonthCardNotify extends __SBuyMonthCardNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566884;

	public int getType() {
		return 6566884;
	}

	public int buymonthcardtime;
	public int monthcardleftdays;

	public SBuyMonthCardNotify() {
	}

	public SBuyMonthCardNotify(int _buymonthcardtime_, int _monthcardleftdays_) {
		this.buymonthcardtime = _buymonthcardtime_;
		this.monthcardleftdays = _monthcardleftdays_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(buymonthcardtime);
		_os_.marshal(monthcardleftdays);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		buymonthcardtime = _os_.unmarshal_int();
		monthcardleftdays = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBuyMonthCardNotify) {
			SBuyMonthCardNotify _o_ = (SBuyMonthCardNotify)_o1_;
			if (buymonthcardtime != _o_.buymonthcardtime) return false;
			if (monthcardleftdays != _o_.monthcardleftdays) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += buymonthcardtime;
		_h_ += monthcardleftdays;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(buymonthcardtime).append(",");
		_sb_.append(monthcardleftdays).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SBuyMonthCardNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = buymonthcardtime - _o_.buymonthcardtime;
		if (0 != _c_) return _c_;
		_c_ = monthcardleftdays - _o_.monthcardleftdays;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

