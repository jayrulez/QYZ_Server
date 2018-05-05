
package lx.gs.bonus.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetGrwoPlanGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetGrwoPlanGift extends __SGetGrwoPlanGift__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556121;

	public int getType() {
		return 6556121;
	}

	public int growplantype;
	public int giftindx;

	public SGetGrwoPlanGift() {
	}

	public SGetGrwoPlanGift(int _growplantype_, int _giftindx_) {
		this.growplantype = _growplantype_;
		this.giftindx = _giftindx_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(growplantype);
		_os_.marshal(giftindx);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		growplantype = _os_.unmarshal_int();
		giftindx = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetGrwoPlanGift) {
			SGetGrwoPlanGift _o_ = (SGetGrwoPlanGift)_o1_;
			if (growplantype != _o_.growplantype) return false;
			if (giftindx != _o_.giftindx) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += growplantype;
		_h_ += giftindx;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(growplantype).append(",");
		_sb_.append(giftindx).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SGetGrwoPlanGift _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = growplantype - _o_.growplantype;
		if (0 != _c_) return _c_;
		_c_ = giftindx - _o_.giftindx;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

