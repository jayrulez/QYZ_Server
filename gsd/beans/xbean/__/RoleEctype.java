
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleEctype extends xdb.XBean implements xbean.RoleEctype {
	private java.util.HashMap<Integer, xbean.ClimbTowerInfo> climbtowers; // 
	private java.util.HashMap<Integer, xbean.ChapterInfo> chapters; // 
	private java.util.HashMap<Integer, xbean.DailyInfo> dailys; // key->ectypetype
	private java.util.HashMap<Integer, Integer> multistory; // 多人剧情副本挑战的得星记录
	private java.util.HashMap<Integer, xbean.HeroesGroupInfo> herogroups; // groupid->HeroesGroupInfo
	private xbean.TeamFightInfo teamfight; // 
	private int matchtype; // cfg.ectype.MatchType
	private long nextmatchtime; // 
	private int multiectypid; // 如果是在匹配多人剧情，需要记一下

	@Override
	public void _reset_unsafe_() {
		climbtowers.clear();
		chapters.clear();
		dailys.clear();
		multistory.clear();
		herogroups.clear();
		teamfight._reset_unsafe_();
		matchtype = 0;
		nextmatchtime = 0L;
		multiectypid = 0;
	}

	RoleEctype(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		climbtowers = new java.util.HashMap<Integer, xbean.ClimbTowerInfo>();
		chapters = new java.util.HashMap<Integer, xbean.ChapterInfo>();
		dailys = new java.util.HashMap<Integer, xbean.DailyInfo>();
		multistory = new java.util.HashMap<Integer, Integer>();
		herogroups = new java.util.HashMap<Integer, xbean.HeroesGroupInfo>();
		teamfight = new TeamFightInfo(0, this, "teamfight");
	}

	public RoleEctype() {
		this(0, null, null);
	}

	public RoleEctype(RoleEctype _o_) {
		this(_o_, null, null);
	}

	RoleEctype(xbean.RoleEctype _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleEctype) assign((RoleEctype)_o1_);
		else if (_o1_ instanceof RoleEctype.Data) assign((RoleEctype.Data)_o1_);
		else if (_o1_ instanceof RoleEctype.Const) assign(((RoleEctype.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleEctype _o_) {
		_o_._xdb_verify_unsafe_();
		climbtowers = new java.util.HashMap<Integer, xbean.ClimbTowerInfo>();
		for (java.util.Map.Entry<Integer, xbean.ClimbTowerInfo> _e_ : _o_.climbtowers.entrySet())
			climbtowers.put(_e_.getKey(), new ClimbTowerInfo(_e_.getValue(), this, "climbtowers"));
		chapters = new java.util.HashMap<Integer, xbean.ChapterInfo>();
		for (java.util.Map.Entry<Integer, xbean.ChapterInfo> _e_ : _o_.chapters.entrySet())
			chapters.put(_e_.getKey(), new ChapterInfo(_e_.getValue(), this, "chapters"));
		dailys = new java.util.HashMap<Integer, xbean.DailyInfo>();
		for (java.util.Map.Entry<Integer, xbean.DailyInfo> _e_ : _o_.dailys.entrySet())
			dailys.put(_e_.getKey(), new DailyInfo(_e_.getValue(), this, "dailys"));
		multistory = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.multistory.entrySet())
			multistory.put(_e_.getKey(), _e_.getValue());
		herogroups = new java.util.HashMap<Integer, xbean.HeroesGroupInfo>();
		for (java.util.Map.Entry<Integer, xbean.HeroesGroupInfo> _e_ : _o_.herogroups.entrySet())
			herogroups.put(_e_.getKey(), new HeroesGroupInfo(_e_.getValue(), this, "herogroups"));
		teamfight = new TeamFightInfo(_o_.teamfight, this, "teamfight");
		matchtype = _o_.matchtype;
		nextmatchtime = _o_.nextmatchtime;
		multiectypid = _o_.multiectypid;
	}

	private void assign(RoleEctype.Data _o_) {
		climbtowers = new java.util.HashMap<Integer, xbean.ClimbTowerInfo>();
		for (java.util.Map.Entry<Integer, xbean.ClimbTowerInfo> _e_ : _o_.climbtowers.entrySet())
			climbtowers.put(_e_.getKey(), new ClimbTowerInfo(_e_.getValue(), this, "climbtowers"));
		chapters = new java.util.HashMap<Integer, xbean.ChapterInfo>();
		for (java.util.Map.Entry<Integer, xbean.ChapterInfo> _e_ : _o_.chapters.entrySet())
			chapters.put(_e_.getKey(), new ChapterInfo(_e_.getValue(), this, "chapters"));
		dailys = new java.util.HashMap<Integer, xbean.DailyInfo>();
		for (java.util.Map.Entry<Integer, xbean.DailyInfo> _e_ : _o_.dailys.entrySet())
			dailys.put(_e_.getKey(), new DailyInfo(_e_.getValue(), this, "dailys"));
		multistory = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.multistory.entrySet())
			multistory.put(_e_.getKey(), _e_.getValue());
		herogroups = new java.util.HashMap<Integer, xbean.HeroesGroupInfo>();
		for (java.util.Map.Entry<Integer, xbean.HeroesGroupInfo> _e_ : _o_.herogroups.entrySet())
			herogroups.put(_e_.getKey(), new HeroesGroupInfo(_e_.getValue(), this, "herogroups"));
		teamfight = new TeamFightInfo(_o_.teamfight, this, "teamfight");
		matchtype = _o_.matchtype;
		nextmatchtime = _o_.nextmatchtime;
		multiectypid = _o_.multiectypid;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)9);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(climbtowers.size());
for (java.util.Map.Entry<Integer, xbean.ClimbTowerInfo> _e_ : climbtowers.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(chapters.size());
for (java.util.Map.Entry<Integer, xbean.ChapterInfo> _e_ : chapters.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(dailys.size());
for (java.util.Map.Entry<Integer, xbean.DailyInfo> _e_ : dailys.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(multistory.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : multistory.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(herogroups.size());
for (java.util.Map.Entry<Integer, xbean.HeroesGroupInfo> _e_ : herogroups.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(26624| 11));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
teamfight.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192| 14));_os_.marshal(matchtype);
    _os_.marshal((short)(10240| 13));_os_.marshal(nextmatchtime);
    _os_.marshal((short)( 8192| 15));_os_.marshal(multiectypid);
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
		climbtowers = new java.util.HashMap<Integer, xbean.ClimbTowerInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.ClimbTowerInfo _v_ = new ClimbTowerInfo(0, this, "climbtowers");
		_v_.unmarshal(_os_);
		climbtowers.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		chapters = new java.util.HashMap<Integer, xbean.ChapterInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.ChapterInfo _v_ = new ChapterInfo(0, this, "chapters");
		_v_.unmarshal(_os_);
		chapters.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		dailys = new java.util.HashMap<Integer, xbean.DailyInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.DailyInfo _v_ = new DailyInfo(0, this, "dailys");
		_v_.unmarshal(_os_);
		dailys.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		multistory = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		multistory.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		herogroups = new java.util.HashMap<Integer, xbean.HeroesGroupInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.HeroesGroupInfo _v_ = new HeroesGroupInfo(0, this, "herogroups");
		_v_.unmarshal(_os_);
		herogroups.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (26624| 11):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
teamfight.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case ( 8192| 14):matchtype = _os_.unmarshal_int();
    				break;
    				case ( 6144| 14):matchtype = _os_.unmarshal_short();
    				break;
    				case (10240| 13):nextmatchtime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 13):nextmatchtime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 13):nextmatchtime = _os_.unmarshal_int();
    				break;
    				case ( 8192| 15):multiectypid = _os_.unmarshal_int();
    				break;
    				case ( 6144| 15):multiectypid = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleEctype copy() {
		_xdb_verify_unsafe_();
		return new RoleEctype(this);
	}

	@Override
	public xbean.RoleEctype toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleEctype toBean() {
		_xdb_verify_unsafe_();
		return new RoleEctype(this); // same as copy()
	}

	@Override
	public xbean.RoleEctype toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleEctype toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.ClimbTowerInfo> getClimbtowers() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "climbtowers"), climbtowers);
	}

	@Override
	public java.util.Map<Integer, xbean.ClimbTowerInfo> getClimbtowersAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.ClimbTowerInfo> climbtowers;
		RoleEctype _o_ = this;
		climbtowers = new java.util.HashMap<Integer, xbean.ClimbTowerInfo>();
		for (java.util.Map.Entry<Integer, xbean.ClimbTowerInfo> _e_ : _o_.climbtowers.entrySet())
			climbtowers.put(_e_.getKey(), new ClimbTowerInfo.Data(_e_.getValue()));
		return climbtowers;
	}

	@Override
	public java.util.Map<Integer, xbean.ChapterInfo> getChapters() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "chapters"), chapters);
	}

	@Override
	public java.util.Map<Integer, xbean.ChapterInfo> getChaptersAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.ChapterInfo> chapters;
		RoleEctype _o_ = this;
		chapters = new java.util.HashMap<Integer, xbean.ChapterInfo>();
		for (java.util.Map.Entry<Integer, xbean.ChapterInfo> _e_ : _o_.chapters.entrySet())
			chapters.put(_e_.getKey(), new ChapterInfo.Data(_e_.getValue()));
		return chapters;
	}

	@Override
	public java.util.Map<Integer, xbean.DailyInfo> getDailys() { // key->ectypetype
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "dailys"), dailys);
	}

	@Override
	public java.util.Map<Integer, xbean.DailyInfo> getDailysAsData() { // key->ectypetype
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.DailyInfo> dailys;
		RoleEctype _o_ = this;
		dailys = new java.util.HashMap<Integer, xbean.DailyInfo>();
		for (java.util.Map.Entry<Integer, xbean.DailyInfo> _e_ : _o_.dailys.entrySet())
			dailys.put(_e_.getKey(), new DailyInfo.Data(_e_.getValue()));
		return dailys;
	}

	@Override
	public java.util.Map<Integer, Integer> getMultistory() { // 多人剧情副本挑战的得星记录
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "multistory"), multistory);
	}

	@Override
	public java.util.Map<Integer, Integer> getMultistoryAsData() { // 多人剧情副本挑战的得星记录
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> multistory;
		RoleEctype _o_ = this;
		multistory = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.multistory.entrySet())
			multistory.put(_e_.getKey(), _e_.getValue());
		return multistory;
	}

	@Override
	public java.util.Map<Integer, xbean.HeroesGroupInfo> getHerogroups() { // groupid->HeroesGroupInfo
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "herogroups"), herogroups);
	}

	@Override
	public java.util.Map<Integer, xbean.HeroesGroupInfo> getHerogroupsAsData() { // groupid->HeroesGroupInfo
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.HeroesGroupInfo> herogroups;
		RoleEctype _o_ = this;
		herogroups = new java.util.HashMap<Integer, xbean.HeroesGroupInfo>();
		for (java.util.Map.Entry<Integer, xbean.HeroesGroupInfo> _e_ : _o_.herogroups.entrySet())
			herogroups.put(_e_.getKey(), new HeroesGroupInfo.Data(_e_.getValue()));
		return herogroups;
	}

	@Override
	public xbean.TeamFightInfo getTeamfight() { // 
		_xdb_verify_unsafe_();
		return teamfight;
	}

	@Override
	public int getMatchtype() { // cfg.ectype.MatchType
		_xdb_verify_unsafe_();
		return matchtype;
	}

	@Override
	public long getNextmatchtime() { // 
		_xdb_verify_unsafe_();
		return nextmatchtime;
	}

	@Override
	public int getMultiectypid() { // 如果是在匹配多人剧情，需要记一下
		_xdb_verify_unsafe_();
		return multiectypid;
	}

	@Override
	public void setMatchtype(int _v_) { // cfg.ectype.MatchType
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "matchtype") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, matchtype) {
					public void rollback() { matchtype = _xdb_saved; }
				};}});
		matchtype = _v_;
	}

	@Override
	public void setNextmatchtime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "nextmatchtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, nextmatchtime) {
					public void rollback() { nextmatchtime = _xdb_saved; }
				};}});
		nextmatchtime = _v_;
	}

	@Override
	public void setMultiectypid(int _v_) { // 如果是在匹配多人剧情，需要记一下
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "multiectypid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, multiectypid) {
					public void rollback() { multiectypid = _xdb_saved; }
				};}});
		multiectypid = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleEctype _o_ = null;
		if ( _o1_ instanceof RoleEctype ) _o_ = (RoleEctype)_o1_;
		else if ( _o1_ instanceof RoleEctype.Const ) _o_ = ((RoleEctype.Const)_o1_).nThis();
		else return false;
		if (!climbtowers.equals(_o_.climbtowers)) return false;
		if (!chapters.equals(_o_.chapters)) return false;
		if (!dailys.equals(_o_.dailys)) return false;
		if (!multistory.equals(_o_.multistory)) return false;
		if (!herogroups.equals(_o_.herogroups)) return false;
		if (!teamfight.equals(_o_.teamfight)) return false;
		if (matchtype != _o_.matchtype) return false;
		if (nextmatchtime != _o_.nextmatchtime) return false;
		if (multiectypid != _o_.multiectypid) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += climbtowers.hashCode();
		_h_ += chapters.hashCode();
		_h_ += dailys.hashCode();
		_h_ += multistory.hashCode();
		_h_ += herogroups.hashCode();
		_h_ += teamfight.hashCode();
		_h_ += matchtype;
		_h_ += nextmatchtime;
		_h_ += multiectypid;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(climbtowers);
		_sb_.append(",");
		_sb_.append(chapters);
		_sb_.append(",");
		_sb_.append(dailys);
		_sb_.append(",");
		_sb_.append(multistory);
		_sb_.append(",");
		_sb_.append(herogroups);
		_sb_.append(",");
		_sb_.append(teamfight);
		_sb_.append(",");
		_sb_.append(matchtype);
		_sb_.append(",");
		_sb_.append(nextmatchtime);
		_sb_.append(",");
		_sb_.append(multiectypid);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("climbtowers"));
		lb.add(new xdb.logs.ListenableMap().setVarName("chapters"));
		lb.add(new xdb.logs.ListenableMap().setVarName("dailys"));
		lb.add(new xdb.logs.ListenableMap().setVarName("multistory"));
		lb.add(new xdb.logs.ListenableMap().setVarName("herogroups"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("teamfight"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("matchtype"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("nextmatchtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("multiectypid"));
		return lb;
	}

	private class Const implements xbean.RoleEctype {
		RoleEctype nThis() {
			return RoleEctype.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleEctype copy() {
			return RoleEctype.this.copy();
		}

		@Override
		public xbean.RoleEctype toData() {
			return RoleEctype.this.toData();
		}

		public xbean.RoleEctype toBean() {
			return RoleEctype.this.toBean();
		}

		@Override
		public xbean.RoleEctype toDataIf() {
			return RoleEctype.this.toDataIf();
		}

		public xbean.RoleEctype toBeanIf() {
			return RoleEctype.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.ClimbTowerInfo> getClimbtowers() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(climbtowers);
		}

		@Override
		public java.util.Map<Integer, xbean.ClimbTowerInfo> getClimbtowersAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.ClimbTowerInfo> climbtowers;
			RoleEctype _o_ = RoleEctype.this;
			climbtowers = new java.util.HashMap<Integer, xbean.ClimbTowerInfo>();
			for (java.util.Map.Entry<Integer, xbean.ClimbTowerInfo> _e_ : _o_.climbtowers.entrySet())
				climbtowers.put(_e_.getKey(), new ClimbTowerInfo.Data(_e_.getValue()));
			return climbtowers;
		}

		@Override
		public java.util.Map<Integer, xbean.ChapterInfo> getChapters() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(chapters);
		}

		@Override
		public java.util.Map<Integer, xbean.ChapterInfo> getChaptersAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.ChapterInfo> chapters;
			RoleEctype _o_ = RoleEctype.this;
			chapters = new java.util.HashMap<Integer, xbean.ChapterInfo>();
			for (java.util.Map.Entry<Integer, xbean.ChapterInfo> _e_ : _o_.chapters.entrySet())
				chapters.put(_e_.getKey(), new ChapterInfo.Data(_e_.getValue()));
			return chapters;
		}

		@Override
		public java.util.Map<Integer, xbean.DailyInfo> getDailys() { // key->ectypetype
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(dailys);
		}

		@Override
		public java.util.Map<Integer, xbean.DailyInfo> getDailysAsData() { // key->ectypetype
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.DailyInfo> dailys;
			RoleEctype _o_ = RoleEctype.this;
			dailys = new java.util.HashMap<Integer, xbean.DailyInfo>();
			for (java.util.Map.Entry<Integer, xbean.DailyInfo> _e_ : _o_.dailys.entrySet())
				dailys.put(_e_.getKey(), new DailyInfo.Data(_e_.getValue()));
			return dailys;
		}

		@Override
		public java.util.Map<Integer, Integer> getMultistory() { // 多人剧情副本挑战的得星记录
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(multistory);
		}

		@Override
		public java.util.Map<Integer, Integer> getMultistoryAsData() { // 多人剧情副本挑战的得星记录
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> multistory;
			RoleEctype _o_ = RoleEctype.this;
			multistory = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.multistory.entrySet())
				multistory.put(_e_.getKey(), _e_.getValue());
			return multistory;
		}

		@Override
		public java.util.Map<Integer, xbean.HeroesGroupInfo> getHerogroups() { // groupid->HeroesGroupInfo
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(herogroups);
		}

		@Override
		public java.util.Map<Integer, xbean.HeroesGroupInfo> getHerogroupsAsData() { // groupid->HeroesGroupInfo
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.HeroesGroupInfo> herogroups;
			RoleEctype _o_ = RoleEctype.this;
			herogroups = new java.util.HashMap<Integer, xbean.HeroesGroupInfo>();
			for (java.util.Map.Entry<Integer, xbean.HeroesGroupInfo> _e_ : _o_.herogroups.entrySet())
				herogroups.put(_e_.getKey(), new HeroesGroupInfo.Data(_e_.getValue()));
			return herogroups;
		}

		@Override
		public xbean.TeamFightInfo getTeamfight() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(teamfight);
		}

		@Override
		public int getMatchtype() { // cfg.ectype.MatchType
			_xdb_verify_unsafe_();
			return matchtype;
		}

		@Override
		public long getNextmatchtime() { // 
			_xdb_verify_unsafe_();
			return nextmatchtime;
		}

		@Override
		public int getMultiectypid() { // 如果是在匹配多人剧情，需要记一下
			_xdb_verify_unsafe_();
			return multiectypid;
		}

		@Override
		public void setMatchtype(int _v_) { // cfg.ectype.MatchType
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNextmatchtime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMultiectypid(int _v_) { // 如果是在匹配多人剧情，需要记一下
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
			return RoleEctype.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleEctype.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleEctype.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleEctype.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleEctype.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleEctype.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleEctype.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleEctype.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleEctype.this.toString();
		}

	}

	public static final class Data implements xbean.RoleEctype {
		private java.util.HashMap<Integer, xbean.ClimbTowerInfo> climbtowers; // 
		private java.util.HashMap<Integer, xbean.ChapterInfo> chapters; // 
		private java.util.HashMap<Integer, xbean.DailyInfo> dailys; // key->ectypetype
		private java.util.HashMap<Integer, Integer> multistory; // 多人剧情副本挑战的得星记录
		private java.util.HashMap<Integer, xbean.HeroesGroupInfo> herogroups; // groupid->HeroesGroupInfo
		private xbean.TeamFightInfo teamfight; // 
		private int matchtype; // cfg.ectype.MatchType
		private long nextmatchtime; // 
		private int multiectypid; // 如果是在匹配多人剧情，需要记一下

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			climbtowers = new java.util.HashMap<Integer, xbean.ClimbTowerInfo>();
			chapters = new java.util.HashMap<Integer, xbean.ChapterInfo>();
			dailys = new java.util.HashMap<Integer, xbean.DailyInfo>();
			multistory = new java.util.HashMap<Integer, Integer>();
			herogroups = new java.util.HashMap<Integer, xbean.HeroesGroupInfo>();
			teamfight = new TeamFightInfo.Data();
		}

		Data(xbean.RoleEctype _o1_) {
			if (_o1_ instanceof RoleEctype) assign((RoleEctype)_o1_);
			else if (_o1_ instanceof RoleEctype.Data) assign((RoleEctype.Data)_o1_);
			else if (_o1_ instanceof RoleEctype.Const) assign(((RoleEctype.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleEctype _o_) {
			climbtowers = new java.util.HashMap<Integer, xbean.ClimbTowerInfo>();
			for (java.util.Map.Entry<Integer, xbean.ClimbTowerInfo> _e_ : _o_.climbtowers.entrySet())
				climbtowers.put(_e_.getKey(), new ClimbTowerInfo.Data(_e_.getValue()));
			chapters = new java.util.HashMap<Integer, xbean.ChapterInfo>();
			for (java.util.Map.Entry<Integer, xbean.ChapterInfo> _e_ : _o_.chapters.entrySet())
				chapters.put(_e_.getKey(), new ChapterInfo.Data(_e_.getValue()));
			dailys = new java.util.HashMap<Integer, xbean.DailyInfo>();
			for (java.util.Map.Entry<Integer, xbean.DailyInfo> _e_ : _o_.dailys.entrySet())
				dailys.put(_e_.getKey(), new DailyInfo.Data(_e_.getValue()));
			multistory = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.multistory.entrySet())
				multistory.put(_e_.getKey(), _e_.getValue());
			herogroups = new java.util.HashMap<Integer, xbean.HeroesGroupInfo>();
			for (java.util.Map.Entry<Integer, xbean.HeroesGroupInfo> _e_ : _o_.herogroups.entrySet())
				herogroups.put(_e_.getKey(), new HeroesGroupInfo.Data(_e_.getValue()));
			teamfight = new TeamFightInfo.Data(_o_.teamfight);
			matchtype = _o_.matchtype;
			nextmatchtime = _o_.nextmatchtime;
			multiectypid = _o_.multiectypid;
		}

		private void assign(RoleEctype.Data _o_) {
			climbtowers = new java.util.HashMap<Integer, xbean.ClimbTowerInfo>();
			for (java.util.Map.Entry<Integer, xbean.ClimbTowerInfo> _e_ : _o_.climbtowers.entrySet())
				climbtowers.put(_e_.getKey(), new ClimbTowerInfo.Data(_e_.getValue()));
			chapters = new java.util.HashMap<Integer, xbean.ChapterInfo>();
			for (java.util.Map.Entry<Integer, xbean.ChapterInfo> _e_ : _o_.chapters.entrySet())
				chapters.put(_e_.getKey(), new ChapterInfo.Data(_e_.getValue()));
			dailys = new java.util.HashMap<Integer, xbean.DailyInfo>();
			for (java.util.Map.Entry<Integer, xbean.DailyInfo> _e_ : _o_.dailys.entrySet())
				dailys.put(_e_.getKey(), new DailyInfo.Data(_e_.getValue()));
			multistory = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.multistory.entrySet())
				multistory.put(_e_.getKey(), _e_.getValue());
			herogroups = new java.util.HashMap<Integer, xbean.HeroesGroupInfo>();
			for (java.util.Map.Entry<Integer, xbean.HeroesGroupInfo> _e_ : _o_.herogroups.entrySet())
				herogroups.put(_e_.getKey(), new HeroesGroupInfo.Data(_e_.getValue()));
			teamfight = new TeamFightInfo.Data(_o_.teamfight);
			matchtype = _o_.matchtype;
			nextmatchtime = _o_.nextmatchtime;
			multiectypid = _o_.multiectypid;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)9);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(climbtowers.size());
for (java.util.Map.Entry<Integer, xbean.ClimbTowerInfo> _e_ : climbtowers.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(chapters.size());
for (java.util.Map.Entry<Integer, xbean.ChapterInfo> _e_ : chapters.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(dailys.size());
for (java.util.Map.Entry<Integer, xbean.DailyInfo> _e_ : dailys.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(multistory.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : multistory.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(herogroups.size());
for (java.util.Map.Entry<Integer, xbean.HeroesGroupInfo> _e_ : herogroups.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(26624| 11));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
teamfight.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192| 14));_os_.marshal(matchtype);
	_os_.marshal((short)(10240| 13));_os_.marshal(nextmatchtime);
	_os_.marshal((short)( 8192| 15));_os_.marshal(multiectypid);
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
		climbtowers = new java.util.HashMap<Integer, xbean.ClimbTowerInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.ClimbTowerInfo _v_ = xbean.Pod.newClimbTowerInfoData();
		_v_.unmarshal(_os_);
		climbtowers.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		chapters = new java.util.HashMap<Integer, xbean.ChapterInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.ChapterInfo _v_ = xbean.Pod.newChapterInfoData();
		_v_.unmarshal(_os_);
		chapters.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		dailys = new java.util.HashMap<Integer, xbean.DailyInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.DailyInfo _v_ = xbean.Pod.newDailyInfoData();
		_v_.unmarshal(_os_);
		dailys.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		multistory = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		multistory.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		herogroups = new java.util.HashMap<Integer, xbean.HeroesGroupInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.HeroesGroupInfo _v_ = xbean.Pod.newHeroesGroupInfoData();
		_v_.unmarshal(_os_);
		herogroups.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (26624| 11):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
teamfight.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case ( 8192| 14):matchtype = _os_.unmarshal_int();
					break;
					case ( 6144| 14):matchtype = _os_.unmarshal_short();
					break;
					case (10240| 13):nextmatchtime = _os_.unmarshal_long();
					break;
					case ( 6144| 13):nextmatchtime = _os_.unmarshal_short();
					break;
					case ( 8192| 13):nextmatchtime = _os_.unmarshal_int();
					break;
					case ( 8192| 15):multiectypid = _os_.unmarshal_int();
					break;
					case ( 6144| 15):multiectypid = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleEctype copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleEctype toData() {
			return new Data(this);
		}

		public xbean.RoleEctype toBean() {
			return new RoleEctype(this, null, null);
		}

		@Override
		public xbean.RoleEctype toDataIf() {
			return this;
		}

		public xbean.RoleEctype toBeanIf() {
			return new RoleEctype(this, null, null);
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
		public java.util.Map<Integer, xbean.ClimbTowerInfo> getClimbtowers() { // 
			return climbtowers;
		}

		@Override
		public java.util.Map<Integer, xbean.ClimbTowerInfo> getClimbtowersAsData() { // 
			return climbtowers;
		}

		@Override
		public java.util.Map<Integer, xbean.ChapterInfo> getChapters() { // 
			return chapters;
		}

		@Override
		public java.util.Map<Integer, xbean.ChapterInfo> getChaptersAsData() { // 
			return chapters;
		}

		@Override
		public java.util.Map<Integer, xbean.DailyInfo> getDailys() { // key->ectypetype
			return dailys;
		}

		@Override
		public java.util.Map<Integer, xbean.DailyInfo> getDailysAsData() { // key->ectypetype
			return dailys;
		}

		@Override
		public java.util.Map<Integer, Integer> getMultistory() { // 多人剧情副本挑战的得星记录
			return multistory;
		}

		@Override
		public java.util.Map<Integer, Integer> getMultistoryAsData() { // 多人剧情副本挑战的得星记录
			return multistory;
		}

		@Override
		public java.util.Map<Integer, xbean.HeroesGroupInfo> getHerogroups() { // groupid->HeroesGroupInfo
			return herogroups;
		}

		@Override
		public java.util.Map<Integer, xbean.HeroesGroupInfo> getHerogroupsAsData() { // groupid->HeroesGroupInfo
			return herogroups;
		}

		@Override
		public xbean.TeamFightInfo getTeamfight() { // 
			return teamfight;
		}

		@Override
		public int getMatchtype() { // cfg.ectype.MatchType
			return matchtype;
		}

		@Override
		public long getNextmatchtime() { // 
			return nextmatchtime;
		}

		@Override
		public int getMultiectypid() { // 如果是在匹配多人剧情，需要记一下
			return multiectypid;
		}

		@Override
		public void setMatchtype(int _v_) { // cfg.ectype.MatchType
			matchtype = _v_;
		}

		@Override
		public void setNextmatchtime(long _v_) { // 
			nextmatchtime = _v_;
		}

		@Override
		public void setMultiectypid(int _v_) { // 如果是在匹配多人剧情，需要记一下
			multiectypid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleEctype.Data)) return false;
			RoleEctype.Data _o_ = (RoleEctype.Data) _o1_;
			if (!climbtowers.equals(_o_.climbtowers)) return false;
			if (!chapters.equals(_o_.chapters)) return false;
			if (!dailys.equals(_o_.dailys)) return false;
			if (!multistory.equals(_o_.multistory)) return false;
			if (!herogroups.equals(_o_.herogroups)) return false;
			if (!teamfight.equals(_o_.teamfight)) return false;
			if (matchtype != _o_.matchtype) return false;
			if (nextmatchtime != _o_.nextmatchtime) return false;
			if (multiectypid != _o_.multiectypid) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += climbtowers.hashCode();
			_h_ += chapters.hashCode();
			_h_ += dailys.hashCode();
			_h_ += multistory.hashCode();
			_h_ += herogroups.hashCode();
			_h_ += teamfight.hashCode();
			_h_ += matchtype;
			_h_ += nextmatchtime;
			_h_ += multiectypid;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(climbtowers);
			_sb_.append(",");
			_sb_.append(chapters);
			_sb_.append(",");
			_sb_.append(dailys);
			_sb_.append(",");
			_sb_.append(multistory);
			_sb_.append(",");
			_sb_.append(herogroups);
			_sb_.append(",");
			_sb_.append(teamfight);
			_sb_.append(",");
			_sb_.append(matchtype);
			_sb_.append(",");
			_sb_.append(nextmatchtime);
			_sb_.append(",");
			_sb_.append(multiectypid);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
