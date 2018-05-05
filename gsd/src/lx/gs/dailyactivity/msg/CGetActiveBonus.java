
package lx.gs.dailyactivity.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.dailyactivity.FDailyActivity;
import common.ErrorCode;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetActiveBonus__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetActiveBonus extends __CGetActiveBonus__ {
	@Override
	protected void process() {
		new AProcedure<CGetActiveBonus>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				SGetActiveBonus response = new SGetActiveBonus();
				final ErrorCode code= FDailyActivity.getActiveBonus(roleid, bonustype, response);
				if(code.err()){
					return error(code);
				}
				response(response);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556433;

	public int getType() {
		return 6556433;
	}

	public int bonustype;

	public CGetActiveBonus() {
	}

	public CGetActiveBonus(int _bonustype_) {
		this.bonustype = _bonustype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bonustype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bonustype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetActiveBonus) {
			CGetActiveBonus _o_ = (CGetActiveBonus)_o1_;
			if (bonustype != _o_.bonustype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bonustype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bonustype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetActiveBonus _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bonustype - _o_.bonustype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

