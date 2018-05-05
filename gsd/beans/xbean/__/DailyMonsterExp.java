
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class DailyMonsterExp extends xdb.XBean implements xbean.DailyMonsterExp {
	private long todaytotaladdmonsterexp; // 

	@Override
	public void _reset_unsafe_() {
		todaytotaladdmonsterexp = 0L;
	}

	DailyMonsterExp(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public DailyMonsterExp() {
		this(0, null, null);
	}

	public DailyMonsterExp(DailyMonsterExp _o_) {
		this(_o_, null, null);
	}

	DailyMonsterExp(xbean.DailyMonsterExp _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof DailyMonsterExp) assign((DailyMonsterExp)_o1_);
		else if (_o1_ instanceof DailyMonsterExp.Data) assign((DailyMonsterExp.Data)_o1_);
		else if (_o1_ instanceof DailyMonsterExp.Const) assign(((DailyMonsterExp.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(DailyMonsterExp _o_) {
		_o_._xdb_verify_unsafe_();
		todaytotaladdmonsterexp = _o_.todaytotaladdmonsterexp;
	}

	private void assign(DailyMonsterExp.Data _o_) {
		todaytotaladdmonsterexp = _o_.todaytotaladdmonsterexp;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(10240|  2));_os_.marshal(todaytotaladdmonsterexp);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  2):todaytotaladdmonsterexp = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):todaytotaladdmonsterexp = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):todaytotaladdmonsterexp = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.DailyMonsterExp copy() {
		_xdb_verify_unsafe_();
		return new DailyMonsterExp(this);
	}

	@Override
	public xbean.DailyMonsterExp toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyMonsterExp toBean() {
		_xdb_verify_unsafe_();
		return new DailyMonsterExp(this); // same as copy()
	}

	@Override
	public xbean.DailyMonsterExp toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyMonsterExp toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getTodaytotaladdmonsterexp() { // 
		_xdb_verify_unsafe_();
		return todaytotaladdmonsterexp;
	}

	@Override
	public void setTodaytotaladdmonsterexp(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "todaytotaladdmonsterexp") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, todaytotaladdmonsterexp) {
					public void rollback() { todaytotaladdmonsterexp = _xdb_saved; }
				};}});
		todaytotaladdmonsterexp = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		DailyMonsterExp _o_ = null;
		if ( _o1_ instanceof DailyMonsterExp ) _o_ = (DailyMonsterExp)_o1_;
		else if ( _o1_ instanceof DailyMonsterExp.Const ) _o_ = ((DailyMonsterExp.Const)_o1_).nThis();
		else return false;
		if (todaytotaladdmonsterexp != _o_.todaytotaladdmonsterexp) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += todaytotaladdmonsterexp;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(todaytotaladdmonsterexp);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("todaytotaladdmonsterexp"));
		return lb;
	}

	private class Const implements xbean.DailyMonsterExp {
		DailyMonsterExp nThis() {
			return DailyMonsterExp.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.DailyMonsterExp copy() {
			return DailyMonsterExp.this.copy();
		}

		@Override
		public xbean.DailyMonsterExp toData() {
			return DailyMonsterExp.this.toData();
		}

		public xbean.DailyMonsterExp toBean() {
			return DailyMonsterExp.this.toBean();
		}

		@Override
		public xbean.DailyMonsterExp toDataIf() {
			return DailyMonsterExp.this.toDataIf();
		}

		public xbean.DailyMonsterExp toBeanIf() {
			return DailyMonsterExp.this.toBeanIf();
		}

		@Override
		public long getTodaytotaladdmonsterexp() { // 
			_xdb_verify_unsafe_();
			return todaytotaladdmonsterexp;
		}

		@Override
		public void setTodaytotaladdmonsterexp(long _v_) { // 
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
			return DailyMonsterExp.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return DailyMonsterExp.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return DailyMonsterExp.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return DailyMonsterExp.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return DailyMonsterExp.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return DailyMonsterExp.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return DailyMonsterExp.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return DailyMonsterExp.this.hashCode();
		}

		@Override
		public String toString() {
			return DailyMonsterExp.this.toString();
		}

	}

	public static final class Data implements xbean.DailyMonsterExp {
		private long todaytotaladdmonsterexp; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.DailyMonsterExp _o1_) {
			if (_o1_ instanceof DailyMonsterExp) assign((DailyMonsterExp)_o1_);
			else if (_o1_ instanceof DailyMonsterExp.Data) assign((DailyMonsterExp.Data)_o1_);
			else if (_o1_ instanceof DailyMonsterExp.Const) assign(((DailyMonsterExp.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(DailyMonsterExp _o_) {
			todaytotaladdmonsterexp = _o_.todaytotaladdmonsterexp;
		}

		private void assign(DailyMonsterExp.Data _o_) {
			todaytotaladdmonsterexp = _o_.todaytotaladdmonsterexp;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(10240|  2));_os_.marshal(todaytotaladdmonsterexp);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  2):todaytotaladdmonsterexp = _os_.unmarshal_long();
					break;
					case ( 6144|  2):todaytotaladdmonsterexp = _os_.unmarshal_short();
					break;
					case ( 8192|  2):todaytotaladdmonsterexp = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.DailyMonsterExp copy() {
			return new Data(this);
		}

		@Override
		public xbean.DailyMonsterExp toData() {
			return new Data(this);
		}

		public xbean.DailyMonsterExp toBean() {
			return new DailyMonsterExp(this, null, null);
		}

		@Override
		public xbean.DailyMonsterExp toDataIf() {
			return this;
		}

		public xbean.DailyMonsterExp toBeanIf() {
			return new DailyMonsterExp(this, null, null);
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
		public long getTodaytotaladdmonsterexp() { // 
			return todaytotaladdmonsterexp;
		}

		@Override
		public void setTodaytotaladdmonsterexp(long _v_) { // 
			todaytotaladdmonsterexp = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof DailyMonsterExp.Data)) return false;
			DailyMonsterExp.Data _o_ = (DailyMonsterExp.Data) _o1_;
			if (todaytotaladdmonsterexp != _o_.todaytotaladdmonsterexp) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += todaytotaladdmonsterexp;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(todaytotaladdmonsterexp);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
