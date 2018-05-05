
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleRank extends xdb.XBean implements xbean.RoleRank {
	private java.util.HashMap<Integer, xbean.ARankInfo> ranks; // key -> ranktype

	@Override
	public void _reset_unsafe_() {
		ranks.clear();
	}

	RoleRank(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		ranks = new java.util.HashMap<Integer, xbean.ARankInfo>();
	}

	public RoleRank() {
		this(0, null, null);
	}

	public RoleRank(RoleRank _o_) {
		this(_o_, null, null);
	}

	RoleRank(xbean.RoleRank _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleRank) assign((RoleRank)_o1_);
		else if (_o1_ instanceof RoleRank.Data) assign((RoleRank.Data)_o1_);
		else if (_o1_ instanceof RoleRank.Const) assign(((RoleRank.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleRank _o_) {
		_o_._xdb_verify_unsafe_();
		ranks = new java.util.HashMap<Integer, xbean.ARankInfo>();
		for (java.util.Map.Entry<Integer, xbean.ARankInfo> _e_ : _o_.ranks.entrySet())
			ranks.put(_e_.getKey(), new ARankInfo(_e_.getValue(), this, "ranks"));
	}

	private void assign(RoleRank.Data _o_) {
		ranks = new java.util.HashMap<Integer, xbean.ARankInfo>();
		for (java.util.Map.Entry<Integer, xbean.ARankInfo> _e_ : _o_.ranks.entrySet())
			ranks.put(_e_.getKey(), new ARankInfo(_e_.getValue(), this, "ranks"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(ranks.size());
for (java.util.Map.Entry<Integer, xbean.ARankInfo> _e_ : ranks.entrySet())
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
		ranks = new java.util.HashMap<Integer, xbean.ARankInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.ARankInfo _v_ = new ARankInfo(0, this, "ranks");
		_v_.unmarshal(_os_);
		ranks.put(_k_, _v_);
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
	public xbean.RoleRank copy() {
		_xdb_verify_unsafe_();
		return new RoleRank(this);
	}

	@Override
	public xbean.RoleRank toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleRank toBean() {
		_xdb_verify_unsafe_();
		return new RoleRank(this); // same as copy()
	}

	@Override
	public xbean.RoleRank toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleRank toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.ARankInfo> getRanks() { // key -> ranktype
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "ranks"), ranks);
	}

	@Override
	public java.util.Map<Integer, xbean.ARankInfo> getRanksAsData() { // key -> ranktype
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.ARankInfo> ranks;
		RoleRank _o_ = this;
		ranks = new java.util.HashMap<Integer, xbean.ARankInfo>();
		for (java.util.Map.Entry<Integer, xbean.ARankInfo> _e_ : _o_.ranks.entrySet())
			ranks.put(_e_.getKey(), new ARankInfo.Data(_e_.getValue()));
		return ranks;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleRank _o_ = null;
		if ( _o1_ instanceof RoleRank ) _o_ = (RoleRank)_o1_;
		else if ( _o1_ instanceof RoleRank.Const ) _o_ = ((RoleRank.Const)_o1_).nThis();
		else return false;
		if (!ranks.equals(_o_.ranks)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += ranks.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ranks);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("ranks"));
		return lb;
	}

	private class Const implements xbean.RoleRank {
		RoleRank nThis() {
			return RoleRank.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleRank copy() {
			return RoleRank.this.copy();
		}

		@Override
		public xbean.RoleRank toData() {
			return RoleRank.this.toData();
		}

		public xbean.RoleRank toBean() {
			return RoleRank.this.toBean();
		}

		@Override
		public xbean.RoleRank toDataIf() {
			return RoleRank.this.toDataIf();
		}

		public xbean.RoleRank toBeanIf() {
			return RoleRank.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.ARankInfo> getRanks() { // key -> ranktype
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(ranks);
		}

		@Override
		public java.util.Map<Integer, xbean.ARankInfo> getRanksAsData() { // key -> ranktype
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.ARankInfo> ranks;
			RoleRank _o_ = RoleRank.this;
			ranks = new java.util.HashMap<Integer, xbean.ARankInfo>();
			for (java.util.Map.Entry<Integer, xbean.ARankInfo> _e_ : _o_.ranks.entrySet())
				ranks.put(_e_.getKey(), new ARankInfo.Data(_e_.getValue()));
			return ranks;
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
			return RoleRank.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleRank.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleRank.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleRank.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleRank.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleRank.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleRank.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleRank.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleRank.this.toString();
		}

	}

	public static final class Data implements xbean.RoleRank {
		private java.util.HashMap<Integer, xbean.ARankInfo> ranks; // key -> ranktype

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			ranks = new java.util.HashMap<Integer, xbean.ARankInfo>();
		}

		Data(xbean.RoleRank _o1_) {
			if (_o1_ instanceof RoleRank) assign((RoleRank)_o1_);
			else if (_o1_ instanceof RoleRank.Data) assign((RoleRank.Data)_o1_);
			else if (_o1_ instanceof RoleRank.Const) assign(((RoleRank.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleRank _o_) {
			ranks = new java.util.HashMap<Integer, xbean.ARankInfo>();
			for (java.util.Map.Entry<Integer, xbean.ARankInfo> _e_ : _o_.ranks.entrySet())
				ranks.put(_e_.getKey(), new ARankInfo.Data(_e_.getValue()));
		}

		private void assign(RoleRank.Data _o_) {
			ranks = new java.util.HashMap<Integer, xbean.ARankInfo>();
			for (java.util.Map.Entry<Integer, xbean.ARankInfo> _e_ : _o_.ranks.entrySet())
				ranks.put(_e_.getKey(), new ARankInfo.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(ranks.size());
for (java.util.Map.Entry<Integer, xbean.ARankInfo> _e_ : ranks.entrySet())
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
		ranks = new java.util.HashMap<Integer, xbean.ARankInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.ARankInfo _v_ = xbean.Pod.newARankInfoData();
		_v_.unmarshal(_os_);
		ranks.put(_k_, _v_);
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
		public xbean.RoleRank copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleRank toData() {
			return new Data(this);
		}

		public xbean.RoleRank toBean() {
			return new RoleRank(this, null, null);
		}

		@Override
		public xbean.RoleRank toDataIf() {
			return this;
		}

		public xbean.RoleRank toBeanIf() {
			return new RoleRank(this, null, null);
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
		public java.util.Map<Integer, xbean.ARankInfo> getRanks() { // key -> ranktype
			return ranks;
		}

		@Override
		public java.util.Map<Integer, xbean.ARankInfo> getRanksAsData() { // key -> ranktype
			return ranks;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleRank.Data)) return false;
			RoleRank.Data _o_ = (RoleRank.Data) _o1_;
			if (!ranks.equals(_o_.ranks)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += ranks.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(ranks);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
