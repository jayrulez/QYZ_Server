
package lx.gs.dailyactivity.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.dailyactivity.FDailyActivity;
import common.ErrorCode;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CFindBack__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CFindBack extends __CFindBack__ {
	@Override
	protected void process() {
		new AProcedure<CFindBack>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				SFindBack respone = new SFindBack();
				final ErrorCode code = FDailyActivity.findBack(roleid, findtype, eventtype);
				if(code.err()){
					return error(code);
				}
				respone.findtype = findtype;
				respone.eventtype = eventtype;
				response(respone);
				return true;
			}
			
		}.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556437;

	public int getType() {
		return 6556437;
	}

	public int findtype; // 找回类型
	public int eventtype; // 要找回的系统

	public CFindBack() {
	}

	public CFindBack(int _findtype_, int _eventtype_) {
		this.findtype = _findtype_;
		this.eventtype = _eventtype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(findtype);
		_os_.marshal(eventtype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		findtype = _os_.unmarshal_int();
		eventtype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFindBack) {
			CFindBack _o_ = (CFindBack)_o1_;
			if (findtype != _o_.findtype) return false;
			if (eventtype != _o_.eventtype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += findtype;
		_h_ += eventtype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(findtype).append(",");
		_sb_.append(eventtype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CFindBack _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = findtype - _o_.findtype;
		if (0 != _c_) return _c_;
		_c_ = eventtype - _o_.eventtype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

