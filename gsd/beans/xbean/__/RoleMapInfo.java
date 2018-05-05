
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleMapInfo extends xdb.XBean implements xbean.RoleMapInfo {
	private long mapid; // 
	private xbean.Vector3 position; // 
	private xbean.Vector3 orient; // 
	private int ridestatus; // 

	@Override
	public void _reset_unsafe_() {
		mapid = 0L;
		position._reset_unsafe_();
		orient._reset_unsafe_();
		ridestatus = 0;
	}

	RoleMapInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		position = new Vector3(0, this, "position");
		orient = new Vector3(0, this, "orient");
	}

	public RoleMapInfo() {
		this(0, null, null);
	}

	public RoleMapInfo(RoleMapInfo _o_) {
		this(_o_, null, null);
	}

	RoleMapInfo(xbean.RoleMapInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleMapInfo) assign((RoleMapInfo)_o1_);
		else if (_o1_ instanceof RoleMapInfo.Data) assign((RoleMapInfo.Data)_o1_);
		else if (_o1_ instanceof RoleMapInfo.Const) assign(((RoleMapInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleMapInfo _o_) {
		_o_._xdb_verify_unsafe_();
		mapid = _o_.mapid;
		position = new Vector3(_o_.position, this, "position");
		orient = new Vector3(_o_.orient, this, "orient");
		ridestatus = _o_.ridestatus;
	}

	private void assign(RoleMapInfo.Data _o_) {
		mapid = _o_.mapid;
		position = new Vector3(_o_.position, this, "position");
		orient = new Vector3(_o_.orient, this, "orient");
		ridestatus = _o_.ridestatus;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(10240|  6));_os_.marshal(mapid);
    _os_.marshal((short)(26624|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
position.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(26624|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
orient.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  7));_os_.marshal(ridestatus);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  6):mapid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  6):mapid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):mapid = _os_.unmarshal_int();
    				break;
    				case (26624|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
position.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case (26624|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
orient.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case ( 8192|  7):ridestatus = _os_.unmarshal_int();
    				break;
    				case ( 6144|  7):ridestatus = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleMapInfo copy() {
		_xdb_verify_unsafe_();
		return new RoleMapInfo(this);
	}

	@Override
	public xbean.RoleMapInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleMapInfo toBean() {
		_xdb_verify_unsafe_();
		return new RoleMapInfo(this); // same as copy()
	}

	@Override
	public xbean.RoleMapInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleMapInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getMapid() { // 
		_xdb_verify_unsafe_();
		return mapid;
	}

	@Override
	public xbean.Vector3 getPosition() { // 
		_xdb_verify_unsafe_();
		return position;
	}

	@Override
	public xbean.Vector3 getOrient() { // 
		_xdb_verify_unsafe_();
		return orient;
	}

	@Override
	public int getRidestatus() { // 
		_xdb_verify_unsafe_();
		return ridestatus;
	}

	@Override
	public void setMapid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "mapid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, mapid) {
					public void rollback() { mapid = _xdb_saved; }
				};}});
		mapid = _v_;
	}

	@Override
	public void setRidestatus(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "ridestatus") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, ridestatus) {
					public void rollback() { ridestatus = _xdb_saved; }
				};}});
		ridestatus = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleMapInfo _o_ = null;
		if ( _o1_ instanceof RoleMapInfo ) _o_ = (RoleMapInfo)_o1_;
		else if ( _o1_ instanceof RoleMapInfo.Const ) _o_ = ((RoleMapInfo.Const)_o1_).nThis();
		else return false;
		if (mapid != _o_.mapid) return false;
		if (!position.equals(_o_.position)) return false;
		if (!orient.equals(_o_.orient)) return false;
		if (ridestatus != _o_.ridestatus) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += mapid;
		_h_ += position.hashCode();
		_h_ += orient.hashCode();
		_h_ += ridestatus;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mapid);
		_sb_.append(",");
		_sb_.append(position);
		_sb_.append(",");
		_sb_.append(orient);
		_sb_.append(",");
		_sb_.append(ridestatus);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("mapid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("position"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("orient"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("ridestatus"));
		return lb;
	}

	private class Const implements xbean.RoleMapInfo {
		RoleMapInfo nThis() {
			return RoleMapInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleMapInfo copy() {
			return RoleMapInfo.this.copy();
		}

		@Override
		public xbean.RoleMapInfo toData() {
			return RoleMapInfo.this.toData();
		}

		public xbean.RoleMapInfo toBean() {
			return RoleMapInfo.this.toBean();
		}

		@Override
		public xbean.RoleMapInfo toDataIf() {
			return RoleMapInfo.this.toDataIf();
		}

		public xbean.RoleMapInfo toBeanIf() {
			return RoleMapInfo.this.toBeanIf();
		}

		@Override
		public long getMapid() { // 
			_xdb_verify_unsafe_();
			return mapid;
		}

		@Override
		public xbean.Vector3 getPosition() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(position);
		}

		@Override
		public xbean.Vector3 getOrient() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(orient);
		}

		@Override
		public int getRidestatus() { // 
			_xdb_verify_unsafe_();
			return ridestatus;
		}

		@Override
		public void setMapid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRidestatus(int _v_) { // 
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
			return RoleMapInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleMapInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleMapInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleMapInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleMapInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleMapInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleMapInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleMapInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleMapInfo.this.toString();
		}

	}

	public static final class Data implements xbean.RoleMapInfo {
		private long mapid; // 
		private xbean.Vector3 position; // 
		private xbean.Vector3 orient; // 
		private int ridestatus; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			position = new Vector3.Data();
			orient = new Vector3.Data();
		}

		Data(xbean.RoleMapInfo _o1_) {
			if (_o1_ instanceof RoleMapInfo) assign((RoleMapInfo)_o1_);
			else if (_o1_ instanceof RoleMapInfo.Data) assign((RoleMapInfo.Data)_o1_);
			else if (_o1_ instanceof RoleMapInfo.Const) assign(((RoleMapInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleMapInfo _o_) {
			mapid = _o_.mapid;
			position = new Vector3.Data(_o_.position);
			orient = new Vector3.Data(_o_.orient);
			ridestatus = _o_.ridestatus;
		}

		private void assign(RoleMapInfo.Data _o_) {
			mapid = _o_.mapid;
			position = new Vector3.Data(_o_.position);
			orient = new Vector3.Data(_o_.orient);
			ridestatus = _o_.ridestatus;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(10240|  6));_os_.marshal(mapid);
	_os_.marshal((short)(26624|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
position.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(26624|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
orient.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  7));_os_.marshal(ridestatus);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  6):mapid = _os_.unmarshal_long();
					break;
					case ( 6144|  6):mapid = _os_.unmarshal_short();
					break;
					case ( 8192|  6):mapid = _os_.unmarshal_int();
					break;
					case (26624|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
position.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case (26624|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
orient.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case ( 8192|  7):ridestatus = _os_.unmarshal_int();
					break;
					case ( 6144|  7):ridestatus = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleMapInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleMapInfo toData() {
			return new Data(this);
		}

		public xbean.RoleMapInfo toBean() {
			return new RoleMapInfo(this, null, null);
		}

		@Override
		public xbean.RoleMapInfo toDataIf() {
			return this;
		}

		public xbean.RoleMapInfo toBeanIf() {
			return new RoleMapInfo(this, null, null);
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
		public long getMapid() { // 
			return mapid;
		}

		@Override
		public xbean.Vector3 getPosition() { // 
			return position;
		}

		@Override
		public xbean.Vector3 getOrient() { // 
			return orient;
		}

		@Override
		public int getRidestatus() { // 
			return ridestatus;
		}

		@Override
		public void setMapid(long _v_) { // 
			mapid = _v_;
		}

		@Override
		public void setRidestatus(int _v_) { // 
			ridestatus = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleMapInfo.Data)) return false;
			RoleMapInfo.Data _o_ = (RoleMapInfo.Data) _o1_;
			if (mapid != _o_.mapid) return false;
			if (!position.equals(_o_.position)) return false;
			if (!orient.equals(_o_.orient)) return false;
			if (ridestatus != _o_.ridestatus) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += mapid;
			_h_ += position.hashCode();
			_h_ += orient.hashCode();
			_h_ += ridestatus;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(mapid);
			_sb_.append(",");
			_sb_.append(position);
			_sb_.append(",");
			_sb_.append(orient);
			_sb_.append(",");
			_sb_.append(ridestatus);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
