
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class HuiWuChampion extends xdb.XBean implements xbean.HuiWuChampion {
	private long roleid; // 
	private int worshipnum; // 
	private String awardword; // 

	@Override
	public void _reset_unsafe_() {
		roleid = 0L;
		worshipnum = 0;
		awardword = "";
	}

	HuiWuChampion(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		awardword = "";
	}

	public HuiWuChampion() {
		this(0, null, null);
	}

	public HuiWuChampion(HuiWuChampion _o_) {
		this(_o_, null, null);
	}

	HuiWuChampion(xbean.HuiWuChampion _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof HuiWuChampion) assign((HuiWuChampion)_o1_);
		else if (_o1_ instanceof HuiWuChampion.Data) assign((HuiWuChampion.Data)_o1_);
		else if (_o1_ instanceof HuiWuChampion.Const) assign(((HuiWuChampion.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(HuiWuChampion _o_) {
		_o_._xdb_verify_unsafe_();
		roleid = _o_.roleid;
		worshipnum = _o_.worshipnum;
		awardword = _o_.awardword;
	}

	private void assign(HuiWuChampion.Data _o_) {
		roleid = _o_.roleid;
		worshipnum = _o_.worshipnum;
		awardword = _o_.awardword;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)(10240|  3));_os_.marshal(roleid);
    _os_.marshal((short)( 8192|  1));_os_.marshal(worshipnum);
    _os_.marshal((short)(18432|  2));_os_.marshal(awardword, xdb.Const.IO_CHARSET);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  3):roleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):roleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):roleid = _os_.unmarshal_int();
    				break;
    				case ( 8192|  1):worshipnum = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):worshipnum = _os_.unmarshal_short();
    				break;
    				case (18432|  2):awardword = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.HuiWuChampion copy() {
		_xdb_verify_unsafe_();
		return new HuiWuChampion(this);
	}

	@Override
	public xbean.HuiWuChampion toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuChampion toBean() {
		_xdb_verify_unsafe_();
		return new HuiWuChampion(this); // same as copy()
	}

	@Override
	public xbean.HuiWuChampion toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuChampion toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getRoleid() { // 
		_xdb_verify_unsafe_();
		return roleid;
	}

	@Override
	public int getWorshipnum() { // 
		_xdb_verify_unsafe_();
		return worshipnum;
	}

	@Override
	public String getAwardword() { // 
		_xdb_verify_unsafe_();
		return awardword;
	}

	@Override
	public com.goldhuman.Common.Octets getAwardwordOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getAwardword(), xdb.Const.IO_CHARSET);
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
	public void setWorshipnum(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "worshipnum") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, worshipnum) {
					public void rollback() { worshipnum = _xdb_saved; }
				};}});
		worshipnum = _v_;
	}

	@Override
	public void setAwardword(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "awardword") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, awardword) {
					public void rollback() { awardword = _xdb_saved; }
				};}});
		awardword = _v_;
	}

	@Override
	public void setAwardwordOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setAwardword(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		HuiWuChampion _o_ = null;
		if ( _o1_ instanceof HuiWuChampion ) _o_ = (HuiWuChampion)_o1_;
		else if ( _o1_ instanceof HuiWuChampion.Const ) _o_ = ((HuiWuChampion.Const)_o1_).nThis();
		else return false;
		if (roleid != _o_.roleid) return false;
		if (worshipnum != _o_.worshipnum) return false;
		if (!awardword.equals(_o_.awardword)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += roleid;
		_h_ += worshipnum;
		_h_ += awardword.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid);
		_sb_.append(",");
		_sb_.append(worshipnum);
		_sb_.append(",");
		_sb_.append("'").append(awardword).append("'");
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("worshipnum"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("awardword"));
		return lb;
	}

	private class Const implements xbean.HuiWuChampion {
		HuiWuChampion nThis() {
			return HuiWuChampion.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.HuiWuChampion copy() {
			return HuiWuChampion.this.copy();
		}

		@Override
		public xbean.HuiWuChampion toData() {
			return HuiWuChampion.this.toData();
		}

		public xbean.HuiWuChampion toBean() {
			return HuiWuChampion.this.toBean();
		}

		@Override
		public xbean.HuiWuChampion toDataIf() {
			return HuiWuChampion.this.toDataIf();
		}

		public xbean.HuiWuChampion toBeanIf() {
			return HuiWuChampion.this.toBeanIf();
		}

		@Override
		public long getRoleid() { // 
			_xdb_verify_unsafe_();
			return roleid;
		}

		@Override
		public int getWorshipnum() { // 
			_xdb_verify_unsafe_();
			return worshipnum;
		}

		@Override
		public String getAwardword() { // 
			_xdb_verify_unsafe_();
			return awardword;
		}

		@Override
		public com.goldhuman.Common.Octets getAwardwordOctets() { // 
			_xdb_verify_unsafe_();
			return HuiWuChampion.this.getAwardwordOctets();
		}

		@Override
		public void setRoleid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setWorshipnum(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAwardword(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAwardwordOctets(com.goldhuman.Common.Octets _v_) { // 
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
			return HuiWuChampion.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return HuiWuChampion.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return HuiWuChampion.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return HuiWuChampion.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return HuiWuChampion.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return HuiWuChampion.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return HuiWuChampion.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return HuiWuChampion.this.hashCode();
		}

		@Override
		public String toString() {
			return HuiWuChampion.this.toString();
		}

	}

	public static final class Data implements xbean.HuiWuChampion {
		private long roleid; // 
		private int worshipnum; // 
		private String awardword; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			awardword = "";
		}

		Data(xbean.HuiWuChampion _o1_) {
			if (_o1_ instanceof HuiWuChampion) assign((HuiWuChampion)_o1_);
			else if (_o1_ instanceof HuiWuChampion.Data) assign((HuiWuChampion.Data)_o1_);
			else if (_o1_ instanceof HuiWuChampion.Const) assign(((HuiWuChampion.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(HuiWuChampion _o_) {
			roleid = _o_.roleid;
			worshipnum = _o_.worshipnum;
			awardword = _o_.awardword;
		}

		private void assign(HuiWuChampion.Data _o_) {
			roleid = _o_.roleid;
			worshipnum = _o_.worshipnum;
			awardword = _o_.awardword;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)(10240|  3));_os_.marshal(roleid);
	_os_.marshal((short)( 8192|  1));_os_.marshal(worshipnum);
	_os_.marshal((short)(18432|  2));_os_.marshal(awardword, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  3):roleid = _os_.unmarshal_long();
					break;
					case ( 6144|  3):roleid = _os_.unmarshal_short();
					break;
					case ( 8192|  3):roleid = _os_.unmarshal_int();
					break;
					case ( 8192|  1):worshipnum = _os_.unmarshal_int();
					break;
					case ( 6144|  1):worshipnum = _os_.unmarshal_short();
					break;
					case (18432|  2):awardword = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.HuiWuChampion copy() {
			return new Data(this);
		}

		@Override
		public xbean.HuiWuChampion toData() {
			return new Data(this);
		}

		public xbean.HuiWuChampion toBean() {
			return new HuiWuChampion(this, null, null);
		}

		@Override
		public xbean.HuiWuChampion toDataIf() {
			return this;
		}

		public xbean.HuiWuChampion toBeanIf() {
			return new HuiWuChampion(this, null, null);
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
		public long getRoleid() { // 
			return roleid;
		}

		@Override
		public int getWorshipnum() { // 
			return worshipnum;
		}

		@Override
		public String getAwardword() { // 
			return awardword;
		}

		@Override
		public com.goldhuman.Common.Octets getAwardwordOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getAwardword(), xdb.Const.IO_CHARSET);
		}

		@Override
		public void setRoleid(long _v_) { // 
			roleid = _v_;
		}

		@Override
		public void setWorshipnum(int _v_) { // 
			worshipnum = _v_;
		}

		@Override
		public void setAwardword(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			awardword = _v_;
		}

		@Override
		public void setAwardwordOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setAwardword(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof HuiWuChampion.Data)) return false;
			HuiWuChampion.Data _o_ = (HuiWuChampion.Data) _o1_;
			if (roleid != _o_.roleid) return false;
			if (worshipnum != _o_.worshipnum) return false;
			if (!awardword.equals(_o_.awardword)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += roleid;
			_h_ += worshipnum;
			_h_ += awardword.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(roleid);
			_sb_.append(",");
			_sb_.append(worshipnum);
			_sb_.append(",");
			_sb_.append("'").append(awardword).append("'");
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
