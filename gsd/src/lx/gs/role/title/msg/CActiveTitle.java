
package lx.gs.role.title.msg;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.role.title.FTitle;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CActiveTitle__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CActiveTitle extends __CActiveTitle__ {
	@Override
	protected void process() {
		new AProcedure<CActiveTitle>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				SActiveTitle result = new SActiveTitle();
				result.titlekey = param.titlekey;
				result.titletype = param.titletype;
				final ErrorCode ret = FTitle.activeTitle(roleid, param.titlekey, param.titletype);
				if(ret.err()) {
					xdb.Trace.debug("Dress.CActiveDress errcode:{} roleid:{} dresskey:{}", ret, roleid, param.titlekey);
					return error(ret);
				}
				response(result);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557903;

	public int getType() {
		return 6557903;
	}

	public int titlekey; // 要激活的title的key
	public int titletype; // 要激活的title的类型，属于哪个模块

	public CActiveTitle() {
	}

	public CActiveTitle(int _titlekey_, int _titletype_) {
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
		if (_o1_ instanceof CActiveTitle) {
			CActiveTitle _o_ = (CActiveTitle)_o1_;
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

	public int compareTo(CActiveTitle _o_) {
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

