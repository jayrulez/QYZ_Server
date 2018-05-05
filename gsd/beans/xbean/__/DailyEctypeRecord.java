
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class DailyEctypeRecord extends xdb.XBean implements xbean.DailyEctypeRecord {
	private long roleid; // 
	private int value; // 

	@Override
	public void _reset_unsafe_() {
		roleid = 0L;
		value = 0;
	}

	DailyEctypeRecord(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public DailyEctypeRecord() {
		this(0, null, null);
	}

	public DailyEctypeRecord(DailyEctypeRecord _o_) {
		this(_o_, null, null);
	}

	DailyEctypeRecord(xbean.DailyEctypeRecord _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof DailyEctypeRecord) assign((DailyEctypeRecord)_o1_);
		else if (_o1_ instanceof DailyEctypeRecord.Data) assign((DailyEctypeRecord.Data)_o1_);
		else if (_o1_ instanceof DailyEctypeRecord.Const) assign(((DailyEctypeRecord.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(DailyEctypeRecord _o_) {
		_o_._xdb_verify_unsafe_();
		roleid = _o_.roleid;
		value = _o_.value;
	}

	private void assign(DailyEctypeRecord.Data _o_) {
		roleid = _o_.roleid;
		value = _o_.value;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(10240|  1));_os_.marshal(roleid);
    _os_.marshal((short)( 8192|  2));_os_.marshal(value);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):roleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):roleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):roleid = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):value = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):value = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.DailyEctypeRecord copy() {
		_xdb_verify_unsafe_();
		return new DailyEctypeRecord(this);
	}

	@Override
	public xbean.DailyEctypeRecord toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyEctypeRecord toBean() {
		_xdb_verify_unsafe_();
		return new DailyEctypeRecord(this); // same as copy()
	}

	@Override
	public xbean.DailyEctypeRecord toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyEctypeRecord toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getRoleid() { // 
		_xdb_verify_unsafe_();
		return roleid;
	}

	@Override
	public int getValue() { // 
		_xdb_verify_unsafe_();
		return value;
	}

	@Override
	public void setRoleid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleid) {
					public void rollback() { roleid = _xdb_saved; }
				};}});
		roleid = _v_;
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
		DailyEctypeRecord _o_ = null;
		if ( _o1_ instanceof DailyEctypeRecord ) _o_ = (DailyEctypeRecord)_o1_;
		else if ( _o1_ instanceof DailyEctypeRecord.Const ) _o_ = ((DailyEctypeRecord.Const)_o1_).nThis();
		else return false;
		if (roleid != _o_.roleid) return false;
		if (value != _o_.value) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += roleid;
		_h_ += value;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid);
		_sb_.append(",");
		_sb_.append(value);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("value"));
		return lb;
	}

	private class Const implements xbean.DailyEctypeRecord {
		DailyEctypeRecord nThis() {
			return DailyEctypeRecord.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.DailyEctypeRecord copy() {
			return DailyEctypeRecord.this.copy();
		}

		@Override
		public xbean.DailyEctypeRecord toData() {
			return DailyEctypeRecord.this.toData();
		}

		public xbean.DailyEctypeRecord toBean() {
			return DailyEctypeRecord.this.toBean();
		}

		@Override
		public xbean.DailyEctypeRecord toDataIf() {
			return DailyEctypeRecord.this.toDataIf();
		}

		public xbean.DailyEctypeRecord toBeanIf() {
			return DailyEctypeRecord.this.toBeanIf();
		}

		@Override
		public long getRoleid() { // 
			_xdb_verify_unsafe_();
			return roleid;
		}

		@Override
		public int getValue() { // 
			_xdb_verify_unsafe_();
			return value;
		}

		@Override
		public void setRoleid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
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
			return DailyEctypeRecord.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return DailyEctypeRecord.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return DailyEctypeRecord.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return DailyEctypeRecord.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return DailyEctypeRecord.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return DailyEctypeRecord.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return DailyEctypeRecord.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return DailyEctypeRecord.this.hashCode();
		}

		@Override
		public String toString() {
			return DailyEctypeRecord.this.toString();
		}

	}

	public static final class Data implements xbean.DailyEctypeRecord {
		private long roleid; // 
		private int value; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.DailyEctypeRecord _o1_) {
			if (_o1_ instanceof DailyEctypeRecord) assign((DailyEctypeRecord)_o1_);
			else if (_o1_ instanceof DailyEctypeRecord.Data) assign((DailyEctypeRecord.Data)_o1_);
			else if (_o1_ instanceof DailyEctypeRecord.Const) assign(((DailyEctypeRecord.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(DailyEctypeRecord _o_) {
			roleid = _o_.roleid;
			value = _o_.value;
		}

		private void assign(DailyEctypeRecord.Data _o_) {
			roleid = _o_.roleid;
			value = _o_.value;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(10240|  1));_os_.marshal(roleid);
	_os_.marshal((short)( 8192|  2));_os_.marshal(value);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):roleid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):roleid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):roleid = _os_.unmarshal_int();
					break;
					case ( 8192|  2):value = _os_.unmarshal_int();
					break;
					case ( 6144|  2):value = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.DailyEctypeRecord copy() {
			return new Data(this);
		}

		@Override
		public xbean.DailyEctypeRecord toData() {
			return new Data(this);
		}

		public xbean.DailyEctypeRecord toBean() {
			return new DailyEctypeRecord(this, null, null);
		}

		@Override
		public xbean.DailyEctypeRecord toDataIf() {
			return this;
		}

		public xbean.DailyEctypeRecord toBeanIf() {
			return new DailyEctypeRecord(this, null, null);
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
		public long getRoleid() { // 
			return roleid;
		}

		@Override
		public int getValue() { // 
			return value;
		}

		@Override
		public void setRoleid(long _v_) { // 
			roleid = _v_;
		}

		@Override
		public void setValue(int _v_) { // 
			value = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof DailyEctypeRecord.Data)) return false;
			DailyEctypeRecord.Data _o_ = (DailyEctypeRecord.Data) _o1_;
			if (roleid != _o_.roleid) return false;
			if (value != _o_.value) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += roleid;
			_h_ += value;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(roleid);
			_sb_.append(",");
			_sb_.append(value);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
