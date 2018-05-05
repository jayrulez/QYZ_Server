
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class OnesdkUserInfo extends xdb.XBean implements xbean.OnesdkUserInfo {
	private long userid; // 
	private long userinfoid; // 

	@Override
	public void _reset_unsafe_() {
		userid = 0L;
		userinfoid = 0L;
	}

	OnesdkUserInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public OnesdkUserInfo() {
		this(0, null, null);
	}

	public OnesdkUserInfo(OnesdkUserInfo _o_) {
		this(_o_, null, null);
	}

	OnesdkUserInfo(xbean.OnesdkUserInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof OnesdkUserInfo) assign((OnesdkUserInfo)_o1_);
		else if (_o1_ instanceof OnesdkUserInfo.Data) assign((OnesdkUserInfo.Data)_o1_);
		else if (_o1_ instanceof OnesdkUserInfo.Const) assign(((OnesdkUserInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(OnesdkUserInfo _o_) {
		_o_._xdb_verify_unsafe_();
		userid = _o_.userid;
		userinfoid = _o_.userinfoid;
	}

	private void assign(OnesdkUserInfo.Data _o_) {
		userid = _o_.userid;
		userinfoid = _o_.userinfoid;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(10240|  0));_os_.marshal(userid);
    _os_.marshal((short)(10240|  1));_os_.marshal(userinfoid);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  0):userid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  0):userid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  0):userid = _os_.unmarshal_int();
    				break;
    				case (10240|  1):userinfoid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):userinfoid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):userinfoid = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.OnesdkUserInfo copy() {
		_xdb_verify_unsafe_();
		return new OnesdkUserInfo(this);
	}

	@Override
	public xbean.OnesdkUserInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.OnesdkUserInfo toBean() {
		_xdb_verify_unsafe_();
		return new OnesdkUserInfo(this); // same as copy()
	}

	@Override
	public xbean.OnesdkUserInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.OnesdkUserInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getUserid() { // 
		_xdb_verify_unsafe_();
		return userid;
	}

	@Override
	public long getUserinfoid() { // 
		_xdb_verify_unsafe_();
		return userinfoid;
	}

	@Override
	public void setUserid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "userid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, userid) {
					public void rollback() { userid = _xdb_saved; }
				};}});
		userid = _v_;
	}

	@Override
	public void setUserinfoid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "userinfoid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, userinfoid) {
					public void rollback() { userinfoid = _xdb_saved; }
				};}});
		userinfoid = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		OnesdkUserInfo _o_ = null;
		if ( _o1_ instanceof OnesdkUserInfo ) _o_ = (OnesdkUserInfo)_o1_;
		else if ( _o1_ instanceof OnesdkUserInfo.Const ) _o_ = ((OnesdkUserInfo.Const)_o1_).nThis();
		else return false;
		if (userid != _o_.userid) return false;
		if (userinfoid != _o_.userinfoid) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += userid;
		_h_ += userinfoid;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(userid);
		_sb_.append(",");
		_sb_.append(userinfoid);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("userid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("userinfoid"));
		return lb;
	}

	private class Const implements xbean.OnesdkUserInfo {
		OnesdkUserInfo nThis() {
			return OnesdkUserInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.OnesdkUserInfo copy() {
			return OnesdkUserInfo.this.copy();
		}

		@Override
		public xbean.OnesdkUserInfo toData() {
			return OnesdkUserInfo.this.toData();
		}

		public xbean.OnesdkUserInfo toBean() {
			return OnesdkUserInfo.this.toBean();
		}

		@Override
		public xbean.OnesdkUserInfo toDataIf() {
			return OnesdkUserInfo.this.toDataIf();
		}

		public xbean.OnesdkUserInfo toBeanIf() {
			return OnesdkUserInfo.this.toBeanIf();
		}

		@Override
		public long getUserid() { // 
			_xdb_verify_unsafe_();
			return userid;
		}

		@Override
		public long getUserinfoid() { // 
			_xdb_verify_unsafe_();
			return userinfoid;
		}

		@Override
		public void setUserid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUserinfoid(long _v_) { // 
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
			return OnesdkUserInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return OnesdkUserInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return OnesdkUserInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return OnesdkUserInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return OnesdkUserInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return OnesdkUserInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return OnesdkUserInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return OnesdkUserInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return OnesdkUserInfo.this.toString();
		}

	}

	public static final class Data implements xbean.OnesdkUserInfo {
		private long userid; // 
		private long userinfoid; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.OnesdkUserInfo _o1_) {
			if (_o1_ instanceof OnesdkUserInfo) assign((OnesdkUserInfo)_o1_);
			else if (_o1_ instanceof OnesdkUserInfo.Data) assign((OnesdkUserInfo.Data)_o1_);
			else if (_o1_ instanceof OnesdkUserInfo.Const) assign(((OnesdkUserInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(OnesdkUserInfo _o_) {
			userid = _o_.userid;
			userinfoid = _o_.userinfoid;
		}

		private void assign(OnesdkUserInfo.Data _o_) {
			userid = _o_.userid;
			userinfoid = _o_.userinfoid;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(10240|  0));_os_.marshal(userid);
	_os_.marshal((short)(10240|  1));_os_.marshal(userinfoid);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  0):userid = _os_.unmarshal_long();
					break;
					case ( 6144|  0):userid = _os_.unmarshal_short();
					break;
					case ( 8192|  0):userid = _os_.unmarshal_int();
					break;
					case (10240|  1):userinfoid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):userinfoid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):userinfoid = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.OnesdkUserInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.OnesdkUserInfo toData() {
			return new Data(this);
		}

		public xbean.OnesdkUserInfo toBean() {
			return new OnesdkUserInfo(this, null, null);
		}

		@Override
		public xbean.OnesdkUserInfo toDataIf() {
			return this;
		}

		public xbean.OnesdkUserInfo toBeanIf() {
			return new OnesdkUserInfo(this, null, null);
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
		public long getUserid() { // 
			return userid;
		}

		@Override
		public long getUserinfoid() { // 
			return userinfoid;
		}

		@Override
		public void setUserid(long _v_) { // 
			userid = _v_;
		}

		@Override
		public void setUserinfoid(long _v_) { // 
			userinfoid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof OnesdkUserInfo.Data)) return false;
			OnesdkUserInfo.Data _o_ = (OnesdkUserInfo.Data) _o1_;
			if (userid != _o_.userid) return false;
			if (userinfoid != _o_.userinfoid) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += userid;
			_h_ += userinfoid;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(userid);
			_sb_.append(",");
			_sb_.append(userinfoid);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
