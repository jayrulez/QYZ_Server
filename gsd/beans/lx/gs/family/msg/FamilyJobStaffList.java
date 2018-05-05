
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class FamilyJobStaffList implements Marshal {
	public java.util.ArrayList<Long> staffs; // 该职位对应的角色id列表

	public FamilyJobStaffList() {
		staffs = new java.util.ArrayList<Long>();
	}

	public FamilyJobStaffList(java.util.ArrayList<Long> _staffs_) {
		this.staffs = _staffs_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(staffs.size());
		for (Long _v_ : staffs) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			staffs.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FamilyJobStaffList) {
			FamilyJobStaffList _o_ = (FamilyJobStaffList)_o1_;
			if (!staffs.equals(_o_.staffs)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += staffs.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(staffs).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

