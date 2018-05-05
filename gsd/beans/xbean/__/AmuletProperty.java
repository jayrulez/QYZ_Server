
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class AmuletProperty extends xdb.XBean implements xbean.AmuletProperty {
	private int propindex; // 属性id
	private int islock; // 是否锁定，0为未锁定，1为锁定
	private int skillid; // 技能id
	private int professionid; // 职业id
	private int addlevel; // 技能增值

	@Override
	public void _reset_unsafe_() {
		propindex = 0;
		islock = 0;
		skillid = 0;
		professionid = 0;
		addlevel = 0;
	}

	AmuletProperty(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public AmuletProperty() {
		this(0, null, null);
	}

	public AmuletProperty(AmuletProperty _o_) {
		this(_o_, null, null);
	}

	AmuletProperty(xbean.AmuletProperty _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof AmuletProperty) assign((AmuletProperty)_o1_);
		else if (_o1_ instanceof AmuletProperty.Data) assign((AmuletProperty.Data)_o1_);
		else if (_o1_ instanceof AmuletProperty.Const) assign(((AmuletProperty.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(AmuletProperty _o_) {
		_o_._xdb_verify_unsafe_();
		propindex = _o_.propindex;
		islock = _o_.islock;
		skillid = _o_.skillid;
		professionid = _o_.professionid;
		addlevel = _o_.addlevel;
	}

	private void assign(AmuletProperty.Data _o_) {
		propindex = _o_.propindex;
		islock = _o_.islock;
		skillid = _o_.skillid;
		professionid = _o_.professionid;
		addlevel = _o_.addlevel;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)5);
    _os_.marshal((short)( 8192|  1));_os_.marshal(propindex);
    _os_.marshal((short)( 8192|  2));_os_.marshal(islock);
    _os_.marshal((short)( 8192|  3));_os_.marshal(skillid);
    _os_.marshal((short)( 8192|  4));_os_.marshal(professionid);
    _os_.marshal((short)( 8192|  5));_os_.marshal(addlevel);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):propindex = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):propindex = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):islock = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):islock = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):skillid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):skillid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):professionid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):professionid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):addlevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):addlevel = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.AmuletProperty copy() {
		_xdb_verify_unsafe_();
		return new AmuletProperty(this);
	}

	@Override
	public xbean.AmuletProperty toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AmuletProperty toBean() {
		_xdb_verify_unsafe_();
		return new AmuletProperty(this); // same as copy()
	}

	@Override
	public xbean.AmuletProperty toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AmuletProperty toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getPropindex() { // 属性id
		_xdb_verify_unsafe_();
		return propindex;
	}

	@Override
	public int getIslock() { // 是否锁定，0为未锁定，1为锁定
		_xdb_verify_unsafe_();
		return islock;
	}

	@Override
	public int getSkillid() { // 技能id
		_xdb_verify_unsafe_();
		return skillid;
	}

	@Override
	public int getProfessionid() { // 职业id
		_xdb_verify_unsafe_();
		return professionid;
	}

	@Override
	public int getAddlevel() { // 技能增值
		_xdb_verify_unsafe_();
		return addlevel;
	}

	@Override
	public void setPropindex(int _v_) { // 属性id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "propindex") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, propindex) {
					public void rollback() { propindex = _xdb_saved; }
				};}});
		propindex = _v_;
	}

	@Override
	public void setIslock(int _v_) { // 是否锁定，0为未锁定，1为锁定
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "islock") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, islock) {
					public void rollback() { islock = _xdb_saved; }
				};}});
		islock = _v_;
	}

	@Override
	public void setSkillid(int _v_) { // 技能id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "skillid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, skillid) {
					public void rollback() { skillid = _xdb_saved; }
				};}});
		skillid = _v_;
	}

	@Override
	public void setProfessionid(int _v_) { // 职业id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "professionid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, professionid) {
					public void rollback() { professionid = _xdb_saved; }
				};}});
		professionid = _v_;
	}

	@Override
	public void setAddlevel(int _v_) { // 技能增值
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "addlevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, addlevel) {
					public void rollback() { addlevel = _xdb_saved; }
				};}});
		addlevel = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		AmuletProperty _o_ = null;
		if ( _o1_ instanceof AmuletProperty ) _o_ = (AmuletProperty)_o1_;
		else if ( _o1_ instanceof AmuletProperty.Const ) _o_ = ((AmuletProperty.Const)_o1_).nThis();
		else return false;
		if (propindex != _o_.propindex) return false;
		if (islock != _o_.islock) return false;
		if (skillid != _o_.skillid) return false;
		if (professionid != _o_.professionid) return false;
		if (addlevel != _o_.addlevel) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += propindex;
		_h_ += islock;
		_h_ += skillid;
		_h_ += professionid;
		_h_ += addlevel;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(propindex);
		_sb_.append(",");
		_sb_.append(islock);
		_sb_.append(",");
		_sb_.append(skillid);
		_sb_.append(",");
		_sb_.append(professionid);
		_sb_.append(",");
		_sb_.append(addlevel);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("propindex"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("islock"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("skillid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("professionid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("addlevel"));
		return lb;
	}

	private class Const implements xbean.AmuletProperty {
		AmuletProperty nThis() {
			return AmuletProperty.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.AmuletProperty copy() {
			return AmuletProperty.this.copy();
		}

		@Override
		public xbean.AmuletProperty toData() {
			return AmuletProperty.this.toData();
		}

		public xbean.AmuletProperty toBean() {
			return AmuletProperty.this.toBean();
		}

		@Override
		public xbean.AmuletProperty toDataIf() {
			return AmuletProperty.this.toDataIf();
		}

		public xbean.AmuletProperty toBeanIf() {
			return AmuletProperty.this.toBeanIf();
		}

		@Override
		public int getPropindex() { // 属性id
			_xdb_verify_unsafe_();
			return propindex;
		}

		@Override
		public int getIslock() { // 是否锁定，0为未锁定，1为锁定
			_xdb_verify_unsafe_();
			return islock;
		}

		@Override
		public int getSkillid() { // 技能id
			_xdb_verify_unsafe_();
			return skillid;
		}

		@Override
		public int getProfessionid() { // 职业id
			_xdb_verify_unsafe_();
			return professionid;
		}

		@Override
		public int getAddlevel() { // 技能增值
			_xdb_verify_unsafe_();
			return addlevel;
		}

		@Override
		public void setPropindex(int _v_) { // 属性id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIslock(int _v_) { // 是否锁定，0为未锁定，1为锁定
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setSkillid(int _v_) { // 技能id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setProfessionid(int _v_) { // 职业id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAddlevel(int _v_) { // 技能增值
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
			return AmuletProperty.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return AmuletProperty.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return AmuletProperty.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return AmuletProperty.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return AmuletProperty.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return AmuletProperty.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return AmuletProperty.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return AmuletProperty.this.hashCode();
		}

		@Override
		public String toString() {
			return AmuletProperty.this.toString();
		}

	}

	public static final class Data implements xbean.AmuletProperty {
		private int propindex; // 属性id
		private int islock; // 是否锁定，0为未锁定，1为锁定
		private int skillid; // 技能id
		private int professionid; // 职业id
		private int addlevel; // 技能增值

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.AmuletProperty _o1_) {
			if (_o1_ instanceof AmuletProperty) assign((AmuletProperty)_o1_);
			else if (_o1_ instanceof AmuletProperty.Data) assign((AmuletProperty.Data)_o1_);
			else if (_o1_ instanceof AmuletProperty.Const) assign(((AmuletProperty.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(AmuletProperty _o_) {
			propindex = _o_.propindex;
			islock = _o_.islock;
			skillid = _o_.skillid;
			professionid = _o_.professionid;
			addlevel = _o_.addlevel;
		}

		private void assign(AmuletProperty.Data _o_) {
			propindex = _o_.propindex;
			islock = _o_.islock;
			skillid = _o_.skillid;
			professionid = _o_.professionid;
			addlevel = _o_.addlevel;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)5);
	_os_.marshal((short)( 8192|  1));_os_.marshal(propindex);
	_os_.marshal((short)( 8192|  2));_os_.marshal(islock);
	_os_.marshal((short)( 8192|  3));_os_.marshal(skillid);
	_os_.marshal((short)( 8192|  4));_os_.marshal(professionid);
	_os_.marshal((short)( 8192|  5));_os_.marshal(addlevel);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):propindex = _os_.unmarshal_int();
					break;
					case ( 6144|  1):propindex = _os_.unmarshal_short();
					break;
					case ( 8192|  2):islock = _os_.unmarshal_int();
					break;
					case ( 6144|  2):islock = _os_.unmarshal_short();
					break;
					case ( 8192|  3):skillid = _os_.unmarshal_int();
					break;
					case ( 6144|  3):skillid = _os_.unmarshal_short();
					break;
					case ( 8192|  4):professionid = _os_.unmarshal_int();
					break;
					case ( 6144|  4):professionid = _os_.unmarshal_short();
					break;
					case ( 8192|  5):addlevel = _os_.unmarshal_int();
					break;
					case ( 6144|  5):addlevel = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.AmuletProperty copy() {
			return new Data(this);
		}

		@Override
		public xbean.AmuletProperty toData() {
			return new Data(this);
		}

		public xbean.AmuletProperty toBean() {
			return new AmuletProperty(this, null, null);
		}

		@Override
		public xbean.AmuletProperty toDataIf() {
			return this;
		}

		public xbean.AmuletProperty toBeanIf() {
			return new AmuletProperty(this, null, null);
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
		public int getPropindex() { // 属性id
			return propindex;
		}

		@Override
		public int getIslock() { // 是否锁定，0为未锁定，1为锁定
			return islock;
		}

		@Override
		public int getSkillid() { // 技能id
			return skillid;
		}

		@Override
		public int getProfessionid() { // 职业id
			return professionid;
		}

		@Override
		public int getAddlevel() { // 技能增值
			return addlevel;
		}

		@Override
		public void setPropindex(int _v_) { // 属性id
			propindex = _v_;
		}

		@Override
		public void setIslock(int _v_) { // 是否锁定，0为未锁定，1为锁定
			islock = _v_;
		}

		@Override
		public void setSkillid(int _v_) { // 技能id
			skillid = _v_;
		}

		@Override
		public void setProfessionid(int _v_) { // 职业id
			professionid = _v_;
		}

		@Override
		public void setAddlevel(int _v_) { // 技能增值
			addlevel = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof AmuletProperty.Data)) return false;
			AmuletProperty.Data _o_ = (AmuletProperty.Data) _o1_;
			if (propindex != _o_.propindex) return false;
			if (islock != _o_.islock) return false;
			if (skillid != _o_.skillid) return false;
			if (professionid != _o_.professionid) return false;
			if (addlevel != _o_.addlevel) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += propindex;
			_h_ += islock;
			_h_ += skillid;
			_h_ += professionid;
			_h_ += addlevel;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(propindex);
			_sb_.append(",");
			_sb_.append(islock);
			_sb_.append(",");
			_sb_.append(skillid);
			_sb_.append(",");
			_sb_.append(professionid);
			_sb_.append(",");
			_sb_.append(addlevel);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
