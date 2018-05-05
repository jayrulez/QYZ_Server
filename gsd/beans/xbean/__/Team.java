
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Team extends xdb.XBean implements xbean.Team {
	private long teamid; // teamid
	private long leaderid; // 
	private long createtime; // 组队时间
	private java.util.HashMap<Long, xbean.TeamMember> members; // 队友信息，key为角色id，value为角色加入队伍的时间
	private java.util.HashMap<Long, Long> requestforjoin; // 请求入队的申请，key为申请加入的角色id
	private java.util.HashMap<Long, Long> invitetojoin; // 邀请别人加入自己的队伍，key为邀请人的角色id，value为邀请的时间
	private java.util.HashMap<Long, Long> invitefollow; // 邀请别人跟随，key为邀请角色id，value为邀请的时间，邀请有时间冷却限定
	private long leadertransroleid; // 队长转移申请发送给的roleid，0为未发出转移申请

	@Override
	public void _reset_unsafe_() {
		teamid = 0L;
		leaderid = 0L;
		createtime = 0L;
		members.clear();
		requestforjoin.clear();
		invitetojoin.clear();
		invitefollow.clear();
		leadertransroleid = 0L;
	}

	Team(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		members = new java.util.HashMap<Long, xbean.TeamMember>();
		requestforjoin = new java.util.HashMap<Long, Long>();
		invitetojoin = new java.util.HashMap<Long, Long>();
		invitefollow = new java.util.HashMap<Long, Long>();
	}

	public Team() {
		this(0, null, null);
	}

	public Team(Team _o_) {
		this(_o_, null, null);
	}

	Team(xbean.Team _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Team) assign((Team)_o1_);
		else if (_o1_ instanceof Team.Data) assign((Team.Data)_o1_);
		else if (_o1_ instanceof Team.Const) assign(((Team.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Team _o_) {
		_o_._xdb_verify_unsafe_();
		teamid = _o_.teamid;
		leaderid = _o_.leaderid;
		createtime = _o_.createtime;
		members = new java.util.HashMap<Long, xbean.TeamMember>();
		for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.members.entrySet())
			members.put(_e_.getKey(), new TeamMember(_e_.getValue(), this, "members"));
		requestforjoin = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestforjoin.entrySet())
			requestforjoin.put(_e_.getKey(), _e_.getValue());
		invitetojoin = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitetojoin.entrySet())
			invitetojoin.put(_e_.getKey(), _e_.getValue());
		invitefollow = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitefollow.entrySet())
			invitefollow.put(_e_.getKey(), _e_.getValue());
		leadertransroleid = _o_.leadertransroleid;
	}

	private void assign(Team.Data _o_) {
		teamid = _o_.teamid;
		leaderid = _o_.leaderid;
		createtime = _o_.createtime;
		members = new java.util.HashMap<Long, xbean.TeamMember>();
		for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.members.entrySet())
			members.put(_e_.getKey(), new TeamMember(_e_.getValue(), this, "members"));
		requestforjoin = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestforjoin.entrySet())
			requestforjoin.put(_e_.getKey(), _e_.getValue());
		invitetojoin = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitetojoin.entrySet())
			invitetojoin.put(_e_.getKey(), _e_.getValue());
		invitefollow = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitefollow.entrySet())
			invitefollow.put(_e_.getKey(), _e_.getValue());
		leadertransroleid = _o_.leadertransroleid;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)8);
    _os_.marshal((short)(10240|  1));_os_.marshal(teamid);
    _os_.marshal((short)(10240|  2));_os_.marshal(leaderid);
    _os_.marshal((short)(10240|  3));_os_.marshal(createtime);
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(members.size());
for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : members.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(requestforjoin.size());
for (java.util.Map.Entry<Long, Long> _e_ : requestforjoin.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(invitetojoin.size());
for (java.util.Map.Entry<Long, Long> _e_ : invitetojoin.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(invitefollow.size());
for (java.util.Map.Entry<Long, Long> _e_ : invitefollow.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  8));_os_.marshal(leadertransroleid);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):teamid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):teamid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):teamid = _os_.unmarshal_int();
    				break;
    				case (10240|  2):leaderid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):leaderid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):leaderid = _os_.unmarshal_int();
    				break;
    				case (10240|  3):createtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):createtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):createtime = _os_.unmarshal_int();
    				break;
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		members = new java.util.HashMap<Long, xbean.TeamMember>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.TeamMember _v_ = new TeamMember(0, this, "members");
		_v_.unmarshal(_os_);
		members.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		requestforjoin = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		requestforjoin.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		invitetojoin = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		invitetojoin.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		invitefollow = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		invitefollow.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (10240|  8):leadertransroleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  8):leadertransroleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  8):leadertransroleid = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Team copy() {
		_xdb_verify_unsafe_();
		return new Team(this);
	}

	@Override
	public xbean.Team toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Team toBean() {
		_xdb_verify_unsafe_();
		return new Team(this); // same as copy()
	}

	@Override
	public xbean.Team toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Team toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getTeamid() { // teamid
		_xdb_verify_unsafe_();
		return teamid;
	}

	@Override
	public long getLeaderid() { // 
		_xdb_verify_unsafe_();
		return leaderid;
	}

	@Override
	public long getCreatetime() { // 组队时间
		_xdb_verify_unsafe_();
		return createtime;
	}

	@Override
	public java.util.Map<Long, xbean.TeamMember> getMembers() { // 队友信息，key为角色id，value为角色加入队伍的时间
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "members"), members);
	}

	@Override
	public java.util.Map<Long, xbean.TeamMember> getMembersAsData() { // 队友信息，key为角色id，value为角色加入队伍的时间
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.TeamMember> members;
		Team _o_ = this;
		members = new java.util.HashMap<Long, xbean.TeamMember>();
		for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.members.entrySet())
			members.put(_e_.getKey(), new TeamMember.Data(_e_.getValue()));
		return members;
	}

	@Override
	public java.util.Map<Long, Long> getRequestforjoin() { // 请求入队的申请，key为申请加入的角色id
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "requestforjoin"), requestforjoin);
	}

	@Override
	public java.util.Map<Long, Long> getRequestforjoinAsData() { // 请求入队的申请，key为申请加入的角色id
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> requestforjoin;
		Team _o_ = this;
		requestforjoin = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestforjoin.entrySet())
			requestforjoin.put(_e_.getKey(), _e_.getValue());
		return requestforjoin;
	}

	@Override
	public java.util.Map<Long, Long> getInvitetojoin() { // 邀请别人加入自己的队伍，key为邀请人的角色id，value为邀请的时间
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "invitetojoin"), invitetojoin);
	}

	@Override
	public java.util.Map<Long, Long> getInvitetojoinAsData() { // 邀请别人加入自己的队伍，key为邀请人的角色id，value为邀请的时间
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> invitetojoin;
		Team _o_ = this;
		invitetojoin = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitetojoin.entrySet())
			invitetojoin.put(_e_.getKey(), _e_.getValue());
		return invitetojoin;
	}

	@Override
	public java.util.Map<Long, Long> getInvitefollow() { // 邀请别人跟随，key为邀请角色id，value为邀请的时间，邀请有时间冷却限定
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "invitefollow"), invitefollow);
	}

	@Override
	public java.util.Map<Long, Long> getInvitefollowAsData() { // 邀请别人跟随，key为邀请角色id，value为邀请的时间，邀请有时间冷却限定
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> invitefollow;
		Team _o_ = this;
		invitefollow = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitefollow.entrySet())
			invitefollow.put(_e_.getKey(), _e_.getValue());
		return invitefollow;
	}

	@Override
	public long getLeadertransroleid() { // 队长转移申请发送给的roleid，0为未发出转移申请
		_xdb_verify_unsafe_();
		return leadertransroleid;
	}

	@Override
	public void setTeamid(long _v_) { // teamid
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "teamid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, teamid) {
					public void rollback() { teamid = _xdb_saved; }
				};}});
		teamid = _v_;
	}

	@Override
	public void setLeaderid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "leaderid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, leaderid) {
					public void rollback() { leaderid = _xdb_saved; }
				};}});
		leaderid = _v_;
	}

	@Override
	public void setCreatetime(long _v_) { // 组队时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "createtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, createtime) {
					public void rollback() { createtime = _xdb_saved; }
				};}});
		createtime = _v_;
	}

	@Override
	public void setLeadertransroleid(long _v_) { // 队长转移申请发送给的roleid，0为未发出转移申请
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "leadertransroleid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, leadertransroleid) {
					public void rollback() { leadertransroleid = _xdb_saved; }
				};}});
		leadertransroleid = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Team _o_ = null;
		if ( _o1_ instanceof Team ) _o_ = (Team)_o1_;
		else if ( _o1_ instanceof Team.Const ) _o_ = ((Team.Const)_o1_).nThis();
		else return false;
		if (teamid != _o_.teamid) return false;
		if (leaderid != _o_.leaderid) return false;
		if (createtime != _o_.createtime) return false;
		if (!members.equals(_o_.members)) return false;
		if (!requestforjoin.equals(_o_.requestforjoin)) return false;
		if (!invitetojoin.equals(_o_.invitetojoin)) return false;
		if (!invitefollow.equals(_o_.invitefollow)) return false;
		if (leadertransroleid != _o_.leadertransroleid) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += teamid;
		_h_ += leaderid;
		_h_ += createtime;
		_h_ += members.hashCode();
		_h_ += requestforjoin.hashCode();
		_h_ += invitetojoin.hashCode();
		_h_ += invitefollow.hashCode();
		_h_ += leadertransroleid;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(teamid);
		_sb_.append(",");
		_sb_.append(leaderid);
		_sb_.append(",");
		_sb_.append(createtime);
		_sb_.append(",");
		_sb_.append(members);
		_sb_.append(",");
		_sb_.append(requestforjoin);
		_sb_.append(",");
		_sb_.append(invitetojoin);
		_sb_.append(",");
		_sb_.append(invitefollow);
		_sb_.append(",");
		_sb_.append(leadertransroleid);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("teamid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("leaderid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("createtime"));
		lb.add(new xdb.logs.ListenableMap().setVarName("members"));
		lb.add(new xdb.logs.ListenableMap().setVarName("requestforjoin"));
		lb.add(new xdb.logs.ListenableMap().setVarName("invitetojoin"));
		lb.add(new xdb.logs.ListenableMap().setVarName("invitefollow"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("leadertransroleid"));
		return lb;
	}

	private class Const implements xbean.Team {
		Team nThis() {
			return Team.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Team copy() {
			return Team.this.copy();
		}

		@Override
		public xbean.Team toData() {
			return Team.this.toData();
		}

		public xbean.Team toBean() {
			return Team.this.toBean();
		}

		@Override
		public xbean.Team toDataIf() {
			return Team.this.toDataIf();
		}

		public xbean.Team toBeanIf() {
			return Team.this.toBeanIf();
		}

		@Override
		public long getTeamid() { // teamid
			_xdb_verify_unsafe_();
			return teamid;
		}

		@Override
		public long getLeaderid() { // 
			_xdb_verify_unsafe_();
			return leaderid;
		}

		@Override
		public long getCreatetime() { // 组队时间
			_xdb_verify_unsafe_();
			return createtime;
		}

		@Override
		public java.util.Map<Long, xbean.TeamMember> getMembers() { // 队友信息，key为角色id，value为角色加入队伍的时间
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(members);
		}

		@Override
		public java.util.Map<Long, xbean.TeamMember> getMembersAsData() { // 队友信息，key为角色id，value为角色加入队伍的时间
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.TeamMember> members;
			Team _o_ = Team.this;
			members = new java.util.HashMap<Long, xbean.TeamMember>();
			for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.members.entrySet())
				members.put(_e_.getKey(), new TeamMember.Data(_e_.getValue()));
			return members;
		}

		@Override
		public java.util.Map<Long, Long> getRequestforjoin() { // 请求入队的申请，key为申请加入的角色id
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(requestforjoin);
		}

		@Override
		public java.util.Map<Long, Long> getRequestforjoinAsData() { // 请求入队的申请，key为申请加入的角色id
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> requestforjoin;
			Team _o_ = Team.this;
			requestforjoin = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestforjoin.entrySet())
				requestforjoin.put(_e_.getKey(), _e_.getValue());
			return requestforjoin;
		}

		@Override
		public java.util.Map<Long, Long> getInvitetojoin() { // 邀请别人加入自己的队伍，key为邀请人的角色id，value为邀请的时间
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(invitetojoin);
		}

		@Override
		public java.util.Map<Long, Long> getInvitetojoinAsData() { // 邀请别人加入自己的队伍，key为邀请人的角色id，value为邀请的时间
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> invitetojoin;
			Team _o_ = Team.this;
			invitetojoin = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitetojoin.entrySet())
				invitetojoin.put(_e_.getKey(), _e_.getValue());
			return invitetojoin;
		}

		@Override
		public java.util.Map<Long, Long> getInvitefollow() { // 邀请别人跟随，key为邀请角色id，value为邀请的时间，邀请有时间冷却限定
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(invitefollow);
		}

		@Override
		public java.util.Map<Long, Long> getInvitefollowAsData() { // 邀请别人跟随，key为邀请角色id，value为邀请的时间，邀请有时间冷却限定
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> invitefollow;
			Team _o_ = Team.this;
			invitefollow = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitefollow.entrySet())
				invitefollow.put(_e_.getKey(), _e_.getValue());
			return invitefollow;
		}

		@Override
		public long getLeadertransroleid() { // 队长转移申请发送给的roleid，0为未发出转移申请
			_xdb_verify_unsafe_();
			return leadertransroleid;
		}

		@Override
		public void setTeamid(long _v_) { // teamid
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLeaderid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCreatetime(long _v_) { // 组队时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLeadertransroleid(long _v_) { // 队长转移申请发送给的roleid，0为未发出转移申请
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
			return Team.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Team.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Team.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Team.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Team.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Team.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Team.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Team.this.hashCode();
		}

		@Override
		public String toString() {
			return Team.this.toString();
		}

	}

	public static final class Data implements xbean.Team {
		private long teamid; // teamid
		private long leaderid; // 
		private long createtime; // 组队时间
		private java.util.HashMap<Long, xbean.TeamMember> members; // 队友信息，key为角色id，value为角色加入队伍的时间
		private java.util.HashMap<Long, Long> requestforjoin; // 请求入队的申请，key为申请加入的角色id
		private java.util.HashMap<Long, Long> invitetojoin; // 邀请别人加入自己的队伍，key为邀请人的角色id，value为邀请的时间
		private java.util.HashMap<Long, Long> invitefollow; // 邀请别人跟随，key为邀请角色id，value为邀请的时间，邀请有时间冷却限定
		private long leadertransroleid; // 队长转移申请发送给的roleid，0为未发出转移申请

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			members = new java.util.HashMap<Long, xbean.TeamMember>();
			requestforjoin = new java.util.HashMap<Long, Long>();
			invitetojoin = new java.util.HashMap<Long, Long>();
			invitefollow = new java.util.HashMap<Long, Long>();
		}

		Data(xbean.Team _o1_) {
			if (_o1_ instanceof Team) assign((Team)_o1_);
			else if (_o1_ instanceof Team.Data) assign((Team.Data)_o1_);
			else if (_o1_ instanceof Team.Const) assign(((Team.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Team _o_) {
			teamid = _o_.teamid;
			leaderid = _o_.leaderid;
			createtime = _o_.createtime;
			members = new java.util.HashMap<Long, xbean.TeamMember>();
			for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.members.entrySet())
				members.put(_e_.getKey(), new TeamMember.Data(_e_.getValue()));
			requestforjoin = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestforjoin.entrySet())
				requestforjoin.put(_e_.getKey(), _e_.getValue());
			invitetojoin = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitetojoin.entrySet())
				invitetojoin.put(_e_.getKey(), _e_.getValue());
			invitefollow = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitefollow.entrySet())
				invitefollow.put(_e_.getKey(), _e_.getValue());
			leadertransroleid = _o_.leadertransroleid;
		}

		private void assign(Team.Data _o_) {
			teamid = _o_.teamid;
			leaderid = _o_.leaderid;
			createtime = _o_.createtime;
			members = new java.util.HashMap<Long, xbean.TeamMember>();
			for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.members.entrySet())
				members.put(_e_.getKey(), new TeamMember.Data(_e_.getValue()));
			requestforjoin = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestforjoin.entrySet())
				requestforjoin.put(_e_.getKey(), _e_.getValue());
			invitetojoin = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitetojoin.entrySet())
				invitetojoin.put(_e_.getKey(), _e_.getValue());
			invitefollow = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitefollow.entrySet())
				invitefollow.put(_e_.getKey(), _e_.getValue());
			leadertransroleid = _o_.leadertransroleid;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)8);
	_os_.marshal((short)(10240|  1));_os_.marshal(teamid);
	_os_.marshal((short)(10240|  2));_os_.marshal(leaderid);
	_os_.marshal((short)(10240|  3));_os_.marshal(createtime);
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(members.size());
for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : members.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(requestforjoin.size());
for (java.util.Map.Entry<Long, Long> _e_ : requestforjoin.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(invitetojoin.size());
for (java.util.Map.Entry<Long, Long> _e_ : invitetojoin.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(invitefollow.size());
for (java.util.Map.Entry<Long, Long> _e_ : invitefollow.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  8));_os_.marshal(leadertransroleid);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):teamid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):teamid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):teamid = _os_.unmarshal_int();
					break;
					case (10240|  2):leaderid = _os_.unmarshal_long();
					break;
					case ( 6144|  2):leaderid = _os_.unmarshal_short();
					break;
					case ( 8192|  2):leaderid = _os_.unmarshal_int();
					break;
					case (10240|  3):createtime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):createtime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):createtime = _os_.unmarshal_int();
					break;
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		members = new java.util.HashMap<Long, xbean.TeamMember>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.TeamMember _v_ = xbean.Pod.newTeamMemberData();
		_v_.unmarshal(_os_);
		members.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		requestforjoin = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		requestforjoin.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		invitetojoin = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		invitetojoin.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		invitefollow = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		invitefollow.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (10240|  8):leadertransroleid = _os_.unmarshal_long();
					break;
					case ( 6144|  8):leadertransroleid = _os_.unmarshal_short();
					break;
					case ( 8192|  8):leadertransroleid = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Team copy() {
			return new Data(this);
		}

		@Override
		public xbean.Team toData() {
			return new Data(this);
		}

		public xbean.Team toBean() {
			return new Team(this, null, null);
		}

		@Override
		public xbean.Team toDataIf() {
			return this;
		}

		public xbean.Team toBeanIf() {
			return new Team(this, null, null);
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
		public long getTeamid() { // teamid
			return teamid;
		}

		@Override
		public long getLeaderid() { // 
			return leaderid;
		}

		@Override
		public long getCreatetime() { // 组队时间
			return createtime;
		}

		@Override
		public java.util.Map<Long, xbean.TeamMember> getMembers() { // 队友信息，key为角色id，value为角色加入队伍的时间
			return members;
		}

		@Override
		public java.util.Map<Long, xbean.TeamMember> getMembersAsData() { // 队友信息，key为角色id，value为角色加入队伍的时间
			return members;
		}

		@Override
		public java.util.Map<Long, Long> getRequestforjoin() { // 请求入队的申请，key为申请加入的角色id
			return requestforjoin;
		}

		@Override
		public java.util.Map<Long, Long> getRequestforjoinAsData() { // 请求入队的申请，key为申请加入的角色id
			return requestforjoin;
		}

		@Override
		public java.util.Map<Long, Long> getInvitetojoin() { // 邀请别人加入自己的队伍，key为邀请人的角色id，value为邀请的时间
			return invitetojoin;
		}

		@Override
		public java.util.Map<Long, Long> getInvitetojoinAsData() { // 邀请别人加入自己的队伍，key为邀请人的角色id，value为邀请的时间
			return invitetojoin;
		}

		@Override
		public java.util.Map<Long, Long> getInvitefollow() { // 邀请别人跟随，key为邀请角色id，value为邀请的时间，邀请有时间冷却限定
			return invitefollow;
		}

		@Override
		public java.util.Map<Long, Long> getInvitefollowAsData() { // 邀请别人跟随，key为邀请角色id，value为邀请的时间，邀请有时间冷却限定
			return invitefollow;
		}

		@Override
		public long getLeadertransroleid() { // 队长转移申请发送给的roleid，0为未发出转移申请
			return leadertransroleid;
		}

		@Override
		public void setTeamid(long _v_) { // teamid
			teamid = _v_;
		}

		@Override
		public void setLeaderid(long _v_) { // 
			leaderid = _v_;
		}

		@Override
		public void setCreatetime(long _v_) { // 组队时间
			createtime = _v_;
		}

		@Override
		public void setLeadertransroleid(long _v_) { // 队长转移申请发送给的roleid，0为未发出转移申请
			leadertransroleid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Team.Data)) return false;
			Team.Data _o_ = (Team.Data) _o1_;
			if (teamid != _o_.teamid) return false;
			if (leaderid != _o_.leaderid) return false;
			if (createtime != _o_.createtime) return false;
			if (!members.equals(_o_.members)) return false;
			if (!requestforjoin.equals(_o_.requestforjoin)) return false;
			if (!invitetojoin.equals(_o_.invitetojoin)) return false;
			if (!invitefollow.equals(_o_.invitefollow)) return false;
			if (leadertransroleid != _o_.leadertransroleid) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += teamid;
			_h_ += leaderid;
			_h_ += createtime;
			_h_ += members.hashCode();
			_h_ += requestforjoin.hashCode();
			_h_ += invitetojoin.hashCode();
			_h_ += invitefollow.hashCode();
			_h_ += leadertransroleid;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(teamid);
			_sb_.append(",");
			_sb_.append(leaderid);
			_sb_.append(",");
			_sb_.append(createtime);
			_sb_.append(",");
			_sb_.append(members);
			_sb_.append(",");
			_sb_.append(requestforjoin);
			_sb_.append(",");
			_sb_.append(invitetojoin);
			_sb_.append(",");
			_sb_.append(invitefollow);
			_sb_.append(",");
			_sb_.append(leadertransroleid);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
