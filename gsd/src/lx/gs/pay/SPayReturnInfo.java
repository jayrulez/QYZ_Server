
package lx.gs.pay;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SPayReturnInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SPayReturnInfo extends __SPayReturnInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574806;

	public int getType() {
		return 6574806;
	}

	public byte hasgotpayreturn;
	public long vipexp;
	public long yuanbao;
	public long bindyuanbao;

	public SPayReturnInfo() {
	}

	public SPayReturnInfo(byte _hasgotpayreturn_, long _vipexp_, long _yuanbao_, long _bindyuanbao_) {
		this.hasgotpayreturn = _hasgotpayreturn_;
		this.vipexp = _vipexp_;
		this.yuanbao = _yuanbao_;
		this.bindyuanbao = _bindyuanbao_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(hasgotpayreturn);
		_os_.marshal(vipexp);
		_os_.marshal(yuanbao);
		_os_.marshal(bindyuanbao);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		hasgotpayreturn = _os_.unmarshal_byte();
		vipexp = _os_.unmarshal_long();
		yuanbao = _os_.unmarshal_long();
		bindyuanbao = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SPayReturnInfo) {
			SPayReturnInfo _o_ = (SPayReturnInfo)_o1_;
			if (hasgotpayreturn != _o_.hasgotpayreturn) return false;
			if (vipexp != _o_.vipexp) return false;
			if (yuanbao != _o_.yuanbao) return false;
			if (bindyuanbao != _o_.bindyuanbao) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)hasgotpayreturn;
		_h_ += (int)vipexp;
		_h_ += (int)yuanbao;
		_h_ += (int)bindyuanbao;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(hasgotpayreturn).append(",");
		_sb_.append(vipexp).append(",");
		_sb_.append(yuanbao).append(",");
		_sb_.append(bindyuanbao).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SPayReturnInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = hasgotpayreturn - _o_.hasgotpayreturn;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(vipexp - _o_.vipexp);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(yuanbao - _o_.yuanbao);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(bindyuanbao - _o_.bindyuanbao);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

