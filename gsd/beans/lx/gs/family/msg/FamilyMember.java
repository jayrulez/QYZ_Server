
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 个人家族信息
*/
public class FamilyMember implements Marshal {
	public long roleid;
	public long jointime; // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
	public long pcontribution; // 个人帮贡，跟特定家族相关，如果离开家族要重新计算该值
	public long dailybuild; // 每日的帮贡
	public int familyjob; // 家族的职位，组长，青龙使，副组长等等
	public java.lang.String rolename;
	public long lastonlinetime; // 最近一次登录的时间
	public int isonline; // 是否在线，0否；1是
	public int attackpower; // 战斗力
	public int level; // 玩家等级
	public int totalquitnum; // 退出过家族的总次数
	public int viplevel; // vip等级
	public int professiontype; // 职业信息
	public int gender; // 性别

	public FamilyMember() {
		rolename = "";
	}

	public FamilyMember(long _roleid_, long _jointime_, long _pcontribution_, long _dailybuild_, int _familyjob_, java.lang.String _rolename_, long _lastonlinetime_, int _isonline_, int _attackpower_, int _level_, int _totalquitnum_, int _viplevel_, int _professiontype_, int _gender_) {
		this.roleid = _roleid_;
		this.jointime = _jointime_;
		this.pcontribution = _pcontribution_;
		this.dailybuild = _dailybuild_;
		this.familyjob = _familyjob_;
		this.rolename = _rolename_;
		this.lastonlinetime = _lastonlinetime_;
		this.isonline = _isonline_;
		this.attackpower = _attackpower_;
		this.level = _level_;
		this.totalquitnum = _totalquitnum_;
		this.viplevel = _viplevel_;
		this.professiontype = _professiontype_;
		this.gender = _gender_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(jointime);
		_os_.marshal(pcontribution);
		_os_.marshal(dailybuild);
		_os_.marshal(familyjob);
		_os_.marshal(rolename, "UTF-16LE");
		_os_.marshal(lastonlinetime);
		_os_.marshal(isonline);
		_os_.marshal(attackpower);
		_os_.marshal(level);
		_os_.marshal(totalquitnum);
		_os_.marshal(viplevel);
		_os_.marshal(professiontype);
		_os_.marshal(gender);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		jointime = _os_.unmarshal_long();
		pcontribution = _os_.unmarshal_long();
		dailybuild = _os_.unmarshal_long();
		familyjob = _os_.unmarshal_int();
		rolename = _os_.unmarshal_String("UTF-16LE");
		lastonlinetime = _os_.unmarshal_long();
		isonline = _os_.unmarshal_int();
		attackpower = _os_.unmarshal_int();
		level = _os_.unmarshal_int();
		totalquitnum = _os_.unmarshal_int();
		viplevel = _os_.unmarshal_int();
		professiontype = _os_.unmarshal_int();
		gender = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FamilyMember) {
			FamilyMember _o_ = (FamilyMember)_o1_;
			if (roleid != _o_.roleid) return false;
			if (jointime != _o_.jointime) return false;
			if (pcontribution != _o_.pcontribution) return false;
			if (dailybuild != _o_.dailybuild) return false;
			if (familyjob != _o_.familyjob) return false;
			if (!rolename.equals(_o_.rolename)) return false;
			if (lastonlinetime != _o_.lastonlinetime) return false;
			if (isonline != _o_.isonline) return false;
			if (attackpower != _o_.attackpower) return false;
			if (level != _o_.level) return false;
			if (totalquitnum != _o_.totalquitnum) return false;
			if (viplevel != _o_.viplevel) return false;
			if (professiontype != _o_.professiontype) return false;
			if (gender != _o_.gender) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += (int)jointime;
		_h_ += (int)pcontribution;
		_h_ += (int)dailybuild;
		_h_ += familyjob;
		_h_ += rolename.hashCode();
		_h_ += (int)lastonlinetime;
		_h_ += isonline;
		_h_ += attackpower;
		_h_ += level;
		_h_ += totalquitnum;
		_h_ += viplevel;
		_h_ += professiontype;
		_h_ += gender;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(jointime).append(",");
		_sb_.append(pcontribution).append(",");
		_sb_.append(dailybuild).append(",");
		_sb_.append(familyjob).append(",");
		_sb_.append("T").append(rolename.length()).append(",");
		_sb_.append(lastonlinetime).append(",");
		_sb_.append(isonline).append(",");
		_sb_.append(attackpower).append(",");
		_sb_.append(level).append(",");
		_sb_.append(totalquitnum).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append(professiontype).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

