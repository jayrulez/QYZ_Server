
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Title extends xdb.XBean implements xbean.Title {
	private int titlekey; // 称号id
	private int titletype; // 称号所属类型，战力榜，竞技场榜，财富榜等
	private int state; // 称号的状态，0为未获得，1为获得，2为激活
	private long gettime; // 称号获得的时间
	private long activetime; // 称号激活的时间
	private long expiretime; // 称号到期时间

	@Override
	public void _reset_unsafe_() {
		titlekey = 0;
		titletype = 0;
		state = 0;
		gettime = 0L;
		activetime = 0L;
		expiretime = 0L;
	}

	Title(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public Title() {
		this(0, null, null);
	}

	public Title(Title _o_) {
		this(_o_, null, null);
	}

	Title(xbean.Title _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Title) assign((Title)_o1_);
		else if (_o1_ instanceof Title.Data) assign((Title.Data)_o1_);
		else if (_o1_ instanceof Title.Const) assign(((Title.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Title _o_) {
		_o_._xdb_verify_unsafe_();
		titlekey = _o_.titlekey;
		titletype = _o_.titletype;
		state = _o_.state;
		gettime = _o_.gettime;
		activetime = _o_.activetime;
		expiretime = _o_.expiretime;
	}

	private void assign(Title.Data _o_) {
		titlekey = _o_.titlekey;
		titletype = _o_.titletype;
		state = _o_.state;
		gettime = _o_.gettime;
		activetime = _o_.activetime;
		expiretime = _o_.expiretime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)( 8192|  1));_os_.marshal(titlekey);
    _os_.marshal((short)( 8192|  2));_os_.marshal(titletype);
    _os_.marshal((short)( 8192|  3));_os_.marshal(state);
    _os_.marshal((short)(10240|  4));_os_.marshal(gettime);
    _os_.marshal((short)(10240|  5));_os_.marshal(activetime);
    _os_.marshal((short)(10240|  6));_os_.marshal(expiretime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):titlekey = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):titlekey = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):titletype = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):titletype = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):state = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):state = _os_.unmarshal_short();
    				break;
    				case (10240|  4):gettime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):gettime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):gettime = _os_.unmarshal_int();
    				break;
    				case (10240|  5):activetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):activetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):activetime = _os_.unmarshal_int();
    				break;
    				case (10240|  6):expiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  6):expiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):expiretime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Title copy() {
		_xdb_verify_unsafe_();
		return new Title(this);
	}

	@Override
	public xbean.Title toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Title toBean() {
		_xdb_verify_unsafe_();
		return new Title(this); // same as copy()
	}

	@Override
	public xbean.Title toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Title toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getTitlekey() { // 称号id
		_xdb_verify_unsafe_();
		return titlekey;
	}

	@Override
	public int getTitletype() { // 称号所属类型，战力榜，竞技场榜，财富榜等
		_xdb_verify_unsafe_();
		return titletype;
	}

	@Override
	public int getState() { // 称号的状态，0为未获得，1为获得，2为激活
		_xdb_verify_unsafe_();
		return state;
	}

	@Override
	public long getGettime() { // 称号获得的时间
		_xdb_verify_unsafe_();
		return gettime;
	}

	@Override
	public long getActivetime() { // 称号激活的时间
		_xdb_verify_unsafe_();
		return activetime;
	}

	@Override
	public long getExpiretime() { // 称号到期时间
		_xdb_verify_unsafe_();
		return expiretime;
	}

	@Override
	public void setTitlekey(int _v_) { // 称号id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "titlekey") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, titlekey) {
					public void rollback() { titlekey = _xdb_saved; }
				};}});
		titlekey = _v_;
	}

	@Override
	public void setTitletype(int _v_) { // 称号所属类型，战力榜，竞技场榜，财富榜等
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "titletype") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, titletype) {
					public void rollback() { titletype = _xdb_saved; }
				};}});
		titletype = _v_;
	}

	@Override
	public void setState(int _v_) { // 称号的状态，0为未获得，1为获得，2为激活
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "state") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, state) {
					public void rollback() { state = _xdb_saved; }
				};}});
		state = _v_;
	}

	@Override
	public void setGettime(long _v_) { // 称号获得的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "gettime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, gettime) {
					public void rollback() { gettime = _xdb_saved; }
				};}});
		gettime = _v_;
	}

	@Override
	public void setActivetime(long _v_) { // 称号激活的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "activetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, activetime) {
					public void rollback() { activetime = _xdb_saved; }
				};}});
		activetime = _v_;
	}

	@Override
	public void setExpiretime(long _v_) { // 称号到期时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "expiretime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, expiretime) {
					public void rollback() { expiretime = _xdb_saved; }
				};}});
		expiretime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Title _o_ = null;
		if ( _o1_ instanceof Title ) _o_ = (Title)_o1_;
		else if ( _o1_ instanceof Title.Const ) _o_ = ((Title.Const)_o1_).nThis();
		else return false;
		if (titlekey != _o_.titlekey) return false;
		if (titletype != _o_.titletype) return false;
		if (state != _o_.state) return false;
		if (gettime != _o_.gettime) return false;
		if (activetime != _o_.activetime) return false;
		if (expiretime != _o_.expiretime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += titlekey;
		_h_ += titletype;
		_h_ += state;
		_h_ += gettime;
		_h_ += activetime;
		_h_ += expiretime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(titlekey);
		_sb_.append(",");
		_sb_.append(titletype);
		_sb_.append(",");
		_sb_.append(state);
		_sb_.append(",");
		_sb_.append(gettime);
		_sb_.append(",");
		_sb_.append(activetime);
		_sb_.append(",");
		_sb_.append(expiretime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("titlekey"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("titletype"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("state"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("gettime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("activetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("expiretime"));
		return lb;
	}

	private class Const implements xbean.Title {
		Title nThis() {
			return Title.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Title copy() {
			return Title.this.copy();
		}

		@Override
		public xbean.Title toData() {
			return Title.this.toData();
		}

		public xbean.Title toBean() {
			return Title.this.toBean();
		}

		@Override
		public xbean.Title toDataIf() {
			return Title.this.toDataIf();
		}

		public xbean.Title toBeanIf() {
			return Title.this.toBeanIf();
		}

		@Override
		public int getTitlekey() { // 称号id
			_xdb_verify_unsafe_();
			return titlekey;
		}

		@Override
		public int getTitletype() { // 称号所属类型，战力榜，竞技场榜，财富榜等
			_xdb_verify_unsafe_();
			return titletype;
		}

		@Override
		public int getState() { // 称号的状态，0为未获得，1为获得，2为激活
			_xdb_verify_unsafe_();
			return state;
		}

		@Override
		public long getGettime() { // 称号获得的时间
			_xdb_verify_unsafe_();
			return gettime;
		}

		@Override
		public long getActivetime() { // 称号激活的时间
			_xdb_verify_unsafe_();
			return activetime;
		}

		@Override
		public long getExpiretime() { // 称号到期时间
			_xdb_verify_unsafe_();
			return expiretime;
		}

		@Override
		public void setTitlekey(int _v_) { // 称号id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTitletype(int _v_) { // 称号所属类型，战力榜，竞技场榜，财富榜等
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setState(int _v_) { // 称号的状态，0为未获得，1为获得，2为激活
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGettime(long _v_) { // 称号获得的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setActivetime(long _v_) { // 称号激活的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExpiretime(long _v_) { // 称号到期时间
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
			return Title.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Title.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Title.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Title.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Title.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Title.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Title.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Title.this.hashCode();
		}

		@Override
		public String toString() {
			return Title.this.toString();
		}

	}

	public static final class Data implements xbean.Title {
		private int titlekey; // 称号id
		private int titletype; // 称号所属类型，战力榜，竞技场榜，财富榜等
		private int state; // 称号的状态，0为未获得，1为获得，2为激活
		private long gettime; // 称号获得的时间
		private long activetime; // 称号激活的时间
		private long expiretime; // 称号到期时间

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.Title _o1_) {
			if (_o1_ instanceof Title) assign((Title)_o1_);
			else if (_o1_ instanceof Title.Data) assign((Title.Data)_o1_);
			else if (_o1_ instanceof Title.Const) assign(((Title.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Title _o_) {
			titlekey = _o_.titlekey;
			titletype = _o_.titletype;
			state = _o_.state;
			gettime = _o_.gettime;
			activetime = _o_.activetime;
			expiretime = _o_.expiretime;
		}

		private void assign(Title.Data _o_) {
			titlekey = _o_.titlekey;
			titletype = _o_.titletype;
			state = _o_.state;
			gettime = _o_.gettime;
			activetime = _o_.activetime;
			expiretime = _o_.expiretime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)( 8192|  1));_os_.marshal(titlekey);
	_os_.marshal((short)( 8192|  2));_os_.marshal(titletype);
	_os_.marshal((short)( 8192|  3));_os_.marshal(state);
	_os_.marshal((short)(10240|  4));_os_.marshal(gettime);
	_os_.marshal((short)(10240|  5));_os_.marshal(activetime);
	_os_.marshal((short)(10240|  6));_os_.marshal(expiretime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):titlekey = _os_.unmarshal_int();
					break;
					case ( 6144|  1):titlekey = _os_.unmarshal_short();
					break;
					case ( 8192|  2):titletype = _os_.unmarshal_int();
					break;
					case ( 6144|  2):titletype = _os_.unmarshal_short();
					break;
					case ( 8192|  3):state = _os_.unmarshal_int();
					break;
					case ( 6144|  3):state = _os_.unmarshal_short();
					break;
					case (10240|  4):gettime = _os_.unmarshal_long();
					break;
					case ( 6144|  4):gettime = _os_.unmarshal_short();
					break;
					case ( 8192|  4):gettime = _os_.unmarshal_int();
					break;
					case (10240|  5):activetime = _os_.unmarshal_long();
					break;
					case ( 6144|  5):activetime = _os_.unmarshal_short();
					break;
					case ( 8192|  5):activetime = _os_.unmarshal_int();
					break;
					case (10240|  6):expiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  6):expiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  6):expiretime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Title copy() {
			return new Data(this);
		}

		@Override
		public xbean.Title toData() {
			return new Data(this);
		}

		public xbean.Title toBean() {
			return new Title(this, null, null);
		}

		@Override
		public xbean.Title toDataIf() {
			return this;
		}

		public xbean.Title toBeanIf() {
			return new Title(this, null, null);
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
		public int getTitlekey() { // 称号id
			return titlekey;
		}

		@Override
		public int getTitletype() { // 称号所属类型，战力榜，竞技场榜，财富榜等
			return titletype;
		}

		@Override
		public int getState() { // 称号的状态，0为未获得，1为获得，2为激活
			return state;
		}

		@Override
		public long getGettime() { // 称号获得的时间
			return gettime;
		}

		@Override
		public long getActivetime() { // 称号激活的时间
			return activetime;
		}

		@Override
		public long getExpiretime() { // 称号到期时间
			return expiretime;
		}

		@Override
		public void setTitlekey(int _v_) { // 称号id
			titlekey = _v_;
		}

		@Override
		public void setTitletype(int _v_) { // 称号所属类型，战力榜，竞技场榜，财富榜等
			titletype = _v_;
		}

		@Override
		public void setState(int _v_) { // 称号的状态，0为未获得，1为获得，2为激活
			state = _v_;
		}

		@Override
		public void setGettime(long _v_) { // 称号获得的时间
			gettime = _v_;
		}

		@Override
		public void setActivetime(long _v_) { // 称号激活的时间
			activetime = _v_;
		}

		@Override
		public void setExpiretime(long _v_) { // 称号到期时间
			expiretime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Title.Data)) return false;
			Title.Data _o_ = (Title.Data) _o1_;
			if (titlekey != _o_.titlekey) return false;
			if (titletype != _o_.titletype) return false;
			if (state != _o_.state) return false;
			if (gettime != _o_.gettime) return false;
			if (activetime != _o_.activetime) return false;
			if (expiretime != _o_.expiretime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += titlekey;
			_h_ += titletype;
			_h_ += state;
			_h_ += gettime;
			_h_ += activetime;
			_h_ += expiretime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(titlekey);
			_sb_.append(",");
			_sb_.append(titletype);
			_sb_.append(",");
			_sb_.append(state);
			_sb_.append(",");
			_sb_.append(gettime);
			_sb_.append(",");
			_sb_.append(activetime);
			_sb_.append(",");
			_sb_.append(expiretime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
