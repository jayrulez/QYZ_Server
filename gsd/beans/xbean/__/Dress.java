
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Dress extends xdb.XBean implements xbean.Dress {
	private int modelid; // 
	private long expiretime; // 过期时间

	@Override
	public void _reset_unsafe_() {
		modelid = 0;
		expiretime = 0L;
	}

	Dress(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public Dress() {
		this(0, null, null);
	}

	public Dress(Dress _o_) {
		this(_o_, null, null);
	}

	Dress(xbean.Dress _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Dress) assign((Dress)_o1_);
		else if (_o1_ instanceof Dress.Data) assign((Dress.Data)_o1_);
		else if (_o1_ instanceof Dress.Const) assign(((Dress.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Dress _o_) {
		_o_._xdb_verify_unsafe_();
		modelid = _o_.modelid;
		expiretime = _o_.expiretime;
	}

	private void assign(Dress.Data _o_) {
		modelid = _o_.modelid;
		expiretime = _o_.expiretime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(modelid);
    _os_.marshal((short)(10240|  6));_os_.marshal(expiretime);
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
    				case (10240|  6):expiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  6):expiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):expiretime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Dress copy() {
		_xdb_verify_unsafe_();
		return new Dress(this);
	}

	@Override
	public xbean.Dress toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Dress toBean() {
		_xdb_verify_unsafe_();
		return new Dress(this); // same as copy()
	}

	@Override
	public xbean.Dress toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Dress toBeanIf() {
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
		Dress _o_ = null;
		if ( _o1_ instanceof Dress ) _o_ = (Dress)_o1_;
		else if ( _o1_ instanceof Dress.Const ) _o_ = ((Dress.Const)_o1_).nThis();
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

	private class Const implements xbean.Dress {
		Dress nThis() {
			return Dress.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Dress copy() {
			return Dress.this.copy();
		}

		@Override
		public xbean.Dress toData() {
			return Dress.this.toData();
		}

		public xbean.Dress toBean() {
			return Dress.this.toBean();
		}

		@Override
		public xbean.Dress toDataIf() {
			return Dress.this.toDataIf();
		}

		public xbean.Dress toBeanIf() {
			return Dress.this.toBeanIf();
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
			return Dress.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Dress.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Dress.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Dress.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Dress.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Dress.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Dress.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Dress.this.hashCode();
		}

		@Override
		public String toString() {
			return Dress.this.toString();
		}

	}

	public static final class Data implements xbean.Dress {
		private int modelid; // 
		private long expiretime; // 过期时间

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.Dress _o1_) {
			if (_o1_ instanceof Dress) assign((Dress)_o1_);
			else if (_o1_ instanceof Dress.Data) assign((Dress.Data)_o1_);
			else if (_o1_ instanceof Dress.Const) assign(((Dress.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Dress _o_) {
			modelid = _o_.modelid;
			expiretime = _o_.expiretime;
		}

		private void assign(Dress.Data _o_) {
			modelid = _o_.modelid;
			expiretime = _o_.expiretime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(modelid);
	_os_.marshal((short)(10240|  6));_os_.marshal(expiretime);
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
					case (10240|  6):expiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  6):expiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  6):expiretime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Dress copy() {
			return new Data(this);
		}

		@Override
		public xbean.Dress toData() {
			return new Data(this);
		}

		public xbean.Dress toBean() {
			return new Dress(this, null, null);
		}

		@Override
		public xbean.Dress toDataIf() {
			return this;
		}

		public xbean.Dress toBeanIf() {
			return new Dress(this, null, null);
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
			if (!(_o1_ instanceof Dress.Data)) return false;
			Dress.Data _o_ = (Dress.Data) _o1_;
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
