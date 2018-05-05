
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleTeamInfo extends xdb.XBean implements xbean.RoleTeamInfo {
	private long teamid; // 
	private int autoacceptrequest; // 是否自动接收别人的入队申请
	private int autoacceptinvite; // 是否自动接收别人的入队邀请
	private java.util.HashMap<Long, xbean.TeamMember> requesttojoin; // 请求加入别人队伍的申请，key为队伍的队长角色id,long为发送申请的时间

	@Override
	public void _reset_unsafe_() {
		teamid = 0L;
		autoacceptrequest = 0;
		autoacceptinvite = 0;
		requesttojoin.clear();
	}

	RoleTeamInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		requesttojoin = new java.util.HashMap<Long, xbean.TeamMember>();
	}

	public RoleTeamInfo() {
		this(0, null, null);
	}

	public RoleTeamInfo(RoleTeamInfo _o_) {
		this(_o_, null, null);
	}

	RoleTeamInfo(xbean.RoleTeamInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleTeamInfo) assign((RoleTeamInfo)_o1_);
		else if (_o1_ instanceof RoleTeamInfo.Data) assign((RoleTeamInfo.Data)_o1_);
		else if (_o1_ instanceof RoleTeamInfo.Const) assign(((RoleTeamInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleTeamInfo _o_) {
		_o_._xdb_verify_unsafe_();
		teamid = _o_.teamid;
		autoacceptrequest = _o_.autoacceptrequest;
		autoacceptinvite = _o_.autoacceptinvite;
		requesttojoin = new java.util.HashMap<Long, xbean.TeamMember>();
		for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.requesttojoin.entrySet())
			requesttojoin.put(_e_.getKey(), new TeamMember(_e_.getValue(), this, "requesttojoin"));
	}

	private void assign(RoleTeamInfo.Data _o_) {
		teamid = _o_.teamid;
		autoacceptrequest = _o_.autoacceptrequest;
		autoacceptinvite = _o_.autoacceptinvite;
		requesttojoin = new java.util.HashMap<Long, xbean.TeamMember>();
		for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.requesttojoin.entrySet())
			requesttojoin.put(_e_.getKey(), new TeamMember(_e_.getValue(), this, "requesttojoin"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(10240|  1));_os_.marshal(teamid);
    _os_.marshal((short)( 8192|  2));_os_.marshal(autoacceptrequest);
    _os_.marshal((short)( 8192|  3));_os_.marshal(autoacceptinvite);
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(requesttojoin.size());
for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : requesttojoin.entrySet())
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
    				case (10240|  1):teamid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):teamid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):teamid = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):autoacceptrequest = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):autoacceptrequest = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):autoacceptinvite = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):autoacceptinvite = _os_.unmarshal_short();
    				break;
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		requesttojoin = new java.util.HashMap<Long, xbean.TeamMember>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.TeamMember _v_ = new TeamMember(0, this, "requesttojoin");
		_v_.unmarshal(_os_);
		requesttojoin.put(_k_, _v_);
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
	public xbean.RoleTeamInfo copy() {
		_xdb_verify_unsafe_();
		return new RoleTeamInfo(this);
	}

	@Override
	public xbean.RoleTeamInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleTeamInfo toBean() {
		_xdb_verify_unsafe_();
		return new RoleTeamInfo(this); // same as copy()
	}

	@Override
	public xbean.RoleTeamInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleTeamInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getTeamid() { // 
		_xdb_verify_unsafe_();
		return teamid;
	}

	@Override
	public int getAutoacceptrequest() { // 是否自动接收别人的入队申请
		_xdb_verify_unsafe_();
		return autoacceptrequest;
	}

	@Override
	public int getAutoacceptinvite() { // 是否自动接收别人的入队邀请
		_xdb_verify_unsafe_();
		return autoacceptinvite;
	}

	@Override
	public java.util.Map<Long, xbean.TeamMember> getRequesttojoin() { // 请求加入别人队伍的申请，key为队伍的队长角色id,long为发送申请的时间
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "requesttojoin"), requesttojoin);
	}

	@Override
	public java.util.Map<Long, xbean.TeamMember> getRequesttojoinAsData() { // 请求加入别人队伍的申请，key为队伍的队长角色id,long为发送申请的时间
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.TeamMember> requesttojoin;
		RoleTeamInfo _o_ = this;
		requesttojoin = new java.util.HashMap<Long, xbean.TeamMember>();
		for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.requesttojoin.entrySet())
			requesttojoin.put(_e_.getKey(), new TeamMember.Data(_e_.getValue()));
		return requesttojoin;
	}

	@Override
	public void setTeamid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "teamid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, teamid) {
					public void rollback() { teamid = _xdb_saved; }
				};}});
		teamid = _v_;
	}

	@Override
	public void setAutoacceptrequest(int _v_) { // 是否自动接收别人的入队申请
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "autoacceptrequest") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, autoacceptrequest) {
					public void rollback() { autoacceptrequest = _xdb_saved; }
				};}});
		autoacceptrequest = _v_;
	}

	@Override
	public void setAutoacceptinvite(int _v_) { // 是否自动接收别人的入队邀请
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "autoacceptinvite") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, autoacceptinvite) {
					public void rollback() { autoacceptinvite = _xdb_saved; }
				};}});
		autoacceptinvite = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleTeamInfo _o_ = null;
		if ( _o1_ instanceof RoleTeamInfo ) _o_ = (RoleTeamInfo)_o1_;
		else if ( _o1_ instanceof RoleTeamInfo.Const ) _o_ = ((RoleTeamInfo.Const)_o1_).nThis();
		else return false;
		if (teamid != _o_.teamid) return false;
		if (autoacceptrequest != _o_.autoacceptrequest) return false;
		if (autoacceptinvite != _o_.autoacceptinvite) return false;
		if (!requesttojoin.equals(_o_.requesttojoin)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += teamid;
		_h_ += autoacceptrequest;
		_h_ += autoacceptinvite;
		_h_ += requesttojoin.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(teamid);
		_sb_.append(",");
		_sb_.append(autoacceptrequest);
		_sb_.append(",");
		_sb_.append(autoacceptinvite);
		_sb_.append(",");
		_sb_.append(requesttojoin);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("teamid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("autoacceptrequest"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("autoacceptinvite"));
		lb.add(new xdb.logs.ListenableMap().setVarName("requesttojoin"));
		return lb;
	}

	private class Const implements xbean.RoleTeamInfo {
		RoleTeamInfo nThis() {
			return RoleTeamInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleTeamInfo copy() {
			return RoleTeamInfo.this.copy();
		}

		@Override
		public xbean.RoleTeamInfo toData() {
			return RoleTeamInfo.this.toData();
		}

		public xbean.RoleTeamInfo toBean() {
			return RoleTeamInfo.this.toBean();
		}

		@Override
		public xbean.RoleTeamInfo toDataIf() {
			return RoleTeamInfo.this.toDataIf();
		}

		public xbean.RoleTeamInfo toBeanIf() {
			return RoleTeamInfo.this.toBeanIf();
		}

		@Override
		public long getTeamid() { // 
			_xdb_verify_unsafe_();
			return teamid;
		}

		@Override
		public int getAutoacceptrequest() { // 是否自动接收别人的入队申请
			_xdb_verify_unsafe_();
			return autoacceptrequest;
		}

		@Override
		public int getAutoacceptinvite() { // 是否自动接收别人的入队邀请
			_xdb_verify_unsafe_();
			return autoacceptinvite;
		}

		@Override
		public java.util.Map<Long, xbean.TeamMember> getRequesttojoin() { // 请求加入别人队伍的申请，key为队伍的队长角色id,long为发送申请的时间
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(requesttojoin);
		}

		@Override
		public java.util.Map<Long, xbean.TeamMember> getRequesttojoinAsData() { // 请求加入别人队伍的申请，key为队伍的队长角色id,long为发送申请的时间
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.TeamMember> requesttojoin;
			RoleTeamInfo _o_ = RoleTeamInfo.this;
			requesttojoin = new java.util.HashMap<Long, xbean.TeamMember>();
			for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.requesttojoin.entrySet())
				requesttojoin.put(_e_.getKey(), new TeamMember.Data(_e_.getValue()));
			return requesttojoin;
		}

		@Override
		public void setTeamid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAutoacceptrequest(int _v_) { // 是否自动接收别人的入队申请
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAutoacceptinvite(int _v_) { // 是否自动接收别人的入队邀请
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
			return RoleTeamInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleTeamInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleTeamInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleTeamInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleTeamInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleTeamInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleTeamInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleTeamInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleTeamInfo.this.toString();
		}

	}

	public static final class Data implements xbean.RoleTeamInfo {
		private long teamid; // 
		private int autoacceptrequest; // 是否自动接收别人的入队申请
		private int autoacceptinvite; // 是否自动接收别人的入队邀请
		private java.util.HashMap<Long, xbean.TeamMember> requesttojoin; // 请求加入别人队伍的申请，key为队伍的队长角色id,long为发送申请的时间

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			requesttojoin = new java.util.HashMap<Long, xbean.TeamMember>();
		}

		Data(xbean.RoleTeamInfo _o1_) {
			if (_o1_ instanceof RoleTeamInfo) assign((RoleTeamInfo)_o1_);
			else if (_o1_ instanceof RoleTeamInfo.Data) assign((RoleTeamInfo.Data)_o1_);
			else if (_o1_ instanceof RoleTeamInfo.Const) assign(((RoleTeamInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleTeamInfo _o_) {
			teamid = _o_.teamid;
			autoacceptrequest = _o_.autoacceptrequest;
			autoacceptinvite = _o_.autoacceptinvite;
			requesttojoin = new java.util.HashMap<Long, xbean.TeamMember>();
			for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.requesttojoin.entrySet())
				requesttojoin.put(_e_.getKey(), new TeamMember.Data(_e_.getValue()));
		}

		private void assign(RoleTeamInfo.Data _o_) {
			teamid = _o_.teamid;
			autoacceptrequest = _o_.autoacceptrequest;
			autoacceptinvite = _o_.autoacceptinvite;
			requesttojoin = new java.util.HashMap<Long, xbean.TeamMember>();
			for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : _o_.requesttojoin.entrySet())
				requesttojoin.put(_e_.getKey(), new TeamMember.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(10240|  1));_os_.marshal(teamid);
	_os_.marshal((short)( 8192|  2));_os_.marshal(autoacceptrequest);
	_os_.marshal((short)( 8192|  3));_os_.marshal(autoacceptinvite);
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(requesttojoin.size());
for (java.util.Map.Entry<Long, xbean.TeamMember> _e_ : requesttojoin.entrySet())
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
					case (10240|  1):teamid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):teamid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):teamid = _os_.unmarshal_int();
					break;
					case ( 8192|  2):autoacceptrequest = _os_.unmarshal_int();
					break;
					case ( 6144|  2):autoacceptrequest = _os_.unmarshal_short();
					break;
					case ( 8192|  3):autoacceptinvite = _os_.unmarshal_int();
					break;
					case ( 6144|  3):autoacceptinvite = _os_.unmarshal_short();
					break;
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		requesttojoin = new java.util.HashMap<Long, xbean.TeamMember>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.TeamMember _v_ = xbean.Pod.newTeamMemberData();
		_v_.unmarshal(_os_);
		requesttojoin.put(_k_, _v_);
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
		public xbean.RoleTeamInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleTeamInfo toData() {
			return new Data(this);
		}

		public xbean.RoleTeamInfo toBean() {
			return new RoleTeamInfo(this, null, null);
		}

		@Override
		public xbean.RoleTeamInfo toDataIf() {
			return this;
		}

		public xbean.RoleTeamInfo toBeanIf() {
			return new RoleTeamInfo(this, null, null);
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
		public long getTeamid() { // 
			return teamid;
		}

		@Override
		public int getAutoacceptrequest() { // 是否自动接收别人的入队申请
			return autoacceptrequest;
		}

		@Override
		public int getAutoacceptinvite() { // 是否自动接收别人的入队邀请
			return autoacceptinvite;
		}

		@Override
		public java.util.Map<Long, xbean.TeamMember> getRequesttojoin() { // 请求加入别人队伍的申请，key为队伍的队长角色id,long为发送申请的时间
			return requesttojoin;
		}

		@Override
		public java.util.Map<Long, xbean.TeamMember> getRequesttojoinAsData() { // 请求加入别人队伍的申请，key为队伍的队长角色id,long为发送申请的时间
			return requesttojoin;
		}

		@Override
		public void setTeamid(long _v_) { // 
			teamid = _v_;
		}

		@Override
		public void setAutoacceptrequest(int _v_) { // 是否自动接收别人的入队申请
			autoacceptrequest = _v_;
		}

		@Override
		public void setAutoacceptinvite(int _v_) { // 是否自动接收别人的入队邀请
			autoacceptinvite = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleTeamInfo.Data)) return false;
			RoleTeamInfo.Data _o_ = (RoleTeamInfo.Data) _o1_;
			if (teamid != _o_.teamid) return false;
			if (autoacceptrequest != _o_.autoacceptrequest) return false;
			if (autoacceptinvite != _o_.autoacceptinvite) return false;
			if (!requesttojoin.equals(_o_.requesttojoin)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += teamid;
			_h_ += autoacceptrequest;
			_h_ += autoacceptinvite;
			_h_ += requesttojoin.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(teamid);
			_sb_.append(",");
			_sb_.append(autoacceptrequest);
			_sb_.append(",");
			_sb_.append(autoacceptinvite);
			_sb_.append(",");
			_sb_.append(requesttojoin);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
