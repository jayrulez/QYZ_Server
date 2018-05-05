
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class GMSenseword extends xdb.XBean implements xbean.GMSenseword {
	private xdb.util.SetX<String> addwords; // 
	private xdb.util.SetX<String> removewords; // 

	@Override
	public void _reset_unsafe_() {
		addwords.clear();
		removewords.clear();
	}

	GMSenseword(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		addwords = new xdb.util.SetX<String>();
		removewords = new xdb.util.SetX<String>();
	}

	public GMSenseword() {
		this(0, null, null);
	}

	public GMSenseword(GMSenseword _o_) {
		this(_o_, null, null);
	}

	GMSenseword(xbean.GMSenseword _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof GMSenseword) assign((GMSenseword)_o1_);
		else if (_o1_ instanceof GMSenseword.Data) assign((GMSenseword.Data)_o1_);
		else if (_o1_ instanceof GMSenseword.Const) assign(((GMSenseword.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(GMSenseword _o_) {
		_o_._xdb_verify_unsafe_();
		addwords = new xdb.util.SetX<String>();
		addwords.addAll(_o_.addwords);
		removewords = new xdb.util.SetX<String>();
		removewords.addAll(_o_.removewords);
	}

	private void assign(GMSenseword.Data _o_) {
		addwords = new xdb.util.SetX<String>();
		addwords.addAll(_o_.addwords);
		removewords = new xdb.util.SetX<String>();
		removewords.addAll(_o_.removewords);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(addwords.size());
for (String _v_ : addwords) {
	_os_.marshal(_v_, xdb.Const.IO_CHARSET);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(20480|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(removewords.size());
for (String _v_ : removewords) {
	_os_.marshal(_v_, xdb.Const.IO_CHARSET);
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
	String _v_ = "";
	_v_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
	addwords.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (20480|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	String _v_ = "";
	_v_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
	removewords.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.GMSenseword copy() {
		_xdb_verify_unsafe_();
		return new GMSenseword(this);
	}

	@Override
	public xbean.GMSenseword toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GMSenseword toBean() {
		_xdb_verify_unsafe_();
		return new GMSenseword(this); // same as copy()
	}

	@Override
	public xbean.GMSenseword toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GMSenseword toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Set<String> getAddwords() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "addwords"), addwords);
	}

	public java.util.Set<String> getAddwordsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Set<String> addwords;
		GMSenseword _o_ = this;
		addwords = new xdb.util.SetX<String>();
		addwords.addAll(_o_.addwords);
		return addwords;
	}

	@Override
	public java.util.Set<String> getRemovewords() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "removewords"), removewords);
	}

	public java.util.Set<String> getRemovewordsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Set<String> removewords;
		GMSenseword _o_ = this;
		removewords = new xdb.util.SetX<String>();
		removewords.addAll(_o_.removewords);
		return removewords;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		GMSenseword _o_ = null;
		if ( _o1_ instanceof GMSenseword ) _o_ = (GMSenseword)_o1_;
		else if ( _o1_ instanceof GMSenseword.Const ) _o_ = ((GMSenseword.Const)_o1_).nThis();
		else return false;
		if (!addwords.equals(_o_.addwords)) return false;
		if (!removewords.equals(_o_.removewords)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += addwords.hashCode();
		_h_ += removewords.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(addwords);
		_sb_.append(",");
		_sb_.append(removewords);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableSet().setVarName("addwords"));
		lb.add(new xdb.logs.ListenableSet().setVarName("removewords"));
		return lb;
	}

	private class Const implements xbean.GMSenseword {
		GMSenseword nThis() {
			return GMSenseword.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.GMSenseword copy() {
			return GMSenseword.this.copy();
		}

		@Override
		public xbean.GMSenseword toData() {
			return GMSenseword.this.toData();
		}

		public xbean.GMSenseword toBean() {
			return GMSenseword.this.toBean();
		}

		@Override
		public xbean.GMSenseword toDataIf() {
			return GMSenseword.this.toDataIf();
		}

		public xbean.GMSenseword toBeanIf() {
			return GMSenseword.this.toBeanIf();
		}

		@Override
		public java.util.Set<String> getAddwords() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(addwords);
		}

		public java.util.Set<String> getAddwordsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Set<String> addwords;
			GMSenseword _o_ = GMSenseword.this;
		addwords = new xdb.util.SetX<String>();
		addwords.addAll(_o_.addwords);
			return addwords;
		}

		@Override
		public java.util.Set<String> getRemovewords() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(removewords);
		}

		public java.util.Set<String> getRemovewordsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Set<String> removewords;
			GMSenseword _o_ = GMSenseword.this;
		removewords = new xdb.util.SetX<String>();
		removewords.addAll(_o_.removewords);
			return removewords;
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
			return GMSenseword.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return GMSenseword.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return GMSenseword.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return GMSenseword.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return GMSenseword.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return GMSenseword.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return GMSenseword.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return GMSenseword.this.hashCode();
		}

		@Override
		public String toString() {
			return GMSenseword.this.toString();
		}

	}

	public static final class Data implements xbean.GMSenseword {
		private java.util.HashSet<String> addwords; // 
		private java.util.HashSet<String> removewords; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			addwords = new java.util.HashSet<String>();
			removewords = new java.util.HashSet<String>();
		}

		Data(xbean.GMSenseword _o1_) {
			if (_o1_ instanceof GMSenseword) assign((GMSenseword)_o1_);
			else if (_o1_ instanceof GMSenseword.Data) assign((GMSenseword.Data)_o1_);
			else if (_o1_ instanceof GMSenseword.Const) assign(((GMSenseword.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(GMSenseword _o_) {
			addwords = new java.util.HashSet<String>();
			addwords.addAll(_o_.addwords);
			removewords = new java.util.HashSet<String>();
			removewords.addAll(_o_.removewords);
		}

		private void assign(GMSenseword.Data _o_) {
			addwords = new java.util.HashSet<String>();
			addwords.addAll(_o_.addwords);
			removewords = new java.util.HashSet<String>();
			removewords.addAll(_o_.removewords);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(addwords.size());
for (String _v_ : addwords) {
	_os_.marshal(_v_, xdb.Const.IO_CHARSET);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(20480|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(removewords.size());
for (String _v_ : removewords) {
	_os_.marshal(_v_, xdb.Const.IO_CHARSET);
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
	String _v_ = "";
	_v_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
	addwords.add(_v_);
}
_os_ = _temp_;}
					break;
					case (20480|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	String _v_ = "";
	_v_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
	removewords.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.GMSenseword copy() {
			return new Data(this);
		}

		@Override
		public xbean.GMSenseword toData() {
			return new Data(this);
		}

		public xbean.GMSenseword toBean() {
			return new GMSenseword(this, null, null);
		}

		@Override
		public xbean.GMSenseword toDataIf() {
			return this;
		}

		public xbean.GMSenseword toBeanIf() {
			return new GMSenseword(this, null, null);
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
		public java.util.Set<String> getAddwords() { // 
			return addwords;
		}

		@Override
		public java.util.Set<String> getAddwordsAsData() { // 
			return addwords;
		}

		@Override
		public java.util.Set<String> getRemovewords() { // 
			return removewords;
		}

		@Override
		public java.util.Set<String> getRemovewordsAsData() { // 
			return removewords;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof GMSenseword.Data)) return false;
			GMSenseword.Data _o_ = (GMSenseword.Data) _o1_;
			if (!addwords.equals(_o_.addwords)) return false;
			if (!removewords.equals(_o_.removewords)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += addwords.hashCode();
			_h_ += removewords.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(addwords);
			_sb_.append(",");
			_sb_.append(removewords);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
