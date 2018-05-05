
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

abstract class __CEvolvePetSkill__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEvolvePetSkill extends __CEvolvePetSkill__ {
	@Override
	protected void process() {
		new AProcedure<CEvolvePetSkill>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid))
					return false;
				final xbean.Pet pet = FPet.getPetByModelId(roleid, modelid);
				final Map<Integer, Integer> skills = pet.getSkills();
				if(!skills.containsKey(skillid))
					return error(ErrorCode.SKILL_UNAVALIABLE);

				final int level = skills.remove(skillid);

				final cfg.skill.SkillLvlupCost curcfg = CfgMgr.skilllvlupcost.get(skillid);
				if(level < curcfg.skilllvlupdata.size())
					return error(ErrorCode.CUR_LEVEL_CANNOT_EVOLVE);
				final int newSkillid = curcfg.nextskillid;
				if(newSkillid == 0)
					return error(ErrorCode.NOT_NEXT_EVOLVE_SKILL);
				final cfg.skill.SkillLvlupCost nextcfg = CfgMgr.skilllvlupcost.get(newSkillid);
				final cfg.skill.SkillLvlupData costcfg = nextcfg.skilllvlupdata.get(0);

				final xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
				if(role.getLevel() < costcfg.requirelvl)
					return error(ErrorCode.NOT_ENOUGH_LEVEL);
				final ErrorCode err = FCondition.checkByReflection(roleid, costcfg, By.Pet_Evolve_Skill);
				if(err.err())
					return error(err);
				final int newLevel = 1;
				skills.put(newSkillid, newLevel);

				final SEvolvePetSkill msg = new SEvolvePetSkill(modelid, skillid, newSkillid, 1);
				response(msg);
				if(FPet.isActiveEquipPet(roleid, modelid)) {
					FPet.syncPetSkill(roleid, pet, skillid);
				}
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578964;

	public int getType() {
		return 6578964;
	}

	public int modelid; // 123
	public int skillid;

	public CEvolvePetSkill() {
	}

	public CEvolvePetSkill(int _modelid_, int _skillid_) {
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
		if (_o1_ instanceof CEvolvePetSkill) {
			CEvolvePetSkill _o_ = (CEvolvePetSkill)_o1_;
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

	public int compareTo(CEvolvePetSkill _o_) {
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

