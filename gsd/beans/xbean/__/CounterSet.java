
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class CounterSet extends xdb.XBean implements xbean.CounterSet {
	private xdb.util.SetX<Long> items; // 

	@Override
	public void _reset_unsafe_() {
		items.clear();
	}

	CounterSet(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		items = new xdb.util.SetX<Long>();
	}

	public CounterSet() {
		this(0, null, null);
	}

	public CounterSet(CounterSet _o_) {
		this(_o_, null, null);
	}

	CounterSet(xbean.CounterSet _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof CounterSet) assign((CounterSet)_o1_);
		else if (_o1_ instanceof CounterSet.Data) assign((CounterSet.Data)_o1_);
		else if (_o1_ instanceof CounterSet.Const) assign(((CounterSet.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(CounterSet _o_) {
		_o_._xdb_verify_unsafe_();
		items = new xdb.util.SetX<Long>();
		items.addAll(_o_.items);
	}

	private void assign(CounterSet.Data _o_) {
		items = new xdb.util.SetX<Long>();
		items.addAll(_o_.items);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(20480|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (Long _v_ : items) {
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
    				case (20480|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	items.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.CounterSet copy() {
		_xdb_verify_unsafe_();
		return new CounterSet(this);
	}

	@Override
	public xbean.CounterSet toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.CounterSet toBean() {
		_xdb_verify_unsafe_();
		return new CounterSet(this); // same as copy()
	}

	@Override
	public xbean.CounterSet toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.CounterSet toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Set<Long> getItems() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "items"), items);
	}

	public java.util.Set<Long> getItemsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Set<Long> items;
		CounterSet _o_ = this;
		items = new xdb.util.SetX<Long>();
		items.addAll(_o_.items);
		return items;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		CounterSet _o_ = null;
		if ( _o1_ instanceof CounterSet ) _o_ = (CounterSet)_o1_;
		else if ( _o1_ instanceof CounterSet.Const ) _o_ = ((CounterSet.Const)_o1_).nThis();
		else return false;
		if (!items.equals(_o_.items)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += items.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(items);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableSet().setVarName("items"));
		return lb;
	}

	private class Const implements xbean.CounterSet {
		CounterSet nThis() {
			return CounterSet.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.CounterSet copy() {
			return CounterSet.this.copy();
		}

		@Override
		public xbean.CounterSet toData() {
			return CounterSet.this.toData();
		}

		public xbean.CounterSet toBean() {
			return CounterSet.this.toBean();
		}

		@Override
		public xbean.CounterSet toDataIf() {
			return CounterSet.this.toDataIf();
		}

		public xbean.CounterSet toBeanIf() {
			return CounterSet.this.toBeanIf();
		}

		@Override
		public java.util.Set<Long> getItems() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(items);
		}

		public java.util.Set<Long> getItemsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Set<Long> items;
			CounterSet _o_ = CounterSet.this;
		items = new xdb.util.SetX<Long>();
		items.addAll(_o_.items);
			return items;
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
			return CounterSet.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return CounterSet.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return CounterSet.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return CounterSet.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return CounterSet.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return CounterSet.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return CounterSet.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return CounterSet.this.hashCode();
		}

		@Override
		public String toString() {
			return CounterSet.this.toString();
		}

	}

	public static final class Data implements xbean.CounterSet {
		private java.util.HashSet<Long> items; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			items = new java.util.HashSet<Long>();
		}

		Data(xbean.CounterSet _o1_) {
			if (_o1_ instanceof CounterSet) assign((CounterSet)_o1_);
			else if (_o1_ instanceof CounterSet.Data) assign((CounterSet.Data)_o1_);
			else if (_o1_ instanceof CounterSet.Const) assign(((CounterSet.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(CounterSet _o_) {
			items = new java.util.HashSet<Long>();
			items.addAll(_o_.items);
		}

		private void assign(CounterSet.Data _o_) {
			items = new java.util.HashSet<Long>();
			items.addAll(_o_.items);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(20480|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (Long _v_ : items) {
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
					case (20480|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	items.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.CounterSet copy() {
			return new Data(this);
		}

		@Override
		public xbean.CounterSet toData() {
			return new Data(this);
		}

		public xbean.CounterSet toBean() {
			return new CounterSet(this, null, null);
		}

		@Override
		public xbean.CounterSet toDataIf() {
			return this;
		}

		public xbean.CounterSet toBeanIf() {
			return new CounterSet(this, null, null);
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
		public java.util.Set<Long> getItems() { // 
			return items;
		}

		@Override
		public java.util.Set<Long> getItemsAsData() { // 
			return items;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof CounterSet.Data)) return false;
			CounterSet.Data _o_ = (CounterSet.Data) _o1_;
			if (!items.equals(_o_.items)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += items.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(items);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
