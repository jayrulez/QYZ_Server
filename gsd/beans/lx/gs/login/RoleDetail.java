
package lx.gs.login;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleDetail implements Marshal {
	public long roleid;
	public int serverid;
	public java.lang.String rolename;
	public int profession;
	public int gender;
	public int level;
	public long vipexp;
	public int viplevel;
	public long familyid;
	public java.util.HashMap<Integer,Long> currencys; // cfg.currency.CurrencyType -> num
	public int combatpower;
	public java.util.HashMap<Integer,Float> attrs;
	public long todaytotaladdmonsterexp;
	public int changenametimes;
	public long creatroletime;

	public RoleDetail() {
		rolename = "";
		currencys = new java.util.HashMap<Integer,Long>();
		attrs = new java.util.HashMap<Integer,Float>();
	}

	public RoleDetail(long _roleid_, int _serverid_, java.lang.String _rolename_, int _profession_, int _gender_, int _level_, long _vipexp_, int _viplevel_, long _familyid_, java.util.HashMap<Integer,Long> _currencys_, int _combatpower_, java.util.HashMap<Integer,Float> _attrs_, long _todaytotaladdmonsterexp_, int _changenametimes_, long _creatroletime_) {
		this.roleid = _roleid_;
		this.serverid = _serverid_;
		this.rolename = _rolename_;
		this.profession = _profession_;
		this.gender = _gender_;
		this.level = _level_;
		this.vipexp = _vipexp_;
		this.viplevel = _viplevel_;
		this.familyid = _familyid_;
		this.currencys = _currencys_;
		this.combatpower = _combatpower_;
		this.attrs = _attrs_;
		this.todaytotaladdmonsterexp = _todaytotaladdmonsterexp_;
		this.changenametimes = _changenametimes_;
		this.creatroletime = _creatroletime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(serverid);
		_os_.marshal(rolename, "UTF-16LE");
		_os_.marshal(profession);
		_os_.marshal(gender);
		_os_.marshal(level);
		_os_.marshal(vipexp);
		_os_.marshal(viplevel);
		_os_.marshal(familyid);
		_os_.compact_uint32(currencys.size());
		for (java.util.Map.Entry<Integer, Long> _e_ : currencys.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(combatpower);
		_os_.compact_uint32(attrs.size());
		for (java.util.Map.Entry<Integer, Float> _e_ : attrs.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(todaytotaladdmonsterexp);
		_os_.marshal(changenametimes);
		_os_.marshal(creatroletime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		serverid = _os_.unmarshal_int();
		rolename = _os_.unmarshal_String("UTF-16LE");
		profession = _os_.unmarshal_int();
		gender = _os_.unmarshal_int();
		level = _os_.unmarshal_int();
		vipexp = _os_.unmarshal_long();
		viplevel = _os_.unmarshal_int();
		familyid = _os_.unmarshal_long();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			long _v_;
			_v_ = _os_.unmarshal_long();
			currencys.put(_k_, _v_);
		}
		combatpower = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			float _v_;
			_v_ = _os_.unmarshal_float();
			attrs.put(_k_, _v_);
		}
		todaytotaladdmonsterexp = _os_.unmarshal_long();
		changenametimes = _os_.unmarshal_int();
		creatroletime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleDetail) {
			RoleDetail _o_ = (RoleDetail)_o1_;
			if (roleid != _o_.roleid) return false;
			if (serverid != _o_.serverid) return false;
			if (!rolename.equals(_o_.rolename)) return false;
			if (profession != _o_.profession) return false;
			if (gender != _o_.gender) return false;
			if (level != _o_.level) return false;
			if (vipexp != _o_.vipexp) return false;
			if (viplevel != _o_.viplevel) return false;
			if (familyid != _o_.familyid) return false;
			if (!currencys.equals(_o_.currencys)) return false;
			if (combatpower != _o_.combatpower) return false;
			if (!attrs.equals(_o_.attrs)) return false;
			if (todaytotaladdmonsterexp != _o_.todaytotaladdmonsterexp) return false;
			if (changenametimes != _o_.changenametimes) return false;
			if (creatroletime != _o_.creatroletime) return false;
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
		_h_ += gender;
		_h_ += level;
		_h_ += (int)vipexp;
		_h_ += viplevel;
		_h_ += (int)familyid;
		_h_ += currencys.hashCode();
		_h_ += combatpower;
		_h_ += attrs.hashCode();
		_h_ += (int)todaytotaladdmonsterexp;
		_h_ += changenametimes;
		_h_ += (int)creatroletime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append("T").append(rolename.length()).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(level).append(",");
		_sb_.append(vipexp).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append(familyid).append(",");
		_sb_.append(currencys).append(",");
		_sb_.append(combatpower).append(",");
		_sb_.append(attrs).append(",");
		_sb_.append(todaytotaladdmonsterexp).append(",");
		_sb_.append(changenametimes).append(",");
		_sb_.append(creatroletime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

