
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class DeletedRole extends xdb.XBean implements xbean.DeletedRole {
	private long roleid; // 
	private String username; // 
	private String newusername; // 
	private long deletetime; // 

	@Override
	public void _reset_unsafe_() {
		roleid = 0L;
		username = "";
		newusername = "";
		deletetime = 0L;
	}

	DeletedRole(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		username = "";
		newusername = "";
	}

	public DeletedRole() {
		this(0, null, null);
	}

	public DeletedRole(DeletedRole _o_) {
		this(_o_, null, null);
	}

	DeletedRole(xbean.DeletedRole _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof DeletedRole) assign((DeletedRole)_o1_);
		else if (_o1_ instanceof DeletedRole.Data) assign((DeletedRole.Data)_o1_);
		else if (_o1_ instanceof DeletedRole.Const) assign(((DeletedRole.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(DeletedRole _o_) {
		_o_._xdb_verify_unsafe_();
		roleid = _o_.roleid;
		username = _o_.username;
		newusername = _o_.newusername;
		deletetime = _o_.deletetime;
	}

	private void assign(DeletedRole.Data _o_) {
		roleid = _o_.roleid;
		username = _o_.username;
		newusername = _o_.newusername;
		deletetime = _o_.deletetime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(10240|  1));_os_.marshal(roleid);
    _os_.marshal((short)(18432|  2));_os_.marshal(username, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(18432|  3));_os_.marshal(newusername, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(10240|  4));_os_.marshal(deletetime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):roleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):roleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):roleid = _os_.unmarshal_int();
    				break;
    				case (18432|  2):username = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (18432|  3):newusername = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (10240|  4):deletetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):deletetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):deletetime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.DeletedRole copy() {
		_xdb_verify_unsafe_();
		return new DeletedRole(this);
	}

	@Override
	public xbean.DeletedRole toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DeletedRole toBean() {
		_xdb_verify_unsafe_();
		return new DeletedRole(this); // same as copy()
	}

	@Override
	public xbean.DeletedRole toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DeletedRole toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getRoleid() { // 
		_xdb_verify_unsafe_();
		return roleid;
	}

	@Override
	public String getUsername() { // 
		_xdb_verify_unsafe_();
		return username;
	}

	@Override
	public com.goldhuman.Common.Octets getUsernameOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getUsername(), xdb.Const.IO_CHARSET);
	}

	@Override
	public String getNewusername() { // 
		_xdb_verify_unsafe_();
		return newusername;
	}

	@Override
	public com.goldhuman.Common.Octets getNewusernameOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getNewusername(), xdb.Const.IO_CHARSET);
	}

	@Override
	public long getDeletetime() { // 
		_xdb_verify_unsafe_();
		return deletetime;
	}

	@Override
	public void setRoleid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleid) {
					public void rollback() { roleid = _xdb_saved; }
				};}});
		roleid = _v_;
	}

	@Override
	public void setUsername(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "username") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, username) {
					public void rollback() { username = _xdb_saved; }
				};}});
		username = _v_;
	}

	@Override
	public void setUsernameOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setUsername(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setNewusername(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "newusername") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, newusername) {
					public void rollback() { newusername = _xdb_saved; }
				};}});
		newusername = _v_;
	}

	@Override
	public void setNewusernameOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setNewusername(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setDeletetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "deletetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, deletetime) {
					public void rollback() { deletetime = _xdb_saved; }
				};}});
		deletetime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		DeletedRole _o_ = null;
		if ( _o1_ instanceof DeletedRole ) _o_ = (DeletedRole)_o1_;
		else if ( _o1_ instanceof DeletedRole.Const ) _o_ = ((DeletedRole.Const)_o1_).nThis();
		else return false;
		if (roleid != _o_.roleid) return false;
		if (!username.equals(_o_.username)) return false;
		if (!newusername.equals(_o_.newusername)) return false;
		if (deletetime != _o_.deletetime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += roleid;
		_h_ += username.hashCode();
		_h_ += newusername.hashCode();
		_h_ += deletetime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid);
		_sb_.append(",");
		_sb_.append("'").append(username).append("'");
		_sb_.append(",");
		_sb_.append("'").append(newusername).append("'");
		_sb_.append(",");
		_sb_.append(deletetime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("username"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("newusername"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("deletetime"));
		return lb;
	}

	private class Const implements xbean.DeletedRole {
		DeletedRole nThis() {
			return DeletedRole.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.DeletedRole copy() {
			return DeletedRole.this.copy();
		}

		@Override
		public xbean.DeletedRole toData() {
			return DeletedRole.this.toData();
		}

		public xbean.DeletedRole toBean() {
			return DeletedRole.this.toBean();
		}

		@Override
		public xbean.DeletedRole toDataIf() {
			return DeletedRole.this.toDataIf();
		}

		public xbean.DeletedRole toBeanIf() {
			return DeletedRole.this.toBeanIf();
		}

		@Override
		public long getRoleid() { // 
			_xdb_verify_unsafe_();
			return roleid;
		}

		@Override
		public String getUsername() { // 
			_xdb_verify_unsafe_();
			return username;
		}

		@Override
		public com.goldhuman.Common.Octets getUsernameOctets() { // 
			_xdb_verify_unsafe_();
			return DeletedRole.this.getUsernameOctets();
		}

		@Override
		public String getNewusername() { // 
			_xdb_verify_unsafe_();
			return newusername;
		}

		@Override
		public com.goldhuman.Common.Octets getNewusernameOctets() { // 
			_xdb_verify_unsafe_();
			return DeletedRole.this.getNewusernameOctets();
		}

		@Override
		public long getDeletetime() { // 
			_xdb_verify_unsafe_();
			return deletetime;
		}

		@Override
		public void setRoleid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUsername(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUsernameOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNewusername(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNewusernameOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDeletetime(long _v_) { // 
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
			return DeletedRole.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return DeletedRole.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return DeletedRole.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return DeletedRole.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return DeletedRole.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return DeletedRole.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return DeletedRole.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return DeletedRole.this.hashCode();
		}

		@Override
		public String toString() {
			return DeletedRole.this.toString();
		}

	}

	public static final class Data implements xbean.DeletedRole {
		private long roleid; // 
		private String username; // 
		private String newusername; // 
		private long deletetime; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			username = "";
			newusername = "";
		}

		Data(xbean.DeletedRole _o1_) {
			if (_o1_ instanceof DeletedRole) assign((DeletedRole)_o1_);
			else if (_o1_ instanceof DeletedRole.Data) assign((DeletedRole.Data)_o1_);
			else if (_o1_ instanceof DeletedRole.Const) assign(((DeletedRole.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(DeletedRole _o_) {
			roleid = _o_.roleid;
			username = _o_.username;
			newusername = _o_.newusername;
			deletetime = _o_.deletetime;
		}

		private void assign(DeletedRole.Data _o_) {
			roleid = _o_.roleid;
			username = _o_.username;
			newusername = _o_.newusername;
			deletetime = _o_.deletetime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(10240|  1));_os_.marshal(roleid);
	_os_.marshal((short)(18432|  2));_os_.marshal(username, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(18432|  3));_os_.marshal(newusername, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(10240|  4));_os_.marshal(deletetime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):roleid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):roleid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):roleid = _os_.unmarshal_int();
					break;
					case (18432|  2):username = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (18432|  3):newusername = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (10240|  4):deletetime = _os_.unmarshal_long();
					break;
					case ( 6144|  4):deletetime = _os_.unmarshal_short();
					break;
					case ( 8192|  4):deletetime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.DeletedRole copy() {
			return new Data(this);
		}

		@Override
		public xbean.DeletedRole toData() {
			return new Data(this);
		}

		public xbean.DeletedRole toBean() {
			return new DeletedRole(this, null, null);
		}

		@Override
		public xbean.DeletedRole toDataIf() {
			return this;
		}

		public xbean.DeletedRole toBeanIf() {
			return new DeletedRole(this, null, null);
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
		public long getRoleid() { // 
			return roleid;
		}

		@Override
		public String getUsername() { // 
			return username;
		}

		@Override
		public com.goldhuman.Common.Octets getUsernameOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getUsername(), xdb.Const.IO_CHARSET);
		}

		@Override
		public String getNewusername() { // 
			return newusername;
		}

		@Override
		public com.goldhuman.Common.Octets getNewusernameOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getNewusername(), xdb.Const.IO_CHARSET);
		}

		@Override
		public long getDeletetime() { // 
			return deletetime;
		}

		@Override
		public void setRoleid(long _v_) { // 
			roleid = _v_;
		}

		@Override
		public void setUsername(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			username = _v_;
		}

		@Override
		public void setUsernameOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setUsername(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setNewusername(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			newusername = _v_;
		}

		@Override
		public void setNewusernameOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setNewusername(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setDeletetime(long _v_) { // 
			deletetime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof DeletedRole.Data)) return false;
			DeletedRole.Data _o_ = (DeletedRole.Data) _o1_;
			if (roleid != _o_.roleid) return false;
			if (!username.equals(_o_.username)) return false;
			if (!newusername.equals(_o_.newusername)) return false;
			if (deletetime != _o_.deletetime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += roleid;
			_h_ += username.hashCode();
			_h_ += newusername.hashCode();
			_h_ += deletetime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(roleid);
			_sb_.append(",");
			_sb_.append("'").append(username).append("'");
			_sb_.append(",");
			_sb_.append("'").append(newusername).append("'");
			_sb_.append(",");
			_sb_.append(deletetime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
