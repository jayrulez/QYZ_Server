
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ExchangeItem extends xdb.XBean implements xbean.ExchangeItem {
	private long id; // 
	private long owner; // 
	private int price; // 单价
	private int modelid; // 
	private int num; // 
	private long expiretime; // 
	private int anneallevel; // 
	private int perfuselevel; // 
	private java.util.LinkedList<xbean.AccessoryProp> accessorymainprop; // 
	private java.util.LinkedList<xbean.AccessoryProp> accessoryviceprop; // 
	private long unshelvetime; // 下架时间

	@Override
	public void _reset_unsafe_() {
		id = 0L;
		owner = 0L;
		price = 0;
		modelid = 0;
		num = 0;
		expiretime = 0L;
		anneallevel = 0;
		perfuselevel = 0;
		accessorymainprop.clear();
		accessoryviceprop.clear();
		unshelvetime = 0L;
	}

	ExchangeItem(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		accessorymainprop = new java.util.LinkedList<xbean.AccessoryProp>();
		accessoryviceprop = new java.util.LinkedList<xbean.AccessoryProp>();
	}

	public ExchangeItem() {
		this(0, null, null);
	}

	public ExchangeItem(ExchangeItem _o_) {
		this(_o_, null, null);
	}

	ExchangeItem(xbean.ExchangeItem _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ExchangeItem) assign((ExchangeItem)_o1_);
		else if (_o1_ instanceof ExchangeItem.Data) assign((ExchangeItem.Data)_o1_);
		else if (_o1_ instanceof ExchangeItem.Const) assign(((ExchangeItem.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ExchangeItem _o_) {
		_o_._xdb_verify_unsafe_();
		id = _o_.id;
		owner = _o_.owner;
		price = _o_.price;
		modelid = _o_.modelid;
		num = _o_.num;
		expiretime = _o_.expiretime;
		anneallevel = _o_.anneallevel;
		perfuselevel = _o_.perfuselevel;
		accessorymainprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.accessorymainprop)
			accessorymainprop.add(new AccessoryProp(_v_, this, "accessorymainprop"));
		accessoryviceprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.accessoryviceprop)
			accessoryviceprop.add(new AccessoryProp(_v_, this, "accessoryviceprop"));
		unshelvetime = _o_.unshelvetime;
	}

	private void assign(ExchangeItem.Data _o_) {
		id = _o_.id;
		owner = _o_.owner;
		price = _o_.price;
		modelid = _o_.modelid;
		num = _o_.num;
		expiretime = _o_.expiretime;
		anneallevel = _o_.anneallevel;
		perfuselevel = _o_.perfuselevel;
		accessorymainprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.accessorymainprop)
			accessorymainprop.add(new AccessoryProp(_v_, this, "accessorymainprop"));
		accessoryviceprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.accessoryviceprop)
			accessoryviceprop.add(new AccessoryProp(_v_, this, "accessoryviceprop"));
		unshelvetime = _o_.unshelvetime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)11);
    _os_.marshal((short)(10240|  1));_os_.marshal(id);
    _os_.marshal((short)(10240|  2));_os_.marshal(owner);
    _os_.marshal((short)( 8192|  3));_os_.marshal(price);
    _os_.marshal((short)( 8192|  4));_os_.marshal(modelid);
    _os_.marshal((short)( 8192|  5));_os_.marshal(num);
    _os_.marshal((short)(10240|  7));_os_.marshal(expiretime);
    _os_.marshal((short)( 8192|  8));_os_.marshal(anneallevel);
    _os_.marshal((short)( 8192|  9));_os_.marshal(perfuselevel);
    _os_.marshal((short)(22528| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(accessorymainprop.size());
for (xbean.AccessoryProp _v_ : accessorymainprop) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528| 11));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(accessoryviceprop.size());
for (xbean.AccessoryProp _v_ : accessoryviceprop) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240| 12));_os_.marshal(unshelvetime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):id = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):id = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):id = _os_.unmarshal_int();
    				break;
    				case (10240|  2):owner = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):owner = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):owner = _os_.unmarshal_int();
    				break;
    				case ( 8192|  3):price = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):price = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):modelid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):modelid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):num = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):num = _os_.unmarshal_short();
    				break;
    				case (10240|  7):expiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  7):expiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  7):expiretime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  8):anneallevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  8):anneallevel = _os_.unmarshal_short();
    				break;
    				case ( 8192|  9):perfuselevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  9):perfuselevel = _os_.unmarshal_short();
    				break;
    				case (22528| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.AccessoryProp _v_ = new AccessoryProp(0, this, "accessorymainprop");
	_v_.unmarshal(_os_);
	accessorymainprop.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528| 11):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.AccessoryProp _v_ = new AccessoryProp(0, this, "accessoryviceprop");
	_v_.unmarshal(_os_);
	accessoryviceprop.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (10240| 12):unshelvetime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 12):unshelvetime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 12):unshelvetime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ExchangeItem copy() {
		_xdb_verify_unsafe_();
		return new ExchangeItem(this);
	}

	@Override
	public xbean.ExchangeItem toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ExchangeItem toBean() {
		_xdb_verify_unsafe_();
		return new ExchangeItem(this); // same as copy()
	}

	@Override
	public xbean.ExchangeItem toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ExchangeItem toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getId() { // 
		_xdb_verify_unsafe_();
		return id;
	}

	@Override
	public long getOwner() { // 
		_xdb_verify_unsafe_();
		return owner;
	}

	@Override
	public int getPrice() { // 单价
		_xdb_verify_unsafe_();
		return price;
	}

	@Override
	public int getModelid() { // 
		_xdb_verify_unsafe_();
		return modelid;
	}

	@Override
	public int getNum() { // 
		_xdb_verify_unsafe_();
		return num;
	}

	@Override
	public long getExpiretime() { // 
		_xdb_verify_unsafe_();
		return expiretime;
	}

	@Override
	public int getAnneallevel() { // 
		_xdb_verify_unsafe_();
		return anneallevel;
	}

	@Override
	public int getPerfuselevel() { // 
		_xdb_verify_unsafe_();
		return perfuselevel;
	}

	@Override
	public java.util.List<xbean.AccessoryProp> getAccessorymainprop() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "accessorymainprop"), accessorymainprop);
	}

	public java.util.List<xbean.AccessoryProp> getAccessorymainpropAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<xbean.AccessoryProp> accessorymainprop;
		ExchangeItem _o_ = this;
		accessorymainprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.accessorymainprop)
			accessorymainprop.add(new AccessoryProp.Data(_v_));
		return accessorymainprop;
	}

	@Override
	public java.util.List<xbean.AccessoryProp> getAccessoryviceprop() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "accessoryviceprop"), accessoryviceprop);
	}

	public java.util.List<xbean.AccessoryProp> getAccessoryvicepropAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<xbean.AccessoryProp> accessoryviceprop;
		ExchangeItem _o_ = this;
		accessoryviceprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.accessoryviceprop)
			accessoryviceprop.add(new AccessoryProp.Data(_v_));
		return accessoryviceprop;
	}

	@Override
	public long getUnshelvetime() { // 下架时间
		_xdb_verify_unsafe_();
		return unshelvetime;
	}

	@Override
	public void setId(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "id") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, id) {
					public void rollback() { id = _xdb_saved; }
				};}});
		id = _v_;
	}

	@Override
	public void setOwner(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "owner") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, owner) {
					public void rollback() { owner = _xdb_saved; }
				};}});
		owner = _v_;
	}

	@Override
	public void setPrice(int _v_) { // 单价
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "price") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, price) {
					public void rollback() { price = _xdb_saved; }
				};}});
		price = _v_;
	}

	@Override
	public void setModelid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "modelid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, modelid) {
					public void rollback() { modelid = _xdb_saved; }
				};}});
		modelid = _v_;
	}

	@Override
	public void setNum(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "num") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, num) {
					public void rollback() { num = _xdb_saved; }
				};}});
		num = _v_;
	}

	@Override
	public void setExpiretime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "expiretime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, expiretime) {
					public void rollback() { expiretime = _xdb_saved; }
				};}});
		expiretime = _v_;
	}

	@Override
	public void setAnneallevel(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "anneallevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, anneallevel) {
					public void rollback() { anneallevel = _xdb_saved; }
				};}});
		anneallevel = _v_;
	}

	@Override
	public void setPerfuselevel(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "perfuselevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, perfuselevel) {
					public void rollback() { perfuselevel = _xdb_saved; }
				};}});
		perfuselevel = _v_;
	}

	@Override
	public void setUnshelvetime(long _v_) { // 下架时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "unshelvetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, unshelvetime) {
					public void rollback() { unshelvetime = _xdb_saved; }
				};}});
		unshelvetime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ExchangeItem _o_ = null;
		if ( _o1_ instanceof ExchangeItem ) _o_ = (ExchangeItem)_o1_;
		else if ( _o1_ instanceof ExchangeItem.Const ) _o_ = ((ExchangeItem.Const)_o1_).nThis();
		else return false;
		if (id != _o_.id) return false;
		if (owner != _o_.owner) return false;
		if (price != _o_.price) return false;
		if (modelid != _o_.modelid) return false;
		if (num != _o_.num) return false;
		if (expiretime != _o_.expiretime) return false;
		if (anneallevel != _o_.anneallevel) return false;
		if (perfuselevel != _o_.perfuselevel) return false;
		if (!accessorymainprop.equals(_o_.accessorymainprop)) return false;
		if (!accessoryviceprop.equals(_o_.accessoryviceprop)) return false;
		if (unshelvetime != _o_.unshelvetime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += id;
		_h_ += owner;
		_h_ += price;
		_h_ += modelid;
		_h_ += num;
		_h_ += expiretime;
		_h_ += anneallevel;
		_h_ += perfuselevel;
		_h_ += accessorymainprop.hashCode();
		_h_ += accessoryviceprop.hashCode();
		_h_ += unshelvetime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id);
		_sb_.append(",");
		_sb_.append(owner);
		_sb_.append(",");
		_sb_.append(price);
		_sb_.append(",");
		_sb_.append(modelid);
		_sb_.append(",");
		_sb_.append(num);
		_sb_.append(",");
		_sb_.append(expiretime);
		_sb_.append(",");
		_sb_.append(anneallevel);
		_sb_.append(",");
		_sb_.append(perfuselevel);
		_sb_.append(",");
		_sb_.append(accessorymainprop);
		_sb_.append(",");
		_sb_.append(accessoryviceprop);
		_sb_.append(",");
		_sb_.append(unshelvetime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("id"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("owner"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("price"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("modelid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("num"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("expiretime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("anneallevel"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("perfuselevel"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("accessorymainprop"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("accessoryviceprop"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("unshelvetime"));
		return lb;
	}

	private class Const implements xbean.ExchangeItem {
		ExchangeItem nThis() {
			return ExchangeItem.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ExchangeItem copy() {
			return ExchangeItem.this.copy();
		}

		@Override
		public xbean.ExchangeItem toData() {
			return ExchangeItem.this.toData();
		}

		public xbean.ExchangeItem toBean() {
			return ExchangeItem.this.toBean();
		}

		@Override
		public xbean.ExchangeItem toDataIf() {
			return ExchangeItem.this.toDataIf();
		}

		public xbean.ExchangeItem toBeanIf() {
			return ExchangeItem.this.toBeanIf();
		}

		@Override
		public long getId() { // 
			_xdb_verify_unsafe_();
			return id;
		}

		@Override
		public long getOwner() { // 
			_xdb_verify_unsafe_();
			return owner;
		}

		@Override
		public int getPrice() { // 单价
			_xdb_verify_unsafe_();
			return price;
		}

		@Override
		public int getModelid() { // 
			_xdb_verify_unsafe_();
			return modelid;
		}

		@Override
		public int getNum() { // 
			_xdb_verify_unsafe_();
			return num;
		}

		@Override
		public long getExpiretime() { // 
			_xdb_verify_unsafe_();
			return expiretime;
		}

		@Override
		public int getAnneallevel() { // 
			_xdb_verify_unsafe_();
			return anneallevel;
		}

		@Override
		public int getPerfuselevel() { // 
			_xdb_verify_unsafe_();
			return perfuselevel;
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getAccessorymainprop() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(accessorymainprop);
		}

		public java.util.List<xbean.AccessoryProp> getAccessorymainpropAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<xbean.AccessoryProp> accessorymainprop;
			ExchangeItem _o_ = ExchangeItem.this;
		accessorymainprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.accessorymainprop)
			accessorymainprop.add(new AccessoryProp.Data(_v_));
			return accessorymainprop;
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getAccessoryviceprop() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(accessoryviceprop);
		}

		public java.util.List<xbean.AccessoryProp> getAccessoryvicepropAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<xbean.AccessoryProp> accessoryviceprop;
			ExchangeItem _o_ = ExchangeItem.this;
		accessoryviceprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.accessoryviceprop)
			accessoryviceprop.add(new AccessoryProp.Data(_v_));
			return accessoryviceprop;
		}

		@Override
		public long getUnshelvetime() { // 下架时间
			_xdb_verify_unsafe_();
			return unshelvetime;
		}

		@Override
		public void setId(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setOwner(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPrice(int _v_) { // 单价
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setModelid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNum(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExpiretime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAnneallevel(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPerfuselevel(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUnshelvetime(long _v_) { // 下架时间
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
			return ExchangeItem.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ExchangeItem.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ExchangeItem.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ExchangeItem.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ExchangeItem.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ExchangeItem.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ExchangeItem.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ExchangeItem.this.hashCode();
		}

		@Override
		public String toString() {
			return ExchangeItem.this.toString();
		}

	}

	public static final class Data implements xbean.ExchangeItem {
		private long id; // 
		private long owner; // 
		private int price; // 单价
		private int modelid; // 
		private int num; // 
		private long expiretime; // 
		private int anneallevel; // 
		private int perfuselevel; // 
		private java.util.LinkedList<xbean.AccessoryProp> accessorymainprop; // 
		private java.util.LinkedList<xbean.AccessoryProp> accessoryviceprop; // 
		private long unshelvetime; // 下架时间

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			accessorymainprop = new java.util.LinkedList<xbean.AccessoryProp>();
			accessoryviceprop = new java.util.LinkedList<xbean.AccessoryProp>();
		}

		Data(xbean.ExchangeItem _o1_) {
			if (_o1_ instanceof ExchangeItem) assign((ExchangeItem)_o1_);
			else if (_o1_ instanceof ExchangeItem.Data) assign((ExchangeItem.Data)_o1_);
			else if (_o1_ instanceof ExchangeItem.Const) assign(((ExchangeItem.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ExchangeItem _o_) {
			id = _o_.id;
			owner = _o_.owner;
			price = _o_.price;
			modelid = _o_.modelid;
			num = _o_.num;
			expiretime = _o_.expiretime;
			anneallevel = _o_.anneallevel;
			perfuselevel = _o_.perfuselevel;
			accessorymainprop = new java.util.LinkedList<xbean.AccessoryProp>();
			for (xbean.AccessoryProp _v_ : _o_.accessorymainprop)
				accessorymainprop.add(new AccessoryProp.Data(_v_));
			accessoryviceprop = new java.util.LinkedList<xbean.AccessoryProp>();
			for (xbean.AccessoryProp _v_ : _o_.accessoryviceprop)
				accessoryviceprop.add(new AccessoryProp.Data(_v_));
			unshelvetime = _o_.unshelvetime;
		}

		private void assign(ExchangeItem.Data _o_) {
			id = _o_.id;
			owner = _o_.owner;
			price = _o_.price;
			modelid = _o_.modelid;
			num = _o_.num;
			expiretime = _o_.expiretime;
			anneallevel = _o_.anneallevel;
			perfuselevel = _o_.perfuselevel;
			accessorymainprop = new java.util.LinkedList<xbean.AccessoryProp>();
			for (xbean.AccessoryProp _v_ : _o_.accessorymainprop)
				accessorymainprop.add(new AccessoryProp.Data(_v_));
			accessoryviceprop = new java.util.LinkedList<xbean.AccessoryProp>();
			for (xbean.AccessoryProp _v_ : _o_.accessoryviceprop)
				accessoryviceprop.add(new AccessoryProp.Data(_v_));
			unshelvetime = _o_.unshelvetime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)11);
	_os_.marshal((short)(10240|  1));_os_.marshal(id);
	_os_.marshal((short)(10240|  2));_os_.marshal(owner);
	_os_.marshal((short)( 8192|  3));_os_.marshal(price);
	_os_.marshal((short)( 8192|  4));_os_.marshal(modelid);
	_os_.marshal((short)( 8192|  5));_os_.marshal(num);
	_os_.marshal((short)(10240|  7));_os_.marshal(expiretime);
	_os_.marshal((short)( 8192|  8));_os_.marshal(anneallevel);
	_os_.marshal((short)( 8192|  9));_os_.marshal(perfuselevel);
	_os_.marshal((short)(22528| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(accessorymainprop.size());
for (xbean.AccessoryProp _v_ : accessorymainprop) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528| 11));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(accessoryviceprop.size());
for (xbean.AccessoryProp _v_ : accessoryviceprop) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240| 12));_os_.marshal(unshelvetime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):id = _os_.unmarshal_long();
					break;
					case ( 6144|  1):id = _os_.unmarshal_short();
					break;
					case ( 8192|  1):id = _os_.unmarshal_int();
					break;
					case (10240|  2):owner = _os_.unmarshal_long();
					break;
					case ( 6144|  2):owner = _os_.unmarshal_short();
					break;
					case ( 8192|  2):owner = _os_.unmarshal_int();
					break;
					case ( 8192|  3):price = _os_.unmarshal_int();
					break;
					case ( 6144|  3):price = _os_.unmarshal_short();
					break;
					case ( 8192|  4):modelid = _os_.unmarshal_int();
					break;
					case ( 6144|  4):modelid = _os_.unmarshal_short();
					break;
					case ( 8192|  5):num = _os_.unmarshal_int();
					break;
					case ( 6144|  5):num = _os_.unmarshal_short();
					break;
					case (10240|  7):expiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  7):expiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  7):expiretime = _os_.unmarshal_int();
					break;
					case ( 8192|  8):anneallevel = _os_.unmarshal_int();
					break;
					case ( 6144|  8):anneallevel = _os_.unmarshal_short();
					break;
					case ( 8192|  9):perfuselevel = _os_.unmarshal_int();
					break;
					case ( 6144|  9):perfuselevel = _os_.unmarshal_short();
					break;
					case (22528| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.AccessoryProp _v_ = xbean.Pod.newAccessoryPropData();
	_v_.unmarshal(_os_);
	accessorymainprop.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528| 11):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.AccessoryProp _v_ = xbean.Pod.newAccessoryPropData();
	_v_.unmarshal(_os_);
	accessoryviceprop.add(_v_);
}
_os_ = _temp_;}
					break;
					case (10240| 12):unshelvetime = _os_.unmarshal_long();
					break;
					case ( 6144| 12):unshelvetime = _os_.unmarshal_short();
					break;
					case ( 8192| 12):unshelvetime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ExchangeItem copy() {
			return new Data(this);
		}

		@Override
		public xbean.ExchangeItem toData() {
			return new Data(this);
		}

		public xbean.ExchangeItem toBean() {
			return new ExchangeItem(this, null, null);
		}

		@Override
		public xbean.ExchangeItem toDataIf() {
			return this;
		}

		public xbean.ExchangeItem toBeanIf() {
			return new ExchangeItem(this, null, null);
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
		public long getId() { // 
			return id;
		}

		@Override
		public long getOwner() { // 
			return owner;
		}

		@Override
		public int getPrice() { // 单价
			return price;
		}

		@Override
		public int getModelid() { // 
			return modelid;
		}

		@Override
		public int getNum() { // 
			return num;
		}

		@Override
		public long getExpiretime() { // 
			return expiretime;
		}

		@Override
		public int getAnneallevel() { // 
			return anneallevel;
		}

		@Override
		public int getPerfuselevel() { // 
			return perfuselevel;
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getAccessorymainprop() { // 
			return accessorymainprop;
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getAccessorymainpropAsData() { // 
			return accessorymainprop;
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getAccessoryviceprop() { // 
			return accessoryviceprop;
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getAccessoryvicepropAsData() { // 
			return accessoryviceprop;
		}

		@Override
		public long getUnshelvetime() { // 下架时间
			return unshelvetime;
		}

		@Override
		public void setId(long _v_) { // 
			id = _v_;
		}

		@Override
		public void setOwner(long _v_) { // 
			owner = _v_;
		}

		@Override
		public void setPrice(int _v_) { // 单价
			price = _v_;
		}

		@Override
		public void setModelid(int _v_) { // 
			modelid = _v_;
		}

		@Override
		public void setNum(int _v_) { // 
			num = _v_;
		}

		@Override
		public void setExpiretime(long _v_) { // 
			expiretime = _v_;
		}

		@Override
		public void setAnneallevel(int _v_) { // 
			anneallevel = _v_;
		}

		@Override
		public void setPerfuselevel(int _v_) { // 
			perfuselevel = _v_;
		}

		@Override
		public void setUnshelvetime(long _v_) { // 下架时间
			unshelvetime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ExchangeItem.Data)) return false;
			ExchangeItem.Data _o_ = (ExchangeItem.Data) _o1_;
			if (id != _o_.id) return false;
			if (owner != _o_.owner) return false;
			if (price != _o_.price) return false;
			if (modelid != _o_.modelid) return false;
			if (num != _o_.num) return false;
			if (expiretime != _o_.expiretime) return false;
			if (anneallevel != _o_.anneallevel) return false;
			if (perfuselevel != _o_.perfuselevel) return false;
			if (!accessorymainprop.equals(_o_.accessorymainprop)) return false;
			if (!accessoryviceprop.equals(_o_.accessoryviceprop)) return false;
			if (unshelvetime != _o_.unshelvetime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += id;
			_h_ += owner;
			_h_ += price;
			_h_ += modelid;
			_h_ += num;
			_h_ += expiretime;
			_h_ += anneallevel;
			_h_ += perfuselevel;
			_h_ += accessorymainprop.hashCode();
			_h_ += accessoryviceprop.hashCode();
			_h_ += unshelvetime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(id);
			_sb_.append(",");
			_sb_.append(owner);
			_sb_.append(",");
			_sb_.append(price);
			_sb_.append(",");
			_sb_.append(modelid);
			_sb_.append(",");
			_sb_.append(num);
			_sb_.append(",");
			_sb_.append(expiretime);
			_sb_.append(",");
			_sb_.append(anneallevel);
			_sb_.append(",");
			_sb_.append(perfuselevel);
			_sb_.append(",");
			_sb_.append(accessorymainprop);
			_sb_.append(",");
			_sb_.append(accessoryviceprop);
			_sb_.append(",");
			_sb_.append(unshelvetime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
