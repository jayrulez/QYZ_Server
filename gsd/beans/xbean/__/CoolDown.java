
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class CoolDown extends xdb.XBean implements xbean.CoolDown {
	private long id; // 
	private long expiretime; // 

	@Override
	public void _reset_unsafe_() {
		id = 0L;
		expiretime = 0L;
	}

	CoolDown(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public CoolDown() {
		this(0, null, null);
	}

	public CoolDown(CoolDown _o_) {
		this(_o_, null, null);
	}

	CoolDown(xbean.CoolDown _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof CoolDown) assign((CoolDown)_o1_);
		else if (_o1_ instanceof CoolDown.Data) assign((CoolDown.Data)_o1_);
		else if (_o1_ instanceof CoolDown.Const) assign(((CoolDown.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(CoolDown _o_) {
		_o_._xdb_verify_unsafe_();
		id = _o_.id;
		expiretime = _o_.expiretime;
	}

	private void assign(CoolDown.Data _o_) {
		id = _o_.id;
		expiretime = _o_.expiretime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(10240|  1));_os_.marshal(id);
    _os_.marshal((short)(10240|  2));_os_.marshal(expiretime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):id = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):id = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):id = _os_.unmarshal_int();
    				break;
    				case (10240|  2):expiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):expiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):expiretime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.CoolDown copy() {
		_xdb_verify_unsafe_();
		return new CoolDown(this);
	}

	@Override
	public xbean.CoolDown toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.CoolDown toBean() {
		_xdb_verify_unsafe_();
		return new CoolDown(this); // same as copy()
	}

	@Override
	public xbean.CoolDown toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.CoolDown toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getId() { // 
		_xdb_verify_unsafe_();
		return id;
	}

	@Override
	public long getExpiretime() { // 
		_xdb_verify_unsafe_();
		return expiretime;
	}

	@Override
	public void setId(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "id") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, id) {
					public void rollback() { id = _xdb_saved; }
				};}});
		id = _v_;
	}

	@Override
	public void setExpiretime(long _v_) { // 
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
		CoolDown _o_ = null;
		if ( _o1_ instanceof CoolDown ) _o_ = (CoolDown)_o1_;
		else if ( _o1_ instanceof CoolDown.Const ) _o_ = ((CoolDown.Const)_o1_).nThis();
		else return false;
		if (id != _o_.id) return false;
		if (expiretime != _o_.expiretime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += id;
		_h_ += expiretime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id);
		_sb_.append(",");
		_sb_.append(expiretime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("id"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("expiretime"));
		return lb;
	}

	private class Const implements xbean.CoolDown {
		CoolDown nThis() {
			return CoolDown.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.CoolDown copy() {
			return CoolDown.this.copy();
		}

		@Override
		public xbean.CoolDown toData() {
			return CoolDown.this.toData();
		}

		public xbean.CoolDown toBean() {
			return CoolDown.this.toBean();
		}

		@Override
		public xbean.CoolDown toDataIf() {
			return CoolDown.this.toDataIf();
		}

		public xbean.CoolDown toBeanIf() {
			return CoolDown.this.toBeanIf();
		}

		@Override
		public long getId() { // 
			_xdb_verify_unsafe_();
			return id;
		}

		@Override
		public long getExpiretime() { // 
			_xdb_verify_unsafe_();
			return expiretime;
		}

		@Override
		public void setId(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExpiretime(long _v_) { // 
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
			return CoolDown.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return CoolDown.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return CoolDown.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return CoolDown.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return CoolDown.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return CoolDown.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return CoolDown.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return CoolDown.this.hashCode();
		}

		@Override
		public String toString() {
			return CoolDown.this.toString();
		}

	}

	public static final class Data implements xbean.CoolDown {
		private long id; // 
		private long expiretime; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.CoolDown _o1_) {
			if (_o1_ instanceof CoolDown) assign((CoolDown)_o1_);
			else if (_o1_ instanceof CoolDown.Data) assign((CoolDown.Data)_o1_);
			else if (_o1_ instanceof CoolDown.Const) assign(((CoolDown.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(CoolDown _o_) {
			id = _o_.id;
			expiretime = _o_.expiretime;
		}

		private void assign(CoolDown.Data _o_) {
			id = _o_.id;
			expiretime = _o_.expiretime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(10240|  1));_os_.marshal(id);
	_os_.marshal((short)(10240|  2));_os_.marshal(expiretime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):id = _os_.unmarshal_long();
					break;
					case ( 6144|  1):id = _os_.unmarshal_short();
					break;
					case ( 8192|  1):id = _os_.unmarshal_int();
					break;
					case (10240|  2):expiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):expiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):expiretime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.CoolDown copy() {
			return new Data(this);
		}

		@Override
		public xbean.CoolDown toData() {
			return new Data(this);
		}

		public xbean.CoolDown toBean() {
			return new CoolDown(this, null, null);
		}

		@Override
		public xbean.CoolDown toDataIf() {
			return this;
		}

		public xbean.CoolDown toBeanIf() {
			return new CoolDown(this, null, null);
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
		public long getId() { // 
			return id;
		}

		@Override
		public long getExpiretime() { // 
			return expiretime;
		}

		@Override
		public void setId(long _v_) { // 
			id = _v_;
		}

		@Override
		public void setExpiretime(long _v_) { // 
			expiretime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof CoolDown.Data)) return false;
			CoolDown.Data _o_ = (CoolDown.Data) _o1_;
			if (id != _o_.id) return false;
			if (expiretime != _o_.expiretime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += id;
			_h_ += expiretime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(id);
			_sb_.append(",");
			_sb_.append(expiretime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
