
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class AccessoryWashResult extends xdb.XBean implements xbean.AccessoryWashResult {
	private int oldpropindex; // 旧的key
	private xbean.AccessoryProp newprop; // 新属性key
	private boolean needbind; // 需要设置成绑定

	@Override
	public void _reset_unsafe_() {
		oldpropindex = -1;
		newprop._reset_unsafe_();
		needbind = false;
	}

	AccessoryWashResult(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		oldpropindex = -1;
		newprop = new AccessoryProp(0, this, "newprop");
	}

	public AccessoryWashResult() {
		this(0, null, null);
	}

	public AccessoryWashResult(AccessoryWashResult _o_) {
		this(_o_, null, null);
	}

	AccessoryWashResult(xbean.AccessoryWashResult _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof AccessoryWashResult) assign((AccessoryWashResult)_o1_);
		else if (_o1_ instanceof AccessoryWashResult.Data) assign((AccessoryWashResult.Data)_o1_);
		else if (_o1_ instanceof AccessoryWashResult.Const) assign(((AccessoryWashResult.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(AccessoryWashResult _o_) {
		_o_._xdb_verify_unsafe_();
		oldpropindex = _o_.oldpropindex;
		newprop = new AccessoryProp(_o_.newprop, this, "newprop");
		needbind = _o_.needbind;
	}

	private void assign(AccessoryWashResult.Data _o_) {
		oldpropindex = _o_.oldpropindex;
		newprop = new AccessoryProp(_o_.newprop, this, "newprop");
		needbind = _o_.needbind;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  1));_os_.marshal(oldpropindex);
    _os_.marshal((short)(26624|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
newprop.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 2048|  3));_os_.marshal(needbind);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):oldpropindex = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):oldpropindex = _os_.unmarshal_short();
    				break;
    				case (26624|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
newprop.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case ( 2048|  3):needbind = _os_.unmarshal_boolean();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.AccessoryWashResult copy() {
		_xdb_verify_unsafe_();
		return new AccessoryWashResult(this);
	}

	@Override
	public xbean.AccessoryWashResult toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AccessoryWashResult toBean() {
		_xdb_verify_unsafe_();
		return new AccessoryWashResult(this); // same as copy()
	}

	@Override
	public xbean.AccessoryWashResult toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AccessoryWashResult toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getOldpropindex() { // 旧的key
		_xdb_verify_unsafe_();
		return oldpropindex;
	}

	@Override
	public xbean.AccessoryProp getNewprop() { // 新属性key
		_xdb_verify_unsafe_();
		return newprop;
	}

	@Override
	public boolean getNeedbind() { // 需要设置成绑定
		_xdb_verify_unsafe_();
		return needbind;
	}

	@Override
	public void setOldpropindex(int _v_) { // 旧的key
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "oldpropindex") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, oldpropindex) {
					public void rollback() { oldpropindex = _xdb_saved; }
				};}});
		oldpropindex = _v_;
	}

	@Override
	public void setNeedbind(boolean _v_) { // 需要设置成绑定
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "needbind") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, needbind) {
					public void rollback() { needbind = _xdb_saved; }
				};}});
		needbind = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		AccessoryWashResult _o_ = null;
		if ( _o1_ instanceof AccessoryWashResult ) _o_ = (AccessoryWashResult)_o1_;
		else if ( _o1_ instanceof AccessoryWashResult.Const ) _o_ = ((AccessoryWashResult.Const)_o1_).nThis();
		else return false;
		if (oldpropindex != _o_.oldpropindex) return false;
		if (!newprop.equals(_o_.newprop)) return false;
		if (needbind != _o_.needbind) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += oldpropindex;
		_h_ += newprop.hashCode();
		_h_ += needbind ? 1231 : 1237;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(oldpropindex);
		_sb_.append(",");
		_sb_.append(newprop);
		_sb_.append(",");
		_sb_.append(needbind);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("oldpropindex"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("newprop"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("needbind"));
		return lb;
	}

	private class Const implements xbean.AccessoryWashResult {
		AccessoryWashResult nThis() {
			return AccessoryWashResult.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.AccessoryWashResult copy() {
			return AccessoryWashResult.this.copy();
		}

		@Override
		public xbean.AccessoryWashResult toData() {
			return AccessoryWashResult.this.toData();
		}

		public xbean.AccessoryWashResult toBean() {
			return AccessoryWashResult.this.toBean();
		}

		@Override
		public xbean.AccessoryWashResult toDataIf() {
			return AccessoryWashResult.this.toDataIf();
		}

		public xbean.AccessoryWashResult toBeanIf() {
			return AccessoryWashResult.this.toBeanIf();
		}

		@Override
		public int getOldpropindex() { // 旧的key
			_xdb_verify_unsafe_();
			return oldpropindex;
		}

		@Override
		public xbean.AccessoryProp getNewprop() { // 新属性key
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(newprop);
		}

		@Override
		public boolean getNeedbind() { // 需要设置成绑定
			_xdb_verify_unsafe_();
			return needbind;
		}

		@Override
		public void setOldpropindex(int _v_) { // 旧的key
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNeedbind(boolean _v_) { // 需要设置成绑定
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
			return AccessoryWashResult.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return AccessoryWashResult.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return AccessoryWashResult.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return AccessoryWashResult.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return AccessoryWashResult.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return AccessoryWashResult.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return AccessoryWashResult.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return AccessoryWashResult.this.hashCode();
		}

		@Override
		public String toString() {
			return AccessoryWashResult.this.toString();
		}

	}

	public static final class Data implements xbean.AccessoryWashResult {
		private int oldpropindex; // 旧的key
		private xbean.AccessoryProp newprop; // 新属性key
		private boolean needbind; // 需要设置成绑定

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			oldpropindex = -1;
			newprop = new AccessoryProp.Data();
		}

		Data(xbean.AccessoryWashResult _o1_) {
			if (_o1_ instanceof AccessoryWashResult) assign((AccessoryWashResult)_o1_);
			else if (_o1_ instanceof AccessoryWashResult.Data) assign((AccessoryWashResult.Data)_o1_);
			else if (_o1_ instanceof AccessoryWashResult.Const) assign(((AccessoryWashResult.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(AccessoryWashResult _o_) {
			oldpropindex = _o_.oldpropindex;
			newprop = new AccessoryProp.Data(_o_.newprop);
			needbind = _o_.needbind;
		}

		private void assign(AccessoryWashResult.Data _o_) {
			oldpropindex = _o_.oldpropindex;
			newprop = new AccessoryProp.Data(_o_.newprop);
			needbind = _o_.needbind;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  1));_os_.marshal(oldpropindex);
	_os_.marshal((short)(26624|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
newprop.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 2048|  3));_os_.marshal(needbind);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):oldpropindex = _os_.unmarshal_int();
					break;
					case ( 6144|  1):oldpropindex = _os_.unmarshal_short();
					break;
					case (26624|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
newprop.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case ( 2048|  3):needbind = _os_.unmarshal_boolean();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.AccessoryWashResult copy() {
			return new Data(this);
		}

		@Override
		public xbean.AccessoryWashResult toData() {
			return new Data(this);
		}

		public xbean.AccessoryWashResult toBean() {
			return new AccessoryWashResult(this, null, null);
		}

		@Override
		public xbean.AccessoryWashResult toDataIf() {
			return this;
		}

		public xbean.AccessoryWashResult toBeanIf() {
			return new AccessoryWashResult(this, null, null);
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
		public int getOldpropindex() { // 旧的key
			return oldpropindex;
		}

		@Override
		public xbean.AccessoryProp getNewprop() { // 新属性key
			return newprop;
		}

		@Override
		public boolean getNeedbind() { // 需要设置成绑定
			return needbind;
		}

		@Override
		public void setOldpropindex(int _v_) { // 旧的key
			oldpropindex = _v_;
		}

		@Override
		public void setNeedbind(boolean _v_) { // 需要设置成绑定
			needbind = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof AccessoryWashResult.Data)) return false;
			AccessoryWashResult.Data _o_ = (AccessoryWashResult.Data) _o1_;
			if (oldpropindex != _o_.oldpropindex) return false;
			if (!newprop.equals(_o_.newprop)) return false;
			if (needbind != _o_.needbind) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += oldpropindex;
			_h_ += newprop.hashCode();
			_h_ += needbind ? 1231 : 1237;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(oldpropindex);
			_sb_.append(",");
			_sb_.append(newprop);
			_sb_.append(",");
			_sb_.append(needbind);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
