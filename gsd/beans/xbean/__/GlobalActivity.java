
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class GlobalActivity extends xdb.XBean implements xbean.GlobalActivity {
	private long timestamp; // 

	@Override
	public void _reset_unsafe_() {
		timestamp = 0L;
	}

	GlobalActivity(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public GlobalActivity() {
		this(0, null, null);
	}

	public GlobalActivity(GlobalActivity _o_) {
		this(_o_, null, null);
	}

	GlobalActivity(xbean.GlobalActivity _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof GlobalActivity) assign((GlobalActivity)_o1_);
		else if (_o1_ instanceof GlobalActivity.Data) assign((GlobalActivity.Data)_o1_);
		else if (_o1_ instanceof GlobalActivity.Const) assign(((GlobalActivity.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(GlobalActivity _o_) {
		_o_._xdb_verify_unsafe_();
		timestamp = _o_.timestamp;
	}

	private void assign(GlobalActivity.Data _o_) {
		timestamp = _o_.timestamp;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(10240|  1));_os_.marshal(timestamp);
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
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.GlobalActivity copy() {
		_xdb_verify_unsafe_();
		return new GlobalActivity(this);
	}

	@Override
	public xbean.GlobalActivity toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GlobalActivity toBean() {
		_xdb_verify_unsafe_();
		return new GlobalActivity(this); // same as copy()
	}

	@Override
	public xbean.GlobalActivity toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GlobalActivity toBeanIf() {
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
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		GlobalActivity _o_ = null;
		if ( _o1_ instanceof GlobalActivity ) _o_ = (GlobalActivity)_o1_;
		else if ( _o1_ instanceof GlobalActivity.Const ) _o_ = ((GlobalActivity.Const)_o1_).nThis();
		else return false;
		if (timestamp != _o_.timestamp) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += timestamp;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(timestamp);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("timestamp"));
		return lb;
	}

	private class Const implements xbean.GlobalActivity {
		GlobalActivity nThis() {
			return GlobalActivity.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.GlobalActivity copy() {
			return GlobalActivity.this.copy();
		}

		@Override
		public xbean.GlobalActivity toData() {
			return GlobalActivity.this.toData();
		}

		public xbean.GlobalActivity toBean() {
			return GlobalActivity.this.toBean();
		}

		@Override
		public xbean.GlobalActivity toDataIf() {
			return GlobalActivity.this.toDataIf();
		}

		public xbean.GlobalActivity toBeanIf() {
			return GlobalActivity.this.toBeanIf();
		}

		@Override
		public long getTimestamp() { // 
			_xdb_verify_unsafe_();
			return timestamp;
		}

		@Override
		public void setTimestamp(long _v_) { // 
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
			return GlobalActivity.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return GlobalActivity.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return GlobalActivity.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return GlobalActivity.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return GlobalActivity.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return GlobalActivity.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return GlobalActivity.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return GlobalActivity.this.hashCode();
		}

		@Override
		public String toString() {
			return GlobalActivity.this.toString();
		}

	}

	public static final class Data implements xbean.GlobalActivity {
		private long timestamp; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.GlobalActivity _o1_) {
			if (_o1_ instanceof GlobalActivity) assign((GlobalActivity)_o1_);
			else if (_o1_ instanceof GlobalActivity.Data) assign((GlobalActivity.Data)_o1_);
			else if (_o1_ instanceof GlobalActivity.Const) assign(((GlobalActivity.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(GlobalActivity _o_) {
			timestamp = _o_.timestamp;
		}

		private void assign(GlobalActivity.Data _o_) {
			timestamp = _o_.timestamp;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(10240|  1));_os_.marshal(timestamp);
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
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.GlobalActivity copy() {
			return new Data(this);
		}

		@Override
		public xbean.GlobalActivity toData() {
			return new Data(this);
		}

		public xbean.GlobalActivity toBean() {
			return new GlobalActivity(this, null, null);
		}

		@Override
		public xbean.GlobalActivity toDataIf() {
			return this;
		}

		public xbean.GlobalActivity toBeanIf() {
			return new GlobalActivity(this, null, null);
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
		public void setTimestamp(long _v_) { // 
			timestamp = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof GlobalActivity.Data)) return false;
			GlobalActivity.Data _o_ = (GlobalActivity.Data) _o1_;
			if (timestamp != _o_.timestamp) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += timestamp;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(timestamp);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
