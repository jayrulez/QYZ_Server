
package lx.gs.pet.msg;

import cfg.CfgMgr;
import cfg.Const;
import cfg.pet.PetWash;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.AttrUtils;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.pet.FPet;
import xbean.Pet;

import java.util.HashMap;
import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CWashPet__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CWashPet extends __CWashPet__ {
	@Override
	protected void process() {
		new AProcedure<CWashPet>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FMap.isInWorldOrFamily(roleid))
					return false;
				final Pet pet = FPet.getPetByModelId(roleid, modelid);

				SWashMaxValue max = FPet.getWashMaxProto(pet);
				if(max.currvalues.equals(max.maxvalues)){
					return error(ErrorCode.PET_WASH_MAX);
				}

				final int num = isten == Const.TRUE ? 10 : 1;
				final PetWash wcfg = CfgMgr.petwash.get(washid);
				final ErrorCode err = FCondition.checkByReflection(roleid, wcfg, num, By.Pet_Wash);
				if(err.err())
					return error(err);

				final Map<Integer, Float> lastwashrecord = pet.getLastwashrecord();
				lastwashrecord.clear();
				FPet.genWashAtrrs(lastwashrecord, pet, wcfg, num);

//				if(!lastwashrecord.values().stream().anyMatch(aFloat -> aFloat != 0)){ // 全都为0
//					return error(ErrorCode.PET_WASH_MAX);
//				}
				Map<Integer, Float> rawMap = new HashMap<>(FPet.getPetRawAttrs(pet));
				Map<Integer, Float> afterWashMap = new HashMap<>(lastwashrecord);
				AttrUtils.addValue(afterWashMap, rawMap);
				afterWashMap = FPet.getPetFinalAttrMap(afterWashMap);
				rawMap = FPet.getPetFinalAttrMap(rawMap);

				Map<Integer, Float> deltaMap = new HashMap<>(lastwashrecord);
				for (int id : FPet.washAttrId().keySet()) {
					deltaMap.put(id, afterWashMap.getOrDefault(id, 0f) - rawMap.getOrDefault(id, 0f));
				}

				final SWashPet re = new SWashPet();
				re.modelid = modelid;
				re.deltavalues.putAll(deltaMap);
				return response(re);
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6558901;

	public int getType() {
		return 6558901;
	}

	public int modelid;
	public int washid;
	public int isten;

	public CWashPet() {
	}

	public CWashPet(int _modelid_, int _washid_, int _isten_) {
		this.modelid = _modelid_;
		this.washid = _washid_;
		this.isten = _isten_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(modelid);
		_os_.marshal(washid);
		_os_.marshal(isten);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		modelid = _os_.unmarshal_int();
		washid = _os_.unmarshal_int();
		isten = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CWashPet) {
			CWashPet _o_ = (CWashPet)_o1_;
			if (modelid != _o_.modelid) return false;
			if (washid != _o_.washid) return false;
			if (isten != _o_.isten) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += modelid;
		_h_ += washid;
		_h_ += isten;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid).append(",");
		_sb_.append(washid).append(",");
		_sb_.append(isten).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CWashPet _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		_c_ = washid - _o_.washid;
		if (0 != _c_) return _c_;
		_c_ = isten - _o_.isten;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

