
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleFamily extends xdb.XBean implements xbean.RoleFamily {
	private long currentfid; // 当前所在的家族id
	private java.util.HashMap<Long, Long> requestedfamily; // 申请过的家族信息列表
	private long lastquittime; // 最近一次退出家族的时间
	private int totalquitnum; // 总共退出家族的次数
	private int hasjoinpartytoday; // 今天是否参加过仙府聚宴, 0,否，1,是
	private int isfindbackparty; // 是否找回昨天的仙府聚宴，0，否，1，是

	@Override
	public void _reset_unsafe_() {
		currentfid = 0L;
		requestedfamily.clear();
		lastquittime = 0L;
		totalquitnum = 0;
		hasjoinpartytoday = 0;
		isfindbackparty = 0;
	}

	RoleFamily(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		requestedfamily = new java.util.HashMap<Long, Long>();
	}

	public RoleFamily() {
		this(0, null, null);
	}

	public RoleFamily(RoleFamily _o_) {
		this(_o_, null, null);
	}

	RoleFamily(xbean.RoleFamily _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleFamily) assign((RoleFamily)_o1_);
		else if (_o1_ instanceof RoleFamily.Data) assign((RoleFamily.Data)_o1_);
		else if (_o1_ instanceof RoleFamily.Const) assign(((RoleFamily.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleFamily _o_) {
		_o_._xdb_verify_unsafe_();
		currentfid = _o_.currentfid;
		requestedfamily = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestedfamily.entrySet())
			requestedfamily.put(_e_.getKey(), _e_.getValue());
		lastquittime = _o_.lastquittime;
		totalquitnum = _o_.totalquitnum;
		hasjoinpartytoday = _o_.hasjoinpartytoday;
		isfindbackparty = _o_.isfindbackparty;
	}

	private void assign(RoleFamily.Data _o_) {
		currentfid = _o_.currentfid;
		requestedfamily = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestedfamily.entrySet())
			requestedfamily.put(_e_.getKey(), _e_.getValue());
		lastquittime = _o_.lastquittime;
		totalquitnum = _o_.totalquitnum;
		hasjoinpartytoday = _o_.hasjoinpartytoday;
		isfindbackparty = _o_.isfindbackparty;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)(10240|  1));_os_.marshal(currentfid);
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(requestedfamily.size());
for (java.util.Map.Entry<Long, Long> _e_ : requestedfamily.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  5));_os_.marshal(lastquittime);
    _os_.marshal((short)( 8192|  6));_os_.marshal(totalquitnum);
    _os_.marshal((short)( 8192|  9));_os_.marshal(hasjoinpartytoday);
    _os_.marshal((short)( 8192| 10));_os_.marshal(isfindbackparty);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):currentfid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):currentfid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):currentfid = _os_.unmarshal_int();
    				break;
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		requestedfamily = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		requestedfamily.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (10240|  5):lastquittime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):lastquittime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):lastquittime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  6):totalquitnum = _os_.unmarshal_int();
    				break;
    				case ( 6144|  6):totalquitnum = _os_.unmarshal_short();
    				break;
    				case ( 8192|  9):hasjoinpartytoday = _os_.unmarshal_int();
    				break;
    				case ( 6144|  9):hasjoinpartytoday = _os_.unmarshal_short();
    				break;
    				case ( 8192| 10):isfindbackparty = _os_.unmarshal_int();
    				break;
    				case ( 6144| 10):isfindbackparty = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleFamily copy() {
		_xdb_verify_unsafe_();
		return new RoleFamily(this);
	}

	@Override
	public xbean.RoleFamily toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleFamily toBean() {
		_xdb_verify_unsafe_();
		return new RoleFamily(this); // same as copy()
	}

	@Override
	public xbean.RoleFamily toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleFamily toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getCurrentfid() { // 当前所在的家族id
		_xdb_verify_unsafe_();
		return currentfid;
	}

	@Override
	public java.util.Map<Long, Long> getRequestedfamily() { // 申请过的家族信息列表
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "requestedfamily"), requestedfamily);
	}

	@Override
	public java.util.Map<Long, Long> getRequestedfamilyAsData() { // 申请过的家族信息列表
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> requestedfamily;
		RoleFamily _o_ = this;
		requestedfamily = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestedfamily.entrySet())
			requestedfamily.put(_e_.getKey(), _e_.getValue());
		return requestedfamily;
	}

	@Override
	public long getLastquittime() { // 最近一次退出家族的时间
		_xdb_verify_unsafe_();
		return lastquittime;
	}

	@Override
	public int getTotalquitnum() { // 总共退出家族的次数
		_xdb_verify_unsafe_();
		return totalquitnum;
	}

	@Override
	public int getHasjoinpartytoday() { // 今天是否参加过仙府聚宴, 0,否，1,是
		_xdb_verify_unsafe_();
		return hasjoinpartytoday;
	}

	@Override
	public int getIsfindbackparty() { // 是否找回昨天的仙府聚宴，0，否，1，是
		_xdb_verify_unsafe_();
		return isfindbackparty;
	}

	@Override
	public void setCurrentfid(long _v_) { // 当前所在的家族id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "currentfid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, currentfid) {
					public void rollback() { currentfid = _xdb_saved; }
				};}});
		currentfid = _v_;
	}

	@Override
	public void setLastquittime(long _v_) { // 最近一次退出家族的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastquittime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastquittime) {
					public void rollback() { lastquittime = _xdb_saved; }
				};}});
		lastquittime = _v_;
	}

	@Override
	public void setTotalquitnum(int _v_) { // 总共退出家族的次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalquitnum") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, totalquitnum) {
					public void rollback() { totalquitnum = _xdb_saved; }
				};}});
		totalquitnum = _v_;
	}

	@Override
	public void setHasjoinpartytoday(int _v_) { // 今天是否参加过仙府聚宴, 0,否，1,是
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "hasjoinpartytoday") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, hasjoinpartytoday) {
					public void rollback() { hasjoinpartytoday = _xdb_saved; }
				};}});
		hasjoinpartytoday = _v_;
	}

	@Override
	public void setIsfindbackparty(int _v_) { // 是否找回昨天的仙府聚宴，0，否，1，是
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "isfindbackparty") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, isfindbackparty) {
					public void rollback() { isfindbackparty = _xdb_saved; }
				};}});
		isfindbackparty = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleFamily _o_ = null;
		if ( _o1_ instanceof RoleFamily ) _o_ = (RoleFamily)_o1_;
		else if ( _o1_ instanceof RoleFamily.Const ) _o_ = ((RoleFamily.Const)_o1_).nThis();
		else return false;
		if (currentfid != _o_.currentfid) return false;
		if (!requestedfamily.equals(_o_.requestedfamily)) return false;
		if (lastquittime != _o_.lastquittime) return false;
		if (totalquitnum != _o_.totalquitnum) return false;
		if (hasjoinpartytoday != _o_.hasjoinpartytoday) return false;
		if (isfindbackparty != _o_.isfindbackparty) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += currentfid;
		_h_ += requestedfamily.hashCode();
		_h_ += lastquittime;
		_h_ += totalquitnum;
		_h_ += hasjoinpartytoday;
		_h_ += isfindbackparty;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(currentfid);
		_sb_.append(",");
		_sb_.append(requestedfamily);
		_sb_.append(",");
		_sb_.append(lastquittime);
		_sb_.append(",");
		_sb_.append(totalquitnum);
		_sb_.append(",");
		_sb_.append(hasjoinpartytoday);
		_sb_.append(",");
		_sb_.append(isfindbackparty);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("currentfid"));
		lb.add(new xdb.logs.ListenableMap().setVarName("requestedfamily"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastquittime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalquitnum"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("hasjoinpartytoday"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isfindbackparty"));
		return lb;
	}

	private class Const implements xbean.RoleFamily {
		RoleFamily nThis() {
			return RoleFamily.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleFamily copy() {
			return RoleFamily.this.copy();
		}

		@Override
		public xbean.RoleFamily toData() {
			return RoleFamily.this.toData();
		}

		public xbean.RoleFamily toBean() {
			return RoleFamily.this.toBean();
		}

		@Override
		public xbean.RoleFamily toDataIf() {
			return RoleFamily.this.toDataIf();
		}

		public xbean.RoleFamily toBeanIf() {
			return RoleFamily.this.toBeanIf();
		}

		@Override
		public long getCurrentfid() { // 当前所在的家族id
			_xdb_verify_unsafe_();
			return currentfid;
		}

		@Override
		public java.util.Map<Long, Long> getRequestedfamily() { // 申请过的家族信息列表
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(requestedfamily);
		}

		@Override
		public java.util.Map<Long, Long> getRequestedfamilyAsData() { // 申请过的家族信息列表
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> requestedfamily;
			RoleFamily _o_ = RoleFamily.this;
			requestedfamily = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestedfamily.entrySet())
				requestedfamily.put(_e_.getKey(), _e_.getValue());
			return requestedfamily;
		}

		@Override
		public long getLastquittime() { // 最近一次退出家族的时间
			_xdb_verify_unsafe_();
			return lastquittime;
		}

		@Override
		public int getTotalquitnum() { // 总共退出家族的次数
			_xdb_verify_unsafe_();
			return totalquitnum;
		}

		@Override
		public int getHasjoinpartytoday() { // 今天是否参加过仙府聚宴, 0,否，1,是
			_xdb_verify_unsafe_();
			return hasjoinpartytoday;
		}

		@Override
		public int getIsfindbackparty() { // 是否找回昨天的仙府聚宴，0，否，1，是
			_xdb_verify_unsafe_();
			return isfindbackparty;
		}

		@Override
		public void setCurrentfid(long _v_) { // 当前所在的家族id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastquittime(long _v_) { // 最近一次退出家族的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalquitnum(int _v_) { // 总共退出家族的次数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHasjoinpartytoday(int _v_) { // 今天是否参加过仙府聚宴, 0,否，1,是
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsfindbackparty(int _v_) { // 是否找回昨天的仙府聚宴，0，否，1，是
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
			return RoleFamily.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleFamily.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleFamily.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleFamily.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleFamily.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleFamily.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleFamily.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleFamily.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleFamily.this.toString();
		}

	}

	public static final class Data implements xbean.RoleFamily {
		private long currentfid; // 当前所在的家族id
		private java.util.HashMap<Long, Long> requestedfamily; // 申请过的家族信息列表
		private long lastquittime; // 最近一次退出家族的时间
		private int totalquitnum; // 总共退出家族的次数
		private int hasjoinpartytoday; // 今天是否参加过仙府聚宴, 0,否，1,是
		private int isfindbackparty; // 是否找回昨天的仙府聚宴，0，否，1，是

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			requestedfamily = new java.util.HashMap<Long, Long>();
		}

		Data(xbean.RoleFamily _o1_) {
			if (_o1_ instanceof RoleFamily) assign((RoleFamily)_o1_);
			else if (_o1_ instanceof RoleFamily.Data) assign((RoleFamily.Data)_o1_);
			else if (_o1_ instanceof RoleFamily.Const) assign(((RoleFamily.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleFamily _o_) {
			currentfid = _o_.currentfid;
			requestedfamily = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestedfamily.entrySet())
				requestedfamily.put(_e_.getKey(), _e_.getValue());
			lastquittime = _o_.lastquittime;
			totalquitnum = _o_.totalquitnum;
			hasjoinpartytoday = _o_.hasjoinpartytoday;
			isfindbackparty = _o_.isfindbackparty;
		}

		private void assign(RoleFamily.Data _o_) {
			currentfid = _o_.currentfid;
			requestedfamily = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestedfamily.entrySet())
				requestedfamily.put(_e_.getKey(), _e_.getValue());
			lastquittime = _o_.lastquittime;
			totalquitnum = _o_.totalquitnum;
			hasjoinpartytoday = _o_.hasjoinpartytoday;
			isfindbackparty = _o_.isfindbackparty;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)(10240|  1));_os_.marshal(currentfid);
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(requestedfamily.size());
for (java.util.Map.Entry<Long, Long> _e_ : requestedfamily.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  5));_os_.marshal(lastquittime);
	_os_.marshal((short)( 8192|  6));_os_.marshal(totalquitnum);
	_os_.marshal((short)( 8192|  9));_os_.marshal(hasjoinpartytoday);
	_os_.marshal((short)( 8192| 10));_os_.marshal(isfindbackparty);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):currentfid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):currentfid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):currentfid = _os_.unmarshal_int();
					break;
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		requestedfamily = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		requestedfamily.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (10240|  5):lastquittime = _os_.unmarshal_long();
					break;
					case ( 6144|  5):lastquittime = _os_.unmarshal_short();
					break;
					case ( 8192|  5):lastquittime = _os_.unmarshal_int();
					break;
					case ( 8192|  6):totalquitnum = _os_.unmarshal_int();
					break;
					case ( 6144|  6):totalquitnum = _os_.unmarshal_short();
					break;
					case ( 8192|  9):hasjoinpartytoday = _os_.unmarshal_int();
					break;
					case ( 6144|  9):hasjoinpartytoday = _os_.unmarshal_short();
					break;
					case ( 8192| 10):isfindbackparty = _os_.unmarshal_int();
					break;
					case ( 6144| 10):isfindbackparty = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleFamily copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleFamily toData() {
			return new Data(this);
		}

		public xbean.RoleFamily toBean() {
			return new RoleFamily(this, null, null);
		}

		@Override
		public xbean.RoleFamily toDataIf() {
			return this;
		}

		public xbean.RoleFamily toBeanIf() {
			return new RoleFamily(this, null, null);
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
		public long getCurrentfid() { // 当前所在的家族id
			return currentfid;
		}

		@Override
		public java.util.Map<Long, Long> getRequestedfamily() { // 申请过的家族信息列表
			return requestedfamily;
		}

		@Override
		public java.util.Map<Long, Long> getRequestedfamilyAsData() { // 申请过的家族信息列表
			return requestedfamily;
		}

		@Override
		public long getLastquittime() { // 最近一次退出家族的时间
			return lastquittime;
		}

		@Override
		public int getTotalquitnum() { // 总共退出家族的次数
			return totalquitnum;
		}

		@Override
		public int getHasjoinpartytoday() { // 今天是否参加过仙府聚宴, 0,否，1,是
			return hasjoinpartytoday;
		}

		@Override
		public int getIsfindbackparty() { // 是否找回昨天的仙府聚宴，0，否，1，是
			return isfindbackparty;
		}

		@Override
		public void setCurrentfid(long _v_) { // 当前所在的家族id
			currentfid = _v_;
		}

		@Override
		public void setLastquittime(long _v_) { // 最近一次退出家族的时间
			lastquittime = _v_;
		}

		@Override
		public void setTotalquitnum(int _v_) { // 总共退出家族的次数
			totalquitnum = _v_;
		}

		@Override
		public void setHasjoinpartytoday(int _v_) { // 今天是否参加过仙府聚宴, 0,否，1,是
			hasjoinpartytoday = _v_;
		}

		@Override
		public void setIsfindbackparty(int _v_) { // 是否找回昨天的仙府聚宴，0，否，1，是
			isfindbackparty = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleFamily.Data)) return false;
			RoleFamily.Data _o_ = (RoleFamily.Data) _o1_;
			if (currentfid != _o_.currentfid) return false;
			if (!requestedfamily.equals(_o_.requestedfamily)) return false;
			if (lastquittime != _o_.lastquittime) return false;
			if (totalquitnum != _o_.totalquitnum) return false;
			if (hasjoinpartytoday != _o_.hasjoinpartytoday) return false;
			if (isfindbackparty != _o_.isfindbackparty) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += currentfid;
			_h_ += requestedfamily.hashCode();
			_h_ += lastquittime;
			_h_ += totalquitnum;
			_h_ += hasjoinpartytoday;
			_h_ += isfindbackparty;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(currentfid);
			_sb_.append(",");
			_sb_.append(requestedfamily);
			_sb_.append(",");
			_sb_.append(lastquittime);
			_sb_.append(",");
			_sb_.append(totalquitnum);
			_sb_.append(",");
			_sb_.append(hasjoinpartytoday);
			_sb_.append(",");
			_sb_.append(isfindbackparty);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
