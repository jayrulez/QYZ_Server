
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleRelation extends xdb.XBean implements xbean.RoleRelation {
	private java.util.LinkedList<Long> rolelist; // 

	@Override
	public void _reset_unsafe_() {
		rolelist.clear();
	}

	RoleRelation(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		rolelist = new java.util.LinkedList<Long>();
	}

	public RoleRelation() {
		this(0, null, null);
	}

	public RoleRelation(RoleRelation _o_) {
		this(_o_, null, null);
	}

	RoleRelation(xbean.RoleRelation _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleRelation) assign((RoleRelation)_o1_);
		else if (_o1_ instanceof RoleRelation.Data) assign((RoleRelation.Data)_o1_);
		else if (_o1_ instanceof RoleRelation.Const) assign(((RoleRelation.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleRelation _o_) {
		_o_._xdb_verify_unsafe_();
		rolelist = new java.util.LinkedList<Long>();
		rolelist.addAll(_o_.rolelist);
	}

	private void assign(RoleRelation.Data _o_) {
		rolelist = new java.util.LinkedList<Long>();
		rolelist.addAll(_o_.rolelist);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(rolelist.size());
for (Long _v_ : rolelist) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	rolelist.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleRelation copy() {
		_xdb_verify_unsafe_();
		return new RoleRelation(this);
	}

	@Override
	public xbean.RoleRelation toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleRelation toBean() {
		_xdb_verify_unsafe_();
		return new RoleRelation(this); // same as copy()
	}

	@Override
	public xbean.RoleRelation toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleRelation toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<Long> getRolelist() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "rolelist"), rolelist);
	}

	public java.util.List<Long> getRolelistAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Long> rolelist;
		RoleRelation _o_ = this;
		rolelist = new java.util.LinkedList<Long>();
		rolelist.addAll(_o_.rolelist);
		return rolelist;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleRelation _o_ = null;
		if ( _o1_ instanceof RoleRelation ) _o_ = (RoleRelation)_o1_;
		else if ( _o1_ instanceof RoleRelation.Const ) _o_ = ((RoleRelation.Const)_o1_).nThis();
		else return false;
		if (!rolelist.equals(_o_.rolelist)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += rolelist.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(rolelist);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("rolelist"));
		return lb;
	}

	private class Const implements xbean.RoleRelation {
		RoleRelation nThis() {
			return RoleRelation.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleRelation copy() {
			return RoleRelation.this.copy();
		}

		@Override
		public xbean.RoleRelation toData() {
			return RoleRelation.this.toData();
		}

		public xbean.RoleRelation toBean() {
			return RoleRelation.this.toBean();
		}

		@Override
		public xbean.RoleRelation toDataIf() {
			return RoleRelation.this.toDataIf();
		}

		public xbean.RoleRelation toBeanIf() {
			return RoleRelation.this.toBeanIf();
		}

		@Override
		public java.util.List<Long> getRolelist() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(rolelist);
		}

		public java.util.List<Long> getRolelistAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Long> rolelist;
			RoleRelation _o_ = RoleRelation.this;
		rolelist = new java.util.LinkedList<Long>();
		rolelist.addAll(_o_.rolelist);
			return rolelist;
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
			return RoleRelation.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleRelation.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleRelation.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleRelation.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleRelation.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleRelation.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleRelation.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleRelation.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleRelation.this.toString();
		}

	}

	public static final class Data implements xbean.RoleRelation {
		private java.util.LinkedList<Long> rolelist; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			rolelist = new java.util.LinkedList<Long>();
		}

		Data(xbean.RoleRelation _o1_) {
			if (_o1_ instanceof RoleRelation) assign((RoleRelation)_o1_);
			else if (_o1_ instanceof RoleRelation.Data) assign((RoleRelation.Data)_o1_);
			else if (_o1_ instanceof RoleRelation.Const) assign(((RoleRelation.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleRelation _o_) {
			rolelist = new java.util.LinkedList<Long>();
			rolelist.addAll(_o_.rolelist);
		}

		private void assign(RoleRelation.Data _o_) {
			rolelist = new java.util.LinkedList<Long>();
			rolelist.addAll(_o_.rolelist);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(rolelist.size());
for (Long _v_ : rolelist) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	rolelist.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleRelation copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleRelation toData() {
			return new Data(this);
		}

		public xbean.RoleRelation toBean() {
			return new RoleRelation(this, null, null);
		}

		@Override
		public xbean.RoleRelation toDataIf() {
			return this;
		}

		public xbean.RoleRelation toBeanIf() {
			return new RoleRelation(this, null, null);
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
		public java.util.List<Long> getRolelist() { // 
			return rolelist;
		}

		@Override
		public java.util.List<Long> getRolelistAsData() { // 
			return rolelist;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleRelation.Data)) return false;
			RoleRelation.Data _o_ = (RoleRelation.Data) _o1_;
			if (!rolelist.equals(_o_.rolelist)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += rolelist.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(rolelist);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
