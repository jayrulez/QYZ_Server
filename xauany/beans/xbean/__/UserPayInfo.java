
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class UserPayInfo extends xdb.XBean implements xbean.UserPayInfo {
	private long totalpay; // 
	private long totalyunbao; // 
	private long totalbindyuanbao; // 
	private long totalvipexp; // 
	private boolean hasgotreturn; // 
	private long roleid; // 

	@Override
	public void _reset_unsafe_() {
		totalpay = 0L;
		totalyunbao = 0L;
		totalbindyuanbao = 0L;
		totalvipexp = 0L;
		hasgotreturn = false;
		roleid = 0L;
	}

	UserPayInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public UserPayInfo() {
		this(0, null, null);
	}

	public UserPayInfo(UserPayInfo _o_) {
		this(_o_, null, null);
	}

	UserPayInfo(xbean.UserPayInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof UserPayInfo) assign((UserPayInfo)_o1_);
		else if (_o1_ instanceof UserPayInfo.Data) assign((UserPayInfo.Data)_o1_);
		else if (_o1_ instanceof UserPayInfo.Const) assign(((UserPayInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(UserPayInfo _o_) {
		_o_._xdb_verify_unsafe_();
		totalpay = _o_.totalpay;
		totalyunbao = _o_.totalyunbao;
		totalbindyuanbao = _o_.totalbindyuanbao;
		totalvipexp = _o_.totalvipexp;
		hasgotreturn = _o_.hasgotreturn;
		roleid = _o_.roleid;
	}

	private void assign(UserPayInfo.Data _o_) {
		totalpay = _o_.totalpay;
		totalyunbao = _o_.totalyunbao;
		totalbindyuanbao = _o_.totalbindyuanbao;
		totalvipexp = _o_.totalvipexp;
		hasgotreturn = _o_.hasgotreturn;
		roleid = _o_.roleid;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)(10240|  1));_os_.marshal(totalpay);
    _os_.marshal((short)(10240|  4));_os_.marshal(totalyunbao);
    _os_.marshal((short)(10240|  5));_os_.marshal(totalbindyuanbao);
    _os_.marshal((short)(10240|  6));_os_.marshal(totalvipexp);
    _os_.marshal((short)( 2048|  2));_os_.marshal(hasgotreturn);
    _os_.marshal((short)(10240|  3));_os_.marshal(roleid);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):totalpay = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):totalpay = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):totalpay = _os_.unmarshal_int();
    				break;
    				case (10240|  4):totalyunbao = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):totalyunbao = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):totalyunbao = _os_.unmarshal_int();
    				break;
    				case (10240|  5):totalbindyuanbao = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):totalbindyuanbao = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):totalbindyuanbao = _os_.unmarshal_int();
    				break;
    				case (10240|  6):totalvipexp = _os_.unmarshal_long();
    				break;
    				case ( 6144|  6):totalvipexp = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):totalvipexp = _os_.unmarshal_int();
    				break;
    				case ( 2048|  2):hasgotreturn = _os_.unmarshal_boolean();
    				break;
    				case (10240|  3):roleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):roleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):roleid = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.UserPayInfo copy() {
		_xdb_verify_unsafe_();
		return new UserPayInfo(this);
	}

	@Override
	public xbean.UserPayInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.UserPayInfo toBean() {
		_xdb_verify_unsafe_();
		return new UserPayInfo(this); // same as copy()
	}

	@Override
	public xbean.UserPayInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.UserPayInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getTotalpay() { // 
		_xdb_verify_unsafe_();
		return totalpay;
	}

	@Override
	public long getTotalyunbao() { // 
		_xdb_verify_unsafe_();
		return totalyunbao;
	}

	@Override
	public long getTotalbindyuanbao() { // 
		_xdb_verify_unsafe_();
		return totalbindyuanbao;
	}

	@Override
	public long getTotalvipexp() { // 
		_xdb_verify_unsafe_();
		return totalvipexp;
	}

	@Override
	public boolean getHasgotreturn() { // 
		_xdb_verify_unsafe_();
		return hasgotreturn;
	}

	@Override
	public long getRoleid() { // 
		_xdb_verify_unsafe_();
		return roleid;
	}

	@Override
	public void setTotalpay(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalpay") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalpay) {
					public void rollback() { totalpay = _xdb_saved; }
				};}});
		totalpay = _v_;
	}

	@Override
	public void setTotalyunbao(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalyunbao") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalyunbao) {
					public void rollback() { totalyunbao = _xdb_saved; }
				};}});
		totalyunbao = _v_;
	}

	@Override
	public void setTotalbindyuanbao(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalbindyuanbao") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalbindyuanbao) {
					public void rollback() { totalbindyuanbao = _xdb_saved; }
				};}});
		totalbindyuanbao = _v_;
	}

	@Override
	public void setTotalvipexp(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalvipexp") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalvipexp) {
					public void rollback() { totalvipexp = _xdb_saved; }
				};}});
		totalvipexp = _v_;
	}

	@Override
	public void setHasgotreturn(boolean _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "hasgotreturn") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, hasgotreturn) {
					public void rollback() { hasgotreturn = _xdb_saved; }
				};}});
		hasgotreturn = _v_;
	}

	@Override
	public void setRoleid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleid) {
					public void rollback() { roleid = _xdb_saved; }
				};}});
		roleid = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		UserPayInfo _o_ = null;
		if ( _o1_ instanceof UserPayInfo ) _o_ = (UserPayInfo)_o1_;
		else if ( _o1_ instanceof UserPayInfo.Const ) _o_ = ((UserPayInfo.Const)_o1_).nThis();
		else return false;
		if (totalpay != _o_.totalpay) return false;
		if (totalyunbao != _o_.totalyunbao) return false;
		if (totalbindyuanbao != _o_.totalbindyuanbao) return false;
		if (totalvipexp != _o_.totalvipexp) return false;
		if (hasgotreturn != _o_.hasgotreturn) return false;
		if (roleid != _o_.roleid) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += totalpay;
		_h_ += totalyunbao;
		_h_ += totalbindyuanbao;
		_h_ += totalvipexp;
		_h_ += hasgotreturn ? 1231 : 1237;
		_h_ += roleid;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(totalpay);
		_sb_.append(",");
		_sb_.append(totalyunbao);
		_sb_.append(",");
		_sb_.append(totalbindyuanbao);
		_sb_.append(",");
		_sb_.append(totalvipexp);
		_sb_.append(",");
		_sb_.append(hasgotreturn);
		_sb_.append(",");
		_sb_.append(roleid);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalpay"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalyunbao"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalbindyuanbao"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalvipexp"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("hasgotreturn"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid"));
		return lb;
	}

	private class Const implements xbean.UserPayInfo {
		UserPayInfo nThis() {
			return UserPayInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.UserPayInfo copy() {
			return UserPayInfo.this.copy();
		}

		@Override
		public xbean.UserPayInfo toData() {
			return UserPayInfo.this.toData();
		}

		public xbean.UserPayInfo toBean() {
			return UserPayInfo.this.toBean();
		}

		@Override
		public xbean.UserPayInfo toDataIf() {
			return UserPayInfo.this.toDataIf();
		}

		public xbean.UserPayInfo toBeanIf() {
			return UserPayInfo.this.toBeanIf();
		}

		@Override
		public long getTotalpay() { // 
			_xdb_verify_unsafe_();
			return totalpay;
		}

		@Override
		public long getTotalyunbao() { // 
			_xdb_verify_unsafe_();
			return totalyunbao;
		}

		@Override
		public long getTotalbindyuanbao() { // 
			_xdb_verify_unsafe_();
			return totalbindyuanbao;
		}

		@Override
		public long getTotalvipexp() { // 
			_xdb_verify_unsafe_();
			return totalvipexp;
		}

		@Override
		public boolean getHasgotreturn() { // 
			_xdb_verify_unsafe_();
			return hasgotreturn;
		}

		@Override
		public long getRoleid() { // 
			_xdb_verify_unsafe_();
			return roleid;
		}

		@Override
		public void setTotalpay(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalyunbao(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalbindyuanbao(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalvipexp(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHasgotreturn(boolean _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRoleid(long _v_) { // 
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
			return UserPayInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return UserPayInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return UserPayInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return UserPayInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return UserPayInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return UserPayInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return UserPayInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return UserPayInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return UserPayInfo.this.toString();
		}

	}

	public static final class Data implements xbean.UserPayInfo {
		private long totalpay; // 
		private long totalyunbao; // 
		private long totalbindyuanbao; // 
		private long totalvipexp; // 
		private boolean hasgotreturn; // 
		private long roleid; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.UserPayInfo _o1_) {
			if (_o1_ instanceof UserPayInfo) assign((UserPayInfo)_o1_);
			else if (_o1_ instanceof UserPayInfo.Data) assign((UserPayInfo.Data)_o1_);
			else if (_o1_ instanceof UserPayInfo.Const) assign(((UserPayInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(UserPayInfo _o_) {
			totalpay = _o_.totalpay;
			totalyunbao = _o_.totalyunbao;
			totalbindyuanbao = _o_.totalbindyuanbao;
			totalvipexp = _o_.totalvipexp;
			hasgotreturn = _o_.hasgotreturn;
			roleid = _o_.roleid;
		}

		private void assign(UserPayInfo.Data _o_) {
			totalpay = _o_.totalpay;
			totalyunbao = _o_.totalyunbao;
			totalbindyuanbao = _o_.totalbindyuanbao;
			totalvipexp = _o_.totalvipexp;
			hasgotreturn = _o_.hasgotreturn;
			roleid = _o_.roleid;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)(10240|  1));_os_.marshal(totalpay);
	_os_.marshal((short)(10240|  4));_os_.marshal(totalyunbao);
	_os_.marshal((short)(10240|  5));_os_.marshal(totalbindyuanbao);
	_os_.marshal((short)(10240|  6));_os_.marshal(totalvipexp);
	_os_.marshal((short)( 2048|  2));_os_.marshal(hasgotreturn);
	_os_.marshal((short)(10240|  3));_os_.marshal(roleid);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):totalpay = _os_.unmarshal_long();
					break;
					case ( 6144|  1):totalpay = _os_.unmarshal_short();
					break;
					case ( 8192|  1):totalpay = _os_.unmarshal_int();
					break;
					case (10240|  4):totalyunbao = _os_.unmarshal_long();
					break;
					case ( 6144|  4):totalyunbao = _os_.unmarshal_short();
					break;
					case ( 8192|  4):totalyunbao = _os_.unmarshal_int();
					break;
					case (10240|  5):totalbindyuanbao = _os_.unmarshal_long();
					break;
					case ( 6144|  5):totalbindyuanbao = _os_.unmarshal_short();
					break;
					case ( 8192|  5):totalbindyuanbao = _os_.unmarshal_int();
					break;
					case (10240|  6):totalvipexp = _os_.unmarshal_long();
					break;
					case ( 6144|  6):totalvipexp = _os_.unmarshal_short();
					break;
					case ( 8192|  6):totalvipexp = _os_.unmarshal_int();
					break;
					case ( 2048|  2):hasgotreturn = _os_.unmarshal_boolean();
					break;
					case (10240|  3):roleid = _os_.unmarshal_long();
					break;
					case ( 6144|  3):roleid = _os_.unmarshal_short();
					break;
					case ( 8192|  3):roleid = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.UserPayInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.UserPayInfo toData() {
			return new Data(this);
		}

		public xbean.UserPayInfo toBean() {
			return new UserPayInfo(this, null, null);
		}

		@Override
		public xbean.UserPayInfo toDataIf() {
			return this;
		}

		public xbean.UserPayInfo toBeanIf() {
			return new UserPayInfo(this, null, null);
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
		public long getTotalpay() { // 
			return totalpay;
		}

		@Override
		public long getTotalyunbao() { // 
			return totalyunbao;
		}

		@Override
		public long getTotalbindyuanbao() { // 
			return totalbindyuanbao;
		}

		@Override
		public long getTotalvipexp() { // 
			return totalvipexp;
		}

		@Override
		public boolean getHasgotreturn() { // 
			return hasgotreturn;
		}

		@Override
		public long getRoleid() { // 
			return roleid;
		}

		@Override
		public void setTotalpay(long _v_) { // 
			totalpay = _v_;
		}

		@Override
		public void setTotalyunbao(long _v_) { // 
			totalyunbao = _v_;
		}

		@Override
		public void setTotalbindyuanbao(long _v_) { // 
			totalbindyuanbao = _v_;
		}

		@Override
		public void setTotalvipexp(long _v_) { // 
			totalvipexp = _v_;
		}

		@Override
		public void setHasgotreturn(boolean _v_) { // 
			hasgotreturn = _v_;
		}

		@Override
		public void setRoleid(long _v_) { // 
			roleid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof UserPayInfo.Data)) return false;
			UserPayInfo.Data _o_ = (UserPayInfo.Data) _o1_;
			if (totalpay != _o_.totalpay) return false;
			if (totalyunbao != _o_.totalyunbao) return false;
			if (totalbindyuanbao != _o_.totalbindyuanbao) return false;
			if (totalvipexp != _o_.totalvipexp) return false;
			if (hasgotreturn != _o_.hasgotreturn) return false;
			if (roleid != _o_.roleid) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += totalpay;
			_h_ += totalyunbao;
			_h_ += totalbindyuanbao;
			_h_ += totalvipexp;
			_h_ += hasgotreturn ? 1231 : 1237;
			_h_ += roleid;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(totalpay);
			_sb_.append(",");
			_sb_.append(totalyunbao);
			_sb_.append(",");
			_sb_.append(totalbindyuanbao);
			_sb_.append(",");
			_sb_.append(totalvipexp);
			_sb_.append(",");
			_sb_.append(hasgotreturn);
			_sb_.append(",");
			_sb_.append(roleid);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
