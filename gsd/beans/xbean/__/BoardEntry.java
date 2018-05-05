
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class BoardEntry extends xdb.XBean implements xbean.BoardEntry {
	private int ranking; // 
	private long id; // 家族战力榜寸的是家族id,其他是存roleid
	private String name; // 
	private long val1; // 
	private int val2; // 
	private long updatetime; // 更新时间，值相同的记录排序时时间早的在前面

	@Override
	public void _reset_unsafe_() {
		ranking = 0;
		id = 0L;
		name = "";
		val1 = 0L;
		val2 = 0;
		updatetime = 0L;
	}

	BoardEntry(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		name = "";
	}

	public BoardEntry() {
		this(0, null, null);
	}

	public BoardEntry(BoardEntry _o_) {
		this(_o_, null, null);
	}

	BoardEntry(xbean.BoardEntry _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof BoardEntry) assign((BoardEntry)_o1_);
		else if (_o1_ instanceof BoardEntry.Data) assign((BoardEntry.Data)_o1_);
		else if (_o1_ instanceof BoardEntry.Const) assign(((BoardEntry.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(BoardEntry _o_) {
		_o_._xdb_verify_unsafe_();
		ranking = _o_.ranking;
		id = _o_.id;
		name = _o_.name;
		val1 = _o_.val1;
		val2 = _o_.val2;
		updatetime = _o_.updatetime;
	}

	private void assign(BoardEntry.Data _o_) {
		ranking = _o_.ranking;
		id = _o_.id;
		name = _o_.name;
		val1 = _o_.val1;
		val2 = _o_.val2;
		updatetime = _o_.updatetime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)( 8192|  1));_os_.marshal(ranking);
    _os_.marshal((short)(10240|  2));_os_.marshal(id);
    _os_.marshal((short)(18432|  3));_os_.marshal(name, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(10240|  4));_os_.marshal(val1);
    _os_.marshal((short)( 8192|  5));_os_.marshal(val2);
    _os_.marshal((short)(10240|  6));_os_.marshal(updatetime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):ranking = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):ranking = _os_.unmarshal_short();
    				break;
    				case (10240|  2):id = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):id = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):id = _os_.unmarshal_int();
    				break;
    				case (18432|  3):name = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (10240|  4):val1 = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):val1 = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):val1 = _os_.unmarshal_int();
    				break;
    				case ( 8192|  5):val2 = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):val2 = _os_.unmarshal_short();
    				break;
    				case (10240|  6):updatetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  6):updatetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):updatetime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.BoardEntry copy() {
		_xdb_verify_unsafe_();
		return new BoardEntry(this);
	}

	@Override
	public xbean.BoardEntry toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.BoardEntry toBean() {
		_xdb_verify_unsafe_();
		return new BoardEntry(this); // same as copy()
	}

	@Override
	public xbean.BoardEntry toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.BoardEntry toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getRanking() { // 
		_xdb_verify_unsafe_();
		return ranking;
	}

	@Override
	public long getId() { // 家族战力榜寸的是家族id,其他是存roleid
		_xdb_verify_unsafe_();
		return id;
	}

	@Override
	public String getName() { // 
		_xdb_verify_unsafe_();
		return name;
	}

	@Override
	public com.goldhuman.Common.Octets getNameOctets() { // 
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getName(), xdb.Const.IO_CHARSET);
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
	public void setRanking(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "ranking") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, ranking) {
					public void rollback() { ranking = _xdb_saved; }
				};}});
		ranking = _v_;
	}

	@Override
	public void setId(long _v_) { // 家族战力榜寸的是家族id,其他是存roleid
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "id") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, id) {
					public void rollback() { id = _xdb_saved; }
				};}});
		id = _v_;
	}

	@Override
	public void setName(String _v_) { // 
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "name") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, name) {
					public void rollback() { name = _xdb_saved; }
				};}});
		name = _v_;
	}

	@Override
	public void setNameOctets(com.goldhuman.Common.Octets _v_) { // 
		_xdb_verify_unsafe_();
		this.setName(_v_.getString(xdb.Const.IO_CHARSET));
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
		BoardEntry _o_ = null;
		if ( _o1_ instanceof BoardEntry ) _o_ = (BoardEntry)_o1_;
		else if ( _o1_ instanceof BoardEntry.Const ) _o_ = ((BoardEntry.Const)_o1_).nThis();
		else return false;
		if (ranking != _o_.ranking) return false;
		if (id != _o_.id) return false;
		if (!name.equals(_o_.name)) return false;
		if (val1 != _o_.val1) return false;
		if (val2 != _o_.val2) return false;
		if (updatetime != _o_.updatetime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += ranking;
		_h_ += id;
		_h_ += name.hashCode();
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
		_sb_.append(ranking);
		_sb_.append(",");
		_sb_.append(id);
		_sb_.append(",");
		_sb_.append("'").append(name).append("'");
		_sb_.append(",");
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
		lb.add(new xdb.logs.ListenableChanged().setVarName("ranking"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("id"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("name"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("val1"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("val2"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("updatetime"));
		return lb;
	}

	private class Const implements xbean.BoardEntry {
		BoardEntry nThis() {
			return BoardEntry.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.BoardEntry copy() {
			return BoardEntry.this.copy();
		}

		@Override
		public xbean.BoardEntry toData() {
			return BoardEntry.this.toData();
		}

		public xbean.BoardEntry toBean() {
			return BoardEntry.this.toBean();
		}

		@Override
		public xbean.BoardEntry toDataIf() {
			return BoardEntry.this.toDataIf();
		}

		public xbean.BoardEntry toBeanIf() {
			return BoardEntry.this.toBeanIf();
		}

		@Override
		public int getRanking() { // 
			_xdb_verify_unsafe_();
			return ranking;
		}

		@Override
		public long getId() { // 家族战力榜寸的是家族id,其他是存roleid
			_xdb_verify_unsafe_();
			return id;
		}

		@Override
		public String getName() { // 
			_xdb_verify_unsafe_();
			return name;
		}

		@Override
		public com.goldhuman.Common.Octets getNameOctets() { // 
			_xdb_verify_unsafe_();
			return BoardEntry.this.getNameOctets();
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
		public void setRanking(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setId(long _v_) { // 家族战力榜寸的是家族id,其他是存roleid
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setName(String _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNameOctets(com.goldhuman.Common.Octets _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
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
			return BoardEntry.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return BoardEntry.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return BoardEntry.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return BoardEntry.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return BoardEntry.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return BoardEntry.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return BoardEntry.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return BoardEntry.this.hashCode();
		}

		@Override
		public String toString() {
			return BoardEntry.this.toString();
		}

	}

	public static final class Data implements xbean.BoardEntry {
		private int ranking; // 
		private long id; // 家族战力榜寸的是家族id,其他是存roleid
		private String name; // 
		private long val1; // 
		private int val2; // 
		private long updatetime; // 更新时间，值相同的记录排序时时间早的在前面

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			name = "";
		}

		Data(xbean.BoardEntry _o1_) {
			if (_o1_ instanceof BoardEntry) assign((BoardEntry)_o1_);
			else if (_o1_ instanceof BoardEntry.Data) assign((BoardEntry.Data)_o1_);
			else if (_o1_ instanceof BoardEntry.Const) assign(((BoardEntry.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(BoardEntry _o_) {
			ranking = _o_.ranking;
			id = _o_.id;
			name = _o_.name;
			val1 = _o_.val1;
			val2 = _o_.val2;
			updatetime = _o_.updatetime;
		}

		private void assign(BoardEntry.Data _o_) {
			ranking = _o_.ranking;
			id = _o_.id;
			name = _o_.name;
			val1 = _o_.val1;
			val2 = _o_.val2;
			updatetime = _o_.updatetime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)( 8192|  1));_os_.marshal(ranking);
	_os_.marshal((short)(10240|  2));_os_.marshal(id);
	_os_.marshal((short)(18432|  3));_os_.marshal(name, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(10240|  4));_os_.marshal(val1);
	_os_.marshal((short)( 8192|  5));_os_.marshal(val2);
	_os_.marshal((short)(10240|  6));_os_.marshal(updatetime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):ranking = _os_.unmarshal_int();
					break;
					case ( 6144|  1):ranking = _os_.unmarshal_short();
					break;
					case (10240|  2):id = _os_.unmarshal_long();
					break;
					case ( 6144|  2):id = _os_.unmarshal_short();
					break;
					case ( 8192|  2):id = _os_.unmarshal_int();
					break;
					case (18432|  3):name = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (10240|  4):val1 = _os_.unmarshal_long();
					break;
					case ( 6144|  4):val1 = _os_.unmarshal_short();
					break;
					case ( 8192|  4):val1 = _os_.unmarshal_int();
					break;
					case ( 8192|  5):val2 = _os_.unmarshal_int();
					break;
					case ( 6144|  5):val2 = _os_.unmarshal_short();
					break;
					case (10240|  6):updatetime = _os_.unmarshal_long();
					break;
					case ( 6144|  6):updatetime = _os_.unmarshal_short();
					break;
					case ( 8192|  6):updatetime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.BoardEntry copy() {
			return new Data(this);
		}

		@Override
		public xbean.BoardEntry toData() {
			return new Data(this);
		}

		public xbean.BoardEntry toBean() {
			return new BoardEntry(this, null, null);
		}

		@Override
		public xbean.BoardEntry toDataIf() {
			return this;
		}

		public xbean.BoardEntry toBeanIf() {
			return new BoardEntry(this, null, null);
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
		public int getRanking() { // 
			return ranking;
		}

		@Override
		public long getId() { // 家族战力榜寸的是家族id,其他是存roleid
			return id;
		}

		@Override
		public String getName() { // 
			return name;
		}

		@Override
		public com.goldhuman.Common.Octets getNameOctets() { // 
			return com.goldhuman.Common.Octets.wrap(getName(), xdb.Const.IO_CHARSET);
		}

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
		public void setRanking(int _v_) { // 
			ranking = _v_;
		}

		@Override
		public void setId(long _v_) { // 家族战力榜寸的是家族id,其他是存roleid
			id = _v_;
		}

		@Override
		public void setName(String _v_) { // 
			if (null == _v_)
				throw new NullPointerException();
			name = _v_;
		}

		@Override
		public void setNameOctets(com.goldhuman.Common.Octets _v_) { // 
			this.setName(_v_.getString(xdb.Const.IO_CHARSET));
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
			if (!(_o1_ instanceof BoardEntry.Data)) return false;
			BoardEntry.Data _o_ = (BoardEntry.Data) _o1_;
			if (ranking != _o_.ranking) return false;
			if (id != _o_.id) return false;
			if (!name.equals(_o_.name)) return false;
			if (val1 != _o_.val1) return false;
			if (val2 != _o_.val2) return false;
			if (updatetime != _o_.updatetime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += ranking;
			_h_ += id;
			_h_ += name.hashCode();
			_h_ += val1;
			_h_ += val2;
			_h_ += updatetime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(ranking);
			_sb_.append(",");
			_sb_.append(id);
			_sb_.append(",");
			_sb_.append("'").append(name).append("'");
			_sb_.append(",");
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
