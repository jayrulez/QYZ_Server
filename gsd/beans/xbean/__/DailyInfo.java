
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class DailyInfo extends xdb.XBean implements xbean.DailyInfo {
	private int value; // 

	@Override
	public void _reset_unsafe_() {
		value = 0;
	}

	DailyInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public DailyInfo() {
		this(0, null, null);
	}

	public DailyInfo(DailyInfo _o_) {
		this(_o_, null, null);
	}

	DailyInfo(xbean.DailyInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof DailyInfo) assign((DailyInfo)_o1_);
		else if (_o1_ instanceof DailyInfo.Data) assign((DailyInfo.Data)_o1_);
		else if (_o1_ instanceof DailyInfo.Const) assign(((DailyInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(DailyInfo _o_) {
		_o_._xdb_verify_unsafe_();
		value = _o_.value;
	}

	private void assign(DailyInfo.Data _o_) {
		value = _o_.value;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)( 8192|  1));_os_.marshal(value);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):value = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):value = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.DailyInfo copy() {
		_xdb_verify_unsafe_();
		return new DailyInfo(this);
	}

	@Override
	public xbean.DailyInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyInfo toBean() {
		_xdb_verify_unsafe_();
		return new DailyInfo(this); // same as copy()
	}

	@Override
	public xbean.DailyInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getValue() { // 
		_xdb_verify_unsafe_();
		return value;
	}

	@Override
	public void setValue(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "value") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, value) {
					public void rollback() { value = _xdb_saved; }
				};}});
		value = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		DailyInfo _o_ = null;
		if ( _o1_ instanceof DailyInfo ) _o_ = (DailyInfo)_o1_;
		else if ( _o1_ instanceof DailyInfo.Const ) _o_ = ((DailyInfo.Const)_o1_).nThis();
		else return false;
		if (value != _o_.value) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += value;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(value);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("value"));
		return lb;
	}

	private class Const implements xbean.DailyInfo {
		DailyInfo nThis() {
			return DailyInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.DailyInfo copy() {
			return DailyInfo.this.copy();
		}

		@Override
		public xbean.DailyInfo toData() {
			return DailyInfo.this.toData();
		}

		public xbean.DailyInfo toBean() {
			return DailyInfo.this.toBean();
		}

		@Override
		public xbean.DailyInfo toDataIf() {
			return DailyInfo.this.toDataIf();
		}

		public xbean.DailyInfo toBeanIf() {
			return DailyInfo.this.toBeanIf();
		}

		@Override
		public int getValue() { // 
			_xdb_verify_unsafe_();
			return value;
		}

		@Override
		public void setValue(int _v_) { // 
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
			return DailyInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return DailyInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return DailyInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return DailyInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return DailyInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return DailyInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return DailyInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return DailyInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return DailyInfo.this.toString();
		}

	}

	public static final class Data implements xbean.DailyInfo {
		private int value; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.DailyInfo _o1_) {
			if (_o1_ instanceof DailyInfo) assign((DailyInfo)_o1_);
			else if (_o1_ instanceof DailyInfo.Data) assign((DailyInfo.Data)_o1_);
			else if (_o1_ instanceof DailyInfo.Const) assign(((DailyInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(DailyInfo _o_) {
			value = _o_.value;
		}

		private void assign(DailyInfo.Data _o_) {
			value = _o_.value;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)( 8192|  1));_os_.marshal(value);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):value = _os_.unmarshal_int();
					break;
					case ( 6144|  1):value = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.DailyInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.DailyInfo toData() {
			return new Data(this);
		}

		public xbean.DailyInfo toBean() {
			return new DailyInfo(this, null, null);
		}

		@Override
		public xbean.DailyInfo toDataIf() {
			return this;
		}

		public xbean.DailyInfo toBeanIf() {
			return new DailyInfo(this, null, null);
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
		public int getValue() { // 
			return value;
		}

		@Override
		public void setValue(int _v_) { // 
			value = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof DailyInfo.Data)) return false;
			DailyInfo.Data _o_ = (DailyInfo.Data) _o1_;
			if (value != _o_.value) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += value;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(value);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
