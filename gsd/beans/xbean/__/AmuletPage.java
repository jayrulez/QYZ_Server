
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class AmuletPage extends xdb.XBean implements xbean.AmuletPage {
	private int pageindex; // 护符页的id
	private java.util.HashMap<Integer, xbean.AmuletProperty> propmap; // key是属性index，value是护符属性
	private java.util.HashMap<Integer, xbean.AmuletProperty> lastwashresult; // 上次洗练结果

	@Override
	public void _reset_unsafe_() {
		pageindex = 0;
		propmap.clear();
		lastwashresult.clear();
	}

	AmuletPage(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		propmap = new java.util.HashMap<Integer, xbean.AmuletProperty>();
		lastwashresult = new java.util.HashMap<Integer, xbean.AmuletProperty>();
	}

	public AmuletPage() {
		this(0, null, null);
	}

	public AmuletPage(AmuletPage _o_) {
		this(_o_, null, null);
	}

	AmuletPage(xbean.AmuletPage _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof AmuletPage) assign((AmuletPage)_o1_);
		else if (_o1_ instanceof AmuletPage.Data) assign((AmuletPage.Data)_o1_);
		else if (_o1_ instanceof AmuletPage.Const) assign(((AmuletPage.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(AmuletPage _o_) {
		_o_._xdb_verify_unsafe_();
		pageindex = _o_.pageindex;
		propmap = new java.util.HashMap<Integer, xbean.AmuletProperty>();
		for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.propmap.entrySet())
			propmap.put(_e_.getKey(), new AmuletProperty(_e_.getValue(), this, "propmap"));
		lastwashresult = new java.util.HashMap<Integer, xbean.AmuletProperty>();
		for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.lastwashresult.entrySet())
			lastwashresult.put(_e_.getKey(), new AmuletProperty(_e_.getValue(), this, "lastwashresult"));
	}

	private void assign(AmuletPage.Data _o_) {
		pageindex = _o_.pageindex;
		propmap = new java.util.HashMap<Integer, xbean.AmuletProperty>();
		for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.propmap.entrySet())
			propmap.put(_e_.getKey(), new AmuletProperty(_e_.getValue(), this, "propmap"));
		lastwashresult = new java.util.HashMap<Integer, xbean.AmuletProperty>();
		for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.lastwashresult.entrySet())
			lastwashresult.put(_e_.getKey(), new AmuletProperty(_e_.getValue(), this, "lastwashresult"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  1));_os_.marshal(pageindex);
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(propmap.size());
for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : propmap.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(lastwashresult.size());
for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : lastwashresult.entrySet())
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
    				case ( 8192|  1):pageindex = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):pageindex = _os_.unmarshal_short();
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		propmap = new java.util.HashMap<Integer, xbean.AmuletProperty>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.AmuletProperty _v_ = new AmuletProperty(0, this, "propmap");
		_v_.unmarshal(_os_);
		propmap.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		lastwashresult = new java.util.HashMap<Integer, xbean.AmuletProperty>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.AmuletProperty _v_ = new AmuletProperty(0, this, "lastwashresult");
		_v_.unmarshal(_os_);
		lastwashresult.put(_k_, _v_);
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
	public xbean.AmuletPage copy() {
		_xdb_verify_unsafe_();
		return new AmuletPage(this);
	}

	@Override
	public xbean.AmuletPage toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AmuletPage toBean() {
		_xdb_verify_unsafe_();
		return new AmuletPage(this); // same as copy()
	}

	@Override
	public xbean.AmuletPage toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.AmuletPage toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getPageindex() { // 护符页的id
		_xdb_verify_unsafe_();
		return pageindex;
	}

	@Override
	public java.util.Map<Integer, xbean.AmuletProperty> getPropmap() { // key是属性index，value是护符属性
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "propmap"), propmap);
	}

	@Override
	public java.util.Map<Integer, xbean.AmuletProperty> getPropmapAsData() { // key是属性index，value是护符属性
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.AmuletProperty> propmap;
		AmuletPage _o_ = this;
		propmap = new java.util.HashMap<Integer, xbean.AmuletProperty>();
		for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.propmap.entrySet())
			propmap.put(_e_.getKey(), new AmuletProperty.Data(_e_.getValue()));
		return propmap;
	}

	@Override
	public java.util.Map<Integer, xbean.AmuletProperty> getLastwashresult() { // 上次洗练结果
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "lastwashresult"), lastwashresult);
	}

	@Override
	public java.util.Map<Integer, xbean.AmuletProperty> getLastwashresultAsData() { // 上次洗练结果
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.AmuletProperty> lastwashresult;
		AmuletPage _o_ = this;
		lastwashresult = new java.util.HashMap<Integer, xbean.AmuletProperty>();
		for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.lastwashresult.entrySet())
			lastwashresult.put(_e_.getKey(), new AmuletProperty.Data(_e_.getValue()));
		return lastwashresult;
	}

	@Override
	public void setPageindex(int _v_) { // 护符页的id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "pageindex") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, pageindex) {
					public void rollback() { pageindex = _xdb_saved; }
				};}});
		pageindex = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		AmuletPage _o_ = null;
		if ( _o1_ instanceof AmuletPage ) _o_ = (AmuletPage)_o1_;
		else if ( _o1_ instanceof AmuletPage.Const ) _o_ = ((AmuletPage.Const)_o1_).nThis();
		else return false;
		if (pageindex != _o_.pageindex) return false;
		if (!propmap.equals(_o_.propmap)) return false;
		if (!lastwashresult.equals(_o_.lastwashresult)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += pageindex;
		_h_ += propmap.hashCode();
		_h_ += lastwashresult.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pageindex);
		_sb_.append(",");
		_sb_.append(propmap);
		_sb_.append(",");
		_sb_.append(lastwashresult);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("pageindex"));
		lb.add(new xdb.logs.ListenableMap().setVarName("propmap"));
		lb.add(new xdb.logs.ListenableMap().setVarName("lastwashresult"));
		return lb;
	}

	private class Const implements xbean.AmuletPage {
		AmuletPage nThis() {
			return AmuletPage.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.AmuletPage copy() {
			return AmuletPage.this.copy();
		}

		@Override
		public xbean.AmuletPage toData() {
			return AmuletPage.this.toData();
		}

		public xbean.AmuletPage toBean() {
			return AmuletPage.this.toBean();
		}

		@Override
		public xbean.AmuletPage toDataIf() {
			return AmuletPage.this.toDataIf();
		}

		public xbean.AmuletPage toBeanIf() {
			return AmuletPage.this.toBeanIf();
		}

		@Override
		public int getPageindex() { // 护符页的id
			_xdb_verify_unsafe_();
			return pageindex;
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletProperty> getPropmap() { // key是属性index，value是护符属性
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(propmap);
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletProperty> getPropmapAsData() { // key是属性index，value是护符属性
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.AmuletProperty> propmap;
			AmuletPage _o_ = AmuletPage.this;
			propmap = new java.util.HashMap<Integer, xbean.AmuletProperty>();
			for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.propmap.entrySet())
				propmap.put(_e_.getKey(), new AmuletProperty.Data(_e_.getValue()));
			return propmap;
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletProperty> getLastwashresult() { // 上次洗练结果
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(lastwashresult);
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletProperty> getLastwashresultAsData() { // 上次洗练结果
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.AmuletProperty> lastwashresult;
			AmuletPage _o_ = AmuletPage.this;
			lastwashresult = new java.util.HashMap<Integer, xbean.AmuletProperty>();
			for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.lastwashresult.entrySet())
				lastwashresult.put(_e_.getKey(), new AmuletProperty.Data(_e_.getValue()));
			return lastwashresult;
		}

		@Override
		public void setPageindex(int _v_) { // 护符页的id
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
			return AmuletPage.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return AmuletPage.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return AmuletPage.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return AmuletPage.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return AmuletPage.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return AmuletPage.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return AmuletPage.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return AmuletPage.this.hashCode();
		}

		@Override
		public String toString() {
			return AmuletPage.this.toString();
		}

	}

	public static final class Data implements xbean.AmuletPage {
		private int pageindex; // 护符页的id
		private java.util.HashMap<Integer, xbean.AmuletProperty> propmap; // key是属性index，value是护符属性
		private java.util.HashMap<Integer, xbean.AmuletProperty> lastwashresult; // 上次洗练结果

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			propmap = new java.util.HashMap<Integer, xbean.AmuletProperty>();
			lastwashresult = new java.util.HashMap<Integer, xbean.AmuletProperty>();
		}

		Data(xbean.AmuletPage _o1_) {
			if (_o1_ instanceof AmuletPage) assign((AmuletPage)_o1_);
			else if (_o1_ instanceof AmuletPage.Data) assign((AmuletPage.Data)_o1_);
			else if (_o1_ instanceof AmuletPage.Const) assign(((AmuletPage.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(AmuletPage _o_) {
			pageindex = _o_.pageindex;
			propmap = new java.util.HashMap<Integer, xbean.AmuletProperty>();
			for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.propmap.entrySet())
				propmap.put(_e_.getKey(), new AmuletProperty.Data(_e_.getValue()));
			lastwashresult = new java.util.HashMap<Integer, xbean.AmuletProperty>();
			for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.lastwashresult.entrySet())
				lastwashresult.put(_e_.getKey(), new AmuletProperty.Data(_e_.getValue()));
		}

		private void assign(AmuletPage.Data _o_) {
			pageindex = _o_.pageindex;
			propmap = new java.util.HashMap<Integer, xbean.AmuletProperty>();
			for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.propmap.entrySet())
				propmap.put(_e_.getKey(), new AmuletProperty.Data(_e_.getValue()));
			lastwashresult = new java.util.HashMap<Integer, xbean.AmuletProperty>();
			for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : _o_.lastwashresult.entrySet())
				lastwashresult.put(_e_.getKey(), new AmuletProperty.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  1));_os_.marshal(pageindex);
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(propmap.size());
for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : propmap.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(lastwashresult.size());
for (java.util.Map.Entry<Integer, xbean.AmuletProperty> _e_ : lastwashresult.entrySet())
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
					case ( 8192|  1):pageindex = _os_.unmarshal_int();
					break;
					case ( 6144|  1):pageindex = _os_.unmarshal_short();
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		propmap = new java.util.HashMap<Integer, xbean.AmuletProperty>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.AmuletProperty _v_ = xbean.Pod.newAmuletPropertyData();
		_v_.unmarshal(_os_);
		propmap.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		lastwashresult = new java.util.HashMap<Integer, xbean.AmuletProperty>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.AmuletProperty _v_ = xbean.Pod.newAmuletPropertyData();
		_v_.unmarshal(_os_);
		lastwashresult.put(_k_, _v_);
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
		public xbean.AmuletPage copy() {
			return new Data(this);
		}

		@Override
		public xbean.AmuletPage toData() {
			return new Data(this);
		}

		public xbean.AmuletPage toBean() {
			return new AmuletPage(this, null, null);
		}

		@Override
		public xbean.AmuletPage toDataIf() {
			return this;
		}

		public xbean.AmuletPage toBeanIf() {
			return new AmuletPage(this, null, null);
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
		public int getPageindex() { // 护符页的id
			return pageindex;
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletProperty> getPropmap() { // key是属性index，value是护符属性
			return propmap;
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletProperty> getPropmapAsData() { // key是属性index，value是护符属性
			return propmap;
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletProperty> getLastwashresult() { // 上次洗练结果
			return lastwashresult;
		}

		@Override
		public java.util.Map<Integer, xbean.AmuletProperty> getLastwashresultAsData() { // 上次洗练结果
			return lastwashresult;
		}

		@Override
		public void setPageindex(int _v_) { // 护符页的id
			pageindex = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof AmuletPage.Data)) return false;
			AmuletPage.Data _o_ = (AmuletPage.Data) _o1_;
			if (pageindex != _o_.pageindex) return false;
			if (!propmap.equals(_o_.propmap)) return false;
			if (!lastwashresult.equals(_o_.lastwashresult)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += pageindex;
			_h_ += propmap.hashCode();
			_h_ += lastwashresult.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(pageindex);
			_sb_.append(",");
			_sb_.append(propmap);
			_sb_.append(",");
			_sb_.append(lastwashresult);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
