
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleFragmentDepot extends xdb.XBean implements xbean.RoleFragmentDepot {
	private int capacity; // 包裹容量 
	private java.util.HashMap<Integer, xbean.Fragment> fragments; // 背包里的碎片列表

	@Override
	public void _reset_unsafe_() {
		capacity = 0;
		fragments.clear();
	}

	RoleFragmentDepot(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		fragments = new java.util.HashMap<Integer, xbean.Fragment>();
	}

	public RoleFragmentDepot() {
		this(0, null, null);
	}

	public RoleFragmentDepot(RoleFragmentDepot _o_) {
		this(_o_, null, null);
	}

	RoleFragmentDepot(xbean.RoleFragmentDepot _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleFragmentDepot) assign((RoleFragmentDepot)_o1_);
		else if (_o1_ instanceof RoleFragmentDepot.Data) assign((RoleFragmentDepot.Data)_o1_);
		else if (_o1_ instanceof RoleFragmentDepot.Const) assign(((RoleFragmentDepot.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleFragmentDepot _o_) {
		_o_._xdb_verify_unsafe_();
		capacity = _o_.capacity;
		fragments = new java.util.HashMap<Integer, xbean.Fragment>();
		for (java.util.Map.Entry<Integer, xbean.Fragment> _e_ : _o_.fragments.entrySet())
			fragments.put(_e_.getKey(), new Fragment(_e_.getValue(), this, "fragments"));
	}

	private void assign(RoleFragmentDepot.Data _o_) {
		capacity = _o_.capacity;
		fragments = new java.util.HashMap<Integer, xbean.Fragment>();
		for (java.util.Map.Entry<Integer, xbean.Fragment> _e_ : _o_.fragments.entrySet())
			fragments.put(_e_.getKey(), new Fragment(_e_.getValue(), this, "fragments"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(fragments.size());
for (java.util.Map.Entry<Integer, xbean.Fragment> _e_ : fragments.entrySet())
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
		fragments = new java.util.HashMap<Integer, xbean.Fragment>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Fragment _v_ = new Fragment(0, this, "fragments");
		_v_.unmarshal(_os_);
		fragments.put(_k_, _v_);
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
	public xbean.RoleFragmentDepot copy() {
		_xdb_verify_unsafe_();
		return new RoleFragmentDepot(this);
	}

	@Override
	public xbean.RoleFragmentDepot toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleFragmentDepot toBean() {
		_xdb_verify_unsafe_();
		return new RoleFragmentDepot(this); // same as copy()
	}

	@Override
	public xbean.RoleFragmentDepot toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleFragmentDepot toBeanIf() {
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
	public java.util.Map<Integer, xbean.Fragment> getFragments() { // 背包里的碎片列表
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "fragments"), fragments);
	}

	@Override
	public java.util.Map<Integer, xbean.Fragment> getFragmentsAsData() { // 背包里的碎片列表
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Fragment> fragments;
		RoleFragmentDepot _o_ = this;
		fragments = new java.util.HashMap<Integer, xbean.Fragment>();
		for (java.util.Map.Entry<Integer, xbean.Fragment> _e_ : _o_.fragments.entrySet())
			fragments.put(_e_.getKey(), new Fragment.Data(_e_.getValue()));
		return fragments;
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
		RoleFragmentDepot _o_ = null;
		if ( _o1_ instanceof RoleFragmentDepot ) _o_ = (RoleFragmentDepot)_o1_;
		else if ( _o1_ instanceof RoleFragmentDepot.Const ) _o_ = ((RoleFragmentDepot.Const)_o1_).nThis();
		else return false;
		if (capacity != _o_.capacity) return false;
		if (!fragments.equals(_o_.fragments)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += capacity;
		_h_ += fragments.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(capacity);
		_sb_.append(",");
		_sb_.append(fragments);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("capacity"));
		lb.add(new xdb.logs.ListenableMap().setVarName("fragments"));
		return lb;
	}

	private class Const implements xbean.RoleFragmentDepot {
		RoleFragmentDepot nThis() {
			return RoleFragmentDepot.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleFragmentDepot copy() {
			return RoleFragmentDepot.this.copy();
		}

		@Override
		public xbean.RoleFragmentDepot toData() {
			return RoleFragmentDepot.this.toData();
		}

		public xbean.RoleFragmentDepot toBean() {
			return RoleFragmentDepot.this.toBean();
		}

		@Override
		public xbean.RoleFragmentDepot toDataIf() {
			return RoleFragmentDepot.this.toDataIf();
		}

		public xbean.RoleFragmentDepot toBeanIf() {
			return RoleFragmentDepot.this.toBeanIf();
		}

		@Override
		public int getCapacity() { // 包裹容量 
			_xdb_verify_unsafe_();
			return capacity;
		}

		@Override
		public java.util.Map<Integer, xbean.Fragment> getFragments() { // 背包里的碎片列表
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(fragments);
		}

		@Override
		public java.util.Map<Integer, xbean.Fragment> getFragmentsAsData() { // 背包里的碎片列表
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Fragment> fragments;
			RoleFragmentDepot _o_ = RoleFragmentDepot.this;
			fragments = new java.util.HashMap<Integer, xbean.Fragment>();
			for (java.util.Map.Entry<Integer, xbean.Fragment> _e_ : _o_.fragments.entrySet())
				fragments.put(_e_.getKey(), new Fragment.Data(_e_.getValue()));
			return fragments;
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
			return RoleFragmentDepot.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleFragmentDepot.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleFragmentDepot.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleFragmentDepot.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleFragmentDepot.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleFragmentDepot.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleFragmentDepot.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleFragmentDepot.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleFragmentDepot.this.toString();
		}

	}

	public static final class Data implements xbean.RoleFragmentDepot {
		private int capacity; // 包裹容量 
		private java.util.HashMap<Integer, xbean.Fragment> fragments; // 背包里的碎片列表

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			fragments = new java.util.HashMap<Integer, xbean.Fragment>();
		}

		Data(xbean.RoleFragmentDepot _o1_) {
			if (_o1_ instanceof RoleFragmentDepot) assign((RoleFragmentDepot)_o1_);
			else if (_o1_ instanceof RoleFragmentDepot.Data) assign((RoleFragmentDepot.Data)_o1_);
			else if (_o1_ instanceof RoleFragmentDepot.Const) assign(((RoleFragmentDepot.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleFragmentDepot _o_) {
			capacity = _o_.capacity;
			fragments = new java.util.HashMap<Integer, xbean.Fragment>();
			for (java.util.Map.Entry<Integer, xbean.Fragment> _e_ : _o_.fragments.entrySet())
				fragments.put(_e_.getKey(), new Fragment.Data(_e_.getValue()));
		}

		private void assign(RoleFragmentDepot.Data _o_) {
			capacity = _o_.capacity;
			fragments = new java.util.HashMap<Integer, xbean.Fragment>();
			for (java.util.Map.Entry<Integer, xbean.Fragment> _e_ : _o_.fragments.entrySet())
				fragments.put(_e_.getKey(), new Fragment.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(capacity);
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(fragments.size());
for (java.util.Map.Entry<Integer, xbean.Fragment> _e_ : fragments.entrySet())
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
		fragments = new java.util.HashMap<Integer, xbean.Fragment>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Fragment _v_ = xbean.Pod.newFragmentData();
		_v_.unmarshal(_os_);
		fragments.put(_k_, _v_);
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
		public xbean.RoleFragmentDepot copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleFragmentDepot toData() {
			return new Data(this);
		}

		public xbean.RoleFragmentDepot toBean() {
			return new RoleFragmentDepot(this, null, null);
		}

		@Override
		public xbean.RoleFragmentDepot toDataIf() {
			return this;
		}

		public xbean.RoleFragmentDepot toBeanIf() {
			return new RoleFragmentDepot(this, null, null);
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
		public java.util.Map<Integer, xbean.Fragment> getFragments() { // 背包里的碎片列表
			return fragments;
		}

		@Override
		public java.util.Map<Integer, xbean.Fragment> getFragmentsAsData() { // 背包里的碎片列表
			return fragments;
		}

		@Override
		public void setCapacity(int _v_) { // 包裹容量 
			capacity = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleFragmentDepot.Data)) return false;
			RoleFragmentDepot.Data _o_ = (RoleFragmentDepot.Data) _o1_;
			if (capacity != _o_.capacity) return false;
			if (!fragments.equals(_o_.fragments)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += capacity;
			_h_ += fragments.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(capacity);
			_sb_.append(",");
			_sb_.append(fragments);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
