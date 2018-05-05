
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RankRecord extends xdb.XBean implements xbean.RankRecord {
	private long value; // 
	private long id; // 

	@Override
	public void _reset_unsafe_() {
		value = 0L;
		id = 0L;
	}

	RankRecord(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public RankRecord() {
		this(0, null, null);
	}

	public RankRecord(RankRecord _o_) {
		this(_o_, null, null);
	}

	RankRecord(xbean.RankRecord _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RankRecord) assign((RankRecord)_o1_);
		else if (_o1_ instanceof RankRecord.Data) assign((RankRecord.Data)_o1_);
		else if (_o1_ instanceof RankRecord.Const) assign(((RankRecord.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RankRecord _o_) {
		_o_._xdb_verify_unsafe_();
		value = _o_.value;
		id = _o_.id;
	}

	private void assign(RankRecord.Data _o_) {
		value = _o_.value;
		id = _o_.id;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(10240|  1));_os_.marshal(value);
    _os_.marshal((short)(10240|  2));_os_.marshal(id);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):value = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):value = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):value = _os_.unmarshal_int();
    				break;
    				case (10240|  2):id = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):id = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):id = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RankRecord copy() {
		_xdb_verify_unsafe_();
		return new RankRecord(this);
	}

	@Override
	public xbean.RankRecord toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RankRecord toBean() {
		_xdb_verify_unsafe_();
		return new RankRecord(this); // same as copy()
	}

	@Override
	public xbean.RankRecord toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RankRecord toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getValue() { // 
		_xdb_verify_unsafe_();
		return value;
	}

	@Override
	public long getId() { // 
		_xdb_verify_unsafe_();
		return id;
	}

	@Override
	public void setValue(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "value") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, value) {
					public void rollback() { value = _xdb_saved; }
				};}});
		value = _v_;
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
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RankRecord _o_ = null;
		if ( _o1_ instanceof RankRecord ) _o_ = (RankRecord)_o1_;
		else if ( _o1_ instanceof RankRecord.Const ) _o_ = ((RankRecord.Const)_o1_).nThis();
		else return false;
		if (value != _o_.value) return false;
		if (id != _o_.id) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += value;
		_h_ += id;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(value);
		_sb_.append(",");
		_sb_.append(id);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("value"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("id"));
		return lb;
	}

	private class Const implements xbean.RankRecord {
		RankRecord nThis() {
			return RankRecord.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RankRecord copy() {
			return RankRecord.this.copy();
		}

		@Override
		public xbean.RankRecord toData() {
			return RankRecord.this.toData();
		}

		public xbean.RankRecord toBean() {
			return RankRecord.this.toBean();
		}

		@Override
		public xbean.RankRecord toDataIf() {
			return RankRecord.this.toDataIf();
		}

		public xbean.RankRecord toBeanIf() {
			return RankRecord.this.toBeanIf();
		}

		@Override
		public long getValue() { // 
			_xdb_verify_unsafe_();
			return value;
		}

		@Override
		public long getId() { // 
			_xdb_verify_unsafe_();
			return id;
		}

		@Override
		public void setValue(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setId(long _v_) { // 
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
			return RankRecord.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RankRecord.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RankRecord.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RankRecord.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RankRecord.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RankRecord.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RankRecord.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RankRecord.this.hashCode();
		}

		@Override
		public String toString() {
			return RankRecord.this.toString();
		}

	}

	public static final class Data implements xbean.RankRecord {
		private long value; // 
		private long id; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.RankRecord _o1_) {
			if (_o1_ instanceof RankRecord) assign((RankRecord)_o1_);
			else if (_o1_ instanceof RankRecord.Data) assign((RankRecord.Data)_o1_);
			else if (_o1_ instanceof RankRecord.Const) assign(((RankRecord.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RankRecord _o_) {
			value = _o_.value;
			id = _o_.id;
		}

		private void assign(RankRecord.Data _o_) {
			value = _o_.value;
			id = _o_.id;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(10240|  1));_os_.marshal(value);
	_os_.marshal((short)(10240|  2));_os_.marshal(id);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):value = _os_.unmarshal_long();
					break;
					case ( 6144|  1):value = _os_.unmarshal_short();
					break;
					case ( 8192|  1):value = _os_.unmarshal_int();
					break;
					case (10240|  2):id = _os_.unmarshal_long();
					break;
					case ( 6144|  2):id = _os_.unmarshal_short();
					break;
					case ( 8192|  2):id = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RankRecord copy() {
			return new Data(this);
		}

		@Override
		public xbean.RankRecord toData() {
			return new Data(this);
		}

		public xbean.RankRecord toBean() {
			return new RankRecord(this, null, null);
		}

		@Override
		public xbean.RankRecord toDataIf() {
			return this;
		}

		public xbean.RankRecord toBeanIf() {
			return new RankRecord(this, null, null);
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
		public long getValue() { // 
			return value;
		}

		@Override
		public long getId() { // 
			return id;
		}

		@Override
		public void setValue(long _v_) { // 
			value = _v_;
		}

		@Override
		public void setId(long _v_) { // 
			id = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RankRecord.Data)) return false;
			RankRecord.Data _o_ = (RankRecord.Data) _o1_;
			if (value != _o_.value) return false;
			if (id != _o_.id) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += value;
			_h_ += id;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(value);
			_sb_.append(",");
			_sb_.append(id);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
