
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class GlobalActivationCode extends xdb.XBean implements xbean.GlobalActivationCode {
	private xdb.util.SetX<Integer> alltypes; // 

	@Override
	public void _reset_unsafe_() {
		alltypes.clear();
	}

	GlobalActivationCode(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		alltypes = new xdb.util.SetX<Integer>();
	}

	public GlobalActivationCode() {
		this(0, null, null);
	}

	public GlobalActivationCode(GlobalActivationCode _o_) {
		this(_o_, null, null);
	}

	GlobalActivationCode(xbean.GlobalActivationCode _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof GlobalActivationCode) assign((GlobalActivationCode)_o1_);
		else if (_o1_ instanceof GlobalActivationCode.Data) assign((GlobalActivationCode.Data)_o1_);
		else if (_o1_ instanceof GlobalActivationCode.Const) assign(((GlobalActivationCode.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(GlobalActivationCode _o_) {
		_o_._xdb_verify_unsafe_();
		alltypes = new xdb.util.SetX<Integer>();
		alltypes.addAll(_o_.alltypes);
	}

	private void assign(GlobalActivationCode.Data _o_) {
		alltypes = new xdb.util.SetX<Integer>();
		alltypes.addAll(_o_.alltypes);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(alltypes.size());
for (Integer _v_ : alltypes) {
	_os_.marshal(_v_);
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
    				case (20480|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	alltypes.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.GlobalActivationCode copy() {
		_xdb_verify_unsafe_();
		return new GlobalActivationCode(this);
	}

	@Override
	public xbean.GlobalActivationCode toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GlobalActivationCode toBean() {
		_xdb_verify_unsafe_();
		return new GlobalActivationCode(this); // same as copy()
	}

	@Override
	public xbean.GlobalActivationCode toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GlobalActivationCode toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Set<Integer> getAlltypes() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "alltypes"), alltypes);
	}

	public java.util.Set<Integer> getAlltypesAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Set<Integer> alltypes;
		GlobalActivationCode _o_ = this;
		alltypes = new xdb.util.SetX<Integer>();
		alltypes.addAll(_o_.alltypes);
		return alltypes;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		GlobalActivationCode _o_ = null;
		if ( _o1_ instanceof GlobalActivationCode ) _o_ = (GlobalActivationCode)_o1_;
		else if ( _o1_ instanceof GlobalActivationCode.Const ) _o_ = ((GlobalActivationCode.Const)_o1_).nThis();
		else return false;
		if (!alltypes.equals(_o_.alltypes)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += alltypes.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(alltypes);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableSet().setVarName("alltypes"));
		return lb;
	}

	private class Const implements xbean.GlobalActivationCode {
		GlobalActivationCode nThis() {
			return GlobalActivationCode.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.GlobalActivationCode copy() {
			return GlobalActivationCode.this.copy();
		}

		@Override
		public xbean.GlobalActivationCode toData() {
			return GlobalActivationCode.this.toData();
		}

		public xbean.GlobalActivationCode toBean() {
			return GlobalActivationCode.this.toBean();
		}

		@Override
		public xbean.GlobalActivationCode toDataIf() {
			return GlobalActivationCode.this.toDataIf();
		}

		public xbean.GlobalActivationCode toBeanIf() {
			return GlobalActivationCode.this.toBeanIf();
		}

		@Override
		public java.util.Set<Integer> getAlltypes() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(alltypes);
		}

		public java.util.Set<Integer> getAlltypesAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Set<Integer> alltypes;
			GlobalActivationCode _o_ = GlobalActivationCode.this;
		alltypes = new xdb.util.SetX<Integer>();
		alltypes.addAll(_o_.alltypes);
			return alltypes;
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
			return GlobalActivationCode.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return GlobalActivationCode.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return GlobalActivationCode.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return GlobalActivationCode.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return GlobalActivationCode.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return GlobalActivationCode.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return GlobalActivationCode.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return GlobalActivationCode.this.hashCode();
		}

		@Override
		public String toString() {
			return GlobalActivationCode.this.toString();
		}

	}

	public static final class Data implements xbean.GlobalActivationCode {
		private java.util.HashSet<Integer> alltypes; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			alltypes = new java.util.HashSet<Integer>();
		}

		Data(xbean.GlobalActivationCode _o1_) {
			if (_o1_ instanceof GlobalActivationCode) assign((GlobalActivationCode)_o1_);
			else if (_o1_ instanceof GlobalActivationCode.Data) assign((GlobalActivationCode.Data)_o1_);
			else if (_o1_ instanceof GlobalActivationCode.Const) assign(((GlobalActivationCode.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(GlobalActivationCode _o_) {
			alltypes = new java.util.HashSet<Integer>();
			alltypes.addAll(_o_.alltypes);
		}

		private void assign(GlobalActivationCode.Data _o_) {
			alltypes = new java.util.HashSet<Integer>();
			alltypes.addAll(_o_.alltypes);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(alltypes.size());
for (Integer _v_ : alltypes) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (20480|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	alltypes.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.GlobalActivationCode copy() {
			return new Data(this);
		}

		@Override
		public xbean.GlobalActivationCode toData() {
			return new Data(this);
		}

		public xbean.GlobalActivationCode toBean() {
			return new GlobalActivationCode(this, null, null);
		}

		@Override
		public xbean.GlobalActivationCode toDataIf() {
			return this;
		}

		public xbean.GlobalActivationCode toBeanIf() {
			return new GlobalActivationCode(this, null, null);
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
		public java.util.Set<Integer> getAlltypes() { // 
			return alltypes;
		}

		@Override
		public java.util.Set<Integer> getAlltypesAsData() { // 
			return alltypes;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof GlobalActivationCode.Data)) return false;
			GlobalActivationCode.Data _o_ = (GlobalActivationCode.Data) _o1_;
			if (!alltypes.equals(_o_.alltypes)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += alltypes.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(alltypes);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
