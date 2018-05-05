
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ForbidItem extends xdb.XBean implements xbean.ForbidItem {
	private long forbidtimeinterval; // 
	private long forbidrealsetime; // 
	private String desc; // 
	private String notifytouser; // 

	@Override
	public void _reset_unsafe_() {
		forbidtimeinterval = 0L;
		forbidrealsetime = 0L;
		desc = "";
		notifytouser = "";
	}

	ForbidItem(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		desc = "";
		notifytouser = "";
	}

	public ForbidItem() {
		this(0, null, null);
	}

	public ForbidItem(ForbidItem _o_) {
		this(_o_, null, null);
	}

	ForbidItem(xbean.ForbidItem _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ForbidItem) assign((ForbidItem)_o1_);
		else if (_o1_ instanceof ForbidItem.Data) assign((ForbidItem.Data)_o1_);
		else if (_o1_ instanceof ForbidItem.Const) assign(((ForbidItem.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ForbidItem _o_) {
		_o_._xdb_verify_unsafe_();
		forbidtimeinterval = _o_.forbidtimeinterval;
		forbidrealsetime = _o_.forbidrealsetime;
		desc = _o_.desc;
		notifytouser = _o_.notifytouser;
	}

	private void assign(ForbidItem.Data _o_) {
		forbidtimeinterval = _o_.forbidtimeinterval;
		forbidrealsetime = _o_.forbidrealsetime;
		desc = _o_.desc;
		notifytouser = _o_.notifytouser;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(10240|  1));_os_.marshal(forbidtimeinterval);
    _os_.marshal((short)(10240|  2));_os_.marshal(forbidrealsetime);
    _os_.marshal((short)(18432|  3));_os_.marshal(desc, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(18432|  4));_os_.marshal(notifytouser, xdb.Const.IO_CHARSET);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):forbidtimeinterval = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):forbidtimeinterval = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):forbidtimeinterval = _os_.unmarshal_int();
    				break;
    				case (10240|  2):forbidrealsetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):forbidrealsetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):forbidrealsetime = _os_.unmarshal_int();
    				break;
    				case (18432|  3):desc = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (18432|  4):notifytouser = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ForbidItem copy() {
		_xdb_verify_unsafe_();
		return new ForbidItem(this);
	}

	@Override
	public xbean.ForbidItem toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ForbidItem toBean() {
		_xdb_verify_unsafe_();
		return new ForbidItem(this); // same as copy()
	}

	@Override
	public xbean.ForbidItem toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ForbidItem toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getForbidtimeinterval() { // 
		_xdb_verify_unsafe_();
		return forbidtimeinterval;
	}

	@Override
	public long getForbidrealsetime() { // 
		_xdb_verify_unsafe_();
		return forbidrealsetime;
	}

	@Override
	public String getDesc() { // 
		_xdb_verify_unsafe_();
		return desc;
	}

	@Override
	public com.goldhuman.Common.Octets getDescOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getDesc(), xdb.Const.IO_CHARSET);
	}

	@Override
	public String getNotifytouser() { // 
		_xdb_verify_unsafe_();
		return notifytouser;
	}

	@Override
	public com.goldhuman.Common.Octets getNotifytouserOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getNotifytouser(), xdb.Const.IO_CHARSET);
	}

	@Override
	public void setForbidtimeinterval(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "forbidtimeinterval") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, forbidtimeinterval) {
					public void rollback() { forbidtimeinterval = _xdb_saved; }
				};}});
		forbidtimeinterval = _v_;
	}

	@Override
	public void setForbidrealsetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "forbidrealsetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, forbidrealsetime) {
					public void rollback() { forbidrealsetime = _xdb_saved; }
				};}});
		forbidrealsetime = _v_;
	}

	@Override
	public void setDesc(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "desc") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, desc) {
					public void rollback() { desc = _xdb_saved; }
				};}});
		desc = _v_;
	}

	@Override
	public void setDescOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setDesc(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setNotifytouser(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "notifytouser") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, notifytouser) {
					public void rollback() { notifytouser = _xdb_saved; }
				};}});
		notifytouser = _v_;
	}

	@Override
	public void setNotifytouserOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setNotifytouser(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ForbidItem _o_ = null;
		if ( _o1_ instanceof ForbidItem ) _o_ = (ForbidItem)_o1_;
		else if ( _o1_ instanceof ForbidItem.Const ) _o_ = ((ForbidItem.Const)_o1_).nThis();
		else return false;
		if (forbidtimeinterval != _o_.forbidtimeinterval) return false;
		if (forbidrealsetime != _o_.forbidrealsetime) return false;
		if (!desc.equals(_o_.desc)) return false;
		if (!notifytouser.equals(_o_.notifytouser)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += forbidtimeinterval;
		_h_ += forbidrealsetime;
		_h_ += desc.hashCode();
		_h_ += notifytouser.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(forbidtimeinterval);
		_sb_.append(",");
		_sb_.append(forbidrealsetime);
		_sb_.append(",");
		_sb_.append("'").append(desc).append("'");
		_sb_.append(",");
		_sb_.append("'").append(notifytouser).append("'");
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("forbidtimeinterval"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("forbidrealsetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("desc"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("notifytouser"));
		return lb;
	}

	private class Const implements xbean.ForbidItem {
		ForbidItem nThis() {
			return ForbidItem.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ForbidItem copy() {
			return ForbidItem.this.copy();
		}

		@Override
		public xbean.ForbidItem toData() {
			return ForbidItem.this.toData();
		}

		public xbean.ForbidItem toBean() {
			return ForbidItem.this.toBean();
		}

		@Override
		public xbean.ForbidItem toDataIf() {
			return ForbidItem.this.toDataIf();
		}

		public xbean.ForbidItem toBeanIf() {
			return ForbidItem.this.toBeanIf();
		}

		@Override
		public long getForbidtimeinterval() { // 
			_xdb_verify_unsafe_();
			return forbidtimeinterval;
		}

		@Override
		public long getForbidrealsetime() { // 
			_xdb_verify_unsafe_();
			return forbidrealsetime;
		}

		@Override
		public String getDesc() { // 
			_xdb_verify_unsafe_();
			return desc;
		}

		@Override
		public com.goldhuman.Common.Octets getDescOctets() { // 
			_xdb_verify_unsafe_();
			return ForbidItem.this.getDescOctets();
		}

		@Override
		public String getNotifytouser() { // 
			_xdb_verify_unsafe_();
			return notifytouser;
		}

		@Override
		public com.goldhuman.Common.Octets getNotifytouserOctets() { // 
			_xdb_verify_unsafe_();
			return ForbidItem.this.getNotifytouserOctets();
		}

		@Override
		public void setForbidtimeinterval(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setForbidrealsetime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDesc(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDescOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNotifytouser(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNotifytouserOctets(com.goldhuman.Common.Octets _v_) { // 
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
			return ForbidItem.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ForbidItem.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ForbidItem.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ForbidItem.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ForbidItem.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ForbidItem.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ForbidItem.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ForbidItem.this.hashCode();
		}

		@Override
		public String toString() {
			return ForbidItem.this.toString();
		}

	}

	public static final class Data implements xbean.ForbidItem {
		private long forbidtimeinterval; // 
		private long forbidrealsetime; // 
		private String desc; // 
		private String notifytouser; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			desc = "";
			notifytouser = "";
		}

		Data(xbean.ForbidItem _o1_) {
			if (_o1_ instanceof ForbidItem) assign((ForbidItem)_o1_);
			else if (_o1_ instanceof ForbidItem.Data) assign((ForbidItem.Data)_o1_);
			else if (_o1_ instanceof ForbidItem.Const) assign(((ForbidItem.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ForbidItem _o_) {
			forbidtimeinterval = _o_.forbidtimeinterval;
			forbidrealsetime = _o_.forbidrealsetime;
			desc = _o_.desc;
			notifytouser = _o_.notifytouser;
		}

		private void assign(ForbidItem.Data _o_) {
			forbidtimeinterval = _o_.forbidtimeinterval;
			forbidrealsetime = _o_.forbidrealsetime;
			desc = _o_.desc;
			notifytouser = _o_.notifytouser;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(10240|  1));_os_.marshal(forbidtimeinterval);
	_os_.marshal((short)(10240|  2));_os_.marshal(forbidrealsetime);
	_os_.marshal((short)(18432|  3));_os_.marshal(desc, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(18432|  4));_os_.marshal(notifytouser, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):forbidtimeinterval = _os_.unmarshal_long();
					break;
					case ( 6144|  1):forbidtimeinterval = _os_.unmarshal_short();
					break;
					case ( 8192|  1):forbidtimeinterval = _os_.unmarshal_int();
					break;
					case (10240|  2):forbidrealsetime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):forbidrealsetime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):forbidrealsetime = _os_.unmarshal_int();
					break;
					case (18432|  3):desc = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (18432|  4):notifytouser = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ForbidItem copy() {
			return new Data(this);
		}

		@Override
		public xbean.ForbidItem toData() {
			return new Data(this);
		}

		public xbean.ForbidItem toBean() {
			return new ForbidItem(this, null, null);
		}

		@Override
		public xbean.ForbidItem toDataIf() {
			return this;
		}

		public xbean.ForbidItem toBeanIf() {
			return new ForbidItem(this, null, null);
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
		public long getForbidtimeinterval() { // 
			return forbidtimeinterval;
		}

		@Override
		public long getForbidrealsetime() { // 
			return forbidrealsetime;
		}

		@Override
		public String getDesc() { // 
			return desc;
		}

		@Override
		public com.goldhuman.Common.Octets getDescOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getDesc(), xdb.Const.IO_CHARSET);
		}

		@Override
		public String getNotifytouser() { // 
			return notifytouser;
		}

		@Override
		public com.goldhuman.Common.Octets getNotifytouserOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getNotifytouser(), xdb.Const.IO_CHARSET);
		}

		@Override
		public void setForbidtimeinterval(long _v_) { // 
			forbidtimeinterval = _v_;
		}

		@Override
		public void setForbidrealsetime(long _v_) { // 
			forbidrealsetime = _v_;
		}

		@Override
		public void setDesc(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			desc = _v_;
		}

		@Override
		public void setDescOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setDesc(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setNotifytouser(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			notifytouser = _v_;
		}

		@Override
		public void setNotifytouserOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setNotifytouser(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ForbidItem.Data)) return false;
			ForbidItem.Data _o_ = (ForbidItem.Data) _o1_;
			if (forbidtimeinterval != _o_.forbidtimeinterval) return false;
			if (forbidrealsetime != _o_.forbidrealsetime) return false;
			if (!desc.equals(_o_.desc)) return false;
			if (!notifytouser.equals(_o_.notifytouser)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += forbidtimeinterval;
			_h_ += forbidrealsetime;
			_h_ += desc.hashCode();
			_h_ += notifytouser.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(forbidtimeinterval);
			_sb_.append(",");
			_sb_.append(forbidrealsetime);
			_sb_.append(",");
			_sb_.append("'").append(desc).append("'");
			_sb_.append(",");
			_sb_.append("'").append(notifytouser).append("'");
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
