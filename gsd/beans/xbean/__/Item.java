
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Item extends xdb.XBean implements xbean.Item {
	private long itemid; // 物品唯一id
	private int modelid; // 物品model id
	private int count; // 物品数量
	private boolean isbind; // 是否绑定
	private int position; // 在包裹中的位置
	private long expiretime; // 过期时间

	@Override
	public void _reset_unsafe_() {
		itemid = 0L;
		modelid = 0;
		count = 0;
		isbind = false;
		position = 0;
		expiretime = 0L;
	}

	Item(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public Item() {
		this(0, null, null);
	}

	public Item(Item _o_) {
		this(_o_, null, null);
	}

	Item(xbean.Item _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Item) assign((Item)_o1_);
		else if (_o1_ instanceof Item.Data) assign((Item.Data)_o1_);
		else if (_o1_ instanceof Item.Const) assign(((Item.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Item _o_) {
		_o_._xdb_verify_unsafe_();
		itemid = _o_.itemid;
		modelid = _o_.modelid;
		count = _o_.count;
		isbind = _o_.isbind;
		position = _o_.position;
		expiretime = _o_.expiretime;
	}

	private void assign(Item.Data _o_) {
		itemid = _o_.itemid;
		modelid = _o_.modelid;
		count = _o_.count;
		isbind = _o_.isbind;
		position = _o_.position;
		expiretime = _o_.expiretime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)(10240|  1));_os_.marshal(itemid);
    _os_.marshal((short)( 8192|  2));_os_.marshal(modelid);
    _os_.marshal((short)( 8192|  3));_os_.marshal(count);
    _os_.marshal((short)( 2048|  4));_os_.marshal(isbind);
    _os_.marshal((short)( 8192|  5));_os_.marshal(position);
    _os_.marshal((short)(10240|  6));_os_.marshal(expiretime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):itemid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):itemid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):itemid = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):modelid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):modelid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):count = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):count = _os_.unmarshal_short();
    				break;
    				case ( 2048|  4):isbind = _os_.unmarshal_boolean();
    				break;
    				case ( 8192|  5):position = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):position = _os_.unmarshal_short();
    				break;
    				case (10240|  6):expiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  6):expiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):expiretime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Item copy() {
		_xdb_verify_unsafe_();
		return new Item(this);
	}

	@Override
	public xbean.Item toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Item toBean() {
		_xdb_verify_unsafe_();
		return new Item(this); // same as copy()
	}

	@Override
	public xbean.Item toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Item toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getItemid() { // 物品唯一id
		_xdb_verify_unsafe_();
		return itemid;
	}

	@Override
	public int getModelid() { // 物品model id
		_xdb_verify_unsafe_();
		return modelid;
	}

	@Override
	public int getCount() { // 物品数量
		_xdb_verify_unsafe_();
		return count;
	}

	@Override
	public boolean getIsbind() { // 是否绑定
		_xdb_verify_unsafe_();
		return isbind;
	}

	@Override
	public int getPosition() { // 在包裹中的位置
		_xdb_verify_unsafe_();
		return position;
	}

	@Override
	public long getExpiretime() { // 过期时间
		_xdb_verify_unsafe_();
		return expiretime;
	}

	@Override
	public void setItemid(long _v_) { // 物品唯一id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "itemid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, itemid) {
					public void rollback() { itemid = _xdb_saved; }
				};}});
		itemid = _v_;
	}

	@Override
	public void setModelid(int _v_) { // 物品model id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "modelid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, modelid) {
					public void rollback() { modelid = _xdb_saved; }
				};}});
		modelid = _v_;
	}

	@Override
	public void setCount(int _v_) { // 物品数量
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "count") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, count) {
					public void rollback() { count = _xdb_saved; }
				};}});
		count = _v_;
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
	public void setPosition(int _v_) { // 在包裹中的位置
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
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Item _o_ = null;
		if ( _o1_ instanceof Item ) _o_ = (Item)_o1_;
		else if ( _o1_ instanceof Item.Const ) _o_ = ((Item.Const)_o1_).nThis();
		else return false;
		if (itemid != _o_.itemid) return false;
		if (modelid != _o_.modelid) return false;
		if (count != _o_.count) return false;
		if (isbind != _o_.isbind) return false;
		if (position != _o_.position) return false;
		if (expiretime != _o_.expiretime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += itemid;
		_h_ += modelid;
		_h_ += count;
		_h_ += isbind ? 1231 : 1237;
		_h_ += position;
		_h_ += expiretime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(itemid);
		_sb_.append(",");
		_sb_.append(modelid);
		_sb_.append(",");
		_sb_.append(count);
		_sb_.append(",");
		_sb_.append(isbind);
		_sb_.append(",");
		_sb_.append(position);
		_sb_.append(",");
		_sb_.append(expiretime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("itemid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("modelid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("count"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isbind"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("position"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("expiretime"));
		return lb;
	}

	private class Const implements xbean.Item {
		Item nThis() {
			return Item.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Item copy() {
			return Item.this.copy();
		}

		@Override
		public xbean.Item toData() {
			return Item.this.toData();
		}

		public xbean.Item toBean() {
			return Item.this.toBean();
		}

		@Override
		public xbean.Item toDataIf() {
			return Item.this.toDataIf();
		}

		public xbean.Item toBeanIf() {
			return Item.this.toBeanIf();
		}

		@Override
		public long getItemid() { // 物品唯一id
			_xdb_verify_unsafe_();
			return itemid;
		}

		@Override
		public int getModelid() { // 物品model id
			_xdb_verify_unsafe_();
			return modelid;
		}

		@Override
		public int getCount() { // 物品数量
			_xdb_verify_unsafe_();
			return count;
		}

		@Override
		public boolean getIsbind() { // 是否绑定
			_xdb_verify_unsafe_();
			return isbind;
		}

		@Override
		public int getPosition() { // 在包裹中的位置
			_xdb_verify_unsafe_();
			return position;
		}

		@Override
		public long getExpiretime() { // 过期时间
			_xdb_verify_unsafe_();
			return expiretime;
		}

		@Override
		public void setItemid(long _v_) { // 物品唯一id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setModelid(int _v_) { // 物品model id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCount(int _v_) { // 物品数量
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsbind(boolean _v_) { // 是否绑定
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPosition(int _v_) { // 在包裹中的位置
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExpiretime(long _v_) { // 过期时间
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
			return Item.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Item.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Item.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Item.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Item.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Item.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Item.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Item.this.hashCode();
		}

		@Override
		public String toString() {
			return Item.this.toString();
		}

	}

	public static final class Data implements xbean.Item {
		private long itemid; // 物品唯一id
		private int modelid; // 物品model id
		private int count; // 物品数量
		private boolean isbind; // 是否绑定
		private int position; // 在包裹中的位置
		private long expiretime; // 过期时间

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.Item _o1_) {
			if (_o1_ instanceof Item) assign((Item)_o1_);
			else if (_o1_ instanceof Item.Data) assign((Item.Data)_o1_);
			else if (_o1_ instanceof Item.Const) assign(((Item.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Item _o_) {
			itemid = _o_.itemid;
			modelid = _o_.modelid;
			count = _o_.count;
			isbind = _o_.isbind;
			position = _o_.position;
			expiretime = _o_.expiretime;
		}

		private void assign(Item.Data _o_) {
			itemid = _o_.itemid;
			modelid = _o_.modelid;
			count = _o_.count;
			isbind = _o_.isbind;
			position = _o_.position;
			expiretime = _o_.expiretime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)(10240|  1));_os_.marshal(itemid);
	_os_.marshal((short)( 8192|  2));_os_.marshal(modelid);
	_os_.marshal((short)( 8192|  3));_os_.marshal(count);
	_os_.marshal((short)( 2048|  4));_os_.marshal(isbind);
	_os_.marshal((short)( 8192|  5));_os_.marshal(position);
	_os_.marshal((short)(10240|  6));_os_.marshal(expiretime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):itemid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):itemid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):itemid = _os_.unmarshal_int();
					break;
					case ( 8192|  2):modelid = _os_.unmarshal_int();
					break;
					case ( 6144|  2):modelid = _os_.unmarshal_short();
					break;
					case ( 8192|  3):count = _os_.unmarshal_int();
					break;
					case ( 6144|  3):count = _os_.unmarshal_short();
					break;
					case ( 2048|  4):isbind = _os_.unmarshal_boolean();
					break;
					case ( 8192|  5):position = _os_.unmarshal_int();
					break;
					case ( 6144|  5):position = _os_.unmarshal_short();
					break;
					case (10240|  6):expiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  6):expiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  6):expiretime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Item copy() {
			return new Data(this);
		}

		@Override
		public xbean.Item toData() {
			return new Data(this);
		}

		public xbean.Item toBean() {
			return new Item(this, null, null);
		}

		@Override
		public xbean.Item toDataIf() {
			return this;
		}

		public xbean.Item toBeanIf() {
			return new Item(this, null, null);
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
		public long getItemid() { // 物品唯一id
			return itemid;
		}

		@Override
		public int getModelid() { // 物品model id
			return modelid;
		}

		@Override
		public int getCount() { // 物品数量
			return count;
		}

		@Override
		public boolean getIsbind() { // 是否绑定
			return isbind;
		}

		@Override
		public int getPosition() { // 在包裹中的位置
			return position;
		}

		@Override
		public long getExpiretime() { // 过期时间
			return expiretime;
		}

		@Override
		public void setItemid(long _v_) { // 物品唯一id
			itemid = _v_;
		}

		@Override
		public void setModelid(int _v_) { // 物品model id
			modelid = _v_;
		}

		@Override
		public void setCount(int _v_) { // 物品数量
			count = _v_;
		}

		@Override
		public void setIsbind(boolean _v_) { // 是否绑定
			isbind = _v_;
		}

		@Override
		public void setPosition(int _v_) { // 在包裹中的位置
			position = _v_;
		}

		@Override
		public void setExpiretime(long _v_) { // 过期时间
			expiretime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Item.Data)) return false;
			Item.Data _o_ = (Item.Data) _o1_;
			if (itemid != _o_.itemid) return false;
			if (modelid != _o_.modelid) return false;
			if (count != _o_.count) return false;
			if (isbind != _o_.isbind) return false;
			if (position != _o_.position) return false;
			if (expiretime != _o_.expiretime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += itemid;
			_h_ += modelid;
			_h_ += count;
			_h_ += isbind ? 1231 : 1237;
			_h_ += position;
			_h_ += expiretime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(itemid);
			_sb_.append(",");
			_sb_.append(modelid);
			_sb_.append(",");
			_sb_.append(count);
			_sb_.append(",");
			_sb_.append(isbind);
			_sb_.append(",");
			_sb_.append(position);
			_sb_.append(",");
			_sb_.append(expiretime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
