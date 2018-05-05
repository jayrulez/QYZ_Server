
package lx.gs.dailyactivity.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Undoactiveinfos implements Marshal {
	public java.util.HashMap<Integer,lx.gs.dailyactivity.msg.Undoactiveinfo> undoactive;

	public Undoactiveinfos() {
		undoactive = new java.util.HashMap<Integer,lx.gs.dailyactivity.msg.Undoactiveinfo>();
	}

	public Undoactiveinfos(java.util.HashMap<Integer,lx.gs.dailyactivity.msg.Undoactiveinfo> _undoactive_) {
		this.undoactive = _undoactive_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.dailyactivity.msg.Undoactiveinfo> _e_ : undoactive.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(undoactive.size());
		for (java.util.Map.Entry<Integer, lx.gs.dailyactivity.msg.Undoactiveinfo> _e_ : undoactive.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.dailyactivity.msg.Undoactiveinfo _v_ = new lx.gs.dailyactivity.msg.Undoactiveinfo();
			_v_.unmarshal(_os_);
			undoactive.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Undoactiveinfos) {
			Undoactiveinfos _o_ = (Undoactiveinfos)_o1_;
			if (!undoactive.equals(_o_.undoactive)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += undoactive.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(undoactive).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

