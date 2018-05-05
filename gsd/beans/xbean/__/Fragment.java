
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Fragment extends xdb.XBean implements xbean.Fragment {
	private long fragmentid; // 物品唯一id
	private int modelid; // 物品model id
	private int count; // 物品数量
	private boolean isbind; // 是否绑定
	private int position; // 在包裹中的位置
	private long expiretime; // 过期时间

	@Override
	public void _reset_unsafe_() {
		fragmentid = 0L;
		modelid = 0;
		count = 0;
		isbind = false;
		position = 0;
		expiretime = 0L;
	}

	Fragment(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public Fragment() {
		this(0, null, null);
	}

	public Fragment(Fragment _o_) {
		this(_o_, null, null);
	}

	Fragment(xbean.Fragment _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Fragment) assign((Fragment)_o1_);
		else if (_o1_ instanceof Fragment.Data) assign((Fragment.Data)_o1_);
		else if (_o1_ instanceof Fragment.Const) assign(((Fragment.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Fragment _o_) {
		_o_._xdb_verify_unsafe_();
		fragmentid = _o_.fragmentid;
		modelid = _o_.modelid;
		count = _o_.count;
		isbind = _o_.isbind;
		position = _o_.position;
		expiretime = _o_.expiretime;
	}

	private void assign(Fragment.Data _o_) {
		fragmentid = _o_.fragmentid;
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
    _os_.marshal((short)(10240|  1));_os_.marshal(fragmentid);
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
    				case (10240|  1):fragmentid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):fragmentid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):fragmentid = _os_.unmarshal_int();
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
	public xbean.Fragment copy() {
		_xdb_verify_unsafe_();
		return new Fragment(this);
	}

	@Override
	public xbean.Fragment toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Fragment toBean() {
		_xdb_verify_unsafe_();
		return new Fragment(this); // same as copy()
	}

	@Override
	public xbean.Fragment toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Fragment toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getFragmentid() { // 物品唯一id
		_xdb_verify_unsafe_();
		return fragmentid;
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
	public void setFragmentid(long _v_) { // 物品唯一id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "fragmentid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, fragmentid) {
					public void rollback() { fragmentid = _xdb_saved; }
				};}});
		fragmentid = _v_;
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
		Fragment _o_ = null;
		if ( _o1_ instanceof Fragment ) _o_ = (Fragment)_o1_;
		else if ( _o1_ instanceof Fragment.Const ) _o_ = ((Fragment.Const)_o1_).nThis();
		else return false;
		if (fragmentid != _o_.fragmentid) return false;
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
		_h_ += fragmentid;
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
		_sb_.append(fragmentid);
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
		lb.add(new xdb.logs.ListenableChanged().setVarName("fragmentid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("modelid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("count"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isbind"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("position"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("expiretime"));
		return lb;
	}

	private class Const implements xbean.Fragment {
		Fragment nThis() {
			return Fragment.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Fragment copy() {
			return Fragment.this.copy();
		}

		@Override
		public xbean.Fragment toData() {
			return Fragment.this.toData();
		}

		public xbean.Fragment toBean() {
			return Fragment.this.toBean();
		}

		@Override
		public xbean.Fragment toDataIf() {
			return Fragment.this.toDataIf();
		}

		public xbean.Fragment toBeanIf() {
			return Fragment.this.toBeanIf();
		}

		@Override
		public long getFragmentid() { // 物品唯一id
			_xdb_verify_unsafe_();
			return fragmentid;
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
		public void setFragmentid(long _v_) { // 物品唯一id
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
			return Fragment.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Fragment.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Fragment.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Fragment.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Fragment.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Fragment.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Fragment.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Fragment.this.hashCode();
		}

		@Override
		public String toString() {
			return Fragment.this.toString();
		}

	}

	public static final class Data implements xbean.Fragment {
		private long fragmentid; // 物品唯一id
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

		Data(xbean.Fragment _o1_) {
			if (_o1_ instanceof Fragment) assign((Fragment)_o1_);
			else if (_o1_ instanceof Fragment.Data) assign((Fragment.Data)_o1_);
			else if (_o1_ instanceof Fragment.Const) assign(((Fragment.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Fragment _o_) {
			fragmentid = _o_.fragmentid;
			modelid = _o_.modelid;
			count = _o_.count;
			isbind = _o_.isbind;
			position = _o_.position;
			expiretime = _o_.expiretime;
		}

		private void assign(Fragment.Data _o_) {
			fragmentid = _o_.fragmentid;
			modelid = _o_.modelid;
			count = _o_.count;
			isbind = _o_.isbind;
			position = _o_.position;
			expiretime = _o_.expiretime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)(10240|  1));_os_.marshal(fragmentid);
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
					case (10240|  1):fragmentid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):fragmentid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):fragmentid = _os_.unmarshal_int();
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
		public xbean.Fragment copy() {
			return new Data(this);
		}

		@Override
		public xbean.Fragment toData() {
			return new Data(this);
		}

		public xbean.Fragment toBean() {
			return new Fragment(this, null, null);
		}

		@Override
		public xbean.Fragment toDataIf() {
			return this;
		}

		public xbean.Fragment toBeanIf() {
			return new Fragment(this, null, null);
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
		public long getFragmentid() { // 物品唯一id
			return fragmentid;
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
		public void setFragmentid(long _v_) { // 物品唯一id
			fragmentid = _v_;
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
			if (!(_o1_ instanceof Fragment.Data)) return false;
			Fragment.Data _o_ = (Fragment.Data) _o1_;
			if (fragmentid != _o_.fragmentid) return false;
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
			_h_ += fragmentid;
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
			_sb_.append(fragmentid);
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
