
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class User extends xdb.XBean implements xbean.User {
	private java.util.LinkedList<Long> roleids; // 
	private long lastloginrole; // 
	private int deletedroles; // 
	private java.util.HashMap<Long, Long> deleteinfo; // 

	@Override
	public void _reset_unsafe_() {
		roleids.clear();
		lastloginrole = 0L;
		deletedroles = 0;
		deleteinfo.clear();
	}

	User(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		roleids = new java.util.LinkedList<Long>();
		deleteinfo = new java.util.HashMap<Long, Long>();
	}

	public User() {
		this(0, null, null);
	}

	public User(User _o_) {
		this(_o_, null, null);
	}

	User(xbean.User _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof User) assign((User)_o1_);
		else if (_o1_ instanceof User.Data) assign((User.Data)_o1_);
		else if (_o1_ instanceof User.Const) assign(((User.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(User _o_) {
		_o_._xdb_verify_unsafe_();
		roleids = new java.util.LinkedList<Long>();
		roleids.addAll(_o_.roleids);
		lastloginrole = _o_.lastloginrole;
		deletedroles = _o_.deletedroles;
		deleteinfo = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.deleteinfo.entrySet())
			deleteinfo.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(User.Data _o_) {
		roleids = new java.util.LinkedList<Long>();
		roleids.addAll(_o_.roleids);
		lastloginrole = _o_.lastloginrole;
		deletedroles = _o_.deletedroles;
		deleteinfo = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.deleteinfo.entrySet())
			deleteinfo.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(roleids.size());
for (Long _v_ : roleids) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  2));_os_.marshal(lastloginrole);
    _os_.marshal((short)( 8192|  3));_os_.marshal(deletedroles);
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(deleteinfo.size());
for (java.util.Map.Entry<Long, Long> _e_ : deleteinfo.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
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
	roleids.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (10240|  2):lastloginrole = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):lastloginrole = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):lastloginrole = _os_.unmarshal_int();
    				break;
    				case ( 8192|  3):deletedroles = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):deletedroles = _os_.unmarshal_short();
    				break;
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		deleteinfo = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		deleteinfo.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.User copy() {
		_xdb_verify_unsafe_();
		return new User(this);
	}

	@Override
	public xbean.User toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.User toBean() {
		_xdb_verify_unsafe_();
		return new User(this); // same as copy()
	}

	@Override
	public xbean.User toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.User toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<Long> getRoleids() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "roleids"), roleids);
	}

	public java.util.List<Long> getRoleidsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Long> roleids;
		User _o_ = this;
		roleids = new java.util.LinkedList<Long>();
		roleids.addAll(_o_.roleids);
		return roleids;
	}

	@Override
	public long getLastloginrole() { // 
		_xdb_verify_unsafe_();
		return lastloginrole;
	}

	@Override
	public int getDeletedroles() { // 
		_xdb_verify_unsafe_();
		return deletedroles;
	}

	@Override
	public java.util.Map<Long, Long> getDeleteinfo() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "deleteinfo"), deleteinfo);
	}

	@Override
	public java.util.Map<Long, Long> getDeleteinfoAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> deleteinfo;
		User _o_ = this;
		deleteinfo = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.deleteinfo.entrySet())
			deleteinfo.put(_e_.getKey(), _e_.getValue());
		return deleteinfo;
	}

	@Override
	public void setLastloginrole(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastloginrole") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastloginrole) {
					public void rollback() { lastloginrole = _xdb_saved; }
				};}});
		lastloginrole = _v_;
	}

	@Override
	public void setDeletedroles(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "deletedroles") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, deletedroles) {
					public void rollback() { deletedroles = _xdb_saved; }
				};}});
		deletedroles = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		User _o_ = null;
		if ( _o1_ instanceof User ) _o_ = (User)_o1_;
		else if ( _o1_ instanceof User.Const ) _o_ = ((User.Const)_o1_).nThis();
		else return false;
		if (!roleids.equals(_o_.roleids)) return false;
		if (lastloginrole != _o_.lastloginrole) return false;
		if (deletedroles != _o_.deletedroles) return false;
		if (!deleteinfo.equals(_o_.deleteinfo)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += roleids.hashCode();
		_h_ += lastloginrole;
		_h_ += deletedroles;
		_h_ += deleteinfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleids);
		_sb_.append(",");
		_sb_.append(lastloginrole);
		_sb_.append(",");
		_sb_.append(deletedroles);
		_sb_.append(",");
		_sb_.append(deleteinfo);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleids"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastloginrole"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("deletedroles"));
		lb.add(new xdb.logs.ListenableMap().setVarName("deleteinfo"));
		return lb;
	}

	private class Const implements xbean.User {
		User nThis() {
			return User.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.User copy() {
			return User.this.copy();
		}

		@Override
		public xbean.User toData() {
			return User.this.toData();
		}

		public xbean.User toBean() {
			return User.this.toBean();
		}

		@Override
		public xbean.User toDataIf() {
			return User.this.toDataIf();
		}

		public xbean.User toBeanIf() {
			return User.this.toBeanIf();
		}

		@Override
		public java.util.List<Long> getRoleids() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(roleids);
		}

		public java.util.List<Long> getRoleidsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Long> roleids;
			User _o_ = User.this;
		roleids = new java.util.LinkedList<Long>();
		roleids.addAll(_o_.roleids);
			return roleids;
		}

		@Override
		public long getLastloginrole() { // 
			_xdb_verify_unsafe_();
			return lastloginrole;
		}

		@Override
		public int getDeletedroles() { // 
			_xdb_verify_unsafe_();
			return deletedroles;
		}

		@Override
		public java.util.Map<Long, Long> getDeleteinfo() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(deleteinfo);
		}

		@Override
		public java.util.Map<Long, Long> getDeleteinfoAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> deleteinfo;
			User _o_ = User.this;
			deleteinfo = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.deleteinfo.entrySet())
				deleteinfo.put(_e_.getKey(), _e_.getValue());
			return deleteinfo;
		}

		@Override
		public void setLastloginrole(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDeletedroles(int _v_) { // 
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
			return User.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return User.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return User.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return User.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return User.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return User.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return User.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return User.this.hashCode();
		}

		@Override
		public String toString() {
			return User.this.toString();
		}

	}

	public static final class Data implements xbean.User {
		private java.util.LinkedList<Long> roleids; // 
		private long lastloginrole; // 
		private int deletedroles; // 
		private java.util.HashMap<Long, Long> deleteinfo; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			roleids = new java.util.LinkedList<Long>();
			deleteinfo = new java.util.HashMap<Long, Long>();
		}

		Data(xbean.User _o1_) {
			if (_o1_ instanceof User) assign((User)_o1_);
			else if (_o1_ instanceof User.Data) assign((User.Data)_o1_);
			else if (_o1_ instanceof User.Const) assign(((User.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(User _o_) {
			roleids = new java.util.LinkedList<Long>();
			roleids.addAll(_o_.roleids);
			lastloginrole = _o_.lastloginrole;
			deletedroles = _o_.deletedroles;
			deleteinfo = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.deleteinfo.entrySet())
				deleteinfo.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(User.Data _o_) {
			roleids = new java.util.LinkedList<Long>();
			roleids.addAll(_o_.roleids);
			lastloginrole = _o_.lastloginrole;
			deletedroles = _o_.deletedroles;
			deleteinfo = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.deleteinfo.entrySet())
				deleteinfo.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(roleids.size());
for (Long _v_ : roleids) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  2));_os_.marshal(lastloginrole);
	_os_.marshal((short)( 8192|  3));_os_.marshal(deletedroles);
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(deleteinfo.size());
for (java.util.Map.Entry<Long, Long> _e_ : deleteinfo.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
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
	roleids.add(_v_);
}
_os_ = _temp_;}
					break;
					case (10240|  2):lastloginrole = _os_.unmarshal_long();
					break;
					case ( 6144|  2):lastloginrole = _os_.unmarshal_short();
					break;
					case ( 8192|  2):lastloginrole = _os_.unmarshal_int();
					break;
					case ( 8192|  3):deletedroles = _os_.unmarshal_int();
					break;
					case ( 6144|  3):deletedroles = _os_.unmarshal_short();
					break;
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		deleteinfo = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		deleteinfo.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.User copy() {
			return new Data(this);
		}

		@Override
		public xbean.User toData() {
			return new Data(this);
		}

		public xbean.User toBean() {
			return new User(this, null, null);
		}

		@Override
		public xbean.User toDataIf() {
			return this;
		}

		public xbean.User toBeanIf() {
			return new User(this, null, null);
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
		public java.util.List<Long> getRoleids() { // 
			return roleids;
		}

		@Override
		public java.util.List<Long> getRoleidsAsData() { // 
			return roleids;
		}

		@Override
		public long getLastloginrole() { // 
			return lastloginrole;
		}

		@Override
		public int getDeletedroles() { // 
			return deletedroles;
		}

		@Override
		public java.util.Map<Long, Long> getDeleteinfo() { // 
			return deleteinfo;
		}

		@Override
		public java.util.Map<Long, Long> getDeleteinfoAsData() { // 
			return deleteinfo;
		}

		@Override
		public void setLastloginrole(long _v_) { // 
			lastloginrole = _v_;
		}

		@Override
		public void setDeletedroles(int _v_) { // 
			deletedroles = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof User.Data)) return false;
			User.Data _o_ = (User.Data) _o1_;
			if (!roleids.equals(_o_.roleids)) return false;
			if (lastloginrole != _o_.lastloginrole) return false;
			if (deletedroles != _o_.deletedroles) return false;
			if (!deleteinfo.equals(_o_.deleteinfo)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += roleids.hashCode();
			_h_ += lastloginrole;
			_h_ += deletedroles;
			_h_ += deleteinfo.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(roleids);
			_sb_.append(",");
			_sb_.append(lastloginrole);
			_sb_.append(",");
			_sb_.append(deletedroles);
			_sb_.append(",");
			_sb_.append(deleteinfo);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
