
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class HuiWuProfessionTerm extends xdb.XBean implements xbean.HuiWuProfessionTerm {
	private int profession; // 
	private xdb.util.SetX<Long> enrollroleids; // 报名玩家
	private java.util.LinkedList<Long> preselection1roleids; // 筛选玩家
	private java.util.LinkedList<Long> preselection2roleids; // 预选玩家
	private java.util.HashMap<Long, Integer> preselection2roleidbeguessnums; // 预选玩家
	private java.util.HashMap<Integer, xbean.HuiWuRound> rounds; // round -> HuiWuRound
	private long champion; // 

	@Override
	public void _reset_unsafe_() {
		profession = 0;
		enrollroleids.clear();
		preselection1roleids.clear();
		preselection2roleids.clear();
		preselection2roleidbeguessnums.clear();
		rounds.clear();
		champion = 0L;
	}

	HuiWuProfessionTerm(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		enrollroleids = new xdb.util.SetX<Long>();
		preselection1roleids = new java.util.LinkedList<Long>();
		preselection2roleids = new java.util.LinkedList<Long>();
		preselection2roleidbeguessnums = new java.util.HashMap<Long, Integer>();
		rounds = new java.util.HashMap<Integer, xbean.HuiWuRound>();
	}

	public HuiWuProfessionTerm() {
		this(0, null, null);
	}

	public HuiWuProfessionTerm(HuiWuProfessionTerm _o_) {
		this(_o_, null, null);
	}

	HuiWuProfessionTerm(xbean.HuiWuProfessionTerm _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof HuiWuProfessionTerm) assign((HuiWuProfessionTerm)_o1_);
		else if (_o1_ instanceof HuiWuProfessionTerm.Data) assign((HuiWuProfessionTerm.Data)_o1_);
		else if (_o1_ instanceof HuiWuProfessionTerm.Const) assign(((HuiWuProfessionTerm.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(HuiWuProfessionTerm _o_) {
		_o_._xdb_verify_unsafe_();
		profession = _o_.profession;
		enrollroleids = new xdb.util.SetX<Long>();
		enrollroleids.addAll(_o_.enrollroleids);
		preselection1roleids = new java.util.LinkedList<Long>();
		preselection1roleids.addAll(_o_.preselection1roleids);
		preselection2roleids = new java.util.LinkedList<Long>();
		preselection2roleids.addAll(_o_.preselection2roleids);
		preselection2roleidbeguessnums = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.preselection2roleidbeguessnums.entrySet())
			preselection2roleidbeguessnums.put(_e_.getKey(), _e_.getValue());
		rounds = new java.util.HashMap<Integer, xbean.HuiWuRound>();
		for (java.util.Map.Entry<Integer, xbean.HuiWuRound> _e_ : _o_.rounds.entrySet())
			rounds.put(_e_.getKey(), new HuiWuRound(_e_.getValue(), this, "rounds"));
		champion = _o_.champion;
	}

	private void assign(HuiWuProfessionTerm.Data _o_) {
		profession = _o_.profession;
		enrollroleids = new xdb.util.SetX<Long>();
		enrollroleids.addAll(_o_.enrollroleids);
		preselection1roleids = new java.util.LinkedList<Long>();
		preselection1roleids.addAll(_o_.preselection1roleids);
		preselection2roleids = new java.util.LinkedList<Long>();
		preselection2roleids.addAll(_o_.preselection2roleids);
		preselection2roleidbeguessnums = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.preselection2roleidbeguessnums.entrySet())
			preselection2roleidbeguessnums.put(_e_.getKey(), _e_.getValue());
		rounds = new java.util.HashMap<Integer, xbean.HuiWuRound>();
		for (java.util.Map.Entry<Integer, xbean.HuiWuRound> _e_ : _o_.rounds.entrySet())
			rounds.put(_e_.getKey(), new HuiWuRound(_e_.getValue(), this, "rounds"));
		champion = _o_.champion;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)7);
    _os_.marshal((short)( 8192|  7));_os_.marshal(profession);
    _os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(enrollroleids.size());
for (Long _v_ : enrollroleids) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(preselection1roleids.size());
for (Long _v_ : preselection1roleids) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(preselection2roleids.size());
for (Long _v_ : preselection2roleids) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(preselection2roleidbeguessnums.size());
for (java.util.Map.Entry<Long, Integer> _e_ : preselection2roleidbeguessnums.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(rounds.size());
for (java.util.Map.Entry<Integer, xbean.HuiWuRound> _e_ : rounds.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  6));_os_.marshal(champion);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  7):profession = _os_.unmarshal_int();
    				break;
    				case ( 6144|  7):profession = _os_.unmarshal_short();
    				break;
    				case (20480|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	enrollroleids.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	preselection1roleids.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	preselection2roleids.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		preselection2roleidbeguessnums = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		preselection2roleidbeguessnums.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		rounds = new java.util.HashMap<Integer, xbean.HuiWuRound>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.HuiWuRound _v_ = new HuiWuRound(0, this, "rounds");
		_v_.unmarshal(_os_);
		rounds.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (10240|  6):champion = _os_.unmarshal_long();
    				break;
    				case ( 6144|  6):champion = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):champion = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.HuiWuProfessionTerm copy() {
		_xdb_verify_unsafe_();
		return new HuiWuProfessionTerm(this);
	}

	@Override
	public xbean.HuiWuProfessionTerm toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuProfessionTerm toBean() {
		_xdb_verify_unsafe_();
		return new HuiWuProfessionTerm(this); // same as copy()
	}

	@Override
	public xbean.HuiWuProfessionTerm toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuProfessionTerm toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getProfession() { // 
		_xdb_verify_unsafe_();
		return profession;
	}

	@Override
	public java.util.Set<Long> getEnrollroleids() { // 报名玩家
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "enrollroleids"), enrollroleids);
	}

	public java.util.Set<Long> getEnrollroleidsAsData() { // 报名玩家
		_xdb_verify_unsafe_();
		java.util.Set<Long> enrollroleids;
		HuiWuProfessionTerm _o_ = this;
		enrollroleids = new xdb.util.SetX<Long>();
		enrollroleids.addAll(_o_.enrollroleids);
		return enrollroleids;
	}

	@Override
	public java.util.List<Long> getPreselection1roleids() { // 筛选玩家
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "preselection1roleids"), preselection1roleids);
	}

	public java.util.List<Long> getPreselection1roleidsAsData() { // 筛选玩家
		_xdb_verify_unsafe_();
		java.util.List<Long> preselection1roleids;
		HuiWuProfessionTerm _o_ = this;
		preselection1roleids = new java.util.LinkedList<Long>();
		preselection1roleids.addAll(_o_.preselection1roleids);
		return preselection1roleids;
	}

	@Override
	public java.util.List<Long> getPreselection2roleids() { // 预选玩家
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "preselection2roleids"), preselection2roleids);
	}

	public java.util.List<Long> getPreselection2roleidsAsData() { // 预选玩家
		_xdb_verify_unsafe_();
		java.util.List<Long> preselection2roleids;
		HuiWuProfessionTerm _o_ = this;
		preselection2roleids = new java.util.LinkedList<Long>();
		preselection2roleids.addAll(_o_.preselection2roleids);
		return preselection2roleids;
	}

	@Override
	public java.util.Map<Long, Integer> getPreselection2roleidbeguessnums() { // 预选玩家
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "preselection2roleidbeguessnums"), preselection2roleidbeguessnums);
	}

	@Override
	public java.util.Map<Long, Integer> getPreselection2roleidbeguessnumsAsData() { // 预选玩家
		_xdb_verify_unsafe_();
		java.util.Map<Long, Integer> preselection2roleidbeguessnums;
		HuiWuProfessionTerm _o_ = this;
		preselection2roleidbeguessnums = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.preselection2roleidbeguessnums.entrySet())
			preselection2roleidbeguessnums.put(_e_.getKey(), _e_.getValue());
		return preselection2roleidbeguessnums;
	}

	@Override
	public java.util.Map<Integer, xbean.HuiWuRound> getRounds() { // round -> HuiWuRound
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "rounds"), rounds);
	}

	@Override
	public java.util.Map<Integer, xbean.HuiWuRound> getRoundsAsData() { // round -> HuiWuRound
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.HuiWuRound> rounds;
		HuiWuProfessionTerm _o_ = this;
		rounds = new java.util.HashMap<Integer, xbean.HuiWuRound>();
		for (java.util.Map.Entry<Integer, xbean.HuiWuRound> _e_ : _o_.rounds.entrySet())
			rounds.put(_e_.getKey(), new HuiWuRound.Data(_e_.getValue()));
		return rounds;
	}

	@Override
	public long getChampion() { // 
		_xdb_verify_unsafe_();
		return champion;
	}

	@Override
	public void setProfession(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "profession") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, profession) {
					public void rollback() { profession = _xdb_saved; }
				};}});
		profession = _v_;
	}

	@Override
	public void setChampion(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "champion") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, champion) {
					public void rollback() { champion = _xdb_saved; }
				};}});
		champion = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		HuiWuProfessionTerm _o_ = null;
		if ( _o1_ instanceof HuiWuProfessionTerm ) _o_ = (HuiWuProfessionTerm)_o1_;
		else if ( _o1_ instanceof HuiWuProfessionTerm.Const ) _o_ = ((HuiWuProfessionTerm.Const)_o1_).nThis();
		else return false;
		if (profession != _o_.profession) return false;
		if (!enrollroleids.equals(_o_.enrollroleids)) return false;
		if (!preselection1roleids.equals(_o_.preselection1roleids)) return false;
		if (!preselection2roleids.equals(_o_.preselection2roleids)) return false;
		if (!preselection2roleidbeguessnums.equals(_o_.preselection2roleidbeguessnums)) return false;
		if (!rounds.equals(_o_.rounds)) return false;
		if (champion != _o_.champion) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += profession;
		_h_ += enrollroleids.hashCode();
		_h_ += preselection1roleids.hashCode();
		_h_ += preselection2roleids.hashCode();
		_h_ += preselection2roleidbeguessnums.hashCode();
		_h_ += rounds.hashCode();
		_h_ += champion;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(profession);
		_sb_.append(",");
		_sb_.append(enrollroleids);
		_sb_.append(",");
		_sb_.append(preselection1roleids);
		_sb_.append(",");
		_sb_.append(preselection2roleids);
		_sb_.append(",");
		_sb_.append(preselection2roleidbeguessnums);
		_sb_.append(",");
		_sb_.append(rounds);
		_sb_.append(",");
		_sb_.append(champion);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("profession"));
		lb.add(new xdb.logs.ListenableSet().setVarName("enrollroleids"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("preselection1roleids"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("preselection2roleids"));
		lb.add(new xdb.logs.ListenableMap().setVarName("preselection2roleidbeguessnums"));
		lb.add(new xdb.logs.ListenableMap().setVarName("rounds"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("champion"));
		return lb;
	}

	private class Const implements xbean.HuiWuProfessionTerm {
		HuiWuProfessionTerm nThis() {
			return HuiWuProfessionTerm.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.HuiWuProfessionTerm copy() {
			return HuiWuProfessionTerm.this.copy();
		}

		@Override
		public xbean.HuiWuProfessionTerm toData() {
			return HuiWuProfessionTerm.this.toData();
		}

		public xbean.HuiWuProfessionTerm toBean() {
			return HuiWuProfessionTerm.this.toBean();
		}

		@Override
		public xbean.HuiWuProfessionTerm toDataIf() {
			return HuiWuProfessionTerm.this.toDataIf();
		}

		public xbean.HuiWuProfessionTerm toBeanIf() {
			return HuiWuProfessionTerm.this.toBeanIf();
		}

		@Override
		public int getProfession() { // 
			_xdb_verify_unsafe_();
			return profession;
		}

		@Override
		public java.util.Set<Long> getEnrollroleids() { // 报名玩家
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(enrollroleids);
		}

		public java.util.Set<Long> getEnrollroleidsAsData() { // 报名玩家
			_xdb_verify_unsafe_();
			java.util.Set<Long> enrollroleids;
			HuiWuProfessionTerm _o_ = HuiWuProfessionTerm.this;
		enrollroleids = new xdb.util.SetX<Long>();
		enrollroleids.addAll(_o_.enrollroleids);
			return enrollroleids;
		}

		@Override
		public java.util.List<Long> getPreselection1roleids() { // 筛选玩家
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(preselection1roleids);
		}

		public java.util.List<Long> getPreselection1roleidsAsData() { // 筛选玩家
			_xdb_verify_unsafe_();
			java.util.List<Long> preselection1roleids;
			HuiWuProfessionTerm _o_ = HuiWuProfessionTerm.this;
		preselection1roleids = new java.util.LinkedList<Long>();
		preselection1roleids.addAll(_o_.preselection1roleids);
			return preselection1roleids;
		}

		@Override
		public java.util.List<Long> getPreselection2roleids() { // 预选玩家
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(preselection2roleids);
		}

		public java.util.List<Long> getPreselection2roleidsAsData() { // 预选玩家
			_xdb_verify_unsafe_();
			java.util.List<Long> preselection2roleids;
			HuiWuProfessionTerm _o_ = HuiWuProfessionTerm.this;
		preselection2roleids = new java.util.LinkedList<Long>();
		preselection2roleids.addAll(_o_.preselection2roleids);
			return preselection2roleids;
		}

		@Override
		public java.util.Map<Long, Integer> getPreselection2roleidbeguessnums() { // 预选玩家
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(preselection2roleidbeguessnums);
		}

		@Override
		public java.util.Map<Long, Integer> getPreselection2roleidbeguessnumsAsData() { // 预选玩家
			_xdb_verify_unsafe_();
			java.util.Map<Long, Integer> preselection2roleidbeguessnums;
			HuiWuProfessionTerm _o_ = HuiWuProfessionTerm.this;
			preselection2roleidbeguessnums = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.preselection2roleidbeguessnums.entrySet())
				preselection2roleidbeguessnums.put(_e_.getKey(), _e_.getValue());
			return preselection2roleidbeguessnums;
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuRound> getRounds() { // round -> HuiWuRound
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(rounds);
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuRound> getRoundsAsData() { // round -> HuiWuRound
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.HuiWuRound> rounds;
			HuiWuProfessionTerm _o_ = HuiWuProfessionTerm.this;
			rounds = new java.util.HashMap<Integer, xbean.HuiWuRound>();
			for (java.util.Map.Entry<Integer, xbean.HuiWuRound> _e_ : _o_.rounds.entrySet())
				rounds.put(_e_.getKey(), new HuiWuRound.Data(_e_.getValue()));
			return rounds;
		}

		@Override
		public long getChampion() { // 
			_xdb_verify_unsafe_();
			return champion;
		}

		@Override
		public void setProfession(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setChampion(long _v_) { // 
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
			return HuiWuProfessionTerm.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return HuiWuProfessionTerm.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return HuiWuProfessionTerm.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return HuiWuProfessionTerm.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return HuiWuProfessionTerm.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return HuiWuProfessionTerm.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return HuiWuProfessionTerm.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return HuiWuProfessionTerm.this.hashCode();
		}

		@Override
		public String toString() {
			return HuiWuProfessionTerm.this.toString();
		}

	}

	public static final class Data implements xbean.HuiWuProfessionTerm {
		private int profession; // 
		private java.util.HashSet<Long> enrollroleids; // 报名玩家
		private java.util.LinkedList<Long> preselection1roleids; // 筛选玩家
		private java.util.LinkedList<Long> preselection2roleids; // 预选玩家
		private java.util.HashMap<Long, Integer> preselection2roleidbeguessnums; // 预选玩家
		private java.util.HashMap<Integer, xbean.HuiWuRound> rounds; // round -> HuiWuRound
		private long champion; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			enrollroleids = new java.util.HashSet<Long>();
			preselection1roleids = new java.util.LinkedList<Long>();
			preselection2roleids = new java.util.LinkedList<Long>();
			preselection2roleidbeguessnums = new java.util.HashMap<Long, Integer>();
			rounds = new java.util.HashMap<Integer, xbean.HuiWuRound>();
		}

		Data(xbean.HuiWuProfessionTerm _o1_) {
			if (_o1_ instanceof HuiWuProfessionTerm) assign((HuiWuProfessionTerm)_o1_);
			else if (_o1_ instanceof HuiWuProfessionTerm.Data) assign((HuiWuProfessionTerm.Data)_o1_);
			else if (_o1_ instanceof HuiWuProfessionTerm.Const) assign(((HuiWuProfessionTerm.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(HuiWuProfessionTerm _o_) {
			profession = _o_.profession;
			enrollroleids = new java.util.HashSet<Long>();
			enrollroleids.addAll(_o_.enrollroleids);
			preselection1roleids = new java.util.LinkedList<Long>();
			preselection1roleids.addAll(_o_.preselection1roleids);
			preselection2roleids = new java.util.LinkedList<Long>();
			preselection2roleids.addAll(_o_.preselection2roleids);
			preselection2roleidbeguessnums = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.preselection2roleidbeguessnums.entrySet())
				preselection2roleidbeguessnums.put(_e_.getKey(), _e_.getValue());
			rounds = new java.util.HashMap<Integer, xbean.HuiWuRound>();
			for (java.util.Map.Entry<Integer, xbean.HuiWuRound> _e_ : _o_.rounds.entrySet())
				rounds.put(_e_.getKey(), new HuiWuRound.Data(_e_.getValue()));
			champion = _o_.champion;
		}

		private void assign(HuiWuProfessionTerm.Data _o_) {
			profession = _o_.profession;
			enrollroleids = new java.util.HashSet<Long>();
			enrollroleids.addAll(_o_.enrollroleids);
			preselection1roleids = new java.util.LinkedList<Long>();
			preselection1roleids.addAll(_o_.preselection1roleids);
			preselection2roleids = new java.util.LinkedList<Long>();
			preselection2roleids.addAll(_o_.preselection2roleids);
			preselection2roleidbeguessnums = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.preselection2roleidbeguessnums.entrySet())
				preselection2roleidbeguessnums.put(_e_.getKey(), _e_.getValue());
			rounds = new java.util.HashMap<Integer, xbean.HuiWuRound>();
			for (java.util.Map.Entry<Integer, xbean.HuiWuRound> _e_ : _o_.rounds.entrySet())
				rounds.put(_e_.getKey(), new HuiWuRound.Data(_e_.getValue()));
			champion = _o_.champion;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)7);
	_os_.marshal((short)( 8192|  7));_os_.marshal(profession);
	_os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(enrollroleids.size());
for (Long _v_ : enrollroleids) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(preselection1roleids.size());
for (Long _v_ : preselection1roleids) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(preselection2roleids.size());
for (Long _v_ : preselection2roleids) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(preselection2roleidbeguessnums.size());
for (java.util.Map.Entry<Long, Integer> _e_ : preselection2roleidbeguessnums.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(rounds.size());
for (java.util.Map.Entry<Integer, xbean.HuiWuRound> _e_ : rounds.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  6));_os_.marshal(champion);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  7):profession = _os_.unmarshal_int();
					break;
					case ( 6144|  7):profession = _os_.unmarshal_short();
					break;
					case (20480|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	enrollroleids.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	preselection1roleids.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	preselection2roleids.add(_v_);
}
_os_ = _temp_;}
					break;
					case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		preselection2roleidbeguessnums = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		preselection2roleidbeguessnums.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		rounds = new java.util.HashMap<Integer, xbean.HuiWuRound>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.HuiWuRound _v_ = xbean.Pod.newHuiWuRoundData();
		_v_.unmarshal(_os_);
		rounds.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (10240|  6):champion = _os_.unmarshal_long();
					break;
					case ( 6144|  6):champion = _os_.unmarshal_short();
					break;
					case ( 8192|  6):champion = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.HuiWuProfessionTerm copy() {
			return new Data(this);
		}

		@Override
		public xbean.HuiWuProfessionTerm toData() {
			return new Data(this);
		}

		public xbean.HuiWuProfessionTerm toBean() {
			return new HuiWuProfessionTerm(this, null, null);
		}

		@Override
		public xbean.HuiWuProfessionTerm toDataIf() {
			return this;
		}

		public xbean.HuiWuProfessionTerm toBeanIf() {
			return new HuiWuProfessionTerm(this, null, null);
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
		public int getProfession() { // 
			return profession;
		}

		@Override
		public java.util.Set<Long> getEnrollroleids() { // 报名玩家
			return enrollroleids;
		}

		@Override
		public java.util.Set<Long> getEnrollroleidsAsData() { // 报名玩家
			return enrollroleids;
		}

		@Override
		public java.util.List<Long> getPreselection1roleids() { // 筛选玩家
			return preselection1roleids;
		}

		@Override
		public java.util.List<Long> getPreselection1roleidsAsData() { // 筛选玩家
			return preselection1roleids;
		}

		@Override
		public java.util.List<Long> getPreselection2roleids() { // 预选玩家
			return preselection2roleids;
		}

		@Override
		public java.util.List<Long> getPreselection2roleidsAsData() { // 预选玩家
			return preselection2roleids;
		}

		@Override
		public java.util.Map<Long, Integer> getPreselection2roleidbeguessnums() { // 预选玩家
			return preselection2roleidbeguessnums;
		}

		@Override
		public java.util.Map<Long, Integer> getPreselection2roleidbeguessnumsAsData() { // 预选玩家
			return preselection2roleidbeguessnums;
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuRound> getRounds() { // round -> HuiWuRound
			return rounds;
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuRound> getRoundsAsData() { // round -> HuiWuRound
			return rounds;
		}

		@Override
		public long getChampion() { // 
			return champion;
		}

		@Override
		public void setProfession(int _v_) { // 
			profession = _v_;
		}

		@Override
		public void setChampion(long _v_) { // 
			champion = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof HuiWuProfessionTerm.Data)) return false;
			HuiWuProfessionTerm.Data _o_ = (HuiWuProfessionTerm.Data) _o1_;
			if (profession != _o_.profession) return false;
			if (!enrollroleids.equals(_o_.enrollroleids)) return false;
			if (!preselection1roleids.equals(_o_.preselection1roleids)) return false;
			if (!preselection2roleids.equals(_o_.preselection2roleids)) return false;
			if (!preselection2roleidbeguessnums.equals(_o_.preselection2roleidbeguessnums)) return false;
			if (!rounds.equals(_o_.rounds)) return false;
			if (champion != _o_.champion) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += profession;
			_h_ += enrollroleids.hashCode();
			_h_ += preselection1roleids.hashCode();
			_h_ += preselection2roleids.hashCode();
			_h_ += preselection2roleidbeguessnums.hashCode();
			_h_ += rounds.hashCode();
			_h_ += champion;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(profession);
			_sb_.append(",");
			_sb_.append(enrollroleids);
			_sb_.append(",");
			_sb_.append(preselection1roleids);
			_sb_.append(",");
			_sb_.append(preselection2roleids);
			_sb_.append(",");
			_sb_.append(preselection2roleidbeguessnums);
			_sb_.append(",");
			_sb_.append(rounds);
			_sb_.append(",");
			_sb_.append(champion);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
