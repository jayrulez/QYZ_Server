
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleItemBag extends xdb.XBean implements xbean.RoleItemBag {
	private int capacity; // 包裹容量 
	private java.util.HashMap<Integer, xbean.Item> items; // 背包里的物品列表，key为格子index

	@Override
	public void _reset_unsafe_() {
		capacity = 0;
		items.clear();
	}

	RoleItemBag(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		items = new java.util.HashMap<Integer, xbean.Item>();
	}

	public RoleItemBag() {
		this(0, null, null);
	}

	public RoleItemBag(RoleItemBag _o_) {
		this(_o_, null, null);
	}

	RoleItemBag(xbean.RoleItemBag _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleItemBag) assign((RoleItemBag)_o1_);
		else if (_o1_ instanceof RoleItemBag.Data) assign((RoleItemBag.Data)_o1_);
		else if (_o1_ instanceof RoleItemBag.Const) assign(((RoleItemBag.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleItemBag _o_) {
		_o_._xdb_verify_unsafe_();
		capacity = _o_.capacity;
		items = new java.util.HashMap<Integer, xbean.Item>();
		for (java.util.Map.Entry<Integer, xbean.Item> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), new Item(_e_.getValue(), this, "items"));
	}

	private void assign(RoleItemBag.Data _o_) {
		capacity = _o_.capacity;
		items = new java.util.HashMap<Integer, xbean.Item>();
		for (java.util.Map.Entry<Integer, xbean.Item> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), new Item(_e_.getValue(), this, "items"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (java.util.Map.Entry<Integer, xbean.Item> _e_ : items.entrySet())
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
    				case ( 8192|  1):capacity = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):capacity = _os_.unmarshal_short();
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		items = new java.util.HashMap<Integer, xbean.Item>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Item _v_ = new Item(0, this, "items");
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
	public xbean.RoleItemBag copy() {
		_xdb_verify_unsafe_();
		return new RoleItemBag(this);
	}

	@Override
	public xbean.RoleItemBag toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleItemBag toBean() {
		_xdb_verify_unsafe_();
		return new RoleItemBag(this); // same as copy()
	}

	@Override
	public xbean.RoleItemBag toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleItemBag toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getCapacity() { // 包裹容量 
		_xdb_verify_unsafe_();
		return capacity;
	}

	@Override
	public java.util.Map<Integer, xbean.Item> getItems() { // 背包里的物品列表，key为格子index
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "items"), items);
	}

	@Override
	public java.util.Map<Integer, xbean.Item> getItemsAsData() { // 背包里的物品列表，key为格子index
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Item> items;
		RoleItemBag _o_ = this;
		items = new java.util.HashMap<Integer, xbean.Item>();
		for (java.util.Map.Entry<Integer, xbean.Item> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), new Item.Data(_e_.getValue()));
		return items;
	}

	@Override
	public void setCapacity(int _v_) { // 包裹容量 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "capacity") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, capacity) {
					public void rollback() { capacity = _xdb_saved; }
				};}});
		capacity = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleItemBag _o_ = null;
		if ( _o1_ instanceof RoleItemBag ) _o_ = (RoleItemBag)_o1_;
		else if ( _o1_ instanceof RoleItemBag.Const ) _o_ = ((RoleItemBag.Const)_o1_).nThis();
		else return false;
		if (capacity != _o_.capacity) return false;
		if (!items.equals(_o_.items)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += capacity;
		_h_ += items.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(capacity);
		_sb_.append(",");
		_sb_.append(items);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("capacity"));
		lb.add(new xdb.logs.ListenableMap().setVarName("items"));
		return lb;
	}

	private class Const implements xbean.RoleItemBag {
		RoleItemBag nThis() {
			return RoleItemBag.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleItemBag copy() {
			return RoleItemBag.this.copy();
		}

		@Override
		public xbean.RoleItemBag toData() {
			return RoleItemBag.this.toData();
		}

		public xbean.RoleItemBag toBean() {
			return RoleItemBag.this.toBean();
		}

		@Override
		public xbean.RoleItemBag toDataIf() {
			return RoleItemBag.this.toDataIf();
		}

		public xbean.RoleItemBag toBeanIf() {
			return RoleItemBag.this.toBeanIf();
		}

		@Override
		public int getCapacity() { // 包裹容量 
			_xdb_verify_unsafe_();
			return capacity;
		}

		@Override
		public java.util.Map<Integer, xbean.Item> getItems() { // 背包里的物品列表，key为格子index
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(items);
		}

		@Override
		public java.util.Map<Integer, xbean.Item> getItemsAsData() { // 背包里的物品列表，key为格子index
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Item> items;
			RoleItemBag _o_ = RoleItemBag.this;
			items = new java.util.HashMap<Integer, xbean.Item>();
			for (java.util.Map.Entry<Integer, xbean.Item> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), new Item.Data(_e_.getValue()));
			return items;
		}

		@Override
		public void setCapacity(int _v_) { // 包裹容量 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
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
			return RoleItemBag.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleItemBag.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleItemBag.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleItemBag.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleItemBag.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleItemBag.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleItemBag.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleItemBag.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleItemBag.this.toString();
		}

	}

	public static final class Data implements xbean.RoleItemBag {
		private int capacity; // 包裹容量 
		private java.util.HashMap<Integer, xbean.Item> items; // 背包里的物品列表，key为格子index

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			items = new java.util.HashMap<Integer, xbean.Item>();
		}

		Data(xbean.RoleItemBag _o1_) {
			if (_o1_ instanceof RoleItemBag) assign((RoleItemBag)_o1_);
			else if (_o1_ instanceof RoleItemBag.Data) assign((RoleItemBag.Data)_o1_);
			else if (_o1_ instanceof RoleItemBag.Const) assign(((RoleItemBag.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleItemBag _o_) {
			capacity = _o_.capacity;
			items = new java.util.HashMap<Integer, xbean.Item>();
			for (java.util.Map.Entry<Integer, xbean.Item> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), new Item.Data(_e_.getValue()));
		}

		private void assign(RoleItemBag.Data _o_) {
			capacity = _o_.capacity;
			items = new java.util.HashMap<Integer, xbean.Item>();
			for (java.util.Map.Entry<Integer, xbean.Item> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), new Item.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (java.util.Map.Entry<Integer, xbean.Item> _e_ : items.entrySet())
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
					case ( 8192|  1):capacity = _os_.unmarshal_int();
					break;
					case ( 6144|  1):capacity = _os_.unmarshal_short();
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		items = new java.util.HashMap<Integer, xbean.Item>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Item _v_ = xbean.Pod.newItemData();
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
		public xbean.RoleItemBag copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleItemBag toData() {
			return new Data(this);
		}

		public xbean.RoleItemBag toBean() {
			return new RoleItemBag(this, null, null);
		}

		@Override
		public xbean.RoleItemBag toDataIf() {
			return this;
		}

		public xbean.RoleItemBag toBeanIf() {
			return new RoleItemBag(this, null, null);
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
		public int getCapacity() { // 包裹容量 
			return capacity;
		}

		@Override
		public java.util.Map<Integer, xbean.Item> getItems() { // 背包里的物品列表，key为格子index
			return items;
		}

		@Override
		public java.util.Map<Integer, xbean.Item> getItemsAsData() { // 背包里的物品列表，key为格子index
			return items;
		}

		@Override
		public void setCapacity(int _v_) { // 包裹容量 
			capacity = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleItemBag.Data)) return false;
			RoleItemBag.Data _o_ = (RoleItemBag.Data) _o1_;
			if (capacity != _o_.capacity) return false;
			if (!items.equals(_o_.items)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += capacity;
			_h_ += items.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(capacity);
			_sb_.append(",");
			_sb_.append(items);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
