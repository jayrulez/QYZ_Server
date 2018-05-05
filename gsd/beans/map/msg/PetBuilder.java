
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class PetBuilder implements Marshal {
	public map.msg.FighterBuilder basic;
	public long ownerid;
	public int level;
	public int starlevel;
	public int awakelevel;
	public int skinid;
	public java.util.HashMap<Integer,Integer> skills;
	public int hp;
	public int mp;
	public java.util.ArrayList<Float> attrs;
	public java.util.ArrayList<Integer> buffs;
	public map.msg.Vector3 defaultrelateownerposition;
	public byte serverai;

	public PetBuilder() {
		basic = new map.msg.FighterBuilder();
		skills = new java.util.HashMap<Integer,Integer>();
		attrs = new java.util.ArrayList<Float>();
		buffs = new java.util.ArrayList<Integer>();
		defaultrelateownerposition = new map.msg.Vector3();
	}

	public PetBuilder(map.msg.FighterBuilder _basic_, long _ownerid_, int _level_, int _starlevel_, int _awakelevel_, int _skinid_, java.util.HashMap<Integer,Integer> _skills_, int _hp_, int _mp_, java.util.ArrayList<Float> _attrs_, java.util.ArrayList<Integer> _buffs_, map.msg.Vector3 _defaultrelateownerposition_, byte _serverai_) {
		this.basic = _basic_;
		this.ownerid = _ownerid_;
		this.level = _level_;
		this.starlevel = _starlevel_;
		this.awakelevel = _awakelevel_;
		this.skinid = _skinid_;
		this.skills = _skills_;
		this.hp = _hp_;
		this.mp = _mp_;
		this.attrs = _attrs_;
		this.buffs = _buffs_;
		this.defaultrelateownerposition = _defaultrelateownerposition_;
		this.serverai = _serverai_;
	}

	public final boolean _validator_() {
		if (!basic._validator_()) return false;
		if (!defaultrelateownerposition._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(basic);
		_os_.marshal(ownerid);
		_os_.marshal(level);
		_os_.marshal(starlevel);
		_os_.marshal(awakelevel);
		_os_.marshal(skinid);
		_os_.compact_uint32(skills.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : skills.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(hp);
		_os_.marshal(mp);
		_os_.compact_uint32(attrs.size());
		for (Float _v_ : attrs) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(buffs.size());
		for (Integer _v_ : buffs) {
			_os_.marshal(_v_);
		}
		_os_.marshal(defaultrelateownerposition);
		_os_.marshal(serverai);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		basic.unmarshal(_os_);
		ownerid = _os_.unmarshal_long();
		level = _os_.unmarshal_int();
		starlevel = _os_.unmarshal_int();
		awakelevel = _os_.unmarshal_int();
		skinid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			skills.put(_k_, _v_);
		}
		hp = _os_.unmarshal_int();
		mp = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			float _v_;
			_v_ = _os_.unmarshal_float();
			attrs.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			buffs.add(_v_);
		}
		defaultrelateownerposition.unmarshal(_os_);
		serverai = _os_.unmarshal_byte();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PetBuilder) {
			PetBuilder _o_ = (PetBuilder)_o1_;
			if (!basic.equals(_o_.basic)) return false;
			if (ownerid != _o_.ownerid) return false;
			if (level != _o_.level) return false;
			if (starlevel != _o_.starlevel) return false;
			if (awakelevel != _o_.awakelevel) return false;
			if (skinid != _o_.skinid) return false;
			if (!skills.equals(_o_.skills)) return false;
			if (hp != _o_.hp) return false;
			if (mp != _o_.mp) return false;
			if (!attrs.equals(_o_.attrs)) return false;
			if (!buffs.equals(_o_.buffs)) return false;
			if (!defaultrelateownerposition.equals(_o_.defaultrelateownerposition)) return false;
			if (serverai != _o_.serverai) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += basic.hashCode();
		_h_ += (int)ownerid;
		_h_ += level;
		_h_ += starlevel;
		_h_ += awakelevel;
		_h_ += skinid;
		_h_ += skills.hashCode();
		_h_ += hp;
		_h_ += mp;
		_h_ += attrs.hashCode();
		_h_ += buffs.hashCode();
		_h_ += defaultrelateownerposition.hashCode();
		_h_ += (int)serverai;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(basic).append(",");
		_sb_.append(ownerid).append(",");
		_sb_.append(level).append(",");
		_sb_.append(starlevel).append(",");
		_sb_.append(awakelevel).append(",");
		_sb_.append(skinid).append(",");
		_sb_.append(skills).append(",");
		_sb_.append(hp).append(",");
		_sb_.append(mp).append(",");
		_sb_.append(attrs).append(",");
		_sb_.append(buffs).append(",");
		_sb_.append(defaultrelateownerposition).append(",");
		_sb_.append(serverai).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

