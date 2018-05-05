
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class UserDeletedRole extends xdb.XBean implements xbean.UserDeletedRole {
	private java.util.LinkedList<xbean.DeletedRole> roles; // 

	@Override
	public void _reset_unsafe_() {
		roles.clear();
	}

	UserDeletedRole(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		roles = new java.util.LinkedList<xbean.DeletedRole>();
	}

	public UserDeletedRole() {
		this(0, null, null);
	}

	public UserDeletedRole(UserDeletedRole _o_) {
		this(_o_, null, null);
	}

	UserDeletedRole(xbean.UserDeletedRole _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof UserDeletedRole) assign((UserDeletedRole)_o1_);
		else if (_o1_ instanceof UserDeletedRole.Data) assign((UserDeletedRole.Data)_o1_);
		else if (_o1_ instanceof UserDeletedRole.Const) assign(((UserDeletedRole.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(UserDeletedRole _o_) {
		_o_._xdb_verify_unsafe_();
		roles = new java.util.LinkedList<xbean.DeletedRole>();
		for (xbean.DeletedRole _v_ : _o_.roles)
			roles.add(new DeletedRole(_v_, this, "roles"));
	}

	private void assign(UserDeletedRole.Data _o_) {
		roles = new java.util.LinkedList<xbean.DeletedRole>();
		for (xbean.DeletedRole _v_ : _o_.roles)
			roles.add(new DeletedRole(_v_, this, "roles"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(roles.size());
for (xbean.DeletedRole _v_ : roles) {
	_v_.marshal(_os_);
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
	xbean.DeletedRole _v_ = new DeletedRole(0, this, "roles");
	_v_.unmarshal(_os_);
	roles.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.UserDeletedRole copy() {
		_xdb_verify_unsafe_();
		return new UserDeletedRole(this);
	}

	@Override
	public xbean.UserDeletedRole toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.UserDeletedRole toBean() {
		_xdb_verify_unsafe_();
		return new UserDeletedRole(this); // same as copy()
	}

	@Override
	public xbean.UserDeletedRole toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.UserDeletedRole toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<xbean.DeletedRole> getRoles() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "roles"), roles);
	}

	public java.util.List<xbean.DeletedRole> getRolesAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<xbean.DeletedRole> roles;
		UserDeletedRole _o_ = this;
		roles = new java.util.LinkedList<xbean.DeletedRole>();
		for (xbean.DeletedRole _v_ : _o_.roles)
			roles.add(new DeletedRole.Data(_v_));
		return roles;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		UserDeletedRole _o_ = null;
		if ( _o1_ instanceof UserDeletedRole ) _o_ = (UserDeletedRole)_o1_;
		else if ( _o1_ instanceof UserDeletedRole.Const ) _o_ = ((UserDeletedRole.Const)_o1_).nThis();
		else return false;
		if (!roles.equals(_o_.roles)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += roles.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roles);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("roles"));
		return lb;
	}

	private class Const implements xbean.UserDeletedRole {
		UserDeletedRole nThis() {
			return UserDeletedRole.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.UserDeletedRole copy() {
			return UserDeletedRole.this.copy();
		}

		@Override
		public xbean.UserDeletedRole toData() {
			return UserDeletedRole.this.toData();
		}

		public xbean.UserDeletedRole toBean() {
			return UserDeletedRole.this.toBean();
		}

		@Override
		public xbean.UserDeletedRole toDataIf() {
			return UserDeletedRole.this.toDataIf();
		}

		public xbean.UserDeletedRole toBeanIf() {
			return UserDeletedRole.this.toBeanIf();
		}

		@Override
		public java.util.List<xbean.DeletedRole> getRoles() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(roles);
		}

		public java.util.List<xbean.DeletedRole> getRolesAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<xbean.DeletedRole> roles;
			UserDeletedRole _o_ = UserDeletedRole.this;
		roles = new java.util.LinkedList<xbean.DeletedRole>();
		for (xbean.DeletedRole _v_ : _o_.roles)
			roles.add(new DeletedRole.Data(_v_));
			return roles;
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
			return UserDeletedRole.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return UserDeletedRole.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return UserDeletedRole.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return UserDeletedRole.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return UserDeletedRole.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return UserDeletedRole.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return UserDeletedRole.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return UserDeletedRole.this.hashCode();
		}

		@Override
		public String toString() {
			return UserDeletedRole.this.toString();
		}

	}

	public static final class Data implements xbean.UserDeletedRole {
		private java.util.LinkedList<xbean.DeletedRole> roles; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			roles = new java.util.LinkedList<xbean.DeletedRole>();
		}

		Data(xbean.UserDeletedRole _o1_) {
			if (_o1_ instanceof UserDeletedRole) assign((UserDeletedRole)_o1_);
			else if (_o1_ instanceof UserDeletedRole.Data) assign((UserDeletedRole.Data)_o1_);
			else if (_o1_ instanceof UserDeletedRole.Const) assign(((UserDeletedRole.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(UserDeletedRole _o_) {
			roles = new java.util.LinkedList<xbean.DeletedRole>();
			for (xbean.DeletedRole _v_ : _o_.roles)
				roles.add(new DeletedRole.Data(_v_));
		}

		private void assign(UserDeletedRole.Data _o_) {
			roles = new java.util.LinkedList<xbean.DeletedRole>();
			for (xbean.DeletedRole _v_ : _o_.roles)
				roles.add(new DeletedRole.Data(_v_));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(roles.size());
for (xbean.DeletedRole _v_ : roles) {
	_v_.marshal(_os_);
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
	xbean.DeletedRole _v_ = xbean.Pod.newDeletedRoleData();
	_v_.unmarshal(_os_);
	roles.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.UserDeletedRole copy() {
			return new Data(this);
		}

		@Override
		public xbean.UserDeletedRole toData() {
			return new Data(this);
		}

		public xbean.UserDeletedRole toBean() {
			return new UserDeletedRole(this, null, null);
		}

		@Override
		public xbean.UserDeletedRole toDataIf() {
			return this;
		}

		public xbean.UserDeletedRole toBeanIf() {
			return new UserDeletedRole(this, null, null);
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
		public java.util.List<xbean.DeletedRole> getRoles() { // 
			return roles;
		}

		@Override
		public java.util.List<xbean.DeletedRole> getRolesAsData() { // 
			return roles;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof UserDeletedRole.Data)) return false;
			UserDeletedRole.Data _o_ = (UserDeletedRole.Data) _o1_;
			if (!roles.equals(_o_.roles)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += roles.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(roles);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
