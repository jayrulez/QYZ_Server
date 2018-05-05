
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ArenaFightReport extends xdb.XBean implements xbean.ArenaFightReport {
	private long fighttime; // 
	private int challengetype; // 
	private int succ; // 
	private int newrank; // 
	private String opponentname; // 
	private int oldrank; // 

	@Override
	public void _reset_unsafe_() {
		fighttime = 0L;
		challengetype = 0;
		succ = 0;
		newrank = 0;
		opponentname = "";
		oldrank = 0;
	}

	ArenaFightReport(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		opponentname = "";
	}

	public ArenaFightReport() {
		this(0, null, null);
	}

	public ArenaFightReport(ArenaFightReport _o_) {
		this(_o_, null, null);
	}

	ArenaFightReport(xbean.ArenaFightReport _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ArenaFightReport) assign((ArenaFightReport)_o1_);
		else if (_o1_ instanceof ArenaFightReport.Data) assign((ArenaFightReport.Data)_o1_);
		else if (_o1_ instanceof ArenaFightReport.Const) assign(((ArenaFightReport.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ArenaFightReport _o_) {
		_o_._xdb_verify_unsafe_();
		fighttime = _o_.fighttime;
		challengetype = _o_.challengetype;
		succ = _o_.succ;
		newrank = _o_.newrank;
		opponentname = _o_.opponentname;
		oldrank = _o_.oldrank;
	}

	private void assign(ArenaFightReport.Data _o_) {
		fighttime = _o_.fighttime;
		challengetype = _o_.challengetype;
		succ = _o_.succ;
		newrank = _o_.newrank;
		opponentname = _o_.opponentname;
		oldrank = _o_.oldrank;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)(10240|  1));_os_.marshal(fighttime);
    _os_.marshal((short)( 8192|  2));_os_.marshal(challengetype);
    _os_.marshal((short)( 8192|  3));_os_.marshal(succ);
    _os_.marshal((short)( 8192|  4));_os_.marshal(newrank);
    _os_.marshal((short)(18432|  7));_os_.marshal(opponentname, xdb.Const.IO_CHARSET);
    _os_.marshal((short)( 8192|  8));_os_.marshal(oldrank);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):fighttime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):fighttime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):fighttime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):challengetype = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):challengetype = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):succ = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):succ = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):newrank = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):newrank = _os_.unmarshal_short();
    				break;
    				case (18432|  7):opponentname = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case ( 8192|  8):oldrank = _os_.unmarshal_int();
    				break;
    				case ( 6144|  8):oldrank = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ArenaFightReport copy() {
		_xdb_verify_unsafe_();
		return new ArenaFightReport(this);
	}

	@Override
	public xbean.ArenaFightReport toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ArenaFightReport toBean() {
		_xdb_verify_unsafe_();
		return new ArenaFightReport(this); // same as copy()
	}

	@Override
	public xbean.ArenaFightReport toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ArenaFightReport toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getFighttime() { // 
		_xdb_verify_unsafe_();
		return fighttime;
	}

	@Override
	public int getChallengetype() { // 
		_xdb_verify_unsafe_();
		return challengetype;
	}

	@Override
	public int getSucc() { // 
		_xdb_verify_unsafe_();
		return succ;
	}

	@Override
	public int getNewrank() { // 
		_xdb_verify_unsafe_();
		return newrank;
	}

	@Override
	public String getOpponentname() { // 
		_xdb_verify_unsafe_();
		return opponentname;
	}

	@Override
	public com.goldhuman.Common.Octets getOpponentnameOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getOpponentname(), xdb.Const.IO_CHARSET);
	}

	@Override
	public int getOldrank() { // 
		_xdb_verify_unsafe_();
		return oldrank;
	}

	@Override
	public void setFighttime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "fighttime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, fighttime) {
					public void rollback() { fighttime = _xdb_saved; }
				};}});
		fighttime = _v_;
	}

	@Override
	public void setChallengetype(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "challengetype") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, challengetype) {
					public void rollback() { challengetype = _xdb_saved; }
				};}});
		challengetype = _v_;
	}

	@Override
	public void setSucc(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "succ") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, succ) {
					public void rollback() { succ = _xdb_saved; }
				};}});
		succ = _v_;
	}

	@Override
	public void setNewrank(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "newrank") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, newrank) {
					public void rollback() { newrank = _xdb_saved; }
				};}});
		newrank = _v_;
	}

	@Override
	public void setOpponentname(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "opponentname") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, opponentname) {
					public void rollback() { opponentname = _xdb_saved; }
				};}});
		opponentname = _v_;
	}

	@Override
	public void setOpponentnameOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setOpponentname(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setOldrank(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "oldrank") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, oldrank) {
					public void rollback() { oldrank = _xdb_saved; }
				};}});
		oldrank = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ArenaFightReport _o_ = null;
		if ( _o1_ instanceof ArenaFightReport ) _o_ = (ArenaFightReport)_o1_;
		else if ( _o1_ instanceof ArenaFightReport.Const ) _o_ = ((ArenaFightReport.Const)_o1_).nThis();
		else return false;
		if (fighttime != _o_.fighttime) return false;
		if (challengetype != _o_.challengetype) return false;
		if (succ != _o_.succ) return false;
		if (newrank != _o_.newrank) return false;
		if (!opponentname.equals(_o_.opponentname)) return false;
		if (oldrank != _o_.oldrank) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += fighttime;
		_h_ += challengetype;
		_h_ += succ;
		_h_ += newrank;
		_h_ += opponentname.hashCode();
		_h_ += oldrank;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(fighttime);
		_sb_.append(",");
		_sb_.append(challengetype);
		_sb_.append(",");
		_sb_.append(succ);
		_sb_.append(",");
		_sb_.append(newrank);
		_sb_.append(",");
		_sb_.append("'").append(opponentname).append("'");
		_sb_.append(",");
		_sb_.append(oldrank);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("fighttime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("challengetype"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("succ"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("newrank"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("opponentname"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("oldrank"));
		return lb;
	}

	private class Const implements xbean.ArenaFightReport {
		ArenaFightReport nThis() {
			return ArenaFightReport.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ArenaFightReport copy() {
			return ArenaFightReport.this.copy();
		}

		@Override
		public xbean.ArenaFightReport toData() {
			return ArenaFightReport.this.toData();
		}

		public xbean.ArenaFightReport toBean() {
			return ArenaFightReport.this.toBean();
		}

		@Override
		public xbean.ArenaFightReport toDataIf() {
			return ArenaFightReport.this.toDataIf();
		}

		public xbean.ArenaFightReport toBeanIf() {
			return ArenaFightReport.this.toBeanIf();
		}

		@Override
		public long getFighttime() { // 
			_xdb_verify_unsafe_();
			return fighttime;
		}

		@Override
		public int getChallengetype() { // 
			_xdb_verify_unsafe_();
			return challengetype;
		}

		@Override
		public int getSucc() { // 
			_xdb_verify_unsafe_();
			return succ;
		}

		@Override
		public int getNewrank() { // 
			_xdb_verify_unsafe_();
			return newrank;
		}

		@Override
		public String getOpponentname() { // 
			_xdb_verify_unsafe_();
			return opponentname;
		}

		@Override
		public com.goldhuman.Common.Octets getOpponentnameOctets() { // 
			_xdb_verify_unsafe_();
			return ArenaFightReport.this.getOpponentnameOctets();
		}

		@Override
		public int getOldrank() { // 
			_xdb_verify_unsafe_();
			return oldrank;
		}

		@Override
		public void setFighttime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setChallengetype(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setSucc(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNewrank(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setOpponentname(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setOpponentnameOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setOldrank(int _v_) { // 
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
			return ArenaFightReport.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ArenaFightReport.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ArenaFightReport.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ArenaFightReport.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ArenaFightReport.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ArenaFightReport.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ArenaFightReport.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ArenaFightReport.this.hashCode();
		}

		@Override
		public String toString() {
			return ArenaFightReport.this.toString();
		}

	}

	public static final class Data implements xbean.ArenaFightReport {
		private long fighttime; // 
		private int challengetype; // 
		private int succ; // 
		private int newrank; // 
		private String opponentname; // 
		private int oldrank; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			opponentname = "";
		}

		Data(xbean.ArenaFightReport _o1_) {
			if (_o1_ instanceof ArenaFightReport) assign((ArenaFightReport)_o1_);
			else if (_o1_ instanceof ArenaFightReport.Data) assign((ArenaFightReport.Data)_o1_);
			else if (_o1_ instanceof ArenaFightReport.Const) assign(((ArenaFightReport.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ArenaFightReport _o_) {
			fighttime = _o_.fighttime;
			challengetype = _o_.challengetype;
			succ = _o_.succ;
			newrank = _o_.newrank;
			opponentname = _o_.opponentname;
			oldrank = _o_.oldrank;
		}

		private void assign(ArenaFightReport.Data _o_) {
			fighttime = _o_.fighttime;
			challengetype = _o_.challengetype;
			succ = _o_.succ;
			newrank = _o_.newrank;
			opponentname = _o_.opponentname;
			oldrank = _o_.oldrank;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)(10240|  1));_os_.marshal(fighttime);
	_os_.marshal((short)( 8192|  2));_os_.marshal(challengetype);
	_os_.marshal((short)( 8192|  3));_os_.marshal(succ);
	_os_.marshal((short)( 8192|  4));_os_.marshal(newrank);
	_os_.marshal((short)(18432|  7));_os_.marshal(opponentname, xdb.Const.IO_CHARSET);
	_os_.marshal((short)( 8192|  8));_os_.marshal(oldrank);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):fighttime = _os_.unmarshal_long();
					break;
					case ( 6144|  1):fighttime = _os_.unmarshal_short();
					break;
					case ( 8192|  1):fighttime = _os_.unmarshal_int();
					break;
					case ( 8192|  2):challengetype = _os_.unmarshal_int();
					break;
					case ( 6144|  2):challengetype = _os_.unmarshal_short();
					break;
					case ( 8192|  3):succ = _os_.unmarshal_int();
					break;
					case ( 6144|  3):succ = _os_.unmarshal_short();
					break;
					case ( 8192|  4):newrank = _os_.unmarshal_int();
					break;
					case ( 6144|  4):newrank = _os_.unmarshal_short();
					break;
					case (18432|  7):opponentname = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case ( 8192|  8):oldrank = _os_.unmarshal_int();
					break;
					case ( 6144|  8):oldrank = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ArenaFightReport copy() {
			return new Data(this);
		}

		@Override
		public xbean.ArenaFightReport toData() {
			return new Data(this);
		}

		public xbean.ArenaFightReport toBean() {
			return new ArenaFightReport(this, null, null);
		}

		@Override
		public xbean.ArenaFightReport toDataIf() {
			return this;
		}

		public xbean.ArenaFightReport toBeanIf() {
			return new ArenaFightReport(this, null, null);
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
		public long getFighttime() { // 
			return fighttime;
		}

		@Override
		public int getChallengetype() { // 
			return challengetype;
		}

		@Override
		public int getSucc() { // 
			return succ;
		}

		@Override
		public int getNewrank() { // 
			return newrank;
		}

		@Override
		public String getOpponentname() { // 
			return opponentname;
		}

		@Override
		public com.goldhuman.Common.Octets getOpponentnameOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getOpponentname(), xdb.Const.IO_CHARSET);
		}

		@Override
		public int getOldrank() { // 
			return oldrank;
		}

		@Override
		public void setFighttime(long _v_) { // 
			fighttime = _v_;
		}

		@Override
		public void setChallengetype(int _v_) { // 
			challengetype = _v_;
		}

		@Override
		public void setSucc(int _v_) { // 
			succ = _v_;
		}

		@Override
		public void setNewrank(int _v_) { // 
			newrank = _v_;
		}

		@Override
		public void setOpponentname(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			opponentname = _v_;
		}

		@Override
		public void setOpponentnameOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setOpponentname(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setOldrank(int _v_) { // 
			oldrank = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ArenaFightReport.Data)) return false;
			ArenaFightReport.Data _o_ = (ArenaFightReport.Data) _o1_;
			if (fighttime != _o_.fighttime) return false;
			if (challengetype != _o_.challengetype) return false;
			if (succ != _o_.succ) return false;
			if (newrank != _o_.newrank) return false;
			if (!opponentname.equals(_o_.opponentname)) return false;
			if (oldrank != _o_.oldrank) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += fighttime;
			_h_ += challengetype;
			_h_ += succ;
			_h_ += newrank;
			_h_ += opponentname.hashCode();
			_h_ += oldrank;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(fighttime);
			_sb_.append(",");
			_sb_.append(challengetype);
			_sb_.append(",");
			_sb_.append(succ);
			_sb_.append(",");
			_sb_.append(newrank);
			_sb_.append(",");
			_sb_.append("'").append(opponentname).append("'");
			_sb_.append(",");
			_sb_.append(oldrank);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
