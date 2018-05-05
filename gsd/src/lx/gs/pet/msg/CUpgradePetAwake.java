
package lx.gs.pet.msg;

import cfg.CfgMgr;
import cfg.pet.GetBuff;
import cfg.pet.GetSkill;
import cfg.pet.PetBasicStatus;
import cfg.pet.Property;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.event.EventModule;
import lx.gs.event.PetAwakeUpEvent;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;
import map.msg.XAddPetBuff;
import xbean.Pet;
import xbean.RolePet;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUpgradePetAwake__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUpgradePetAwake extends __CUpgradePetAwake__ {
	@Override
	protected void process() {
		new AProcedure<CUpgradePetAwake>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid))
					return false;
				RolePet rolePet = FPet.get(roleid);
				final Pet pet = rolePet.getPetmap().get(modelid);
				final PetBasicStatus model = CfgMgr.petbasicstatus.get(pet.getModelid());
				final cfg.pet.PetAwake awakeCfg = CfgMgr.petawake.get(pet.getModelid());
				final int newAwakeLevel = pet.getAwakelevel() + 1;
				final cfg.pet.AwakeInfo awakeInfo = awakeCfg.awakelvlup_awakeid.get(newAwakeLevel);
				if(pet.getLevel() < awakeInfo.requirepetlevel)
					return error(ErrorCode.NOT_ENOUGH_LEVEL);

				ErrorCode errorCode = FCondition.checkA(roleid, awakeInfo.requirexunibi, 1, By.Pet_Wake, -1, -1);
				if(errorCode.err())
					return error(errorCode);

				Map<Integer, Integer> map = FPet.get(roleid).getPetfragmentmap();
				int currCount = map.getOrDefault(model.fragmentid, 0);
				if(currCount < awakeInfo.petfragmentcost)
					return error(ErrorCode.PET_WAKE_FRAGMENT_NUM_NOT_ENOUGH);

				map.put(model.fragmentid, currCount - awakeInfo.petfragmentcost);
				FPet.syncFragmentByModelId(roleid, model.fragmentid);
				pet.setAwakelevel(newAwakeLevel);
				EventModule.INSTANCE.broadcastEvent(new PetAwakeUpEvent(roleid, newAwakeLevel, modelid, FPet.isFightPet(rolePet, modelid)));
				FPet.checkRoleKarma(roleid);

				awakeInfo.effect.forEach(awakeEffect -> {
					switch (awakeEffect.getTypeId()) {
						case GetSkill.TYPEID: {
							GetSkill getSkill = (GetSkill) awakeEffect;
							if(getSkill.skillid > 0){
								pet.getSkills().put(getSkill.skillid, 1);
								FPet.syncPetSkill(roleid, pet, getSkill.skillid);
							}
							break;
						}
						case Property.TYPEID: {
							Property property = (Property) awakeEffect;
							common.AttrUtils.addAttrs(pet.getFixedattrs(), property.gainability);
							FPet.syncPetAttrs(roleid, pet);
							break;
						}
						case GetBuff.TYPEID:{
							GetBuff getBuff = (GetBuff) awakeEffect;
							pet.getBuffids().add(getBuff.buffid);
							if(FPet.isActiveEquipPet(roleid, modelid)){
								FMap.dispatchPetMessageInProcedure(roleid, pet.getModelid(), new XAddPetBuff(getBuff.buffid));
							}
							break;
						}
						default:
							throw new RuntimeException("unknown awake effect:" + awakeEffect);

					}
				});
				FPet.syncAwake(roleid, pet);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565703;

	public int getType() {
		return 6565703;
	}

	public int modelid;

	public CUpgradePetAwake() {
	}

	public CUpgradePetAwake(int _modelid_) {
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
		if (_o1_ instanceof CUpgradePetAwake) {
			CUpgradePetAwake _o_ = (CUpgradePetAwake)_o1_;
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

	public int compareTo(CUpgradePetAwake _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

