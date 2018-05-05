
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Equip extends xdb.XBean implements xbean.Equip {
	private long equipid; // 装备id
	private int modelid; // model id
	private int position; // 包裹中的位置
	private long expiretime; // 过期时间
	private boolean isbind; // 是否绑定
	private xbean.NormalEquip normalequip; // 普通装备的属性
	private xbean.Accessory accessory; // 饰品类装备的属性

	@Override
	public void _reset_unsafe_() {
		equipid = 0L;
		modelid = 0;
		position = 0;
		expiretime = 0L;
		isbind = false;
		normalequip._reset_unsafe_();
		accessory._reset_unsafe_();
	}

	Equip(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		normalequip = new NormalEquip(0, this, "normalequip");
		accessory = new Accessory(0, this, "accessory");
	}

	public Equip() {
		this(0, null, null);
	}

	public Equip(Equip _o_) {
		this(_o_, null, null);
	}

	Equip(xbean.Equip _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Equip) assign((Equip)_o1_);
		else if (_o1_ instanceof Equip.Data) assign((Equip.Data)_o1_);
		else if (_o1_ instanceof Equip.Const) assign(((Equip.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Equip _o_) {
		_o_._xdb_verify_unsafe_();
		equipid = _o_.equipid;
		modelid = _o_.modelid;
		position = _o_.position;
		expiretime = _o_.expiretime;
		isbind = _o_.isbind;
		normalequip = new NormalEquip(_o_.normalequip, this, "normalequip");
		accessory = new Accessory(_o_.accessory, this, "accessory");
	}

	private void assign(Equip.Data _o_) {
		equipid = _o_.equipid;
		modelid = _o_.modelid;
		position = _o_.position;
		expiretime = _o_.expiretime;
		isbind = _o_.isbind;
		normalequip = new NormalEquip(_o_.normalequip, this, "normalequip");
		accessory = new Accessory(_o_.accessory, this, "accessory");
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)7);
    _os_.marshal((short)(10240|  1));_os_.marshal(equipid);
    _os_.marshal((short)( 8192|  2));_os_.marshal(modelid);
    _os_.marshal((short)( 8192|  3));_os_.marshal(position);
    _os_.marshal((short)(10240|  4));_os_.marshal(expiretime);
    _os_.marshal((short)( 2048|  5));_os_.marshal(isbind);
    _os_.marshal((short)(26624|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
normalequip.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(26624|  8));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
accessory.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):equipid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):equipid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):equipid = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):modelid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):modelid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):position = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):position = _os_.unmarshal_short();
    				break;
    				case (10240|  4):expiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):expiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):expiretime = _os_.unmarshal_int();
    				break;
    				case ( 2048|  5):isbind = _os_.unmarshal_boolean();
    				break;
    				case (26624|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
normalequip.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case (26624|  8):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
accessory.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Equip copy() {
		_xdb_verify_unsafe_();
		return new Equip(this);
	}

	@Override
	public xbean.Equip toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Equip toBean() {
		_xdb_verify_unsafe_();
		return new Equip(this); // same as copy()
	}

	@Override
	public xbean.Equip toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Equip toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getEquipid() { // 装备id
		_xdb_verify_unsafe_();
		return equipid;
	}

	@Override
	public int getModelid() { // model id
		_xdb_verify_unsafe_();
		return modelid;
	}

	@Override
	public int getPosition() { // 包裹中的位置
		_xdb_verify_unsafe_();
		return position;
	}

	@Override
	public long getExpiretime() { // 过期时间
		_xdb_verify_unsafe_();
		return expiretime;
	}

	@Override
	public boolean getIsbind() { // 是否绑定
		_xdb_verify_unsafe_();
		return isbind;
	}

	@Override
	public xbean.NormalEquip getNormalequip() { // 普通装备的属性
		_xdb_verify_unsafe_();
		return normalequip;
	}

	@Override
	public xbean.Accessory getAccessory() { // 饰品类装备的属性
		_xdb_verify_unsafe_();
		return accessory;
	}

	@Override
	public void setEquipid(long _v_) { // 装备id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "equipid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, equipid) {
					public void rollback() { equipid = _xdb_saved; }
				};}});
		equipid = _v_;
	}

	@Override
	public void setModelid(int _v_) { // model id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "modelid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, modelid) {
					public void rollback() { modelid = _xdb_saved; }
				};}});
		modelid = _v_;
	}

	@Override
	public void setPosition(int _v_) { // 包裹中的位置
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "position") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, position) {
					public void rollback() { position = _xdb_saved; }
				};}});
		position = _v_;
	}

	@Override
	public void setExpiretime(long _v_) { // 过期时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "expiretime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, expiretime) {
					public void rollback() { expiretime = _xdb_saved; }
				};}});
		expiretime = _v_;
	}

	@Override
	public void setIsbind(boolean _v_) { // 是否绑定
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "isbind") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, isbind) {
					public void rollback() { isbind = _xdb_saved; }
				};}});
		isbind = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Equip _o_ = null;
		if ( _o1_ instanceof Equip ) _o_ = (Equip)_o1_;
		else if ( _o1_ instanceof Equip.Const ) _o_ = ((Equip.Const)_o1_).nThis();
		else return false;
		if (equipid != _o_.equipid) return false;
		if (modelid != _o_.modelid) return false;
		if (position != _o_.position) return false;
		if (expiretime != _o_.expiretime) return false;
		if (isbind != _o_.isbind) return false;
		if (!normalequip.equals(_o_.normalequip)) return false;
		if (!accessory.equals(_o_.accessory)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += equipid;
		_h_ += modelid;
		_h_ += position;
		_h_ += expiretime;
		_h_ += isbind ? 1231 : 1237;
		_h_ += normalequip.hashCode();
		_h_ += accessory.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(equipid);
		_sb_.append(",");
		_sb_.append(modelid);
		_sb_.append(",");
		_sb_.append(position);
		_sb_.append(",");
		_sb_.append(expiretime);
		_sb_.append(",");
		_sb_.append(isbind);
		_sb_.append(",");
		_sb_.append(normalequip);
		_sb_.append(",");
		_sb_.append(accessory);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("equipid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("modelid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("position"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("expiretime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isbind"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("normalequip"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("accessory"));
		return lb;
	}

	private class Const implements xbean.Equip {
		Equip nThis() {
			return Equip.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Equip copy() {
			return Equip.this.copy();
		}

		@Override
		public xbean.Equip toData() {
			return Equip.this.toData();
		}

		public xbean.Equip toBean() {
			return Equip.this.toBean();
		}

		@Override
		public xbean.Equip toDataIf() {
			return Equip.this.toDataIf();
		}

		public xbean.Equip toBeanIf() {
			return Equip.this.toBeanIf();
		}

		@Override
		public long getEquipid() { // 装备id
			_xdb_verify_unsafe_();
			return equipid;
		}

		@Override
		public int getModelid() { // model id
			_xdb_verify_unsafe_();
			return modelid;
		}

		@Override
		public int getPosition() { // 包裹中的位置
			_xdb_verify_unsafe_();
			return position;
		}

		@Override
		public long getExpiretime() { // 过期时间
			_xdb_verify_unsafe_();
			return expiretime;
		}

		@Override
		public boolean getIsbind() { // 是否绑定
			_xdb_verify_unsafe_();
			return isbind;
		}

		@Override
		public xbean.NormalEquip getNormalequip() { // 普通装备的属性
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(normalequip);
		}

		@Override
		public xbean.Accessory getAccessory() { // 饰品类装备的属性
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(accessory);
		}

		@Override
		public void setEquipid(long _v_) { // 装备id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setModelid(int _v_) { // model id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPosition(int _v_) { // 包裹中的位置
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExpiretime(long _v_) { // 过期时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsbind(boolean _v_) { // 是否绑定
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
			return Equip.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Equip.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Equip.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Equip.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Equip.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Equip.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Equip.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Equip.this.hashCode();
		}

		@Override
		public String toString() {
			return Equip.this.toString();
		}

	}

	public static final class Data implements xbean.Equip {
		private long equipid; // 装备id
		private int modelid; // model id
		private int position; // 包裹中的位置
		private long expiretime; // 过期时间
		private boolean isbind; // 是否绑定
		private xbean.NormalEquip normalequip; // 普通装备的属性
		private xbean.Accessory accessory; // 饰品类装备的属性

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			normalequip = new NormalEquip.Data();
			accessory = new Accessory.Data();
		}

		Data(xbean.Equip _o1_) {
			if (_o1_ instanceof Equip) assign((Equip)_o1_);
			else if (_o1_ instanceof Equip.Data) assign((Equip.Data)_o1_);
			else if (_o1_ instanceof Equip.Const) assign(((Equip.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Equip _o_) {
			equipid = _o_.equipid;
			modelid = _o_.modelid;
			position = _o_.position;
			expiretime = _o_.expiretime;
			isbind = _o_.isbind;
			normalequip = new NormalEquip.Data(_o_.normalequip);
			accessory = new Accessory.Data(_o_.accessory);
		}

		private void assign(Equip.Data _o_) {
			equipid = _o_.equipid;
			modelid = _o_.modelid;
			position = _o_.position;
			expiretime = _o_.expiretime;
			isbind = _o_.isbind;
			normalequip = new NormalEquip.Data(_o_.normalequip);
			accessory = new Accessory.Data(_o_.accessory);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)7);
	_os_.marshal((short)(10240|  1));_os_.marshal(equipid);
	_os_.marshal((short)( 8192|  2));_os_.marshal(modelid);
	_os_.marshal((short)( 8192|  3));_os_.marshal(position);
	_os_.marshal((short)(10240|  4));_os_.marshal(expiretime);
	_os_.marshal((short)( 2048|  5));_os_.marshal(isbind);
	_os_.marshal((short)(26624|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
normalequip.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(26624|  8));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
accessory.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):equipid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):equipid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):equipid = _os_.unmarshal_int();
					break;
					case ( 8192|  2):modelid = _os_.unmarshal_int();
					break;
					case ( 6144|  2):modelid = _os_.unmarshal_short();
					break;
					case ( 8192|  3):position = _os_.unmarshal_int();
					break;
					case ( 6144|  3):position = _os_.unmarshal_short();
					break;
					case (10240|  4):expiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  4):expiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  4):expiretime = _os_.unmarshal_int();
					break;
					case ( 2048|  5):isbind = _os_.unmarshal_boolean();
					break;
					case (26624|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
normalequip.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case (26624|  8):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
accessory.unmarshal(_os_);
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Equip copy() {
			return new Data(this);
		}

		@Override
		public xbean.Equip toData() {
			return new Data(this);
		}

		public xbean.Equip toBean() {
			return new Equip(this, null, null);
		}

		@Override
		public xbean.Equip toDataIf() {
			return this;
		}

		public xbean.Equip toBeanIf() {
			return new Equip(this, null, null);
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
		public long getEquipid() { // 装备id
			return equipid;
		}

		@Override
		public int getModelid() { // model id
			return modelid;
		}

		@Override
		public int getPosition() { // 包裹中的位置
			return position;
		}

		@Override
		public long getExpiretime() { // 过期时间
			return expiretime;
		}

		@Override
		public boolean getIsbind() { // 是否绑定
			return isbind;
		}

		@Override
		public xbean.NormalEquip getNormalequip() { // 普通装备的属性
			return normalequip;
		}

		@Override
		public xbean.Accessory getAccessory() { // 饰品类装备的属性
			return accessory;
		}

		@Override
		public void setEquipid(long _v_) { // 装备id
			equipid = _v_;
		}

		@Override
		public void setModelid(int _v_) { // model id
			modelid = _v_;
		}

		@Override
		public void setPosition(int _v_) { // 包裹中的位置
			position = _v_;
		}

		@Override
		public void setExpiretime(long _v_) { // 过期时间
			expiretime = _v_;
		}

		@Override
		public void setIsbind(boolean _v_) { // 是否绑定
			isbind = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Equip.Data)) return false;
			Equip.Data _o_ = (Equip.Data) _o1_;
			if (equipid != _o_.equipid) return false;
			if (modelid != _o_.modelid) return false;
			if (position != _o_.position) return false;
			if (expiretime != _o_.expiretime) return false;
			if (isbind != _o_.isbind) return false;
			if (!normalequip.equals(_o_.normalequip)) return false;
			if (!accessory.equals(_o_.accessory)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += equipid;
			_h_ += modelid;
			_h_ += position;
			_h_ += expiretime;
			_h_ += isbind ? 1231 : 1237;
			_h_ += normalequip.hashCode();
			_h_ += accessory.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(equipid);
			_sb_.append(",");
			_sb_.append(modelid);
			_sb_.append(",");
			_sb_.append(position);
			_sb_.append(",");
			_sb_.append(expiretime);
			_sb_.append(",");
			_sb_.append(isbind);
			_sb_.append(",");
			_sb_.append(normalequip);
			_sb_.append(",");
			_sb_.append(accessory);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
