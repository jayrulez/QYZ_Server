
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleRide extends xdb.XBean implements xbean.RoleRide {
	private java.util.HashMap<Integer, xbean.Ride> rides; // 角色的坐骑信息
	private int activeride; // 当前激活的坐骑

	@Override
	public void _reset_unsafe_() {
		rides.clear();
		activeride = 0;
	}

	RoleRide(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		rides = new java.util.HashMap<Integer, xbean.Ride>();
	}

	public RoleRide() {
		this(0, null, null);
	}

	public RoleRide(RoleRide _o_) {
		this(_o_, null, null);
	}

	RoleRide(xbean.RoleRide _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleRide) assign((RoleRide)_o1_);
		else if (_o1_ instanceof RoleRide.Data) assign((RoleRide.Data)_o1_);
		else if (_o1_ instanceof RoleRide.Const) assign(((RoleRide.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleRide _o_) {
		_o_._xdb_verify_unsafe_();
		rides = new java.util.HashMap<Integer, xbean.Ride>();
		for (java.util.Map.Entry<Integer, xbean.Ride> _e_ : _o_.rides.entrySet())
			rides.put(_e_.getKey(), new Ride(_e_.getValue(), this, "rides"));
		activeride = _o_.activeride;
	}

	private void assign(RoleRide.Data _o_) {
		rides = new java.util.HashMap<Integer, xbean.Ride>();
		for (java.util.Map.Entry<Integer, xbean.Ride> _e_ : _o_.rides.entrySet())
			rides.put(_e_.getKey(), new Ride(_e_.getValue(), this, "rides"));
		activeride = _o_.activeride;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(rides.size());
for (java.util.Map.Entry<Integer, xbean.Ride> _e_ : rides.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  2));_os_.marshal(activeride);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		rides = new java.util.HashMap<Integer, xbean.Ride>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Ride _v_ = new Ride(0, this, "rides");
		_v_.unmarshal(_os_);
		rides.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  2):activeride = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):activeride = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleRide copy() {
		_xdb_verify_unsafe_();
		return new RoleRide(this);
	}

	@Override
	public xbean.RoleRide toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleRide toBean() {
		_xdb_verify_unsafe_();
		return new RoleRide(this); // same as copy()
	}

	@Override
	public xbean.RoleRide toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleRide toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.Ride> getRides() { // 角色的坐骑信息
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "rides"), rides);
	}

	@Override
	public java.util.Map<Integer, xbean.Ride> getRidesAsData() { // 角色的坐骑信息
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Ride> rides;
		RoleRide _o_ = this;
		rides = new java.util.HashMap<Integer, xbean.Ride>();
		for (java.util.Map.Entry<Integer, xbean.Ride> _e_ : _o_.rides.entrySet())
			rides.put(_e_.getKey(), new Ride.Data(_e_.getValue()));
		return rides;
	}

	@Override
	public int getActiveride() { // 当前激活的坐骑
		_xdb_verify_unsafe_();
		return activeride;
	}

	@Override
	public void setActiveride(int _v_) { // 当前激活的坐骑
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "activeride") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, activeride) {
					public void rollback() { activeride = _xdb_saved; }
				};}});
		activeride = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleRide _o_ = null;
		if ( _o1_ instanceof RoleRide ) _o_ = (RoleRide)_o1_;
		else if ( _o1_ instanceof RoleRide.Const ) _o_ = ((RoleRide.Const)_o1_).nThis();
		else return false;
		if (!rides.equals(_o_.rides)) return false;
		if (activeride != _o_.activeride) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += rides.hashCode();
		_h_ += activeride;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(rides);
		_sb_.append(",");
		_sb_.append(activeride);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("rides"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("activeride"));
		return lb;
	}

	private class Const implements xbean.RoleRide {
		RoleRide nThis() {
			return RoleRide.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleRide copy() {
			return RoleRide.this.copy();
		}

		@Override
		public xbean.RoleRide toData() {
			return RoleRide.this.toData();
		}

		public xbean.RoleRide toBean() {
			return RoleRide.this.toBean();
		}

		@Override
		public xbean.RoleRide toDataIf() {
			return RoleRide.this.toDataIf();
		}

		public xbean.RoleRide toBeanIf() {
			return RoleRide.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.Ride> getRides() { // 角色的坐骑信息
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(rides);
		}

		@Override
		public java.util.Map<Integer, xbean.Ride> getRidesAsData() { // 角色的坐骑信息
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Ride> rides;
			RoleRide _o_ = RoleRide.this;
			rides = new java.util.HashMap<Integer, xbean.Ride>();
			for (java.util.Map.Entry<Integer, xbean.Ride> _e_ : _o_.rides.entrySet())
				rides.put(_e_.getKey(), new Ride.Data(_e_.getValue()));
			return rides;
		}

		@Override
		public int getActiveride() { // 当前激活的坐骑
			_xdb_verify_unsafe_();
			return activeride;
		}

		@Override
		public void setActiveride(int _v_) { // 当前激活的坐骑
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
			return RoleRide.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleRide.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleRide.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleRide.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleRide.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleRide.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleRide.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleRide.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleRide.this.toString();
		}

	}

	public static final class Data implements xbean.RoleRide {
		private java.util.HashMap<Integer, xbean.Ride> rides; // 角色的坐骑信息
		private int activeride; // 当前激活的坐骑

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			rides = new java.util.HashMap<Integer, xbean.Ride>();
		}

		Data(xbean.RoleRide _o1_) {
			if (_o1_ instanceof RoleRide) assign((RoleRide)_o1_);
			else if (_o1_ instanceof RoleRide.Data) assign((RoleRide.Data)_o1_);
			else if (_o1_ instanceof RoleRide.Const) assign(((RoleRide.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleRide _o_) {
			rides = new java.util.HashMap<Integer, xbean.Ride>();
			for (java.util.Map.Entry<Integer, xbean.Ride> _e_ : _o_.rides.entrySet())
				rides.put(_e_.getKey(), new Ride.Data(_e_.getValue()));
			activeride = _o_.activeride;
		}

		private void assign(RoleRide.Data _o_) {
			rides = new java.util.HashMap<Integer, xbean.Ride>();
			for (java.util.Map.Entry<Integer, xbean.Ride> _e_ : _o_.rides.entrySet())
				rides.put(_e_.getKey(), new Ride.Data(_e_.getValue()));
			activeride = _o_.activeride;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(rides.size());
for (java.util.Map.Entry<Integer, xbean.Ride> _e_ : rides.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  2));_os_.marshal(activeride);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		rides = new java.util.HashMap<Integer, xbean.Ride>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Ride _v_ = xbean.Pod.newRideData();
		_v_.unmarshal(_os_);
		rides.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192|  2):activeride = _os_.unmarshal_int();
					break;
					case ( 6144|  2):activeride = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleRide copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleRide toData() {
			return new Data(this);
		}

		public xbean.RoleRide toBean() {
			return new RoleRide(this, null, null);
		}

		@Override
		public xbean.RoleRide toDataIf() {
			return this;
		}

		public xbean.RoleRide toBeanIf() {
			return new RoleRide(this, null, null);
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
		public java.util.Map<Integer, xbean.Ride> getRides() { // 角色的坐骑信息
			return rides;
		}

		@Override
		public java.util.Map<Integer, xbean.Ride> getRidesAsData() { // 角色的坐骑信息
			return rides;
		}

		@Override
		public int getActiveride() { // 当前激活的坐骑
			return activeride;
		}

		@Override
		public void setActiveride(int _v_) { // 当前激活的坐骑
			activeride = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleRide.Data)) return false;
			RoleRide.Data _o_ = (RoleRide.Data) _o1_;
			if (!rides.equals(_o_.rides)) return false;
			if (activeride != _o_.activeride) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += rides.hashCode();
			_h_ += activeride;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(rides);
			_sb_.append(",");
			_sb_.append(activeride);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
