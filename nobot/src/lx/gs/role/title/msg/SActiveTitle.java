
package lx.gs.role.title.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SActiveTitle__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SActiveTitle extends __SActiveTitle__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557904;

	public int getType() {
		return 6557904;
	}

	public int titlekey; // 要激活的title的key
	public int titletype; // 要激活的title的类型，属于哪个模块

	public SActiveTitle() {
	}

	public SActiveTitle(int _titlekey_, int _titletype_) {
		this.titlekey = _titlekey_;
		this.titletype = _titletype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(titlekey);
		_os_.marshal(titletype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		titlekey = _os_.unmarshal_int();
		titletype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SActiveTitle) {
			SActiveTitle _o_ = (SActiveTitle)_o1_;
			if (titlekey != _o_.titlekey) return false;
			if (titletype != _o_.titletype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += titlekey;
		_h_ += titletype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(titlekey).append(",");
		_sb_.append(titletype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SActiveTitle _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = titlekey - _o_.titlekey;
		if (0 != _c_) return _c_;
		_c_ = titletype - _o_.titletype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

