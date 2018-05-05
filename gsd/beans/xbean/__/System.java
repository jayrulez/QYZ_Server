
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class System extends xdb.XBean implements xbean.System {
	private boolean initrobot; // 
	private java.util.LinkedList<Long> outrankrobots; // 
	private long gsdfirststarttime; // 
	private int rolenumsreach20; // 
	private int maxsystemfamnum; // 

	@Override
	public void _reset_unsafe_() {
		initrobot = false;
		outrankrobots.clear();
		gsdfirststarttime = 0L;
		rolenumsreach20 = 0;
		maxsystemfamnum = 200;
	}

	System(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		outrankrobots = new java.util.LinkedList<Long>();
		maxsystemfamnum = 200;
	}

	public System() {
		this(0, null, null);
	}

	public System(System _o_) {
		this(_o_, null, null);
	}

	System(xbean.System _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof System) assign((System)_o1_);
		else if (_o1_ instanceof System.Data) assign((System.Data)_o1_);
		else if (_o1_ instanceof System.Const) assign(((System.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(System _o_) {
		_o_._xdb_verify_unsafe_();
		initrobot = _o_.initrobot;
		outrankrobots = new java.util.LinkedList<Long>();
		outrankrobots.addAll(_o_.outrankrobots);
		gsdfirststarttime = _o_.gsdfirststarttime;
		rolenumsreach20 = _o_.rolenumsreach20;
		maxsystemfamnum = _o_.maxsystemfamnum;
	}

	private void assign(System.Data _o_) {
		initrobot = _o_.initrobot;
		outrankrobots = new java.util.LinkedList<Long>();
		outrankrobots.addAll(_o_.outrankrobots);
		gsdfirststarttime = _o_.gsdfirststarttime;
		rolenumsreach20 = _o_.rolenumsreach20;
		maxsystemfamnum = _o_.maxsystemfamnum;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)5);
    _os_.marshal((short)( 2048|  1));_os_.marshal(initrobot);
    _os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(outrankrobots.size());
for (Long _v_ : outrankrobots) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  3));_os_.marshal(gsdfirststarttime);
    _os_.marshal((short)( 8192|  4));_os_.marshal(rolenumsreach20);
    _os_.marshal((short)( 8192|  5));_os_.marshal(maxsystemfamnum);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 2048|  1):initrobot = _os_.unmarshal_boolean();
    				break;
    				case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	outrankrobots.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (10240|  3):gsdfirststarttime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):gsdfirststarttime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):gsdfirststarttime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  4):rolenumsreach20 = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):rolenumsreach20 = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):maxsystemfamnum = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):maxsystemfamnum = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.System copy() {
		_xdb_verify_unsafe_();
		return new System(this);
	}

	@Override
	public xbean.System toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.System toBean() {
		_xdb_verify_unsafe_();
		return new System(this); // same as copy()
	}

	@Override
	public xbean.System toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.System toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public boolean getInitrobot() { // 
		_xdb_verify_unsafe_();
		return initrobot;
	}

	@Override
	public java.util.List<Long> getOutrankrobots() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "outrankrobots"), outrankrobots);
	}

	public java.util.List<Long> getOutrankrobotsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Long> outrankrobots;
		System _o_ = this;
		outrankrobots = new java.util.LinkedList<Long>();
		outrankrobots.addAll(_o_.outrankrobots);
		return outrankrobots;
	}

	@Override
	public long getGsdfirststarttime() { // 
		_xdb_verify_unsafe_();
		return gsdfirststarttime;
	}

	@Override
	public int getRolenumsreach20() { // 
		_xdb_verify_unsafe_();
		return rolenumsreach20;
	}

	@Override
	public int getMaxsystemfamnum() { // 
		_xdb_verify_unsafe_();
		return maxsystemfamnum;
	}

	@Override
	public void setInitrobot(boolean _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "initrobot") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, initrobot) {
					public void rollback() { initrobot = _xdb_saved; }
				};}});
		initrobot = _v_;
	}

	@Override
	public void setGsdfirststarttime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "gsdfirststarttime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, gsdfirststarttime) {
					public void rollback() { gsdfirststarttime = _xdb_saved; }
				};}});
		gsdfirststarttime = _v_;
	}

	@Override
	public void setRolenumsreach20(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "rolenumsreach20") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, rolenumsreach20) {
					public void rollback() { rolenumsreach20 = _xdb_saved; }
				};}});
		rolenumsreach20 = _v_;
	}

	@Override
	public void setMaxsystemfamnum(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "maxsystemfamnum") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, maxsystemfamnum) {
					public void rollback() { maxsystemfamnum = _xdb_saved; }
				};}});
		maxsystemfamnum = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		System _o_ = null;
		if ( _o1_ instanceof System ) _o_ = (System)_o1_;
		else if ( _o1_ instanceof System.Const ) _o_ = ((System.Const)_o1_).nThis();
		else return false;
		if (initrobot != _o_.initrobot) return false;
		if (!outrankrobots.equals(_o_.outrankrobots)) return false;
		if (gsdfirststarttime != _o_.gsdfirststarttime) return false;
		if (rolenumsreach20 != _o_.rolenumsreach20) return false;
		if (maxsystemfamnum != _o_.maxsystemfamnum) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += initrobot ? 1231 : 1237;
		_h_ += outrankrobots.hashCode();
		_h_ += gsdfirststarttime;
		_h_ += rolenumsreach20;
		_h_ += maxsystemfamnum;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(initrobot);
		_sb_.append(",");
		_sb_.append(outrankrobots);
		_sb_.append(",");
		_sb_.append(gsdfirststarttime);
		_sb_.append(",");
		_sb_.append(rolenumsreach20);
		_sb_.append(",");
		_sb_.append(maxsystemfamnum);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("initrobot"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("outrankrobots"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("gsdfirststarttime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("rolenumsreach20"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("maxsystemfamnum"));
		return lb;
	}

	private class Const implements xbean.System {
		System nThis() {
			return System.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.System copy() {
			return System.this.copy();
		}

		@Override
		public xbean.System toData() {
			return System.this.toData();
		}

		public xbean.System toBean() {
			return System.this.toBean();
		}

		@Override
		public xbean.System toDataIf() {
			return System.this.toDataIf();
		}

		public xbean.System toBeanIf() {
			return System.this.toBeanIf();
		}

		@Override
		public boolean getInitrobot() { // 
			_xdb_verify_unsafe_();
			return initrobot;
		}

		@Override
		public java.util.List<Long> getOutrankrobots() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(outrankrobots);
		}

		public java.util.List<Long> getOutrankrobotsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Long> outrankrobots;
			System _o_ = System.this;
		outrankrobots = new java.util.LinkedList<Long>();
		outrankrobots.addAll(_o_.outrankrobots);
			return outrankrobots;
		}

		@Override
		public long getGsdfirststarttime() { // 
			_xdb_verify_unsafe_();
			return gsdfirststarttime;
		}

		@Override
		public int getRolenumsreach20() { // 
			_xdb_verify_unsafe_();
			return rolenumsreach20;
		}

		@Override
		public int getMaxsystemfamnum() { // 
			_xdb_verify_unsafe_();
			return maxsystemfamnum;
		}

		@Override
		public void setInitrobot(boolean _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGsdfirststarttime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRolenumsreach20(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMaxsystemfamnum(int _v_) { // 
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
			return System.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return System.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return System.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return System.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return System.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return System.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return System.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return System.this.hashCode();
		}

		@Override
		public String toString() {
			return System.this.toString();
		}

	}

	public static final class Data implements xbean.System {
		private boolean initrobot; // 
		private java.util.LinkedList<Long> outrankrobots; // 
		private long gsdfirststarttime; // 
		private int rolenumsreach20; // 
		private int maxsystemfamnum; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			outrankrobots = new java.util.LinkedList<Long>();
			maxsystemfamnum = 200;
		}

		Data(xbean.System _o1_) {
			if (_o1_ instanceof System) assign((System)_o1_);
			else if (_o1_ instanceof System.Data) assign((System.Data)_o1_);
			else if (_o1_ instanceof System.Const) assign(((System.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(System _o_) {
			initrobot = _o_.initrobot;
			outrankrobots = new java.util.LinkedList<Long>();
			outrankrobots.addAll(_o_.outrankrobots);
			gsdfirststarttime = _o_.gsdfirststarttime;
			rolenumsreach20 = _o_.rolenumsreach20;
			maxsystemfamnum = _o_.maxsystemfamnum;
		}

		private void assign(System.Data _o_) {
			initrobot = _o_.initrobot;
			outrankrobots = new java.util.LinkedList<Long>();
			outrankrobots.addAll(_o_.outrankrobots);
			gsdfirststarttime = _o_.gsdfirststarttime;
			rolenumsreach20 = _o_.rolenumsreach20;
			maxsystemfamnum = _o_.maxsystemfamnum;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)5);
	_os_.marshal((short)( 2048|  1));_os_.marshal(initrobot);
	_os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(outrankrobots.size());
for (Long _v_ : outrankrobots) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  3));_os_.marshal(gsdfirststarttime);
	_os_.marshal((short)( 8192|  4));_os_.marshal(rolenumsreach20);
	_os_.marshal((short)( 8192|  5));_os_.marshal(maxsystemfamnum);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 2048|  1):initrobot = _os_.unmarshal_boolean();
					break;
					case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	outrankrobots.add(_v_);
}
_os_ = _temp_;}
					break;
					case (10240|  3):gsdfirststarttime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):gsdfirststarttime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):gsdfirststarttime = _os_.unmarshal_int();
					break;
					case ( 8192|  4):rolenumsreach20 = _os_.unmarshal_int();
					break;
					case ( 6144|  4):rolenumsreach20 = _os_.unmarshal_short();
					break;
					case ( 8192|  5):maxsystemfamnum = _os_.unmarshal_int();
					break;
					case ( 6144|  5):maxsystemfamnum = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.System copy() {
			return new Data(this);
		}

		@Override
		public xbean.System toData() {
			return new Data(this);
		}

		public xbean.System toBean() {
			return new System(this, null, null);
		}

		@Override
		public xbean.System toDataIf() {
			return this;
		}

		public xbean.System toBeanIf() {
			return new System(this, null, null);
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
		public boolean getInitrobot() { // 
			return initrobot;
		}

		@Override
		public java.util.List<Long> getOutrankrobots() { // 
			return outrankrobots;
		}

		@Override
		public java.util.List<Long> getOutrankrobotsAsData() { // 
			return outrankrobots;
		}

		@Override
		public long getGsdfirststarttime() { // 
			return gsdfirststarttime;
		}

		@Override
		public int getRolenumsreach20() { // 
			return rolenumsreach20;
		}

		@Override
		public int getMaxsystemfamnum() { // 
			return maxsystemfamnum;
		}

		@Override
		public void setInitrobot(boolean _v_) { // 
			initrobot = _v_;
		}

		@Override
		public void setGsdfirststarttime(long _v_) { // 
			gsdfirststarttime = _v_;
		}

		@Override
		public void setRolenumsreach20(int _v_) { // 
			rolenumsreach20 = _v_;
		}

		@Override
		public void setMaxsystemfamnum(int _v_) { // 
			maxsystemfamnum = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof System.Data)) return false;
			System.Data _o_ = (System.Data) _o1_;
			if (initrobot != _o_.initrobot) return false;
			if (!outrankrobots.equals(_o_.outrankrobots)) return false;
			if (gsdfirststarttime != _o_.gsdfirststarttime) return false;
			if (rolenumsreach20 != _o_.rolenumsreach20) return false;
			if (maxsystemfamnum != _o_.maxsystemfamnum) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += initrobot ? 1231 : 1237;
			_h_ += outrankrobots.hashCode();
			_h_ += gsdfirststarttime;
			_h_ += rolenumsreach20;
			_h_ += maxsystemfamnum;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(initrobot);
			_sb_.append(",");
			_sb_.append(outrankrobots);
			_sb_.append(",");
			_sb_.append(gsdfirststarttime);
			_sb_.append(",");
			_sb_.append(rolenumsreach20);
			_sb_.append(",");
			_sb_.append(maxsystemfamnum);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
