
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class PlayerEvaluate implements Marshal {
	public java.lang.String name;
	public int camp;
	public int damage;
	public int continuekill;
	public int kill;
	public java.util.ArrayList<Integer> evaluates;

	public PlayerEvaluate() {
		name = "";
		evaluates = new java.util.ArrayList<Integer>();
	}

	public PlayerEvaluate(java.lang.String _name_, int _camp_, int _damage_, int _continuekill_, int _kill_, java.util.ArrayList<Integer> _evaluates_) {
		this.name = _name_;
		this.camp = _camp_;
		this.damage = _damage_;
		this.continuekill = _continuekill_;
		this.kill = _kill_;
		this.evaluates = _evaluates_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(camp);
		_os_.marshal(damage);
		_os_.marshal(continuekill);
		_os_.marshal(kill);
		_os_.compact_uint32(evaluates.size());
		for (Integer _v_ : evaluates) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		name = _os_.unmarshal_String("UTF-16LE");
		camp = _os_.unmarshal_int();
		damage = _os_.unmarshal_int();
		continuekill = _os_.unmarshal_int();
		kill = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			evaluates.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerEvaluate) {
			PlayerEvaluate _o_ = (PlayerEvaluate)_o1_;
			if (!name.equals(_o_.name)) return false;
			if (camp != _o_.camp) return false;
			if (damage != _o_.damage) return false;
			if (continuekill != _o_.continuekill) return false;
			if (kill != _o_.kill) return false;
			if (!evaluates.equals(_o_.evaluates)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += name.hashCode();
		_h_ += camp;
		_h_ += damage;
		_h_ += continuekill;
		_h_ += kill;
		_h_ += evaluates.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(camp).append(",");
		_sb_.append(damage).append(",");
		_sb_.append(continuekill).append(",");
		_sb_.append(kill).append(",");
		_sb_.append(evaluates).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

