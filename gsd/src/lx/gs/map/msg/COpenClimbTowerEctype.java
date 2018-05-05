
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __COpenClimbTowerEctype__ extends xio.Protocol { }

/** 爬塔副本
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class COpenClimbTowerEctype extends __COpenClimbTowerEctype__ {
	@Override
	protected void process() {
		new AProcedure<COpenClimbTowerEctype>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				if (!FMap.isInWorldOrFamily(roleid))
					return error(ErrorCode.CANNOT_ENTER_ECTYPE_IN_ECTYPE);
				final cfg.ectype.ClimbTowerEctype ecfg = CfgMgr.climbtowerectype.get(ectypeid);
				ErrorCode err = FCondition.checkByReflection(roleid, ecfg, 1, By.Open_Ectype, ConfigId.CLIMB_TOWER_ECTYPE, ectypeid);
				if(err.err()) {
					return error(err);
				}

				final xbean.RoleEctype info = FMap.getEctype(roleid);
				final xbean.ClimbTowerInfo towerInfo = info.getClimbtowers().get(ectypeid);
				FMap.openOnePlayerEctypeInProcedure(roleid, ectypeid, false, towerInfo != null ? towerInfo.getMaxfloorid() : 0);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575229;

	public int getType() {
		return 6575229;
	}

	public int ectypeid;

	public COpenClimbTowerEctype() {
	}

	public COpenClimbTowerEctype(int _ectypeid_) {
		this.ectypeid = _ectypeid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof COpenClimbTowerEctype) {
			COpenClimbTowerEctype _o_ = (COpenClimbTowerEctype)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(COpenClimbTowerEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

