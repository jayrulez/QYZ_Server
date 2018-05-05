
package lx.gs.pet.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEquipPetSkin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEquipPetSkin extends __CEquipPetSkin__ {
	@Override
	protected void process() {
		new AProcedure<CEquipPetSkin>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid))
					return false;
				final xbean.Pet pet = FPet.getPetByModelId(roleid, modelid);
				final cfg.pet.PetSkin pcfg = CfgMgr.petskin.get(skinid);
				if (!pet.getSkinidlist().contains(skinid))
					return false;
				if (pcfg.petid != pet.getModelid())
					return error(ErrorCode.PET_SKIN_NOT_MATCH);
				pet.setActiveskinid(skinid);
				FPet.syncPetSkin(roleid, pet);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568809;

	public int getType() {
		return 6568809;
	}

	public int modelid;
	public int skinid;

	public CEquipPetSkin() {
	}

	public CEquipPetSkin(int _modelid_, int _skinid_) {
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
		if (_o1_ instanceof CEquipPetSkin) {
			CEquipPetSkin _o_ = (CEquipPetSkin)_o1_;
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

	public int compareTo(CEquipPetSkin _o_) {
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

