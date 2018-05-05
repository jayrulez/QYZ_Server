
package lx.matcher;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __GAddMultiMatch__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class GAddMultiMatch extends __GAddMultiMatch__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6647987;

	public int getType() {
		return 6647987;
	}

	public long gid;
	public java.util.ArrayList<lx.gs.map.msg.MatchMultiStroyInfo> members;

	public GAddMultiMatch() {
		members = new java.util.ArrayList<lx.gs.map.msg.MatchMultiStroyInfo>();
	}

	public GAddMultiMatch(long _gid_, java.util.ArrayList<lx.gs.map.msg.MatchMultiStroyInfo> _members_) {
		this.gid = _gid_;
		this.members = _members_;
	}

	public final boolean _validator_() {
		for (lx.gs.map.msg.MatchMultiStroyInfo _v_ : members)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(gid);
		_os_.compact_uint32(members.size());
		for (lx.gs.map.msg.MatchMultiStroyInfo _v_ : members) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		gid = _os_.unmarshal_long();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.map.msg.MatchMultiStroyInfo _v_ = new lx.gs.map.msg.MatchMultiStroyInfo();
			_v_.unmarshal(_os_);
			members.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GAddMultiMatch) {
			GAddMultiMatch _o_ = (GAddMultiMatch)_o1_;
			if (gid != _o_.gid) return false;
			if (!members.equals(_o_.members)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)gid;
		_h_ += members.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(gid).append(",");
		_sb_.append(members).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

