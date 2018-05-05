
package lx.gs.activity.huiwu.msg;

import cfg.CfgMgr;
import cfg.huiwu.Stage;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.activity.huiwu.FHuiWu;
import lx.gs.bonus.FBonus;
import common.ErrorCode;
import lx.gs.logger.By;

import java.util.Set;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEnroll__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEnroll extends __CEnroll__ {
	@Override
	protected void process() {
		new AProcedure<CEnroll>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
				FHuiWu.checkAttend(roleid);
				if(info.getStage() != Stage.BEGIN_ENROLL)
					return error(ErrorCode.HUIWU_ENROLL_END);
                final int profession = xtable.Roleinfos.selectProfession(roleid);
				final Set<Long> enrollRoleids = info.getTerminfobyprofession().get(profession).getEnrollroleids();
				if(!enrollRoleids.add(roleid))
					return error(ErrorCode.HUIWU_ALREAY_ENROLL);

				final SEnroll re = new SEnroll();
				response(re);
				return FBonus.genAndAddBonus(roleid, CfgMgr.huiwu.enrollaward, common.Bonus.BindType.BIND, re.bonus, By.HuiWu_Enroll);
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581372;

	public int getType() {
		return 6581372;
	}


	public CEnroll() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEnroll) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEnroll _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

