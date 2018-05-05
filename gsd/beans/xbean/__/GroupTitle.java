
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class GroupTitle extends xdb.XBean implements xbean.GroupTitle {
	private java.util.HashMap<Integer, xbean.Title> titles; // 称号信息

	@Override
	public void _reset_unsafe_() {
		titles.clear();
	}

	GroupTitle(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		titles = new java.util.HashMap<Integer, xbean.Title>();
	}

	public GroupTitle() {
		this(0, null, null);
	}

	public GroupTitle(GroupTitle _o_) {
		this(_o_, null, null);
	}

	GroupTitle(xbean.GroupTitle _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof GroupTitle) assign((GroupTitle)_o1_);
		else if (_o1_ instanceof GroupTitle.Data) assign((GroupTitle.Data)_o1_);
		else if (_o1_ instanceof GroupTitle.Const) assign(((GroupTitle.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(GroupTitle _o_) {
		_o_._xdb_verify_unsafe_();
		titles = new java.util.HashMap<Integer, xbean.Title>();
		for (java.util.Map.Entry<Integer, xbean.Title> _e_ : _o_.titles.entrySet())
			titles.put(_e_.getKey(), new Title(_e_.getValue(), this, "titles"));
	}

	private void assign(GroupTitle.Data _o_) {
		titles = new java.util.HashMap<Integer, xbean.Title>();
		for (java.util.Map.Entry<Integer, xbean.Title> _e_ : _o_.titles.entrySet())
			titles.put(_e_.getKey(), new Title(_e_.getValue(), this, "titles"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(titles.size());
for (java.util.Map.Entry<Integer, xbean.Title> _e_ : titles.entrySet())
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
		titles = new java.util.HashMap<Integer, xbean.Title>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Title _v_ = new Title(0, this, "titles");
		_v_.unmarshal(_os_);
		titles.put(_k_, _v_);
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
	public xbean.GroupTitle copy() {
		_xdb_verify_unsafe_();
		return new GroupTitle(this);
	}

	@Override
	public xbean.GroupTitle toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GroupTitle toBean() {
		_xdb_verify_unsafe_();
		return new GroupTitle(this); // same as copy()
	}

	@Override
	public xbean.GroupTitle toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GroupTitle toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.Title> getTitles() { // 称号信息
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "titles"), titles);
	}

	@Override
	public java.util.Map<Integer, xbean.Title> getTitlesAsData() { // 称号信息
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Title> titles;
		GroupTitle _o_ = this;
		titles = new java.util.HashMap<Integer, xbean.Title>();
		for (java.util.Map.Entry<Integer, xbean.Title> _e_ : _o_.titles.entrySet())
			titles.put(_e_.getKey(), new Title.Data(_e_.getValue()));
		return titles;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		GroupTitle _o_ = null;
		if ( _o1_ instanceof GroupTitle ) _o_ = (GroupTitle)_o1_;
		else if ( _o1_ instanceof GroupTitle.Const ) _o_ = ((GroupTitle.Const)_o1_).nThis();
		else return false;
		if (!titles.equals(_o_.titles)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += titles.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(titles);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("titles"));
		return lb;
	}

	private class Const implements xbean.GroupTitle {
		GroupTitle nThis() {
			return GroupTitle.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.GroupTitle copy() {
			return GroupTitle.this.copy();
		}

		@Override
		public xbean.GroupTitle toData() {
			return GroupTitle.this.toData();
		}

		public xbean.GroupTitle toBean() {
			return GroupTitle.this.toBean();
		}

		@Override
		public xbean.GroupTitle toDataIf() {
			return GroupTitle.this.toDataIf();
		}

		public xbean.GroupTitle toBeanIf() {
			return GroupTitle.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.Title> getTitles() { // 称号信息
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(titles);
		}

		@Override
		public java.util.Map<Integer, xbean.Title> getTitlesAsData() { // 称号信息
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Title> titles;
			GroupTitle _o_ = GroupTitle.this;
			titles = new java.util.HashMap<Integer, xbean.Title>();
			for (java.util.Map.Entry<Integer, xbean.Title> _e_ : _o_.titles.entrySet())
				titles.put(_e_.getKey(), new Title.Data(_e_.getValue()));
			return titles;
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
			return GroupTitle.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return GroupTitle.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return GroupTitle.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return GroupTitle.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return GroupTitle.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return GroupTitle.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return GroupTitle.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return GroupTitle.this.hashCode();
		}

		@Override
		public String toString() {
			return GroupTitle.this.toString();
		}

	}

	public static final class Data implements xbean.GroupTitle {
		private java.util.HashMap<Integer, xbean.Title> titles; // 称号信息

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			titles = new java.util.HashMap<Integer, xbean.Title>();
		}

		Data(xbean.GroupTitle _o1_) {
			if (_o1_ instanceof GroupTitle) assign((GroupTitle)_o1_);
			else if (_o1_ instanceof GroupTitle.Data) assign((GroupTitle.Data)_o1_);
			else if (_o1_ instanceof GroupTitle.Const) assign(((GroupTitle.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(GroupTitle _o_) {
			titles = new java.util.HashMap<Integer, xbean.Title>();
			for (java.util.Map.Entry<Integer, xbean.Title> _e_ : _o_.titles.entrySet())
				titles.put(_e_.getKey(), new Title.Data(_e_.getValue()));
		}

		private void assign(GroupTitle.Data _o_) {
			titles = new java.util.HashMap<Integer, xbean.Title>();
			for (java.util.Map.Entry<Integer, xbean.Title> _e_ : _o_.titles.entrySet())
				titles.put(_e_.getKey(), new Title.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(titles.size());
for (java.util.Map.Entry<Integer, xbean.Title> _e_ : titles.entrySet())
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
		titles = new java.util.HashMap<Integer, xbean.Title>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Title _v_ = xbean.Pod.newTitleData();
		_v_.unmarshal(_os_);
		titles.put(_k_, _v_);
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
		public xbean.GroupTitle copy() {
			return new Data(this);
		}

		@Override
		public xbean.GroupTitle toData() {
			return new Data(this);
		}

		public xbean.GroupTitle toBean() {
			return new GroupTitle(this, null, null);
		}

		@Override
		public xbean.GroupTitle toDataIf() {
			return this;
		}

		public xbean.GroupTitle toBeanIf() {
			return new GroupTitle(this, null, null);
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
		public java.util.Map<Integer, xbean.Title> getTitles() { // 称号信息
			return titles;
		}

		@Override
		public java.util.Map<Integer, xbean.Title> getTitlesAsData() { // 称号信息
			return titles;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof GroupTitle.Data)) return false;
			GroupTitle.Data _o_ = (GroupTitle.Data) _o1_;
			if (!titles.equals(_o_.titles)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += titles.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(titles);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
