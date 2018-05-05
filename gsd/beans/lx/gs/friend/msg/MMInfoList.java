
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class MMInfoList implements Marshal {
	public java.util.ArrayList<lx.gs.friend.msg.RoleShowInfo> mmlist;

	public MMInfoList() {
		mmlist = new java.util.ArrayList<lx.gs.friend.msg.RoleShowInfo>();
	}

	public MMInfoList(java.util.ArrayList<lx.gs.friend.msg.RoleShowInfo> _mmlist_) {
		this.mmlist = _mmlist_;
	}

	public final boolean _validator_() {
		for (lx.gs.friend.msg.RoleShowInfo _v_ : mmlist)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(mmlist.size());
		for (lx.gs.friend.msg.RoleShowInfo _v_ : mmlist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.friend.msg.RoleShowInfo _v_ = new lx.gs.friend.msg.RoleShowInfo();
			_v_.unmarshal(_os_);
			mmlist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MMInfoList) {
			MMInfoList _o_ = (MMInfoList)_o1_;
			if (!mmlist.equals(_o_.mmlist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += mmlist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mmlist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

