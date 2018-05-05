
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class GroupAttr extends xdb.XBean implements xbean.GroupAttr {
	private java.util.HashMap<Integer, Float> attrs; // 

	@Override
	public void _reset_unsafe_() {
		attrs.clear();
	}

	GroupAttr(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		attrs = new java.util.HashMap<Integer, Float>();
	}

	public GroupAttr() {
		this(0, null, null);
	}

	public GroupAttr(GroupAttr _o_) {
		this(_o_, null, null);
	}

	GroupAttr(xbean.GroupAttr _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof GroupAttr) assign((GroupAttr)_o1_);
		else if (_o1_ instanceof GroupAttr.Data) assign((GroupAttr.Data)_o1_);
		else if (_o1_ instanceof GroupAttr.Const) assign(((GroupAttr.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(GroupAttr _o_) {
		_o_._xdb_verify_unsafe_();
		attrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.attrs.entrySet())
			attrs.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(GroupAttr.Data _o_) {
		attrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.attrs.entrySet())
			attrs.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(attrs.size());
for (java.util.Map.Entry<Integer, Float> _e_ : attrs.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
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
		attrs = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		attrs.put(_k_, _v_);
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
	public xbean.GroupAttr copy() {
		_xdb_verify_unsafe_();
		return new GroupAttr(this);
	}

	@Override
	public xbean.GroupAttr toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GroupAttr toBean() {
		_xdb_verify_unsafe_();
		return new GroupAttr(this); // same as copy()
	}

	@Override
	public xbean.GroupAttr toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.GroupAttr toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, Float> getAttrs() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "attrs"), attrs);
	}

	@Override
	public java.util.Map<Integer, Float> getAttrsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Float> attrs;
		GroupAttr _o_ = this;
		attrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.attrs.entrySet())
			attrs.put(_e_.getKey(), _e_.getValue());
		return attrs;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		GroupAttr _o_ = null;
		if ( _o1_ instanceof GroupAttr ) _o_ = (GroupAttr)_o1_;
		else if ( _o1_ instanceof GroupAttr.Const ) _o_ = ((GroupAttr.Const)_o1_).nThis();
		else return false;
		if (!attrs.equals(_o_.attrs)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += attrs.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(attrs);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("attrs"));
		return lb;
	}

	private class Const implements xbean.GroupAttr {
		GroupAttr nThis() {
			return GroupAttr.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.GroupAttr copy() {
			return GroupAttr.this.copy();
		}

		@Override
		public xbean.GroupAttr toData() {
			return GroupAttr.this.toData();
		}

		public xbean.GroupAttr toBean() {
			return GroupAttr.this.toBean();
		}

		@Override
		public xbean.GroupAttr toDataIf() {
			return GroupAttr.this.toDataIf();
		}

		public xbean.GroupAttr toBeanIf() {
			return GroupAttr.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, Float> getAttrs() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(attrs);
		}

		@Override
		public java.util.Map<Integer, Float> getAttrsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Float> attrs;
			GroupAttr _o_ = GroupAttr.this;
			attrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.attrs.entrySet())
				attrs.put(_e_.getKey(), _e_.getValue());
			return attrs;
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
			return GroupAttr.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return GroupAttr.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return GroupAttr.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return GroupAttr.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return GroupAttr.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return GroupAttr.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return GroupAttr.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return GroupAttr.this.hashCode();
		}

		@Override
		public String toString() {
			return GroupAttr.this.toString();
		}

	}

	public static final class Data implements xbean.GroupAttr {
		private java.util.HashMap<Integer, Float> attrs; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			attrs = new java.util.HashMap<Integer, Float>();
		}

		Data(xbean.GroupAttr _o1_) {
			if (_o1_ instanceof GroupAttr) assign((GroupAttr)_o1_);
			else if (_o1_ instanceof GroupAttr.Data) assign((GroupAttr.Data)_o1_);
			else if (_o1_ instanceof GroupAttr.Const) assign(((GroupAttr.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(GroupAttr _o_) {
			attrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.attrs.entrySet())
				attrs.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(GroupAttr.Data _o_) {
			attrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.attrs.entrySet())
				attrs.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(attrs.size());
for (java.util.Map.Entry<Integer, Float> _e_ : attrs.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
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
		attrs = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		attrs.put(_k_, _v_);
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
		public xbean.GroupAttr copy() {
			return new Data(this);
		}

		@Override
		public xbean.GroupAttr toData() {
			return new Data(this);
		}

		public xbean.GroupAttr toBean() {
			return new GroupAttr(this, null, null);
		}

		@Override
		public xbean.GroupAttr toDataIf() {
			return this;
		}

		public xbean.GroupAttr toBeanIf() {
			return new GroupAttr(this, null, null);
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
		public java.util.Map<Integer, Float> getAttrs() { // 
			return attrs;
		}

		@Override
		public java.util.Map<Integer, Float> getAttrsAsData() { // 
			return attrs;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof GroupAttr.Data)) return false;
			GroupAttr.Data _o_ = (GroupAttr.Data) _o1_;
			if (!attrs.equals(_o_.attrs)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += attrs.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(attrs);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
