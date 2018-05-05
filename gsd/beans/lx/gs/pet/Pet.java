
package lx.gs.pet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 伙伴信息
*/
public class Pet implements Marshal {
	public int modelid;
	public int activeskinid;
	public java.util.ArrayList<Integer> skinidlist;
	public int level; // 伙伴的级别
	public long exp; // 伙伴的经验值
	public int starlevel; // 星阶
	public java.util.HashMap<Integer,Integer> skills; // 伙伴的技能信息
	public int awakelevel; // 觉醒的等级
	public java.util.HashMap<Integer,Float> attrs;
	public long combatpower;

	public Pet() {
		skinidlist = new java.util.ArrayList<Integer>();
		skills = new java.util.HashMap<Integer,Integer>();
		attrs = new java.util.HashMap<Integer,Float>();
	}

	public Pet(int _modelid_, int _activeskinid_, java.util.ArrayList<Integer> _skinidlist_, int _level_, long _exp_, int _starlevel_, java.util.HashMap<Integer,Integer> _skills_, int _awakelevel_, java.util.HashMap<Integer,Float> _attrs_, long _combatpower_) {
		this.modelid = _modelid_;
		this.activeskinid = _activeskinid_;
		this.skinidlist = _skinidlist_;
		this.level = _level_;
		this.exp = _exp_;
		this.starlevel = _starlevel_;
		this.skills = _skills_;
		this.awakelevel = _awakelevel_;
		this.attrs = _attrs_;
		this.combatpower = _combatpower_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(modelid);
		_os_.marshal(activeskinid);
		_os_.compact_uint32(skinidlist.size());
		for (Integer _v_ : skinidlist) {
			_os_.marshal(_v_);
		}
		_os_.marshal(level);
		_os_.marshal(exp);
		_os_.marshal(starlevel);
		_os_.compact_uint32(skills.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : skills.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(awakelevel);
		_os_.compact_uint32(attrs.size());
		for (java.util.Map.Entry<Integer, Float> _e_ : attrs.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(combatpower);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		modelid = _os_.unmarshal_int();
		activeskinid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			skinidlist.add(_v_);
		}
		level = _os_.unmarshal_int();
		exp = _os_.unmarshal_long();
		starlevel = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			skills.put(_k_, _v_);
		}
		awakelevel = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			float _v_;
			_v_ = _os_.unmarshal_float();
			attrs.put(_k_, _v_);
		}
		combatpower = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Pet) {
			Pet _o_ = (Pet)_o1_;
			if (modelid != _o_.modelid) return false;
			if (activeskinid != _o_.activeskinid) return false;
			if (!skinidlist.equals(_o_.skinidlist)) return false;
			if (level != _o_.level) return false;
			if (exp != _o_.exp) return false;
			if (starlevel != _o_.starlevel) return false;
			if (!skills.equals(_o_.skills)) return false;
			if (awakelevel != _o_.awakelevel) return false;
			if (!attrs.equals(_o_.attrs)) return false;
			if (combatpower != _o_.combatpower) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += modelid;
		_h_ += activeskinid;
		_h_ += skinidlist.hashCode();
		_h_ += level;
		_h_ += (int)exp;
		_h_ += starlevel;
		_h_ += skills.hashCode();
		_h_ += awakelevel;
		_h_ += attrs.hashCode();
		_h_ += (int)combatpower;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid).append(",");
		_sb_.append(activeskinid).append(",");
		_sb_.append(skinidlist).append(",");
		_sb_.append(level).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(starlevel).append(",");
		_sb_.append(skills).append(",");
		_sb_.append(awakelevel).append(",");
		_sb_.append(attrs).append(",");
		_sb_.append(combatpower).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

