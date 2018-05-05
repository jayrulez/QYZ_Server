
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class HuiWuBattle extends xdb.XBean implements xbean.HuiWuBattle {
	private int index; // 
	private long roleid1; // 
	private long roleid2; // 
	private int state; // cfg.huiwu.BattleState
	private long mapid; // 

	@Override
	public void _reset_unsafe_() {
		index = 0;
		roleid1 = 0L;
		roleid2 = 0L;
		state = 0;
		mapid = 0L;
	}

	HuiWuBattle(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public HuiWuBattle() {
		this(0, null, null);
	}

	public HuiWuBattle(HuiWuBattle _o_) {
		this(_o_, null, null);
	}

	HuiWuBattle(xbean.HuiWuBattle _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof HuiWuBattle) assign((HuiWuBattle)_o1_);
		else if (_o1_ instanceof HuiWuBattle.Data) assign((HuiWuBattle.Data)_o1_);
		else if (_o1_ instanceof HuiWuBattle.Const) assign(((HuiWuBattle.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(HuiWuBattle _o_) {
		_o_._xdb_verify_unsafe_();
		index = _o_.index;
		roleid1 = _o_.roleid1;
		roleid2 = _o_.roleid2;
		state = _o_.state;
		mapid = _o_.mapid;
	}

	private void assign(HuiWuBattle.Data _o_) {
		index = _o_.index;
		roleid1 = _o_.roleid1;
		roleid2 = _o_.roleid2;
		state = _o_.state;
		mapid = _o_.mapid;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)5);
    _os_.marshal((short)( 8192|  5));_os_.marshal(index);
    _os_.marshal((short)(10240|  1));_os_.marshal(roleid1);
    _os_.marshal((short)(10240|  2));_os_.marshal(roleid2);
    _os_.marshal((short)( 8192|  3));_os_.marshal(state);
    _os_.marshal((short)(10240|  4));_os_.marshal(mapid);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  5):index = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):index = _os_.unmarshal_short();
    				break;
    				case (10240|  1):roleid1 = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):roleid1 = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):roleid1 = _os_.unmarshal_int();
    				break;
    				case (10240|  2):roleid2 = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):roleid2 = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):roleid2 = _os_.unmarshal_int();
    				break;
    				case ( 8192|  3):state = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):state = _os_.unmarshal_short();
    				break;
    				case (10240|  4):mapid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):mapid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):mapid = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.HuiWuBattle copy() {
		_xdb_verify_unsafe_();
		return new HuiWuBattle(this);
	}

	@Override
	public xbean.HuiWuBattle toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuBattle toBean() {
		_xdb_verify_unsafe_();
		return new HuiWuBattle(this); // same as copy()
	}

	@Override
	public xbean.HuiWuBattle toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuBattle toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getIndex() { // 
		_xdb_verify_unsafe_();
		return index;
	}

	@Override
	public long getRoleid1() { // 
		_xdb_verify_unsafe_();
		return roleid1;
	}

	@Override
	public long getRoleid2() { // 
		_xdb_verify_unsafe_();
		return roleid2;
	}

	@Override
	public int getState() { // cfg.huiwu.BattleState
		_xdb_verify_unsafe_();
		return state;
	}

	@Override
	public long getMapid() { // 
		_xdb_verify_unsafe_();
		return mapid;
	}

	@Override
	public void setIndex(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "index") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, index) {
					public void rollback() { index = _xdb_saved; }
				};}});
		index = _v_;
	}

	@Override
	public void setRoleid1(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleid1") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleid1) {
					public void rollback() { roleid1 = _xdb_saved; }
				};}});
		roleid1 = _v_;
	}

	@Override
	public void setRoleid2(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleid2") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleid2) {
					public void rollback() { roleid2 = _xdb_saved; }
				};}});
		roleid2 = _v_;
	}

	@Override
	public void setState(int _v_) { // cfg.huiwu.BattleState
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "state") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, state) {
					public void rollback() { state = _xdb_saved; }
				};}});
		state = _v_;
	}

	@Override
	public void setMapid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "mapid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, mapid) {
					public void rollback() { mapid = _xdb_saved; }
				};}});
		mapid = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		HuiWuBattle _o_ = null;
		if ( _o1_ instanceof HuiWuBattle ) _o_ = (HuiWuBattle)_o1_;
		else if ( _o1_ instanceof HuiWuBattle.Const ) _o_ = ((HuiWuBattle.Const)_o1_).nThis();
		else return false;
		if (index != _o_.index) return false;
		if (roleid1 != _o_.roleid1) return false;
		if (roleid2 != _o_.roleid2) return false;
		if (state != _o_.state) return false;
		if (mapid != _o_.mapid) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += index;
		_h_ += roleid1;
		_h_ += roleid2;
		_h_ += state;
		_h_ += mapid;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(index);
		_sb_.append(",");
		_sb_.append(roleid1);
		_sb_.append(",");
		_sb_.append(roleid2);
		_sb_.append(",");
		_sb_.append(state);
		_sb_.append(",");
		_sb_.append(mapid);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("index"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid1"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid2"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("state"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("mapid"));
		return lb;
	}

	private class Const implements xbean.HuiWuBattle {
		HuiWuBattle nThis() {
			return HuiWuBattle.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.HuiWuBattle copy() {
			return HuiWuBattle.this.copy();
		}

		@Override
		public xbean.HuiWuBattle toData() {
			return HuiWuBattle.this.toData();
		}

		public xbean.HuiWuBattle toBean() {
			return HuiWuBattle.this.toBean();
		}

		@Override
		public xbean.HuiWuBattle toDataIf() {
			return HuiWuBattle.this.toDataIf();
		}

		public xbean.HuiWuBattle toBeanIf() {
			return HuiWuBattle.this.toBeanIf();
		}

		@Override
		public int getIndex() { // 
			_xdb_verify_unsafe_();
			return index;
		}

		@Override
		public long getRoleid1() { // 
			_xdb_verify_unsafe_();
			return roleid1;
		}

		@Override
		public long getRoleid2() { // 
			_xdb_verify_unsafe_();
			return roleid2;
		}

		@Override
		public int getState() { // cfg.huiwu.BattleState
			_xdb_verify_unsafe_();
			return state;
		}

		@Override
		public long getMapid() { // 
			_xdb_verify_unsafe_();
			return mapid;
		}

		@Override
		public void setIndex(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRoleid1(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRoleid2(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setState(int _v_) { // cfg.huiwu.BattleState
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMapid(long _v_) { // 
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
			return HuiWuBattle.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return HuiWuBattle.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return HuiWuBattle.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return HuiWuBattle.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return HuiWuBattle.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return HuiWuBattle.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return HuiWuBattle.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return HuiWuBattle.this.hashCode();
		}

		@Override
		public String toString() {
			return HuiWuBattle.this.toString();
		}

	}

	public static final class Data implements xbean.HuiWuBattle {
		private int index; // 
		private long roleid1; // 
		private long roleid2; // 
		private int state; // cfg.huiwu.BattleState
		private long mapid; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.HuiWuBattle _o1_) {
			if (_o1_ instanceof HuiWuBattle) assign((HuiWuBattle)_o1_);
			else if (_o1_ instanceof HuiWuBattle.Data) assign((HuiWuBattle.Data)_o1_);
			else if (_o1_ instanceof HuiWuBattle.Const) assign(((HuiWuBattle.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(HuiWuBattle _o_) {
			index = _o_.index;
			roleid1 = _o_.roleid1;
			roleid2 = _o_.roleid2;
			state = _o_.state;
			mapid = _o_.mapid;
		}

		private void assign(HuiWuBattle.Data _o_) {
			index = _o_.index;
			roleid1 = _o_.roleid1;
			roleid2 = _o_.roleid2;
			state = _o_.state;
			mapid = _o_.mapid;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)5);
	_os_.marshal((short)( 8192|  5));_os_.marshal(index);
	_os_.marshal((short)(10240|  1));_os_.marshal(roleid1);
	_os_.marshal((short)(10240|  2));_os_.marshal(roleid2);
	_os_.marshal((short)( 8192|  3));_os_.marshal(state);
	_os_.marshal((short)(10240|  4));_os_.marshal(mapid);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  5):index = _os_.unmarshal_int();
					break;
					case ( 6144|  5):index = _os_.unmarshal_short();
					break;
					case (10240|  1):roleid1 = _os_.unmarshal_long();
					break;
					case ( 6144|  1):roleid1 = _os_.unmarshal_short();
					break;
					case ( 8192|  1):roleid1 = _os_.unmarshal_int();
					break;
					case (10240|  2):roleid2 = _os_.unmarshal_long();
					break;
					case ( 6144|  2):roleid2 = _os_.unmarshal_short();
					break;
					case ( 8192|  2):roleid2 = _os_.unmarshal_int();
					break;
					case ( 8192|  3):state = _os_.unmarshal_int();
					break;
					case ( 6144|  3):state = _os_.unmarshal_short();
					break;
					case (10240|  4):mapid = _os_.unmarshal_long();
					break;
					case ( 6144|  4):mapid = _os_.unmarshal_short();
					break;
					case ( 8192|  4):mapid = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.HuiWuBattle copy() {
			return new Data(this);
		}

		@Override
		public xbean.HuiWuBattle toData() {
			return new Data(this);
		}

		public xbean.HuiWuBattle toBean() {
			return new HuiWuBattle(this, null, null);
		}

		@Override
		public xbean.HuiWuBattle toDataIf() {
			return this;
		}

		public xbean.HuiWuBattle toBeanIf() {
			return new HuiWuBattle(this, null, null);
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
		public int getIndex() { // 
			return index;
		}

		@Override
		public long getRoleid1() { // 
			return roleid1;
		}

		@Override
		public long getRoleid2() { // 
			return roleid2;
		}

		@Override
		public int getState() { // cfg.huiwu.BattleState
			return state;
		}

		@Override
		public long getMapid() { // 
			return mapid;
		}

		@Override
		public void setIndex(int _v_) { // 
			index = _v_;
		}

		@Override
		public void setRoleid1(long _v_) { // 
			roleid1 = _v_;
		}

		@Override
		public void setRoleid2(long _v_) { // 
			roleid2 = _v_;
		}

		@Override
		public void setState(int _v_) { // cfg.huiwu.BattleState
			state = _v_;
		}

		@Override
		public void setMapid(long _v_) { // 
			mapid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof HuiWuBattle.Data)) return false;
			HuiWuBattle.Data _o_ = (HuiWuBattle.Data) _o1_;
			if (index != _o_.index) return false;
			if (roleid1 != _o_.roleid1) return false;
			if (roleid2 != _o_.roleid2) return false;
			if (state != _o_.state) return false;
			if (mapid != _o_.mapid) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += index;
			_h_ += roleid1;
			_h_ += roleid2;
			_h_ += state;
			_h_ += mapid;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(index);
			_sb_.append(",");
			_sb_.append(roleid1);
			_sb_.append(",");
			_sb_.append(roleid2);
			_sb_.append(",");
			_sb_.append(state);
			_sb_.append(",");
			_sb_.append(mapid);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
