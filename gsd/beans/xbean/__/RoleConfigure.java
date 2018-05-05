
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleConfigure extends xdb.XBean implements xbean.RoleConfigure {
	private java.util.HashMap<String, String> datas; // 

	@Override
	public void _reset_unsafe_() {
		datas.clear();
	}

	RoleConfigure(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		datas = new java.util.HashMap<String, String>();
	}

	public RoleConfigure() {
		this(0, null, null);
	}

	public RoleConfigure(RoleConfigure _o_) {
		this(_o_, null, null);
	}

	RoleConfigure(xbean.RoleConfigure _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleConfigure) assign((RoleConfigure)_o1_);
		else if (_o1_ instanceof RoleConfigure.Data) assign((RoleConfigure.Data)_o1_);
		else if (_o1_ instanceof RoleConfigure.Const) assign(((RoleConfigure.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleConfigure _o_) {
		_o_._xdb_verify_unsafe_();
		datas = new java.util.HashMap<String, String>();
		for (java.util.Map.Entry<String, String> _e_ : _o_.datas.entrySet())
			datas.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(RoleConfigure.Data _o_) {
		datas = new java.util.HashMap<String, String>();
		for (java.util.Map.Entry<String, String> _e_ : _o_.datas.entrySet())
			datas.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(datas.size());
for (java.util.Map.Entry<String, String> _e_ : datas.entrySet())
{
	_os_.marshal(_e_.getKey(), xdb.Const.IO_CHARSET);
	_os_.marshal(_e_.getValue(), xdb.Const.IO_CHARSET);
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
		datas = new java.util.HashMap<String, String>(size * 2);
	}
	for (; size > 0; --size)
	{
		String _k_ = "";
		_k_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
		String _v_ = "";
		_v_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
		datas.put(_k_, _v_);
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
	public xbean.RoleConfigure copy() {
		_xdb_verify_unsafe_();
		return new RoleConfigure(this);
	}

	@Override
	public xbean.RoleConfigure toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleConfigure toBean() {
		_xdb_verify_unsafe_();
		return new RoleConfigure(this); // same as copy()
	}

	@Override
	public xbean.RoleConfigure toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleConfigure toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<String, String> getDatas() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "datas"), datas);
	}

	@Override
	public java.util.Map<String, String> getDatasAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<String, String> datas;
		RoleConfigure _o_ = this;
		datas = new java.util.HashMap<String, String>();
		for (java.util.Map.Entry<String, String> _e_ : _o_.datas.entrySet())
			datas.put(_e_.getKey(), _e_.getValue());
		return datas;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleConfigure _o_ = null;
		if ( _o1_ instanceof RoleConfigure ) _o_ = (RoleConfigure)_o1_;
		else if ( _o1_ instanceof RoleConfigure.Const ) _o_ = ((RoleConfigure.Const)_o1_).nThis();
		else return false;
		if (!datas.equals(_o_.datas)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += datas.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(datas);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("datas"));
		return lb;
	}

	private class Const implements xbean.RoleConfigure {
		RoleConfigure nThis() {
			return RoleConfigure.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleConfigure copy() {
			return RoleConfigure.this.copy();
		}

		@Override
		public xbean.RoleConfigure toData() {
			return RoleConfigure.this.toData();
		}

		public xbean.RoleConfigure toBean() {
			return RoleConfigure.this.toBean();
		}

		@Override
		public xbean.RoleConfigure toDataIf() {
			return RoleConfigure.this.toDataIf();
		}

		public xbean.RoleConfigure toBeanIf() {
			return RoleConfigure.this.toBeanIf();
		}

		@Override
		public java.util.Map<String, String> getDatas() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(datas);
		}

		@Override
		public java.util.Map<String, String> getDatasAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<String, String> datas;
			RoleConfigure _o_ = RoleConfigure.this;
			datas = new java.util.HashMap<String, String>();
			for (java.util.Map.Entry<String, String> _e_ : _o_.datas.entrySet())
				datas.put(_e_.getKey(), _e_.getValue());
			return datas;
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
			return RoleConfigure.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleConfigure.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleConfigure.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleConfigure.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleConfigure.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleConfigure.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleConfigure.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleConfigure.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleConfigure.this.toString();
		}

	}

	public static final class Data implements xbean.RoleConfigure {
		private java.util.HashMap<String, String> datas; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			datas = new java.util.HashMap<String, String>();
		}

		Data(xbean.RoleConfigure _o1_) {
			if (_o1_ instanceof RoleConfigure) assign((RoleConfigure)_o1_);
			else if (_o1_ instanceof RoleConfigure.Data) assign((RoleConfigure.Data)_o1_);
			else if (_o1_ instanceof RoleConfigure.Const) assign(((RoleConfigure.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleConfigure _o_) {
			datas = new java.util.HashMap<String, String>();
			for (java.util.Map.Entry<String, String> _e_ : _o_.datas.entrySet())
				datas.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(RoleConfigure.Data _o_) {
			datas = new java.util.HashMap<String, String>();
			for (java.util.Map.Entry<String, String> _e_ : _o_.datas.entrySet())
				datas.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(datas.size());
for (java.util.Map.Entry<String, String> _e_ : datas.entrySet())
{
	_os_.marshal(_e_.getKey(), xdb.Const.IO_CHARSET);
	_os_.marshal(_e_.getValue(), xdb.Const.IO_CHARSET);
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
		datas = new java.util.HashMap<String, String>(size * 2);
	}
	for (; size > 0; --size)
	{
		String _k_ = "";
		_k_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
		String _v_ = "";
		_v_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
		datas.put(_k_, _v_);
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
		public xbean.RoleConfigure copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleConfigure toData() {
			return new Data(this);
		}

		public xbean.RoleConfigure toBean() {
			return new RoleConfigure(this, null, null);
		}

		@Override
		public xbean.RoleConfigure toDataIf() {
			return this;
		}

		public xbean.RoleConfigure toBeanIf() {
			return new RoleConfigure(this, null, null);
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
		public java.util.Map<String, String> getDatas() { // 
			return datas;
		}

		@Override
		public java.util.Map<String, String> getDatasAsData() { // 
			return datas;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleConfigure.Data)) return false;
			RoleConfigure.Data _o_ = (RoleConfigure.Data) _o1_;
			if (!datas.equals(_o_.datas)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += datas.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(datas);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
