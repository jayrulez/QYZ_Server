
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import common.RefObject;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __COpenPersonalBossEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class COpenPersonalBossEctype extends __COpenPersonalBossEctype__ {
	@Override
	protected void process() {
		new AProcedure<COpenPersonalBossEctype>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				if (!FMap.isInWorldOrFamily(roleid))
					return error(ErrorCode.CANNOT_ENTER_ECTYPE_IN_ECTYPE);
				final cfg.ectype.PersonalBoss pcfg = CfgMgr.personalboss.get(ectypeid);
				final RefObject<ErrorCode> check = new RefObject<>(ErrorCode.INVALID_ECTYPEID);
				new xdb.Procedure() {
					@Override
					protected boolean process() {
						check.value = FCondition.checkByReflection(roleid, pcfg, 1, By.Open_Ectype, ConfigId.PERSONAL_BOSS_ECTYPE, ectypeid);
						// 策划要求挑战不扣次数,只能成功时扣次数,那只好这样子了
						return false;
					}
				}.call();
				if(check.value.err())
					return error(check.value);

				FMap.openOnePlayerEctypeInProcedure(roleid, ectypeid);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566531;

	public int getType() {
		return 6566531;
	}

	public int ectypeid;

	public COpenPersonalBossEctype() {
	}

	public COpenPersonalBossEctype(int _ectypeid_) {
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
		if (_o1_ instanceof COpenPersonalBossEctype) {
			COpenPersonalBossEctype _o_ = (COpenPersonalBossEctype)_o1_;
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

	public int compareTo(COpenPersonalBossEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

