
package lx.gs.amulet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleAmuletInfo implements Marshal {
	public java.util.HashMap<Integer,lx.gs.amulet.AmuletPage> pagemap; // 护符页信息

	public RoleAmuletInfo() {
		pagemap = new java.util.HashMap<Integer,lx.gs.amulet.AmuletPage>();
	}

	public RoleAmuletInfo(java.util.HashMap<Integer,lx.gs.amulet.AmuletPage> _pagemap_) {
		this.pagemap = _pagemap_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.amulet.AmuletPage> _e_ : pagemap.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(pagemap.size());
		for (java.util.Map.Entry<Integer, lx.gs.amulet.AmuletPage> _e_ : pagemap.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.amulet.AmuletPage _v_ = new lx.gs.amulet.AmuletPage();
			_v_.unmarshal(_os_);
			pagemap.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleAmuletInfo) {
			RoleAmuletInfo _o_ = (RoleAmuletInfo)_o1_;
			if (!pagemap.equals(_o_.pagemap)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += pagemap.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pagemap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

