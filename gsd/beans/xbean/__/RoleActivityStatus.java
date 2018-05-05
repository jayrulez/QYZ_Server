
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleActivityStatus extends xdb.XBean implements xbean.RoleActivityStatus {
	private long timestamp; // 
	private int status; // 

	@Override
	public void _reset_unsafe_() {
		timestamp = 0L;
		status = 0;
	}

	RoleActivityStatus(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public RoleActivityStatus() {
		this(0, null, null);
	}

	public RoleActivityStatus(RoleActivityStatus _o_) {
		this(_o_, null, null);
	}

	RoleActivityStatus(xbean.RoleActivityStatus _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleActivityStatus) assign((RoleActivityStatus)_o1_);
		else if (_o1_ instanceof RoleActivityStatus.Data) assign((RoleActivityStatus.Data)_o1_);
		else if (_o1_ instanceof RoleActivityStatus.Const) assign(((RoleActivityStatus.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleActivityStatus _o_) {
		_o_._xdb_verify_unsafe_();
		timestamp = _o_.timestamp;
		status = _o_.status;
	}

	private void assign(RoleActivityStatus.Data _o_) {
		timestamp = _o_.timestamp;
		status = _o_.status;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(10240|  1));_os_.marshal(timestamp);
    _os_.marshal((short)( 8192|  2));_os_.marshal(status);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):timestamp = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):timestamp = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):timestamp = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):status = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):status = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleActivityStatus copy() {
		_xdb_verify_unsafe_();
		return new RoleActivityStatus(this);
	}

	@Override
	public xbean.RoleActivityStatus toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleActivityStatus toBean() {
		_xdb_verify_unsafe_();
		return new RoleActivityStatus(this); // same as copy()
	}

	@Override
	public xbean.RoleActivityStatus toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleActivityStatus toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getTimestamp() { // 
		_xdb_verify_unsafe_();
		return timestamp;
	}

	@Override
	public int getStatus() { // 
		_xdb_verify_unsafe_();
		return status;
	}

	@Override
	public void setTimestamp(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "timestamp") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, timestamp) {
					public void rollback() { timestamp = _xdb_saved; }
				};}});
		timestamp = _v_;
	}

	@Override
	public void setStatus(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "status") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, status) {
					public void rollback() { status = _xdb_saved; }
				};}});
		status = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleActivityStatus _o_ = null;
		if ( _o1_ instanceof RoleActivityStatus ) _o_ = (RoleActivityStatus)_o1_;
		else if ( _o1_ instanceof RoleActivityStatus.Const ) _o_ = ((RoleActivityStatus.Const)_o1_).nThis();
		else return false;
		if (timestamp != _o_.timestamp) return false;
		if (status != _o_.status) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += timestamp;
		_h_ += status;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(timestamp);
		_sb_.append(",");
		_sb_.append(status);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("timestamp"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("status"));
		return lb;
	}

	private class Const implements xbean.RoleActivityStatus {
		RoleActivityStatus nThis() {
			return RoleActivityStatus.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleActivityStatus copy() {
			return RoleActivityStatus.this.copy();
		}

		@Override
		public xbean.RoleActivityStatus toData() {
			return RoleActivityStatus.this.toData();
		}

		public xbean.RoleActivityStatus toBean() {
			return RoleActivityStatus.this.toBean();
		}

		@Override
		public xbean.RoleActivityStatus toDataIf() {
			return RoleActivityStatus.this.toDataIf();
		}

		public xbean.RoleActivityStatus toBeanIf() {
			return RoleActivityStatus.this.toBeanIf();
		}

		@Override
		public long getTimestamp() { // 
			_xdb_verify_unsafe_();
			return timestamp;
		}

		@Override
		public int getStatus() { // 
			_xdb_verify_unsafe_();
			return status;
		}

		@Override
		public void setTimestamp(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setStatus(int _v_) { // 
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
			return RoleActivityStatus.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleActivityStatus.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleActivityStatus.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleActivityStatus.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleActivityStatus.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleActivityStatus.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleActivityStatus.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleActivityStatus.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleActivityStatus.this.toString();
		}

	}

	public static final class Data implements xbean.RoleActivityStatus {
		private long timestamp; // 
		private int status; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.RoleActivityStatus _o1_) {
			if (_o1_ instanceof RoleActivityStatus) assign((RoleActivityStatus)_o1_);
			else if (_o1_ instanceof RoleActivityStatus.Data) assign((RoleActivityStatus.Data)_o1_);
			else if (_o1_ instanceof RoleActivityStatus.Const) assign(((RoleActivityStatus.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleActivityStatus _o_) {
			timestamp = _o_.timestamp;
			status = _o_.status;
		}

		private void assign(RoleActivityStatus.Data _o_) {
			timestamp = _o_.timestamp;
			status = _o_.status;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(10240|  1));_os_.marshal(timestamp);
	_os_.marshal((short)( 8192|  2));_os_.marshal(status);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):timestamp = _os_.unmarshal_long();
					break;
					case ( 6144|  1):timestamp = _os_.unmarshal_short();
					break;
					case ( 8192|  1):timestamp = _os_.unmarshal_int();
					break;
					case ( 8192|  2):status = _os_.unmarshal_int();
					break;
					case ( 6144|  2):status = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleActivityStatus copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleActivityStatus toData() {
			return new Data(this);
		}

		public xbean.RoleActivityStatus toBean() {
			return new RoleActivityStatus(this, null, null);
		}

		@Override
		public xbean.RoleActivityStatus toDataIf() {
			return this;
		}

		public xbean.RoleActivityStatus toBeanIf() {
			return new RoleActivityStatus(this, null, null);
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
		public long getTimestamp() { // 
			return timestamp;
		}

		@Override
		public int getStatus() { // 
			return status;
		}

		@Override
		public void setTimestamp(long _v_) { // 
			timestamp = _v_;
		}

		@Override
		public void setStatus(int _v_) { // 
			status = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleActivityStatus.Data)) return false;
			RoleActivityStatus.Data _o_ = (RoleActivityStatus.Data) _o1_;
			if (timestamp != _o_.timestamp) return false;
			if (status != _o_.status) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += timestamp;
			_h_ += status;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(timestamp);
			_sb_.append(",");
			_sb_.append(status);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
