
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class BoardRecord extends xdb.XBean implements xbean.BoardRecord {
	private java.util.HashMap<Integer, xbean.BoardRecordEntry> records; // 

	@Override
	public void _reset_unsafe_() {
		records.clear();
	}

	BoardRecord(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		records = new java.util.HashMap<Integer, xbean.BoardRecordEntry>();
	}

	public BoardRecord() {
		this(0, null, null);
	}

	public BoardRecord(BoardRecord _o_) {
		this(_o_, null, null);
	}

	BoardRecord(xbean.BoardRecord _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof BoardRecord) assign((BoardRecord)_o1_);
		else if (_o1_ instanceof BoardRecord.Data) assign((BoardRecord.Data)_o1_);
		else if (_o1_ instanceof BoardRecord.Const) assign(((BoardRecord.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(BoardRecord _o_) {
		_o_._xdb_verify_unsafe_();
		records = new java.util.HashMap<Integer, xbean.BoardRecordEntry>();
		for (java.util.Map.Entry<Integer, xbean.BoardRecordEntry> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new BoardRecordEntry(_e_.getValue(), this, "records"));
	}

	private void assign(BoardRecord.Data _o_) {
		records = new java.util.HashMap<Integer, xbean.BoardRecordEntry>();
		for (java.util.Map.Entry<Integer, xbean.BoardRecordEntry> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new BoardRecordEntry(_e_.getValue(), this, "records"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(records.size());
for (java.util.Map.Entry<Integer, xbean.BoardRecordEntry> _e_ : records.entrySet())
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
		records = new java.util.HashMap<Integer, xbean.BoardRecordEntry>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.BoardRecordEntry _v_ = new BoardRecordEntry(0, this, "records");
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
	public xbean.BoardRecord copy() {
		_xdb_verify_unsafe_();
		return new BoardRecord(this);
	}

	@Override
	public xbean.BoardRecord toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.BoardRecord toBean() {
		_xdb_verify_unsafe_();
		return new BoardRecord(this); // same as copy()
	}

	@Override
	public xbean.BoardRecord toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.BoardRecord toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.BoardRecordEntry> getRecords() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "records"), records);
	}

	@Override
	public java.util.Map<Integer, xbean.BoardRecordEntry> getRecordsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.BoardRecordEntry> records;
		BoardRecord _o_ = this;
		records = new java.util.HashMap<Integer, xbean.BoardRecordEntry>();
		for (java.util.Map.Entry<Integer, xbean.BoardRecordEntry> _e_ : _o_.records.entrySet())
			records.put(_e_.getKey(), new BoardRecordEntry.Data(_e_.getValue()));
		return records;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		BoardRecord _o_ = null;
		if ( _o1_ instanceof BoardRecord ) _o_ = (BoardRecord)_o1_;
		else if ( _o1_ instanceof BoardRecord.Const ) _o_ = ((BoardRecord.Const)_o1_).nThis();
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

	private class Const implements xbean.BoardRecord {
		BoardRecord nThis() {
			return BoardRecord.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.BoardRecord copy() {
			return BoardRecord.this.copy();
		}

		@Override
		public xbean.BoardRecord toData() {
			return BoardRecord.this.toData();
		}

		public xbean.BoardRecord toBean() {
			return BoardRecord.this.toBean();
		}

		@Override
		public xbean.BoardRecord toDataIf() {
			return BoardRecord.this.toDataIf();
		}

		public xbean.BoardRecord toBeanIf() {
			return BoardRecord.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.BoardRecordEntry> getRecords() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(records);
		}

		@Override
		public java.util.Map<Integer, xbean.BoardRecordEntry> getRecordsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.BoardRecordEntry> records;
			BoardRecord _o_ = BoardRecord.this;
			records = new java.util.HashMap<Integer, xbean.BoardRecordEntry>();
			for (java.util.Map.Entry<Integer, xbean.BoardRecordEntry> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new BoardRecordEntry.Data(_e_.getValue()));
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
			return BoardRecord.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return BoardRecord.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return BoardRecord.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return BoardRecord.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return BoardRecord.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return BoardRecord.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return BoardRecord.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return BoardRecord.this.hashCode();
		}

		@Override
		public String toString() {
			return BoardRecord.this.toString();
		}

	}

	public static final class Data implements xbean.BoardRecord {
		private java.util.HashMap<Integer, xbean.BoardRecordEntry> records; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			records = new java.util.HashMap<Integer, xbean.BoardRecordEntry>();
		}

		Data(xbean.BoardRecord _o1_) {
			if (_o1_ instanceof BoardRecord) assign((BoardRecord)_o1_);
			else if (_o1_ instanceof BoardRecord.Data) assign((BoardRecord.Data)_o1_);
			else if (_o1_ instanceof BoardRecord.Const) assign(((BoardRecord.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(BoardRecord _o_) {
			records = new java.util.HashMap<Integer, xbean.BoardRecordEntry>();
			for (java.util.Map.Entry<Integer, xbean.BoardRecordEntry> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new BoardRecordEntry.Data(_e_.getValue()));
		}

		private void assign(BoardRecord.Data _o_) {
			records = new java.util.HashMap<Integer, xbean.BoardRecordEntry>();
			for (java.util.Map.Entry<Integer, xbean.BoardRecordEntry> _e_ : _o_.records.entrySet())
				records.put(_e_.getKey(), new BoardRecordEntry.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(records.size());
for (java.util.Map.Entry<Integer, xbean.BoardRecordEntry> _e_ : records.entrySet())
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
		records = new java.util.HashMap<Integer, xbean.BoardRecordEntry>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.BoardRecordEntry _v_ = xbean.Pod.newBoardRecordEntryData();
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
		public xbean.BoardRecord copy() {
			return new Data(this);
		}

		@Override
		public xbean.BoardRecord toData() {
			return new Data(this);
		}

		public xbean.BoardRecord toBean() {
			return new BoardRecord(this, null, null);
		}

		@Override
		public xbean.BoardRecord toDataIf() {
			return this;
		}

		public xbean.BoardRecord toBeanIf() {
			return new BoardRecord(this, null, null);
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
		public java.util.Map<Integer, xbean.BoardRecordEntry> getRecords() { // 
			return records;
		}

		@Override
		public java.util.Map<Integer, xbean.BoardRecordEntry> getRecordsAsData() { // 
			return records;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof BoardRecord.Data)) return false;
			BoardRecord.Data _o_ = (BoardRecord.Data) _o1_;
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
