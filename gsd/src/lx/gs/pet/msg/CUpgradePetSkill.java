
package lx.gs.pet.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUpgradePetSkill__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUpgradePetSkill extends __CUpgradePetSkill__ {
	@Override
	protected void process() {
		new AProcedure<CUpgradePetSkill>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid))
					return false;
				final xbean.Pet pet = FPet.getPetByModelId(roleid, modelid);
				final Map<Integer, Integer> skills = pet.getSkills();
				if(!skills.containsKey(skillid))
					return error(ErrorCode.SKILL_UNAVALIABLE);

				final int newLevel = skills.get(skillid) + 1;
				final cfg.skill.SkillLvlupCost curcfg = CfgMgr.skilllvlupcost.get(skillid);
				if(newLevel > curcfg.skilllvlupdata.size())
					return error(ErrorCode.CANNOT_UPGRADE_MAX_LEVEL);
				final cfg.skill.SkillLvlupData costcfg = curcfg.skilllvlupdata.get(newLevel - 1);
				if(pet.getLevel() < costcfg.requirelvl)
					return error(ErrorCode.NOT_ENOUGH_LEVEL);
				final ErrorCode err = FCondition.checkByReflection(roleid, costcfg, By.Pet_UpLevel_Skill);
				if(err.err())
					return error(err);
				skills.put(skillid, newLevel);
				FPet.syncPetSkill(roleid, pet, skillid);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570496;

	public int getType() {
		return 6570496;
	}

	public int modelid;
	public int skillid;

	public CUpgradePetSkill() {
	}

	public CUpgradePetSkill(int _modelid_, int _skillid_) {
		this.modelid = _modelid_;
		this.skillid = _skillid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(modelid);
		_os_.marshal(skillid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		modelid = _os_.unmarshal_int();
		skillid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUpgradePetSkill) {
			CUpgradePetSkill _o_ = (CUpgradePetSkill)_o1_;
			if (modelid != _o_.modelid) return false;
			if (skillid != _o_.skillid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += modelid;
		_h_ += skillid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid).append(",");
		_sb_.append(skillid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUpgradePetSkill _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		_c_ = skillid - _o_.skillid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

