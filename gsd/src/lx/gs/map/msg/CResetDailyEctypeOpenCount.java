
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CResetDailyEctypeOpenCount__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CResetDailyEctypeOpenCount extends __CResetDailyEctypeOpenCount__ {
	@Override
	protected void process() {
		new AProcedure<CResetDailyEctypeOpenCount>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final ErrorCode err = FCondition.checkA(roleid, CfgMgr.ectypesingle.ectypes.get(ectypetype).viptimes, 1,
						By.Reset_Daily_Open_Count, ConfigId.DAILY_ECTYPE_RESET_OPEN_COUNT, ectypetype);
				if(err.err())
					return error(err);
				if(!FLimit.clearLimit(roleid, ConfigId.DAILY_ECTYPE, ectypetype))
					return error(ErrorCode.NOT_NEED_RESET_OPEN_COUNT);
				response(new SResetDailyEctypeOpenCount(ectypetype));
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573765;

	public int getType() {
		return 6573765;
	}

	public int ectypetype; // cfg.ectype.EctypeType

	public CResetDailyEctypeOpenCount() {
	}

	public CResetDailyEctypeOpenCount(int _ectypetype_) {
		this.ectypetype = _ectypetype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypetype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypetype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CResetDailyEctypeOpenCount) {
			CResetDailyEctypeOpenCount _o_ = (CResetDailyEctypeOpenCount)_o1_;
			if (ectypetype != _o_.ectypetype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypetype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypetype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CResetDailyEctypeOpenCount _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypetype - _o_.ectypetype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

