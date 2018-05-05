
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleAchievement extends xdb.XBean implements xbean.RoleAchievement {
	private java.util.HashMap<Integer, Integer> achievementstates; // 
	private java.util.HashMap<Integer, Long> counters; // 
	private java.util.HashMap<Integer, xbean.CounterSet> countersets; // 

	@Override
	public void _reset_unsafe_() {
		achievementstates.clear();
		counters.clear();
		countersets.clear();
	}

	RoleAchievement(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		achievementstates = new java.util.HashMap<Integer, Integer>();
		counters = new java.util.HashMap<Integer, Long>();
		countersets = new java.util.HashMap<Integer, xbean.CounterSet>();
	}

	public RoleAchievement() {
		this(0, null, null);
	}

	public RoleAchievement(RoleAchievement _o_) {
		this(_o_, null, null);
	}

	RoleAchievement(xbean.RoleAchievement _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleAchievement) assign((RoleAchievement)_o1_);
		else if (_o1_ instanceof RoleAchievement.Data) assign((RoleAchievement.Data)_o1_);
		else if (_o1_ instanceof RoleAchievement.Const) assign(((RoleAchievement.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleAchievement _o_) {
		_o_._xdb_verify_unsafe_();
		achievementstates = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.achievementstates.entrySet())
			achievementstates.put(_e_.getKey(), _e_.getValue());
		counters = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.counters.entrySet())
			counters.put(_e_.getKey(), _e_.getValue());
		countersets = new java.util.HashMap<Integer, xbean.CounterSet>();
		for (java.util.Map.Entry<Integer, xbean.CounterSet> _e_ : _o_.countersets.entrySet())
			countersets.put(_e_.getKey(), new CounterSet(_e_.getValue(), this, "countersets"));
	}

	private void assign(RoleAchievement.Data _o_) {
		achievementstates = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.achievementstates.entrySet())
			achievementstates.put(_e_.getKey(), _e_.getValue());
		counters = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.counters.entrySet())
			counters.put(_e_.getKey(), _e_.getValue());
		countersets = new java.util.HashMap<Integer, xbean.CounterSet>();
		for (java.util.Map.Entry<Integer, xbean.CounterSet> _e_ : _o_.countersets.entrySet())
			countersets.put(_e_.getKey(), new CounterSet(_e_.getValue(), this, "countersets"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)(24576|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(achievementstates.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : achievementstates.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(counters.size());
for (java.util.Map.Entry<Integer, Long> _e_ : counters.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(countersets.size());
for (java.util.Map.Entry<Integer, xbean.CounterSet> _e_ : countersets.entrySet())
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
    				case (24576|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		achievementstates = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		achievementstates.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		counters = new java.util.HashMap<Integer, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		counters.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		countersets = new java.util.HashMap<Integer, xbean.CounterSet>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.CounterSet _v_ = new CounterSet(0, this, "countersets");
		_v_.unmarshal(_os_);
		countersets.put(_k_, _v_);
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
	public xbean.RoleAchievement copy() {
		_xdb_verify_unsafe_();
		return new RoleAchievement(this);
	}

	@Override
	public xbean.RoleAchievement toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleAchievement toBean() {
		_xdb_verify_unsafe_();
		return new RoleAchievement(this); // same as copy()
	}

	@Override
	public xbean.RoleAchievement toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleAchievement toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, Integer> getAchievementstates() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "achievementstates"), achievementstates);
	}

	@Override
	public java.util.Map<Integer, Integer> getAchievementstatesAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> achievementstates;
		RoleAchievement _o_ = this;
		achievementstates = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.achievementstates.entrySet())
			achievementstates.put(_e_.getKey(), _e_.getValue());
		return achievementstates;
	}

	@Override
	public java.util.Map<Integer, Long> getCounters() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "counters"), counters);
	}

	@Override
	public java.util.Map<Integer, Long> getCountersAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Long> counters;
		RoleAchievement _o_ = this;
		counters = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.counters.entrySet())
			counters.put(_e_.getKey(), _e_.getValue());
		return counters;
	}

	@Override
	public java.util.Map<Integer, xbean.CounterSet> getCountersets() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "countersets"), countersets);
	}

	@Override
	public java.util.Map<Integer, xbean.CounterSet> getCountersetsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.CounterSet> countersets;
		RoleAchievement _o_ = this;
		countersets = new java.util.HashMap<Integer, xbean.CounterSet>();
		for (java.util.Map.Entry<Integer, xbean.CounterSet> _e_ : _o_.countersets.entrySet())
			countersets.put(_e_.getKey(), new CounterSet.Data(_e_.getValue()));
		return countersets;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleAchievement _o_ = null;
		if ( _o1_ instanceof RoleAchievement ) _o_ = (RoleAchievement)_o1_;
		else if ( _o1_ instanceof RoleAchievement.Const ) _o_ = ((RoleAchievement.Const)_o1_).nThis();
		else return false;
		if (!achievementstates.equals(_o_.achievementstates)) return false;
		if (!counters.equals(_o_.counters)) return false;
		if (!countersets.equals(_o_.countersets)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += achievementstates.hashCode();
		_h_ += counters.hashCode();
		_h_ += countersets.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(achievementstates);
		_sb_.append(",");
		_sb_.append(counters);
		_sb_.append(",");
		_sb_.append(countersets);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("achievementstates"));
		lb.add(new xdb.logs.ListenableMap().setVarName("counters"));
		lb.add(new xdb.logs.ListenableMap().setVarName("countersets"));
		return lb;
	}

	private class Const implements xbean.RoleAchievement {
		RoleAchievement nThis() {
			return RoleAchievement.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleAchievement copy() {
			return RoleAchievement.this.copy();
		}

		@Override
		public xbean.RoleAchievement toData() {
			return RoleAchievement.this.toData();
		}

		public xbean.RoleAchievement toBean() {
			return RoleAchievement.this.toBean();
		}

		@Override
		public xbean.RoleAchievement toDataIf() {
			return RoleAchievement.this.toDataIf();
		}

		public xbean.RoleAchievement toBeanIf() {
			return RoleAchievement.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, Integer> getAchievementstates() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(achievementstates);
		}

		@Override
		public java.util.Map<Integer, Integer> getAchievementstatesAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> achievementstates;
			RoleAchievement _o_ = RoleAchievement.this;
			achievementstates = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.achievementstates.entrySet())
				achievementstates.put(_e_.getKey(), _e_.getValue());
			return achievementstates;
		}

		@Override
		public java.util.Map<Integer, Long> getCounters() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(counters);
		}

		@Override
		public java.util.Map<Integer, Long> getCountersAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Long> counters;
			RoleAchievement _o_ = RoleAchievement.this;
			counters = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.counters.entrySet())
				counters.put(_e_.getKey(), _e_.getValue());
			return counters;
		}

		@Override
		public java.util.Map<Integer, xbean.CounterSet> getCountersets() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(countersets);
		}

		@Override
		public java.util.Map<Integer, xbean.CounterSet> getCountersetsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.CounterSet> countersets;
			RoleAchievement _o_ = RoleAchievement.this;
			countersets = new java.util.HashMap<Integer, xbean.CounterSet>();
			for (java.util.Map.Entry<Integer, xbean.CounterSet> _e_ : _o_.countersets.entrySet())
				countersets.put(_e_.getKey(), new CounterSet.Data(_e_.getValue()));
			return countersets;
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
			return RoleAchievement.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleAchievement.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleAchievement.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleAchievement.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleAchievement.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleAchievement.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleAchievement.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleAchievement.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleAchievement.this.toString();
		}

	}

	public static final class Data implements xbean.RoleAchievement {
		private java.util.HashMap<Integer, Integer> achievementstates; // 
		private java.util.HashMap<Integer, Long> counters; // 
		private java.util.HashMap<Integer, xbean.CounterSet> countersets; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			achievementstates = new java.util.HashMap<Integer, Integer>();
			counters = new java.util.HashMap<Integer, Long>();
			countersets = new java.util.HashMap<Integer, xbean.CounterSet>();
		}

		Data(xbean.RoleAchievement _o1_) {
			if (_o1_ instanceof RoleAchievement) assign((RoleAchievement)_o1_);
			else if (_o1_ instanceof RoleAchievement.Data) assign((RoleAchievement.Data)_o1_);
			else if (_o1_ instanceof RoleAchievement.Const) assign(((RoleAchievement.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleAchievement _o_) {
			achievementstates = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.achievementstates.entrySet())
				achievementstates.put(_e_.getKey(), _e_.getValue());
			counters = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.counters.entrySet())
				counters.put(_e_.getKey(), _e_.getValue());
			countersets = new java.util.HashMap<Integer, xbean.CounterSet>();
			for (java.util.Map.Entry<Integer, xbean.CounterSet> _e_ : _o_.countersets.entrySet())
				countersets.put(_e_.getKey(), new CounterSet.Data(_e_.getValue()));
		}

		private void assign(RoleAchievement.Data _o_) {
			achievementstates = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.achievementstates.entrySet())
				achievementstates.put(_e_.getKey(), _e_.getValue());
			counters = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.counters.entrySet())
				counters.put(_e_.getKey(), _e_.getValue());
			countersets = new java.util.HashMap<Integer, xbean.CounterSet>();
			for (java.util.Map.Entry<Integer, xbean.CounterSet> _e_ : _o_.countersets.entrySet())
				countersets.put(_e_.getKey(), new CounterSet.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)(24576|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(achievementstates.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : achievementstates.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(counters.size());
for (java.util.Map.Entry<Integer, Long> _e_ : counters.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(countersets.size());
for (java.util.Map.Entry<Integer, xbean.CounterSet> _e_ : countersets.entrySet())
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
					case (24576|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		achievementstates = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		achievementstates.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		counters = new java.util.HashMap<Integer, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		counters.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		countersets = new java.util.HashMap<Integer, xbean.CounterSet>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.CounterSet _v_ = xbean.Pod.newCounterSetData();
		_v_.unmarshal(_os_);
		countersets.put(_k_, _v_);
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
		public xbean.RoleAchievement copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleAchievement toData() {
			return new Data(this);
		}

		public xbean.RoleAchievement toBean() {
			return new RoleAchievement(this, null, null);
		}

		@Override
		public xbean.RoleAchievement toDataIf() {
			return this;
		}

		public xbean.RoleAchievement toBeanIf() {
			return new RoleAchievement(this, null, null);
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
		public java.util.Map<Integer, Integer> getAchievementstates() { // 
			return achievementstates;
		}

		@Override
		public java.util.Map<Integer, Integer> getAchievementstatesAsData() { // 
			return achievementstates;
		}

		@Override
		public java.util.Map<Integer, Long> getCounters() { // 
			return counters;
		}

		@Override
		public java.util.Map<Integer, Long> getCountersAsData() { // 
			return counters;
		}

		@Override
		public java.util.Map<Integer, xbean.CounterSet> getCountersets() { // 
			return countersets;
		}

		@Override
		public java.util.Map<Integer, xbean.CounterSet> getCountersetsAsData() { // 
			return countersets;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleAchievement.Data)) return false;
			RoleAchievement.Data _o_ = (RoleAchievement.Data) _o1_;
			if (!achievementstates.equals(_o_.achievementstates)) return false;
			if (!counters.equals(_o_.counters)) return false;
			if (!countersets.equals(_o_.countersets)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += achievementstates.hashCode();
			_h_ += counters.hashCode();
			_h_ += countersets.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(achievementstates);
			_sb_.append(",");
			_sb_.append(counters);
			_sb_.append(",");
			_sb_.append(countersets);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
