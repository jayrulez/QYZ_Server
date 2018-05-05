
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ARank extends xdb.XBean implements xbean.ARank {
	private java.util.HashMap<Long, Integer> id2rank; // roleid-rank
	private java.util.HashMap<Integer, xbean.RankRecord> records; // 
	private long createtime; // 

	@Override
	public void _reset_unsafe_() {
		id2rank.clear();
		records.clear();
		createtime = 0L;
	}

	ARank(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		id2rank = new java.util.HashMap<Long, Integer>();
		records = new java.util.HashMap<Integer, xbean.RankRecord>();
	}

	public ARank() {
		this(0, null, null);
	}

	public ARank(ARank _o_) {
		this(_o_, null, null);
	}

	ARank(xbean.ARank _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ARank) assign((ARank)_o1_);
		else if (_o1_ instanceof ARank.Data) assign((ARank.Data)_o1_);
		else if (_o1_ instanceof ARank.Const) assign(((ARank.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ARank _o_) {
		_o_._xdb_verify_unsafe_();
		id2rank = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.id2rank.entrySet())
			id2rank.put(_e_.getKey(), _e_.getValue());
		records = new java.util.HashMap<Integer, xbean.RankRecord>();
		for (java.util.Map.Entry<Integer, xbean.RankRecord> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new RankRecord(_e_.getValue(), this, "records"));
		createtime = _o_.createtime;
	}

	private void assign(ARank.Data _o_) {
		id2rank = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.id2rank.entrySet())
			id2rank.put(_e_.getKey(), _e_.getValue());
		records = new java.util.HashMap<Integer, xbean.RankRecord>();
		for (java.util.Map.Entry<Integer, xbean.RankRecord> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new RankRecord(_e_.getValue(), this, "records"));
		createtime = _o_.createtime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(id2rank.size());
for (java.util.Map.Entry<Long, Integer> _e_ : id2rank.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(records.size());
for (java.util.Map.Entry<Integer, xbean.RankRecord> _e_ : records.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  3));_os_.marshal(createtime);
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
		id2rank = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		id2rank.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		records = new java.util.HashMap<Integer, xbean.RankRecord>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.RankRecord _v_ = new RankRecord(0, this, "records");
		_v_.unmarshal(_os_);
		records.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (10240|  3):createtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):createtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):createtime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ARank copy() {
		_xdb_verify_unsafe_();
		return new ARank(this);
	}

	@Override
	public xbean.ARank toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ARank toBean() {
		_xdb_verify_unsafe_();
		return new ARank(this); // same as copy()
	}

	@Override
	public xbean.ARank toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ARank toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Long, Integer> getId2rank() { // roleid-rank
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "id2rank"), id2rank);
	}

	@Override
	public java.util.Map<Long, Integer> getId2rankAsData() { // roleid-rank
		_xdb_verify_unsafe_();
		java.util.Map<Long, Integer> id2rank;
		ARank _o_ = this;
		id2rank = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.id2rank.entrySet())
			id2rank.put(_e_.getKey(), _e_.getValue());
		return id2rank;
	}

	@Override
	public java.util.Map<Integer, xbean.RankRecord> getRecords() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "records"), records);
	}

	@Override
	public java.util.Map<Integer, xbean.RankRecord> getRecordsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.RankRecord> records;
		ARank _o_ = this;
		records = new java.util.HashMap<Integer, xbean.RankRecord>();
		for (java.util.Map.Entry<Integer, xbean.RankRecord> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new RankRecord.Data(_e_.getValue()));
		return records;
	}

	@Override
	public long getCreatetime() { // 
		_xdb_verify_unsafe_();
		return createtime;
	}

	@Override
	public void setCreatetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "createtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, createtime) {
					public void rollback() { createtime = _xdb_saved; }
				};}});
		createtime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ARank _o_ = null;
		if ( _o1_ instanceof ARank ) _o_ = (ARank)_o1_;
		else if ( _o1_ instanceof ARank.Const ) _o_ = ((ARank.Const)_o1_).nThis();
		else return false;
		if (!id2rank.equals(_o_.id2rank)) return false;
		if (!records.equals(_o_.records)) return false;
		if (createtime != _o_.createtime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += id2rank.hashCode();
		_h_ += records.hashCode();
		_h_ += createtime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id2rank);
		_sb_.append(",");
		_sb_.append(records);
		_sb_.append(",");
		_sb_.append(createtime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("id2rank"));
		lb.add(new xdb.logs.ListenableMap().setVarName("records"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("createtime"));
		return lb;
	}

	private class Const implements xbean.ARank {
		ARank nThis() {
			return ARank.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ARank copy() {
			return ARank.this.copy();
		}

		@Override
		public xbean.ARank toData() {
			return ARank.this.toData();
		}

		public xbean.ARank toBean() {
			return ARank.this.toBean();
		}

		@Override
		public xbean.ARank toDataIf() {
			return ARank.this.toDataIf();
		}

		public xbean.ARank toBeanIf() {
			return ARank.this.toBeanIf();
		}

		@Override
		public java.util.Map<Long, Integer> getId2rank() { // roleid-rank
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(id2rank);
		}

		@Override
		public java.util.Map<Long, Integer> getId2rankAsData() { // roleid-rank
			_xdb_verify_unsafe_();
			java.util.Map<Long, Integer> id2rank;
			ARank _o_ = ARank.this;
			id2rank = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.id2rank.entrySet())
				id2rank.put(_e_.getKey(), _e_.getValue());
			return id2rank;
		}

		@Override
		public java.util.Map<Integer, xbean.RankRecord> getRecords() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(records);
		}

		@Override
		public java.util.Map<Integer, xbean.RankRecord> getRecordsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.RankRecord> records;
			ARank _o_ = ARank.this;
			records = new java.util.HashMap<Integer, xbean.RankRecord>();
			for (java.util.Map.Entry<Integer, xbean.RankRecord> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new RankRecord.Data(_e_.getValue()));
			return records;
		}

		@Override
		public long getCreatetime() { // 
			_xdb_verify_unsafe_();
			return createtime;
		}

		@Override
		public void setCreatetime(long _v_) { // 
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
			return ARank.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ARank.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ARank.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ARank.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ARank.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ARank.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ARank.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ARank.this.hashCode();
		}

		@Override
		public String toString() {
			return ARank.this.toString();
		}

	}

	public static final class Data implements xbean.ARank {
		private java.util.HashMap<Long, Integer> id2rank; // roleid-rank
		private java.util.HashMap<Integer, xbean.RankRecord> records; // 
		private long createtime; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			id2rank = new java.util.HashMap<Long, Integer>();
			records = new java.util.HashMap<Integer, xbean.RankRecord>();
		}

		Data(xbean.ARank _o1_) {
			if (_o1_ instanceof ARank) assign((ARank)_o1_);
			else if (_o1_ instanceof ARank.Data) assign((ARank.Data)_o1_);
			else if (_o1_ instanceof ARank.Const) assign(((ARank.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ARank _o_) {
			id2rank = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.id2rank.entrySet())
				id2rank.put(_e_.getKey(), _e_.getValue());
			records = new java.util.HashMap<Integer, xbean.RankRecord>();
			for (java.util.Map.Entry<Integer, xbean.RankRecord> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new RankRecord.Data(_e_.getValue()));
			createtime = _o_.createtime;
		}

		private void assign(ARank.Data _o_) {
			id2rank = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.id2rank.entrySet())
				id2rank.put(_e_.getKey(), _e_.getValue());
			records = new java.util.HashMap<Integer, xbean.RankRecord>();
			for (java.util.Map.Entry<Integer, xbean.RankRecord> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new RankRecord.Data(_e_.getValue()));
			createtime = _o_.createtime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(id2rank.size());
for (java.util.Map.Entry<Long, Integer> _e_ : id2rank.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(records.size());
for (java.util.Map.Entry<Integer, xbean.RankRecord> _e_ : records.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  3));_os_.marshal(createtime);
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
		id2rank = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		id2rank.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		records = new java.util.HashMap<Integer, xbean.RankRecord>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.RankRecord _v_ = xbean.Pod.newRankRecordData();
		_v_.unmarshal(_os_);
		records.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (10240|  3):createtime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):createtime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):createtime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ARank copy() {
			return new Data(this);
		}

		@Override
		public xbean.ARank toData() {
			return new Data(this);
		}

		public xbean.ARank toBean() {
			return new ARank(this, null, null);
		}

		@Override
		public xbean.ARank toDataIf() {
			return this;
		}

		public xbean.ARank toBeanIf() {
			return new ARank(this, null, null);
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
		public java.util.Map<Long, Integer> getId2rank() { // roleid-rank
			return id2rank;
		}

		@Override
		public java.util.Map<Long, Integer> getId2rankAsData() { // roleid-rank
			return id2rank;
		}

		@Override
		public java.util.Map<Integer, xbean.RankRecord> getRecords() { // 
			return records;
		}

		@Override
		public java.util.Map<Integer, xbean.RankRecord> getRecordsAsData() { // 
			return records;
		}

		@Override
		public long getCreatetime() { // 
			return createtime;
		}

		@Override
		public void setCreatetime(long _v_) { // 
			createtime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ARank.Data)) return false;
			ARank.Data _o_ = (ARank.Data) _o1_;
			if (!id2rank.equals(_o_.id2rank)) return false;
			if (!records.equals(_o_.records)) return false;
			if (createtime != _o_.createtime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += id2rank.hashCode();
			_h_ += records.hashCode();
			_h_ += createtime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(id2rank);
			_sb_.append(",");
			_sb_.append(records);
			_sb_.append(",");
			_sb_.append(createtime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
