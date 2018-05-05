
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleExchange extends xdb.XBean implements xbean.RoleExchange {
	private java.util.LinkedList<Long> items; // exchid
	private java.util.LinkedList<Long> logs; // logid

	@Override
	public void _reset_unsafe_() {
		items.clear();
		logs.clear();
	}

	RoleExchange(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		items = new java.util.LinkedList<Long>();
		logs = new java.util.LinkedList<Long>();
	}

	public RoleExchange() {
		this(0, null, null);
	}

	public RoleExchange(RoleExchange _o_) {
		this(_o_, null, null);
	}

	RoleExchange(xbean.RoleExchange _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleExchange) assign((RoleExchange)_o1_);
		else if (_o1_ instanceof RoleExchange.Data) assign((RoleExchange.Data)_o1_);
		else if (_o1_ instanceof RoleExchange.Const) assign(((RoleExchange.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleExchange _o_) {
		_o_._xdb_verify_unsafe_();
		items = new java.util.LinkedList<Long>();
		items.addAll(_o_.items);
		logs = new java.util.LinkedList<Long>();
		logs.addAll(_o_.logs);
	}

	private void assign(RoleExchange.Data _o_) {
		items = new java.util.LinkedList<Long>();
		items.addAll(_o_.items);
		logs = new java.util.LinkedList<Long>();
		logs.addAll(_o_.logs);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(22528|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (Long _v_ : items) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(logs.size());
for (Long _v_ : logs) {
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
    				case (22528|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	items.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	logs.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleExchange copy() {
		_xdb_verify_unsafe_();
		return new RoleExchange(this);
	}

	@Override
	public xbean.RoleExchange toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleExchange toBean() {
		_xdb_verify_unsafe_();
		return new RoleExchange(this); // same as copy()
	}

	@Override
	public xbean.RoleExchange toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleExchange toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<Long> getItems() { // exchid
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "items"), items);
	}

	public java.util.List<Long> getItemsAsData() { // exchid
		_xdb_verify_unsafe_();
		java.util.List<Long> items;
		RoleExchange _o_ = this;
		items = new java.util.LinkedList<Long>();
		items.addAll(_o_.items);
		return items;
	}

	@Override
	public java.util.List<Long> getLogs() { // logid
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "logs"), logs);
	}

	public java.util.List<Long> getLogsAsData() { // logid
		_xdb_verify_unsafe_();
		java.util.List<Long> logs;
		RoleExchange _o_ = this;
		logs = new java.util.LinkedList<Long>();
		logs.addAll(_o_.logs);
		return logs;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleExchange _o_ = null;
		if ( _o1_ instanceof RoleExchange ) _o_ = (RoleExchange)_o1_;
		else if ( _o1_ instanceof RoleExchange.Const ) _o_ = ((RoleExchange.Const)_o1_).nThis();
		else return false;
		if (!items.equals(_o_.items)) return false;
		if (!logs.equals(_o_.logs)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += items.hashCode();
		_h_ += logs.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(items);
		_sb_.append(",");
		_sb_.append(logs);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("items"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("logs"));
		return lb;
	}

	private class Const implements xbean.RoleExchange {
		RoleExchange nThis() {
			return RoleExchange.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleExchange copy() {
			return RoleExchange.this.copy();
		}

		@Override
		public xbean.RoleExchange toData() {
			return RoleExchange.this.toData();
		}

		public xbean.RoleExchange toBean() {
			return RoleExchange.this.toBean();
		}

		@Override
		public xbean.RoleExchange toDataIf() {
			return RoleExchange.this.toDataIf();
		}

		public xbean.RoleExchange toBeanIf() {
			return RoleExchange.this.toBeanIf();
		}

		@Override
		public java.util.List<Long> getItems() { // exchid
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(items);
		}

		public java.util.List<Long> getItemsAsData() { // exchid
			_xdb_verify_unsafe_();
			java.util.List<Long> items;
			RoleExchange _o_ = RoleExchange.this;
		items = new java.util.LinkedList<Long>();
		items.addAll(_o_.items);
			return items;
		}

		@Override
		public java.util.List<Long> getLogs() { // logid
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(logs);
		}

		public java.util.List<Long> getLogsAsData() { // logid
			_xdb_verify_unsafe_();
			java.util.List<Long> logs;
			RoleExchange _o_ = RoleExchange.this;
		logs = new java.util.LinkedList<Long>();
		logs.addAll(_o_.logs);
			return logs;
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
			return RoleExchange.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleExchange.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleExchange.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleExchange.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleExchange.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleExchange.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleExchange.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleExchange.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleExchange.this.toString();
		}

	}

	public static final class Data implements xbean.RoleExchange {
		private java.util.LinkedList<Long> items; // exchid
		private java.util.LinkedList<Long> logs; // logid

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			items = new java.util.LinkedList<Long>();
			logs = new java.util.LinkedList<Long>();
		}

		Data(xbean.RoleExchange _o1_) {
			if (_o1_ instanceof RoleExchange) assign((RoleExchange)_o1_);
			else if (_o1_ instanceof RoleExchange.Data) assign((RoleExchange.Data)_o1_);
			else if (_o1_ instanceof RoleExchange.Const) assign(((RoleExchange.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleExchange _o_) {
			items = new java.util.LinkedList<Long>();
			items.addAll(_o_.items);
			logs = new java.util.LinkedList<Long>();
			logs.addAll(_o_.logs);
		}

		private void assign(RoleExchange.Data _o_) {
			items = new java.util.LinkedList<Long>();
			items.addAll(_o_.items);
			logs = new java.util.LinkedList<Long>();
			logs.addAll(_o_.logs);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(22528|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(items.size());
for (Long _v_ : items) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(logs.size());
for (Long _v_ : logs) {
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
					case (22528|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	items.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	logs.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleExchange copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleExchange toData() {
			return new Data(this);
		}

		public xbean.RoleExchange toBean() {
			return new RoleExchange(this, null, null);
		}

		@Override
		public xbean.RoleExchange toDataIf() {
			return this;
		}

		public xbean.RoleExchange toBeanIf() {
			return new RoleExchange(this, null, null);
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
		public java.util.List<Long> getItems() { // exchid
			return items;
		}

		@Override
		public java.util.List<Long> getItemsAsData() { // exchid
			return items;
		}

		@Override
		public java.util.List<Long> getLogs() { // logid
			return logs;
		}

		@Override
		public java.util.List<Long> getLogsAsData() { // logid
			return logs;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleExchange.Data)) return false;
			RoleExchange.Data _o_ = (RoleExchange.Data) _o1_;
			if (!items.equals(_o_.items)) return false;
			if (!logs.equals(_o_.logs)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += items.hashCode();
			_h_ += logs.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(items);
			_sb_.append(",");
			_sb_.append(logs);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
