
package lx.gs.arena.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChallenge__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChallenge extends __SChallenge__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583897;

	public int getType() {
		return 6583897;
	}

	public int newrank;
	public int challengesuccnum;
	public java.util.ArrayList<lx.gs.arena.msg.ChallangeInfo> challengeranks;

	public SChallenge() {
		challengeranks = new java.util.ArrayList<lx.gs.arena.msg.ChallangeInfo>();
	}

	public SChallenge(int _newrank_, int _challengesuccnum_, java.util.ArrayList<lx.gs.arena.msg.ChallangeInfo> _challengeranks_) {
		this.newrank = _newrank_;
		this.challengesuccnum = _challengesuccnum_;
		this.challengeranks = _challengeranks_;
	}

	public final boolean _validator_() {
		for (lx.gs.arena.msg.ChallangeInfo _v_ : challengeranks)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(newrank);
		_os_.marshal(challengesuccnum);
		_os_.compact_uint32(challengeranks.size());
		for (lx.gs.arena.msg.ChallangeInfo _v_ : challengeranks) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		newrank = _os_.unmarshal_int();
		challengesuccnum = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.arena.msg.ChallangeInfo _v_ = new lx.gs.arena.msg.ChallangeInfo();
			_v_.unmarshal(_os_);
			challengeranks.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChallenge) {
			SChallenge _o_ = (SChallenge)_o1_;
			if (newrank != _o_.newrank) return false;
			if (challengesuccnum != _o_.challengesuccnum) return false;
			if (!challengeranks.equals(_o_.challengeranks)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += newrank;
		_h_ += challengesuccnum;
		_h_ += challengeranks.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(newrank).append(",");
		_sb_.append(challengesuccnum).append(",");
		_sb_.append(challengeranks).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

