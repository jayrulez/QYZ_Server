
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleActivityRecord extends xdb.XBean implements xbean.RoleActivityRecord {
	private long totalcostyuanbbao; // 累计花费元宝奖励
	private int killworldboss; // 杀死世界boss个数
	private int arenawin; // 竞技场胜场
	private int teamspeedwin; // 鸿蒙争霸胜场
	private int teamfightwin; // 天下会武胜场

	@Override
	public void _reset_unsafe_() {
		totalcostyuanbbao = 0L;
		killworldboss = 0;
		arenawin = 0;
		teamspeedwin = 0;
		teamfightwin = 0;
	}

	RoleActivityRecord(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public RoleActivityRecord() {
		this(0, null, null);
	}

	public RoleActivityRecord(RoleActivityRecord _o_) {
		this(_o_, null, null);
	}

	RoleActivityRecord(xbean.RoleActivityRecord _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleActivityRecord) assign((RoleActivityRecord)_o1_);
		else if (_o1_ instanceof RoleActivityRecord.Data) assign((RoleActivityRecord.Data)_o1_);
		else if (_o1_ instanceof RoleActivityRecord.Const) assign(((RoleActivityRecord.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleActivityRecord _o_) {
		_o_._xdb_verify_unsafe_();
		totalcostyuanbbao = _o_.totalcostyuanbbao;
		killworldboss = _o_.killworldboss;
		arenawin = _o_.arenawin;
		teamspeedwin = _o_.teamspeedwin;
		teamfightwin = _o_.teamfightwin;
	}

	private void assign(RoleActivityRecord.Data _o_) {
		totalcostyuanbbao = _o_.totalcostyuanbbao;
		killworldboss = _o_.killworldboss;
		arenawin = _o_.arenawin;
		teamspeedwin = _o_.teamspeedwin;
		teamfightwin = _o_.teamfightwin;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)5);
    _os_.marshal((short)(10240|  1));_os_.marshal(totalcostyuanbbao);
    _os_.marshal((short)( 8192|  2));_os_.marshal(killworldboss);
    _os_.marshal((short)( 8192|  3));_os_.marshal(arenawin);
    _os_.marshal((short)( 8192|  5));_os_.marshal(teamspeedwin);
    _os_.marshal((short)( 8192|  6));_os_.marshal(teamfightwin);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):totalcostyuanbbao = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):totalcostyuanbbao = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):totalcostyuanbbao = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):killworldboss = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):killworldboss = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):arenawin = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):arenawin = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):teamspeedwin = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):teamspeedwin = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):teamfightwin = _os_.unmarshal_int();
    				break;
    				case ( 6144|  6):teamfightwin = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleActivityRecord copy() {
		_xdb_verify_unsafe_();
		return new RoleActivityRecord(this);
	}

	@Override
	public xbean.RoleActivityRecord toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleActivityRecord toBean() {
		_xdb_verify_unsafe_();
		return new RoleActivityRecord(this); // same as copy()
	}

	@Override
	public xbean.RoleActivityRecord toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleActivityRecord toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getTotalcostyuanbbao() { // 累计花费元宝奖励
		_xdb_verify_unsafe_();
		return totalcostyuanbbao;
	}

	@Override
	public int getKillworldboss() { // 杀死世界boss个数
		_xdb_verify_unsafe_();
		return killworldboss;
	}

	@Override
	public int getArenawin() { // 竞技场胜场
		_xdb_verify_unsafe_();
		return arenawin;
	}

	@Override
	public int getTeamspeedwin() { // 鸿蒙争霸胜场
		_xdb_verify_unsafe_();
		return teamspeedwin;
	}

	@Override
	public int getTeamfightwin() { // 天下会武胜场
		_xdb_verify_unsafe_();
		return teamfightwin;
	}

	@Override
	public void setTotalcostyuanbbao(long _v_) { // 累计花费元宝奖励
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalcostyuanbbao") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalcostyuanbbao) {
					public void rollback() { totalcostyuanbbao = _xdb_saved; }
				};}});
		totalcostyuanbbao = _v_;
	}

	@Override
	public void setKillworldboss(int _v_) { // 杀死世界boss个数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "killworldboss") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, killworldboss) {
					public void rollback() { killworldboss = _xdb_saved; }
				};}});
		killworldboss = _v_;
	}

	@Override
	public void setArenawin(int _v_) { // 竞技场胜场
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "arenawin") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, arenawin) {
					public void rollback() { arenawin = _xdb_saved; }
				};}});
		arenawin = _v_;
	}

	@Override
	public void setTeamspeedwin(int _v_) { // 鸿蒙争霸胜场
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "teamspeedwin") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, teamspeedwin) {
					public void rollback() { teamspeedwin = _xdb_saved; }
				};}});
		teamspeedwin = _v_;
	}

	@Override
	public void setTeamfightwin(int _v_) { // 天下会武胜场
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "teamfightwin") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, teamfightwin) {
					public void rollback() { teamfightwin = _xdb_saved; }
				};}});
		teamfightwin = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleActivityRecord _o_ = null;
		if ( _o1_ instanceof RoleActivityRecord ) _o_ = (RoleActivityRecord)_o1_;
		else if ( _o1_ instanceof RoleActivityRecord.Const ) _o_ = ((RoleActivityRecord.Const)_o1_).nThis();
		else return false;
		if (totalcostyuanbbao != _o_.totalcostyuanbbao) return false;
		if (killworldboss != _o_.killworldboss) return false;
		if (arenawin != _o_.arenawin) return false;
		if (teamspeedwin != _o_.teamspeedwin) return false;
		if (teamfightwin != _o_.teamfightwin) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += totalcostyuanbbao;
		_h_ += killworldboss;
		_h_ += arenawin;
		_h_ += teamspeedwin;
		_h_ += teamfightwin;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(totalcostyuanbbao);
		_sb_.append(",");
		_sb_.append(killworldboss);
		_sb_.append(",");
		_sb_.append(arenawin);
		_sb_.append(",");
		_sb_.append(teamspeedwin);
		_sb_.append(",");
		_sb_.append(teamfightwin);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalcostyuanbbao"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("killworldboss"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("arenawin"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("teamspeedwin"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("teamfightwin"));
		return lb;
	}

	private class Const implements xbean.RoleActivityRecord {
		RoleActivityRecord nThis() {
			return RoleActivityRecord.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleActivityRecord copy() {
			return RoleActivityRecord.this.copy();
		}

		@Override
		public xbean.RoleActivityRecord toData() {
			return RoleActivityRecord.this.toData();
		}

		public xbean.RoleActivityRecord toBean() {
			return RoleActivityRecord.this.toBean();
		}

		@Override
		public xbean.RoleActivityRecord toDataIf() {
			return RoleActivityRecord.this.toDataIf();
		}

		public xbean.RoleActivityRecord toBeanIf() {
			return RoleActivityRecord.this.toBeanIf();
		}

		@Override
		public long getTotalcostyuanbbao() { // 累计花费元宝奖励
			_xdb_verify_unsafe_();
			return totalcostyuanbbao;
		}

		@Override
		public int getKillworldboss() { // 杀死世界boss个数
			_xdb_verify_unsafe_();
			return killworldboss;
		}

		@Override
		public int getArenawin() { // 竞技场胜场
			_xdb_verify_unsafe_();
			return arenawin;
		}

		@Override
		public int getTeamspeedwin() { // 鸿蒙争霸胜场
			_xdb_verify_unsafe_();
			return teamspeedwin;
		}

		@Override
		public int getTeamfightwin() { // 天下会武胜场
			_xdb_verify_unsafe_();
			return teamfightwin;
		}

		@Override
		public void setTotalcostyuanbbao(long _v_) { // 累计花费元宝奖励
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setKillworldboss(int _v_) { // 杀死世界boss个数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setArenawin(int _v_) { // 竞技场胜场
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTeamspeedwin(int _v_) { // 鸿蒙争霸胜场
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTeamfightwin(int _v_) { // 天下会武胜场
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
			return RoleActivityRecord.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleActivityRecord.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleActivityRecord.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleActivityRecord.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleActivityRecord.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleActivityRecord.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleActivityRecord.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleActivityRecord.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleActivityRecord.this.toString();
		}

	}

	public static final class Data implements xbean.RoleActivityRecord {
		private long totalcostyuanbbao; // 累计花费元宝奖励
		private int killworldboss; // 杀死世界boss个数
		private int arenawin; // 竞技场胜场
		private int teamspeedwin; // 鸿蒙争霸胜场
		private int teamfightwin; // 天下会武胜场

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.RoleActivityRecord _o1_) {
			if (_o1_ instanceof RoleActivityRecord) assign((RoleActivityRecord)_o1_);
			else if (_o1_ instanceof RoleActivityRecord.Data) assign((RoleActivityRecord.Data)_o1_);
			else if (_o1_ instanceof RoleActivityRecord.Const) assign(((RoleActivityRecord.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleActivityRecord _o_) {
			totalcostyuanbbao = _o_.totalcostyuanbbao;
			killworldboss = _o_.killworldboss;
			arenawin = _o_.arenawin;
			teamspeedwin = _o_.teamspeedwin;
			teamfightwin = _o_.teamfightwin;
		}

		private void assign(RoleActivityRecord.Data _o_) {
			totalcostyuanbbao = _o_.totalcostyuanbbao;
			killworldboss = _o_.killworldboss;
			arenawin = _o_.arenawin;
			teamspeedwin = _o_.teamspeedwin;
			teamfightwin = _o_.teamfightwin;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)5);
	_os_.marshal((short)(10240|  1));_os_.marshal(totalcostyuanbbao);
	_os_.marshal((short)( 8192|  2));_os_.marshal(killworldboss);
	_os_.marshal((short)( 8192|  3));_os_.marshal(arenawin);
	_os_.marshal((short)( 8192|  5));_os_.marshal(teamspeedwin);
	_os_.marshal((short)( 8192|  6));_os_.marshal(teamfightwin);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):totalcostyuanbbao = _os_.unmarshal_long();
					break;
					case ( 6144|  1):totalcostyuanbbao = _os_.unmarshal_short();
					break;
					case ( 8192|  1):totalcostyuanbbao = _os_.unmarshal_int();
					break;
					case ( 8192|  2):killworldboss = _os_.unmarshal_int();
					break;
					case ( 6144|  2):killworldboss = _os_.unmarshal_short();
					break;
					case ( 8192|  3):arenawin = _os_.unmarshal_int();
					break;
					case ( 6144|  3):arenawin = _os_.unmarshal_short();
					break;
					case ( 8192|  5):teamspeedwin = _os_.unmarshal_int();
					break;
					case ( 6144|  5):teamspeedwin = _os_.unmarshal_short();
					break;
					case ( 8192|  6):teamfightwin = _os_.unmarshal_int();
					break;
					case ( 6144|  6):teamfightwin = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleActivityRecord copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleActivityRecord toData() {
			return new Data(this);
		}

		public xbean.RoleActivityRecord toBean() {
			return new RoleActivityRecord(this, null, null);
		}

		@Override
		public xbean.RoleActivityRecord toDataIf() {
			return this;
		}

		public xbean.RoleActivityRecord toBeanIf() {
			return new RoleActivityRecord(this, null, null);
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
		public long getTotalcostyuanbbao() { // 累计花费元宝奖励
			return totalcostyuanbbao;
		}

		@Override
		public int getKillworldboss() { // 杀死世界boss个数
			return killworldboss;
		}

		@Override
		public int getArenawin() { // 竞技场胜场
			return arenawin;
		}

		@Override
		public int getTeamspeedwin() { // 鸿蒙争霸胜场
			return teamspeedwin;
		}

		@Override
		public int getTeamfightwin() { // 天下会武胜场
			return teamfightwin;
		}

		@Override
		public void setTotalcostyuanbbao(long _v_) { // 累计花费元宝奖励
			totalcostyuanbbao = _v_;
		}

		@Override
		public void setKillworldboss(int _v_) { // 杀死世界boss个数
			killworldboss = _v_;
		}

		@Override
		public void setArenawin(int _v_) { // 竞技场胜场
			arenawin = _v_;
		}

		@Override
		public void setTeamspeedwin(int _v_) { // 鸿蒙争霸胜场
			teamspeedwin = _v_;
		}

		@Override
		public void setTeamfightwin(int _v_) { // 天下会武胜场
			teamfightwin = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleActivityRecord.Data)) return false;
			RoleActivityRecord.Data _o_ = (RoleActivityRecord.Data) _o1_;
			if (totalcostyuanbbao != _o_.totalcostyuanbbao) return false;
			if (killworldboss != _o_.killworldboss) return false;
			if (arenawin != _o_.arenawin) return false;
			if (teamspeedwin != _o_.teamspeedwin) return false;
			if (teamfightwin != _o_.teamfightwin) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += totalcostyuanbbao;
			_h_ += killworldboss;
			_h_ += arenawin;
			_h_ += teamspeedwin;
			_h_ += teamfightwin;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(totalcostyuanbbao);
			_sb_.append(",");
			_sb_.append(killworldboss);
			_sb_.append(",");
			_sb_.append(arenawin);
			_sb_.append(",");
			_sb_.append(teamspeedwin);
			_sb_.append(",");
			_sb_.append(teamfightwin);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
