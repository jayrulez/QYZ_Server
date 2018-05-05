
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleItemDepot extends xdb.XBean implements xbean.RoleItemDepot {
	private int capacity; // 包裹容量 
	private java.util.HashMap<Integer, xbean.Item> items; // 背包里的物品列表，key为包裹编号

	@Override
	public void _reset_unsafe_() {
		capacity = 0;
		items.clear();
	}

	RoleItemDepot(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		items = new java.util.HashMap<Integer, xbean.Item>();
	}

	public RoleItemDepot() {
		this(0, null, null);
	}

	public RoleItemDepot(RoleItemDepot _o_) {
		this(_o_, null, null);
	}

	RoleItemDepot(xbean.RoleItemDepot _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleItemDepot) assign((RoleItemDepot)_o1_);
		else if (_o1_ instanceof RoleItemDepot.Data) assign((RoleItemDepot.Data)_o1_);
		else if (_o1_ instanceof RoleItemDepot.Const) assign(((RoleItemDepot.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleItemDepot _o_) {
		_o_._xdb_verify_unsafe_();
		capacity = _o_.capacity;
		items = new java.util.HashMap<Integer, xbean.Item>();
		for (java.util.Map.Entry<Integer, xbean.Item> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), new Item(_e_.getValue(), this, "items"));
	}

	private void assign(RoleItemDepot.Data _o_) {
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
	public xbean.RoleItemDepot copy() {
		_xdb_verify_unsafe_();
		return new RoleItemDepot(this);
	}

	@Override
	public xbean.RoleItemDepot toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleItemDepot toBean() {
		_xdb_verify_unsafe_();
		return new RoleItemDepot(this); // same as copy()
	}

	@Override
	public xbean.RoleItemDepot toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleItemDepot toBeanIf() {
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
	public java.util.Map<Integer, xbean.Item> getItems() { // 背包里的物品列表，key为包裹编号
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "items"), items);
	}

	@Override
	public java.util.Map<Integer, xbean.Item> getItemsAsData() { // 背包里的物品列表，key为包裹编号
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Item> items;
		RoleItemDepot _o_ = this;
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
		RoleItemDepot _o_ = null;
		if ( _o1_ instanceof RoleItemDepot ) _o_ = (RoleItemDepot)_o1_;
		else if ( _o1_ instanceof RoleItemDepot.Const ) _o_ = ((RoleItemDepot.Const)_o1_).nThis();
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

	private class Const implements xbean.RoleItemDepot {
		RoleItemDepot nThis() {
			return RoleItemDepot.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleItemDepot copy() {
			return RoleItemDepot.this.copy();
		}

		@Override
		public xbean.RoleItemDepot toData() {
			return RoleItemDepot.this.toData();
		}

		public xbean.RoleItemDepot toBean() {
			return RoleItemDepot.this.toBean();
		}

		@Override
		public xbean.RoleItemDepot toDataIf() {
			return RoleItemDepot.this.toDataIf();
		}

		public xbean.RoleItemDepot toBeanIf() {
			return RoleItemDepot.this.toBeanIf();
		}

		@Override
		public int getCapacity() { // 包裹容量 
			_xdb_verify_unsafe_();
			return capacity;
		}

		@Override
		public java.util.Map<Integer, xbean.Item> getItems() { // 背包里的物品列表，key为包裹编号
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(items);
		}

		@Override
		public java.util.Map<Integer, xbean.Item> getItemsAsData() { // 背包里的物品列表，key为包裹编号
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Item> items;
			RoleItemDepot _o_ = RoleItemDepot.this;
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
			return RoleItemDepot.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleItemDepot.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleItemDepot.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleItemDepot.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleItemDepot.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleItemDepot.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleItemDepot.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleItemDepot.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleItemDepot.this.toString();
		}

	}

	public static final class Data implements xbean.RoleItemDepot {
		private int capacity; // 包裹容量 
		private java.util.HashMap<Integer, xbean.Item> items; // 背包里的物品列表，key为包裹编号

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			items = new java.util.HashMap<Integer, xbean.Item>();
		}

		Data(xbean.RoleItemDepot _o1_) {
			if (_o1_ instanceof RoleItemDepot) assign((RoleItemDepot)_o1_);
			else if (_o1_ instanceof RoleItemDepot.Data) assign((RoleItemDepot.Data)_o1_);
			else if (_o1_ instanceof RoleItemDepot.Const) assign(((RoleItemDepot.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleItemDepot _o_) {
			capacity = _o_.capacity;
			items = new java.util.HashMap<Integer, xbean.Item>();
			for (java.util.Map.Entry<Integer, xbean.Item> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), new Item.Data(_e_.getValue()));
		}

		private void assign(RoleItemDepot.Data _o_) {
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
		public xbean.RoleItemDepot copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleItemDepot toData() {
			return new Data(this);
		}

		public xbean.RoleItemDepot toBean() {
			return new RoleItemDepot(this, null, null);
		}

		@Override
		public xbean.RoleItemDepot toDataIf() {
			return this;
		}

		public xbean.RoleItemDepot toBeanIf() {
			return new RoleItemDepot(this, null, null);
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
		public java.util.Map<Integer, xbean.Item> getItems() { // 背包里的物品列表，key为包裹编号
			return items;
		}

		@Override
		public java.util.Map<Integer, xbean.Item> getItemsAsData() { // 背包里的物品列表，key为包裹编号
			return items;
		}

		@Override
		public void setCapacity(int _v_) { // 包裹容量 
			capacity = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleItemDepot.Data)) return false;
			RoleItemDepot.Data _o_ = (RoleItemDepot.Data) _o1_;
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
