
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ARankInfo extends xdb.XBean implements xbean.ARankInfo {

	@Override
	public void _reset_unsafe_() {
	}

	ARankInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public ARankInfo() {
		this(0, null, null);
	}

	public ARankInfo(ARankInfo _o_) {
		this(_o_, null, null);
	}

	ARankInfo(xbean.ARankInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ARankInfo) assign((ARankInfo)_o1_);
		else if (_o1_ instanceof ARankInfo.Data) assign((ARankInfo.Data)_o1_);
		else if (_o1_ instanceof ARankInfo.Const) assign(((ARankInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ARankInfo _o_) {
		_o_._xdb_verify_unsafe_();
	}

	private void assign(ARankInfo.Data _o_) {
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)0);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ARankInfo copy() {
		_xdb_verify_unsafe_();
		return new ARankInfo(this);
	}

	@Override
	public xbean.ARankInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ARankInfo toBean() {
		_xdb_verify_unsafe_();
		return new ARankInfo(this); // same as copy()
	}

	@Override
	public xbean.ARankInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ARankInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ARankInfo _o_ = null;
		if ( _o1_ instanceof ARankInfo ) _o_ = (ARankInfo)_o1_;
		else if ( _o1_ instanceof ARankInfo.Const ) _o_ = ((ARankInfo.Const)_o1_).nThis();
		else return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		return lb;
	}

	private class Const implements xbean.ARankInfo {
		ARankInfo nThis() {
			return ARankInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ARankInfo copy() {
			return ARankInfo.this.copy();
		}

		@Override
		public xbean.ARankInfo toData() {
			return ARankInfo.this.toData();
		}

		public xbean.ARankInfo toBean() {
			return ARankInfo.this.toBean();
		}

		@Override
		public xbean.ARankInfo toDataIf() {
			return ARankInfo.this.toDataIf();
		}

		public xbean.ARankInfo toBeanIf() {
			return ARankInfo.this.toBeanIf();
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
			return ARankInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ARankInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ARankInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ARankInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ARankInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ARankInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ARankInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ARankInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return ARankInfo.this.toString();
		}

	}

	public static final class Data implements xbean.ARankInfo {

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.ARankInfo _o1_) {
			if (_o1_ instanceof ARankInfo) assign((ARankInfo)_o1_);
			else if (_o1_ instanceof ARankInfo.Data) assign((ARankInfo.Data)_o1_);
			else if (_o1_ instanceof ARankInfo.Const) assign(((ARankInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ARankInfo _o_) {
		}

		private void assign(ARankInfo.Data _o_) {
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)0);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ARankInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.ARankInfo toData() {
			return new Data(this);
		}

		public xbean.ARankInfo toBean() {
			return new ARankInfo(this, null, null);
		}

		@Override
		public xbean.ARankInfo toDataIf() {
			return this;
		}

		public xbean.ARankInfo toBeanIf() {
			return new ARankInfo(this, null, null);
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
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ARankInfo.Data)) return false;
			ARankInfo.Data _o_ = (ARankInfo.Data) _o1_;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
