
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class EnemyShowInfo implements Marshal {
	public long roleid;
	public java.lang.String rolename;
	public int level;
	public int viplevel;
	public int profession;
	public int gender;
	public int attackpower; // 攻击力
	public int online; // 是在在线，1为在线，0为不在线
	public long time;
	public int killtime; // 击杀该玩家的次数
	public int bekilltime; // 被该玩家击杀的次数

	public EnemyShowInfo() {
		rolename = "";
	}

	public EnemyShowInfo(long _roleid_, java.lang.String _rolename_, int _level_, int _viplevel_, int _profession_, int _gender_, int _attackpower_, int _online_, long _time_, int _killtime_, int _bekilltime_) {
		this.roleid = _roleid_;
		this.rolename = _rolename_;
		this.level = _level_;
		this.viplevel = _viplevel_;
		this.profession = _profession_;
		this.gender = _gender_;
		this.attackpower = _attackpower_;
		this.online = _online_;
		this.time = _time_;
		this.killtime = _killtime_;
		this.bekilltime = _bekilltime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(rolename, "UTF-16LE");
		_os_.marshal(level);
		_os_.marshal(viplevel);
		_os_.marshal(profession);
		_os_.marshal(gender);
		_os_.marshal(attackpower);
		_os_.marshal(online);
		_os_.marshal(time);
		_os_.marshal(killtime);
		_os_.marshal(bekilltime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		rolename = _os_.unmarshal_String("UTF-16LE");
		level = _os_.unmarshal_int();
		viplevel = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		gender = _os_.unmarshal_int();
		attackpower = _os_.unmarshal_int();
		online = _os_.unmarshal_int();
		time = _os_.unmarshal_long();
		killtime = _os_.unmarshal_int();
		bekilltime = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EnemyShowInfo) {
			EnemyShowInfo _o_ = (EnemyShowInfo)_o1_;
			if (roleid != _o_.roleid) return false;
			if (!rolename.equals(_o_.rolename)) return false;
			if (level != _o_.level) return false;
			if (viplevel != _o_.viplevel) return false;
			if (profession != _o_.profession) return false;
			if (gender != _o_.gender) return false;
			if (attackpower != _o_.attackpower) return false;
			if (online != _o_.online) return false;
			if (time != _o_.time) return false;
			if (killtime != _o_.killtime) return false;
			if (bekilltime != _o_.bekilltime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += rolename.hashCode();
		_h_ += level;
		_h_ += viplevel;
		_h_ += profession;
		_h_ += gender;
		_h_ += attackpower;
		_h_ += online;
		_h_ += (int)time;
		_h_ += killtime;
		_h_ += bekilltime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append("T").append(rolename.length()).append(",");
		_sb_.append(level).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(attackpower).append(",");
		_sb_.append(online).append(",");
		_sb_.append(time).append(",");
		_sb_.append(killtime).append(",");
		_sb_.append(bekilltime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

