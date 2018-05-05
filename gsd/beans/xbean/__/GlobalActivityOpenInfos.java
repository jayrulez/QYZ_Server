
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class GlobalActivityOpenInfos extends xdb.XBean implements xbean.GlobalActivityOpenInfos {
	private java.util.LinkedList<xbean.TimeRange> infos; // 
	private boolean enable; // 

	@Override
	public void _reset_unsafe_() {
		infos.clear();
		enable = false;
	}

	GlobalActivityOpenInfos(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		infos = new java.util.LinkedList<xbean.TimeRange>();
	}

	public GlobalActivityOpenInfos() {
		this(0, null, null);
	}

	public GlobalActivityOpenInfos(GlobalActivityOpenInfos _o_) {
		this(_o_, null, null);
	}

	GlobalActivityOpenInfos(xbean.GlobalActivityOpenInfos _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof GlobalActivityOpenInfos) assign((GlobalActivityOpenInfos)_o1_);
		else if (_o1_ instanceof GlobalActivityOpenInfos.Data) assign((GlobalActivityOpenInfos.Data)_o1_);
		else if (_o1_ instanceof GlobalActivityOpenInfos.Const) assign(((GlobalActivityOpenInfos.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(GlobalActivityOpenInfos _o_) {
		_o_._xdb_verify_unsafe_();
		infos = new java.util.LinkedList<xbean.TimeRange>();
		for (xbean.TimeRange _v_ : _o_.infos)
			infos.add(new TimeRange(_v_, this, "infos"));
		enable = _o_.enable;
	}

	private void assign(GlobalActivityOpenInfos.Data _o_) {
		infos = new java.util.LinkedList<xbean.TimeRange>();
		for (xbean.TimeRange _v_ : _o_.infos)
			infos.add(new TimeRange(_v_, this, "infos"));
		enable = _o_.enable;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(infos.size());
for (xbean.TimeRange _v_ : infos) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 2048|  2));_os_.marshal(enable);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.TimeRange _v_ = new TimeRange(0, this, "infos");
	_v_.unmarshal(_os_);
	infos.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 2048|  2):enable = _os_.unmarshal_boolean();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.GlobalActivityOpenInfos copy() {
		_xdb_verify_unsafe_();
		return new GlobalActivityOpenInfos(this);
	}

	@Override
	public xbean.GlobalActivityOpenInfos toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GlobalActivityOpenInfos toBean() {
		_xdb_verify_unsafe_();
		return new GlobalActivityOpenInfos(this); // same as copy()
	}

	@Override
	public xbean.GlobalActivityOpenInfos toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GlobalActivityOpenInfos toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<xbean.TimeRange> getInfos() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "infos"), infos);
	}

	public java.util.List<xbean.TimeRange> getInfosAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<xbean.TimeRange> infos;
		GlobalActivityOpenInfos _o_ = this;
		infos = new java.util.LinkedList<xbean.TimeRange>();
		for (xbean.TimeRange _v_ : _o_.infos)
			infos.add(new TimeRange.Data(_v_));
		return infos;
	}

	@Override
	public boolean getEnable() { // 
		_xdb_verify_unsafe_();
		return enable;
	}

	@Override
	public void setEnable(boolean _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "enable") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, enable) {
					public void rollback() { enable = _xdb_saved; }
				};}});
		enable = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		GlobalActivityOpenInfos _o_ = null;
		if ( _o1_ instanceof GlobalActivityOpenInfos ) _o_ = (GlobalActivityOpenInfos)_o1_;
		else if ( _o1_ instanceof GlobalActivityOpenInfos.Const ) _o_ = ((GlobalActivityOpenInfos.Const)_o1_).nThis();
		else return false;
		if (!infos.equals(_o_.infos)) return false;
		if (enable != _o_.enable) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += infos.hashCode();
		_h_ += enable ? 1231 : 1237;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(infos);
		_sb_.append(",");
		_sb_.append(enable);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("infos"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("enable"));
		return lb;
	}

	private class Const implements xbean.GlobalActivityOpenInfos {
		GlobalActivityOpenInfos nThis() {
			return GlobalActivityOpenInfos.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.GlobalActivityOpenInfos copy() {
			return GlobalActivityOpenInfos.this.copy();
		}

		@Override
		public xbean.GlobalActivityOpenInfos toData() {
			return GlobalActivityOpenInfos.this.toData();
		}

		public xbean.GlobalActivityOpenInfos toBean() {
			return GlobalActivityOpenInfos.this.toBean();
		}

		@Override
		public xbean.GlobalActivityOpenInfos toDataIf() {
			return GlobalActivityOpenInfos.this.toDataIf();
		}

		public xbean.GlobalActivityOpenInfos toBeanIf() {
			return GlobalActivityOpenInfos.this.toBeanIf();
		}

		@Override
		public java.util.List<xbean.TimeRange> getInfos() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(infos);
		}

		public java.util.List<xbean.TimeRange> getInfosAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<xbean.TimeRange> infos;
			GlobalActivityOpenInfos _o_ = GlobalActivityOpenInfos.this;
		infos = new java.util.LinkedList<xbean.TimeRange>();
		for (xbean.TimeRange _v_ : _o_.infos)
			infos.add(new TimeRange.Data(_v_));
			return infos;
		}

		@Override
		public boolean getEnable() { // 
			_xdb_verify_unsafe_();
			return enable;
		}

		@Override
		public void setEnable(boolean _v_) { // 
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
			return GlobalActivityOpenInfos.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return GlobalActivityOpenInfos.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return GlobalActivityOpenInfos.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return GlobalActivityOpenInfos.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return GlobalActivityOpenInfos.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return GlobalActivityOpenInfos.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return GlobalActivityOpenInfos.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return GlobalActivityOpenInfos.this.hashCode();
		}

		@Override
		public String toString() {
			return GlobalActivityOpenInfos.this.toString();
		}

	}

	public static final class Data implements xbean.GlobalActivityOpenInfos {
		private java.util.LinkedList<xbean.TimeRange> infos; // 
		private boolean enable; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			infos = new java.util.LinkedList<xbean.TimeRange>();
		}

		Data(xbean.GlobalActivityOpenInfos _o1_) {
			if (_o1_ instanceof GlobalActivityOpenInfos) assign((GlobalActivityOpenInfos)_o1_);
			else if (_o1_ instanceof GlobalActivityOpenInfos.Data) assign((GlobalActivityOpenInfos.Data)_o1_);
			else if (_o1_ instanceof GlobalActivityOpenInfos.Const) assign(((GlobalActivityOpenInfos.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(GlobalActivityOpenInfos _o_) {
			infos = new java.util.LinkedList<xbean.TimeRange>();
			for (xbean.TimeRange _v_ : _o_.infos)
				infos.add(new TimeRange.Data(_v_));
			enable = _o_.enable;
		}

		private void assign(GlobalActivityOpenInfos.Data _o_) {
			infos = new java.util.LinkedList<xbean.TimeRange>();
			for (xbean.TimeRange _v_ : _o_.infos)
				infos.add(new TimeRange.Data(_v_));
			enable = _o_.enable;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(infos.size());
for (xbean.TimeRange _v_ : infos) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 2048|  2));_os_.marshal(enable);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.TimeRange _v_ = xbean.Pod.newTimeRangeData();
	_v_.unmarshal(_os_);
	infos.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 2048|  2):enable = _os_.unmarshal_boolean();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.GlobalActivityOpenInfos copy() {
			return new Data(this);
		}

		@Override
		public xbean.GlobalActivityOpenInfos toData() {
			return new Data(this);
		}

		public xbean.GlobalActivityOpenInfos toBean() {
			return new GlobalActivityOpenInfos(this, null, null);
		}

		@Override
		public xbean.GlobalActivityOpenInfos toDataIf() {
			return this;
		}

		public xbean.GlobalActivityOpenInfos toBeanIf() {
			return new GlobalActivityOpenInfos(this, null, null);
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
		public java.util.List<xbean.TimeRange> getInfos() { // 
			return infos;
		}

		@Override
		public java.util.List<xbean.TimeRange> getInfosAsData() { // 
			return infos;
		}

		@Override
		public boolean getEnable() { // 
			return enable;
		}

		@Override
		public void setEnable(boolean _v_) { // 
			enable = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof GlobalActivityOpenInfos.Data)) return false;
			GlobalActivityOpenInfos.Data _o_ = (GlobalActivityOpenInfos.Data) _o1_;
			if (!infos.equals(_o_.infos)) return false;
			if (enable != _o_.enable) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += infos.hashCode();
			_h_ += enable ? 1231 : 1237;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(infos);
			_sb_.append(",");
			_sb_.append(enable);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
