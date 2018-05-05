
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleFriend extends xdb.XBean implements xbean.RoleFriend {
	private int frienddegress; // 我与好友的友好度 互送花决定
	private int relation; // 我与好友的脉脉关系
	private long time; // 我与好友的脉脉关系

	@Override
	public void _reset_unsafe_() {
		frienddegress = 0;
		relation = 0;
		time = 0L;
	}

	RoleFriend(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public RoleFriend() {
		this(0, null, null);
	}

	public RoleFriend(RoleFriend _o_) {
		this(_o_, null, null);
	}

	RoleFriend(xbean.RoleFriend _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleFriend) assign((RoleFriend)_o1_);
		else if (_o1_ instanceof RoleFriend.Data) assign((RoleFriend.Data)_o1_);
		else if (_o1_ instanceof RoleFriend.Const) assign(((RoleFriend.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleFriend _o_) {
		_o_._xdb_verify_unsafe_();
		frienddegress = _o_.frienddegress;
		relation = _o_.relation;
		time = _o_.time;
	}

	private void assign(RoleFriend.Data _o_) {
		frienddegress = _o_.frienddegress;
		relation = _o_.relation;
		time = _o_.time;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  1));_os_.marshal(frienddegress);
    _os_.marshal((short)( 8192|  2));_os_.marshal(relation);
    _os_.marshal((short)(10240|  3));_os_.marshal(time);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):frienddegress = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):frienddegress = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):relation = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):relation = _os_.unmarshal_short();
    				break;
    				case (10240|  3):time = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):time = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):time = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleFriend copy() {
		_xdb_verify_unsafe_();
		return new RoleFriend(this);
	}

	@Override
	public xbean.RoleFriend toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleFriend toBean() {
		_xdb_verify_unsafe_();
		return new RoleFriend(this); // same as copy()
	}

	@Override
	public xbean.RoleFriend toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleFriend toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getFrienddegress() { // 我与好友的友好度 互送花决定
		_xdb_verify_unsafe_();
		return frienddegress;
	}

	@Override
	public int getRelation() { // 我与好友的脉脉关系
		_xdb_verify_unsafe_();
		return relation;
	}

	@Override
	public long getTime() { // 我与好友的脉脉关系
		_xdb_verify_unsafe_();
		return time;
	}

	@Override
	public void setFrienddegress(int _v_) { // 我与好友的友好度 互送花决定
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "frienddegress") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, frienddegress) {
					public void rollback() { frienddegress = _xdb_saved; }
				};}});
		frienddegress = _v_;
	}

	@Override
	public void setRelation(int _v_) { // 我与好友的脉脉关系
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "relation") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, relation) {
					public void rollback() { relation = _xdb_saved; }
				};}});
		relation = _v_;
	}

	@Override
	public void setTime(long _v_) { // 我与好友的脉脉关系
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "time") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, time) {
					public void rollback() { time = _xdb_saved; }
				};}});
		time = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleFriend _o_ = null;
		if ( _o1_ instanceof RoleFriend ) _o_ = (RoleFriend)_o1_;
		else if ( _o1_ instanceof RoleFriend.Const ) _o_ = ((RoleFriend.Const)_o1_).nThis();
		else return false;
		if (frienddegress != _o_.frienddegress) return false;
		if (relation != _o_.relation) return false;
		if (time != _o_.time) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += frienddegress;
		_h_ += relation;
		_h_ += time;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(frienddegress);
		_sb_.append(",");
		_sb_.append(relation);
		_sb_.append(",");
		_sb_.append(time);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("frienddegress"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("relation"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("time"));
		return lb;
	}

	private class Const implements xbean.RoleFriend {
		RoleFriend nThis() {
			return RoleFriend.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleFriend copy() {
			return RoleFriend.this.copy();
		}

		@Override
		public xbean.RoleFriend toData() {
			return RoleFriend.this.toData();
		}

		public xbean.RoleFriend toBean() {
			return RoleFriend.this.toBean();
		}

		@Override
		public xbean.RoleFriend toDataIf() {
			return RoleFriend.this.toDataIf();
		}

		public xbean.RoleFriend toBeanIf() {
			return RoleFriend.this.toBeanIf();
		}

		@Override
		public int getFrienddegress() { // 我与好友的友好度 互送花决定
			_xdb_verify_unsafe_();
			return frienddegress;
		}

		@Override
		public int getRelation() { // 我与好友的脉脉关系
			_xdb_verify_unsafe_();
			return relation;
		}

		@Override
		public long getTime() { // 我与好友的脉脉关系
			_xdb_verify_unsafe_();
			return time;
		}

		@Override
		public void setFrienddegress(int _v_) { // 我与好友的友好度 互送花决定
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRelation(int _v_) { // 我与好友的脉脉关系
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTime(long _v_) { // 我与好友的脉脉关系
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
			return RoleFriend.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleFriend.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleFriend.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleFriend.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleFriend.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleFriend.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleFriend.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleFriend.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleFriend.this.toString();
		}

	}

	public static final class Data implements xbean.RoleFriend {
		private int frienddegress; // 我与好友的友好度 互送花决定
		private int relation; // 我与好友的脉脉关系
		private long time; // 我与好友的脉脉关系

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.RoleFriend _o1_) {
			if (_o1_ instanceof RoleFriend) assign((RoleFriend)_o1_);
			else if (_o1_ instanceof RoleFriend.Data) assign((RoleFriend.Data)_o1_);
			else if (_o1_ instanceof RoleFriend.Const) assign(((RoleFriend.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleFriend _o_) {
			frienddegress = _o_.frienddegress;
			relation = _o_.relation;
			time = _o_.time;
		}

		private void assign(RoleFriend.Data _o_) {
			frienddegress = _o_.frienddegress;
			relation = _o_.relation;
			time = _o_.time;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  1));_os_.marshal(frienddegress);
	_os_.marshal((short)( 8192|  2));_os_.marshal(relation);
	_os_.marshal((short)(10240|  3));_os_.marshal(time);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):frienddegress = _os_.unmarshal_int();
					break;
					case ( 6144|  1):frienddegress = _os_.unmarshal_short();
					break;
					case ( 8192|  2):relation = _os_.unmarshal_int();
					break;
					case ( 6144|  2):relation = _os_.unmarshal_short();
					break;
					case (10240|  3):time = _os_.unmarshal_long();
					break;
					case ( 6144|  3):time = _os_.unmarshal_short();
					break;
					case ( 8192|  3):time = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleFriend copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleFriend toData() {
			return new Data(this);
		}

		public xbean.RoleFriend toBean() {
			return new RoleFriend(this, null, null);
		}

		@Override
		public xbean.RoleFriend toDataIf() {
			return this;
		}

		public xbean.RoleFriend toBeanIf() {
			return new RoleFriend(this, null, null);
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
		public int getFrienddegress() { // 我与好友的友好度 互送花决定
			return frienddegress;
		}

		@Override
		public int getRelation() { // 我与好友的脉脉关系
			return relation;
		}

		@Override
		public long getTime() { // 我与好友的脉脉关系
			return time;
		}

		@Override
		public void setFrienddegress(int _v_) { // 我与好友的友好度 互送花决定
			frienddegress = _v_;
		}

		@Override
		public void setRelation(int _v_) { // 我与好友的脉脉关系
			relation = _v_;
		}

		@Override
		public void setTime(long _v_) { // 我与好友的脉脉关系
			time = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleFriend.Data)) return false;
			RoleFriend.Data _o_ = (RoleFriend.Data) _o1_;
			if (frienddegress != _o_.frienddegress) return false;
			if (relation != _o_.relation) return false;
			if (time != _o_.time) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += frienddegress;
			_h_ += relation;
			_h_ += time;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(frienddegress);
			_sb_.append(",");
			_sb_.append(relation);
			_sb_.append(",");
			_sb_.append(time);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
