
package lx.gs.bonus.msg;

import cfg.CfgMgr;
import cfg.bonus.BONUSTYPE;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;

import java.util.List;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSign__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSign extends __CSign__ {
	@Override
	protected void process() {
		new AProcedure<CSign>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
				xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
				List<Integer> signDateList = welfare.getSigndate();
                if(welfare.getHastodaysign() == 1){
                    return error(ErrorCode.HAS_SIGNED);
                }
                int toSigndate = signDateList.size() + 1;
                welfare.setHastodaysign(1);
                signDateList.add(toSigndate);

				SSign response = new SSign();
				response.signtype = signtype;
				response.signdate = toSigndate;
                cfg.bonus.MonthBonus conf = CfgMgr.monthbonus.get(toSigndate);
				FBonus.genBonus(roleid, conf.bonuslist, 1, response.signgift);
				if (roleinfo.getViplevel() >= conf.requireviplevel.level) {
					FBonus.mutiBonus(response.signgift,2); //vip双倍奖励
				}
                FLogger.welfare(roleid, roleinfo, BONUSTYPE.MONTH_SIGN, toSigndate);
				boolean result = FBonus.addBonus(roleid, response.signgift, By.Signin);
				if(result) response(response);
				return result;
			}
		}.validateRoleidAndExecute();

	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556109;

	public int getType() {
		return 6556109;
	}

	public int signtype; // 0，正常签到；1，补充签到
	public int signdate;

	public CSign() {
	}

	public CSign(int _signtype_, int _signdate_) {
		this.signtype = _signtype_;
		this.signdate = _signdate_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(signtype);
		_os_.marshal(signdate);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		signtype = _os_.unmarshal_int();
		signdate = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSign) {
			CSign _o_ = (CSign)_o1_;
			if (signtype != _o_.signtype) return false;
			if (signdate != _o_.signdate) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += signtype;
		_h_ += signdate;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(signtype).append(",");
		_sb_.append(signdate).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSign _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = signtype - _o_.signtype;
		if (0 != _c_) return _c_;
		_c_ = signdate - _o_.signdate;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
