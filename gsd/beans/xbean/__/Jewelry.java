
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Jewelry extends xdb.XBean implements xbean.Jewelry {
	private int id; // 宝珠id
	private int level; // 宝珠等级
	private int exp; // 宝珠经验

	@Override
	public void _reset_unsafe_() {
		id = 0;
		level = 0;
		exp = 0;
	}

	Jewelry(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public Jewelry() {
		this(0, null, null);
	}

	public Jewelry(Jewelry _o_) {
		this(_o_, null, null);
	}

	Jewelry(xbean.Jewelry _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Jewelry) assign((Jewelry)_o1_);
		else if (_o1_ instanceof Jewelry.Data) assign((Jewelry.Data)_o1_);
		else if (_o1_ instanceof Jewelry.Const) assign(((Jewelry.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Jewelry _o_) {
		_o_._xdb_verify_unsafe_();
		id = _o_.id;
		level = _o_.level;
		exp = _o_.exp;
	}

	private void assign(Jewelry.Data _o_) {
		id = _o_.id;
		level = _o_.level;
		exp = _o_.exp;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  1));_os_.marshal(id);
    _os_.marshal((short)( 8192|  2));_os_.marshal(level);
    _os_.marshal((short)( 8192|  3));_os_.marshal(exp);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):id = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):id = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):level = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):level = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):exp = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):exp = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Jewelry copy() {
		_xdb_verify_unsafe_();
		return new Jewelry(this);
	}

	@Override
	public xbean.Jewelry toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Jewelry toBean() {
		_xdb_verify_unsafe_();
		return new Jewelry(this); // same as copy()
	}

	@Override
	public xbean.Jewelry toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Jewelry toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getId() { // 宝珠id
		_xdb_verify_unsafe_();
		return id;
	}

	@Override
	public int getLevel() { // 宝珠等级
		_xdb_verify_unsafe_();
		return level;
	}

	@Override
	public int getExp() { // 宝珠经验
		_xdb_verify_unsafe_();
		return exp;
	}

	@Override
	public void setId(int _v_) { // 宝珠id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "id") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, id) {
					public void rollback() { id = _xdb_saved; }
				};}});
		id = _v_;
	}

	@Override
	public void setLevel(int _v_) { // 宝珠等级
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "level") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, level) {
					public void rollback() { level = _xdb_saved; }
				};}});
		level = _v_;
	}

	@Override
	public void setExp(int _v_) { // 宝珠经验
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "exp") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, exp) {
					public void rollback() { exp = _xdb_saved; }
				};}});
		exp = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Jewelry _o_ = null;
		if ( _o1_ instanceof Jewelry ) _o_ = (Jewelry)_o1_;
		else if ( _o1_ instanceof Jewelry.Const ) _o_ = ((Jewelry.Const)_o1_).nThis();
		else return false;
		if (id != _o_.id) return false;
		if (level != _o_.level) return false;
		if (exp != _o_.exp) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += id;
		_h_ += level;
		_h_ += exp;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id);
		_sb_.append(",");
		_sb_.append(level);
		_sb_.append(",");
		_sb_.append(exp);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("id"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("level"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("exp"));
		return lb;
	}

	private class Const implements xbean.Jewelry {
		Jewelry nThis() {
			return Jewelry.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Jewelry copy() {
			return Jewelry.this.copy();
		}

		@Override
		public xbean.Jewelry toData() {
			return Jewelry.this.toData();
		}

		public xbean.Jewelry toBean() {
			return Jewelry.this.toBean();
		}

		@Override
		public xbean.Jewelry toDataIf() {
			return Jewelry.this.toDataIf();
		}

		public xbean.Jewelry toBeanIf() {
			return Jewelry.this.toBeanIf();
		}

		@Override
		public int getId() { // 宝珠id
			_xdb_verify_unsafe_();
			return id;
		}

		@Override
		public int getLevel() { // 宝珠等级
			_xdb_verify_unsafe_();
			return level;
		}

		@Override
		public int getExp() { // 宝珠经验
			_xdb_verify_unsafe_();
			return exp;
		}

		@Override
		public void setId(int _v_) { // 宝珠id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLevel(int _v_) { // 宝珠等级
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExp(int _v_) { // 宝珠经验
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
			return Jewelry.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Jewelry.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Jewelry.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Jewelry.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Jewelry.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Jewelry.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Jewelry.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Jewelry.this.hashCode();
		}

		@Override
		public String toString() {
			return Jewelry.this.toString();
		}

	}

	public static final class Data implements xbean.Jewelry {
		private int id; // 宝珠id
		private int level; // 宝珠等级
		private int exp; // 宝珠经验

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.Jewelry _o1_) {
			if (_o1_ instanceof Jewelry) assign((Jewelry)_o1_);
			else if (_o1_ instanceof Jewelry.Data) assign((Jewelry.Data)_o1_);
			else if (_o1_ instanceof Jewelry.Const) assign(((Jewelry.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Jewelry _o_) {
			id = _o_.id;
			level = _o_.level;
			exp = _o_.exp;
		}

		private void assign(Jewelry.Data _o_) {
			id = _o_.id;
			level = _o_.level;
			exp = _o_.exp;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  1));_os_.marshal(id);
	_os_.marshal((short)( 8192|  2));_os_.marshal(level);
	_os_.marshal((short)( 8192|  3));_os_.marshal(exp);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):id = _os_.unmarshal_int();
					break;
					case ( 6144|  1):id = _os_.unmarshal_short();
					break;
					case ( 8192|  2):level = _os_.unmarshal_int();
					break;
					case ( 6144|  2):level = _os_.unmarshal_short();
					break;
					case ( 8192|  3):exp = _os_.unmarshal_int();
					break;
					case ( 6144|  3):exp = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Jewelry copy() {
			return new Data(this);
		}

		@Override
		public xbean.Jewelry toData() {
			return new Data(this);
		}

		public xbean.Jewelry toBean() {
			return new Jewelry(this, null, null);
		}

		@Override
		public xbean.Jewelry toDataIf() {
			return this;
		}

		public xbean.Jewelry toBeanIf() {
			return new Jewelry(this, null, null);
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
		public int getId() { // 宝珠id
			return id;
		}

		@Override
		public int getLevel() { // 宝珠等级
			return level;
		}

		@Override
		public int getExp() { // 宝珠经验
			return exp;
		}

		@Override
		public void setId(int _v_) { // 宝珠id
			id = _v_;
		}

		@Override
		public void setLevel(int _v_) { // 宝珠等级
			level = _v_;
		}

		@Override
		public void setExp(int _v_) { // 宝珠经验
			exp = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Jewelry.Data)) return false;
			Jewelry.Data _o_ = (Jewelry.Data) _o1_;
			if (id != _o_.id) return false;
			if (level != _o_.level) return false;
			if (exp != _o_.exp) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += id;
			_h_ += level;
			_h_ += exp;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(id);
			_sb_.append(",");
			_sb_.append(level);
			_sb_.append(",");
			_sb_.append(exp);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
