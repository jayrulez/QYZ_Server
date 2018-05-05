
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleActiveCode extends xdb.XBean implements xbean.RoleActiveCode {
	private java.util.HashMap<Integer, xbean.ActiveCode> codegroups; // 

	@Override
	public void _reset_unsafe_() {
		codegroups.clear();
	}

	RoleActiveCode(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		codegroups = new java.util.HashMap<Integer, xbean.ActiveCode>();
	}

	public RoleActiveCode() {
		this(0, null, null);
	}

	public RoleActiveCode(RoleActiveCode _o_) {
		this(_o_, null, null);
	}

	RoleActiveCode(xbean.RoleActiveCode _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleActiveCode) assign((RoleActiveCode)_o1_);
		else if (_o1_ instanceof RoleActiveCode.Data) assign((RoleActiveCode.Data)_o1_);
		else if (_o1_ instanceof RoleActiveCode.Const) assign(((RoleActiveCode.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleActiveCode _o_) {
		_o_._xdb_verify_unsafe_();
		codegroups = new java.util.HashMap<Integer, xbean.ActiveCode>();
		for (java.util.Map.Entry<Integer, xbean.ActiveCode> _e_ : _o_.codegroups.entrySet())
			codegroups.put(_e_.getKey(), new ActiveCode(_e_.getValue(), this, "codegroups"));
	}

	private void assign(RoleActiveCode.Data _o_) {
		codegroups = new java.util.HashMap<Integer, xbean.ActiveCode>();
		for (java.util.Map.Entry<Integer, xbean.ActiveCode> _e_ : _o_.codegroups.entrySet())
			codegroups.put(_e_.getKey(), new ActiveCode(_e_.getValue(), this, "codegroups"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(codegroups.size());
for (java.util.Map.Entry<Integer, xbean.ActiveCode> _e_ : codegroups.entrySet())
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
		codegroups = new java.util.HashMap<Integer, xbean.ActiveCode>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.ActiveCode _v_ = new ActiveCode(0, this, "codegroups");
		_v_.unmarshal(_os_);
		codegroups.put(_k_, _v_);
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
	public xbean.RoleActiveCode copy() {
		_xdb_verify_unsafe_();
		return new RoleActiveCode(this);
	}

	@Override
	public xbean.RoleActiveCode toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleActiveCode toBean() {
		_xdb_verify_unsafe_();
		return new RoleActiveCode(this); // same as copy()
	}

	@Override
	public xbean.RoleActiveCode toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleActiveCode toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.ActiveCode> getCodegroups() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "codegroups"), codegroups);
	}

	@Override
	public java.util.Map<Integer, xbean.ActiveCode> getCodegroupsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.ActiveCode> codegroups;
		RoleActiveCode _o_ = this;
		codegroups = new java.util.HashMap<Integer, xbean.ActiveCode>();
		for (java.util.Map.Entry<Integer, xbean.ActiveCode> _e_ : _o_.codegroups.entrySet())
			codegroups.put(_e_.getKey(), new ActiveCode.Data(_e_.getValue()));
		return codegroups;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleActiveCode _o_ = null;
		if ( _o1_ instanceof RoleActiveCode ) _o_ = (RoleActiveCode)_o1_;
		else if ( _o1_ instanceof RoleActiveCode.Const ) _o_ = ((RoleActiveCode.Const)_o1_).nThis();
		else return false;
		if (!codegroups.equals(_o_.codegroups)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += codegroups.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(codegroups);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("codegroups"));
		return lb;
	}

	private class Const implements xbean.RoleActiveCode {
		RoleActiveCode nThis() {
			return RoleActiveCode.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleActiveCode copy() {
			return RoleActiveCode.this.copy();
		}

		@Override
		public xbean.RoleActiveCode toData() {
			return RoleActiveCode.this.toData();
		}

		public xbean.RoleActiveCode toBean() {
			return RoleActiveCode.this.toBean();
		}

		@Override
		public xbean.RoleActiveCode toDataIf() {
			return RoleActiveCode.this.toDataIf();
		}

		public xbean.RoleActiveCode toBeanIf() {
			return RoleActiveCode.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.ActiveCode> getCodegroups() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(codegroups);
		}

		@Override
		public java.util.Map<Integer, xbean.ActiveCode> getCodegroupsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.ActiveCode> codegroups;
			RoleActiveCode _o_ = RoleActiveCode.this;
			codegroups = new java.util.HashMap<Integer, xbean.ActiveCode>();
			for (java.util.Map.Entry<Integer, xbean.ActiveCode> _e_ : _o_.codegroups.entrySet())
				codegroups.put(_e_.getKey(), new ActiveCode.Data(_e_.getValue()));
			return codegroups;
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
			return RoleActiveCode.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleActiveCode.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleActiveCode.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleActiveCode.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleActiveCode.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleActiveCode.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleActiveCode.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleActiveCode.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleActiveCode.this.toString();
		}

	}

	public static final class Data implements xbean.RoleActiveCode {
		private java.util.HashMap<Integer, xbean.ActiveCode> codegroups; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			codegroups = new java.util.HashMap<Integer, xbean.ActiveCode>();
		}

		Data(xbean.RoleActiveCode _o1_) {
			if (_o1_ instanceof RoleActiveCode) assign((RoleActiveCode)_o1_);
			else if (_o1_ instanceof RoleActiveCode.Data) assign((RoleActiveCode.Data)_o1_);
			else if (_o1_ instanceof RoleActiveCode.Const) assign(((RoleActiveCode.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleActiveCode _o_) {
			codegroups = new java.util.HashMap<Integer, xbean.ActiveCode>();
			for (java.util.Map.Entry<Integer, xbean.ActiveCode> _e_ : _o_.codegroups.entrySet())
				codegroups.put(_e_.getKey(), new ActiveCode.Data(_e_.getValue()));
		}

		private void assign(RoleActiveCode.Data _o_) {
			codegroups = new java.util.HashMap<Integer, xbean.ActiveCode>();
			for (java.util.Map.Entry<Integer, xbean.ActiveCode> _e_ : _o_.codegroups.entrySet())
				codegroups.put(_e_.getKey(), new ActiveCode.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(codegroups.size());
for (java.util.Map.Entry<Integer, xbean.ActiveCode> _e_ : codegroups.entrySet())
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
		codegroups = new java.util.HashMap<Integer, xbean.ActiveCode>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.ActiveCode _v_ = xbean.Pod.newActiveCodeData();
		_v_.unmarshal(_os_);
		codegroups.put(_k_, _v_);
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
		public xbean.RoleActiveCode copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleActiveCode toData() {
			return new Data(this);
		}

		public xbean.RoleActiveCode toBean() {
			return new RoleActiveCode(this, null, null);
		}

		@Override
		public xbean.RoleActiveCode toDataIf() {
			return this;
		}

		public xbean.RoleActiveCode toBeanIf() {
			return new RoleActiveCode(this, null, null);
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
		public java.util.Map<Integer, xbean.ActiveCode> getCodegroups() { // 
			return codegroups;
		}

		@Override
		public java.util.Map<Integer, xbean.ActiveCode> getCodegroupsAsData() { // 
			return codegroups;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleActiveCode.Data)) return false;
			RoleActiveCode.Data _o_ = (RoleActiveCode.Data) _o1_;
			if (!codegroups.equals(_o_.codegroups)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += codegroups.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(codegroups);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
