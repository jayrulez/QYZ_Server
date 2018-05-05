
package lx.gs.equip.normalequip;

import cfg.Const;
import cfg.achievement.CounterType;
import cfg.active.EventNum;
import cfg.equip.AnnealCost;
import cfg.equip.AnnealRate;
import cfg.equip.Equip;
import cfg.equip.MainEquip;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import common.Utils;
import gs.AProcedure;
import lx.gs.achievement.FAchievement;
import lx.gs.activity.FActivity;
import lx.gs.bag.FBag;
import lx.gs.bag.ItemBag;
import lx.gs.bag.msg.SItemBind;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.equip.FEquip;
import lx.gs.event.EquipAnnealEvent;
import lx.gs.event.EventModule;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import xbean.Item;
import xbean.NormalEquip;
import xdb.Trace;
import xdb.Transaction;
import xtable.Roleinfos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAnnealEquip__ extends xio.Protocol { }

/** 炼器强化装备
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAnnealEquip extends lx.gs.equip.normalequip.__CAnnealEquip__ {
	@Override
	@SuppressWarnings("unchecked")
	protected void process() {
		new AProcedure<CAnnealEquip>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				xbean.Equip equip = FEquip.getEquip(roleid, bagtype, pos);
				Equip model = FEquip.getEquipModel(equip.getModelid());
				if(!(model instanceof MainEquip)){
					return error(ErrorCode.PARAM_ERROR);
				}

				NormalEquip normalEquip = equip.getNormalequip();
				int nextLevel = normalEquip.getAnneallevel() + 1;

				if(nextLevel > FEquip.getMaxAnnealLevel(model)){
					return error(ErrorCode.EQUIP_ENHANCE_STRENGTHEN_LIMIT);
				}

				boolean useHelpItem = usewanbifu == Const.TRUE;
				boolean unBind = unbindonly == Const.TRUE;
				List<Item> costBindItemList = new ArrayList<>();

				AnnealCost annealCost = cfg.CfgMgr.annealcost.get(model.type);
				AnnealRate annealRate = cfg.CfgMgr.annealrate.get(nextLevel);
				int costItemCount = annealCost.itemcost.get(nextLevel - 1);
				int costGoldCount = annealCost.expenses.get(nextLevel - 1);
				int successRate = annealRate.rate;
				int reduceLevel = cfg.equip.EquipAnneal.FAIL_REDUCE_NUMBER[nextLevel - 1];

				if (!FRole.checkCostXuNiBi(roleid, Roleinfos.get(roleid), costGoldCount, By.Equip_Anneal)) {
					return error(ErrorCode.XUNIBI_NOT_ENOUGH);
				}

				ItemBag itemBag = FItem.getItemBag(roleid);
				if(unBind){
					if(!FBag.deleteUnBind(itemBag, AnnealCost.COST_ITEM_ID, costItemCount, By.Equip_Anneal)){
						return error(ErrorCode.XUNIBI_NOT_ENOUGH);
					}
				} else {
					if(!FBag.deleteBindFirst(itemBag, AnnealCost.COST_ITEM_ID, costItemCount, costBindItemList, By.Equip_Anneal)){
						return error(ErrorCode.ITEM_NUMBER_NOT_ENOUGH);
					}
				}


				if(useHelpItem){
					int helpItemModelId = helpitemmodelid;
					cfg.equip.AnnealItemUseLimit limitConf = cfg.CfgMgr.annealitemuselimit.get(helpItemModelId);
					cfg.equip.AnnealItemEffect itemEffect = cfg.CfgMgr.annealitemeffect.get(helpItemModelId);
					cfg.equip.AnnealItemCost itemCost = cfg.CfgMgr.annealitemcost.get(helpItemModelId);

					if(!limitConf.uselimit.get(nextLevel - 1)){
						Trace.warn("client send wrong parameter");
						return error(ErrorCode.PARAM_ERROR);
					}
					successRate += itemEffect.effect.get(nextLevel - 1);
					reduceLevel = Math.max(0, reduceLevel - itemEffect.lostcontrol);
					int costCount = itemCost.itemcost.get(nextLevel - 1);

					if(unBind){
						if(!FBag.deleteUnBind(itemBag, helpItemModelId, costCount, By.Equip_Anneal)){
							return error(ErrorCode.ITEM_NUMBER_NOT_ENOUGH);
						}
					} else {
						if(!FBag.deleteBindFirst(itemBag, helpItemModelId, costCount, costBindItemList, By.Equip_Anneal)){
							return error(ErrorCode.ITEM_NUMBER_NOT_ENOUGH);
						}
					}
				}

				int index = Utils.getRandomIndex(Arrays.asList(Math.max(AnnealRate.RATE_BASE_NUMBER - successRate, 0), successRate));
				if(index == 1){
					//炼器成功
					normalEquip.setAnneallevel(nextLevel);
					FAchievement.updateCounter(roleid, CounterType.MAX_ANNEAL_EQUIP, nextLevel);
                    FActivity.checkEquipAnnel(roleid, bagtype);
					EventModule.INSTANCE.broadcastEvent(new EquipAnnealEvent(roleid, model.id, nextLevel));
					if(!costBindItemList.isEmpty() && !equip.getIsbind()){
						equip.setIsbind(true);
						Transaction.tsendWhileCommit(roleid, new SItemBind(bagtype, pos));
						costBindItemList.clear();
					}
				} else {
					//炼器失败，失败要不要掉级别?五级一下不掉级，>5时，失败掉2级
					if(nextLevel > cfg.equip.EquipAnneal.FAIL_REDUCE_BASELEVEL){
						normalEquip.setAnneallevel(Math.max(0, normalEquip.getAnneallevel() - reduceLevel));
					}
				}
                FDailyActivity.addActiveScores(roleid, EventNum.ANNEAL);
				SAnnealEquip result = new SAnnealEquip();
				result.pos = pos;
				result.bagtype = bagtype;
				result.newlevel = normalEquip.getAnneallevel();
				return response(result);
			}
		}.validateRoleidAndExecute();	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579722;

	public int getType() {
		return 6579722;
	}

	public int bagtype;
	public int pos;
	public int unbindonly; // 是否只用非绑定的
	public int usewanbifu; // 是否使用完毕符
	public int helpitemmodelid; // 辅助物品的mdoel id

	public CAnnealEquip() {
	}

	public CAnnealEquip(int _bagtype_, int _pos_, int _unbindonly_, int _usewanbifu_, int _helpitemmodelid_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.unbindonly = _unbindonly_;
		this.usewanbifu = _usewanbifu_;
		this.helpitemmodelid = _helpitemmodelid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(unbindonly);
		_os_.marshal(usewanbifu);
		_os_.marshal(helpitemmodelid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		unbindonly = _os_.unmarshal_int();
		usewanbifu = _os_.unmarshal_int();
		helpitemmodelid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAnnealEquip) {
			CAnnealEquip _o_ = (CAnnealEquip)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (unbindonly != _o_.unbindonly) return false;
			if (usewanbifu != _o_.usewanbifu) return false;
			if (helpitemmodelid != _o_.helpitemmodelid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += unbindonly;
		_h_ += usewanbifu;
		_h_ += helpitemmodelid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(unbindonly).append(",");
		_sb_.append(usewanbifu).append(",");
		_sb_.append(helpitemmodelid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAnnealEquip _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = unbindonly - _o_.unbindonly;
		if (0 != _c_) return _c_;
		_c_ = usewanbifu - _o_.usewanbifu;
		if (0 != _c_) return _c_;
		_c_ = helpitemmodelid - _o_.helpitemmodelid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

