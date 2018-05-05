
package lx.gs.pet.msg;

import cfg.CfgMgr;
import cfg.active.EventNum;
import cfg.bag.BagType;
import cfg.item.ItemExperience;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.FBag;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;
import xbean.Item;
import xtable.Roleinfos;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUpgradePetLevel__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUpgradePetLevel extends __CUpgradePetLevel__ {
	@Override
	protected void process() {
		new AProcedure<CUpgradePetLevel>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid) || materialnum <= 0)
					return false;
				Item material = FBag.delItemByPosNum(roleid, BagType.ITEM, materialpos, materialnum, By.Pet_UpLevel);
				if (material == null) {
					return error(ErrorCode.PARAM_ERROR);
				}
				final xbean.Pet pet = FPet.getPetByModelId(roleid, modelid);
				final cfg.cmd.action.PetExp ecfg = (cfg.cmd.action.PetExp)(((ItemExperience) CfgMgr.itembasic.get(material.getModelid())).effect);
				FPet.addExp(roleid, pet, Math.multiplyExact(ecfg.amount, materialnum), Roleinfos.selectLevel(roleid));
                FDailyActivity.addActiveScores(roleid, EventNum.USEHUOBANDAN);
				response(new SSyncPetLevel(pet.getModelid(), pet.getLevel(), pet.getExp()));
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576416;

	public int getType() {
		return 6576416;
	}

	public int modelid;
	public int materialpos;
	public int materialnum;

	public CUpgradePetLevel() {
	}

	public CUpgradePetLevel(int _modelid_, int _materialpos_, int _materialnum_) {
		this.modelid = _modelid_;
		this.materialpos = _materialpos_;
		this.materialnum = _materialnum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(modelid);
		_os_.marshal(materialpos);
		_os_.marshal(materialnum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		modelid = _os_.unmarshal_int();
		materialpos = _os_.unmarshal_int();
		materialnum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUpgradePetLevel) {
			CUpgradePetLevel _o_ = (CUpgradePetLevel)_o1_;
			if (modelid != _o_.modelid) return false;
			if (materialpos != _o_.materialpos) return false;
			if (materialnum != _o_.materialnum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += modelid;
		_h_ += materialpos;
		_h_ += materialnum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid).append(",");
		_sb_.append(materialpos).append(",");
		_sb_.append(materialnum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUpgradePetLevel _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		_c_ = materialpos - _o_.materialpos;
		if (0 != _c_) return _c_;
		_c_ = materialnum - _o_.materialnum;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

