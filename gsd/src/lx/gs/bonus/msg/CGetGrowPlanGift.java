
package lx.gs.bonus.msg;

import cfg.bonus.GrowPlan;
import cfg.item.EItemBindType;
import cfg.pay.GrowPlanType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import common.ErrorCode;
import lx.gs.logger.By;

import java.util.List;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetGrowPlanGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetGrowPlanGift extends __CGetGrowPlanGift__ {
	@Override
	protected void process() {
		new AProcedure<CGetGrowPlanGift>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
				xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
                if(roleinfo.getBuygrowplan() != growplantype){
                    return error(ErrorCode.WRONG_GROW_PLAN_TYPE);
                }
                int requireday = 0;
                List<Integer> target;
                int buyDay;
                if(growplantype == GrowPlanType.FIRSTTYPE && giftindx <= GrowPlan.FIRST_GROW_PLAN_END_INDEX){
                    target = welfare.getThreegrowplan();
                    requireday = giftindx;
                    buyDay = roleinfo.getGrowonetime();
                }else if(growplantype == GrowPlanType.SECONDTYPE && giftindx >= GrowPlan.SECOND_GROW_PLAN_START_INDEX && giftindx <= GrowPlan.SECOND_GROW_PLAN_END_INDEX){
                    target = welfare.getFivegrowplan();
                    requireday = giftindx - GrowPlan.FIRST_GROW_PLAN_END_INDEX;
                    buyDay = roleinfo.getGrowtwotime();
                }else if(growplantype == GrowPlanType.THIRDTYPE && giftindx >= GrowPlan.THIRD_GROW_PLAN_START_INDEX && giftindx <= GrowPlan.THIRD_GROW_PLAN_END_INDEX){
                    target = welfare.getSevengrowplan();
                    requireday = giftindx - GrowPlan.SECOND_GROW_PLAN_END_INDEX;
                    buyDay = roleinfo.getGrowthreetime();
                }else {
                    return error(ErrorCode.PARAM_ERROR);
                }
//                if(requireday > roleinfo.getLogindaycount()){
//                    return error(ErrorCode.NOT_ENOUGH_LOGIN_DAYS);
//                }
                if(requireday - 1 > roleinfo.getLogindaycount() - buyDay){
                    return error(ErrorCode.NOT_ENOUGH_LOGIN_DAYS);
                }
				if(target.contains(giftindx)){
					return error(ErrorCode.HAS_RECEIVED);
				}
				target.add(giftindx);
				SGetGrwoPlanGift response = new SGetGrwoPlanGift();
				response.growplantype = growplantype;
                response.giftindx = giftindx;
				boolean result = FBonus.addBonus(roleid, CfgMgr.growplan.get(giftindx).bonuslist, common.Bonus.BindType.BIND, By.Grow_Plan);
				if(result) response(response);
				return result;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556120;

	public int getType() {
		return 6556120;
	}

	public int growplantype;
	public int giftindx;

	public CGetGrowPlanGift() {
	}

	public CGetGrowPlanGift(int _growplantype_, int _giftindx_) {
		this.growplantype = _growplantype_;
		this.giftindx = _giftindx_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(growplantype);
		_os_.marshal(giftindx);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		growplantype = _os_.unmarshal_int();
		giftindx = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetGrowPlanGift) {
			CGetGrowPlanGift _o_ = (CGetGrowPlanGift)_o1_;
			if (growplantype != _o_.growplantype) return false;
			if (giftindx != _o_.giftindx) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += growplantype;
		_h_ += giftindx;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(growplantype).append(",");
		_sb_.append(giftindx).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetGrowPlanGift _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = growplantype - _o_.growplantype;
		if (0 != _c_) return _c_;
		_c_ = giftindx - _o_.giftindx;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

