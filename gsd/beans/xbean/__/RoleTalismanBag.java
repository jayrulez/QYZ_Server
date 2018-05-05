
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleTalismanBag extends xdb.XBean implements xbean.RoleTalismanBag {
	private int capacity; // 包裹容量
	private java.util.HashMap<Integer, xbean.Talisman> talismans; // 所有法宝,key为包裹编号
	private java.util.HashMap<Integer, xbean.Talisman> equipedtalismans; // 所有法宝,key为包裹编号
	private int luckytype; // 当前法宝洗练运势，为0时表示没有
	private int luckywashtimes; // 在当前运势下已经洗练的次数，策划会配置一个上限
	private int maxcombatpower; // 达到过的最大战斗力

	@Override
	public void _reset_unsafe_() {
		capacity = 0;
		talismans.clear();
		equipedtalismans.clear();
		luckytype = 0;
		luckywashtimes = 0;
		maxcombatpower = 0;
	}

	RoleTalismanBag(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		talismans = new java.util.HashMap<Integer, xbean.Talisman>();
		equipedtalismans = new java.util.HashMap<Integer, xbean.Talisman>();
	}

	public RoleTalismanBag() {
		this(0, null, null);
	}

	public RoleTalismanBag(RoleTalismanBag _o_) {
		this(_o_, null, null);
	}

	RoleTalismanBag(xbean.RoleTalismanBag _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleTalismanBag) assign((RoleTalismanBag)_o1_);
		else if (_o1_ instanceof RoleTalismanBag.Data) assign((RoleTalismanBag.Data)_o1_);
		else if (_o1_ instanceof RoleTalismanBag.Const) assign(((RoleTalismanBag.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleTalismanBag _o_) {
		_o_._xdb_verify_unsafe_();
		capacity = _o_.capacity;
		talismans = new java.util.HashMap<Integer, xbean.Talisman>();
		for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
			talismans.put(_e_.getKey(), new Talisman(_e_.getValue(), this, "talismans"));
		equipedtalismans = new java.util.HashMap<Integer, xbean.Talisman>();
		for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.equipedtalismans.entrySet())
			equipedtalismans.put(_e_.getKey(), new Talisman(_e_.getValue(), this, "equipedtalismans"));
		luckytype = _o_.luckytype;
		luckywashtimes = _o_.luckywashtimes;
		maxcombatpower = _o_.maxcombatpower;
	}

	private void assign(RoleTalismanBag.Data _o_) {
		capacity = _o_.capacity;
		talismans = new java.util.HashMap<Integer, xbean.Talisman>();
		for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
			talismans.put(_e_.getKey(), new Talisman(_e_.getValue(), this, "talismans"));
		equipedtalismans = new java.util.HashMap<Integer, xbean.Talisman>();
		for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.equipedtalismans.entrySet())
			equipedtalismans.put(_e_.getKey(), new Talisman(_e_.getValue(), this, "equipedtalismans"));
		luckytype = _o_.luckytype;
		luckywashtimes = _o_.luckywashtimes;
		maxcombatpower = _o_.maxcombatpower;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(talismans.size());
for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : talismans.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equipedtalismans.size());
for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : equipedtalismans.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  5));_os_.marshal(luckytype);
    _os_.marshal((short)( 8192|  6));_os_.marshal(luckywashtimes);
    _os_.marshal((short)( 8192|  7));_os_.marshal(maxcombatpower);
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
		talismans = new java.util.HashMap<Integer, xbean.Talisman>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Talisman _v_ = new Talisman(0, this, "talismans");
		_v_.unmarshal(_os_);
		talismans.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		equipedtalismans = new java.util.HashMap<Integer, xbean.Talisman>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Talisman _v_ = new Talisman(0, this, "equipedtalismans");
		_v_.unmarshal(_os_);
		equipedtalismans.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  5):luckytype = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):luckytype = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):luckywashtimes = _os_.unmarshal_int();
    				break;
    				case ( 6144|  6):luckywashtimes = _os_.unmarshal_short();
    				break;
    				case ( 8192|  7):maxcombatpower = _os_.unmarshal_int();
    				break;
    				case ( 6144|  7):maxcombatpower = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleTalismanBag copy() {
		_xdb_verify_unsafe_();
		return new RoleTalismanBag(this);
	}

	@Override
	public xbean.RoleTalismanBag toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleTalismanBag toBean() {
		_xdb_verify_unsafe_();
		return new RoleTalismanBag(this); // same as copy()
	}

	@Override
	public xbean.RoleTalismanBag toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleTalismanBag toBeanIf() {
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
	public java.util.Map<Integer, xbean.Talisman> getTalismans() { // 所有法宝,key为包裹编号
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "talismans"), talismans);
	}

	@Override
	public java.util.Map<Integer, xbean.Talisman> getTalismansAsData() { // 所有法宝,key为包裹编号
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Talisman> talismans;
		RoleTalismanBag _o_ = this;
		talismans = new java.util.HashMap<Integer, xbean.Talisman>();
		for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
			talismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
		return talismans;
	}

	@Override
	public java.util.Map<Integer, xbean.Talisman> getEquipedtalismans() { // 所有法宝,key为包裹编号
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "equipedtalismans"), equipedtalismans);
	}

	@Override
	public java.util.Map<Integer, xbean.Talisman> getEquipedtalismansAsData() { // 所有法宝,key为包裹编号
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Talisman> equipedtalismans;
		RoleTalismanBag _o_ = this;
		equipedtalismans = new java.util.HashMap<Integer, xbean.Talisman>();
		for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.equipedtalismans.entrySet())
			equipedtalismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
		return equipedtalismans;
	}

	@Override
	public int getLuckytype() { // 当前法宝洗练运势，为0时表示没有
		_xdb_verify_unsafe_();
		return luckytype;
	}

	@Override
	public int getLuckywashtimes() { // 在当前运势下已经洗练的次数，策划会配置一个上限
		_xdb_verify_unsafe_();
		return luckywashtimes;
	}

	@Override
	public int getMaxcombatpower() { // 达到过的最大战斗力
		_xdb_verify_unsafe_();
		return maxcombatpower;
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
	public void setLuckytype(int _v_) { // 当前法宝洗练运势，为0时表示没有
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "luckytype") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, luckytype) {
					public void rollback() { luckytype = _xdb_saved; }
				};}});
		luckytype = _v_;
	}

	@Override
	public void setLuckywashtimes(int _v_) { // 在当前运势下已经洗练的次数，策划会配置一个上限
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "luckywashtimes") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, luckywashtimes) {
					public void rollback() { luckywashtimes = _xdb_saved; }
				};}});
		luckywashtimes = _v_;
	}

	@Override
	public void setMaxcombatpower(int _v_) { // 达到过的最大战斗力
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "maxcombatpower") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, maxcombatpower) {
					public void rollback() { maxcombatpower = _xdb_saved; }
				};}});
		maxcombatpower = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleTalismanBag _o_ = null;
		if ( _o1_ instanceof RoleTalismanBag ) _o_ = (RoleTalismanBag)_o1_;
		else if ( _o1_ instanceof RoleTalismanBag.Const ) _o_ = ((RoleTalismanBag.Const)_o1_).nThis();
		else return false;
		if (capacity != _o_.capacity) return false;
		if (!talismans.equals(_o_.talismans)) return false;
		if (!equipedtalismans.equals(_o_.equipedtalismans)) return false;
		if (luckytype != _o_.luckytype) return false;
		if (luckywashtimes != _o_.luckywashtimes) return false;
		if (maxcombatpower != _o_.maxcombatpower) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += capacity;
		_h_ += talismans.hashCode();
		_h_ += equipedtalismans.hashCode();
		_h_ += luckytype;
		_h_ += luckywashtimes;
		_h_ += maxcombatpower;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(capacity);
		_sb_.append(",");
		_sb_.append(talismans);
		_sb_.append(",");
		_sb_.append(equipedtalismans);
		_sb_.append(",");
		_sb_.append(luckytype);
		_sb_.append(",");
		_sb_.append(luckywashtimes);
		_sb_.append(",");
		_sb_.append(maxcombatpower);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("capacity"));
		lb.add(new xdb.logs.ListenableMap().setVarName("talismans"));
		lb.add(new xdb.logs.ListenableMap().setVarName("equipedtalismans"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("luckytype"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("luckywashtimes"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("maxcombatpower"));
		return lb;
	}

	private class Const implements xbean.RoleTalismanBag {
		RoleTalismanBag nThis() {
			return RoleTalismanBag.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleTalismanBag copy() {
			return RoleTalismanBag.this.copy();
		}

		@Override
		public xbean.RoleTalismanBag toData() {
			return RoleTalismanBag.this.toData();
		}

		public xbean.RoleTalismanBag toBean() {
			return RoleTalismanBag.this.toBean();
		}

		@Override
		public xbean.RoleTalismanBag toDataIf() {
			return RoleTalismanBag.this.toDataIf();
		}

		public xbean.RoleTalismanBag toBeanIf() {
			return RoleTalismanBag.this.toBeanIf();
		}

		@Override
		public int getCapacity() { // 包裹容量
			_xdb_verify_unsafe_();
			return capacity;
		}

		@Override
		public java.util.Map<Integer, xbean.Talisman> getTalismans() { // 所有法宝,key为包裹编号
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(talismans);
		}

		@Override
		public java.util.Map<Integer, xbean.Talisman> getTalismansAsData() { // 所有法宝,key为包裹编号
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Talisman> talismans;
			RoleTalismanBag _o_ = RoleTalismanBag.this;
			talismans = new java.util.HashMap<Integer, xbean.Talisman>();
			for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
				talismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
			return talismans;
		}

		@Override
		public java.util.Map<Integer, xbean.Talisman> getEquipedtalismans() { // 所有法宝,key为包裹编号
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(equipedtalismans);
		}

		@Override
		public java.util.Map<Integer, xbean.Talisman> getEquipedtalismansAsData() { // 所有法宝,key为包裹编号
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Talisman> equipedtalismans;
			RoleTalismanBag _o_ = RoleTalismanBag.this;
			equipedtalismans = new java.util.HashMap<Integer, xbean.Talisman>();
			for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.equipedtalismans.entrySet())
				equipedtalismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
			return equipedtalismans;
		}

		@Override
		public int getLuckytype() { // 当前法宝洗练运势，为0时表示没有
			_xdb_verify_unsafe_();
			return luckytype;
		}

		@Override
		public int getLuckywashtimes() { // 在当前运势下已经洗练的次数，策划会配置一个上限
			_xdb_verify_unsafe_();
			return luckywashtimes;
		}

		@Override
		public int getMaxcombatpower() { // 达到过的最大战斗力
			_xdb_verify_unsafe_();
			return maxcombatpower;
		}

		@Override
		public void setCapacity(int _v_) { // 包裹容量
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLuckytype(int _v_) { // 当前法宝洗练运势，为0时表示没有
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLuckywashtimes(int _v_) { // 在当前运势下已经洗练的次数，策划会配置一个上限
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMaxcombatpower(int _v_) { // 达到过的最大战斗力
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
			return RoleTalismanBag.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleTalismanBag.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleTalismanBag.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleTalismanBag.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleTalismanBag.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleTalismanBag.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleTalismanBag.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleTalismanBag.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleTalismanBag.this.toString();
		}

	}

	public static final class Data implements xbean.RoleTalismanBag {
		private int capacity; // 包裹容量
		private java.util.HashMap<Integer, xbean.Talisman> talismans; // 所有法宝,key为包裹编号
		private java.util.HashMap<Integer, xbean.Talisman> equipedtalismans; // 所有法宝,key为包裹编号
		private int luckytype; // 当前法宝洗练运势，为0时表示没有
		private int luckywashtimes; // 在当前运势下已经洗练的次数，策划会配置一个上限
		private int maxcombatpower; // 达到过的最大战斗力

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			talismans = new java.util.HashMap<Integer, xbean.Talisman>();
			equipedtalismans = new java.util.HashMap<Integer, xbean.Talisman>();
		}

		Data(xbean.RoleTalismanBag _o1_) {
			if (_o1_ instanceof RoleTalismanBag) assign((RoleTalismanBag)_o1_);
			else if (_o1_ instanceof RoleTalismanBag.Data) assign((RoleTalismanBag.Data)_o1_);
			else if (_o1_ instanceof RoleTalismanBag.Const) assign(((RoleTalismanBag.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleTalismanBag _o_) {
			capacity = _o_.capacity;
			talismans = new java.util.HashMap<Integer, xbean.Talisman>();
			for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
				talismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
			equipedtalismans = new java.util.HashMap<Integer, xbean.Talisman>();
			for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.equipedtalismans.entrySet())
				equipedtalismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
			luckytype = _o_.luckytype;
			luckywashtimes = _o_.luckywashtimes;
			maxcombatpower = _o_.maxcombatpower;
		}

		private void assign(RoleTalismanBag.Data _o_) {
			capacity = _o_.capacity;
			talismans = new java.util.HashMap<Integer, xbean.Talisman>();
			for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
				talismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
			equipedtalismans = new java.util.HashMap<Integer, xbean.Talisman>();
			for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.equipedtalismans.entrySet())
				equipedtalismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
			luckytype = _o_.luckytype;
			luckywashtimes = _o_.luckywashtimes;
			maxcombatpower = _o_.maxcombatpower;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(talismans.size());
for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : talismans.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equipedtalismans.size());
for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : equipedtalismans.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  5));_os_.marshal(luckytype);
	_os_.marshal((short)( 8192|  6));_os_.marshal(luckywashtimes);
	_os_.marshal((short)( 8192|  7));_os_.marshal(maxcombatpower);
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
		talismans = new java.util.HashMap<Integer, xbean.Talisman>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Talisman _v_ = xbean.Pod.newTalismanData();
		_v_.unmarshal(_os_);
		talismans.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		equipedtalismans = new java.util.HashMap<Integer, xbean.Talisman>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Talisman _v_ = xbean.Pod.newTalismanData();
		_v_.unmarshal(_os_);
		equipedtalismans.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192|  5):luckytype = _os_.unmarshal_int();
					break;
					case ( 6144|  5):luckytype = _os_.unmarshal_short();
					break;
					case ( 8192|  6):luckywashtimes = _os_.unmarshal_int();
					break;
					case ( 6144|  6):luckywashtimes = _os_.unmarshal_short();
					break;
					case ( 8192|  7):maxcombatpower = _os_.unmarshal_int();
					break;
					case ( 6144|  7):maxcombatpower = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleTalismanBag copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleTalismanBag toData() {
			return new Data(this);
		}

		public xbean.RoleTalismanBag toBean() {
			return new RoleTalismanBag(this, null, null);
		}

		@Override
		public xbean.RoleTalismanBag toDataIf() {
			return this;
		}

		public xbean.RoleTalismanBag toBeanIf() {
			return new RoleTalismanBag(this, null, null);
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
		public java.util.Map<Integer, xbean.Talisman> getTalismans() { // 所有法宝,key为包裹编号
			return talismans;
		}

		@Override
		public java.util.Map<Integer, xbean.Talisman> getTalismansAsData() { // 所有法宝,key为包裹编号
			return talismans;
		}

		@Override
		public java.util.Map<Integer, xbean.Talisman> getEquipedtalismans() { // 所有法宝,key为包裹编号
			return equipedtalismans;
		}

		@Override
		public java.util.Map<Integer, xbean.Talisman> getEquipedtalismansAsData() { // 所有法宝,key为包裹编号
			return equipedtalismans;
		}

		@Override
		public int getLuckytype() { // 当前法宝洗练运势，为0时表示没有
			return luckytype;
		}

		@Override
		public int getLuckywashtimes() { // 在当前运势下已经洗练的次数，策划会配置一个上限
			return luckywashtimes;
		}

		@Override
		public int getMaxcombatpower() { // 达到过的最大战斗力
			return maxcombatpower;
		}

		@Override
		public void setCapacity(int _v_) { // 包裹容量
			capacity = _v_;
		}

		@Override
		public void setLuckytype(int _v_) { // 当前法宝洗练运势，为0时表示没有
			luckytype = _v_;
		}

		@Override
		public void setLuckywashtimes(int _v_) { // 在当前运势下已经洗练的次数，策划会配置一个上限
			luckywashtimes = _v_;
		}

		@Override
		public void setMaxcombatpower(int _v_) { // 达到过的最大战斗力
			maxcombatpower = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleTalismanBag.Data)) return false;
			RoleTalismanBag.Data _o_ = (RoleTalismanBag.Data) _o1_;
			if (capacity != _o_.capacity) return false;
			if (!talismans.equals(_o_.talismans)) return false;
			if (!equipedtalismans.equals(_o_.equipedtalismans)) return false;
			if (luckytype != _o_.luckytype) return false;
			if (luckywashtimes != _o_.luckywashtimes) return false;
			if (maxcombatpower != _o_.maxcombatpower) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += capacity;
			_h_ += talismans.hashCode();
			_h_ += equipedtalismans.hashCode();
			_h_ += luckytype;
			_h_ += luckywashtimes;
			_h_ += maxcombatpower;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(capacity);
			_sb_.append(",");
			_sb_.append(talismans);
			_sb_.append(",");
			_sb_.append(equipedtalismans);
			_sb_.append(",");
			_sb_.append(luckytype);
			_sb_.append(",");
			_sb_.append(luckywashtimes);
			_sb_.append(",");
			_sb_.append(maxcombatpower);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
