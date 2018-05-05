
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class GlobalAllActivity extends xdb.XBean implements xbean.GlobalAllActivity {
	private java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos> openinfos; // 
	private java.util.HashMap<Integer, xbean.GlobalActivity> datas; // 

	@Override
	public void _reset_unsafe_() {
		openinfos.clear();
		datas.clear();
	}

	GlobalAllActivity(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		openinfos = new java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos>();
		datas = new java.util.HashMap<Integer, xbean.GlobalActivity>();
	}

	public GlobalAllActivity() {
		this(0, null, null);
	}

	public GlobalAllActivity(GlobalAllActivity _o_) {
		this(_o_, null, null);
	}

	GlobalAllActivity(xbean.GlobalAllActivity _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof GlobalAllActivity) assign((GlobalAllActivity)_o1_);
		else if (_o1_ instanceof GlobalAllActivity.Data) assign((GlobalAllActivity.Data)_o1_);
		else if (_o1_ instanceof GlobalAllActivity.Const) assign(((GlobalAllActivity.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(GlobalAllActivity _o_) {
		_o_._xdb_verify_unsafe_();
		openinfos = new java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos>();
		for (java.util.Map.Entry<Integer, xbean.GlobalActivityOpenInfos> _e_ : _o_.openinfos.entrySet())
			openinfos.put(_e_.getKey(), new GlobalActivityOpenInfos(_e_.getValue(), this, "openinfos"));
		datas = new java.util.HashMap<Integer, xbean.GlobalActivity>();
		for (java.util.Map.Entry<Integer, xbean.GlobalActivity> _e_ : _o_.datas.entrySet())
			datas.put(_e_.getKey(), new GlobalActivity(_e_.getValue(), this, "datas"));
	}

	private void assign(GlobalAllActivity.Data _o_) {
		openinfos = new java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos>();
		for (java.util.Map.Entry<Integer, xbean.GlobalActivityOpenInfos> _e_ : _o_.openinfos.entrySet())
			openinfos.put(_e_.getKey(), new GlobalActivityOpenInfos(_e_.getValue(), this, "openinfos"));
		datas = new java.util.HashMap<Integer, xbean.GlobalActivity>();
		for (java.util.Map.Entry<Integer, xbean.GlobalActivity> _e_ : _o_.datas.entrySet())
			datas.put(_e_.getKey(), new GlobalActivity(_e_.getValue(), this, "datas"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(openinfos.size());
for (java.util.Map.Entry<Integer, xbean.GlobalActivityOpenInfos> _e_ : openinfos.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(datas.size());
for (java.util.Map.Entry<Integer, xbean.GlobalActivity> _e_ : datas.entrySet())
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
		openinfos = new java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.GlobalActivityOpenInfos _v_ = new GlobalActivityOpenInfos(0, this, "openinfos");
		_v_.unmarshal(_os_);
		openinfos.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		datas = new java.util.HashMap<Integer, xbean.GlobalActivity>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.GlobalActivity _v_ = new GlobalActivity(0, this, "datas");
		_v_.unmarshal(_os_);
		datas.put(_k_, _v_);
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
	public xbean.GlobalAllActivity copy() {
		_xdb_verify_unsafe_();
		return new GlobalAllActivity(this);
	}

	@Override
	public xbean.GlobalAllActivity toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GlobalAllActivity toBean() {
		_xdb_verify_unsafe_();
		return new GlobalAllActivity(this); // same as copy()
	}

	@Override
	public xbean.GlobalAllActivity toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GlobalAllActivity toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.GlobalActivityOpenInfos> getOpeninfos() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "openinfos"), openinfos);
	}

	@Override
	public java.util.Map<Integer, xbean.GlobalActivityOpenInfos> getOpeninfosAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.GlobalActivityOpenInfos> openinfos;
		GlobalAllActivity _o_ = this;
		openinfos = new java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos>();
		for (java.util.Map.Entry<Integer, xbean.GlobalActivityOpenInfos> _e_ : _o_.openinfos.entrySet())
			openinfos.put(_e_.getKey(), new GlobalActivityOpenInfos.Data(_e_.getValue()));
		return openinfos;
	}

	@Override
	public java.util.Map<Integer, xbean.GlobalActivity> getDatas() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "datas"), datas);
	}

	@Override
	public java.util.Map<Integer, xbean.GlobalActivity> getDatasAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.GlobalActivity> datas;
		GlobalAllActivity _o_ = this;
		datas = new java.util.HashMap<Integer, xbean.GlobalActivity>();
		for (java.util.Map.Entry<Integer, xbean.GlobalActivity> _e_ : _o_.datas.entrySet())
			datas.put(_e_.getKey(), new GlobalActivity.Data(_e_.getValue()));
		return datas;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		GlobalAllActivity _o_ = null;
		if ( _o1_ instanceof GlobalAllActivity ) _o_ = (GlobalAllActivity)_o1_;
		else if ( _o1_ instanceof GlobalAllActivity.Const ) _o_ = ((GlobalAllActivity.Const)_o1_).nThis();
		else return false;
		if (!openinfos.equals(_o_.openinfos)) return false;
		if (!datas.equals(_o_.datas)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += openinfos.hashCode();
		_h_ += datas.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(openinfos);
		_sb_.append(",");
		_sb_.append(datas);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("openinfos"));
		lb.add(new xdb.logs.ListenableMap().setVarName("datas"));
		return lb;
	}

	private class Const implements xbean.GlobalAllActivity {
		GlobalAllActivity nThis() {
			return GlobalAllActivity.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.GlobalAllActivity copy() {
			return GlobalAllActivity.this.copy();
		}

		@Override
		public xbean.GlobalAllActivity toData() {
			return GlobalAllActivity.this.toData();
		}

		public xbean.GlobalAllActivity toBean() {
			return GlobalAllActivity.this.toBean();
		}

		@Override
		public xbean.GlobalAllActivity toDataIf() {
			return GlobalAllActivity.this.toDataIf();
		}

		public xbean.GlobalAllActivity toBeanIf() {
			return GlobalAllActivity.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.GlobalActivityOpenInfos> getOpeninfos() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(openinfos);
		}

		@Override
		public java.util.Map<Integer, xbean.GlobalActivityOpenInfos> getOpeninfosAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.GlobalActivityOpenInfos> openinfos;
			GlobalAllActivity _o_ = GlobalAllActivity.this;
			openinfos = new java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos>();
			for (java.util.Map.Entry<Integer, xbean.GlobalActivityOpenInfos> _e_ : _o_.openinfos.entrySet())
				openinfos.put(_e_.getKey(), new GlobalActivityOpenInfos.Data(_e_.getValue()));
			return openinfos;
		}

		@Override
		public java.util.Map<Integer, xbean.GlobalActivity> getDatas() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(datas);
		}

		@Override
		public java.util.Map<Integer, xbean.GlobalActivity> getDatasAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.GlobalActivity> datas;
			GlobalAllActivity _o_ = GlobalAllActivity.this;
			datas = new java.util.HashMap<Integer, xbean.GlobalActivity>();
			for (java.util.Map.Entry<Integer, xbean.GlobalActivity> _e_ : _o_.datas.entrySet())
				datas.put(_e_.getKey(), new GlobalActivity.Data(_e_.getValue()));
			return datas;
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
			return GlobalAllActivity.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return GlobalAllActivity.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return GlobalAllActivity.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return GlobalAllActivity.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return GlobalAllActivity.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return GlobalAllActivity.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return GlobalAllActivity.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return GlobalAllActivity.this.hashCode();
		}

		@Override
		public String toString() {
			return GlobalAllActivity.this.toString();
		}

	}

	public static final class Data implements xbean.GlobalAllActivity {
		private java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos> openinfos; // 
		private java.util.HashMap<Integer, xbean.GlobalActivity> datas; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			openinfos = new java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos>();
			datas = new java.util.HashMap<Integer, xbean.GlobalActivity>();
		}

		Data(xbean.GlobalAllActivity _o1_) {
			if (_o1_ instanceof GlobalAllActivity) assign((GlobalAllActivity)_o1_);
			else if (_o1_ instanceof GlobalAllActivity.Data) assign((GlobalAllActivity.Data)_o1_);
			else if (_o1_ instanceof GlobalAllActivity.Const) assign(((GlobalAllActivity.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(GlobalAllActivity _o_) {
			openinfos = new java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos>();
			for (java.util.Map.Entry<Integer, xbean.GlobalActivityOpenInfos> _e_ : _o_.openinfos.entrySet())
				openinfos.put(_e_.getKey(), new GlobalActivityOpenInfos.Data(_e_.getValue()));
			datas = new java.util.HashMap<Integer, xbean.GlobalActivity>();
			for (java.util.Map.Entry<Integer, xbean.GlobalActivity> _e_ : _o_.datas.entrySet())
				datas.put(_e_.getKey(), new GlobalActivity.Data(_e_.getValue()));
		}

		private void assign(GlobalAllActivity.Data _o_) {
			openinfos = new java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos>();
			for (java.util.Map.Entry<Integer, xbean.GlobalActivityOpenInfos> _e_ : _o_.openinfos.entrySet())
				openinfos.put(_e_.getKey(), new GlobalActivityOpenInfos.Data(_e_.getValue()));
			datas = new java.util.HashMap<Integer, xbean.GlobalActivity>();
			for (java.util.Map.Entry<Integer, xbean.GlobalActivity> _e_ : _o_.datas.entrySet())
				datas.put(_e_.getKey(), new GlobalActivity.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(openinfos.size());
for (java.util.Map.Entry<Integer, xbean.GlobalActivityOpenInfos> _e_ : openinfos.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(datas.size());
for (java.util.Map.Entry<Integer, xbean.GlobalActivity> _e_ : datas.entrySet())
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
		openinfos = new java.util.HashMap<Integer, xbean.GlobalActivityOpenInfos>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.GlobalActivityOpenInfos _v_ = xbean.Pod.newGlobalActivityOpenInfosData();
		_v_.unmarshal(_os_);
		openinfos.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		datas = new java.util.HashMap<Integer, xbean.GlobalActivity>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.GlobalActivity _v_ = xbean.Pod.newGlobalActivityData();
		_v_.unmarshal(_os_);
		datas.put(_k_, _v_);
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
		public xbean.GlobalAllActivity copy() {
			return new Data(this);
		}

		@Override
		public xbean.GlobalAllActivity toData() {
			return new Data(this);
		}

		public xbean.GlobalAllActivity toBean() {
			return new GlobalAllActivity(this, null, null);
		}

		@Override
		public xbean.GlobalAllActivity toDataIf() {
			return this;
		}

		public xbean.GlobalAllActivity toBeanIf() {
			return new GlobalAllActivity(this, null, null);
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
		public java.util.Map<Integer, xbean.GlobalActivityOpenInfos> getOpeninfos() { // 
			return openinfos;
		}

		@Override
		public java.util.Map<Integer, xbean.GlobalActivityOpenInfos> getOpeninfosAsData() { // 
			return openinfos;
		}

		@Override
		public java.util.Map<Integer, xbean.GlobalActivity> getDatas() { // 
			return datas;
		}

		@Override
		public java.util.Map<Integer, xbean.GlobalActivity> getDatasAsData() { // 
			return datas;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof GlobalAllActivity.Data)) return false;
			GlobalAllActivity.Data _o_ = (GlobalAllActivity.Data) _o1_;
			if (!openinfos.equals(_o_.openinfos)) return false;
			if (!datas.equals(_o_.datas)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += openinfos.hashCode();
			_h_ += datas.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(openinfos);
			_sb_.append(",");
			_sb_.append(datas);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
