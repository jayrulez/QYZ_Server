
package lx.gs.skill.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChangeEquipActiveSkill__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChangeEquipActiveSkill extends __SChangeEquipActiveSkill__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557703;

	public int getType() {
		return 6557703;
	}

	public java.util.HashMap<Integer,Integer> equipskillpositions;

	public SChangeEquipActiveSkill() {
		equipskillpositions = new java.util.HashMap<Integer,Integer>();
	}

	public SChangeEquipActiveSkill(java.util.HashMap<Integer,Integer> _equipskillpositions_) {
		this.equipskillpositions = _equipskillpositions_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(equipskillpositions.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : equipskillpositions.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			equipskillpositions.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChangeEquipActiveSkill) {
			SChangeEquipActiveSkill _o_ = (SChangeEquipActiveSkill)_o1_;
			if (!equipskillpositions.equals(_o_.equipskillpositions)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += equipskillpositions.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(equipskillpositions).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

