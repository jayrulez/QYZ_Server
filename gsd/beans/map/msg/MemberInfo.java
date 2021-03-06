
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class MemberInfo implements Marshal {
	public long roleid;
	public int serverid;
	public java.lang.String name;
	public int profession;
	public int viplevel; // vip等级
	public int level;
	public int gender;
	public int combatpower;

	public MemberInfo() {
		name = "";
	}

	public MemberInfo(long _roleid_, int _serverid_, java.lang.String _name_, int _profession_, int _viplevel_, int _level_, int _gender_, int _combatpower_) {
		this.roleid = _roleid_;
		this.serverid = _serverid_;
		this.name = _name_;
		this.profession = _profession_;
		this.viplevel = _viplevel_;
		this.level = _level_;
		this.gender = _gender_;
		this.combatpower = _combatpower_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(serverid);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(profession);
		_os_.marshal(viplevel);
		_os_.marshal(level);
		_os_.marshal(gender);
		_os_.marshal(combatpower);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		serverid = _os_.unmarshal_int();
		name = _os_.unmarshal_String("UTF-16LE");
		profession = _os_.unmarshal_int();
		viplevel = _os_.unmarshal_int();
		level = _os_.unmarshal_int();
		gender = _os_.unmarshal_int();
		combatpower = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MemberInfo) {
			MemberInfo _o_ = (MemberInfo)_o1_;
			if (roleid != _o_.roleid) return false;
			if (serverid != _o_.serverid) return false;
			if (!name.equals(_o_.name)) return false;
			if (profession != _o_.profession) return false;
			if (viplevel != _o_.viplevel) return false;
			if (level != _o_.level) return false;
			if (gender != _o_.gender) return false;
			if (combatpower != _o_.combatpower) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += serverid;
		_h_ += name.hashCode();
		_h_ += profession;
		_h_ += viplevel;
		_h_ += level;
		_h_ += gender;
		_h_ += combatpower;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append(level).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(combatpower).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

