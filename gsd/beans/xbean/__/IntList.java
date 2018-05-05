
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class IntList extends xdb.XBean implements xbean.IntList {
	private java.util.LinkedList<Integer> val; // 

	@Override
	public void _reset_unsafe_() {
		val.clear();
	}

	IntList(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		val = new java.util.LinkedList<Integer>();
	}

	public IntList() {
		this(0, null, null);
	}

	public IntList(IntList _o_) {
		this(_o_, null, null);
	}

	IntList(xbean.IntList _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof IntList) assign((IntList)_o1_);
		else if (_o1_ instanceof IntList.Data) assign((IntList.Data)_o1_);
		else if (_o1_ instanceof IntList.Const) assign(((IntList.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(IntList _o_) {
		_o_._xdb_verify_unsafe_();
		val = new java.util.LinkedList<Integer>();
		val.addAll(_o_.val);
	}

	private void assign(IntList.Data _o_) {
		val = new java.util.LinkedList<Integer>();
		val.addAll(_o_.val);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
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
    				case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
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
	public xbean.IntList copy() {
		_xdb_verify_unsafe_();
		return new IntList(this);
	}

	@Override
	public xbean.IntList toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IntList toBean() {
		_xdb_verify_unsafe_();
		return new IntList(this); // same as copy()
	}

	@Override
	public xbean.IntList toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IntList toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<Integer> getVal() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "val"), val);
	}

	public java.util.List<Integer> getValAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Integer> val;
		IntList _o_ = this;
		val = new java.util.LinkedList<Integer>();
		val.addAll(_o_.val);
		return val;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		IntList _o_ = null;
		if ( _o1_ instanceof IntList ) _o_ = (IntList)_o1_;
		else if ( _o1_ instanceof IntList.Const ) _o_ = ((IntList.Const)_o1_).nThis();
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
		lb.add(new xdb.logs.ListenableChanged().setVarName("val"));
		return lb;
	}

	private class Const implements xbean.IntList {
		IntList nThis() {
			return IntList.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.IntList copy() {
			return IntList.this.copy();
		}

		@Override
		public xbean.IntList toData() {
			return IntList.this.toData();
		}

		public xbean.IntList toBean() {
			return IntList.this.toBean();
		}

		@Override
		public xbean.IntList toDataIf() {
			return IntList.this.toDataIf();
		}

		public xbean.IntList toBeanIf() {
			return IntList.this.toBeanIf();
		}

		@Override
		public java.util.List<Integer> getVal() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(val);
		}

		public java.util.List<Integer> getValAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Integer> val;
			IntList _o_ = IntList.this;
		val = new java.util.LinkedList<Integer>();
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
			return IntList.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return IntList.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return IntList.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return IntList.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return IntList.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return IntList.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return IntList.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return IntList.this.hashCode();
		}

		@Override
		public String toString() {
			return IntList.this.toString();
		}

	}

	public static final class Data implements xbean.IntList {
		private java.util.LinkedList<Integer> val; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			val = new java.util.LinkedList<Integer>();
		}

		Data(xbean.IntList _o1_) {
			if (_o1_ instanceof IntList) assign((IntList)_o1_);
			else if (_o1_ instanceof IntList.Data) assign((IntList.Data)_o1_);
			else if (_o1_ instanceof IntList.Const) assign(((IntList.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(IntList _o_) {
			val = new java.util.LinkedList<Integer>();
			val.addAll(_o_.val);
		}

		private void assign(IntList.Data _o_) {
			val = new java.util.LinkedList<Integer>();
			val.addAll(_o_.val);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
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
					case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
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
		public xbean.IntList copy() {
			return new Data(this);
		}

		@Override
		public xbean.IntList toData() {
			return new Data(this);
		}

		public xbean.IntList toBean() {
			return new IntList(this, null, null);
		}

		@Override
		public xbean.IntList toDataIf() {
			return this;
		}

		public xbean.IntList toBeanIf() {
			return new IntList(this, null, null);
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
		public java.util.List<Integer> getVal() { // 
			return val;
		}

		@Override
		public java.util.List<Integer> getValAsData() { // 
			return val;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof IntList.Data)) return false;
			IntList.Data _o_ = (IntList.Data) _o1_;
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
