
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RandomBonus extends xdb.XBean implements xbean.RandomBonus {
	private int bindtype; // 
	private java.util.HashMap<Integer, Integer> items; // 

	@Override
	public void _reset_unsafe_() {
		bindtype = 0;
		items.clear();
	}

	RandomBonus(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		items = new java.util.HashMap<Integer, Integer>();
	}

	public RandomBonus() {
		this(0, null, null);
	}

	public RandomBonus(RandomBonus _o_) {
		this(_o_, null, null);
	}

	RandomBonus(xbean.RandomBonus _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RandomBonus) assign((RandomBonus)_o1_);
		else if (_o1_ instanceof RandomBonus.Data) assign((RandomBonus.Data)_o1_);
		else if (_o1_ instanceof RandomBonus.Const) assign(((RandomBonus.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RandomBonus _o_) {
		_o_._xdb_verify_unsafe_();
		bindtype = _o_.bindtype;
		items = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(RandomBonus.Data _o_) {
		bindtype = _o_.bindtype;
		items = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(bindtype);
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : items.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
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
    				case ( 8192|  1):bindtype = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):bindtype = _os_.unmarshal_short();
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
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RandomBonus copy() {
		_xdb_verify_unsafe_();
		return new RandomBonus(this);
	}

	@Override
	public xbean.RandomBonus toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RandomBonus toBean() {
		_xdb_verify_unsafe_();
		return new RandomBonus(this); // same as copy()
	}

	@Override
	public xbean.RandomBonus toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RandomBonus toBeanIf() {
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
	public java.util.Map<Integer, Integer> getItems() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "items"), items);
	}

	@Override
	public java.util.Map<Integer, Integer> getItemsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> items;
		RandomBonus _o_ = this;
		items = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
			items.put(_e_.getKey(), _e_.getValue());
		return items;
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
		RandomBonus _o_ = null;
		if ( _o1_ instanceof RandomBonus ) _o_ = (RandomBonus)_o1_;
		else if ( _o1_ instanceof RandomBonus.Const ) _o_ = ((RandomBonus.Const)_o1_).nThis();
		else return false;
		if (bindtype != _o_.bindtype) return false;
		if (!items.equals(_o_.items)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += bindtype;
		_h_ += items.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bindtype);
		_sb_.append(",");
		_sb_.append(items);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("bindtype"));
		lb.add(new xdb.logs.ListenableMap().setVarName("items"));
		return lb;
	}

	private class Const implements xbean.RandomBonus {
		RandomBonus nThis() {
			return RandomBonus.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RandomBonus copy() {
			return RandomBonus.this.copy();
		}

		@Override
		public xbean.RandomBonus toData() {
			return RandomBonus.this.toData();
		}

		public xbean.RandomBonus toBean() {
			return RandomBonus.this.toBean();
		}

		@Override
		public xbean.RandomBonus toDataIf() {
			return RandomBonus.this.toDataIf();
		}

		public xbean.RandomBonus toBeanIf() {
			return RandomBonus.this.toBeanIf();
		}

		@Override
		public int getBindtype() { // 
			_xdb_verify_unsafe_();
			return bindtype;
		}

		@Override
		public java.util.Map<Integer, Integer> getItems() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(items);
		}

		@Override
		public java.util.Map<Integer, Integer> getItemsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> items;
			RandomBonus _o_ = RandomBonus.this;
			items = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), _e_.getValue());
			return items;
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
			return RandomBonus.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RandomBonus.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RandomBonus.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RandomBonus.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RandomBonus.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RandomBonus.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RandomBonus.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RandomBonus.this.hashCode();
		}

		@Override
		public String toString() {
			return RandomBonus.this.toString();
		}

	}

	public static final class Data implements xbean.RandomBonus {
		private int bindtype; // 
		private java.util.HashMap<Integer, Integer> items; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			items = new java.util.HashMap<Integer, Integer>();
		}

		Data(xbean.RandomBonus _o1_) {
			if (_o1_ instanceof RandomBonus) assign((RandomBonus)_o1_);
			else if (_o1_ instanceof RandomBonus.Data) assign((RandomBonus.Data)_o1_);
			else if (_o1_ instanceof RandomBonus.Const) assign(((RandomBonus.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RandomBonus _o_) {
			bindtype = _o_.bindtype;
			items = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(RandomBonus.Data _o_) {
			bindtype = _o_.bindtype;
			items = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.items.entrySet())
				items.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(bindtype);
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : items.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):bindtype = _os_.unmarshal_int();
					break;
					case ( 6144|  1):bindtype = _os_.unmarshal_short();
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
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RandomBonus copy() {
			return new Data(this);
		}

		@Override
		public xbean.RandomBonus toData() {
			return new Data(this);
		}

		public xbean.RandomBonus toBean() {
			return new RandomBonus(this, null, null);
		}

		@Override
		public xbean.RandomBonus toDataIf() {
			return this;
		}

		public xbean.RandomBonus toBeanIf() {
			return new RandomBonus(this, null, null);
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
		public java.util.Map<Integer, Integer> getItems() { // 
			return items;
		}

		@Override
		public java.util.Map<Integer, Integer> getItemsAsData() { // 
			return items;
		}

		@Override
		public void setBindtype(int _v_) { // 
			bindtype = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RandomBonus.Data)) return false;
			RandomBonus.Data _o_ = (RandomBonus.Data) _o1_;
			if (bindtype != _o_.bindtype) return false;
			if (!items.equals(_o_.items)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += bindtype;
			_h_ += items.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(bindtype);
			_sb_.append(",");
			_sb_.append(items);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
