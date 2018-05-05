
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class AccessoryProp extends xdb.XBean implements xbean.AccessoryProp {
	private int key; // 
	private float val; // 

	@Override
	public void _reset_unsafe_() {
		key = 0;
		val = 0.0f;
	}

	AccessoryProp(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public AccessoryProp() {
		this(0, null, null);
	}

	public AccessoryProp(AccessoryProp _o_) {
		this(_o_, null, null);
	}

	AccessoryProp(xbean.AccessoryProp _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof AccessoryProp) assign((AccessoryProp)_o1_);
		else if (_o1_ instanceof AccessoryProp.Data) assign((AccessoryProp.Data)_o1_);
		else if (_o1_ instanceof AccessoryProp.Const) assign(((AccessoryProp.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(AccessoryProp _o_) {
		_o_._xdb_verify_unsafe_();
		key = _o_.key;
		val = _o_.val;
	}

	private void assign(AccessoryProp.Data _o_) {
		key = _o_.key;
		val = _o_.val;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(key);
    _os_.marshal((short)(12288|  2));_os_.marshal(val);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):key = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):key = _os_.unmarshal_short();
    				break;
    				case (12288|  2):val = _os_.unmarshal_float();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.AccessoryProp copy() {
		_xdb_verify_unsafe_();
		return new AccessoryProp(this);
	}

	@Override
	public xbean.AccessoryProp toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AccessoryProp toBean() {
		_xdb_verify_unsafe_();
		return new AccessoryProp(this); // same as copy()
	}

	@Override
	public xbean.AccessoryProp toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AccessoryProp toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getKey() { // 
		_xdb_verify_unsafe_();
		return key;
	}

	@Override
	public float getVal() { // 
		_xdb_verify_unsafe_();
		return val;
	}

	@Override
	public void setKey(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "key") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, key) {
					public void rollback() { key = _xdb_saved; }
				};}});
		key = _v_;
	}

	@Override
	public void setVal(float _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "val") {
			protected xdb.Log create() {
				return new xdb.logs.LogFloat(this, val) {
					public void rollback() { val = _xdb_saved; }
				};}});
		val = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		AccessoryProp _o_ = null;
		if ( _o1_ instanceof AccessoryProp ) _o_ = (AccessoryProp)_o1_;
		else if ( _o1_ instanceof AccessoryProp.Const ) _o_ = ((AccessoryProp.Const)_o1_).nThis();
		else return false;
		if (key != _o_.key) return false;
		if (val != _o_.val) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += key;
		_h_ += val;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(key);
		_sb_.append(",");
		_sb_.append(val);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("key"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("val"));
		return lb;
	}

	private class Const implements xbean.AccessoryProp {
		AccessoryProp nThis() {
			return AccessoryProp.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.AccessoryProp copy() {
			return AccessoryProp.this.copy();
		}

		@Override
		public xbean.AccessoryProp toData() {
			return AccessoryProp.this.toData();
		}

		public xbean.AccessoryProp toBean() {
			return AccessoryProp.this.toBean();
		}

		@Override
		public xbean.AccessoryProp toDataIf() {
			return AccessoryProp.this.toDataIf();
		}

		public xbean.AccessoryProp toBeanIf() {
			return AccessoryProp.this.toBeanIf();
		}

		@Override
		public int getKey() { // 
			_xdb_verify_unsafe_();
			return key;
		}

		@Override
		public float getVal() { // 
			_xdb_verify_unsafe_();
			return val;
		}

		@Override
		public void setKey(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setVal(float _v_) { // 
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
			return AccessoryProp.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return AccessoryProp.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return AccessoryProp.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return AccessoryProp.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return AccessoryProp.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return AccessoryProp.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return AccessoryProp.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return AccessoryProp.this.hashCode();
		}

		@Override
		public String toString() {
			return AccessoryProp.this.toString();
		}

	}

	public static final class Data implements xbean.AccessoryProp {
		private int key; // 
		private float val; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.AccessoryProp _o1_) {
			if (_o1_ instanceof AccessoryProp) assign((AccessoryProp)_o1_);
			else if (_o1_ instanceof AccessoryProp.Data) assign((AccessoryProp.Data)_o1_);
			else if (_o1_ instanceof AccessoryProp.Const) assign(((AccessoryProp.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(AccessoryProp _o_) {
			key = _o_.key;
			val = _o_.val;
		}

		private void assign(AccessoryProp.Data _o_) {
			key = _o_.key;
			val = _o_.val;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(key);
	_os_.marshal((short)(12288|  2));_os_.marshal(val);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):key = _os_.unmarshal_int();
					break;
					case ( 6144|  1):key = _os_.unmarshal_short();
					break;
					case (12288|  2):val = _os_.unmarshal_float();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.AccessoryProp copy() {
			return new Data(this);
		}

		@Override
		public xbean.AccessoryProp toData() {
			return new Data(this);
		}

		public xbean.AccessoryProp toBean() {
			return new AccessoryProp(this, null, null);
		}

		@Override
		public xbean.AccessoryProp toDataIf() {
			return this;
		}

		public xbean.AccessoryProp toBeanIf() {
			return new AccessoryProp(this, null, null);
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
		public int getKey() { // 
			return key;
		}

		@Override
		public float getVal() { // 
			return val;
		}

		@Override
		public void setKey(int _v_) { // 
			key = _v_;
		}

		@Override
		public void setVal(float _v_) { // 
			val = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof AccessoryProp.Data)) return false;
			AccessoryProp.Data _o_ = (AccessoryProp.Data) _o1_;
			if (key != _o_.key) return false;
			if (val != _o_.val) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += key;
			_h_ += val;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(key);
			_sb_.append(",");
			_sb_.append(val);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
