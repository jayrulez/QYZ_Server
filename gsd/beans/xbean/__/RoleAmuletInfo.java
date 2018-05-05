
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleAmuletInfo extends xdb.XBean implements xbean.RoleAmuletInfo {
	private java.util.HashMap<Integer, xbean.AmuletPage> pagemap; // 护符页信息,key为页id，1,2,3

	@Override
	public void _reset_unsafe_() {
		pagemap.clear();
	}

	RoleAmuletInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		pagemap = new java.util.HashMap<Integer, xbean.AmuletPage>();
	}

	public RoleAmuletInfo() {
		this(0, null, null);
	}

	public RoleAmuletInfo(RoleAmuletInfo _o_) {
		this(_o_, null, null);
	}

	RoleAmuletInfo(xbean.RoleAmuletInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleAmuletInfo) assign((RoleAmuletInfo)_o1_);
		else if (_o1_ instanceof RoleAmuletInfo.Data) assign((RoleAmuletInfo.Data)_o1_);
		else if (_o1_ instanceof RoleAmuletInfo.Const) assign(((RoleAmuletInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleAmuletInfo _o_) {
		_o_._xdb_verify_unsafe_();
		pagemap = new java.util.HashMap<Integer, xbean.AmuletPage>();
		for (java.util.Map.Entry<Integer, xbean.AmuletPage> _e_ : _o_.pagemap.entrySet())
			pagemap.put(_e_.getKey(), new AmuletPage(_e_.getValue(), this, "pagemap"));
	}

	private void assign(RoleAmuletInfo.Data _o_) {
		pagemap = new java.util.HashMap<Integer, xbean.AmuletPage>();
		for (java.util.Map.Entry<Integer, xbean.AmuletPage> _e_ : _o_.pagemap.entrySet())
			pagemap.put(_e_.getKey(), new AmuletPage(_e_.getValue(), this, "pagemap"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(pagemap.size());
for (java.util.Map.Entry<Integer, xbean.AmuletPage> _e_ : pagemap.entrySet())
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
		pagemap = new java.util.HashMap<Integer, xbean.AmuletPage>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.AmuletPage _v_ = new AmuletPage(0, this, "pagemap");
		_v_.unmarshal(_os_);
		pagemap.put(_k_, _v_);
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
	public xbean.RoleAmuletInfo copy() {
		_xdb_verify_unsafe_();
		return new RoleAmuletInfo(this);
	}

	@Override
	public xbean.RoleAmuletInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleAmuletInfo toBean() {
		_xdb_verify_unsafe_();
		return new RoleAmuletInfo(this); // same as copy()
	}

	@Override
	public xbean.RoleAmuletInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleAmuletInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.AmuletPage> getPagemap() { // 护符页信息,key为页id，1,2,3
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "pagemap"), pagemap);
	}

	@Override
	public java.util.Map<Integer, xbean.AmuletPage> getPagemapAsData() { // 护符页信息,key为页id，1,2,3
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.AmuletPage> pagemap;
		RoleAmuletInfo _o_ = this;
		pagemap = new java.util.HashMap<Integer, xbean.AmuletPage>();
		for (java.util.Map.Entry<Integer, xbean.AmuletPage> _e_ : _o_.pagemap.entrySet())
			pagemap.put(_e_.getKey(), new AmuletPage.Data(_e_.getValue()));
		return pagemap;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleAmuletInfo _o_ = null;
		if ( _o1_ instanceof RoleAmuletInfo ) _o_ = (RoleAmuletInfo)_o1_;
		else if ( _o1_ instanceof RoleAmuletInfo.Const ) _o_ = ((RoleAmuletInfo.Const)_o1_).nThis();
		else return false;
		if (!pagemap.equals(_o_.pagemap)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += pagemap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pagemap);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("pagemap"));
		return lb;
	}

	private class Const implements xbean.RoleAmuletInfo {
		RoleAmuletInfo nThis() {
			return RoleAmuletInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleAmuletInfo copy() {
			return RoleAmuletInfo.this.copy();
		}

		@Override
		public xbean.RoleAmuletInfo toData() {
			return RoleAmuletInfo.this.toData();
		}

		public xbean.RoleAmuletInfo toBean() {
			return RoleAmuletInfo.this.toBean();
		}

		@Override
		public xbean.RoleAmuletInfo toDataIf() {
			return RoleAmuletInfo.this.toDataIf();
		}

		public xbean.RoleAmuletInfo toBeanIf() {
			return RoleAmuletInfo.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletPage> getPagemap() { // 护符页信息,key为页id，1,2,3
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(pagemap);
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletPage> getPagemapAsData() { // 护符页信息,key为页id，1,2,3
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.AmuletPage> pagemap;
			RoleAmuletInfo _o_ = RoleAmuletInfo.this;
			pagemap = new java.util.HashMap<Integer, xbean.AmuletPage>();
			for (java.util.Map.Entry<Integer, xbean.AmuletPage> _e_ : _o_.pagemap.entrySet())
				pagemap.put(_e_.getKey(), new AmuletPage.Data(_e_.getValue()));
			return pagemap;
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
			return RoleAmuletInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleAmuletInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleAmuletInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleAmuletInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleAmuletInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleAmuletInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleAmuletInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleAmuletInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleAmuletInfo.this.toString();
		}

	}

	public static final class Data implements xbean.RoleAmuletInfo {
		private java.util.HashMap<Integer, xbean.AmuletPage> pagemap; // 护符页信息,key为页id，1,2,3

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			pagemap = new java.util.HashMap<Integer, xbean.AmuletPage>();
		}

		Data(xbean.RoleAmuletInfo _o1_) {
			if (_o1_ instanceof RoleAmuletInfo) assign((RoleAmuletInfo)_o1_);
			else if (_o1_ instanceof RoleAmuletInfo.Data) assign((RoleAmuletInfo.Data)_o1_);
			else if (_o1_ instanceof RoleAmuletInfo.Const) assign(((RoleAmuletInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleAmuletInfo _o_) {
			pagemap = new java.util.HashMap<Integer, xbean.AmuletPage>();
			for (java.util.Map.Entry<Integer, xbean.AmuletPage> _e_ : _o_.pagemap.entrySet())
				pagemap.put(_e_.getKey(), new AmuletPage.Data(_e_.getValue()));
		}

		private void assign(RoleAmuletInfo.Data _o_) {
			pagemap = new java.util.HashMap<Integer, xbean.AmuletPage>();
			for (java.util.Map.Entry<Integer, xbean.AmuletPage> _e_ : _o_.pagemap.entrySet())
				pagemap.put(_e_.getKey(), new AmuletPage.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(pagemap.size());
for (java.util.Map.Entry<Integer, xbean.AmuletPage> _e_ : pagemap.entrySet())
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
		pagemap = new java.util.HashMap<Integer, xbean.AmuletPage>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.AmuletPage _v_ = xbean.Pod.newAmuletPageData();
		_v_.unmarshal(_os_);
		pagemap.put(_k_, _v_);
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
		public xbean.RoleAmuletInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleAmuletInfo toData() {
			return new Data(this);
		}

		public xbean.RoleAmuletInfo toBean() {
			return new RoleAmuletInfo(this, null, null);
		}

		@Override
		public xbean.RoleAmuletInfo toDataIf() {
			return this;
		}

		public xbean.RoleAmuletInfo toBeanIf() {
			return new RoleAmuletInfo(this, null, null);
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
		public java.util.Map<Integer, xbean.AmuletPage> getPagemap() { // 护符页信息,key为页id，1,2,3
			return pagemap;
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletPage> getPagemapAsData() { // 护符页信息,key为页id，1,2,3
			return pagemap;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleAmuletInfo.Data)) return false;
			RoleAmuletInfo.Data _o_ = (RoleAmuletInfo.Data) _o1_;
			if (!pagemap.equals(_o_.pagemap)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += pagemap.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(pagemap);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
