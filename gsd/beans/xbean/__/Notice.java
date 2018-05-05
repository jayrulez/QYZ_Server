
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Notice extends xdb.XBean implements xbean.Notice {
	private byte [] data; // 

	@Override
	public void _reset_unsafe_() {
		data = new byte[0];
	}

	Notice(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		data = new byte[0];
	}

	public Notice() {
		this(0, null, null);
	}

	public Notice(Notice _o_) {
		this(_o_, null, null);
	}

	Notice(xbean.Notice _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Notice) assign((Notice)_o1_);
		else if (_o1_ instanceof Notice.Data) assign((Notice.Data)_o1_);
		else if (_o1_ instanceof Notice.Const) assign(((Notice.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Notice _o_) {
		_o_._xdb_verify_unsafe_();
		data = java.util.Arrays.copyOf(_o_.data, _o_.data.length);
	}

	private void assign(Notice.Data _o_) {
		data = java.util.Arrays.copyOf(_o_.data, _o_.data.length);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(16384|  1));_os_.marshal(data);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (16384|  1):data = _os_.unmarshal_bytes();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Notice copy() {
		_xdb_verify_unsafe_();
		return new Notice(this);
	}

	@Override
	public xbean.Notice toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Notice toBean() {
		_xdb_verify_unsafe_();
		return new Notice(this); // same as copy()
	}

	@Override
	public xbean.Notice toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Notice toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public <T extends com.goldhuman.Common.Marshal.Marshal> T getData(T _v_) { // 
		_xdb_verify_unsafe_();
		try {
			_v_.unmarshal(OctetsStream.wrap(com.goldhuman.Common.Octets.wrap(data)));
			return _v_;
		} catch (MarshalException _e_) {
			throw new xio.MarshalError();
		}
	}

	@Override
	public boolean isDataEmpty() { // 
		_xdb_verify_unsafe_();
		return data.length == 0;
	}

	@Override
	public byte[] getDataCopy() { // 
		_xdb_verify_unsafe_();
		return java.util.Arrays.copyOf(data, data.length);
	}

	@Override
	public void setData(com.goldhuman.Common.Marshal.Marshal _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "data") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<byte []>(this, data) {
					public void rollback() { data = _xdb_saved; }
			}; }});
		data = _v_.marshal(new OctetsStream()).getBytes();
	}

	@Override
	public void setDataCopy(byte[] _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "data") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<byte []>(this, data) {
					public void rollback() { data = _xdb_saved; }
			}; }});
		data = java.util.Arrays.copyOf(_v_, _v_.length);
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Notice _o_ = null;
		if ( _o1_ instanceof Notice ) _o_ = (Notice)_o1_;
		else if ( _o1_ instanceof Notice.Const ) _o_ = ((Notice.Const)_o1_).nThis();
		else return false;
		if (!java.util.Arrays.equals(data, _o_.data)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += java.util.Arrays.hashCode(data);
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append('B').append(data.length);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("data"));
		return lb;
	}

	private class Const implements xbean.Notice {
		Notice nThis() {
			return Notice.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Notice copy() {
			return Notice.this.copy();
		}

		@Override
		public xbean.Notice toData() {
			return Notice.this.toData();
		}

		public xbean.Notice toBean() {
			return Notice.this.toBean();
		}

		@Override
		public xbean.Notice toDataIf() {
			return Notice.this.toDataIf();
		}

		public xbean.Notice toBeanIf() {
			return Notice.this.toBeanIf();
		}

		@Override
		public <T extends com.goldhuman.Common.Marshal.Marshal> T getData(T _v_) { // 
			_xdb_verify_unsafe_();
			return Notice.this.getData(_v_);
		}

		@Override
		public boolean isDataEmpty() { // 
			_xdb_verify_unsafe_();
			return Notice.this.isDataEmpty();
		}

		@Override
		public byte[] getDataCopy() { // 
			_xdb_verify_unsafe_();
			return Notice.this.getDataCopy();
		}

		@Override
		public void setData(com.goldhuman.Common.Marshal.Marshal _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDataCopy(byte[] _v_) { // 
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
			return Notice.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Notice.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Notice.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Notice.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Notice.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Notice.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Notice.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Notice.this.hashCode();
		}

		@Override
		public String toString() {
			return Notice.this.toString();
		}

	}

	public static final class Data implements xbean.Notice {
		private byte [] data; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			data = new byte[0];
		}

		Data(xbean.Notice _o1_) {
			if (_o1_ instanceof Notice) assign((Notice)_o1_);
			else if (_o1_ instanceof Notice.Data) assign((Notice.Data)_o1_);
			else if (_o1_ instanceof Notice.Const) assign(((Notice.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Notice _o_) {
			data = java.util.Arrays.copyOf(_o_.data, _o_.data.length);
		}

		private void assign(Notice.Data _o_) {
			data = java.util.Arrays.copyOf(_o_.data, _o_.data.length);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(16384|  1));_os_.marshal(data);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (16384|  1):data = _os_.unmarshal_bytes();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Notice copy() {
			return new Data(this);
		}

		@Override
		public xbean.Notice toData() {
			return new Data(this);
		}

		public xbean.Notice toBean() {
			return new Notice(this, null, null);
		}

		@Override
		public xbean.Notice toDataIf() {
			return this;
		}

		public xbean.Notice toBeanIf() {
			return new Notice(this, null, null);
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
		public <T extends com.goldhuman.Common.Marshal.Marshal> T getData(T _v_) { // 
			try {
				_v_.unmarshal(OctetsStream.wrap(com.goldhuman.Common.Octets.wrap(data)));
				return _v_;
			} catch (MarshalException _e_) {
				throw new xio.MarshalError();
			}
		}

		@Override
		public boolean isDataEmpty() { // 
			return data.length == 0;
		}

		@Override
		public byte[] getDataCopy() { // 
			return java.util.Arrays.copyOf(data, data.length);
		}

		@Override
		public void setData(com.goldhuman.Common.Marshal.Marshal _v_) { // 
			data = _v_.marshal(new OctetsStream()).getBytes();
		}

		@Override
		public void setDataCopy(byte[] _v_) { // 
			data = java.util.Arrays.copyOf(_v_, _v_.length);
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Notice.Data)) return false;
			Notice.Data _o_ = (Notice.Data) _o1_;
			if (!java.util.Arrays.equals(data, _o_.data)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += java.util.Arrays.hashCode(data);
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append('B').append(data.length);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
