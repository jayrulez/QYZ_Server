
package lx.gs.pet.msg;

import cfg.CfgMgr;
import cfg.Const;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;
import map.msg.SUnequipPet;
import xbean.Pet;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUnActivePet__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUnActivePet extends __CUnActivePet__ {
	@Override
	protected void process() {
		new AProcedure<CUnActivePet>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid))
					return false;
				final ErrorCode err = FCondition.checkA(roleid, CfgMgr.petconfig.equipcd, 1, By.Pet_Equip_Active, ConfigId.PET_ACTIVE_EQUIP, 0);
				if(err.err())
					return error(err);
				final xbean.RolePet info = FPet.get(roleid);
				Pet oldActivePet = FPet.getPetByModelId(roleid, info.getActivepetmodelid());
				if(oldActivePet == null)
					return false;

				info.setActivepetmodelid(Const.NULL);
				FMap.dispatchMessageInProcedure(roleid, new SUnequipPet(oldActivePet.getModelid()));
				response(new SUnActivePet());
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566302;

	public int getType() {
		return 6566302;
	}

	public int modelid;

	public CUnActivePet() {
	}

	public CUnActivePet(int _modelid_) {
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
		if (_o1_ instanceof CUnActivePet) {
			CUnActivePet _o_ = (CUnActivePet)_o1_;
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

	public int compareTo(CUnActivePet _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

