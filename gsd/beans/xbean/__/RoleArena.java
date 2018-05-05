
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleArena extends xdb.XBean implements xbean.RoleArena {
	private boolean open; // 
	private boolean isrobot; // 
	private long lastupdateshengwangtime; // 
	private java.util.LinkedList<Integer> challengeranks; // 
	private java.util.LinkedList<Long> challengerobots; // 仅仅首次挑战Pvp时有效,其余时候都是空的
	private int bechallenging; // 
	private long bechallengelockexpiretime; // 
	private java.util.LinkedList<xbean.ArenaFightReport> fightreports; // 
	private int bestrank; // 
	private boolean haswin; // 是否赢过
	private int fakerank; // 

	@Override
	public void _reset_unsafe_() {
		open = false;
		isrobot = false;
		lastupdateshengwangtime = 0L;
		challengeranks.clear();
		challengerobots.clear();
		bechallenging = 0;
		bechallengelockexpiretime = 0L;
		fightreports.clear();
		bestrank = 0;
		haswin = false;
		fakerank = 0;
	}

	RoleArena(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		challengeranks = new java.util.LinkedList<Integer>();
		challengerobots = new java.util.LinkedList<Long>();
		fightreports = new java.util.LinkedList<xbean.ArenaFightReport>();
	}

	public RoleArena() {
		this(0, null, null);
	}

	public RoleArena(RoleArena _o_) {
		this(_o_, null, null);
	}

	RoleArena(xbean.RoleArena _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleArena) assign((RoleArena)_o1_);
		else if (_o1_ instanceof RoleArena.Data) assign((RoleArena.Data)_o1_);
		else if (_o1_ instanceof RoleArena.Const) assign(((RoleArena.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleArena _o_) {
		_o_._xdb_verify_unsafe_();
		open = _o_.open;
		isrobot = _o_.isrobot;
		lastupdateshengwangtime = _o_.lastupdateshengwangtime;
		challengeranks = new java.util.LinkedList<Integer>();
		challengeranks.addAll(_o_.challengeranks);
		challengerobots = new java.util.LinkedList<Long>();
		challengerobots.addAll(_o_.challengerobots);
		bechallenging = _o_.bechallenging;
		bechallengelockexpiretime = _o_.bechallengelockexpiretime;
		fightreports = new java.util.LinkedList<xbean.ArenaFightReport>();
		for (xbean.ArenaFightReport _v_ : _o_.fightreports)
			fightreports.add(new ArenaFightReport(_v_, this, "fightreports"));
		bestrank = _o_.bestrank;
		haswin = _o_.haswin;
		fakerank = _o_.fakerank;
	}

	private void assign(RoleArena.Data _o_) {
		open = _o_.open;
		isrobot = _o_.isrobot;
		lastupdateshengwangtime = _o_.lastupdateshengwangtime;
		challengeranks = new java.util.LinkedList<Integer>();
		challengeranks.addAll(_o_.challengeranks);
		challengerobots = new java.util.LinkedList<Long>();
		challengerobots.addAll(_o_.challengerobots);
		bechallenging = _o_.bechallenging;
		bechallengelockexpiretime = _o_.bechallengelockexpiretime;
		fightreports = new java.util.LinkedList<xbean.ArenaFightReport>();
		for (xbean.ArenaFightReport _v_ : _o_.fightreports)
			fightreports.add(new ArenaFightReport(_v_, this, "fightreports"));
		bestrank = _o_.bestrank;
		haswin = _o_.haswin;
		fakerank = _o_.fakerank;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)11);
    _os_.marshal((short)( 2048|  9));_os_.marshal(open);
    _os_.marshal((short)( 2048| 10));_os_.marshal(isrobot);
    _os_.marshal((short)(10240|  2));_os_.marshal(lastupdateshengwangtime);
    _os_.marshal((short)(22528|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(challengeranks.size());
for (Integer _v_ : challengeranks) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528| 22));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(challengerobots.size());
for (Long _v_ : challengerobots) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  4));_os_.marshal(bechallenging);
    _os_.marshal((short)(10240|  5));_os_.marshal(bechallengelockexpiretime);
    _os_.marshal((short)(22528|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(fightreports.size());
for (xbean.ArenaFightReport _v_ : fightreports) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  7));_os_.marshal(bestrank);
    _os_.marshal((short)( 2048| 20));_os_.marshal(haswin);
    _os_.marshal((short)( 8192| 21));_os_.marshal(fakerank);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 2048|  9):open = _os_.unmarshal_boolean();
    				break;
    				case ( 2048| 10):isrobot = _os_.unmarshal_boolean();
    				break;
    				case (10240|  2):lastupdateshengwangtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):lastupdateshengwangtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):lastupdateshengwangtime = _os_.unmarshal_int();
    				break;
    				case (22528|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	challengeranks.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528| 22):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	challengerobots.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  4):bechallenging = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):bechallenging = _os_.unmarshal_short();
    				break;
    				case (10240|  5):bechallengelockexpiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):bechallengelockexpiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):bechallengelockexpiretime = _os_.unmarshal_int();
    				break;
    				case (22528|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.ArenaFightReport _v_ = new ArenaFightReport(0, this, "fightreports");
	_v_.unmarshal(_os_);
	fightreports.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  7):bestrank = _os_.unmarshal_int();
    				break;
    				case ( 6144|  7):bestrank = _os_.unmarshal_short();
    				break;
    				case ( 2048| 20):haswin = _os_.unmarshal_boolean();
    				break;
    				case ( 8192| 21):fakerank = _os_.unmarshal_int();
    				break;
    				case ( 6144| 21):fakerank = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleArena copy() {
		_xdb_verify_unsafe_();
		return new RoleArena(this);
	}

	@Override
	public xbean.RoleArena toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleArena toBean() {
		_xdb_verify_unsafe_();
		return new RoleArena(this); // same as copy()
	}

	@Override
	public xbean.RoleArena toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleArena toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public boolean getOpen() { // 
		_xdb_verify_unsafe_();
		return open;
	}

	@Override
	public boolean getIsrobot() { // 
		_xdb_verify_unsafe_();
		return isrobot;
	}

	@Override
	public long getLastupdateshengwangtime() { // 
		_xdb_verify_unsafe_();
		return lastupdateshengwangtime;
	}

	@Override
	public java.util.List<Integer> getChallengeranks() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "challengeranks"), challengeranks);
	}

	public java.util.List<Integer> getChallengeranksAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Integer> challengeranks;
		RoleArena _o_ = this;
		challengeranks = new java.util.LinkedList<Integer>();
		challengeranks.addAll(_o_.challengeranks);
		return challengeranks;
	}

	@Override
	public java.util.List<Long> getChallengerobots() { // 仅仅首次挑战Pvp时有效,其余时候都是空的
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "challengerobots"), challengerobots);
	}

	public java.util.List<Long> getChallengerobotsAsData() { // 仅仅首次挑战Pvp时有效,其余时候都是空的
		_xdb_verify_unsafe_();
		java.util.List<Long> challengerobots;
		RoleArena _o_ = this;
		challengerobots = new java.util.LinkedList<Long>();
		challengerobots.addAll(_o_.challengerobots);
		return challengerobots;
	}

	@Override
	public int getBechallenging() { // 
		_xdb_verify_unsafe_();
		return bechallenging;
	}

	@Override
	public long getBechallengelockexpiretime() { // 
		_xdb_verify_unsafe_();
		return bechallengelockexpiretime;
	}

	@Override
	public java.util.List<xbean.ArenaFightReport> getFightreports() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "fightreports"), fightreports);
	}

	public java.util.List<xbean.ArenaFightReport> getFightreportsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<xbean.ArenaFightReport> fightreports;
		RoleArena _o_ = this;
		fightreports = new java.util.LinkedList<xbean.ArenaFightReport>();
		for (xbean.ArenaFightReport _v_ : _o_.fightreports)
			fightreports.add(new ArenaFightReport.Data(_v_));
		return fightreports;
	}

	@Override
	public int getBestrank() { // 
		_xdb_verify_unsafe_();
		return bestrank;
	}

	@Override
	public boolean getHaswin() { // 是否赢过
		_xdb_verify_unsafe_();
		return haswin;
	}

	@Override
	public int getFakerank() { // 
		_xdb_verify_unsafe_();
		return fakerank;
	}

	@Override
	public void setOpen(boolean _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "open") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, open) {
					public void rollback() { open = _xdb_saved; }
				};}});
		open = _v_;
	}

	@Override
	public void setIsrobot(boolean _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "isrobot") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, isrobot) {
					public void rollback() { isrobot = _xdb_saved; }
				};}});
		isrobot = _v_;
	}

	@Override
	public void setLastupdateshengwangtime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastupdateshengwangtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastupdateshengwangtime) {
					public void rollback() { lastupdateshengwangtime = _xdb_saved; }
				};}});
		lastupdateshengwangtime = _v_;
	}

	@Override
	public void setBechallenging(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "bechallenging") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, bechallenging) {
					public void rollback() { bechallenging = _xdb_saved; }
				};}});
		bechallenging = _v_;
	}

	@Override
	public void setBechallengelockexpiretime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "bechallengelockexpiretime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, bechallengelockexpiretime) {
					public void rollback() { bechallengelockexpiretime = _xdb_saved; }
				};}});
		bechallengelockexpiretime = _v_;
	}

	@Override
	public void setBestrank(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "bestrank") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, bestrank) {
					public void rollback() { bestrank = _xdb_saved; }
				};}});
		bestrank = _v_;
	}

	@Override
	public void setHaswin(boolean _v_) { // 是否赢过
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "haswin") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, haswin) {
					public void rollback() { haswin = _xdb_saved; }
				};}});
		haswin = _v_;
	}

	@Override
	public void setFakerank(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "fakerank") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, fakerank) {
					public void rollback() { fakerank = _xdb_saved; }
				};}});
		fakerank = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleArena _o_ = null;
		if ( _o1_ instanceof RoleArena ) _o_ = (RoleArena)_o1_;
		else if ( _o1_ instanceof RoleArena.Const ) _o_ = ((RoleArena.Const)_o1_).nThis();
		else return false;
		if (open != _o_.open) return false;
		if (isrobot != _o_.isrobot) return false;
		if (lastupdateshengwangtime != _o_.lastupdateshengwangtime) return false;
		if (!challengeranks.equals(_o_.challengeranks)) return false;
		if (!challengerobots.equals(_o_.challengerobots)) return false;
		if (bechallenging != _o_.bechallenging) return false;
		if (bechallengelockexpiretime != _o_.bechallengelockexpiretime) return false;
		if (!fightreports.equals(_o_.fightreports)) return false;
		if (bestrank != _o_.bestrank) return false;
		if (haswin != _o_.haswin) return false;
		if (fakerank != _o_.fakerank) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += open ? 1231 : 1237;
		_h_ += isrobot ? 1231 : 1237;
		_h_ += lastupdateshengwangtime;
		_h_ += challengeranks.hashCode();
		_h_ += challengerobots.hashCode();
		_h_ += bechallenging;
		_h_ += bechallengelockexpiretime;
		_h_ += fightreports.hashCode();
		_h_ += bestrank;
		_h_ += haswin ? 1231 : 1237;
		_h_ += fakerank;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(open);
		_sb_.append(",");
		_sb_.append(isrobot);
		_sb_.append(",");
		_sb_.append(lastupdateshengwangtime);
		_sb_.append(",");
		_sb_.append(challengeranks);
		_sb_.append(",");
		_sb_.append(challengerobots);
		_sb_.append(",");
		_sb_.append(bechallenging);
		_sb_.append(",");
		_sb_.append(bechallengelockexpiretime);
		_sb_.append(",");
		_sb_.append(fightreports);
		_sb_.append(",");
		_sb_.append(bestrank);
		_sb_.append(",");
		_sb_.append(haswin);
		_sb_.append(",");
		_sb_.append(fakerank);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("open"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isrobot"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastupdateshengwangtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("challengeranks"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("challengerobots"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("bechallenging"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("bechallengelockexpiretime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("fightreports"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("bestrank"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("haswin"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("fakerank"));
		return lb;
	}

	private class Const implements xbean.RoleArena {
		RoleArena nThis() {
			return RoleArena.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleArena copy() {
			return RoleArena.this.copy();
		}

		@Override
		public xbean.RoleArena toData() {
			return RoleArena.this.toData();
		}

		public xbean.RoleArena toBean() {
			return RoleArena.this.toBean();
		}

		@Override
		public xbean.RoleArena toDataIf() {
			return RoleArena.this.toDataIf();
		}

		public xbean.RoleArena toBeanIf() {
			return RoleArena.this.toBeanIf();
		}

		@Override
		public boolean getOpen() { // 
			_xdb_verify_unsafe_();
			return open;
		}

		@Override
		public boolean getIsrobot() { // 
			_xdb_verify_unsafe_();
			return isrobot;
		}

		@Override
		public long getLastupdateshengwangtime() { // 
			_xdb_verify_unsafe_();
			return lastupdateshengwangtime;
		}

		@Override
		public java.util.List<Integer> getChallengeranks() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(challengeranks);
		}

		public java.util.List<Integer> getChallengeranksAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Integer> challengeranks;
			RoleArena _o_ = RoleArena.this;
		challengeranks = new java.util.LinkedList<Integer>();
		challengeranks.addAll(_o_.challengeranks);
			return challengeranks;
		}

		@Override
		public java.util.List<Long> getChallengerobots() { // 仅仅首次挑战Pvp时有效,其余时候都是空的
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(challengerobots);
		}

		public java.util.List<Long> getChallengerobotsAsData() { // 仅仅首次挑战Pvp时有效,其余时候都是空的
			_xdb_verify_unsafe_();
			java.util.List<Long> challengerobots;
			RoleArena _o_ = RoleArena.this;
		challengerobots = new java.util.LinkedList<Long>();
		challengerobots.addAll(_o_.challengerobots);
			return challengerobots;
		}

		@Override
		public int getBechallenging() { // 
			_xdb_verify_unsafe_();
			return bechallenging;
		}

		@Override
		public long getBechallengelockexpiretime() { // 
			_xdb_verify_unsafe_();
			return bechallengelockexpiretime;
		}

		@Override
		public java.util.List<xbean.ArenaFightReport> getFightreports() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(fightreports);
		}

		public java.util.List<xbean.ArenaFightReport> getFightreportsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<xbean.ArenaFightReport> fightreports;
			RoleArena _o_ = RoleArena.this;
		fightreports = new java.util.LinkedList<xbean.ArenaFightReport>();
		for (xbean.ArenaFightReport _v_ : _o_.fightreports)
			fightreports.add(new ArenaFightReport.Data(_v_));
			return fightreports;
		}

		@Override
		public int getBestrank() { // 
			_xdb_verify_unsafe_();
			return bestrank;
		}

		@Override
		public boolean getHaswin() { // 是否赢过
			_xdb_verify_unsafe_();
			return haswin;
		}

		@Override
		public int getFakerank() { // 
			_xdb_verify_unsafe_();
			return fakerank;
		}

		@Override
		public void setOpen(boolean _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsrobot(boolean _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastupdateshengwangtime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBechallenging(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBechallengelockexpiretime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBestrank(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHaswin(boolean _v_) { // 是否赢过
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFakerank(int _v_) { // 
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
			return RoleArena.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleArena.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleArena.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleArena.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleArena.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleArena.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleArena.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleArena.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleArena.this.toString();
		}

	}

	public static final class Data implements xbean.RoleArena {
		private boolean open; // 
		private boolean isrobot; // 
		private long lastupdateshengwangtime; // 
		private java.util.LinkedList<Integer> challengeranks; // 
		private java.util.LinkedList<Long> challengerobots; // 仅仅首次挑战Pvp时有效,其余时候都是空的
		private int bechallenging; // 
		private long bechallengelockexpiretime; // 
		private java.util.LinkedList<xbean.ArenaFightReport> fightreports; // 
		private int bestrank; // 
		private boolean haswin; // 是否赢过
		private int fakerank; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			challengeranks = new java.util.LinkedList<Integer>();
			challengerobots = new java.util.LinkedList<Long>();
			fightreports = new java.util.LinkedList<xbean.ArenaFightReport>();
		}

		Data(xbean.RoleArena _o1_) {
			if (_o1_ instanceof RoleArena) assign((RoleArena)_o1_);
			else if (_o1_ instanceof RoleArena.Data) assign((RoleArena.Data)_o1_);
			else if (_o1_ instanceof RoleArena.Const) assign(((RoleArena.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleArena _o_) {
			open = _o_.open;
			isrobot = _o_.isrobot;
			lastupdateshengwangtime = _o_.lastupdateshengwangtime;
			challengeranks = new java.util.LinkedList<Integer>();
			challengeranks.addAll(_o_.challengeranks);
			challengerobots = new java.util.LinkedList<Long>();
			challengerobots.addAll(_o_.challengerobots);
			bechallenging = _o_.bechallenging;
			bechallengelockexpiretime = _o_.bechallengelockexpiretime;
			fightreports = new java.util.LinkedList<xbean.ArenaFightReport>();
			for (xbean.ArenaFightReport _v_ : _o_.fightreports)
				fightreports.add(new ArenaFightReport.Data(_v_));
			bestrank = _o_.bestrank;
			haswin = _o_.haswin;
			fakerank = _o_.fakerank;
		}

		private void assign(RoleArena.Data _o_) {
			open = _o_.open;
			isrobot = _o_.isrobot;
			lastupdateshengwangtime = _o_.lastupdateshengwangtime;
			challengeranks = new java.util.LinkedList<Integer>();
			challengeranks.addAll(_o_.challengeranks);
			challengerobots = new java.util.LinkedList<Long>();
			challengerobots.addAll(_o_.challengerobots);
			bechallenging = _o_.bechallenging;
			bechallengelockexpiretime = _o_.bechallengelockexpiretime;
			fightreports = new java.util.LinkedList<xbean.ArenaFightReport>();
			for (xbean.ArenaFightReport _v_ : _o_.fightreports)
				fightreports.add(new ArenaFightReport.Data(_v_));
			bestrank = _o_.bestrank;
			haswin = _o_.haswin;
			fakerank = _o_.fakerank;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)11);
	_os_.marshal((short)( 2048|  9));_os_.marshal(open);
	_os_.marshal((short)( 2048| 10));_os_.marshal(isrobot);
	_os_.marshal((short)(10240|  2));_os_.marshal(lastupdateshengwangtime);
	_os_.marshal((short)(22528|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(challengeranks.size());
for (Integer _v_ : challengeranks) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528| 22));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(challengerobots.size());
for (Long _v_ : challengerobots) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  4));_os_.marshal(bechallenging);
	_os_.marshal((short)(10240|  5));_os_.marshal(bechallengelockexpiretime);
	_os_.marshal((short)(22528|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(fightreports.size());
for (xbean.ArenaFightReport _v_ : fightreports) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  7));_os_.marshal(bestrank);
	_os_.marshal((short)( 2048| 20));_os_.marshal(haswin);
	_os_.marshal((short)( 8192| 21));_os_.marshal(fakerank);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 2048|  9):open = _os_.unmarshal_boolean();
					break;
					case ( 2048| 10):isrobot = _os_.unmarshal_boolean();
					break;
					case (10240|  2):lastupdateshengwangtime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):lastupdateshengwangtime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):lastupdateshengwangtime = _os_.unmarshal_int();
					break;
					case (22528|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	challengeranks.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528| 22):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	challengerobots.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192|  4):bechallenging = _os_.unmarshal_int();
					break;
					case ( 6144|  4):bechallenging = _os_.unmarshal_short();
					break;
					case (10240|  5):bechallengelockexpiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  5):bechallengelockexpiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  5):bechallengelockexpiretime = _os_.unmarshal_int();
					break;
					case (22528|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.ArenaFightReport _v_ = xbean.Pod.newArenaFightReportData();
	_v_.unmarshal(_os_);
	fightreports.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192|  7):bestrank = _os_.unmarshal_int();
					break;
					case ( 6144|  7):bestrank = _os_.unmarshal_short();
					break;
					case ( 2048| 20):haswin = _os_.unmarshal_boolean();
					break;
					case ( 8192| 21):fakerank = _os_.unmarshal_int();
					break;
					case ( 6144| 21):fakerank = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleArena copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleArena toData() {
			return new Data(this);
		}

		public xbean.RoleArena toBean() {
			return new RoleArena(this, null, null);
		}

		@Override
		public xbean.RoleArena toDataIf() {
			return this;
		}

		public xbean.RoleArena toBeanIf() {
			return new RoleArena(this, null, null);
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
		public boolean getOpen() { // 
			return open;
		}

		@Override
		public boolean getIsrobot() { // 
			return isrobot;
		}

		@Override
		public long getLastupdateshengwangtime() { // 
			return lastupdateshengwangtime;
		}

		@Override
		public java.util.List<Integer> getChallengeranks() { // 
			return challengeranks;
		}

		@Override
		public java.util.List<Integer> getChallengeranksAsData() { // 
			return challengeranks;
		}

		@Override
		public java.util.List<Long> getChallengerobots() { // 仅仅首次挑战Pvp时有效,其余时候都是空的
			return challengerobots;
		}

		@Override
		public java.util.List<Long> getChallengerobotsAsData() { // 仅仅首次挑战Pvp时有效,其余时候都是空的
			return challengerobots;
		}

		@Override
		public int getBechallenging() { // 
			return bechallenging;
		}

		@Override
		public long getBechallengelockexpiretime() { // 
			return bechallengelockexpiretime;
		}

		@Override
		public java.util.List<xbean.ArenaFightReport> getFightreports() { // 
			return fightreports;
		}

		@Override
		public java.util.List<xbean.ArenaFightReport> getFightreportsAsData() { // 
			return fightreports;
		}

		@Override
		public int getBestrank() { // 
			return bestrank;
		}

		@Override
		public boolean getHaswin() { // 是否赢过
			return haswin;
		}

		@Override
		public int getFakerank() { // 
			return fakerank;
		}

		@Override
		public void setOpen(boolean _v_) { // 
			open = _v_;
		}

		@Override
		public void setIsrobot(boolean _v_) { // 
			isrobot = _v_;
		}

		@Override
		public void setLastupdateshengwangtime(long _v_) { // 
			lastupdateshengwangtime = _v_;
		}

		@Override
		public void setBechallenging(int _v_) { // 
			bechallenging = _v_;
		}

		@Override
		public void setBechallengelockexpiretime(long _v_) { // 
			bechallengelockexpiretime = _v_;
		}

		@Override
		public void setBestrank(int _v_) { // 
			bestrank = _v_;
		}

		@Override
		public void setHaswin(boolean _v_) { // 是否赢过
			haswin = _v_;
		}

		@Override
		public void setFakerank(int _v_) { // 
			fakerank = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleArena.Data)) return false;
			RoleArena.Data _o_ = (RoleArena.Data) _o1_;
			if (open != _o_.open) return false;
			if (isrobot != _o_.isrobot) return false;
			if (lastupdateshengwangtime != _o_.lastupdateshengwangtime) return false;
			if (!challengeranks.equals(_o_.challengeranks)) return false;
			if (!challengerobots.equals(_o_.challengerobots)) return false;
			if (bechallenging != _o_.bechallenging) return false;
			if (bechallengelockexpiretime != _o_.bechallengelockexpiretime) return false;
			if (!fightreports.equals(_o_.fightreports)) return false;
			if (bestrank != _o_.bestrank) return false;
			if (haswin != _o_.haswin) return false;
			if (fakerank != _o_.fakerank) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += open ? 1231 : 1237;
			_h_ += isrobot ? 1231 : 1237;
			_h_ += lastupdateshengwangtime;
			_h_ += challengeranks.hashCode();
			_h_ += challengerobots.hashCode();
			_h_ += bechallenging;
			_h_ += bechallengelockexpiretime;
			_h_ += fightreports.hashCode();
			_h_ += bestrank;
			_h_ += haswin ? 1231 : 1237;
			_h_ += fakerank;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(open);
			_sb_.append(",");
			_sb_.append(isrobot);
			_sb_.append(",");
			_sb_.append(lastupdateshengwangtime);
			_sb_.append(",");
			_sb_.append(challengeranks);
			_sb_.append(",");
			_sb_.append(challengerobots);
			_sb_.append(",");
			_sb_.append(bechallenging);
			_sb_.append(",");
			_sb_.append(bechallengelockexpiretime);
			_sb_.append(",");
			_sb_.append(fightreports);
			_sb_.append(",");
			_sb_.append(bestrank);
			_sb_.append(",");
			_sb_.append(haswin);
			_sb_.append(",");
			_sb_.append(fakerank);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
