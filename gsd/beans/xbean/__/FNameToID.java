
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class FNameToID extends xdb.XBean implements xbean.FNameToID {
	private java.util.HashMap<String, Long> data; // 

	@Override
	public void _reset_unsafe_() {
		data.clear();
	}

	FNameToID(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		data = new java.util.HashMap<String, Long>();
	}

	public FNameToID() {
		this(0, null, null);
	}

	public FNameToID(FNameToID _o_) {
		this(_o_, null, null);
	}

	FNameToID(xbean.FNameToID _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof FNameToID) assign((FNameToID)_o1_);
		else if (_o1_ instanceof FNameToID.Data) assign((FNameToID.Data)_o1_);
		else if (_o1_ instanceof FNameToID.Const) assign(((FNameToID.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(FNameToID _o_) {
		_o_._xdb_verify_unsafe_();
		data = new java.util.HashMap<String, Long>();
		for (java.util.Map.Entry<String, Long> _e_ : _o_.data.entrySet())
			data.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(FNameToID.Data _o_) {
		data = new java.util.HashMap<String, Long>();
		for (java.util.Map.Entry<String, Long> _e_ : _o_.data.entrySet())
			data.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(data.size());
for (java.util.Map.Entry<String, Long> _e_ : data.entrySet())
{
	_os_.marshal(_e_.getKey(), xdb.Const.IO_CHARSET);
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
		data = new java.util.HashMap<String, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		String _k_ = "";
		_k_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		data.put(_k_, _v_);
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
	public xbean.FNameToID copy() {
		_xdb_verify_unsafe_();
		return new FNameToID(this);
	}

	@Override
	public xbean.FNameToID toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FNameToID toBean() {
		_xdb_verify_unsafe_();
		return new FNameToID(this); // same as copy()
	}

	@Override
	public xbean.FNameToID toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FNameToID toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<String, Long> getData() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "data"), data);
	}

	@Override
	public java.util.Map<String, Long> getDataAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<String, Long> data;
		FNameToID _o_ = this;
		data = new java.util.HashMap<String, Long>();
		for (java.util.Map.Entry<String, Long> _e_ : _o_.data.entrySet())
			data.put(_e_.getKey(), _e_.getValue());
		return data;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		FNameToID _o_ = null;
		if ( _o1_ instanceof FNameToID ) _o_ = (FNameToID)_o1_;
		else if ( _o1_ instanceof FNameToID.Const ) _o_ = ((FNameToID.Const)_o1_).nThis();
		else return false;
		if (!data.equals(_o_.data)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += data.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(data);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("data"));
		return lb;
	}

	private class Const implements xbean.FNameToID {
		FNameToID nThis() {
			return FNameToID.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.FNameToID copy() {
			return FNameToID.this.copy();
		}

		@Override
		public xbean.FNameToID toData() {
			return FNameToID.this.toData();
		}

		public xbean.FNameToID toBean() {
			return FNameToID.this.toBean();
		}

		@Override
		public xbean.FNameToID toDataIf() {
			return FNameToID.this.toDataIf();
		}

		public xbean.FNameToID toBeanIf() {
			return FNameToID.this.toBeanIf();
		}

		@Override
		public java.util.Map<String, Long> getData() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(data);
		}

		@Override
		public java.util.Map<String, Long> getDataAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<String, Long> data;
			FNameToID _o_ = FNameToID.this;
			data = new java.util.HashMap<String, Long>();
			for (java.util.Map.Entry<String, Long> _e_ : _o_.data.entrySet())
				data.put(_e_.getKey(), _e_.getValue());
			return data;
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
			return FNameToID.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return FNameToID.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return FNameToID.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return FNameToID.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return FNameToID.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return FNameToID.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return FNameToID.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return FNameToID.this.hashCode();
		}

		@Override
		public String toString() {
			return FNameToID.this.toString();
		}

	}

	public static final class Data implements xbean.FNameToID {
		private java.util.HashMap<String, Long> data; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			data = new java.util.HashMap<String, Long>();
		}

		Data(xbean.FNameToID _o1_) {
			if (_o1_ instanceof FNameToID) assign((FNameToID)_o1_);
			else if (_o1_ instanceof FNameToID.Data) assign((FNameToID.Data)_o1_);
			else if (_o1_ instanceof FNameToID.Const) assign(((FNameToID.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(FNameToID _o_) {
			data = new java.util.HashMap<String, Long>();
			for (java.util.Map.Entry<String, Long> _e_ : _o_.data.entrySet())
				data.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(FNameToID.Data _o_) {
			data = new java.util.HashMap<String, Long>();
			for (java.util.Map.Entry<String, Long> _e_ : _o_.data.entrySet())
				data.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(data.size());
for (java.util.Map.Entry<String, Long> _e_ : data.entrySet())
{
	_os_.marshal(_e_.getKey(), xdb.Const.IO_CHARSET);
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
		data = new java.util.HashMap<String, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		String _k_ = "";
		_k_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		data.put(_k_, _v_);
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
		public xbean.FNameToID copy() {
			return new Data(this);
		}

		@Override
		public xbean.FNameToID toData() {
			return new Data(this);
		}

		public xbean.FNameToID toBean() {
			return new FNameToID(this, null, null);
		}

		@Override
		public xbean.FNameToID toDataIf() {
			return this;
		}

		public xbean.FNameToID toBeanIf() {
			return new FNameToID(this, null, null);
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
		public java.util.Map<String, Long> getData() { // 
			return data;
		}

		@Override
		public java.util.Map<String, Long> getDataAsData() { // 
			return data;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof FNameToID.Data)) return false;
			FNameToID.Data _o_ = (FNameToID.Data) _o1_;
			if (!data.equals(_o_.data)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += data.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(data);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
