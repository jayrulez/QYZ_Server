
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleMemInfo extends xdb.XBean implements xbean.RoleMemInfo {
	private java.util.HashMap<Integer, xbean.HeroesGroupMemInfo> herogroupmeminfos; // 青云英雄录内存数据

	@Override
	public void _reset_unsafe_() {
		herogroupmeminfos.clear();
	}

	RoleMemInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		herogroupmeminfos = new java.util.HashMap<Integer, xbean.HeroesGroupMemInfo>();
	}

	public RoleMemInfo() {
		this(0, null, null);
	}

	public RoleMemInfo(RoleMemInfo _o_) {
		this(_o_, null, null);
	}

	RoleMemInfo(xbean.RoleMemInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleMemInfo) assign((RoleMemInfo)_o1_);
		else if (_o1_ instanceof RoleMemInfo.Data) assign((RoleMemInfo.Data)_o1_);
		else if (_o1_ instanceof RoleMemInfo.Const) assign(((RoleMemInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleMemInfo _o_) {
		_o_._xdb_verify_unsafe_();
		herogroupmeminfos = new java.util.HashMap<Integer, xbean.HeroesGroupMemInfo>();
		for (java.util.Map.Entry<Integer, xbean.HeroesGroupMemInfo> _e_ : _o_.herogroupmeminfos.entrySet())
			herogroupmeminfos.put(_e_.getKey(), new HeroesGroupMemInfo(_e_.getValue(), this, "herogroupmeminfos"));
	}

	private void assign(RoleMemInfo.Data _o_) {
		herogroupmeminfos = new java.util.HashMap<Integer, xbean.HeroesGroupMemInfo>();
		for (java.util.Map.Entry<Integer, xbean.HeroesGroupMemInfo> _e_ : _o_.herogroupmeminfos.entrySet())
			herogroupmeminfos.put(_e_.getKey(), new HeroesGroupMemInfo(_e_.getValue(), this, "herogroupmeminfos"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(herogroupmeminfos.size());
for (java.util.Map.Entry<Integer, xbean.HeroesGroupMemInfo> _e_ : herogroupmeminfos.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
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
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		herogroupmeminfos = new java.util.HashMap<Integer, xbean.HeroesGroupMemInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.HeroesGroupMemInfo _v_ = new HeroesGroupMemInfo(0, this, "herogroupmeminfos");
		_v_.unmarshal(_os_);
		herogroupmeminfos.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleMemInfo copy() {
		_xdb_verify_unsafe_();
		return new RoleMemInfo(this);
	}

	@Override
	public xbean.RoleMemInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleMemInfo toBean() {
		_xdb_verify_unsafe_();
		return new RoleMemInfo(this); // same as copy()
	}

	@Override
	public xbean.RoleMemInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleMemInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.HeroesGroupMemInfo> getHerogroupmeminfos() { // 青云英雄录内存数据
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "herogroupmeminfos"), herogroupmeminfos);
	}

	@Override
	public java.util.Map<Integer, xbean.HeroesGroupMemInfo> getHerogroupmeminfosAsData() { // 青云英雄录内存数据
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.HeroesGroupMemInfo> herogroupmeminfos;
		RoleMemInfo _o_ = this;
		herogroupmeminfos = new java.util.HashMap<Integer, xbean.HeroesGroupMemInfo>();
		for (java.util.Map.Entry<Integer, xbean.HeroesGroupMemInfo> _e_ : _o_.herogroupmeminfos.entrySet())
			herogroupmeminfos.put(_e_.getKey(), new HeroesGroupMemInfo.Data(_e_.getValue()));
		return herogroupmeminfos;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleMemInfo _o_ = null;
		if ( _o1_ instanceof RoleMemInfo ) _o_ = (RoleMemInfo)_o1_;
		else if ( _o1_ instanceof RoleMemInfo.Const ) _o_ = ((RoleMemInfo.Const)_o1_).nThis();
		else return false;
		if (!herogroupmeminfos.equals(_o_.herogroupmeminfos)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += herogroupmeminfos.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(herogroupmeminfos);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("herogroupmeminfos"));
		return lb;
	}

	private class Const implements xbean.RoleMemInfo {
		RoleMemInfo nThis() {
			return RoleMemInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleMemInfo copy() {
			return RoleMemInfo.this.copy();
		}

		@Override
		public xbean.RoleMemInfo toData() {
			return RoleMemInfo.this.toData();
		}

		public xbean.RoleMemInfo toBean() {
			return RoleMemInfo.this.toBean();
		}

		@Override
		public xbean.RoleMemInfo toDataIf() {
			return RoleMemInfo.this.toDataIf();
		}

		public xbean.RoleMemInfo toBeanIf() {
			return RoleMemInfo.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.HeroesGroupMemInfo> getHerogroupmeminfos() { // 青云英雄录内存数据
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(herogroupmeminfos);
		}

		@Override
		public java.util.Map<Integer, xbean.HeroesGroupMemInfo> getHerogroupmeminfosAsData() { // 青云英雄录内存数据
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.HeroesGroupMemInfo> herogroupmeminfos;
			RoleMemInfo _o_ = RoleMemInfo.this;
			herogroupmeminfos = new java.util.HashMap<Integer, xbean.HeroesGroupMemInfo>();
			for (java.util.Map.Entry<Integer, xbean.HeroesGroupMemInfo> _e_ : _o_.herogroupmeminfos.entrySet())
				herogroupmeminfos.put(_e_.getKey(), new HeroesGroupMemInfo.Data(_e_.getValue()));
			return herogroupmeminfos;
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
			return RoleMemInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleMemInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleMemInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleMemInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleMemInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleMemInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleMemInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleMemInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleMemInfo.this.toString();
		}

	}

	public static final class Data implements xbean.RoleMemInfo {
		private java.util.HashMap<Integer, xbean.HeroesGroupMemInfo> herogroupmeminfos; // 青云英雄录内存数据

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			herogroupmeminfos = new java.util.HashMap<Integer, xbean.HeroesGroupMemInfo>();
		}

		Data(xbean.RoleMemInfo _o1_) {
			if (_o1_ instanceof RoleMemInfo) assign((RoleMemInfo)_o1_);
			else if (_o1_ instanceof RoleMemInfo.Data) assign((RoleMemInfo.Data)_o1_);
			else if (_o1_ instanceof RoleMemInfo.Const) assign(((RoleMemInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleMemInfo _o_) {
			herogroupmeminfos = new java.util.HashMap<Integer, xbean.HeroesGroupMemInfo>();
			for (java.util.Map.Entry<Integer, xbean.HeroesGroupMemInfo> _e_ : _o_.herogroupmeminfos.entrySet())
				herogroupmeminfos.put(_e_.getKey(), new HeroesGroupMemInfo.Data(_e_.getValue()));
		}

		private void assign(RoleMemInfo.Data _o_) {
			herogroupmeminfos = new java.util.HashMap<Integer, xbean.HeroesGroupMemInfo>();
			for (java.util.Map.Entry<Integer, xbean.HeroesGroupMemInfo> _e_ : _o_.herogroupmeminfos.entrySet())
				herogroupmeminfos.put(_e_.getKey(), new HeroesGroupMemInfo.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(herogroupmeminfos.size());
for (java.util.Map.Entry<Integer, xbean.HeroesGroupMemInfo> _e_ : herogroupmeminfos.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
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
		herogroupmeminfos = new java.util.HashMap<Integer, xbean.HeroesGroupMemInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.HeroesGroupMemInfo _v_ = xbean.Pod.newHeroesGroupMemInfoData();
		_v_.unmarshal(_os_);
		herogroupmeminfos.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleMemInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleMemInfo toData() {
			return new Data(this);
		}

		public xbean.RoleMemInfo toBean() {
			return new RoleMemInfo(this, null, null);
		}

		@Override
		public xbean.RoleMemInfo toDataIf() {
			return this;
		}

		public xbean.RoleMemInfo toBeanIf() {
			return new RoleMemInfo(this, null, null);
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
		public java.util.Map<Integer, xbean.HeroesGroupMemInfo> getHerogroupmeminfos() { // 青云英雄录内存数据
			return herogroupmeminfos;
		}

		@Override
		public java.util.Map<Integer, xbean.HeroesGroupMemInfo> getHerogroupmeminfosAsData() { // 青云英雄录内存数据
			return herogroupmeminfos;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleMemInfo.Data)) return false;
			RoleMemInfo.Data _o_ = (RoleMemInfo.Data) _o1_;
			if (!herogroupmeminfos.equals(_o_.herogroupmeminfos)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += herogroupmeminfos.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(herogroupmeminfos);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
