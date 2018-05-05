
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 家族神兽信息
*/
public class FamilyGodAnimal implements Marshal , Comparable<FamilyGodAnimal>{
	public int animalid;
	public int animallevel; // 神兽进化到的level
	public long exp; // 神兽的经验值

	public FamilyGodAnimal() {
		animallevel = 1;
	}

	public FamilyGodAnimal(int _animalid_, int _animallevel_, long _exp_) {
		this.animalid = _animalid_;
		this.animallevel = _animallevel_;
		this.exp = _exp_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(animalid);
		_os_.marshal(animallevel);
		_os_.marshal(exp);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		animalid = _os_.unmarshal_int();
		animallevel = _os_.unmarshal_int();
		exp = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FamilyGodAnimal) {
			FamilyGodAnimal _o_ = (FamilyGodAnimal)_o1_;
			if (animalid != _o_.animalid) return false;
			if (animallevel != _o_.animallevel) return false;
			if (exp != _o_.exp) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += animalid;
		_h_ += animallevel;
		_h_ += (int)exp;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(animalid).append(",");
		_sb_.append(animallevel).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(FamilyGodAnimal _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = animalid - _o_.animalid;
		if (0 != _c_) return _c_;
		_c_ = animallevel - _o_.animallevel;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(exp - _o_.exp);
		if (0 != _c_) return _c_;
		return _c_;
	}

}

