
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ClimbTowerInfo extends xdb.XBean implements xbean.ClimbTowerInfo {
	private int maxfloorid; // 
	private int costtime; // 秒数

	@Override
	public void _reset_unsafe_() {
		maxfloorid = 0;
		costtime = 0;
	}

	ClimbTowerInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public ClimbTowerInfo() {
		this(0, null, null);
	}

	public ClimbTowerInfo(ClimbTowerInfo _o_) {
		this(_o_, null, null);
	}

	ClimbTowerInfo(xbean.ClimbTowerInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ClimbTowerInfo) assign((ClimbTowerInfo)_o1_);
		else if (_o1_ instanceof ClimbTowerInfo.Data) assign((ClimbTowerInfo.Data)_o1_);
		else if (_o1_ instanceof ClimbTowerInfo.Const) assign(((ClimbTowerInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ClimbTowerInfo _o_) {
		_o_._xdb_verify_unsafe_();
		maxfloorid = _o_.maxfloorid;
		costtime = _o_.costtime;
	}

	private void assign(ClimbTowerInfo.Data _o_) {
		maxfloorid = _o_.maxfloorid;
		costtime = _o_.costtime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(maxfloorid);
    _os_.marshal((short)( 8192|  2));_os_.marshal(costtime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):maxfloorid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):maxfloorid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):costtime = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):costtime = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ClimbTowerInfo copy() {
		_xdb_verify_unsafe_();
		return new ClimbTowerInfo(this);
	}

	@Override
	public xbean.ClimbTowerInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ClimbTowerInfo toBean() {
		_xdb_verify_unsafe_();
		return new ClimbTowerInfo(this); // same as copy()
	}

	@Override
	public xbean.ClimbTowerInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ClimbTowerInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getMaxfloorid() { // 
		_xdb_verify_unsafe_();
		return maxfloorid;
	}

	@Override
	public int getCosttime() { // 秒数
		_xdb_verify_unsafe_();
		return costtime;
	}

	@Override
	public void setMaxfloorid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "maxfloorid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, maxfloorid) {
					public void rollback() { maxfloorid = _xdb_saved; }
				};}});
		maxfloorid = _v_;
	}

	@Override
	public void setCosttime(int _v_) { // 秒数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "costtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, costtime) {
					public void rollback() { costtime = _xdb_saved; }
				};}});
		costtime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ClimbTowerInfo _o_ = null;
		if ( _o1_ instanceof ClimbTowerInfo ) _o_ = (ClimbTowerInfo)_o1_;
		else if ( _o1_ instanceof ClimbTowerInfo.Const ) _o_ = ((ClimbTowerInfo.Const)_o1_).nThis();
		else return false;
		if (maxfloorid != _o_.maxfloorid) return false;
		if (costtime != _o_.costtime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += maxfloorid;
		_h_ += costtime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(maxfloorid);
		_sb_.append(",");
		_sb_.append(costtime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("maxfloorid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("costtime"));
		return lb;
	}

	private class Const implements xbean.ClimbTowerInfo {
		ClimbTowerInfo nThis() {
			return ClimbTowerInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ClimbTowerInfo copy() {
			return ClimbTowerInfo.this.copy();
		}

		@Override
		public xbean.ClimbTowerInfo toData() {
			return ClimbTowerInfo.this.toData();
		}

		public xbean.ClimbTowerInfo toBean() {
			return ClimbTowerInfo.this.toBean();
		}

		@Override
		public xbean.ClimbTowerInfo toDataIf() {
			return ClimbTowerInfo.this.toDataIf();
		}

		public xbean.ClimbTowerInfo toBeanIf() {
			return ClimbTowerInfo.this.toBeanIf();
		}

		@Override
		public int getMaxfloorid() { // 
			_xdb_verify_unsafe_();
			return maxfloorid;
		}

		@Override
		public int getCosttime() { // 秒数
			_xdb_verify_unsafe_();
			return costtime;
		}

		@Override
		public void setMaxfloorid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCosttime(int _v_) { // 秒数
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
			return ClimbTowerInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ClimbTowerInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ClimbTowerInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ClimbTowerInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ClimbTowerInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ClimbTowerInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ClimbTowerInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ClimbTowerInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return ClimbTowerInfo.this.toString();
		}

	}

	public static final class Data implements xbean.ClimbTowerInfo {
		private int maxfloorid; // 
		private int costtime; // 秒数

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.ClimbTowerInfo _o1_) {
			if (_o1_ instanceof ClimbTowerInfo) assign((ClimbTowerInfo)_o1_);
			else if (_o1_ instanceof ClimbTowerInfo.Data) assign((ClimbTowerInfo.Data)_o1_);
			else if (_o1_ instanceof ClimbTowerInfo.Const) assign(((ClimbTowerInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ClimbTowerInfo _o_) {
			maxfloorid = _o_.maxfloorid;
			costtime = _o_.costtime;
		}

		private void assign(ClimbTowerInfo.Data _o_) {
			maxfloorid = _o_.maxfloorid;
			costtime = _o_.costtime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(maxfloorid);
	_os_.marshal((short)( 8192|  2));_os_.marshal(costtime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):maxfloorid = _os_.unmarshal_int();
					break;
					case ( 6144|  1):maxfloorid = _os_.unmarshal_short();
					break;
					case ( 8192|  2):costtime = _os_.unmarshal_int();
					break;
					case ( 6144|  2):costtime = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ClimbTowerInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.ClimbTowerInfo toData() {
			return new Data(this);
		}

		public xbean.ClimbTowerInfo toBean() {
			return new ClimbTowerInfo(this, null, null);
		}

		@Override
		public xbean.ClimbTowerInfo toDataIf() {
			return this;
		}

		public xbean.ClimbTowerInfo toBeanIf() {
			return new ClimbTowerInfo(this, null, null);
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
		public int getMaxfloorid() { // 
			return maxfloorid;
		}

		@Override
		public int getCosttime() { // 秒数
			return costtime;
		}

		@Override
		public void setMaxfloorid(int _v_) { // 
			maxfloorid = _v_;
		}

		@Override
		public void setCosttime(int _v_) { // 秒数
			costtime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ClimbTowerInfo.Data)) return false;
			ClimbTowerInfo.Data _o_ = (ClimbTowerInfo.Data) _o1_;
			if (maxfloorid != _o_.maxfloorid) return false;
			if (costtime != _o_.costtime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += maxfloorid;
			_h_ += costtime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(maxfloorid);
			_sb_.append(",");
			_sb_.append(costtime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
