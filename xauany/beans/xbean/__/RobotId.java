
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RobotId extends xdb.XBean implements xbean.RobotId {
	private long minuserid; // 
	private long maxuserid; // 

	@Override
	public void _reset_unsafe_() {
		minuserid = 0L;
		maxuserid = 0L;
	}

	RobotId(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public RobotId() {
		this(0, null, null);
	}

	public RobotId(RobotId _o_) {
		this(_o_, null, null);
	}

	RobotId(xbean.RobotId _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RobotId) assign((RobotId)_o1_);
		else if (_o1_ instanceof RobotId.Data) assign((RobotId.Data)_o1_);
		else if (_o1_ instanceof RobotId.Const) assign(((RobotId.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RobotId _o_) {
		_o_._xdb_verify_unsafe_();
		minuserid = _o_.minuserid;
		maxuserid = _o_.maxuserid;
	}

	private void assign(RobotId.Data _o_) {
		minuserid = _o_.minuserid;
		maxuserid = _o_.maxuserid;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(10240|  0));_os_.marshal(minuserid);
    _os_.marshal((short)(10240|  1));_os_.marshal(maxuserid);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  0):minuserid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  0):minuserid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  0):minuserid = _os_.unmarshal_int();
    				break;
    				case (10240|  1):maxuserid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):maxuserid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):maxuserid = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RobotId copy() {
		_xdb_verify_unsafe_();
		return new RobotId(this);
	}

	@Override
	public xbean.RobotId toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RobotId toBean() {
		_xdb_verify_unsafe_();
		return new RobotId(this); // same as copy()
	}

	@Override
	public xbean.RobotId toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RobotId toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getMinuserid() { // 
		_xdb_verify_unsafe_();
		return minuserid;
	}

	@Override
	public long getMaxuserid() { // 
		_xdb_verify_unsafe_();
		return maxuserid;
	}

	@Override
	public void setMinuserid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "minuserid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, minuserid) {
					public void rollback() { minuserid = _xdb_saved; }
				};}});
		minuserid = _v_;
	}

	@Override
	public void setMaxuserid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "maxuserid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, maxuserid) {
					public void rollback() { maxuserid = _xdb_saved; }
				};}});
		maxuserid = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RobotId _o_ = null;
		if ( _o1_ instanceof RobotId ) _o_ = (RobotId)_o1_;
		else if ( _o1_ instanceof RobotId.Const ) _o_ = ((RobotId.Const)_o1_).nThis();
		else return false;
		if (minuserid != _o_.minuserid) return false;
		if (maxuserid != _o_.maxuserid) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += minuserid;
		_h_ += maxuserid;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(minuserid);
		_sb_.append(",");
		_sb_.append(maxuserid);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("minuserid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("maxuserid"));
		return lb;
	}

	private class Const implements xbean.RobotId {
		RobotId nThis() {
			return RobotId.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RobotId copy() {
			return RobotId.this.copy();
		}

		@Override
		public xbean.RobotId toData() {
			return RobotId.this.toData();
		}

		public xbean.RobotId toBean() {
			return RobotId.this.toBean();
		}

		@Override
		public xbean.RobotId toDataIf() {
			return RobotId.this.toDataIf();
		}

		public xbean.RobotId toBeanIf() {
			return RobotId.this.toBeanIf();
		}

		@Override
		public long getMinuserid() { // 
			_xdb_verify_unsafe_();
			return minuserid;
		}

		@Override
		public long getMaxuserid() { // 
			_xdb_verify_unsafe_();
			return maxuserid;
		}

		@Override
		public void setMinuserid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMaxuserid(long _v_) { // 
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
			return RobotId.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RobotId.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RobotId.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RobotId.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RobotId.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RobotId.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RobotId.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RobotId.this.hashCode();
		}

		@Override
		public String toString() {
			return RobotId.this.toString();
		}

	}

	public static final class Data implements xbean.RobotId {
		private long minuserid; // 
		private long maxuserid; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.RobotId _o1_) {
			if (_o1_ instanceof RobotId) assign((RobotId)_o1_);
			else if (_o1_ instanceof RobotId.Data) assign((RobotId.Data)_o1_);
			else if (_o1_ instanceof RobotId.Const) assign(((RobotId.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RobotId _o_) {
			minuserid = _o_.minuserid;
			maxuserid = _o_.maxuserid;
		}

		private void assign(RobotId.Data _o_) {
			minuserid = _o_.minuserid;
			maxuserid = _o_.maxuserid;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(10240|  0));_os_.marshal(minuserid);
	_os_.marshal((short)(10240|  1));_os_.marshal(maxuserid);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  0):minuserid = _os_.unmarshal_long();
					break;
					case ( 6144|  0):minuserid = _os_.unmarshal_short();
					break;
					case ( 8192|  0):minuserid = _os_.unmarshal_int();
					break;
					case (10240|  1):maxuserid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):maxuserid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):maxuserid = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RobotId copy() {
			return new Data(this);
		}

		@Override
		public xbean.RobotId toData() {
			return new Data(this);
		}

		public xbean.RobotId toBean() {
			return new RobotId(this, null, null);
		}

		@Override
		public xbean.RobotId toDataIf() {
			return this;
		}

		public xbean.RobotId toBeanIf() {
			return new RobotId(this, null, null);
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
		public long getMinuserid() { // 
			return minuserid;
		}

		@Override
		public long getMaxuserid() { // 
			return maxuserid;
		}

		@Override
		public void setMinuserid(long _v_) { // 
			minuserid = _v_;
		}

		@Override
		public void setMaxuserid(long _v_) { // 
			maxuserid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RobotId.Data)) return false;
			RobotId.Data _o_ = (RobotId.Data) _o1_;
			if (minuserid != _o_.minuserid) return false;
			if (maxuserid != _o_.maxuserid) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += minuserid;
			_h_ += maxuserid;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(minuserid);
			_sb_.append(",");
			_sb_.append(maxuserid);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
