
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ExchangeLog extends xdb.XBean implements xbean.ExchangeLog {
	private long seller; // 
	private long buyer; // 
	private xbean.ExchangeItem item; // 
	private long time; // 

	@Override
	public void _reset_unsafe_() {
		seller = 0L;
		buyer = 0L;
		item._reset_unsafe_();
		time = 0L;
	}

	ExchangeLog(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		item = new ExchangeItem(0, this, "item");
	}

	public ExchangeLog() {
		this(0, null, null);
	}

	public ExchangeLog(ExchangeLog _o_) {
		this(_o_, null, null);
	}

	ExchangeLog(xbean.ExchangeLog _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ExchangeLog) assign((ExchangeLog)_o1_);
		else if (_o1_ instanceof ExchangeLog.Data) assign((ExchangeLog.Data)_o1_);
		else if (_o1_ instanceof ExchangeLog.Const) assign(((ExchangeLog.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ExchangeLog _o_) {
		_o_._xdb_verify_unsafe_();
		seller = _o_.seller;
		buyer = _o_.buyer;
		item = new ExchangeItem(_o_.item, this, "item");
		time = _o_.time;
	}

	private void assign(ExchangeLog.Data _o_) {
		seller = _o_.seller;
		buyer = _o_.buyer;
		item = new ExchangeItem(_o_.item, this, "item");
		time = _o_.time;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(10240|  4));_os_.marshal(seller);
    _os_.marshal((short)(10240|  1));_os_.marshal(buyer);
    _os_.marshal((short)(26624|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
item.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  3));_os_.marshal(time);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  4):seller = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):seller = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):seller = _os_.unmarshal_int();
    				break;
    				case (10240|  1):buyer = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):buyer = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):buyer = _os_.unmarshal_int();
    				break;
    				case (26624|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
item.unmarshal(_os_);
_os_ = _temp_;}
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
	public xbean.ExchangeLog copy() {
		_xdb_verify_unsafe_();
		return new ExchangeLog(this);
	}

	@Override
	public xbean.ExchangeLog toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ExchangeLog toBean() {
		_xdb_verify_unsafe_();
		return new ExchangeLog(this); // same as copy()
	}

	@Override
	public xbean.ExchangeLog toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ExchangeLog toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getSeller() { // 
		_xdb_verify_unsafe_();
		return seller;
	}

	@Override
	public long getBuyer() { // 
		_xdb_verify_unsafe_();
		return buyer;
	}

	@Override
	public xbean.ExchangeItem getItem() { // 
		_xdb_verify_unsafe_();
		return item;
	}

	@Override
	public long getTime() { // 
		_xdb_verify_unsafe_();
		return time;
	}

	@Override
	public void setSeller(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "seller") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, seller) {
					public void rollback() { seller = _xdb_saved; }
				};}});
		seller = _v_;
	}

	@Override
	public void setBuyer(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "buyer") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, buyer) {
					public void rollback() { buyer = _xdb_saved; }
				};}});
		buyer = _v_;
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
		ExchangeLog _o_ = null;
		if ( _o1_ instanceof ExchangeLog ) _o_ = (ExchangeLog)_o1_;
		else if ( _o1_ instanceof ExchangeLog.Const ) _o_ = ((ExchangeLog.Const)_o1_).nThis();
		else return false;
		if (seller != _o_.seller) return false;
		if (buyer != _o_.buyer) return false;
		if (!item.equals(_o_.item)) return false;
		if (time != _o_.time) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += seller;
		_h_ += buyer;
		_h_ += item.hashCode();
		_h_ += time;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(seller);
		_sb_.append(",");
		_sb_.append(buyer);
		_sb_.append(",");
		_sb_.append(item);
		_sb_.append(",");
		_sb_.append(time);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("seller"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("buyer"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("item"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("time"));
		return lb;
	}

	private class Const implements xbean.ExchangeLog {
		ExchangeLog nThis() {
			return ExchangeLog.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ExchangeLog copy() {
			return ExchangeLog.this.copy();
		}

		@Override
		public xbean.ExchangeLog toData() {
			return ExchangeLog.this.toData();
		}

		public xbean.ExchangeLog toBean() {
			return ExchangeLog.this.toBean();
		}

		@Override
		public xbean.ExchangeLog toDataIf() {
			return ExchangeLog.this.toDataIf();
		}

		public xbean.ExchangeLog toBeanIf() {
			return ExchangeLog.this.toBeanIf();
		}

		@Override
		public long getSeller() { // 
			_xdb_verify_unsafe_();
			return seller;
		}

		@Override
		public long getBuyer() { // 
			_xdb_verify_unsafe_();
			return buyer;
		}

		@Override
		public xbean.ExchangeItem getItem() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(item);
		}

		@Override
		public long getTime() { // 
			_xdb_verify_unsafe_();
			return time;
		}

		@Override
		public void setSeller(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBuyer(long _v_) { // 
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
			return ExchangeLog.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ExchangeLog.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ExchangeLog.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ExchangeLog.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ExchangeLog.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ExchangeLog.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ExchangeLog.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ExchangeLog.this.hashCode();
		}

		@Override
		public String toString() {
			return ExchangeLog.this.toString();
		}

	}

	public static final class Data implements xbean.ExchangeLog {
		private long seller; // 
		private long buyer; // 
		private xbean.ExchangeItem item; // 
		private long time; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			item = new ExchangeItem.Data();
		}

		Data(xbean.ExchangeLog _o1_) {
			if (_o1_ instanceof ExchangeLog) assign((ExchangeLog)_o1_);
			else if (_o1_ instanceof ExchangeLog.Data) assign((ExchangeLog.Data)_o1_);
			else if (_o1_ instanceof ExchangeLog.Const) assign(((ExchangeLog.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ExchangeLog _o_) {
			seller = _o_.seller;
			buyer = _o_.buyer;
			item = new ExchangeItem.Data(_o_.item);
			time = _o_.time;
		}

		private void assign(ExchangeLog.Data _o_) {
			seller = _o_.seller;
			buyer = _o_.buyer;
			item = new ExchangeItem.Data(_o_.item);
			time = _o_.time;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(10240|  4));_os_.marshal(seller);
	_os_.marshal((short)(10240|  1));_os_.marshal(buyer);
	_os_.marshal((short)(26624|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
item.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  3));_os_.marshal(time);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  4):seller = _os_.unmarshal_long();
					break;
					case ( 6144|  4):seller = _os_.unmarshal_short();
					break;
					case ( 8192|  4):seller = _os_.unmarshal_int();
					break;
					case (10240|  1):buyer = _os_.unmarshal_long();
					break;
					case ( 6144|  1):buyer = _os_.unmarshal_short();
					break;
					case ( 8192|  1):buyer = _os_.unmarshal_int();
					break;
					case (26624|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
item.unmarshal(_os_);
_os_ = _temp_;}
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
		public xbean.ExchangeLog copy() {
			return new Data(this);
		}

		@Override
		public xbean.ExchangeLog toData() {
			return new Data(this);
		}

		public xbean.ExchangeLog toBean() {
			return new ExchangeLog(this, null, null);
		}

		@Override
		public xbean.ExchangeLog toDataIf() {
			return this;
		}

		public xbean.ExchangeLog toBeanIf() {
			return new ExchangeLog(this, null, null);
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
		public long getSeller() { // 
			return seller;
		}

		@Override
		public long getBuyer() { // 
			return buyer;
		}

		@Override
		public xbean.ExchangeItem getItem() { // 
			return item;
		}

		@Override
		public long getTime() { // 
			return time;
		}

		@Override
		public void setSeller(long _v_) { // 
			seller = _v_;
		}

		@Override
		public void setBuyer(long _v_) { // 
			buyer = _v_;
		}

		@Override
		public void setTime(long _v_) { // 
			time = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ExchangeLog.Data)) return false;
			ExchangeLog.Data _o_ = (ExchangeLog.Data) _o1_;
			if (seller != _o_.seller) return false;
			if (buyer != _o_.buyer) return false;
			if (!item.equals(_o_.item)) return false;
			if (time != _o_.time) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += seller;
			_h_ += buyer;
			_h_ += item.hashCode();
			_h_ += time;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(seller);
			_sb_.append(",");
			_sb_.append(buyer);
			_sb_.append(",");
			_sb_.append(item);
			_sb_.append(",");
			_sb_.append(time);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
