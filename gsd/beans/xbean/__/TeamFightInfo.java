
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class TeamFightInfo extends xdb.XBean implements xbean.TeamFightInfo {
	private long lastupdatetime; // 
	private int weekscore; // 本周积分
	private int todaywinnum; // 本日胜利次数
	private boolean obtaintodaywinreward; // 是否领过今日奖励
	private int todayfightnum; // 本日参加次数
	private java.util.LinkedList<Integer> obtainscorerewards; // 本周已领积分奖励

	@Override
	public void _reset_unsafe_() {
		lastupdatetime = 0L;
		weekscore = 0;
		todaywinnum = 0;
		obtaintodaywinreward = false;
		todayfightnum = 0;
		obtainscorerewards.clear();
	}

	TeamFightInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		obtainscorerewards = new java.util.LinkedList<Integer>();
	}

	public TeamFightInfo() {
		this(0, null, null);
	}

	public TeamFightInfo(TeamFightInfo _o_) {
		this(_o_, null, null);
	}

	TeamFightInfo(xbean.TeamFightInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof TeamFightInfo) assign((TeamFightInfo)_o1_);
		else if (_o1_ instanceof TeamFightInfo.Data) assign((TeamFightInfo.Data)_o1_);
		else if (_o1_ instanceof TeamFightInfo.Const) assign(((TeamFightInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(TeamFightInfo _o_) {
		_o_._xdb_verify_unsafe_();
		lastupdatetime = _o_.lastupdatetime;
		weekscore = _o_.weekscore;
		todaywinnum = _o_.todaywinnum;
		obtaintodaywinreward = _o_.obtaintodaywinreward;
		todayfightnum = _o_.todayfightnum;
		obtainscorerewards = new java.util.LinkedList<Integer>();
		obtainscorerewards.addAll(_o_.obtainscorerewards);
	}

	private void assign(TeamFightInfo.Data _o_) {
		lastupdatetime = _o_.lastupdatetime;
		weekscore = _o_.weekscore;
		todaywinnum = _o_.todaywinnum;
		obtaintodaywinreward = _o_.obtaintodaywinreward;
		todayfightnum = _o_.todayfightnum;
		obtainscorerewards = new java.util.LinkedList<Integer>();
		obtainscorerewards.addAll(_o_.obtainscorerewards);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)(10240|  1));_os_.marshal(lastupdatetime);
    _os_.marshal((short)( 8192|  2));_os_.marshal(weekscore);
    _os_.marshal((short)( 8192|  3));_os_.marshal(todaywinnum);
    _os_.marshal((short)( 2048|  4));_os_.marshal(obtaintodaywinreward);
    _os_.marshal((short)( 8192|  5));_os_.marshal(todayfightnum);
    _os_.marshal((short)(22528|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(obtainscorerewards.size());
for (Integer _v_ : obtainscorerewards) {
	_os_.marshal(_v_);
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
    				case (10240|  1):lastupdatetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):lastupdatetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):lastupdatetime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):weekscore = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):weekscore = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):todaywinnum = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):todaywinnum = _os_.unmarshal_short();
    				break;
    				case ( 2048|  4):obtaintodaywinreward = _os_.unmarshal_boolean();
    				break;
    				case ( 8192|  5):todayfightnum = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):todayfightnum = _os_.unmarshal_short();
    				break;
    				case (22528|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	obtainscorerewards.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.TeamFightInfo copy() {
		_xdb_verify_unsafe_();
		return new TeamFightInfo(this);
	}

	@Override
	public xbean.TeamFightInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TeamFightInfo toBean() {
		_xdb_verify_unsafe_();
		return new TeamFightInfo(this); // same as copy()
	}

	@Override
	public xbean.TeamFightInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TeamFightInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getLastupdatetime() { // 
		_xdb_verify_unsafe_();
		return lastupdatetime;
	}

	@Override
	public int getWeekscore() { // 本周积分
		_xdb_verify_unsafe_();
		return weekscore;
	}

	@Override
	public int getTodaywinnum() { // 本日胜利次数
		_xdb_verify_unsafe_();
		return todaywinnum;
	}

	@Override
	public boolean getObtaintodaywinreward() { // 是否领过今日奖励
		_xdb_verify_unsafe_();
		return obtaintodaywinreward;
	}

	@Override
	public int getTodayfightnum() { // 本日参加次数
		_xdb_verify_unsafe_();
		return todayfightnum;
	}

	@Override
	public java.util.List<Integer> getObtainscorerewards() { // 本周已领积分奖励
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "obtainscorerewards"), obtainscorerewards);
	}

	public java.util.List<Integer> getObtainscorerewardsAsData() { // 本周已领积分奖励
		_xdb_verify_unsafe_();
		java.util.List<Integer> obtainscorerewards;
		TeamFightInfo _o_ = this;
		obtainscorerewards = new java.util.LinkedList<Integer>();
		obtainscorerewards.addAll(_o_.obtainscorerewards);
		return obtainscorerewards;
	}

	@Override
	public void setLastupdatetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastupdatetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastupdatetime) {
					public void rollback() { lastupdatetime = _xdb_saved; }
				};}});
		lastupdatetime = _v_;
	}

	@Override
	public void setWeekscore(int _v_) { // 本周积分
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "weekscore") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, weekscore) {
					public void rollback() { weekscore = _xdb_saved; }
				};}});
		weekscore = _v_;
	}

	@Override
	public void setTodaywinnum(int _v_) { // 本日胜利次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "todaywinnum") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, todaywinnum) {
					public void rollback() { todaywinnum = _xdb_saved; }
				};}});
		todaywinnum = _v_;
	}

	@Override
	public void setObtaintodaywinreward(boolean _v_) { // 是否领过今日奖励
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "obtaintodaywinreward") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, obtaintodaywinreward) {
					public void rollback() { obtaintodaywinreward = _xdb_saved; }
				};}});
		obtaintodaywinreward = _v_;
	}

	@Override
	public void setTodayfightnum(int _v_) { // 本日参加次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "todayfightnum") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, todayfightnum) {
					public void rollback() { todayfightnum = _xdb_saved; }
				};}});
		todayfightnum = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		TeamFightInfo _o_ = null;
		if ( _o1_ instanceof TeamFightInfo ) _o_ = (TeamFightInfo)_o1_;
		else if ( _o1_ instanceof TeamFightInfo.Const ) _o_ = ((TeamFightInfo.Const)_o1_).nThis();
		else return false;
		if (lastupdatetime != _o_.lastupdatetime) return false;
		if (weekscore != _o_.weekscore) return false;
		if (todaywinnum != _o_.todaywinnum) return false;
		if (obtaintodaywinreward != _o_.obtaintodaywinreward) return false;
		if (todayfightnum != _o_.todayfightnum) return false;
		if (!obtainscorerewards.equals(_o_.obtainscorerewards)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += lastupdatetime;
		_h_ += weekscore;
		_h_ += todaywinnum;
		_h_ += obtaintodaywinreward ? 1231 : 1237;
		_h_ += todayfightnum;
		_h_ += obtainscorerewards.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lastupdatetime);
		_sb_.append(",");
		_sb_.append(weekscore);
		_sb_.append(",");
		_sb_.append(todaywinnum);
		_sb_.append(",");
		_sb_.append(obtaintodaywinreward);
		_sb_.append(",");
		_sb_.append(todayfightnum);
		_sb_.append(",");
		_sb_.append(obtainscorerewards);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastupdatetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("weekscore"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("todaywinnum"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("obtaintodaywinreward"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("todayfightnum"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("obtainscorerewards"));
		return lb;
	}

	private class Const implements xbean.TeamFightInfo {
		TeamFightInfo nThis() {
			return TeamFightInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.TeamFightInfo copy() {
			return TeamFightInfo.this.copy();
		}

		@Override
		public xbean.TeamFightInfo toData() {
			return TeamFightInfo.this.toData();
		}

		public xbean.TeamFightInfo toBean() {
			return TeamFightInfo.this.toBean();
		}

		@Override
		public xbean.TeamFightInfo toDataIf() {
			return TeamFightInfo.this.toDataIf();
		}

		public xbean.TeamFightInfo toBeanIf() {
			return TeamFightInfo.this.toBeanIf();
		}

		@Override
		public long getLastupdatetime() { // 
			_xdb_verify_unsafe_();
			return lastupdatetime;
		}

		@Override
		public int getWeekscore() { // 本周积分
			_xdb_verify_unsafe_();
			return weekscore;
		}

		@Override
		public int getTodaywinnum() { // 本日胜利次数
			_xdb_verify_unsafe_();
			return todaywinnum;
		}

		@Override
		public boolean getObtaintodaywinreward() { // 是否领过今日奖励
			_xdb_verify_unsafe_();
			return obtaintodaywinreward;
		}

		@Override
		public int getTodayfightnum() { // 本日参加次数
			_xdb_verify_unsafe_();
			return todayfightnum;
		}

		@Override
		public java.util.List<Integer> getObtainscorerewards() { // 本周已领积分奖励
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(obtainscorerewards);
		}

		public java.util.List<Integer> getObtainscorerewardsAsData() { // 本周已领积分奖励
			_xdb_verify_unsafe_();
			java.util.List<Integer> obtainscorerewards;
			TeamFightInfo _o_ = TeamFightInfo.this;
		obtainscorerewards = new java.util.LinkedList<Integer>();
		obtainscorerewards.addAll(_o_.obtainscorerewards);
			return obtainscorerewards;
		}

		@Override
		public void setLastupdatetime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setWeekscore(int _v_) { // 本周积分
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTodaywinnum(int _v_) { // 本日胜利次数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setObtaintodaywinreward(boolean _v_) { // 是否领过今日奖励
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTodayfightnum(int _v_) { // 本日参加次数
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
			return TeamFightInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return TeamFightInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return TeamFightInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return TeamFightInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return TeamFightInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return TeamFightInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return TeamFightInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return TeamFightInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return TeamFightInfo.this.toString();
		}

	}

	public static final class Data implements xbean.TeamFightInfo {
		private long lastupdatetime; // 
		private int weekscore; // 本周积分
		private int todaywinnum; // 本日胜利次数
		private boolean obtaintodaywinreward; // 是否领过今日奖励
		private int todayfightnum; // 本日参加次数
		private java.util.LinkedList<Integer> obtainscorerewards; // 本周已领积分奖励

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			obtainscorerewards = new java.util.LinkedList<Integer>();
		}

		Data(xbean.TeamFightInfo _o1_) {
			if (_o1_ instanceof TeamFightInfo) assign((TeamFightInfo)_o1_);
			else if (_o1_ instanceof TeamFightInfo.Data) assign((TeamFightInfo.Data)_o1_);
			else if (_o1_ instanceof TeamFightInfo.Const) assign(((TeamFightInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(TeamFightInfo _o_) {
			lastupdatetime = _o_.lastupdatetime;
			weekscore = _o_.weekscore;
			todaywinnum = _o_.todaywinnum;
			obtaintodaywinreward = _o_.obtaintodaywinreward;
			todayfightnum = _o_.todayfightnum;
			obtainscorerewards = new java.util.LinkedList<Integer>();
			obtainscorerewards.addAll(_o_.obtainscorerewards);
		}

		private void assign(TeamFightInfo.Data _o_) {
			lastupdatetime = _o_.lastupdatetime;
			weekscore = _o_.weekscore;
			todaywinnum = _o_.todaywinnum;
			obtaintodaywinreward = _o_.obtaintodaywinreward;
			todayfightnum = _o_.todayfightnum;
			obtainscorerewards = new java.util.LinkedList<Integer>();
			obtainscorerewards.addAll(_o_.obtainscorerewards);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)(10240|  1));_os_.marshal(lastupdatetime);
	_os_.marshal((short)( 8192|  2));_os_.marshal(weekscore);
	_os_.marshal((short)( 8192|  3));_os_.marshal(todaywinnum);
	_os_.marshal((short)( 2048|  4));_os_.marshal(obtaintodaywinreward);
	_os_.marshal((short)( 8192|  5));_os_.marshal(todayfightnum);
	_os_.marshal((short)(22528|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(obtainscorerewards.size());
for (Integer _v_ : obtainscorerewards) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):lastupdatetime = _os_.unmarshal_long();
					break;
					case ( 6144|  1):lastupdatetime = _os_.unmarshal_short();
					break;
					case ( 8192|  1):lastupdatetime = _os_.unmarshal_int();
					break;
					case ( 8192|  2):weekscore = _os_.unmarshal_int();
					break;
					case ( 6144|  2):weekscore = _os_.unmarshal_short();
					break;
					case ( 8192|  3):todaywinnum = _os_.unmarshal_int();
					break;
					case ( 6144|  3):todaywinnum = _os_.unmarshal_short();
					break;
					case ( 2048|  4):obtaintodaywinreward = _os_.unmarshal_boolean();
					break;
					case ( 8192|  5):todayfightnum = _os_.unmarshal_int();
					break;
					case ( 6144|  5):todayfightnum = _os_.unmarshal_short();
					break;
					case (22528|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	obtainscorerewards.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.TeamFightInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.TeamFightInfo toData() {
			return new Data(this);
		}

		public xbean.TeamFightInfo toBean() {
			return new TeamFightInfo(this, null, null);
		}

		@Override
		public xbean.TeamFightInfo toDataIf() {
			return this;
		}

		public xbean.TeamFightInfo toBeanIf() {
			return new TeamFightInfo(this, null, null);
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
		public long getLastupdatetime() { // 
			return lastupdatetime;
		}

		@Override
		public int getWeekscore() { // 本周积分
			return weekscore;
		}

		@Override
		public int getTodaywinnum() { // 本日胜利次数
			return todaywinnum;
		}

		@Override
		public boolean getObtaintodaywinreward() { // 是否领过今日奖励
			return obtaintodaywinreward;
		}

		@Override
		public int getTodayfightnum() { // 本日参加次数
			return todayfightnum;
		}

		@Override
		public java.util.List<Integer> getObtainscorerewards() { // 本周已领积分奖励
			return obtainscorerewards;
		}

		@Override
		public java.util.List<Integer> getObtainscorerewardsAsData() { // 本周已领积分奖励
			return obtainscorerewards;
		}

		@Override
		public void setLastupdatetime(long _v_) { // 
			lastupdatetime = _v_;
		}

		@Override
		public void setWeekscore(int _v_) { // 本周积分
			weekscore = _v_;
		}

		@Override
		public void setTodaywinnum(int _v_) { // 本日胜利次数
			todaywinnum = _v_;
		}

		@Override
		public void setObtaintodaywinreward(boolean _v_) { // 是否领过今日奖励
			obtaintodaywinreward = _v_;
		}

		@Override
		public void setTodayfightnum(int _v_) { // 本日参加次数
			todayfightnum = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof TeamFightInfo.Data)) return false;
			TeamFightInfo.Data _o_ = (TeamFightInfo.Data) _o1_;
			if (lastupdatetime != _o_.lastupdatetime) return false;
			if (weekscore != _o_.weekscore) return false;
			if (todaywinnum != _o_.todaywinnum) return false;
			if (obtaintodaywinreward != _o_.obtaintodaywinreward) return false;
			if (todayfightnum != _o_.todayfightnum) return false;
			if (!obtainscorerewards.equals(_o_.obtainscorerewards)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += lastupdatetime;
			_h_ += weekscore;
			_h_ += todaywinnum;
			_h_ += obtaintodaywinreward ? 1231 : 1237;
			_h_ += todayfightnum;
			_h_ += obtainscorerewards.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(lastupdatetime);
			_sb_.append(",");
			_sb_.append(weekscore);
			_sb_.append(",");
			_sb_.append(todaywinnum);
			_sb_.append(",");
			_sb_.append(obtaintodaywinreward);
			_sb_.append(",");
			_sb_.append(todayfightnum);
			_sb_.append(",");
			_sb_.append(obtainscorerewards);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
