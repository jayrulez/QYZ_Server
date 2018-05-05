
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleWelfareInfo extends xdb.XBean implements xbean.RoleWelfareInfo {
	private long lastsigntime; // 上次签到的时间
	private int totalsigncount; // 总的签到天数
	private int continuesigncount; // 连续签到天数,每月开始都重置
	private java.util.LinkedList<Integer> signdate; // 当月已签到的日期,每30天清空一次
	private int hastodaysign; // 记录今天是否已经签到
	private java.util.ArrayList<Integer> receivedmonthcarddate; // 已领取每月卡专属礼包的日期,30天清空一次
	private java.util.LinkedList<Integer> receivedonlinegift; // 已经领取的每日在线时长奖励，每天0点清空
	private java.util.LinkedList<Integer> receivednewgift; // 已经领取的新手礼包id
	private java.util.LinkedList<Integer> receiveconlogingift; // 已经领取的连续登陆礼包
	private int wishtimes; // 每天已许愿次数,上限为5次,其中免费许愿一次，vip购买宝碟可额外许愿，每天0点清空
	private long lastwishtime; // 上次许愿时间
	private long lastwishpetid; // 上次许愿的伙伴
	private java.util.LinkedList<Integer> receivedgrowplangift; // 已领取的成长计划礼包
	private java.util.LinkedList<Integer> receivedpaycharge; // 已领取的充值和消费礼包
	private int iseatlunch; // 是否领取午餐；0，否；1，是
	private int iseatdinner; // 是否领取晚餐
	private java.util.LinkedList<Integer> threegrowplan; // 领取的成长计划礼包，第一档
	private java.util.LinkedList<Integer> fivegrowplan; // 
	private java.util.LinkedList<Integer> sevengrowplan; // 

	@Override
	public void _reset_unsafe_() {
		lastsigntime = 0L;
		totalsigncount = 0;
		continuesigncount = 0;
		signdate.clear();
		hastodaysign = 0;
		receivedmonthcarddate.clear();
		receivedonlinegift.clear();
		receivednewgift.clear();
		receiveconlogingift.clear();
		wishtimes = 0;
		lastwishtime = 0L;
		lastwishpetid = 0L;
		receivedgrowplangift.clear();
		receivedpaycharge.clear();
		iseatlunch = 0;
		iseatdinner = 0;
		threegrowplan.clear();
		fivegrowplan.clear();
		sevengrowplan.clear();
	}

	RoleWelfareInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		signdate = new java.util.LinkedList<Integer>();
		receivedmonthcarddate = new java.util.ArrayList<Integer>();
		receivedonlinegift = new java.util.LinkedList<Integer>();
		receivednewgift = new java.util.LinkedList<Integer>();
		receiveconlogingift = new java.util.LinkedList<Integer>();
		receivedgrowplangift = new java.util.LinkedList<Integer>();
		receivedpaycharge = new java.util.LinkedList<Integer>();
		threegrowplan = new java.util.LinkedList<Integer>();
		fivegrowplan = new java.util.LinkedList<Integer>();
		sevengrowplan = new java.util.LinkedList<Integer>();
	}

	public RoleWelfareInfo() {
		this(0, null, null);
	}

	public RoleWelfareInfo(RoleWelfareInfo _o_) {
		this(_o_, null, null);
	}

	RoleWelfareInfo(xbean.RoleWelfareInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleWelfareInfo) assign((RoleWelfareInfo)_o1_);
		else if (_o1_ instanceof RoleWelfareInfo.Data) assign((RoleWelfareInfo.Data)_o1_);
		else if (_o1_ instanceof RoleWelfareInfo.Const) assign(((RoleWelfareInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleWelfareInfo _o_) {
		_o_._xdb_verify_unsafe_();
		lastsigntime = _o_.lastsigntime;
		totalsigncount = _o_.totalsigncount;
		continuesigncount = _o_.continuesigncount;
		signdate = new java.util.LinkedList<Integer>();
		signdate.addAll(_o_.signdate);
		hastodaysign = _o_.hastodaysign;
		receivedmonthcarddate = new java.util.ArrayList<Integer>();
		receivedmonthcarddate.addAll(_o_.receivedmonthcarddate);
		receivedonlinegift = new java.util.LinkedList<Integer>();
		receivedonlinegift.addAll(_o_.receivedonlinegift);
		receivednewgift = new java.util.LinkedList<Integer>();
		receivednewgift.addAll(_o_.receivednewgift);
		receiveconlogingift = new java.util.LinkedList<Integer>();
		receiveconlogingift.addAll(_o_.receiveconlogingift);
		wishtimes = _o_.wishtimes;
		lastwishtime = _o_.lastwishtime;
		lastwishpetid = _o_.lastwishpetid;
		receivedgrowplangift = new java.util.LinkedList<Integer>();
		receivedgrowplangift.addAll(_o_.receivedgrowplangift);
		receivedpaycharge = new java.util.LinkedList<Integer>();
		receivedpaycharge.addAll(_o_.receivedpaycharge);
		iseatlunch = _o_.iseatlunch;
		iseatdinner = _o_.iseatdinner;
		threegrowplan = new java.util.LinkedList<Integer>();
		threegrowplan.addAll(_o_.threegrowplan);
		fivegrowplan = new java.util.LinkedList<Integer>();
		fivegrowplan.addAll(_o_.fivegrowplan);
		sevengrowplan = new java.util.LinkedList<Integer>();
		sevengrowplan.addAll(_o_.sevengrowplan);
	}

	private void assign(RoleWelfareInfo.Data _o_) {
		lastsigntime = _o_.lastsigntime;
		totalsigncount = _o_.totalsigncount;
		continuesigncount = _o_.continuesigncount;
		signdate = new java.util.LinkedList<Integer>();
		signdate.addAll(_o_.signdate);
		hastodaysign = _o_.hastodaysign;
		receivedmonthcarddate = new java.util.ArrayList<Integer>();
		receivedmonthcarddate.addAll(_o_.receivedmonthcarddate);
		receivedonlinegift = new java.util.LinkedList<Integer>();
		receivedonlinegift.addAll(_o_.receivedonlinegift);
		receivednewgift = new java.util.LinkedList<Integer>();
		receivednewgift.addAll(_o_.receivednewgift);
		receiveconlogingift = new java.util.LinkedList<Integer>();
		receiveconlogingift.addAll(_o_.receiveconlogingift);
		wishtimes = _o_.wishtimes;
		lastwishtime = _o_.lastwishtime;
		lastwishpetid = _o_.lastwishpetid;
		receivedgrowplangift = new java.util.LinkedList<Integer>();
		receivedgrowplangift.addAll(_o_.receivedgrowplangift);
		receivedpaycharge = new java.util.LinkedList<Integer>();
		receivedpaycharge.addAll(_o_.receivedpaycharge);
		iseatlunch = _o_.iseatlunch;
		iseatdinner = _o_.iseatdinner;
		threegrowplan = new java.util.LinkedList<Integer>();
		threegrowplan.addAll(_o_.threegrowplan);
		fivegrowplan = new java.util.LinkedList<Integer>();
		fivegrowplan.addAll(_o_.fivegrowplan);
		sevengrowplan = new java.util.LinkedList<Integer>();
		sevengrowplan.addAll(_o_.sevengrowplan);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)19);
    _os_.marshal((short)(10240| 14));_os_.marshal(lastsigntime);
    _os_.marshal((short)( 8192|  1));_os_.marshal(totalsigncount);
    _os_.marshal((short)( 8192|  2));_os_.marshal(continuesigncount);
    _os_.marshal((short)(22528|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(signdate.size());
for (Integer _v_ : signdate) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  4));_os_.marshal(hastodaysign);
    _os_.marshal((short)(22528|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivedmonthcarddate.size());
for (Integer _v_ : receivedmonthcarddate) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivedonlinegift.size());
for (Integer _v_ : receivedonlinegift) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivednewgift.size());
for (Integer _v_ : receivednewgift) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528| 19));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receiveconlogingift.size());
for (Integer _v_ : receiveconlogingift) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  8));_os_.marshal(wishtimes);
    _os_.marshal((short)(10240|  9));_os_.marshal(lastwishtime);
    _os_.marshal((short)(10240| 16));_os_.marshal(lastwishpetid);
    _os_.marshal((short)(22528| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivedgrowplangift.size());
for (Integer _v_ : receivedgrowplangift) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528| 15));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivedpaycharge.size());
for (Integer _v_ : receivedpaycharge) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192| 17));_os_.marshal(iseatlunch);
    _os_.marshal((short)( 8192| 18));_os_.marshal(iseatdinner);
    _os_.marshal((short)(22528| 20));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(threegrowplan.size());
for (Integer _v_ : threegrowplan) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528| 21));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(fivegrowplan.size());
for (Integer _v_ : fivegrowplan) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528| 22));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(sevengrowplan.size());
for (Integer _v_ : sevengrowplan) {
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
    				case (10240| 14):lastsigntime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 14):lastsigntime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 14):lastsigntime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  1):totalsigncount = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):totalsigncount = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):continuesigncount = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):continuesigncount = _os_.unmarshal_short();
    				break;
    				case (22528|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	signdate.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  4):hastodaysign = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):hastodaysign = _os_.unmarshal_short();
    				break;
    				case (22528|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivedmonthcarddate.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivedonlinegift.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivednewgift.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528| 19):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receiveconlogingift.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  8):wishtimes = _os_.unmarshal_int();
    				break;
    				case ( 6144|  8):wishtimes = _os_.unmarshal_short();
    				break;
    				case (10240|  9):lastwishtime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  9):lastwishtime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  9):lastwishtime = _os_.unmarshal_int();
    				break;
    				case (10240| 16):lastwishpetid = _os_.unmarshal_long();
    				break;
    				case ( 6144| 16):lastwishpetid = _os_.unmarshal_short();
    				break;
    				case ( 8192| 16):lastwishpetid = _os_.unmarshal_int();
    				break;
    				case (22528| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivedgrowplangift.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528| 15):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivedpaycharge.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192| 17):iseatlunch = _os_.unmarshal_int();
    				break;
    				case ( 6144| 17):iseatlunch = _os_.unmarshal_short();
    				break;
    				case ( 8192| 18):iseatdinner = _os_.unmarshal_int();
    				break;
    				case ( 6144| 18):iseatdinner = _os_.unmarshal_short();
    				break;
    				case (22528| 20):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	threegrowplan.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528| 21):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	fivegrowplan.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528| 22):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	sevengrowplan.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleWelfareInfo copy() {
		_xdb_verify_unsafe_();
		return new RoleWelfareInfo(this);
	}

	@Override
	public xbean.RoleWelfareInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleWelfareInfo toBean() {
		_xdb_verify_unsafe_();
		return new RoleWelfareInfo(this); // same as copy()
	}

	@Override
	public xbean.RoleWelfareInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleWelfareInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getLastsigntime() { // 上次签到的时间
		_xdb_verify_unsafe_();
		return lastsigntime;
	}

	@Override
	public int getTotalsigncount() { // 总的签到天数
		_xdb_verify_unsafe_();
		return totalsigncount;
	}

	@Override
	public int getContinuesigncount() { // 连续签到天数,每月开始都重置
		_xdb_verify_unsafe_();
		return continuesigncount;
	}

	@Override
	public java.util.List<Integer> getSigndate() { // 当月已签到的日期,每30天清空一次
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "signdate"), signdate);
	}

	public java.util.List<Integer> getSigndateAsData() { // 当月已签到的日期,每30天清空一次
		_xdb_verify_unsafe_();
		java.util.List<Integer> signdate;
		RoleWelfareInfo _o_ = this;
		signdate = new java.util.LinkedList<Integer>();
		signdate.addAll(_o_.signdate);
		return signdate;
	}

	@Override
	public int getHastodaysign() { // 记录今天是否已经签到
		_xdb_verify_unsafe_();
		return hastodaysign;
	}

	@Override
	public java.util.List<Integer> getReceivedmonthcarddate() { // 已领取每月卡专属礼包的日期,30天清空一次
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "receivedmonthcarddate"), receivedmonthcarddate);
	}

	public java.util.List<Integer> getReceivedmonthcarddateAsData() { // 已领取每月卡专属礼包的日期,30天清空一次
		_xdb_verify_unsafe_();
		java.util.List<Integer> receivedmonthcarddate;
		RoleWelfareInfo _o_ = this;
		receivedmonthcarddate = new java.util.ArrayList<Integer>();
		receivedmonthcarddate.addAll(_o_.receivedmonthcarddate);
		return receivedmonthcarddate;
	}

	@Override
	public java.util.List<Integer> getReceivedonlinegift() { // 已经领取的每日在线时长奖励，每天0点清空
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "receivedonlinegift"), receivedonlinegift);
	}

	public java.util.List<Integer> getReceivedonlinegiftAsData() { // 已经领取的每日在线时长奖励，每天0点清空
		_xdb_verify_unsafe_();
		java.util.List<Integer> receivedonlinegift;
		RoleWelfareInfo _o_ = this;
		receivedonlinegift = new java.util.LinkedList<Integer>();
		receivedonlinegift.addAll(_o_.receivedonlinegift);
		return receivedonlinegift;
	}

	@Override
	public java.util.List<Integer> getReceivednewgift() { // 已经领取的新手礼包id
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "receivednewgift"), receivednewgift);
	}

	public java.util.List<Integer> getReceivednewgiftAsData() { // 已经领取的新手礼包id
		_xdb_verify_unsafe_();
		java.util.List<Integer> receivednewgift;
		RoleWelfareInfo _o_ = this;
		receivednewgift = new java.util.LinkedList<Integer>();
		receivednewgift.addAll(_o_.receivednewgift);
		return receivednewgift;
	}

	@Override
	public java.util.List<Integer> getReceiveconlogingift() { // 已经领取的连续登陆礼包
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "receiveconlogingift"), receiveconlogingift);
	}

	public java.util.List<Integer> getReceiveconlogingiftAsData() { // 已经领取的连续登陆礼包
		_xdb_verify_unsafe_();
		java.util.List<Integer> receiveconlogingift;
		RoleWelfareInfo _o_ = this;
		receiveconlogingift = new java.util.LinkedList<Integer>();
		receiveconlogingift.addAll(_o_.receiveconlogingift);
		return receiveconlogingift;
	}

	@Override
	public int getWishtimes() { // 每天已许愿次数,上限为5次,其中免费许愿一次，vip购买宝碟可额外许愿，每天0点清空
		_xdb_verify_unsafe_();
		return wishtimes;
	}

	@Override
	public long getLastwishtime() { // 上次许愿时间
		_xdb_verify_unsafe_();
		return lastwishtime;
	}

	@Override
	public long getLastwishpetid() { // 上次许愿的伙伴
		_xdb_verify_unsafe_();
		return lastwishpetid;
	}

	@Override
	public java.util.List<Integer> getReceivedgrowplangift() { // 已领取的成长计划礼包
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "receivedgrowplangift"), receivedgrowplangift);
	}

	public java.util.List<Integer> getReceivedgrowplangiftAsData() { // 已领取的成长计划礼包
		_xdb_verify_unsafe_();
		java.util.List<Integer> receivedgrowplangift;
		RoleWelfareInfo _o_ = this;
		receivedgrowplangift = new java.util.LinkedList<Integer>();
		receivedgrowplangift.addAll(_o_.receivedgrowplangift);
		return receivedgrowplangift;
	}

	@Override
	public java.util.List<Integer> getReceivedpaycharge() { // 已领取的充值和消费礼包
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "receivedpaycharge"), receivedpaycharge);
	}

	public java.util.List<Integer> getReceivedpaychargeAsData() { // 已领取的充值和消费礼包
		_xdb_verify_unsafe_();
		java.util.List<Integer> receivedpaycharge;
		RoleWelfareInfo _o_ = this;
		receivedpaycharge = new java.util.LinkedList<Integer>();
		receivedpaycharge.addAll(_o_.receivedpaycharge);
		return receivedpaycharge;
	}

	@Override
	public int getIseatlunch() { // 是否领取午餐；0，否；1，是
		_xdb_verify_unsafe_();
		return iseatlunch;
	}

	@Override
	public int getIseatdinner() { // 是否领取晚餐
		_xdb_verify_unsafe_();
		return iseatdinner;
	}

	@Override
	public java.util.List<Integer> getThreegrowplan() { // 领取的成长计划礼包，第一档
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "threegrowplan"), threegrowplan);
	}

	public java.util.List<Integer> getThreegrowplanAsData() { // 领取的成长计划礼包，第一档
		_xdb_verify_unsafe_();
		java.util.List<Integer> threegrowplan;
		RoleWelfareInfo _o_ = this;
		threegrowplan = new java.util.LinkedList<Integer>();
		threegrowplan.addAll(_o_.threegrowplan);
		return threegrowplan;
	}

	@Override
	public java.util.List<Integer> getFivegrowplan() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "fivegrowplan"), fivegrowplan);
	}

	public java.util.List<Integer> getFivegrowplanAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Integer> fivegrowplan;
		RoleWelfareInfo _o_ = this;
		fivegrowplan = new java.util.LinkedList<Integer>();
		fivegrowplan.addAll(_o_.fivegrowplan);
		return fivegrowplan;
	}

	@Override
	public java.util.List<Integer> getSevengrowplan() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "sevengrowplan"), sevengrowplan);
	}

	public java.util.List<Integer> getSevengrowplanAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Integer> sevengrowplan;
		RoleWelfareInfo _o_ = this;
		sevengrowplan = new java.util.LinkedList<Integer>();
		sevengrowplan.addAll(_o_.sevengrowplan);
		return sevengrowplan;
	}

	@Override
	public void setLastsigntime(long _v_) { // 上次签到的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastsigntime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastsigntime) {
					public void rollback() { lastsigntime = _xdb_saved; }
				};}});
		lastsigntime = _v_;
	}

	@Override
	public void setTotalsigncount(int _v_) { // 总的签到天数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalsigncount") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, totalsigncount) {
					public void rollback() { totalsigncount = _xdb_saved; }
				};}});
		totalsigncount = _v_;
	}

	@Override
	public void setContinuesigncount(int _v_) { // 连续签到天数,每月开始都重置
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "continuesigncount") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, continuesigncount) {
					public void rollback() { continuesigncount = _xdb_saved; }
				};}});
		continuesigncount = _v_;
	}

	@Override
	public void setHastodaysign(int _v_) { // 记录今天是否已经签到
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "hastodaysign") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, hastodaysign) {
					public void rollback() { hastodaysign = _xdb_saved; }
				};}});
		hastodaysign = _v_;
	}

	@Override
	public void setWishtimes(int _v_) { // 每天已许愿次数,上限为5次,其中免费许愿一次，vip购买宝碟可额外许愿，每天0点清空
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "wishtimes") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, wishtimes) {
					public void rollback() { wishtimes = _xdb_saved; }
				};}});
		wishtimes = _v_;
	}

	@Override
	public void setLastwishtime(long _v_) { // 上次许愿时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastwishtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastwishtime) {
					public void rollback() { lastwishtime = _xdb_saved; }
				};}});
		lastwishtime = _v_;
	}

	@Override
	public void setLastwishpetid(long _v_) { // 上次许愿的伙伴
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastwishpetid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastwishpetid) {
					public void rollback() { lastwishpetid = _xdb_saved; }
				};}});
		lastwishpetid = _v_;
	}

	@Override
	public void setIseatlunch(int _v_) { // 是否领取午餐；0，否；1，是
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "iseatlunch") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, iseatlunch) {
					public void rollback() { iseatlunch = _xdb_saved; }
				};}});
		iseatlunch = _v_;
	}

	@Override
	public void setIseatdinner(int _v_) { // 是否领取晚餐
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "iseatdinner") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, iseatdinner) {
					public void rollback() { iseatdinner = _xdb_saved; }
				};}});
		iseatdinner = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleWelfareInfo _o_ = null;
		if ( _o1_ instanceof RoleWelfareInfo ) _o_ = (RoleWelfareInfo)_o1_;
		else if ( _o1_ instanceof RoleWelfareInfo.Const ) _o_ = ((RoleWelfareInfo.Const)_o1_).nThis();
		else return false;
		if (lastsigntime != _o_.lastsigntime) return false;
		if (totalsigncount != _o_.totalsigncount) return false;
		if (continuesigncount != _o_.continuesigncount) return false;
		if (!signdate.equals(_o_.signdate)) return false;
		if (hastodaysign != _o_.hastodaysign) return false;
		if (!receivedmonthcarddate.equals(_o_.receivedmonthcarddate)) return false;
		if (!receivedonlinegift.equals(_o_.receivedonlinegift)) return false;
		if (!receivednewgift.equals(_o_.receivednewgift)) return false;
		if (!receiveconlogingift.equals(_o_.receiveconlogingift)) return false;
		if (wishtimes != _o_.wishtimes) return false;
		if (lastwishtime != _o_.lastwishtime) return false;
		if (lastwishpetid != _o_.lastwishpetid) return false;
		if (!receivedgrowplangift.equals(_o_.receivedgrowplangift)) return false;
		if (!receivedpaycharge.equals(_o_.receivedpaycharge)) return false;
		if (iseatlunch != _o_.iseatlunch) return false;
		if (iseatdinner != _o_.iseatdinner) return false;
		if (!threegrowplan.equals(_o_.threegrowplan)) return false;
		if (!fivegrowplan.equals(_o_.fivegrowplan)) return false;
		if (!sevengrowplan.equals(_o_.sevengrowplan)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += lastsigntime;
		_h_ += totalsigncount;
		_h_ += continuesigncount;
		_h_ += signdate.hashCode();
		_h_ += hastodaysign;
		_h_ += receivedmonthcarddate.hashCode();
		_h_ += receivedonlinegift.hashCode();
		_h_ += receivednewgift.hashCode();
		_h_ += receiveconlogingift.hashCode();
		_h_ += wishtimes;
		_h_ += lastwishtime;
		_h_ += lastwishpetid;
		_h_ += receivedgrowplangift.hashCode();
		_h_ += receivedpaycharge.hashCode();
		_h_ += iseatlunch;
		_h_ += iseatdinner;
		_h_ += threegrowplan.hashCode();
		_h_ += fivegrowplan.hashCode();
		_h_ += sevengrowplan.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lastsigntime);
		_sb_.append(",");
		_sb_.append(totalsigncount);
		_sb_.append(",");
		_sb_.append(continuesigncount);
		_sb_.append(",");
		_sb_.append(signdate);
		_sb_.append(",");
		_sb_.append(hastodaysign);
		_sb_.append(",");
		_sb_.append(receivedmonthcarddate);
		_sb_.append(",");
		_sb_.append(receivedonlinegift);
		_sb_.append(",");
		_sb_.append(receivednewgift);
		_sb_.append(",");
		_sb_.append(receiveconlogingift);
		_sb_.append(",");
		_sb_.append(wishtimes);
		_sb_.append(",");
		_sb_.append(lastwishtime);
		_sb_.append(",");
		_sb_.append(lastwishpetid);
		_sb_.append(",");
		_sb_.append(receivedgrowplangift);
		_sb_.append(",");
		_sb_.append(receivedpaycharge);
		_sb_.append(",");
		_sb_.append(iseatlunch);
		_sb_.append(",");
		_sb_.append(iseatdinner);
		_sb_.append(",");
		_sb_.append(threegrowplan);
		_sb_.append(",");
		_sb_.append(fivegrowplan);
		_sb_.append(",");
		_sb_.append(sevengrowplan);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastsigntime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalsigncount"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("continuesigncount"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("signdate"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("hastodaysign"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("receivedmonthcarddate"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("receivedonlinegift"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("receivednewgift"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("receiveconlogingift"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("wishtimes"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastwishtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastwishpetid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("receivedgrowplangift"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("receivedpaycharge"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("iseatlunch"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("iseatdinner"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("threegrowplan"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("fivegrowplan"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("sevengrowplan"));
		return lb;
	}

	private class Const implements xbean.RoleWelfareInfo {
		RoleWelfareInfo nThis() {
			return RoleWelfareInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleWelfareInfo copy() {
			return RoleWelfareInfo.this.copy();
		}

		@Override
		public xbean.RoleWelfareInfo toData() {
			return RoleWelfareInfo.this.toData();
		}

		public xbean.RoleWelfareInfo toBean() {
			return RoleWelfareInfo.this.toBean();
		}

		@Override
		public xbean.RoleWelfareInfo toDataIf() {
			return RoleWelfareInfo.this.toDataIf();
		}

		public xbean.RoleWelfareInfo toBeanIf() {
			return RoleWelfareInfo.this.toBeanIf();
		}

		@Override
		public long getLastsigntime() { // 上次签到的时间
			_xdb_verify_unsafe_();
			return lastsigntime;
		}

		@Override
		public int getTotalsigncount() { // 总的签到天数
			_xdb_verify_unsafe_();
			return totalsigncount;
		}

		@Override
		public int getContinuesigncount() { // 连续签到天数,每月开始都重置
			_xdb_verify_unsafe_();
			return continuesigncount;
		}

		@Override
		public java.util.List<Integer> getSigndate() { // 当月已签到的日期,每30天清空一次
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(signdate);
		}

		public java.util.List<Integer> getSigndateAsData() { // 当月已签到的日期,每30天清空一次
			_xdb_verify_unsafe_();
			java.util.List<Integer> signdate;
			RoleWelfareInfo _o_ = RoleWelfareInfo.this;
		signdate = new java.util.LinkedList<Integer>();
		signdate.addAll(_o_.signdate);
			return signdate;
		}

		@Override
		public int getHastodaysign() { // 记录今天是否已经签到
			_xdb_verify_unsafe_();
			return hastodaysign;
		}

		@Override
		public java.util.List<Integer> getReceivedmonthcarddate() { // 已领取每月卡专属礼包的日期,30天清空一次
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(receivedmonthcarddate);
		}

		public java.util.List<Integer> getReceivedmonthcarddateAsData() { // 已领取每月卡专属礼包的日期,30天清空一次
			_xdb_verify_unsafe_();
			java.util.List<Integer> receivedmonthcarddate;
			RoleWelfareInfo _o_ = RoleWelfareInfo.this;
		receivedmonthcarddate = new java.util.ArrayList<Integer>();
		receivedmonthcarddate.addAll(_o_.receivedmonthcarddate);
			return receivedmonthcarddate;
		}

		@Override
		public java.util.List<Integer> getReceivedonlinegift() { // 已经领取的每日在线时长奖励，每天0点清空
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(receivedonlinegift);
		}

		public java.util.List<Integer> getReceivedonlinegiftAsData() { // 已经领取的每日在线时长奖励，每天0点清空
			_xdb_verify_unsafe_();
			java.util.List<Integer> receivedonlinegift;
			RoleWelfareInfo _o_ = RoleWelfareInfo.this;
		receivedonlinegift = new java.util.LinkedList<Integer>();
		receivedonlinegift.addAll(_o_.receivedonlinegift);
			return receivedonlinegift;
		}

		@Override
		public java.util.List<Integer> getReceivednewgift() { // 已经领取的新手礼包id
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(receivednewgift);
		}

		public java.util.List<Integer> getReceivednewgiftAsData() { // 已经领取的新手礼包id
			_xdb_verify_unsafe_();
			java.util.List<Integer> receivednewgift;
			RoleWelfareInfo _o_ = RoleWelfareInfo.this;
		receivednewgift = new java.util.LinkedList<Integer>();
		receivednewgift.addAll(_o_.receivednewgift);
			return receivednewgift;
		}

		@Override
		public java.util.List<Integer> getReceiveconlogingift() { // 已经领取的连续登陆礼包
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(receiveconlogingift);
		}

		public java.util.List<Integer> getReceiveconlogingiftAsData() { // 已经领取的连续登陆礼包
			_xdb_verify_unsafe_();
			java.util.List<Integer> receiveconlogingift;
			RoleWelfareInfo _o_ = RoleWelfareInfo.this;
		receiveconlogingift = new java.util.LinkedList<Integer>();
		receiveconlogingift.addAll(_o_.receiveconlogingift);
			return receiveconlogingift;
		}

		@Override
		public int getWishtimes() { // 每天已许愿次数,上限为5次,其中免费许愿一次，vip购买宝碟可额外许愿，每天0点清空
			_xdb_verify_unsafe_();
			return wishtimes;
		}

		@Override
		public long getLastwishtime() { // 上次许愿时间
			_xdb_verify_unsafe_();
			return lastwishtime;
		}

		@Override
		public long getLastwishpetid() { // 上次许愿的伙伴
			_xdb_verify_unsafe_();
			return lastwishpetid;
		}

		@Override
		public java.util.List<Integer> getReceivedgrowplangift() { // 已领取的成长计划礼包
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(receivedgrowplangift);
		}

		public java.util.List<Integer> getReceivedgrowplangiftAsData() { // 已领取的成长计划礼包
			_xdb_verify_unsafe_();
			java.util.List<Integer> receivedgrowplangift;
			RoleWelfareInfo _o_ = RoleWelfareInfo.this;
		receivedgrowplangift = new java.util.LinkedList<Integer>();
		receivedgrowplangift.addAll(_o_.receivedgrowplangift);
			return receivedgrowplangift;
		}

		@Override
		public java.util.List<Integer> getReceivedpaycharge() { // 已领取的充值和消费礼包
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(receivedpaycharge);
		}

		public java.util.List<Integer> getReceivedpaychargeAsData() { // 已领取的充值和消费礼包
			_xdb_verify_unsafe_();
			java.util.List<Integer> receivedpaycharge;
			RoleWelfareInfo _o_ = RoleWelfareInfo.this;
		receivedpaycharge = new java.util.LinkedList<Integer>();
		receivedpaycharge.addAll(_o_.receivedpaycharge);
			return receivedpaycharge;
		}

		@Override
		public int getIseatlunch() { // 是否领取午餐；0，否；1，是
			_xdb_verify_unsafe_();
			return iseatlunch;
		}

		@Override
		public int getIseatdinner() { // 是否领取晚餐
			_xdb_verify_unsafe_();
			return iseatdinner;
		}

		@Override
		public java.util.List<Integer> getThreegrowplan() { // 领取的成长计划礼包，第一档
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(threegrowplan);
		}

		public java.util.List<Integer> getThreegrowplanAsData() { // 领取的成长计划礼包，第一档
			_xdb_verify_unsafe_();
			java.util.List<Integer> threegrowplan;
			RoleWelfareInfo _o_ = RoleWelfareInfo.this;
		threegrowplan = new java.util.LinkedList<Integer>();
		threegrowplan.addAll(_o_.threegrowplan);
			return threegrowplan;
		}

		@Override
		public java.util.List<Integer> getFivegrowplan() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(fivegrowplan);
		}

		public java.util.List<Integer> getFivegrowplanAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Integer> fivegrowplan;
			RoleWelfareInfo _o_ = RoleWelfareInfo.this;
		fivegrowplan = new java.util.LinkedList<Integer>();
		fivegrowplan.addAll(_o_.fivegrowplan);
			return fivegrowplan;
		}

		@Override
		public java.util.List<Integer> getSevengrowplan() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(sevengrowplan);
		}

		public java.util.List<Integer> getSevengrowplanAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Integer> sevengrowplan;
			RoleWelfareInfo _o_ = RoleWelfareInfo.this;
		sevengrowplan = new java.util.LinkedList<Integer>();
		sevengrowplan.addAll(_o_.sevengrowplan);
			return sevengrowplan;
		}

		@Override
		public void setLastsigntime(long _v_) { // 上次签到的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalsigncount(int _v_) { // 总的签到天数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setContinuesigncount(int _v_) { // 连续签到天数,每月开始都重置
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHastodaysign(int _v_) { // 记录今天是否已经签到
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setWishtimes(int _v_) { // 每天已许愿次数,上限为5次,其中免费许愿一次，vip购买宝碟可额外许愿，每天0点清空
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastwishtime(long _v_) { // 上次许愿时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastwishpetid(long _v_) { // 上次许愿的伙伴
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIseatlunch(int _v_) { // 是否领取午餐；0，否；1，是
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIseatdinner(int _v_) { // 是否领取晚餐
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
			return RoleWelfareInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleWelfareInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleWelfareInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleWelfareInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleWelfareInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleWelfareInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleWelfareInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleWelfareInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleWelfareInfo.this.toString();
		}

	}

	public static final class Data implements xbean.RoleWelfareInfo {
		private long lastsigntime; // 上次签到的时间
		private int totalsigncount; // 总的签到天数
		private int continuesigncount; // 连续签到天数,每月开始都重置
		private java.util.LinkedList<Integer> signdate; // 当月已签到的日期,每30天清空一次
		private int hastodaysign; // 记录今天是否已经签到
		private java.util.ArrayList<Integer> receivedmonthcarddate; // 已领取每月卡专属礼包的日期,30天清空一次
		private java.util.LinkedList<Integer> receivedonlinegift; // 已经领取的每日在线时长奖励，每天0点清空
		private java.util.LinkedList<Integer> receivednewgift; // 已经领取的新手礼包id
		private java.util.LinkedList<Integer> receiveconlogingift; // 已经领取的连续登陆礼包
		private int wishtimes; // 每天已许愿次数,上限为5次,其中免费许愿一次，vip购买宝碟可额外许愿，每天0点清空
		private long lastwishtime; // 上次许愿时间
		private long lastwishpetid; // 上次许愿的伙伴
		private java.util.LinkedList<Integer> receivedgrowplangift; // 已领取的成长计划礼包
		private java.util.LinkedList<Integer> receivedpaycharge; // 已领取的充值和消费礼包
		private int iseatlunch; // 是否领取午餐；0，否；1，是
		private int iseatdinner; // 是否领取晚餐
		private java.util.LinkedList<Integer> threegrowplan; // 领取的成长计划礼包，第一档
		private java.util.LinkedList<Integer> fivegrowplan; // 
		private java.util.LinkedList<Integer> sevengrowplan; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			signdate = new java.util.LinkedList<Integer>();
			receivedmonthcarddate = new java.util.ArrayList<Integer>();
			receivedonlinegift = new java.util.LinkedList<Integer>();
			receivednewgift = new java.util.LinkedList<Integer>();
			receiveconlogingift = new java.util.LinkedList<Integer>();
			receivedgrowplangift = new java.util.LinkedList<Integer>();
			receivedpaycharge = new java.util.LinkedList<Integer>();
			threegrowplan = new java.util.LinkedList<Integer>();
			fivegrowplan = new java.util.LinkedList<Integer>();
			sevengrowplan = new java.util.LinkedList<Integer>();
		}

		Data(xbean.RoleWelfareInfo _o1_) {
			if (_o1_ instanceof RoleWelfareInfo) assign((RoleWelfareInfo)_o1_);
			else if (_o1_ instanceof RoleWelfareInfo.Data) assign((RoleWelfareInfo.Data)_o1_);
			else if (_o1_ instanceof RoleWelfareInfo.Const) assign(((RoleWelfareInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleWelfareInfo _o_) {
			lastsigntime = _o_.lastsigntime;
			totalsigncount = _o_.totalsigncount;
			continuesigncount = _o_.continuesigncount;
			signdate = new java.util.LinkedList<Integer>();
			signdate.addAll(_o_.signdate);
			hastodaysign = _o_.hastodaysign;
			receivedmonthcarddate = new java.util.ArrayList<Integer>();
			receivedmonthcarddate.addAll(_o_.receivedmonthcarddate);
			receivedonlinegift = new java.util.LinkedList<Integer>();
			receivedonlinegift.addAll(_o_.receivedonlinegift);
			receivednewgift = new java.util.LinkedList<Integer>();
			receivednewgift.addAll(_o_.receivednewgift);
			receiveconlogingift = new java.util.LinkedList<Integer>();
			receiveconlogingift.addAll(_o_.receiveconlogingift);
			wishtimes = _o_.wishtimes;
			lastwishtime = _o_.lastwishtime;
			lastwishpetid = _o_.lastwishpetid;
			receivedgrowplangift = new java.util.LinkedList<Integer>();
			receivedgrowplangift.addAll(_o_.receivedgrowplangift);
			receivedpaycharge = new java.util.LinkedList<Integer>();
			receivedpaycharge.addAll(_o_.receivedpaycharge);
			iseatlunch = _o_.iseatlunch;
			iseatdinner = _o_.iseatdinner;
			threegrowplan = new java.util.LinkedList<Integer>();
			threegrowplan.addAll(_o_.threegrowplan);
			fivegrowplan = new java.util.LinkedList<Integer>();
			fivegrowplan.addAll(_o_.fivegrowplan);
			sevengrowplan = new java.util.LinkedList<Integer>();
			sevengrowplan.addAll(_o_.sevengrowplan);
		}

		private void assign(RoleWelfareInfo.Data _o_) {
			lastsigntime = _o_.lastsigntime;
			totalsigncount = _o_.totalsigncount;
			continuesigncount = _o_.continuesigncount;
			signdate = new java.util.LinkedList<Integer>();
			signdate.addAll(_o_.signdate);
			hastodaysign = _o_.hastodaysign;
			receivedmonthcarddate = new java.util.ArrayList<Integer>();
			receivedmonthcarddate.addAll(_o_.receivedmonthcarddate);
			receivedonlinegift = new java.util.LinkedList<Integer>();
			receivedonlinegift.addAll(_o_.receivedonlinegift);
			receivednewgift = new java.util.LinkedList<Integer>();
			receivednewgift.addAll(_o_.receivednewgift);
			receiveconlogingift = new java.util.LinkedList<Integer>();
			receiveconlogingift.addAll(_o_.receiveconlogingift);
			wishtimes = _o_.wishtimes;
			lastwishtime = _o_.lastwishtime;
			lastwishpetid = _o_.lastwishpetid;
			receivedgrowplangift = new java.util.LinkedList<Integer>();
			receivedgrowplangift.addAll(_o_.receivedgrowplangift);
			receivedpaycharge = new java.util.LinkedList<Integer>();
			receivedpaycharge.addAll(_o_.receivedpaycharge);
			iseatlunch = _o_.iseatlunch;
			iseatdinner = _o_.iseatdinner;
			threegrowplan = new java.util.LinkedList<Integer>();
			threegrowplan.addAll(_o_.threegrowplan);
			fivegrowplan = new java.util.LinkedList<Integer>();
			fivegrowplan.addAll(_o_.fivegrowplan);
			sevengrowplan = new java.util.LinkedList<Integer>();
			sevengrowplan.addAll(_o_.sevengrowplan);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)19);
	_os_.marshal((short)(10240| 14));_os_.marshal(lastsigntime);
	_os_.marshal((short)( 8192|  1));_os_.marshal(totalsigncount);
	_os_.marshal((short)( 8192|  2));_os_.marshal(continuesigncount);
	_os_.marshal((short)(22528|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(signdate.size());
for (Integer _v_ : signdate) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  4));_os_.marshal(hastodaysign);
	_os_.marshal((short)(22528|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivedmonthcarddate.size());
for (Integer _v_ : receivedmonthcarddate) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  6));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivedonlinegift.size());
for (Integer _v_ : receivedonlinegift) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivednewgift.size());
for (Integer _v_ : receivednewgift) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528| 19));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receiveconlogingift.size());
for (Integer _v_ : receiveconlogingift) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  8));_os_.marshal(wishtimes);
	_os_.marshal((short)(10240|  9));_os_.marshal(lastwishtime);
	_os_.marshal((short)(10240| 16));_os_.marshal(lastwishpetid);
	_os_.marshal((short)(22528| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivedgrowplangift.size());
for (Integer _v_ : receivedgrowplangift) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528| 15));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(receivedpaycharge.size());
for (Integer _v_ : receivedpaycharge) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192| 17));_os_.marshal(iseatlunch);
	_os_.marshal((short)( 8192| 18));_os_.marshal(iseatdinner);
	_os_.marshal((short)(22528| 20));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(threegrowplan.size());
for (Integer _v_ : threegrowplan) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528| 21));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(fivegrowplan.size());
for (Integer _v_ : fivegrowplan) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528| 22));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(sevengrowplan.size());
for (Integer _v_ : sevengrowplan) {
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
					case (10240| 14):lastsigntime = _os_.unmarshal_long();
					break;
					case ( 6144| 14):lastsigntime = _os_.unmarshal_short();
					break;
					case ( 8192| 14):lastsigntime = _os_.unmarshal_int();
					break;
					case ( 8192|  1):totalsigncount = _os_.unmarshal_int();
					break;
					case ( 6144|  1):totalsigncount = _os_.unmarshal_short();
					break;
					case ( 8192|  2):continuesigncount = _os_.unmarshal_int();
					break;
					case ( 6144|  2):continuesigncount = _os_.unmarshal_short();
					break;
					case (22528|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	signdate.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192|  4):hastodaysign = _os_.unmarshal_int();
					break;
					case ( 6144|  4):hastodaysign = _os_.unmarshal_short();
					break;
					case (22528|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivedmonthcarddate.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528|  6):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivedonlinegift.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivednewgift.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528| 19):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receiveconlogingift.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192|  8):wishtimes = _os_.unmarshal_int();
					break;
					case ( 6144|  8):wishtimes = _os_.unmarshal_short();
					break;
					case (10240|  9):lastwishtime = _os_.unmarshal_long();
					break;
					case ( 6144|  9):lastwishtime = _os_.unmarshal_short();
					break;
					case ( 8192|  9):lastwishtime = _os_.unmarshal_int();
					break;
					case (10240| 16):lastwishpetid = _os_.unmarshal_long();
					break;
					case ( 6144| 16):lastwishpetid = _os_.unmarshal_short();
					break;
					case ( 8192| 16):lastwishpetid = _os_.unmarshal_int();
					break;
					case (22528| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivedgrowplangift.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528| 15):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	receivedpaycharge.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192| 17):iseatlunch = _os_.unmarshal_int();
					break;
					case ( 6144| 17):iseatlunch = _os_.unmarshal_short();
					break;
					case ( 8192| 18):iseatdinner = _os_.unmarshal_int();
					break;
					case ( 6144| 18):iseatdinner = _os_.unmarshal_short();
					break;
					case (22528| 20):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	threegrowplan.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528| 21):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	fivegrowplan.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528| 22):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	sevengrowplan.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleWelfareInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleWelfareInfo toData() {
			return new Data(this);
		}

		public xbean.RoleWelfareInfo toBean() {
			return new RoleWelfareInfo(this, null, null);
		}

		@Override
		public xbean.RoleWelfareInfo toDataIf() {
			return this;
		}

		public xbean.RoleWelfareInfo toBeanIf() {
			return new RoleWelfareInfo(this, null, null);
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
		public long getLastsigntime() { // 上次签到的时间
			return lastsigntime;
		}

		@Override
		public int getTotalsigncount() { // 总的签到天数
			return totalsigncount;
		}

		@Override
		public int getContinuesigncount() { // 连续签到天数,每月开始都重置
			return continuesigncount;
		}

		@Override
		public java.util.List<Integer> getSigndate() { // 当月已签到的日期,每30天清空一次
			return signdate;
		}

		@Override
		public java.util.List<Integer> getSigndateAsData() { // 当月已签到的日期,每30天清空一次
			return signdate;
		}

		@Override
		public int getHastodaysign() { // 记录今天是否已经签到
			return hastodaysign;
		}

		@Override
		public java.util.List<Integer> getReceivedmonthcarddate() { // 已领取每月卡专属礼包的日期,30天清空一次
			return receivedmonthcarddate;
		}

		@Override
		public java.util.List<Integer> getReceivedmonthcarddateAsData() { // 已领取每月卡专属礼包的日期,30天清空一次
			return receivedmonthcarddate;
		}

		@Override
		public java.util.List<Integer> getReceivedonlinegift() { // 已经领取的每日在线时长奖励，每天0点清空
			return receivedonlinegift;
		}

		@Override
		public java.util.List<Integer> getReceivedonlinegiftAsData() { // 已经领取的每日在线时长奖励，每天0点清空
			return receivedonlinegift;
		}

		@Override
		public java.util.List<Integer> getReceivednewgift() { // 已经领取的新手礼包id
			return receivednewgift;
		}

		@Override
		public java.util.List<Integer> getReceivednewgiftAsData() { // 已经领取的新手礼包id
			return receivednewgift;
		}

		@Override
		public java.util.List<Integer> getReceiveconlogingift() { // 已经领取的连续登陆礼包
			return receiveconlogingift;
		}

		@Override
		public java.util.List<Integer> getReceiveconlogingiftAsData() { // 已经领取的连续登陆礼包
			return receiveconlogingift;
		}

		@Override
		public int getWishtimes() { // 每天已许愿次数,上限为5次,其中免费许愿一次，vip购买宝碟可额外许愿，每天0点清空
			return wishtimes;
		}

		@Override
		public long getLastwishtime() { // 上次许愿时间
			return lastwishtime;
		}

		@Override
		public long getLastwishpetid() { // 上次许愿的伙伴
			return lastwishpetid;
		}

		@Override
		public java.util.List<Integer> getReceivedgrowplangift() { // 已领取的成长计划礼包
			return receivedgrowplangift;
		}

		@Override
		public java.util.List<Integer> getReceivedgrowplangiftAsData() { // 已领取的成长计划礼包
			return receivedgrowplangift;
		}

		@Override
		public java.util.List<Integer> getReceivedpaycharge() { // 已领取的充值和消费礼包
			return receivedpaycharge;
		}

		@Override
		public java.util.List<Integer> getReceivedpaychargeAsData() { // 已领取的充值和消费礼包
			return receivedpaycharge;
		}

		@Override
		public int getIseatlunch() { // 是否领取午餐；0，否；1，是
			return iseatlunch;
		}

		@Override
		public int getIseatdinner() { // 是否领取晚餐
			return iseatdinner;
		}

		@Override
		public java.util.List<Integer> getThreegrowplan() { // 领取的成长计划礼包，第一档
			return threegrowplan;
		}

		@Override
		public java.util.List<Integer> getThreegrowplanAsData() { // 领取的成长计划礼包，第一档
			return threegrowplan;
		}

		@Override
		public java.util.List<Integer> getFivegrowplan() { // 
			return fivegrowplan;
		}

		@Override
		public java.util.List<Integer> getFivegrowplanAsData() { // 
			return fivegrowplan;
		}

		@Override
		public java.util.List<Integer> getSevengrowplan() { // 
			return sevengrowplan;
		}

		@Override
		public java.util.List<Integer> getSevengrowplanAsData() { // 
			return sevengrowplan;
		}

		@Override
		public void setLastsigntime(long _v_) { // 上次签到的时间
			lastsigntime = _v_;
		}

		@Override
		public void setTotalsigncount(int _v_) { // 总的签到天数
			totalsigncount = _v_;
		}

		@Override
		public void setContinuesigncount(int _v_) { // 连续签到天数,每月开始都重置
			continuesigncount = _v_;
		}

		@Override
		public void setHastodaysign(int _v_) { // 记录今天是否已经签到
			hastodaysign = _v_;
		}

		@Override
		public void setWishtimes(int _v_) { // 每天已许愿次数,上限为5次,其中免费许愿一次，vip购买宝碟可额外许愿，每天0点清空
			wishtimes = _v_;
		}

		@Override
		public void setLastwishtime(long _v_) { // 上次许愿时间
			lastwishtime = _v_;
		}

		@Override
		public void setLastwishpetid(long _v_) { // 上次许愿的伙伴
			lastwishpetid = _v_;
		}

		@Override
		public void setIseatlunch(int _v_) { // 是否领取午餐；0，否；1，是
			iseatlunch = _v_;
		}

		@Override
		public void setIseatdinner(int _v_) { // 是否领取晚餐
			iseatdinner = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleWelfareInfo.Data)) return false;
			RoleWelfareInfo.Data _o_ = (RoleWelfareInfo.Data) _o1_;
			if (lastsigntime != _o_.lastsigntime) return false;
			if (totalsigncount != _o_.totalsigncount) return false;
			if (continuesigncount != _o_.continuesigncount) return false;
			if (!signdate.equals(_o_.signdate)) return false;
			if (hastodaysign != _o_.hastodaysign) return false;
			if (!receivedmonthcarddate.equals(_o_.receivedmonthcarddate)) return false;
			if (!receivedonlinegift.equals(_o_.receivedonlinegift)) return false;
			if (!receivednewgift.equals(_o_.receivednewgift)) return false;
			if (!receiveconlogingift.equals(_o_.receiveconlogingift)) return false;
			if (wishtimes != _o_.wishtimes) return false;
			if (lastwishtime != _o_.lastwishtime) return false;
			if (lastwishpetid != _o_.lastwishpetid) return false;
			if (!receivedgrowplangift.equals(_o_.receivedgrowplangift)) return false;
			if (!receivedpaycharge.equals(_o_.receivedpaycharge)) return false;
			if (iseatlunch != _o_.iseatlunch) return false;
			if (iseatdinner != _o_.iseatdinner) return false;
			if (!threegrowplan.equals(_o_.threegrowplan)) return false;
			if (!fivegrowplan.equals(_o_.fivegrowplan)) return false;
			if (!sevengrowplan.equals(_o_.sevengrowplan)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += lastsigntime;
			_h_ += totalsigncount;
			_h_ += continuesigncount;
			_h_ += signdate.hashCode();
			_h_ += hastodaysign;
			_h_ += receivedmonthcarddate.hashCode();
			_h_ += receivedonlinegift.hashCode();
			_h_ += receivednewgift.hashCode();
			_h_ += receiveconlogingift.hashCode();
			_h_ += wishtimes;
			_h_ += lastwishtime;
			_h_ += lastwishpetid;
			_h_ += receivedgrowplangift.hashCode();
			_h_ += receivedpaycharge.hashCode();
			_h_ += iseatlunch;
			_h_ += iseatdinner;
			_h_ += threegrowplan.hashCode();
			_h_ += fivegrowplan.hashCode();
			_h_ += sevengrowplan.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(lastsigntime);
			_sb_.append(",");
			_sb_.append(totalsigncount);
			_sb_.append(",");
			_sb_.append(continuesigncount);
			_sb_.append(",");
			_sb_.append(signdate);
			_sb_.append(",");
			_sb_.append(hastodaysign);
			_sb_.append(",");
			_sb_.append(receivedmonthcarddate);
			_sb_.append(",");
			_sb_.append(receivedonlinegift);
			_sb_.append(",");
			_sb_.append(receivednewgift);
			_sb_.append(",");
			_sb_.append(receiveconlogingift);
			_sb_.append(",");
			_sb_.append(wishtimes);
			_sb_.append(",");
			_sb_.append(lastwishtime);
			_sb_.append(",");
			_sb_.append(lastwishpetid);
			_sb_.append(",");
			_sb_.append(receivedgrowplangift);
			_sb_.append(",");
			_sb_.append(receivedpaycharge);
			_sb_.append(",");
			_sb_.append(iseatlunch);
			_sb_.append(",");
			_sb_.append(iseatdinner);
			_sb_.append(",");
			_sb_.append(threegrowplan);
			_sb_.append(",");
			_sb_.append(fivegrowplan);
			_sb_.append(",");
			_sb_.append(sevengrowplan);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
