
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleTalismanDepot extends xdb.XBean implements xbean.RoleTalismanDepot {
	private int capacity; // 包裹容量
	private java.util.HashMap<Integer, xbean.Talisman> talismans; // 所有法宝,key为包裹编号

	@Override
	public void _reset_unsafe_() {
		capacity = 0;
		talismans.clear();
	}

	RoleTalismanDepot(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		talismans = new java.util.HashMap<Integer, xbean.Talisman>();
	}

	public RoleTalismanDepot() {
		this(0, null, null);
	}

	public RoleTalismanDepot(RoleTalismanDepot _o_) {
		this(_o_, null, null);
	}

	RoleTalismanDepot(xbean.RoleTalismanDepot _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleTalismanDepot) assign((RoleTalismanDepot)_o1_);
		else if (_o1_ instanceof RoleTalismanDepot.Data) assign((RoleTalismanDepot.Data)_o1_);
		else if (_o1_ instanceof RoleTalismanDepot.Const) assign(((RoleTalismanDepot.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleTalismanDepot _o_) {
		_o_._xdb_verify_unsafe_();
		capacity = _o_.capacity;
		talismans = new java.util.HashMap<Integer, xbean.Talisman>();
		for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
			talismans.put(_e_.getKey(), new Talisman(_e_.getValue(), this, "talismans"));
	}

	private void assign(RoleTalismanDepot.Data _o_) {
		capacity = _o_.capacity;
		talismans = new java.util.HashMap<Integer, xbean.Talisman>();
		for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
			talismans.put(_e_.getKey(), new Talisman(_e_.getValue(), this, "talismans"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(talismans.size());
for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : talismans.entrySet())
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
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleTalismanDepot copy() {
		_xdb_verify_unsafe_();
		return new RoleTalismanDepot(this);
	}

	@Override
	public xbean.RoleTalismanDepot toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleTalismanDepot toBean() {
		_xdb_verify_unsafe_();
		return new RoleTalismanDepot(this); // same as copy()
	}

	@Override
	public xbean.RoleTalismanDepot toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleTalismanDepot toBeanIf() {
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
		RoleTalismanDepot _o_ = this;
		talismans = new java.util.HashMap<Integer, xbean.Talisman>();
		for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
			talismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
		return talismans;
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
		RoleTalismanDepot _o_ = null;
		if ( _o1_ instanceof RoleTalismanDepot ) _o_ = (RoleTalismanDepot)_o1_;
		else if ( _o1_ instanceof RoleTalismanDepot.Const ) _o_ = ((RoleTalismanDepot.Const)_o1_).nThis();
		else return false;
		if (capacity != _o_.capacity) return false;
		if (!talismans.equals(_o_.talismans)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += capacity;
		_h_ += talismans.hashCode();
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
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("capacity"));
		lb.add(new xdb.logs.ListenableMap().setVarName("talismans"));
		return lb;
	}

	private class Const implements xbean.RoleTalismanDepot {
		RoleTalismanDepot nThis() {
			return RoleTalismanDepot.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleTalismanDepot copy() {
			return RoleTalismanDepot.this.copy();
		}

		@Override
		public xbean.RoleTalismanDepot toData() {
			return RoleTalismanDepot.this.toData();
		}

		public xbean.RoleTalismanDepot toBean() {
			return RoleTalismanDepot.this.toBean();
		}

		@Override
		public xbean.RoleTalismanDepot toDataIf() {
			return RoleTalismanDepot.this.toDataIf();
		}

		public xbean.RoleTalismanDepot toBeanIf() {
			return RoleTalismanDepot.this.toBeanIf();
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
			RoleTalismanDepot _o_ = RoleTalismanDepot.this;
			talismans = new java.util.HashMap<Integer, xbean.Talisman>();
			for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
				talismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
			return talismans;
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
			return RoleTalismanDepot.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleTalismanDepot.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleTalismanDepot.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleTalismanDepot.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleTalismanDepot.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleTalismanDepot.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleTalismanDepot.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleTalismanDepot.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleTalismanDepot.this.toString();
		}

	}

	public static final class Data implements xbean.RoleTalismanDepot {
		private int capacity; // 包裹容量
		private java.util.HashMap<Integer, xbean.Talisman> talismans; // 所有法宝,key为包裹编号

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			talismans = new java.util.HashMap<Integer, xbean.Talisman>();
		}

		Data(xbean.RoleTalismanDepot _o1_) {
			if (_o1_ instanceof RoleTalismanDepot) assign((RoleTalismanDepot)_o1_);
			else if (_o1_ instanceof RoleTalismanDepot.Data) assign((RoleTalismanDepot.Data)_o1_);
			else if (_o1_ instanceof RoleTalismanDepot.Const) assign(((RoleTalismanDepot.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleTalismanDepot _o_) {
			capacity = _o_.capacity;
			talismans = new java.util.HashMap<Integer, xbean.Talisman>();
			for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
				talismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
		}

		private void assign(RoleTalismanDepot.Data _o_) {
			capacity = _o_.capacity;
			talismans = new java.util.HashMap<Integer, xbean.Talisman>();
			for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : _o_.talismans.entrySet())
				talismans.put(_e_.getKey(), new Talisman.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(talismans.size());
for (java.util.Map.Entry<Integer, xbean.Talisman> _e_ : talismans.entrySet())
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
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleTalismanDepot copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleTalismanDepot toData() {
			return new Data(this);
		}

		public xbean.RoleTalismanDepot toBean() {
			return new RoleTalismanDepot(this, null, null);
		}

		@Override
		public xbean.RoleTalismanDepot toDataIf() {
			return this;
		}

		public xbean.RoleTalismanDepot toBeanIf() {
			return new RoleTalismanDepot(this, null, null);
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
		public void setCapacity(int _v_) { // 包裹容量
			capacity = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleTalismanDepot.Data)) return false;
			RoleTalismanDepot.Data _o_ = (RoleTalismanDepot.Data) _o1_;
			if (capacity != _o_.capacity) return false;
			if (!talismans.equals(_o_.talismans)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += capacity;
			_h_ += talismans.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(capacity);
			_sb_.append(",");
			_sb_.append(talismans);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
