
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleOrderHistroy extends xdb.XBean implements xbean.RoleOrderHistroy {
	private java.util.HashMap<Long, xbean.AppOrder> succeedorder; // 
	private java.util.HashMap<Long, xbean.AppOrder> timeoutorder; // 

	@Override
	public void _reset_unsafe_() {
		succeedorder.clear();
		timeoutorder.clear();
	}

	RoleOrderHistroy(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		succeedorder = new java.util.HashMap<Long, xbean.AppOrder>();
		timeoutorder = new java.util.HashMap<Long, xbean.AppOrder>();
	}

	public RoleOrderHistroy() {
		this(0, null, null);
	}

	public RoleOrderHistroy(RoleOrderHistroy _o_) {
		this(_o_, null, null);
	}

	RoleOrderHistroy(xbean.RoleOrderHistroy _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleOrderHistroy) assign((RoleOrderHistroy)_o1_);
		else if (_o1_ instanceof RoleOrderHistroy.Data) assign((RoleOrderHistroy.Data)_o1_);
		else if (_o1_ instanceof RoleOrderHistroy.Const) assign(((RoleOrderHistroy.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleOrderHistroy _o_) {
		_o_._xdb_verify_unsafe_();
		succeedorder = new java.util.HashMap<Long, xbean.AppOrder>();
		for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.succeedorder.entrySet())
			succeedorder.put(_e_.getKey(), new AppOrder(_e_.getValue(), this, "succeedorder"));
		timeoutorder = new java.util.HashMap<Long, xbean.AppOrder>();
		for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.timeoutorder.entrySet())
			timeoutorder.put(_e_.getKey(), new AppOrder(_e_.getValue(), this, "timeoutorder"));
	}

	private void assign(RoleOrderHistroy.Data _o_) {
		succeedorder = new java.util.HashMap<Long, xbean.AppOrder>();
		for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.succeedorder.entrySet())
			succeedorder.put(_e_.getKey(), new AppOrder(_e_.getValue(), this, "succeedorder"));
		timeoutorder = new java.util.HashMap<Long, xbean.AppOrder>();
		for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.timeoutorder.entrySet())
			timeoutorder.put(_e_.getKey(), new AppOrder(_e_.getValue(), this, "timeoutorder"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(succeedorder.size());
for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : succeedorder.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(timeoutorder.size());
for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : timeoutorder.entrySet())
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
		succeedorder = new java.util.HashMap<Long, xbean.AppOrder>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.AppOrder _v_ = new AppOrder(0, this, "succeedorder");
		_v_.unmarshal(_os_);
		succeedorder.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		timeoutorder = new java.util.HashMap<Long, xbean.AppOrder>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.AppOrder _v_ = new AppOrder(0, this, "timeoutorder");
		_v_.unmarshal(_os_);
		timeoutorder.put(_k_, _v_);
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
	public xbean.RoleOrderHistroy copy() {
		_xdb_verify_unsafe_();
		return new RoleOrderHistroy(this);
	}

	@Override
	public xbean.RoleOrderHistroy toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleOrderHistroy toBean() {
		_xdb_verify_unsafe_();
		return new RoleOrderHistroy(this); // same as copy()
	}

	@Override
	public xbean.RoleOrderHistroy toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleOrderHistroy toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Long, xbean.AppOrder> getSucceedorder() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "succeedorder"), succeedorder);
	}

	@Override
	public java.util.Map<Long, xbean.AppOrder> getSucceedorderAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.AppOrder> succeedorder;
		RoleOrderHistroy _o_ = this;
		succeedorder = new java.util.HashMap<Long, xbean.AppOrder>();
		for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.succeedorder.entrySet())
			succeedorder.put(_e_.getKey(), new AppOrder.Data(_e_.getValue()));
		return succeedorder;
	}

	@Override
	public java.util.Map<Long, xbean.AppOrder> getTimeoutorder() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "timeoutorder"), timeoutorder);
	}

	@Override
	public java.util.Map<Long, xbean.AppOrder> getTimeoutorderAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.AppOrder> timeoutorder;
		RoleOrderHistroy _o_ = this;
		timeoutorder = new java.util.HashMap<Long, xbean.AppOrder>();
		for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.timeoutorder.entrySet())
			timeoutorder.put(_e_.getKey(), new AppOrder.Data(_e_.getValue()));
		return timeoutorder;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleOrderHistroy _o_ = null;
		if ( _o1_ instanceof RoleOrderHistroy ) _o_ = (RoleOrderHistroy)_o1_;
		else if ( _o1_ instanceof RoleOrderHistroy.Const ) _o_ = ((RoleOrderHistroy.Const)_o1_).nThis();
		else return false;
		if (!succeedorder.equals(_o_.succeedorder)) return false;
		if (!timeoutorder.equals(_o_.timeoutorder)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += succeedorder.hashCode();
		_h_ += timeoutorder.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(succeedorder);
		_sb_.append(",");
		_sb_.append(timeoutorder);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("succeedorder"));
		lb.add(new xdb.logs.ListenableMap().setVarName("timeoutorder"));
		return lb;
	}

	private class Const implements xbean.RoleOrderHistroy {
		RoleOrderHistroy nThis() {
			return RoleOrderHistroy.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleOrderHistroy copy() {
			return RoleOrderHistroy.this.copy();
		}

		@Override
		public xbean.RoleOrderHistroy toData() {
			return RoleOrderHistroy.this.toData();
		}

		public xbean.RoleOrderHistroy toBean() {
			return RoleOrderHistroy.this.toBean();
		}

		@Override
		public xbean.RoleOrderHistroy toDataIf() {
			return RoleOrderHistroy.this.toDataIf();
		}

		public xbean.RoleOrderHistroy toBeanIf() {
			return RoleOrderHistroy.this.toBeanIf();
		}

		@Override
		public java.util.Map<Long, xbean.AppOrder> getSucceedorder() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(succeedorder);
		}

		@Override
		public java.util.Map<Long, xbean.AppOrder> getSucceedorderAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.AppOrder> succeedorder;
			RoleOrderHistroy _o_ = RoleOrderHistroy.this;
			succeedorder = new java.util.HashMap<Long, xbean.AppOrder>();
			for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.succeedorder.entrySet())
				succeedorder.put(_e_.getKey(), new AppOrder.Data(_e_.getValue()));
			return succeedorder;
		}

		@Override
		public java.util.Map<Long, xbean.AppOrder> getTimeoutorder() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(timeoutorder);
		}

		@Override
		public java.util.Map<Long, xbean.AppOrder> getTimeoutorderAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.AppOrder> timeoutorder;
			RoleOrderHistroy _o_ = RoleOrderHistroy.this;
			timeoutorder = new java.util.HashMap<Long, xbean.AppOrder>();
			for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.timeoutorder.entrySet())
				timeoutorder.put(_e_.getKey(), new AppOrder.Data(_e_.getValue()));
			return timeoutorder;
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
			return RoleOrderHistroy.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleOrderHistroy.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleOrderHistroy.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleOrderHistroy.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleOrderHistroy.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleOrderHistroy.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleOrderHistroy.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleOrderHistroy.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleOrderHistroy.this.toString();
		}

	}

	public static final class Data implements xbean.RoleOrderHistroy {
		private java.util.HashMap<Long, xbean.AppOrder> succeedorder; // 
		private java.util.HashMap<Long, xbean.AppOrder> timeoutorder; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			succeedorder = new java.util.HashMap<Long, xbean.AppOrder>();
			timeoutorder = new java.util.HashMap<Long, xbean.AppOrder>();
		}

		Data(xbean.RoleOrderHistroy _o1_) {
			if (_o1_ instanceof RoleOrderHistroy) assign((RoleOrderHistroy)_o1_);
			else if (_o1_ instanceof RoleOrderHistroy.Data) assign((RoleOrderHistroy.Data)_o1_);
			else if (_o1_ instanceof RoleOrderHistroy.Const) assign(((RoleOrderHistroy.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleOrderHistroy _o_) {
			succeedorder = new java.util.HashMap<Long, xbean.AppOrder>();
			for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.succeedorder.entrySet())
				succeedorder.put(_e_.getKey(), new AppOrder.Data(_e_.getValue()));
			timeoutorder = new java.util.HashMap<Long, xbean.AppOrder>();
			for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.timeoutorder.entrySet())
				timeoutorder.put(_e_.getKey(), new AppOrder.Data(_e_.getValue()));
		}

		private void assign(RoleOrderHistroy.Data _o_) {
			succeedorder = new java.util.HashMap<Long, xbean.AppOrder>();
			for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.succeedorder.entrySet())
				succeedorder.put(_e_.getKey(), new AppOrder.Data(_e_.getValue()));
			timeoutorder = new java.util.HashMap<Long, xbean.AppOrder>();
			for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : _o_.timeoutorder.entrySet())
				timeoutorder.put(_e_.getKey(), new AppOrder.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(succeedorder.size());
for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : succeedorder.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(timeoutorder.size());
for (java.util.Map.Entry<Long, xbean.AppOrder> _e_ : timeoutorder.entrySet())
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
		succeedorder = new java.util.HashMap<Long, xbean.AppOrder>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.AppOrder _v_ = xbean.Pod.newAppOrderData();
		_v_.unmarshal(_os_);
		succeedorder.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		timeoutorder = new java.util.HashMap<Long, xbean.AppOrder>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.AppOrder _v_ = xbean.Pod.newAppOrderData();
		_v_.unmarshal(_os_);
		timeoutorder.put(_k_, _v_);
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
		public xbean.RoleOrderHistroy copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleOrderHistroy toData() {
			return new Data(this);
		}

		public xbean.RoleOrderHistroy toBean() {
			return new RoleOrderHistroy(this, null, null);
		}

		@Override
		public xbean.RoleOrderHistroy toDataIf() {
			return this;
		}

		public xbean.RoleOrderHistroy toBeanIf() {
			return new RoleOrderHistroy(this, null, null);
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
		public java.util.Map<Long, xbean.AppOrder> getSucceedorder() { // 
			return succeedorder;
		}

		@Override
		public java.util.Map<Long, xbean.AppOrder> getSucceedorderAsData() { // 
			return succeedorder;
		}

		@Override
		public java.util.Map<Long, xbean.AppOrder> getTimeoutorder() { // 
			return timeoutorder;
		}

		@Override
		public java.util.Map<Long, xbean.AppOrder> getTimeoutorderAsData() { // 
			return timeoutorder;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleOrderHistroy.Data)) return false;
			RoleOrderHistroy.Data _o_ = (RoleOrderHistroy.Data) _o1_;
			if (!succeedorder.equals(_o_.succeedorder)) return false;
			if (!timeoutorder.equals(_o_.timeoutorder)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += succeedorder.hashCode();
			_h_ += timeoutorder.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(succeedorder);
			_sb_.append(",");
			_sb_.append(timeoutorder);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
