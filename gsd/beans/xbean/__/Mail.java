
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Mail extends xdb.XBean implements xbean.Mail {
	private int mailid; // cfg.mail.Mail
	private long sendtime; // 
	private String title; // 
	private String content; // 
	private int read; // 
	private xbean.Bonus accessory; // 
	private java.util.LinkedList<String> params; // 

	@Override
	public void _reset_unsafe_() {
		mailid = 0;
		sendtime = 0L;
		title = "";
		content = "";
		read = 0;
		accessory._reset_unsafe_();
		params.clear();
	}

	Mail(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		title = "";
		content = "";
		accessory = new Bonus(0, this, "accessory");
		params = new java.util.LinkedList<String>();
	}

	public Mail() {
		this(0, null, null);
	}

	public Mail(Mail _o_) {
		this(_o_, null, null);
	}

	Mail(xbean.Mail _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Mail) assign((Mail)_o1_);
		else if (_o1_ instanceof Mail.Data) assign((Mail.Data)_o1_);
		else if (_o1_ instanceof Mail.Const) assign(((Mail.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Mail _o_) {
		_o_._xdb_verify_unsafe_();
		mailid = _o_.mailid;
		sendtime = _o_.sendtime;
		title = _o_.title;
		content = _o_.content;
		read = _o_.read;
		accessory = new Bonus(_o_.accessory, this, "accessory");
		params = new java.util.LinkedList<String>();
		params.addAll(_o_.params);
	}

	private void assign(Mail.Data _o_) {
		mailid = _o_.mailid;
		sendtime = _o_.sendtime;
		title = _o_.title;
		content = _o_.content;
		read = _o_.read;
		accessory = new Bonus(_o_.accessory, this, "accessory");
		params = new java.util.LinkedList<String>();
		params.addAll(_o_.params);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)7);
    _os_.marshal((short)( 8192|  7));_os_.marshal(mailid);
    _os_.marshal((short)(10240|  2));_os_.marshal(sendtime);
    _os_.marshal((short)(18432|  0));_os_.marshal(title, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(18432|  1));_os_.marshal(content, xdb.Const.IO_CHARSET);
    _os_.marshal((short)( 8192|  8));_os_.marshal(read);
    _os_.marshal((short)(26624|  9));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
accessory.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(params.size());
for (String _v_ : params) {
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
    				case ( 8192|  7):mailid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  7):mailid = _os_.unmarshal_short();
    				break;
    				case (10240|  2):sendtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):sendtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):sendtime = _os_.unmarshal_int();
    				break;
    				case (18432|  0):title = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (18432|  1):content = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case ( 8192|  8):read = _os_.unmarshal_int();
    				break;
    				case ( 6144|  8):read = _os_.unmarshal_short();
    				break;
    				case (26624|  9):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
accessory.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case (22528| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	String _v_ = "";
	_v_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
	params.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Mail copy() {
		_xdb_verify_unsafe_();
		return new Mail(this);
	}

	@Override
	public xbean.Mail toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Mail toBean() {
		_xdb_verify_unsafe_();
		return new Mail(this); // same as copy()
	}

	@Override
	public xbean.Mail toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Mail toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getMailid() { // cfg.mail.Mail
		_xdb_verify_unsafe_();
		return mailid;
	}

	@Override
	public long getSendtime() { // 
		_xdb_verify_unsafe_();
		return sendtime;
	}

	@Override
	public String getTitle() { // 
		_xdb_verify_unsafe_();
		return title;
	}

	@Override
	public com.goldhuman.Common.Octets getTitleOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getTitle(), xdb.Const.IO_CHARSET);
	}

	@Override
	public String getContent() { // 
		_xdb_verify_unsafe_();
		return content;
	}

	@Override
	public com.goldhuman.Common.Octets getContentOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getContent(), xdb.Const.IO_CHARSET);
	}

	@Override
	public int getRead() { // 
		_xdb_verify_unsafe_();
		return read;
	}

	@Override
	public xbean.Bonus getAccessory() { // 
		_xdb_verify_unsafe_();
		return accessory;
	}

	@Override
	public java.util.List<String> getParams() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "params"), params);
	}

	public java.util.List<String> getParamsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<String> params;
		Mail _o_ = this;
		params = new java.util.LinkedList<String>();
		params.addAll(_o_.params);
		return params;
	}

	@Override
	public void setMailid(int _v_) { // cfg.mail.Mail
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "mailid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, mailid) {
					public void rollback() { mailid = _xdb_saved; }
				};}});
		mailid = _v_;
	}

	@Override
	public void setSendtime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "sendtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, sendtime) {
					public void rollback() { sendtime = _xdb_saved; }
				};}});
		sendtime = _v_;
	}

	@Override
	public void setTitle(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "title") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, title) {
					public void rollback() { title = _xdb_saved; }
				};}});
		title = _v_;
	}

	@Override
	public void setTitleOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setTitle(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setContent(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "content") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, content) {
					public void rollback() { content = _xdb_saved; }
				};}});
		content = _v_;
	}

	@Override
	public void setContentOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setContent(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setRead(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "read") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, read) {
					public void rollback() { read = _xdb_saved; }
				};}});
		read = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Mail _o_ = null;
		if ( _o1_ instanceof Mail ) _o_ = (Mail)_o1_;
		else if ( _o1_ instanceof Mail.Const ) _o_ = ((Mail.Const)_o1_).nThis();
		else return false;
		if (mailid != _o_.mailid) return false;
		if (sendtime != _o_.sendtime) return false;
		if (!title.equals(_o_.title)) return false;
		if (!content.equals(_o_.content)) return false;
		if (read != _o_.read) return false;
		if (!accessory.equals(_o_.accessory)) return false;
		if (!params.equals(_o_.params)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += mailid;
		_h_ += sendtime;
		_h_ += title.hashCode();
		_h_ += content.hashCode();
		_h_ += read;
		_h_ += accessory.hashCode();
		_h_ += params.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mailid);
		_sb_.append(",");
		_sb_.append(sendtime);
		_sb_.append(",");
		_sb_.append("'").append(title).append("'");
		_sb_.append(",");
		_sb_.append("'").append(content).append("'");
		_sb_.append(",");
		_sb_.append(read);
		_sb_.append(",");
		_sb_.append(accessory);
		_sb_.append(",");
		_sb_.append(params);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("mailid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("sendtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("title"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("content"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("read"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("accessory"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("params"));
		return lb;
	}

	private class Const implements xbean.Mail {
		Mail nThis() {
			return Mail.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Mail copy() {
			return Mail.this.copy();
		}

		@Override
		public xbean.Mail toData() {
			return Mail.this.toData();
		}

		public xbean.Mail toBean() {
			return Mail.this.toBean();
		}

		@Override
		public xbean.Mail toDataIf() {
			return Mail.this.toDataIf();
		}

		public xbean.Mail toBeanIf() {
			return Mail.this.toBeanIf();
		}

		@Override
		public int getMailid() { // cfg.mail.Mail
			_xdb_verify_unsafe_();
			return mailid;
		}

		@Override
		public long getSendtime() { // 
			_xdb_verify_unsafe_();
			return sendtime;
		}

		@Override
		public String getTitle() { // 
			_xdb_verify_unsafe_();
			return title;
		}

		@Override
		public com.goldhuman.Common.Octets getTitleOctets() { // 
			_xdb_verify_unsafe_();
			return Mail.this.getTitleOctets();
		}

		@Override
		public String getContent() { // 
			_xdb_verify_unsafe_();
			return content;
		}

		@Override
		public com.goldhuman.Common.Octets getContentOctets() { // 
			_xdb_verify_unsafe_();
			return Mail.this.getContentOctets();
		}

		@Override
		public int getRead() { // 
			_xdb_verify_unsafe_();
			return read;
		}

		@Override
		public xbean.Bonus getAccessory() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(accessory);
		}

		@Override
		public java.util.List<String> getParams() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(params);
		}

		public java.util.List<String> getParamsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<String> params;
			Mail _o_ = Mail.this;
		params = new java.util.LinkedList<String>();
		params.addAll(_o_.params);
			return params;
		}

		@Override
		public void setMailid(int _v_) { // cfg.mail.Mail
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setSendtime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTitle(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTitleOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setContent(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setContentOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRead(int _v_) { // 
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
			return Mail.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Mail.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Mail.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Mail.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Mail.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Mail.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Mail.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Mail.this.hashCode();
		}

		@Override
		public String toString() {
			return Mail.this.toString();
		}

	}

	public static final class Data implements xbean.Mail {
		private int mailid; // cfg.mail.Mail
		private long sendtime; // 
		private String title; // 
		private String content; // 
		private int read; // 
		private xbean.Bonus accessory; // 
		private java.util.LinkedList<String> params; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			title = "";
			content = "";
			accessory = new Bonus.Data();
			params = new java.util.LinkedList<String>();
		}

		Data(xbean.Mail _o1_) {
			if (_o1_ instanceof Mail) assign((Mail)_o1_);
			else if (_o1_ instanceof Mail.Data) assign((Mail.Data)_o1_);
			else if (_o1_ instanceof Mail.Const) assign(((Mail.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Mail _o_) {
			mailid = _o_.mailid;
			sendtime = _o_.sendtime;
			title = _o_.title;
			content = _o_.content;
			read = _o_.read;
			accessory = new Bonus.Data(_o_.accessory);
			params = new java.util.LinkedList<String>();
			params.addAll(_o_.params);
		}

		private void assign(Mail.Data _o_) {
			mailid = _o_.mailid;
			sendtime = _o_.sendtime;
			title = _o_.title;
			content = _o_.content;
			read = _o_.read;
			accessory = new Bonus.Data(_o_.accessory);
			params = new java.util.LinkedList<String>();
			params.addAll(_o_.params);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)7);
	_os_.marshal((short)( 8192|  7));_os_.marshal(mailid);
	_os_.marshal((short)(10240|  2));_os_.marshal(sendtime);
	_os_.marshal((short)(18432|  0));_os_.marshal(title, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(18432|  1));_os_.marshal(content, xdb.Const.IO_CHARSET);
	_os_.marshal((short)( 8192|  8));_os_.marshal(read);
	_os_.marshal((short)(26624|  9));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
accessory.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(params.size());
for (String _v_ : params) {
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
					case ( 8192|  7):mailid = _os_.unmarshal_int();
					break;
					case ( 6144|  7):mailid = _os_.unmarshal_short();
					break;
					case (10240|  2):sendtime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):sendtime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):sendtime = _os_.unmarshal_int();
					break;
					case (18432|  0):title = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (18432|  1):content = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case ( 8192|  8):read = _os_.unmarshal_int();
					break;
					case ( 6144|  8):read = _os_.unmarshal_short();
					break;
					case (26624|  9):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
accessory.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case (22528| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	String _v_ = "";
	_v_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
	params.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Mail copy() {
			return new Data(this);
		}

		@Override
		public xbean.Mail toData() {
			return new Data(this);
		}

		public xbean.Mail toBean() {
			return new Mail(this, null, null);
		}

		@Override
		public xbean.Mail toDataIf() {
			return this;
		}

		public xbean.Mail toBeanIf() {
			return new Mail(this, null, null);
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
		public int getMailid() { // cfg.mail.Mail
			return mailid;
		}

		@Override
		public long getSendtime() { // 
			return sendtime;
		}

		@Override
		public String getTitle() { // 
			return title;
		}

		@Override
		public com.goldhuman.Common.Octets getTitleOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getTitle(), xdb.Const.IO_CHARSET);
		}

		@Override
		public String getContent() { // 
			return content;
		}

		@Override
		public com.goldhuman.Common.Octets getContentOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getContent(), xdb.Const.IO_CHARSET);
		}

		@Override
		public int getRead() { // 
			return read;
		}

		@Override
		public xbean.Bonus getAccessory() { // 
			return accessory;
		}

		@Override
		public java.util.List<String> getParams() { // 
			return params;
		}

		@Override
		public java.util.List<String> getParamsAsData() { // 
			return params;
		}

		@Override
		public void setMailid(int _v_) { // cfg.mail.Mail
			mailid = _v_;
		}

		@Override
		public void setSendtime(long _v_) { // 
			sendtime = _v_;
		}

		@Override
		public void setTitle(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			title = _v_;
		}

		@Override
		public void setTitleOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setTitle(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setContent(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			content = _v_;
		}

		@Override
		public void setContentOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setContent(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setRead(int _v_) { // 
			read = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Mail.Data)) return false;
			Mail.Data _o_ = (Mail.Data) _o1_;
			if (mailid != _o_.mailid) return false;
			if (sendtime != _o_.sendtime) return false;
			if (!title.equals(_o_.title)) return false;
			if (!content.equals(_o_.content)) return false;
			if (read != _o_.read) return false;
			if (!accessory.equals(_o_.accessory)) return false;
			if (!params.equals(_o_.params)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += mailid;
			_h_ += sendtime;
			_h_ += title.hashCode();
			_h_ += content.hashCode();
			_h_ += read;
			_h_ += accessory.hashCode();
			_h_ += params.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(mailid);
			_sb_.append(",");
			_sb_.append(sendtime);
			_sb_.append(",");
			_sb_.append("'").append(title).append("'");
			_sb_.append(",");
			_sb_.append("'").append(content).append("'");
			_sb_.append(",");
			_sb_.append(read);
			_sb_.append(",");
			_sb_.append(accessory);
			_sb_.append(",");
			_sb_.append(params);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
