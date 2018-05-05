
package lx.gs.pet.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;
import map.msg.SUnequipPet;
import xbean.Pet;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CActivePet__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CActivePet extends __CActivePet__ {
	@Override
	protected void process() {
		new AProcedure<CActivePet>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid))
					return false;
				final ErrorCode err = FCondition.checkA(roleid, CfgMgr.petconfig.equipcd, 1, By.Pet_Equip_Active, ConfigId.PET_ACTIVE_EQUIP, 0);
				if(err.err())
					return error(err);
				final xbean.RolePet info = FPet.get(roleid);
				Map<Integer, Pet> petMap = info.getPetmap();
				Pet oldActivePet = petMap.get(info.getActivepetmodelid());

				Pet pet = petMap.get(modelid);
				if(pet == null) return false;
				if(oldActivePet != null){
					FMap.dispatchMessageInProcedure(roleid, new SUnequipPet(oldActivePet.getModelid()));
				}
				info.setActivepetmodelid(modelid);

				FPet.activePet(roleid, pet);
				return true;
			}
		}.validateRoleidAndExecute();	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565173;

	public int getType() {
		return 6565173;
	}

	public int modelid;

	public CActivePet() {
	}

	public CActivePet(int _modelid_) {
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
		if (_o1_ instanceof CActivePet) {
			CActivePet _o_ = (CActivePet)_o1_;
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

	public int compareTo(CActivePet _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

