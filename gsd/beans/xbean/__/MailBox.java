
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class MailBox extends xdb.XBean implements xbean.MailBox {
	private int nextmailid; // 
	private java.util.HashMap<Integer, xbean.Mail> mails; // 
	private long maxsysmail; // 已经领取的最大的系统邮件的id

	@Override
	public void _reset_unsafe_() {
		nextmailid = 0;
		mails.clear();
		maxsysmail = 0L;
	}

	MailBox(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		mails = new java.util.HashMap<Integer, xbean.Mail>();
	}

	public MailBox() {
		this(0, null, null);
	}

	public MailBox(MailBox _o_) {
		this(_o_, null, null);
	}

	MailBox(xbean.MailBox _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof MailBox) assign((MailBox)_o1_);
		else if (_o1_ instanceof MailBox.Data) assign((MailBox.Data)_o1_);
		else if (_o1_ instanceof MailBox.Const) assign(((MailBox.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(MailBox _o_) {
		_o_._xdb_verify_unsafe_();
		nextmailid = _o_.nextmailid;
		mails = new java.util.HashMap<Integer, xbean.Mail>();
		for (java.util.Map.Entry<Integer, xbean.Mail> _e_ : _o_.mails.entrySet())
			mails.put(_e_.getKey(), new Mail(_e_.getValue(), this, "mails"));
		maxsysmail = _o_.maxsysmail;
	}

	private void assign(MailBox.Data _o_) {
		nextmailid = _o_.nextmailid;
		mails = new java.util.HashMap<Integer, xbean.Mail>();
		for (java.util.Map.Entry<Integer, xbean.Mail> _e_ : _o_.mails.entrySet())
			mails.put(_e_.getKey(), new Mail(_e_.getValue(), this, "mails"));
		maxsysmail = _o_.maxsysmail;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  0));_os_.marshal(nextmailid);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(mails.size());
for (java.util.Map.Entry<Integer, xbean.Mail> _e_ : mails.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  2));_os_.marshal(maxsysmail);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  0):nextmailid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  0):nextmailid = _os_.unmarshal_short();
    				break;
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		mails = new java.util.HashMap<Integer, xbean.Mail>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Mail _v_ = new Mail(0, this, "mails");
		_v_.unmarshal(_os_);
		mails.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (10240|  2):maxsysmail = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):maxsysmail = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):maxsysmail = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.MailBox copy() {
		_xdb_verify_unsafe_();
		return new MailBox(this);
	}

	@Override
	public xbean.MailBox toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.MailBox toBean() {
		_xdb_verify_unsafe_();
		return new MailBox(this); // same as copy()
	}

	@Override
	public xbean.MailBox toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.MailBox toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getNextmailid() { // 
		_xdb_verify_unsafe_();
		return nextmailid;
	}

	@Override
	public java.util.Map<Integer, xbean.Mail> getMails() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "mails"), mails);
	}

	@Override
	public java.util.Map<Integer, xbean.Mail> getMailsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Mail> mails;
		MailBox _o_ = this;
		mails = new java.util.HashMap<Integer, xbean.Mail>();
		for (java.util.Map.Entry<Integer, xbean.Mail> _e_ : _o_.mails.entrySet())
			mails.put(_e_.getKey(), new Mail.Data(_e_.getValue()));
		return mails;
	}

	@Override
	public long getMaxsysmail() { // 已经领取的最大的系统邮件的id
		_xdb_verify_unsafe_();
		return maxsysmail;
	}

	@Override
	public void setNextmailid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "nextmailid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, nextmailid) {
					public void rollback() { nextmailid = _xdb_saved; }
				};}});
		nextmailid = _v_;
	}

	@Override
	public void setMaxsysmail(long _v_) { // 已经领取的最大的系统邮件的id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "maxsysmail") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, maxsysmail) {
					public void rollback() { maxsysmail = _xdb_saved; }
				};}});
		maxsysmail = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		MailBox _o_ = null;
		if ( _o1_ instanceof MailBox ) _o_ = (MailBox)_o1_;
		else if ( _o1_ instanceof MailBox.Const ) _o_ = ((MailBox.Const)_o1_).nThis();
		else return false;
		if (nextmailid != _o_.nextmailid) return false;
		if (!mails.equals(_o_.mails)) return false;
		if (maxsysmail != _o_.maxsysmail) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += nextmailid;
		_h_ += mails.hashCode();
		_h_ += maxsysmail;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(nextmailid);
		_sb_.append(",");
		_sb_.append(mails);
		_sb_.append(",");
		_sb_.append(maxsysmail);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("nextmailid"));
		lb.add(new xdb.logs.ListenableMap().setVarName("mails"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("maxsysmail"));
		return lb;
	}

	private class Const implements xbean.MailBox {
		MailBox nThis() {
			return MailBox.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.MailBox copy() {
			return MailBox.this.copy();
		}

		@Override
		public xbean.MailBox toData() {
			return MailBox.this.toData();
		}

		public xbean.MailBox toBean() {
			return MailBox.this.toBean();
		}

		@Override
		public xbean.MailBox toDataIf() {
			return MailBox.this.toDataIf();
		}

		public xbean.MailBox toBeanIf() {
			return MailBox.this.toBeanIf();
		}

		@Override
		public int getNextmailid() { // 
			_xdb_verify_unsafe_();
			return nextmailid;
		}

		@Override
		public java.util.Map<Integer, xbean.Mail> getMails() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(mails);
		}

		@Override
		public java.util.Map<Integer, xbean.Mail> getMailsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Mail> mails;
			MailBox _o_ = MailBox.this;
			mails = new java.util.HashMap<Integer, xbean.Mail>();
			for (java.util.Map.Entry<Integer, xbean.Mail> _e_ : _o_.mails.entrySet())
				mails.put(_e_.getKey(), new Mail.Data(_e_.getValue()));
			return mails;
		}

		@Override
		public long getMaxsysmail() { // 已经领取的最大的系统邮件的id
			_xdb_verify_unsafe_();
			return maxsysmail;
		}

		@Override
		public void setNextmailid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMaxsysmail(long _v_) { // 已经领取的最大的系统邮件的id
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
			return MailBox.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return MailBox.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return MailBox.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return MailBox.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return MailBox.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return MailBox.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return MailBox.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return MailBox.this.hashCode();
		}

		@Override
		public String toString() {
			return MailBox.this.toString();
		}

	}

	public static final class Data implements xbean.MailBox {
		private int nextmailid; // 
		private java.util.HashMap<Integer, xbean.Mail> mails; // 
		private long maxsysmail; // 已经领取的最大的系统邮件的id

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			mails = new java.util.HashMap<Integer, xbean.Mail>();
		}

		Data(xbean.MailBox _o1_) {
			if (_o1_ instanceof MailBox) assign((MailBox)_o1_);
			else if (_o1_ instanceof MailBox.Data) assign((MailBox.Data)_o1_);
			else if (_o1_ instanceof MailBox.Const) assign(((MailBox.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(MailBox _o_) {
			nextmailid = _o_.nextmailid;
			mails = new java.util.HashMap<Integer, xbean.Mail>();
			for (java.util.Map.Entry<Integer, xbean.Mail> _e_ : _o_.mails.entrySet())
				mails.put(_e_.getKey(), new Mail.Data(_e_.getValue()));
			maxsysmail = _o_.maxsysmail;
		}

		private void assign(MailBox.Data _o_) {
			nextmailid = _o_.nextmailid;
			mails = new java.util.HashMap<Integer, xbean.Mail>();
			for (java.util.Map.Entry<Integer, xbean.Mail> _e_ : _o_.mails.entrySet())
				mails.put(_e_.getKey(), new Mail.Data(_e_.getValue()));
			maxsysmail = _o_.maxsysmail;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  0));_os_.marshal(nextmailid);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(mails.size());
for (java.util.Map.Entry<Integer, xbean.Mail> _e_ : mails.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  2));_os_.marshal(maxsysmail);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  0):nextmailid = _os_.unmarshal_int();
					break;
					case ( 6144|  0):nextmailid = _os_.unmarshal_short();
					break;
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		mails = new java.util.HashMap<Integer, xbean.Mail>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Mail _v_ = xbean.Pod.newMailData();
		_v_.unmarshal(_os_);
		mails.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (10240|  2):maxsysmail = _os_.unmarshal_long();
					break;
					case ( 6144|  2):maxsysmail = _os_.unmarshal_short();
					break;
					case ( 8192|  2):maxsysmail = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.MailBox copy() {
			return new Data(this);
		}

		@Override
		public xbean.MailBox toData() {
			return new Data(this);
		}

		public xbean.MailBox toBean() {
			return new MailBox(this, null, null);
		}

		@Override
		public xbean.MailBox toDataIf() {
			return this;
		}

		public xbean.MailBox toBeanIf() {
			return new MailBox(this, null, null);
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
		public int getNextmailid() { // 
			return nextmailid;
		}

		@Override
		public java.util.Map<Integer, xbean.Mail> getMails() { // 
			return mails;
		}

		@Override
		public java.util.Map<Integer, xbean.Mail> getMailsAsData() { // 
			return mails;
		}

		@Override
		public long getMaxsysmail() { // 已经领取的最大的系统邮件的id
			return maxsysmail;
		}

		@Override
		public void setNextmailid(int _v_) { // 
			nextmailid = _v_;
		}

		@Override
		public void setMaxsysmail(long _v_) { // 已经领取的最大的系统邮件的id
			maxsysmail = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof MailBox.Data)) return false;
			MailBox.Data _o_ = (MailBox.Data) _o1_;
			if (nextmailid != _o_.nextmailid) return false;
			if (!mails.equals(_o_.mails)) return false;
			if (maxsysmail != _o_.maxsysmail) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += nextmailid;
			_h_ += mails.hashCode();
			_h_ += maxsysmail;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(nextmailid);
			_sb_.append(",");
			_sb_.append(mails);
			_sb_.append(",");
			_sb_.append(maxsysmail);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
