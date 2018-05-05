
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class LayoutInfo implements Marshal {
	public int id;
	public int completed;
	public java.util.ArrayList<Integer> openentryids;
	public java.util.ArrayList<Integer> openexitids;
	public byte showglobaltips;

	public LayoutInfo() {
		openentryids = new java.util.ArrayList<Integer>();
		openexitids = new java.util.ArrayList<Integer>();
	}

	public LayoutInfo(int _id_, int _completed_, java.util.ArrayList<Integer> _openentryids_, java.util.ArrayList<Integer> _openexitids_, byte _showglobaltips_) {
		this.id = _id_;
		this.completed = _completed_;
		this.openentryids = _openentryids_;
		this.openexitids = _openexitids_;
		this.showglobaltips = _showglobaltips_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(completed);
		_os_.compact_uint32(openentryids.size());
		for (Integer _v_ : openentryids) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(openexitids.size());
		for (Integer _v_ : openexitids) {
			_os_.marshal(_v_);
		}
		_os_.marshal(showglobaltips);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_int();
		completed = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			openentryids.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			openexitids.add(_v_);
		}
		showglobaltips = _os_.unmarshal_byte();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LayoutInfo) {
			LayoutInfo _o_ = (LayoutInfo)_o1_;
			if (id != _o_.id) return false;
			if (completed != _o_.completed) return false;
			if (!openentryids.equals(_o_.openentryids)) return false;
			if (!openexitids.equals(_o_.openexitids)) return false;
			if (showglobaltips != _o_.showglobaltips) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += id;
		_h_ += completed;
		_h_ += openentryids.hashCode();
		_h_ += openexitids.hashCode();
		_h_ += (int)showglobaltips;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(completed).append(",");
		_sb_.append(openentryids).append(",");
		_sb_.append(openexitids).append(",");
		_sb_.append(showglobaltips).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

