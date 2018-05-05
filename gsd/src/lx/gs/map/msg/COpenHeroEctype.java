
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.cmd.condition.LimitType;
import cfg.ectype.HeroEctypeMsg;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.map.FMap;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __COpenHeroEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class COpenHeroEctype extends __COpenHeroEctype__ {
	@Override
	protected void process() {
		new AProcedure<COpenHeroEctype>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if (!FMap.isInWorldOrFamily(roleid)) {
					return error(ErrorCode.CANNOT_ENTER_ECTYPE_IN_ECTYPE);
				}
				final HeroEctypeMsg ectypeMsg = CfgMgr.herosets.ectypemsg_id.get(groupid);
				ErrorCode err = FCondition.checkByReflection(roleid, ectypeMsg, 1, By.Open_Ectype, ConfigId.HEROES_ECTYPE, ectypeid);
				if (err.err()) {
					return error(err);
				}

				if (FLimit.getLimitTimes(roleid, ConfigId.HEROES_ECTYPE, 0, LimitType.DAY) >= CfgMgr.herosets.dailylimit.num) {
					return error(ErrorCode.HERO_ECTYPE_CHALLENGE_MAX);
				}

				FMap.openOnePlayerEctypeInProcedure(roleid, ectypeid, false, groupid);

				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582066;

	public int getType() {
		return 6582066;
	}

	public int groupid;
	public int ectypeid;

	public COpenHeroEctype() {
	}

	public COpenHeroEctype(int _groupid_, int _ectypeid_) {
		this.groupid = _groupid_;
		this.ectypeid = _ectypeid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(groupid);
		_os_.marshal(ectypeid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		groupid = _os_.unmarshal_int();
		ectypeid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof COpenHeroEctype) {
			COpenHeroEctype _o_ = (COpenHeroEctype)_o1_;
			if (groupid != _o_.groupid) return false;
			if (ectypeid != _o_.ectypeid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += groupid;
		_h_ += ectypeid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(groupid).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(COpenHeroEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = groupid - _o_.groupid;
		if (0 != _c_) return _c_;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

