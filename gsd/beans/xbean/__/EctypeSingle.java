
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class EctypeSingle extends xdb.XBean implements xbean.EctypeSingle {
	private java.util.HashMap<Integer, xbean.DailyEctypeRecord> dailyectypebestrecords; // key = ectypeid

	@Override
	public void _reset_unsafe_() {
		dailyectypebestrecords.clear();
	}

	EctypeSingle(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		dailyectypebestrecords = new java.util.HashMap<Integer, xbean.DailyEctypeRecord>();
	}

	public EctypeSingle() {
		this(0, null, null);
	}

	public EctypeSingle(EctypeSingle _o_) {
		this(_o_, null, null);
	}

	EctypeSingle(xbean.EctypeSingle _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof EctypeSingle) assign((EctypeSingle)_o1_);
		else if (_o1_ instanceof EctypeSingle.Data) assign((EctypeSingle.Data)_o1_);
		else if (_o1_ instanceof EctypeSingle.Const) assign(((EctypeSingle.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(EctypeSingle _o_) {
		_o_._xdb_verify_unsafe_();
		dailyectypebestrecords = new java.util.HashMap<Integer, xbean.DailyEctypeRecord>();
		for (java.util.Map.Entry<Integer, xbean.DailyEctypeRecord> _e_ : _o_.dailyectypebestrecords.entrySet())
			dailyectypebestrecords.put(_e_.getKey(), new DailyEctypeRecord(_e_.getValue(), this, "dailyectypebestrecords"));
	}

	private void assign(EctypeSingle.Data _o_) {
		dailyectypebestrecords = new java.util.HashMap<Integer, xbean.DailyEctypeRecord>();
		for (java.util.Map.Entry<Integer, xbean.DailyEctypeRecord> _e_ : _o_.dailyectypebestrecords.entrySet())
			dailyectypebestrecords.put(_e_.getKey(), new DailyEctypeRecord(_e_.getValue(), this, "dailyectypebestrecords"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(dailyectypebestrecords.size());
for (java.util.Map.Entry<Integer, xbean.DailyEctypeRecord> _e_ : dailyectypebestrecords.entrySet())
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
		dailyectypebestrecords = new java.util.HashMap<Integer, xbean.DailyEctypeRecord>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.DailyEctypeRecord _v_ = new DailyEctypeRecord(0, this, "dailyectypebestrecords");
		_v_.unmarshal(_os_);
		dailyectypebestrecords.put(_k_, _v_);
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
	public xbean.EctypeSingle copy() {
		_xdb_verify_unsafe_();
		return new EctypeSingle(this);
	}

	@Override
	public xbean.EctypeSingle toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.EctypeSingle toBean() {
		_xdb_verify_unsafe_();
		return new EctypeSingle(this); // same as copy()
	}

	@Override
	public xbean.EctypeSingle toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.EctypeSingle toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.DailyEctypeRecord> getDailyectypebestrecords() { // key = ectypeid
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "dailyectypebestrecords"), dailyectypebestrecords);
	}

	@Override
	public java.util.Map<Integer, xbean.DailyEctypeRecord> getDailyectypebestrecordsAsData() { // key = ectypeid
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.DailyEctypeRecord> dailyectypebestrecords;
		EctypeSingle _o_ = this;
		dailyectypebestrecords = new java.util.HashMap<Integer, xbean.DailyEctypeRecord>();
		for (java.util.Map.Entry<Integer, xbean.DailyEctypeRecord> _e_ : _o_.dailyectypebestrecords.entrySet())
			dailyectypebestrecords.put(_e_.getKey(), new DailyEctypeRecord.Data(_e_.getValue()));
		return dailyectypebestrecords;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		EctypeSingle _o_ = null;
		if ( _o1_ instanceof EctypeSingle ) _o_ = (EctypeSingle)_o1_;
		else if ( _o1_ instanceof EctypeSingle.Const ) _o_ = ((EctypeSingle.Const)_o1_).nThis();
		else return false;
		if (!dailyectypebestrecords.equals(_o_.dailyectypebestrecords)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += dailyectypebestrecords.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(dailyectypebestrecords);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("dailyectypebestrecords"));
		return lb;
	}

	private class Const implements xbean.EctypeSingle {
		EctypeSingle nThis() {
			return EctypeSingle.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.EctypeSingle copy() {
			return EctypeSingle.this.copy();
		}

		@Override
		public xbean.EctypeSingle toData() {
			return EctypeSingle.this.toData();
		}

		public xbean.EctypeSingle toBean() {
			return EctypeSingle.this.toBean();
		}

		@Override
		public xbean.EctypeSingle toDataIf() {
			return EctypeSingle.this.toDataIf();
		}

		public xbean.EctypeSingle toBeanIf() {
			return EctypeSingle.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.DailyEctypeRecord> getDailyectypebestrecords() { // key = ectypeid
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(dailyectypebestrecords);
		}

		@Override
		public java.util.Map<Integer, xbean.DailyEctypeRecord> getDailyectypebestrecordsAsData() { // key = ectypeid
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.DailyEctypeRecord> dailyectypebestrecords;
			EctypeSingle _o_ = EctypeSingle.this;
			dailyectypebestrecords = new java.util.HashMap<Integer, xbean.DailyEctypeRecord>();
			for (java.util.Map.Entry<Integer, xbean.DailyEctypeRecord> _e_ : _o_.dailyectypebestrecords.entrySet())
				dailyectypebestrecords.put(_e_.getKey(), new DailyEctypeRecord.Data(_e_.getValue()));
			return dailyectypebestrecords;
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
			return EctypeSingle.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return EctypeSingle.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return EctypeSingle.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return EctypeSingle.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return EctypeSingle.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return EctypeSingle.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return EctypeSingle.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return EctypeSingle.this.hashCode();
		}

		@Override
		public String toString() {
			return EctypeSingle.this.toString();
		}

	}

	public static final class Data implements xbean.EctypeSingle {
		private java.util.HashMap<Integer, xbean.DailyEctypeRecord> dailyectypebestrecords; // key = ectypeid

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			dailyectypebestrecords = new java.util.HashMap<Integer, xbean.DailyEctypeRecord>();
		}

		Data(xbean.EctypeSingle _o1_) {
			if (_o1_ instanceof EctypeSingle) assign((EctypeSingle)_o1_);
			else if (_o1_ instanceof EctypeSingle.Data) assign((EctypeSingle.Data)_o1_);
			else if (_o1_ instanceof EctypeSingle.Const) assign(((EctypeSingle.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(EctypeSingle _o_) {
			dailyectypebestrecords = new java.util.HashMap<Integer, xbean.DailyEctypeRecord>();
			for (java.util.Map.Entry<Integer, xbean.DailyEctypeRecord> _e_ : _o_.dailyectypebestrecords.entrySet())
				dailyectypebestrecords.put(_e_.getKey(), new DailyEctypeRecord.Data(_e_.getValue()));
		}

		private void assign(EctypeSingle.Data _o_) {
			dailyectypebestrecords = new java.util.HashMap<Integer, xbean.DailyEctypeRecord>();
			for (java.util.Map.Entry<Integer, xbean.DailyEctypeRecord> _e_ : _o_.dailyectypebestrecords.entrySet())
				dailyectypebestrecords.put(_e_.getKey(), new DailyEctypeRecord.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(dailyectypebestrecords.size());
for (java.util.Map.Entry<Integer, xbean.DailyEctypeRecord> _e_ : dailyectypebestrecords.entrySet())
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
		dailyectypebestrecords = new java.util.HashMap<Integer, xbean.DailyEctypeRecord>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.DailyEctypeRecord _v_ = xbean.Pod.newDailyEctypeRecordData();
		_v_.unmarshal(_os_);
		dailyectypebestrecords.put(_k_, _v_);
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
		public xbean.EctypeSingle copy() {
			return new Data(this);
		}

		@Override
		public xbean.EctypeSingle toData() {
			return new Data(this);
		}

		public xbean.EctypeSingle toBean() {
			return new EctypeSingle(this, null, null);
		}

		@Override
		public xbean.EctypeSingle toDataIf() {
			return this;
		}

		public xbean.EctypeSingle toBeanIf() {
			return new EctypeSingle(this, null, null);
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
		public java.util.Map<Integer, xbean.DailyEctypeRecord> getDailyectypebestrecords() { // key = ectypeid
			return dailyectypebestrecords;
		}

		@Override
		public java.util.Map<Integer, xbean.DailyEctypeRecord> getDailyectypebestrecordsAsData() { // key = ectypeid
			return dailyectypebestrecords;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof EctypeSingle.Data)) return false;
			EctypeSingle.Data _o_ = (EctypeSingle.Data) _o1_;
			if (!dailyectypebestrecords.equals(_o_.dailyectypebestrecords)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += dailyectypebestrecords.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(dailyectypebestrecords);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
