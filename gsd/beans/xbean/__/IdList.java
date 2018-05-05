
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class IdList extends xdb.XBean implements xbean.IdList {
	private java.util.LinkedList<Long> lists; // 

	@Override
	public void _reset_unsafe_() {
		lists.clear();
	}

	IdList(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		lists = new java.util.LinkedList<Long>();
	}

	public IdList() {
		this(0, null, null);
	}

	public IdList(IdList _o_) {
		this(_o_, null, null);
	}

	IdList(xbean.IdList _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof IdList) assign((IdList)_o1_);
		else if (_o1_ instanceof IdList.Data) assign((IdList.Data)_o1_);
		else if (_o1_ instanceof IdList.Const) assign(((IdList.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(IdList _o_) {
		_o_._xdb_verify_unsafe_();
		lists = new java.util.LinkedList<Long>();
		lists.addAll(_o_.lists);
	}

	private void assign(IdList.Data _o_) {
		lists = new java.util.LinkedList<Long>();
		lists.addAll(_o_.lists);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(lists.size());
for (Long _v_ : lists) {
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
    				case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	lists.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.IdList copy() {
		_xdb_verify_unsafe_();
		return new IdList(this);
	}

	@Override
	public xbean.IdList toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IdList toBean() {
		_xdb_verify_unsafe_();
		return new IdList(this); // same as copy()
	}

	@Override
	public xbean.IdList toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IdList toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<Long> getLists() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "lists"), lists);
	}

	public java.util.List<Long> getListsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Long> lists;
		IdList _o_ = this;
		lists = new java.util.LinkedList<Long>();
		lists.addAll(_o_.lists);
		return lists;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		IdList _o_ = null;
		if ( _o1_ instanceof IdList ) _o_ = (IdList)_o1_;
		else if ( _o1_ instanceof IdList.Const ) _o_ = ((IdList.Const)_o1_).nThis();
		else return false;
		if (!lists.equals(_o_.lists)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += lists.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lists);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("lists"));
		return lb;
	}

	private class Const implements xbean.IdList {
		IdList nThis() {
			return IdList.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.IdList copy() {
			return IdList.this.copy();
		}

		@Override
		public xbean.IdList toData() {
			return IdList.this.toData();
		}

		public xbean.IdList toBean() {
			return IdList.this.toBean();
		}

		@Override
		public xbean.IdList toDataIf() {
			return IdList.this.toDataIf();
		}

		public xbean.IdList toBeanIf() {
			return IdList.this.toBeanIf();
		}

		@Override
		public java.util.List<Long> getLists() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(lists);
		}

		public java.util.List<Long> getListsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Long> lists;
			IdList _o_ = IdList.this;
		lists = new java.util.LinkedList<Long>();
		lists.addAll(_o_.lists);
			return lists;
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
			return IdList.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return IdList.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return IdList.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return IdList.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return IdList.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return IdList.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return IdList.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return IdList.this.hashCode();
		}

		@Override
		public String toString() {
			return IdList.this.toString();
		}

	}

	public static final class Data implements xbean.IdList {
		private java.util.LinkedList<Long> lists; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			lists = new java.util.LinkedList<Long>();
		}

		Data(xbean.IdList _o1_) {
			if (_o1_ instanceof IdList) assign((IdList)_o1_);
			else if (_o1_ instanceof IdList.Data) assign((IdList.Data)_o1_);
			else if (_o1_ instanceof IdList.Const) assign(((IdList.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(IdList _o_) {
			lists = new java.util.LinkedList<Long>();
			lists.addAll(_o_.lists);
		}

		private void assign(IdList.Data _o_) {
			lists = new java.util.LinkedList<Long>();
			lists.addAll(_o_.lists);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(lists.size());
for (Long _v_ : lists) {
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
					case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	lists.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.IdList copy() {
			return new Data(this);
		}

		@Override
		public xbean.IdList toData() {
			return new Data(this);
		}

		public xbean.IdList toBean() {
			return new IdList(this, null, null);
		}

		@Override
		public xbean.IdList toDataIf() {
			return this;
		}

		public xbean.IdList toBeanIf() {
			return new IdList(this, null, null);
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
		public java.util.List<Long> getLists() { // 
			return lists;
		}

		@Override
		public java.util.List<Long> getListsAsData() { // 
			return lists;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof IdList.Data)) return false;
			IdList.Data _o_ = (IdList.Data) _o1_;
			if (!lists.equals(_o_.lists)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += lists.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(lists);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
