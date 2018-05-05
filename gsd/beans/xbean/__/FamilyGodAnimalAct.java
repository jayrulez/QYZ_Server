
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class FamilyGodAnimalAct extends xdb.XBean implements xbean.FamilyGodAnimalAct {
	private long lastlaunchtime; // 最近一次开启的时间
	private long starttime; // 活动开始时间
	private long endtime; // 活动结束时间
	private int weeklaunchnum; // 每周开启的次数

	@Override
	public void _reset_unsafe_() {
		lastlaunchtime = 0L;
		starttime = 0L;
		endtime = 0L;
		weeklaunchnum = 0;
	}

	FamilyGodAnimalAct(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public FamilyGodAnimalAct() {
		this(0, null, null);
	}

	public FamilyGodAnimalAct(FamilyGodAnimalAct _o_) {
		this(_o_, null, null);
	}

	FamilyGodAnimalAct(xbean.FamilyGodAnimalAct _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof FamilyGodAnimalAct) assign((FamilyGodAnimalAct)_o1_);
		else if (_o1_ instanceof FamilyGodAnimalAct.Data) assign((FamilyGodAnimalAct.Data)_o1_);
		else if (_o1_ instanceof FamilyGodAnimalAct.Const) assign(((FamilyGodAnimalAct.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(FamilyGodAnimalAct _o_) {
		_o_._xdb_verify_unsafe_();
		lastlaunchtime = _o_.lastlaunchtime;
		starttime = _o_.starttime;
		endtime = _o_.endtime;
		weeklaunchnum = _o_.weeklaunchnum;
	}

	private void assign(FamilyGodAnimalAct.Data _o_) {
		lastlaunchtime = _o_.lastlaunchtime;
		starttime = _o_.starttime;
		endtime = _o_.endtime;
		weeklaunchnum = _o_.weeklaunchnum;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(10240|  1));_os_.marshal(lastlaunchtime);
    _os_.marshal((short)(10240|  2));_os_.marshal(starttime);
    _os_.marshal((short)(10240|  4));_os_.marshal(endtime);
    _os_.marshal((short)( 8192|  3));_os_.marshal(weeklaunchnum);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):lastlaunchtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):lastlaunchtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):lastlaunchtime = _os_.unmarshal_int();
    				break;
    				case (10240|  2):starttime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):starttime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):starttime = _os_.unmarshal_int();
    				break;
    				case (10240|  4):endtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):endtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):endtime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  3):weeklaunchnum = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):weeklaunchnum = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.FamilyGodAnimalAct copy() {
		_xdb_verify_unsafe_();
		return new FamilyGodAnimalAct(this);
	}

	@Override
	public xbean.FamilyGodAnimalAct toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyGodAnimalAct toBean() {
		_xdb_verify_unsafe_();
		return new FamilyGodAnimalAct(this); // same as copy()
	}

	@Override
	public xbean.FamilyGodAnimalAct toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyGodAnimalAct toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getLastlaunchtime() { // 最近一次开启的时间
		_xdb_verify_unsafe_();
		return lastlaunchtime;
	}

	@Override
	public long getStarttime() { // 活动开始时间
		_xdb_verify_unsafe_();
		return starttime;
	}

	@Override
	public long getEndtime() { // 活动结束时间
		_xdb_verify_unsafe_();
		return endtime;
	}

	@Override
	public int getWeeklaunchnum() { // 每周开启的次数
		_xdb_verify_unsafe_();
		return weeklaunchnum;
	}

	@Override
	public void setLastlaunchtime(long _v_) { // 最近一次开启的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastlaunchtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastlaunchtime) {
					public void rollback() { lastlaunchtime = _xdb_saved; }
				};}});
		lastlaunchtime = _v_;
	}

	@Override
	public void setStarttime(long _v_) { // 活动开始时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "starttime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, starttime) {
					public void rollback() { starttime = _xdb_saved; }
				};}});
		starttime = _v_;
	}

	@Override
	public void setEndtime(long _v_) { // 活动结束时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "endtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, endtime) {
					public void rollback() { endtime = _xdb_saved; }
				};}});
		endtime = _v_;
	}

	@Override
	public void setWeeklaunchnum(int _v_) { // 每周开启的次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "weeklaunchnum") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, weeklaunchnum) {
					public void rollback() { weeklaunchnum = _xdb_saved; }
				};}});
		weeklaunchnum = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		FamilyGodAnimalAct _o_ = null;
		if ( _o1_ instanceof FamilyGodAnimalAct ) _o_ = (FamilyGodAnimalAct)_o1_;
		else if ( _o1_ instanceof FamilyGodAnimalAct.Const ) _o_ = ((FamilyGodAnimalAct.Const)_o1_).nThis();
		else return false;
		if (lastlaunchtime != _o_.lastlaunchtime) return false;
		if (starttime != _o_.starttime) return false;
		if (endtime != _o_.endtime) return false;
		if (weeklaunchnum != _o_.weeklaunchnum) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += lastlaunchtime;
		_h_ += starttime;
		_h_ += endtime;
		_h_ += weeklaunchnum;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lastlaunchtime);
		_sb_.append(",");
		_sb_.append(starttime);
		_sb_.append(",");
		_sb_.append(endtime);
		_sb_.append(",");
		_sb_.append(weeklaunchnum);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastlaunchtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("starttime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("endtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("weeklaunchnum"));
		return lb;
	}

	private class Const implements xbean.FamilyGodAnimalAct {
		FamilyGodAnimalAct nThis() {
			return FamilyGodAnimalAct.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.FamilyGodAnimalAct copy() {
			return FamilyGodAnimalAct.this.copy();
		}

		@Override
		public xbean.FamilyGodAnimalAct toData() {
			return FamilyGodAnimalAct.this.toData();
		}

		public xbean.FamilyGodAnimalAct toBean() {
			return FamilyGodAnimalAct.this.toBean();
		}

		@Override
		public xbean.FamilyGodAnimalAct toDataIf() {
			return FamilyGodAnimalAct.this.toDataIf();
		}

		public xbean.FamilyGodAnimalAct toBeanIf() {
			return FamilyGodAnimalAct.this.toBeanIf();
		}

		@Override
		public long getLastlaunchtime() { // 最近一次开启的时间
			_xdb_verify_unsafe_();
			return lastlaunchtime;
		}

		@Override
		public long getStarttime() { // 活动开始时间
			_xdb_verify_unsafe_();
			return starttime;
		}

		@Override
		public long getEndtime() { // 活动结束时间
			_xdb_verify_unsafe_();
			return endtime;
		}

		@Override
		public int getWeeklaunchnum() { // 每周开启的次数
			_xdb_verify_unsafe_();
			return weeklaunchnum;
		}

		@Override
		public void setLastlaunchtime(long _v_) { // 最近一次开启的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setStarttime(long _v_) { // 活动开始时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setEndtime(long _v_) { // 活动结束时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setWeeklaunchnum(int _v_) { // 每周开启的次数
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
			return FamilyGodAnimalAct.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return FamilyGodAnimalAct.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return FamilyGodAnimalAct.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return FamilyGodAnimalAct.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return FamilyGodAnimalAct.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return FamilyGodAnimalAct.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return FamilyGodAnimalAct.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return FamilyGodAnimalAct.this.hashCode();
		}

		@Override
		public String toString() {
			return FamilyGodAnimalAct.this.toString();
		}

	}

	public static final class Data implements xbean.FamilyGodAnimalAct {
		private long lastlaunchtime; // 最近一次开启的时间
		private long starttime; // 活动开始时间
		private long endtime; // 活动结束时间
		private int weeklaunchnum; // 每周开启的次数

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.FamilyGodAnimalAct _o1_) {
			if (_o1_ instanceof FamilyGodAnimalAct) assign((FamilyGodAnimalAct)_o1_);
			else if (_o1_ instanceof FamilyGodAnimalAct.Data) assign((FamilyGodAnimalAct.Data)_o1_);
			else if (_o1_ instanceof FamilyGodAnimalAct.Const) assign(((FamilyGodAnimalAct.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(FamilyGodAnimalAct _o_) {
			lastlaunchtime = _o_.lastlaunchtime;
			starttime = _o_.starttime;
			endtime = _o_.endtime;
			weeklaunchnum = _o_.weeklaunchnum;
		}

		private void assign(FamilyGodAnimalAct.Data _o_) {
			lastlaunchtime = _o_.lastlaunchtime;
			starttime = _o_.starttime;
			endtime = _o_.endtime;
			weeklaunchnum = _o_.weeklaunchnum;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(10240|  1));_os_.marshal(lastlaunchtime);
	_os_.marshal((short)(10240|  2));_os_.marshal(starttime);
	_os_.marshal((short)(10240|  4));_os_.marshal(endtime);
	_os_.marshal((short)( 8192|  3));_os_.marshal(weeklaunchnum);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):lastlaunchtime = _os_.unmarshal_long();
					break;
					case ( 6144|  1):lastlaunchtime = _os_.unmarshal_short();
					break;
					case ( 8192|  1):lastlaunchtime = _os_.unmarshal_int();
					break;
					case (10240|  2):starttime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):starttime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):starttime = _os_.unmarshal_int();
					break;
					case (10240|  4):endtime = _os_.unmarshal_long();
					break;
					case ( 6144|  4):endtime = _os_.unmarshal_short();
					break;
					case ( 8192|  4):endtime = _os_.unmarshal_int();
					break;
					case ( 8192|  3):weeklaunchnum = _os_.unmarshal_int();
					break;
					case ( 6144|  3):weeklaunchnum = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.FamilyGodAnimalAct copy() {
			return new Data(this);
		}

		@Override
		public xbean.FamilyGodAnimalAct toData() {
			return new Data(this);
		}

		public xbean.FamilyGodAnimalAct toBean() {
			return new FamilyGodAnimalAct(this, null, null);
		}

		@Override
		public xbean.FamilyGodAnimalAct toDataIf() {
			return this;
		}

		public xbean.FamilyGodAnimalAct toBeanIf() {
			return new FamilyGodAnimalAct(this, null, null);
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
		public long getLastlaunchtime() { // 最近一次开启的时间
			return lastlaunchtime;
		}

		@Override
		public long getStarttime() { // 活动开始时间
			return starttime;
		}

		@Override
		public long getEndtime() { // 活动结束时间
			return endtime;
		}

		@Override
		public int getWeeklaunchnum() { // 每周开启的次数
			return weeklaunchnum;
		}

		@Override
		public void setLastlaunchtime(long _v_) { // 最近一次开启的时间
			lastlaunchtime = _v_;
		}

		@Override
		public void setStarttime(long _v_) { // 活动开始时间
			starttime = _v_;
		}

		@Override
		public void setEndtime(long _v_) { // 活动结束时间
			endtime = _v_;
		}

		@Override
		public void setWeeklaunchnum(int _v_) { // 每周开启的次数
			weeklaunchnum = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof FamilyGodAnimalAct.Data)) return false;
			FamilyGodAnimalAct.Data _o_ = (FamilyGodAnimalAct.Data) _o1_;
			if (lastlaunchtime != _o_.lastlaunchtime) return false;
			if (starttime != _o_.starttime) return false;
			if (endtime != _o_.endtime) return false;
			if (weeklaunchnum != _o_.weeklaunchnum) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += lastlaunchtime;
			_h_ += starttime;
			_h_ += endtime;
			_h_ += weeklaunchnum;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(lastlaunchtime);
			_sb_.append(",");
			_sb_.append(starttime);
			_sb_.append(",");
			_sb_.append(endtime);
			_sb_.append(",");
			_sb_.append(weeklaunchnum);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
