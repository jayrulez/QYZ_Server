
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAddMoney__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAddMoney extends __SAddMoney__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575532;

	public int getType() {
		return 6575532;
	}

	public long crowfamilyid;
	public int amount; // 增加的金额

	public SAddMoney() {
	}

	public SAddMoney(long _crowfamilyid_, int _amount_) {
		this.crowfamilyid = _crowfamilyid_;
		this.amount = _amount_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(crowfamilyid);
		_os_.marshal(amount);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		crowfamilyid = _os_.unmarshal_long();
		amount = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAddMoney) {
			SAddMoney _o_ = (SAddMoney)_o1_;
			if (crowfamilyid != _o_.crowfamilyid) return false;
			if (amount != _o_.amount) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)crowfamilyid;
		_h_ += amount;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(crowfamilyid).append(",");
		_sb_.append(amount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SAddMoney _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(crowfamilyid - _o_.crowfamilyid);
		if (0 != _c_) return _c_;
		_c_ = amount - _o_.amount;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

