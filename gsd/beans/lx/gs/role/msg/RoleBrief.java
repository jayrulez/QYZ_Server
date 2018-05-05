
package lx.gs.role.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleBrief implements Marshal {
	public long roleid;
	public int serverid;
	public java.lang.String rolename;
	public int profession;
	public int level;
	public int viplevel;
	public int gender;
	public int dressid;
	public java.util.ArrayList<map.msg.EquipBrief> equips;

	public RoleBrief() {
		rolename = "";
		equips = new java.util.ArrayList<map.msg.EquipBrief>();
	}

	public RoleBrief(long _roleid_, int _serverid_, java.lang.String _rolename_, int _profession_, int _level_, int _viplevel_, int _gender_, int _dressid_, java.util.ArrayList<map.msg.EquipBrief> _equips_) {
		this.roleid = _roleid_;
		this.serverid = _serverid_;
		this.rolename = _rolename_;
		this.profession = _profession_;
		this.level = _level_;
		this.viplevel = _viplevel_;
		this.gender = _gender_;
		this.dressid = _dressid_;
		this.equips = _equips_;
	}

	public final boolean _validator_() {
		for (map.msg.EquipBrief _v_ : equips)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(serverid);
		_os_.marshal(rolename, "UTF-16LE");
		_os_.marshal(profession);
		_os_.marshal(level);
		_os_.marshal(viplevel);
		_os_.marshal(gender);
		_os_.marshal(dressid);
		_os_.compact_uint32(equips.size());
		for (map.msg.EquipBrief _v_ : equips) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		serverid = _os_.unmarshal_int();
		rolename = _os_.unmarshal_String("UTF-16LE");
		profession = _os_.unmarshal_int();
		level = _os_.unmarshal_int();
		viplevel = _os_.unmarshal_int();
		gender = _os_.unmarshal_int();
		dressid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.EquipBrief _v_ = new map.msg.EquipBrief();
			_v_.unmarshal(_os_);
			equips.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleBrief) {
			RoleBrief _o_ = (RoleBrief)_o1_;
			if (roleid != _o_.roleid) return false;
			if (serverid != _o_.serverid) return false;
			if (!rolename.equals(_o_.rolename)) return false;
			if (profession != _o_.profession) return false;
			if (level != _o_.level) return false;
			if (viplevel != _o_.viplevel) return false;
			if (gender != _o_.gender) return false;
			if (dressid != _o_.dressid) return false;
			if (!equips.equals(_o_.equips)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += serverid;
		_h_ += rolename.hashCode();
		_h_ += profession;
		_h_ += level;
		_h_ += viplevel;
		_h_ += gender;
		_h_ += dressid;
		_h_ += equips.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append("T").append(rolename.length()).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(level).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(dressid).append(",");
		_sb_.append(equips).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

