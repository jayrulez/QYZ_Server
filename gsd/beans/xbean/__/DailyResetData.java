
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class DailyResetData extends xdb.XBean implements xbean.DailyResetData {
	private long lastupdatedailytime; // 
	private xbean.DailyArena arena; // 
	private xbean.DailyMonsterExp monsterexp; // 
	private int killexpmonsters; // 击杀经验怪的数量
	private java.util.LinkedList<Integer> recexpmonbonus; // 已经领取的击杀经验怪奖励

	@Override
	public void _reset_unsafe_() {
		lastupdatedailytime = 0L;
		arena._reset_unsafe_();
		monsterexp._reset_unsafe_();
		killexpmonsters = 0;
		recexpmonbonus.clear();
	}

	DailyResetData(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		arena = new DailyArena(0, this, "arena");
		monsterexp = new DailyMonsterExp(0, this, "monsterexp");
		recexpmonbonus = new java.util.LinkedList<Integer>();
	}

	public DailyResetData() {
		this(0, null, null);
	}

	public DailyResetData(DailyResetData _o_) {
		this(_o_, null, null);
	}

	DailyResetData(xbean.DailyResetData _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof DailyResetData) assign((DailyResetData)_o1_);
		else if (_o1_ instanceof DailyResetData.Data) assign((DailyResetData.Data)_o1_);
		else if (_o1_ instanceof DailyResetData.Const) assign(((DailyResetData.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(DailyResetData _o_) {
		_o_._xdb_verify_unsafe_();
		lastupdatedailytime = _o_.lastupdatedailytime;
		arena = new DailyArena(_o_.arena, this, "arena");
		monsterexp = new DailyMonsterExp(_o_.monsterexp, this, "monsterexp");
		killexpmonsters = _o_.killexpmonsters;
		recexpmonbonus = new java.util.LinkedList<Integer>();
		recexpmonbonus.addAll(_o_.recexpmonbonus);
	}

	private void assign(DailyResetData.Data _o_) {
		lastupdatedailytime = _o_.lastupdatedailytime;
		arena = new DailyArena(_o_.arena, this, "arena");
		monsterexp = new DailyMonsterExp(_o_.monsterexp, this, "monsterexp");
		killexpmonsters = _o_.killexpmonsters;
		recexpmonbonus = new java.util.LinkedList<Integer>();
		recexpmonbonus.addAll(_o_.recexpmonbonus);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)5);
    _os_.marshal((short)(10240|  1));_os_.marshal(lastupdatedailytime);
    _os_.marshal((short)(26624|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
arena.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(26624|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
monsterexp.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  4));_os_.marshal(killexpmonsters);
    _os_.marshal((short)(22528|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(recexpmonbonus.size());
for (Integer _v_ : recexpmonbonus) {
	_os_.marshal(_v_);
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
    				case (10240|  1):lastupdatedailytime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):lastupdatedailytime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):lastupdatedailytime = _os_.unmarshal_int();
    				break;
    				case (26624|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
arena.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case (26624|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
monsterexp.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case ( 8192|  4):killexpmonsters = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):killexpmonsters = _os_.unmarshal_short();
    				break;
    				case (22528|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	recexpmonbonus.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.DailyResetData copy() {
		_xdb_verify_unsafe_();
		return new DailyResetData(this);
	}

	@Override
	public xbean.DailyResetData toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyResetData toBean() {
		_xdb_verify_unsafe_();
		return new DailyResetData(this); // same as copy()
	}

	@Override
	public xbean.DailyResetData toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyResetData toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getLastupdatedailytime() { // 
		_xdb_verify_unsafe_();
		return lastupdatedailytime;
	}

	@Override
	public xbean.DailyArena getArena() { // 
		_xdb_verify_unsafe_();
		return arena;
	}

	@Override
	public xbean.DailyMonsterExp getMonsterexp() { // 
		_xdb_verify_unsafe_();
		return monsterexp;
	}

	@Override
	public int getKillexpmonsters() { // 击杀经验怪的数量
		_xdb_verify_unsafe_();
		return killexpmonsters;
	}

	@Override
	public java.util.List<Integer> getRecexpmonbonus() { // 已经领取的击杀经验怪奖励
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "recexpmonbonus"), recexpmonbonus);
	}

	public java.util.List<Integer> getRecexpmonbonusAsData() { // 已经领取的击杀经验怪奖励
		_xdb_verify_unsafe_();
		java.util.List<Integer> recexpmonbonus;
		DailyResetData _o_ = this;
		recexpmonbonus = new java.util.LinkedList<Integer>();
		recexpmonbonus.addAll(_o_.recexpmonbonus);
		return recexpmonbonus;
	}

	@Override
	public void setLastupdatedailytime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastupdatedailytime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastupdatedailytime) {
					public void rollback() { lastupdatedailytime = _xdb_saved; }
				};}});
		lastupdatedailytime = _v_;
	}

	@Override
	public void setKillexpmonsters(int _v_) { // 击杀经验怪的数量
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "killexpmonsters") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, killexpmonsters) {
					public void rollback() { killexpmonsters = _xdb_saved; }
				};}});
		killexpmonsters = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		DailyResetData _o_ = null;
		if ( _o1_ instanceof DailyResetData ) _o_ = (DailyResetData)_o1_;
		else if ( _o1_ instanceof DailyResetData.Const ) _o_ = ((DailyResetData.Const)_o1_).nThis();
		else return false;
		if (lastupdatedailytime != _o_.lastupdatedailytime) return false;
		if (!arena.equals(_o_.arena)) return false;
		if (!monsterexp.equals(_o_.monsterexp)) return false;
		if (killexpmonsters != _o_.killexpmonsters) return false;
		if (!recexpmonbonus.equals(_o_.recexpmonbonus)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += lastupdatedailytime;
		_h_ += arena.hashCode();
		_h_ += monsterexp.hashCode();
		_h_ += killexpmonsters;
		_h_ += recexpmonbonus.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lastupdatedailytime);
		_sb_.append(",");
		_sb_.append(arena);
		_sb_.append(",");
		_sb_.append(monsterexp);
		_sb_.append(",");
		_sb_.append(killexpmonsters);
		_sb_.append(",");
		_sb_.append(recexpmonbonus);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastupdatedailytime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("arena"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("monsterexp"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("killexpmonsters"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("recexpmonbonus"));
		return lb;
	}

	private class Const implements xbean.DailyResetData {
		DailyResetData nThis() {
			return DailyResetData.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.DailyResetData copy() {
			return DailyResetData.this.copy();
		}

		@Override
		public xbean.DailyResetData toData() {
			return DailyResetData.this.toData();
		}

		public xbean.DailyResetData toBean() {
			return DailyResetData.this.toBean();
		}

		@Override
		public xbean.DailyResetData toDataIf() {
			return DailyResetData.this.toDataIf();
		}

		public xbean.DailyResetData toBeanIf() {
			return DailyResetData.this.toBeanIf();
		}

		@Override
		public long getLastupdatedailytime() { // 
			_xdb_verify_unsafe_();
			return lastupdatedailytime;
		}

		@Override
		public xbean.DailyArena getArena() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(arena);
		}

		@Override
		public xbean.DailyMonsterExp getMonsterexp() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(monsterexp);
		}

		@Override
		public int getKillexpmonsters() { // 击杀经验怪的数量
			_xdb_verify_unsafe_();
			return killexpmonsters;
		}

		@Override
		public java.util.List<Integer> getRecexpmonbonus() { // 已经领取的击杀经验怪奖励
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(recexpmonbonus);
		}

		public java.util.List<Integer> getRecexpmonbonusAsData() { // 已经领取的击杀经验怪奖励
			_xdb_verify_unsafe_();
			java.util.List<Integer> recexpmonbonus;
			DailyResetData _o_ = DailyResetData.this;
		recexpmonbonus = new java.util.LinkedList<Integer>();
		recexpmonbonus.addAll(_o_.recexpmonbonus);
			return recexpmonbonus;
		}

		@Override
		public void setLastupdatedailytime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setKillexpmonsters(int _v_) { // 击杀经验怪的数量
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
			return DailyResetData.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return DailyResetData.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return DailyResetData.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return DailyResetData.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return DailyResetData.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return DailyResetData.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return DailyResetData.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return DailyResetData.this.hashCode();
		}

		@Override
		public String toString() {
			return DailyResetData.this.toString();
		}

	}

	public static final class Data implements xbean.DailyResetData {
		private long lastupdatedailytime; // 
		private xbean.DailyArena arena; // 
		private xbean.DailyMonsterExp monsterexp; // 
		private int killexpmonsters; // 击杀经验怪的数量
		private java.util.LinkedList<Integer> recexpmonbonus; // 已经领取的击杀经验怪奖励

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			arena = new DailyArena.Data();
			monsterexp = new DailyMonsterExp.Data();
			recexpmonbonus = new java.util.LinkedList<Integer>();
		}

		Data(xbean.DailyResetData _o1_) {
			if (_o1_ instanceof DailyResetData) assign((DailyResetData)_o1_);
			else if (_o1_ instanceof DailyResetData.Data) assign((DailyResetData.Data)_o1_);
			else if (_o1_ instanceof DailyResetData.Const) assign(((DailyResetData.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(DailyResetData _o_) {
			lastupdatedailytime = _o_.lastupdatedailytime;
			arena = new DailyArena.Data(_o_.arena);
			monsterexp = new DailyMonsterExp.Data(_o_.monsterexp);
			killexpmonsters = _o_.killexpmonsters;
			recexpmonbonus = new java.util.LinkedList<Integer>();
			recexpmonbonus.addAll(_o_.recexpmonbonus);
		}

		private void assign(DailyResetData.Data _o_) {
			lastupdatedailytime = _o_.lastupdatedailytime;
			arena = new DailyArena.Data(_o_.arena);
			monsterexp = new DailyMonsterExp.Data(_o_.monsterexp);
			killexpmonsters = _o_.killexpmonsters;
			recexpmonbonus = new java.util.LinkedList<Integer>();
			recexpmonbonus.addAll(_o_.recexpmonbonus);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)5);
	_os_.marshal((short)(10240|  1));_os_.marshal(lastupdatedailytime);
	_os_.marshal((short)(26624|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
arena.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(26624|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
monsterexp.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  4));_os_.marshal(killexpmonsters);
	_os_.marshal((short)(22528|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(recexpmonbonus.size());
for (Integer _v_ : recexpmonbonus) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):lastupdatedailytime = _os_.unmarshal_long();
					break;
					case ( 6144|  1):lastupdatedailytime = _os_.unmarshal_short();
					break;
					case ( 8192|  1):lastupdatedailytime = _os_.unmarshal_int();
					break;
					case (26624|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
arena.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case (26624|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
monsterexp.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case ( 8192|  4):killexpmonsters = _os_.unmarshal_int();
					break;
					case ( 6144|  4):killexpmonsters = _os_.unmarshal_short();
					break;
					case (22528|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	recexpmonbonus.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.DailyResetData copy() {
			return new Data(this);
		}

		@Override
		public xbean.DailyResetData toData() {
			return new Data(this);
		}

		public xbean.DailyResetData toBean() {
			return new DailyResetData(this, null, null);
		}

		@Override
		public xbean.DailyResetData toDataIf() {
			return this;
		}

		public xbean.DailyResetData toBeanIf() {
			return new DailyResetData(this, null, null);
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
		public long getLastupdatedailytime() { // 
			return lastupdatedailytime;
		}

		@Override
		public xbean.DailyArena getArena() { // 
			return arena;
		}

		@Override
		public xbean.DailyMonsterExp getMonsterexp() { // 
			return monsterexp;
		}

		@Override
		public int getKillexpmonsters() { // 击杀经验怪的数量
			return killexpmonsters;
		}

		@Override
		public java.util.List<Integer> getRecexpmonbonus() { // 已经领取的击杀经验怪奖励
			return recexpmonbonus;
		}

		@Override
		public java.util.List<Integer> getRecexpmonbonusAsData() { // 已经领取的击杀经验怪奖励
			return recexpmonbonus;
		}

		@Override
		public void setLastupdatedailytime(long _v_) { // 
			lastupdatedailytime = _v_;
		}

		@Override
		public void setKillexpmonsters(int _v_) { // 击杀经验怪的数量
			killexpmonsters = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof DailyResetData.Data)) return false;
			DailyResetData.Data _o_ = (DailyResetData.Data) _o1_;
			if (lastupdatedailytime != _o_.lastupdatedailytime) return false;
			if (!arena.equals(_o_.arena)) return false;
			if (!monsterexp.equals(_o_.monsterexp)) return false;
			if (killexpmonsters != _o_.killexpmonsters) return false;
			if (!recexpmonbonus.equals(_o_.recexpmonbonus)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += lastupdatedailytime;
			_h_ += arena.hashCode();
			_h_ += monsterexp.hashCode();
			_h_ += killexpmonsters;
			_h_ += recexpmonbonus.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(lastupdatedailytime);
			_sb_.append(",");
			_sb_.append(arena);
			_sb_.append(",");
			_sb_.append(monsterexp);
			_sb_.append(",");
			_sb_.append(killexpmonsters);
			_sb_.append(",");
			_sb_.append(recexpmonbonus);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
