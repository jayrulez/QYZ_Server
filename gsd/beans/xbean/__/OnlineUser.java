
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class OnlineUser extends xdb.XBean implements xbean.OnlineUser {
	private String username; // 
	private int plattype; // 
	private String deviceid; // 
	private String os; // 
	private String peer; // 
	private long logintime; // 
	private String platform; // 

	@Override
	public void _reset_unsafe_() {
		username = "";
		plattype = 0;
		deviceid = "";
		os = "";
		peer = "";
		logintime = 0L;
		platform = "";
	}

	OnlineUser(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		username = "";
		deviceid = "";
		os = "";
		peer = "";
		platform = "";
	}

	public OnlineUser() {
		this(0, null, null);
	}

	public OnlineUser(OnlineUser _o_) {
		this(_o_, null, null);
	}

	OnlineUser(xbean.OnlineUser _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof OnlineUser) assign((OnlineUser)_o1_);
		else if (_o1_ instanceof OnlineUser.Data) assign((OnlineUser.Data)_o1_);
		else if (_o1_ instanceof OnlineUser.Const) assign(((OnlineUser.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(OnlineUser _o_) {
		_o_._xdb_verify_unsafe_();
		username = _o_.username;
		plattype = _o_.plattype;
		deviceid = _o_.deviceid;
		os = _o_.os;
		peer = _o_.peer;
		logintime = _o_.logintime;
		platform = _o_.platform;
	}

	private void assign(OnlineUser.Data _o_) {
		username = _o_.username;
		plattype = _o_.plattype;
		deviceid = _o_.deviceid;
		os = _o_.os;
		peer = _o_.peer;
		logintime = _o_.logintime;
		platform = _o_.platform;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)7);
    _os_.marshal((short)(18432|  1));_os_.marshal(username, xdb.Const.IO_CHARSET);
    _os_.marshal((short)( 8192|  2));_os_.marshal(plattype);
    _os_.marshal((short)(18432|  3));_os_.marshal(deviceid, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(18432|  4));_os_.marshal(os, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(18432|  5));_os_.marshal(peer, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(10240|  6));_os_.marshal(logintime);
    _os_.marshal((short)(18432|  7));_os_.marshal(platform, xdb.Const.IO_CHARSET);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (18432|  1):username = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case ( 8192|  2):plattype = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):plattype = _os_.unmarshal_short();
    				break;
    				case (18432|  3):deviceid = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (18432|  4):os = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (18432|  5):peer = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (10240|  6):logintime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  6):logintime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):logintime = _os_.unmarshal_int();
    				break;
    				case (18432|  7):platform = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.OnlineUser copy() {
		_xdb_verify_unsafe_();
		return new OnlineUser(this);
	}

	@Override
	public xbean.OnlineUser toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.OnlineUser toBean() {
		_xdb_verify_unsafe_();
		return new OnlineUser(this); // same as copy()
	}

	@Override
	public xbean.OnlineUser toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.OnlineUser toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
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
	public int getPlattype() { // 
		_xdb_verify_unsafe_();
		return plattype;
	}

	@Override
	public String getDeviceid() { // 
		_xdb_verify_unsafe_();
		return deviceid;
	}

	@Override
	public com.goldhuman.Common.Octets getDeviceidOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getDeviceid(), xdb.Const.IO_CHARSET);
	}

	@Override
	public String getOs() { // 
		_xdb_verify_unsafe_();
		return os;
	}

	@Override
	public com.goldhuman.Common.Octets getOsOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getOs(), xdb.Const.IO_CHARSET);
	}

	@Override
	public String getPeer() { // 
		_xdb_verify_unsafe_();
		return peer;
	}

	@Override
	public com.goldhuman.Common.Octets getPeerOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getPeer(), xdb.Const.IO_CHARSET);
	}

	@Override
	public long getLogintime() { // 
		_xdb_verify_unsafe_();
		return logintime;
	}

	@Override
	public String getPlatform() { // 
		_xdb_verify_unsafe_();
		return platform;
	}

	@Override
	public com.goldhuman.Common.Octets getPlatformOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getPlatform(), xdb.Const.IO_CHARSET);
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
	public void setPlattype(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "plattype") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, plattype) {
					public void rollback() { plattype = _xdb_saved; }
				};}});
		plattype = _v_;
	}

	@Override
	public void setDeviceid(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "deviceid") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, deviceid) {
					public void rollback() { deviceid = _xdb_saved; }
				};}});
		deviceid = _v_;
	}

	@Override
	public void setDeviceidOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setDeviceid(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setOs(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "os") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, os) {
					public void rollback() { os = _xdb_saved; }
				};}});
		os = _v_;
	}

	@Override
	public void setOsOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setOs(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setPeer(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "peer") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, peer) {
					public void rollback() { peer = _xdb_saved; }
				};}});
		peer = _v_;
	}

	@Override
	public void setPeerOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setPeer(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setLogintime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "logintime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, logintime) {
					public void rollback() { logintime = _xdb_saved; }
				};}});
		logintime = _v_;
	}

	@Override
	public void setPlatform(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "platform") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, platform) {
					public void rollback() { platform = _xdb_saved; }
				};}});
		platform = _v_;
	}

	@Override
	public void setPlatformOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setPlatform(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		OnlineUser _o_ = null;
		if ( _o1_ instanceof OnlineUser ) _o_ = (OnlineUser)_o1_;
		else if ( _o1_ instanceof OnlineUser.Const ) _o_ = ((OnlineUser.Const)_o1_).nThis();
		else return false;
		if (!username.equals(_o_.username)) return false;
		if (plattype != _o_.plattype) return false;
		if (!deviceid.equals(_o_.deviceid)) return false;
		if (!os.equals(_o_.os)) return false;
		if (!peer.equals(_o_.peer)) return false;
		if (logintime != _o_.logintime) return false;
		if (!platform.equals(_o_.platform)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += username.hashCode();
		_h_ += plattype;
		_h_ += deviceid.hashCode();
		_h_ += os.hashCode();
		_h_ += peer.hashCode();
		_h_ += logintime;
		_h_ += platform.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("'").append(username).append("'");
		_sb_.append(",");
		_sb_.append(plattype);
		_sb_.append(",");
		_sb_.append("'").append(deviceid).append("'");
		_sb_.append(",");
		_sb_.append("'").append(os).append("'");
		_sb_.append(",");
		_sb_.append("'").append(peer).append("'");
		_sb_.append(",");
		_sb_.append(logintime);
		_sb_.append(",");
		_sb_.append("'").append(platform).append("'");
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("username"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("plattype"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("deviceid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("os"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("peer"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("logintime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("platform"));
		return lb;
	}

	private class Const implements xbean.OnlineUser {
		OnlineUser nThis() {
			return OnlineUser.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.OnlineUser copy() {
			return OnlineUser.this.copy();
		}

		@Override
		public xbean.OnlineUser toData() {
			return OnlineUser.this.toData();
		}

		public xbean.OnlineUser toBean() {
			return OnlineUser.this.toBean();
		}

		@Override
		public xbean.OnlineUser toDataIf() {
			return OnlineUser.this.toDataIf();
		}

		public xbean.OnlineUser toBeanIf() {
			return OnlineUser.this.toBeanIf();
		}

		@Override
		public String getUsername() { // 
			_xdb_verify_unsafe_();
			return username;
		}

		@Override
		public com.goldhuman.Common.Octets getUsernameOctets() { // 
			_xdb_verify_unsafe_();
			return OnlineUser.this.getUsernameOctets();
		}

		@Override
		public int getPlattype() { // 
			_xdb_verify_unsafe_();
			return plattype;
		}

		@Override
		public String getDeviceid() { // 
			_xdb_verify_unsafe_();
			return deviceid;
		}

		@Override
		public com.goldhuman.Common.Octets getDeviceidOctets() { // 
			_xdb_verify_unsafe_();
			return OnlineUser.this.getDeviceidOctets();
		}

		@Override
		public String getOs() { // 
			_xdb_verify_unsafe_();
			return os;
		}

		@Override
		public com.goldhuman.Common.Octets getOsOctets() { // 
			_xdb_verify_unsafe_();
			return OnlineUser.this.getOsOctets();
		}

		@Override
		public String getPeer() { // 
			_xdb_verify_unsafe_();
			return peer;
		}

		@Override
		public com.goldhuman.Common.Octets getPeerOctets() { // 
			_xdb_verify_unsafe_();
			return OnlineUser.this.getPeerOctets();
		}

		@Override
		public long getLogintime() { // 
			_xdb_verify_unsafe_();
			return logintime;
		}

		@Override
		public String getPlatform() { // 
			_xdb_verify_unsafe_();
			return platform;
		}

		@Override
		public com.goldhuman.Common.Octets getPlatformOctets() { // 
			_xdb_verify_unsafe_();
			return OnlineUser.this.getPlatformOctets();
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
		public void setPlattype(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDeviceid(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDeviceidOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setOs(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setOsOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPeer(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPeerOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLogintime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPlatform(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPlatformOctets(com.goldhuman.Common.Octets _v_) { // 
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
			return OnlineUser.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return OnlineUser.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return OnlineUser.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return OnlineUser.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return OnlineUser.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return OnlineUser.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return OnlineUser.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return OnlineUser.this.hashCode();
		}

		@Override
		public String toString() {
			return OnlineUser.this.toString();
		}

	}

	public static final class Data implements xbean.OnlineUser {
		private String username; // 
		private int plattype; // 
		private String deviceid; // 
		private String os; // 
		private String peer; // 
		private long logintime; // 
		private String platform; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			username = "";
			deviceid = "";
			os = "";
			peer = "";
			platform = "";
		}

		Data(xbean.OnlineUser _o1_) {
			if (_o1_ instanceof OnlineUser) assign((OnlineUser)_o1_);
			else if (_o1_ instanceof OnlineUser.Data) assign((OnlineUser.Data)_o1_);
			else if (_o1_ instanceof OnlineUser.Const) assign(((OnlineUser.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(OnlineUser _o_) {
			username = _o_.username;
			plattype = _o_.plattype;
			deviceid = _o_.deviceid;
			os = _o_.os;
			peer = _o_.peer;
			logintime = _o_.logintime;
			platform = _o_.platform;
		}

		private void assign(OnlineUser.Data _o_) {
			username = _o_.username;
			plattype = _o_.plattype;
			deviceid = _o_.deviceid;
			os = _o_.os;
			peer = _o_.peer;
			logintime = _o_.logintime;
			platform = _o_.platform;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)7);
	_os_.marshal((short)(18432|  1));_os_.marshal(username, xdb.Const.IO_CHARSET);
	_os_.marshal((short)( 8192|  2));_os_.marshal(plattype);
	_os_.marshal((short)(18432|  3));_os_.marshal(deviceid, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(18432|  4));_os_.marshal(os, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(18432|  5));_os_.marshal(peer, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(10240|  6));_os_.marshal(logintime);
	_os_.marshal((short)(18432|  7));_os_.marshal(platform, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (18432|  1):username = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case ( 8192|  2):plattype = _os_.unmarshal_int();
					break;
					case ( 6144|  2):plattype = _os_.unmarshal_short();
					break;
					case (18432|  3):deviceid = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (18432|  4):os = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (18432|  5):peer = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (10240|  6):logintime = _os_.unmarshal_long();
					break;
					case ( 6144|  6):logintime = _os_.unmarshal_short();
					break;
					case ( 8192|  6):logintime = _os_.unmarshal_int();
					break;
					case (18432|  7):platform = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.OnlineUser copy() {
			return new Data(this);
		}

		@Override
		public xbean.OnlineUser toData() {
			return new Data(this);
		}

		public xbean.OnlineUser toBean() {
			return new OnlineUser(this, null, null);
		}

		@Override
		public xbean.OnlineUser toDataIf() {
			return this;
		}

		public xbean.OnlineUser toBeanIf() {
			return new OnlineUser(this, null, null);
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
		public String getUsername() { // 
			return username;
		}

		@Override
		public com.goldhuman.Common.Octets getUsernameOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getUsername(), xdb.Const.IO_CHARSET);
		}

		@Override
		public int getPlattype() { // 
			return plattype;
		}

		@Override
		public String getDeviceid() { // 
			return deviceid;
		}

		@Override
		public com.goldhuman.Common.Octets getDeviceidOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getDeviceid(), xdb.Const.IO_CHARSET);
		}

		@Override
		public String getOs() { // 
			return os;
		}

		@Override
		public com.goldhuman.Common.Octets getOsOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getOs(), xdb.Const.IO_CHARSET);
		}

		@Override
		public String getPeer() { // 
			return peer;
		}

		@Override
		public com.goldhuman.Common.Octets getPeerOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getPeer(), xdb.Const.IO_CHARSET);
		}

		@Override
		public long getLogintime() { // 
			return logintime;
		}

		@Override
		public String getPlatform() { // 
			return platform;
		}

		@Override
		public com.goldhuman.Common.Octets getPlatformOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getPlatform(), xdb.Const.IO_CHARSET);
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
		public void setPlattype(int _v_) { // 
			plattype = _v_;
		}

		@Override
		public void setDeviceid(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			deviceid = _v_;
		}

		@Override
		public void setDeviceidOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setDeviceid(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setOs(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			os = _v_;
		}

		@Override
		public void setOsOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setOs(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setPeer(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			peer = _v_;
		}

		@Override
		public void setPeerOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setPeer(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setLogintime(long _v_) { // 
			logintime = _v_;
		}

		@Override
		public void setPlatform(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			platform = _v_;
		}

		@Override
		public void setPlatformOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setPlatform(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof OnlineUser.Data)) return false;
			OnlineUser.Data _o_ = (OnlineUser.Data) _o1_;
			if (!username.equals(_o_.username)) return false;
			if (plattype != _o_.plattype) return false;
			if (!deviceid.equals(_o_.deviceid)) return false;
			if (!os.equals(_o_.os)) return false;
			if (!peer.equals(_o_.peer)) return false;
			if (logintime != _o_.logintime) return false;
			if (!platform.equals(_o_.platform)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += username.hashCode();
			_h_ += plattype;
			_h_ += deviceid.hashCode();
			_h_ += os.hashCode();
			_h_ += peer.hashCode();
			_h_ += logintime;
			_h_ += platform.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append("'").append(username).append("'");
			_sb_.append(",");
			_sb_.append(plattype);
			_sb_.append(",");
			_sb_.append("'").append(deviceid).append("'");
			_sb_.append(",");
			_sb_.append("'").append(os).append("'");
			_sb_.append(",");
			_sb_.append("'").append(peer).append("'");
			_sb_.append(",");
			_sb_.append(logintime);
			_sb_.append(",");
			_sb_.append("'").append(platform).append("'");
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
