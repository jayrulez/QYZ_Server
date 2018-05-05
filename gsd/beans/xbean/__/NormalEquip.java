
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class NormalEquip extends xdb.XBean implements xbean.NormalEquip {
	private int anneallevel; // 装备强化等级
	private int perfuselevel; // 装备灌注等级
	private int goldcost; // 追加消耗的虚拟币
	private int annealitemcost; // 炼器消耗的物品数量
	private int perfuseitemcost; // 灌注消耗的物品数量

	@Override
	public void _reset_unsafe_() {
		anneallevel = 0;
		perfuselevel = 0;
		goldcost = 0;
		annealitemcost = 0;
		perfuseitemcost = 0;
	}

	NormalEquip(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public NormalEquip() {
		this(0, null, null);
	}

	public NormalEquip(NormalEquip _o_) {
		this(_o_, null, null);
	}

	NormalEquip(xbean.NormalEquip _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof NormalEquip) assign((NormalEquip)_o1_);
		else if (_o1_ instanceof NormalEquip.Data) assign((NormalEquip.Data)_o1_);
		else if (_o1_ instanceof NormalEquip.Const) assign(((NormalEquip.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(NormalEquip _o_) {
		_o_._xdb_verify_unsafe_();
		anneallevel = _o_.anneallevel;
		perfuselevel = _o_.perfuselevel;
		goldcost = _o_.goldcost;
		annealitemcost = _o_.annealitemcost;
		perfuseitemcost = _o_.perfuseitemcost;
	}

	private void assign(NormalEquip.Data _o_) {
		anneallevel = _o_.anneallevel;
		perfuselevel = _o_.perfuselevel;
		goldcost = _o_.goldcost;
		annealitemcost = _o_.annealitemcost;
		perfuseitemcost = _o_.perfuseitemcost;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)5);
    _os_.marshal((short)( 8192|  1));_os_.marshal(anneallevel);
    _os_.marshal((short)( 8192|  2));_os_.marshal(perfuselevel);
    _os_.marshal((short)( 8192|  3));_os_.marshal(goldcost);
    _os_.marshal((short)( 8192|  4));_os_.marshal(annealitemcost);
    _os_.marshal((short)( 8192|  5));_os_.marshal(perfuseitemcost);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):anneallevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):anneallevel = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):perfuselevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):perfuselevel = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):goldcost = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):goldcost = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):annealitemcost = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):annealitemcost = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):perfuseitemcost = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):perfuseitemcost = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.NormalEquip copy() {
		_xdb_verify_unsafe_();
		return new NormalEquip(this);
	}

	@Override
	public xbean.NormalEquip toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.NormalEquip toBean() {
		_xdb_verify_unsafe_();
		return new NormalEquip(this); // same as copy()
	}

	@Override
	public xbean.NormalEquip toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.NormalEquip toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getAnneallevel() { // 装备强化等级
		_xdb_verify_unsafe_();
		return anneallevel;
	}

	@Override
	public int getPerfuselevel() { // 装备灌注等级
		_xdb_verify_unsafe_();
		return perfuselevel;
	}

	@Override
	public int getGoldcost() { // 追加消耗的虚拟币
		_xdb_verify_unsafe_();
		return goldcost;
	}

	@Override
	public int getAnnealitemcost() { // 炼器消耗的物品数量
		_xdb_verify_unsafe_();
		return annealitemcost;
	}

	@Override
	public int getPerfuseitemcost() { // 灌注消耗的物品数量
		_xdb_verify_unsafe_();
		return perfuseitemcost;
	}

	@Override
	public void setAnneallevel(int _v_) { // 装备强化等级
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "anneallevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, anneallevel) {
					public void rollback() { anneallevel = _xdb_saved; }
				};}});
		anneallevel = _v_;
	}

	@Override
	public void setPerfuselevel(int _v_) { // 装备灌注等级
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "perfuselevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, perfuselevel) {
					public void rollback() { perfuselevel = _xdb_saved; }
				};}});
		perfuselevel = _v_;
	}

	@Override
	public void setGoldcost(int _v_) { // 追加消耗的虚拟币
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "goldcost") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, goldcost) {
					public void rollback() { goldcost = _xdb_saved; }
				};}});
		goldcost = _v_;
	}

	@Override
	public void setAnnealitemcost(int _v_) { // 炼器消耗的物品数量
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "annealitemcost") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, annealitemcost) {
					public void rollback() { annealitemcost = _xdb_saved; }
				};}});
		annealitemcost = _v_;
	}

	@Override
	public void setPerfuseitemcost(int _v_) { // 灌注消耗的物品数量
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "perfuseitemcost") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, perfuseitemcost) {
					public void rollback() { perfuseitemcost = _xdb_saved; }
				};}});
		perfuseitemcost = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		NormalEquip _o_ = null;
		if ( _o1_ instanceof NormalEquip ) _o_ = (NormalEquip)_o1_;
		else if ( _o1_ instanceof NormalEquip.Const ) _o_ = ((NormalEquip.Const)_o1_).nThis();
		else return false;
		if (anneallevel != _o_.anneallevel) return false;
		if (perfuselevel != _o_.perfuselevel) return false;
		if (goldcost != _o_.goldcost) return false;
		if (annealitemcost != _o_.annealitemcost) return false;
		if (perfuseitemcost != _o_.perfuseitemcost) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += anneallevel;
		_h_ += perfuselevel;
		_h_ += goldcost;
		_h_ += annealitemcost;
		_h_ += perfuseitemcost;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(anneallevel);
		_sb_.append(",");
		_sb_.append(perfuselevel);
		_sb_.append(",");
		_sb_.append(goldcost);
		_sb_.append(",");
		_sb_.append(annealitemcost);
		_sb_.append(",");
		_sb_.append(perfuseitemcost);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("anneallevel"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("perfuselevel"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("goldcost"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("annealitemcost"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("perfuseitemcost"));
		return lb;
	}

	private class Const implements xbean.NormalEquip {
		NormalEquip nThis() {
			return NormalEquip.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.NormalEquip copy() {
			return NormalEquip.this.copy();
		}

		@Override
		public xbean.NormalEquip toData() {
			return NormalEquip.this.toData();
		}

		public xbean.NormalEquip toBean() {
			return NormalEquip.this.toBean();
		}

		@Override
		public xbean.NormalEquip toDataIf() {
			return NormalEquip.this.toDataIf();
		}

		public xbean.NormalEquip toBeanIf() {
			return NormalEquip.this.toBeanIf();
		}

		@Override
		public int getAnneallevel() { // 装备强化等级
			_xdb_verify_unsafe_();
			return anneallevel;
		}

		@Override
		public int getPerfuselevel() { // 装备灌注等级
			_xdb_verify_unsafe_();
			return perfuselevel;
		}

		@Override
		public int getGoldcost() { // 追加消耗的虚拟币
			_xdb_verify_unsafe_();
			return goldcost;
		}

		@Override
		public int getAnnealitemcost() { // 炼器消耗的物品数量
			_xdb_verify_unsafe_();
			return annealitemcost;
		}

		@Override
		public int getPerfuseitemcost() { // 灌注消耗的物品数量
			_xdb_verify_unsafe_();
			return perfuseitemcost;
		}

		@Override
		public void setAnneallevel(int _v_) { // 装备强化等级
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPerfuselevel(int _v_) { // 装备灌注等级
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGoldcost(int _v_) { // 追加消耗的虚拟币
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAnnealitemcost(int _v_) { // 炼器消耗的物品数量
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPerfuseitemcost(int _v_) { // 灌注消耗的物品数量
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
			return NormalEquip.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return NormalEquip.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return NormalEquip.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return NormalEquip.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return NormalEquip.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return NormalEquip.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return NormalEquip.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return NormalEquip.this.hashCode();
		}

		@Override
		public String toString() {
			return NormalEquip.this.toString();
		}

	}

	public static final class Data implements xbean.NormalEquip {
		private int anneallevel; // 装备强化等级
		private int perfuselevel; // 装备灌注等级
		private int goldcost; // 追加消耗的虚拟币
		private int annealitemcost; // 炼器消耗的物品数量
		private int perfuseitemcost; // 灌注消耗的物品数量

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.NormalEquip _o1_) {
			if (_o1_ instanceof NormalEquip) assign((NormalEquip)_o1_);
			else if (_o1_ instanceof NormalEquip.Data) assign((NormalEquip.Data)_o1_);
			else if (_o1_ instanceof NormalEquip.Const) assign(((NormalEquip.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(NormalEquip _o_) {
			anneallevel = _o_.anneallevel;
			perfuselevel = _o_.perfuselevel;
			goldcost = _o_.goldcost;
			annealitemcost = _o_.annealitemcost;
			perfuseitemcost = _o_.perfuseitemcost;
		}

		private void assign(NormalEquip.Data _o_) {
			anneallevel = _o_.anneallevel;
			perfuselevel = _o_.perfuselevel;
			goldcost = _o_.goldcost;
			annealitemcost = _o_.annealitemcost;
			perfuseitemcost = _o_.perfuseitemcost;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)5);
	_os_.marshal((short)( 8192|  1));_os_.marshal(anneallevel);
	_os_.marshal((short)( 8192|  2));_os_.marshal(perfuselevel);
	_os_.marshal((short)( 8192|  3));_os_.marshal(goldcost);
	_os_.marshal((short)( 8192|  4));_os_.marshal(annealitemcost);
	_os_.marshal((short)( 8192|  5));_os_.marshal(perfuseitemcost);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):anneallevel = _os_.unmarshal_int();
					break;
					case ( 6144|  1):anneallevel = _os_.unmarshal_short();
					break;
					case ( 8192|  2):perfuselevel = _os_.unmarshal_int();
					break;
					case ( 6144|  2):perfuselevel = _os_.unmarshal_short();
					break;
					case ( 8192|  3):goldcost = _os_.unmarshal_int();
					break;
					case ( 6144|  3):goldcost = _os_.unmarshal_short();
					break;
					case ( 8192|  4):annealitemcost = _os_.unmarshal_int();
					break;
					case ( 6144|  4):annealitemcost = _os_.unmarshal_short();
					break;
					case ( 8192|  5):perfuseitemcost = _os_.unmarshal_int();
					break;
					case ( 6144|  5):perfuseitemcost = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.NormalEquip copy() {
			return new Data(this);
		}

		@Override
		public xbean.NormalEquip toData() {
			return new Data(this);
		}

		public xbean.NormalEquip toBean() {
			return new NormalEquip(this, null, null);
		}

		@Override
		public xbean.NormalEquip toDataIf() {
			return this;
		}

		public xbean.NormalEquip toBeanIf() {
			return new NormalEquip(this, null, null);
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
		public int getAnneallevel() { // 装备强化等级
			return anneallevel;
		}

		@Override
		public int getPerfuselevel() { // 装备灌注等级
			return perfuselevel;
		}

		@Override
		public int getGoldcost() { // 追加消耗的虚拟币
			return goldcost;
		}

		@Override
		public int getAnnealitemcost() { // 炼器消耗的物品数量
			return annealitemcost;
		}

		@Override
		public int getPerfuseitemcost() { // 灌注消耗的物品数量
			return perfuseitemcost;
		}

		@Override
		public void setAnneallevel(int _v_) { // 装备强化等级
			anneallevel = _v_;
		}

		@Override
		public void setPerfuselevel(int _v_) { // 装备灌注等级
			perfuselevel = _v_;
		}

		@Override
		public void setGoldcost(int _v_) { // 追加消耗的虚拟币
			goldcost = _v_;
		}

		@Override
		public void setAnnealitemcost(int _v_) { // 炼器消耗的物品数量
			annealitemcost = _v_;
		}

		@Override
		public void setPerfuseitemcost(int _v_) { // 灌注消耗的物品数量
			perfuseitemcost = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof NormalEquip.Data)) return false;
			NormalEquip.Data _o_ = (NormalEquip.Data) _o1_;
			if (anneallevel != _o_.anneallevel) return false;
			if (perfuselevel != _o_.perfuselevel) return false;
			if (goldcost != _o_.goldcost) return false;
			if (annealitemcost != _o_.annealitemcost) return false;
			if (perfuseitemcost != _o_.perfuseitemcost) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += anneallevel;
			_h_ += perfuselevel;
			_h_ += goldcost;
			_h_ += annealitemcost;
			_h_ += perfuseitemcost;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(anneallevel);
			_sb_.append(",");
			_sb_.append(perfuselevel);
			_sb_.append(",");
			_sb_.append(goldcost);
			_sb_.append(",");
			_sb_.append(annealitemcost);
			_sb_.append(",");
			_sb_.append(perfuseitemcost);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
