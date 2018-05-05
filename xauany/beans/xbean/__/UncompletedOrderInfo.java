
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class UncompletedOrderInfo extends xdb.XBean implements xbean.UncompletedOrderInfo {
	private int serverid; // 
	private int plattype; // 
	private String platorderid; // 
	private long userid; // 
	private byte [] vars; // 
	private int times; // 

	@Override
	public void _reset_unsafe_() {
		serverid = 0;
		plattype = 0;
		platorderid = "";
		userid = 0L;
		vars = new byte[0];
		times = 0;
	}

	UncompletedOrderInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		platorderid = "";
		vars = new byte[0];
	}

	public UncompletedOrderInfo() {
		this(0, null, null);
	}

	public UncompletedOrderInfo(UncompletedOrderInfo _o_) {
		this(_o_, null, null);
	}

	UncompletedOrderInfo(xbean.UncompletedOrderInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof UncompletedOrderInfo) assign((UncompletedOrderInfo)_o1_);
		else if (_o1_ instanceof UncompletedOrderInfo.Data) assign((UncompletedOrderInfo.Data)_o1_);
		else if (_o1_ instanceof UncompletedOrderInfo.Const) assign(((UncompletedOrderInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(UncompletedOrderInfo _o_) {
		_o_._xdb_verify_unsafe_();
		serverid = _o_.serverid;
		plattype = _o_.plattype;
		platorderid = _o_.platorderid;
		userid = _o_.userid;
		vars = java.util.Arrays.copyOf(_o_.vars, _o_.vars.length);
		times = _o_.times;
	}

	private void assign(UncompletedOrderInfo.Data _o_) {
		serverid = _o_.serverid;
		plattype = _o_.plattype;
		platorderid = _o_.platorderid;
		userid = _o_.userid;
		vars = java.util.Arrays.copyOf(_o_.vars, _o_.vars.length);
		times = _o_.times;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)( 8192|  0));_os_.marshal(serverid);
    _os_.marshal((short)( 8192|  1));_os_.marshal(plattype);
    _os_.marshal((short)(18432|  2));_os_.marshal(platorderid, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(10240|  3));_os_.marshal(userid);
    _os_.marshal((short)(16384|  4));_os_.marshal(vars);
    _os_.marshal((short)( 8192|  5));_os_.marshal(times);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  0):serverid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  0):serverid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):plattype = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):plattype = _os_.unmarshal_short();
    				break;
    				case (18432|  2):platorderid = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (10240|  3):userid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):userid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):userid = _os_.unmarshal_int();
    				break;
    				case (16384|  4):vars = _os_.unmarshal_bytes();
    				break;
    				case ( 8192|  5):times = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):times = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.UncompletedOrderInfo copy() {
		_xdb_verify_unsafe_();
		return new UncompletedOrderInfo(this);
	}

	@Override
	public xbean.UncompletedOrderInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.UncompletedOrderInfo toBean() {
		_xdb_verify_unsafe_();
		return new UncompletedOrderInfo(this); // same as copy()
	}

	@Override
	public xbean.UncompletedOrderInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.UncompletedOrderInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getServerid() { // 
		_xdb_verify_unsafe_();
		return serverid;
	}

	@Override
	public int getPlattype() { // 
		_xdb_verify_unsafe_();
		return plattype;
	}

	@Override
	public String getPlatorderid() { // 
		_xdb_verify_unsafe_();
		return platorderid;
	}

	@Override
	public com.goldhuman.Common.Octets getPlatorderidOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getPlatorderid(), xdb.Const.IO_CHARSET);
	}

	@Override
	public long getUserid() { // 
		_xdb_verify_unsafe_();
		return userid;
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
	public int getTimes() { // 
		_xdb_verify_unsafe_();
		return times;
	}

	@Override
	public void setServerid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "serverid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, serverid) {
					public void rollback() { serverid = _xdb_saved; }
				};}});
		serverid = _v_;
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
	public void setPlatorderid(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "platorderid") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, platorderid) {
					public void rollback() { platorderid = _xdb_saved; }
				};}});
		platorderid = _v_;
	}

	@Override
	public void setPlatorderidOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setPlatorderid(_v_.getString(xdb.Const.IO_CHARSET));
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
	public void setTimes(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "times") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, times) {
					public void rollback() { times = _xdb_saved; }
				};}});
		times = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		UncompletedOrderInfo _o_ = null;
		if ( _o1_ instanceof UncompletedOrderInfo ) _o_ = (UncompletedOrderInfo)_o1_;
		else if ( _o1_ instanceof UncompletedOrderInfo.Const ) _o_ = ((UncompletedOrderInfo.Const)_o1_).nThis();
		else return false;
		if (serverid != _o_.serverid) return false;
		if (plattype != _o_.plattype) return false;
		if (!platorderid.equals(_o_.platorderid)) return false;
		if (userid != _o_.userid) return false;
		if (!java.util.Arrays.equals(vars, _o_.vars)) return false;
		if (times != _o_.times) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += serverid;
		_h_ += plattype;
		_h_ += platorderid.hashCode();
		_h_ += userid;
		_h_ += java.util.Arrays.hashCode(vars);
		_h_ += times;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(serverid);
		_sb_.append(",");
		_sb_.append(plattype);
		_sb_.append(",");
		_sb_.append("'").append(platorderid).append("'");
		_sb_.append(",");
		_sb_.append(userid);
		_sb_.append(",");
		_sb_.append('B').append(vars.length);
		_sb_.append(",");
		_sb_.append(times);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("serverid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("plattype"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("platorderid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("userid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("vars"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("times"));
		return lb;
	}

	private class Const implements xbean.UncompletedOrderInfo {
		UncompletedOrderInfo nThis() {
			return UncompletedOrderInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.UncompletedOrderInfo copy() {
			return UncompletedOrderInfo.this.copy();
		}

		@Override
		public xbean.UncompletedOrderInfo toData() {
			return UncompletedOrderInfo.this.toData();
		}

		public xbean.UncompletedOrderInfo toBean() {
			return UncompletedOrderInfo.this.toBean();
		}

		@Override
		public xbean.UncompletedOrderInfo toDataIf() {
			return UncompletedOrderInfo.this.toDataIf();
		}

		public xbean.UncompletedOrderInfo toBeanIf() {
			return UncompletedOrderInfo.this.toBeanIf();
		}

		@Override
		public int getServerid() { // 
			_xdb_verify_unsafe_();
			return serverid;
		}

		@Override
		public int getPlattype() { // 
			_xdb_verify_unsafe_();
			return plattype;
		}

		@Override
		public String getPlatorderid() { // 
			_xdb_verify_unsafe_();
			return platorderid;
		}

		@Override
		public com.goldhuman.Common.Octets getPlatorderidOctets() { // 
			_xdb_verify_unsafe_();
			return UncompletedOrderInfo.this.getPlatorderidOctets();
		}

		@Override
		public long getUserid() { // 
			_xdb_verify_unsafe_();
			return userid;
		}

		@Override
		public <T extends com.goldhuman.Common.Marshal.Marshal> T getVars(T _v_) { // 
			_xdb_verify_unsafe_();
			return UncompletedOrderInfo.this.getVars(_v_);
		}

		@Override
		public boolean isVarsEmpty() { // 
			_xdb_verify_unsafe_();
			return UncompletedOrderInfo.this.isVarsEmpty();
		}

		@Override
		public byte[] getVarsCopy() { // 
			_xdb_verify_unsafe_();
			return UncompletedOrderInfo.this.getVarsCopy();
		}

		@Override
		public int getTimes() { // 
			_xdb_verify_unsafe_();
			return times;
		}

		@Override
		public void setServerid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPlattype(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPlatorderid(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPlatorderidOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUserid(long _v_) { // 
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
		public void setTimes(int _v_) { // 
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
			return UncompletedOrderInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return UncompletedOrderInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return UncompletedOrderInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return UncompletedOrderInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return UncompletedOrderInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return UncompletedOrderInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return UncompletedOrderInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return UncompletedOrderInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return UncompletedOrderInfo.this.toString();
		}

	}

	public static final class Data implements xbean.UncompletedOrderInfo {
		private int serverid; // 
		private int plattype; // 
		private String platorderid; // 
		private long userid; // 
		private byte [] vars; // 
		private int times; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			platorderid = "";
			vars = new byte[0];
		}

		Data(xbean.UncompletedOrderInfo _o1_) {
			if (_o1_ instanceof UncompletedOrderInfo) assign((UncompletedOrderInfo)_o1_);
			else if (_o1_ instanceof UncompletedOrderInfo.Data) assign((UncompletedOrderInfo.Data)_o1_);
			else if (_o1_ instanceof UncompletedOrderInfo.Const) assign(((UncompletedOrderInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(UncompletedOrderInfo _o_) {
			serverid = _o_.serverid;
			plattype = _o_.plattype;
			platorderid = _o_.platorderid;
			userid = _o_.userid;
			vars = java.util.Arrays.copyOf(_o_.vars, _o_.vars.length);
			times = _o_.times;
		}

		private void assign(UncompletedOrderInfo.Data _o_) {
			serverid = _o_.serverid;
			plattype = _o_.plattype;
			platorderid = _o_.platorderid;
			userid = _o_.userid;
			vars = java.util.Arrays.copyOf(_o_.vars, _o_.vars.length);
			times = _o_.times;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)( 8192|  0));_os_.marshal(serverid);
	_os_.marshal((short)( 8192|  1));_os_.marshal(plattype);
	_os_.marshal((short)(18432|  2));_os_.marshal(platorderid, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(10240|  3));_os_.marshal(userid);
	_os_.marshal((short)(16384|  4));_os_.marshal(vars);
	_os_.marshal((short)( 8192|  5));_os_.marshal(times);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  0):serverid = _os_.unmarshal_int();
					break;
					case ( 6144|  0):serverid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):plattype = _os_.unmarshal_int();
					break;
					case ( 6144|  1):plattype = _os_.unmarshal_short();
					break;
					case (18432|  2):platorderid = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (10240|  3):userid = _os_.unmarshal_long();
					break;
					case ( 6144|  3):userid = _os_.unmarshal_short();
					break;
					case ( 8192|  3):userid = _os_.unmarshal_int();
					break;
					case (16384|  4):vars = _os_.unmarshal_bytes();
					break;
					case ( 8192|  5):times = _os_.unmarshal_int();
					break;
					case ( 6144|  5):times = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.UncompletedOrderInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.UncompletedOrderInfo toData() {
			return new Data(this);
		}

		public xbean.UncompletedOrderInfo toBean() {
			return new UncompletedOrderInfo(this, null, null);
		}

		@Override
		public xbean.UncompletedOrderInfo toDataIf() {
			return this;
		}

		public xbean.UncompletedOrderInfo toBeanIf() {
			return new UncompletedOrderInfo(this, null, null);
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
		public int getServerid() { // 
			return serverid;
		}

		@Override
		public int getPlattype() { // 
			return plattype;
		}

		@Override
		public String getPlatorderid() { // 
			return platorderid;
		}

		@Override
		public com.goldhuman.Common.Octets getPlatorderidOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getPlatorderid(), xdb.Const.IO_CHARSET);
		}

		@Override
		public long getUserid() { // 
			return userid;
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
		public int getTimes() { // 
			return times;
		}

		@Override
		public void setServerid(int _v_) { // 
			serverid = _v_;
		}

		@Override
		public void setPlattype(int _v_) { // 
			plattype = _v_;
		}

		@Override
		public void setPlatorderid(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			platorderid = _v_;
		}

		@Override
		public void setPlatorderidOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setPlatorderid(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setUserid(long _v_) { // 
			userid = _v_;
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
		public void setTimes(int _v_) { // 
			times = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof UncompletedOrderInfo.Data)) return false;
			UncompletedOrderInfo.Data _o_ = (UncompletedOrderInfo.Data) _o1_;
			if (serverid != _o_.serverid) return false;
			if (plattype != _o_.plattype) return false;
			if (!platorderid.equals(_o_.platorderid)) return false;
			if (userid != _o_.userid) return false;
			if (!java.util.Arrays.equals(vars, _o_.vars)) return false;
			if (times != _o_.times) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += serverid;
			_h_ += plattype;
			_h_ += platorderid.hashCode();
			_h_ += userid;
			_h_ += java.util.Arrays.hashCode(vars);
			_h_ += times;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(serverid);
			_sb_.append(",");
			_sb_.append(plattype);
			_sb_.append(",");
			_sb_.append("'").append(platorderid).append("'");
			_sb_.append(",");
			_sb_.append(userid);
			_sb_.append(",");
			_sb_.append('B').append(vars.length);
			_sb_.append(",");
			_sb_.append(times);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
