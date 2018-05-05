
package lx.gs.exchange.msg;

import cfg.CfgMgr;
import cfg.bag.BagType;
import cfg.exchange.Const;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.FBag;
import lx.gs.bag.StorageBag;
import lx.gs.equip.FEquip;
import lx.gs.exchange.FExchange;
import lx.gs.logger.By;
import xbean.*;
import xbean.ExchangeItem;
import xtable.Exchangeitem;

import java.lang.System;
import java.util.ArrayList;
import java.util.List;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAddItem__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAddItem extends __CAddItem__ {
	@Override
	@SuppressWarnings("unchecked")
	protected void process() {
		new AProcedure<CAddItem>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final int aprice = param.aprice;
				if(aprice <= 1)
					return error(ErrorCode.INVALID_PRICE);
				final RoleExchange info = FExchange.get(roleid);
				final List<Long> items = info.getItems();
				if(items.size() >= Const.MAX_EXCHANGE_ITEM_NUM)
					return error(ErrorCode.EXCEED_MAX_EXCHANGE_ITEM_NUM);

				StorageBag storageBag = FBag.getStorageBag(roleid, bagtype);
				Object item = FBag.delItemByPosNum(storageBag, pos, num, By.Exchange_Add);
				if(item == null || storageBag.isBind(item)) {
					return false;
				}
				if(!CfgMgr.exchange.containsKey(storageBag.getModelId(item))){
					return error(ErrorCode.ITEM_CAN_NOT_TRADE);
				}
				final ExchangeItem ei = Pod.newExchangeItem();
				ei.setOwner(roleid);
				ei.setPrice(aprice);
				ei.setNum(num);
				ei.setModelid(storageBag.getModelId(item));
				ei.setExpiretime(storageBag.getExpiretime(item));
                ei.setUnshelvetime(System.currentTimeMillis() + Const.EXCHANGE_UNSHELVE_TIME * 1000L);
				switch(bagtype) {
					case BagType.EQUIP: {
						Equip equip = (Equip) item;
						if(FEquip.isAccessory(equip)){
							List<AccessoryProp> tempMain = new ArrayList<>(equip.getAccessory().getMainprop());
							List<AccessoryProp> tempExtra = new ArrayList<>(equip.getAccessory().getExtraprop());
							equip.getAccessory().getMainprop().clear();
							equip.getAccessory().getExtraprop().clear();
							ei.getAccessorymainprop().addAll(tempMain);
							ei.getAccessoryviceprop().addAll(tempExtra);
						} else if(FEquip.isNormalEquip(equip)){
							ei.setAnneallevel(equip.getNormalequip().getAnneallevel());
							ei.setPerfuselevel(equip.getNormalequip().getPerfuselevel());
						}
						break;
					}
					default : break;
				}

				long id = Exchangeitem.insert(ei);
				ei.setId(id);
				items.add(id);
				response(new SAddItem(FExchange.convert(ei)));

				FExchange.addItem(ei.toData());
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557602;

	public int getType() {
		return 6557602;
	}

	public int bagtype; // cfg.role.BagType
	public int pos;
	public int num;
	public int aprice; // 单价

	public CAddItem() {
	}

	public CAddItem(int _bagtype_, int _pos_, int _num_, int _aprice_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.num = _num_;
		this.aprice = _aprice_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(num);
		_os_.marshal(aprice);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		num = _os_.unmarshal_int();
		aprice = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAddItem) {
			CAddItem _o_ = (CAddItem)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (num != _o_.num) return false;
			if (aprice != _o_.aprice) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += num;
		_h_ += aprice;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(num).append(",");
		_sb_.append(aprice).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAddItem _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = num - _o_.num;
		if (0 != _c_) return _c_;
		_c_ = aprice - _o_.aprice;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

