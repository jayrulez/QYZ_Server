
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class IdGen extends xdb.XBean implements xbean.IdGen {
	private long itemid; // 

	@Override
	public void _reset_unsafe_() {
		itemid = 0L;
	}

	IdGen(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public IdGen() {
		this(0, null, null);
	}

	public IdGen(IdGen _o_) {
		this(_o_, null, null);
	}

	IdGen(xbean.IdGen _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof IdGen) assign((IdGen)_o1_);
		else if (_o1_ instanceof IdGen.Data) assign((IdGen.Data)_o1_);
		else if (_o1_ instanceof IdGen.Const) assign(((IdGen.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(IdGen _o_) {
		_o_._xdb_verify_unsafe_();
		itemid = _o_.itemid;
	}

	private void assign(IdGen.Data _o_) {
		itemid = _o_.itemid;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(10240|  1));_os_.marshal(itemid);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):itemid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):itemid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):itemid = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.IdGen copy() {
		_xdb_verify_unsafe_();
		return new IdGen(this);
	}

	@Override
	public xbean.IdGen toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IdGen toBean() {
		_xdb_verify_unsafe_();
		return new IdGen(this); // same as copy()
	}

	@Override
	public xbean.IdGen toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IdGen toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getItemid() { // 
		_xdb_verify_unsafe_();
		return itemid;
	}

	@Override
	public void setItemid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "itemid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, itemid) {
					public void rollback() { itemid = _xdb_saved; }
				};}});
		itemid = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		IdGen _o_ = null;
		if ( _o1_ instanceof IdGen ) _o_ = (IdGen)_o1_;
		else if ( _o1_ instanceof IdGen.Const ) _o_ = ((IdGen.Const)_o1_).nThis();
		else return false;
		if (itemid != _o_.itemid) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += itemid;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(itemid);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("itemid"));
		return lb;
	}

	private class Const implements xbean.IdGen {
		IdGen nThis() {
			return IdGen.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.IdGen copy() {
			return IdGen.this.copy();
		}

		@Override
		public xbean.IdGen toData() {
			return IdGen.this.toData();
		}

		public xbean.IdGen toBean() {
			return IdGen.this.toBean();
		}

		@Override
		public xbean.IdGen toDataIf() {
			return IdGen.this.toDataIf();
		}

		public xbean.IdGen toBeanIf() {
			return IdGen.this.toBeanIf();
		}

		@Override
		public long getItemid() { // 
			_xdb_verify_unsafe_();
			return itemid;
		}

		@Override
		public void setItemid(long _v_) { // 
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
			return IdGen.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return IdGen.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return IdGen.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return IdGen.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return IdGen.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return IdGen.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return IdGen.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return IdGen.this.hashCode();
		}

		@Override
		public String toString() {
			return IdGen.this.toString();
		}

	}

	public static final class Data implements xbean.IdGen {
		private long itemid; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.IdGen _o1_) {
			if (_o1_ instanceof IdGen) assign((IdGen)_o1_);
			else if (_o1_ instanceof IdGen.Data) assign((IdGen.Data)_o1_);
			else if (_o1_ instanceof IdGen.Const) assign(((IdGen.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(IdGen _o_) {
			itemid = _o_.itemid;
		}

		private void assign(IdGen.Data _o_) {
			itemid = _o_.itemid;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(10240|  1));_os_.marshal(itemid);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):itemid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):itemid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):itemid = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.IdGen copy() {
			return new Data(this);
		}

		@Override
		public xbean.IdGen toData() {
			return new Data(this);
		}

		public xbean.IdGen toBean() {
			return new IdGen(this, null, null);
		}

		@Override
		public xbean.IdGen toDataIf() {
			return this;
		}

		public xbean.IdGen toBeanIf() {
			return new IdGen(this, null, null);
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
		public long getItemid() { // 
			return itemid;
		}

		@Override
		public void setItemid(long _v_) { // 
			itemid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof IdGen.Data)) return false;
			IdGen.Data _o_ = (IdGen.Data) _o1_;
			if (itemid != _o_.itemid) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += itemid;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(itemid);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
