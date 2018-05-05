
package lx.gs.bonus.msg;

import cfg.CfgMgr;
import cfg.bonus.BONUSTYPE;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CContinueLoginGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CContinueLoginGift extends __CContinueLoginGift__ {
	@Override
	protected void process() {
		// protocol handle
		new AProcedure<CContinueLoginGift>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
                xbean.RoleWelfareInfo welfareInfo = FBonus.get(roleid);
				if (roleinfo.getLeftgifttimes() <= 0) {
					return error(ErrorCode.NO_LEFT_TIME);
				}
                if(welfareInfo.getReceiveconlogingift().contains(boxid)){
                    return error(ErrorCode.HAS_RECEIVE_BONUS);
                }
				roleinfo.setLeftgifttimes(roleinfo.getLeftgifttimes() - 1);
                welfareInfo.getReceiveconlogingift().add(boxid);
                FLogger.welfare(roleid, roleinfo, BONUSTYPE.CONTINUE_LOGIN, boxid);
				SContinueLoginGift response = new SContinueLoginGift();
				response.boxid = boxid;
				response.lefttimes = roleinfo.getLeftgifttimes();
				response(response);
				return FBonus.genAndAddBonus(roleid, CfgMgr.continuityloginbonus.bonuslist, common.Bonus.BindType.BIND, response.logingift,
						By.Continue_Login);
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556112;

	public int getType() {
		return 6556112;
	}

	public int boxid;

	public CContinueLoginGift() {
	}

	public CContinueLoginGift(int _boxid_) {
		this.boxid = _boxid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(boxid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		boxid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CContinueLoginGift) {
			CContinueLoginGift _o_ = (CContinueLoginGift)_o1_;
			if (boxid != _o_.boxid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += boxid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(boxid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CContinueLoginGift _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = boxid - _o_.boxid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
