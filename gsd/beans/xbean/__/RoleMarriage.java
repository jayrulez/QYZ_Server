
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleMarriage extends xdb.XBean implements xbean.RoleMarriage {
	private long coupleroleid; // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
	private long curproposeid; // 当前正在求婚对象的id
	private long startproposetime; // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚
	private java.util.LinkedList<Long> wishfriendlist; // 求婚后发送祝福的好友

	@Override
	public void _reset_unsafe_() {
		coupleroleid = 0L;
		curproposeid = 0L;
		startproposetime = 0L;
		wishfriendlist.clear();
	}

	RoleMarriage(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		wishfriendlist = new java.util.LinkedList<Long>();
	}

	public RoleMarriage() {
		this(0, null, null);
	}

	public RoleMarriage(RoleMarriage _o_) {
		this(_o_, null, null);
	}

	RoleMarriage(xbean.RoleMarriage _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleMarriage) assign((RoleMarriage)_o1_);
		else if (_o1_ instanceof RoleMarriage.Data) assign((RoleMarriage.Data)_o1_);
		else if (_o1_ instanceof RoleMarriage.Const) assign(((RoleMarriage.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleMarriage _o_) {
		_o_._xdb_verify_unsafe_();
		coupleroleid = _o_.coupleroleid;
		curproposeid = _o_.curproposeid;
		startproposetime = _o_.startproposetime;
		wishfriendlist = new java.util.LinkedList<Long>();
		wishfriendlist.addAll(_o_.wishfriendlist);
	}

	private void assign(RoleMarriage.Data _o_) {
		coupleroleid = _o_.coupleroleid;
		curproposeid = _o_.curproposeid;
		startproposetime = _o_.startproposetime;
		wishfriendlist = new java.util.LinkedList<Long>();
		wishfriendlist.addAll(_o_.wishfriendlist);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(10240|  1));_os_.marshal(coupleroleid);
    _os_.marshal((short)(10240|  2));_os_.marshal(curproposeid);
    _os_.marshal((short)(10240|  3));_os_.marshal(startproposetime);
    _os_.marshal((short)(22528|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(wishfriendlist.size());
for (Long _v_ : wishfriendlist) {
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
    				case (10240|  1):coupleroleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):coupleroleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):coupleroleid = _os_.unmarshal_int();
    				break;
    				case (10240|  2):curproposeid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):curproposeid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):curproposeid = _os_.unmarshal_int();
    				break;
    				case (10240|  3):startproposetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):startproposetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):startproposetime = _os_.unmarshal_int();
    				break;
    				case (22528|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	wishfriendlist.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleMarriage copy() {
		_xdb_verify_unsafe_();
		return new RoleMarriage(this);
	}

	@Override
	public xbean.RoleMarriage toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleMarriage toBean() {
		_xdb_verify_unsafe_();
		return new RoleMarriage(this); // same as copy()
	}

	@Override
	public xbean.RoleMarriage toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleMarriage toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getCoupleroleid() { // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
		_xdb_verify_unsafe_();
		return coupleroleid;
	}

	@Override
	public long getCurproposeid() { // 当前正在求婚对象的id
		_xdb_verify_unsafe_();
		return curproposeid;
	}

	@Override
	public long getStartproposetime() { // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚
		_xdb_verify_unsafe_();
		return startproposetime;
	}

	@Override
	public java.util.List<Long> getWishfriendlist() { // 求婚后发送祝福的好友
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "wishfriendlist"), wishfriendlist);
	}

	public java.util.List<Long> getWishfriendlistAsData() { // 求婚后发送祝福的好友
		_xdb_verify_unsafe_();
		java.util.List<Long> wishfriendlist;
		RoleMarriage _o_ = this;
		wishfriendlist = new java.util.LinkedList<Long>();
		wishfriendlist.addAll(_o_.wishfriendlist);
		return wishfriendlist;
	}

	@Override
	public void setCoupleroleid(long _v_) { // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "coupleroleid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, coupleroleid) {
					public void rollback() { coupleroleid = _xdb_saved; }
				};}});
		coupleroleid = _v_;
	}

	@Override
	public void setCurproposeid(long _v_) { // 当前正在求婚对象的id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "curproposeid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, curproposeid) {
					public void rollback() { curproposeid = _xdb_saved; }
				};}});
		curproposeid = _v_;
	}

	@Override
	public void setStartproposetime(long _v_) { // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "startproposetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, startproposetime) {
					public void rollback() { startproposetime = _xdb_saved; }
				};}});
		startproposetime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleMarriage _o_ = null;
		if ( _o1_ instanceof RoleMarriage ) _o_ = (RoleMarriage)_o1_;
		else if ( _o1_ instanceof RoleMarriage.Const ) _o_ = ((RoleMarriage.Const)_o1_).nThis();
		else return false;
		if (coupleroleid != _o_.coupleroleid) return false;
		if (curproposeid != _o_.curproposeid) return false;
		if (startproposetime != _o_.startproposetime) return false;
		if (!wishfriendlist.equals(_o_.wishfriendlist)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += coupleroleid;
		_h_ += curproposeid;
		_h_ += startproposetime;
		_h_ += wishfriendlist.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(coupleroleid);
		_sb_.append(",");
		_sb_.append(curproposeid);
		_sb_.append(",");
		_sb_.append(startproposetime);
		_sb_.append(",");
		_sb_.append(wishfriendlist);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("coupleroleid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("curproposeid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("startproposetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("wishfriendlist"));
		return lb;
	}

	private class Const implements xbean.RoleMarriage {
		RoleMarriage nThis() {
			return RoleMarriage.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleMarriage copy() {
			return RoleMarriage.this.copy();
		}

		@Override
		public xbean.RoleMarriage toData() {
			return RoleMarriage.this.toData();
		}

		public xbean.RoleMarriage toBean() {
			return RoleMarriage.this.toBean();
		}

		@Override
		public xbean.RoleMarriage toDataIf() {
			return RoleMarriage.this.toDataIf();
		}

		public xbean.RoleMarriage toBeanIf() {
			return RoleMarriage.this.toBeanIf();
		}

		@Override
		public long getCoupleroleid() { // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
			_xdb_verify_unsafe_();
			return coupleroleid;
		}

		@Override
		public long getCurproposeid() { // 当前正在求婚对象的id
			_xdb_verify_unsafe_();
			return curproposeid;
		}

		@Override
		public long getStartproposetime() { // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚
			_xdb_verify_unsafe_();
			return startproposetime;
		}

		@Override
		public java.util.List<Long> getWishfriendlist() { // 求婚后发送祝福的好友
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(wishfriendlist);
		}

		public java.util.List<Long> getWishfriendlistAsData() { // 求婚后发送祝福的好友
			_xdb_verify_unsafe_();
			java.util.List<Long> wishfriendlist;
			RoleMarriage _o_ = RoleMarriage.this;
		wishfriendlist = new java.util.LinkedList<Long>();
		wishfriendlist.addAll(_o_.wishfriendlist);
			return wishfriendlist;
		}

		@Override
		public void setCoupleroleid(long _v_) { // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCurproposeid(long _v_) { // 当前正在求婚对象的id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setStartproposetime(long _v_) { // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚
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
			return RoleMarriage.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleMarriage.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleMarriage.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleMarriage.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleMarriage.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleMarriage.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleMarriage.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleMarriage.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleMarriage.this.toString();
		}

	}

	public static final class Data implements xbean.RoleMarriage {
		private long coupleroleid; // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
		private long curproposeid; // 当前正在求婚对象的id
		private long startproposetime; // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚
		private java.util.LinkedList<Long> wishfriendlist; // 求婚后发送祝福的好友

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			wishfriendlist = new java.util.LinkedList<Long>();
		}

		Data(xbean.RoleMarriage _o1_) {
			if (_o1_ instanceof RoleMarriage) assign((RoleMarriage)_o1_);
			else if (_o1_ instanceof RoleMarriage.Data) assign((RoleMarriage.Data)_o1_);
			else if (_o1_ instanceof RoleMarriage.Const) assign(((RoleMarriage.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleMarriage _o_) {
			coupleroleid = _o_.coupleroleid;
			curproposeid = _o_.curproposeid;
			startproposetime = _o_.startproposetime;
			wishfriendlist = new java.util.LinkedList<Long>();
			wishfriendlist.addAll(_o_.wishfriendlist);
		}

		private void assign(RoleMarriage.Data _o_) {
			coupleroleid = _o_.coupleroleid;
			curproposeid = _o_.curproposeid;
			startproposetime = _o_.startproposetime;
			wishfriendlist = new java.util.LinkedList<Long>();
			wishfriendlist.addAll(_o_.wishfriendlist);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(10240|  1));_os_.marshal(coupleroleid);
	_os_.marshal((short)(10240|  2));_os_.marshal(curproposeid);
	_os_.marshal((short)(10240|  3));_os_.marshal(startproposetime);
	_os_.marshal((short)(22528|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(wishfriendlist.size());
for (Long _v_ : wishfriendlist) {
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
					case (10240|  1):coupleroleid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):coupleroleid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):coupleroleid = _os_.unmarshal_int();
					break;
					case (10240|  2):curproposeid = _os_.unmarshal_long();
					break;
					case ( 6144|  2):curproposeid = _os_.unmarshal_short();
					break;
					case ( 8192|  2):curproposeid = _os_.unmarshal_int();
					break;
					case (10240|  3):startproposetime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):startproposetime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):startproposetime = _os_.unmarshal_int();
					break;
					case (22528|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	long _v_ = 0;
	_v_ = _os_.unmarshal_long();
	wishfriendlist.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleMarriage copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleMarriage toData() {
			return new Data(this);
		}

		public xbean.RoleMarriage toBean() {
			return new RoleMarriage(this, null, null);
		}

		@Override
		public xbean.RoleMarriage toDataIf() {
			return this;
		}

		public xbean.RoleMarriage toBeanIf() {
			return new RoleMarriage(this, null, null);
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
		public long getCoupleroleid() { // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
			return coupleroleid;
		}

		@Override
		public long getCurproposeid() { // 当前正在求婚对象的id
			return curproposeid;
		}

		@Override
		public long getStartproposetime() { // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚
			return startproposetime;
		}

		@Override
		public java.util.List<Long> getWishfriendlist() { // 求婚后发送祝福的好友
			return wishfriendlist;
		}

		@Override
		public java.util.List<Long> getWishfriendlistAsData() { // 求婚后发送祝福的好友
			return wishfriendlist;
		}

		@Override
		public void setCoupleroleid(long _v_) { // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
			coupleroleid = _v_;
		}

		@Override
		public void setCurproposeid(long _v_) { // 当前正在求婚对象的id
			curproposeid = _v_;
		}

		@Override
		public void setStartproposetime(long _v_) { // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚
			startproposetime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleMarriage.Data)) return false;
			RoleMarriage.Data _o_ = (RoleMarriage.Data) _o1_;
			if (coupleroleid != _o_.coupleroleid) return false;
			if (curproposeid != _o_.curproposeid) return false;
			if (startproposetime != _o_.startproposetime) return false;
			if (!wishfriendlist.equals(_o_.wishfriendlist)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += coupleroleid;
			_h_ += curproposeid;
			_h_ += startproposetime;
			_h_ += wishfriendlist.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(coupleroleid);
			_sb_.append(",");
			_sb_.append(curproposeid);
			_sb_.append(",");
			_sb_.append(startproposetime);
			_sb_.append(",");
			_sb_.append(wishfriendlist);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
