package lx.gs.equip.normalequip;

import cfg.CfgMgr;
import cfg.bag.BagType;
import cfg.equip.MainEquip;
import cfg.item.EItemColor;
import cfg.tips.LocationType;
import cfg.tips.TipsCode;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.BodyEquipBag;
import lx.gs.bag.EquipBag;
import lx.gs.bag.FBag;
import lx.gs.cmd.FCondition;
import lx.gs.equip.FEquip;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.tips.FTips;
import xbean.Equip;
import xbean.Item;
import xtable.Roleinfos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUpgradeEquip__ extends xio.Protocol { }

/** 神器进阶
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUpgradeEquip extends lx.gs.equip.normalequip.__CUpgradeEquip__ {
    @Override
    protected void process() {
        new AProcedure<CUpgradeEquip>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                //检查用户的级别，到一定级别才能进行该操作，有绑定的目标装备也会变成绑定，消耗的物品都需要从协议里传过来。
                int roleLevel = Roleinfos.selectLevel(roleid);
                if (roleLevel < cfg.equip.Equip.MIN_UPGRADE_LEVEL || roleLevel > cfg.equip.Equip.MAX_UPGRADE_LEVEL
                        || (bagtype1 != BagType.EQUIP && bagtype1 != BagType.EQUIP_BODY))
                    return error(ErrorCode.PARAM_ERROR);

                EquipBag equipBag = FEquip.getEquipBag(roleid);
                BodyEquipBag bodyEquipBag = FEquip.getBodyBag(roleid);

                Equip equip1 = FBag.deleteItemByPos(bagtype1 == BagType.EQUIP ? equipBag : bodyEquipBag, pos1, By.Equip_Upgrade);

                MainEquip model1 = (MainEquip) FEquip.getEquipModel(equip1);
                //检查进阶的装备是否是橙色或者红色
                if (model1.quality != EItemColor.ORANGE && model1.quality != EItemColor.RED)
                    return error(ErrorCode.EQUIP_EVOLVE_NOT_SUPPORT_TYPE);

                if (model1.nextid == 0)
                    return error(ErrorCode.EQUIP_EVOLVE_CAN_NOT_EVOLVE);

                Equip equip2 = null;
                if (model1.extraequipid != 0) {
                    if (bagtype2 != BagType.EQUIP && bagtype2 != BagType.EQUIP_BODY)
                        return error(ErrorCode.PARAM_ERROR);
                    equip2 = FBag.deleteItemByPos(bagtype2 == BagType.EQUIP ? equipBag : bodyEquipBag, pos2, By.Equip_Upgrade);

                    if (equip2.getModelid() != model1.extraequipid)
                        return error(ErrorCode.PARAM_ERROR);

                    MainEquip model2 = (MainEquip) FEquip.getEquipModel(equip2);
                    //这一步判断应该在策划配置的时候就保证正确了
                    if (model1.professionlimit != null && model2.professionlimit != null
                            && model1.professionlimit.profession != model2.professionlimit.profession)
                        return error(ErrorCode.PARAM_ERROR);
                }

                //进阶到的装备信息
                MainEquip evolveModel = (MainEquip) CfgMgr.equip.get(model1.nextid);
                //进阶消耗的物品信息
                int itemcount = evolveModel.carryingitemnum - model1.carryingitemnum;

                List<Item> costedBindItemList = new ArrayList<>();
                if (!FItem.spendItemBindFirst(roleid, cfg.equip.Equip.UPGRADE_COST_ITEM, itemcount, costedBindItemList, By.Equip_Upgrade))
                    return error(ErrorCode.EQUIP_EVOLVE_COST_ITEM_NOT_ENOUGH);

                //进阶需要消耗一些虚拟币
                ErrorCode errorCode = FCondition.checkA(roleid, evolveModel.upgradecurrencycost, 1, By.Equip_Upgrade, 0, 0);
                if (errorCode.err())
                    return error(errorCode);

                boolean isNewEquipBind = !costedBindItemList.isEmpty() || equip1.getIsbind() || (equip2 != null && equip2.getIsbind());
                costedBindItemList.clear();

                //把当前装备换成新的装备,炼器等级和追加等级保留
                xbean.Equip newEquip = FEquip.createEquip(roleid, evolveModel.id, isNewEquipBind);
                int anneallevel = Math.max(equip1.getNormalequip().getAnneallevel(), equip2 == null ? 0 : equip2.getNormalequip().getAnneallevel());
                int perfuselevel = Math.max(equip1.getNormalequip().getPerfuselevel(), equip2 == null ? 0 : equip2.getNormalequip().getPerfuselevel());
                newEquip.getNormalequip().setAnneallevel(anneallevel);
                newEquip.getNormalequip().setPerfuselevel(perfuselevel);

				if(!FBag.addItemToBag(equipBag, Arrays.asList(newEquip), By.Equip_Upgrade)){
					return error(ErrorCode.BAG_FULL);
				}
                if (bagtype1 == BagType.EQUIP_BODY || bagtype2 == BagType.EQUIP_BODY) {
                    int newPos = bagtype1 == BagType.EQUIP_BODY ? pos1 : pos2;
                    bodyEquipBag.load(newPos, equipBag, newEquip.getPosition());
				}
                if(evolveModel.quality == EItemColor.RED){
                    FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.MYSTERY_EQUIP_BROADCAST, Roleinfos.selectName(roleid), evolveModel.name);
                }
				response(new SUpgradeEquip(bagtype1, pos1, bagtype2, pos2));
                return true;
            }
        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570203;

	public int getType() {
		return 6570203;
	}

	public int bagtype1; // 包裹类型
	public int pos1; // 格子号
	public int bagtype2; // 包裹类型
	public int pos2; // 格子号

	public CUpgradeEquip() {
	}

	public CUpgradeEquip(int _bagtype1_, int _pos1_, int _bagtype2_, int _pos2_) {
		this.bagtype1 = _bagtype1_;
		this.pos1 = _pos1_;
		this.bagtype2 = _bagtype2_;
		this.pos2 = _pos2_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype1);
		_os_.marshal(pos1);
		_os_.marshal(bagtype2);
		_os_.marshal(pos2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype1 = _os_.unmarshal_int();
		pos1 = _os_.unmarshal_int();
		bagtype2 = _os_.unmarshal_int();
		pos2 = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUpgradeEquip) {
			CUpgradeEquip _o_ = (CUpgradeEquip)_o1_;
			if (bagtype1 != _o_.bagtype1) return false;
			if (pos1 != _o_.pos1) return false;
			if (bagtype2 != _o_.bagtype2) return false;
			if (pos2 != _o_.pos2) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype1;
		_h_ += pos1;
		_h_ += bagtype2;
		_h_ += pos2;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype1).append(",");
		_sb_.append(pos1).append(",");
		_sb_.append(bagtype2).append(",");
		_sb_.append(pos2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUpgradeEquip _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype1 - _o_.bagtype1;
		if (0 != _c_) return _c_;
		_c_ = pos1 - _o_.pos1;
		if (0 != _c_) return _c_;
		_c_ = bagtype2 - _o_.bagtype2;
		if (0 != _c_) return _c_;
		_c_ = pos2 - _o_.pos2;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

