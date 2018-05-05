
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 家族活动信息
*/
public class FamilyActivity implements Marshal {
	public java.util.HashMap<Integer,lx.gs.family.msg.FamilyGodAnimal> godanimalinfo; // 家族神兽信息

	public FamilyActivity() {
		godanimalinfo = new java.util.HashMap<Integer,lx.gs.family.msg.FamilyGodAnimal>();
	}

	public FamilyActivity(java.util.HashMap<Integer,lx.gs.family.msg.FamilyGodAnimal> _godanimalinfo_) {
		this.godanimalinfo = _godanimalinfo_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.family.msg.FamilyGodAnimal> _e_ : godanimalinfo.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(godanimalinfo.size());
		for (java.util.Map.Entry<Integer, lx.gs.family.msg.FamilyGodAnimal> _e_ : godanimalinfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.family.msg.FamilyGodAnimal _v_ = new lx.gs.family.msg.FamilyGodAnimal();
			_v_.unmarshal(_os_);
			godanimalinfo.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FamilyActivity) {
			FamilyActivity _o_ = (FamilyActivity)_o1_;
			if (!godanimalinfo.equals(_o_.godanimalinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += godanimalinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(godanimalinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

