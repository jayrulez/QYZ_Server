
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Forbid extends xdb.XBean implements xbean.Forbid {
	private java.util.HashMap<Integer, xbean.ForbidItem> items; // 

	@Override
	public void _reset_unsafe_() {
		items.clear();
	}

	Forbid(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		items = new java.util.HashMap<Integer, xbean.ForbidItem>();
	}

	public Forbid() {
		this(0, null, null);
	}

	public Forbid(Forbid _o_) {
		this(_o_, null, null);
	}

	Forbid(xbean.Forbid _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Forbid) assign((Forbid)_o1_);
		else if (_o1_ instanceof Forbid.Data) assign((Forbid.Data)_o1_);
		else if (_o1_ instanceof Forbid.Const) assign(((Forbid.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Forbid _o_) {
		_o_._xdb_verify_unsafe_();
		items = new java.util.HashMap<Integer, xbean.ForbidItem>();
		for (java.util.Map.Entry<Integer, xbean.ForbidItem> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), new ForbidItem(_e_.getValue(), this, "items"));
	}

	private void assign(Forbid.Data _o_) {
		items = new java.util.HashMap<Integer, xbean.ForbidItem>();
		for (java.util.Map.Entry<Integer, xbean.ForbidItem> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), new ForbidItem(_e_.getValue(), this, "items"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (java.util.Map.Entry<Integer, xbean.ForbidItem> _e_ : items.entrySet())
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
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		items = new java.util.HashMap<Integer, xbean.ForbidItem>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.ForbidItem _v_ = new ForbidItem(0, this, "items");
		_v_.unmarshal(_os_);
		items.put(_k_, _v_);
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
	public xbean.Forbid copy() {
		_xdb_verify_unsafe_();
		return new Forbid(this);
	}

	@Override
	public xbean.Forbid toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Forbid toBean() {
		_xdb_verify_unsafe_();
		return new Forbid(this); // same as copy()
	}

	@Override
	public xbean.Forbid toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Forbid toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.ForbidItem> getItems() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "items"), items);
	}

	@Override
	public java.util.Map<Integer, xbean.ForbidItem> getItemsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.ForbidItem> items;
		Forbid _o_ = this;
		items = new java.util.HashMap<Integer, xbean.ForbidItem>();
		for (java.util.Map.Entry<Integer, xbean.ForbidItem> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), new ForbidItem.Data(_e_.getValue()));
		return items;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Forbid _o_ = null;
		if ( _o1_ instanceof Forbid ) _o_ = (Forbid)_o1_;
		else if ( _o1_ instanceof Forbid.Const ) _o_ = ((Forbid.Const)_o1_).nThis();
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
		lb.add(new xdb.logs.ListenableMap().setVarName("items"));
		return lb;
	}

	private class Const implements xbean.Forbid {
		Forbid nThis() {
			return Forbid.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Forbid copy() {
			return Forbid.this.copy();
		}

		@Override
		public xbean.Forbid toData() {
			return Forbid.this.toData();
		}

		public xbean.Forbid toBean() {
			return Forbid.this.toBean();
		}

		@Override
		public xbean.Forbid toDataIf() {
			return Forbid.this.toDataIf();
		}

		public xbean.Forbid toBeanIf() {
			return Forbid.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.ForbidItem> getItems() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(items);
		}

		@Override
		public java.util.Map<Integer, xbean.ForbidItem> getItemsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.ForbidItem> items;
			Forbid _o_ = Forbid.this;
			items = new java.util.HashMap<Integer, xbean.ForbidItem>();
			for (java.util.Map.Entry<Integer, xbean.ForbidItem> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), new ForbidItem.Data(_e_.getValue()));
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
			return Forbid.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Forbid.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Forbid.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Forbid.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Forbid.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Forbid.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Forbid.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Forbid.this.hashCode();
		}

		@Override
		public String toString() {
			return Forbid.this.toString();
		}

	}

	public static final class Data implements xbean.Forbid {
		private java.util.HashMap<Integer, xbean.ForbidItem> items; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			items = new java.util.HashMap<Integer, xbean.ForbidItem>();
		}

		Data(xbean.Forbid _o1_) {
			if (_o1_ instanceof Forbid) assign((Forbid)_o1_);
			else if (_o1_ instanceof Forbid.Data) assign((Forbid.Data)_o1_);
			else if (_o1_ instanceof Forbid.Const) assign(((Forbid.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Forbid _o_) {
			items = new java.util.HashMap<Integer, xbean.ForbidItem>();
			for (java.util.Map.Entry<Integer, xbean.ForbidItem> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), new ForbidItem.Data(_e_.getValue()));
		}

		private void assign(Forbid.Data _o_) {
			items = new java.util.HashMap<Integer, xbean.ForbidItem>();
			for (java.util.Map.Entry<Integer, xbean.ForbidItem> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), new ForbidItem.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (java.util.Map.Entry<Integer, xbean.ForbidItem> _e_ : items.entrySet())
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
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		items = new java.util.HashMap<Integer, xbean.ForbidItem>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.ForbidItem _v_ = xbean.Pod.newForbidItemData();
		_v_.unmarshal(_os_);
		items.put(_k_, _v_);
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
		public xbean.Forbid copy() {
			return new Data(this);
		}

		@Override
		public xbean.Forbid toData() {
			return new Data(this);
		}

		public xbean.Forbid toBean() {
			return new Forbid(this, null, null);
		}

		@Override
		public xbean.Forbid toDataIf() {
			return this;
		}

		public xbean.Forbid toBeanIf() {
			return new Forbid(this, null, null);
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
		public java.util.Map<Integer, xbean.ForbidItem> getItems() { // 
			return items;
		}

		@Override
		public java.util.Map<Integer, xbean.ForbidItem> getItemsAsData() { // 
			return items;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Forbid.Data)) return false;
			Forbid.Data _o_ = (Forbid.Data) _o1_;
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
