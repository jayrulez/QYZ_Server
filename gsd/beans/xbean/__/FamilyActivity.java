
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class FamilyActivity extends xdb.XBean implements xbean.FamilyActivity {
	private java.util.HashMap<Integer, xbean.FamilyGodAnimal> godanimalinfo; // 家族神兽信息

	@Override
	public void _reset_unsafe_() {
		godanimalinfo.clear();
	}

	FamilyActivity(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		godanimalinfo = new java.util.HashMap<Integer, xbean.FamilyGodAnimal>();
	}

	public FamilyActivity() {
		this(0, null, null);
	}

	public FamilyActivity(FamilyActivity _o_) {
		this(_o_, null, null);
	}

	FamilyActivity(xbean.FamilyActivity _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof FamilyActivity) assign((FamilyActivity)_o1_);
		else if (_o1_ instanceof FamilyActivity.Data) assign((FamilyActivity.Data)_o1_);
		else if (_o1_ instanceof FamilyActivity.Const) assign(((FamilyActivity.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(FamilyActivity _o_) {
		_o_._xdb_verify_unsafe_();
		godanimalinfo = new java.util.HashMap<Integer, xbean.FamilyGodAnimal>();
		for (java.util.Map.Entry<Integer, xbean.FamilyGodAnimal> _e_ : _o_.godanimalinfo.entrySet())
			godanimalinfo.put(_e_.getKey(), new FamilyGodAnimal(_e_.getValue(), this, "godanimalinfo"));
	}

	private void assign(FamilyActivity.Data _o_) {
		godanimalinfo = new java.util.HashMap<Integer, xbean.FamilyGodAnimal>();
		for (java.util.Map.Entry<Integer, xbean.FamilyGodAnimal> _e_ : _o_.godanimalinfo.entrySet())
			godanimalinfo.put(_e_.getKey(), new FamilyGodAnimal(_e_.getValue(), this, "godanimalinfo"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(godanimalinfo.size());
for (java.util.Map.Entry<Integer, xbean.FamilyGodAnimal> _e_ : godanimalinfo.entrySet())
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
		godanimalinfo = new java.util.HashMap<Integer, xbean.FamilyGodAnimal>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.FamilyGodAnimal _v_ = new FamilyGodAnimal(0, this, "godanimalinfo");
		_v_.unmarshal(_os_);
		godanimalinfo.put(_k_, _v_);
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
	public xbean.FamilyActivity copy() {
		_xdb_verify_unsafe_();
		return new FamilyActivity(this);
	}

	@Override
	public xbean.FamilyActivity toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyActivity toBean() {
		_xdb_verify_unsafe_();
		return new FamilyActivity(this); // same as copy()
	}

	@Override
	public xbean.FamilyActivity toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyActivity toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.FamilyGodAnimal> getGodanimalinfo() { // 家族神兽信息
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "godanimalinfo"), godanimalinfo);
	}

	@Override
	public java.util.Map<Integer, xbean.FamilyGodAnimal> getGodanimalinfoAsData() { // 家族神兽信息
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.FamilyGodAnimal> godanimalinfo;
		FamilyActivity _o_ = this;
		godanimalinfo = new java.util.HashMap<Integer, xbean.FamilyGodAnimal>();
		for (java.util.Map.Entry<Integer, xbean.FamilyGodAnimal> _e_ : _o_.godanimalinfo.entrySet())
			godanimalinfo.put(_e_.getKey(), new FamilyGodAnimal.Data(_e_.getValue()));
		return godanimalinfo;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		FamilyActivity _o_ = null;
		if ( _o1_ instanceof FamilyActivity ) _o_ = (FamilyActivity)_o1_;
		else if ( _o1_ instanceof FamilyActivity.Const ) _o_ = ((FamilyActivity.Const)_o1_).nThis();
		else return false;
		if (!godanimalinfo.equals(_o_.godanimalinfo)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += godanimalinfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(godanimalinfo);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("godanimalinfo"));
		return lb;
	}

	private class Const implements xbean.FamilyActivity {
		FamilyActivity nThis() {
			return FamilyActivity.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.FamilyActivity copy() {
			return FamilyActivity.this.copy();
		}

		@Override
		public xbean.FamilyActivity toData() {
			return FamilyActivity.this.toData();
		}

		public xbean.FamilyActivity toBean() {
			return FamilyActivity.this.toBean();
		}

		@Override
		public xbean.FamilyActivity toDataIf() {
			return FamilyActivity.this.toDataIf();
		}

		public xbean.FamilyActivity toBeanIf() {
			return FamilyActivity.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.FamilyGodAnimal> getGodanimalinfo() { // 家族神兽信息
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(godanimalinfo);
		}

		@Override
		public java.util.Map<Integer, xbean.FamilyGodAnimal> getGodanimalinfoAsData() { // 家族神兽信息
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.FamilyGodAnimal> godanimalinfo;
			FamilyActivity _o_ = FamilyActivity.this;
			godanimalinfo = new java.util.HashMap<Integer, xbean.FamilyGodAnimal>();
			for (java.util.Map.Entry<Integer, xbean.FamilyGodAnimal> _e_ : _o_.godanimalinfo.entrySet())
				godanimalinfo.put(_e_.getKey(), new FamilyGodAnimal.Data(_e_.getValue()));
			return godanimalinfo;
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
			return FamilyActivity.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return FamilyActivity.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return FamilyActivity.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return FamilyActivity.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return FamilyActivity.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return FamilyActivity.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return FamilyActivity.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return FamilyActivity.this.hashCode();
		}

		@Override
		public String toString() {
			return FamilyActivity.this.toString();
		}

	}

	public static final class Data implements xbean.FamilyActivity {
		private java.util.HashMap<Integer, xbean.FamilyGodAnimal> godanimalinfo; // 家族神兽信息

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			godanimalinfo = new java.util.HashMap<Integer, xbean.FamilyGodAnimal>();
		}

		Data(xbean.FamilyActivity _o1_) {
			if (_o1_ instanceof FamilyActivity) assign((FamilyActivity)_o1_);
			else if (_o1_ instanceof FamilyActivity.Data) assign((FamilyActivity.Data)_o1_);
			else if (_o1_ instanceof FamilyActivity.Const) assign(((FamilyActivity.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(FamilyActivity _o_) {
			godanimalinfo = new java.util.HashMap<Integer, xbean.FamilyGodAnimal>();
			for (java.util.Map.Entry<Integer, xbean.FamilyGodAnimal> _e_ : _o_.godanimalinfo.entrySet())
				godanimalinfo.put(_e_.getKey(), new FamilyGodAnimal.Data(_e_.getValue()));
		}

		private void assign(FamilyActivity.Data _o_) {
			godanimalinfo = new java.util.HashMap<Integer, xbean.FamilyGodAnimal>();
			for (java.util.Map.Entry<Integer, xbean.FamilyGodAnimal> _e_ : _o_.godanimalinfo.entrySet())
				godanimalinfo.put(_e_.getKey(), new FamilyGodAnimal.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(godanimalinfo.size());
for (java.util.Map.Entry<Integer, xbean.FamilyGodAnimal> _e_ : godanimalinfo.entrySet())
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
		godanimalinfo = new java.util.HashMap<Integer, xbean.FamilyGodAnimal>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.FamilyGodAnimal _v_ = xbean.Pod.newFamilyGodAnimalData();
		_v_.unmarshal(_os_);
		godanimalinfo.put(_k_, _v_);
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
		public xbean.FamilyActivity copy() {
			return new Data(this);
		}

		@Override
		public xbean.FamilyActivity toData() {
			return new Data(this);
		}

		public xbean.FamilyActivity toBean() {
			return new FamilyActivity(this, null, null);
		}

		@Override
		public xbean.FamilyActivity toDataIf() {
			return this;
		}

		public xbean.FamilyActivity toBeanIf() {
			return new FamilyActivity(this, null, null);
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
		public java.util.Map<Integer, xbean.FamilyGodAnimal> getGodanimalinfo() { // 家族神兽信息
			return godanimalinfo;
		}

		@Override
		public java.util.Map<Integer, xbean.FamilyGodAnimal> getGodanimalinfoAsData() { // 家族神兽信息
			return godanimalinfo;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof FamilyActivity.Data)) return false;
			FamilyActivity.Data _o_ = (FamilyActivity.Data) _o1_;
			if (!godanimalinfo.equals(_o_.godanimalinfo)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += godanimalinfo.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(godanimalinfo);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
