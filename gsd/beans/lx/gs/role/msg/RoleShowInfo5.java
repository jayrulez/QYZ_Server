
package lx.gs.role.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleShowInfo5 implements Marshal {
	public long roleid;
	public int serverid;
	public java.lang.String name;
	public int profession;
	public int gender;
	public int level; // 级别
	public int viplevel; // vip等级
	public int combatpower;
	public java.util.ArrayList<lx.gs.role.msg.EquipInfo> equips;
	public int dressid;
	public int title; // 称号的key
	public java.lang.String familyname; // 家族名字
	public int familyjob; // 家族职位
	public int familylevel; // 家族等级
	public java.lang.String lovername; // 情人的角色id
	public java.util.HashMap<Integer,Float> fightattrs; // 战斗属性值
	public long lastonlinetime; // 上次在线时间

	public RoleShowInfo5() {
		name = "";
		equips = new java.util.ArrayList<lx.gs.role.msg.EquipInfo>();
		familyname = "";
		lovername = "";
		fightattrs = new java.util.HashMap<Integer,Float>();
	}

	public RoleShowInfo5(long _roleid_, int _serverid_, java.lang.String _name_, int _profession_, int _gender_, int _level_, int _viplevel_, int _combatpower_, java.util.ArrayList<lx.gs.role.msg.EquipInfo> _equips_, int _dressid_, int _title_, java.lang.String _familyname_, int _familyjob_, int _familylevel_, java.lang.String _lovername_, java.util.HashMap<Integer,Float> _fightattrs_, long _lastonlinetime_) {
		this.roleid = _roleid_;
		this.serverid = _serverid_;
		this.name = _name_;
		this.profession = _profession_;
		this.gender = _gender_;
		this.level = _level_;
		this.viplevel = _viplevel_;
		this.combatpower = _combatpower_;
		this.equips = _equips_;
		this.dressid = _dressid_;
		this.title = _title_;
		this.familyname = _familyname_;
		this.familyjob = _familyjob_;
		this.familylevel = _familylevel_;
		this.lovername = _lovername_;
		this.fightattrs = _fightattrs_;
		this.lastonlinetime = _lastonlinetime_;
	}

	public final boolean _validator_() {
		for (lx.gs.role.msg.EquipInfo _v_ : equips)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(serverid);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(profession);
		_os_.marshal(gender);
		_os_.marshal(level);
		_os_.marshal(viplevel);
		_os_.marshal(combatpower);
		_os_.compact_uint32(equips.size());
		for (lx.gs.role.msg.EquipInfo _v_ : equips) {
			_os_.marshal(_v_);
		}
		_os_.marshal(dressid);
		_os_.marshal(title);
		_os_.marshal(familyname, "UTF-16LE");
		_os_.marshal(familyjob);
		_os_.marshal(familylevel);
		_os_.marshal(lovername, "UTF-16LE");
		_os_.compact_uint32(fightattrs.size());
		for (java.util.Map.Entry<Integer, Float> _e_ : fightattrs.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(lastonlinetime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		serverid = _os_.unmarshal_int();
		name = _os_.unmarshal_String("UTF-16LE");
		profession = _os_.unmarshal_int();
		gender = _os_.unmarshal_int();
		level = _os_.unmarshal_int();
		viplevel = _os_.unmarshal_int();
		combatpower = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.role.msg.EquipInfo _v_ = new lx.gs.role.msg.EquipInfo();
			_v_.unmarshal(_os_);
			equips.add(_v_);
		}
		dressid = _os_.unmarshal_int();
		title = _os_.unmarshal_int();
		familyname = _os_.unmarshal_String("UTF-16LE");
		familyjob = _os_.unmarshal_int();
		familylevel = _os_.unmarshal_int();
		lovername = _os_.unmarshal_String("UTF-16LE");
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			float _v_;
			_v_ = _os_.unmarshal_float();
			fightattrs.put(_k_, _v_);
		}
		lastonlinetime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleShowInfo5) {
			RoleShowInfo5 _o_ = (RoleShowInfo5)_o1_;
			if (roleid != _o_.roleid) return false;
			if (serverid != _o_.serverid) return false;
			if (!name.equals(_o_.name)) return false;
			if (profession != _o_.profession) return false;
			if (gender != _o_.gender) return false;
			if (level != _o_.level) return false;
			if (viplevel != _o_.viplevel) return false;
			if (combatpower != _o_.combatpower) return false;
			if (!equips.equals(_o_.equips)) return false;
			if (dressid != _o_.dressid) return false;
			if (title != _o_.title) return false;
			if (!familyname.equals(_o_.familyname)) return false;
			if (familyjob != _o_.familyjob) return false;
			if (familylevel != _o_.familylevel) return false;
			if (!lovername.equals(_o_.lovername)) return false;
			if (!fightattrs.equals(_o_.fightattrs)) return false;
			if (lastonlinetime != _o_.lastonlinetime) return false;
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
		_h_ += gender;
		_h_ += level;
		_h_ += viplevel;
		_h_ += combatpower;
		_h_ += equips.hashCode();
		_h_ += dressid;
		_h_ += title;
		_h_ += familyname.hashCode();
		_h_ += familyjob;
		_h_ += familylevel;
		_h_ += lovername.hashCode();
		_h_ += fightattrs.hashCode();
		_h_ += (int)lastonlinetime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(level).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append(combatpower).append(",");
		_sb_.append(equips).append(",");
		_sb_.append(dressid).append(",");
		_sb_.append(title).append(",");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(familyjob).append(",");
		_sb_.append(familylevel).append(",");
		_sb_.append("T").append(lovername.length()).append(",");
		_sb_.append(fightattrs).append(",");
		_sb_.append(lastonlinetime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

