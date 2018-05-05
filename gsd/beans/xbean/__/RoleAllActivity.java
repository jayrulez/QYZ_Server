
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleAllActivity extends xdb.XBean implements xbean.RoleAllActivity {
	private java.util.HashMap<Integer, xbean.RoleActivityStatus> status; // 每个活动的状态
	private java.util.HashMap<Integer, xbean.RoleActivityRecordMap> records; // 活动数据

	@Override
	public void _reset_unsafe_() {
		status.clear();
		records.clear();
	}

	RoleAllActivity(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		status = new java.util.HashMap<Integer, xbean.RoleActivityStatus>();
		records = new java.util.HashMap<Integer, xbean.RoleActivityRecordMap>();
	}

	public RoleAllActivity() {
		this(0, null, null);
	}

	public RoleAllActivity(RoleAllActivity _o_) {
		this(_o_, null, null);
	}

	RoleAllActivity(xbean.RoleAllActivity _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleAllActivity) assign((RoleAllActivity)_o1_);
		else if (_o1_ instanceof RoleAllActivity.Data) assign((RoleAllActivity.Data)_o1_);
		else if (_o1_ instanceof RoleAllActivity.Const) assign(((RoleAllActivity.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleAllActivity _o_) {
		_o_._xdb_verify_unsafe_();
		status = new java.util.HashMap<Integer, xbean.RoleActivityStatus>();
		for (java.util.Map.Entry<Integer, xbean.RoleActivityStatus> _e_ : _o_.status.entrySet())
			status.put(_e_.getKey(), new RoleActivityStatus(_e_.getValue(), this, "status"));
		records = new java.util.HashMap<Integer, xbean.RoleActivityRecordMap>();
		for (java.util.Map.Entry<Integer, xbean.RoleActivityRecordMap> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new RoleActivityRecordMap(_e_.getValue(), this, "records"));
	}

	private void assign(RoleAllActivity.Data _o_) {
		status = new java.util.HashMap<Integer, xbean.RoleActivityStatus>();
		for (java.util.Map.Entry<Integer, xbean.RoleActivityStatus> _e_ : _o_.status.entrySet())
			status.put(_e_.getKey(), new RoleActivityStatus(_e_.getValue(), this, "status"));
		records = new java.util.HashMap<Integer, xbean.RoleActivityRecordMap>();
		for (java.util.Map.Entry<Integer, xbean.RoleActivityRecordMap> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new RoleActivityRecordMap(_e_.getValue(), this, "records"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(status.size());
for (java.util.Map.Entry<Integer, xbean.RoleActivityStatus> _e_ : status.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(records.size());
for (java.util.Map.Entry<Integer, xbean.RoleActivityRecordMap> _e_ : records.entrySet())
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
		status = new java.util.HashMap<Integer, xbean.RoleActivityStatus>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.RoleActivityStatus _v_ = new RoleActivityStatus(0, this, "status");
		_v_.unmarshal(_os_);
		status.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		records = new java.util.HashMap<Integer, xbean.RoleActivityRecordMap>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.RoleActivityRecordMap _v_ = new RoleActivityRecordMap(0, this, "records");
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
	public xbean.RoleAllActivity copy() {
		_xdb_verify_unsafe_();
		return new RoleAllActivity(this);
	}

	@Override
	public xbean.RoleAllActivity toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleAllActivity toBean() {
		_xdb_verify_unsafe_();
		return new RoleAllActivity(this); // same as copy()
	}

	@Override
	public xbean.RoleAllActivity toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleAllActivity toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.RoleActivityStatus> getStatus() { // 每个活动的状态
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "status"), status);
	}

	@Override
	public java.util.Map<Integer, xbean.RoleActivityStatus> getStatusAsData() { // 每个活动的状态
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.RoleActivityStatus> status;
		RoleAllActivity _o_ = this;
		status = new java.util.HashMap<Integer, xbean.RoleActivityStatus>();
		for (java.util.Map.Entry<Integer, xbean.RoleActivityStatus> _e_ : _o_.status.entrySet())
			status.put(_e_.getKey(), new RoleActivityStatus.Data(_e_.getValue()));
		return status;
	}

	@Override
	public java.util.Map<Integer, xbean.RoleActivityRecordMap> getRecords() { // 活动数据
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "records"), records);
	}

	@Override
	public java.util.Map<Integer, xbean.RoleActivityRecordMap> getRecordsAsData() { // 活动数据
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.RoleActivityRecordMap> records;
		RoleAllActivity _o_ = this;
		records = new java.util.HashMap<Integer, xbean.RoleActivityRecordMap>();
		for (java.util.Map.Entry<Integer, xbean.RoleActivityRecordMap> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new RoleActivityRecordMap.Data(_e_.getValue()));
		return records;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleAllActivity _o_ = null;
		if ( _o1_ instanceof RoleAllActivity ) _o_ = (RoleAllActivity)_o1_;
		else if ( _o1_ instanceof RoleAllActivity.Const ) _o_ = ((RoleAllActivity.Const)_o1_).nThis();
		else return false;
		if (!status.equals(_o_.status)) return false;
		if (!records.equals(_o_.records)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += status.hashCode();
		_h_ += records.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(status);
		_sb_.append(",");
		_sb_.append(records);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("status"));
		lb.add(new xdb.logs.ListenableMap().setVarName("records"));
		return lb;
	}

	private class Const implements xbean.RoleAllActivity {
		RoleAllActivity nThis() {
			return RoleAllActivity.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleAllActivity copy() {
			return RoleAllActivity.this.copy();
		}

		@Override
		public xbean.RoleAllActivity toData() {
			return RoleAllActivity.this.toData();
		}

		public xbean.RoleAllActivity toBean() {
			return RoleAllActivity.this.toBean();
		}

		@Override
		public xbean.RoleAllActivity toDataIf() {
			return RoleAllActivity.this.toDataIf();
		}

		public xbean.RoleAllActivity toBeanIf() {
			return RoleAllActivity.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.RoleActivityStatus> getStatus() { // 每个活动的状态
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(status);
		}

		@Override
		public java.util.Map<Integer, xbean.RoleActivityStatus> getStatusAsData() { // 每个活动的状态
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.RoleActivityStatus> status;
			RoleAllActivity _o_ = RoleAllActivity.this;
			status = new java.util.HashMap<Integer, xbean.RoleActivityStatus>();
			for (java.util.Map.Entry<Integer, xbean.RoleActivityStatus> _e_ : _o_.status.entrySet())
				status.put(_e_.getKey(), new RoleActivityStatus.Data(_e_.getValue()));
			return status;
		}

		@Override
		public java.util.Map<Integer, xbean.RoleActivityRecordMap> getRecords() { // 活动数据
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(records);
		}

		@Override
		public java.util.Map<Integer, xbean.RoleActivityRecordMap> getRecordsAsData() { // 活动数据
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.RoleActivityRecordMap> records;
			RoleAllActivity _o_ = RoleAllActivity.this;
			records = new java.util.HashMap<Integer, xbean.RoleActivityRecordMap>();
			for (java.util.Map.Entry<Integer, xbean.RoleActivityRecordMap> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new RoleActivityRecordMap.Data(_e_.getValue()));
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
			return RoleAllActivity.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleAllActivity.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleAllActivity.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleAllActivity.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleAllActivity.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleAllActivity.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleAllActivity.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleAllActivity.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleAllActivity.this.toString();
		}

	}

	public static final class Data implements xbean.RoleAllActivity {
		private java.util.HashMap<Integer, xbean.RoleActivityStatus> status; // 每个活动的状态
		private java.util.HashMap<Integer, xbean.RoleActivityRecordMap> records; // 活动数据

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			status = new java.util.HashMap<Integer, xbean.RoleActivityStatus>();
			records = new java.util.HashMap<Integer, xbean.RoleActivityRecordMap>();
		}

		Data(xbean.RoleAllActivity _o1_) {
			if (_o1_ instanceof RoleAllActivity) assign((RoleAllActivity)_o1_);
			else if (_o1_ instanceof RoleAllActivity.Data) assign((RoleAllActivity.Data)_o1_);
			else if (_o1_ instanceof RoleAllActivity.Const) assign(((RoleAllActivity.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleAllActivity _o_) {
			status = new java.util.HashMap<Integer, xbean.RoleActivityStatus>();
			for (java.util.Map.Entry<Integer, xbean.RoleActivityStatus> _e_ : _o_.status.entrySet())
				status.put(_e_.getKey(), new RoleActivityStatus.Data(_e_.getValue()));
			records = new java.util.HashMap<Integer, xbean.RoleActivityRecordMap>();
			for (java.util.Map.Entry<Integer, xbean.RoleActivityRecordMap> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new RoleActivityRecordMap.Data(_e_.getValue()));
		}

		private void assign(RoleAllActivity.Data _o_) {
			status = new java.util.HashMap<Integer, xbean.RoleActivityStatus>();
			for (java.util.Map.Entry<Integer, xbean.RoleActivityStatus> _e_ : _o_.status.entrySet())
				status.put(_e_.getKey(), new RoleActivityStatus.Data(_e_.getValue()));
			records = new java.util.HashMap<Integer, xbean.RoleActivityRecordMap>();
			for (java.util.Map.Entry<Integer, xbean.RoleActivityRecordMap> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new RoleActivityRecordMap.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(status.size());
for (java.util.Map.Entry<Integer, xbean.RoleActivityStatus> _e_ : status.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(records.size());
for (java.util.Map.Entry<Integer, xbean.RoleActivityRecordMap> _e_ : records.entrySet())
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
		status = new java.util.HashMap<Integer, xbean.RoleActivityStatus>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.RoleActivityStatus _v_ = xbean.Pod.newRoleActivityStatusData();
		_v_.unmarshal(_os_);
		status.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		records = new java.util.HashMap<Integer, xbean.RoleActivityRecordMap>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.RoleActivityRecordMap _v_ = xbean.Pod.newRoleActivityRecordMapData();
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
		public xbean.RoleAllActivity copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleAllActivity toData() {
			return new Data(this);
		}

		public xbean.RoleAllActivity toBean() {
			return new RoleAllActivity(this, null, null);
		}

		@Override
		public xbean.RoleAllActivity toDataIf() {
			return this;
		}

		public xbean.RoleAllActivity toBeanIf() {
			return new RoleAllActivity(this, null, null);
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
		public java.util.Map<Integer, xbean.RoleActivityStatus> getStatus() { // 每个活动的状态
			return status;
		}

		@Override
		public java.util.Map<Integer, xbean.RoleActivityStatus> getStatusAsData() { // 每个活动的状态
			return status;
		}

		@Override
		public java.util.Map<Integer, xbean.RoleActivityRecordMap> getRecords() { // 活动数据
			return records;
		}

		@Override
		public java.util.Map<Integer, xbean.RoleActivityRecordMap> getRecordsAsData() { // 活动数据
			return records;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleAllActivity.Data)) return false;
			RoleAllActivity.Data _o_ = (RoleAllActivity.Data) _o1_;
			if (!status.equals(_o_.status)) return false;
			if (!records.equals(_o_.records)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += status.hashCode();
			_h_ += records.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(status);
			_sb_.append(",");
			_sb_.append(records);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
