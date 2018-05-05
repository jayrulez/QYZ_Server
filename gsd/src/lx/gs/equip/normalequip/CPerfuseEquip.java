
package lx.gs.equip.normalequip;

import cfg.CfgMgr;
import cfg.Const;
import cfg.achievement.CounterType;
import cfg.active.EventNum;
import cfg.equip.AppendCost;
import cfg.equip.MainEquip;
import cfg.item.EItemColor;
import cfg.tips.LocationType;
import cfg.tips.TipsCode;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.achievement.FAchievement;
import lx.gs.activity.FActivity;
import lx.gs.bag.AbstractBag;
import lx.gs.bag.FBag;
import lx.gs.bag.ItemBag;
import lx.gs.bag.msg.SItemBind;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.equip.FEquip;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import lx.gs.tips.FTips;
import xbean.Equip;
import xbean.Item;
import xbean.NormalEquip;
import xdb.Transaction;
import xtable.Roleinfos;

import java.util.ArrayList;
import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CPerfuseEquip__ extends xio.Protocol { }

/** 装备灌注
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CPerfuseEquip extends lx.gs.equip.normalequip.__CPerfuseEquip__ {
    @Override
    protected void process() {
        new AProcedure<CPerfuseEquip>(this) {
            @Override
            protected boolean doProcess() throws Exception {
				AbstractBag<Equip> equipBag = FBag.getBag(roleid, bagtype);
				xbean.Equip equip = equipBag.getByPosition(pos);
				cfg.equip.Equip model = FEquip.getEquipModel(equip);
				if(!(model instanceof MainEquip)){
					return error(ErrorCode.PARAM_ERROR);
				}
				NormalEquip normalEquip = equip.getNormalequip();
				int nextLevel = normalEquip.getPerfuselevel() + 1;

				if(nextLevel > FEquip.getMaxPerfuseLevel(model)){
					return error(ErrorCode.EQUIP_PERFUSE_MAX_LEVEL);
				}

				//追加后的属性值的增加由其他模块计算
				AppendCost appendCost = cfg.CfgMgr.appendcost.get(model.type);

                int costitemcount = appendCost.itemcost.get(nextLevel - 1);
				int goldCount = appendCost.expenses.get(nextLevel - 1);

				if (!FRole.checkCostXuNiBi(roleid, Roleinfos.get(roleid), goldCount, By.Equip_Perfuse)) {
					return error(ErrorCode.XUNIBI_NOT_ENOUGH);
				}
                List<Item> costBindItemList = new ArrayList<>();

				ItemBag itemBag = FItem.getItemBag(roleid);
				if(unbindonly == Const.TRUE){
					if(!FBag.deleteUnBind(itemBag, AppendCost.COST_ITEM_ID, costitemcount, By.Equip_Perfuse)){
						return error(ErrorCode.EQUIP_APPEND_NOT_ENOUGH_ITEM);
					}
				} else {
					if(!FBag.deleteBindFirst(itemBag, AppendCost.COST_ITEM_ID, costitemcount, costBindItemList, By.Equip_Perfuse)){
						return error(ErrorCode.EQUIP_APPEND_NOT_ENOUGH_ITEM);
					}
				}
                if (!costBindItemList.isEmpty() && !equip.getIsbind()) {
					equip.setIsbind(true);
					Transaction.tsendWhileCommit(roleid, new SItemBind(bagtype, pos));
					costBindItemList.clear();
                }

				normalEquip.setGoldcost(normalEquip.getGoldcost() + goldCount);
                normalEquip.setPerfuseitemcost(normalEquip.getPerfuseitemcost() + costitemcount);
                normalEquip.setPerfuselevel(nextLevel);

                FAchievement.updateCounter(roleid, CounterType.MAX_PERFUSE_EQUIP, nextLevel);
                FActivity.checkEquipPerfuse(roleid, bagtype);
                if(CfgMgr.broadcastcfg.perfuse.contains(nextLevel)){
					if(model.quality == EItemColor.RED){
						FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.PERFUSE_UP_RED_BROADCAST, Roleinfos.selectName(roleid), model.name, nextLevel + "");
					}
					if(model.quality == EItemColor.ORANGE){
						FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.PERFUSE_UP_ORANGE_BROADCAST, Roleinfos.selectName(roleid), model.name, nextLevel + "");
					}
				}
                FDailyActivity.addActiveScores(roleid, EventNum.PERFUSE);
                SPerfuseInfoUpdate result = new SPerfuseInfoUpdate();
                result.bagtype = param.bagtype;
                result.pos = param.pos;
                result.newlevel = nextLevel;
                return response(result);
            }
        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582031;

	public int getType() {
		return 6582031;
	}

	public int bagtype;
	public int pos;
	public int unbindonly; // 是否只用非绑定的

	public CPerfuseEquip() {
	}

	public CPerfuseEquip(int _bagtype_, int _pos_, int _unbindonly_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.unbindonly = _unbindonly_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(unbindonly);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		unbindonly = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CPerfuseEquip) {
			CPerfuseEquip _o_ = (CPerfuseEquip)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (unbindonly != _o_.unbindonly) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += unbindonly;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(unbindonly).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CPerfuseEquip _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = unbindonly - _o_.unbindonly;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

