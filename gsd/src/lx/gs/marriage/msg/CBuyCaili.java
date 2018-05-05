
package lx.gs.marriage.msg;

import cfg.CfgMgr;
import cfg.currency.CurrencyType;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.marriage.FMarriage;
import lx.gs.role.FRole;

import java.util.HashMap;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBuyCaili__ extends xio.Protocol { }

/** 购买彩礼
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBuyCaili extends __CBuyCaili__ {
	@Override
	protected void process() {
		new AProcedure<CBuyCaili>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				final int type = FMarriage.cailiType(roleid);//先判断已拥有的彩礼类型
				ErrorCode result = ErrorCode.OK;
                int needYuanbao;
				if(cailitypeid == FMarriage.NORMAL_CAILI){//如果想购买普通彩礼，只要背包中有彩礼就不能再购买了
					if(type != 0){
						return error(ErrorCode.ALREADY_HAVE_CAILI);
					}
                    needYuanbao = CfgMgr.marrigeconfig.marrigepack.get(0).marrigepackprice.amount;
					result = FCondition.checkA(roleid, CfgMgr.marrigeconfig.marrigepack.get(0).marrigepackprice, 1, By.Marry_Buy, 0, 0);
				}else if (cailitypeid == FMarriage.LUXURY_CAILI) {//如果购买豪华彩礼，如果背包中存在普通彩礼，那么可以补差价升级
					if(type == FMarriage.LUXURY_CAILI){
						return error(ErrorCode.ALREADY_HAVE_CAILI);
					}else if(type == FMarriage.NORMAL_CAILI){
						needYuanbao = CfgMgr.marrigeconfig.marrigepack.get(1).marrigepackprice.amount - CfgMgr.marrigeconfig.marrigepack.get(0).marrigepackprice.amount;
						if(!FRole.checkCostCurrency(roleid, CurrencyType.YuanBao, needYuanbao, By.Marry_Buy)){
							result = ErrorCode.YUANBAO_NOT_ENOUGH;
						}
                        FItem.spendItemBindFirst(roleid, FMarriage.NORMAL_CAILI, 1, By.Marry);
					}else {
                        needYuanbao = CfgMgr.marrigeconfig.marrigepack.get(1).marrigepackprice.amount;
						result = FCondition.checkA(roleid, CfgMgr.marrigeconfig.marrigepack.get(1).marrigepackprice, 1, By.Marry_Buy, 0, 0);
					}
				}else {
					return error(ErrorCode.PARAM_ERROR);
				}
				if(result.err()){//如果购买失败
					return error(result);
				}
				HashMap<Integer,Integer> content = new HashMap<>();
				content.put(cailitypeid, 1);
				if(!FBonus.addBonus(roleid, common.Bonus.BindType.BIND, content, By.Marry_Buy)){//添加彩礼到背包中
                    return false;
                }
                FLogger.shop_trade(roleid, FBonus.getRoleInfo(roleid), cailitypeid, 1, CurrencyType.YuanBao, needYuanbao);
				SBuyCaili response = new SBuyCaili();
				response.cailitypeid = cailitypeid;
				response(response);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566522;

	public int getType() {
		return 6566522;
	}

	public int cailitypeid; // 购买的彩礼类型

	public CBuyCaili() {
	}

	public CBuyCaili(int _cailitypeid_) {
		this.cailitypeid = _cailitypeid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(cailitypeid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		cailitypeid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBuyCaili) {
			CBuyCaili _o_ = (CBuyCaili)_o1_;
			if (cailitypeid != _o_.cailitypeid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += cailitypeid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(cailitypeid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CBuyCaili _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = cailitypeid - _o_.cailitypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

