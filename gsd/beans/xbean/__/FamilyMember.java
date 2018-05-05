
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class FamilyMember extends xdb.XBean implements xbean.FamilyMember {
	private long jointime; // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
	private int familyjob; // 家族的职位，组长，青龙使，副组长等等
	private long roleid; // 个人角色Id,为了方便排序找到最大贡献度
	private long personalbuild; // 个人贡献度,转移族长的时候用
	private long dailybuild; // 每日贡献度,每天清空一次，用下面的时间判断

	@Override
	public void _reset_unsafe_() {
		jointime = 0L;
		familyjob = 0;
		roleid = 0L;
		personalbuild = 0L;
		dailybuild = 0L;
	}

	FamilyMember(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public FamilyMember() {
		this(0, null, null);
	}

	public FamilyMember(FamilyMember _o_) {
		this(_o_, null, null);
	}

	FamilyMember(xbean.FamilyMember _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof FamilyMember) assign((FamilyMember)_o1_);
		else if (_o1_ instanceof FamilyMember.Data) assign((FamilyMember.Data)_o1_);
		else if (_o1_ instanceof FamilyMember.Const) assign(((FamilyMember.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(FamilyMember _o_) {
		_o_._xdb_verify_unsafe_();
		jointime = _o_.jointime;
		familyjob = _o_.familyjob;
		roleid = _o_.roleid;
		personalbuild = _o_.personalbuild;
		dailybuild = _o_.dailybuild;
	}

	private void assign(FamilyMember.Data _o_) {
		jointime = _o_.jointime;
		familyjob = _o_.familyjob;
		roleid = _o_.roleid;
		personalbuild = _o_.personalbuild;
		dailybuild = _o_.dailybuild;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)5);
    _os_.marshal((short)(10240|  1));_os_.marshal(jointime);
    _os_.marshal((short)( 8192|  2));_os_.marshal(familyjob);
    _os_.marshal((short)(10240|  3));_os_.marshal(roleid);
    _os_.marshal((short)(10240|  5));_os_.marshal(personalbuild);
    _os_.marshal((short)(10240|  7));_os_.marshal(dailybuild);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):jointime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):jointime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):jointime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):familyjob = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):familyjob = _os_.unmarshal_short();
    				break;
    				case (10240|  3):roleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):roleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):roleid = _os_.unmarshal_int();
    				break;
    				case (10240|  5):personalbuild = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):personalbuild = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):personalbuild = _os_.unmarshal_int();
    				break;
    				case (10240|  7):dailybuild = _os_.unmarshal_long();
    				break;
    				case ( 6144|  7):dailybuild = _os_.unmarshal_short();
    				break;
    				case ( 8192|  7):dailybuild = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.FamilyMember copy() {
		_xdb_verify_unsafe_();
		return new FamilyMember(this);
	}

	@Override
	public xbean.FamilyMember toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyMember toBean() {
		_xdb_verify_unsafe_();
		return new FamilyMember(this); // same as copy()
	}

	@Override
	public xbean.FamilyMember toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyMember toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getJointime() { // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
		_xdb_verify_unsafe_();
		return jointime;
	}

	@Override
	public int getFamilyjob() { // 家族的职位，组长，青龙使，副组长等等
		_xdb_verify_unsafe_();
		return familyjob;
	}

	@Override
	public long getRoleid() { // 个人角色Id,为了方便排序找到最大贡献度
		_xdb_verify_unsafe_();
		return roleid;
	}

	@Override
	public long getPersonalbuild() { // 个人贡献度,转移族长的时候用
		_xdb_verify_unsafe_();
		return personalbuild;
	}

	@Override
	public long getDailybuild() { // 每日贡献度,每天清空一次，用下面的时间判断
		_xdb_verify_unsafe_();
		return dailybuild;
	}

	@Override
	public void setJointime(long _v_) { // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "jointime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, jointime) {
					public void rollback() { jointime = _xdb_saved; }
				};}});
		jointime = _v_;
	}

	@Override
	public void setFamilyjob(int _v_) { // 家族的职位，组长，青龙使，副组长等等
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "familyjob") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, familyjob) {
					public void rollback() { familyjob = _xdb_saved; }
				};}});
		familyjob = _v_;
	}

	@Override
	public void setRoleid(long _v_) { // 个人角色Id,为了方便排序找到最大贡献度
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleid) {
					public void rollback() { roleid = _xdb_saved; }
				};}});
		roleid = _v_;
	}

	@Override
	public void setPersonalbuild(long _v_) { // 个人贡献度,转移族长的时候用
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "personalbuild") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, personalbuild) {
					public void rollback() { personalbuild = _xdb_saved; }
				};}});
		personalbuild = _v_;
	}

	@Override
	public void setDailybuild(long _v_) { // 每日贡献度,每天清空一次，用下面的时间判断
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "dailybuild") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, dailybuild) {
					public void rollback() { dailybuild = _xdb_saved; }
				};}});
		dailybuild = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		FamilyMember _o_ = null;
		if ( _o1_ instanceof FamilyMember ) _o_ = (FamilyMember)_o1_;
		else if ( _o1_ instanceof FamilyMember.Const ) _o_ = ((FamilyMember.Const)_o1_).nThis();
		else return false;
		if (jointime != _o_.jointime) return false;
		if (familyjob != _o_.familyjob) return false;
		if (roleid != _o_.roleid) return false;
		if (personalbuild != _o_.personalbuild) return false;
		if (dailybuild != _o_.dailybuild) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += jointime;
		_h_ += familyjob;
		_h_ += roleid;
		_h_ += personalbuild;
		_h_ += dailybuild;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(jointime);
		_sb_.append(",");
		_sb_.append(familyjob);
		_sb_.append(",");
		_sb_.append(roleid);
		_sb_.append(",");
		_sb_.append(personalbuild);
		_sb_.append(",");
		_sb_.append(dailybuild);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("jointime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("familyjob"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("personalbuild"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("dailybuild"));
		return lb;
	}

	private class Const implements xbean.FamilyMember {
		FamilyMember nThis() {
			return FamilyMember.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.FamilyMember copy() {
			return FamilyMember.this.copy();
		}

		@Override
		public xbean.FamilyMember toData() {
			return FamilyMember.this.toData();
		}

		public xbean.FamilyMember toBean() {
			return FamilyMember.this.toBean();
		}

		@Override
		public xbean.FamilyMember toDataIf() {
			return FamilyMember.this.toDataIf();
		}

		public xbean.FamilyMember toBeanIf() {
			return FamilyMember.this.toBeanIf();
		}

		@Override
		public long getJointime() { // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
			_xdb_verify_unsafe_();
			return jointime;
		}

		@Override
		public int getFamilyjob() { // 家族的职位，组长，青龙使，副组长等等
			_xdb_verify_unsafe_();
			return familyjob;
		}

		@Override
		public long getRoleid() { // 个人角色Id,为了方便排序找到最大贡献度
			_xdb_verify_unsafe_();
			return roleid;
		}

		@Override
		public long getPersonalbuild() { // 个人贡献度,转移族长的时候用
			_xdb_verify_unsafe_();
			return personalbuild;
		}

		@Override
		public long getDailybuild() { // 每日贡献度,每天清空一次，用下面的时间判断
			_xdb_verify_unsafe_();
			return dailybuild;
		}

		@Override
		public void setJointime(long _v_) { // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFamilyjob(int _v_) { // 家族的职位，组长，青龙使，副组长等等
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRoleid(long _v_) { // 个人角色Id,为了方便排序找到最大贡献度
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPersonalbuild(long _v_) { // 个人贡献度,转移族长的时候用
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDailybuild(long _v_) { // 每日贡献度,每天清空一次，用下面的时间判断
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
			return FamilyMember.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return FamilyMember.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return FamilyMember.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return FamilyMember.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return FamilyMember.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return FamilyMember.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return FamilyMember.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return FamilyMember.this.hashCode();
		}

		@Override
		public String toString() {
			return FamilyMember.this.toString();
		}

	}

	public static final class Data implements xbean.FamilyMember {
		private long jointime; // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
		private int familyjob; // 家族的职位，组长，青龙使，副组长等等
		private long roleid; // 个人角色Id,为了方便排序找到最大贡献度
		private long personalbuild; // 个人贡献度,转移族长的时候用
		private long dailybuild; // 每日贡献度,每天清空一次，用下面的时间判断

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.FamilyMember _o1_) {
			if (_o1_ instanceof FamilyMember) assign((FamilyMember)_o1_);
			else if (_o1_ instanceof FamilyMember.Data) assign((FamilyMember.Data)_o1_);
			else if (_o1_ instanceof FamilyMember.Const) assign(((FamilyMember.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(FamilyMember _o_) {
			jointime = _o_.jointime;
			familyjob = _o_.familyjob;
			roleid = _o_.roleid;
			personalbuild = _o_.personalbuild;
			dailybuild = _o_.dailybuild;
		}

		private void assign(FamilyMember.Data _o_) {
			jointime = _o_.jointime;
			familyjob = _o_.familyjob;
			roleid = _o_.roleid;
			personalbuild = _o_.personalbuild;
			dailybuild = _o_.dailybuild;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)5);
	_os_.marshal((short)(10240|  1));_os_.marshal(jointime);
	_os_.marshal((short)( 8192|  2));_os_.marshal(familyjob);
	_os_.marshal((short)(10240|  3));_os_.marshal(roleid);
	_os_.marshal((short)(10240|  5));_os_.marshal(personalbuild);
	_os_.marshal((short)(10240|  7));_os_.marshal(dailybuild);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):jointime = _os_.unmarshal_long();
					break;
					case ( 6144|  1):jointime = _os_.unmarshal_short();
					break;
					case ( 8192|  1):jointime = _os_.unmarshal_int();
					break;
					case ( 8192|  2):familyjob = _os_.unmarshal_int();
					break;
					case ( 6144|  2):familyjob = _os_.unmarshal_short();
					break;
					case (10240|  3):roleid = _os_.unmarshal_long();
					break;
					case ( 6144|  3):roleid = _os_.unmarshal_short();
					break;
					case ( 8192|  3):roleid = _os_.unmarshal_int();
					break;
					case (10240|  5):personalbuild = _os_.unmarshal_long();
					break;
					case ( 6144|  5):personalbuild = _os_.unmarshal_short();
					break;
					case ( 8192|  5):personalbuild = _os_.unmarshal_int();
					break;
					case (10240|  7):dailybuild = _os_.unmarshal_long();
					break;
					case ( 6144|  7):dailybuild = _os_.unmarshal_short();
					break;
					case ( 8192|  7):dailybuild = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.FamilyMember copy() {
			return new Data(this);
		}

		@Override
		public xbean.FamilyMember toData() {
			return new Data(this);
		}

		public xbean.FamilyMember toBean() {
			return new FamilyMember(this, null, null);
		}

		@Override
		public xbean.FamilyMember toDataIf() {
			return this;
		}

		public xbean.FamilyMember toBeanIf() {
			return new FamilyMember(this, null, null);
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
		public long getJointime() { // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
			return jointime;
		}

		@Override
		public int getFamilyjob() { // 家族的职位，组长，青龙使，副组长等等
			return familyjob;
		}

		@Override
		public long getRoleid() { // 个人角色Id,为了方便排序找到最大贡献度
			return roleid;
		}

		@Override
		public long getPersonalbuild() { // 个人贡献度,转移族长的时候用
			return personalbuild;
		}

		@Override
		public long getDailybuild() { // 每日贡献度,每天清空一次，用下面的时间判断
			return dailybuild;
		}

		@Override
		public void setJointime(long _v_) { // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
			jointime = _v_;
		}

		@Override
		public void setFamilyjob(int _v_) { // 家族的职位，组长，青龙使，副组长等等
			familyjob = _v_;
		}

		@Override
		public void setRoleid(long _v_) { // 个人角色Id,为了方便排序找到最大贡献度
			roleid = _v_;
		}

		@Override
		public void setPersonalbuild(long _v_) { // 个人贡献度,转移族长的时候用
			personalbuild = _v_;
		}

		@Override
		public void setDailybuild(long _v_) { // 每日贡献度,每天清空一次，用下面的时间判断
			dailybuild = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof FamilyMember.Data)) return false;
			FamilyMember.Data _o_ = (FamilyMember.Data) _o1_;
			if (jointime != _o_.jointime) return false;
			if (familyjob != _o_.familyjob) return false;
			if (roleid != _o_.roleid) return false;
			if (personalbuild != _o_.personalbuild) return false;
			if (dailybuild != _o_.dailybuild) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += jointime;
			_h_ += familyjob;
			_h_ += roleid;
			_h_ += personalbuild;
			_h_ += dailybuild;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(jointime);
			_sb_.append(",");
			_sb_.append(familyjob);
			_sb_.append(",");
			_sb_.append(roleid);
			_sb_.append(",");
			_sb_.append(personalbuild);
			_sb_.append(",");
			_sb_.append(dailybuild);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
