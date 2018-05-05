
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.ectype.EctypeType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __COpenDailyEctype__ extends xio.Protocol { }

/** 日常经验金钱造化副本
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class COpenDailyEctype extends __COpenDailyEctype__ {
	@Override
	protected void process() {
		new AProcedure<COpenDailyEctype>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				if (!FMap.isInWorldOrFamily(roleid))
					return error(ErrorCode.CANNOT_ENTER_ECTYPE_IN_ECTYPE);
				ErrorCode err = FCondition.checkByReflection(roleid, CfgMgr.ectypesingle.ectypes.get(ectypetype), 1, By.Open_Ectype, ConfigId.DAILY_ECTYPE, ectypetype);
				if(err.err())
					return error(err);
				final int ectypeid = FMap.getDailyEctypeidByRoleLevel(roleid, ectypetype);
                final Object cmd;
                switch (ectypetype) {
                    case EctypeType.EXP: {
                        cmd = CfgMgr.expectype.get(ectypeid);
                        break;
                    }
                    case EctypeType.CURRENCY: {
                        cmd = CfgMgr.currencyectype.get(ectypeid);
                        break;
                    }
                    default: {
                        cmd = CfgMgr.dailyectype.get(ectypeid);
                    }
                }
				err = FCondition.checkByReflection(roleid, cmd, 1, By.Open_Ectype, ConfigId.DAILY_ECTYPE, ectypetype);
				if(err.err())
					return error(err);

				FMap.openOnePlayerEctypeInProcedure(roleid, ectypeid);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564142;

	public int getType() {
		return 6564142;
	}

	public int ectypetype; // cfg.ectype.EctypeType

	public COpenDailyEctype() {
	}

	public COpenDailyEctype(int _ectypetype_) {
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
		if (_o1_ instanceof COpenDailyEctype) {
			COpenDailyEctype _o_ = (COpenDailyEctype)_o1_;
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

	public int compareTo(COpenDailyEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypetype - _o_.ectypetype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

