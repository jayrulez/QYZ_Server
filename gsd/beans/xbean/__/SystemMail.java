
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class SystemMail extends xdb.XBean implements xbean.SystemMail {
	private long id; // 
	private int mailid; // cfg.mail.Mail
	private long sendtime; // 
	private String title; // 
	private String content; // 
	private xbean.Bonus bonus; // 
	private java.util.LinkedList<String> params; // 
	private xdb.util.SetX<Long> records; // 已领奖玩家id

	@Override
	public void _reset_unsafe_() {
		id = 0L;
		mailid = 0;
		sendtime = 0L;
		title = "";
		content = "";
		bonus._reset_unsafe_();
		params.clear();
		records.clear();
	}

	SystemMail(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		title = "";
		content = "";
		bonus = new Bonus(0, this, "bonus");
		params = new java.util.LinkedList<String>();
		records = new xdb.util.SetX<Long>();
	}

	public SystemMail() {
		this(0, null, null);
	}

	public SystemMail(SystemMail _o_) {
		this(_o_, null, null);
	}

	SystemMail(xbean.SystemMail _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof SystemMail) assign((SystemMail)_o1_);
		else if (_o1_ instanceof SystemMail.Data) assign((SystemMail.Data)_o1_);
		else if (_o1_ instanceof SystemMail.Const) assign(((SystemMail.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(SystemMail _o_) {
		_o_._xdb_verify_unsafe_();
		id = _o_.id;
		mailid = _o_.mailid;
		sendtime = _o_.sendtime;
		title = _o_.title;
		content = _o_.content;
		bonus = new Bonus(_o_.bonus, this, "bonus");
		params = new java.util.LinkedList<String>();
		params.addAll(_o_.params);
		records = new xdb.util.SetX<Long>();
		records.addAll(_o_.records);
	}

	private void assign(SystemMail.Data _o_) {
		id = _o_.id;
		mailid = _o_.mailid;
		sendtime = _o_.sendtime;
		title = _o_.title;
		content = _o_.content;
		bonus = new Bonus(_o_.bonus, this, "bonus");
		params = new java.util.LinkedList<String>();
		params.addAll(_o_.params);
		records = new xdb.util.SetX<Long>();
		records.addAll(_o_.records);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)8);
    _os_.marshal((short)(10240|  1));_os_.marshal(id);
    _os_.marshal((short)( 8192|  2));_os_.marshal(mailid);
    _os_.marshal((short)(10240|  3));_os_.marshal(sendtime);
    _os_.marshal((short)(18432|  4));_os_.marshal(title, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(18432|  5));_os_.marshal(content, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(26624|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
bonus.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(params.size());
for (String _v_ : params) {
	_os_.marshal(_v_, xdb.Const.IO_CHARSET);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(20480|  8));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(records.size());
for (Long _v_ : records) {
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
    				case (10240|  1):id = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):id = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):id = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):mailid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):mailid = _os_.unmarshal_short();
    				break;
    				case (10240|  3):sendtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):sendtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):sendtime = _os_.unmarshal_int();
    				break;
    				case (18432|  4):title = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (18432|  5):content = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (26624|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
bonus.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case (22528|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	String _v_ = "";
	_v_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
	params.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (20480|  8):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	records.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.SystemMail copy() {
		_xdb_verify_unsafe_();
		return new SystemMail(this);
	}

	@Override
	public xbean.SystemMail toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.SystemMail toBean() {
		_xdb_verify_unsafe_();
		return new SystemMail(this); // same as copy()
	}

	@Override
	public xbean.SystemMail toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.SystemMail toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getId() { // 
		_xdb_verify_unsafe_();
		return id;
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
	public xbean.Bonus getBonus() { // 
		_xdb_verify_unsafe_();
		return bonus;
	}

	@Override
	public java.util.List<String> getParams() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "params"), params);
	}

	public java.util.List<String> getParamsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<String> params;
		SystemMail _o_ = this;
		params = new java.util.LinkedList<String>();
		params.addAll(_o_.params);
		return params;
	}

	@Override
	public java.util.Set<Long> getRecords() { // 已领奖玩家id
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "records"), records);
	}

	public java.util.Set<Long> getRecordsAsData() { // 已领奖玩家id
		_xdb_verify_unsafe_();
		java.util.Set<Long> records;
		SystemMail _o_ = this;
		records = new xdb.util.SetX<Long>();
		records.addAll(_o_.records);
		return records;
	}

	@Override
	public void setId(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "id") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, id) {
					public void rollback() { id = _xdb_saved; }
				};}});
		id = _v_;
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
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		SystemMail _o_ = null;
		if ( _o1_ instanceof SystemMail ) _o_ = (SystemMail)_o1_;
		else if ( _o1_ instanceof SystemMail.Const ) _o_ = ((SystemMail.Const)_o1_).nThis();
		else return false;
		if (id != _o_.id) return false;
		if (mailid != _o_.mailid) return false;
		if (sendtime != _o_.sendtime) return false;
		if (!title.equals(_o_.title)) return false;
		if (!content.equals(_o_.content)) return false;
		if (!bonus.equals(_o_.bonus)) return false;
		if (!params.equals(_o_.params)) return false;
		if (!records.equals(_o_.records)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += id;
		_h_ += mailid;
		_h_ += sendtime;
		_h_ += title.hashCode();
		_h_ += content.hashCode();
		_h_ += bonus.hashCode();
		_h_ += params.hashCode();
		_h_ += records.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id);
		_sb_.append(",");
		_sb_.append(mailid);
		_sb_.append(",");
		_sb_.append(sendtime);
		_sb_.append(",");
		_sb_.append("'").append(title).append("'");
		_sb_.append(",");
		_sb_.append("'").append(content).append("'");
		_sb_.append(",");
		_sb_.append(bonus);
		_sb_.append(",");
		_sb_.append(params);
		_sb_.append(",");
		_sb_.append(records);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("id"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("mailid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("sendtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("title"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("content"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("bonus"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("params"));
		lb.add(new xdb.logs.ListenableSet().setVarName("records"));
		return lb;
	}

	private class Const implements xbean.SystemMail {
		SystemMail nThis() {
			return SystemMail.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.SystemMail copy() {
			return SystemMail.this.copy();
		}

		@Override
		public xbean.SystemMail toData() {
			return SystemMail.this.toData();
		}

		public xbean.SystemMail toBean() {
			return SystemMail.this.toBean();
		}

		@Override
		public xbean.SystemMail toDataIf() {
			return SystemMail.this.toDataIf();
		}

		public xbean.SystemMail toBeanIf() {
			return SystemMail.this.toBeanIf();
		}

		@Override
		public long getId() { // 
			_xdb_verify_unsafe_();
			return id;
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
			return SystemMail.this.getTitleOctets();
		}

		@Override
		public String getContent() { // 
			_xdb_verify_unsafe_();
			return content;
		}

		@Override
		public com.goldhuman.Common.Octets getContentOctets() { // 
			_xdb_verify_unsafe_();
			return SystemMail.this.getContentOctets();
		}

		@Override
		public xbean.Bonus getBonus() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(bonus);
		}

		@Override
		public java.util.List<String> getParams() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(params);
		}

		public java.util.List<String> getParamsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<String> params;
			SystemMail _o_ = SystemMail.this;
		params = new java.util.LinkedList<String>();
		params.addAll(_o_.params);
			return params;
		}

		@Override
		public java.util.Set<Long> getRecords() { // 已领奖玩家id
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(records);
		}

		public java.util.Set<Long> getRecordsAsData() { // 已领奖玩家id
			_xdb_verify_unsafe_();
			java.util.Set<Long> records;
			SystemMail _o_ = SystemMail.this;
		records = new xdb.util.SetX<Long>();
		records.addAll(_o_.records);
			return records;
		}

		@Override
		public void setId(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
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
			return SystemMail.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return SystemMail.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return SystemMail.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return SystemMail.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return SystemMail.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return SystemMail.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return SystemMail.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return SystemMail.this.hashCode();
		}

		@Override
		public String toString() {
			return SystemMail.this.toString();
		}

	}

	public static final class Data implements xbean.SystemMail {
		private long id; // 
		private int mailid; // cfg.mail.Mail
		private long sendtime; // 
		private String title; // 
		private String content; // 
		private xbean.Bonus bonus; // 
		private java.util.LinkedList<String> params; // 
		private java.util.HashSet<Long> records; // 已领奖玩家id

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			title = "";
			content = "";
			bonus = new Bonus.Data();
			params = new java.util.LinkedList<String>();
			records = new java.util.HashSet<Long>();
		}

		Data(xbean.SystemMail _o1_) {
			if (_o1_ instanceof SystemMail) assign((SystemMail)_o1_);
			else if (_o1_ instanceof SystemMail.Data) assign((SystemMail.Data)_o1_);
			else if (_o1_ instanceof SystemMail.Const) assign(((SystemMail.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(SystemMail _o_) {
			id = _o_.id;
			mailid = _o_.mailid;
			sendtime = _o_.sendtime;
			title = _o_.title;
			content = _o_.content;
			bonus = new Bonus.Data(_o_.bonus);
			params = new java.util.LinkedList<String>();
			params.addAll(_o_.params);
			records = new java.util.HashSet<Long>();
			records.addAll(_o_.records);
		}

		private void assign(SystemMail.Data _o_) {
			id = _o_.id;
			mailid = _o_.mailid;
			sendtime = _o_.sendtime;
			title = _o_.title;
			content = _o_.content;
			bonus = new Bonus.Data(_o_.bonus);
			params = new java.util.LinkedList<String>();
			params.addAll(_o_.params);
			records = new java.util.HashSet<Long>();
			records.addAll(_o_.records);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)8);
	_os_.marshal((short)(10240|  1));_os_.marshal(id);
	_os_.marshal((short)( 8192|  2));_os_.marshal(mailid);
	_os_.marshal((short)(10240|  3));_os_.marshal(sendtime);
	_os_.marshal((short)(18432|  4));_os_.marshal(title, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(18432|  5));_os_.marshal(content, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(26624|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
bonus.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(params.size());
for (String _v_ : params) {
	_os_.marshal(_v_, xdb.Const.IO_CHARSET);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(20480|  8));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(records.size());
for (Long _v_ : records) {
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
					case (10240|  1):id = _os_.unmarshal_long();
					break;
					case ( 6144|  1):id = _os_.unmarshal_short();
					break;
					case ( 8192|  1):id = _os_.unmarshal_int();
					break;
					case ( 8192|  2):mailid = _os_.unmarshal_int();
					break;
					case ( 6144|  2):mailid = _os_.unmarshal_short();
					break;
					case (10240|  3):sendtime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):sendtime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):sendtime = _os_.unmarshal_int();
					break;
					case (18432|  4):title = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (18432|  5):content = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (26624|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
bonus.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case (22528|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	String _v_ = "";
	_v_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
	params.add(_v_);
}
_os_ = _temp_;}
					break;
					case (20480|  8):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	records.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.SystemMail copy() {
			return new Data(this);
		}

		@Override
		public xbean.SystemMail toData() {
			return new Data(this);
		}

		public xbean.SystemMail toBean() {
			return new SystemMail(this, null, null);
		}

		@Override
		public xbean.SystemMail toDataIf() {
			return this;
		}

		public xbean.SystemMail toBeanIf() {
			return new SystemMail(this, null, null);
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
		public long getId() { // 
			return id;
		}

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
		public xbean.Bonus getBonus() { // 
			return bonus;
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
		public java.util.Set<Long> getRecords() { // 已领奖玩家id
			return records;
		}

		@Override
		public java.util.Set<Long> getRecordsAsData() { // 已领奖玩家id
			return records;
		}

		@Override
		public void setId(long _v_) { // 
			id = _v_;
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
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof SystemMail.Data)) return false;
			SystemMail.Data _o_ = (SystemMail.Data) _o1_;
			if (id != _o_.id) return false;
			if (mailid != _o_.mailid) return false;
			if (sendtime != _o_.sendtime) return false;
			if (!title.equals(_o_.title)) return false;
			if (!content.equals(_o_.content)) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			if (!params.equals(_o_.params)) return false;
			if (!records.equals(_o_.records)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += id;
			_h_ += mailid;
			_h_ += sendtime;
			_h_ += title.hashCode();
			_h_ += content.hashCode();
			_h_ += bonus.hashCode();
			_h_ += params.hashCode();
			_h_ += records.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(id);
			_sb_.append(",");
			_sb_.append(mailid);
			_sb_.append(",");
			_sb_.append(sendtime);
			_sb_.append(",");
			_sb_.append("'").append(title).append("'");
			_sb_.append(",");
			_sb_.append("'").append(content).append("'");
			_sb_.append(",");
			_sb_.append(bonus);
			_sb_.append(",");
			_sb_.append(params);
			_sb_.append(",");
			_sb_.append(records);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
