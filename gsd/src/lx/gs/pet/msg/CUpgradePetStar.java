
package lx.gs.pet.msg;

import cfg.CfgMgr;
import cfg.cmd.condition.Condition;
import cfg.pet.PetStageStar;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.event.EventModule;
import lx.gs.event.PetStarUpEvent;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;
import map.msg.SChangePetStarLevel;
import xbean.Pet;
import xbean.RolePet;

import java.util.ArrayList;
import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUpgradePetStar__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUpgradePetStar extends __CUpgradePetStar__ {
	@Override
	protected void process() {
		new AProcedure<CUpgradePetStar>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid))
					return false;
				RolePet rolePet = FPet.get(roleid);
				final Pet pet = rolePet.getPetmap().get(modelid);
				final int oldStarLevel = pet.getStarlevel();
				final PetStageStar starCfg = CfgMgr.petstagestar.get(oldStarLevel);
				if(pet.getLevel() < starCfg.requirepetlvl){
					return error(ErrorCode.NOT_ENOUGH_LEVEL);
				}
				List<Condition> conditionList = new ArrayList<Condition>();
				conditionList.add(starCfg.requirexunibi);
				conditionList.addAll(starCfg.requireitem);

				ErrorCode errorCode = FCondition.check(roleid, 1, By.Pet_UpLevel_Star, -1, -1, conditionList);
				if(errorCode.err()) return error(errorCode);

				int newStarLevel = oldStarLevel + 1;
				pet.setStarlevel(newStarLevel);

				if(FPet.isFightPet(rolePet, modelid)){
					EventModule.INSTANCE.broadcastEvent(new PetStarUpEvent(roleid));
				}
				FPet.onPetLevelUp(pet);
				FPet.syncPetAttrs(roleid, pet);
				FPet.checkRoleKarma(roleid);
                FMap.dispatchPetMessageInProcedure(roleid, pet.getModelid(), new SChangePetStarLevel(newStarLevel));
				return response(new SUpgradePetStar(modelid, newStarLevel));
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566001;

	public int getType() {
		return 6566001;
	}

	public int modelid;

	public CUpgradePetStar() {
	}

	public CUpgradePetStar(int _modelid_) {
		this.modelid = _modelid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(modelid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		modelid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUpgradePetStar) {
			CUpgradePetStar _o_ = (CUpgradePetStar)_o1_;
			if (modelid != _o_.modelid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += modelid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUpgradePetStar _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

