
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class LongSet extends xdb.XBean implements xbean.LongSet {
	private xdb.util.SetX<Long> val; // 

	@Override
	public void _reset_unsafe_() {
		val.clear();
	}

	LongSet(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		val = new xdb.util.SetX<Long>();
	}

	public LongSet() {
		this(0, null, null);
	}

	public LongSet(LongSet _o_) {
		this(_o_, null, null);
	}

	LongSet(xbean.LongSet _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof LongSet) assign((LongSet)_o1_);
		else if (_o1_ instanceof LongSet.Data) assign((LongSet.Data)_o1_);
		else if (_o1_ instanceof LongSet.Const) assign(((LongSet.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(LongSet _o_) {
		_o_._xdb_verify_unsafe_();
		val = new xdb.util.SetX<Long>();
		val.addAll(_o_.val);
	}

	private void assign(LongSet.Data _o_) {
		val = new xdb.util.SetX<Long>();
		val.addAll(_o_.val);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
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
    				case (20480|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
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
	public xbean.LongSet copy() {
		_xdb_verify_unsafe_();
		return new LongSet(this);
	}

	@Override
	public xbean.LongSet toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.LongSet toBean() {
		_xdb_verify_unsafe_();
		return new LongSet(this); // same as copy()
	}

	@Override
	public xbean.LongSet toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.LongSet toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Set<Long> getVal() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "val"), val);
	}

	public java.util.Set<Long> getValAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Set<Long> val;
		LongSet _o_ = this;
		val = new xdb.util.SetX<Long>();
		val.addAll(_o_.val);
		return val;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		LongSet _o_ = null;
		if ( _o1_ instanceof LongSet ) _o_ = (LongSet)_o1_;
		else if ( _o1_ instanceof LongSet.Const ) _o_ = ((LongSet.Const)_o1_).nThis();
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

	private class Const implements xbean.LongSet {
		LongSet nThis() {
			return LongSet.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.LongSet copy() {
			return LongSet.this.copy();
		}

		@Override
		public xbean.LongSet toData() {
			return LongSet.this.toData();
		}

		public xbean.LongSet toBean() {
			return LongSet.this.toBean();
		}

		@Override
		public xbean.LongSet toDataIf() {
			return LongSet.this.toDataIf();
		}

		public xbean.LongSet toBeanIf() {
			return LongSet.this.toBeanIf();
		}

		@Override
		public java.util.Set<Long> getVal() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(val);
		}

		public java.util.Set<Long> getValAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Set<Long> val;
			LongSet _o_ = LongSet.this;
		val = new xdb.util.SetX<Long>();
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
			return LongSet.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return LongSet.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return LongSet.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return LongSet.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return LongSet.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return LongSet.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return LongSet.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return LongSet.this.hashCode();
		}

		@Override
		public String toString() {
			return LongSet.this.toString();
		}

	}

	public static final class Data implements xbean.LongSet {
		private java.util.HashSet<Long> val; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			val = new java.util.HashSet<Long>();
		}

		Data(xbean.LongSet _o1_) {
			if (_o1_ instanceof LongSet) assign((LongSet)_o1_);
			else if (_o1_ instanceof LongSet.Data) assign((LongSet.Data)_o1_);
			else if (_o1_ instanceof LongSet.Const) assign(((LongSet.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(LongSet _o_) {
			val = new java.util.HashSet<Long>();
			val.addAll(_o_.val);
		}

		private void assign(LongSet.Data _o_) {
			val = new java.util.HashSet<Long>();
			val.addAll(_o_.val);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
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
					case (20480|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
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
		public xbean.LongSet copy() {
			return new Data(this);
		}

		@Override
		public xbean.LongSet toData() {
			return new Data(this);
		}

		public xbean.LongSet toBean() {
			return new LongSet(this, null, null);
		}

		@Override
		public xbean.LongSet toDataIf() {
			return this;
		}

		public xbean.LongSet toBeanIf() {
			return new LongSet(this, null, null);
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
		public java.util.Set<Long> getVal() { // 
			return val;
		}

		@Override
		public java.util.Set<Long> getValAsData() { // 
			return val;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof LongSet.Data)) return false;
			LongSet.Data _o_ = (LongSet.Data) _o1_;
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
