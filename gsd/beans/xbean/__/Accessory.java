
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Accessory extends xdb.XBean implements xbean.Accessory {
	private java.util.LinkedList<xbean.AccessoryProp> mainprop; // 主属性,目前是2个
	private java.util.LinkedList<xbean.AccessoryProp> extraprop; // 附加属性，目前是5个
	private xbean.AccessoryWashResult lastwashrecord; // 饰品洗练的临时结果,一次有效，成功应用或者取消后删除

	@Override
	public void _reset_unsafe_() {
		mainprop.clear();
		extraprop.clear();
		lastwashrecord._reset_unsafe_();
	}

	Accessory(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		mainprop = new java.util.LinkedList<xbean.AccessoryProp>();
		extraprop = new java.util.LinkedList<xbean.AccessoryProp>();
		lastwashrecord = new AccessoryWashResult(0, this, "lastwashrecord");
	}

	public Accessory() {
		this(0, null, null);
	}

	public Accessory(Accessory _o_) {
		this(_o_, null, null);
	}

	Accessory(xbean.Accessory _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Accessory) assign((Accessory)_o1_);
		else if (_o1_ instanceof Accessory.Data) assign((Accessory.Data)_o1_);
		else if (_o1_ instanceof Accessory.Const) assign(((Accessory.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Accessory _o_) {
		_o_._xdb_verify_unsafe_();
		mainprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.mainprop)
			mainprop.add(new AccessoryProp(_v_, this, "mainprop"));
		extraprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.extraprop)
			extraprop.add(new AccessoryProp(_v_, this, "extraprop"));
		lastwashrecord = new AccessoryWashResult(_o_.lastwashrecord, this, "lastwashrecord");
	}

	private void assign(Accessory.Data _o_) {
		mainprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.mainprop)
			mainprop.add(new AccessoryProp(_v_, this, "mainprop"));
		extraprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.extraprop)
			extraprop.add(new AccessoryProp(_v_, this, "extraprop"));
		lastwashrecord = new AccessoryWashResult(_o_.lastwashrecord, this, "lastwashrecord");
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(mainprop.size());
for (xbean.AccessoryProp _v_ : mainprop) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(extraprop.size());
for (xbean.AccessoryProp _v_ : extraprop) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(26624|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
lastwashrecord.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.AccessoryProp _v_ = new AccessoryProp(0, this, "mainprop");
	_v_.unmarshal(_os_);
	mainprop.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.AccessoryProp _v_ = new AccessoryProp(0, this, "extraprop");
	_v_.unmarshal(_os_);
	extraprop.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (26624|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
lastwashrecord.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Accessory copy() {
		_xdb_verify_unsafe_();
		return new Accessory(this);
	}

	@Override
	public xbean.Accessory toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Accessory toBean() {
		_xdb_verify_unsafe_();
		return new Accessory(this); // same as copy()
	}

	@Override
	public xbean.Accessory toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Accessory toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<xbean.AccessoryProp> getMainprop() { // 主属性,目前是2个
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "mainprop"), mainprop);
	}

	public java.util.List<xbean.AccessoryProp> getMainpropAsData() { // 主属性,目前是2个
		_xdb_verify_unsafe_();
		java.util.List<xbean.AccessoryProp> mainprop;
		Accessory _o_ = this;
		mainprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.mainprop)
			mainprop.add(new AccessoryProp.Data(_v_));
		return mainprop;
	}

	@Override
	public java.util.List<xbean.AccessoryProp> getExtraprop() { // 附加属性，目前是5个
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "extraprop"), extraprop);
	}

	public java.util.List<xbean.AccessoryProp> getExtrapropAsData() { // 附加属性，目前是5个
		_xdb_verify_unsafe_();
		java.util.List<xbean.AccessoryProp> extraprop;
		Accessory _o_ = this;
		extraprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.extraprop)
			extraprop.add(new AccessoryProp.Data(_v_));
		return extraprop;
	}

	@Override
	public xbean.AccessoryWashResult getLastwashrecord() { // 饰品洗练的临时结果,一次有效，成功应用或者取消后删除
		_xdb_verify_unsafe_();
		return lastwashrecord;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Accessory _o_ = null;
		if ( _o1_ instanceof Accessory ) _o_ = (Accessory)_o1_;
		else if ( _o1_ instanceof Accessory.Const ) _o_ = ((Accessory.Const)_o1_).nThis();
		else return false;
		if (!mainprop.equals(_o_.mainprop)) return false;
		if (!extraprop.equals(_o_.extraprop)) return false;
		if (!lastwashrecord.equals(_o_.lastwashrecord)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += mainprop.hashCode();
		_h_ += extraprop.hashCode();
		_h_ += lastwashrecord.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mainprop);
		_sb_.append(",");
		_sb_.append(extraprop);
		_sb_.append(",");
		_sb_.append(lastwashrecord);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("mainprop"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("extraprop"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastwashrecord"));
		return lb;
	}

	private class Const implements xbean.Accessory {
		Accessory nThis() {
			return Accessory.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Accessory copy() {
			return Accessory.this.copy();
		}

		@Override
		public xbean.Accessory toData() {
			return Accessory.this.toData();
		}

		public xbean.Accessory toBean() {
			return Accessory.this.toBean();
		}

		@Override
		public xbean.Accessory toDataIf() {
			return Accessory.this.toDataIf();
		}

		public xbean.Accessory toBeanIf() {
			return Accessory.this.toBeanIf();
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getMainprop() { // 主属性,目前是2个
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(mainprop);
		}

		public java.util.List<xbean.AccessoryProp> getMainpropAsData() { // 主属性,目前是2个
			_xdb_verify_unsafe_();
			java.util.List<xbean.AccessoryProp> mainprop;
			Accessory _o_ = Accessory.this;
		mainprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.mainprop)
			mainprop.add(new AccessoryProp.Data(_v_));
			return mainprop;
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getExtraprop() { // 附加属性，目前是5个
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(extraprop);
		}

		public java.util.List<xbean.AccessoryProp> getExtrapropAsData() { // 附加属性，目前是5个
			_xdb_verify_unsafe_();
			java.util.List<xbean.AccessoryProp> extraprop;
			Accessory _o_ = Accessory.this;
		extraprop = new java.util.LinkedList<xbean.AccessoryProp>();
		for (xbean.AccessoryProp _v_ : _o_.extraprop)
			extraprop.add(new AccessoryProp.Data(_v_));
			return extraprop;
		}

		@Override
		public xbean.AccessoryWashResult getLastwashrecord() { // 饰品洗练的临时结果,一次有效，成功应用或者取消后删除
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(lastwashrecord);
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
			return Accessory.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Accessory.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Accessory.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Accessory.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Accessory.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Accessory.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Accessory.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Accessory.this.hashCode();
		}

		@Override
		public String toString() {
			return Accessory.this.toString();
		}

	}

	public static final class Data implements xbean.Accessory {
		private java.util.LinkedList<xbean.AccessoryProp> mainprop; // 主属性,目前是2个
		private java.util.LinkedList<xbean.AccessoryProp> extraprop; // 附加属性，目前是5个
		private xbean.AccessoryWashResult lastwashrecord; // 饰品洗练的临时结果,一次有效，成功应用或者取消后删除

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			mainprop = new java.util.LinkedList<xbean.AccessoryProp>();
			extraprop = new java.util.LinkedList<xbean.AccessoryProp>();
			lastwashrecord = new AccessoryWashResult.Data();
		}

		Data(xbean.Accessory _o1_) {
			if (_o1_ instanceof Accessory) assign((Accessory)_o1_);
			else if (_o1_ instanceof Accessory.Data) assign((Accessory.Data)_o1_);
			else if (_o1_ instanceof Accessory.Const) assign(((Accessory.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Accessory _o_) {
			mainprop = new java.util.LinkedList<xbean.AccessoryProp>();
			for (xbean.AccessoryProp _v_ : _o_.mainprop)
				mainprop.add(new AccessoryProp.Data(_v_));
			extraprop = new java.util.LinkedList<xbean.AccessoryProp>();
			for (xbean.AccessoryProp _v_ : _o_.extraprop)
				extraprop.add(new AccessoryProp.Data(_v_));
			lastwashrecord = new AccessoryWashResult.Data(_o_.lastwashrecord);
		}

		private void assign(Accessory.Data _o_) {
			mainprop = new java.util.LinkedList<xbean.AccessoryProp>();
			for (xbean.AccessoryProp _v_ : _o_.mainprop)
				mainprop.add(new AccessoryProp.Data(_v_));
			extraprop = new java.util.LinkedList<xbean.AccessoryProp>();
			for (xbean.AccessoryProp _v_ : _o_.extraprop)
				extraprop.add(new AccessoryProp.Data(_v_));
			lastwashrecord = new AccessoryWashResult.Data(_o_.lastwashrecord);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(mainprop.size());
for (xbean.AccessoryProp _v_ : mainprop) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(extraprop.size());
for (xbean.AccessoryProp _v_ : extraprop) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(26624|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
lastwashrecord.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.AccessoryProp _v_ = xbean.Pod.newAccessoryPropData();
	_v_.unmarshal(_os_);
	mainprop.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.AccessoryProp _v_ = xbean.Pod.newAccessoryPropData();
	_v_.unmarshal(_os_);
	extraprop.add(_v_);
}
_os_ = _temp_;}
					break;
					case (26624|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
lastwashrecord.unmarshal(_os_);
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Accessory copy() {
			return new Data(this);
		}

		@Override
		public xbean.Accessory toData() {
			return new Data(this);
		}

		public xbean.Accessory toBean() {
			return new Accessory(this, null, null);
		}

		@Override
		public xbean.Accessory toDataIf() {
			return this;
		}

		public xbean.Accessory toBeanIf() {
			return new Accessory(this, null, null);
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
		public java.util.List<xbean.AccessoryProp> getMainprop() { // 主属性,目前是2个
			return mainprop;
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getMainpropAsData() { // 主属性,目前是2个
			return mainprop;
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getExtraprop() { // 附加属性，目前是5个
			return extraprop;
		}

		@Override
		public java.util.List<xbean.AccessoryProp> getExtrapropAsData() { // 附加属性，目前是5个
			return extraprop;
		}

		@Override
		public xbean.AccessoryWashResult getLastwashrecord() { // 饰品洗练的临时结果,一次有效，成功应用或者取消后删除
			return lastwashrecord;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Accessory.Data)) return false;
			Accessory.Data _o_ = (Accessory.Data) _o1_;
			if (!mainprop.equals(_o_.mainprop)) return false;
			if (!extraprop.equals(_o_.extraprop)) return false;
			if (!lastwashrecord.equals(_o_.lastwashrecord)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += mainprop.hashCode();
			_h_ += extraprop.hashCode();
			_h_ += lastwashrecord.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(mainprop);
			_sb_.append(",");
			_sb_.append(extraprop);
			_sb_.append(",");
			_sb_.append(lastwashrecord);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
