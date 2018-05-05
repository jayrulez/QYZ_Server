
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ActivationCode extends xdb.XBean implements xbean.ActivationCode {
	private int type; // 
	private int status; // 
	private long usetime; // 

	@Override
	public void _reset_unsafe_() {
		type = 0;
		status = 0;
		usetime = 0L;
	}

	ActivationCode(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public ActivationCode() {
		this(0, null, null);
	}

	public ActivationCode(ActivationCode _o_) {
		this(_o_, null, null);
	}

	ActivationCode(xbean.ActivationCode _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ActivationCode) assign((ActivationCode)_o1_);
		else if (_o1_ instanceof ActivationCode.Data) assign((ActivationCode.Data)_o1_);
		else if (_o1_ instanceof ActivationCode.Const) assign(((ActivationCode.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ActivationCode _o_) {
		_o_._xdb_verify_unsafe_();
		type = _o_.type;
		status = _o_.status;
		usetime = _o_.usetime;
	}

	private void assign(ActivationCode.Data _o_) {
		type = _o_.type;
		status = _o_.status;
		usetime = _o_.usetime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  0));_os_.marshal(type);
    _os_.marshal((short)( 8192|  1));_os_.marshal(status);
    _os_.marshal((short)(10240|  2));_os_.marshal(usetime);
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
    				case ( 8192|  1):status = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):status = _os_.unmarshal_short();
    				break;
    				case (10240|  2):usetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):usetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):usetime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ActivationCode copy() {
		_xdb_verify_unsafe_();
		return new ActivationCode(this);
	}

	@Override
	public xbean.ActivationCode toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ActivationCode toBean() {
		_xdb_verify_unsafe_();
		return new ActivationCode(this); // same as copy()
	}

	@Override
	public xbean.ActivationCode toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ActivationCode toBeanIf() {
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
	public int getStatus() { // 
		_xdb_verify_unsafe_();
		return status;
	}

	@Override
	public long getUsetime() { // 
		_xdb_verify_unsafe_();
		return usetime;
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
	public void setStatus(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "status") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, status) {
					public void rollback() { status = _xdb_saved; }
				};}});
		status = _v_;
	}

	@Override
	public void setUsetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "usetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, usetime) {
					public void rollback() { usetime = _xdb_saved; }
				};}});
		usetime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ActivationCode _o_ = null;
		if ( _o1_ instanceof ActivationCode ) _o_ = (ActivationCode)_o1_;
		else if ( _o1_ instanceof ActivationCode.Const ) _o_ = ((ActivationCode.Const)_o1_).nThis();
		else return false;
		if (type != _o_.type) return false;
		if (status != _o_.status) return false;
		if (usetime != _o_.usetime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += type;
		_h_ += status;
		_h_ += usetime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(type);
		_sb_.append(",");
		_sb_.append(status);
		_sb_.append(",");
		_sb_.append(usetime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("type"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("status"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("usetime"));
		return lb;
	}

	private class Const implements xbean.ActivationCode {
		ActivationCode nThis() {
			return ActivationCode.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ActivationCode copy() {
			return ActivationCode.this.copy();
		}

		@Override
		public xbean.ActivationCode toData() {
			return ActivationCode.this.toData();
		}

		public xbean.ActivationCode toBean() {
			return ActivationCode.this.toBean();
		}

		@Override
		public xbean.ActivationCode toDataIf() {
			return ActivationCode.this.toDataIf();
		}

		public xbean.ActivationCode toBeanIf() {
			return ActivationCode.this.toBeanIf();
		}

		@Override
		public int getType() { // 
			_xdb_verify_unsafe_();
			return type;
		}

		@Override
		public int getStatus() { // 
			_xdb_verify_unsafe_();
			return status;
		}

		@Override
		public long getUsetime() { // 
			_xdb_verify_unsafe_();
			return usetime;
		}

		@Override
		public void setType(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setStatus(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUsetime(long _v_) { // 
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
			return ActivationCode.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ActivationCode.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ActivationCode.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ActivationCode.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ActivationCode.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ActivationCode.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ActivationCode.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ActivationCode.this.hashCode();
		}

		@Override
		public String toString() {
			return ActivationCode.this.toString();
		}

	}

	public static final class Data implements xbean.ActivationCode {
		private int type; // 
		private int status; // 
		private long usetime; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.ActivationCode _o1_) {
			if (_o1_ instanceof ActivationCode) assign((ActivationCode)_o1_);
			else if (_o1_ instanceof ActivationCode.Data) assign((ActivationCode.Data)_o1_);
			else if (_o1_ instanceof ActivationCode.Const) assign(((ActivationCode.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ActivationCode _o_) {
			type = _o_.type;
			status = _o_.status;
			usetime = _o_.usetime;
		}

		private void assign(ActivationCode.Data _o_) {
			type = _o_.type;
			status = _o_.status;
			usetime = _o_.usetime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  0));_os_.marshal(type);
	_os_.marshal((short)( 8192|  1));_os_.marshal(status);
	_os_.marshal((short)(10240|  2));_os_.marshal(usetime);
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
					case ( 8192|  1):status = _os_.unmarshal_int();
					break;
					case ( 6144|  1):status = _os_.unmarshal_short();
					break;
					case (10240|  2):usetime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):usetime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):usetime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ActivationCode copy() {
			return new Data(this);
		}

		@Override
		public xbean.ActivationCode toData() {
			return new Data(this);
		}

		public xbean.ActivationCode toBean() {
			return new ActivationCode(this, null, null);
		}

		@Override
		public xbean.ActivationCode toDataIf() {
			return this;
		}

		public xbean.ActivationCode toBeanIf() {
			return new ActivationCode(this, null, null);
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
		public int getStatus() { // 
			return status;
		}

		@Override
		public long getUsetime() { // 
			return usetime;
		}

		@Override
		public void setType(int _v_) { // 
			type = _v_;
		}

		@Override
		public void setStatus(int _v_) { // 
			status = _v_;
		}

		@Override
		public void setUsetime(long _v_) { // 
			usetime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ActivationCode.Data)) return false;
			ActivationCode.Data _o_ = (ActivationCode.Data) _o1_;
			if (type != _o_.type) return false;
			if (status != _o_.status) return false;
			if (usetime != _o_.usetime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += type;
			_h_ += status;
			_h_ += usetime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(type);
			_sb_.append(",");
			_sb_.append(status);
			_sb_.append(",");
			_sb_.append(usetime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
