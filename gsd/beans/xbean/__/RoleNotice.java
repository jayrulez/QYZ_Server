
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleNotice extends xdb.XBean implements xbean.RoleNotice {
	private java.util.LinkedList<xbean.Notice> notices; // 

	@Override
	public void _reset_unsafe_() {
		notices.clear();
	}

	RoleNotice(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		notices = new java.util.LinkedList<xbean.Notice>();
	}

	public RoleNotice() {
		this(0, null, null);
	}

	public RoleNotice(RoleNotice _o_) {
		this(_o_, null, null);
	}

	RoleNotice(xbean.RoleNotice _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleNotice) assign((RoleNotice)_o1_);
		else if (_o1_ instanceof RoleNotice.Data) assign((RoleNotice.Data)_o1_);
		else if (_o1_ instanceof RoleNotice.Const) assign(((RoleNotice.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleNotice _o_) {
		_o_._xdb_verify_unsafe_();
		notices = new java.util.LinkedList<xbean.Notice>();
		for (xbean.Notice _v_ : _o_.notices)
			notices.add(new Notice(_v_, this, "notices"));
	}

	private void assign(RoleNotice.Data _o_) {
		notices = new java.util.LinkedList<xbean.Notice>();
		for (xbean.Notice _v_ : _o_.notices)
			notices.add(new Notice(_v_, this, "notices"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(22528|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(notices.size());
for (xbean.Notice _v_ : notices) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (22528|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.Notice _v_ = new Notice(0, this, "notices");
	_v_.unmarshal(_os_);
	notices.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleNotice copy() {
		_xdb_verify_unsafe_();
		return new RoleNotice(this);
	}

	@Override
	public xbean.RoleNotice toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleNotice toBean() {
		_xdb_verify_unsafe_();
		return new RoleNotice(this); // same as copy()
	}

	@Override
	public xbean.RoleNotice toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleNotice toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<xbean.Notice> getNotices() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "notices"), notices);
	}

	public java.util.List<xbean.Notice> getNoticesAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<xbean.Notice> notices;
		RoleNotice _o_ = this;
		notices = new java.util.LinkedList<xbean.Notice>();
		for (xbean.Notice _v_ : _o_.notices)
			notices.add(new Notice.Data(_v_));
		return notices;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleNotice _o_ = null;
		if ( _o1_ instanceof RoleNotice ) _o_ = (RoleNotice)_o1_;
		else if ( _o1_ instanceof RoleNotice.Const ) _o_ = ((RoleNotice.Const)_o1_).nThis();
		else return false;
		if (!notices.equals(_o_.notices)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += notices.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(notices);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("notices"));
		return lb;
	}

	private class Const implements xbean.RoleNotice {
		RoleNotice nThis() {
			return RoleNotice.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleNotice copy() {
			return RoleNotice.this.copy();
		}

		@Override
		public xbean.RoleNotice toData() {
			return RoleNotice.this.toData();
		}

		public xbean.RoleNotice toBean() {
			return RoleNotice.this.toBean();
		}

		@Override
		public xbean.RoleNotice toDataIf() {
			return RoleNotice.this.toDataIf();
		}

		public xbean.RoleNotice toBeanIf() {
			return RoleNotice.this.toBeanIf();
		}

		@Override
		public java.util.List<xbean.Notice> getNotices() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(notices);
		}

		public java.util.List<xbean.Notice> getNoticesAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<xbean.Notice> notices;
			RoleNotice _o_ = RoleNotice.this;
		notices = new java.util.LinkedList<xbean.Notice>();
		for (xbean.Notice _v_ : _o_.notices)
			notices.add(new Notice.Data(_v_));
			return notices;
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
			return RoleNotice.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleNotice.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleNotice.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleNotice.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleNotice.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleNotice.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleNotice.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleNotice.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleNotice.this.toString();
		}

	}

	public static final class Data implements xbean.RoleNotice {
		private java.util.LinkedList<xbean.Notice> notices; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			notices = new java.util.LinkedList<xbean.Notice>();
		}

		Data(xbean.RoleNotice _o1_) {
			if (_o1_ instanceof RoleNotice) assign((RoleNotice)_o1_);
			else if (_o1_ instanceof RoleNotice.Data) assign((RoleNotice.Data)_o1_);
			else if (_o1_ instanceof RoleNotice.Const) assign(((RoleNotice.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleNotice _o_) {
			notices = new java.util.LinkedList<xbean.Notice>();
			for (xbean.Notice _v_ : _o_.notices)
				notices.add(new Notice.Data(_v_));
		}

		private void assign(RoleNotice.Data _o_) {
			notices = new java.util.LinkedList<xbean.Notice>();
			for (xbean.Notice _v_ : _o_.notices)
				notices.add(new Notice.Data(_v_));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(22528|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(notices.size());
for (xbean.Notice _v_ : notices) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (22528|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.Notice _v_ = xbean.Pod.newNoticeData();
	_v_.unmarshal(_os_);
	notices.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleNotice copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleNotice toData() {
			return new Data(this);
		}

		public xbean.RoleNotice toBean() {
			return new RoleNotice(this, null, null);
		}

		@Override
		public xbean.RoleNotice toDataIf() {
			return this;
		}

		public xbean.RoleNotice toBeanIf() {
			return new RoleNotice(this, null, null);
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
		public java.util.List<xbean.Notice> getNotices() { // 
			return notices;
		}

		@Override
		public java.util.List<xbean.Notice> getNoticesAsData() { // 
			return notices;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleNotice.Data)) return false;
			RoleNotice.Data _o_ = (RoleNotice.Data) _o1_;
			if (!notices.equals(_o_.notices)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += notices.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(notices);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
