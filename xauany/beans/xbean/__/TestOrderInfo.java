
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class TestOrderInfo extends xdb.XBean implements xbean.TestOrderInfo {
	private long createtime; // 
	private String gsorderid; // 游戏服务器订单id
	private String useridentity; // 平台用户id
	private byte [] vars; // 

	@Override
	public void _reset_unsafe_() {
		createtime = 0L;
		gsorderid = "";
		useridentity = "";
		vars = new byte[0];
	}

	TestOrderInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		gsorderid = "";
		useridentity = "";
		vars = new byte[0];
	}

	public TestOrderInfo() {
		this(0, null, null);
	}

	public TestOrderInfo(TestOrderInfo _o_) {
		this(_o_, null, null);
	}

	TestOrderInfo(xbean.TestOrderInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof TestOrderInfo) assign((TestOrderInfo)_o1_);
		else if (_o1_ instanceof TestOrderInfo.Data) assign((TestOrderInfo.Data)_o1_);
		else if (_o1_ instanceof TestOrderInfo.Const) assign(((TestOrderInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(TestOrderInfo _o_) {
		_o_._xdb_verify_unsafe_();
		createtime = _o_.createtime;
		gsorderid = _o_.gsorderid;
		useridentity = _o_.useridentity;
		vars = java.util.Arrays.copyOf(_o_.vars, _o_.vars.length);
	}

	private void assign(TestOrderInfo.Data _o_) {
		createtime = _o_.createtime;
		gsorderid = _o_.gsorderid;
		useridentity = _o_.useridentity;
		vars = java.util.Arrays.copyOf(_o_.vars, _o_.vars.length);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(10240|  0));_os_.marshal(createtime);
    _os_.marshal((short)(18432|  1));_os_.marshal(gsorderid, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(18432|  2));_os_.marshal(useridentity, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(16384|  3));_os_.marshal(vars);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  0):createtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  0):createtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  0):createtime = _os_.unmarshal_int();
    				break;
    				case (18432|  1):gsorderid = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (18432|  2):useridentity = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (16384|  3):vars = _os_.unmarshal_bytes();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.TestOrderInfo copy() {
		_xdb_verify_unsafe_();
		return new TestOrderInfo(this);
	}

	@Override
	public xbean.TestOrderInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TestOrderInfo toBean() {
		_xdb_verify_unsafe_();
		return new TestOrderInfo(this); // same as copy()
	}

	@Override
	public xbean.TestOrderInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TestOrderInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getCreatetime() { // 
		_xdb_verify_unsafe_();
		return createtime;
	}

	@Override
	public String getGsorderid() { // 游戏服务器订单id
		_xdb_verify_unsafe_();
		return gsorderid;
	}

	@Override
	public com.goldhuman.Common.Octets getGsorderidOctets() { // 游戏服务器订单id
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getGsorderid(), xdb.Const.IO_CHARSET);
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
	public <T extends com.goldhuman.Common.Marshal.Marshal> T getVars(T _v_) { // 
		_xdb_verify_unsafe_();
		try {
			_v_.unmarshal(OctetsStream.wrap(com.goldhuman.Common.Octets.wrap(vars)));
			return _v_;
		} catch (MarshalException _e_) {
			throw new xio.MarshalError();
		}
	}

	@Override
	public boolean isVarsEmpty() { // 
		_xdb_verify_unsafe_();
		return vars.length == 0;
	}

	@Override
	public byte[] getVarsCopy() { // 
		_xdb_verify_unsafe_();
		return java.util.Arrays.copyOf(vars, vars.length);
	}

	@Override
	public void setCreatetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "createtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, createtime) {
					public void rollback() { createtime = _xdb_saved; }
				};}});
		createtime = _v_;
	}

	@Override
	public void setGsorderid(String _v_) { // 游戏服务器订单id
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "gsorderid") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, gsorderid) {
					public void rollback() { gsorderid = _xdb_saved; }
				};}});
		gsorderid = _v_;
	}

	@Override
	public void setGsorderidOctets(com.goldhuman.Common.Octets _v_) { // 游戏服务器订单id
		_xdb_verify_unsafe_();
		this.setGsorderid(_v_.getString(xdb.Const.IO_CHARSET));
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
	public void setVars(com.goldhuman.Common.Marshal.Marshal _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "vars") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<byte []>(this, vars) {
					public void rollback() { vars = _xdb_saved; }
			}; }});
		vars = _v_.marshal(new OctetsStream()).getBytes();
	}

	@Override
	public void setVarsCopy(byte[] _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "vars") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<byte []>(this, vars) {
					public void rollback() { vars = _xdb_saved; }
			}; }});
		vars = java.util.Arrays.copyOf(_v_, _v_.length);
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		TestOrderInfo _o_ = null;
		if ( _o1_ instanceof TestOrderInfo ) _o_ = (TestOrderInfo)_o1_;
		else if ( _o1_ instanceof TestOrderInfo.Const ) _o_ = ((TestOrderInfo.Const)_o1_).nThis();
		else return false;
		if (createtime != _o_.createtime) return false;
		if (!gsorderid.equals(_o_.gsorderid)) return false;
		if (!useridentity.equals(_o_.useridentity)) return false;
		if (!java.util.Arrays.equals(vars, _o_.vars)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += createtime;
		_h_ += gsorderid.hashCode();
		_h_ += useridentity.hashCode();
		_h_ += java.util.Arrays.hashCode(vars);
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(createtime);
		_sb_.append(",");
		_sb_.append("'").append(gsorderid).append("'");
		_sb_.append(",");
		_sb_.append("'").append(useridentity).append("'");
		_sb_.append(",");
		_sb_.append('B').append(vars.length);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("createtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("gsorderid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("useridentity"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("vars"));
		return lb;
	}

	private class Const implements xbean.TestOrderInfo {
		TestOrderInfo nThis() {
			return TestOrderInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.TestOrderInfo copy() {
			return TestOrderInfo.this.copy();
		}

		@Override
		public xbean.TestOrderInfo toData() {
			return TestOrderInfo.this.toData();
		}

		public xbean.TestOrderInfo toBean() {
			return TestOrderInfo.this.toBean();
		}

		@Override
		public xbean.TestOrderInfo toDataIf() {
			return TestOrderInfo.this.toDataIf();
		}

		public xbean.TestOrderInfo toBeanIf() {
			return TestOrderInfo.this.toBeanIf();
		}

		@Override
		public long getCreatetime() { // 
			_xdb_verify_unsafe_();
			return createtime;
		}

		@Override
		public String getGsorderid() { // 游戏服务器订单id
			_xdb_verify_unsafe_();
			return gsorderid;
		}

		@Override
		public com.goldhuman.Common.Octets getGsorderidOctets() { // 游戏服务器订单id
			_xdb_verify_unsafe_();
			return TestOrderInfo.this.getGsorderidOctets();
		}

		@Override
		public String getUseridentity() { // 平台用户id
			_xdb_verify_unsafe_();
			return useridentity;
		}

		@Override
		public com.goldhuman.Common.Octets getUseridentityOctets() { // 平台用户id
			_xdb_verify_unsafe_();
			return TestOrderInfo.this.getUseridentityOctets();
		}

		@Override
		public <T extends com.goldhuman.Common.Marshal.Marshal> T getVars(T _v_) { // 
			_xdb_verify_unsafe_();
			return TestOrderInfo.this.getVars(_v_);
		}

		@Override
		public boolean isVarsEmpty() { // 
			_xdb_verify_unsafe_();
			return TestOrderInfo.this.isVarsEmpty();
		}

		@Override
		public byte[] getVarsCopy() { // 
			_xdb_verify_unsafe_();
			return TestOrderInfo.this.getVarsCopy();
		}

		@Override
		public void setCreatetime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGsorderid(String _v_) { // 游戏服务器订单id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGsorderidOctets(com.goldhuman.Common.Octets _v_) { // 游戏服务器订单id
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
		public void setVars(com.goldhuman.Common.Marshal.Marshal _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setVarsCopy(byte[] _v_) { // 
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
			return TestOrderInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return TestOrderInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return TestOrderInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return TestOrderInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return TestOrderInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return TestOrderInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return TestOrderInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return TestOrderInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return TestOrderInfo.this.toString();
		}

	}

	public static final class Data implements xbean.TestOrderInfo {
		private long createtime; // 
		private String gsorderid; // 游戏服务器订单id
		private String useridentity; // 平台用户id
		private byte [] vars; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			gsorderid = "";
			useridentity = "";
			vars = new byte[0];
		}

		Data(xbean.TestOrderInfo _o1_) {
			if (_o1_ instanceof TestOrderInfo) assign((TestOrderInfo)_o1_);
			else if (_o1_ instanceof TestOrderInfo.Data) assign((TestOrderInfo.Data)_o1_);
			else if (_o1_ instanceof TestOrderInfo.Const) assign(((TestOrderInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(TestOrderInfo _o_) {
			createtime = _o_.createtime;
			gsorderid = _o_.gsorderid;
			useridentity = _o_.useridentity;
			vars = java.util.Arrays.copyOf(_o_.vars, _o_.vars.length);
		}

		private void assign(TestOrderInfo.Data _o_) {
			createtime = _o_.createtime;
			gsorderid = _o_.gsorderid;
			useridentity = _o_.useridentity;
			vars = java.util.Arrays.copyOf(_o_.vars, _o_.vars.length);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(10240|  0));_os_.marshal(createtime);
	_os_.marshal((short)(18432|  1));_os_.marshal(gsorderid, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(18432|  2));_os_.marshal(useridentity, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(16384|  3));_os_.marshal(vars);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  0):createtime = _os_.unmarshal_long();
					break;
					case ( 6144|  0):createtime = _os_.unmarshal_short();
					break;
					case ( 8192|  0):createtime = _os_.unmarshal_int();
					break;
					case (18432|  1):gsorderid = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (18432|  2):useridentity = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (16384|  3):vars = _os_.unmarshal_bytes();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.TestOrderInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.TestOrderInfo toData() {
			return new Data(this);
		}

		public xbean.TestOrderInfo toBean() {
			return new TestOrderInfo(this, null, null);
		}

		@Override
		public xbean.TestOrderInfo toDataIf() {
			return this;
		}

		public xbean.TestOrderInfo toBeanIf() {
			return new TestOrderInfo(this, null, null);
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
		public long getCreatetime() { // 
			return createtime;
		}

		@Override
		public String getGsorderid() { // 游戏服务器订单id
			return gsorderid;
		}

		@Override
		public com.goldhuman.Common.Octets getGsorderidOctets() { // 游戏服务器订单id
			return com.goldhuman.Common.Octets.wrap(getGsorderid(), xdb.Const.IO_CHARSET);
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
		public <T extends com.goldhuman.Common.Marshal.Marshal> T getVars(T _v_) { // 
			try {
				_v_.unmarshal(OctetsStream.wrap(com.goldhuman.Common.Octets.wrap(vars)));
				return _v_;
			} catch (MarshalException _e_) {
				throw new xio.MarshalError();
			}
		}

		@Override
		public boolean isVarsEmpty() { // 
			return vars.length == 0;
		}

		@Override
		public byte[] getVarsCopy() { // 
			return java.util.Arrays.copyOf(vars, vars.length);
		}

		@Override
		public void setCreatetime(long _v_) { // 
			createtime = _v_;
		}

		@Override
		public void setGsorderid(String _v_) { // 游戏服务器订单id
			if (null == _v_)
				throw new NullPointerException();
			gsorderid = _v_;
		}

		@Override
		public void setGsorderidOctets(com.goldhuman.Common.Octets _v_) { // 游戏服务器订单id
			this.setGsorderid(_v_.getString(xdb.Const.IO_CHARSET));
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
		public void setVars(com.goldhuman.Common.Marshal.Marshal _v_) { // 
			vars = _v_.marshal(new OctetsStream()).getBytes();
		}

		@Override
		public void setVarsCopy(byte[] _v_) { // 
			vars = java.util.Arrays.copyOf(_v_, _v_.length);
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof TestOrderInfo.Data)) return false;
			TestOrderInfo.Data _o_ = (TestOrderInfo.Data) _o1_;
			if (createtime != _o_.createtime) return false;
			if (!gsorderid.equals(_o_.gsorderid)) return false;
			if (!useridentity.equals(_o_.useridentity)) return false;
			if (!java.util.Arrays.equals(vars, _o_.vars)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += createtime;
			_h_ += gsorderid.hashCode();
			_h_ += useridentity.hashCode();
			_h_ += java.util.Arrays.hashCode(vars);
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(createtime);
			_sb_.append(",");
			_sb_.append("'").append(gsorderid).append("'");
			_sb_.append(",");
			_sb_.append("'").append(useridentity).append("'");
			_sb_.append(",");
			_sb_.append('B').append(vars.length);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
