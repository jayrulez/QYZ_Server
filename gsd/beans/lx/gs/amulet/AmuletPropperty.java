
package lx.gs.amulet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class AmuletPropperty implements Marshal , Comparable<AmuletPropperty>{
	public int propindex; // 属性id
	public int islock; // 是否锁定，0为未锁定，1为锁定
	public int skillid; // 技能id
	public int professionid; // 职业id
	public int addlevel; // 技能增值

	public AmuletPropperty() {
	}

	public AmuletPropperty(int _propindex_, int _islock_, int _skillid_, int _professionid_, int _addlevel_) {
		this.propindex = _propindex_;
		this.islock = _islock_;
		this.skillid = _skillid_;
		this.professionid = _professionid_;
		this.addlevel = _addlevel_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(propindex);
		_os_.marshal(islock);
		_os_.marshal(skillid);
		_os_.marshal(professionid);
		_os_.marshal(addlevel);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		propindex = _os_.unmarshal_int();
		islock = _os_.unmarshal_int();
		skillid = _os_.unmarshal_int();
		professionid = _os_.unmarshal_int();
		addlevel = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AmuletPropperty) {
			AmuletPropperty _o_ = (AmuletPropperty)_o1_;
			if (propindex != _o_.propindex) return false;
			if (islock != _o_.islock) return false;
			if (skillid != _o_.skillid) return false;
			if (professionid != _o_.professionid) return false;
			if (addlevel != _o_.addlevel) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += propindex;
		_h_ += islock;
		_h_ += skillid;
		_h_ += professionid;
		_h_ += addlevel;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(propindex).append(",");
		_sb_.append(islock).append(",");
		_sb_.append(skillid).append(",");
		_sb_.append(professionid).append(",");
		_sb_.append(addlevel).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(AmuletPropperty _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = propindex - _o_.propindex;
		if (0 != _c_) return _c_;
		_c_ = islock - _o_.islock;
		if (0 != _c_) return _c_;
		_c_ = skillid - _o_.skillid;
		if (0 != _c_) return _c_;
		_c_ = professionid - _o_.professionid;
		if (0 != _c_) return _c_;
		_c_ = addlevel - _o_.addlevel;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

