
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class PlayerBuilder implements Marshal {
	public map.msg.FighterBuilder basic;
	public int serverid;
	public int gender;
	public int profession;
	public java.lang.String name;
	public int level;
	public int viplevel;
	public java.lang.String familyname;
	public int iscantakeparty;
	public java.util.HashSet<Integer> taskmonsters;
	public int dressid;
	public int rideid; // 坐骑id
	public int ridestatus; // 见cfg.MountType. 0表示无,1 骑行 2飞行
	public int titleid;
	public int fabaoid;
	public java.util.HashMap<Integer,Integer> fabaoskills;
	public java.util.ArrayList<Integer> fabaobuffs;
	public java.util.HashMap<Integer,Integer> roleskills;
	public java.util.HashMap<Integer,Integer> amulets;
	public java.util.ArrayList<map.msg.EquipBrief> equips;
	public java.util.ArrayList<Float> attrs;
	public int combatpower;
	public int pkstate; // cfg.fight.PKState
	public map.msg.Team team;
	public java.util.ArrayList<map.msg.PetBuilder> pets;
	public java.util.ArrayList<map.msg.RoleMapContext> last;
	public map.msg.RoleMapContext cur;
	public byte ready;

	public PlayerBuilder() {
		basic = new map.msg.FighterBuilder();
		name = "";
		familyname = "";
		taskmonsters = new java.util.HashSet<Integer>();
		fabaoskills = new java.util.HashMap<Integer,Integer>();
		fabaobuffs = new java.util.ArrayList<Integer>();
		roleskills = new java.util.HashMap<Integer,Integer>();
		amulets = new java.util.HashMap<Integer,Integer>();
		equips = new java.util.ArrayList<map.msg.EquipBrief>();
		attrs = new java.util.ArrayList<Float>();
		team = new map.msg.Team();
		pets = new java.util.ArrayList<map.msg.PetBuilder>();
		last = new java.util.ArrayList<map.msg.RoleMapContext>();
		cur = new map.msg.RoleMapContext();
	}

	public PlayerBuilder(map.msg.FighterBuilder _basic_, int _serverid_, int _gender_, int _profession_, java.lang.String _name_, int _level_, int _viplevel_, java.lang.String _familyname_, int _iscantakeparty_, java.util.HashSet<Integer> _taskmonsters_, int _dressid_, int _rideid_, int _ridestatus_, int _titleid_, int _fabaoid_, java.util.HashMap<Integer,Integer> _fabaoskills_, java.util.ArrayList<Integer> _fabaobuffs_, java.util.HashMap<Integer,Integer> _roleskills_, java.util.HashMap<Integer,Integer> _amulets_, java.util.ArrayList<map.msg.EquipBrief> _equips_, java.util.ArrayList<Float> _attrs_, int _combatpower_, int _pkstate_, map.msg.Team _team_, java.util.ArrayList<map.msg.PetBuilder> _pets_, java.util.ArrayList<map.msg.RoleMapContext> _last_, map.msg.RoleMapContext _cur_, byte _ready_) {
		this.basic = _basic_;
		this.serverid = _serverid_;
		this.gender = _gender_;
		this.profession = _profession_;
		this.name = _name_;
		this.level = _level_;
		this.viplevel = _viplevel_;
		this.familyname = _familyname_;
		this.iscantakeparty = _iscantakeparty_;
		this.taskmonsters = _taskmonsters_;
		this.dressid = _dressid_;
		this.rideid = _rideid_;
		this.ridestatus = _ridestatus_;
		this.titleid = _titleid_;
		this.fabaoid = _fabaoid_;
		this.fabaoskills = _fabaoskills_;
		this.fabaobuffs = _fabaobuffs_;
		this.roleskills = _roleskills_;
		this.amulets = _amulets_;
		this.equips = _equips_;
		this.attrs = _attrs_;
		this.combatpower = _combatpower_;
		this.pkstate = _pkstate_;
		this.team = _team_;
		this.pets = _pets_;
		this.last = _last_;
		this.cur = _cur_;
		this.ready = _ready_;
	}

	public final boolean _validator_() {
		if (!basic._validator_()) return false;
		for (map.msg.EquipBrief _v_ : equips)
			if (!_v_._validator_()) return false;
		if (!team._validator_()) return false;
		for (map.msg.PetBuilder _v_ : pets)
			if (!_v_._validator_()) return false;
		for (map.msg.RoleMapContext _v_ : last)
			if (!_v_._validator_()) return false;
		if (!cur._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(basic);
		_os_.marshal(serverid);
		_os_.marshal(gender);
		_os_.marshal(profession);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(level);
		_os_.marshal(viplevel);
		_os_.marshal(familyname, "UTF-16LE");
		_os_.marshal(iscantakeparty);
		_os_.compact_uint32(taskmonsters.size());
		for (Integer _v_ : taskmonsters) {
			_os_.marshal(_v_);
		}
		_os_.marshal(dressid);
		_os_.marshal(rideid);
		_os_.marshal(ridestatus);
		_os_.marshal(titleid);
		_os_.marshal(fabaoid);
		_os_.compact_uint32(fabaoskills.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : fabaoskills.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(fabaobuffs.size());
		for (Integer _v_ : fabaobuffs) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(roleskills.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : roleskills.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(amulets.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : amulets.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(equips.size());
		for (map.msg.EquipBrief _v_ : equips) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(attrs.size());
		for (Float _v_ : attrs) {
			_os_.marshal(_v_);
		}
		_os_.marshal(combatpower);
		_os_.marshal(pkstate);
		_os_.marshal(team);
		_os_.compact_uint32(pets.size());
		for (map.msg.PetBuilder _v_ : pets) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(last.size());
		for (map.msg.RoleMapContext _v_ : last) {
			_os_.marshal(_v_);
		}
		_os_.marshal(cur);
		_os_.marshal(ready);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		basic.unmarshal(_os_);
		serverid = _os_.unmarshal_int();
		gender = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		name = _os_.unmarshal_String("UTF-16LE");
		level = _os_.unmarshal_int();
		viplevel = _os_.unmarshal_int();
		familyname = _os_.unmarshal_String("UTF-16LE");
		iscantakeparty = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			taskmonsters.add(_v_);
		}
		dressid = _os_.unmarshal_int();
		rideid = _os_.unmarshal_int();
		ridestatus = _os_.unmarshal_int();
		titleid = _os_.unmarshal_int();
		fabaoid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			fabaoskills.put(_k_, _v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			fabaobuffs.add(_v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			roleskills.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			amulets.put(_k_, _v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.EquipBrief _v_ = new map.msg.EquipBrief();
			_v_.unmarshal(_os_);
			equips.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			float _v_;
			_v_ = _os_.unmarshal_float();
			attrs.add(_v_);
		}
		combatpower = _os_.unmarshal_int();
		pkstate = _os_.unmarshal_int();
		team.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.PetBuilder _v_ = new map.msg.PetBuilder();
			_v_.unmarshal(_os_);
			pets.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.RoleMapContext _v_ = new map.msg.RoleMapContext();
			_v_.unmarshal(_os_);
			last.add(_v_);
		}
		cur.unmarshal(_os_);
		ready = _os_.unmarshal_byte();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerBuilder) {
			PlayerBuilder _o_ = (PlayerBuilder)_o1_;
			if (!basic.equals(_o_.basic)) return false;
			if (serverid != _o_.serverid) return false;
			if (gender != _o_.gender) return false;
			if (profession != _o_.profession) return false;
			if (!name.equals(_o_.name)) return false;
			if (level != _o_.level) return false;
			if (viplevel != _o_.viplevel) return false;
			if (!familyname.equals(_o_.familyname)) return false;
			if (iscantakeparty != _o_.iscantakeparty) return false;
			if (!taskmonsters.equals(_o_.taskmonsters)) return false;
			if (dressid != _o_.dressid) return false;
			if (rideid != _o_.rideid) return false;
			if (ridestatus != _o_.ridestatus) return false;
			if (titleid != _o_.titleid) return false;
			if (fabaoid != _o_.fabaoid) return false;
			if (!fabaoskills.equals(_o_.fabaoskills)) return false;
			if (!fabaobuffs.equals(_o_.fabaobuffs)) return false;
			if (!roleskills.equals(_o_.roleskills)) return false;
			if (!amulets.equals(_o_.amulets)) return false;
			if (!equips.equals(_o_.equips)) return false;
			if (!attrs.equals(_o_.attrs)) return false;
			if (combatpower != _o_.combatpower) return false;
			if (pkstate != _o_.pkstate) return false;
			if (!team.equals(_o_.team)) return false;
			if (!pets.equals(_o_.pets)) return false;
			if (!last.equals(_o_.last)) return false;
			if (!cur.equals(_o_.cur)) return false;
			if (ready != _o_.ready) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += basic.hashCode();
		_h_ += serverid;
		_h_ += gender;
		_h_ += profession;
		_h_ += name.hashCode();
		_h_ += level;
		_h_ += viplevel;
		_h_ += familyname.hashCode();
		_h_ += iscantakeparty;
		_h_ += taskmonsters.hashCode();
		_h_ += dressid;
		_h_ += rideid;
		_h_ += ridestatus;
		_h_ += titleid;
		_h_ += fabaoid;
		_h_ += fabaoskills.hashCode();
		_h_ += fabaobuffs.hashCode();
		_h_ += roleskills.hashCode();
		_h_ += amulets.hashCode();
		_h_ += equips.hashCode();
		_h_ += attrs.hashCode();
		_h_ += combatpower;
		_h_ += pkstate;
		_h_ += team.hashCode();
		_h_ += pets.hashCode();
		_h_ += last.hashCode();
		_h_ += cur.hashCode();
		_h_ += (int)ready;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(basic).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(profession).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(level).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(iscantakeparty).append(",");
		_sb_.append(taskmonsters).append(",");
		_sb_.append(dressid).append(",");
		_sb_.append(rideid).append(",");
		_sb_.append(ridestatus).append(",");
		_sb_.append(titleid).append(",");
		_sb_.append(fabaoid).append(",");
		_sb_.append(fabaoskills).append(",");
		_sb_.append(fabaobuffs).append(",");
		_sb_.append(roleskills).append(",");
		_sb_.append(amulets).append(",");
		_sb_.append(equips).append(",");
		_sb_.append(attrs).append(",");
		_sb_.append(combatpower).append(",");
		_sb_.append(pkstate).append(",");
		_sb_.append(team).append(",");
		_sb_.append(pets).append(",");
		_sb_.append(last).append(",");
		_sb_.append(cur).append(",");
		_sb_.append(ready).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

