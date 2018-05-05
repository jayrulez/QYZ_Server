
package lx.gs.amulet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class AmuletPage implements Marshal {
	public int pageindex; // 护符页的id
	public java.util.HashMap<Integer,lx.gs.amulet.AmuletPropperty> propmap; // 护符属性

	public AmuletPage() {
		propmap = new java.util.HashMap<Integer,lx.gs.amulet.AmuletPropperty>();
	}

	public AmuletPage(int _pageindex_, java.util.HashMap<Integer,lx.gs.amulet.AmuletPropperty> _propmap_) {
		this.pageindex = _pageindex_;
		this.propmap = _propmap_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.amulet.AmuletPropperty> _e_ : propmap.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(pageindex);
		_os_.compact_uint32(propmap.size());
		for (java.util.Map.Entry<Integer, lx.gs.amulet.AmuletPropperty> _e_ : propmap.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		pageindex = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.amulet.AmuletPropperty _v_ = new lx.gs.amulet.AmuletPropperty();
			_v_.unmarshal(_os_);
			propmap.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AmuletPage) {
			AmuletPage _o_ = (AmuletPage)_o1_;
			if (pageindex != _o_.pageindex) return false;
			if (!propmap.equals(_o_.propmap)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += pageindex;
		_h_ += propmap.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pageindex).append(",");
		_sb_.append(propmap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

