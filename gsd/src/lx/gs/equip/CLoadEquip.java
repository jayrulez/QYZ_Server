
package lx.gs.equip;

import cfg.bag.BagType;
import cfg.equip.MainEquip;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.activity.FActivity;
import lx.gs.bag.BodyEquipBag;
import lx.gs.bag.EquipBag;
import lx.gs.cmd.FCondition;
import xtable.Roleinfos;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CLoadEquip__ extends xio.Protocol { }

/** 从包裹装载装备到身上
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CLoadEquip extends __CLoadEquip__ {
	@Override
	protected void process() {
		new AProcedure<CLoadEquip>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				EquipBag equipBag = FEquip.getEquipBag(roleid);
				BodyEquipBag bodyEquipBag = FEquip.getBodyBag(roleid);
				xbean.Equip equip = equipBag.getByPosition(pos);

				cfg.equip.Equip model = FEquip.getEquipModel(equip);
				int roleLevel = Roleinfos.selectLevel(roleid);
				if(roleLevel < model.level){
					return error(ErrorCode.NOT_ENOUGH_LEVEL);
				}
				if(FEquip.isNormalEquip(model)){
					MainEquip mainEquip = (MainEquip) model;
					ErrorCode errorCode = FCondition.checkA(roleid, mainEquip.professionlimit, 1, null, 0, 0);
					if(errorCode.err()) {
						return error(errorCode);
					}
				}
				int destPos = bodyEquipBag.getPosByEquipType(model.type);
				FEquip.bindOnEquip(roleid, equip, BagType.EQUIP_BODY, destPos);
				boolean result = bodyEquipBag.load(destPos, equipBag, pos);
                FActivity.checkEuipQuality(roleid);//check equip quality in body
                FActivity.checkEquipAnnel(roleid, BagType.EQUIP_BODY);//check equip anneal and perfuse in body
                FActivity.checkEquipPerfuse(roleid, BagType.EQUIP_BODY);
                return result;
			}
		}.validateRoleidAndExecute();	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584502;

	public int getType() {
		return 6584502;
	}

	public int pos; // 格子号

	public CLoadEquip() {
	}

	public CLoadEquip(int _pos_) {
		this.pos = _pos_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(pos);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		pos = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CLoadEquip) {
			CLoadEquip _o_ = (CLoadEquip)_o1_;
			if (pos != _o_.pos) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += pos;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CLoadEquip _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

