
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class FamilyJobStaffList extends xdb.XBean implements xbean.FamilyJobStaffList {
	private java.util.HashMap<Long, Long> staffs; // key为角色id，long为成为该职位的时间

	@Override
	public void _reset_unsafe_() {
		staffs.clear();
	}

	FamilyJobStaffList(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		staffs = new java.util.HashMap<Long, Long>();
	}

	public FamilyJobStaffList() {
		this(0, null, null);
	}

	public FamilyJobStaffList(FamilyJobStaffList _o_) {
		this(_o_, null, null);
	}

	FamilyJobStaffList(xbean.FamilyJobStaffList _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof FamilyJobStaffList) assign((FamilyJobStaffList)_o1_);
		else if (_o1_ instanceof FamilyJobStaffList.Data) assign((FamilyJobStaffList.Data)_o1_);
		else if (_o1_ instanceof FamilyJobStaffList.Const) assign(((FamilyJobStaffList.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(FamilyJobStaffList _o_) {
		_o_._xdb_verify_unsafe_();
		staffs = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.staffs.entrySet())
			staffs.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(FamilyJobStaffList.Data _o_) {
		staffs = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.staffs.entrySet())
			staffs.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(staffs.size());
for (java.util.Map.Entry<Long, Long> _e_ : staffs.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
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
		staffs = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		staffs.put(_k_, _v_);
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
	public xbean.FamilyJobStaffList copy() {
		_xdb_verify_unsafe_();
		return new FamilyJobStaffList(this);
	}

	@Override
	public xbean.FamilyJobStaffList toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyJobStaffList toBean() {
		_xdb_verify_unsafe_();
		return new FamilyJobStaffList(this); // same as copy()
	}

	@Override
	public xbean.FamilyJobStaffList toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyJobStaffList toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Long, Long> getStaffs() { // key为角色id，long为成为该职位的时间
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "staffs"), staffs);
	}

	@Override
	public java.util.Map<Long, Long> getStaffsAsData() { // key为角色id，long为成为该职位的时间
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> staffs;
		FamilyJobStaffList _o_ = this;
		staffs = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.staffs.entrySet())
			staffs.put(_e_.getKey(), _e_.getValue());
		return staffs;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		FamilyJobStaffList _o_ = null;
		if ( _o1_ instanceof FamilyJobStaffList ) _o_ = (FamilyJobStaffList)_o1_;
		else if ( _o1_ instanceof FamilyJobStaffList.Const ) _o_ = ((FamilyJobStaffList.Const)_o1_).nThis();
		else return false;
		if (!staffs.equals(_o_.staffs)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += staffs.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(staffs);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("staffs"));
		return lb;
	}

	private class Const implements xbean.FamilyJobStaffList {
		FamilyJobStaffList nThis() {
			return FamilyJobStaffList.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.FamilyJobStaffList copy() {
			return FamilyJobStaffList.this.copy();
		}

		@Override
		public xbean.FamilyJobStaffList toData() {
			return FamilyJobStaffList.this.toData();
		}

		public xbean.FamilyJobStaffList toBean() {
			return FamilyJobStaffList.this.toBean();
		}

		@Override
		public xbean.FamilyJobStaffList toDataIf() {
			return FamilyJobStaffList.this.toDataIf();
		}

		public xbean.FamilyJobStaffList toBeanIf() {
			return FamilyJobStaffList.this.toBeanIf();
		}

		@Override
		public java.util.Map<Long, Long> getStaffs() { // key为角色id，long为成为该职位的时间
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(staffs);
		}

		@Override
		public java.util.Map<Long, Long> getStaffsAsData() { // key为角色id，long为成为该职位的时间
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> staffs;
			FamilyJobStaffList _o_ = FamilyJobStaffList.this;
			staffs = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.staffs.entrySet())
				staffs.put(_e_.getKey(), _e_.getValue());
			return staffs;
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
			return FamilyJobStaffList.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return FamilyJobStaffList.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return FamilyJobStaffList.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return FamilyJobStaffList.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return FamilyJobStaffList.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return FamilyJobStaffList.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return FamilyJobStaffList.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return FamilyJobStaffList.this.hashCode();
		}

		@Override
		public String toString() {
			return FamilyJobStaffList.this.toString();
		}

	}

	public static final class Data implements xbean.FamilyJobStaffList {
		private java.util.HashMap<Long, Long> staffs; // key为角色id，long为成为该职位的时间

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			staffs = new java.util.HashMap<Long, Long>();
		}

		Data(xbean.FamilyJobStaffList _o1_) {
			if (_o1_ instanceof FamilyJobStaffList) assign((FamilyJobStaffList)_o1_);
			else if (_o1_ instanceof FamilyJobStaffList.Data) assign((FamilyJobStaffList.Data)_o1_);
			else if (_o1_ instanceof FamilyJobStaffList.Const) assign(((FamilyJobStaffList.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(FamilyJobStaffList _o_) {
			staffs = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.staffs.entrySet())
				staffs.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(FamilyJobStaffList.Data _o_) {
			staffs = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.staffs.entrySet())
				staffs.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(staffs.size());
for (java.util.Map.Entry<Long, Long> _e_ : staffs.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
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
		staffs = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		staffs.put(_k_, _v_);
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
		public xbean.FamilyJobStaffList copy() {
			return new Data(this);
		}

		@Override
		public xbean.FamilyJobStaffList toData() {
			return new Data(this);
		}

		public xbean.FamilyJobStaffList toBean() {
			return new FamilyJobStaffList(this, null, null);
		}

		@Override
		public xbean.FamilyJobStaffList toDataIf() {
			return this;
		}

		public xbean.FamilyJobStaffList toBeanIf() {
			return new FamilyJobStaffList(this, null, null);
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
		public java.util.Map<Long, Long> getStaffs() { // key为角色id，long为成为该职位的时间
			return staffs;
		}

		@Override
		public java.util.Map<Long, Long> getStaffsAsData() { // key为角色id，long为成为该职位的时间
			return staffs;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof FamilyJobStaffList.Data)) return false;
			FamilyJobStaffList.Data _o_ = (FamilyJobStaffList.Data) _o1_;
			if (!staffs.equals(_o_.staffs)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += staffs.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(staffs);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
