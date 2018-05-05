
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleMapInfo2 implements Marshal {
	public int hp;
	public int mp;
	public long deathtime;
	public java.util.HashMap<Integer,map.msg.PetMapContext> pets;
	public java.util.HashMap<Integer,Long> skills; // skillid -> activetime, BABAO_ID = 0 reserve for fabao skill

	public RoleMapInfo2() {
		pets = new java.util.HashMap<Integer,map.msg.PetMapContext>();
		skills = new java.util.HashMap<Integer,Long>();
	}

	public RoleMapInfo2(int _hp_, int _mp_, long _deathtime_, java.util.HashMap<Integer,map.msg.PetMapContext> _pets_, java.util.HashMap<Integer,Long> _skills_) {
		this.hp = _hp_;
		this.mp = _mp_;
		this.deathtime = _deathtime_;
		this.pets = _pets_;
		this.skills = _skills_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, map.msg.PetMapContext> _e_ : pets.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(hp);
		_os_.marshal(mp);
		_os_.marshal(deathtime);
		_os_.compact_uint32(pets.size());
		for (java.util.Map.Entry<Integer, map.msg.PetMapContext> _e_ : pets.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(skills.size());
		for (java.util.Map.Entry<Integer, Long> _e_ : skills.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		hp = _os_.unmarshal_int();
		mp = _os_.unmarshal_int();
		deathtime = _os_.unmarshal_long();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			map.msg.PetMapContext _v_ = new map.msg.PetMapContext();
			_v_.unmarshal(_os_);
			pets.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			long _v_;
			_v_ = _os_.unmarshal_long();
			skills.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleMapInfo2) {
			RoleMapInfo2 _o_ = (RoleMapInfo2)_o1_;
			if (hp != _o_.hp) return false;
			if (mp != _o_.mp) return false;
			if (deathtime != _o_.deathtime) return false;
			if (!pets.equals(_o_.pets)) return false;
			if (!skills.equals(_o_.skills)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += hp;
		_h_ += mp;
		_h_ += (int)deathtime;
		_h_ += pets.hashCode();
		_h_ += skills.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(hp).append(",");
		_sb_.append(mp).append(",");
		_sb_.append(deathtime).append(",");
		_sb_.append(pets).append(",");
		_sb_.append(skills).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

