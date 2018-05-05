
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleEquipDepot extends xdb.XBean implements xbean.RoleEquipDepot {
	private int capacity; // 包裹容量 
	private java.util.TreeMap<Integer, xbean.Equip> equipbag; // 装备包裹，key为物品的包裹编号

	@Override
	public void _reset_unsafe_() {
		capacity = 0;
		equipbag.clear();
	}

	RoleEquipDepot(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		equipbag = new java.util.TreeMap<Integer, xbean.Equip>();
	}

	public RoleEquipDepot() {
		this(0, null, null);
	}

	public RoleEquipDepot(RoleEquipDepot _o_) {
		this(_o_, null, null);
	}

	RoleEquipDepot(xbean.RoleEquipDepot _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleEquipDepot) assign((RoleEquipDepot)_o1_);
		else if (_o1_ instanceof RoleEquipDepot.Data) assign((RoleEquipDepot.Data)_o1_);
		else if (_o1_ instanceof RoleEquipDepot.Const) assign(((RoleEquipDepot.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleEquipDepot _o_) {
		_o_._xdb_verify_unsafe_();
		capacity = _o_.capacity;
		equipbag = new java.util.TreeMap<Integer, xbean.Equip>();
		for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipbag.entrySet())
			equipbag.put(_e_.getKey(), new Equip(_e_.getValue(), this, "equipbag"));
	}

	private void assign(RoleEquipDepot.Data _o_) {
		capacity = _o_.capacity;
		equipbag = new java.util.TreeMap<Integer, xbean.Equip>();
		for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipbag.entrySet())
			equipbag.put(_e_.getKey(), new Equip(_e_.getValue(), this, "equipbag"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equipbag.size());
for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : equipbag.entrySet())
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
for (int size = _os_.uncompact_uint32(); size > 0; --size)
{
	int _k_ = 0;
	_k_ = _os_.unmarshal_int();
	xbean.Equip _v_ = new Equip(0, this, "equipbag");
	_v_.unmarshal(_os_);
	equipbag.put(_k_, _v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleEquipDepot copy() {
		_xdb_verify_unsafe_();
		return new RoleEquipDepot(this);
	}

	@Override
	public xbean.RoleEquipDepot toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleEquipDepot toBean() {
		_xdb_verify_unsafe_();
		return new RoleEquipDepot(this); // same as copy()
	}

	@Override
	public xbean.RoleEquipDepot toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleEquipDepot toBeanIf() {
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
	public java.util.NavigableMap<Integer, xbean.Equip> getEquipbag() { // 装备包裹，key为物品的包裹编号
		_xdb_verify_unsafe_();
		return xdb.Logs.logNavigableMap(new xdb.LogKey(this, "equipbag"), equipbag);
	}

	public java.util.NavigableMap<Integer, xbean.Equip> getEquipbagAsData() { // 装备包裹，key为物品的包裹编号
		_xdb_verify_unsafe_();
		java.util.NavigableMap<Integer, xbean.Equip> equipbag;
		RoleEquipDepot _o_ = this;
		equipbag = new java.util.TreeMap<Integer, xbean.Equip>();
		for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipbag.entrySet())
			equipbag.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
		return equipbag;
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
		RoleEquipDepot _o_ = null;
		if ( _o1_ instanceof RoleEquipDepot ) _o_ = (RoleEquipDepot)_o1_;
		else if ( _o1_ instanceof RoleEquipDepot.Const ) _o_ = ((RoleEquipDepot.Const)_o1_).nThis();
		else return false;
		if (capacity != _o_.capacity) return false;
		if (!equipbag.equals(_o_.equipbag)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += capacity;
		_h_ += equipbag.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(capacity);
		_sb_.append(",");
		_sb_.append(equipbag);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("capacity"));
		lb.add(new xdb.logs.ListenableMap().setVarName("equipbag"));
		return lb;
	}

	private class Const implements xbean.RoleEquipDepot {
		RoleEquipDepot nThis() {
			return RoleEquipDepot.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleEquipDepot copy() {
			return RoleEquipDepot.this.copy();
		}

		@Override
		public xbean.RoleEquipDepot toData() {
			return RoleEquipDepot.this.toData();
		}

		public xbean.RoleEquipDepot toBean() {
			return RoleEquipDepot.this.toBean();
		}

		@Override
		public xbean.RoleEquipDepot toDataIf() {
			return RoleEquipDepot.this.toDataIf();
		}

		public xbean.RoleEquipDepot toBeanIf() {
			return RoleEquipDepot.this.toBeanIf();
		}

		@Override
		public int getCapacity() { // 包裹容量 
			_xdb_verify_unsafe_();
			return capacity;
		}

		@Override
		public java.util.NavigableMap<Integer, xbean.Equip> getEquipbag() { // 装备包裹，key为物品的包裹编号
			_xdb_verify_unsafe_();
			return xdb.Consts.constNavigableMap(equipbag);
		}

		@Override
		public java.util.NavigableMap<Integer, xbean.Equip> getEquipbagAsData() { // 装备包裹，key为物品的包裹编号
			_xdb_verify_unsafe_();
			java.util.NavigableMap<Integer, xbean.Equip> equipbag;
			RoleEquipDepot _o_ = RoleEquipDepot.this;
			equipbag = new java.util.TreeMap<Integer, xbean.Equip>();
			for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipbag.entrySet())
				equipbag.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
			return equipbag;
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
			return RoleEquipDepot.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleEquipDepot.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleEquipDepot.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleEquipDepot.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleEquipDepot.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleEquipDepot.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleEquipDepot.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleEquipDepot.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleEquipDepot.this.toString();
		}

	}

	public static final class Data implements xbean.RoleEquipDepot {
		private int capacity; // 包裹容量 
		private java.util.TreeMap<Integer, xbean.Equip> equipbag; // 装备包裹，key为物品的包裹编号

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			equipbag = new java.util.TreeMap<Integer, xbean.Equip>();
		}

		Data(xbean.RoleEquipDepot _o1_) {
			if (_o1_ instanceof RoleEquipDepot) assign((RoleEquipDepot)_o1_);
			else if (_o1_ instanceof RoleEquipDepot.Data) assign((RoleEquipDepot.Data)_o1_);
			else if (_o1_ instanceof RoleEquipDepot.Const) assign(((RoleEquipDepot.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleEquipDepot _o_) {
			capacity = _o_.capacity;
			equipbag = new java.util.TreeMap<Integer, xbean.Equip>();
			for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipbag.entrySet())
				equipbag.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
		}

		private void assign(RoleEquipDepot.Data _o_) {
			capacity = _o_.capacity;
			equipbag = new java.util.TreeMap<Integer, xbean.Equip>();
			for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipbag.entrySet())
				equipbag.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equipbag.size());
for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : equipbag.entrySet())
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
for (int size = _os_.uncompact_uint32(); size > 0; --size)
{
	int _k_ = 0;
	_k_ = _os_.unmarshal_int();
	xbean.Equip _v_ = xbean.Pod.newEquipData();
	_v_.unmarshal(_os_);
	equipbag.put(_k_, _v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleEquipDepot copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleEquipDepot toData() {
			return new Data(this);
		}

		public xbean.RoleEquipDepot toBean() {
			return new RoleEquipDepot(this, null, null);
		}

		@Override
		public xbean.RoleEquipDepot toDataIf() {
			return this;
		}

		public xbean.RoleEquipDepot toBeanIf() {
			return new RoleEquipDepot(this, null, null);
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
		public java.util.NavigableMap<Integer, xbean.Equip> getEquipbag() { // 装备包裹，key为物品的包裹编号
			return equipbag;
		}

		@Override
		public java.util.NavigableMap<Integer, xbean.Equip> getEquipbagAsData() { // 装备包裹，key为物品的包裹编号
			return equipbag;
		}

		@Override
		public void setCapacity(int _v_) { // 包裹容量 
			capacity = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleEquipDepot.Data)) return false;
			RoleEquipDepot.Data _o_ = (RoleEquipDepot.Data) _o1_;
			if (capacity != _o_.capacity) return false;
			if (!equipbag.equals(_o_.equipbag)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += capacity;
			_h_ += equipbag.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(capacity);
			_sb_.append(",");
			_sb_.append(equipbag);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
