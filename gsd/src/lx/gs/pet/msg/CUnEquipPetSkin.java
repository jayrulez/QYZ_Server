
package lx.gs.pet.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gs.AProcedure;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUnEquipPetSkin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUnEquipPetSkin extends __CUnEquipPetSkin__ {
	@Override
	protected void process() {
		new AProcedure<CUnEquipPetSkin>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid))
					return false;
				final xbean.Pet pet = FPet.getPetByModelId(roleid, modelid);
				if (pet.getActiveskinid() == 0)
					return false;
				pet.setActiveskinid(0);
				FPet.syncPetSkin(roleid, pet);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579590;

	public int getType() {
		return 6579590;
	}

	public int modelid;

	public CUnEquipPetSkin() {
	}

	public CUnEquipPetSkin(int _modelid_) {
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
		if (_o1_ instanceof CUnEquipPetSkin) {
			CUnEquipPetSkin _o_ = (CUnEquipPetSkin)_o1_;
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

	public int compareTo(CUnEquipPetSkin _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

