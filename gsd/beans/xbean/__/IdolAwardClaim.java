
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class IdolAwardClaim extends xdb.XBean implements xbean.IdolAwardClaim {
	private xdb.util.SetX<Integer> claiminfo; // 领取状况

	@Override
	public void _reset_unsafe_() {
		claiminfo.clear();
	}

	IdolAwardClaim(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		claiminfo = new xdb.util.SetX<Integer>();
	}

	public IdolAwardClaim() {
		this(0, null, null);
	}

	public IdolAwardClaim(IdolAwardClaim _o_) {
		this(_o_, null, null);
	}

	IdolAwardClaim(xbean.IdolAwardClaim _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof IdolAwardClaim) assign((IdolAwardClaim)_o1_);
		else if (_o1_ instanceof IdolAwardClaim.Data) assign((IdolAwardClaim.Data)_o1_);
		else if (_o1_ instanceof IdolAwardClaim.Const) assign(((IdolAwardClaim.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(IdolAwardClaim _o_) {
		_o_._xdb_verify_unsafe_();
		claiminfo = new xdb.util.SetX<Integer>();
		claiminfo.addAll(_o_.claiminfo);
	}

	private void assign(IdolAwardClaim.Data _o_) {
		claiminfo = new xdb.util.SetX<Integer>();
		claiminfo.addAll(_o_.claiminfo);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(claiminfo.size());
for (Integer _v_ : claiminfo) {
	_os_.marshal(_v_);
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
    				case (20480|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	claiminfo.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.IdolAwardClaim copy() {
		_xdb_verify_unsafe_();
		return new IdolAwardClaim(this);
	}

	@Override
	public xbean.IdolAwardClaim toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IdolAwardClaim toBean() {
		_xdb_verify_unsafe_();
		return new IdolAwardClaim(this); // same as copy()
	}

	@Override
	public xbean.IdolAwardClaim toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IdolAwardClaim toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Set<Integer> getClaiminfo() { // 领取状况
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "claiminfo"), claiminfo);
	}

	public java.util.Set<Integer> getClaiminfoAsData() { // 领取状况
		_xdb_verify_unsafe_();
		java.util.Set<Integer> claiminfo;
		IdolAwardClaim _o_ = this;
		claiminfo = new xdb.util.SetX<Integer>();
		claiminfo.addAll(_o_.claiminfo);
		return claiminfo;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		IdolAwardClaim _o_ = null;
		if ( _o1_ instanceof IdolAwardClaim ) _o_ = (IdolAwardClaim)_o1_;
		else if ( _o1_ instanceof IdolAwardClaim.Const ) _o_ = ((IdolAwardClaim.Const)_o1_).nThis();
		else return false;
		if (!claiminfo.equals(_o_.claiminfo)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += claiminfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(claiminfo);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableSet().setVarName("claiminfo"));
		return lb;
	}

	private class Const implements xbean.IdolAwardClaim {
		IdolAwardClaim nThis() {
			return IdolAwardClaim.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.IdolAwardClaim copy() {
			return IdolAwardClaim.this.copy();
		}

		@Override
		public xbean.IdolAwardClaim toData() {
			return IdolAwardClaim.this.toData();
		}

		public xbean.IdolAwardClaim toBean() {
			return IdolAwardClaim.this.toBean();
		}

		@Override
		public xbean.IdolAwardClaim toDataIf() {
			return IdolAwardClaim.this.toDataIf();
		}

		public xbean.IdolAwardClaim toBeanIf() {
			return IdolAwardClaim.this.toBeanIf();
		}

		@Override
		public java.util.Set<Integer> getClaiminfo() { // 领取状况
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(claiminfo);
		}

		public java.util.Set<Integer> getClaiminfoAsData() { // 领取状况
			_xdb_verify_unsafe_();
			java.util.Set<Integer> claiminfo;
			IdolAwardClaim _o_ = IdolAwardClaim.this;
		claiminfo = new xdb.util.SetX<Integer>();
		claiminfo.addAll(_o_.claiminfo);
			return claiminfo;
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
			return IdolAwardClaim.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return IdolAwardClaim.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return IdolAwardClaim.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return IdolAwardClaim.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return IdolAwardClaim.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return IdolAwardClaim.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return IdolAwardClaim.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return IdolAwardClaim.this.hashCode();
		}

		@Override
		public String toString() {
			return IdolAwardClaim.this.toString();
		}

	}

	public static final class Data implements xbean.IdolAwardClaim {
		private java.util.HashSet<Integer> claiminfo; // 领取状况

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			claiminfo = new java.util.HashSet<Integer>();
		}

		Data(xbean.IdolAwardClaim _o1_) {
			if (_o1_ instanceof IdolAwardClaim) assign((IdolAwardClaim)_o1_);
			else if (_o1_ instanceof IdolAwardClaim.Data) assign((IdolAwardClaim.Data)_o1_);
			else if (_o1_ instanceof IdolAwardClaim.Const) assign(((IdolAwardClaim.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(IdolAwardClaim _o_) {
			claiminfo = new java.util.HashSet<Integer>();
			claiminfo.addAll(_o_.claiminfo);
		}

		private void assign(IdolAwardClaim.Data _o_) {
			claiminfo = new java.util.HashSet<Integer>();
			claiminfo.addAll(_o_.claiminfo);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(claiminfo.size());
for (Integer _v_ : claiminfo) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (20480|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	claiminfo.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.IdolAwardClaim copy() {
			return new Data(this);
		}

		@Override
		public xbean.IdolAwardClaim toData() {
			return new Data(this);
		}

		public xbean.IdolAwardClaim toBean() {
			return new IdolAwardClaim(this, null, null);
		}

		@Override
		public xbean.IdolAwardClaim toDataIf() {
			return this;
		}

		public xbean.IdolAwardClaim toBeanIf() {
			return new IdolAwardClaim(this, null, null);
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
		public java.util.Set<Integer> getClaiminfo() { // 领取状况
			return claiminfo;
		}

		@Override
		public java.util.Set<Integer> getClaiminfoAsData() { // 领取状况
			return claiminfo;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof IdolAwardClaim.Data)) return false;
			IdolAwardClaim.Data _o_ = (IdolAwardClaim.Data) _o1_;
			if (!claiminfo.equals(_o_.claiminfo)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += claiminfo.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(claiminfo);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
