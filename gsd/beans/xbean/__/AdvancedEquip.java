
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class AdvancedEquip extends xdb.XBean implements xbean.AdvancedEquip {

	@Override
	public void _reset_unsafe_() {
	}

	AdvancedEquip(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public AdvancedEquip() {
		this(0, null, null);
	}

	public AdvancedEquip(AdvancedEquip _o_) {
		this(_o_, null, null);
	}

	AdvancedEquip(xbean.AdvancedEquip _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof AdvancedEquip) assign((AdvancedEquip)_o1_);
		else if (_o1_ instanceof AdvancedEquip.Data) assign((AdvancedEquip.Data)_o1_);
		else if (_o1_ instanceof AdvancedEquip.Const) assign(((AdvancedEquip.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(AdvancedEquip _o_) {
		_o_._xdb_verify_unsafe_();
	}

	private void assign(AdvancedEquip.Data _o_) {
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
	public xbean.AdvancedEquip copy() {
		_xdb_verify_unsafe_();
		return new AdvancedEquip(this);
	}

	@Override
	public xbean.AdvancedEquip toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AdvancedEquip toBean() {
		_xdb_verify_unsafe_();
		return new AdvancedEquip(this); // same as copy()
	}

	@Override
	public xbean.AdvancedEquip toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AdvancedEquip toBeanIf() {
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
		AdvancedEquip _o_ = null;
		if ( _o1_ instanceof AdvancedEquip ) _o_ = (AdvancedEquip)_o1_;
		else if ( _o1_ instanceof AdvancedEquip.Const ) _o_ = ((AdvancedEquip.Const)_o1_).nThis();
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

	private class Const implements xbean.AdvancedEquip {
		AdvancedEquip nThis() {
			return AdvancedEquip.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.AdvancedEquip copy() {
			return AdvancedEquip.this.copy();
		}

		@Override
		public xbean.AdvancedEquip toData() {
			return AdvancedEquip.this.toData();
		}

		public xbean.AdvancedEquip toBean() {
			return AdvancedEquip.this.toBean();
		}

		@Override
		public xbean.AdvancedEquip toDataIf() {
			return AdvancedEquip.this.toDataIf();
		}

		public xbean.AdvancedEquip toBeanIf() {
			return AdvancedEquip.this.toBeanIf();
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
			return AdvancedEquip.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return AdvancedEquip.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return AdvancedEquip.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return AdvancedEquip.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return AdvancedEquip.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return AdvancedEquip.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return AdvancedEquip.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return AdvancedEquip.this.hashCode();
		}

		@Override
		public String toString() {
			return AdvancedEquip.this.toString();
		}

	}

	public static final class Data implements xbean.AdvancedEquip {

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.AdvancedEquip _o1_) {
			if (_o1_ instanceof AdvancedEquip) assign((AdvancedEquip)_o1_);
			else if (_o1_ instanceof AdvancedEquip.Data) assign((AdvancedEquip.Data)_o1_);
			else if (_o1_ instanceof AdvancedEquip.Const) assign(((AdvancedEquip.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(AdvancedEquip _o_) {
		}

		private void assign(AdvancedEquip.Data _o_) {
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
		public xbean.AdvancedEquip copy() {
			return new Data(this);
		}

		@Override
		public xbean.AdvancedEquip toData() {
			return new Data(this);
		}

		public xbean.AdvancedEquip toBean() {
			return new AdvancedEquip(this, null, null);
		}

		@Override
		public xbean.AdvancedEquip toDataIf() {
			return this;
		}

		public xbean.AdvancedEquip toBeanIf() {
			return new AdvancedEquip(this, null, null);
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
			if (!(_o1_ instanceof AdvancedEquip.Data)) return false;
			AdvancedEquip.Data _o_ = (AdvancedEquip.Data) _o1_;
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
