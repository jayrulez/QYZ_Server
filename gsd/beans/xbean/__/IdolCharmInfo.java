
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class IdolCharmInfo extends xdb.XBean implements xbean.IdolCharmInfo {
	private int charm; // 
	private long guardid; // 
	private int guarddegree; // 
	private long guardtime; // 

	@Override
	public void _reset_unsafe_() {
		charm = 0;
		guardid = 0L;
		guarddegree = 0;
		guardtime = 0L;
	}

	IdolCharmInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public IdolCharmInfo() {
		this(0, null, null);
	}

	public IdolCharmInfo(IdolCharmInfo _o_) {
		this(_o_, null, null);
	}

	IdolCharmInfo(xbean.IdolCharmInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof IdolCharmInfo) assign((IdolCharmInfo)_o1_);
		else if (_o1_ instanceof IdolCharmInfo.Data) assign((IdolCharmInfo.Data)_o1_);
		else if (_o1_ instanceof IdolCharmInfo.Const) assign(((IdolCharmInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(IdolCharmInfo _o_) {
		_o_._xdb_verify_unsafe_();
		charm = _o_.charm;
		guardid = _o_.guardid;
		guarddegree = _o_.guarddegree;
		guardtime = _o_.guardtime;
	}

	private void assign(IdolCharmInfo.Data _o_) {
		charm = _o_.charm;
		guardid = _o_.guardid;
		guarddegree = _o_.guarddegree;
		guardtime = _o_.guardtime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)( 8192|  1));_os_.marshal(charm);
    _os_.marshal((short)(10240|  2));_os_.marshal(guardid);
    _os_.marshal((short)( 8192|  4));_os_.marshal(guarddegree);
    _os_.marshal((short)(10240|  5));_os_.marshal(guardtime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):charm = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):charm = _os_.unmarshal_short();
    				break;
    				case (10240|  2):guardid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):guardid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):guardid = _os_.unmarshal_int();
    				break;
    				case ( 8192|  4):guarddegree = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):guarddegree = _os_.unmarshal_short();
    				break;
    				case (10240|  5):guardtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):guardtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):guardtime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.IdolCharmInfo copy() {
		_xdb_verify_unsafe_();
		return new IdolCharmInfo(this);
	}

	@Override
	public xbean.IdolCharmInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IdolCharmInfo toBean() {
		_xdb_verify_unsafe_();
		return new IdolCharmInfo(this); // same as copy()
	}

	@Override
	public xbean.IdolCharmInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.IdolCharmInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getCharm() { // 
		_xdb_verify_unsafe_();
		return charm;
	}

	@Override
	public long getGuardid() { // 
		_xdb_verify_unsafe_();
		return guardid;
	}

	@Override
	public int getGuarddegree() { // 
		_xdb_verify_unsafe_();
		return guarddegree;
	}

	@Override
	public long getGuardtime() { // 
		_xdb_verify_unsafe_();
		return guardtime;
	}

	@Override
	public void setCharm(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "charm") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, charm) {
					public void rollback() { charm = _xdb_saved; }
				};}});
		charm = _v_;
	}

	@Override
	public void setGuardid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "guardid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, guardid) {
					public void rollback() { guardid = _xdb_saved; }
				};}});
		guardid = _v_;
	}

	@Override
	public void setGuarddegree(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "guarddegree") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, guarddegree) {
					public void rollback() { guarddegree = _xdb_saved; }
				};}});
		guarddegree = _v_;
	}

	@Override
	public void setGuardtime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "guardtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, guardtime) {
					public void rollback() { guardtime = _xdb_saved; }
				};}});
		guardtime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		IdolCharmInfo _o_ = null;
		if ( _o1_ instanceof IdolCharmInfo ) _o_ = (IdolCharmInfo)_o1_;
		else if ( _o1_ instanceof IdolCharmInfo.Const ) _o_ = ((IdolCharmInfo.Const)_o1_).nThis();
		else return false;
		if (charm != _o_.charm) return false;
		if (guardid != _o_.guardid) return false;
		if (guarddegree != _o_.guarddegree) return false;
		if (guardtime != _o_.guardtime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += charm;
		_h_ += guardid;
		_h_ += guarddegree;
		_h_ += guardtime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(charm);
		_sb_.append(",");
		_sb_.append(guardid);
		_sb_.append(",");
		_sb_.append(guarddegree);
		_sb_.append(",");
		_sb_.append(guardtime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("charm"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("guardid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("guarddegree"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("guardtime"));
		return lb;
	}

	private class Const implements xbean.IdolCharmInfo {
		IdolCharmInfo nThis() {
			return IdolCharmInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.IdolCharmInfo copy() {
			return IdolCharmInfo.this.copy();
		}

		@Override
		public xbean.IdolCharmInfo toData() {
			return IdolCharmInfo.this.toData();
		}

		public xbean.IdolCharmInfo toBean() {
			return IdolCharmInfo.this.toBean();
		}

		@Override
		public xbean.IdolCharmInfo toDataIf() {
			return IdolCharmInfo.this.toDataIf();
		}

		public xbean.IdolCharmInfo toBeanIf() {
			return IdolCharmInfo.this.toBeanIf();
		}

		@Override
		public int getCharm() { // 
			_xdb_verify_unsafe_();
			return charm;
		}

		@Override
		public long getGuardid() { // 
			_xdb_verify_unsafe_();
			return guardid;
		}

		@Override
		public int getGuarddegree() { // 
			_xdb_verify_unsafe_();
			return guarddegree;
		}

		@Override
		public long getGuardtime() { // 
			_xdb_verify_unsafe_();
			return guardtime;
		}

		@Override
		public void setCharm(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGuardid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGuarddegree(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGuardtime(long _v_) { // 
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
			return IdolCharmInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return IdolCharmInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return IdolCharmInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return IdolCharmInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return IdolCharmInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return IdolCharmInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return IdolCharmInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return IdolCharmInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return IdolCharmInfo.this.toString();
		}

	}

	public static final class Data implements xbean.IdolCharmInfo {
		private int charm; // 
		private long guardid; // 
		private int guarddegree; // 
		private long guardtime; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.IdolCharmInfo _o1_) {
			if (_o1_ instanceof IdolCharmInfo) assign((IdolCharmInfo)_o1_);
			else if (_o1_ instanceof IdolCharmInfo.Data) assign((IdolCharmInfo.Data)_o1_);
			else if (_o1_ instanceof IdolCharmInfo.Const) assign(((IdolCharmInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(IdolCharmInfo _o_) {
			charm = _o_.charm;
			guardid = _o_.guardid;
			guarddegree = _o_.guarddegree;
			guardtime = _o_.guardtime;
		}

		private void assign(IdolCharmInfo.Data _o_) {
			charm = _o_.charm;
			guardid = _o_.guardid;
			guarddegree = _o_.guarddegree;
			guardtime = _o_.guardtime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)( 8192|  1));_os_.marshal(charm);
	_os_.marshal((short)(10240|  2));_os_.marshal(guardid);
	_os_.marshal((short)( 8192|  4));_os_.marshal(guarddegree);
	_os_.marshal((short)(10240|  5));_os_.marshal(guardtime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):charm = _os_.unmarshal_int();
					break;
					case ( 6144|  1):charm = _os_.unmarshal_short();
					break;
					case (10240|  2):guardid = _os_.unmarshal_long();
					break;
					case ( 6144|  2):guardid = _os_.unmarshal_short();
					break;
					case ( 8192|  2):guardid = _os_.unmarshal_int();
					break;
					case ( 8192|  4):guarddegree = _os_.unmarshal_int();
					break;
					case ( 6144|  4):guarddegree = _os_.unmarshal_short();
					break;
					case (10240|  5):guardtime = _os_.unmarshal_long();
					break;
					case ( 6144|  5):guardtime = _os_.unmarshal_short();
					break;
					case ( 8192|  5):guardtime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.IdolCharmInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.IdolCharmInfo toData() {
			return new Data(this);
		}

		public xbean.IdolCharmInfo toBean() {
			return new IdolCharmInfo(this, null, null);
		}

		@Override
		public xbean.IdolCharmInfo toDataIf() {
			return this;
		}

		public xbean.IdolCharmInfo toBeanIf() {
			return new IdolCharmInfo(this, null, null);
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
		public int getCharm() { // 
			return charm;
		}

		@Override
		public long getGuardid() { // 
			return guardid;
		}

		@Override
		public int getGuarddegree() { // 
			return guarddegree;
		}

		@Override
		public long getGuardtime() { // 
			return guardtime;
		}

		@Override
		public void setCharm(int _v_) { // 
			charm = _v_;
		}

		@Override
		public void setGuardid(long _v_) { // 
			guardid = _v_;
		}

		@Override
		public void setGuarddegree(int _v_) { // 
			guarddegree = _v_;
		}

		@Override
		public void setGuardtime(long _v_) { // 
			guardtime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof IdolCharmInfo.Data)) return false;
			IdolCharmInfo.Data _o_ = (IdolCharmInfo.Data) _o1_;
			if (charm != _o_.charm) return false;
			if (guardid != _o_.guardid) return false;
			if (guarddegree != _o_.guarddegree) return false;
			if (guardtime != _o_.guardtime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += charm;
			_h_ += guardid;
			_h_ += guarddegree;
			_h_ += guardtime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(charm);
			_sb_.append(",");
			_sb_.append(guardid);
			_sb_.append(",");
			_sb_.append(guarddegree);
			_sb_.append(",");
			_sb_.append(guardtime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
