
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleLimit extends xdb.XBean implements xbean.RoleLimit {
	private java.util.HashMap<Long, xbean.Limit> limits; // key->(moduleid, eventid)
	private java.util.HashMap<Long, xbean.CoolDown> cooldowns; // key->(moduleid, eventid)

	@Override
	public void _reset_unsafe_() {
		limits.clear();
		cooldowns.clear();
	}

	RoleLimit(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		limits = new java.util.HashMap<Long, xbean.Limit>();
		cooldowns = new java.util.HashMap<Long, xbean.CoolDown>();
	}

	public RoleLimit() {
		this(0, null, null);
	}

	public RoleLimit(RoleLimit _o_) {
		this(_o_, null, null);
	}

	RoleLimit(xbean.RoleLimit _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleLimit) assign((RoleLimit)_o1_);
		else if (_o1_ instanceof RoleLimit.Data) assign((RoleLimit.Data)_o1_);
		else if (_o1_ instanceof RoleLimit.Const) assign(((RoleLimit.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleLimit _o_) {
		_o_._xdb_verify_unsafe_();
		limits = new java.util.HashMap<Long, xbean.Limit>();
		for (java.util.Map.Entry<Long, xbean.Limit> _e_ : _o_.limits.entrySet())
			limits.put(_e_.getKey(), new Limit(_e_.getValue(), this, "limits"));
		cooldowns = new java.util.HashMap<Long, xbean.CoolDown>();
		for (java.util.Map.Entry<Long, xbean.CoolDown> _e_ : _o_.cooldowns.entrySet())
			cooldowns.put(_e_.getKey(), new CoolDown(_e_.getValue(), this, "cooldowns"));
	}

	private void assign(RoleLimit.Data _o_) {
		limits = new java.util.HashMap<Long, xbean.Limit>();
		for (java.util.Map.Entry<Long, xbean.Limit> _e_ : _o_.limits.entrySet())
			limits.put(_e_.getKey(), new Limit(_e_.getValue(), this, "limits"));
		cooldowns = new java.util.HashMap<Long, xbean.CoolDown>();
		for (java.util.Map.Entry<Long, xbean.CoolDown> _e_ : _o_.cooldowns.entrySet())
			cooldowns.put(_e_.getKey(), new CoolDown(_e_.getValue(), this, "cooldowns"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(limits.size());
for (java.util.Map.Entry<Long, xbean.Limit> _e_ : limits.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(cooldowns.size());
for (java.util.Map.Entry<Long, xbean.CoolDown> _e_ : cooldowns.entrySet())
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
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		limits = new java.util.HashMap<Long, xbean.Limit>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.Limit _v_ = new Limit(0, this, "limits");
		_v_.unmarshal(_os_);
		limits.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		cooldowns = new java.util.HashMap<Long, xbean.CoolDown>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.CoolDown _v_ = new CoolDown(0, this, "cooldowns");
		_v_.unmarshal(_os_);
		cooldowns.put(_k_, _v_);
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
	public xbean.RoleLimit copy() {
		_xdb_verify_unsafe_();
		return new RoleLimit(this);
	}

	@Override
	public xbean.RoleLimit toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleLimit toBean() {
		_xdb_verify_unsafe_();
		return new RoleLimit(this); // same as copy()
	}

	@Override
	public xbean.RoleLimit toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleLimit toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Long, xbean.Limit> getLimits() { // key->(moduleid, eventid)
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "limits"), limits);
	}

	@Override
	public java.util.Map<Long, xbean.Limit> getLimitsAsData() { // key->(moduleid, eventid)
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.Limit> limits;
		RoleLimit _o_ = this;
		limits = new java.util.HashMap<Long, xbean.Limit>();
		for (java.util.Map.Entry<Long, xbean.Limit> _e_ : _o_.limits.entrySet())
			limits.put(_e_.getKey(), new Limit.Data(_e_.getValue()));
		return limits;
	}

	@Override
	public java.util.Map<Long, xbean.CoolDown> getCooldowns() { // key->(moduleid, eventid)
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "cooldowns"), cooldowns);
	}

	@Override
	public java.util.Map<Long, xbean.CoolDown> getCooldownsAsData() { // key->(moduleid, eventid)
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.CoolDown> cooldowns;
		RoleLimit _o_ = this;
		cooldowns = new java.util.HashMap<Long, xbean.CoolDown>();
		for (java.util.Map.Entry<Long, xbean.CoolDown> _e_ : _o_.cooldowns.entrySet())
			cooldowns.put(_e_.getKey(), new CoolDown.Data(_e_.getValue()));
		return cooldowns;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleLimit _o_ = null;
		if ( _o1_ instanceof RoleLimit ) _o_ = (RoleLimit)_o1_;
		else if ( _o1_ instanceof RoleLimit.Const ) _o_ = ((RoleLimit.Const)_o1_).nThis();
		else return false;
		if (!limits.equals(_o_.limits)) return false;
		if (!cooldowns.equals(_o_.cooldowns)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += limits.hashCode();
		_h_ += cooldowns.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(limits);
		_sb_.append(",");
		_sb_.append(cooldowns);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("limits"));
		lb.add(new xdb.logs.ListenableMap().setVarName("cooldowns"));
		return lb;
	}

	private class Const implements xbean.RoleLimit {
		RoleLimit nThis() {
			return RoleLimit.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleLimit copy() {
			return RoleLimit.this.copy();
		}

		@Override
		public xbean.RoleLimit toData() {
			return RoleLimit.this.toData();
		}

		public xbean.RoleLimit toBean() {
			return RoleLimit.this.toBean();
		}

		@Override
		public xbean.RoleLimit toDataIf() {
			return RoleLimit.this.toDataIf();
		}

		public xbean.RoleLimit toBeanIf() {
			return RoleLimit.this.toBeanIf();
		}

		@Override
		public java.util.Map<Long, xbean.Limit> getLimits() { // key->(moduleid, eventid)
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(limits);
		}

		@Override
		public java.util.Map<Long, xbean.Limit> getLimitsAsData() { // key->(moduleid, eventid)
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.Limit> limits;
			RoleLimit _o_ = RoleLimit.this;
			limits = new java.util.HashMap<Long, xbean.Limit>();
			for (java.util.Map.Entry<Long, xbean.Limit> _e_ : _o_.limits.entrySet())
				limits.put(_e_.getKey(), new Limit.Data(_e_.getValue()));
			return limits;
		}

		@Override
		public java.util.Map<Long, xbean.CoolDown> getCooldowns() { // key->(moduleid, eventid)
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(cooldowns);
		}

		@Override
		public java.util.Map<Long, xbean.CoolDown> getCooldownsAsData() { // key->(moduleid, eventid)
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.CoolDown> cooldowns;
			RoleLimit _o_ = RoleLimit.this;
			cooldowns = new java.util.HashMap<Long, xbean.CoolDown>();
			for (java.util.Map.Entry<Long, xbean.CoolDown> _e_ : _o_.cooldowns.entrySet())
				cooldowns.put(_e_.getKey(), new CoolDown.Data(_e_.getValue()));
			return cooldowns;
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
			return RoleLimit.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleLimit.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleLimit.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleLimit.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleLimit.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleLimit.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleLimit.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleLimit.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleLimit.this.toString();
		}

	}

	public static final class Data implements xbean.RoleLimit {
		private java.util.HashMap<Long, xbean.Limit> limits; // key->(moduleid, eventid)
		private java.util.HashMap<Long, xbean.CoolDown> cooldowns; // key->(moduleid, eventid)

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			limits = new java.util.HashMap<Long, xbean.Limit>();
			cooldowns = new java.util.HashMap<Long, xbean.CoolDown>();
		}

		Data(xbean.RoleLimit _o1_) {
			if (_o1_ instanceof RoleLimit) assign((RoleLimit)_o1_);
			else if (_o1_ instanceof RoleLimit.Data) assign((RoleLimit.Data)_o1_);
			else if (_o1_ instanceof RoleLimit.Const) assign(((RoleLimit.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleLimit _o_) {
			limits = new java.util.HashMap<Long, xbean.Limit>();
			for (java.util.Map.Entry<Long, xbean.Limit> _e_ : _o_.limits.entrySet())
				limits.put(_e_.getKey(), new Limit.Data(_e_.getValue()));
			cooldowns = new java.util.HashMap<Long, xbean.CoolDown>();
			for (java.util.Map.Entry<Long, xbean.CoolDown> _e_ : _o_.cooldowns.entrySet())
				cooldowns.put(_e_.getKey(), new CoolDown.Data(_e_.getValue()));
		}

		private void assign(RoleLimit.Data _o_) {
			limits = new java.util.HashMap<Long, xbean.Limit>();
			for (java.util.Map.Entry<Long, xbean.Limit> _e_ : _o_.limits.entrySet())
				limits.put(_e_.getKey(), new Limit.Data(_e_.getValue()));
			cooldowns = new java.util.HashMap<Long, xbean.CoolDown>();
			for (java.util.Map.Entry<Long, xbean.CoolDown> _e_ : _o_.cooldowns.entrySet())
				cooldowns.put(_e_.getKey(), new CoolDown.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(limits.size());
for (java.util.Map.Entry<Long, xbean.Limit> _e_ : limits.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(cooldowns.size());
for (java.util.Map.Entry<Long, xbean.CoolDown> _e_ : cooldowns.entrySet())
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
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		limits = new java.util.HashMap<Long, xbean.Limit>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.Limit _v_ = xbean.Pod.newLimitData();
		_v_.unmarshal(_os_);
		limits.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		cooldowns = new java.util.HashMap<Long, xbean.CoolDown>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.CoolDown _v_ = xbean.Pod.newCoolDownData();
		_v_.unmarshal(_os_);
		cooldowns.put(_k_, _v_);
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
		public xbean.RoleLimit copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleLimit toData() {
			return new Data(this);
		}

		public xbean.RoleLimit toBean() {
			return new RoleLimit(this, null, null);
		}

		@Override
		public xbean.RoleLimit toDataIf() {
			return this;
		}

		public xbean.RoleLimit toBeanIf() {
			return new RoleLimit(this, null, null);
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
		public java.util.Map<Long, xbean.Limit> getLimits() { // key->(moduleid, eventid)
			return limits;
		}

		@Override
		public java.util.Map<Long, xbean.Limit> getLimitsAsData() { // key->(moduleid, eventid)
			return limits;
		}

		@Override
		public java.util.Map<Long, xbean.CoolDown> getCooldowns() { // key->(moduleid, eventid)
			return cooldowns;
		}

		@Override
		public java.util.Map<Long, xbean.CoolDown> getCooldownsAsData() { // key->(moduleid, eventid)
			return cooldowns;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleLimit.Data)) return false;
			RoleLimit.Data _o_ = (RoleLimit.Data) _o1_;
			if (!limits.equals(_o_.limits)) return false;
			if (!cooldowns.equals(_o_.cooldowns)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += limits.hashCode();
			_h_ += cooldowns.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(limits);
			_sb_.append(",");
			_sb_.append(cooldowns);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
