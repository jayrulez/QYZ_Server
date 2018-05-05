
package lx.gs.pet.msg;

import cfg.CfgMgr;
import cfg.pet.PetBasicStatus;
import cfg.pet.PetFragment;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.logger.By;
import lx.gs.pet.FPet;
import xbean.Pet;
import xbean.RolePet;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCallPet__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCallPet extends __CCallPet__ {
	@Override
	protected void process() {
		new AProcedure<CCallPet>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				Pet pet = FPet.getPetByModelId(roleid, modelid);
				if(pet != null) return error(ErrorCode.PET_ALREADY_EXIST);

				PetBasicStatus model = CfgMgr.petbasicstatus.get(modelid);
				if(model == null) return error(ErrorCode.PARAM_ERROR);
				PetFragment fragmentModel = CfgMgr.petfragment.get(model.fragmentid);
				RolePet rolePet = FPet.get(roleid);
				Map<Integer, Integer> fragmentMap = rolePet.getPetfragmentmap();
				int currCount = fragmentMap.containsKey(fragmentModel.id) ? fragmentMap.get(fragmentModel.id) : 0;
				int remain = currCount - fragmentModel.number;
				if(remain < 0) return error(ErrorCode.PET_FRAGMENT_NOT_ENOUGH);

				fragmentMap.put(fragmentModel.id, remain);

				FPet.syncFragmentByModelId(roleid, model.fragmentid);
				FPet.addPet(roleid, modelid, By.Pet_Call);
				FPet.checkRoleKarma(roleid);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579970;

	public int getType() {
		return 6579970;
	}

	public int modelid;

	public CCallPet() {
	}

	public CCallPet(int _modelid_) {
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
		if (_o1_ instanceof CCallPet) {
			CCallPet _o_ = (CCallPet)_o1_;
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

	public int compareTo(CCallPet _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

