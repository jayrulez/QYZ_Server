
package lx.gs.role.title.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class GroupTitle implements Marshal {
	public java.util.ArrayList<lx.gs.role.title.msg.Title> titleinfo; // 称号信息

	public GroupTitle() {
		titleinfo = new java.util.ArrayList<lx.gs.role.title.msg.Title>();
	}

	public GroupTitle(java.util.ArrayList<lx.gs.role.title.msg.Title> _titleinfo_) {
		this.titleinfo = _titleinfo_;
	}

	public final boolean _validator_() {
		for (lx.gs.role.title.msg.Title _v_ : titleinfo)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(titleinfo.size());
		for (lx.gs.role.title.msg.Title _v_ : titleinfo) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.role.title.msg.Title _v_ = new lx.gs.role.title.msg.Title();
			_v_.unmarshal(_os_);
			titleinfo.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GroupTitle) {
			GroupTitle _o_ = (GroupTitle)_o1_;
			if (!titleinfo.equals(_o_.titleinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += titleinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(titleinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

