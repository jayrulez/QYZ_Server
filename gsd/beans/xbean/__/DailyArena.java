
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class DailyArena extends xdb.XBean implements xbean.DailyArena {
	private int challengesuccnum; // 
	private java.util.LinkedList<Integer> obtainrewards; // 

	@Override
	public void _reset_unsafe_() {
		challengesuccnum = 0;
		obtainrewards.clear();
	}

	DailyArena(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		obtainrewards = new java.util.LinkedList<Integer>();
	}

	public DailyArena() {
		this(0, null, null);
	}

	public DailyArena(DailyArena _o_) {
		this(_o_, null, null);
	}

	DailyArena(xbean.DailyArena _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof DailyArena) assign((DailyArena)_o1_);
		else if (_o1_ instanceof DailyArena.Data) assign((DailyArena.Data)_o1_);
		else if (_o1_ instanceof DailyArena.Const) assign(((DailyArena.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(DailyArena _o_) {
		_o_._xdb_verify_unsafe_();
		challengesuccnum = _o_.challengesuccnum;
		obtainrewards = new java.util.LinkedList<Integer>();
		obtainrewards.addAll(_o_.obtainrewards);
	}

	private void assign(DailyArena.Data _o_) {
		challengesuccnum = _o_.challengesuccnum;
		obtainrewards = new java.util.LinkedList<Integer>();
		obtainrewards.addAll(_o_.obtainrewards);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(challengesuccnum);
    _os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(obtainrewards.size());
for (Integer _v_ : obtainrewards) {
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
    				case ( 8192|  1):challengesuccnum = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):challengesuccnum = _os_.unmarshal_short();
    				break;
    				case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	obtainrewards.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.DailyArena copy() {
		_xdb_verify_unsafe_();
		return new DailyArena(this);
	}

	@Override
	public xbean.DailyArena toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyArena toBean() {
		_xdb_verify_unsafe_();
		return new DailyArena(this); // same as copy()
	}

	@Override
	public xbean.DailyArena toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyArena toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getChallengesuccnum() { // 
		_xdb_verify_unsafe_();
		return challengesuccnum;
	}

	@Override
	public java.util.List<Integer> getObtainrewards() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "obtainrewards"), obtainrewards);
	}

	public java.util.List<Integer> getObtainrewardsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Integer> obtainrewards;
		DailyArena _o_ = this;
		obtainrewards = new java.util.LinkedList<Integer>();
		obtainrewards.addAll(_o_.obtainrewards);
		return obtainrewards;
	}

	@Override
	public void setChallengesuccnum(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "challengesuccnum") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, challengesuccnum) {
					public void rollback() { challengesuccnum = _xdb_saved; }
				};}});
		challengesuccnum = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		DailyArena _o_ = null;
		if ( _o1_ instanceof DailyArena ) _o_ = (DailyArena)_o1_;
		else if ( _o1_ instanceof DailyArena.Const ) _o_ = ((DailyArena.Const)_o1_).nThis();
		else return false;
		if (challengesuccnum != _o_.challengesuccnum) return false;
		if (!obtainrewards.equals(_o_.obtainrewards)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += challengesuccnum;
		_h_ += obtainrewards.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(challengesuccnum);
		_sb_.append(",");
		_sb_.append(obtainrewards);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("challengesuccnum"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("obtainrewards"));
		return lb;
	}

	private class Const implements xbean.DailyArena {
		DailyArena nThis() {
			return DailyArena.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.DailyArena copy() {
			return DailyArena.this.copy();
		}

		@Override
		public xbean.DailyArena toData() {
			return DailyArena.this.toData();
		}

		public xbean.DailyArena toBean() {
			return DailyArena.this.toBean();
		}

		@Override
		public xbean.DailyArena toDataIf() {
			return DailyArena.this.toDataIf();
		}

		public xbean.DailyArena toBeanIf() {
			return DailyArena.this.toBeanIf();
		}

		@Override
		public int getChallengesuccnum() { // 
			_xdb_verify_unsafe_();
			return challengesuccnum;
		}

		@Override
		public java.util.List<Integer> getObtainrewards() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(obtainrewards);
		}

		public java.util.List<Integer> getObtainrewardsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Integer> obtainrewards;
			DailyArena _o_ = DailyArena.this;
		obtainrewards = new java.util.LinkedList<Integer>();
		obtainrewards.addAll(_o_.obtainrewards);
			return obtainrewards;
		}

		@Override
		public void setChallengesuccnum(int _v_) { // 
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
			return DailyArena.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return DailyArena.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return DailyArena.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return DailyArena.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return DailyArena.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return DailyArena.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return DailyArena.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return DailyArena.this.hashCode();
		}

		@Override
		public String toString() {
			return DailyArena.this.toString();
		}

	}

	public static final class Data implements xbean.DailyArena {
		private int challengesuccnum; // 
		private java.util.LinkedList<Integer> obtainrewards; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			obtainrewards = new java.util.LinkedList<Integer>();
		}

		Data(xbean.DailyArena _o1_) {
			if (_o1_ instanceof DailyArena) assign((DailyArena)_o1_);
			else if (_o1_ instanceof DailyArena.Data) assign((DailyArena.Data)_o1_);
			else if (_o1_ instanceof DailyArena.Const) assign(((DailyArena.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(DailyArena _o_) {
			challengesuccnum = _o_.challengesuccnum;
			obtainrewards = new java.util.LinkedList<Integer>();
			obtainrewards.addAll(_o_.obtainrewards);
		}

		private void assign(DailyArena.Data _o_) {
			challengesuccnum = _o_.challengesuccnum;
			obtainrewards = new java.util.LinkedList<Integer>();
			obtainrewards.addAll(_o_.obtainrewards);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(challengesuccnum);
	_os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(obtainrewards.size());
for (Integer _v_ : obtainrewards) {
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
					case ( 8192|  1):challengesuccnum = _os_.unmarshal_int();
					break;
					case ( 6144|  1):challengesuccnum = _os_.unmarshal_short();
					break;
					case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	obtainrewards.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.DailyArena copy() {
			return new Data(this);
		}

		@Override
		public xbean.DailyArena toData() {
			return new Data(this);
		}

		public xbean.DailyArena toBean() {
			return new DailyArena(this, null, null);
		}

		@Override
		public xbean.DailyArena toDataIf() {
			return this;
		}

		public xbean.DailyArena toBeanIf() {
			return new DailyArena(this, null, null);
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
		public int getChallengesuccnum() { // 
			return challengesuccnum;
		}

		@Override
		public java.util.List<Integer> getObtainrewards() { // 
			return obtainrewards;
		}

		@Override
		public java.util.List<Integer> getObtainrewardsAsData() { // 
			return obtainrewards;
		}

		@Override
		public void setChallengesuccnum(int _v_) { // 
			challengesuccnum = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof DailyArena.Data)) return false;
			DailyArena.Data _o_ = (DailyArena.Data) _o1_;
			if (challengesuccnum != _o_.challengesuccnum) return false;
			if (!obtainrewards.equals(_o_.obtainrewards)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += challengesuccnum;
			_h_ += obtainrewards.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(challengesuccnum);
			_sb_.append(",");
			_sb_.append(obtainrewards);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
