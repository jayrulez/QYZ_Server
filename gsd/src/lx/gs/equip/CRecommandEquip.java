
package lx.gs.equip;

import cfg.bag.BagType;
import cfg.equip.MainEquip;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.BodyEquipBag;
import lx.gs.bag.EquipBag;
import lx.gs.cmd.FCondition;
import xtable.Roleinfos;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRecommandEquip__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRecommandEquip extends __CRecommandEquip__ {
	@Override
	protected void process() {
		new AProcedure<CRecommandEquip>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				EquipBag equipBag = FEquip.getEquipBag(roleid);
				BodyEquipBag bodyEquipBag = FEquip.getBodyBag(roleid);
				xbean.Equip equip = equipBag.getByPosition(pos);
				if(equip == null)
					return false;
				cfg.equip.Equip model = FEquip.getEquipModel(equip);

				if(!FEquip.isNormalEquip(model)){
					return false;
				}

				int roleLevel = Roleinfos.selectLevel(roleid);
				if(roleLevel < model.level){
					return response(new SRecommandEquip(ErrorCode.NOT_ENOUGH_LEVEL.getErrorId()));
				}
				MainEquip mainEquip = (MainEquip) model;
				ErrorCode errorCode2 = FCondition.checkA(roleid, mainEquip.professionlimit, 1, null, 0, 0);
				if(errorCode2.err()) {
					return response(new SRecommandEquip(errorCode2.getErrorId()));
				}

				int destPos = bodyEquipBag.getPosByEquipType(model.type);
				xbean.Equip oldEquip = bodyEquipBag.getByPosition(destPos);
				if(oldEquip != null){
					ErrorCode errorCode = FEquip.swapEquipProp(equip, oldEquip, true, true);
					if(errorCode.err()){
						return response(new SRecommandEquip(errorCode.getErrorId()));
					}
				}

				FEquip.bindOnEquip(roleLevel, equip, BagType.EQUIP, pos);

				bodyEquipBag.load(destPos, equipBag, pos);
				return response(new SRecommandEquip(ErrorCode.OK.getErrorId()));
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555764;

	public int getType() {
		return 6555764;
	}

	public int pos; // 装备包裹里的格子号

	public CRecommandEquip() {
	}

	public CRecommandEquip(int _pos_) {
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
		if (_o1_ instanceof CRecommandEquip) {
			CRecommandEquip _o_ = (CRecommandEquip)_o1_;
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

	public int compareTo(CRecommandEquip _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

