
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class UserActivationCode extends xdb.XBean implements xbean.UserActivationCode {
	private java.util.HashMap<Integer, Long> all; // key:codetype

	@Override
	public void _reset_unsafe_() {
		all.clear();
	}

	UserActivationCode(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		all = new java.util.HashMap<Integer, Long>();
	}

	public UserActivationCode() {
		this(0, null, null);
	}

	public UserActivationCode(UserActivationCode _o_) {
		this(_o_, null, null);
	}

	UserActivationCode(xbean.UserActivationCode _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof UserActivationCode) assign((UserActivationCode)_o1_);
		else if (_o1_ instanceof UserActivationCode.Data) assign((UserActivationCode.Data)_o1_);
		else if (_o1_ instanceof UserActivationCode.Const) assign(((UserActivationCode.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(UserActivationCode _o_) {
		_o_._xdb_verify_unsafe_();
		all = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.all.entrySet())
			all.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(UserActivationCode.Data _o_) {
		all = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.all.entrySet())
			all.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(all.size());
for (java.util.Map.Entry<Integer, Long> _e_ : all.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
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
    				case (24576|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		all = new java.util.HashMap<Integer, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		all.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.UserActivationCode copy() {
		_xdb_verify_unsafe_();
		return new UserActivationCode(this);
	}

	@Override
	public xbean.UserActivationCode toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.UserActivationCode toBean() {
		_xdb_verify_unsafe_();
		return new UserActivationCode(this); // same as copy()
	}

	@Override
	public xbean.UserActivationCode toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.UserActivationCode toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, Long> getAll() { // key:codetype
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "all"), all);
	}

	@Override
	public java.util.Map<Integer, Long> getAllAsData() { // key:codetype
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Long> all;
		UserActivationCode _o_ = this;
		all = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.all.entrySet())
			all.put(_e_.getKey(), _e_.getValue());
		return all;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		UserActivationCode _o_ = null;
		if ( _o1_ instanceof UserActivationCode ) _o_ = (UserActivationCode)_o1_;
		else if ( _o1_ instanceof UserActivationCode.Const ) _o_ = ((UserActivationCode.Const)_o1_).nThis();
		else return false;
		if (!all.equals(_o_.all)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += all.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(all);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("all"));
		return lb;
	}

	private class Const implements xbean.UserActivationCode {
		UserActivationCode nThis() {
			return UserActivationCode.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.UserActivationCode copy() {
			return UserActivationCode.this.copy();
		}

		@Override
		public xbean.UserActivationCode toData() {
			return UserActivationCode.this.toData();
		}

		public xbean.UserActivationCode toBean() {
			return UserActivationCode.this.toBean();
		}

		@Override
		public xbean.UserActivationCode toDataIf() {
			return UserActivationCode.this.toDataIf();
		}

		public xbean.UserActivationCode toBeanIf() {
			return UserActivationCode.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, Long> getAll() { // key:codetype
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(all);
		}

		@Override
		public java.util.Map<Integer, Long> getAllAsData() { // key:codetype
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Long> all;
			UserActivationCode _o_ = UserActivationCode.this;
			all = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.all.entrySet())
				all.put(_e_.getKey(), _e_.getValue());
			return all;
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
			return UserActivationCode.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return UserActivationCode.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return UserActivationCode.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return UserActivationCode.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return UserActivationCode.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return UserActivationCode.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return UserActivationCode.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return UserActivationCode.this.hashCode();
		}

		@Override
		public String toString() {
			return UserActivationCode.this.toString();
		}

	}

	public static final class Data implements xbean.UserActivationCode {
		private java.util.HashMap<Integer, Long> all; // key:codetype

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			all = new java.util.HashMap<Integer, Long>();
		}

		Data(xbean.UserActivationCode _o1_) {
			if (_o1_ instanceof UserActivationCode) assign((UserActivationCode)_o1_);
			else if (_o1_ instanceof UserActivationCode.Data) assign((UserActivationCode.Data)_o1_);
			else if (_o1_ instanceof UserActivationCode.Const) assign(((UserActivationCode.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(UserActivationCode _o_) {
			all = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.all.entrySet())
				all.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(UserActivationCode.Data _o_) {
			all = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.all.entrySet())
				all.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(all.size());
for (java.util.Map.Entry<Integer, Long> _e_ : all.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (24576|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		all = new java.util.HashMap<Integer, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		all.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.UserActivationCode copy() {
			return new Data(this);
		}

		@Override
		public xbean.UserActivationCode toData() {
			return new Data(this);
		}

		public xbean.UserActivationCode toBean() {
			return new UserActivationCode(this, null, null);
		}

		@Override
		public xbean.UserActivationCode toDataIf() {
			return this;
		}

		public xbean.UserActivationCode toBeanIf() {
			return new UserActivationCode(this, null, null);
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
		public java.util.Map<Integer, Long> getAll() { // key:codetype
			return all;
		}

		@Override
		public java.util.Map<Integer, Long> getAllAsData() { // key:codetype
			return all;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof UserActivationCode.Data)) return false;
			UserActivationCode.Data _o_ = (UserActivationCode.Data) _o1_;
			if (!all.equals(_o_.all)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += all.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(all);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
