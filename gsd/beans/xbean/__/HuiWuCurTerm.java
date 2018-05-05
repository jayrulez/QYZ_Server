
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class HuiWuCurTerm extends xdb.XBean implements xbean.HuiWuCurTerm {
	private int termid; // 
	private long opentime; // 
	private long endtime; // 
	private long guessendtime; // 
	private int stage; // cfg.huiwu.Stage
	private int roundindex; // 
	private int roundstage; // cfg.huiwu.RoundStage
	private long battlebegintime; // 
	private java.util.HashMap<Integer, xbean.HuiWuProfessionTerm> terminfobyprofession; // 
	private java.util.HashMap<Long, Long> guess; // 投票
	private java.util.HashMap<Long, Integer> continuouschampions; // roleid -> continue win counts

	@Override
	public void _reset_unsafe_() {
		termid = 0;
		opentime = 0L;
		endtime = 0L;
		guessendtime = 0L;
		stage = 0;
		roundindex = 0;
		roundstage = 0;
		battlebegintime = 0L;
		terminfobyprofession.clear();
		guess.clear();
		continuouschampions.clear();
	}

	HuiWuCurTerm(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		terminfobyprofession = new java.util.HashMap<Integer, xbean.HuiWuProfessionTerm>();
		guess = new java.util.HashMap<Long, Long>();
		continuouschampions = new java.util.HashMap<Long, Integer>();
	}

	public HuiWuCurTerm() {
		this(0, null, null);
	}

	public HuiWuCurTerm(HuiWuCurTerm _o_) {
		this(_o_, null, null);
	}

	HuiWuCurTerm(xbean.HuiWuCurTerm _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof HuiWuCurTerm) assign((HuiWuCurTerm)_o1_);
		else if (_o1_ instanceof HuiWuCurTerm.Data) assign((HuiWuCurTerm.Data)_o1_);
		else if (_o1_ instanceof HuiWuCurTerm.Const) assign(((HuiWuCurTerm.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(HuiWuCurTerm _o_) {
		_o_._xdb_verify_unsafe_();
		termid = _o_.termid;
		opentime = _o_.opentime;
		endtime = _o_.endtime;
		guessendtime = _o_.guessendtime;
		stage = _o_.stage;
		roundindex = _o_.roundindex;
		roundstage = _o_.roundstage;
		battlebegintime = _o_.battlebegintime;
		terminfobyprofession = new java.util.HashMap<Integer, xbean.HuiWuProfessionTerm>();
		for (java.util.Map.Entry<Integer, xbean.HuiWuProfessionTerm> _e_ : _o_.terminfobyprofession.entrySet())
			terminfobyprofession.put(_e_.getKey(), new HuiWuProfessionTerm(_e_.getValue(), this, "terminfobyprofession"));
		guess = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.guess.entrySet())
			guess.put(_e_.getKey(), _e_.getValue());
		continuouschampions = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.continuouschampions.entrySet())
			continuouschampions.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(HuiWuCurTerm.Data _o_) {
		termid = _o_.termid;
		opentime = _o_.opentime;
		endtime = _o_.endtime;
		guessendtime = _o_.guessendtime;
		stage = _o_.stage;
		roundindex = _o_.roundindex;
		roundstage = _o_.roundstage;
		battlebegintime = _o_.battlebegintime;
		terminfobyprofession = new java.util.HashMap<Integer, xbean.HuiWuProfessionTerm>();
		for (java.util.Map.Entry<Integer, xbean.HuiWuProfessionTerm> _e_ : _o_.terminfobyprofession.entrySet())
			terminfobyprofession.put(_e_.getKey(), new HuiWuProfessionTerm(_e_.getValue(), this, "terminfobyprofession"));
		guess = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.guess.entrySet())
			guess.put(_e_.getKey(), _e_.getValue());
		continuouschampions = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.continuouschampions.entrySet())
			continuouschampions.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)11);
    _os_.marshal((short)( 8192|  1));_os_.marshal(termid);
    _os_.marshal((short)(10240|  2));_os_.marshal(opentime);
    _os_.marshal((short)(10240|  3));_os_.marshal(endtime);
    _os_.marshal((short)(10240|  8));_os_.marshal(guessendtime);
    _os_.marshal((short)( 8192|  4));_os_.marshal(stage);
    _os_.marshal((short)( 8192| 12));_os_.marshal(roundindex);
    _os_.marshal((short)( 8192| 13));_os_.marshal(roundstage);
    _os_.marshal((short)(10240| 14));_os_.marshal(battlebegintime);
    _os_.marshal((short)(24576| 11));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(terminfobyprofession.size());
for (java.util.Map.Entry<Integer, xbean.HuiWuProfessionTerm> _e_ : terminfobyprofession.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  9));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(guess.size());
for (java.util.Map.Entry<Long, Long> _e_ : guess.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(continuouschampions.size());
for (java.util.Map.Entry<Long, Integer> _e_ : continuouschampions.entrySet())
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
    				case ( 8192|  1):termid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):termid = _os_.unmarshal_short();
    				break;
    				case (10240|  2):opentime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):opentime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):opentime = _os_.unmarshal_int();
    				break;
    				case (10240|  3):endtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):endtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):endtime = _os_.unmarshal_int();
    				break;
    				case (10240|  8):guessendtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  8):guessendtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  8):guessendtime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  4):stage = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):stage = _os_.unmarshal_short();
    				break;
    				case ( 8192| 12):roundindex = _os_.unmarshal_int();
    				break;
    				case ( 6144| 12):roundindex = _os_.unmarshal_short();
    				break;
    				case ( 8192| 13):roundstage = _os_.unmarshal_int();
    				break;
    				case ( 6144| 13):roundstage = _os_.unmarshal_short();
    				break;
    				case (10240| 14):battlebegintime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 14):battlebegintime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 14):battlebegintime = _os_.unmarshal_int();
    				break;
    				case (24576| 11):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		terminfobyprofession = new java.util.HashMap<Integer, xbean.HuiWuProfessionTerm>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.HuiWuProfessionTerm _v_ = new HuiWuProfessionTerm(0, this, "terminfobyprofession");
		_v_.unmarshal(_os_);
		terminfobyprofession.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  9):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		guess = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		guess.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		continuouschampions = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		continuouschampions.put(_k_, _v_);
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
	public xbean.HuiWuCurTerm copy() {
		_xdb_verify_unsafe_();
		return new HuiWuCurTerm(this);
	}

	@Override
	public xbean.HuiWuCurTerm toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuCurTerm toBean() {
		_xdb_verify_unsafe_();
		return new HuiWuCurTerm(this); // same as copy()
	}

	@Override
	public xbean.HuiWuCurTerm toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.HuiWuCurTerm toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getTermid() { // 
		_xdb_verify_unsafe_();
		return termid;
	}

	@Override
	public long getOpentime() { // 
		_xdb_verify_unsafe_();
		return opentime;
	}

	@Override
	public long getEndtime() { // 
		_xdb_verify_unsafe_();
		return endtime;
	}

	@Override
	public long getGuessendtime() { // 
		_xdb_verify_unsafe_();
		return guessendtime;
	}

	@Override
	public int getStage() { // cfg.huiwu.Stage
		_xdb_verify_unsafe_();
		return stage;
	}

	@Override
	public int getRoundindex() { // 
		_xdb_verify_unsafe_();
		return roundindex;
	}

	@Override
	public int getRoundstage() { // cfg.huiwu.RoundStage
		_xdb_verify_unsafe_();
		return roundstage;
	}

	@Override
	public long getBattlebegintime() { // 
		_xdb_verify_unsafe_();
		return battlebegintime;
	}

	@Override
	public java.util.Map<Integer, xbean.HuiWuProfessionTerm> getTerminfobyprofession() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "terminfobyprofession"), terminfobyprofession);
	}

	@Override
	public java.util.Map<Integer, xbean.HuiWuProfessionTerm> getTerminfobyprofessionAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.HuiWuProfessionTerm> terminfobyprofession;
		HuiWuCurTerm _o_ = this;
		terminfobyprofession = new java.util.HashMap<Integer, xbean.HuiWuProfessionTerm>();
		for (java.util.Map.Entry<Integer, xbean.HuiWuProfessionTerm> _e_ : _o_.terminfobyprofession.entrySet())
			terminfobyprofession.put(_e_.getKey(), new HuiWuProfessionTerm.Data(_e_.getValue()));
		return terminfobyprofession;
	}

	@Override
	public java.util.Map<Long, Long> getGuess() { // 投票
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "guess"), guess);
	}

	@Override
	public java.util.Map<Long, Long> getGuessAsData() { // 投票
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> guess;
		HuiWuCurTerm _o_ = this;
		guess = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.guess.entrySet())
			guess.put(_e_.getKey(), _e_.getValue());
		return guess;
	}

	@Override
	public java.util.Map<Long, Integer> getContinuouschampions() { // roleid -> continue win counts
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "continuouschampions"), continuouschampions);
	}

	@Override
	public java.util.Map<Long, Integer> getContinuouschampionsAsData() { // roleid -> continue win counts
		_xdb_verify_unsafe_();
		java.util.Map<Long, Integer> continuouschampions;
		HuiWuCurTerm _o_ = this;
		continuouschampions = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.continuouschampions.entrySet())
			continuouschampions.put(_e_.getKey(), _e_.getValue());
		return continuouschampions;
	}

	@Override
	public void setTermid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "termid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, termid) {
					public void rollback() { termid = _xdb_saved; }
				};}});
		termid = _v_;
	}

	@Override
	public void setOpentime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "opentime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, opentime) {
					public void rollback() { opentime = _xdb_saved; }
				};}});
		opentime = _v_;
	}

	@Override
	public void setEndtime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "endtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, endtime) {
					public void rollback() { endtime = _xdb_saved; }
				};}});
		endtime = _v_;
	}

	@Override
	public void setGuessendtime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "guessendtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, guessendtime) {
					public void rollback() { guessendtime = _xdb_saved; }
				};}});
		guessendtime = _v_;
	}

	@Override
	public void setStage(int _v_) { // cfg.huiwu.Stage
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "stage") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, stage) {
					public void rollback() { stage = _xdb_saved; }
				};}});
		stage = _v_;
	}

	@Override
	public void setRoundindex(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roundindex") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, roundindex) {
					public void rollback() { roundindex = _xdb_saved; }
				};}});
		roundindex = _v_;
	}

	@Override
	public void setRoundstage(int _v_) { // cfg.huiwu.RoundStage
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roundstage") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, roundstage) {
					public void rollback() { roundstage = _xdb_saved; }
				};}});
		roundstage = _v_;
	}

	@Override
	public void setBattlebegintime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "battlebegintime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, battlebegintime) {
					public void rollback() { battlebegintime = _xdb_saved; }
				};}});
		battlebegintime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		HuiWuCurTerm _o_ = null;
		if ( _o1_ instanceof HuiWuCurTerm ) _o_ = (HuiWuCurTerm)_o1_;
		else if ( _o1_ instanceof HuiWuCurTerm.Const ) _o_ = ((HuiWuCurTerm.Const)_o1_).nThis();
		else return false;
		if (termid != _o_.termid) return false;
		if (opentime != _o_.opentime) return false;
		if (endtime != _o_.endtime) return false;
		if (guessendtime != _o_.guessendtime) return false;
		if (stage != _o_.stage) return false;
		if (roundindex != _o_.roundindex) return false;
		if (roundstage != _o_.roundstage) return false;
		if (battlebegintime != _o_.battlebegintime) return false;
		if (!terminfobyprofession.equals(_o_.terminfobyprofession)) return false;
		if (!guess.equals(_o_.guess)) return false;
		if (!continuouschampions.equals(_o_.continuouschampions)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += termid;
		_h_ += opentime;
		_h_ += endtime;
		_h_ += guessendtime;
		_h_ += stage;
		_h_ += roundindex;
		_h_ += roundstage;
		_h_ += battlebegintime;
		_h_ += terminfobyprofession.hashCode();
		_h_ += guess.hashCode();
		_h_ += continuouschampions.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(termid);
		_sb_.append(",");
		_sb_.append(opentime);
		_sb_.append(",");
		_sb_.append(endtime);
		_sb_.append(",");
		_sb_.append(guessendtime);
		_sb_.append(",");
		_sb_.append(stage);
		_sb_.append(",");
		_sb_.append(roundindex);
		_sb_.append(",");
		_sb_.append(roundstage);
		_sb_.append(",");
		_sb_.append(battlebegintime);
		_sb_.append(",");
		_sb_.append(terminfobyprofession);
		_sb_.append(",");
		_sb_.append(guess);
		_sb_.append(",");
		_sb_.append(continuouschampions);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("termid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("opentime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("endtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("guessendtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("stage"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("roundindex"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("roundstage"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("battlebegintime"));
		lb.add(new xdb.logs.ListenableMap().setVarName("terminfobyprofession"));
		lb.add(new xdb.logs.ListenableMap().setVarName("guess"));
		lb.add(new xdb.logs.ListenableMap().setVarName("continuouschampions"));
		return lb;
	}

	private class Const implements xbean.HuiWuCurTerm {
		HuiWuCurTerm nThis() {
			return HuiWuCurTerm.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.HuiWuCurTerm copy() {
			return HuiWuCurTerm.this.copy();
		}

		@Override
		public xbean.HuiWuCurTerm toData() {
			return HuiWuCurTerm.this.toData();
		}

		public xbean.HuiWuCurTerm toBean() {
			return HuiWuCurTerm.this.toBean();
		}

		@Override
		public xbean.HuiWuCurTerm toDataIf() {
			return HuiWuCurTerm.this.toDataIf();
		}

		public xbean.HuiWuCurTerm toBeanIf() {
			return HuiWuCurTerm.this.toBeanIf();
		}

		@Override
		public int getTermid() { // 
			_xdb_verify_unsafe_();
			return termid;
		}

		@Override
		public long getOpentime() { // 
			_xdb_verify_unsafe_();
			return opentime;
		}

		@Override
		public long getEndtime() { // 
			_xdb_verify_unsafe_();
			return endtime;
		}

		@Override
		public long getGuessendtime() { // 
			_xdb_verify_unsafe_();
			return guessendtime;
		}

		@Override
		public int getStage() { // cfg.huiwu.Stage
			_xdb_verify_unsafe_();
			return stage;
		}

		@Override
		public int getRoundindex() { // 
			_xdb_verify_unsafe_();
			return roundindex;
		}

		@Override
		public int getRoundstage() { // cfg.huiwu.RoundStage
			_xdb_verify_unsafe_();
			return roundstage;
		}

		@Override
		public long getBattlebegintime() { // 
			_xdb_verify_unsafe_();
			return battlebegintime;
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuProfessionTerm> getTerminfobyprofession() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(terminfobyprofession);
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuProfessionTerm> getTerminfobyprofessionAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.HuiWuProfessionTerm> terminfobyprofession;
			HuiWuCurTerm _o_ = HuiWuCurTerm.this;
			terminfobyprofession = new java.util.HashMap<Integer, xbean.HuiWuProfessionTerm>();
			for (java.util.Map.Entry<Integer, xbean.HuiWuProfessionTerm> _e_ : _o_.terminfobyprofession.entrySet())
				terminfobyprofession.put(_e_.getKey(), new HuiWuProfessionTerm.Data(_e_.getValue()));
			return terminfobyprofession;
		}

		@Override
		public java.util.Map<Long, Long> getGuess() { // 投票
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(guess);
		}

		@Override
		public java.util.Map<Long, Long> getGuessAsData() { // 投票
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> guess;
			HuiWuCurTerm _o_ = HuiWuCurTerm.this;
			guess = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.guess.entrySet())
				guess.put(_e_.getKey(), _e_.getValue());
			return guess;
		}

		@Override
		public java.util.Map<Long, Integer> getContinuouschampions() { // roleid -> continue win counts
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(continuouschampions);
		}

		@Override
		public java.util.Map<Long, Integer> getContinuouschampionsAsData() { // roleid -> continue win counts
			_xdb_verify_unsafe_();
			java.util.Map<Long, Integer> continuouschampions;
			HuiWuCurTerm _o_ = HuiWuCurTerm.this;
			continuouschampions = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.continuouschampions.entrySet())
				continuouschampions.put(_e_.getKey(), _e_.getValue());
			return continuouschampions;
		}

		@Override
		public void setTermid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setOpentime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setEndtime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGuessendtime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setStage(int _v_) { // cfg.huiwu.Stage
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRoundindex(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRoundstage(int _v_) { // cfg.huiwu.RoundStage
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBattlebegintime(long _v_) { // 
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
			return HuiWuCurTerm.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return HuiWuCurTerm.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return HuiWuCurTerm.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return HuiWuCurTerm.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return HuiWuCurTerm.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return HuiWuCurTerm.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return HuiWuCurTerm.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return HuiWuCurTerm.this.hashCode();
		}

		@Override
		public String toString() {
			return HuiWuCurTerm.this.toString();
		}

	}

	public static final class Data implements xbean.HuiWuCurTerm {
		private int termid; // 
		private long opentime; // 
		private long endtime; // 
		private long guessendtime; // 
		private int stage; // cfg.huiwu.Stage
		private int roundindex; // 
		private int roundstage; // cfg.huiwu.RoundStage
		private long battlebegintime; // 
		private java.util.HashMap<Integer, xbean.HuiWuProfessionTerm> terminfobyprofession; // 
		private java.util.HashMap<Long, Long> guess; // 投票
		private java.util.HashMap<Long, Integer> continuouschampions; // roleid -> continue win counts

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			terminfobyprofession = new java.util.HashMap<Integer, xbean.HuiWuProfessionTerm>();
			guess = new java.util.HashMap<Long, Long>();
			continuouschampions = new java.util.HashMap<Long, Integer>();
		}

		Data(xbean.HuiWuCurTerm _o1_) {
			if (_o1_ instanceof HuiWuCurTerm) assign((HuiWuCurTerm)_o1_);
			else if (_o1_ instanceof HuiWuCurTerm.Data) assign((HuiWuCurTerm.Data)_o1_);
			else if (_o1_ instanceof HuiWuCurTerm.Const) assign(((HuiWuCurTerm.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(HuiWuCurTerm _o_) {
			termid = _o_.termid;
			opentime = _o_.opentime;
			endtime = _o_.endtime;
			guessendtime = _o_.guessendtime;
			stage = _o_.stage;
			roundindex = _o_.roundindex;
			roundstage = _o_.roundstage;
			battlebegintime = _o_.battlebegintime;
			terminfobyprofession = new java.util.HashMap<Integer, xbean.HuiWuProfessionTerm>();
			for (java.util.Map.Entry<Integer, xbean.HuiWuProfessionTerm> _e_ : _o_.terminfobyprofession.entrySet())
				terminfobyprofession.put(_e_.getKey(), new HuiWuProfessionTerm.Data(_e_.getValue()));
			guess = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.guess.entrySet())
				guess.put(_e_.getKey(), _e_.getValue());
			continuouschampions = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.continuouschampions.entrySet())
				continuouschampions.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(HuiWuCurTerm.Data _o_) {
			termid = _o_.termid;
			opentime = _o_.opentime;
			endtime = _o_.endtime;
			guessendtime = _o_.guessendtime;
			stage = _o_.stage;
			roundindex = _o_.roundindex;
			roundstage = _o_.roundstage;
			battlebegintime = _o_.battlebegintime;
			terminfobyprofession = new java.util.HashMap<Integer, xbean.HuiWuProfessionTerm>();
			for (java.util.Map.Entry<Integer, xbean.HuiWuProfessionTerm> _e_ : _o_.terminfobyprofession.entrySet())
				terminfobyprofession.put(_e_.getKey(), new HuiWuProfessionTerm.Data(_e_.getValue()));
			guess = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.guess.entrySet())
				guess.put(_e_.getKey(), _e_.getValue());
			continuouschampions = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.continuouschampions.entrySet())
				continuouschampions.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)11);
	_os_.marshal((short)( 8192|  1));_os_.marshal(termid);
	_os_.marshal((short)(10240|  2));_os_.marshal(opentime);
	_os_.marshal((short)(10240|  3));_os_.marshal(endtime);
	_os_.marshal((short)(10240|  8));_os_.marshal(guessendtime);
	_os_.marshal((short)( 8192|  4));_os_.marshal(stage);
	_os_.marshal((short)( 8192| 12));_os_.marshal(roundindex);
	_os_.marshal((short)( 8192| 13));_os_.marshal(roundstage);
	_os_.marshal((short)(10240| 14));_os_.marshal(battlebegintime);
	_os_.marshal((short)(24576| 11));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(terminfobyprofession.size());
for (java.util.Map.Entry<Integer, xbean.HuiWuProfessionTerm> _e_ : terminfobyprofession.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  9));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(guess.size());
for (java.util.Map.Entry<Long, Long> _e_ : guess.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(continuouschampions.size());
for (java.util.Map.Entry<Long, Integer> _e_ : continuouschampions.entrySet())
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
					case ( 8192|  1):termid = _os_.unmarshal_int();
					break;
					case ( 6144|  1):termid = _os_.unmarshal_short();
					break;
					case (10240|  2):opentime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):opentime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):opentime = _os_.unmarshal_int();
					break;
					case (10240|  3):endtime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):endtime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):endtime = _os_.unmarshal_int();
					break;
					case (10240|  8):guessendtime = _os_.unmarshal_long();
					break;
					case ( 6144|  8):guessendtime = _os_.unmarshal_short();
					break;
					case ( 8192|  8):guessendtime = _os_.unmarshal_int();
					break;
					case ( 8192|  4):stage = _os_.unmarshal_int();
					break;
					case ( 6144|  4):stage = _os_.unmarshal_short();
					break;
					case ( 8192| 12):roundindex = _os_.unmarshal_int();
					break;
					case ( 6144| 12):roundindex = _os_.unmarshal_short();
					break;
					case ( 8192| 13):roundstage = _os_.unmarshal_int();
					break;
					case ( 6144| 13):roundstage = _os_.unmarshal_short();
					break;
					case (10240| 14):battlebegintime = _os_.unmarshal_long();
					break;
					case ( 6144| 14):battlebegintime = _os_.unmarshal_short();
					break;
					case ( 8192| 14):battlebegintime = _os_.unmarshal_int();
					break;
					case (24576| 11):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		terminfobyprofession = new java.util.HashMap<Integer, xbean.HuiWuProfessionTerm>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.HuiWuProfessionTerm _v_ = xbean.Pod.newHuiWuProfessionTermData();
		_v_.unmarshal(_os_);
		terminfobyprofession.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  9):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		guess = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		guess.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		continuouschampions = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		continuouschampions.put(_k_, _v_);
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
		public xbean.HuiWuCurTerm copy() {
			return new Data(this);
		}

		@Override
		public xbean.HuiWuCurTerm toData() {
			return new Data(this);
		}

		public xbean.HuiWuCurTerm toBean() {
			return new HuiWuCurTerm(this, null, null);
		}

		@Override
		public xbean.HuiWuCurTerm toDataIf() {
			return this;
		}

		public xbean.HuiWuCurTerm toBeanIf() {
			return new HuiWuCurTerm(this, null, null);
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
		public int getTermid() { // 
			return termid;
		}

		@Override
		public long getOpentime() { // 
			return opentime;
		}

		@Override
		public long getEndtime() { // 
			return endtime;
		}

		@Override
		public long getGuessendtime() { // 
			return guessendtime;
		}

		@Override
		public int getStage() { // cfg.huiwu.Stage
			return stage;
		}

		@Override
		public int getRoundindex() { // 
			return roundindex;
		}

		@Override
		public int getRoundstage() { // cfg.huiwu.RoundStage
			return roundstage;
		}

		@Override
		public long getBattlebegintime() { // 
			return battlebegintime;
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuProfessionTerm> getTerminfobyprofession() { // 
			return terminfobyprofession;
		}

		@Override
		public java.util.Map<Integer, xbean.HuiWuProfessionTerm> getTerminfobyprofessionAsData() { // 
			return terminfobyprofession;
		}

		@Override
		public java.util.Map<Long, Long> getGuess() { // 投票
			return guess;
		}

		@Override
		public java.util.Map<Long, Long> getGuessAsData() { // 投票
			return guess;
		}

		@Override
		public java.util.Map<Long, Integer> getContinuouschampions() { // roleid -> continue win counts
			return continuouschampions;
		}

		@Override
		public java.util.Map<Long, Integer> getContinuouschampionsAsData() { // roleid -> continue win counts
			return continuouschampions;
		}

		@Override
		public void setTermid(int _v_) { // 
			termid = _v_;
		}

		@Override
		public void setOpentime(long _v_) { // 
			opentime = _v_;
		}

		@Override
		public void setEndtime(long _v_) { // 
			endtime = _v_;
		}

		@Override
		public void setGuessendtime(long _v_) { // 
			guessendtime = _v_;
		}

		@Override
		public void setStage(int _v_) { // cfg.huiwu.Stage
			stage = _v_;
		}

		@Override
		public void setRoundindex(int _v_) { // 
			roundindex = _v_;
		}

		@Override
		public void setRoundstage(int _v_) { // cfg.huiwu.RoundStage
			roundstage = _v_;
		}

		@Override
		public void setBattlebegintime(long _v_) { // 
			battlebegintime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof HuiWuCurTerm.Data)) return false;
			HuiWuCurTerm.Data _o_ = (HuiWuCurTerm.Data) _o1_;
			if (termid != _o_.termid) return false;
			if (opentime != _o_.opentime) return false;
			if (endtime != _o_.endtime) return false;
			if (guessendtime != _o_.guessendtime) return false;
			if (stage != _o_.stage) return false;
			if (roundindex != _o_.roundindex) return false;
			if (roundstage != _o_.roundstage) return false;
			if (battlebegintime != _o_.battlebegintime) return false;
			if (!terminfobyprofession.equals(_o_.terminfobyprofession)) return false;
			if (!guess.equals(_o_.guess)) return false;
			if (!continuouschampions.equals(_o_.continuouschampions)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += termid;
			_h_ += opentime;
			_h_ += endtime;
			_h_ += guessendtime;
			_h_ += stage;
			_h_ += roundindex;
			_h_ += roundstage;
			_h_ += battlebegintime;
			_h_ += terminfobyprofession.hashCode();
			_h_ += guess.hashCode();
			_h_ += continuouschampions.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(termid);
			_sb_.append(",");
			_sb_.append(opentime);
			_sb_.append(",");
			_sb_.append(endtime);
			_sb_.append(",");
			_sb_.append(guessendtime);
			_sb_.append(",");
			_sb_.append(stage);
			_sb_.append(",");
			_sb_.append(roundindex);
			_sb_.append(",");
			_sb_.append(roundstage);
			_sb_.append(",");
			_sb_.append(battlebegintime);
			_sb_.append(",");
			_sb_.append(terminfobyprofession);
			_sb_.append(",");
			_sb_.append(guess);
			_sb_.append(",");
			_sb_.append(continuouschampions);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
