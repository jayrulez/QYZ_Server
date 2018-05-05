
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class UserInfo extends xdb.XBean implements xbean.UserInfo {
	private int plattype; // 平台id
	private String useridentity; // 平台用户id

	@Override
	public void _reset_unsafe_() {
		plattype = 0;
		useridentity = "";
	}

	UserInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		useridentity = "";
	}

	public UserInfo() {
		this(0, null, null);
	}

	public UserInfo(UserInfo _o_) {
		this(_o_, null, null);
	}

	UserInfo(xbean.UserInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof UserInfo) assign((UserInfo)_o1_);
		else if (_o1_ instanceof UserInfo.Data) assign((UserInfo.Data)_o1_);
		else if (_o1_ instanceof UserInfo.Const) assign(((UserInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(UserInfo _o_) {
		_o_._xdb_verify_unsafe_();
		plattype = _o_.plattype;
		useridentity = _o_.useridentity;
	}

	private void assign(UserInfo.Data _o_) {
		plattype = _o_.plattype;
		useridentity = _o_.useridentity;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  0));_os_.marshal(plattype);
    _os_.marshal((short)(18432|  1));_os_.marshal(useridentity, xdb.Const.IO_CHARSET);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  0):plattype = _os_.unmarshal_int();
    				break;
    				case ( 6144|  0):plattype = _os_.unmarshal_short();
    				break;
    				case (18432|  1):useridentity = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.UserInfo copy() {
		_xdb_verify_unsafe_();
		return new UserInfo(this);
	}

	@Override
	public xbean.UserInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.UserInfo toBean() {
		_xdb_verify_unsafe_();
		return new UserInfo(this); // same as copy()
	}

	@Override
	public xbean.UserInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.UserInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getPlattype() { // 平台id
		_xdb_verify_unsafe_();
		return plattype;
	}

	@Override
	public String getUseridentity() { // 平台用户id
		_xdb_verify_unsafe_();
		return useridentity;
	}

	@Override
	public com.goldhuman.Common.Octets getUseridentityOctets() { // 平台用户id
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getUseridentity(), xdb.Const.IO_CHARSET);
	}

	@Override
	public void setPlattype(int _v_) { // 平台id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "plattype") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, plattype) {
					public void rollback() { plattype = _xdb_saved; }
				};}});
		plattype = _v_;
	}

	@Override
	public void setUseridentity(String _v_) { // 平台用户id
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "useridentity") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, useridentity) {
					public void rollback() { useridentity = _xdb_saved; }
				};}});
		useridentity = _v_;
	}

	@Override
	public void setUseridentityOctets(com.goldhuman.Common.Octets _v_) { // 平台用户id
		_xdb_verify_unsafe_();
		this.setUseridentity(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		UserInfo _o_ = null;
		if ( _o1_ instanceof UserInfo ) _o_ = (UserInfo)_o1_;
		else if ( _o1_ instanceof UserInfo.Const ) _o_ = ((UserInfo.Const)_o1_).nThis();
		else return false;
		if (plattype != _o_.plattype) return false;
		if (!useridentity.equals(_o_.useridentity)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += plattype;
		_h_ += useridentity.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(plattype);
		_sb_.append(",");
		_sb_.append("'").append(useridentity).append("'");
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("plattype"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("useridentity"));
		return lb;
	}

	private class Const implements xbean.UserInfo {
		UserInfo nThis() {
			return UserInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.UserInfo copy() {
			return UserInfo.this.copy();
		}

		@Override
		public xbean.UserInfo toData() {
			return UserInfo.this.toData();
		}

		public xbean.UserInfo toBean() {
			return UserInfo.this.toBean();
		}

		@Override
		public xbean.UserInfo toDataIf() {
			return UserInfo.this.toDataIf();
		}

		public xbean.UserInfo toBeanIf() {
			return UserInfo.this.toBeanIf();
		}

		@Override
		public int getPlattype() { // 平台id
			_xdb_verify_unsafe_();
			return plattype;
		}

		@Override
		public String getUseridentity() { // 平台用户id
			_xdb_verify_unsafe_();
			return useridentity;
		}

		@Override
		public com.goldhuman.Common.Octets getUseridentityOctets() { // 平台用户id
			_xdb_verify_unsafe_();
			return UserInfo.this.getUseridentityOctets();
		}

		@Override
		public void setPlattype(int _v_) { // 平台id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUseridentity(String _v_) { // 平台用户id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUseridentityOctets(com.goldhuman.Common.Octets _v_) { // 平台用户id
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
			return UserInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return UserInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return UserInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return UserInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return UserInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return UserInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return UserInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return UserInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return UserInfo.this.toString();
		}

	}

	public static final class Data implements xbean.UserInfo {
		private int plattype; // 平台id
		private String useridentity; // 平台用户id

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			useridentity = "";
		}

		Data(xbean.UserInfo _o1_) {
			if (_o1_ instanceof UserInfo) assign((UserInfo)_o1_);
			else if (_o1_ instanceof UserInfo.Data) assign((UserInfo.Data)_o1_);
			else if (_o1_ instanceof UserInfo.Const) assign(((UserInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(UserInfo _o_) {
			plattype = _o_.plattype;
			useridentity = _o_.useridentity;
		}

		private void assign(UserInfo.Data _o_) {
			plattype = _o_.plattype;
			useridentity = _o_.useridentity;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  0));_os_.marshal(plattype);
	_os_.marshal((short)(18432|  1));_os_.marshal(useridentity, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  0):plattype = _os_.unmarshal_int();
					break;
					case ( 6144|  0):plattype = _os_.unmarshal_short();
					break;
					case (18432|  1):useridentity = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.UserInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.UserInfo toData() {
			return new Data(this);
		}

		public xbean.UserInfo toBean() {
			return new UserInfo(this, null, null);
		}

		@Override
		public xbean.UserInfo toDataIf() {
			return this;
		}

		public xbean.UserInfo toBeanIf() {
			return new UserInfo(this, null, null);
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
		public int getPlattype() { // 平台id
			return plattype;
		}

		@Override
		public String getUseridentity() { // 平台用户id
			return useridentity;
		}

		@Override
		public com.goldhuman.Common.Octets getUseridentityOctets() { // 平台用户id
			return com.goldhuman.Common.Octets.wrap(getUseridentity(), xdb.Const.IO_CHARSET);
		}

		@Override
		public void setPlattype(int _v_) { // 平台id
			plattype = _v_;
		}

		@Override
		public void setUseridentity(String _v_) { // 平台用户id
			if (null == _v_)
				throw new NullPointerException();
			useridentity = _v_;
		}

		@Override
		public void setUseridentityOctets(com.goldhuman.Common.Octets _v_) { // 平台用户id
			this.setUseridentity(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof UserInfo.Data)) return false;
			UserInfo.Data _o_ = (UserInfo.Data) _o1_;
			if (plattype != _o_.plattype) return false;
			if (!useridentity.equals(_o_.useridentity)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += plattype;
			_h_ += useridentity.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(plattype);
			_sb_.append(",");
			_sb_.append("'").append(useridentity).append("'");
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
