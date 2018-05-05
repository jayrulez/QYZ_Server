
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RolePay extends xdb.XBean implements xbean.RolePay {
	private boolean cangetfirstbonus; // 是否首冲过
	private boolean isfirstpayused; // 是否领取过首冲奖励
	private java.util.HashMap<Integer, Boolean> isentryfirstpay; // 每个充值条目是否首冲过
	private long totalpay; // 累计充值，以分为单位，充值时花费人民币，得到元宝和绑定元宝
	private long lastrefreshtime; // 上次刷新时间
	private int dailypaystatus; // 每日充值状态，分为未充值，充值没领奖，充值已领奖
	private long dailytotalpay; // 每日累计充值
	private int dailytotalpaystatus; // 每日累计充值状态
	private long roleallpay; // 角色所有充钱的记录，包含月卡和成长计划
	private int dailyactivepay; // 每日特价购是否使用过
	private boolean hasgotpayreturn; // 已经得到充值返还
	private long totalyunbao; // 
	private long totalbindyuanbao; // 
	private long totalvipexp; // 

	@Override
	public void _reset_unsafe_() {
		cangetfirstbonus = false;
		isfirstpayused = false;
		isentryfirstpay.clear();
		totalpay = 0L;
		lastrefreshtime = 0L;
		dailypaystatus = 0;
		dailytotalpay = 0L;
		dailytotalpaystatus = 0;
		roleallpay = 0L;
		dailyactivepay = 0;
		hasgotpayreturn = false;
		totalyunbao = 0L;
		totalbindyuanbao = 0L;
		totalvipexp = 0L;
	}

	RolePay(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		isentryfirstpay = new java.util.HashMap<Integer, Boolean>();
	}

	public RolePay() {
		this(0, null, null);
	}

	public RolePay(RolePay _o_) {
		this(_o_, null, null);
	}

	RolePay(xbean.RolePay _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RolePay) assign((RolePay)_o1_);
		else if (_o1_ instanceof RolePay.Data) assign((RolePay.Data)_o1_);
		else if (_o1_ instanceof RolePay.Const) assign(((RolePay.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RolePay _o_) {
		_o_._xdb_verify_unsafe_();
		cangetfirstbonus = _o_.cangetfirstbonus;
		isfirstpayused = _o_.isfirstpayused;
		isentryfirstpay = new java.util.HashMap<Integer, Boolean>();
		for (java.util.Map.Entry<Integer, Boolean> _e_ : _o_.isentryfirstpay.entrySet())
			isentryfirstpay.put(_e_.getKey(), _e_.getValue());
		totalpay = _o_.totalpay;
		lastrefreshtime = _o_.lastrefreshtime;
		dailypaystatus = _o_.dailypaystatus;
		dailytotalpay = _o_.dailytotalpay;
		dailytotalpaystatus = _o_.dailytotalpaystatus;
		roleallpay = _o_.roleallpay;
		dailyactivepay = _o_.dailyactivepay;
		hasgotpayreturn = _o_.hasgotpayreturn;
		totalyunbao = _o_.totalyunbao;
		totalbindyuanbao = _o_.totalbindyuanbao;
		totalvipexp = _o_.totalvipexp;
	}

	private void assign(RolePay.Data _o_) {
		cangetfirstbonus = _o_.cangetfirstbonus;
		isfirstpayused = _o_.isfirstpayused;
		isentryfirstpay = new java.util.HashMap<Integer, Boolean>();
		for (java.util.Map.Entry<Integer, Boolean> _e_ : _o_.isentryfirstpay.entrySet())
			isentryfirstpay.put(_e_.getKey(), _e_.getValue());
		totalpay = _o_.totalpay;
		lastrefreshtime = _o_.lastrefreshtime;
		dailypaystatus = _o_.dailypaystatus;
		dailytotalpay = _o_.dailytotalpay;
		dailytotalpaystatus = _o_.dailytotalpaystatus;
		roleallpay = _o_.roleallpay;
		dailyactivepay = _o_.dailyactivepay;
		hasgotpayreturn = _o_.hasgotpayreturn;
		totalyunbao = _o_.totalyunbao;
		totalbindyuanbao = _o_.totalbindyuanbao;
		totalvipexp = _o_.totalvipexp;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)14);
    _os_.marshal((short)( 2048| 10));_os_.marshal(cangetfirstbonus);
    _os_.marshal((short)( 2048|  9));_os_.marshal(isfirstpayused);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(isentryfirstpay.size());
for (java.util.Map.Entry<Integer, Boolean> _e_ : isentryfirstpay.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240|  7));_os_.marshal(totalpay);
    _os_.marshal((short)(10240|  3));_os_.marshal(lastrefreshtime);
    _os_.marshal((short)( 8192|  4));_os_.marshal(dailypaystatus);
    _os_.marshal((short)(10240|  8));_os_.marshal(dailytotalpay);
    _os_.marshal((short)( 8192|  6));_os_.marshal(dailytotalpaystatus);
    _os_.marshal((short)(10240| 11));_os_.marshal(roleallpay);
    _os_.marshal((short)( 8192| 17));_os_.marshal(dailyactivepay);
    _os_.marshal((short)( 2048| 12));_os_.marshal(hasgotpayreturn);
    _os_.marshal((short)(10240| 14));_os_.marshal(totalyunbao);
    _os_.marshal((short)(10240| 15));_os_.marshal(totalbindyuanbao);
    _os_.marshal((short)(10240| 16));_os_.marshal(totalvipexp);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 2048| 10):cangetfirstbonus = _os_.unmarshal_boolean();
    				break;
    				case ( 2048|  9):isfirstpayused = _os_.unmarshal_boolean();
    				break;
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		isentryfirstpay = new java.util.HashMap<Integer, Boolean>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		boolean _v_ = false;
		_v_ = _os_.unmarshal_boolean();
		isentryfirstpay.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (10240|  7):totalpay = _os_.unmarshal_long();
    				break;
    				case ( 6144|  7):totalpay = _os_.unmarshal_short();
    				break;
    				case ( 8192|  7):totalpay = _os_.unmarshal_int();
    				break;
    				case (10240|  3):lastrefreshtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):lastrefreshtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):lastrefreshtime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  4):dailypaystatus = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):dailypaystatus = _os_.unmarshal_short();
    				break;
    				case (10240|  8):dailytotalpay = _os_.unmarshal_long();
    				break;
    				case ( 6144|  8):dailytotalpay = _os_.unmarshal_short();
    				break;
    				case ( 8192|  8):dailytotalpay = _os_.unmarshal_int();
    				break;
    				case ( 8192|  6):dailytotalpaystatus = _os_.unmarshal_int();
    				break;
    				case ( 6144|  6):dailytotalpaystatus = _os_.unmarshal_short();
    				break;
    				case (10240| 11):roleallpay = _os_.unmarshal_long();
    				break;
    				case ( 6144| 11):roleallpay = _os_.unmarshal_short();
    				break;
    				case ( 8192| 11):roleallpay = _os_.unmarshal_int();
    				break;
    				case ( 8192| 17):dailyactivepay = _os_.unmarshal_int();
    				break;
    				case ( 6144| 17):dailyactivepay = _os_.unmarshal_short();
    				break;
    				case ( 2048| 12):hasgotpayreturn = _os_.unmarshal_boolean();
    				break;
    				case (10240| 14):totalyunbao = _os_.unmarshal_long();
    				break;
    				case ( 6144| 14):totalyunbao = _os_.unmarshal_short();
    				break;
    				case ( 8192| 14):totalyunbao = _os_.unmarshal_int();
    				break;
    				case (10240| 15):totalbindyuanbao = _os_.unmarshal_long();
    				break;
    				case ( 6144| 15):totalbindyuanbao = _os_.unmarshal_short();
    				break;
    				case ( 8192| 15):totalbindyuanbao = _os_.unmarshal_int();
    				break;
    				case (10240| 16):totalvipexp = _os_.unmarshal_long();
    				break;
    				case ( 6144| 16):totalvipexp = _os_.unmarshal_short();
    				break;
    				case ( 8192| 16):totalvipexp = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RolePay copy() {
		_xdb_verify_unsafe_();
		return new RolePay(this);
	}

	@Override
	public xbean.RolePay toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RolePay toBean() {
		_xdb_verify_unsafe_();
		return new RolePay(this); // same as copy()
	}

	@Override
	public xbean.RolePay toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RolePay toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public boolean getCangetfirstbonus() { // 是否首冲过
		_xdb_verify_unsafe_();
		return cangetfirstbonus;
	}

	@Override
	public boolean getIsfirstpayused() { // 是否领取过首冲奖励
		_xdb_verify_unsafe_();
		return isfirstpayused;
	}

	@Override
	public java.util.Map<Integer, Boolean> getIsentryfirstpay() { // 每个充值条目是否首冲过
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "isentryfirstpay"), isentryfirstpay);
	}

	@Override
	public java.util.Map<Integer, Boolean> getIsentryfirstpayAsData() { // 每个充值条目是否首冲过
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Boolean> isentryfirstpay;
		RolePay _o_ = this;
		isentryfirstpay = new java.util.HashMap<Integer, Boolean>();
		for (java.util.Map.Entry<Integer, Boolean> _e_ : _o_.isentryfirstpay.entrySet())
			isentryfirstpay.put(_e_.getKey(), _e_.getValue());
		return isentryfirstpay;
	}

	@Override
	public long getTotalpay() { // 累计充值，以分为单位，充值时花费人民币，得到元宝和绑定元宝
		_xdb_verify_unsafe_();
		return totalpay;
	}

	@Override
	public long getLastrefreshtime() { // 上次刷新时间
		_xdb_verify_unsafe_();
		return lastrefreshtime;
	}

	@Override
	public int getDailypaystatus() { // 每日充值状态，分为未充值，充值没领奖，充值已领奖
		_xdb_verify_unsafe_();
		return dailypaystatus;
	}

	@Override
	public long getDailytotalpay() { // 每日累计充值
		_xdb_verify_unsafe_();
		return dailytotalpay;
	}

	@Override
	public int getDailytotalpaystatus() { // 每日累计充值状态
		_xdb_verify_unsafe_();
		return dailytotalpaystatus;
	}

	@Override
	public long getRoleallpay() { // 角色所有充钱的记录，包含月卡和成长计划
		_xdb_verify_unsafe_();
		return roleallpay;
	}

	@Override
	public int getDailyactivepay() { // 每日特价购是否使用过
		_xdb_verify_unsafe_();
		return dailyactivepay;
	}

	@Override
	public boolean getHasgotpayreturn() { // 已经得到充值返还
		_xdb_verify_unsafe_();
		return hasgotpayreturn;
	}

	@Override
	public long getTotalyunbao() { // 
		_xdb_verify_unsafe_();
		return totalyunbao;
	}

	@Override
	public long getTotalbindyuanbao() { // 
		_xdb_verify_unsafe_();
		return totalbindyuanbao;
	}

	@Override
	public long getTotalvipexp() { // 
		_xdb_verify_unsafe_();
		return totalvipexp;
	}

	@Override
	public void setCangetfirstbonus(boolean _v_) { // 是否首冲过
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "cangetfirstbonus") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, cangetfirstbonus) {
					public void rollback() { cangetfirstbonus = _xdb_saved; }
				};}});
		cangetfirstbonus = _v_;
	}

	@Override
	public void setIsfirstpayused(boolean _v_) { // 是否领取过首冲奖励
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "isfirstpayused") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, isfirstpayused) {
					public void rollback() { isfirstpayused = _xdb_saved; }
				};}});
		isfirstpayused = _v_;
	}

	@Override
	public void setTotalpay(long _v_) { // 累计充值，以分为单位，充值时花费人民币，得到元宝和绑定元宝
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalpay") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalpay) {
					public void rollback() { totalpay = _xdb_saved; }
				};}});
		totalpay = _v_;
	}

	@Override
	public void setLastrefreshtime(long _v_) { // 上次刷新时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastrefreshtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastrefreshtime) {
					public void rollback() { lastrefreshtime = _xdb_saved; }
				};}});
		lastrefreshtime = _v_;
	}

	@Override
	public void setDailypaystatus(int _v_) { // 每日充值状态，分为未充值，充值没领奖，充值已领奖
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "dailypaystatus") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, dailypaystatus) {
					public void rollback() { dailypaystatus = _xdb_saved; }
				};}});
		dailypaystatus = _v_;
	}

	@Override
	public void setDailytotalpay(long _v_) { // 每日累计充值
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "dailytotalpay") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, dailytotalpay) {
					public void rollback() { dailytotalpay = _xdb_saved; }
				};}});
		dailytotalpay = _v_;
	}

	@Override
	public void setDailytotalpaystatus(int _v_) { // 每日累计充值状态
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "dailytotalpaystatus") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, dailytotalpaystatus) {
					public void rollback() { dailytotalpaystatus = _xdb_saved; }
				};}});
		dailytotalpaystatus = _v_;
	}

	@Override
	public void setRoleallpay(long _v_) { // 角色所有充钱的记录，包含月卡和成长计划
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleallpay") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleallpay) {
					public void rollback() { roleallpay = _xdb_saved; }
				};}});
		roleallpay = _v_;
	}

	@Override
	public void setDailyactivepay(int _v_) { // 每日特价购是否使用过
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "dailyactivepay") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, dailyactivepay) {
					public void rollback() { dailyactivepay = _xdb_saved; }
				};}});
		dailyactivepay = _v_;
	}

	@Override
	public void setHasgotpayreturn(boolean _v_) { // 已经得到充值返还
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "hasgotpayreturn") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, hasgotpayreturn) {
					public void rollback() { hasgotpayreturn = _xdb_saved; }
				};}});
		hasgotpayreturn = _v_;
	}

	@Override
	public void setTotalyunbao(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalyunbao") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalyunbao) {
					public void rollback() { totalyunbao = _xdb_saved; }
				};}});
		totalyunbao = _v_;
	}

	@Override
	public void setTotalbindyuanbao(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalbindyuanbao") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalbindyuanbao) {
					public void rollback() { totalbindyuanbao = _xdb_saved; }
				};}});
		totalbindyuanbao = _v_;
	}

	@Override
	public void setTotalvipexp(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalvipexp") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalvipexp) {
					public void rollback() { totalvipexp = _xdb_saved; }
				};}});
		totalvipexp = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RolePay _o_ = null;
		if ( _o1_ instanceof RolePay ) _o_ = (RolePay)_o1_;
		else if ( _o1_ instanceof RolePay.Const ) _o_ = ((RolePay.Const)_o1_).nThis();
		else return false;
		if (cangetfirstbonus != _o_.cangetfirstbonus) return false;
		if (isfirstpayused != _o_.isfirstpayused) return false;
		if (!isentryfirstpay.equals(_o_.isentryfirstpay)) return false;
		if (totalpay != _o_.totalpay) return false;
		if (lastrefreshtime != _o_.lastrefreshtime) return false;
		if (dailypaystatus != _o_.dailypaystatus) return false;
		if (dailytotalpay != _o_.dailytotalpay) return false;
		if (dailytotalpaystatus != _o_.dailytotalpaystatus) return false;
		if (roleallpay != _o_.roleallpay) return false;
		if (dailyactivepay != _o_.dailyactivepay) return false;
		if (hasgotpayreturn != _o_.hasgotpayreturn) return false;
		if (totalyunbao != _o_.totalyunbao) return false;
		if (totalbindyuanbao != _o_.totalbindyuanbao) return false;
		if (totalvipexp != _o_.totalvipexp) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += cangetfirstbonus ? 1231 : 1237;
		_h_ += isfirstpayused ? 1231 : 1237;
		_h_ += isentryfirstpay.hashCode();
		_h_ += totalpay;
		_h_ += lastrefreshtime;
		_h_ += dailypaystatus;
		_h_ += dailytotalpay;
		_h_ += dailytotalpaystatus;
		_h_ += roleallpay;
		_h_ += dailyactivepay;
		_h_ += hasgotpayreturn ? 1231 : 1237;
		_h_ += totalyunbao;
		_h_ += totalbindyuanbao;
		_h_ += totalvipexp;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(cangetfirstbonus);
		_sb_.append(",");
		_sb_.append(isfirstpayused);
		_sb_.append(",");
		_sb_.append(isentryfirstpay);
		_sb_.append(",");
		_sb_.append(totalpay);
		_sb_.append(",");
		_sb_.append(lastrefreshtime);
		_sb_.append(",");
		_sb_.append(dailypaystatus);
		_sb_.append(",");
		_sb_.append(dailytotalpay);
		_sb_.append(",");
		_sb_.append(dailytotalpaystatus);
		_sb_.append(",");
		_sb_.append(roleallpay);
		_sb_.append(",");
		_sb_.append(dailyactivepay);
		_sb_.append(",");
		_sb_.append(hasgotpayreturn);
		_sb_.append(",");
		_sb_.append(totalyunbao);
		_sb_.append(",");
		_sb_.append(totalbindyuanbao);
		_sb_.append(",");
		_sb_.append(totalvipexp);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("cangetfirstbonus"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isfirstpayused"));
		lb.add(new xdb.logs.ListenableMap().setVarName("isentryfirstpay"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalpay"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastrefreshtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("dailypaystatus"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("dailytotalpay"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("dailytotalpaystatus"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleallpay"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("dailyactivepay"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("hasgotpayreturn"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalyunbao"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalbindyuanbao"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalvipexp"));
		return lb;
	}

	private class Const implements xbean.RolePay {
		RolePay nThis() {
			return RolePay.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RolePay copy() {
			return RolePay.this.copy();
		}

		@Override
		public xbean.RolePay toData() {
			return RolePay.this.toData();
		}

		public xbean.RolePay toBean() {
			return RolePay.this.toBean();
		}

		@Override
		public xbean.RolePay toDataIf() {
			return RolePay.this.toDataIf();
		}

		public xbean.RolePay toBeanIf() {
			return RolePay.this.toBeanIf();
		}

		@Override
		public boolean getCangetfirstbonus() { // 是否首冲过
			_xdb_verify_unsafe_();
			return cangetfirstbonus;
		}

		@Override
		public boolean getIsfirstpayused() { // 是否领取过首冲奖励
			_xdb_verify_unsafe_();
			return isfirstpayused;
		}

		@Override
		public java.util.Map<Integer, Boolean> getIsentryfirstpay() { // 每个充值条目是否首冲过
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(isentryfirstpay);
		}

		@Override
		public java.util.Map<Integer, Boolean> getIsentryfirstpayAsData() { // 每个充值条目是否首冲过
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Boolean> isentryfirstpay;
			RolePay _o_ = RolePay.this;
			isentryfirstpay = new java.util.HashMap<Integer, Boolean>();
			for (java.util.Map.Entry<Integer, Boolean> _e_ : _o_.isentryfirstpay.entrySet())
				isentryfirstpay.put(_e_.getKey(), _e_.getValue());
			return isentryfirstpay;
		}

		@Override
		public long getTotalpay() { // 累计充值，以分为单位，充值时花费人民币，得到元宝和绑定元宝
			_xdb_verify_unsafe_();
			return totalpay;
		}

		@Override
		public long getLastrefreshtime() { // 上次刷新时间
			_xdb_verify_unsafe_();
			return lastrefreshtime;
		}

		@Override
		public int getDailypaystatus() { // 每日充值状态，分为未充值，充值没领奖，充值已领奖
			_xdb_verify_unsafe_();
			return dailypaystatus;
		}

		@Override
		public long getDailytotalpay() { // 每日累计充值
			_xdb_verify_unsafe_();
			return dailytotalpay;
		}

		@Override
		public int getDailytotalpaystatus() { // 每日累计充值状态
			_xdb_verify_unsafe_();
			return dailytotalpaystatus;
		}

		@Override
		public long getRoleallpay() { // 角色所有充钱的记录，包含月卡和成长计划
			_xdb_verify_unsafe_();
			return roleallpay;
		}

		@Override
		public int getDailyactivepay() { // 每日特价购是否使用过
			_xdb_verify_unsafe_();
			return dailyactivepay;
		}

		@Override
		public boolean getHasgotpayreturn() { // 已经得到充值返还
			_xdb_verify_unsafe_();
			return hasgotpayreturn;
		}

		@Override
		public long getTotalyunbao() { // 
			_xdb_verify_unsafe_();
			return totalyunbao;
		}

		@Override
		public long getTotalbindyuanbao() { // 
			_xdb_verify_unsafe_();
			return totalbindyuanbao;
		}

		@Override
		public long getTotalvipexp() { // 
			_xdb_verify_unsafe_();
			return totalvipexp;
		}

		@Override
		public void setCangetfirstbonus(boolean _v_) { // 是否首冲过
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsfirstpayused(boolean _v_) { // 是否领取过首冲奖励
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalpay(long _v_) { // 累计充值，以分为单位，充值时花费人民币，得到元宝和绑定元宝
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastrefreshtime(long _v_) { // 上次刷新时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDailypaystatus(int _v_) { // 每日充值状态，分为未充值，充值没领奖，充值已领奖
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDailytotalpay(long _v_) { // 每日累计充值
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDailytotalpaystatus(int _v_) { // 每日累计充值状态
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRoleallpay(long _v_) { // 角色所有充钱的记录，包含月卡和成长计划
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDailyactivepay(int _v_) { // 每日特价购是否使用过
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHasgotpayreturn(boolean _v_) { // 已经得到充值返还
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalyunbao(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalbindyuanbao(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalvipexp(long _v_) { // 
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
			return RolePay.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RolePay.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RolePay.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RolePay.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RolePay.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RolePay.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RolePay.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RolePay.this.hashCode();
		}

		@Override
		public String toString() {
			return RolePay.this.toString();
		}

	}

	public static final class Data implements xbean.RolePay {
		private boolean cangetfirstbonus; // 是否首冲过
		private boolean isfirstpayused; // 是否领取过首冲奖励
		private java.util.HashMap<Integer, Boolean> isentryfirstpay; // 每个充值条目是否首冲过
		private long totalpay; // 累计充值，以分为单位，充值时花费人民币，得到元宝和绑定元宝
		private long lastrefreshtime; // 上次刷新时间
		private int dailypaystatus; // 每日充值状态，分为未充值，充值没领奖，充值已领奖
		private long dailytotalpay; // 每日累计充值
		private int dailytotalpaystatus; // 每日累计充值状态
		private long roleallpay; // 角色所有充钱的记录，包含月卡和成长计划
		private int dailyactivepay; // 每日特价购是否使用过
		private boolean hasgotpayreturn; // 已经得到充值返还
		private long totalyunbao; // 
		private long totalbindyuanbao; // 
		private long totalvipexp; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			isentryfirstpay = new java.util.HashMap<Integer, Boolean>();
		}

		Data(xbean.RolePay _o1_) {
			if (_o1_ instanceof RolePay) assign((RolePay)_o1_);
			else if (_o1_ instanceof RolePay.Data) assign((RolePay.Data)_o1_);
			else if (_o1_ instanceof RolePay.Const) assign(((RolePay.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RolePay _o_) {
			cangetfirstbonus = _o_.cangetfirstbonus;
			isfirstpayused = _o_.isfirstpayused;
			isentryfirstpay = new java.util.HashMap<Integer, Boolean>();
			for (java.util.Map.Entry<Integer, Boolean> _e_ : _o_.isentryfirstpay.entrySet())
				isentryfirstpay.put(_e_.getKey(), _e_.getValue());
			totalpay = _o_.totalpay;
			lastrefreshtime = _o_.lastrefreshtime;
			dailypaystatus = _o_.dailypaystatus;
			dailytotalpay = _o_.dailytotalpay;
			dailytotalpaystatus = _o_.dailytotalpaystatus;
			roleallpay = _o_.roleallpay;
			dailyactivepay = _o_.dailyactivepay;
			hasgotpayreturn = _o_.hasgotpayreturn;
			totalyunbao = _o_.totalyunbao;
			totalbindyuanbao = _o_.totalbindyuanbao;
			totalvipexp = _o_.totalvipexp;
		}

		private void assign(RolePay.Data _o_) {
			cangetfirstbonus = _o_.cangetfirstbonus;
			isfirstpayused = _o_.isfirstpayused;
			isentryfirstpay = new java.util.HashMap<Integer, Boolean>();
			for (java.util.Map.Entry<Integer, Boolean> _e_ : _o_.isentryfirstpay.entrySet())
				isentryfirstpay.put(_e_.getKey(), _e_.getValue());
			totalpay = _o_.totalpay;
			lastrefreshtime = _o_.lastrefreshtime;
			dailypaystatus = _o_.dailypaystatus;
			dailytotalpay = _o_.dailytotalpay;
			dailytotalpaystatus = _o_.dailytotalpaystatus;
			roleallpay = _o_.roleallpay;
			dailyactivepay = _o_.dailyactivepay;
			hasgotpayreturn = _o_.hasgotpayreturn;
			totalyunbao = _o_.totalyunbao;
			totalbindyuanbao = _o_.totalbindyuanbao;
			totalvipexp = _o_.totalvipexp;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)14);
	_os_.marshal((short)( 2048| 10));_os_.marshal(cangetfirstbonus);
	_os_.marshal((short)( 2048|  9));_os_.marshal(isfirstpayused);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(isentryfirstpay.size());
for (java.util.Map.Entry<Integer, Boolean> _e_ : isentryfirstpay.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240|  7));_os_.marshal(totalpay);
	_os_.marshal((short)(10240|  3));_os_.marshal(lastrefreshtime);
	_os_.marshal((short)( 8192|  4));_os_.marshal(dailypaystatus);
	_os_.marshal((short)(10240|  8));_os_.marshal(dailytotalpay);
	_os_.marshal((short)( 8192|  6));_os_.marshal(dailytotalpaystatus);
	_os_.marshal((short)(10240| 11));_os_.marshal(roleallpay);
	_os_.marshal((short)( 8192| 17));_os_.marshal(dailyactivepay);
	_os_.marshal((short)( 2048| 12));_os_.marshal(hasgotpayreturn);
	_os_.marshal((short)(10240| 14));_os_.marshal(totalyunbao);
	_os_.marshal((short)(10240| 15));_os_.marshal(totalbindyuanbao);
	_os_.marshal((short)(10240| 16));_os_.marshal(totalvipexp);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 2048| 10):cangetfirstbonus = _os_.unmarshal_boolean();
					break;
					case ( 2048|  9):isfirstpayused = _os_.unmarshal_boolean();
					break;
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		isentryfirstpay = new java.util.HashMap<Integer, Boolean>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		boolean _v_ = false;
		_v_ = _os_.unmarshal_boolean();
		isentryfirstpay.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (10240|  7):totalpay = _os_.unmarshal_long();
					break;
					case ( 6144|  7):totalpay = _os_.unmarshal_short();
					break;
					case ( 8192|  7):totalpay = _os_.unmarshal_int();
					break;
					case (10240|  3):lastrefreshtime = _os_.unmarshal_long();
					break;
					case ( 6144|  3):lastrefreshtime = _os_.unmarshal_short();
					break;
					case ( 8192|  3):lastrefreshtime = _os_.unmarshal_int();
					break;
					case ( 8192|  4):dailypaystatus = _os_.unmarshal_int();
					break;
					case ( 6144|  4):dailypaystatus = _os_.unmarshal_short();
					break;
					case (10240|  8):dailytotalpay = _os_.unmarshal_long();
					break;
					case ( 6144|  8):dailytotalpay = _os_.unmarshal_short();
					break;
					case ( 8192|  8):dailytotalpay = _os_.unmarshal_int();
					break;
					case ( 8192|  6):dailytotalpaystatus = _os_.unmarshal_int();
					break;
					case ( 6144|  6):dailytotalpaystatus = _os_.unmarshal_short();
					break;
					case (10240| 11):roleallpay = _os_.unmarshal_long();
					break;
					case ( 6144| 11):roleallpay = _os_.unmarshal_short();
					break;
					case ( 8192| 11):roleallpay = _os_.unmarshal_int();
					break;
					case ( 8192| 17):dailyactivepay = _os_.unmarshal_int();
					break;
					case ( 6144| 17):dailyactivepay = _os_.unmarshal_short();
					break;
					case ( 2048| 12):hasgotpayreturn = _os_.unmarshal_boolean();
					break;
					case (10240| 14):totalyunbao = _os_.unmarshal_long();
					break;
					case ( 6144| 14):totalyunbao = _os_.unmarshal_short();
					break;
					case ( 8192| 14):totalyunbao = _os_.unmarshal_int();
					break;
					case (10240| 15):totalbindyuanbao = _os_.unmarshal_long();
					break;
					case ( 6144| 15):totalbindyuanbao = _os_.unmarshal_short();
					break;
					case ( 8192| 15):totalbindyuanbao = _os_.unmarshal_int();
					break;
					case (10240| 16):totalvipexp = _os_.unmarshal_long();
					break;
					case ( 6144| 16):totalvipexp = _os_.unmarshal_short();
					break;
					case ( 8192| 16):totalvipexp = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RolePay copy() {
			return new Data(this);
		}

		@Override
		public xbean.RolePay toData() {
			return new Data(this);
		}

		public xbean.RolePay toBean() {
			return new RolePay(this, null, null);
		}

		@Override
		public xbean.RolePay toDataIf() {
			return this;
		}

		public xbean.RolePay toBeanIf() {
			return new RolePay(this, null, null);
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
		public boolean getCangetfirstbonus() { // 是否首冲过
			return cangetfirstbonus;
		}

		@Override
		public boolean getIsfirstpayused() { // 是否领取过首冲奖励
			return isfirstpayused;
		}

		@Override
		public java.util.Map<Integer, Boolean> getIsentryfirstpay() { // 每个充值条目是否首冲过
			return isentryfirstpay;
		}

		@Override
		public java.util.Map<Integer, Boolean> getIsentryfirstpayAsData() { // 每个充值条目是否首冲过
			return isentryfirstpay;
		}

		@Override
		public long getTotalpay() { // 累计充值，以分为单位，充值时花费人民币，得到元宝和绑定元宝
			return totalpay;
		}

		@Override
		public long getLastrefreshtime() { // 上次刷新时间
			return lastrefreshtime;
		}

		@Override
		public int getDailypaystatus() { // 每日充值状态，分为未充值，充值没领奖，充值已领奖
			return dailypaystatus;
		}

		@Override
		public long getDailytotalpay() { // 每日累计充值
			return dailytotalpay;
		}

		@Override
		public int getDailytotalpaystatus() { // 每日累计充值状态
			return dailytotalpaystatus;
		}

		@Override
		public long getRoleallpay() { // 角色所有充钱的记录，包含月卡和成长计划
			return roleallpay;
		}

		@Override
		public int getDailyactivepay() { // 每日特价购是否使用过
			return dailyactivepay;
		}

		@Override
		public boolean getHasgotpayreturn() { // 已经得到充值返还
			return hasgotpayreturn;
		}

		@Override
		public long getTotalyunbao() { // 
			return totalyunbao;
		}

		@Override
		public long getTotalbindyuanbao() { // 
			return totalbindyuanbao;
		}

		@Override
		public long getTotalvipexp() { // 
			return totalvipexp;
		}

		@Override
		public void setCangetfirstbonus(boolean _v_) { // 是否首冲过
			cangetfirstbonus = _v_;
		}

		@Override
		public void setIsfirstpayused(boolean _v_) { // 是否领取过首冲奖励
			isfirstpayused = _v_;
		}

		@Override
		public void setTotalpay(long _v_) { // 累计充值，以分为单位，充值时花费人民币，得到元宝和绑定元宝
			totalpay = _v_;
		}

		@Override
		public void setLastrefreshtime(long _v_) { // 上次刷新时间
			lastrefreshtime = _v_;
		}

		@Override
		public void setDailypaystatus(int _v_) { // 每日充值状态，分为未充值，充值没领奖，充值已领奖
			dailypaystatus = _v_;
		}

		@Override
		public void setDailytotalpay(long _v_) { // 每日累计充值
			dailytotalpay = _v_;
		}

		@Override
		public void setDailytotalpaystatus(int _v_) { // 每日累计充值状态
			dailytotalpaystatus = _v_;
		}

		@Override
		public void setRoleallpay(long _v_) { // 角色所有充钱的记录，包含月卡和成长计划
			roleallpay = _v_;
		}

		@Override
		public void setDailyactivepay(int _v_) { // 每日特价购是否使用过
			dailyactivepay = _v_;
		}

		@Override
		public void setHasgotpayreturn(boolean _v_) { // 已经得到充值返还
			hasgotpayreturn = _v_;
		}

		@Override
		public void setTotalyunbao(long _v_) { // 
			totalyunbao = _v_;
		}

		@Override
		public void setTotalbindyuanbao(long _v_) { // 
			totalbindyuanbao = _v_;
		}

		@Override
		public void setTotalvipexp(long _v_) { // 
			totalvipexp = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RolePay.Data)) return false;
			RolePay.Data _o_ = (RolePay.Data) _o1_;
			if (cangetfirstbonus != _o_.cangetfirstbonus) return false;
			if (isfirstpayused != _o_.isfirstpayused) return false;
			if (!isentryfirstpay.equals(_o_.isentryfirstpay)) return false;
			if (totalpay != _o_.totalpay) return false;
			if (lastrefreshtime != _o_.lastrefreshtime) return false;
			if (dailypaystatus != _o_.dailypaystatus) return false;
			if (dailytotalpay != _o_.dailytotalpay) return false;
			if (dailytotalpaystatus != _o_.dailytotalpaystatus) return false;
			if (roleallpay != _o_.roleallpay) return false;
			if (dailyactivepay != _o_.dailyactivepay) return false;
			if (hasgotpayreturn != _o_.hasgotpayreturn) return false;
			if (totalyunbao != _o_.totalyunbao) return false;
			if (totalbindyuanbao != _o_.totalbindyuanbao) return false;
			if (totalvipexp != _o_.totalvipexp) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += cangetfirstbonus ? 1231 : 1237;
			_h_ += isfirstpayused ? 1231 : 1237;
			_h_ += isentryfirstpay.hashCode();
			_h_ += totalpay;
			_h_ += lastrefreshtime;
			_h_ += dailypaystatus;
			_h_ += dailytotalpay;
			_h_ += dailytotalpaystatus;
			_h_ += roleallpay;
			_h_ += dailyactivepay;
			_h_ += hasgotpayreturn ? 1231 : 1237;
			_h_ += totalyunbao;
			_h_ += totalbindyuanbao;
			_h_ += totalvipexp;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(cangetfirstbonus);
			_sb_.append(",");
			_sb_.append(isfirstpayused);
			_sb_.append(",");
			_sb_.append(isentryfirstpay);
			_sb_.append(",");
			_sb_.append(totalpay);
			_sb_.append(",");
			_sb_.append(lastrefreshtime);
			_sb_.append(",");
			_sb_.append(dailypaystatus);
			_sb_.append(",");
			_sb_.append(dailytotalpay);
			_sb_.append(",");
			_sb_.append(dailytotalpaystatus);
			_sb_.append(",");
			_sb_.append(roleallpay);
			_sb_.append(",");
			_sb_.append(dailyactivepay);
			_sb_.append(",");
			_sb_.append(hasgotpayreturn);
			_sb_.append(",");
			_sb_.append(totalyunbao);
			_sb_.append(",");
			_sb_.append(totalbindyuanbao);
			_sb_.append(",");
			_sb_.append(totalvipexp);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
