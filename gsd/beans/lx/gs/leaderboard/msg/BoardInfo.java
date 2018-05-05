
package lx.gs.leaderboard.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class BoardInfo implements Marshal {
	public java.util.ArrayList<lx.gs.leaderboard.msg.BoardEntry> info;

	public BoardInfo() {
		info = new java.util.ArrayList<lx.gs.leaderboard.msg.BoardEntry>();
	}

	public BoardInfo(java.util.ArrayList<lx.gs.leaderboard.msg.BoardEntry> _info_) {
		this.info = _info_;
	}

	public final boolean _validator_() {
		for (lx.gs.leaderboard.msg.BoardEntry _v_ : info)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(info.size());
		for (lx.gs.leaderboard.msg.BoardEntry _v_ : info) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.leaderboard.msg.BoardEntry _v_ = new lx.gs.leaderboard.msg.BoardEntry();
			_v_.unmarshal(_os_);
			info.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BoardInfo) {
			BoardInfo _o_ = (BoardInfo)_o1_;
			if (!info.equals(_o_.info)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += info.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(info).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

