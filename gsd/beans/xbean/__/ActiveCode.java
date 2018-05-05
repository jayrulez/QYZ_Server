
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ActiveCode extends xdb.XBean implements xbean.ActiveCode {
	private long lastusetime; // 
	private int todayusecount; // 
	private int totalusecount; // 

	@Override
	public void _reset_unsafe_() {
		lastusetime = 0L;
		todayusecount = 0;
		totalusecount = 0;
	}

	ActiveCode(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public ActiveCode() {
		this(0, null, null);
	}

	public ActiveCode(ActiveCode _o_) {
		this(_o_, null, null);
	}

	ActiveCode(xbean.ActiveCode _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ActiveCode) assign((ActiveCode)_o1_);
		else if (_o1_ instanceof ActiveCode.Data) assign((ActiveCode.Data)_o1_);
		else if (_o1_ instanceof ActiveCode.Const) assign(((ActiveCode.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ActiveCode _o_) {
		_o_._xdb_verify_unsafe_();
		lastusetime = _o_.lastusetime;
		todayusecount = _o_.todayusecount;
		totalusecount = _o_.totalusecount;
	}

	private void assign(ActiveCode.Data _o_) {
		lastusetime = _o_.lastusetime;
		todayusecount = _o_.todayusecount;
		totalusecount = _o_.totalusecount;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)(10240|  1));_os_.marshal(lastusetime);
    _os_.marshal((short)( 8192|  2));_os_.marshal(todayusecount);
    _os_.marshal((short)( 8192|  3));_os_.marshal(totalusecount);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):lastusetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):lastusetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):lastusetime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):todayusecount = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):todayusecount = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):totalusecount = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):totalusecount = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ActiveCode copy() {
		_xdb_verify_unsafe_();
		return new ActiveCode(this);
	}

	@Override
	public xbean.ActiveCode toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ActiveCode toBean() {
		_xdb_verify_unsafe_();
		return new ActiveCode(this); // same as copy()
	}

	@Override
	public xbean.ActiveCode toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ActiveCode toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getLastusetime() { // 
		_xdb_verify_unsafe_();
		return lastusetime;
	}

	@Override
	public int getTodayusecount() { // 
		_xdb_verify_unsafe_();
		return todayusecount;
	}

	@Override
	public int getTotalusecount() { // 
		_xdb_verify_unsafe_();
		return totalusecount;
	}

	@Override
	public void setLastusetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastusetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastusetime) {
					public void rollback() { lastusetime = _xdb_saved; }
				};}});
		lastusetime = _v_;
	}

	@Override
	public void setTodayusecount(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "todayusecount") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, todayusecount) {
					public void rollback() { todayusecount = _xdb_saved; }
				};}});
		todayusecount = _v_;
	}

	@Override
	public void setTotalusecount(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalusecount") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, totalusecount) {
					public void rollback() { totalusecount = _xdb_saved; }
				};}});
		totalusecount = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ActiveCode _o_ = null;
		if ( _o1_ instanceof ActiveCode ) _o_ = (ActiveCode)_o1_;
		else if ( _o1_ instanceof ActiveCode.Const ) _o_ = ((ActiveCode.Const)_o1_).nThis();
		else return false;
		if (lastusetime != _o_.lastusetime) return false;
		if (todayusecount != _o_.todayusecount) return false;
		if (totalusecount != _o_.totalusecount) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += lastusetime;
		_h_ += todayusecount;
		_h_ += totalusecount;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lastusetime);
		_sb_.append(",");
		_sb_.append(todayusecount);
		_sb_.append(",");
		_sb_.append(totalusecount);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastusetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("todayusecount"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalusecount"));
		return lb;
	}

	private class Const implements xbean.ActiveCode {
		ActiveCode nThis() {
			return ActiveCode.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ActiveCode copy() {
			return ActiveCode.this.copy();
		}

		@Override
		public xbean.ActiveCode toData() {
			return ActiveCode.this.toData();
		}

		public xbean.ActiveCode toBean() {
			return ActiveCode.this.toBean();
		}

		@Override
		public xbean.ActiveCode toDataIf() {
			return ActiveCode.this.toDataIf();
		}

		public xbean.ActiveCode toBeanIf() {
			return ActiveCode.this.toBeanIf();
		}

		@Override
		public long getLastusetime() { // 
			_xdb_verify_unsafe_();
			return lastusetime;
		}

		@Override
		public int getTodayusecount() { // 
			_xdb_verify_unsafe_();
			return todayusecount;
		}

		@Override
		public int getTotalusecount() { // 
			_xdb_verify_unsafe_();
			return totalusecount;
		}

		@Override
		public void setLastusetime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTodayusecount(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalusecount(int _v_) { // 
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
			return ActiveCode.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ActiveCode.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ActiveCode.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ActiveCode.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ActiveCode.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ActiveCode.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ActiveCode.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ActiveCode.this.hashCode();
		}

		@Override
		public String toString() {
			return ActiveCode.this.toString();
		}

	}

	public static final class Data implements xbean.ActiveCode {
		private long lastusetime; // 
		private int todayusecount; // 
		private int totalusecount; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.ActiveCode _o1_) {
			if (_o1_ instanceof ActiveCode) assign((ActiveCode)_o1_);
			else if (_o1_ instanceof ActiveCode.Data) assign((ActiveCode.Data)_o1_);
			else if (_o1_ instanceof ActiveCode.Const) assign(((ActiveCode.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ActiveCode _o_) {
			lastusetime = _o_.lastusetime;
			todayusecount = _o_.todayusecount;
			totalusecount = _o_.totalusecount;
		}

		private void assign(ActiveCode.Data _o_) {
			lastusetime = _o_.lastusetime;
			todayusecount = _o_.todayusecount;
			totalusecount = _o_.totalusecount;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)(10240|  1));_os_.marshal(lastusetime);
	_os_.marshal((short)( 8192|  2));_os_.marshal(todayusecount);
	_os_.marshal((short)( 8192|  3));_os_.marshal(totalusecount);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):lastusetime = _os_.unmarshal_long();
					break;
					case ( 6144|  1):lastusetime = _os_.unmarshal_short();
					break;
					case ( 8192|  1):lastusetime = _os_.unmarshal_int();
					break;
					case ( 8192|  2):todayusecount = _os_.unmarshal_int();
					break;
					case ( 6144|  2):todayusecount = _os_.unmarshal_short();
					break;
					case ( 8192|  3):totalusecount = _os_.unmarshal_int();
					break;
					case ( 6144|  3):totalusecount = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ActiveCode copy() {
			return new Data(this);
		}

		@Override
		public xbean.ActiveCode toData() {
			return new Data(this);
		}

		public xbean.ActiveCode toBean() {
			return new ActiveCode(this, null, null);
		}

		@Override
		public xbean.ActiveCode toDataIf() {
			return this;
		}

		public xbean.ActiveCode toBeanIf() {
			return new ActiveCode(this, null, null);
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
		public long getLastusetime() { // 
			return lastusetime;
		}

		@Override
		public int getTodayusecount() { // 
			return todayusecount;
		}

		@Override
		public int getTotalusecount() { // 
			return totalusecount;
		}

		@Override
		public void setLastusetime(long _v_) { // 
			lastusetime = _v_;
		}

		@Override
		public void setTodayusecount(int _v_) { // 
			todayusecount = _v_;
		}

		@Override
		public void setTotalusecount(int _v_) { // 
			totalusecount = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ActiveCode.Data)) return false;
			ActiveCode.Data _o_ = (ActiveCode.Data) _o1_;
			if (lastusetime != _o_.lastusetime) return false;
			if (todayusecount != _o_.todayusecount) return false;
			if (totalusecount != _o_.totalusecount) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += lastusetime;
			_h_ += todayusecount;
			_h_ += totalusecount;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(lastusetime);
			_sb_.append(",");
			_sb_.append(todayusecount);
			_sb_.append(",");
			_sb_.append(totalusecount);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
