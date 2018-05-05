
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEctypeMemberEnter__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEctypeMemberEnter extends __SEctypeMemberEnter__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6715479;

	public int getType() {
		return 6715479;
	}

	public long id;
	public java.lang.String name;
	public int gender;
	public int profession;
	public int level;
	public java.lang.String familyname;
	public int serverid;
	public int viplevel;

	public SEctypeMemberEnter() {
		name = "";
		familyname = "";
	}

	public SEctypeMemberEnter(long _id_, java.lang.String _name_, int _gender_, int _profession_, int _level_, java.lang.String _familyname_, int _serverid_, int _viplevel_) {
		this.id = _id_;
		this.name = _name_;
		this.gender = _gender_;
		this.profession = _profession_;
		this.level = _level_;
		this.familyname = _familyname_;
		this.serverid = _serverid_;
		this.viplevel = _viplevel_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(gender);
		_os_.marshal(profession);
		_os_.marshal(level);
		_os_.marshal(familyname, "UTF-16LE");
		_os_.marshal(serverid);
		_os_.marshal(viplevel);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_long();
		name = _os_.unmarshal_String("UTF-16LE");
		gender = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		level = _os_.unmarshal_int();
		familyname = _os_.unmarshal_String("UTF-16LE");
		serverid = _os_.unmarshal_int();
		viplevel = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEctypeMemberEnter) {
			SEctypeMemberEnter _o_ = (SEctypeMemberEnter)_o1_;
			if (id != _o_.id) return false;
			if (!name.equals(_o_.name)) return false;
			if (gender != _o_.gender) return false;
			if (profession != _o_.profession) return false;
			if (level != _o_.level) return false;
			if (!familyname.equals(_o_.familyname)) return false;
			if (serverid != _o_.serverid) return false;
			if (viplevel != _o_.viplevel) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)id;
		_h_ += name.hashCode();
		_h_ += gender;
		_h_ += profession;
		_h_ += level;
		_h_ += familyname.hashCode();
		_h_ += serverid;
		_h_ += viplevel;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(level).append(",");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

