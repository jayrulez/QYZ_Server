
package lx.gs.pet.msg;

import cfg.Const;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;
import map.msg.SUnequipPet;
import xbean.RolePet;

import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUnloadPet__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUnloadPet extends __CUnloadPet__ {
	@Override
	protected void process() {
		new AProcedure<CUnloadPet>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				RolePet rolePet = FPet.get(roleid);
				List<Integer> fightlist = rolePet.getFightpets();
				if(!fightlist.contains(modelid))
					return error(ErrorCode.PARAM_ERROR);

				fightlist.remove(Integer.valueOf(modelid));

				if(rolePet.getActivepetmodelid() == modelid){
					response(new SUnActivePet());
					rolePet.setActivepetmodelid(Const.NULL);
					FMap.dispatchMessageInProcedure(roleid, new SUnequipPet(modelid));
				}
				FPet.syncFightPet(roleid);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575244;

	public int getType() {
		return 6575244;
	}

	public int modelid;

	public CUnloadPet() {
	}

	public CUnloadPet(int _modelid_) {
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
		if (_o1_ instanceof CUnloadPet) {
			CUnloadPet _o_ = (CUnloadPet)_o1_;
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

	public int compareTo(CUnloadPet _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

