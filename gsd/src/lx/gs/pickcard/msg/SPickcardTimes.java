
package lx.gs.pickcard.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SPickcardTimes__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SPickcardTimes extends __SPickcardTimes__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577453;

	public int getType() {
		return 6577453;
	}

	public int huobanhigh; // 剩余多少次必得
	public int huobanlow;
	public int fabaohigh;
	public int fabaolow;

	public SPickcardTimes() {
	}

	public SPickcardTimes(int _huobanhigh_, int _huobanlow_, int _fabaohigh_, int _fabaolow_) {
		this.huobanhigh = _huobanhigh_;
		this.huobanlow = _huobanlow_;
		this.fabaohigh = _fabaohigh_;
		this.fabaolow = _fabaolow_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(huobanhigh);
		_os_.marshal(huobanlow);
		_os_.marshal(fabaohigh);
		_os_.marshal(fabaolow);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		huobanhigh = _os_.unmarshal_int();
		huobanlow = _os_.unmarshal_int();
		fabaohigh = _os_.unmarshal_int();
		fabaolow = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SPickcardTimes) {
			SPickcardTimes _o_ = (SPickcardTimes)_o1_;
			if (huobanhigh != _o_.huobanhigh) return false;
			if (huobanlow != _o_.huobanlow) return false;
			if (fabaohigh != _o_.fabaohigh) return false;
			if (fabaolow != _o_.fabaolow) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += huobanhigh;
		_h_ += huobanlow;
		_h_ += fabaohigh;
		_h_ += fabaolow;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(huobanhigh).append(",");
		_sb_.append(huobanlow).append(",");
		_sb_.append(fabaohigh).append(",");
		_sb_.append(fabaolow).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SPickcardTimes _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = huobanhigh - _o_.huobanhigh;
		if (0 != _c_) return _c_;
		_c_ = huobanlow - _o_.huobanlow;
		if (0 != _c_) return _c_;
		_c_ = fabaohigh - _o_.fabaohigh;
		if (0 != _c_) return _c_;
		_c_ = fabaolow - _o_.fabaolow;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

