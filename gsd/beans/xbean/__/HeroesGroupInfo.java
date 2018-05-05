
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class HeroesGroupInfo extends xdb.XBean implements xbean.HeroesGroupInfo {
	private int refreshtime; // 已经进行过的刷新次数
	private int ectypeid; // 上次随机到的副本id，为0或空则取默认值

	@Override
	public void _reset_unsafe_() {
		refreshtime = 0;
		ectypeid = 0;
	}

	HeroesGroupInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public HeroesGroupInfo() {
		this(0, null, null);
	}

	public HeroesGroupInfo(HeroesGroupInfo _o_) {
		this(_o_, null, null);
	}

	HeroesGroupInfo(xbean.HeroesGroupInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof HeroesGroupInfo) assign((HeroesGroupInfo)_o1_);
		else if (_o1_ instanceof HeroesGroupInfo.Data) assign((HeroesGroupInfo.Data)_o1_);
		else if (_o1_ instanceof HeroesGroupInfo.Const) assign(((HeroesGroupInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(HeroesGroupInfo _o_) {
		_o_._xdb_verify_unsafe_();
		refreshtime = _o_.refreshtime;
		ectypeid = _o_.ectypeid;
	}

	private void assign(HeroesGroupInfo.Data _o_) {
		refreshtime = _o_.refreshtime;
		ectypeid = _o_.ectypeid;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(refreshtime);
    _os_.marshal((short)( 8192|  2));_os_.marshal(ectypeid);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):refreshtime = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):refreshtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):ectypeid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):ectypeid = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.HeroesGroupInfo copy() {
		_xdb_verify_unsafe_();
		return new HeroesGroupInfo(this);
	}

	@Override
	public xbean.HeroesGroupInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HeroesGroupInfo toBean() {
		_xdb_verify_unsafe_();
		return new HeroesGroupInfo(this); // same as copy()
	}

	@Override
	public xbean.HeroesGroupInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HeroesGroupInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getRefreshtime() { // 已经进行过的刷新次数
		_xdb_verify_unsafe_();
		return refreshtime;
	}

	@Override
	public int getEctypeid() { // 上次随机到的副本id，为0或空则取默认值
		_xdb_verify_unsafe_();
		return ectypeid;
	}

	@Override
	public void setRefreshtime(int _v_) { // 已经进行过的刷新次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "refreshtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, refreshtime) {
					public void rollback() { refreshtime = _xdb_saved; }
				};}});
		refreshtime = _v_;
	}

	@Override
	public void setEctypeid(int _v_) { // 上次随机到的副本id，为0或空则取默认值
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "ectypeid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, ectypeid) {
					public void rollback() { ectypeid = _xdb_saved; }
				};}});
		ectypeid = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		HeroesGroupInfo _o_ = null;
		if ( _o1_ instanceof HeroesGroupInfo ) _o_ = (HeroesGroupInfo)_o1_;
		else if ( _o1_ instanceof HeroesGroupInfo.Const ) _o_ = ((HeroesGroupInfo.Const)_o1_).nThis();
		else return false;
		if (refreshtime != _o_.refreshtime) return false;
		if (ectypeid != _o_.ectypeid) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += refreshtime;
		_h_ += ectypeid;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(refreshtime);
		_sb_.append(",");
		_sb_.append(ectypeid);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("refreshtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("ectypeid"));
		return lb;
	}

	private class Const implements xbean.HeroesGroupInfo {
		HeroesGroupInfo nThis() {
			return HeroesGroupInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.HeroesGroupInfo copy() {
			return HeroesGroupInfo.this.copy();
		}

		@Override
		public xbean.HeroesGroupInfo toData() {
			return HeroesGroupInfo.this.toData();
		}

		public xbean.HeroesGroupInfo toBean() {
			return HeroesGroupInfo.this.toBean();
		}

		@Override
		public xbean.HeroesGroupInfo toDataIf() {
			return HeroesGroupInfo.this.toDataIf();
		}

		public xbean.HeroesGroupInfo toBeanIf() {
			return HeroesGroupInfo.this.toBeanIf();
		}

		@Override
		public int getRefreshtime() { // 已经进行过的刷新次数
			_xdb_verify_unsafe_();
			return refreshtime;
		}

		@Override
		public int getEctypeid() { // 上次随机到的副本id，为0或空则取默认值
			_xdb_verify_unsafe_();
			return ectypeid;
		}

		@Override
		public void setRefreshtime(int _v_) { // 已经进行过的刷新次数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setEctypeid(int _v_) { // 上次随机到的副本id，为0或空则取默认值
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
			return HeroesGroupInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return HeroesGroupInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return HeroesGroupInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return HeroesGroupInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return HeroesGroupInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return HeroesGroupInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return HeroesGroupInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return HeroesGroupInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return HeroesGroupInfo.this.toString();
		}

	}

	public static final class Data implements xbean.HeroesGroupInfo {
		private int refreshtime; // 已经进行过的刷新次数
		private int ectypeid; // 上次随机到的副本id，为0或空则取默认值

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.HeroesGroupInfo _o1_) {
			if (_o1_ instanceof HeroesGroupInfo) assign((HeroesGroupInfo)_o1_);
			else if (_o1_ instanceof HeroesGroupInfo.Data) assign((HeroesGroupInfo.Data)_o1_);
			else if (_o1_ instanceof HeroesGroupInfo.Const) assign(((HeroesGroupInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(HeroesGroupInfo _o_) {
			refreshtime = _o_.refreshtime;
			ectypeid = _o_.ectypeid;
		}

		private void assign(HeroesGroupInfo.Data _o_) {
			refreshtime = _o_.refreshtime;
			ectypeid = _o_.ectypeid;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(refreshtime);
	_os_.marshal((short)( 8192|  2));_os_.marshal(ectypeid);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):refreshtime = _os_.unmarshal_int();
					break;
					case ( 6144|  1):refreshtime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):ectypeid = _os_.unmarshal_int();
					break;
					case ( 6144|  2):ectypeid = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.HeroesGroupInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.HeroesGroupInfo toData() {
			return new Data(this);
		}

		public xbean.HeroesGroupInfo toBean() {
			return new HeroesGroupInfo(this, null, null);
		}

		@Override
		public xbean.HeroesGroupInfo toDataIf() {
			return this;
		}

		public xbean.HeroesGroupInfo toBeanIf() {
			return new HeroesGroupInfo(this, null, null);
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
		public int getRefreshtime() { // 已经进行过的刷新次数
			return refreshtime;
		}

		@Override
		public int getEctypeid() { // 上次随机到的副本id，为0或空则取默认值
			return ectypeid;
		}

		@Override
		public void setRefreshtime(int _v_) { // 已经进行过的刷新次数
			refreshtime = _v_;
		}

		@Override
		public void setEctypeid(int _v_) { // 上次随机到的副本id，为0或空则取默认值
			ectypeid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof HeroesGroupInfo.Data)) return false;
			HeroesGroupInfo.Data _o_ = (HeroesGroupInfo.Data) _o1_;
			if (refreshtime != _o_.refreshtime) return false;
			if (ectypeid != _o_.ectypeid) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += refreshtime;
			_h_ += ectypeid;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(refreshtime);
			_sb_.append(",");
			_sb_.append(ectypeid);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
