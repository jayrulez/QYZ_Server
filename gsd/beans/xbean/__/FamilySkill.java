
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class FamilySkill extends xdb.XBean implements xbean.FamilySkill {
	private int skillid; // 家族技能id
	private long studytime; // 学习的时间
	private int level; // 技能的等级
	private long uptime; // 技能升级的时间

	@Override
	public void _reset_unsafe_() {
		skillid = 0;
		studytime = 0L;
		level = 0;
		uptime = 0L;
	}

	FamilySkill(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public FamilySkill() {
		this(0, null, null);
	}

	public FamilySkill(FamilySkill _o_) {
		this(_o_, null, null);
	}

	FamilySkill(xbean.FamilySkill _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof FamilySkill) assign((FamilySkill)_o1_);
		else if (_o1_ instanceof FamilySkill.Data) assign((FamilySkill.Data)_o1_);
		else if (_o1_ instanceof FamilySkill.Const) assign(((FamilySkill.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(FamilySkill _o_) {
		_o_._xdb_verify_unsafe_();
		skillid = _o_.skillid;
		studytime = _o_.studytime;
		level = _o_.level;
		uptime = _o_.uptime;
	}

	private void assign(FamilySkill.Data _o_) {
		skillid = _o_.skillid;
		studytime = _o_.studytime;
		level = _o_.level;
		uptime = _o_.uptime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)( 8192|  1));_os_.marshal(skillid);
    _os_.marshal((short)(10240|  2));_os_.marshal(studytime);
    _os_.marshal((short)( 8192|  3));_os_.marshal(level);
    _os_.marshal((short)(10240|  4));_os_.marshal(uptime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):skillid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):skillid = _os_.unmarshal_short();
    				break;
    				case (10240|  2):studytime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):studytime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):studytime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  3):level = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):level = _os_.unmarshal_short();
    				break;
    				case (10240|  4):uptime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):uptime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):uptime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.FamilySkill copy() {
		_xdb_verify_unsafe_();
		return new FamilySkill(this);
	}

	@Override
	public xbean.FamilySkill toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilySkill toBean() {
		_xdb_verify_unsafe_();
		return new FamilySkill(this); // same as copy()
	}

	@Override
	public xbean.FamilySkill toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilySkill toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getSkillid() { // 家族技能id
		_xdb_verify_unsafe_();
		return skillid;
	}

	@Override
	public long getStudytime() { // 学习的时间
		_xdb_verify_unsafe_();
		return studytime;
	}

	@Override
	public int getLevel() { // 技能的等级
		_xdb_verify_unsafe_();
		return level;
	}

	@Override
	public long getUptime() { // 技能升级的时间
		_xdb_verify_unsafe_();
		return uptime;
	}

	@Override
	public void setSkillid(int _v_) { // 家族技能id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "skillid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, skillid) {
					public void rollback() { skillid = _xdb_saved; }
				};}});
		skillid = _v_;
	}

	@Override
	public void setStudytime(long _v_) { // 学习的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "studytime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, studytime) {
					public void rollback() { studytime = _xdb_saved; }
				};}});
		studytime = _v_;
	}

	@Override
	public void setLevel(int _v_) { // 技能的等级
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "level") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, level) {
					public void rollback() { level = _xdb_saved; }
				};}});
		level = _v_;
	}

	@Override
	public void setUptime(long _v_) { // 技能升级的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "uptime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, uptime) {
					public void rollback() { uptime = _xdb_saved; }
				};}});
		uptime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		FamilySkill _o_ = null;
		if ( _o1_ instanceof FamilySkill ) _o_ = (FamilySkill)_o1_;
		else if ( _o1_ instanceof FamilySkill.Const ) _o_ = ((FamilySkill.Const)_o1_).nThis();
		else return false;
		if (skillid != _o_.skillid) return false;
		if (studytime != _o_.studytime) return false;
		if (level != _o_.level) return false;
		if (uptime != _o_.uptime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += skillid;
		_h_ += studytime;
		_h_ += level;
		_h_ += uptime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(skillid);
		_sb_.append(",");
		_sb_.append(studytime);
		_sb_.append(",");
		_sb_.append(level);
		_sb_.append(",");
		_sb_.append(uptime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("skillid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("studytime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("level"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("uptime"));
		return lb;
	}

	private class Const implements xbean.FamilySkill {
		FamilySkill nThis() {
			return FamilySkill.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.FamilySkill copy() {
			return FamilySkill.this.copy();
		}

		@Override
		public xbean.FamilySkill toData() {
			return FamilySkill.this.toData();
		}

		public xbean.FamilySkill toBean() {
			return FamilySkill.this.toBean();
		}

		@Override
		public xbean.FamilySkill toDataIf() {
			return FamilySkill.this.toDataIf();
		}

		public xbean.FamilySkill toBeanIf() {
			return FamilySkill.this.toBeanIf();
		}

		@Override
		public int getSkillid() { // 家族技能id
			_xdb_verify_unsafe_();
			return skillid;
		}

		@Override
		public long getStudytime() { // 学习的时间
			_xdb_verify_unsafe_();
			return studytime;
		}

		@Override
		public int getLevel() { // 技能的等级
			_xdb_verify_unsafe_();
			return level;
		}

		@Override
		public long getUptime() { // 技能升级的时间
			_xdb_verify_unsafe_();
			return uptime;
		}

		@Override
		public void setSkillid(int _v_) { // 家族技能id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setStudytime(long _v_) { // 学习的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLevel(int _v_) { // 技能的等级
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUptime(long _v_) { // 技能升级的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean toConst() {
			_xdb_verify_unsafe_();
			return this;
		}

		@Override
		public boolean isConst() {
			_xdb_verify_unsafe_();
			return true;
		}

		@Override
		public boolean isData() {
			return FamilySkill.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return FamilySkill.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return FamilySkill.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return FamilySkill.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return FamilySkill.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return FamilySkill.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return FamilySkill.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return FamilySkill.this.hashCode();
		}

		@Override
		public String toString() {
			return FamilySkill.this.toString();
		}

	}

	public static final class Data implements xbean.FamilySkill {
		private int skillid; // 家族技能id
		private long studytime; // 学习的时间
		private int level; // 技能的等级
		private long uptime; // 技能升级的时间

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.FamilySkill _o1_) {
			if (_o1_ instanceof FamilySkill) assign((FamilySkill)_o1_);
			else if (_o1_ instanceof FamilySkill.Data) assign((FamilySkill.Data)_o1_);
			else if (_o1_ instanceof FamilySkill.Const) assign(((FamilySkill.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(FamilySkill _o_) {
			skillid = _o_.skillid;
			studytime = _o_.studytime;
			level = _o_.level;
			uptime = _o_.uptime;
		}

		private void assign(FamilySkill.Data _o_) {
			skillid = _o_.skillid;
			studytime = _o_.studytime;
			level = _o_.level;
			uptime = _o_.uptime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)( 8192|  1));_os_.marshal(skillid);
	_os_.marshal((short)(10240|  2));_os_.marshal(studytime);
	_os_.marshal((short)( 8192|  3));_os_.marshal(level);
	_os_.marshal((short)(10240|  4));_os_.marshal(uptime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):skillid = _os_.unmarshal_int();
					break;
					case ( 6144|  1):skillid = _os_.unmarshal_short();
					break;
					case (10240|  2):studytime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):studytime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):studytime = _os_.unmarshal_int();
					break;
					case ( 8192|  3):level = _os_.unmarshal_int();
					break;
					case ( 6144|  3):level = _os_.unmarshal_short();
					break;
					case (10240|  4):uptime = _os_.unmarshal_long();
					break;
					case ( 6144|  4):uptime = _os_.unmarshal_short();
					break;
					case ( 8192|  4):uptime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.FamilySkill copy() {
			return new Data(this);
		}

		@Override
		public xbean.FamilySkill toData() {
			return new Data(this);
		}

		public xbean.FamilySkill toBean() {
			return new FamilySkill(this, null, null);
		}

		@Override
		public xbean.FamilySkill toDataIf() {
			return this;
		}

		public xbean.FamilySkill toBeanIf() {
			return new FamilySkill(this, null, null);
		}

		// xdb.Bean interface. Data Unsupported
		public boolean xdbManaged() { throw new UnsupportedOperationException(); }
		public xdb.Bean xdbParent() { throw new UnsupportedOperationException(); }
		public String xdbVarname()  { throw new UnsupportedOperationException(); }
		public Long    xdbObjId()   { throw new UnsupportedOperationException(); }
		public xdb.Bean toConst()   { throw new UnsupportedOperationException(); }
		public boolean isConst()    { return false; }
		public boolean isData()     { return true; }

		@Override
		public int getSkillid() { // 家族技能id
			return skillid;
		}

		@Override
		public long getStudytime() { // 学习的时间
			return studytime;
		}

		@Override
		public int getLevel() { // 技能的等级
			return level;
		}

		@Override
		public long getUptime() { // 技能升级的时间
			return uptime;
		}

		@Override
		public void setSkillid(int _v_) { // 家族技能id
			skillid = _v_;
		}

		@Override
		public void setStudytime(long _v_) { // 学习的时间
			studytime = _v_;
		}

		@Override
		public void setLevel(int _v_) { // 技能的等级
			level = _v_;
		}

		@Override
		public void setUptime(long _v_) { // 技能升级的时间
			uptime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof FamilySkill.Data)) return false;
			FamilySkill.Data _o_ = (FamilySkill.Data) _o1_;
			if (skillid != _o_.skillid) return false;
			if (studytime != _o_.studytime) return false;
			if (level != _o_.level) return false;
			if (uptime != _o_.uptime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += skillid;
			_h_ += studytime;
			_h_ += level;
			_h_ += uptime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(skillid);
			_sb_.append(",");
			_sb_.append(studytime);
			_sb_.append(",");
			_sb_.append(level);
			_sb_.append(",");
			_sb_.append(uptime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
