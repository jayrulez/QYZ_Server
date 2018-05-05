
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class LongList extends xdb.XBean implements xbean.LongList {
	private java.util.LinkedList<Long> val; // 

	@Override
	public void _reset_unsafe_() {
		val.clear();
	}

	LongList(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		val = new java.util.LinkedList<Long>();
	}

	public LongList() {
		this(0, null, null);
	}

	public LongList(LongList _o_) {
		this(_o_, null, null);
	}

	LongList(xbean.LongList _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof LongList) assign((LongList)_o1_);
		else if (_o1_ instanceof LongList.Data) assign((LongList.Data)_o1_);
		else if (_o1_ instanceof LongList.Const) assign(((LongList.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(LongList _o_) {
		_o_._xdb_verify_unsafe_();
		val = new java.util.LinkedList<Long>();
		val.addAll(_o_.val);
	}

	private void assign(LongList.Data _o_) {
		val = new java.util.LinkedList<Long>();
		val.addAll(_o_.val);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(val.size());
for (Long _v_ : val) {
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
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
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
	public xbean.LongList copy() {
		_xdb_verify_unsafe_();
		return new LongList(this);
	}

	@Override
	public xbean.LongList toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.LongList toBean() {
		_xdb_verify_unsafe_();
		return new LongList(this); // same as copy()
	}

	@Override
	public xbean.LongList toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.LongList toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<Long> getVal() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "val"), val);
	}

	public java.util.List<Long> getValAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Long> val;
		LongList _o_ = this;
		val = new java.util.LinkedList<Long>();
		val.addAll(_o_.val);
		return val;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		LongList _o_ = null;
		if ( _o1_ instanceof LongList ) _o_ = (LongList)_o1_;
		else if ( _o1_ instanceof LongList.Const ) _o_ = ((LongList.Const)_o1_).nThis();
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

	private class Const implements xbean.LongList {
		LongList nThis() {
			return LongList.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.LongList copy() {
			return LongList.this.copy();
		}

		@Override
		public xbean.LongList toData() {
			return LongList.this.toData();
		}

		public xbean.LongList toBean() {
			return LongList.this.toBean();
		}

		@Override
		public xbean.LongList toDataIf() {
			return LongList.this.toDataIf();
		}

		public xbean.LongList toBeanIf() {
			return LongList.this.toBeanIf();
		}

		@Override
		public java.util.List<Long> getVal() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(val);
		}

		public java.util.List<Long> getValAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Long> val;
			LongList _o_ = LongList.this;
		val = new java.util.LinkedList<Long>();
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
			return LongList.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return LongList.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return LongList.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return LongList.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return LongList.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return LongList.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return LongList.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return LongList.this.hashCode();
		}

		@Override
		public String toString() {
			return LongList.this.toString();
		}

	}

	public static final class Data implements xbean.LongList {
		private java.util.LinkedList<Long> val; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			val = new java.util.LinkedList<Long>();
		}

		Data(xbean.LongList _o1_) {
			if (_o1_ instanceof LongList) assign((LongList)_o1_);
			else if (_o1_ instanceof LongList.Data) assign((LongList.Data)_o1_);
			else if (_o1_ instanceof LongList.Const) assign(((LongList.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(LongList _o_) {
			val = new java.util.LinkedList<Long>();
			val.addAll(_o_.val);
		}

		private void assign(LongList.Data _o_) {
			val = new java.util.LinkedList<Long>();
			val.addAll(_o_.val);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(val.size());
for (Long _v_ : val) {
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
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
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
		public xbean.LongList copy() {
			return new Data(this);
		}

		@Override
		public xbean.LongList toData() {
			return new Data(this);
		}

		public xbean.LongList toBean() {
			return new LongList(this, null, null);
		}

		@Override
		public xbean.LongList toDataIf() {
			return this;
		}

		public xbean.LongList toBeanIf() {
			return new LongList(this, null, null);
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
		public java.util.List<Long> getVal() { // 
			return val;
		}

		@Override
		public java.util.List<Long> getValAsData() { // 
			return val;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof LongList.Data)) return false;
			LongList.Data _o_ = (LongList.Data) _o1_;
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
