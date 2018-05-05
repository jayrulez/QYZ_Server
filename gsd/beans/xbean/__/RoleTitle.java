
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleTitle extends xdb.XBean implements xbean.RoleTitle {
	private int activekey; // 当前激活的称号id
	private int activetype; // 当前激活的称号的类型
	private java.util.HashMap<Integer, xbean.GroupTitle> titleinfo; // 称号信息，key为称号的类型，如战力榜，竞技场榜，财富榜，成就榜等

	@Override
	public void _reset_unsafe_() {
		activekey = 0;
		activetype = 0;
		titleinfo.clear();
	}

	RoleTitle(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		titleinfo = new java.util.HashMap<Integer, xbean.GroupTitle>();
	}

	public RoleTitle() {
		this(0, null, null);
	}

	public RoleTitle(RoleTitle _o_) {
		this(_o_, null, null);
	}

	RoleTitle(xbean.RoleTitle _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleTitle) assign((RoleTitle)_o1_);
		else if (_o1_ instanceof RoleTitle.Data) assign((RoleTitle.Data)_o1_);
		else if (_o1_ instanceof RoleTitle.Const) assign(((RoleTitle.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleTitle _o_) {
		_o_._xdb_verify_unsafe_();
		activekey = _o_.activekey;
		activetype = _o_.activetype;
		titleinfo = new java.util.HashMap<Integer, xbean.GroupTitle>();
		for (java.util.Map.Entry<Integer, xbean.GroupTitle> _e_ : _o_.titleinfo.entrySet())
			titleinfo.put(_e_.getKey(), new GroupTitle(_e_.getValue(), this, "titleinfo"));
	}

	private void assign(RoleTitle.Data _o_) {
		activekey = _o_.activekey;
		activetype = _o_.activetype;
		titleinfo = new java.util.HashMap<Integer, xbean.GroupTitle>();
		for (java.util.Map.Entry<Integer, xbean.GroupTitle> _e_ : _o_.titleinfo.entrySet())
			titleinfo.put(_e_.getKey(), new GroupTitle(_e_.getValue(), this, "titleinfo"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  1));_os_.marshal(activekey);
    _os_.marshal((short)( 8192|  2));_os_.marshal(activetype);
    _os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(titleinfo.size());
for (java.util.Map.Entry<Integer, xbean.GroupTitle> _e_ : titleinfo.entrySet())
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
    				case ( 8192|  1):activekey = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):activekey = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):activetype = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):activetype = _os_.unmarshal_short();
    				break;
    				case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		titleinfo = new java.util.HashMap<Integer, xbean.GroupTitle>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.GroupTitle _v_ = new GroupTitle(0, this, "titleinfo");
		_v_.unmarshal(_os_);
		titleinfo.put(_k_, _v_);
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
	public xbean.RoleTitle copy() {
		_xdb_verify_unsafe_();
		return new RoleTitle(this);
	}

	@Override
	public xbean.RoleTitle toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleTitle toBean() {
		_xdb_verify_unsafe_();
		return new RoleTitle(this); // same as copy()
	}

	@Override
	public xbean.RoleTitle toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleTitle toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getActivekey() { // 当前激活的称号id
		_xdb_verify_unsafe_();
		return activekey;
	}

	@Override
	public int getActivetype() { // 当前激活的称号的类型
		_xdb_verify_unsafe_();
		return activetype;
	}

	@Override
	public java.util.Map<Integer, xbean.GroupTitle> getTitleinfo() { // 称号信息，key为称号的类型，如战力榜，竞技场榜，财富榜，成就榜等
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "titleinfo"), titleinfo);
	}

	@Override
	public java.util.Map<Integer, xbean.GroupTitle> getTitleinfoAsData() { // 称号信息，key为称号的类型，如战力榜，竞技场榜，财富榜，成就榜等
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.GroupTitle> titleinfo;
		RoleTitle _o_ = this;
		titleinfo = new java.util.HashMap<Integer, xbean.GroupTitle>();
		for (java.util.Map.Entry<Integer, xbean.GroupTitle> _e_ : _o_.titleinfo.entrySet())
			titleinfo.put(_e_.getKey(), new GroupTitle.Data(_e_.getValue()));
		return titleinfo;
	}

	@Override
	public void setActivekey(int _v_) { // 当前激活的称号id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "activekey") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, activekey) {
					public void rollback() { activekey = _xdb_saved; }
				};}});
		activekey = _v_;
	}

	@Override
	public void setActivetype(int _v_) { // 当前激活的称号的类型
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "activetype") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, activetype) {
					public void rollback() { activetype = _xdb_saved; }
				};}});
		activetype = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleTitle _o_ = null;
		if ( _o1_ instanceof RoleTitle ) _o_ = (RoleTitle)_o1_;
		else if ( _o1_ instanceof RoleTitle.Const ) _o_ = ((RoleTitle.Const)_o1_).nThis();
		else return false;
		if (activekey != _o_.activekey) return false;
		if (activetype != _o_.activetype) return false;
		if (!titleinfo.equals(_o_.titleinfo)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += activekey;
		_h_ += activetype;
		_h_ += titleinfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(activekey);
		_sb_.append(",");
		_sb_.append(activetype);
		_sb_.append(",");
		_sb_.append(titleinfo);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("activekey"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("activetype"));
		lb.add(new xdb.logs.ListenableMap().setVarName("titleinfo"));
		return lb;
	}

	private class Const implements xbean.RoleTitle {
		RoleTitle nThis() {
			return RoleTitle.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleTitle copy() {
			return RoleTitle.this.copy();
		}

		@Override
		public xbean.RoleTitle toData() {
			return RoleTitle.this.toData();
		}

		public xbean.RoleTitle toBean() {
			return RoleTitle.this.toBean();
		}

		@Override
		public xbean.RoleTitle toDataIf() {
			return RoleTitle.this.toDataIf();
		}

		public xbean.RoleTitle toBeanIf() {
			return RoleTitle.this.toBeanIf();
		}

		@Override
		public int getActivekey() { // 当前激活的称号id
			_xdb_verify_unsafe_();
			return activekey;
		}

		@Override
		public int getActivetype() { // 当前激活的称号的类型
			_xdb_verify_unsafe_();
			return activetype;
		}

		@Override
		public java.util.Map<Integer, xbean.GroupTitle> getTitleinfo() { // 称号信息，key为称号的类型，如战力榜，竞技场榜，财富榜，成就榜等
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(titleinfo);
		}

		@Override
		public java.util.Map<Integer, xbean.GroupTitle> getTitleinfoAsData() { // 称号信息，key为称号的类型，如战力榜，竞技场榜，财富榜，成就榜等
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.GroupTitle> titleinfo;
			RoleTitle _o_ = RoleTitle.this;
			titleinfo = new java.util.HashMap<Integer, xbean.GroupTitle>();
			for (java.util.Map.Entry<Integer, xbean.GroupTitle> _e_ : _o_.titleinfo.entrySet())
				titleinfo.put(_e_.getKey(), new GroupTitle.Data(_e_.getValue()));
			return titleinfo;
		}

		@Override
		public void setActivekey(int _v_) { // 当前激活的称号id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setActivetype(int _v_) { // 当前激活的称号的类型
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
			return RoleTitle.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleTitle.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleTitle.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleTitle.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleTitle.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleTitle.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleTitle.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleTitle.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleTitle.this.toString();
		}

	}

	public static final class Data implements xbean.RoleTitle {
		private int activekey; // 当前激活的称号id
		private int activetype; // 当前激活的称号的类型
		private java.util.HashMap<Integer, xbean.GroupTitle> titleinfo; // 称号信息，key为称号的类型，如战力榜，竞技场榜，财富榜，成就榜等

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			titleinfo = new java.util.HashMap<Integer, xbean.GroupTitle>();
		}

		Data(xbean.RoleTitle _o1_) {
			if (_o1_ instanceof RoleTitle) assign((RoleTitle)_o1_);
			else if (_o1_ instanceof RoleTitle.Data) assign((RoleTitle.Data)_o1_);
			else if (_o1_ instanceof RoleTitle.Const) assign(((RoleTitle.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleTitle _o_) {
			activekey = _o_.activekey;
			activetype = _o_.activetype;
			titleinfo = new java.util.HashMap<Integer, xbean.GroupTitle>();
			for (java.util.Map.Entry<Integer, xbean.GroupTitle> _e_ : _o_.titleinfo.entrySet())
				titleinfo.put(_e_.getKey(), new GroupTitle.Data(_e_.getValue()));
		}

		private void assign(RoleTitle.Data _o_) {
			activekey = _o_.activekey;
			activetype = _o_.activetype;
			titleinfo = new java.util.HashMap<Integer, xbean.GroupTitle>();
			for (java.util.Map.Entry<Integer, xbean.GroupTitle> _e_ : _o_.titleinfo.entrySet())
				titleinfo.put(_e_.getKey(), new GroupTitle.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  1));_os_.marshal(activekey);
	_os_.marshal((short)( 8192|  2));_os_.marshal(activetype);
	_os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(titleinfo.size());
for (java.util.Map.Entry<Integer, xbean.GroupTitle> _e_ : titleinfo.entrySet())
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
					case ( 8192|  1):activekey = _os_.unmarshal_int();
					break;
					case ( 6144|  1):activekey = _os_.unmarshal_short();
					break;
					case ( 8192|  2):activetype = _os_.unmarshal_int();
					break;
					case ( 6144|  2):activetype = _os_.unmarshal_short();
					break;
					case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		titleinfo = new java.util.HashMap<Integer, xbean.GroupTitle>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.GroupTitle _v_ = xbean.Pod.newGroupTitleData();
		_v_.unmarshal(_os_);
		titleinfo.put(_k_, _v_);
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
		public xbean.RoleTitle copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleTitle toData() {
			return new Data(this);
		}

		public xbean.RoleTitle toBean() {
			return new RoleTitle(this, null, null);
		}

		@Override
		public xbean.RoleTitle toDataIf() {
			return this;
		}

		public xbean.RoleTitle toBeanIf() {
			return new RoleTitle(this, null, null);
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
		public int getActivekey() { // 当前激活的称号id
			return activekey;
		}

		@Override
		public int getActivetype() { // 当前激活的称号的类型
			return activetype;
		}

		@Override
		public java.util.Map<Integer, xbean.GroupTitle> getTitleinfo() { // 称号信息，key为称号的类型，如战力榜，竞技场榜，财富榜，成就榜等
			return titleinfo;
		}

		@Override
		public java.util.Map<Integer, xbean.GroupTitle> getTitleinfoAsData() { // 称号信息，key为称号的类型，如战力榜，竞技场榜，财富榜，成就榜等
			return titleinfo;
		}

		@Override
		public void setActivekey(int _v_) { // 当前激活的称号id
			activekey = _v_;
		}

		@Override
		public void setActivetype(int _v_) { // 当前激活的称号的类型
			activetype = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleTitle.Data)) return false;
			RoleTitle.Data _o_ = (RoleTitle.Data) _o1_;
			if (activekey != _o_.activekey) return false;
			if (activetype != _o_.activetype) return false;
			if (!titleinfo.equals(_o_.titleinfo)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += activekey;
			_h_ += activetype;
			_h_ += titleinfo.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(activekey);
			_sb_.append(",");
			_sb_.append(activetype);
			_sb_.append(",");
			_sb_.append(titleinfo);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
