
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Enemyinfo extends xdb.XBean implements xbean.Enemyinfo {
	private int bekillnum; // 
	private int killnum; // 
	private long lastbekilltime; // 最后被击杀的时间

	@Override
	public void _reset_unsafe_() {
		bekillnum = 0;
		killnum = 0;
		lastbekilltime = 0L;
	}

	Enemyinfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public Enemyinfo() {
		this(0, null, null);
	}

	public Enemyinfo(Enemyinfo _o_) {
		this(_o_, null, null);
	}

	Enemyinfo(xbean.Enemyinfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Enemyinfo) assign((Enemyinfo)_o1_);
		else if (_o1_ instanceof Enemyinfo.Data) assign((Enemyinfo.Data)_o1_);
		else if (_o1_ instanceof Enemyinfo.Const) assign(((Enemyinfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Enemyinfo _o_) {
		_o_._xdb_verify_unsafe_();
		bekillnum = _o_.bekillnum;
		killnum = _o_.killnum;
		lastbekilltime = _o_.lastbekilltime;
	}

	private void assign(Enemyinfo.Data _o_) {
		bekillnum = _o_.bekillnum;
		killnum = _o_.killnum;
		lastbekilltime = _o_.lastbekilltime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  1));_os_.marshal(bekillnum);
    _os_.marshal((short)( 8192|  2));_os_.marshal(killnum);
    _os_.marshal((short)(10240|  3));_os_.marshal(lastbekilltime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):bekillnum = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):bekillnum = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):killnum = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):killnum = _os_.unmarshal_short();
    				break;
    				case (10240|  3):lastbekilltime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):lastbekilltime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):lastbekilltime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Enemyinfo copy() {
		_xdb_verify_unsafe_();
		return new Enemyinfo(this);
	}

	@Override
	public xbean.Enemyinfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Enemyinfo toBean() {
		_xdb_verify_unsafe_();
		return new Enemyinfo(this); // same as copy()
	}

	@Override
	public xbean.Enemyinfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Enemyinfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getBekillnum() { // 
		_xdb_verify_unsafe_();
		return bekillnum;
	}

	@Override
	public int getKillnum() { // 
		_xdb_verify_unsafe_();
		return killnum;
	}

	@Override
	public long getLastbekilltime() { // 最后被击杀的时间
		_xdb_verify_unsafe_();
		return lastbekilltime;
	}

	@Override
	public void setBekillnum(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "bekillnum") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, bekillnum) {
					public void rollback() { bekillnum = _xdb_saved; }
				};}});
		bekillnum = _v_;
	}

	@Override
	public void setKillnum(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "killnum") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, killnum) {
					public void rollback() { killnum = _xdb_saved; }
				};}});
		killnum = _v_;
	}

	@Override
	public void setLastbekilltime(long _v_) { // 最后被击杀的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastbekilltime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastbekilltime) {
					public void rollback() { lastbekilltime = _xdb_saved; }
				};}});
		lastbekilltime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Enemyinfo _o_ = null;
		if ( _o1_ instanceof Enemyinfo ) _o_ = (Enemyinfo)_o1_;
		else if ( _o1_ instanceof Enemyinfo.Const ) _o_ = ((Enemyinfo.Const)_o1_).nThis();
		else return false;
		if (bekillnum != _o_.bekillnum) return false;
		if (killnum != _o_.killnum) return false;
		if (lastbekilltime != _o_.lastbekilltime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += bekillnum;
		_h_ += killnum;
		_h_ += lastbekilltime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bekillnum);
		_sb_.append(",");
		_sb_.append(killnum);
		_sb_.append(",");
		_sb_.append(lastbekilltime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("bekillnum"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("killnum"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastbekilltime"));
		return lb;
	}

	private class Const implements xbean.Enemyinfo {
		Enemyinfo nThis() {
			return Enemyinfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Enemyinfo copy() {
			return Enemyinfo.this.copy();
		}

		@Override
		public xbean.Enemyinfo toData() {
			return Enemyinfo.this.toData();
		}

		public xbean.Enemyinfo toBean() {
			return Enemyinfo.this.toBean();
		}

		@Override
		public xbean.Enemyinfo toDataIf() {
			return Enemyinfo.this.toDataIf();
		}

		public xbean.Enemyinfo toBeanIf() {
			return Enemyinfo.this.toBeanIf();
		}

		@Override
		public int getBekillnum() { // 
			_xdb_verify_unsafe_();
			return bekillnum;
		}

		@Override
		public int getKillnum() { // 
			_xdb_verify_unsafe_();
			return killnum;
		}

		@Override
		public long getLastbekilltime() { // 最后被击杀的时间
			_xdb_verify_unsafe_();
			return lastbekilltime;
		}

		@Override
		public void setBekillnum(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setKillnum(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastbekilltime(long _v_) { // 最后被击杀的时间
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
			return Enemyinfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Enemyinfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Enemyinfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Enemyinfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Enemyinfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Enemyinfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Enemyinfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Enemyinfo.this.hashCode();
		}

		@Override
		public String toString() {
			return Enemyinfo.this.toString();
		}

	}

	public static final class Data implements xbean.Enemyinfo {
		private int bekillnum; // 
		private int killnum; // 
		private long lastbekilltime; // 最后被击杀的时间

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.Enemyinfo _o1_) {
			if (_o1_ instanceof Enemyinfo) assign((Enemyinfo)_o1_);
			else if (_o1_ instanceof Enemyinfo.Data) assign((Enemyinfo.Data)_o1_);
			else if (_o1_ instanceof Enemyinfo.Const) assign(((Enemyinfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Enemyinfo _o_) {
			bekillnum = _o_.bekillnum;
			killnum = _o_.killnum;
			lastbekilltime = _o_.lastbekilltime;
		}

		private void assign(Enemyinfo.Data _o_) {
			bekillnum = _o_.bekillnum;
			killnum = _o_.killnum;
			lastbekilltime = _o_.lastbekilltime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  1));_os_.marshal(bekillnum);
	_os_.marshal((short)( 8192|  2));_os_.marshal(killnum);
	_os_.marshal((short)(10240|  3));_os_.marshal(lastbekilltime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):bekillnum = _os_.unmarshal_int();
					break;
					case ( 6144|  1):bekillnum = _os_.unmarshal_short();
					break;
					case ( 8192|  2):killnum = _os_.unmarshal_int();
					break;
					case ( 6144|  2):killnum = _os_.unmarshal_short();
					break;
					case (10240|  3):lastbekilltime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):lastbekilltime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):lastbekilltime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Enemyinfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.Enemyinfo toData() {
			return new Data(this);
		}

		public xbean.Enemyinfo toBean() {
			return new Enemyinfo(this, null, null);
		}

		@Override
		public xbean.Enemyinfo toDataIf() {
			return this;
		}

		public xbean.Enemyinfo toBeanIf() {
			return new Enemyinfo(this, null, null);
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
		public int getBekillnum() { // 
			return bekillnum;
		}

		@Override
		public int getKillnum() { // 
			return killnum;
		}

		@Override
		public long getLastbekilltime() { // 最后被击杀的时间
			return lastbekilltime;
		}

		@Override
		public void setBekillnum(int _v_) { // 
			bekillnum = _v_;
		}

		@Override
		public void setKillnum(int _v_) { // 
			killnum = _v_;
		}

		@Override
		public void setLastbekilltime(long _v_) { // 最后被击杀的时间
			lastbekilltime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Enemyinfo.Data)) return false;
			Enemyinfo.Data _o_ = (Enemyinfo.Data) _o1_;
			if (bekillnum != _o_.bekillnum) return false;
			if (killnum != _o_.killnum) return false;
			if (lastbekilltime != _o_.lastbekilltime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += bekillnum;
			_h_ += killnum;
			_h_ += lastbekilltime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(bekillnum);
			_sb_.append(",");
			_sb_.append(killnum);
			_sb_.append(",");
			_sb_.append(lastbekilltime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
