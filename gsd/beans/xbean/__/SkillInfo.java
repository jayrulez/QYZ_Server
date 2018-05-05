
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class SkillInfo extends xdb.XBean implements xbean.SkillInfo {
	private int level; // 

	@Override
	public void _reset_unsafe_() {
		level = 0;
	}

	SkillInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public SkillInfo() {
		this(0, null, null);
	}

	public SkillInfo(SkillInfo _o_) {
		this(_o_, null, null);
	}

	SkillInfo(xbean.SkillInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof SkillInfo) assign((SkillInfo)_o1_);
		else if (_o1_ instanceof SkillInfo.Data) assign((SkillInfo.Data)_o1_);
		else if (_o1_ instanceof SkillInfo.Const) assign(((SkillInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(SkillInfo _o_) {
		_o_._xdb_verify_unsafe_();
		level = _o_.level;
	}

	private void assign(SkillInfo.Data _o_) {
		level = _o_.level;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)( 8192|  0));_os_.marshal(level);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  0):level = _os_.unmarshal_int();
    				break;
    				case ( 6144|  0):level = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.SkillInfo copy() {
		_xdb_verify_unsafe_();
		return new SkillInfo(this);
	}

	@Override
	public xbean.SkillInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.SkillInfo toBean() {
		_xdb_verify_unsafe_();
		return new SkillInfo(this); // same as copy()
	}

	@Override
	public xbean.SkillInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.SkillInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getLevel() { // 
		_xdb_verify_unsafe_();
		return level;
	}

	@Override
	public void setLevel(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "level") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, level) {
					public void rollback() { level = _xdb_saved; }
				};}});
		level = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		SkillInfo _o_ = null;
		if ( _o1_ instanceof SkillInfo ) _o_ = (SkillInfo)_o1_;
		else if ( _o1_ instanceof SkillInfo.Const ) _o_ = ((SkillInfo.Const)_o1_).nThis();
		else return false;
		if (level != _o_.level) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += level;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(level);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("level"));
		return lb;
	}

	private class Const implements xbean.SkillInfo {
		SkillInfo nThis() {
			return SkillInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.SkillInfo copy() {
			return SkillInfo.this.copy();
		}

		@Override
		public xbean.SkillInfo toData() {
			return SkillInfo.this.toData();
		}

		public xbean.SkillInfo toBean() {
			return SkillInfo.this.toBean();
		}

		@Override
		public xbean.SkillInfo toDataIf() {
			return SkillInfo.this.toDataIf();
		}

		public xbean.SkillInfo toBeanIf() {
			return SkillInfo.this.toBeanIf();
		}

		@Override
		public int getLevel() { // 
			_xdb_verify_unsafe_();
			return level;
		}

		@Override
		public void setLevel(int _v_) { // 
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
			return SkillInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return SkillInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return SkillInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return SkillInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return SkillInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return SkillInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return SkillInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return SkillInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return SkillInfo.this.toString();
		}

	}

	public static final class Data implements xbean.SkillInfo {
		private int level; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.SkillInfo _o1_) {
			if (_o1_ instanceof SkillInfo) assign((SkillInfo)_o1_);
			else if (_o1_ instanceof SkillInfo.Data) assign((SkillInfo.Data)_o1_);
			else if (_o1_ instanceof SkillInfo.Const) assign(((SkillInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(SkillInfo _o_) {
			level = _o_.level;
		}

		private void assign(SkillInfo.Data _o_) {
			level = _o_.level;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)( 8192|  0));_os_.marshal(level);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  0):level = _os_.unmarshal_int();
					break;
					case ( 6144|  0):level = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.SkillInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.SkillInfo toData() {
			return new Data(this);
		}

		public xbean.SkillInfo toBean() {
			return new SkillInfo(this, null, null);
		}

		@Override
		public xbean.SkillInfo toDataIf() {
			return this;
		}

		public xbean.SkillInfo toBeanIf() {
			return new SkillInfo(this, null, null);
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
		public int getLevel() { // 
			return level;
		}

		@Override
		public void setLevel(int _v_) { // 
			level = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof SkillInfo.Data)) return false;
			SkillInfo.Data _o_ = (SkillInfo.Data) _o1_;
			if (level != _o_.level) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += level;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(level);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
