
package lx.gs.pet.msg;

import cfg.CfgMgr;
import cfg.currency.CurrencyType;
import cfg.item.EItemType;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.pet.FPet;

import java.util.Set;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBuyPetSkin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBuyPetSkin extends __CBuyPetSkin__ {
	@Override
	protected void process() {
		new AProcedure<CBuyPetSkin>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final xbean.Pet pet = FPet.getPetByModelId(roleid, modelid);
				final Set<Integer> buySkins = pet.getSkinidlist();
				if(buySkins.contains(skinid))
					return false;
				final cfg.pet.PetSkin pcfg = CfgMgr.petskin.get(skinid);
				final ErrorCode err = FCondition.checkByReflection(roleid, pcfg, By.Pet_BuySkin);
				if(err.err())
					return error(err);
				buySkins.add(Integer.valueOf(skinid));
                FLogger.shop_trade(roleid, FBonus.getRoleInfo(roleid), skinid, 1, CurrencyType.YuanBao, pcfg.price.amount);
				return response(new SBuyPetSkin(modelid, skinid));
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575522;

	public int getType() {
		return 6575522;
	}

	public int modelid;
	public int skinid;

	public CBuyPetSkin() {
	}

	public CBuyPetSkin(int _modelid_, int _skinid_) {
		this.modelid = _modelid_;
		this.skinid = _skinid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(modelid);
		_os_.marshal(skinid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		modelid = _os_.unmarshal_int();
		skinid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBuyPetSkin) {
			CBuyPetSkin _o_ = (CBuyPetSkin)_o1_;
			if (modelid != _o_.modelid) return false;
			if (skinid != _o_.skinid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += modelid;
		_h_ += skinid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid).append(",");
		_sb_.append(skinid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CBuyPetSkin _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		_c_ = skinid - _o_.skinid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

