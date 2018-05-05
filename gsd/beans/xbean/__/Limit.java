
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Limit extends xdb.XBean implements xbean.Limit {
	private long id; // 
	private java.util.HashMap<Integer, Integer> typenums; // limittype -> num 每种限制类型相应的已达到的次数
	private int lastbuytime; // 

	@Override
	public void _reset_unsafe_() {
		id = 0L;
		typenums.clear();
		lastbuytime = 0;
	}

	Limit(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		typenums = new java.util.HashMap<Integer, Integer>();
	}

	public Limit() {
		this(0, null, null);
	}

	public Limit(Limit _o_) {
		this(_o_, null, null);
	}

	Limit(xbean.Limit _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Limit) assign((Limit)_o1_);
		else if (_o1_ instanceof Limit.Data) assign((Limit.Data)_o1_);
		else if (_o1_ instanceof Limit.Const) assign(((Limit.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Limit _o_) {
		_o_._xdb_verify_unsafe_();
		id = _o_.id;
		typenums = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.typenums.entrySet())
			typenums.put(_e_.getKey(), _e_.getValue());
		lastbuytime = _o_.lastbuytime;
	}

	private void assign(Limit.Data _o_) {
		id = _o_.id;
		typenums = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.typenums.entrySet())
			typenums.put(_e_.getKey(), _e_.getValue());
		lastbuytime = _o_.lastbuytime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)(10240|  0));_os_.marshal(id);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(typenums.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : typenums.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  2));_os_.marshal(lastbuytime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  0):id = _os_.unmarshal_long();
    				break;
    				case ( 6144|  0):id = _os_.unmarshal_short();
    				break;
    				case ( 8192|  0):id = _os_.unmarshal_int();
    				break;
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		typenums = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		typenums.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  2):lastbuytime = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):lastbuytime = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Limit copy() {
		_xdb_verify_unsafe_();
		return new Limit(this);
	}

	@Override
	public xbean.Limit toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Limit toBean() {
		_xdb_verify_unsafe_();
		return new Limit(this); // same as copy()
	}

	@Override
	public xbean.Limit toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Limit toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getId() { // 
		_xdb_verify_unsafe_();
		return id;
	}

	@Override
	public java.util.Map<Integer, Integer> getTypenums() { // limittype -> num 每种限制类型相应的已达到的次数
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "typenums"), typenums);
	}

	@Override
	public java.util.Map<Integer, Integer> getTypenumsAsData() { // limittype -> num 每种限制类型相应的已达到的次数
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> typenums;
		Limit _o_ = this;
		typenums = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.typenums.entrySet())
			typenums.put(_e_.getKey(), _e_.getValue());
		return typenums;
	}

	@Override
	public int getLastbuytime() { // 
		_xdb_verify_unsafe_();
		return lastbuytime;
	}

	@Override
	public void setId(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "id") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, id) {
					public void rollback() { id = _xdb_saved; }
				};}});
		id = _v_;
	}

	@Override
	public void setLastbuytime(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastbuytime") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, lastbuytime) {
					public void rollback() { lastbuytime = _xdb_saved; }
				};}});
		lastbuytime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Limit _o_ = null;
		if ( _o1_ instanceof Limit ) _o_ = (Limit)_o1_;
		else if ( _o1_ instanceof Limit.Const ) _o_ = ((Limit.Const)_o1_).nThis();
		else return false;
		if (id != _o_.id) return false;
		if (!typenums.equals(_o_.typenums)) return false;
		if (lastbuytime != _o_.lastbuytime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += id;
		_h_ += typenums.hashCode();
		_h_ += lastbuytime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id);
		_sb_.append(",");
		_sb_.append(typenums);
		_sb_.append(",");
		_sb_.append(lastbuytime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("id"));
		lb.add(new xdb.logs.ListenableMap().setVarName("typenums"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastbuytime"));
		return lb;
	}

	private class Const implements xbean.Limit {
		Limit nThis() {
			return Limit.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Limit copy() {
			return Limit.this.copy();
		}

		@Override
		public xbean.Limit toData() {
			return Limit.this.toData();
		}

		public xbean.Limit toBean() {
			return Limit.this.toBean();
		}

		@Override
		public xbean.Limit toDataIf() {
			return Limit.this.toDataIf();
		}

		public xbean.Limit toBeanIf() {
			return Limit.this.toBeanIf();
		}

		@Override
		public long getId() { // 
			_xdb_verify_unsafe_();
			return id;
		}

		@Override
		public java.util.Map<Integer, Integer> getTypenums() { // limittype -> num 每种限制类型相应的已达到的次数
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(typenums);
		}

		@Override
		public java.util.Map<Integer, Integer> getTypenumsAsData() { // limittype -> num 每种限制类型相应的已达到的次数
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> typenums;
			Limit _o_ = Limit.this;
			typenums = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.typenums.entrySet())
				typenums.put(_e_.getKey(), _e_.getValue());
			return typenums;
		}

		@Override
		public int getLastbuytime() { // 
			_xdb_verify_unsafe_();
			return lastbuytime;
		}

		@Override
		public void setId(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastbuytime(int _v_) { // 
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
			return Limit.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Limit.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Limit.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Limit.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Limit.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Limit.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Limit.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Limit.this.hashCode();
		}

		@Override
		public String toString() {
			return Limit.this.toString();
		}

	}

	public static final class Data implements xbean.Limit {
		private long id; // 
		private java.util.HashMap<Integer, Integer> typenums; // limittype -> num 每种限制类型相应的已达到的次数
		private int lastbuytime; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			typenums = new java.util.HashMap<Integer, Integer>();
		}

		Data(xbean.Limit _o1_) {
			if (_o1_ instanceof Limit) assign((Limit)_o1_);
			else if (_o1_ instanceof Limit.Data) assign((Limit.Data)_o1_);
			else if (_o1_ instanceof Limit.Const) assign(((Limit.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Limit _o_) {
			id = _o_.id;
			typenums = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.typenums.entrySet())
				typenums.put(_e_.getKey(), _e_.getValue());
			lastbuytime = _o_.lastbuytime;
		}

		private void assign(Limit.Data _o_) {
			id = _o_.id;
			typenums = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.typenums.entrySet())
				typenums.put(_e_.getKey(), _e_.getValue());
			lastbuytime = _o_.lastbuytime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)(10240|  0));_os_.marshal(id);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(typenums.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : typenums.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  2));_os_.marshal(lastbuytime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  0):id = _os_.unmarshal_long();
					break;
					case ( 6144|  0):id = _os_.unmarshal_short();
					break;
					case ( 8192|  0):id = _os_.unmarshal_int();
					break;
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		typenums = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		typenums.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192|  2):lastbuytime = _os_.unmarshal_int();
					break;
					case ( 6144|  2):lastbuytime = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Limit copy() {
			return new Data(this);
		}

		@Override
		public xbean.Limit toData() {
			return new Data(this);
		}

		public xbean.Limit toBean() {
			return new Limit(this, null, null);
		}

		@Override
		public xbean.Limit toDataIf() {
			return this;
		}

		public xbean.Limit toBeanIf() {
			return new Limit(this, null, null);
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
		public long getId() { // 
			return id;
		}

		@Override
		public java.util.Map<Integer, Integer> getTypenums() { // limittype -> num 每种限制类型相应的已达到的次数
			return typenums;
		}

		@Override
		public java.util.Map<Integer, Integer> getTypenumsAsData() { // limittype -> num 每种限制类型相应的已达到的次数
			return typenums;
		}

		@Override
		public int getLastbuytime() { // 
			return lastbuytime;
		}

		@Override
		public void setId(long _v_) { // 
			id = _v_;
		}

		@Override
		public void setLastbuytime(int _v_) { // 
			lastbuytime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Limit.Data)) return false;
			Limit.Data _o_ = (Limit.Data) _o1_;
			if (id != _o_.id) return false;
			if (!typenums.equals(_o_.typenums)) return false;
			if (lastbuytime != _o_.lastbuytime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += id;
			_h_ += typenums.hashCode();
			_h_ += lastbuytime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(id);
			_sb_.append(",");
			_sb_.append(typenums);
			_sb_.append(",");
			_sb_.append(lastbuytime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
