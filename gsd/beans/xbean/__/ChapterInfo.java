
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class ChapterInfo extends xdb.XBean implements xbean.ChapterInfo {
	private java.util.LinkedList<Integer> sectionstars; // 对应关卡1,2,,,,
	private java.util.LinkedList<Integer> obtainrewardindexs; // 

	@Override
	public void _reset_unsafe_() {
		sectionstars.clear();
		obtainrewardindexs.clear();
	}

	ChapterInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		sectionstars = new java.util.LinkedList<Integer>();
		obtainrewardindexs = new java.util.LinkedList<Integer>();
	}

	public ChapterInfo() {
		this(0, null, null);
	}

	public ChapterInfo(ChapterInfo _o_) {
		this(_o_, null, null);
	}

	ChapterInfo(xbean.ChapterInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof ChapterInfo) assign((ChapterInfo)_o1_);
		else if (_o1_ instanceof ChapterInfo.Data) assign((ChapterInfo.Data)_o1_);
		else if (_o1_ instanceof ChapterInfo.Const) assign(((ChapterInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(ChapterInfo _o_) {
		_o_._xdb_verify_unsafe_();
		sectionstars = new java.util.LinkedList<Integer>();
		sectionstars.addAll(_o_.sectionstars);
		obtainrewardindexs = new java.util.LinkedList<Integer>();
		obtainrewardindexs.addAll(_o_.obtainrewardindexs);
	}

	private void assign(ChapterInfo.Data _o_) {
		sectionstars = new java.util.LinkedList<Integer>();
		sectionstars.addAll(_o_.sectionstars);
		obtainrewardindexs = new java.util.LinkedList<Integer>();
		obtainrewardindexs.addAll(_o_.obtainrewardindexs);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(sectionstars.size());
for (Integer _v_ : sectionstars) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(obtainrewardindexs.size());
for (Integer _v_ : obtainrewardindexs) {
	_os_.marshal(_v_);
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
    				case (22528|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	sectionstars.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	obtainrewardindexs.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.ChapterInfo copy() {
		_xdb_verify_unsafe_();
		return new ChapterInfo(this);
	}

	@Override
	public xbean.ChapterInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ChapterInfo toBean() {
		_xdb_verify_unsafe_();
		return new ChapterInfo(this); // same as copy()
	}

	@Override
	public xbean.ChapterInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.ChapterInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.List<Integer> getSectionstars() { // 对应关卡1,2,,,,
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "sectionstars"), sectionstars);
	}

	public java.util.List<Integer> getSectionstarsAsData() { // 对应关卡1,2,,,,
		_xdb_verify_unsafe_();
		java.util.List<Integer> sectionstars;
		ChapterInfo _o_ = this;
		sectionstars = new java.util.LinkedList<Integer>();
		sectionstars.addAll(_o_.sectionstars);
		return sectionstars;
	}

	@Override
	public java.util.List<Integer> getObtainrewardindexs() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "obtainrewardindexs"), obtainrewardindexs);
	}

	public java.util.List<Integer> getObtainrewardindexsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Integer> obtainrewardindexs;
		ChapterInfo _o_ = this;
		obtainrewardindexs = new java.util.LinkedList<Integer>();
		obtainrewardindexs.addAll(_o_.obtainrewardindexs);
		return obtainrewardindexs;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		ChapterInfo _o_ = null;
		if ( _o1_ instanceof ChapterInfo ) _o_ = (ChapterInfo)_o1_;
		else if ( _o1_ instanceof ChapterInfo.Const ) _o_ = ((ChapterInfo.Const)_o1_).nThis();
		else return false;
		if (!sectionstars.equals(_o_.sectionstars)) return false;
		if (!obtainrewardindexs.equals(_o_.obtainrewardindexs)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += sectionstars.hashCode();
		_h_ += obtainrewardindexs.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(sectionstars);
		_sb_.append(",");
		_sb_.append(obtainrewardindexs);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("sectionstars"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("obtainrewardindexs"));
		return lb;
	}

	private class Const implements xbean.ChapterInfo {
		ChapterInfo nThis() {
			return ChapterInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.ChapterInfo copy() {
			return ChapterInfo.this.copy();
		}

		@Override
		public xbean.ChapterInfo toData() {
			return ChapterInfo.this.toData();
		}

		public xbean.ChapterInfo toBean() {
			return ChapterInfo.this.toBean();
		}

		@Override
		public xbean.ChapterInfo toDataIf() {
			return ChapterInfo.this.toDataIf();
		}

		public xbean.ChapterInfo toBeanIf() {
			return ChapterInfo.this.toBeanIf();
		}

		@Override
		public java.util.List<Integer> getSectionstars() { // 对应关卡1,2,,,,
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(sectionstars);
		}

		public java.util.List<Integer> getSectionstarsAsData() { // 对应关卡1,2,,,,
			_xdb_verify_unsafe_();
			java.util.List<Integer> sectionstars;
			ChapterInfo _o_ = ChapterInfo.this;
		sectionstars = new java.util.LinkedList<Integer>();
		sectionstars.addAll(_o_.sectionstars);
			return sectionstars;
		}

		@Override
		public java.util.List<Integer> getObtainrewardindexs() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(obtainrewardindexs);
		}

		public java.util.List<Integer> getObtainrewardindexsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Integer> obtainrewardindexs;
			ChapterInfo _o_ = ChapterInfo.this;
		obtainrewardindexs = new java.util.LinkedList<Integer>();
		obtainrewardindexs.addAll(_o_.obtainrewardindexs);
			return obtainrewardindexs;
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
			return ChapterInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return ChapterInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return ChapterInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return ChapterInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return ChapterInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return ChapterInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return ChapterInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return ChapterInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return ChapterInfo.this.toString();
		}

	}

	public static final class Data implements xbean.ChapterInfo {
		private java.util.LinkedList<Integer> sectionstars; // 对应关卡1,2,,,,
		private java.util.LinkedList<Integer> obtainrewardindexs; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			sectionstars = new java.util.LinkedList<Integer>();
			obtainrewardindexs = new java.util.LinkedList<Integer>();
		}

		Data(xbean.ChapterInfo _o1_) {
			if (_o1_ instanceof ChapterInfo) assign((ChapterInfo)_o1_);
			else if (_o1_ instanceof ChapterInfo.Data) assign((ChapterInfo.Data)_o1_);
			else if (_o1_ instanceof ChapterInfo.Const) assign(((ChapterInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(ChapterInfo _o_) {
			sectionstars = new java.util.LinkedList<Integer>();
			sectionstars.addAll(_o_.sectionstars);
			obtainrewardindexs = new java.util.LinkedList<Integer>();
			obtainrewardindexs.addAll(_o_.obtainrewardindexs);
		}

		private void assign(ChapterInfo.Data _o_) {
			sectionstars = new java.util.LinkedList<Integer>();
			sectionstars.addAll(_o_.sectionstars);
			obtainrewardindexs = new java.util.LinkedList<Integer>();
			obtainrewardindexs.addAll(_o_.obtainrewardindexs);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(22528|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(sectionstars.size());
for (Integer _v_ : sectionstars) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(obtainrewardindexs.size());
for (Integer _v_ : obtainrewardindexs) {
	_os_.marshal(_v_);
}
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
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	sectionstars.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	obtainrewardindexs.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.ChapterInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.ChapterInfo toData() {
			return new Data(this);
		}

		public xbean.ChapterInfo toBean() {
			return new ChapterInfo(this, null, null);
		}

		@Override
		public xbean.ChapterInfo toDataIf() {
			return this;
		}

		public xbean.ChapterInfo toBeanIf() {
			return new ChapterInfo(this, null, null);
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
		public java.util.List<Integer> getSectionstars() { // 对应关卡1,2,,,,
			return sectionstars;
		}

		@Override
		public java.util.List<Integer> getSectionstarsAsData() { // 对应关卡1,2,,,,
			return sectionstars;
		}

		@Override
		public java.util.List<Integer> getObtainrewardindexs() { // 
			return obtainrewardindexs;
		}

		@Override
		public java.util.List<Integer> getObtainrewardindexsAsData() { // 
			return obtainrewardindexs;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof ChapterInfo.Data)) return false;
			ChapterInfo.Data _o_ = (ChapterInfo.Data) _o1_;
			if (!sectionstars.equals(_o_.sectionstars)) return false;
			if (!obtainrewardindexs.equals(_o_.obtainrewardindexs)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += sectionstars.hashCode();
			_h_ += obtainrewardindexs.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(sectionstars);
			_sb_.append(",");
			_sb_.append(obtainrewardindexs);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
