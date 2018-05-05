
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class BoardInfo extends xdb.XBean implements xbean.BoardInfo {
	private java.util.HashMap<Integer, xbean.BoardEntry> latestboard; // 
	private java.util.HashMap<Long, Integer> rolerank; // 
	private long lastupdatetime; // 
	private java.util.HashMap<Long, Integer> yesterdayrank; // 

	@Override
	public void _reset_unsafe_() {
		latestboard.clear();
		rolerank.clear();
		lastupdatetime = 0L;
		yesterdayrank.clear();
	}

	BoardInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		latestboard = new java.util.HashMap<Integer, xbean.BoardEntry>();
		rolerank = new java.util.HashMap<Long, Integer>();
		yesterdayrank = new java.util.HashMap<Long, Integer>();
	}

	public BoardInfo() {
		this(0, null, null);
	}

	public BoardInfo(BoardInfo _o_) {
		this(_o_, null, null);
	}

	BoardInfo(xbean.BoardInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof BoardInfo) assign((BoardInfo)_o1_);
		else if (_o1_ instanceof BoardInfo.Data) assign((BoardInfo.Data)_o1_);
		else if (_o1_ instanceof BoardInfo.Const) assign(((BoardInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(BoardInfo _o_) {
		_o_._xdb_verify_unsafe_();
		latestboard = new java.util.HashMap<Integer, xbean.BoardEntry>();
		for (java.util.Map.Entry<Integer, xbean.BoardEntry> _e_ : _o_.latestboard.entrySet())
			latestboard.put(_e_.getKey(), new BoardEntry(_e_.getValue(), this, "latestboard"));
		rolerank = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.rolerank.entrySet())
			rolerank.put(_e_.getKey(), _e_.getValue());
		lastupdatetime = _o_.lastupdatetime;
		yesterdayrank = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.yesterdayrank.entrySet())
			yesterdayrank.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(BoardInfo.Data _o_) {
		latestboard = new java.util.HashMap<Integer, xbean.BoardEntry>();
		for (java.util.Map.Entry<Integer, xbean.BoardEntry> _e_ : _o_.latestboard.entrySet())
			latestboard.put(_e_.getKey(), new BoardEntry(_e_.getValue(), this, "latestboard"));
		rolerank = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.rolerank.entrySet())
			rolerank.put(_e_.getKey(), _e_.getValue());
		lastupdatetime = _o_.lastupdatetime;
		yesterdayrank = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.yesterdayrank.entrySet())
			yesterdayrank.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)4);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(latestboard.size());
for (java.util.Map.Entry<Integer, xbean.BoardEntry> _e_ : latestboard.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(rolerank.size());
for (java.util.Map.Entry<Long, Integer> _e_ : rolerank.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  5));_os_.marshal(lastupdatetime);
    _os_.marshal((short)(24576|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(yesterdayrank.size());
for (java.util.Map.Entry<Long, Integer> _e_ : yesterdayrank.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
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
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		latestboard = new java.util.HashMap<Integer, xbean.BoardEntry>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.BoardEntry _v_ = new BoardEntry(0, this, "latestboard");
		_v_.unmarshal(_os_);
		latestboard.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		rolerank = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		rolerank.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (10240|  5):lastupdatetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):lastupdatetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):lastupdatetime = _os_.unmarshal_int();
    				break;
    				case (24576|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		yesterdayrank = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		yesterdayrank.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.BoardInfo copy() {
		_xdb_verify_unsafe_();
		return new BoardInfo(this);
	}

	@Override
	public xbean.BoardInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.BoardInfo toBean() {
		_xdb_verify_unsafe_();
		return new BoardInfo(this); // same as copy()
	}

	@Override
	public xbean.BoardInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.BoardInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.BoardEntry> getLatestboard() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "latestboard"), latestboard);
	}

	@Override
	public java.util.Map<Integer, xbean.BoardEntry> getLatestboardAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.BoardEntry> latestboard;
		BoardInfo _o_ = this;
		latestboard = new java.util.HashMap<Integer, xbean.BoardEntry>();
		for (java.util.Map.Entry<Integer, xbean.BoardEntry> _e_ : _o_.latestboard.entrySet())
			latestboard.put(_e_.getKey(), new BoardEntry.Data(_e_.getValue()));
		return latestboard;
	}

	@Override
	public java.util.Map<Long, Integer> getRolerank() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "rolerank"), rolerank);
	}

	@Override
	public java.util.Map<Long, Integer> getRolerankAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Long, Integer> rolerank;
		BoardInfo _o_ = this;
		rolerank = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.rolerank.entrySet())
			rolerank.put(_e_.getKey(), _e_.getValue());
		return rolerank;
	}

	@Override
	public long getLastupdatetime() { // 
		_xdb_verify_unsafe_();
		return lastupdatetime;
	}

	@Override
	public java.util.Map<Long, Integer> getYesterdayrank() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "yesterdayrank"), yesterdayrank);
	}

	@Override
	public java.util.Map<Long, Integer> getYesterdayrankAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Long, Integer> yesterdayrank;
		BoardInfo _o_ = this;
		yesterdayrank = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.yesterdayrank.entrySet())
			yesterdayrank.put(_e_.getKey(), _e_.getValue());
		return yesterdayrank;
	}

	@Override
	public void setLastupdatetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastupdatetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastupdatetime) {
					public void rollback() { lastupdatetime = _xdb_saved; }
				};}});
		lastupdatetime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		BoardInfo _o_ = null;
		if ( _o1_ instanceof BoardInfo ) _o_ = (BoardInfo)_o1_;
		else if ( _o1_ instanceof BoardInfo.Const ) _o_ = ((BoardInfo.Const)_o1_).nThis();
		else return false;
		if (!latestboard.equals(_o_.latestboard)) return false;
		if (!rolerank.equals(_o_.rolerank)) return false;
		if (lastupdatetime != _o_.lastupdatetime) return false;
		if (!yesterdayrank.equals(_o_.yesterdayrank)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += latestboard.hashCode();
		_h_ += rolerank.hashCode();
		_h_ += lastupdatetime;
		_h_ += yesterdayrank.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(latestboard);
		_sb_.append(",");
		_sb_.append(rolerank);
		_sb_.append(",");
		_sb_.append(lastupdatetime);
		_sb_.append(",");
		_sb_.append(yesterdayrank);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("latestboard"));
		lb.add(new xdb.logs.ListenableMap().setVarName("rolerank"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastupdatetime"));
		lb.add(new xdb.logs.ListenableMap().setVarName("yesterdayrank"));
		return lb;
	}

	private class Const implements xbean.BoardInfo {
		BoardInfo nThis() {
			return BoardInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.BoardInfo copy() {
			return BoardInfo.this.copy();
		}

		@Override
		public xbean.BoardInfo toData() {
			return BoardInfo.this.toData();
		}

		public xbean.BoardInfo toBean() {
			return BoardInfo.this.toBean();
		}

		@Override
		public xbean.BoardInfo toDataIf() {
			return BoardInfo.this.toDataIf();
		}

		public xbean.BoardInfo toBeanIf() {
			return BoardInfo.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.BoardEntry> getLatestboard() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(latestboard);
		}

		@Override
		public java.util.Map<Integer, xbean.BoardEntry> getLatestboardAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.BoardEntry> latestboard;
			BoardInfo _o_ = BoardInfo.this;
			latestboard = new java.util.HashMap<Integer, xbean.BoardEntry>();
			for (java.util.Map.Entry<Integer, xbean.BoardEntry> _e_ : _o_.latestboard.entrySet())
				latestboard.put(_e_.getKey(), new BoardEntry.Data(_e_.getValue()));
			return latestboard;
		}

		@Override
		public java.util.Map<Long, Integer> getRolerank() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(rolerank);
		}

		@Override
		public java.util.Map<Long, Integer> getRolerankAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Long, Integer> rolerank;
			BoardInfo _o_ = BoardInfo.this;
			rolerank = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.rolerank.entrySet())
				rolerank.put(_e_.getKey(), _e_.getValue());
			return rolerank;
		}

		@Override
		public long getLastupdatetime() { // 
			_xdb_verify_unsafe_();
			return lastupdatetime;
		}

		@Override
		public java.util.Map<Long, Integer> getYesterdayrank() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(yesterdayrank);
		}

		@Override
		public java.util.Map<Long, Integer> getYesterdayrankAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Long, Integer> yesterdayrank;
			BoardInfo _o_ = BoardInfo.this;
			yesterdayrank = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.yesterdayrank.entrySet())
				yesterdayrank.put(_e_.getKey(), _e_.getValue());
			return yesterdayrank;
		}

		@Override
		public void setLastupdatetime(long _v_) { // 
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
			return BoardInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return BoardInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return BoardInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return BoardInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return BoardInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return BoardInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return BoardInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return BoardInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return BoardInfo.this.toString();
		}

	}

	public static final class Data implements xbean.BoardInfo {
		private java.util.HashMap<Integer, xbean.BoardEntry> latestboard; // 
		private java.util.HashMap<Long, Integer> rolerank; // 
		private long lastupdatetime; // 
		private java.util.HashMap<Long, Integer> yesterdayrank; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			latestboard = new java.util.HashMap<Integer, xbean.BoardEntry>();
			rolerank = new java.util.HashMap<Long, Integer>();
			yesterdayrank = new java.util.HashMap<Long, Integer>();
		}

		Data(xbean.BoardInfo _o1_) {
			if (_o1_ instanceof BoardInfo) assign((BoardInfo)_o1_);
			else if (_o1_ instanceof BoardInfo.Data) assign((BoardInfo.Data)_o1_);
			else if (_o1_ instanceof BoardInfo.Const) assign(((BoardInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(BoardInfo _o_) {
			latestboard = new java.util.HashMap<Integer, xbean.BoardEntry>();
			for (java.util.Map.Entry<Integer, xbean.BoardEntry> _e_ : _o_.latestboard.entrySet())
				latestboard.put(_e_.getKey(), new BoardEntry.Data(_e_.getValue()));
			rolerank = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.rolerank.entrySet())
				rolerank.put(_e_.getKey(), _e_.getValue());
			lastupdatetime = _o_.lastupdatetime;
			yesterdayrank = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.yesterdayrank.entrySet())
				yesterdayrank.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(BoardInfo.Data _o_) {
			latestboard = new java.util.HashMap<Integer, xbean.BoardEntry>();
			for (java.util.Map.Entry<Integer, xbean.BoardEntry> _e_ : _o_.latestboard.entrySet())
				latestboard.put(_e_.getKey(), new BoardEntry.Data(_e_.getValue()));
			rolerank = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.rolerank.entrySet())
				rolerank.put(_e_.getKey(), _e_.getValue());
			lastupdatetime = _o_.lastupdatetime;
			yesterdayrank = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.yesterdayrank.entrySet())
				yesterdayrank.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)4);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(latestboard.size());
for (java.util.Map.Entry<Integer, xbean.BoardEntry> _e_ : latestboard.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(rolerank.size());
for (java.util.Map.Entry<Long, Integer> _e_ : rolerank.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  5));_os_.marshal(lastupdatetime);
	_os_.marshal((short)(24576|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(yesterdayrank.size());
for (java.util.Map.Entry<Long, Integer> _e_ : yesterdayrank.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		latestboard = new java.util.HashMap<Integer, xbean.BoardEntry>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.BoardEntry _v_ = xbean.Pod.newBoardEntryData();
		_v_.unmarshal(_os_);
		latestboard.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		rolerank = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		rolerank.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (10240|  5):lastupdatetime = _os_.unmarshal_long();
					break;
					case ( 6144|  5):lastupdatetime = _os_.unmarshal_short();
					break;
					case ( 8192|  5):lastupdatetime = _os_.unmarshal_int();
					break;
					case (24576|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		yesterdayrank = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		yesterdayrank.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.BoardInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.BoardInfo toData() {
			return new Data(this);
		}

		public xbean.BoardInfo toBean() {
			return new BoardInfo(this, null, null);
		}

		@Override
		public xbean.BoardInfo toDataIf() {
			return this;
		}

		public xbean.BoardInfo toBeanIf() {
			return new BoardInfo(this, null, null);
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
		public java.util.Map<Integer, xbean.BoardEntry> getLatestboard() { // 
			return latestboard;
		}

		@Override
		public java.util.Map<Integer, xbean.BoardEntry> getLatestboardAsData() { // 
			return latestboard;
		}

		@Override
		public java.util.Map<Long, Integer> getRolerank() { // 
			return rolerank;
		}

		@Override
		public java.util.Map<Long, Integer> getRolerankAsData() { // 
			return rolerank;
		}

		@Override
		public long getLastupdatetime() { // 
			return lastupdatetime;
		}

		@Override
		public java.util.Map<Long, Integer> getYesterdayrank() { // 
			return yesterdayrank;
		}

		@Override
		public java.util.Map<Long, Integer> getYesterdayrankAsData() { // 
			return yesterdayrank;
		}

		@Override
		public void setLastupdatetime(long _v_) { // 
			lastupdatetime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof BoardInfo.Data)) return false;
			BoardInfo.Data _o_ = (BoardInfo.Data) _o1_;
			if (!latestboard.equals(_o_.latestboard)) return false;
			if (!rolerank.equals(_o_.rolerank)) return false;
			if (lastupdatetime != _o_.lastupdatetime) return false;
			if (!yesterdayrank.equals(_o_.yesterdayrank)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += latestboard.hashCode();
			_h_ += rolerank.hashCode();
			_h_ += lastupdatetime;
			_h_ += yesterdayrank.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(latestboard);
			_sb_.append(",");
			_sb_.append(rolerank);
			_sb_.append(",");
			_sb_.append(lastupdatetime);
			_sb_.append(",");
			_sb_.append(yesterdayrank);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
