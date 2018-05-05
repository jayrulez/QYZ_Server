
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class ChapterInfo implements Marshal {
	public java.util.ArrayList<Integer> sectionstars; // 对应关卡1,2,,,,
	public java.util.ArrayList<Integer> obtainrewardindexs;

	public ChapterInfo() {
		sectionstars = new java.util.ArrayList<Integer>();
		obtainrewardindexs = new java.util.ArrayList<Integer>();
	}

	public ChapterInfo(java.util.ArrayList<Integer> _sectionstars_, java.util.ArrayList<Integer> _obtainrewardindexs_) {
		this.sectionstars = _sectionstars_;
		this.obtainrewardindexs = _obtainrewardindexs_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(sectionstars.size());
		for (Integer _v_ : sectionstars) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(obtainrewardindexs.size());
		for (Integer _v_ : obtainrewardindexs) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			sectionstars.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			obtainrewardindexs.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ChapterInfo) {
			ChapterInfo _o_ = (ChapterInfo)_o1_;
			if (!sectionstars.equals(_o_.sectionstars)) return false;
			if (!obtainrewardindexs.equals(_o_.obtainrewardindexs)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += sectionstars.hashCode();
		_h_ += obtainrewardindexs.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(sectionstars).append(",");
		_sb_.append(obtainrewardindexs).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

