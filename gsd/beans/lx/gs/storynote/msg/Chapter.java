
package lx.gs.storynote.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Chapter implements Marshal {
	public java.util.ArrayList<Integer> notes;

	public Chapter() {
		notes = new java.util.ArrayList<Integer>();
	}

	public Chapter(java.util.ArrayList<Integer> _notes_) {
		this.notes = _notes_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(notes.size());
		for (Integer _v_ : notes) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			notes.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Chapter) {
			Chapter _o_ = (Chapter)_o1_;
			if (!notes.equals(_o_.notes)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += notes.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(notes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

