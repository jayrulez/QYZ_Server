
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class FamilyLogReport extends xdb.XBean implements xbean.FamilyLogReport {
	private int actiontype; // 家族日志类型，入帮，升职等等,参考协议中的bean定义
	private long roleid; // 角色id
	private long actiontime; // 日志记录的时间
	private int number; // 操作对应的数值

	@Override
	public void _reset_unsafe_() {
		actiontype = 0;
		roleid = 0L;
		actiontime = 0L;
		number = 0;
	}

	FamilyLogReport(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public FamilyLogReport() {
		this(0, null, null);
	}

	public FamilyLogReport(FamilyLogReport _o_) {
		this(_o_, null, null);
	}

	FamilyLogReport(xbean.FamilyLogReport _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof FamilyLogReport) assign((FamilyLogReport)_o1_);
		else if (_o1_ instanceof FamilyLogReport.Data) assign((FamilyLogReport.Data)_o1_);
		else if (_o1_ instanceof FamilyLogReport.Const) assign(((FamilyLogReport.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(FamilyLogReport _o_) {
		_o_._xdb_verify_unsafe_();
		actiontype = _o_.actiontype;
		roleid = _o_.roleid;
		actiontime = _o_.actiontime;
		number = _o_.number;
	}

	private void assign(FamilyLogReport.Data _o_) {
		actiontype = _o_.actiontype;
		roleid = _o_.roleid;
		actiontime = _o_.actiontime;
		number = _o_.number;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)( 8192|  1));_os_.marshal(actiontype);
    _os_.marshal((short)(10240|  2));_os_.marshal(roleid);
    _os_.marshal((short)(10240|  3));_os_.marshal(actiontime);
    _os_.marshal((short)( 8192|  4));_os_.marshal(number);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):actiontype = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):actiontype = _os_.unmarshal_short();
    				break;
    				case (10240|  2):roleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):roleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):roleid = _os_.unmarshal_int();
    				break;
    				case (10240|  3):actiontime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):actiontime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):actiontime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  4):number = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):number = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.FamilyLogReport copy() {
		_xdb_verify_unsafe_();
		return new FamilyLogReport(this);
	}

	@Override
	public xbean.FamilyLogReport toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyLogReport toBean() {
		_xdb_verify_unsafe_();
		return new FamilyLogReport(this); // same as copy()
	}

	@Override
	public xbean.FamilyLogReport toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyLogReport toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getActiontype() { // 家族日志类型，入帮，升职等等,参考协议中的bean定义
		_xdb_verify_unsafe_();
		return actiontype;
	}

	@Override
	public long getRoleid() { // 角色id
		_xdb_verify_unsafe_();
		return roleid;
	}

	@Override
	public long getActiontime() { // 日志记录的时间
		_xdb_verify_unsafe_();
		return actiontime;
	}

	@Override
	public int getNumber() { // 操作对应的数值
		_xdb_verify_unsafe_();
		return number;
	}

	@Override
	public void setActiontype(int _v_) { // 家族日志类型，入帮，升职等等,参考协议中的bean定义
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "actiontype") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, actiontype) {
					public void rollback() { actiontype = _xdb_saved; }
				};}});
		actiontype = _v_;
	}

	@Override
	public void setRoleid(long _v_) { // 角色id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleid) {
					public void rollback() { roleid = _xdb_saved; }
				};}});
		roleid = _v_;
	}

	@Override
	public void setActiontime(long _v_) { // 日志记录的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "actiontime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, actiontime) {
					public void rollback() { actiontime = _xdb_saved; }
				};}});
		actiontime = _v_;
	}

	@Override
	public void setNumber(int _v_) { // 操作对应的数值
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "number") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, number) {
					public void rollback() { number = _xdb_saved; }
				};}});
		number = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		FamilyLogReport _o_ = null;
		if ( _o1_ instanceof FamilyLogReport ) _o_ = (FamilyLogReport)_o1_;
		else if ( _o1_ instanceof FamilyLogReport.Const ) _o_ = ((FamilyLogReport.Const)_o1_).nThis();
		else return false;
		if (actiontype != _o_.actiontype) return false;
		if (roleid != _o_.roleid) return false;
		if (actiontime != _o_.actiontime) return false;
		if (number != _o_.number) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += actiontype;
		_h_ += roleid;
		_h_ += actiontime;
		_h_ += number;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(actiontype);
		_sb_.append(",");
		_sb_.append(roleid);
		_sb_.append(",");
		_sb_.append(actiontime);
		_sb_.append(",");
		_sb_.append(number);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("actiontype"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("actiontime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("number"));
		return lb;
	}

	private class Const implements xbean.FamilyLogReport {
		FamilyLogReport nThis() {
			return FamilyLogReport.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.FamilyLogReport copy() {
			return FamilyLogReport.this.copy();
		}

		@Override
		public xbean.FamilyLogReport toData() {
			return FamilyLogReport.this.toData();
		}

		public xbean.FamilyLogReport toBean() {
			return FamilyLogReport.this.toBean();
		}

		@Override
		public xbean.FamilyLogReport toDataIf() {
			return FamilyLogReport.this.toDataIf();
		}

		public xbean.FamilyLogReport toBeanIf() {
			return FamilyLogReport.this.toBeanIf();
		}

		@Override
		public int getActiontype() { // 家族日志类型，入帮，升职等等,参考协议中的bean定义
			_xdb_verify_unsafe_();
			return actiontype;
		}

		@Override
		public long getRoleid() { // 角色id
			_xdb_verify_unsafe_();
			return roleid;
		}

		@Override
		public long getActiontime() { // 日志记录的时间
			_xdb_verify_unsafe_();
			return actiontime;
		}

		@Override
		public int getNumber() { // 操作对应的数值
			_xdb_verify_unsafe_();
			return number;
		}

		@Override
		public void setActiontype(int _v_) { // 家族日志类型，入帮，升职等等,参考协议中的bean定义
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRoleid(long _v_) { // 角色id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setActiontime(long _v_) { // 日志记录的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNumber(int _v_) { // 操作对应的数值
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
			return FamilyLogReport.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return FamilyLogReport.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return FamilyLogReport.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return FamilyLogReport.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return FamilyLogReport.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return FamilyLogReport.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return FamilyLogReport.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return FamilyLogReport.this.hashCode();
		}

		@Override
		public String toString() {
			return FamilyLogReport.this.toString();
		}

	}

	public static final class Data implements xbean.FamilyLogReport {
		private int actiontype; // 家族日志类型，入帮，升职等等,参考协议中的bean定义
		private long roleid; // 角色id
		private long actiontime; // 日志记录的时间
		private int number; // 操作对应的数值

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.FamilyLogReport _o1_) {
			if (_o1_ instanceof FamilyLogReport) assign((FamilyLogReport)_o1_);
			else if (_o1_ instanceof FamilyLogReport.Data) assign((FamilyLogReport.Data)_o1_);
			else if (_o1_ instanceof FamilyLogReport.Const) assign(((FamilyLogReport.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(FamilyLogReport _o_) {
			actiontype = _o_.actiontype;
			roleid = _o_.roleid;
			actiontime = _o_.actiontime;
			number = _o_.number;
		}

		private void assign(FamilyLogReport.Data _o_) {
			actiontype = _o_.actiontype;
			roleid = _o_.roleid;
			actiontime = _o_.actiontime;
			number = _o_.number;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)( 8192|  1));_os_.marshal(actiontype);
	_os_.marshal((short)(10240|  2));_os_.marshal(roleid);
	_os_.marshal((short)(10240|  3));_os_.marshal(actiontime);
	_os_.marshal((short)( 8192|  4));_os_.marshal(number);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):actiontype = _os_.unmarshal_int();
					break;
					case ( 6144|  1):actiontype = _os_.unmarshal_short();
					break;
					case (10240|  2):roleid = _os_.unmarshal_long();
					break;
					case ( 6144|  2):roleid = _os_.unmarshal_short();
					break;
					case ( 8192|  2):roleid = _os_.unmarshal_int();
					break;
					case (10240|  3):actiontime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):actiontime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):actiontime = _os_.unmarshal_int();
					break;
					case ( 8192|  4):number = _os_.unmarshal_int();
					break;
					case ( 6144|  4):number = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.FamilyLogReport copy() {
			return new Data(this);
		}

		@Override
		public xbean.FamilyLogReport toData() {
			return new Data(this);
		}

		public xbean.FamilyLogReport toBean() {
			return new FamilyLogReport(this, null, null);
		}

		@Override
		public xbean.FamilyLogReport toDataIf() {
			return this;
		}

		public xbean.FamilyLogReport toBeanIf() {
			return new FamilyLogReport(this, null, null);
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
		public int getActiontype() { // 家族日志类型，入帮，升职等等,参考协议中的bean定义
			return actiontype;
		}

		@Override
		public long getRoleid() { // 角色id
			return roleid;
		}

		@Override
		public long getActiontime() { // 日志记录的时间
			return actiontime;
		}

		@Override
		public int getNumber() { // 操作对应的数值
			return number;
		}

		@Override
		public void setActiontype(int _v_) { // 家族日志类型，入帮，升职等等,参考协议中的bean定义
			actiontype = _v_;
		}

		@Override
		public void setRoleid(long _v_) { // 角色id
			roleid = _v_;
		}

		@Override
		public void setActiontime(long _v_) { // 日志记录的时间
			actiontime = _v_;
		}

		@Override
		public void setNumber(int _v_) { // 操作对应的数值
			number = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof FamilyLogReport.Data)) return false;
			FamilyLogReport.Data _o_ = (FamilyLogReport.Data) _o1_;
			if (actiontype != _o_.actiontype) return false;
			if (roleid != _o_.roleid) return false;
			if (actiontime != _o_.actiontime) return false;
			if (number != _o_.number) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += actiontype;
			_h_ += roleid;
			_h_ += actiontime;
			_h_ += number;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(actiontype);
			_sb_.append(",");
			_sb_.append(roleid);
			_sb_.append(",");
			_sb_.append(actiontime);
			_sb_.append(",");
			_sb_.append(number);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
