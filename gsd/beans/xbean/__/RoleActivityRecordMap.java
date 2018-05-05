
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleActivityRecordMap extends xdb.XBean implements xbean.RoleActivityRecordMap {
	private java.util.HashMap<Long, xbean.RoleActivityRecord> records; // 活动数据

	@Override
	public void _reset_unsafe_() {
		records.clear();
	}

	RoleActivityRecordMap(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		records = new java.util.HashMap<Long, xbean.RoleActivityRecord>();
	}

	public RoleActivityRecordMap() {
		this(0, null, null);
	}

	public RoleActivityRecordMap(RoleActivityRecordMap _o_) {
		this(_o_, null, null);
	}

	RoleActivityRecordMap(xbean.RoleActivityRecordMap _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleActivityRecordMap) assign((RoleActivityRecordMap)_o1_);
		else if (_o1_ instanceof RoleActivityRecordMap.Data) assign((RoleActivityRecordMap.Data)_o1_);
		else if (_o1_ instanceof RoleActivityRecordMap.Const) assign(((RoleActivityRecordMap.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleActivityRecordMap _o_) {
		_o_._xdb_verify_unsafe_();
		records = new java.util.HashMap<Long, xbean.RoleActivityRecord>();
		for (java.util.Map.Entry<Long, xbean.RoleActivityRecord> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new RoleActivityRecord(_e_.getValue(), this, "records"));
	}

	private void assign(RoleActivityRecordMap.Data _o_) {
		records = new java.util.HashMap<Long, xbean.RoleActivityRecord>();
		for (java.util.Map.Entry<Long, xbean.RoleActivityRecord> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new RoleActivityRecord(_e_.getValue(), this, "records"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(records.size());
for (java.util.Map.Entry<Long, xbean.RoleActivityRecord> _e_ : records.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		records = new java.util.HashMap<Long, xbean.RoleActivityRecord>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.RoleActivityRecord _v_ = new RoleActivityRecord(0, this, "records");
		_v_.unmarshal(_os_);
		records.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleActivityRecordMap copy() {
		_xdb_verify_unsafe_();
		return new RoleActivityRecordMap(this);
	}

	@Override
	public xbean.RoleActivityRecordMap toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleActivityRecordMap toBean() {
		_xdb_verify_unsafe_();
		return new RoleActivityRecordMap(this); // same as copy()
	}

	@Override
	public xbean.RoleActivityRecordMap toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleActivityRecordMap toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Long, xbean.RoleActivityRecord> getRecords() { // 活动数据
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "records"), records);
	}

	@Override
	public java.util.Map<Long, xbean.RoleActivityRecord> getRecordsAsData() { // 活动数据
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.RoleActivityRecord> records;
		RoleActivityRecordMap _o_ = this;
		records = new java.util.HashMap<Long, xbean.RoleActivityRecord>();
		for (java.util.Map.Entry<Long, xbean.RoleActivityRecord> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new RoleActivityRecord.Data(_e_.getValue()));
		return records;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleActivityRecordMap _o_ = null;
		if ( _o1_ instanceof RoleActivityRecordMap ) _o_ = (RoleActivityRecordMap)_o1_;
		else if ( _o1_ instanceof RoleActivityRecordMap.Const ) _o_ = ((RoleActivityRecordMap.Const)_o1_).nThis();
		else return false;
		if (!records.equals(_o_.records)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += records.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(records);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("records"));
		return lb;
	}

	private class Const implements xbean.RoleActivityRecordMap {
		RoleActivityRecordMap nThis() {
			return RoleActivityRecordMap.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleActivityRecordMap copy() {
			return RoleActivityRecordMap.this.copy();
		}

		@Override
		public xbean.RoleActivityRecordMap toData() {
			return RoleActivityRecordMap.this.toData();
		}

		public xbean.RoleActivityRecordMap toBean() {
			return RoleActivityRecordMap.this.toBean();
		}

		@Override
		public xbean.RoleActivityRecordMap toDataIf() {
			return RoleActivityRecordMap.this.toDataIf();
		}

		public xbean.RoleActivityRecordMap toBeanIf() {
			return RoleActivityRecordMap.this.toBeanIf();
		}

		@Override
		public java.util.Map<Long, xbean.RoleActivityRecord> getRecords() { // 活动数据
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(records);
		}

		@Override
		public java.util.Map<Long, xbean.RoleActivityRecord> getRecordsAsData() { // 活动数据
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.RoleActivityRecord> records;
			RoleActivityRecordMap _o_ = RoleActivityRecordMap.this;
			records = new java.util.HashMap<Long, xbean.RoleActivityRecord>();
			for (java.util.Map.Entry<Long, xbean.RoleActivityRecord> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new RoleActivityRecord.Data(_e_.getValue()));
			return records;
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
			return RoleActivityRecordMap.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleActivityRecordMap.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleActivityRecordMap.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleActivityRecordMap.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleActivityRecordMap.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleActivityRecordMap.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleActivityRecordMap.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleActivityRecordMap.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleActivityRecordMap.this.toString();
		}

	}

	public static final class Data implements xbean.RoleActivityRecordMap {
		private java.util.HashMap<Long, xbean.RoleActivityRecord> records; // 活动数据

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			records = new java.util.HashMap<Long, xbean.RoleActivityRecord>();
		}

		Data(xbean.RoleActivityRecordMap _o1_) {
			if (_o1_ instanceof RoleActivityRecordMap) assign((RoleActivityRecordMap)_o1_);
			else if (_o1_ instanceof RoleActivityRecordMap.Data) assign((RoleActivityRecordMap.Data)_o1_);
			else if (_o1_ instanceof RoleActivityRecordMap.Const) assign(((RoleActivityRecordMap.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleActivityRecordMap _o_) {
			records = new java.util.HashMap<Long, xbean.RoleActivityRecord>();
			for (java.util.Map.Entry<Long, xbean.RoleActivityRecord> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new RoleActivityRecord.Data(_e_.getValue()));
		}

		private void assign(RoleActivityRecordMap.Data _o_) {
			records = new java.util.HashMap<Long, xbean.RoleActivityRecord>();
			for (java.util.Map.Entry<Long, xbean.RoleActivityRecord> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new RoleActivityRecord.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(records.size());
for (java.util.Map.Entry<Long, xbean.RoleActivityRecord> _e_ : records.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		records = new java.util.HashMap<Long, xbean.RoleActivityRecord>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.RoleActivityRecord _v_ = xbean.Pod.newRoleActivityRecordData();
		_v_.unmarshal(_os_);
		records.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleActivityRecordMap copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleActivityRecordMap toData() {
			return new Data(this);
		}

		public xbean.RoleActivityRecordMap toBean() {
			return new RoleActivityRecordMap(this, null, null);
		}

		@Override
		public xbean.RoleActivityRecordMap toDataIf() {
			return this;
		}

		public xbean.RoleActivityRecordMap toBeanIf() {
			return new RoleActivityRecordMap(this, null, null);
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
		public java.util.Map<Long, xbean.RoleActivityRecord> getRecords() { // 活动数据
			return records;
		}

		@Override
		public java.util.Map<Long, xbean.RoleActivityRecord> getRecordsAsData() { // 活动数据
			return records;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleActivityRecordMap.Data)) return false;
			RoleActivityRecordMap.Data _o_ = (RoleActivityRecordMap.Data) _o1_;
			if (!records.equals(_o_.records)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += records.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(records);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
