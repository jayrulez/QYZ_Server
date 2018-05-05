
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class IntSet extends xdb.XBean implements xbean.IntSet {
	private xdb.util.SetX<Integer> val; // 

	@Override
	public void _reset_unsafe_() {
		val.clear();
	}

	IntSet(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		val = new xdb.util.SetX<Integer>();
	}

	public IntSet() {
		this(0, null, null);
	}

	public IntSet(IntSet _o_) {
		this(_o_, null, null);
	}

	IntSet(xbean.IntSet _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof IntSet) assign((IntSet)_o1_);
		else if (_o1_ instanceof IntSet.Data) assign((IntSet.Data)_o1_);
		else if (_o1_ instanceof IntSet.Const) assign(((IntSet.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(IntSet _o_) {
		_o_._xdb_verify_unsafe_();
		val = new xdb.util.SetX<Integer>();
		val.addAll(_o_.val);
	}

	private void assign(IntSet.Data _o_) {
		val = new xdb.util.SetX<Integer>();
		val.addAll(_o_.val);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(val.size());
for (Integer _v_ : val) {
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
	val.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.IntSet copy() {
		_xdb_verify_unsafe_();
		return new IntSet(this);
	}

	@Override
	public xbean.IntSet toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IntSet toBean() {
		_xdb_verify_unsafe_();
		return new IntSet(this); // same as copy()
	}

	@Override
	public xbean.IntSet toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IntSet toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Set<Integer> getVal() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "val"), val);
	}

	public java.util.Set<Integer> getValAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Set<Integer> val;
		IntSet _o_ = this;
		val = new xdb.util.SetX<Integer>();
		val.addAll(_o_.val);
		return val;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		IntSet _o_ = null;
		if ( _o1_ instanceof IntSet ) _o_ = (IntSet)_o1_;
		else if ( _o1_ instanceof IntSet.Const ) _o_ = ((IntSet.Const)_o1_).nThis();
		else return false;
		if (!val.equals(_o_.val)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += val.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(val);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableSet().setVarName("val"));
		return lb;
	}

	private class Const implements xbean.IntSet {
		IntSet nThis() {
			return IntSet.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.IntSet copy() {
			return IntSet.this.copy();
		}

		@Override
		public xbean.IntSet toData() {
			return IntSet.this.toData();
		}

		public xbean.IntSet toBean() {
			return IntSet.this.toBean();
		}

		@Override
		public xbean.IntSet toDataIf() {
			return IntSet.this.toDataIf();
		}

		public xbean.IntSet toBeanIf() {
			return IntSet.this.toBeanIf();
		}

		@Override
		public java.util.Set<Integer> getVal() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(val);
		}

		public java.util.Set<Integer> getValAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Set<Integer> val;
			IntSet _o_ = IntSet.this;
		val = new xdb.util.SetX<Integer>();
		val.addAll(_o_.val);
			return val;
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
			return IntSet.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return IntSet.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return IntSet.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return IntSet.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return IntSet.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return IntSet.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return IntSet.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return IntSet.this.hashCode();
		}

		@Override
		public String toString() {
			return IntSet.this.toString();
		}

	}

	public static final class Data implements xbean.IntSet {
		private java.util.HashSet<Integer> val; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			val = new java.util.HashSet<Integer>();
		}

		Data(xbean.IntSet _o1_) {
			if (_o1_ instanceof IntSet) assign((IntSet)_o1_);
			else if (_o1_ instanceof IntSet.Data) assign((IntSet.Data)_o1_);
			else if (_o1_ instanceof IntSet.Const) assign(((IntSet.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(IntSet _o_) {
			val = new java.util.HashSet<Integer>();
			val.addAll(_o_.val);
		}

		private void assign(IntSet.Data _o_) {
			val = new java.util.HashSet<Integer>();
			val.addAll(_o_.val);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(val.size());
for (Integer _v_ : val) {
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
	val.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.IntSet copy() {
			return new Data(this);
		}

		@Override
		public xbean.IntSet toData() {
			return new Data(this);
		}

		public xbean.IntSet toBean() {
			return new IntSet(this, null, null);
		}

		@Override
		public xbean.IntSet toDataIf() {
			return this;
		}

		public xbean.IntSet toBeanIf() {
			return new IntSet(this, null, null);
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
		public java.util.Set<Integer> getVal() { // 
			return val;
		}

		@Override
		public java.util.Set<Integer> getValAsData() { // 
			return val;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof IntSet.Data)) return false;
			IntSet.Data _o_ = (IntSet.Data) _o1_;
			if (!val.equals(_o_.val)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += val.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(val);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
