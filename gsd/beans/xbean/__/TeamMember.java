
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class TeamMember extends xdb.XBean implements xbean.TeamMember {
	private long roleid; // 
	private long jointime; // 时间，根据不同的集合表示不同的时间，申请时间，发送邀请的时间，加入队伍的时间等等
	private int follow; // 是否在跟随状态,0为非跟随，1为跟随
	private long followtime; // 跟随开始的时间

	@Override
	public void _reset_unsafe_() {
		roleid = 0L;
		jointime = 0L;
		follow = 0;
		followtime = 0L;
	}

	TeamMember(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public TeamMember() {
		this(0, null, null);
	}

	public TeamMember(TeamMember _o_) {
		this(_o_, null, null);
	}

	TeamMember(xbean.TeamMember _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof TeamMember) assign((TeamMember)_o1_);
		else if (_o1_ instanceof TeamMember.Data) assign((TeamMember.Data)_o1_);
		else if (_o1_ instanceof TeamMember.Const) assign(((TeamMember.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(TeamMember _o_) {
		_o_._xdb_verify_unsafe_();
		roleid = _o_.roleid;
		jointime = _o_.jointime;
		follow = _o_.follow;
		followtime = _o_.followtime;
	}

	private void assign(TeamMember.Data _o_) {
		roleid = _o_.roleid;
		jointime = _o_.jointime;
		follow = _o_.follow;
		followtime = _o_.followtime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(10240|  1));_os_.marshal(roleid);
    _os_.marshal((short)(10240|  2));_os_.marshal(jointime);
    _os_.marshal((short)( 8192|  3));_os_.marshal(follow);
    _os_.marshal((short)(10240|  4));_os_.marshal(followtime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):roleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):roleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):roleid = _os_.unmarshal_int();
    				break;
    				case (10240|  2):jointime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):jointime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):jointime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  3):follow = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):follow = _os_.unmarshal_short();
    				break;
    				case (10240|  4):followtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):followtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):followtime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.TeamMember copy() {
		_xdb_verify_unsafe_();
		return new TeamMember(this);
	}

	@Override
	public xbean.TeamMember toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TeamMember toBean() {
		_xdb_verify_unsafe_();
		return new TeamMember(this); // same as copy()
	}

	@Override
	public xbean.TeamMember toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TeamMember toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getRoleid() { // 
		_xdb_verify_unsafe_();
		return roleid;
	}

	@Override
	public long getJointime() { // 时间，根据不同的集合表示不同的时间，申请时间，发送邀请的时间，加入队伍的时间等等
		_xdb_verify_unsafe_();
		return jointime;
	}

	@Override
	public int getFollow() { // 是否在跟随状态,0为非跟随，1为跟随
		_xdb_verify_unsafe_();
		return follow;
	}

	@Override
	public long getFollowtime() { // 跟随开始的时间
		_xdb_verify_unsafe_();
		return followtime;
	}

	@Override
	public void setRoleid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleid) {
					public void rollback() { roleid = _xdb_saved; }
				};}});
		roleid = _v_;
	}

	@Override
	public void setJointime(long _v_) { // 时间，根据不同的集合表示不同的时间，申请时间，发送邀请的时间，加入队伍的时间等等
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "jointime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, jointime) {
					public void rollback() { jointime = _xdb_saved; }
				};}});
		jointime = _v_;
	}

	@Override
	public void setFollow(int _v_) { // 是否在跟随状态,0为非跟随，1为跟随
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "follow") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, follow) {
					public void rollback() { follow = _xdb_saved; }
				};}});
		follow = _v_;
	}

	@Override
	public void setFollowtime(long _v_) { // 跟随开始的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "followtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, followtime) {
					public void rollback() { followtime = _xdb_saved; }
				};}});
		followtime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		TeamMember _o_ = null;
		if ( _o1_ instanceof TeamMember ) _o_ = (TeamMember)_o1_;
		else if ( _o1_ instanceof TeamMember.Const ) _o_ = ((TeamMember.Const)_o1_).nThis();
		else return false;
		if (roleid != _o_.roleid) return false;
		if (jointime != _o_.jointime) return false;
		if (follow != _o_.follow) return false;
		if (followtime != _o_.followtime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += roleid;
		_h_ += jointime;
		_h_ += follow;
		_h_ += followtime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid);
		_sb_.append(",");
		_sb_.append(jointime);
		_sb_.append(",");
		_sb_.append(follow);
		_sb_.append(",");
		_sb_.append(followtime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("jointime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("follow"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("followtime"));
		return lb;
	}

	private class Const implements xbean.TeamMember {
		TeamMember nThis() {
			return TeamMember.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.TeamMember copy() {
			return TeamMember.this.copy();
		}

		@Override
		public xbean.TeamMember toData() {
			return TeamMember.this.toData();
		}

		public xbean.TeamMember toBean() {
			return TeamMember.this.toBean();
		}

		@Override
		public xbean.TeamMember toDataIf() {
			return TeamMember.this.toDataIf();
		}

		public xbean.TeamMember toBeanIf() {
			return TeamMember.this.toBeanIf();
		}

		@Override
		public long getRoleid() { // 
			_xdb_verify_unsafe_();
			return roleid;
		}

		@Override
		public long getJointime() { // 时间，根据不同的集合表示不同的时间，申请时间，发送邀请的时间，加入队伍的时间等等
			_xdb_verify_unsafe_();
			return jointime;
		}

		@Override
		public int getFollow() { // 是否在跟随状态,0为非跟随，1为跟随
			_xdb_verify_unsafe_();
			return follow;
		}

		@Override
		public long getFollowtime() { // 跟随开始的时间
			_xdb_verify_unsafe_();
			return followtime;
		}

		@Override
		public void setRoleid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setJointime(long _v_) { // 时间，根据不同的集合表示不同的时间，申请时间，发送邀请的时间，加入队伍的时间等等
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFollow(int _v_) { // 是否在跟随状态,0为非跟随，1为跟随
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFollowtime(long _v_) { // 跟随开始的时间
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
			return TeamMember.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return TeamMember.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return TeamMember.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return TeamMember.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return TeamMember.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return TeamMember.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return TeamMember.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return TeamMember.this.hashCode();
		}

		@Override
		public String toString() {
			return TeamMember.this.toString();
		}

	}

	public static final class Data implements xbean.TeamMember {
		private long roleid; // 
		private long jointime; // 时间，根据不同的集合表示不同的时间，申请时间，发送邀请的时间，加入队伍的时间等等
		private int follow; // 是否在跟随状态,0为非跟随，1为跟随
		private long followtime; // 跟随开始的时间

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.TeamMember _o1_) {
			if (_o1_ instanceof TeamMember) assign((TeamMember)_o1_);
			else if (_o1_ instanceof TeamMember.Data) assign((TeamMember.Data)_o1_);
			else if (_o1_ instanceof TeamMember.Const) assign(((TeamMember.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(TeamMember _o_) {
			roleid = _o_.roleid;
			jointime = _o_.jointime;
			follow = _o_.follow;
			followtime = _o_.followtime;
		}

		private void assign(TeamMember.Data _o_) {
			roleid = _o_.roleid;
			jointime = _o_.jointime;
			follow = _o_.follow;
			followtime = _o_.followtime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(10240|  1));_os_.marshal(roleid);
	_os_.marshal((short)(10240|  2));_os_.marshal(jointime);
	_os_.marshal((short)( 8192|  3));_os_.marshal(follow);
	_os_.marshal((short)(10240|  4));_os_.marshal(followtime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):roleid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):roleid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):roleid = _os_.unmarshal_int();
					break;
					case (10240|  2):jointime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):jointime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):jointime = _os_.unmarshal_int();
					break;
					case ( 8192|  3):follow = _os_.unmarshal_int();
					break;
					case ( 6144|  3):follow = _os_.unmarshal_short();
					break;
					case (10240|  4):followtime = _os_.unmarshal_long();
					break;
					case ( 6144|  4):followtime = _os_.unmarshal_short();
					break;
					case ( 8192|  4):followtime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.TeamMember copy() {
			return new Data(this);
		}

		@Override
		public xbean.TeamMember toData() {
			return new Data(this);
		}

		public xbean.TeamMember toBean() {
			return new TeamMember(this, null, null);
		}

		@Override
		public xbean.TeamMember toDataIf() {
			return this;
		}

		public xbean.TeamMember toBeanIf() {
			return new TeamMember(this, null, null);
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
		public long getRoleid() { // 
			return roleid;
		}

		@Override
		public long getJointime() { // 时间，根据不同的集合表示不同的时间，申请时间，发送邀请的时间，加入队伍的时间等等
			return jointime;
		}

		@Override
		public int getFollow() { // 是否在跟随状态,0为非跟随，1为跟随
			return follow;
		}

		@Override
		public long getFollowtime() { // 跟随开始的时间
			return followtime;
		}

		@Override
		public void setRoleid(long _v_) { // 
			roleid = _v_;
		}

		@Override
		public void setJointime(long _v_) { // 时间，根据不同的集合表示不同的时间，申请时间，发送邀请的时间，加入队伍的时间等等
			jointime = _v_;
		}

		@Override
		public void setFollow(int _v_) { // 是否在跟随状态,0为非跟随，1为跟随
			follow = _v_;
		}

		@Override
		public void setFollowtime(long _v_) { // 跟随开始的时间
			followtime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof TeamMember.Data)) return false;
			TeamMember.Data _o_ = (TeamMember.Data) _o1_;
			if (roleid != _o_.roleid) return false;
			if (jointime != _o_.jointime) return false;
			if (follow != _o_.follow) return false;
			if (followtime != _o_.followtime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += roleid;
			_h_ += jointime;
			_h_ += follow;
			_h_ += followtime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(roleid);
			_sb_.append(",");
			_sb_.append(jointime);
			_sb_.append(",");
			_sb_.append(follow);
			_sb_.append(",");
			_sb_.append(followtime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
