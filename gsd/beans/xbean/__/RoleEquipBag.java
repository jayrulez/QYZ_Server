
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleEquipBag extends xdb.XBean implements xbean.RoleEquipBag {
	private int capacity; // 包裹容量 
	private java.util.HashMap<Integer, xbean.Equip> equipmap; // 装备包裹，key为格子index
	private java.util.HashMap<Integer, xbean.Equip> equiponbodymap; // 装备包裹，key为格子index

	@Override
	public void _reset_unsafe_() {
		capacity = 0;
		equipmap.clear();
		equiponbodymap.clear();
	}

	RoleEquipBag(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		equipmap = new java.util.HashMap<Integer, xbean.Equip>();
		equiponbodymap = new java.util.HashMap<Integer, xbean.Equip>();
	}

	public RoleEquipBag() {
		this(0, null, null);
	}

	public RoleEquipBag(RoleEquipBag _o_) {
		this(_o_, null, null);
	}

	RoleEquipBag(xbean.RoleEquipBag _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleEquipBag) assign((RoleEquipBag)_o1_);
		else if (_o1_ instanceof RoleEquipBag.Data) assign((RoleEquipBag.Data)_o1_);
		else if (_o1_ instanceof RoleEquipBag.Const) assign(((RoleEquipBag.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleEquipBag _o_) {
		_o_._xdb_verify_unsafe_();
		capacity = _o_.capacity;
		equipmap = new java.util.HashMap<Integer, xbean.Equip>();
		for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipmap.entrySet())
			equipmap.put(_e_.getKey(), new Equip(_e_.getValue(), this, "equipmap"));
		equiponbodymap = new java.util.HashMap<Integer, xbean.Equip>();
		for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equiponbodymap.entrySet())
			equiponbodymap.put(_e_.getKey(), new Equip(_e_.getValue(), this, "equiponbodymap"));
	}

	private void assign(RoleEquipBag.Data _o_) {
		capacity = _o_.capacity;
		equipmap = new java.util.HashMap<Integer, xbean.Equip>();
		for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipmap.entrySet())
			equipmap.put(_e_.getKey(), new Equip(_e_.getValue(), this, "equipmap"));
		equiponbodymap = new java.util.HashMap<Integer, xbean.Equip>();
		for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equiponbodymap.entrySet())
			equiponbodymap.put(_e_.getKey(), new Equip(_e_.getValue(), this, "equiponbodymap"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equipmap.size());
for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : equipmap.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equiponbodymap.size());
for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : equiponbodymap.entrySet())
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
		equipmap = new java.util.HashMap<Integer, xbean.Equip>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Equip _v_ = new Equip(0, this, "equipmap");
		_v_.unmarshal(_os_);
		equipmap.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		equiponbodymap = new java.util.HashMap<Integer, xbean.Equip>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Equip _v_ = new Equip(0, this, "equiponbodymap");
		_v_.unmarshal(_os_);
		equiponbodymap.put(_k_, _v_);
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
	public xbean.RoleEquipBag copy() {
		_xdb_verify_unsafe_();
		return new RoleEquipBag(this);
	}

	@Override
	public xbean.RoleEquipBag toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleEquipBag toBean() {
		_xdb_verify_unsafe_();
		return new RoleEquipBag(this); // same as copy()
	}

	@Override
	public xbean.RoleEquipBag toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleEquipBag toBeanIf() {
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
	public java.util.Map<Integer, xbean.Equip> getEquipmap() { // 装备包裹，key为格子index
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "equipmap"), equipmap);
	}

	@Override
	public java.util.Map<Integer, xbean.Equip> getEquipmapAsData() { // 装备包裹，key为格子index
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Equip> equipmap;
		RoleEquipBag _o_ = this;
		equipmap = new java.util.HashMap<Integer, xbean.Equip>();
		for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipmap.entrySet())
			equipmap.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
		return equipmap;
	}

	@Override
	public java.util.Map<Integer, xbean.Equip> getEquiponbodymap() { // 装备包裹，key为格子index
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "equiponbodymap"), equiponbodymap);
	}

	@Override
	public java.util.Map<Integer, xbean.Equip> getEquiponbodymapAsData() { // 装备包裹，key为格子index
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Equip> equiponbodymap;
		RoleEquipBag _o_ = this;
		equiponbodymap = new java.util.HashMap<Integer, xbean.Equip>();
		for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equiponbodymap.entrySet())
			equiponbodymap.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
		return equiponbodymap;
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
		RoleEquipBag _o_ = null;
		if ( _o1_ instanceof RoleEquipBag ) _o_ = (RoleEquipBag)_o1_;
		else if ( _o1_ instanceof RoleEquipBag.Const ) _o_ = ((RoleEquipBag.Const)_o1_).nThis();
		else return false;
		if (capacity != _o_.capacity) return false;
		if (!equipmap.equals(_o_.equipmap)) return false;
		if (!equiponbodymap.equals(_o_.equiponbodymap)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += capacity;
		_h_ += equipmap.hashCode();
		_h_ += equiponbodymap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(capacity);
		_sb_.append(",");
		_sb_.append(equipmap);
		_sb_.append(",");
		_sb_.append(equiponbodymap);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("capacity"));
		lb.add(new xdb.logs.ListenableMap().setVarName("equipmap"));
		lb.add(new xdb.logs.ListenableMap().setVarName("equiponbodymap"));
		return lb;
	}

	private class Const implements xbean.RoleEquipBag {
		RoleEquipBag nThis() {
			return RoleEquipBag.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleEquipBag copy() {
			return RoleEquipBag.this.copy();
		}

		@Override
		public xbean.RoleEquipBag toData() {
			return RoleEquipBag.this.toData();
		}

		public xbean.RoleEquipBag toBean() {
			return RoleEquipBag.this.toBean();
		}

		@Override
		public xbean.RoleEquipBag toDataIf() {
			return RoleEquipBag.this.toDataIf();
		}

		public xbean.RoleEquipBag toBeanIf() {
			return RoleEquipBag.this.toBeanIf();
		}

		@Override
		public int getCapacity() { // 包裹容量 
			_xdb_verify_unsafe_();
			return capacity;
		}

		@Override
		public java.util.Map<Integer, xbean.Equip> getEquipmap() { // 装备包裹，key为格子index
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(equipmap);
		}

		@Override
		public java.util.Map<Integer, xbean.Equip> getEquipmapAsData() { // 装备包裹，key为格子index
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Equip> equipmap;
			RoleEquipBag _o_ = RoleEquipBag.this;
			equipmap = new java.util.HashMap<Integer, xbean.Equip>();
			for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipmap.entrySet())
				equipmap.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
			return equipmap;
		}

		@Override
		public java.util.Map<Integer, xbean.Equip> getEquiponbodymap() { // 装备包裹，key为格子index
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(equiponbodymap);
		}

		@Override
		public java.util.Map<Integer, xbean.Equip> getEquiponbodymapAsData() { // 装备包裹，key为格子index
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Equip> equiponbodymap;
			RoleEquipBag _o_ = RoleEquipBag.this;
			equiponbodymap = new java.util.HashMap<Integer, xbean.Equip>();
			for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equiponbodymap.entrySet())
				equiponbodymap.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
			return equiponbodymap;
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
			return RoleEquipBag.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleEquipBag.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleEquipBag.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleEquipBag.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleEquipBag.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleEquipBag.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleEquipBag.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleEquipBag.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleEquipBag.this.toString();
		}

	}

	public static final class Data implements xbean.RoleEquipBag {
		private int capacity; // 包裹容量 
		private java.util.HashMap<Integer, xbean.Equip> equipmap; // 装备包裹，key为格子index
		private java.util.HashMap<Integer, xbean.Equip> equiponbodymap; // 装备包裹，key为格子index

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			equipmap = new java.util.HashMap<Integer, xbean.Equip>();
			equiponbodymap = new java.util.HashMap<Integer, xbean.Equip>();
		}

		Data(xbean.RoleEquipBag _o1_) {
			if (_o1_ instanceof RoleEquipBag) assign((RoleEquipBag)_o1_);
			else if (_o1_ instanceof RoleEquipBag.Data) assign((RoleEquipBag.Data)_o1_);
			else if (_o1_ instanceof RoleEquipBag.Const) assign(((RoleEquipBag.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleEquipBag _o_) {
			capacity = _o_.capacity;
			equipmap = new java.util.HashMap<Integer, xbean.Equip>();
			for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipmap.entrySet())
				equipmap.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
			equiponbodymap = new java.util.HashMap<Integer, xbean.Equip>();
			for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equiponbodymap.entrySet())
				equiponbodymap.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
		}

		private void assign(RoleEquipBag.Data _o_) {
			capacity = _o_.capacity;
			equipmap = new java.util.HashMap<Integer, xbean.Equip>();
			for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equipmap.entrySet())
				equipmap.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
			equiponbodymap = new java.util.HashMap<Integer, xbean.Equip>();
			for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : _o_.equiponbodymap.entrySet())
				equiponbodymap.put(_e_.getKey(), new Equip.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equipmap.size());
for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : equipmap.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equiponbodymap.size());
for (java.util.Map.Entry<Integer, xbean.Equip> _e_ : equiponbodymap.entrySet())
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
		equipmap = new java.util.HashMap<Integer, xbean.Equip>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Equip _v_ = xbean.Pod.newEquipData();
		_v_.unmarshal(_os_);
		equipmap.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		equiponbodymap = new java.util.HashMap<Integer, xbean.Equip>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Equip _v_ = xbean.Pod.newEquipData();
		_v_.unmarshal(_os_);
		equiponbodymap.put(_k_, _v_);
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
		public xbean.RoleEquipBag copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleEquipBag toData() {
			return new Data(this);
		}

		public xbean.RoleEquipBag toBean() {
			return new RoleEquipBag(this, null, null);
		}

		@Override
		public xbean.RoleEquipBag toDataIf() {
			return this;
		}

		public xbean.RoleEquipBag toBeanIf() {
			return new RoleEquipBag(this, null, null);
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
		public java.util.Map<Integer, xbean.Equip> getEquipmap() { // 装备包裹，key为格子index
			return equipmap;
		}

		@Override
		public java.util.Map<Integer, xbean.Equip> getEquipmapAsData() { // 装备包裹，key为格子index
			return equipmap;
		}

		@Override
		public java.util.Map<Integer, xbean.Equip> getEquiponbodymap() { // 装备包裹，key为格子index
			return equiponbodymap;
		}

		@Override
		public java.util.Map<Integer, xbean.Equip> getEquiponbodymapAsData() { // 装备包裹，key为格子index
			return equiponbodymap;
		}

		@Override
		public void setCapacity(int _v_) { // 包裹容量 
			capacity = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleEquipBag.Data)) return false;
			RoleEquipBag.Data _o_ = (RoleEquipBag.Data) _o1_;
			if (capacity != _o_.capacity) return false;
			if (!equipmap.equals(_o_.equipmap)) return false;
			if (!equiponbodymap.equals(_o_.equiponbodymap)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += capacity;
			_h_ += equipmap.hashCode();
			_h_ += equiponbodymap.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(capacity);
			_sb_.append(",");
			_sb_.append(equipmap);
			_sb_.append(",");
			_sb_.append(equiponbodymap);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
