
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MPlayerKillMonster__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MPlayerKillMonster extends __MPlayerKillMonster__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6704198;

	public int getType() {
		return 6704198;
	}

	public java.util.HashMap<Integer,Integer> monsters;
	public byte inworld;
	public java.util.ArrayList<Integer> baseexp;
	public float expbonusrate;
	public java.util.ArrayList<Integer> currency;
	public java.util.ArrayList<Integer> pets;
	public java.util.ArrayList<map.msg.Bonus> bonuss;

	public MPlayerKillMonster() {
		monsters = new java.util.HashMap<Integer,Integer>();
		baseexp = new java.util.ArrayList<Integer>();
		currency = new java.util.ArrayList<Integer>();
		pets = new java.util.ArrayList<Integer>();
		bonuss = new java.util.ArrayList<map.msg.Bonus>();
	}

	public MPlayerKillMonster(java.util.HashMap<Integer,Integer> _monsters_, byte _inworld_, java.util.ArrayList<Integer> _baseexp_, float _expbonusrate_, java.util.ArrayList<Integer> _currency_, java.util.ArrayList<Integer> _pets_, java.util.ArrayList<map.msg.Bonus> _bonuss_) {
		this.monsters = _monsters_;
		this.inworld = _inworld_;
		this.baseexp = _baseexp_;
		this.expbonusrate = _expbonusrate_;
		this.currency = _currency_;
		this.pets = _pets_;
		this.bonuss = _bonuss_;
	}

	public final boolean _validator_() {
		for (map.msg.Bonus _v_ : bonuss)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(monsters.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : monsters.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(inworld);
		_os_.compact_uint32(baseexp.size());
		for (Integer _v_ : baseexp) {
			_os_.marshal(_v_);
		}
		_os_.marshal(expbonusrate);
		_os_.compact_uint32(currency.size());
		for (Integer _v_ : currency) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(pets.size());
		for (Integer _v_ : pets) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(bonuss.size());
		for (map.msg.Bonus _v_ : bonuss) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			monsters.put(_k_, _v_);
		}
		inworld = _os_.unmarshal_byte();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			baseexp.add(_v_);
		}
		expbonusrate = _os_.unmarshal_float();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			currency.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			pets.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.Bonus _v_ = new map.msg.Bonus();
			_v_.unmarshal(_os_);
			bonuss.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MPlayerKillMonster) {
			MPlayerKillMonster _o_ = (MPlayerKillMonster)_o1_;
			if (!monsters.equals(_o_.monsters)) return false;
			if (inworld != _o_.inworld) return false;
			if (!baseexp.equals(_o_.baseexp)) return false;
			if (expbonusrate != _o_.expbonusrate) return false;
			if (!currency.equals(_o_.currency)) return false;
			if (!pets.equals(_o_.pets)) return false;
			if (!bonuss.equals(_o_.bonuss)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += monsters.hashCode();
		_h_ += (int)inworld;
		_h_ += baseexp.hashCode();
		_h_ += Float.floatToIntBits(expbonusrate);
		_h_ += currency.hashCode();
		_h_ += pets.hashCode();
		_h_ += bonuss.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(monsters).append(",");
		_sb_.append(inworld).append(",");
		_sb_.append(baseexp).append(",");
		_sb_.append(expbonusrate).append(",");
		_sb_.append(currency).append(",");
		_sb_.append(pets).append(",");
		_sb_.append(bonuss).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

