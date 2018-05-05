
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreateFamilyTeam__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreateFamilyTeam extends __XCreateFamilyTeam__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6697156;

	public int getType() {
		return 6697156;
	}

	public int serverid;
	public int level;
	public java.util.ArrayList<map.msg.FamilyTeamMember> members;

	public XCreateFamilyTeam() {
		members = new java.util.ArrayList<map.msg.FamilyTeamMember>();
	}

	public XCreateFamilyTeam(int _serverid_, int _level_, java.util.ArrayList<map.msg.FamilyTeamMember> _members_) {
		this.serverid = _serverid_;
		this.level = _level_;
		this.members = _members_;
	}

	public final boolean _validator_() {
		for (map.msg.FamilyTeamMember _v_ : members)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(serverid);
		_os_.marshal(level);
		_os_.compact_uint32(members.size());
		for (map.msg.FamilyTeamMember _v_ : members) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		serverid = _os_.unmarshal_int();
		level = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.FamilyTeamMember _v_ = new map.msg.FamilyTeamMember();
			_v_.unmarshal(_os_);
			members.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreateFamilyTeam) {
			XCreateFamilyTeam _o_ = (XCreateFamilyTeam)_o1_;
			if (serverid != _o_.serverid) return false;
			if (level != _o_.level) return false;
			if (!members.equals(_o_.members)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += serverid;
		_h_ += level;
		_h_ += members.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(serverid).append(",");
		_sb_.append(level).append(",");
		_sb_.append(members).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

