
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleMap extends xdb.XBean implements xbean.RoleMap {
	private java.util.HashMap<Integer, Integer> pkstates; // 在各大地图中的pk状态 worldid -> cfg.fight.PKState
	private boolean finishprologue; // 
	private int hp; // 
	private int mp; // 
	private java.util.LinkedList<xbean.RoleMapInfo> prevs; // 
	private boolean isnew; // 

	@Override
	public void _reset_unsafe_() {
		pkstates.clear();
		finishprologue = false;
		hp = 0;
		mp = 0;
		prevs.clear();
		isnew = false;
	}

	RoleMap(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		pkstates = new java.util.HashMap<Integer, Integer>();
		prevs = new java.util.LinkedList<xbean.RoleMapInfo>();
	}

	public RoleMap() {
		this(0, null, null);
	}

	public RoleMap(RoleMap _o_) {
		this(_o_, null, null);
	}

	RoleMap(xbean.RoleMap _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleMap) assign((RoleMap)_o1_);
		else if (_o1_ instanceof RoleMap.Data) assign((RoleMap.Data)_o1_);
		else if (_o1_ instanceof RoleMap.Const) assign(((RoleMap.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleMap _o_) {
		_o_._xdb_verify_unsafe_();
		pkstates = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.pkstates.entrySet())
			pkstates.put(_e_.getKey(), _e_.getValue());
		finishprologue = _o_.finishprologue;
		hp = _o_.hp;
		mp = _o_.mp;
		prevs = new java.util.LinkedList<xbean.RoleMapInfo>();
		for (xbean.RoleMapInfo _v_ : _o_.prevs)
			prevs.add(new RoleMapInfo(_v_, this, "prevs"));
		isnew = _o_.isnew;
	}

	private void assign(RoleMap.Data _o_) {
		pkstates = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.pkstates.entrySet())
			pkstates.put(_e_.getKey(), _e_.getValue());
		finishprologue = _o_.finishprologue;
		hp = _o_.hp;
		mp = _o_.mp;
		prevs = new java.util.LinkedList<xbean.RoleMapInfo>();
		for (xbean.RoleMapInfo _v_ : _o_.prevs)
			prevs.add(new RoleMapInfo(_v_, this, "prevs"));
		isnew = _o_.isnew;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(pkstates.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : pkstates.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 2048|  3));_os_.marshal(finishprologue);
    _os_.marshal((short)( 8192|  4));_os_.marshal(hp);
    _os_.marshal((short)( 8192|  5));_os_.marshal(mp);
    _os_.marshal((short)(22528|  9));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(prevs.size());
for (xbean.RoleMapInfo _v_ : prevs) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 2048| 10));_os_.marshal(isnew);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		pkstates = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		pkstates.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 2048|  3):finishprologue = _os_.unmarshal_boolean();
    				break;
    				case ( 8192|  4):hp = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):hp = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):mp = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):mp = _os_.unmarshal_short();
    				break;
    				case (22528|  9):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.RoleMapInfo _v_ = new RoleMapInfo(0, this, "prevs");
	_v_.unmarshal(_os_);
	prevs.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 2048| 10):isnew = _os_.unmarshal_boolean();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleMap copy() {
		_xdb_verify_unsafe_();
		return new RoleMap(this);
	}

	@Override
	public xbean.RoleMap toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleMap toBean() {
		_xdb_verify_unsafe_();
		return new RoleMap(this); // same as copy()
	}

	@Override
	public xbean.RoleMap toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleMap toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, Integer> getPkstates() { // 在各大地图中的pk状态 worldid -> cfg.fight.PKState
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "pkstates"), pkstates);
	}

	@Override
	public java.util.Map<Integer, Integer> getPkstatesAsData() { // 在各大地图中的pk状态 worldid -> cfg.fight.PKState
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> pkstates;
		RoleMap _o_ = this;
		pkstates = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.pkstates.entrySet())
			pkstates.put(_e_.getKey(), _e_.getValue());
		return pkstates;
	}

	@Override
	public boolean getFinishprologue() { // 
		_xdb_verify_unsafe_();
		return finishprologue;
	}

	@Override
	public int getHp() { // 
		_xdb_verify_unsafe_();
		return hp;
	}

	@Override
	public int getMp() { // 
		_xdb_verify_unsafe_();
		return mp;
	}

	@Override
	public java.util.List<xbean.RoleMapInfo> getPrevs() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "prevs"), prevs);
	}

	public java.util.List<xbean.RoleMapInfo> getPrevsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<xbean.RoleMapInfo> prevs;
		RoleMap _o_ = this;
		prevs = new java.util.LinkedList<xbean.RoleMapInfo>();
		for (xbean.RoleMapInfo _v_ : _o_.prevs)
			prevs.add(new RoleMapInfo.Data(_v_));
		return prevs;
	}

	@Override
	public boolean getIsnew() { // 
		_xdb_verify_unsafe_();
		return isnew;
	}

	@Override
	public void setFinishprologue(boolean _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "finishprologue") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, finishprologue) {
					public void rollback() { finishprologue = _xdb_saved; }
				};}});
		finishprologue = _v_;
	}

	@Override
	public void setHp(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "hp") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, hp) {
					public void rollback() { hp = _xdb_saved; }
				};}});
		hp = _v_;
	}

	@Override
	public void setMp(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "mp") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, mp) {
					public void rollback() { mp = _xdb_saved; }
				};}});
		mp = _v_;
	}

	@Override
	public void setIsnew(boolean _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "isnew") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, isnew) {
					public void rollback() { isnew = _xdb_saved; }
				};}});
		isnew = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleMap _o_ = null;
		if ( _o1_ instanceof RoleMap ) _o_ = (RoleMap)_o1_;
		else if ( _o1_ instanceof RoleMap.Const ) _o_ = ((RoleMap.Const)_o1_).nThis();
		else return false;
		if (!pkstates.equals(_o_.pkstates)) return false;
		if (finishprologue != _o_.finishprologue) return false;
		if (hp != _o_.hp) return false;
		if (mp != _o_.mp) return false;
		if (!prevs.equals(_o_.prevs)) return false;
		if (isnew != _o_.isnew) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += pkstates.hashCode();
		_h_ += finishprologue ? 1231 : 1237;
		_h_ += hp;
		_h_ += mp;
		_h_ += prevs.hashCode();
		_h_ += isnew ? 1231 : 1237;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pkstates);
		_sb_.append(",");
		_sb_.append(finishprologue);
		_sb_.append(",");
		_sb_.append(hp);
		_sb_.append(",");
		_sb_.append(mp);
		_sb_.append(",");
		_sb_.append(prevs);
		_sb_.append(",");
		_sb_.append(isnew);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("pkstates"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("finishprologue"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("hp"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("mp"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("prevs"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isnew"));
		return lb;
	}

	private class Const implements xbean.RoleMap {
		RoleMap nThis() {
			return RoleMap.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleMap copy() {
			return RoleMap.this.copy();
		}

		@Override
		public xbean.RoleMap toData() {
			return RoleMap.this.toData();
		}

		public xbean.RoleMap toBean() {
			return RoleMap.this.toBean();
		}

		@Override
		public xbean.RoleMap toDataIf() {
			return RoleMap.this.toDataIf();
		}

		public xbean.RoleMap toBeanIf() {
			return RoleMap.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, Integer> getPkstates() { // 在各大地图中的pk状态 worldid -> cfg.fight.PKState
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(pkstates);
		}

		@Override
		public java.util.Map<Integer, Integer> getPkstatesAsData() { // 在各大地图中的pk状态 worldid -> cfg.fight.PKState
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> pkstates;
			RoleMap _o_ = RoleMap.this;
			pkstates = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.pkstates.entrySet())
				pkstates.put(_e_.getKey(), _e_.getValue());
			return pkstates;
		}

		@Override
		public boolean getFinishprologue() { // 
			_xdb_verify_unsafe_();
			return finishprologue;
		}

		@Override
		public int getHp() { // 
			_xdb_verify_unsafe_();
			return hp;
		}

		@Override
		public int getMp() { // 
			_xdb_verify_unsafe_();
			return mp;
		}

		@Override
		public java.util.List<xbean.RoleMapInfo> getPrevs() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(prevs);
		}

		public java.util.List<xbean.RoleMapInfo> getPrevsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<xbean.RoleMapInfo> prevs;
			RoleMap _o_ = RoleMap.this;
		prevs = new java.util.LinkedList<xbean.RoleMapInfo>();
		for (xbean.RoleMapInfo _v_ : _o_.prevs)
			prevs.add(new RoleMapInfo.Data(_v_));
			return prevs;
		}

		@Override
		public boolean getIsnew() { // 
			_xdb_verify_unsafe_();
			return isnew;
		}

		@Override
		public void setFinishprologue(boolean _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHp(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMp(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsnew(boolean _v_) { // 
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
			return RoleMap.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleMap.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleMap.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleMap.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleMap.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleMap.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleMap.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleMap.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleMap.this.toString();
		}

	}

	public static final class Data implements xbean.RoleMap {
		private java.util.HashMap<Integer, Integer> pkstates; // 在各大地图中的pk状态 worldid -> cfg.fight.PKState
		private boolean finishprologue; // 
		private int hp; // 
		private int mp; // 
		private java.util.LinkedList<xbean.RoleMapInfo> prevs; // 
		private boolean isnew; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			pkstates = new java.util.HashMap<Integer, Integer>();
			prevs = new java.util.LinkedList<xbean.RoleMapInfo>();
		}

		Data(xbean.RoleMap _o1_) {
			if (_o1_ instanceof RoleMap) assign((RoleMap)_o1_);
			else if (_o1_ instanceof RoleMap.Data) assign((RoleMap.Data)_o1_);
			else if (_o1_ instanceof RoleMap.Const) assign(((RoleMap.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleMap _o_) {
			pkstates = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.pkstates.entrySet())
				pkstates.put(_e_.getKey(), _e_.getValue());
			finishprologue = _o_.finishprologue;
			hp = _o_.hp;
			mp = _o_.mp;
			prevs = new java.util.LinkedList<xbean.RoleMapInfo>();
			for (xbean.RoleMapInfo _v_ : _o_.prevs)
				prevs.add(new RoleMapInfo.Data(_v_));
			isnew = _o_.isnew;
		}

		private void assign(RoleMap.Data _o_) {
			pkstates = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.pkstates.entrySet())
				pkstates.put(_e_.getKey(), _e_.getValue());
			finishprologue = _o_.finishprologue;
			hp = _o_.hp;
			mp = _o_.mp;
			prevs = new java.util.LinkedList<xbean.RoleMapInfo>();
			for (xbean.RoleMapInfo _v_ : _o_.prevs)
				prevs.add(new RoleMapInfo.Data(_v_));
			isnew = _o_.isnew;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(pkstates.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : pkstates.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 2048|  3));_os_.marshal(finishprologue);
	_os_.marshal((short)( 8192|  4));_os_.marshal(hp);
	_os_.marshal((short)( 8192|  5));_os_.marshal(mp);
	_os_.marshal((short)(22528|  9));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(prevs.size());
for (xbean.RoleMapInfo _v_ : prevs) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 2048| 10));_os_.marshal(isnew);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		pkstates = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		pkstates.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 2048|  3):finishprologue = _os_.unmarshal_boolean();
					break;
					case ( 8192|  4):hp = _os_.unmarshal_int();
					break;
					case ( 6144|  4):hp = _os_.unmarshal_short();
					break;
					case ( 8192|  5):mp = _os_.unmarshal_int();
					break;
					case ( 6144|  5):mp = _os_.unmarshal_short();
					break;
					case (22528|  9):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.RoleMapInfo _v_ = xbean.Pod.newRoleMapInfoData();
	_v_.unmarshal(_os_);
	prevs.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 2048| 10):isnew = _os_.unmarshal_boolean();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleMap copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleMap toData() {
			return new Data(this);
		}

		public xbean.RoleMap toBean() {
			return new RoleMap(this, null, null);
		}

		@Override
		public xbean.RoleMap toDataIf() {
			return this;
		}

		public xbean.RoleMap toBeanIf() {
			return new RoleMap(this, null, null);
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
		public java.util.Map<Integer, Integer> getPkstates() { // 在各大地图中的pk状态 worldid -> cfg.fight.PKState
			return pkstates;
		}

		@Override
		public java.util.Map<Integer, Integer> getPkstatesAsData() { // 在各大地图中的pk状态 worldid -> cfg.fight.PKState
			return pkstates;
		}

		@Override
		public boolean getFinishprologue() { // 
			return finishprologue;
		}

		@Override
		public int getHp() { // 
			return hp;
		}

		@Override
		public int getMp() { // 
			return mp;
		}

		@Override
		public java.util.List<xbean.RoleMapInfo> getPrevs() { // 
			return prevs;
		}

		@Override
		public java.util.List<xbean.RoleMapInfo> getPrevsAsData() { // 
			return prevs;
		}

		@Override
		public boolean getIsnew() { // 
			return isnew;
		}

		@Override
		public void setFinishprologue(boolean _v_) { // 
			finishprologue = _v_;
		}

		@Override
		public void setHp(int _v_) { // 
			hp = _v_;
		}

		@Override
		public void setMp(int _v_) { // 
			mp = _v_;
		}

		@Override
		public void setIsnew(boolean _v_) { // 
			isnew = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleMap.Data)) return false;
			RoleMap.Data _o_ = (RoleMap.Data) _o1_;
			if (!pkstates.equals(_o_.pkstates)) return false;
			if (finishprologue != _o_.finishprologue) return false;
			if (hp != _o_.hp) return false;
			if (mp != _o_.mp) return false;
			if (!prevs.equals(_o_.prevs)) return false;
			if (isnew != _o_.isnew) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += pkstates.hashCode();
			_h_ += finishprologue ? 1231 : 1237;
			_h_ += hp;
			_h_ += mp;
			_h_ += prevs.hashCode();
			_h_ += isnew ? 1231 : 1237;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(pkstates);
			_sb_.append(",");
			_sb_.append(finishprologue);
			_sb_.append(",");
			_sb_.append(hp);
			_sb_.append(",");
			_sb_.append(mp);
			_sb_.append(",");
			_sb_.append(prevs);
			_sb_.append(",");
			_sb_.append(isnew);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
