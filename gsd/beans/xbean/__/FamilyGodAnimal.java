
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class FamilyGodAnimal extends xdb.XBean implements xbean.FamilyGodAnimal {
	private int animalid; // 
	private int animallevel; // 神兽进化到的level
	private long exp; // 神兽经验值

	@Override
	public void _reset_unsafe_() {
		animalid = 0;
		animallevel = 1;
		exp = 0L;
	}

	FamilyGodAnimal(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		animallevel = 1;
	}

	public FamilyGodAnimal() {
		this(0, null, null);
	}

	public FamilyGodAnimal(FamilyGodAnimal _o_) {
		this(_o_, null, null);
	}

	FamilyGodAnimal(xbean.FamilyGodAnimal _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof FamilyGodAnimal) assign((FamilyGodAnimal)_o1_);
		else if (_o1_ instanceof FamilyGodAnimal.Data) assign((FamilyGodAnimal.Data)_o1_);
		else if (_o1_ instanceof FamilyGodAnimal.Const) assign(((FamilyGodAnimal.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(FamilyGodAnimal _o_) {
		_o_._xdb_verify_unsafe_();
		animalid = _o_.animalid;
		animallevel = _o_.animallevel;
		exp = _o_.exp;
	}

	private void assign(FamilyGodAnimal.Data _o_) {
		animalid = _o_.animalid;
		animallevel = _o_.animallevel;
		exp = _o_.exp;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  1));_os_.marshal(animalid);
    _os_.marshal((short)( 8192|  2));_os_.marshal(animallevel);
    _os_.marshal((short)(10240|  3));_os_.marshal(exp);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):animalid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):animalid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):animallevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):animallevel = _os_.unmarshal_short();
    				break;
    				case (10240|  3):exp = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):exp = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):exp = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.FamilyGodAnimal copy() {
		_xdb_verify_unsafe_();
		return new FamilyGodAnimal(this);
	}

	@Override
	public xbean.FamilyGodAnimal toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyGodAnimal toBean() {
		_xdb_verify_unsafe_();
		return new FamilyGodAnimal(this); // same as copy()
	}

	@Override
	public xbean.FamilyGodAnimal toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyGodAnimal toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getAnimalid() { // 
		_xdb_verify_unsafe_();
		return animalid;
	}

	@Override
	public int getAnimallevel() { // 神兽进化到的level
		_xdb_verify_unsafe_();
		return animallevel;
	}

	@Override
	public long getExp() { // 神兽经验值
		_xdb_verify_unsafe_();
		return exp;
	}

	@Override
	public void setAnimalid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "animalid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, animalid) {
					public void rollback() { animalid = _xdb_saved; }
				};}});
		animalid = _v_;
	}

	@Override
	public void setAnimallevel(int _v_) { // 神兽进化到的level
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "animallevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, animallevel) {
					public void rollback() { animallevel = _xdb_saved; }
				};}});
		animallevel = _v_;
	}

	@Override
	public void setExp(long _v_) { // 神兽经验值
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "exp") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, exp) {
					public void rollback() { exp = _xdb_saved; }
				};}});
		exp = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		FamilyGodAnimal _o_ = null;
		if ( _o1_ instanceof FamilyGodAnimal ) _o_ = (FamilyGodAnimal)_o1_;
		else if ( _o1_ instanceof FamilyGodAnimal.Const ) _o_ = ((FamilyGodAnimal.Const)_o1_).nThis();
		else return false;
		if (animalid != _o_.animalid) return false;
		if (animallevel != _o_.animallevel) return false;
		if (exp != _o_.exp) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += animalid;
		_h_ += animallevel;
		_h_ += exp;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(animalid);
		_sb_.append(",");
		_sb_.append(animallevel);
		_sb_.append(",");
		_sb_.append(exp);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("animalid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("animallevel"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("exp"));
		return lb;
	}

	private class Const implements xbean.FamilyGodAnimal {
		FamilyGodAnimal nThis() {
			return FamilyGodAnimal.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.FamilyGodAnimal copy() {
			return FamilyGodAnimal.this.copy();
		}

		@Override
		public xbean.FamilyGodAnimal toData() {
			return FamilyGodAnimal.this.toData();
		}

		public xbean.FamilyGodAnimal toBean() {
			return FamilyGodAnimal.this.toBean();
		}

		@Override
		public xbean.FamilyGodAnimal toDataIf() {
			return FamilyGodAnimal.this.toDataIf();
		}

		public xbean.FamilyGodAnimal toBeanIf() {
			return FamilyGodAnimal.this.toBeanIf();
		}

		@Override
		public int getAnimalid() { // 
			_xdb_verify_unsafe_();
			return animalid;
		}

		@Override
		public int getAnimallevel() { // 神兽进化到的level
			_xdb_verify_unsafe_();
			return animallevel;
		}

		@Override
		public long getExp() { // 神兽经验值
			_xdb_verify_unsafe_();
			return exp;
		}

		@Override
		public void setAnimalid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAnimallevel(int _v_) { // 神兽进化到的level
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExp(long _v_) { // 神兽经验值
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
			return FamilyGodAnimal.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return FamilyGodAnimal.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return FamilyGodAnimal.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return FamilyGodAnimal.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return FamilyGodAnimal.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return FamilyGodAnimal.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return FamilyGodAnimal.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return FamilyGodAnimal.this.hashCode();
		}

		@Override
		public String toString() {
			return FamilyGodAnimal.this.toString();
		}

	}

	public static final class Data implements xbean.FamilyGodAnimal {
		private int animalid; // 
		private int animallevel; // 神兽进化到的level
		private long exp; // 神兽经验值

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			animallevel = 1;
		}

		Data(xbean.FamilyGodAnimal _o1_) {
			if (_o1_ instanceof FamilyGodAnimal) assign((FamilyGodAnimal)_o1_);
			else if (_o1_ instanceof FamilyGodAnimal.Data) assign((FamilyGodAnimal.Data)_o1_);
			else if (_o1_ instanceof FamilyGodAnimal.Const) assign(((FamilyGodAnimal.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(FamilyGodAnimal _o_) {
			animalid = _o_.animalid;
			animallevel = _o_.animallevel;
			exp = _o_.exp;
		}

		private void assign(FamilyGodAnimal.Data _o_) {
			animalid = _o_.animalid;
			animallevel = _o_.animallevel;
			exp = _o_.exp;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  1));_os_.marshal(animalid);
	_os_.marshal((short)( 8192|  2));_os_.marshal(animallevel);
	_os_.marshal((short)(10240|  3));_os_.marshal(exp);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):animalid = _os_.unmarshal_int();
					break;
					case ( 6144|  1):animalid = _os_.unmarshal_short();
					break;
					case ( 8192|  2):animallevel = _os_.unmarshal_int();
					break;
					case ( 6144|  2):animallevel = _os_.unmarshal_short();
					break;
					case (10240|  3):exp = _os_.unmarshal_long();
					break;
					case ( 6144|  3):exp = _os_.unmarshal_short();
					break;
					case ( 8192|  3):exp = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.FamilyGodAnimal copy() {
			return new Data(this);
		}

		@Override
		public xbean.FamilyGodAnimal toData() {
			return new Data(this);
		}

		public xbean.FamilyGodAnimal toBean() {
			return new FamilyGodAnimal(this, null, null);
		}

		@Override
		public xbean.FamilyGodAnimal toDataIf() {
			return this;
		}

		public xbean.FamilyGodAnimal toBeanIf() {
			return new FamilyGodAnimal(this, null, null);
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
		public int getAnimalid() { // 
			return animalid;
		}

		@Override
		public int getAnimallevel() { // 神兽进化到的level
			return animallevel;
		}

		@Override
		public long getExp() { // 神兽经验值
			return exp;
		}

		@Override
		public void setAnimalid(int _v_) { // 
			animalid = _v_;
		}

		@Override
		public void setAnimallevel(int _v_) { // 神兽进化到的level
			animallevel = _v_;
		}

		@Override
		public void setExp(long _v_) { // 神兽经验值
			exp = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof FamilyGodAnimal.Data)) return false;
			FamilyGodAnimal.Data _o_ = (FamilyGodAnimal.Data) _o1_;
			if (animalid != _o_.animalid) return false;
			if (animallevel != _o_.animallevel) return false;
			if (exp != _o_.exp) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += animalid;
			_h_ += animallevel;
			_h_ += exp;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(animalid);
			_sb_.append(",");
			_sb_.append(animallevel);
			_sb_.append(",");
			_sb_.append(exp);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
