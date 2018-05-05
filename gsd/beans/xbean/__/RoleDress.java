
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleDress extends xdb.XBean implements xbean.RoleDress {
	private java.util.HashMap<Integer, xbean.Dress> dresses; // 角色的时装信息
	private int activedress; // 当前激活的时装

	@Override
	public void _reset_unsafe_() {
		dresses.clear();
		activedress = 0;
	}

	RoleDress(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		dresses = new java.util.HashMap<Integer, xbean.Dress>();
	}

	public RoleDress() {
		this(0, null, null);
	}

	public RoleDress(RoleDress _o_) {
		this(_o_, null, null);
	}

	RoleDress(xbean.RoleDress _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleDress) assign((RoleDress)_o1_);
		else if (_o1_ instanceof RoleDress.Data) assign((RoleDress.Data)_o1_);
		else if (_o1_ instanceof RoleDress.Const) assign(((RoleDress.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleDress _o_) {
		_o_._xdb_verify_unsafe_();
		dresses = new java.util.HashMap<Integer, xbean.Dress>();
		for (java.util.Map.Entry<Integer, xbean.Dress> _e_ : _o_.dresses.entrySet())
			dresses.put(_e_.getKey(), new Dress(_e_.getValue(), this, "dresses"));
		activedress = _o_.activedress;
	}

	private void assign(RoleDress.Data _o_) {
		dresses = new java.util.HashMap<Integer, xbean.Dress>();
		for (java.util.Map.Entry<Integer, xbean.Dress> _e_ : _o_.dresses.entrySet())
			dresses.put(_e_.getKey(), new Dress(_e_.getValue(), this, "dresses"));
		activedress = _o_.activedress;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(dresses.size());
for (java.util.Map.Entry<Integer, xbean.Dress> _e_ : dresses.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  2));_os_.marshal(activedress);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		dresses = new java.util.HashMap<Integer, xbean.Dress>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Dress _v_ = new Dress(0, this, "dresses");
		_v_.unmarshal(_os_);
		dresses.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  2):activedress = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):activedress = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleDress copy() {
		_xdb_verify_unsafe_();
		return new RoleDress(this);
	}

	@Override
	public xbean.RoleDress toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleDress toBean() {
		_xdb_verify_unsafe_();
		return new RoleDress(this); // same as copy()
	}

	@Override
	public xbean.RoleDress toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleDress toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.Dress> getDresses() { // 角色的时装信息
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "dresses"), dresses);
	}

	@Override
	public java.util.Map<Integer, xbean.Dress> getDressesAsData() { // 角色的时装信息
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Dress> dresses;
		RoleDress _o_ = this;
		dresses = new java.util.HashMap<Integer, xbean.Dress>();
		for (java.util.Map.Entry<Integer, xbean.Dress> _e_ : _o_.dresses.entrySet())
			dresses.put(_e_.getKey(), new Dress.Data(_e_.getValue()));
		return dresses;
	}

	@Override
	public int getActivedress() { // 当前激活的时装
		_xdb_verify_unsafe_();
		return activedress;
	}

	@Override
	public void setActivedress(int _v_) { // 当前激活的时装
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "activedress") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, activedress) {
					public void rollback() { activedress = _xdb_saved; }
				};}});
		activedress = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleDress _o_ = null;
		if ( _o1_ instanceof RoleDress ) _o_ = (RoleDress)_o1_;
		else if ( _o1_ instanceof RoleDress.Const ) _o_ = ((RoleDress.Const)_o1_).nThis();
		else return false;
		if (!dresses.equals(_o_.dresses)) return false;
		if (activedress != _o_.activedress) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += dresses.hashCode();
		_h_ += activedress;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(dresses);
		_sb_.append(",");
		_sb_.append(activedress);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("dresses"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("activedress"));
		return lb;
	}

	private class Const implements xbean.RoleDress {
		RoleDress nThis() {
			return RoleDress.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleDress copy() {
			return RoleDress.this.copy();
		}

		@Override
		public xbean.RoleDress toData() {
			return RoleDress.this.toData();
		}

		public xbean.RoleDress toBean() {
			return RoleDress.this.toBean();
		}

		@Override
		public xbean.RoleDress toDataIf() {
			return RoleDress.this.toDataIf();
		}

		public xbean.RoleDress toBeanIf() {
			return RoleDress.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.Dress> getDresses() { // 角色的时装信息
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(dresses);
		}

		@Override
		public java.util.Map<Integer, xbean.Dress> getDressesAsData() { // 角色的时装信息
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Dress> dresses;
			RoleDress _o_ = RoleDress.this;
			dresses = new java.util.HashMap<Integer, xbean.Dress>();
			for (java.util.Map.Entry<Integer, xbean.Dress> _e_ : _o_.dresses.entrySet())
				dresses.put(_e_.getKey(), new Dress.Data(_e_.getValue()));
			return dresses;
		}

		@Override
		public int getActivedress() { // 当前激活的时装
			_xdb_verify_unsafe_();
			return activedress;
		}

		@Override
		public void setActivedress(int _v_) { // 当前激活的时装
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
			return RoleDress.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleDress.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleDress.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleDress.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleDress.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleDress.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleDress.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleDress.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleDress.this.toString();
		}

	}

	public static final class Data implements xbean.RoleDress {
		private java.util.HashMap<Integer, xbean.Dress> dresses; // 角色的时装信息
		private int activedress; // 当前激活的时装

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			dresses = new java.util.HashMap<Integer, xbean.Dress>();
		}

		Data(xbean.RoleDress _o1_) {
			if (_o1_ instanceof RoleDress) assign((RoleDress)_o1_);
			else if (_o1_ instanceof RoleDress.Data) assign((RoleDress.Data)_o1_);
			else if (_o1_ instanceof RoleDress.Const) assign(((RoleDress.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleDress _o_) {
			dresses = new java.util.HashMap<Integer, xbean.Dress>();
			for (java.util.Map.Entry<Integer, xbean.Dress> _e_ : _o_.dresses.entrySet())
				dresses.put(_e_.getKey(), new Dress.Data(_e_.getValue()));
			activedress = _o_.activedress;
		}

		private void assign(RoleDress.Data _o_) {
			dresses = new java.util.HashMap<Integer, xbean.Dress>();
			for (java.util.Map.Entry<Integer, xbean.Dress> _e_ : _o_.dresses.entrySet())
				dresses.put(_e_.getKey(), new Dress.Data(_e_.getValue()));
			activedress = _o_.activedress;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(dresses.size());
for (java.util.Map.Entry<Integer, xbean.Dress> _e_ : dresses.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  2));_os_.marshal(activedress);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		dresses = new java.util.HashMap<Integer, xbean.Dress>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Dress _v_ = xbean.Pod.newDressData();
		_v_.unmarshal(_os_);
		dresses.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192|  2):activedress = _os_.unmarshal_int();
					break;
					case ( 6144|  2):activedress = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleDress copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleDress toData() {
			return new Data(this);
		}

		public xbean.RoleDress toBean() {
			return new RoleDress(this, null, null);
		}

		@Override
		public xbean.RoleDress toDataIf() {
			return this;
		}

		public xbean.RoleDress toBeanIf() {
			return new RoleDress(this, null, null);
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
		public java.util.Map<Integer, xbean.Dress> getDresses() { // 角色的时装信息
			return dresses;
		}

		@Override
		public java.util.Map<Integer, xbean.Dress> getDressesAsData() { // 角色的时装信息
			return dresses;
		}

		@Override
		public int getActivedress() { // 当前激活的时装
			return activedress;
		}

		@Override
		public void setActivedress(int _v_) { // 当前激活的时装
			activedress = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleDress.Data)) return false;
			RoleDress.Data _o_ = (RoleDress.Data) _o1_;
			if (!dresses.equals(_o_.dresses)) return false;
			if (activedress != _o_.activedress) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += dresses.hashCode();
			_h_ += activedress;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(dresses);
			_sb_.append(",");
			_sb_.append(activedress);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
