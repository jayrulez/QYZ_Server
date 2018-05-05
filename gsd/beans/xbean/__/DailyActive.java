
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class DailyActive extends xdb.XBean implements xbean.DailyActive {
	private java.util.HashMap<Integer, Integer> activetimes; // 各种活动完成的次数
	private java.util.LinkedList<Integer> receivedbonus; // 已领取的奖励类型
	private int activescores; // 完成活动得到的总积分
	private java.util.HashMap<Integer, Integer> undoactive; // 每种活动当天未完成的次数
	private java.util.HashMap<Integer, Integer> eventdonetimes; // 每种活动当天完成总次数，找回用
	private int lastactivelvl; // 昨日最后活跃的等级，找回用

	@Override
	public void _reset_unsafe_() {
		activetimes.clear();
		receivedbonus.clear();
		activescores = 0;
		undoactive.clear();
		eventdonetimes.clear();
		lastactivelvl = 0;
	}

	DailyActive(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		activetimes = new java.util.HashMap<Integer, Integer>();
		receivedbonus = new java.util.LinkedList<Integer>();
		undoactive = new java.util.HashMap<Integer, Integer>();
		eventdonetimes = new java.util.HashMap<Integer, Integer>();
	}

	public DailyActive() {
		this(0, null, null);
	}

	public DailyActive(DailyActive _o_) {
		this(_o_, null, null);
	}

	DailyActive(xbean.DailyActive _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof DailyActive) assign((DailyActive)_o1_);
		else if (_o1_ instanceof DailyActive.Data) assign((DailyActive.Data)_o1_);
		else if (_o1_ instanceof DailyActive.Const) assign(((DailyActive.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(DailyActive _o_) {
		_o_._xdb_verify_unsafe_();
		activetimes = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.activetimes.entrySet())
			activetimes.put(_e_.getKey(), _e_.getValue());
		receivedbonus = new java.util.LinkedList<Integer>();
		receivedbonus.addAll(_o_.receivedbonus);
		activescores = _o_.activescores;
		undoactive = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.undoactive.entrySet())
			undoactive.put(_e_.getKey(), _e_.getValue());
		eventdonetimes = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.eventdonetimes.entrySet())
			eventdonetimes.put(_e_.getKey(), _e_.getValue());
		lastactivelvl = _o_.lastactivelvl;
	}

	private void assign(DailyActive.Data _o_) {
		activetimes = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.activetimes.entrySet())
			activetimes.put(_e_.getKey(), _e_.getValue());
		receivedbonus = new java.util.LinkedList<Integer>();
		receivedbonus.addAll(_o_.receivedbonus);
		activescores = _o_.activescores;
		undoactive = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.undoactive.entrySet())
			undoactive.put(_e_.getKey(), _e_.getValue());
		eventdonetimes = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.eventdonetimes.entrySet())
			eventdonetimes.put(_e_.getKey(), _e_.getValue());
		lastactivelvl = _o_.lastactivelvl;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(activetimes.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : activetimes.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivedbonus.size());
for (Integer _v_ : receivedbonus) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  3));_os_.marshal(activescores);
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(undoactive.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : undoactive.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(eventdonetimes.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : eventdonetimes.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  6));_os_.marshal(lastactivelvl);
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
		activetimes = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		activetimes.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivedbonus.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  3):activescores = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):activescores = _os_.unmarshal_short();
    				break;
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		undoactive = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		undoactive.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		eventdonetimes = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		eventdonetimes.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  6):lastactivelvl = _os_.unmarshal_int();
    				break;
    				case ( 6144|  6):lastactivelvl = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.DailyActive copy() {
		_xdb_verify_unsafe_();
		return new DailyActive(this);
	}

	@Override
	public xbean.DailyActive toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyActive toBean() {
		_xdb_verify_unsafe_();
		return new DailyActive(this); // same as copy()
	}

	@Override
	public xbean.DailyActive toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.DailyActive toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, Integer> getActivetimes() { // 各种活动完成的次数
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "activetimes"), activetimes);
	}

	@Override
	public java.util.Map<Integer, Integer> getActivetimesAsData() { // 各种活动完成的次数
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> activetimes;
		DailyActive _o_ = this;
		activetimes = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.activetimes.entrySet())
			activetimes.put(_e_.getKey(), _e_.getValue());
		return activetimes;
	}

	@Override
	public java.util.List<Integer> getReceivedbonus() { // 已领取的奖励类型
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "receivedbonus"), receivedbonus);
	}

	public java.util.List<Integer> getReceivedbonusAsData() { // 已领取的奖励类型
		_xdb_verify_unsafe_();
		java.util.List<Integer> receivedbonus;
		DailyActive _o_ = this;
		receivedbonus = new java.util.LinkedList<Integer>();
		receivedbonus.addAll(_o_.receivedbonus);
		return receivedbonus;
	}

	@Override
	public int getActivescores() { // 完成活动得到的总积分
		_xdb_verify_unsafe_();
		return activescores;
	}

	@Override
	public java.util.Map<Integer, Integer> getUndoactive() { // 每种活动当天未完成的次数
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "undoactive"), undoactive);
	}

	@Override
	public java.util.Map<Integer, Integer> getUndoactiveAsData() { // 每种活动当天未完成的次数
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> undoactive;
		DailyActive _o_ = this;
		undoactive = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.undoactive.entrySet())
			undoactive.put(_e_.getKey(), _e_.getValue());
		return undoactive;
	}

	@Override
	public java.util.Map<Integer, Integer> getEventdonetimes() { // 每种活动当天完成总次数，找回用
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "eventdonetimes"), eventdonetimes);
	}

	@Override
	public java.util.Map<Integer, Integer> getEventdonetimesAsData() { // 每种活动当天完成总次数，找回用
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> eventdonetimes;
		DailyActive _o_ = this;
		eventdonetimes = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.eventdonetimes.entrySet())
			eventdonetimes.put(_e_.getKey(), _e_.getValue());
		return eventdonetimes;
	}

	@Override
	public int getLastactivelvl() { // 昨日最后活跃的等级，找回用
		_xdb_verify_unsafe_();
		return lastactivelvl;
	}

	@Override
	public void setActivescores(int _v_) { // 完成活动得到的总积分
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "activescores") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, activescores) {
					public void rollback() { activescores = _xdb_saved; }
				};}});
		activescores = _v_;
	}

	@Override
	public void setLastactivelvl(int _v_) { // 昨日最后活跃的等级，找回用
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastactivelvl") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, lastactivelvl) {
					public void rollback() { lastactivelvl = _xdb_saved; }
				};}});
		lastactivelvl = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		DailyActive _o_ = null;
		if ( _o1_ instanceof DailyActive ) _o_ = (DailyActive)_o1_;
		else if ( _o1_ instanceof DailyActive.Const ) _o_ = ((DailyActive.Const)_o1_).nThis();
		else return false;
		if (!activetimes.equals(_o_.activetimes)) return false;
		if (!receivedbonus.equals(_o_.receivedbonus)) return false;
		if (activescores != _o_.activescores) return false;
		if (!undoactive.equals(_o_.undoactive)) return false;
		if (!eventdonetimes.equals(_o_.eventdonetimes)) return false;
		if (lastactivelvl != _o_.lastactivelvl) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += activetimes.hashCode();
		_h_ += receivedbonus.hashCode();
		_h_ += activescores;
		_h_ += undoactive.hashCode();
		_h_ += eventdonetimes.hashCode();
		_h_ += lastactivelvl;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(activetimes);
		_sb_.append(",");
		_sb_.append(receivedbonus);
		_sb_.append(",");
		_sb_.append(activescores);
		_sb_.append(",");
		_sb_.append(undoactive);
		_sb_.append(",");
		_sb_.append(eventdonetimes);
		_sb_.append(",");
		_sb_.append(lastactivelvl);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("activetimes"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("receivedbonus"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("activescores"));
		lb.add(new xdb.logs.ListenableMap().setVarName("undoactive"));
		lb.add(new xdb.logs.ListenableMap().setVarName("eventdonetimes"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastactivelvl"));
		return lb;
	}

	private class Const implements xbean.DailyActive {
		DailyActive nThis() {
			return DailyActive.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.DailyActive copy() {
			return DailyActive.this.copy();
		}

		@Override
		public xbean.DailyActive toData() {
			return DailyActive.this.toData();
		}

		public xbean.DailyActive toBean() {
			return DailyActive.this.toBean();
		}

		@Override
		public xbean.DailyActive toDataIf() {
			return DailyActive.this.toDataIf();
		}

		public xbean.DailyActive toBeanIf() {
			return DailyActive.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, Integer> getActivetimes() { // 各种活动完成的次数
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(activetimes);
		}

		@Override
		public java.util.Map<Integer, Integer> getActivetimesAsData() { // 各种活动完成的次数
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> activetimes;
			DailyActive _o_ = DailyActive.this;
			activetimes = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.activetimes.entrySet())
				activetimes.put(_e_.getKey(), _e_.getValue());
			return activetimes;
		}

		@Override
		public java.util.List<Integer> getReceivedbonus() { // 已领取的奖励类型
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(receivedbonus);
		}

		public java.util.List<Integer> getReceivedbonusAsData() { // 已领取的奖励类型
			_xdb_verify_unsafe_();
			java.util.List<Integer> receivedbonus;
			DailyActive _o_ = DailyActive.this;
		receivedbonus = new java.util.LinkedList<Integer>();
		receivedbonus.addAll(_o_.receivedbonus);
			return receivedbonus;
		}

		@Override
		public int getActivescores() { // 完成活动得到的总积分
			_xdb_verify_unsafe_();
			return activescores;
		}

		@Override
		public java.util.Map<Integer, Integer> getUndoactive() { // 每种活动当天未完成的次数
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(undoactive);
		}

		@Override
		public java.util.Map<Integer, Integer> getUndoactiveAsData() { // 每种活动当天未完成的次数
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> undoactive;
			DailyActive _o_ = DailyActive.this;
			undoactive = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.undoactive.entrySet())
				undoactive.put(_e_.getKey(), _e_.getValue());
			return undoactive;
		}

		@Override
		public java.util.Map<Integer, Integer> getEventdonetimes() { // 每种活动当天完成总次数，找回用
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(eventdonetimes);
		}

		@Override
		public java.util.Map<Integer, Integer> getEventdonetimesAsData() { // 每种活动当天完成总次数，找回用
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> eventdonetimes;
			DailyActive _o_ = DailyActive.this;
			eventdonetimes = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.eventdonetimes.entrySet())
				eventdonetimes.put(_e_.getKey(), _e_.getValue());
			return eventdonetimes;
		}

		@Override
		public int getLastactivelvl() { // 昨日最后活跃的等级，找回用
			_xdb_verify_unsafe_();
			return lastactivelvl;
		}

		@Override
		public void setActivescores(int _v_) { // 完成活动得到的总积分
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastactivelvl(int _v_) { // 昨日最后活跃的等级，找回用
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
			return DailyActive.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return DailyActive.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return DailyActive.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return DailyActive.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return DailyActive.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return DailyActive.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return DailyActive.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return DailyActive.this.hashCode();
		}

		@Override
		public String toString() {
			return DailyActive.this.toString();
		}

	}

	public static final class Data implements xbean.DailyActive {
		private java.util.HashMap<Integer, Integer> activetimes; // 各种活动完成的次数
		private java.util.LinkedList<Integer> receivedbonus; // 已领取的奖励类型
		private int activescores; // 完成活动得到的总积分
		private java.util.HashMap<Integer, Integer> undoactive; // 每种活动当天未完成的次数
		private java.util.HashMap<Integer, Integer> eventdonetimes; // 每种活动当天完成总次数，找回用
		private int lastactivelvl; // 昨日最后活跃的等级，找回用

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			activetimes = new java.util.HashMap<Integer, Integer>();
			receivedbonus = new java.util.LinkedList<Integer>();
			undoactive = new java.util.HashMap<Integer, Integer>();
			eventdonetimes = new java.util.HashMap<Integer, Integer>();
		}

		Data(xbean.DailyActive _o1_) {
			if (_o1_ instanceof DailyActive) assign((DailyActive)_o1_);
			else if (_o1_ instanceof DailyActive.Data) assign((DailyActive.Data)_o1_);
			else if (_o1_ instanceof DailyActive.Const) assign(((DailyActive.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(DailyActive _o_) {
			activetimes = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.activetimes.entrySet())
				activetimes.put(_e_.getKey(), _e_.getValue());
			receivedbonus = new java.util.LinkedList<Integer>();
			receivedbonus.addAll(_o_.receivedbonus);
			activescores = _o_.activescores;
			undoactive = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.undoactive.entrySet())
				undoactive.put(_e_.getKey(), _e_.getValue());
			eventdonetimes = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.eventdonetimes.entrySet())
				eventdonetimes.put(_e_.getKey(), _e_.getValue());
			lastactivelvl = _o_.lastactivelvl;
		}

		private void assign(DailyActive.Data _o_) {
			activetimes = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.activetimes.entrySet())
				activetimes.put(_e_.getKey(), _e_.getValue());
			receivedbonus = new java.util.LinkedList<Integer>();
			receivedbonus.addAll(_o_.receivedbonus);
			activescores = _o_.activescores;
			undoactive = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.undoactive.entrySet())
				undoactive.put(_e_.getKey(), _e_.getValue());
			eventdonetimes = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.eventdonetimes.entrySet())
				eventdonetimes.put(_e_.getKey(), _e_.getValue());
			lastactivelvl = _o_.lastactivelvl;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(activetimes.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : activetimes.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivedbonus.size());
for (Integer _v_ : receivedbonus) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  3));_os_.marshal(activescores);
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(undoactive.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : undoactive.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(eventdonetimes.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : eventdonetimes.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  6));_os_.marshal(lastactivelvl);
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
		activetimes = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		activetimes.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivedbonus.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192|  3):activescores = _os_.unmarshal_int();
					break;
					case ( 6144|  3):activescores = _os_.unmarshal_short();
					break;
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		undoactive = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		undoactive.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		eventdonetimes = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		eventdonetimes.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192|  6):lastactivelvl = _os_.unmarshal_int();
					break;
					case ( 6144|  6):lastactivelvl = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.DailyActive copy() {
			return new Data(this);
		}

		@Override
		public xbean.DailyActive toData() {
			return new Data(this);
		}

		public xbean.DailyActive toBean() {
			return new DailyActive(this, null, null);
		}

		@Override
		public xbean.DailyActive toDataIf() {
			return this;
		}

		public xbean.DailyActive toBeanIf() {
			return new DailyActive(this, null, null);
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
		public java.util.Map<Integer, Integer> getActivetimes() { // 各种活动完成的次数
			return activetimes;
		}

		@Override
		public java.util.Map<Integer, Integer> getActivetimesAsData() { // 各种活动完成的次数
			return activetimes;
		}

		@Override
		public java.util.List<Integer> getReceivedbonus() { // 已领取的奖励类型
			return receivedbonus;
		}

		@Override
		public java.util.List<Integer> getReceivedbonusAsData() { // 已领取的奖励类型
			return receivedbonus;
		}

		@Override
		public int getActivescores() { // 完成活动得到的总积分
			return activescores;
		}

		@Override
		public java.util.Map<Integer, Integer> getUndoactive() { // 每种活动当天未完成的次数
			return undoactive;
		}

		@Override
		public java.util.Map<Integer, Integer> getUndoactiveAsData() { // 每种活动当天未完成的次数
			return undoactive;
		}

		@Override
		public java.util.Map<Integer, Integer> getEventdonetimes() { // 每种活动当天完成总次数，找回用
			return eventdonetimes;
		}

		@Override
		public java.util.Map<Integer, Integer> getEventdonetimesAsData() { // 每种活动当天完成总次数，找回用
			return eventdonetimes;
		}

		@Override
		public int getLastactivelvl() { // 昨日最后活跃的等级，找回用
			return lastactivelvl;
		}

		@Override
		public void setActivescores(int _v_) { // 完成活动得到的总积分
			activescores = _v_;
		}

		@Override
		public void setLastactivelvl(int _v_) { // 昨日最后活跃的等级，找回用
			lastactivelvl = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof DailyActive.Data)) return false;
			DailyActive.Data _o_ = (DailyActive.Data) _o1_;
			if (!activetimes.equals(_o_.activetimes)) return false;
			if (!receivedbonus.equals(_o_.receivedbonus)) return false;
			if (activescores != _o_.activescores) return false;
			if (!undoactive.equals(_o_.undoactive)) return false;
			if (!eventdonetimes.equals(_o_.eventdonetimes)) return false;
			if (lastactivelvl != _o_.lastactivelvl) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += activetimes.hashCode();
			_h_ += receivedbonus.hashCode();
			_h_ += activescores;
			_h_ += undoactive.hashCode();
			_h_ += eventdonetimes.hashCode();
			_h_ += lastactivelvl;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(activetimes);
			_sb_.append(",");
			_sb_.append(receivedbonus);
			_sb_.append(",");
			_sb_.append(activescores);
			_sb_.append(",");
			_sb_.append(undoactive);
			_sb_.append(",");
			_sb_.append(eventdonetimes);
			_sb_.append(",");
			_sb_.append(lastactivelvl);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
