
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class HuiWuRound extends xdb.XBean implements xbean.HuiWuRound {
	private java.util.LinkedList<xbean.HuiWuBattle> battles; // 

	@Override
	public void _reset_unsafe_() {
		battles.clear();
	}

	HuiWuRound(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		battles = new java.util.LinkedList<xbean.HuiWuBattle>();
	}

	public HuiWuRound() {
		this(0, null, null);
	}

	public HuiWuRound(HuiWuRound _o_) {
		this(_o_, null, null);
	}

	HuiWuRound(xbean.HuiWuRound _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof HuiWuRound) assign((HuiWuRound)_o1_);
		else if (_o1_ instanceof HuiWuRound.Data) assign((HuiWuRound.Data)_o1_);
		else if (_o1_ instanceof HuiWuRound.Const) assign(((HuiWuRound.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(HuiWuRound _o_) {
		_o_._xdb_verify_unsafe_();
		battles = new java.util.LinkedList<xbean.HuiWuBattle>();
		for (xbean.HuiWuBattle _v_ : _o_.battles)
			battles.add(new HuiWuBattle(_v_, this, "battles"));
	}

	private void assign(HuiWuRound.Data _o_) {
		battles = new java.util.LinkedList<xbean.HuiWuBattle>();
		for (xbean.HuiWuBattle _v_ : _o_.battles)
			battles.add(new HuiWuBattle(_v_, this, "battles"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(battles.size());
for (xbean.HuiWuBattle _v_ : battles) {
	_v_.marshal(_os_);
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
    				case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.HuiWuBattle _v_ = new HuiWuBattle(0, this, "battles");
	_v_.unmarshal(_os_);
	battles.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.HuiWuRound copy() {
		_xdb_verify_unsafe_();
		return new HuiWuRound(this);
	}

	@Override
	public xbean.HuiWuRound toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuRound toBean() {
		_xdb_verify_unsafe_();
		return new HuiWuRound(this); // same as copy()
	}

	@Override
	public xbean.HuiWuRound toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuRound toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<xbean.HuiWuBattle> getBattles() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "battles"), battles);
	}

	public java.util.List<xbean.HuiWuBattle> getBattlesAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<xbean.HuiWuBattle> battles;
		HuiWuRound _o_ = this;
		battles = new java.util.LinkedList<xbean.HuiWuBattle>();
		for (xbean.HuiWuBattle _v_ : _o_.battles)
			battles.add(new HuiWuBattle.Data(_v_));
		return battles;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		HuiWuRound _o_ = null;
		if ( _o1_ instanceof HuiWuRound ) _o_ = (HuiWuRound)_o1_;
		else if ( _o1_ instanceof HuiWuRound.Const ) _o_ = ((HuiWuRound.Const)_o1_).nThis();
		else return false;
		if (!battles.equals(_o_.battles)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += battles.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(battles);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("battles"));
		return lb;
	}

	private class Const implements xbean.HuiWuRound {
		HuiWuRound nThis() {
			return HuiWuRound.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.HuiWuRound copy() {
			return HuiWuRound.this.copy();
		}

		@Override
		public xbean.HuiWuRound toData() {
			return HuiWuRound.this.toData();
		}

		public xbean.HuiWuRound toBean() {
			return HuiWuRound.this.toBean();
		}

		@Override
		public xbean.HuiWuRound toDataIf() {
			return HuiWuRound.this.toDataIf();
		}

		public xbean.HuiWuRound toBeanIf() {
			return HuiWuRound.this.toBeanIf();
		}

		@Override
		public java.util.List<xbean.HuiWuBattle> getBattles() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(battles);
		}

		public java.util.List<xbean.HuiWuBattle> getBattlesAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<xbean.HuiWuBattle> battles;
			HuiWuRound _o_ = HuiWuRound.this;
		battles = new java.util.LinkedList<xbean.HuiWuBattle>();
		for (xbean.HuiWuBattle _v_ : _o_.battles)
			battles.add(new HuiWuBattle.Data(_v_));
			return battles;
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
			return HuiWuRound.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return HuiWuRound.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return HuiWuRound.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return HuiWuRound.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return HuiWuRound.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return HuiWuRound.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return HuiWuRound.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return HuiWuRound.this.hashCode();
		}

		@Override
		public String toString() {
			return HuiWuRound.this.toString();
		}

	}

	public static final class Data implements xbean.HuiWuRound {
		private java.util.LinkedList<xbean.HuiWuBattle> battles; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			battles = new java.util.LinkedList<xbean.HuiWuBattle>();
		}

		Data(xbean.HuiWuRound _o1_) {
			if (_o1_ instanceof HuiWuRound) assign((HuiWuRound)_o1_);
			else if (_o1_ instanceof HuiWuRound.Data) assign((HuiWuRound.Data)_o1_);
			else if (_o1_ instanceof HuiWuRound.Const) assign(((HuiWuRound.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(HuiWuRound _o_) {
			battles = new java.util.LinkedList<xbean.HuiWuBattle>();
			for (xbean.HuiWuBattle _v_ : _o_.battles)
				battles.add(new HuiWuBattle.Data(_v_));
		}

		private void assign(HuiWuRound.Data _o_) {
			battles = new java.util.LinkedList<xbean.HuiWuBattle>();
			for (xbean.HuiWuBattle _v_ : _o_.battles)
				battles.add(new HuiWuBattle.Data(_v_));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(battles.size());
for (xbean.HuiWuBattle _v_ : battles) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.HuiWuBattle _v_ = xbean.Pod.newHuiWuBattleData();
	_v_.unmarshal(_os_);
	battles.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.HuiWuRound copy() {
			return new Data(this);
		}

		@Override
		public xbean.HuiWuRound toData() {
			return new Data(this);
		}

		public xbean.HuiWuRound toBean() {
			return new HuiWuRound(this, null, null);
		}

		@Override
		public xbean.HuiWuRound toDataIf() {
			return this;
		}

		public xbean.HuiWuRound toBeanIf() {
			return new HuiWuRound(this, null, null);
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
		public java.util.List<xbean.HuiWuBattle> getBattles() { // 
			return battles;
		}

		@Override
		public java.util.List<xbean.HuiWuBattle> getBattlesAsData() { // 
			return battles;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof HuiWuRound.Data)) return false;
			HuiWuRound.Data _o_ = (HuiWuRound.Data) _o1_;
			if (!battles.equals(_o_.battles)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += battles.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(battles);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
