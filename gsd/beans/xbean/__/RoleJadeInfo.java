
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleJadeInfo extends xdb.XBean implements xbean.RoleJadeInfo {
	private int level; // 玉佩等级
	private int bonus; // 玉佩增加值
	private int holenum; // 玉佩孔打开的数量
	private java.util.HashMap<Integer, xbean.Jewelry> jewelry; // 装备到玉佩上的宝珠
	private java.util.ArrayList<xbean.Jewelry> jewelrybag; // 宝珠背包
	private int jewelrygetlevel; // 猎取师档位

	@Override
	public void _reset_unsafe_() {
		level = 0;
		bonus = 0;
		holenum = 0;
		jewelry.clear();
		jewelrybag.clear();
		jewelrygetlevel = 0;
	}

	RoleJadeInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		jewelry = new java.util.HashMap<Integer, xbean.Jewelry>();
		jewelrybag = new java.util.ArrayList<xbean.Jewelry>();
	}

	public RoleJadeInfo() {
		this(0, null, null);
	}

	public RoleJadeInfo(RoleJadeInfo _o_) {
		this(_o_, null, null);
	}

	RoleJadeInfo(xbean.RoleJadeInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleJadeInfo) assign((RoleJadeInfo)_o1_);
		else if (_o1_ instanceof RoleJadeInfo.Data) assign((RoleJadeInfo.Data)_o1_);
		else if (_o1_ instanceof RoleJadeInfo.Const) assign(((RoleJadeInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleJadeInfo _o_) {
		_o_._xdb_verify_unsafe_();
		level = _o_.level;
		bonus = _o_.bonus;
		holenum = _o_.holenum;
		jewelry = new java.util.HashMap<Integer, xbean.Jewelry>();
		for (java.util.Map.Entry<Integer, xbean.Jewelry> _e_ : _o_.jewelry.entrySet())
			jewelry.put(_e_.getKey(), new Jewelry(_e_.getValue(), this, "jewelry"));
		jewelrybag = new java.util.ArrayList<xbean.Jewelry>();
		for (xbean.Jewelry _v_ : _o_.jewelrybag)
			jewelrybag.add(new Jewelry(_v_, this, "jewelrybag"));
		jewelrygetlevel = _o_.jewelrygetlevel;
	}

	private void assign(RoleJadeInfo.Data _o_) {
		level = _o_.level;
		bonus = _o_.bonus;
		holenum = _o_.holenum;
		jewelry = new java.util.HashMap<Integer, xbean.Jewelry>();
		for (java.util.Map.Entry<Integer, xbean.Jewelry> _e_ : _o_.jewelry.entrySet())
			jewelry.put(_e_.getKey(), new Jewelry(_e_.getValue(), this, "jewelry"));
		jewelrybag = new java.util.ArrayList<xbean.Jewelry>();
		for (xbean.Jewelry _v_ : _o_.jewelrybag)
			jewelrybag.add(new Jewelry(_v_, this, "jewelrybag"));
		jewelrygetlevel = _o_.jewelrygetlevel;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)( 8192|  1));_os_.marshal(level);
    _os_.marshal((short)( 8192|  2));_os_.marshal(bonus);
    _os_.marshal((short)( 8192|  3));_os_.marshal(holenum);
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(jewelry.size());
for (java.util.Map.Entry<Integer, xbean.Jewelry> _e_ : jewelry.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(jewelrybag.size());
for (xbean.Jewelry _v_ : jewelrybag) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  6));_os_.marshal(jewelrygetlevel);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):level = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):level = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):bonus = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):bonus = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):holenum = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):holenum = _os_.unmarshal_short();
    				break;
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		jewelry = new java.util.HashMap<Integer, xbean.Jewelry>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Jewelry _v_ = new Jewelry(0, this, "jewelry");
		_v_.unmarshal(_os_);
		jewelry.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (22528|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.Jewelry _v_ = new Jewelry(0, this, "jewelrybag");
	_v_.unmarshal(_os_);
	jewelrybag.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  6):jewelrygetlevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  6):jewelrygetlevel = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleJadeInfo copy() {
		_xdb_verify_unsafe_();
		return new RoleJadeInfo(this);
	}

	@Override
	public xbean.RoleJadeInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleJadeInfo toBean() {
		_xdb_verify_unsafe_();
		return new RoleJadeInfo(this); // same as copy()
	}

	@Override
	public xbean.RoleJadeInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleJadeInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getLevel() { // 玉佩等级
		_xdb_verify_unsafe_();
		return level;
	}

	@Override
	public int getBonus() { // 玉佩增加值
		_xdb_verify_unsafe_();
		return bonus;
	}

	@Override
	public int getHolenum() { // 玉佩孔打开的数量
		_xdb_verify_unsafe_();
		return holenum;
	}

	@Override
	public java.util.Map<Integer, xbean.Jewelry> getJewelry() { // 装备到玉佩上的宝珠
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "jewelry"), jewelry);
	}

	@Override
	public java.util.Map<Integer, xbean.Jewelry> getJewelryAsData() { // 装备到玉佩上的宝珠
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Jewelry> jewelry;
		RoleJadeInfo _o_ = this;
		jewelry = new java.util.HashMap<Integer, xbean.Jewelry>();
		for (java.util.Map.Entry<Integer, xbean.Jewelry> _e_ : _o_.jewelry.entrySet())
			jewelry.put(_e_.getKey(), new Jewelry.Data(_e_.getValue()));
		return jewelry;
	}

	@Override
	public java.util.List<xbean.Jewelry> getJewelrybag() { // 宝珠背包
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "jewelrybag"), jewelrybag);
	}

	public java.util.List<xbean.Jewelry> getJewelrybagAsData() { // 宝珠背包
		_xdb_verify_unsafe_();
		java.util.List<xbean.Jewelry> jewelrybag;
		RoleJadeInfo _o_ = this;
		jewelrybag = new java.util.ArrayList<xbean.Jewelry>();
		for (xbean.Jewelry _v_ : _o_.jewelrybag)
			jewelrybag.add(new Jewelry.Data(_v_));
		return jewelrybag;
	}

	@Override
	public int getJewelrygetlevel() { // 猎取师档位
		_xdb_verify_unsafe_();
		return jewelrygetlevel;
	}

	@Override
	public void setLevel(int _v_) { // 玉佩等级
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "level") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, level) {
					public void rollback() { level = _xdb_saved; }
				};}});
		level = _v_;
	}

	@Override
	public void setBonus(int _v_) { // 玉佩增加值
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "bonus") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, bonus) {
					public void rollback() { bonus = _xdb_saved; }
				};}});
		bonus = _v_;
	}

	@Override
	public void setHolenum(int _v_) { // 玉佩孔打开的数量
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "holenum") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, holenum) {
					public void rollback() { holenum = _xdb_saved; }
				};}});
		holenum = _v_;
	}

	@Override
	public void setJewelrygetlevel(int _v_) { // 猎取师档位
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "jewelrygetlevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, jewelrygetlevel) {
					public void rollback() { jewelrygetlevel = _xdb_saved; }
				};}});
		jewelrygetlevel = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleJadeInfo _o_ = null;
		if ( _o1_ instanceof RoleJadeInfo ) _o_ = (RoleJadeInfo)_o1_;
		else if ( _o1_ instanceof RoleJadeInfo.Const ) _o_ = ((RoleJadeInfo.Const)_o1_).nThis();
		else return false;
		if (level != _o_.level) return false;
		if (bonus != _o_.bonus) return false;
		if (holenum != _o_.holenum) return false;
		if (!jewelry.equals(_o_.jewelry)) return false;
		if (!jewelrybag.equals(_o_.jewelrybag)) return false;
		if (jewelrygetlevel != _o_.jewelrygetlevel) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += level;
		_h_ += bonus;
		_h_ += holenum;
		_h_ += jewelry.hashCode();
		_h_ += jewelrybag.hashCode();
		_h_ += jewelrygetlevel;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(level);
		_sb_.append(",");
		_sb_.append(bonus);
		_sb_.append(",");
		_sb_.append(holenum);
		_sb_.append(",");
		_sb_.append(jewelry);
		_sb_.append(",");
		_sb_.append(jewelrybag);
		_sb_.append(",");
		_sb_.append(jewelrygetlevel);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("level"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("bonus"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("holenum"));
		lb.add(new xdb.logs.ListenableMap().setVarName("jewelry"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("jewelrybag"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("jewelrygetlevel"));
		return lb;
	}

	private class Const implements xbean.RoleJadeInfo {
		RoleJadeInfo nThis() {
			return RoleJadeInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleJadeInfo copy() {
			return RoleJadeInfo.this.copy();
		}

		@Override
		public xbean.RoleJadeInfo toData() {
			return RoleJadeInfo.this.toData();
		}

		public xbean.RoleJadeInfo toBean() {
			return RoleJadeInfo.this.toBean();
		}

		@Override
		public xbean.RoleJadeInfo toDataIf() {
			return RoleJadeInfo.this.toDataIf();
		}

		public xbean.RoleJadeInfo toBeanIf() {
			return RoleJadeInfo.this.toBeanIf();
		}

		@Override
		public int getLevel() { // 玉佩等级
			_xdb_verify_unsafe_();
			return level;
		}

		@Override
		public int getBonus() { // 玉佩增加值
			_xdb_verify_unsafe_();
			return bonus;
		}

		@Override
		public int getHolenum() { // 玉佩孔打开的数量
			_xdb_verify_unsafe_();
			return holenum;
		}

		@Override
		public java.util.Map<Integer, xbean.Jewelry> getJewelry() { // 装备到玉佩上的宝珠
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(jewelry);
		}

		@Override
		public java.util.Map<Integer, xbean.Jewelry> getJewelryAsData() { // 装备到玉佩上的宝珠
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Jewelry> jewelry;
			RoleJadeInfo _o_ = RoleJadeInfo.this;
			jewelry = new java.util.HashMap<Integer, xbean.Jewelry>();
			for (java.util.Map.Entry<Integer, xbean.Jewelry> _e_ : _o_.jewelry.entrySet())
				jewelry.put(_e_.getKey(), new Jewelry.Data(_e_.getValue()));
			return jewelry;
		}

		@Override
		public java.util.List<xbean.Jewelry> getJewelrybag() { // 宝珠背包
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(jewelrybag);
		}

		public java.util.List<xbean.Jewelry> getJewelrybagAsData() { // 宝珠背包
			_xdb_verify_unsafe_();
			java.util.List<xbean.Jewelry> jewelrybag;
			RoleJadeInfo _o_ = RoleJadeInfo.this;
		jewelrybag = new java.util.ArrayList<xbean.Jewelry>();
		for (xbean.Jewelry _v_ : _o_.jewelrybag)
			jewelrybag.add(new Jewelry.Data(_v_));
			return jewelrybag;
		}

		@Override
		public int getJewelrygetlevel() { // 猎取师档位
			_xdb_verify_unsafe_();
			return jewelrygetlevel;
		}

		@Override
		public void setLevel(int _v_) { // 玉佩等级
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBonus(int _v_) { // 玉佩增加值
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHolenum(int _v_) { // 玉佩孔打开的数量
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setJewelrygetlevel(int _v_) { // 猎取师档位
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
			return RoleJadeInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleJadeInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleJadeInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleJadeInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleJadeInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleJadeInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleJadeInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleJadeInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleJadeInfo.this.toString();
		}

	}

	public static final class Data implements xbean.RoleJadeInfo {
		private int level; // 玉佩等级
		private int bonus; // 玉佩增加值
		private int holenum; // 玉佩孔打开的数量
		private java.util.HashMap<Integer, xbean.Jewelry> jewelry; // 装备到玉佩上的宝珠
		private java.util.ArrayList<xbean.Jewelry> jewelrybag; // 宝珠背包
		private int jewelrygetlevel; // 猎取师档位

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			jewelry = new java.util.HashMap<Integer, xbean.Jewelry>();
			jewelrybag = new java.util.ArrayList<xbean.Jewelry>();
		}

		Data(xbean.RoleJadeInfo _o1_) {
			if (_o1_ instanceof RoleJadeInfo) assign((RoleJadeInfo)_o1_);
			else if (_o1_ instanceof RoleJadeInfo.Data) assign((RoleJadeInfo.Data)_o1_);
			else if (_o1_ instanceof RoleJadeInfo.Const) assign(((RoleJadeInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleJadeInfo _o_) {
			level = _o_.level;
			bonus = _o_.bonus;
			holenum = _o_.holenum;
			jewelry = new java.util.HashMap<Integer, xbean.Jewelry>();
			for (java.util.Map.Entry<Integer, xbean.Jewelry> _e_ : _o_.jewelry.entrySet())
				jewelry.put(_e_.getKey(), new Jewelry.Data(_e_.getValue()));
			jewelrybag = new java.util.ArrayList<xbean.Jewelry>();
			for (xbean.Jewelry _v_ : _o_.jewelrybag)
				jewelrybag.add(new Jewelry.Data(_v_));
			jewelrygetlevel = _o_.jewelrygetlevel;
		}

		private void assign(RoleJadeInfo.Data _o_) {
			level = _o_.level;
			bonus = _o_.bonus;
			holenum = _o_.holenum;
			jewelry = new java.util.HashMap<Integer, xbean.Jewelry>();
			for (java.util.Map.Entry<Integer, xbean.Jewelry> _e_ : _o_.jewelry.entrySet())
				jewelry.put(_e_.getKey(), new Jewelry.Data(_e_.getValue()));
			jewelrybag = new java.util.ArrayList<xbean.Jewelry>();
			for (xbean.Jewelry _v_ : _o_.jewelrybag)
				jewelrybag.add(new Jewelry.Data(_v_));
			jewelrygetlevel = _o_.jewelrygetlevel;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)( 8192|  1));_os_.marshal(level);
	_os_.marshal((short)( 8192|  2));_os_.marshal(bonus);
	_os_.marshal((short)( 8192|  3));_os_.marshal(holenum);
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(jewelry.size());
for (java.util.Map.Entry<Integer, xbean.Jewelry> _e_ : jewelry.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(jewelrybag.size());
for (xbean.Jewelry _v_ : jewelrybag) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  6));_os_.marshal(jewelrygetlevel);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):level = _os_.unmarshal_int();
					break;
					case ( 6144|  1):level = _os_.unmarshal_short();
					break;
					case ( 8192|  2):bonus = _os_.unmarshal_int();
					break;
					case ( 6144|  2):bonus = _os_.unmarshal_short();
					break;
					case ( 8192|  3):holenum = _os_.unmarshal_int();
					break;
					case ( 6144|  3):holenum = _os_.unmarshal_short();
					break;
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		jewelry = new java.util.HashMap<Integer, xbean.Jewelry>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Jewelry _v_ = xbean.Pod.newJewelryData();
		_v_.unmarshal(_os_);
		jewelry.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (22528|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.Jewelry _v_ = xbean.Pod.newJewelryData();
	_v_.unmarshal(_os_);
	jewelrybag.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192|  6):jewelrygetlevel = _os_.unmarshal_int();
					break;
					case ( 6144|  6):jewelrygetlevel = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleJadeInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleJadeInfo toData() {
			return new Data(this);
		}

		public xbean.RoleJadeInfo toBean() {
			return new RoleJadeInfo(this, null, null);
		}

		@Override
		public xbean.RoleJadeInfo toDataIf() {
			return this;
		}

		public xbean.RoleJadeInfo toBeanIf() {
			return new RoleJadeInfo(this, null, null);
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
		public int getLevel() { // 玉佩等级
			return level;
		}

		@Override
		public int getBonus() { // 玉佩增加值
			return bonus;
		}

		@Override
		public int getHolenum() { // 玉佩孔打开的数量
			return holenum;
		}

		@Override
		public java.util.Map<Integer, xbean.Jewelry> getJewelry() { // 装备到玉佩上的宝珠
			return jewelry;
		}

		@Override
		public java.util.Map<Integer, xbean.Jewelry> getJewelryAsData() { // 装备到玉佩上的宝珠
			return jewelry;
		}

		@Override
		public java.util.List<xbean.Jewelry> getJewelrybag() { // 宝珠背包
			return jewelrybag;
		}

		@Override
		public java.util.List<xbean.Jewelry> getJewelrybagAsData() { // 宝珠背包
			return jewelrybag;
		}

		@Override
		public int getJewelrygetlevel() { // 猎取师档位
			return jewelrygetlevel;
		}

		@Override
		public void setLevel(int _v_) { // 玉佩等级
			level = _v_;
		}

		@Override
		public void setBonus(int _v_) { // 玉佩增加值
			bonus = _v_;
		}

		@Override
		public void setHolenum(int _v_) { // 玉佩孔打开的数量
			holenum = _v_;
		}

		@Override
		public void setJewelrygetlevel(int _v_) { // 猎取师档位
			jewelrygetlevel = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleJadeInfo.Data)) return false;
			RoleJadeInfo.Data _o_ = (RoleJadeInfo.Data) _o1_;
			if (level != _o_.level) return false;
			if (bonus != _o_.bonus) return false;
			if (holenum != _o_.holenum) return false;
			if (!jewelry.equals(_o_.jewelry)) return false;
			if (!jewelrybag.equals(_o_.jewelrybag)) return false;
			if (jewelrygetlevel != _o_.jewelrygetlevel) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += level;
			_h_ += bonus;
			_h_ += holenum;
			_h_ += jewelry.hashCode();
			_h_ += jewelrybag.hashCode();
			_h_ += jewelrygetlevel;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(level);
			_sb_.append(",");
			_sb_.append(bonus);
			_sb_.append(",");
			_sb_.append(holenum);
			_sb_.append(",");
			_sb_.append(jewelry);
			_sb_.append(",");
			_sb_.append(jewelrybag);
			_sb_.append(",");
			_sb_.append(jewelrygetlevel);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
