
package lx.gs.leaderboard.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SLatestLeaderBoard__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SLatestLeaderBoard extends __SLatestLeaderBoard__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582141;

	public int getType() {
		return 6582141;
	}

	public java.util.HashMap<Integer,lx.gs.leaderboard.msg.BoardInfo> latestbord;

	public SLatestLeaderBoard() {
		latestbord = new java.util.HashMap<Integer,lx.gs.leaderboard.msg.BoardInfo>();
	}

	public SLatestLeaderBoard(java.util.HashMap<Integer,lx.gs.leaderboard.msg.BoardInfo> _latestbord_) {
		this.latestbord = _latestbord_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.leaderboard.msg.BoardInfo> _e_ : latestbord.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(latestbord.size());
		for (java.util.Map.Entry<Integer, lx.gs.leaderboard.msg.BoardInfo> _e_ : latestbord.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.leaderboard.msg.BoardInfo _v_ = new lx.gs.leaderboard.msg.BoardInfo();
			_v_.unmarshal(_os_);
			latestbord.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SLatestLeaderBoard) {
			SLatestLeaderBoard _o_ = (SLatestLeaderBoard)_o1_;
			if (!latestbord.equals(_o_.latestbord)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += latestbord.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(latestbord).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

