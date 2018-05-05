
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ApnsDevice extends xdb.XBean implements xbean.ApnsDevice {
	private String token; // 
	private long updatetime; // 

	@Override
	public void _reset_unsafe_() {
		token = "";
		updatetime = 0L;
	}

	ApnsDevice(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		token = "";
	}

	public ApnsDevice() {
		this(0, null, null);
	}

	public ApnsDevice(ApnsDevice _o_) {
		this(_o_, null, null);
	}

	ApnsDevice(xbean.ApnsDevice _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ApnsDevice) assign((ApnsDevice)_o1_);
		else if (_o1_ instanceof ApnsDevice.Data) assign((ApnsDevice.Data)_o1_);
		else if (_o1_ instanceof ApnsDevice.Const) assign(((ApnsDevice.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ApnsDevice _o_) {
		_o_._xdb_verify_unsafe_();
		token = _o_.token;
		updatetime = _o_.updatetime;
	}

	private void assign(ApnsDevice.Data _o_) {
		token = _o_.token;
		updatetime = _o_.updatetime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(18432|  0));_os_.marshal(token, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(10240|  1));_os_.marshal(updatetime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (18432|  0):token = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (10240|  1):updatetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):updatetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):updatetime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ApnsDevice copy() {
		_xdb_verify_unsafe_();
		return new ApnsDevice(this);
	}

	@Override
	public xbean.ApnsDevice toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ApnsDevice toBean() {
		_xdb_verify_unsafe_();
		return new ApnsDevice(this); // same as copy()
	}

	@Override
	public xbean.ApnsDevice toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ApnsDevice toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public String getToken() { // 
		_xdb_verify_unsafe_();
		return token;
	}

	@Override
	public com.goldhuman.Common.Octets getTokenOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getToken(), xdb.Const.IO_CHARSET);
	}

	@Override
	public long getUpdatetime() { // 
		_xdb_verify_unsafe_();
		return updatetime;
	}

	@Override
	public void setToken(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "token") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, token) {
					public void rollback() { token = _xdb_saved; }
				};}});
		token = _v_;
	}

	@Override
	public void setTokenOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setToken(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setUpdatetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "updatetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, updatetime) {
					public void rollback() { updatetime = _xdb_saved; }
				};}});
		updatetime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ApnsDevice _o_ = null;
		if ( _o1_ instanceof ApnsDevice ) _o_ = (ApnsDevice)_o1_;
		else if ( _o1_ instanceof ApnsDevice.Const ) _o_ = ((ApnsDevice.Const)_o1_).nThis();
		else return false;
		if (!token.equals(_o_.token)) return false;
		if (updatetime != _o_.updatetime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += token.hashCode();
		_h_ += updatetime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("'").append(token).append("'");
		_sb_.append(",");
		_sb_.append(updatetime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("token"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("updatetime"));
		return lb;
	}

	private class Const implements xbean.ApnsDevice {
		ApnsDevice nThis() {
			return ApnsDevice.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ApnsDevice copy() {
			return ApnsDevice.this.copy();
		}

		@Override
		public xbean.ApnsDevice toData() {
			return ApnsDevice.this.toData();
		}

		public xbean.ApnsDevice toBean() {
			return ApnsDevice.this.toBean();
		}

		@Override
		public xbean.ApnsDevice toDataIf() {
			return ApnsDevice.this.toDataIf();
		}

		public xbean.ApnsDevice toBeanIf() {
			return ApnsDevice.this.toBeanIf();
		}

		@Override
		public String getToken() { // 
			_xdb_verify_unsafe_();
			return token;
		}

		@Override
		public com.goldhuman.Common.Octets getTokenOctets() { // 
			_xdb_verify_unsafe_();
			return ApnsDevice.this.getTokenOctets();
		}

		@Override
		public long getUpdatetime() { // 
			_xdb_verify_unsafe_();
			return updatetime;
		}

		@Override
		public void setToken(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTokenOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUpdatetime(long _v_) { // 
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
			return ApnsDevice.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ApnsDevice.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ApnsDevice.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ApnsDevice.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ApnsDevice.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ApnsDevice.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ApnsDevice.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ApnsDevice.this.hashCode();
		}

		@Override
		public String toString() {
			return ApnsDevice.this.toString();
		}

	}

	public static final class Data implements xbean.ApnsDevice {
		private String token; // 
		private long updatetime; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			token = "";
		}

		Data(xbean.ApnsDevice _o1_) {
			if (_o1_ instanceof ApnsDevice) assign((ApnsDevice)_o1_);
			else if (_o1_ instanceof ApnsDevice.Data) assign((ApnsDevice.Data)_o1_);
			else if (_o1_ instanceof ApnsDevice.Const) assign(((ApnsDevice.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ApnsDevice _o_) {
			token = _o_.token;
			updatetime = _o_.updatetime;
		}

		private void assign(ApnsDevice.Data _o_) {
			token = _o_.token;
			updatetime = _o_.updatetime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(18432|  0));_os_.marshal(token, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(10240|  1));_os_.marshal(updatetime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (18432|  0):token = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (10240|  1):updatetime = _os_.unmarshal_long();
					break;
					case ( 6144|  1):updatetime = _os_.unmarshal_short();
					break;
					case ( 8192|  1):updatetime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ApnsDevice copy() {
			return new Data(this);
		}

		@Override
		public xbean.ApnsDevice toData() {
			return new Data(this);
		}

		public xbean.ApnsDevice toBean() {
			return new ApnsDevice(this, null, null);
		}

		@Override
		public xbean.ApnsDevice toDataIf() {
			return this;
		}

		public xbean.ApnsDevice toBeanIf() {
			return new ApnsDevice(this, null, null);
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
		public String getToken() { // 
			return token;
		}

		@Override
		public com.goldhuman.Common.Octets getTokenOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getToken(), xdb.Const.IO_CHARSET);
		}

		@Override
		public long getUpdatetime() { // 
			return updatetime;
		}

		@Override
		public void setToken(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			token = _v_;
		}

		@Override
		public void setTokenOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setToken(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setUpdatetime(long _v_) { // 
			updatetime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ApnsDevice.Data)) return false;
			ApnsDevice.Data _o_ = (ApnsDevice.Data) _o1_;
			if (!token.equals(_o_.token)) return false;
			if (updatetime != _o_.updatetime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += token.hashCode();
			_h_ += updatetime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append("'").append(token).append("'");
			_sb_.append(",");
			_sb_.append(updatetime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
