
package lx.gs.bonus.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetMonthCardGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetMonthCardGift extends __SGetMonthCardGift__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556119;

	public int getType() {
		return 6556119;
	}

	public int date;
	public map.msg.Bonus monthcardgift;

	public SGetMonthCardGift() {
		monthcardgift = new map.msg.Bonus();
	}

	public SGetMonthCardGift(int _date_, map.msg.Bonus _monthcardgift_) {
		this.date = _date_;
		this.monthcardgift = _monthcardgift_;
	}

	public final boolean _validator_() {
		if (!monthcardgift._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(date);
		_os_.marshal(monthcardgift);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		date = _os_.unmarshal_int();
		monthcardgift.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetMonthCardGift) {
			SGetMonthCardGift _o_ = (SGetMonthCardGift)_o1_;
			if (date != _o_.date) return false;
			if (!monthcardgift.equals(_o_.monthcardgift)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += date;
		_h_ += monthcardgift.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(date).append(",");
		_sb_.append(monthcardgift).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

