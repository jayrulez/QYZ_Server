
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class TimeRange extends xdb.XBean implements xbean.TimeRange {
	private long opentime; // 
	private long closetime; // 

	@Override
	public void _reset_unsafe_() {
		opentime = 0L;
		closetime = 0L;
	}

	TimeRange(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public TimeRange() {
		this(0, null, null);
	}

	public TimeRange(TimeRange _o_) {
		this(_o_, null, null);
	}

	TimeRange(xbean.TimeRange _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof TimeRange) assign((TimeRange)_o1_);
		else if (_o1_ instanceof TimeRange.Data) assign((TimeRange.Data)_o1_);
		else if (_o1_ instanceof TimeRange.Const) assign(((TimeRange.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(TimeRange _o_) {
		_o_._xdb_verify_unsafe_();
		opentime = _o_.opentime;
		closetime = _o_.closetime;
	}

	private void assign(TimeRange.Data _o_) {
		opentime = _o_.opentime;
		closetime = _o_.closetime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(10240|  2));_os_.marshal(opentime);
    _os_.marshal((short)(10240|  3));_os_.marshal(closetime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  2):opentime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):opentime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):opentime = _os_.unmarshal_int();
    				break;
    				case (10240|  3):closetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):closetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):closetime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.TimeRange copy() {
		_xdb_verify_unsafe_();
		return new TimeRange(this);
	}

	@Override
	public xbean.TimeRange toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TimeRange toBean() {
		_xdb_verify_unsafe_();
		return new TimeRange(this); // same as copy()
	}

	@Override
	public xbean.TimeRange toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TimeRange toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getOpentime() { // 
		_xdb_verify_unsafe_();
		return opentime;
	}

	@Override
	public long getClosetime() { // 
		_xdb_verify_unsafe_();
		return closetime;
	}

	@Override
	public void setOpentime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "opentime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, opentime) {
					public void rollback() { opentime = _xdb_saved; }
				};}});
		opentime = _v_;
	}

	@Override
	public void setClosetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "closetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, closetime) {
					public void rollback() { closetime = _xdb_saved; }
				};}});
		closetime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		TimeRange _o_ = null;
		if ( _o1_ instanceof TimeRange ) _o_ = (TimeRange)_o1_;
		else if ( _o1_ instanceof TimeRange.Const ) _o_ = ((TimeRange.Const)_o1_).nThis();
		else return false;
		if (opentime != _o_.opentime) return false;
		if (closetime != _o_.closetime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += opentime;
		_h_ += closetime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(opentime);
		_sb_.append(",");
		_sb_.append(closetime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("opentime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("closetime"));
		return lb;
	}

	private class Const implements xbean.TimeRange {
		TimeRange nThis() {
			return TimeRange.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.TimeRange copy() {
			return TimeRange.this.copy();
		}

		@Override
		public xbean.TimeRange toData() {
			return TimeRange.this.toData();
		}

		public xbean.TimeRange toBean() {
			return TimeRange.this.toBean();
		}

		@Override
		public xbean.TimeRange toDataIf() {
			return TimeRange.this.toDataIf();
		}

		public xbean.TimeRange toBeanIf() {
			return TimeRange.this.toBeanIf();
		}

		@Override
		public long getOpentime() { // 
			_xdb_verify_unsafe_();
			return opentime;
		}

		@Override
		public long getClosetime() { // 
			_xdb_verify_unsafe_();
			return closetime;
		}

		@Override
		public void setOpentime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setClosetime(long _v_) { // 
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
			return TimeRange.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return TimeRange.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return TimeRange.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return TimeRange.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return TimeRange.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return TimeRange.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return TimeRange.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return TimeRange.this.hashCode();
		}

		@Override
		public String toString() {
			return TimeRange.this.toString();
		}

	}

	public static final class Data implements xbean.TimeRange {
		private long opentime; // 
		private long closetime; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.TimeRange _o1_) {
			if (_o1_ instanceof TimeRange) assign((TimeRange)_o1_);
			else if (_o1_ instanceof TimeRange.Data) assign((TimeRange.Data)_o1_);
			else if (_o1_ instanceof TimeRange.Const) assign(((TimeRange.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(TimeRange _o_) {
			opentime = _o_.opentime;
			closetime = _o_.closetime;
		}

		private void assign(TimeRange.Data _o_) {
			opentime = _o_.opentime;
			closetime = _o_.closetime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(10240|  2));_os_.marshal(opentime);
	_os_.marshal((short)(10240|  3));_os_.marshal(closetime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  2):opentime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):opentime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):opentime = _os_.unmarshal_int();
					break;
					case (10240|  3):closetime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):closetime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):closetime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.TimeRange copy() {
			return new Data(this);
		}

		@Override
		public xbean.TimeRange toData() {
			return new Data(this);
		}

		public xbean.TimeRange toBean() {
			return new TimeRange(this, null, null);
		}

		@Override
		public xbean.TimeRange toDataIf() {
			return this;
		}

		public xbean.TimeRange toBeanIf() {
			return new TimeRange(this, null, null);
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
		public long getOpentime() { // 
			return opentime;
		}

		@Override
		public long getClosetime() { // 
			return closetime;
		}

		@Override
		public void setOpentime(long _v_) { // 
			opentime = _v_;
		}

		@Override
		public void setClosetime(long _v_) { // 
			closetime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof TimeRange.Data)) return false;
			TimeRange.Data _o_ = (TimeRange.Data) _o1_;
			if (opentime != _o_.opentime) return false;
			if (closetime != _o_.closetime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += opentime;
			_h_ += closetime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(opentime);
			_sb_.append(",");
			_sb_.append(closetime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
