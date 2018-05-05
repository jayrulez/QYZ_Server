
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class HuiWuPriviousTerm extends xdb.XBean implements xbean.HuiWuPriviousTerm {
	private int termid; // 
	private long opentime; // 
	private long endtime; // 
	private java.util.HashMap<Integer, xbean.HuiWuChampion> champions; // profession -> HuiWuChampion

	@Override
	public void _reset_unsafe_() {
		termid = 0;
		opentime = 0L;
		endtime = 0L;
		champions.clear();
	}

	HuiWuPriviousTerm(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		champions = new java.util.HashMap<Integer, xbean.HuiWuChampion>();
	}

	public HuiWuPriviousTerm() {
		this(0, null, null);
	}

	public HuiWuPriviousTerm(HuiWuPriviousTerm _o_) {
		this(_o_, null, null);
	}

	HuiWuPriviousTerm(xbean.HuiWuPriviousTerm _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof HuiWuPriviousTerm) assign((HuiWuPriviousTerm)_o1_);
		else if (_o1_ instanceof HuiWuPriviousTerm.Data) assign((HuiWuPriviousTerm.Data)_o1_);
		else if (_o1_ instanceof HuiWuPriviousTerm.Const) assign(((HuiWuPriviousTerm.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(HuiWuPriviousTerm _o_) {
		_o_._xdb_verify_unsafe_();
		termid = _o_.termid;
		opentime = _o_.opentime;
		endtime = _o_.endtime;
		champions = new java.util.HashMap<Integer, xbean.HuiWuChampion>();
		for (java.util.Map.Entry<Integer, xbean.HuiWuChampion> _e_ : _o_.champions.entrySet())
			champions.put(_e_.getKey(), new HuiWuChampion(_e_.getValue(), this, "champions"));
	}

	private void assign(HuiWuPriviousTerm.Data _o_) {
		termid = _o_.termid;
		opentime = _o_.opentime;
		endtime = _o_.endtime;
		champions = new java.util.HashMap<Integer, xbean.HuiWuChampion>();
		for (java.util.Map.Entry<Integer, xbean.HuiWuChampion> _e_ : _o_.champions.entrySet())
			champions.put(_e_.getKey(), new HuiWuChampion(_e_.getValue(), this, "champions"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)( 8192|  1));_os_.marshal(termid);
    _os_.marshal((short)(10240|  2));_os_.marshal(opentime);
    _os_.marshal((short)(10240|  3));_os_.marshal(endtime);
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(champions.size());
for (java.util.Map.Entry<Integer, xbean.HuiWuChampion> _e_ : champions.entrySet())
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
    				case ( 8192|  1):termid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):termid = _os_.unmarshal_short();
    				break;
    				case (10240|  2):opentime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):opentime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):opentime = _os_.unmarshal_int();
    				break;
    				case (10240|  3):endtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):endtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):endtime = _os_.unmarshal_int();
    				break;
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		champions = new java.util.HashMap<Integer, xbean.HuiWuChampion>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.HuiWuChampion _v_ = new HuiWuChampion(0, this, "champions");
		_v_.unmarshal(_os_);
		champions.put(_k_, _v_);
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
	public xbean.HuiWuPriviousTerm copy() {
		_xdb_verify_unsafe_();
		return new HuiWuPriviousTerm(this);
	}

	@Override
	public xbean.HuiWuPriviousTerm toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuPriviousTerm toBean() {
		_xdb_verify_unsafe_();
		return new HuiWuPriviousTerm(this); // same as copy()
	}

	@Override
	public xbean.HuiWuPriviousTerm toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuPriviousTerm toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getTermid() { // 
		_xdb_verify_unsafe_();
		return termid;
	}

	@Override
	public long getOpentime() { // 
		_xdb_verify_unsafe_();
		return opentime;
	}

	@Override
	public long getEndtime() { // 
		_xdb_verify_unsafe_();
		return endtime;
	}

	@Override
	public java.util.Map<Integer, xbean.HuiWuChampion> getChampions() { // profession -> HuiWuChampion
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "champions"), champions);
	}

	@Override
	public java.util.Map<Integer, xbean.HuiWuChampion> getChampionsAsData() { // profession -> HuiWuChampion
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.HuiWuChampion> champions;
		HuiWuPriviousTerm _o_ = this;
		champions = new java.util.HashMap<Integer, xbean.HuiWuChampion>();
		for (java.util.Map.Entry<Integer, xbean.HuiWuChampion> _e_ : _o_.champions.entrySet())
			champions.put(_e_.getKey(), new HuiWuChampion.Data(_e_.getValue()));
		return champions;
	}

	@Override
	public void setTermid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "termid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, termid) {
					public void rollback() { termid = _xdb_saved; }
				};}});
		termid = _v_;
	}

	@Override
	public void setOpentime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "opentime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, opentime) {
					public void rollback() { opentime = _xdb_saved; }
				};}});
		opentime = _v_;
	}

	@Override
	public void setEndtime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "endtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, endtime) {
					public void rollback() { endtime = _xdb_saved; }
				};}});
		endtime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		HuiWuPriviousTerm _o_ = null;
		if ( _o1_ instanceof HuiWuPriviousTerm ) _o_ = (HuiWuPriviousTerm)_o1_;
		else if ( _o1_ instanceof HuiWuPriviousTerm.Const ) _o_ = ((HuiWuPriviousTerm.Const)_o1_).nThis();
		else return false;
		if (termid != _o_.termid) return false;
		if (opentime != _o_.opentime) return false;
		if (endtime != _o_.endtime) return false;
		if (!champions.equals(_o_.champions)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += termid;
		_h_ += opentime;
		_h_ += endtime;
		_h_ += champions.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(termid);
		_sb_.append(",");
		_sb_.append(opentime);
		_sb_.append(",");
		_sb_.append(endtime);
		_sb_.append(",");
		_sb_.append(champions);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("termid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("opentime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("endtime"));
		lb.add(new xdb.logs.ListenableMap().setVarName("champions"));
		return lb;
	}

	private class Const implements xbean.HuiWuPriviousTerm {
		HuiWuPriviousTerm nThis() {
			return HuiWuPriviousTerm.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.HuiWuPriviousTerm copy() {
			return HuiWuPriviousTerm.this.copy();
		}

		@Override
		public xbean.HuiWuPriviousTerm toData() {
			return HuiWuPriviousTerm.this.toData();
		}

		public xbean.HuiWuPriviousTerm toBean() {
			return HuiWuPriviousTerm.this.toBean();
		}

		@Override
		public xbean.HuiWuPriviousTerm toDataIf() {
			return HuiWuPriviousTerm.this.toDataIf();
		}

		public xbean.HuiWuPriviousTerm toBeanIf() {
			return HuiWuPriviousTerm.this.toBeanIf();
		}

		@Override
		public int getTermid() { // 
			_xdb_verify_unsafe_();
			return termid;
		}

		@Override
		public long getOpentime() { // 
			_xdb_verify_unsafe_();
			return opentime;
		}

		@Override
		public long getEndtime() { // 
			_xdb_verify_unsafe_();
			return endtime;
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuChampion> getChampions() { // profession -> HuiWuChampion
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(champions);
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuChampion> getChampionsAsData() { // profession -> HuiWuChampion
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.HuiWuChampion> champions;
			HuiWuPriviousTerm _o_ = HuiWuPriviousTerm.this;
			champions = new java.util.HashMap<Integer, xbean.HuiWuChampion>();
			for (java.util.Map.Entry<Integer, xbean.HuiWuChampion> _e_ : _o_.champions.entrySet())
				champions.put(_e_.getKey(), new HuiWuChampion.Data(_e_.getValue()));
			return champions;
		}

		@Override
		public void setTermid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setOpentime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setEndtime(long _v_) { // 
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
			return HuiWuPriviousTerm.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return HuiWuPriviousTerm.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return HuiWuPriviousTerm.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return HuiWuPriviousTerm.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return HuiWuPriviousTerm.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return HuiWuPriviousTerm.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return HuiWuPriviousTerm.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return HuiWuPriviousTerm.this.hashCode();
		}

		@Override
		public String toString() {
			return HuiWuPriviousTerm.this.toString();
		}

	}

	public static final class Data implements xbean.HuiWuPriviousTerm {
		private int termid; // 
		private long opentime; // 
		private long endtime; // 
		private java.util.HashMap<Integer, xbean.HuiWuChampion> champions; // profession -> HuiWuChampion

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			champions = new java.util.HashMap<Integer, xbean.HuiWuChampion>();
		}

		Data(xbean.HuiWuPriviousTerm _o1_) {
			if (_o1_ instanceof HuiWuPriviousTerm) assign((HuiWuPriviousTerm)_o1_);
			else if (_o1_ instanceof HuiWuPriviousTerm.Data) assign((HuiWuPriviousTerm.Data)_o1_);
			else if (_o1_ instanceof HuiWuPriviousTerm.Const) assign(((HuiWuPriviousTerm.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(HuiWuPriviousTerm _o_) {
			termid = _o_.termid;
			opentime = _o_.opentime;
			endtime = _o_.endtime;
			champions = new java.util.HashMap<Integer, xbean.HuiWuChampion>();
			for (java.util.Map.Entry<Integer, xbean.HuiWuChampion> _e_ : _o_.champions.entrySet())
				champions.put(_e_.getKey(), new HuiWuChampion.Data(_e_.getValue()));
		}

		private void assign(HuiWuPriviousTerm.Data _o_) {
			termid = _o_.termid;
			opentime = _o_.opentime;
			endtime = _o_.endtime;
			champions = new java.util.HashMap<Integer, xbean.HuiWuChampion>();
			for (java.util.Map.Entry<Integer, xbean.HuiWuChampion> _e_ : _o_.champions.entrySet())
				champions.put(_e_.getKey(), new HuiWuChampion.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)( 8192|  1));_os_.marshal(termid);
	_os_.marshal((short)(10240|  2));_os_.marshal(opentime);
	_os_.marshal((short)(10240|  3));_os_.marshal(endtime);
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(champions.size());
for (java.util.Map.Entry<Integer, xbean.HuiWuChampion> _e_ : champions.entrySet())
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
					case ( 8192|  1):termid = _os_.unmarshal_int();
					break;
					case ( 6144|  1):termid = _os_.unmarshal_short();
					break;
					case (10240|  2):opentime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):opentime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):opentime = _os_.unmarshal_int();
					break;
					case (10240|  3):endtime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):endtime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):endtime = _os_.unmarshal_int();
					break;
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		champions = new java.util.HashMap<Integer, xbean.HuiWuChampion>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.HuiWuChampion _v_ = xbean.Pod.newHuiWuChampionData();
		_v_.unmarshal(_os_);
		champions.put(_k_, _v_);
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
		public xbean.HuiWuPriviousTerm copy() {
			return new Data(this);
		}

		@Override
		public xbean.HuiWuPriviousTerm toData() {
			return new Data(this);
		}

		public xbean.HuiWuPriviousTerm toBean() {
			return new HuiWuPriviousTerm(this, null, null);
		}

		@Override
		public xbean.HuiWuPriviousTerm toDataIf() {
			return this;
		}

		public xbean.HuiWuPriviousTerm toBeanIf() {
			return new HuiWuPriviousTerm(this, null, null);
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
		public int getTermid() { // 
			return termid;
		}

		@Override
		public long getOpentime() { // 
			return opentime;
		}

		@Override
		public long getEndtime() { // 
			return endtime;
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuChampion> getChampions() { // profession -> HuiWuChampion
			return champions;
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuChampion> getChampionsAsData() { // profession -> HuiWuChampion
			return champions;
		}

		@Override
		public void setTermid(int _v_) { // 
			termid = _v_;
		}

		@Override
		public void setOpentime(long _v_) { // 
			opentime = _v_;
		}

		@Override
		public void setEndtime(long _v_) { // 
			endtime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof HuiWuPriviousTerm.Data)) return false;
			HuiWuPriviousTerm.Data _o_ = (HuiWuPriviousTerm.Data) _o1_;
			if (termid != _o_.termid) return false;
			if (opentime != _o_.opentime) return false;
			if (endtime != _o_.endtime) return false;
			if (!champions.equals(_o_.champions)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += termid;
			_h_ += opentime;
			_h_ += endtime;
			_h_ += champions.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(termid);
			_sb_.append(",");
			_sb_.append(opentime);
			_sb_.append(",");
			_sb_.append(endtime);
			_sb_.append(",");
			_sb_.append(champions);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
