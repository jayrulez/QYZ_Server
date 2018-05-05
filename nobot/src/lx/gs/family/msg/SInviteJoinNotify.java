
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SInviteJoinNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SInviteJoinNotify extends __SInviteJoinNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6585253;

	public int getType() {
		return 6585253;
	}

	public long inviteroleid;
	public java.lang.String inviterolename;
	public long familyid;
	public java.lang.String familyname;

	public SInviteJoinNotify() {
		inviterolename = "";
		familyname = "";
	}

	public SInviteJoinNotify(long _inviteroleid_, java.lang.String _inviterolename_, long _familyid_, java.lang.String _familyname_) {
		this.inviteroleid = _inviteroleid_;
		this.inviterolename = _inviterolename_;
		this.familyid = _familyid_;
		this.familyname = _familyname_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(inviteroleid);
		_os_.marshal(inviterolename, "UTF-16LE");
		_os_.marshal(familyid);
		_os_.marshal(familyname, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		inviteroleid = _os_.unmarshal_long();
		inviterolename = _os_.unmarshal_String("UTF-16LE");
		familyid = _os_.unmarshal_long();
		familyname = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SInviteJoinNotify) {
			SInviteJoinNotify _o_ = (SInviteJoinNotify)_o1_;
			if (inviteroleid != _o_.inviteroleid) return false;
			if (!inviterolename.equals(_o_.inviterolename)) return false;
			if (familyid != _o_.familyid) return false;
			if (!familyname.equals(_o_.familyname)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)inviteroleid;
		_h_ += inviterolename.hashCode();
		_h_ += (int)familyid;
		_h_ += familyname.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(inviteroleid).append(",");
		_sb_.append("T").append(inviterolename.length()).append(",");
		_sb_.append(familyid).append(",");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

