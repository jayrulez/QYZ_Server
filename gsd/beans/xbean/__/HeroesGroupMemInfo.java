
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class HeroesGroupMemInfo extends xdb.XBean implements xbean.HeroesGroupMemInfo {
	private int ectypeid; // 
	private int randomtime; // 已经随机的次数
	private java.util.LinkedList<xbean.RandomBonus> bonus; // 上次随机但未领取的奖励，领取后则为空

	@Override
	public void _reset_unsafe_() {
		ectypeid = 0;
		randomtime = 0;
		bonus.clear();
	}

	HeroesGroupMemInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		bonus = new java.util.LinkedList<xbean.RandomBonus>();
	}

	public HeroesGroupMemInfo() {
		this(0, null, null);
	}

	public HeroesGroupMemInfo(HeroesGroupMemInfo _o_) {
		this(_o_, null, null);
	}

	HeroesGroupMemInfo(xbean.HeroesGroupMemInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof HeroesGroupMemInfo) assign((HeroesGroupMemInfo)_o1_);
		else if (_o1_ instanceof HeroesGroupMemInfo.Data) assign((HeroesGroupMemInfo.Data)_o1_);
		else if (_o1_ instanceof HeroesGroupMemInfo.Const) assign(((HeroesGroupMemInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(HeroesGroupMemInfo _o_) {
		_o_._xdb_verify_unsafe_();
		ectypeid = _o_.ectypeid;
		randomtime = _o_.randomtime;
		bonus = new java.util.LinkedList<xbean.RandomBonus>();
		for (xbean.RandomBonus _v_ : _o_.bonus)
			bonus.add(new RandomBonus(_v_, this, "bonus"));
	}

	private void assign(HeroesGroupMemInfo.Data _o_) {
		ectypeid = _o_.ectypeid;
		randomtime = _o_.randomtime;
		bonus = new java.util.LinkedList<xbean.RandomBonus>();
		for (xbean.RandomBonus _v_ : _o_.bonus)
			bonus.add(new RandomBonus(_v_, this, "bonus"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  1));_os_.marshal(ectypeid);
    _os_.marshal((short)( 8192|  2));_os_.marshal(randomtime);
    _os_.marshal((short)(22528|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(bonus.size());
for (xbean.RandomBonus _v_ : bonus) {
	_v_.marshal(_os_);
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
    				case ( 8192|  1):ectypeid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):ectypeid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):randomtime = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):randomtime = _os_.unmarshal_short();
    				break;
    				case (22528|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.RandomBonus _v_ = new RandomBonus(0, this, "bonus");
	_v_.unmarshal(_os_);
	bonus.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.HeroesGroupMemInfo copy() {
		_xdb_verify_unsafe_();
		return new HeroesGroupMemInfo(this);
	}

	@Override
	public xbean.HeroesGroupMemInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HeroesGroupMemInfo toBean() {
		_xdb_verify_unsafe_();
		return new HeroesGroupMemInfo(this); // same as copy()
	}

	@Override
	public xbean.HeroesGroupMemInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HeroesGroupMemInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getEctypeid() { // 
		_xdb_verify_unsafe_();
		return ectypeid;
	}

	@Override
	public int getRandomtime() { // 已经随机的次数
		_xdb_verify_unsafe_();
		return randomtime;
	}

	@Override
	public java.util.List<xbean.RandomBonus> getBonus() { // 上次随机但未领取的奖励，领取后则为空
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "bonus"), bonus);
	}

	public java.util.List<xbean.RandomBonus> getBonusAsData() { // 上次随机但未领取的奖励，领取后则为空
		_xdb_verify_unsafe_();
		java.util.List<xbean.RandomBonus> bonus;
		HeroesGroupMemInfo _o_ = this;
		bonus = new java.util.LinkedList<xbean.RandomBonus>();
		for (xbean.RandomBonus _v_ : _o_.bonus)
			bonus.add(new RandomBonus.Data(_v_));
		return bonus;
	}

	@Override
	public void setEctypeid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "ectypeid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, ectypeid) {
					public void rollback() { ectypeid = _xdb_saved; }
				};}});
		ectypeid = _v_;
	}

	@Override
	public void setRandomtime(int _v_) { // 已经随机的次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "randomtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, randomtime) {
					public void rollback() { randomtime = _xdb_saved; }
				};}});
		randomtime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		HeroesGroupMemInfo _o_ = null;
		if ( _o1_ instanceof HeroesGroupMemInfo ) _o_ = (HeroesGroupMemInfo)_o1_;
		else if ( _o1_ instanceof HeroesGroupMemInfo.Const ) _o_ = ((HeroesGroupMemInfo.Const)_o1_).nThis();
		else return false;
		if (ectypeid != _o_.ectypeid) return false;
		if (randomtime != _o_.randomtime) return false;
		if (!bonus.equals(_o_.bonus)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += randomtime;
		_h_ += bonus.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid);
		_sb_.append(",");
		_sb_.append(randomtime);
		_sb_.append(",");
		_sb_.append(bonus);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("ectypeid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("randomtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("bonus"));
		return lb;
	}

	private class Const implements xbean.HeroesGroupMemInfo {
		HeroesGroupMemInfo nThis() {
			return HeroesGroupMemInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.HeroesGroupMemInfo copy() {
			return HeroesGroupMemInfo.this.copy();
		}

		@Override
		public xbean.HeroesGroupMemInfo toData() {
			return HeroesGroupMemInfo.this.toData();
		}

		public xbean.HeroesGroupMemInfo toBean() {
			return HeroesGroupMemInfo.this.toBean();
		}

		@Override
		public xbean.HeroesGroupMemInfo toDataIf() {
			return HeroesGroupMemInfo.this.toDataIf();
		}

		public xbean.HeroesGroupMemInfo toBeanIf() {
			return HeroesGroupMemInfo.this.toBeanIf();
		}

		@Override
		public int getEctypeid() { // 
			_xdb_verify_unsafe_();
			return ectypeid;
		}

		@Override
		public int getRandomtime() { // 已经随机的次数
			_xdb_verify_unsafe_();
			return randomtime;
		}

		@Override
		public java.util.List<xbean.RandomBonus> getBonus() { // 上次随机但未领取的奖励，领取后则为空
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(bonus);
		}

		public java.util.List<xbean.RandomBonus> getBonusAsData() { // 上次随机但未领取的奖励，领取后则为空
			_xdb_verify_unsafe_();
			java.util.List<xbean.RandomBonus> bonus;
			HeroesGroupMemInfo _o_ = HeroesGroupMemInfo.this;
		bonus = new java.util.LinkedList<xbean.RandomBonus>();
		for (xbean.RandomBonus _v_ : _o_.bonus)
			bonus.add(new RandomBonus.Data(_v_));
			return bonus;
		}

		@Override
		public void setEctypeid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRandomtime(int _v_) { // 已经随机的次数
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
			return HeroesGroupMemInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return HeroesGroupMemInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return HeroesGroupMemInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return HeroesGroupMemInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return HeroesGroupMemInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return HeroesGroupMemInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return HeroesGroupMemInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return HeroesGroupMemInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return HeroesGroupMemInfo.this.toString();
		}

	}

	public static final class Data implements xbean.HeroesGroupMemInfo {
		private int ectypeid; // 
		private int randomtime; // 已经随机的次数
		private java.util.LinkedList<xbean.RandomBonus> bonus; // 上次随机但未领取的奖励，领取后则为空

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			bonus = new java.util.LinkedList<xbean.RandomBonus>();
		}

		Data(xbean.HeroesGroupMemInfo _o1_) {
			if (_o1_ instanceof HeroesGroupMemInfo) assign((HeroesGroupMemInfo)_o1_);
			else if (_o1_ instanceof HeroesGroupMemInfo.Data) assign((HeroesGroupMemInfo.Data)_o1_);
			else if (_o1_ instanceof HeroesGroupMemInfo.Const) assign(((HeroesGroupMemInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(HeroesGroupMemInfo _o_) {
			ectypeid = _o_.ectypeid;
			randomtime = _o_.randomtime;
			bonus = new java.util.LinkedList<xbean.RandomBonus>();
			for (xbean.RandomBonus _v_ : _o_.bonus)
				bonus.add(new RandomBonus.Data(_v_));
		}

		private void assign(HeroesGroupMemInfo.Data _o_) {
			ectypeid = _o_.ectypeid;
			randomtime = _o_.randomtime;
			bonus = new java.util.LinkedList<xbean.RandomBonus>();
			for (xbean.RandomBonus _v_ : _o_.bonus)
				bonus.add(new RandomBonus.Data(_v_));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  1));_os_.marshal(ectypeid);
	_os_.marshal((short)( 8192|  2));_os_.marshal(randomtime);
	_os_.marshal((short)(22528|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(bonus.size());
for (xbean.RandomBonus _v_ : bonus) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):ectypeid = _os_.unmarshal_int();
					break;
					case ( 6144|  1):ectypeid = _os_.unmarshal_short();
					break;
					case ( 8192|  2):randomtime = _os_.unmarshal_int();
					break;
					case ( 6144|  2):randomtime = _os_.unmarshal_short();
					break;
					case (22528|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.RandomBonus _v_ = xbean.Pod.newRandomBonusData();
	_v_.unmarshal(_os_);
	bonus.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.HeroesGroupMemInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.HeroesGroupMemInfo toData() {
			return new Data(this);
		}

		public xbean.HeroesGroupMemInfo toBean() {
			return new HeroesGroupMemInfo(this, null, null);
		}

		@Override
		public xbean.HeroesGroupMemInfo toDataIf() {
			return this;
		}

		public xbean.HeroesGroupMemInfo toBeanIf() {
			return new HeroesGroupMemInfo(this, null, null);
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
		public int getEctypeid() { // 
			return ectypeid;
		}

		@Override
		public int getRandomtime() { // 已经随机的次数
			return randomtime;
		}

		@Override
		public java.util.List<xbean.RandomBonus> getBonus() { // 上次随机但未领取的奖励，领取后则为空
			return bonus;
		}

		@Override
		public java.util.List<xbean.RandomBonus> getBonusAsData() { // 上次随机但未领取的奖励，领取后则为空
			return bonus;
		}

		@Override
		public void setEctypeid(int _v_) { // 
			ectypeid = _v_;
		}

		@Override
		public void setRandomtime(int _v_) { // 已经随机的次数
			randomtime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof HeroesGroupMemInfo.Data)) return false;
			HeroesGroupMemInfo.Data _o_ = (HeroesGroupMemInfo.Data) _o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (randomtime != _o_.randomtime) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += ectypeid;
			_h_ += randomtime;
			_h_ += bonus.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(ectypeid);
			_sb_.append(",");
			_sb_.append(randomtime);
			_sb_.append(",");
			_sb_.append(bonus);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
