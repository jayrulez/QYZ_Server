
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Bonus extends xdb.XBean implements xbean.Bonus {
	private int bindtype; // 
	private java.util.HashMap<Integer, Integer> currencys; // 货币(exp,vipexp也算货币)
	private java.util.HashMap<Integer, Integer> items; // 物品，包含装备,碎片和消耗性物品
	private java.util.LinkedList<xbean.Equip> equips; // 

	@Override
	public void _reset_unsafe_() {
		bindtype = 0;
		currencys.clear();
		items.clear();
		equips.clear();
	}

	Bonus(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		currencys = new java.util.HashMap<Integer, Integer>();
		items = new java.util.HashMap<Integer, Integer>();
		equips = new java.util.LinkedList<xbean.Equip>();
	}

	public Bonus() {
		this(0, null, null);
	}

	public Bonus(Bonus _o_) {
		this(_o_, null, null);
	}

	Bonus(xbean.Bonus _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Bonus) assign((Bonus)_o1_);
		else if (_o1_ instanceof Bonus.Data) assign((Bonus.Data)_o1_);
		else if (_o1_ instanceof Bonus.Const) assign(((Bonus.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Bonus _o_) {
		_o_._xdb_verify_unsafe_();
		bindtype = _o_.bindtype;
		currencys = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.currencys.entrySet())
			currencys.put(_e_.getKey(), _e_.getValue());
		items = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), _e_.getValue());
		equips = new java.util.LinkedList<xbean.Equip>();
		for (xbean.Equip _v_ : _o_.equips)
			equips.add(new Equip(_v_, this, "equips"));
	}

	private void assign(Bonus.Data _o_) {
		bindtype = _o_.bindtype;
		currencys = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.currencys.entrySet())
			currencys.put(_e_.getKey(), _e_.getValue());
		items = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), _e_.getValue());
		equips = new java.util.LinkedList<xbean.Equip>();
		for (xbean.Equip _v_ : _o_.equips)
			equips.add(new Equip(_v_, this, "equips"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)( 8192|  0));_os_.marshal(bindtype);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(currencys.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : currencys.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : items.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equips.size());
for (xbean.Equip _v_ : equips) {
	_v_.marshal(_os_);
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
    				case ( 8192|  0):bindtype = _os_.unmarshal_int();
    				break;
    				case ( 6144|  0):bindtype = _os_.unmarshal_short();
    				break;
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		currencys = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		currencys.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		items = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		items.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (22528|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.Equip _v_ = new Equip(0, this, "equips");
	_v_.unmarshal(_os_);
	equips.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Bonus copy() {
		_xdb_verify_unsafe_();
		return new Bonus(this);
	}

	@Override
	public xbean.Bonus toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Bonus toBean() {
		_xdb_verify_unsafe_();
		return new Bonus(this); // same as copy()
	}

	@Override
	public xbean.Bonus toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Bonus toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getBindtype() { // 
		_xdb_verify_unsafe_();
		return bindtype;
	}

	@Override
	public java.util.Map<Integer, Integer> getCurrencys() { // 货币(exp,vipexp也算货币)
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "currencys"), currencys);
	}

	@Override
	public java.util.Map<Integer, Integer> getCurrencysAsData() { // 货币(exp,vipexp也算货币)
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> currencys;
		Bonus _o_ = this;
		currencys = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.currencys.entrySet())
			currencys.put(_e_.getKey(), _e_.getValue());
		return currencys;
	}

	@Override
	public java.util.Map<Integer, Integer> getItems() { // 物品，包含装备,碎片和消耗性物品
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "items"), items);
	}

	@Override
	public java.util.Map<Integer, Integer> getItemsAsData() { // 物品，包含装备,碎片和消耗性物品
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> items;
		Bonus _o_ = this;
		items = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), _e_.getValue());
		return items;
	}

	@Override
	public java.util.List<xbean.Equip> getEquips() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "equips"), equips);
	}

	public java.util.List<xbean.Equip> getEquipsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<xbean.Equip> equips;
		Bonus _o_ = this;
		equips = new java.util.LinkedList<xbean.Equip>();
		for (xbean.Equip _v_ : _o_.equips)
			equips.add(new Equip.Data(_v_));
		return equips;
	}

	@Override
	public void setBindtype(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "bindtype") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, bindtype) {
					public void rollback() { bindtype = _xdb_saved; }
				};}});
		bindtype = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Bonus _o_ = null;
		if ( _o1_ instanceof Bonus ) _o_ = (Bonus)_o1_;
		else if ( _o1_ instanceof Bonus.Const ) _o_ = ((Bonus.Const)_o1_).nThis();
		else return false;
		if (bindtype != _o_.bindtype) return false;
		if (!currencys.equals(_o_.currencys)) return false;
		if (!items.equals(_o_.items)) return false;
		if (!equips.equals(_o_.equips)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += bindtype;
		_h_ += currencys.hashCode();
		_h_ += items.hashCode();
		_h_ += equips.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bindtype);
		_sb_.append(",");
		_sb_.append(currencys);
		_sb_.append(",");
		_sb_.append(items);
		_sb_.append(",");
		_sb_.append(equips);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("bindtype"));
		lb.add(new xdb.logs.ListenableMap().setVarName("currencys"));
		lb.add(new xdb.logs.ListenableMap().setVarName("items"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("equips"));
		return lb;
	}

	private class Const implements xbean.Bonus {
		Bonus nThis() {
			return Bonus.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Bonus copy() {
			return Bonus.this.copy();
		}

		@Override
		public xbean.Bonus toData() {
			return Bonus.this.toData();
		}

		public xbean.Bonus toBean() {
			return Bonus.this.toBean();
		}

		@Override
		public xbean.Bonus toDataIf() {
			return Bonus.this.toDataIf();
		}

		public xbean.Bonus toBeanIf() {
			return Bonus.this.toBeanIf();
		}

		@Override
		public int getBindtype() { // 
			_xdb_verify_unsafe_();
			return bindtype;
		}

		@Override
		public java.util.Map<Integer, Integer> getCurrencys() { // 货币(exp,vipexp也算货币)
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(currencys);
		}

		@Override
		public java.util.Map<Integer, Integer> getCurrencysAsData() { // 货币(exp,vipexp也算货币)
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> currencys;
			Bonus _o_ = Bonus.this;
			currencys = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.currencys.entrySet())
				currencys.put(_e_.getKey(), _e_.getValue());
			return currencys;
		}

		@Override
		public java.util.Map<Integer, Integer> getItems() { // 物品，包含装备,碎片和消耗性物品
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(items);
		}

		@Override
		public java.util.Map<Integer, Integer> getItemsAsData() { // 物品，包含装备,碎片和消耗性物品
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> items;
			Bonus _o_ = Bonus.this;
			items = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), _e_.getValue());
			return items;
		}

		@Override
		public java.util.List<xbean.Equip> getEquips() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(equips);
		}

		public java.util.List<xbean.Equip> getEquipsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<xbean.Equip> equips;
			Bonus _o_ = Bonus.this;
		equips = new java.util.LinkedList<xbean.Equip>();
		for (xbean.Equip _v_ : _o_.equips)
			equips.add(new Equip.Data(_v_));
			return equips;
		}

		@Override
		public void setBindtype(int _v_) { // 
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
			return Bonus.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Bonus.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Bonus.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Bonus.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Bonus.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Bonus.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Bonus.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Bonus.this.hashCode();
		}

		@Override
		public String toString() {
			return Bonus.this.toString();
		}

	}

	public static final class Data implements xbean.Bonus {
		private int bindtype; // 
		private java.util.HashMap<Integer, Integer> currencys; // 货币(exp,vipexp也算货币)
		private java.util.HashMap<Integer, Integer> items; // 物品，包含装备,碎片和消耗性物品
		private java.util.LinkedList<xbean.Equip> equips; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			currencys = new java.util.HashMap<Integer, Integer>();
			items = new java.util.HashMap<Integer, Integer>();
			equips = new java.util.LinkedList<xbean.Equip>();
		}

		Data(xbean.Bonus _o1_) {
			if (_o1_ instanceof Bonus) assign((Bonus)_o1_);
			else if (_o1_ instanceof Bonus.Data) assign((Bonus.Data)_o1_);
			else if (_o1_ instanceof Bonus.Const) assign(((Bonus.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Bonus _o_) {
			bindtype = _o_.bindtype;
			currencys = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.currencys.entrySet())
				currencys.put(_e_.getKey(), _e_.getValue());
			items = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), _e_.getValue());
			equips = new java.util.LinkedList<xbean.Equip>();
			for (xbean.Equip _v_ : _o_.equips)
				equips.add(new Equip.Data(_v_));
		}

		private void assign(Bonus.Data _o_) {
			bindtype = _o_.bindtype;
			currencys = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.currencys.entrySet())
				currencys.put(_e_.getKey(), _e_.getValue());
			items = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), _e_.getValue());
			equips = new java.util.LinkedList<xbean.Equip>();
			for (xbean.Equip _v_ : _o_.equips)
				equips.add(new Equip.Data(_v_));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)( 8192|  0));_os_.marshal(bindtype);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(currencys.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : currencys.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : items.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equips.size());
for (xbean.Equip _v_ : equips) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  0):bindtype = _os_.unmarshal_int();
					break;
					case ( 6144|  0):bindtype = _os_.unmarshal_short();
					break;
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		currencys = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		currencys.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		items = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		items.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (22528|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.Equip _v_ = xbean.Pod.newEquipData();
	_v_.unmarshal(_os_);
	equips.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Bonus copy() {
			return new Data(this);
		}

		@Override
		public xbean.Bonus toData() {
			return new Data(this);
		}

		public xbean.Bonus toBean() {
			return new Bonus(this, null, null);
		}

		@Override
		public xbean.Bonus toDataIf() {
			return this;
		}

		public xbean.Bonus toBeanIf() {
			return new Bonus(this, null, null);
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
		public int getBindtype() { // 
			return bindtype;
		}

		@Override
		public java.util.Map<Integer, Integer> getCurrencys() { // 货币(exp,vipexp也算货币)
			return currencys;
		}

		@Override
		public java.util.Map<Integer, Integer> getCurrencysAsData() { // 货币(exp,vipexp也算货币)
			return currencys;
		}

		@Override
		public java.util.Map<Integer, Integer> getItems() { // 物品，包含装备,碎片和消耗性物品
			return items;
		}

		@Override
		public java.util.Map<Integer, Integer> getItemsAsData() { // 物品，包含装备,碎片和消耗性物品
			return items;
		}

		@Override
		public java.util.List<xbean.Equip> getEquips() { // 
			return equips;
		}

		@Override
		public java.util.List<xbean.Equip> getEquipsAsData() { // 
			return equips;
		}

		@Override
		public void setBindtype(int _v_) { // 
			bindtype = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Bonus.Data)) return false;
			Bonus.Data _o_ = (Bonus.Data) _o1_;
			if (bindtype != _o_.bindtype) return false;
			if (!currencys.equals(_o_.currencys)) return false;
			if (!items.equals(_o_.items)) return false;
			if (!equips.equals(_o_.equips)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += bindtype;
			_h_ += currencys.hashCode();
			_h_ += items.hashCode();
			_h_ += equips.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(bindtype);
			_sb_.append(",");
			_sb_.append(currencys);
			_sb_.append(",");
			_sb_.append(items);
			_sb_.append(",");
			_sb_.append(equips);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
