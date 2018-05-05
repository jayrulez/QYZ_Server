
package lx.gs.arena.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetChallenge__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetChallenge extends __SGetChallenge__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573808;

	public int getType() {
		return 6573808;
	}

	public java.util.ArrayList<lx.gs.arena.msg.ChallangeInfo> challengeranks;

	public SGetChallenge() {
		challengeranks = new java.util.ArrayList<lx.gs.arena.msg.ChallangeInfo>();
	}

	public SGetChallenge(java.util.ArrayList<lx.gs.arena.msg.ChallangeInfo> _challengeranks_) {
		this.challengeranks = _challengeranks_;
	}

	public final boolean _validator_() {
		for (lx.gs.arena.msg.ChallangeInfo _v_ : challengeranks)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(challengeranks.size());
		for (lx.gs.arena.msg.ChallangeInfo _v_ : challengeranks) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.arena.msg.ChallangeInfo _v_ = new lx.gs.arena.msg.ChallangeInfo();
			_v_.unmarshal(_os_);
			challengeranks.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetChallenge) {
			SGetChallenge _o_ = (SGetChallenge)_o1_;
			if (!challengeranks.equals(_o_.challengeranks)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += challengeranks.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(challengeranks).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

