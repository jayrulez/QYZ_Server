
package lx.gs.role.title.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleTitle implements Marshal {
	public int activekey; // 当前激活的称号id
	public int activetype; // 当前激活的称号类型
	public java.util.HashMap<Integer,lx.gs.role.title.msg.GroupTitle> titles; // 称号信息，key为不同的类型，如战力榜，竞技场榜

	public RoleTitle() {
		titles = new java.util.HashMap<Integer,lx.gs.role.title.msg.GroupTitle>();
	}

	public RoleTitle(int _activekey_, int _activetype_, java.util.HashMap<Integer,lx.gs.role.title.msg.GroupTitle> _titles_) {
		this.activekey = _activekey_;
		this.activetype = _activetype_;
		this.titles = _titles_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.role.title.msg.GroupTitle> _e_ : titles.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(activekey);
		_os_.marshal(activetype);
		_os_.compact_uint32(titles.size());
		for (java.util.Map.Entry<Integer, lx.gs.role.title.msg.GroupTitle> _e_ : titles.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		activekey = _os_.unmarshal_int();
		activetype = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.role.title.msg.GroupTitle _v_ = new lx.gs.role.title.msg.GroupTitle();
			_v_.unmarshal(_os_);
			titles.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleTitle) {
			RoleTitle _o_ = (RoleTitle)_o1_;
			if (activekey != _o_.activekey) return false;
			if (activetype != _o_.activetype) return false;
			if (!titles.equals(_o_.titles)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += activekey;
		_h_ += activetype;
		_h_ += titles.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(activekey).append(",");
		_sb_.append(activetype).append(",");
		_sb_.append(titles).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

