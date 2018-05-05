
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleChat extends xdb.XBean implements xbean.RoleChat {
	private xdb.util.SetX<String> chatface; // 

	@Override
	public void _reset_unsafe_() {
		chatface.clear();
	}

	RoleChat(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		chatface = new xdb.util.SetX<String>();
	}

	public RoleChat() {
		this(0, null, null);
	}

	public RoleChat(RoleChat _o_) {
		this(_o_, null, null);
	}

	RoleChat(xbean.RoleChat _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleChat) assign((RoleChat)_o1_);
		else if (_o1_ instanceof RoleChat.Data) assign((RoleChat.Data)_o1_);
		else if (_o1_ instanceof RoleChat.Const) assign(((RoleChat.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleChat _o_) {
		_o_._xdb_verify_unsafe_();
		chatface = new xdb.util.SetX<String>();
		chatface.addAll(_o_.chatface);
	}

	private void assign(RoleChat.Data _o_) {
		chatface = new xdb.util.SetX<String>();
		chatface.addAll(_o_.chatface);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(chatface.size());
for (String _v_ : chatface) {
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
	chatface.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleChat copy() {
		_xdb_verify_unsafe_();
		return new RoleChat(this);
	}

	@Override
	public xbean.RoleChat toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleChat toBean() {
		_xdb_verify_unsafe_();
		return new RoleChat(this); // same as copy()
	}

	@Override
	public xbean.RoleChat toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleChat toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Set<String> getChatface() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "chatface"), chatface);
	}

	public java.util.Set<String> getChatfaceAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Set<String> chatface;
		RoleChat _o_ = this;
		chatface = new xdb.util.SetX<String>();
		chatface.addAll(_o_.chatface);
		return chatface;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleChat _o_ = null;
		if ( _o1_ instanceof RoleChat ) _o_ = (RoleChat)_o1_;
		else if ( _o1_ instanceof RoleChat.Const ) _o_ = ((RoleChat.Const)_o1_).nThis();
		else return false;
		if (!chatface.equals(_o_.chatface)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += chatface.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(chatface);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableSet().setVarName("chatface"));
		return lb;
	}

	private class Const implements xbean.RoleChat {
		RoleChat nThis() {
			return RoleChat.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleChat copy() {
			return RoleChat.this.copy();
		}

		@Override
		public xbean.RoleChat toData() {
			return RoleChat.this.toData();
		}

		public xbean.RoleChat toBean() {
			return RoleChat.this.toBean();
		}

		@Override
		public xbean.RoleChat toDataIf() {
			return RoleChat.this.toDataIf();
		}

		public xbean.RoleChat toBeanIf() {
			return RoleChat.this.toBeanIf();
		}

		@Override
		public java.util.Set<String> getChatface() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(chatface);
		}

		public java.util.Set<String> getChatfaceAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Set<String> chatface;
			RoleChat _o_ = RoleChat.this;
		chatface = new xdb.util.SetX<String>();
		chatface.addAll(_o_.chatface);
			return chatface;
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
			return RoleChat.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleChat.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleChat.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleChat.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleChat.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleChat.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleChat.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleChat.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleChat.this.toString();
		}

	}

	public static final class Data implements xbean.RoleChat {
		private java.util.HashSet<String> chatface; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			chatface = new java.util.HashSet<String>();
		}

		Data(xbean.RoleChat _o1_) {
			if (_o1_ instanceof RoleChat) assign((RoleChat)_o1_);
			else if (_o1_ instanceof RoleChat.Data) assign((RoleChat.Data)_o1_);
			else if (_o1_ instanceof RoleChat.Const) assign(((RoleChat.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleChat _o_) {
			chatface = new java.util.HashSet<String>();
			chatface.addAll(_o_.chatface);
		}

		private void assign(RoleChat.Data _o_) {
			chatface = new java.util.HashSet<String>();
			chatface.addAll(_o_.chatface);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(20480|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(chatface.size());
for (String _v_ : chatface) {
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
	chatface.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleChat copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleChat toData() {
			return new Data(this);
		}

		public xbean.RoleChat toBean() {
			return new RoleChat(this, null, null);
		}

		@Override
		public xbean.RoleChat toDataIf() {
			return this;
		}

		public xbean.RoleChat toBeanIf() {
			return new RoleChat(this, null, null);
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
		public java.util.Set<String> getChatface() { // 
			return chatface;
		}

		@Override
		public java.util.Set<String> getChatfaceAsData() { // 
			return chatface;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleChat.Data)) return false;
			RoleChat.Data _o_ = (RoleChat.Data) _o1_;
			if (!chatface.equals(_o_.chatface)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += chatface.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(chatface);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
