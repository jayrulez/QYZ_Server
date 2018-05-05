
package lx.gs.pet.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gs.AProcedure;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SPetInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SPetInfo extends __SPetInfo__ {
	@Override
	protected void process() {
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568617;

	public int getType() {
		return 6568617;
	}

	public java.util.HashMap<Integer,Integer> petfragment; // 伙伴碎片，key为伙伴ModelId
	public java.util.HashMap<Integer,lx.gs.pet.Pet> petmap;
	public java.util.ArrayList<Integer> fightpets;
	public int activemodelid;

	public SPetInfo() {
		petfragment = new java.util.HashMap<Integer,Integer>();
		petmap = new java.util.HashMap<Integer,lx.gs.pet.Pet>();
		fightpets = new java.util.ArrayList<Integer>();
	}

	public SPetInfo(java.util.HashMap<Integer,Integer> _petfragment_, java.util.HashMap<Integer,lx.gs.pet.Pet> _petmap_, java.util.ArrayList<Integer> _fightpets_, int _activemodelid_) {
		this.petfragment = _petfragment_;
		this.petmap = _petmap_;
		this.fightpets = _fightpets_;
		this.activemodelid = _activemodelid_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.pet.Pet> _e_ : petmap.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(petfragment.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : petfragment.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(petmap.size());
		for (java.util.Map.Entry<Integer, lx.gs.pet.Pet> _e_ : petmap.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(fightpets.size());
		for (Integer _v_ : fightpets) {
			_os_.marshal(_v_);
		}
		_os_.marshal(activemodelid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			petfragment.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.pet.Pet _v_ = new lx.gs.pet.Pet();
			_v_.unmarshal(_os_);
			petmap.put(_k_, _v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			fightpets.add(_v_);
		}
		activemodelid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SPetInfo) {
			SPetInfo _o_ = (SPetInfo)_o1_;
			if (!petfragment.equals(_o_.petfragment)) return false;
			if (!petmap.equals(_o_.petmap)) return false;
			if (!fightpets.equals(_o_.fightpets)) return false;
			if (activemodelid != _o_.activemodelid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += petfragment.hashCode();
		_h_ += petmap.hashCode();
		_h_ += fightpets.hashCode();
		_h_ += activemodelid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(petfragment).append(",");
		_sb_.append(petmap).append(",");
		_sb_.append(fightpets).append(",");
		_sb_.append(activemodelid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

