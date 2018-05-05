
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class AppOrder extends xdb.XBean implements xbean.AppOrder {
	private long roleid; // 
	private int productid; // 
	private long time; // 

	@Override
	public void _reset_unsafe_() {
		roleid = 0L;
		productid = 0;
		time = 0L;
	}

	AppOrder(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public AppOrder() {
		this(0, null, null);
	}

	public AppOrder(AppOrder _o_) {
		this(_o_, null, null);
	}

	AppOrder(xbean.AppOrder _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof AppOrder) assign((AppOrder)_o1_);
		else if (_o1_ instanceof AppOrder.Data) assign((AppOrder.Data)_o1_);
		else if (_o1_ instanceof AppOrder.Const) assign(((AppOrder.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(AppOrder _o_) {
		_o_._xdb_verify_unsafe_();
		roleid = _o_.roleid;
		productid = _o_.productid;
		time = _o_.time;
	}

	private void assign(AppOrder.Data _o_) {
		roleid = _o_.roleid;
		productid = _o_.productid;
		time = _o_.time;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)(10240|  1));_os_.marshal(roleid);
    _os_.marshal((short)( 8192|  2));_os_.marshal(productid);
    _os_.marshal((short)(10240|  3));_os_.marshal(time);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):roleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):roleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):roleid = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):productid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):productid = _os_.unmarshal_short();
    				break;
    				case (10240|  3):time = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):time = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):time = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.AppOrder copy() {
		_xdb_verify_unsafe_();
		return new AppOrder(this);
	}

	@Override
	public xbean.AppOrder toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AppOrder toBean() {
		_xdb_verify_unsafe_();
		return new AppOrder(this); // same as copy()
	}

	@Override
	public xbean.AppOrder toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AppOrder toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getRoleid() { // 
		_xdb_verify_unsafe_();
		return roleid;
	}

	@Override
	public int getProductid() { // 
		_xdb_verify_unsafe_();
		return productid;
	}

	@Override
	public long getTime() { // 
		_xdb_verify_unsafe_();
		return time;
	}

	@Override
	public void setRoleid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleid) {
					public void rollback() { roleid = _xdb_saved; }
				};}});
		roleid = _v_;
	}

	@Override
	public void setProductid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "productid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, productid) {
					public void rollback() { productid = _xdb_saved; }
				};}});
		productid = _v_;
	}

	@Override
	public void setTime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "time") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, time) {
					public void rollback() { time = _xdb_saved; }
				};}});
		time = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		AppOrder _o_ = null;
		if ( _o1_ instanceof AppOrder ) _o_ = (AppOrder)_o1_;
		else if ( _o1_ instanceof AppOrder.Const ) _o_ = ((AppOrder.Const)_o1_).nThis();
		else return false;
		if (roleid != _o_.roleid) return false;
		if (productid != _o_.productid) return false;
		if (time != _o_.time) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += roleid;
		_h_ += productid;
		_h_ += time;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid);
		_sb_.append(",");
		_sb_.append(productid);
		_sb_.append(",");
		_sb_.append(time);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("productid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("time"));
		return lb;
	}

	private class Const implements xbean.AppOrder {
		AppOrder nThis() {
			return AppOrder.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.AppOrder copy() {
			return AppOrder.this.copy();
		}

		@Override
		public xbean.AppOrder toData() {
			return AppOrder.this.toData();
		}

		public xbean.AppOrder toBean() {
			return AppOrder.this.toBean();
		}

		@Override
		public xbean.AppOrder toDataIf() {
			return AppOrder.this.toDataIf();
		}

		public xbean.AppOrder toBeanIf() {
			return AppOrder.this.toBeanIf();
		}

		@Override
		public long getRoleid() { // 
			_xdb_verify_unsafe_();
			return roleid;
		}

		@Override
		public int getProductid() { // 
			_xdb_verify_unsafe_();
			return productid;
		}

		@Override
		public long getTime() { // 
			_xdb_verify_unsafe_();
			return time;
		}

		@Override
		public void setRoleid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setProductid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTime(long _v_) { // 
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
			return AppOrder.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return AppOrder.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return AppOrder.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return AppOrder.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return AppOrder.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return AppOrder.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return AppOrder.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return AppOrder.this.hashCode();
		}

		@Override
		public String toString() {
			return AppOrder.this.toString();
		}

	}

	public static final class Data implements xbean.AppOrder {
		private long roleid; // 
		private int productid; // 
		private long time; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.AppOrder _o1_) {
			if (_o1_ instanceof AppOrder) assign((AppOrder)_o1_);
			else if (_o1_ instanceof AppOrder.Data) assign((AppOrder.Data)_o1_);
			else if (_o1_ instanceof AppOrder.Const) assign(((AppOrder.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(AppOrder _o_) {
			roleid = _o_.roleid;
			productid = _o_.productid;
			time = _o_.time;
		}

		private void assign(AppOrder.Data _o_) {
			roleid = _o_.roleid;
			productid = _o_.productid;
			time = _o_.time;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)(10240|  1));_os_.marshal(roleid);
	_os_.marshal((short)( 8192|  2));_os_.marshal(productid);
	_os_.marshal((short)(10240|  3));_os_.marshal(time);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):roleid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):roleid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):roleid = _os_.unmarshal_int();
					break;
					case ( 8192|  2):productid = _os_.unmarshal_int();
					break;
					case ( 6144|  2):productid = _os_.unmarshal_short();
					break;
					case (10240|  3):time = _os_.unmarshal_long();
					break;
					case ( 6144|  3):time = _os_.unmarshal_short();
					break;
					case ( 8192|  3):time = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.AppOrder copy() {
			return new Data(this);
		}

		@Override
		public xbean.AppOrder toData() {
			return new Data(this);
		}

		public xbean.AppOrder toBean() {
			return new AppOrder(this, null, null);
		}

		@Override
		public xbean.AppOrder toDataIf() {
			return this;
		}

		public xbean.AppOrder toBeanIf() {
			return new AppOrder(this, null, null);
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
		public long getRoleid() { // 
			return roleid;
		}

		@Override
		public int getProductid() { // 
			return productid;
		}

		@Override
		public long getTime() { // 
			return time;
		}

		@Override
		public void setRoleid(long _v_) { // 
			roleid = _v_;
		}

		@Override
		public void setProductid(int _v_) { // 
			productid = _v_;
		}

		@Override
		public void setTime(long _v_) { // 
			time = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof AppOrder.Data)) return false;
			AppOrder.Data _o_ = (AppOrder.Data) _o1_;
			if (roleid != _o_.roleid) return false;
			if (productid != _o_.productid) return false;
			if (time != _o_.time) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += roleid;
			_h_ += productid;
			_h_ += time;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(roleid);
			_sb_.append(",");
			_sb_.append(productid);
			_sb_.append(",");
			_sb_.append(time);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
