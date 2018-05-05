
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ActivationCodeSet extends xdb.XBean implements xbean.ActivationCodeSet {
	private int type; // 
	private xdb.util.SetX<Long> values; // 
	private long createtime; // 
	private long opentime; // 
	private long expiratetime; // 
	private xdb.util.SetX<Integer> platformset; // 可以激活的平台，空表示无平台限制
	private int isshared; // 是否是共享的礼包码
	private boolean islogin; // 是否用于激活码登陆

	@Override
	public void _reset_unsafe_() {
		type = 0;
		values.clear();
		createtime = 0L;
		opentime = 0L;
		expiratetime = 0L;
		platformset.clear();
		isshared = 0;
		islogin = false;
	}

	ActivationCodeSet(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		values = new xdb.util.SetX<Long>();
		platformset = new xdb.util.SetX<Integer>();
	}

	public ActivationCodeSet() {
		this(0, null, null);
	}

	public ActivationCodeSet(ActivationCodeSet _o_) {
		this(_o_, null, null);
	}

	ActivationCodeSet(xbean.ActivationCodeSet _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ActivationCodeSet) assign((ActivationCodeSet)_o1_);
		else if (_o1_ instanceof ActivationCodeSet.Data) assign((ActivationCodeSet.Data)_o1_);
		else if (_o1_ instanceof ActivationCodeSet.Const) assign(((ActivationCodeSet.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ActivationCodeSet _o_) {
		_o_._xdb_verify_unsafe_();
		type = _o_.type;
		values = new xdb.util.SetX<Long>();
		values.addAll(_o_.values);
		createtime = _o_.createtime;
		opentime = _o_.opentime;
		expiratetime = _o_.expiratetime;
		platformset = new xdb.util.SetX<Integer>();
		platformset.addAll(_o_.platformset);
		isshared = _o_.isshared;
		islogin = _o_.islogin;
	}

	private void assign(ActivationCodeSet.Data _o_) {
		type = _o_.type;
		values = new xdb.util.SetX<Long>();
		values.addAll(_o_.values);
		createtime = _o_.createtime;
		opentime = _o_.opentime;
		expiratetime = _o_.expiratetime;
		platformset = new xdb.util.SetX<Integer>();
		platformset.addAll(_o_.platformset);
		isshared = _o_.isshared;
		islogin = _o_.islogin;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)8);
    _os_.marshal((short)( 8192|  0));_os_.marshal(type);
    _os_.marshal((short)(20480|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(values.size());
for (Long _v_ : values) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  3));_os_.marshal(createtime);
    _os_.marshal((short)(10240|  4));_os_.marshal(opentime);
    _os_.marshal((short)(10240|  5));_os_.marshal(expiratetime);
    _os_.marshal((short)(20480|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(platformset.size());
for (Integer _v_ : platformset) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  7));_os_.marshal(isshared);
    _os_.marshal((short)( 2048|  8));_os_.marshal(islogin);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  0):type = _os_.unmarshal_int();
    				break;
    				case ( 6144|  0):type = _os_.unmarshal_short();
    				break;
    				case (20480|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	values.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (10240|  3):createtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):createtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):createtime = _os_.unmarshal_int();
    				break;
    				case (10240|  4):opentime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):opentime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):opentime = _os_.unmarshal_int();
    				break;
    				case (10240|  5):expiratetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):expiratetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):expiratetime = _os_.unmarshal_int();
    				break;
    				case (20480|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	platformset.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  7):isshared = _os_.unmarshal_int();
    				break;
    				case ( 6144|  7):isshared = _os_.unmarshal_short();
    				break;
    				case ( 2048|  8):islogin = _os_.unmarshal_boolean();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ActivationCodeSet copy() {
		_xdb_verify_unsafe_();
		return new ActivationCodeSet(this);
	}

	@Override
	public xbean.ActivationCodeSet toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ActivationCodeSet toBean() {
		_xdb_verify_unsafe_();
		return new ActivationCodeSet(this); // same as copy()
	}

	@Override
	public xbean.ActivationCodeSet toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ActivationCodeSet toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getType() { // 
		_xdb_verify_unsafe_();
		return type;
	}

	@Override
	public java.util.Set<Long> getValues() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "values"), values);
	}

	public java.util.Set<Long> getValuesAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Set<Long> values;
		ActivationCodeSet _o_ = this;
		values = new xdb.util.SetX<Long>();
		values.addAll(_o_.values);
		return values;
	}

	@Override
	public long getCreatetime() { // 
		_xdb_verify_unsafe_();
		return createtime;
	}

	@Override
	public long getOpentime() { // 
		_xdb_verify_unsafe_();
		return opentime;
	}

	@Override
	public long getExpiratetime() { // 
		_xdb_verify_unsafe_();
		return expiratetime;
	}

	@Override
	public java.util.Set<Integer> getPlatformset() { // 可以激活的平台，空表示无平台限制
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "platformset"), platformset);
	}

	public java.util.Set<Integer> getPlatformsetAsData() { // 可以激活的平台，空表示无平台限制
		_xdb_verify_unsafe_();
		java.util.Set<Integer> platformset;
		ActivationCodeSet _o_ = this;
		platformset = new xdb.util.SetX<Integer>();
		platformset.addAll(_o_.platformset);
		return platformset;
	}

	@Override
	public int getIsshared() { // 是否是共享的礼包码
		_xdb_verify_unsafe_();
		return isshared;
	}

	@Override
	public boolean getIslogin() { // 是否用于激活码登陆
		_xdb_verify_unsafe_();
		return islogin;
	}

	@Override
	public void setType(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "type") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, type) {
					public void rollback() { type = _xdb_saved; }
				};}});
		type = _v_;
	}

	@Override
	public void setCreatetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "createtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, createtime) {
					public void rollback() { createtime = _xdb_saved; }
				};}});
		createtime = _v_;
	}

	@Override
	public void setOpentime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "opentime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, opentime) {
					public void rollback() { opentime = _xdb_saved; }
				};}});
		opentime = _v_;
	}

	@Override
	public void setExpiratetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "expiratetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, expiratetime) {
					public void rollback() { expiratetime = _xdb_saved; }
				};}});
		expiratetime = _v_;
	}

	@Override
	public void setIsshared(int _v_) { // 是否是共享的礼包码
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "isshared") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, isshared) {
					public void rollback() { isshared = _xdb_saved; }
				};}});
		isshared = _v_;
	}

	@Override
	public void setIslogin(boolean _v_) { // 是否用于激活码登陆
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "islogin") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, islogin) {
					public void rollback() { islogin = _xdb_saved; }
				};}});
		islogin = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ActivationCodeSet _o_ = null;
		if ( _o1_ instanceof ActivationCodeSet ) _o_ = (ActivationCodeSet)_o1_;
		else if ( _o1_ instanceof ActivationCodeSet.Const ) _o_ = ((ActivationCodeSet.Const)_o1_).nThis();
		else return false;
		if (type != _o_.type) return false;
		if (!values.equals(_o_.values)) return false;
		if (createtime != _o_.createtime) return false;
		if (opentime != _o_.opentime) return false;
		if (expiratetime != _o_.expiratetime) return false;
		if (!platformset.equals(_o_.platformset)) return false;
		if (isshared != _o_.isshared) return false;
		if (islogin != _o_.islogin) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += type;
		_h_ += values.hashCode();
		_h_ += createtime;
		_h_ += opentime;
		_h_ += expiratetime;
		_h_ += platformset.hashCode();
		_h_ += isshared;
		_h_ += islogin ? 1231 : 1237;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(type);
		_sb_.append(",");
		_sb_.append(values);
		_sb_.append(",");
		_sb_.append(createtime);
		_sb_.append(",");
		_sb_.append(opentime);
		_sb_.append(",");
		_sb_.append(expiratetime);
		_sb_.append(",");
		_sb_.append(platformset);
		_sb_.append(",");
		_sb_.append(isshared);
		_sb_.append(",");
		_sb_.append(islogin);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("type"));
		lb.add(new xdb.logs.ListenableSet().setVarName("values"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("createtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("opentime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("expiratetime"));
		lb.add(new xdb.logs.ListenableSet().setVarName("platformset"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isshared"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("islogin"));
		return lb;
	}

	private class Const implements xbean.ActivationCodeSet {
		ActivationCodeSet nThis() {
			return ActivationCodeSet.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ActivationCodeSet copy() {
			return ActivationCodeSet.this.copy();
		}

		@Override
		public xbean.ActivationCodeSet toData() {
			return ActivationCodeSet.this.toData();
		}

		public xbean.ActivationCodeSet toBean() {
			return ActivationCodeSet.this.toBean();
		}

		@Override
		public xbean.ActivationCodeSet toDataIf() {
			return ActivationCodeSet.this.toDataIf();
		}

		public xbean.ActivationCodeSet toBeanIf() {
			return ActivationCodeSet.this.toBeanIf();
		}

		@Override
		public int getType() { // 
			_xdb_verify_unsafe_();
			return type;
		}

		@Override
		public java.util.Set<Long> getValues() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(values);
		}

		public java.util.Set<Long> getValuesAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Set<Long> values;
			ActivationCodeSet _o_ = ActivationCodeSet.this;
		values = new xdb.util.SetX<Long>();
		values.addAll(_o_.values);
			return values;
		}

		@Override
		public long getCreatetime() { // 
			_xdb_verify_unsafe_();
			return createtime;
		}

		@Override
		public long getOpentime() { // 
			_xdb_verify_unsafe_();
			return opentime;
		}

		@Override
		public long getExpiratetime() { // 
			_xdb_verify_unsafe_();
			return expiratetime;
		}

		@Override
		public java.util.Set<Integer> getPlatformset() { // 可以激活的平台，空表示无平台限制
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(platformset);
		}

		public java.util.Set<Integer> getPlatformsetAsData() { // 可以激活的平台，空表示无平台限制
			_xdb_verify_unsafe_();
			java.util.Set<Integer> platformset;
			ActivationCodeSet _o_ = ActivationCodeSet.this;
		platformset = new xdb.util.SetX<Integer>();
		platformset.addAll(_o_.platformset);
			return platformset;
		}

		@Override
		public int getIsshared() { // 是否是共享的礼包码
			_xdb_verify_unsafe_();
			return isshared;
		}

		@Override
		public boolean getIslogin() { // 是否用于激活码登陆
			_xdb_verify_unsafe_();
			return islogin;
		}

		@Override
		public void setType(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCreatetime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setOpentime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExpiratetime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsshared(int _v_) { // 是否是共享的礼包码
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIslogin(boolean _v_) { // 是否用于激活码登陆
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
			return ActivationCodeSet.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ActivationCodeSet.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ActivationCodeSet.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ActivationCodeSet.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ActivationCodeSet.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ActivationCodeSet.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ActivationCodeSet.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ActivationCodeSet.this.hashCode();
		}

		@Override
		public String toString() {
			return ActivationCodeSet.this.toString();
		}

	}

	public static final class Data implements xbean.ActivationCodeSet {
		private int type; // 
		private java.util.HashSet<Long> values; // 
		private long createtime; // 
		private long opentime; // 
		private long expiratetime; // 
		private java.util.HashSet<Integer> platformset; // 可以激活的平台，空表示无平台限制
		private int isshared; // 是否是共享的礼包码
		private boolean islogin; // 是否用于激活码登陆

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			values = new java.util.HashSet<Long>();
			platformset = new java.util.HashSet<Integer>();
		}

		Data(xbean.ActivationCodeSet _o1_) {
			if (_o1_ instanceof ActivationCodeSet) assign((ActivationCodeSet)_o1_);
			else if (_o1_ instanceof ActivationCodeSet.Data) assign((ActivationCodeSet.Data)_o1_);
			else if (_o1_ instanceof ActivationCodeSet.Const) assign(((ActivationCodeSet.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ActivationCodeSet _o_) {
			type = _o_.type;
			values = new java.util.HashSet<Long>();
			values.addAll(_o_.values);
			createtime = _o_.createtime;
			opentime = _o_.opentime;
			expiratetime = _o_.expiratetime;
			platformset = new java.util.HashSet<Integer>();
			platformset.addAll(_o_.platformset);
			isshared = _o_.isshared;
			islogin = _o_.islogin;
		}

		private void assign(ActivationCodeSet.Data _o_) {
			type = _o_.type;
			values = new java.util.HashSet<Long>();
			values.addAll(_o_.values);
			createtime = _o_.createtime;
			opentime = _o_.opentime;
			expiratetime = _o_.expiratetime;
			platformset = new java.util.HashSet<Integer>();
			platformset.addAll(_o_.platformset);
			isshared = _o_.isshared;
			islogin = _o_.islogin;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)8);
	_os_.marshal((short)( 8192|  0));_os_.marshal(type);
	_os_.marshal((short)(20480|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(values.size());
for (Long _v_ : values) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  3));_os_.marshal(createtime);
	_os_.marshal((short)(10240|  4));_os_.marshal(opentime);
	_os_.marshal((short)(10240|  5));_os_.marshal(expiratetime);
	_os_.marshal((short)(20480|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(platformset.size());
for (Integer _v_ : platformset) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  7));_os_.marshal(isshared);
	_os_.marshal((short)( 2048|  8));_os_.marshal(islogin);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  0):type = _os_.unmarshal_int();
					break;
					case ( 6144|  0):type = _os_.unmarshal_short();
					break;
					case (20480|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	values.add(_v_);
}
_os_ = _temp_;}
					break;
					case (10240|  3):createtime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):createtime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):createtime = _os_.unmarshal_int();
					break;
					case (10240|  4):opentime = _os_.unmarshal_long();
					break;
					case ( 6144|  4):opentime = _os_.unmarshal_short();
					break;
					case ( 8192|  4):opentime = _os_.unmarshal_int();
					break;
					case (10240|  5):expiratetime = _os_.unmarshal_long();
					break;
					case ( 6144|  5):expiratetime = _os_.unmarshal_short();
					break;
					case ( 8192|  5):expiratetime = _os_.unmarshal_int();
					break;
					case (20480|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	platformset.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192|  7):isshared = _os_.unmarshal_int();
					break;
					case ( 6144|  7):isshared = _os_.unmarshal_short();
					break;
					case ( 2048|  8):islogin = _os_.unmarshal_boolean();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ActivationCodeSet copy() {
			return new Data(this);
		}

		@Override
		public xbean.ActivationCodeSet toData() {
			return new Data(this);
		}

		public xbean.ActivationCodeSet toBean() {
			return new ActivationCodeSet(this, null, null);
		}

		@Override
		public xbean.ActivationCodeSet toDataIf() {
			return this;
		}

		public xbean.ActivationCodeSet toBeanIf() {
			return new ActivationCodeSet(this, null, null);
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
		public int getType() { // 
			return type;
		}

		@Override
		public java.util.Set<Long> getValues() { // 
			return values;
		}

		@Override
		public java.util.Set<Long> getValuesAsData() { // 
			return values;
		}

		@Override
		public long getCreatetime() { // 
			return createtime;
		}

		@Override
		public long getOpentime() { // 
			return opentime;
		}

		@Override
		public long getExpiratetime() { // 
			return expiratetime;
		}

		@Override
		public java.util.Set<Integer> getPlatformset() { // 可以激活的平台，空表示无平台限制
			return platformset;
		}

		@Override
		public java.util.Set<Integer> getPlatformsetAsData() { // 可以激活的平台，空表示无平台限制
			return platformset;
		}

		@Override
		public int getIsshared() { // 是否是共享的礼包码
			return isshared;
		}

		@Override
		public boolean getIslogin() { // 是否用于激活码登陆
			return islogin;
		}

		@Override
		public void setType(int _v_) { // 
			type = _v_;
		}

		@Override
		public void setCreatetime(long _v_) { // 
			createtime = _v_;
		}

		@Override
		public void setOpentime(long _v_) { // 
			opentime = _v_;
		}

		@Override
		public void setExpiratetime(long _v_) { // 
			expiratetime = _v_;
		}

		@Override
		public void setIsshared(int _v_) { // 是否是共享的礼包码
			isshared = _v_;
		}

		@Override
		public void setIslogin(boolean _v_) { // 是否用于激活码登陆
			islogin = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ActivationCodeSet.Data)) return false;
			ActivationCodeSet.Data _o_ = (ActivationCodeSet.Data) _o1_;
			if (type != _o_.type) return false;
			if (!values.equals(_o_.values)) return false;
			if (createtime != _o_.createtime) return false;
			if (opentime != _o_.opentime) return false;
			if (expiratetime != _o_.expiratetime) return false;
			if (!platformset.equals(_o_.platformset)) return false;
			if (isshared != _o_.isshared) return false;
			if (islogin != _o_.islogin) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += type;
			_h_ += values.hashCode();
			_h_ += createtime;
			_h_ += opentime;
			_h_ += expiratetime;
			_h_ += platformset.hashCode();
			_h_ += isshared;
			_h_ += islogin ? 1231 : 1237;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(type);
			_sb_.append(",");
			_sb_.append(values);
			_sb_.append(",");
			_sb_.append(createtime);
			_sb_.append(",");
			_sb_.append(opentime);
			_sb_.append(",");
			_sb_.append(expiratetime);
			_sb_.append(",");
			_sb_.append(platformset);
			_sb_.append(",");
			_sb_.append(isshared);
			_sb_.append(",");
			_sb_.append(islogin);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
