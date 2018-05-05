
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Ride extends xdb.XBean implements xbean.Ride {
	private int modelid; // 
	private long expiretime; // 过期时间

	@Override
	public void _reset_unsafe_() {
		modelid = 0;
		expiretime = 0L;
	}

	Ride(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public Ride() {
		this(0, null, null);
	}

	public Ride(Ride _o_) {
		this(_o_, null, null);
	}

	Ride(xbean.Ride _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Ride) assign((Ride)_o1_);
		else if (_o1_ instanceof Ride.Data) assign((Ride.Data)_o1_);
		else if (_o1_ instanceof Ride.Const) assign(((Ride.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Ride _o_) {
		_o_._xdb_verify_unsafe_();
		modelid = _o_.modelid;
		expiretime = _o_.expiretime;
	}

	private void assign(Ride.Data _o_) {
		modelid = _o_.modelid;
		expiretime = _o_.expiretime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(modelid);
    _os_.marshal((short)(10240|  5));_os_.marshal(expiretime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):modelid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):modelid = _os_.unmarshal_short();
    				break;
    				case (10240|  5):expiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):expiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):expiretime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Ride copy() {
		_xdb_verify_unsafe_();
		return new Ride(this);
	}

	@Override
	public xbean.Ride toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Ride toBean() {
		_xdb_verify_unsafe_();
		return new Ride(this); // same as copy()
	}

	@Override
	public xbean.Ride toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Ride toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getModelid() { // 
		_xdb_verify_unsafe_();
		return modelid;
	}

	@Override
	public long getExpiretime() { // 过期时间
		_xdb_verify_unsafe_();
		return expiretime;
	}

	@Override
	public void setModelid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "modelid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, modelid) {
					public void rollback() { modelid = _xdb_saved; }
				};}});
		modelid = _v_;
	}

	@Override
	public void setExpiretime(long _v_) { // 过期时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "expiretime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, expiretime) {
					public void rollback() { expiretime = _xdb_saved; }
				};}});
		expiretime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Ride _o_ = null;
		if ( _o1_ instanceof Ride ) _o_ = (Ride)_o1_;
		else if ( _o1_ instanceof Ride.Const ) _o_ = ((Ride.Const)_o1_).nThis();
		else return false;
		if (modelid != _o_.modelid) return false;
		if (expiretime != _o_.expiretime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += modelid;
		_h_ += expiretime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid);
		_sb_.append(",");
		_sb_.append(expiretime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("modelid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("expiretime"));
		return lb;
	}

	private class Const implements xbean.Ride {
		Ride nThis() {
			return Ride.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Ride copy() {
			return Ride.this.copy();
		}

		@Override
		public xbean.Ride toData() {
			return Ride.this.toData();
		}

		public xbean.Ride toBean() {
			return Ride.this.toBean();
		}

		@Override
		public xbean.Ride toDataIf() {
			return Ride.this.toDataIf();
		}

		public xbean.Ride toBeanIf() {
			return Ride.this.toBeanIf();
		}

		@Override
		public int getModelid() { // 
			_xdb_verify_unsafe_();
			return modelid;
		}

		@Override
		public long getExpiretime() { // 过期时间
			_xdb_verify_unsafe_();
			return expiretime;
		}

		@Override
		public void setModelid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExpiretime(long _v_) { // 过期时间
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
			return Ride.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Ride.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Ride.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Ride.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Ride.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Ride.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Ride.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Ride.this.hashCode();
		}

		@Override
		public String toString() {
			return Ride.this.toString();
		}

	}

	public static final class Data implements xbean.Ride {
		private int modelid; // 
		private long expiretime; // 过期时间

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.Ride _o1_) {
			if (_o1_ instanceof Ride) assign((Ride)_o1_);
			else if (_o1_ instanceof Ride.Data) assign((Ride.Data)_o1_);
			else if (_o1_ instanceof Ride.Const) assign(((Ride.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Ride _o_) {
			modelid = _o_.modelid;
			expiretime = _o_.expiretime;
		}

		private void assign(Ride.Data _o_) {
			modelid = _o_.modelid;
			expiretime = _o_.expiretime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(modelid);
	_os_.marshal((short)(10240|  5));_os_.marshal(expiretime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):modelid = _os_.unmarshal_int();
					break;
					case ( 6144|  1):modelid = _os_.unmarshal_short();
					break;
					case (10240|  5):expiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  5):expiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  5):expiretime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Ride copy() {
			return new Data(this);
		}

		@Override
		public xbean.Ride toData() {
			return new Data(this);
		}

		public xbean.Ride toBean() {
			return new Ride(this, null, null);
		}

		@Override
		public xbean.Ride toDataIf() {
			return this;
		}

		public xbean.Ride toBeanIf() {
			return new Ride(this, null, null);
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
		public int getModelid() { // 
			return modelid;
		}

		@Override
		public long getExpiretime() { // 过期时间
			return expiretime;
		}

		@Override
		public void setModelid(int _v_) { // 
			modelid = _v_;
		}

		@Override
		public void setExpiretime(long _v_) { // 过期时间
			expiretime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Ride.Data)) return false;
			Ride.Data _o_ = (Ride.Data) _o1_;
			if (modelid != _o_.modelid) return false;
			if (expiretime != _o_.expiretime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += modelid;
			_h_ += expiretime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(modelid);
			_sb_.append(",");
			_sb_.append(expiretime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
