
package lx.gs.pet.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;
import xbean.RolePet;
import xtable.Roleinfos;

import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CLoadPet__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CLoadPet extends __CLoadPet__ {
	@Override
	protected void process() {
		new AProcedure<CLoadPet>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid))
					return false;
				RolePet rolePet = FPet.get(roleid);
				List<Integer> fightlist = rolePet.getFightpets();
				if(fightlist.contains(modelid) || fightlist.size() >= FPet.FIGHT_PET_MAX_NUM)
					return error(ErrorCode.PARAM_ERROR);

				if(Roleinfos.selectLevel(roleid) < CfgMgr.petconfig.petslotopenlevel.get(fightlist.size())){
					return error(ErrorCode.NOT_ENOUGH_LEVEL);
				}

				if(!rolePet.getPetmap().containsKey(modelid))
					return error(ErrorCode.PARAM_ERROR);

				if(fightlist.isEmpty()){
                    rolePet.setActivepetmodelid(modelid);
					FPet.activePet(roleid, rolePet.getPetmap().get(modelid));
				}

				fightlist.add(modelid);
				FPet.syncFightPet(roleid);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573099;

	public int getType() {
		return 6573099;
	}

	public int modelid;

	public CLoadPet() {
	}

	public CLoadPet(int _modelid_) {
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
		if (_o1_ instanceof CLoadPet) {
			CLoadPet _o_ = (CLoadPet)_o1_;
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

	public int compareTo(CLoadPet _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

