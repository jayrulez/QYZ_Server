
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class GsdErrorOrderInfo extends xdb.XBean implements xbean.GsdErrorOrderInfo {
	private xbean.UncompletedOrderInfo order; // 
	private int reason; // 

	@Override
	public void _reset_unsafe_() {
		order._reset_unsafe_();
		reason = 0;
	}

	GsdErrorOrderInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		order = new UncompletedOrderInfo(0, this, "order");
	}

	public GsdErrorOrderInfo() {
		this(0, null, null);
	}

	public GsdErrorOrderInfo(GsdErrorOrderInfo _o_) {
		this(_o_, null, null);
	}

	GsdErrorOrderInfo(xbean.GsdErrorOrderInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof GsdErrorOrderInfo) assign((GsdErrorOrderInfo)_o1_);
		else if (_o1_ instanceof GsdErrorOrderInfo.Data) assign((GsdErrorOrderInfo.Data)_o1_);
		else if (_o1_ instanceof GsdErrorOrderInfo.Const) assign(((GsdErrorOrderInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(GsdErrorOrderInfo _o_) {
		_o_._xdb_verify_unsafe_();
		order = new UncompletedOrderInfo(_o_.order, this, "order");
		reason = _o_.reason;
	}

	private void assign(GsdErrorOrderInfo.Data _o_) {
		order = new UncompletedOrderInfo(_o_.order, this, "order");
		reason = _o_.reason;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(26624|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
order.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  1));_os_.marshal(reason);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (26624|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
order.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case ( 8192|  1):reason = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):reason = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.GsdErrorOrderInfo copy() {
		_xdb_verify_unsafe_();
		return new GsdErrorOrderInfo(this);
	}

	@Override
	public xbean.GsdErrorOrderInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GsdErrorOrderInfo toBean() {
		_xdb_verify_unsafe_();
		return new GsdErrorOrderInfo(this); // same as copy()
	}

	@Override
	public xbean.GsdErrorOrderInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GsdErrorOrderInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public xbean.UncompletedOrderInfo getOrder() { // 
		_xdb_verify_unsafe_();
		return order;
	}

	@Override
	public int getReason() { // 
		_xdb_verify_unsafe_();
		return reason;
	}

	@Override
	public void setReason(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "reason") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, reason) {
					public void rollback() { reason = _xdb_saved; }
				};}});
		reason = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		GsdErrorOrderInfo _o_ = null;
		if ( _o1_ instanceof GsdErrorOrderInfo ) _o_ = (GsdErrorOrderInfo)_o1_;
		else if ( _o1_ instanceof GsdErrorOrderInfo.Const ) _o_ = ((GsdErrorOrderInfo.Const)_o1_).nThis();
		else return false;
		if (!order.equals(_o_.order)) return false;
		if (reason != _o_.reason) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += order.hashCode();
		_h_ += reason;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(order);
		_sb_.append(",");
		_sb_.append(reason);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("order"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("reason"));
		return lb;
	}

	private class Const implements xbean.GsdErrorOrderInfo {
		GsdErrorOrderInfo nThis() {
			return GsdErrorOrderInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.GsdErrorOrderInfo copy() {
			return GsdErrorOrderInfo.this.copy();
		}

		@Override
		public xbean.GsdErrorOrderInfo toData() {
			return GsdErrorOrderInfo.this.toData();
		}

		public xbean.GsdErrorOrderInfo toBean() {
			return GsdErrorOrderInfo.this.toBean();
		}

		@Override
		public xbean.GsdErrorOrderInfo toDataIf() {
			return GsdErrorOrderInfo.this.toDataIf();
		}

		public xbean.GsdErrorOrderInfo toBeanIf() {
			return GsdErrorOrderInfo.this.toBeanIf();
		}

		@Override
		public xbean.UncompletedOrderInfo getOrder() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(order);
		}

		@Override
		public int getReason() { // 
			_xdb_verify_unsafe_();
			return reason;
		}

		@Override
		public void setReason(int _v_) { // 
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
			return GsdErrorOrderInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return GsdErrorOrderInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return GsdErrorOrderInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return GsdErrorOrderInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return GsdErrorOrderInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return GsdErrorOrderInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return GsdErrorOrderInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return GsdErrorOrderInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return GsdErrorOrderInfo.this.toString();
		}

	}

	public static final class Data implements xbean.GsdErrorOrderInfo {
		private xbean.UncompletedOrderInfo order; // 
		private int reason; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			order = new UncompletedOrderInfo.Data();
		}

		Data(xbean.GsdErrorOrderInfo _o1_) {
			if (_o1_ instanceof GsdErrorOrderInfo) assign((GsdErrorOrderInfo)_o1_);
			else if (_o1_ instanceof GsdErrorOrderInfo.Data) assign((GsdErrorOrderInfo.Data)_o1_);
			else if (_o1_ instanceof GsdErrorOrderInfo.Const) assign(((GsdErrorOrderInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(GsdErrorOrderInfo _o_) {
			order = new UncompletedOrderInfo.Data(_o_.order);
			reason = _o_.reason;
		}

		private void assign(GsdErrorOrderInfo.Data _o_) {
			order = new UncompletedOrderInfo.Data(_o_.order);
			reason = _o_.reason;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(26624|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
order.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  1));_os_.marshal(reason);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (26624|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
order.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case ( 8192|  1):reason = _os_.unmarshal_int();
					break;
					case ( 6144|  1):reason = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.GsdErrorOrderInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.GsdErrorOrderInfo toData() {
			return new Data(this);
		}

		public xbean.GsdErrorOrderInfo toBean() {
			return new GsdErrorOrderInfo(this, null, null);
		}

		@Override
		public xbean.GsdErrorOrderInfo toDataIf() {
			return this;
		}

		public xbean.GsdErrorOrderInfo toBeanIf() {
			return new GsdErrorOrderInfo(this, null, null);
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
		public xbean.UncompletedOrderInfo getOrder() { // 
			return order;
		}

		@Override
		public int getReason() { // 
			return reason;
		}

		@Override
		public void setReason(int _v_) { // 
			reason = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof GsdErrorOrderInfo.Data)) return false;
			GsdErrorOrderInfo.Data _o_ = (GsdErrorOrderInfo.Data) _o1_;
			if (!order.equals(_o_.order)) return false;
			if (reason != _o_.reason) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += order.hashCode();
			_h_ += reason;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(order);
			_sb_.append(",");
			_sb_.append(reason);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
