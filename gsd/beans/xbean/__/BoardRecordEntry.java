
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class BoardRecordEntry extends xdb.XBean implements xbean.BoardRecordEntry {
	private long val1; // 
	private int val2; // 
	private long updatetime; // 更新时间，值相同的记录排序时时间早的在前面

	@Override
	public void _reset_unsafe_() {
		val1 = 0L;
		val2 = 0;
		updatetime = 0L;
	}

	BoardRecordEntry(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public BoardRecordEntry() {
		this(0, null, null);
	}

	public BoardRecordEntry(BoardRecordEntry _o_) {
		this(_o_, null, null);
	}

	BoardRecordEntry(xbean.BoardRecordEntry _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof BoardRecordEntry) assign((BoardRecordEntry)_o1_);
		else if (_o1_ instanceof BoardRecordEntry.Data) assign((BoardRecordEntry.Data)_o1_);
		else if (_o1_ instanceof BoardRecordEntry.Const) assign(((BoardRecordEntry.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(BoardRecordEntry _o_) {
		_o_._xdb_verify_unsafe_();
		val1 = _o_.val1;
		val2 = _o_.val2;
		updatetime = _o_.updatetime;
	}

	private void assign(BoardRecordEntry.Data _o_) {
		val1 = _o_.val1;
		val2 = _o_.val2;
		updatetime = _o_.updatetime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)(10240|  1));_os_.marshal(val1);
    _os_.marshal((short)( 8192|  2));_os_.marshal(val2);
    _os_.marshal((short)(10240|  3));_os_.marshal(updatetime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):val1 = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):val1 = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):val1 = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):val2 = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):val2 = _os_.unmarshal_short();
    				break;
    				case (10240|  3):updatetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):updatetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):updatetime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.BoardRecordEntry copy() {
		_xdb_verify_unsafe_();
		return new BoardRecordEntry(this);
	}

	@Override
	public xbean.BoardRecordEntry toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.BoardRecordEntry toBean() {
		_xdb_verify_unsafe_();
		return new BoardRecordEntry(this); // same as copy()
	}

	@Override
	public xbean.BoardRecordEntry toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.BoardRecordEntry toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getVal1() { // 
		_xdb_verify_unsafe_();
		return val1;
	}

	@Override
	public int getVal2() { // 
		_xdb_verify_unsafe_();
		return val2;
	}

	@Override
	public long getUpdatetime() { // 更新时间，值相同的记录排序时时间早的在前面
		_xdb_verify_unsafe_();
		return updatetime;
	}

	@Override
	public void setVal1(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "val1") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, val1) {
					public void rollback() { val1 = _xdb_saved; }
				};}});
		val1 = _v_;
	}

	@Override
	public void setVal2(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "val2") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, val2) {
					public void rollback() { val2 = _xdb_saved; }
				};}});
		val2 = _v_;
	}

	@Override
	public void setUpdatetime(long _v_) { // 更新时间，值相同的记录排序时时间早的在前面
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "updatetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, updatetime) {
					public void rollback() { updatetime = _xdb_saved; }
				};}});
		updatetime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		BoardRecordEntry _o_ = null;
		if ( _o1_ instanceof BoardRecordEntry ) _o_ = (BoardRecordEntry)_o1_;
		else if ( _o1_ instanceof BoardRecordEntry.Const ) _o_ = ((BoardRecordEntry.Const)_o1_).nThis();
		else return false;
		if (val1 != _o_.val1) return false;
		if (val2 != _o_.val2) return false;
		if (updatetime != _o_.updatetime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += val1;
		_h_ += val2;
		_h_ += updatetime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(val1);
		_sb_.append(",");
		_sb_.append(val2);
		_sb_.append(",");
		_sb_.append(updatetime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("val1"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("val2"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("updatetime"));
		return lb;
	}

	private class Const implements xbean.BoardRecordEntry {
		BoardRecordEntry nThis() {
			return BoardRecordEntry.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.BoardRecordEntry copy() {
			return BoardRecordEntry.this.copy();
		}

		@Override
		public xbean.BoardRecordEntry toData() {
			return BoardRecordEntry.this.toData();
		}

		public xbean.BoardRecordEntry toBean() {
			return BoardRecordEntry.this.toBean();
		}

		@Override
		public xbean.BoardRecordEntry toDataIf() {
			return BoardRecordEntry.this.toDataIf();
		}

		public xbean.BoardRecordEntry toBeanIf() {
			return BoardRecordEntry.this.toBeanIf();
		}

		@Override
		public long getVal1() { // 
			_xdb_verify_unsafe_();
			return val1;
		}

		@Override
		public int getVal2() { // 
			_xdb_verify_unsafe_();
			return val2;
		}

		@Override
		public long getUpdatetime() { // 更新时间，值相同的记录排序时时间早的在前面
			_xdb_verify_unsafe_();
			return updatetime;
		}

		@Override
		public void setVal1(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setVal2(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUpdatetime(long _v_) { // 更新时间，值相同的记录排序时时间早的在前面
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
			return BoardRecordEntry.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return BoardRecordEntry.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return BoardRecordEntry.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return BoardRecordEntry.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return BoardRecordEntry.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return BoardRecordEntry.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return BoardRecordEntry.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return BoardRecordEntry.this.hashCode();
		}

		@Override
		public String toString() {
			return BoardRecordEntry.this.toString();
		}

	}

	public static final class Data implements xbean.BoardRecordEntry {
		private long val1; // 
		private int val2; // 
		private long updatetime; // 更新时间，值相同的记录排序时时间早的在前面

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.BoardRecordEntry _o1_) {
			if (_o1_ instanceof BoardRecordEntry) assign((BoardRecordEntry)_o1_);
			else if (_o1_ instanceof BoardRecordEntry.Data) assign((BoardRecordEntry.Data)_o1_);
			else if (_o1_ instanceof BoardRecordEntry.Const) assign(((BoardRecordEntry.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(BoardRecordEntry _o_) {
			val1 = _o_.val1;
			val2 = _o_.val2;
			updatetime = _o_.updatetime;
		}

		private void assign(BoardRecordEntry.Data _o_) {
			val1 = _o_.val1;
			val2 = _o_.val2;
			updatetime = _o_.updatetime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)(10240|  1));_os_.marshal(val1);
	_os_.marshal((short)( 8192|  2));_os_.marshal(val2);
	_os_.marshal((short)(10240|  3));_os_.marshal(updatetime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):val1 = _os_.unmarshal_long();
					break;
					case ( 6144|  1):val1 = _os_.unmarshal_short();
					break;
					case ( 8192|  1):val1 = _os_.unmarshal_int();
					break;
					case ( 8192|  2):val2 = _os_.unmarshal_int();
					break;
					case ( 6144|  2):val2 = _os_.unmarshal_short();
					break;
					case (10240|  3):updatetime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):updatetime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):updatetime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.BoardRecordEntry copy() {
			return new Data(this);
		}

		@Override
		public xbean.BoardRecordEntry toData() {
			return new Data(this);
		}

		public xbean.BoardRecordEntry toBean() {
			return new BoardRecordEntry(this, null, null);
		}

		@Override
		public xbean.BoardRecordEntry toDataIf() {
			return this;
		}

		public xbean.BoardRecordEntry toBeanIf() {
			return new BoardRecordEntry(this, null, null);
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
		public long getVal1() { // 
			return val1;
		}

		@Override
		public int getVal2() { // 
			return val2;
		}

		@Override
		public long getUpdatetime() { // 更新时间，值相同的记录排序时时间早的在前面
			return updatetime;
		}

		@Override
		public void setVal1(long _v_) { // 
			val1 = _v_;
		}

		@Override
		public void setVal2(int _v_) { // 
			val2 = _v_;
		}

		@Override
		public void setUpdatetime(long _v_) { // 更新时间，值相同的记录排序时时间早的在前面
			updatetime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof BoardRecordEntry.Data)) return false;
			BoardRecordEntry.Data _o_ = (BoardRecordEntry.Data) _o1_;
			if (val1 != _o_.val1) return false;
			if (val2 != _o_.val2) return false;
			if (updatetime != _o_.updatetime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += val1;
			_h_ += val2;
			_h_ += updatetime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(val1);
			_sb_.append(",");
			_sb_.append(val2);
			_sb_.append(",");
			_sb_.append(updatetime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
