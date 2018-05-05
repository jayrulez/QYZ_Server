
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleInfo extends xdb.XBean implements xbean.RoleInfo {
	private long userid; // 
	private int profession; // 
	private long createtime; // 
	private int serverid; // 
	private long lastlogintime; // 
	private long totalonlinetime; // 
	private long lastlvluptotalonlinetime; // 
	private long lastlogouttime; // 用来判断是否0点在线的问题
	private int logindaycount; // 总的登录天数
	private long lastaddlogindaytime; // 
	private int continuesloginday; // 连续登录天数
	private long totaldayonlinetime; // 每天在线时长，每天登录的时候重置
	private int leftgifttimes; // 剩余连续登录奖励领取次数,每天登录的时候重置
	private int monthcardlefttimes; // 月卡剩余天数
	private int buymonthcardtimes; // 购买月卡次数
	private int buygrowplan; // 是否购买过成长计划
	private int growonetime; // 购买成长计划1的时间，用登录第几天表示
	private int buygrowplantwo; // 购买成长计划2，苹果测试用
	private int growtwotime; // 购买成长计划2的时间，用登录第几天表示
	private int buygrowplanthree; // 购买成长计划3，苹果测试用
	private int growthreetime; // 购买成长计划3的时间，用登录第几天表示
	private String name; // 
	private int changenametime; // 
	private int level; // 
	private long lastlvluptime; // 
	private int viplevel; // 
	private long vipexp; // 
	private java.util.HashMap<Integer, Long> currencys; // cfg.currency.CurrencyType -> num
	private int gender; // cfg.role.GenderType
	private int totalcostyuanbao; // 累计花费元宝,不算绑定的
	private long lastaddtilitime; // 上次自动加体力的时间
	private long lastworldtalktime; // 上次世界频道发言时间
	private long silentendtime; // 禁言到期时间
	private int leftreporttime; // 剩余举报次数，每天重置
	private int bereportedtime; // 被举报次数，每次被禁言后就清空一次

	@Override
	public void _reset_unsafe_() {
		userid = 0L;
		profession = 0;
		createtime = 0L;
		serverid = 0;
		lastlogintime = 0L;
		totalonlinetime = 0L;
		lastlvluptotalonlinetime = 0L;
		lastlogouttime = 0L;
		logindaycount = 0;
		lastaddlogindaytime = 0L;
		continuesloginday = 0;
		totaldayonlinetime = 0L;
		leftgifttimes = 0;
		monthcardlefttimes = 0;
		buymonthcardtimes = 0;
		buygrowplan = 0;
		growonetime = 0;
		buygrowplantwo = 0;
		growtwotime = 0;
		buygrowplanthree = 0;
		growthreetime = 0;
		name = "";
		changenametime = 0;
		level = 0;
		lastlvluptime = 0L;
		viplevel = 0;
		vipexp = 0L;
		currencys.clear();
		gender = 0;
		totalcostyuanbao = 0;
		lastaddtilitime = 0L;
		lastworldtalktime = 0L;
		silentendtime = 0L;
		leftreporttime = 0;
		bereportedtime = 0;
	}

	RoleInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		name = "";
		currencys = new java.util.HashMap<Integer, Long>();
	}

	public RoleInfo() {
		this(0, null, null);
	}

	public RoleInfo(RoleInfo _o_) {
		this(_o_, null, null);
	}

	RoleInfo(xbean.RoleInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleInfo) assign((RoleInfo)_o1_);
		else if (_o1_ instanceof RoleInfo.Data) assign((RoleInfo.Data)_o1_);
		else if (_o1_ instanceof RoleInfo.Const) assign(((RoleInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleInfo _o_) {
		_o_._xdb_verify_unsafe_();
		userid = _o_.userid;
		profession = _o_.profession;
		createtime = _o_.createtime;
		serverid = _o_.serverid;
		lastlogintime = _o_.lastlogintime;
		totalonlinetime = _o_.totalonlinetime;
		lastlvluptotalonlinetime = _o_.lastlvluptotalonlinetime;
		lastlogouttime = _o_.lastlogouttime;
		logindaycount = _o_.logindaycount;
		lastaddlogindaytime = _o_.lastaddlogindaytime;
		continuesloginday = _o_.continuesloginday;
		totaldayonlinetime = _o_.totaldayonlinetime;
		leftgifttimes = _o_.leftgifttimes;
		monthcardlefttimes = _o_.monthcardlefttimes;
		buymonthcardtimes = _o_.buymonthcardtimes;
		buygrowplan = _o_.buygrowplan;
		growonetime = _o_.growonetime;
		buygrowplantwo = _o_.buygrowplantwo;
		growtwotime = _o_.growtwotime;
		buygrowplanthree = _o_.buygrowplanthree;
		growthreetime = _o_.growthreetime;
		name = _o_.name;
		changenametime = _o_.changenametime;
		level = _o_.level;
		lastlvluptime = _o_.lastlvluptime;
		viplevel = _o_.viplevel;
		vipexp = _o_.vipexp;
		currencys = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.currencys.entrySet())
			currencys.put(_e_.getKey(), _e_.getValue());
		gender = _o_.gender;
		totalcostyuanbao = _o_.totalcostyuanbao;
		lastaddtilitime = _o_.lastaddtilitime;
		lastworldtalktime = _o_.lastworldtalktime;
		silentendtime = _o_.silentendtime;
		leftreporttime = _o_.leftreporttime;
		bereportedtime = _o_.bereportedtime;
	}

	private void assign(RoleInfo.Data _o_) {
		userid = _o_.userid;
		profession = _o_.profession;
		createtime = _o_.createtime;
		serverid = _o_.serverid;
		lastlogintime = _o_.lastlogintime;
		totalonlinetime = _o_.totalonlinetime;
		lastlvluptotalonlinetime = _o_.lastlvluptotalonlinetime;
		lastlogouttime = _o_.lastlogouttime;
		logindaycount = _o_.logindaycount;
		lastaddlogindaytime = _o_.lastaddlogindaytime;
		continuesloginday = _o_.continuesloginday;
		totaldayonlinetime = _o_.totaldayonlinetime;
		leftgifttimes = _o_.leftgifttimes;
		monthcardlefttimes = _o_.monthcardlefttimes;
		buymonthcardtimes = _o_.buymonthcardtimes;
		buygrowplan = _o_.buygrowplan;
		growonetime = _o_.growonetime;
		buygrowplantwo = _o_.buygrowplantwo;
		growtwotime = _o_.growtwotime;
		buygrowplanthree = _o_.buygrowplanthree;
		growthreetime = _o_.growthreetime;
		name = _o_.name;
		changenametime = _o_.changenametime;
		level = _o_.level;
		lastlvluptime = _o_.lastlvluptime;
		viplevel = _o_.viplevel;
		vipexp = _o_.vipexp;
		currencys = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.currencys.entrySet())
			currencys.put(_e_.getKey(), _e_.getValue());
		gender = _o_.gender;
		totalcostyuanbao = _o_.totalcostyuanbao;
		lastaddtilitime = _o_.lastaddtilitime;
		lastworldtalktime = _o_.lastworldtalktime;
		silentendtime = _o_.silentendtime;
		leftreporttime = _o_.leftreporttime;
		bereportedtime = _o_.bereportedtime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)35);
    _os_.marshal((short)(10240|  1));_os_.marshal(userid);
    _os_.marshal((short)( 8192|  4));_os_.marshal(profession);
    _os_.marshal((short)(10240| 19));_os_.marshal(createtime);
    _os_.marshal((short)( 8192| 34));_os_.marshal(serverid);
    _os_.marshal((short)(10240|  5));_os_.marshal(lastlogintime);
    _os_.marshal((short)(10240|  8));_os_.marshal(totalonlinetime);
    _os_.marshal((short)(10240| 41));_os_.marshal(lastlvluptotalonlinetime);
    _os_.marshal((short)(10240| 25));_os_.marshal(lastlogouttime);
    _os_.marshal((short)( 8192| 20));_os_.marshal(logindaycount);
    _os_.marshal((short)(10240| 42));_os_.marshal(lastaddlogindaytime);
    _os_.marshal((short)( 8192| 21));_os_.marshal(continuesloginday);
    _os_.marshal((short)(10240| 22));_os_.marshal(totaldayonlinetime);
    _os_.marshal((short)( 8192| 23));_os_.marshal(leftgifttimes);
    _os_.marshal((short)( 8192| 24));_os_.marshal(monthcardlefttimes);
    _os_.marshal((short)( 8192| 26));_os_.marshal(buymonthcardtimes);
    _os_.marshal((short)( 8192| 27));_os_.marshal(buygrowplan);
    _os_.marshal((short)( 8192| 45));_os_.marshal(growonetime);
    _os_.marshal((short)( 8192| 43));_os_.marshal(buygrowplantwo);
    _os_.marshal((short)( 8192| 46));_os_.marshal(growtwotime);
    _os_.marshal((short)( 8192| 44));_os_.marshal(buygrowplanthree);
    _os_.marshal((short)( 8192| 47));_os_.marshal(growthreetime);
    _os_.marshal((short)(18432|  2));_os_.marshal(name, xdb.Const.IO_CHARSET);
    _os_.marshal((short)( 8192| 36));_os_.marshal(changenametime);
    _os_.marshal((short)( 8192| 12));_os_.marshal(level);
    _os_.marshal((short)(10240| 35));_os_.marshal(lastlvluptime);
    _os_.marshal((short)( 8192| 14));_os_.marshal(viplevel);
    _os_.marshal((short)(10240| 18));_os_.marshal(vipexp);
    _os_.marshal((short)(24576| 16));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(currencys.size());
for (java.util.Map.Entry<Integer, Long> _e_ : currencys.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192| 28));_os_.marshal(gender);
    _os_.marshal((short)( 8192| 29));_os_.marshal(totalcostyuanbao);
    _os_.marshal((short)(10240| 32));_os_.marshal(lastaddtilitime);
    _os_.marshal((short)(10240| 37));_os_.marshal(lastworldtalktime);
    _os_.marshal((short)(10240| 38));_os_.marshal(silentendtime);
    _os_.marshal((short)( 8192| 39));_os_.marshal(leftreporttime);
    _os_.marshal((short)( 8192| 40));_os_.marshal(bereportedtime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):userid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):userid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):userid = _os_.unmarshal_int();
    				break;
    				case ( 8192|  4):profession = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):profession = _os_.unmarshal_short();
    				break;
    				case (10240| 19):createtime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 19):createtime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 19):createtime = _os_.unmarshal_int();
    				break;
    				case ( 8192| 34):serverid = _os_.unmarshal_int();
    				break;
    				case ( 6144| 34):serverid = _os_.unmarshal_short();
    				break;
    				case (10240|  5):lastlogintime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):lastlogintime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):lastlogintime = _os_.unmarshal_int();
    				break;
    				case (10240|  8):totalonlinetime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  8):totalonlinetime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  8):totalonlinetime = _os_.unmarshal_int();
    				break;
    				case (10240| 41):lastlvluptotalonlinetime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 41):lastlvluptotalonlinetime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 41):lastlvluptotalonlinetime = _os_.unmarshal_int();
    				break;
    				case (10240| 25):lastlogouttime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 25):lastlogouttime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 25):lastlogouttime = _os_.unmarshal_int();
    				break;
    				case ( 8192| 20):logindaycount = _os_.unmarshal_int();
    				break;
    				case ( 6144| 20):logindaycount = _os_.unmarshal_short();
    				break;
    				case (10240| 42):lastaddlogindaytime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 42):lastaddlogindaytime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 42):lastaddlogindaytime = _os_.unmarshal_int();
    				break;
    				case ( 8192| 21):continuesloginday = _os_.unmarshal_int();
    				break;
    				case ( 6144| 21):continuesloginday = _os_.unmarshal_short();
    				break;
    				case (10240| 22):totaldayonlinetime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 22):totaldayonlinetime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 22):totaldayonlinetime = _os_.unmarshal_int();
    				break;
    				case ( 8192| 23):leftgifttimes = _os_.unmarshal_int();
    				break;
    				case ( 6144| 23):leftgifttimes = _os_.unmarshal_short();
    				break;
    				case ( 8192| 24):monthcardlefttimes = _os_.unmarshal_int();
    				break;
    				case ( 6144| 24):monthcardlefttimes = _os_.unmarshal_short();
    				break;
    				case ( 8192| 26):buymonthcardtimes = _os_.unmarshal_int();
    				break;
    				case ( 6144| 26):buymonthcardtimes = _os_.unmarshal_short();
    				break;
    				case ( 8192| 27):buygrowplan = _os_.unmarshal_int();
    				break;
    				case ( 6144| 27):buygrowplan = _os_.unmarshal_short();
    				break;
    				case ( 8192| 45):growonetime = _os_.unmarshal_int();
    				break;
    				case ( 6144| 45):growonetime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 43):buygrowplantwo = _os_.unmarshal_int();
    				break;
    				case ( 6144| 43):buygrowplantwo = _os_.unmarshal_short();
    				break;
    				case ( 8192| 46):growtwotime = _os_.unmarshal_int();
    				break;
    				case ( 6144| 46):growtwotime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 44):buygrowplanthree = _os_.unmarshal_int();
    				break;
    				case ( 6144| 44):buygrowplanthree = _os_.unmarshal_short();
    				break;
    				case ( 8192| 47):growthreetime = _os_.unmarshal_int();
    				break;
    				case ( 6144| 47):growthreetime = _os_.unmarshal_short();
    				break;
    				case (18432|  2):name = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case ( 8192| 36):changenametime = _os_.unmarshal_int();
    				break;
    				case ( 6144| 36):changenametime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 12):level = _os_.unmarshal_int();
    				break;
    				case ( 6144| 12):level = _os_.unmarshal_short();
    				break;
    				case (10240| 35):lastlvluptime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 35):lastlvluptime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 35):lastlvluptime = _os_.unmarshal_int();
    				break;
    				case ( 8192| 14):viplevel = _os_.unmarshal_int();
    				break;
    				case ( 6144| 14):viplevel = _os_.unmarshal_short();
    				break;
    				case (10240| 18):vipexp = _os_.unmarshal_long();
    				break;
    				case ( 6144| 18):vipexp = _os_.unmarshal_short();
    				break;
    				case ( 8192| 18):vipexp = _os_.unmarshal_int();
    				break;
    				case (24576| 16):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		currencys = new java.util.HashMap<Integer, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		currencys.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192| 28):gender = _os_.unmarshal_int();
    				break;
    				case ( 6144| 28):gender = _os_.unmarshal_short();
    				break;
    				case ( 8192| 29):totalcostyuanbao = _os_.unmarshal_int();
    				break;
    				case ( 6144| 29):totalcostyuanbao = _os_.unmarshal_short();
    				break;
    				case (10240| 32):lastaddtilitime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 32):lastaddtilitime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 32):lastaddtilitime = _os_.unmarshal_int();
    				break;
    				case (10240| 37):lastworldtalktime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 37):lastworldtalktime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 37):lastworldtalktime = _os_.unmarshal_int();
    				break;
    				case (10240| 38):silentendtime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 38):silentendtime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 38):silentendtime = _os_.unmarshal_int();
    				break;
    				case ( 8192| 39):leftreporttime = _os_.unmarshal_int();
    				break;
    				case ( 6144| 39):leftreporttime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 40):bereportedtime = _os_.unmarshal_int();
    				break;
    				case ( 6144| 40):bereportedtime = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleInfo copy() {
		_xdb_verify_unsafe_();
		return new RoleInfo(this);
	}

	@Override
	public xbean.RoleInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleInfo toBean() {
		_xdb_verify_unsafe_();
		return new RoleInfo(this); // same as copy()
	}

	@Override
	public xbean.RoleInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getUserid() { // 
		_xdb_verify_unsafe_();
		return userid;
	}

	@Override
	public int getProfession() { // 
		_xdb_verify_unsafe_();
		return profession;
	}

	@Override
	public long getCreatetime() { // 
		_xdb_verify_unsafe_();
		return createtime;
	}

	@Override
	public int getServerid() { // 
		_xdb_verify_unsafe_();
		return serverid;
	}

	@Override
	public long getLastlogintime() { // 
		_xdb_verify_unsafe_();
		return lastlogintime;
	}

	@Override
	public long getTotalonlinetime() { // 
		_xdb_verify_unsafe_();
		return totalonlinetime;
	}

	@Override
	public long getLastlvluptotalonlinetime() { // 
		_xdb_verify_unsafe_();
		return lastlvluptotalonlinetime;
	}

	@Override
	public long getLastlogouttime() { // 用来判断是否0点在线的问题
		_xdb_verify_unsafe_();
		return lastlogouttime;
	}

	@Override
	public int getLogindaycount() { // 总的登录天数
		_xdb_verify_unsafe_();
		return logindaycount;
	}

	@Override
	public long getLastaddlogindaytime() { // 
		_xdb_verify_unsafe_();
		return lastaddlogindaytime;
	}

	@Override
	public int getContinuesloginday() { // 连续登录天数
		_xdb_verify_unsafe_();
		return continuesloginday;
	}

	@Override
	public long getTotaldayonlinetime() { // 每天在线时长，每天登录的时候重置
		_xdb_verify_unsafe_();
		return totaldayonlinetime;
	}

	@Override
	public int getLeftgifttimes() { // 剩余连续登录奖励领取次数,每天登录的时候重置
		_xdb_verify_unsafe_();
		return leftgifttimes;
	}

	@Override
	public int getMonthcardlefttimes() { // 月卡剩余天数
		_xdb_verify_unsafe_();
		return monthcardlefttimes;
	}

	@Override
	public int getBuymonthcardtimes() { // 购买月卡次数
		_xdb_verify_unsafe_();
		return buymonthcardtimes;
	}

	@Override
	public int getBuygrowplan() { // 是否购买过成长计划
		_xdb_verify_unsafe_();
		return buygrowplan;
	}

	@Override
	public int getGrowonetime() { // 购买成长计划1的时间，用登录第几天表示
		_xdb_verify_unsafe_();
		return growonetime;
	}

	@Override
	public int getBuygrowplantwo() { // 购买成长计划2，苹果测试用
		_xdb_verify_unsafe_();
		return buygrowplantwo;
	}

	@Override
	public int getGrowtwotime() { // 购买成长计划2的时间，用登录第几天表示
		_xdb_verify_unsafe_();
		return growtwotime;
	}

	@Override
	public int getBuygrowplanthree() { // 购买成长计划3，苹果测试用
		_xdb_verify_unsafe_();
		return buygrowplanthree;
	}

	@Override
	public int getGrowthreetime() { // 购买成长计划3的时间，用登录第几天表示
		_xdb_verify_unsafe_();
		return growthreetime;
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
	public int getChangenametime() { // 
		_xdb_verify_unsafe_();
		return changenametime;
	}

	@Override
	public int getLevel() { // 
		_xdb_verify_unsafe_();
		return level;
	}

	@Override
	public long getLastlvluptime() { // 
		_xdb_verify_unsafe_();
		return lastlvluptime;
	}

	@Override
	public int getViplevel() { // 
		_xdb_verify_unsafe_();
		return viplevel;
	}

	@Override
	public long getVipexp() { // 
		_xdb_verify_unsafe_();
		return vipexp;
	}

	@Override
	public java.util.Map<Integer, Long> getCurrencys() { // cfg.currency.CurrencyType -> num
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "currencys"), currencys);
	}

	@Override
	public java.util.Map<Integer, Long> getCurrencysAsData() { // cfg.currency.CurrencyType -> num
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Long> currencys;
		RoleInfo _o_ = this;
		currencys = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.currencys.entrySet())
			currencys.put(_e_.getKey(), _e_.getValue());
		return currencys;
	}

	@Override
	public int getGender() { // cfg.role.GenderType
		_xdb_verify_unsafe_();
		return gender;
	}

	@Override
	public int getTotalcostyuanbao() { // 累计花费元宝,不算绑定的
		_xdb_verify_unsafe_();
		return totalcostyuanbao;
	}

	@Override
	public long getLastaddtilitime() { // 上次自动加体力的时间
		_xdb_verify_unsafe_();
		return lastaddtilitime;
	}

	@Override
	public long getLastworldtalktime() { // 上次世界频道发言时间
		_xdb_verify_unsafe_();
		return lastworldtalktime;
	}

	@Override
	public long getSilentendtime() { // 禁言到期时间
		_xdb_verify_unsafe_();
		return silentendtime;
	}

	@Override
	public int getLeftreporttime() { // 剩余举报次数，每天重置
		_xdb_verify_unsafe_();
		return leftreporttime;
	}

	@Override
	public int getBereportedtime() { // 被举报次数，每次被禁言后就清空一次
		_xdb_verify_unsafe_();
		return bereportedtime;
	}

	@Override
	public void setUserid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "userid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, userid) {
					public void rollback() { userid = _xdb_saved; }
				};}});
		userid = _v_;
	}

	@Override
	public void setProfession(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "profession") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, profession) {
					public void rollback() { profession = _xdb_saved; }
				};}});
		profession = _v_;
	}

	@Override
	public void setCreatetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "createtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, createtime) {
					public void rollback() { createtime = _xdb_saved; }
				};}});
		createtime = _v_;
	}

	@Override
	public void setServerid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "serverid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, serverid) {
					public void rollback() { serverid = _xdb_saved; }
				};}});
		serverid = _v_;
	}

	@Override
	public void setLastlogintime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastlogintime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastlogintime) {
					public void rollback() { lastlogintime = _xdb_saved; }
				};}});
		lastlogintime = _v_;
	}

	@Override
	public void setTotalonlinetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalonlinetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalonlinetime) {
					public void rollback() { totalonlinetime = _xdb_saved; }
				};}});
		totalonlinetime = _v_;
	}

	@Override
	public void setLastlvluptotalonlinetime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastlvluptotalonlinetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastlvluptotalonlinetime) {
					public void rollback() { lastlvluptotalonlinetime = _xdb_saved; }
				};}});
		lastlvluptotalonlinetime = _v_;
	}

	@Override
	public void setLastlogouttime(long _v_) { // 用来判断是否0点在线的问题
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastlogouttime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastlogouttime) {
					public void rollback() { lastlogouttime = _xdb_saved; }
				};}});
		lastlogouttime = _v_;
	}

	@Override
	public void setLogindaycount(int _v_) { // 总的登录天数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "logindaycount") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, logindaycount) {
					public void rollback() { logindaycount = _xdb_saved; }
				};}});
		logindaycount = _v_;
	}

	@Override
	public void setLastaddlogindaytime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastaddlogindaytime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastaddlogindaytime) {
					public void rollback() { lastaddlogindaytime = _xdb_saved; }
				};}});
		lastaddlogindaytime = _v_;
	}

	@Override
	public void setContinuesloginday(int _v_) { // 连续登录天数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "continuesloginday") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, continuesloginday) {
					public void rollback() { continuesloginday = _xdb_saved; }
				};}});
		continuesloginday = _v_;
	}

	@Override
	public void setTotaldayonlinetime(long _v_) { // 每天在线时长，每天登录的时候重置
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totaldayonlinetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totaldayonlinetime) {
					public void rollback() { totaldayonlinetime = _xdb_saved; }
				};}});
		totaldayonlinetime = _v_;
	}

	@Override
	public void setLeftgifttimes(int _v_) { // 剩余连续登录奖励领取次数,每天登录的时候重置
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "leftgifttimes") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, leftgifttimes) {
					public void rollback() { leftgifttimes = _xdb_saved; }
				};}});
		leftgifttimes = _v_;
	}

	@Override
	public void setMonthcardlefttimes(int _v_) { // 月卡剩余天数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "monthcardlefttimes") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, monthcardlefttimes) {
					public void rollback() { monthcardlefttimes = _xdb_saved; }
				};}});
		monthcardlefttimes = _v_;
	}

	@Override
	public void setBuymonthcardtimes(int _v_) { // 购买月卡次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "buymonthcardtimes") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, buymonthcardtimes) {
					public void rollback() { buymonthcardtimes = _xdb_saved; }
				};}});
		buymonthcardtimes = _v_;
	}

	@Override
	public void setBuygrowplan(int _v_) { // 是否购买过成长计划
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "buygrowplan") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, buygrowplan) {
					public void rollback() { buygrowplan = _xdb_saved; }
				};}});
		buygrowplan = _v_;
	}

	@Override
	public void setGrowonetime(int _v_) { // 购买成长计划1的时间，用登录第几天表示
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "growonetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, growonetime) {
					public void rollback() { growonetime = _xdb_saved; }
				};}});
		growonetime = _v_;
	}

	@Override
	public void setBuygrowplantwo(int _v_) { // 购买成长计划2，苹果测试用
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "buygrowplantwo") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, buygrowplantwo) {
					public void rollback() { buygrowplantwo = _xdb_saved; }
				};}});
		buygrowplantwo = _v_;
	}

	@Override
	public void setGrowtwotime(int _v_) { // 购买成长计划2的时间，用登录第几天表示
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "growtwotime") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, growtwotime) {
					public void rollback() { growtwotime = _xdb_saved; }
				};}});
		growtwotime = _v_;
	}

	@Override
	public void setBuygrowplanthree(int _v_) { // 购买成长计划3，苹果测试用
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "buygrowplanthree") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, buygrowplanthree) {
					public void rollback() { buygrowplanthree = _xdb_saved; }
				};}});
		buygrowplanthree = _v_;
	}

	@Override
	public void setGrowthreetime(int _v_) { // 购买成长计划3的时间，用登录第几天表示
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "growthreetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, growthreetime) {
					public void rollback() { growthreetime = _xdb_saved; }
				};}});
		growthreetime = _v_;
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
	public void setChangenametime(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "changenametime") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, changenametime) {
					public void rollback() { changenametime = _xdb_saved; }
				};}});
		changenametime = _v_;
	}

	@Override
	public void setLevel(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "level") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, level) {
					public void rollback() { level = _xdb_saved; }
				};}});
		level = _v_;
	}

	@Override
	public void setLastlvluptime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastlvluptime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastlvluptime) {
					public void rollback() { lastlvluptime = _xdb_saved; }
				};}});
		lastlvluptime = _v_;
	}

	@Override
	public void setViplevel(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "viplevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, viplevel) {
					public void rollback() { viplevel = _xdb_saved; }
				};}});
		viplevel = _v_;
	}

	@Override
	public void setVipexp(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "vipexp") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, vipexp) {
					public void rollback() { vipexp = _xdb_saved; }
				};}});
		vipexp = _v_;
	}

	@Override
	public void setGender(int _v_) { // cfg.role.GenderType
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "gender") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, gender) {
					public void rollback() { gender = _xdb_saved; }
				};}});
		gender = _v_;
	}

	@Override
	public void setTotalcostyuanbao(int _v_) { // 累计花费元宝,不算绑定的
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalcostyuanbao") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, totalcostyuanbao) {
					public void rollback() { totalcostyuanbao = _xdb_saved; }
				};}});
		totalcostyuanbao = _v_;
	}

	@Override
	public void setLastaddtilitime(long _v_) { // 上次自动加体力的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastaddtilitime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastaddtilitime) {
					public void rollback() { lastaddtilitime = _xdb_saved; }
				};}});
		lastaddtilitime = _v_;
	}

	@Override
	public void setLastworldtalktime(long _v_) { // 上次世界频道发言时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastworldtalktime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastworldtalktime) {
					public void rollback() { lastworldtalktime = _xdb_saved; }
				};}});
		lastworldtalktime = _v_;
	}

	@Override
	public void setSilentendtime(long _v_) { // 禁言到期时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "silentendtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, silentendtime) {
					public void rollback() { silentendtime = _xdb_saved; }
				};}});
		silentendtime = _v_;
	}

	@Override
	public void setLeftreporttime(int _v_) { // 剩余举报次数，每天重置
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "leftreporttime") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, leftreporttime) {
					public void rollback() { leftreporttime = _xdb_saved; }
				};}});
		leftreporttime = _v_;
	}

	@Override
	public void setBereportedtime(int _v_) { // 被举报次数，每次被禁言后就清空一次
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "bereportedtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, bereportedtime) {
					public void rollback() { bereportedtime = _xdb_saved; }
				};}});
		bereportedtime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleInfo _o_ = null;
		if ( _o1_ instanceof RoleInfo ) _o_ = (RoleInfo)_o1_;
		else if ( _o1_ instanceof RoleInfo.Const ) _o_ = ((RoleInfo.Const)_o1_).nThis();
		else return false;
		if (userid != _o_.userid) return false;
		if (profession != _o_.profession) return false;
		if (createtime != _o_.createtime) return false;
		if (serverid != _o_.serverid) return false;
		if (lastlogintime != _o_.lastlogintime) return false;
		if (totalonlinetime != _o_.totalonlinetime) return false;
		if (lastlvluptotalonlinetime != _o_.lastlvluptotalonlinetime) return false;
		if (lastlogouttime != _o_.lastlogouttime) return false;
		if (logindaycount != _o_.logindaycount) return false;
		if (lastaddlogindaytime != _o_.lastaddlogindaytime) return false;
		if (continuesloginday != _o_.continuesloginday) return false;
		if (totaldayonlinetime != _o_.totaldayonlinetime) return false;
		if (leftgifttimes != _o_.leftgifttimes) return false;
		if (monthcardlefttimes != _o_.monthcardlefttimes) return false;
		if (buymonthcardtimes != _o_.buymonthcardtimes) return false;
		if (buygrowplan != _o_.buygrowplan) return false;
		if (growonetime != _o_.growonetime) return false;
		if (buygrowplantwo != _o_.buygrowplantwo) return false;
		if (growtwotime != _o_.growtwotime) return false;
		if (buygrowplanthree != _o_.buygrowplanthree) return false;
		if (growthreetime != _o_.growthreetime) return false;
		if (!name.equals(_o_.name)) return false;
		if (changenametime != _o_.changenametime) return false;
		if (level != _o_.level) return false;
		if (lastlvluptime != _o_.lastlvluptime) return false;
		if (viplevel != _o_.viplevel) return false;
		if (vipexp != _o_.vipexp) return false;
		if (!currencys.equals(_o_.currencys)) return false;
		if (gender != _o_.gender) return false;
		if (totalcostyuanbao != _o_.totalcostyuanbao) return false;
		if (lastaddtilitime != _o_.lastaddtilitime) return false;
		if (lastworldtalktime != _o_.lastworldtalktime) return false;
		if (silentendtime != _o_.silentendtime) return false;
		if (leftreporttime != _o_.leftreporttime) return false;
		if (bereportedtime != _o_.bereportedtime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += userid;
		_h_ += profession;
		_h_ += createtime;
		_h_ += serverid;
		_h_ += lastlogintime;
		_h_ += totalonlinetime;
		_h_ += lastlvluptotalonlinetime;
		_h_ += lastlogouttime;
		_h_ += logindaycount;
		_h_ += lastaddlogindaytime;
		_h_ += continuesloginday;
		_h_ += totaldayonlinetime;
		_h_ += leftgifttimes;
		_h_ += monthcardlefttimes;
		_h_ += buymonthcardtimes;
		_h_ += buygrowplan;
		_h_ += growonetime;
		_h_ += buygrowplantwo;
		_h_ += growtwotime;
		_h_ += buygrowplanthree;
		_h_ += growthreetime;
		_h_ += name.hashCode();
		_h_ += changenametime;
		_h_ += level;
		_h_ += lastlvluptime;
		_h_ += viplevel;
		_h_ += vipexp;
		_h_ += currencys.hashCode();
		_h_ += gender;
		_h_ += totalcostyuanbao;
		_h_ += lastaddtilitime;
		_h_ += lastworldtalktime;
		_h_ += silentendtime;
		_h_ += leftreporttime;
		_h_ += bereportedtime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(userid);
		_sb_.append(",");
		_sb_.append(profession);
		_sb_.append(",");
		_sb_.append(createtime);
		_sb_.append(",");
		_sb_.append(serverid);
		_sb_.append(",");
		_sb_.append(lastlogintime);
		_sb_.append(",");
		_sb_.append(totalonlinetime);
		_sb_.append(",");
		_sb_.append(lastlvluptotalonlinetime);
		_sb_.append(",");
		_sb_.append(lastlogouttime);
		_sb_.append(",");
		_sb_.append(logindaycount);
		_sb_.append(",");
		_sb_.append(lastaddlogindaytime);
		_sb_.append(",");
		_sb_.append(continuesloginday);
		_sb_.append(",");
		_sb_.append(totaldayonlinetime);
		_sb_.append(",");
		_sb_.append(leftgifttimes);
		_sb_.append(",");
		_sb_.append(monthcardlefttimes);
		_sb_.append(",");
		_sb_.append(buymonthcardtimes);
		_sb_.append(",");
		_sb_.append(buygrowplan);
		_sb_.append(",");
		_sb_.append(growonetime);
		_sb_.append(",");
		_sb_.append(buygrowplantwo);
		_sb_.append(",");
		_sb_.append(growtwotime);
		_sb_.append(",");
		_sb_.append(buygrowplanthree);
		_sb_.append(",");
		_sb_.append(growthreetime);
		_sb_.append(",");
		_sb_.append("'").append(name).append("'");
		_sb_.append(",");
		_sb_.append(changenametime);
		_sb_.append(",");
		_sb_.append(level);
		_sb_.append(",");
		_sb_.append(lastlvluptime);
		_sb_.append(",");
		_sb_.append(viplevel);
		_sb_.append(",");
		_sb_.append(vipexp);
		_sb_.append(",");
		_sb_.append(currencys);
		_sb_.append(",");
		_sb_.append(gender);
		_sb_.append(",");
		_sb_.append(totalcostyuanbao);
		_sb_.append(",");
		_sb_.append(lastaddtilitime);
		_sb_.append(",");
		_sb_.append(lastworldtalktime);
		_sb_.append(",");
		_sb_.append(silentendtime);
		_sb_.append(",");
		_sb_.append(leftreporttime);
		_sb_.append(",");
		_sb_.append(bereportedtime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("userid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("profession"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("createtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("serverid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastlogintime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalonlinetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastlvluptotalonlinetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastlogouttime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("logindaycount"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastaddlogindaytime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("continuesloginday"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totaldayonlinetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("leftgifttimes"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("monthcardlefttimes"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("buymonthcardtimes"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("buygrowplan"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("growonetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("buygrowplantwo"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("growtwotime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("buygrowplanthree"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("growthreetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("name"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("changenametime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("level"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastlvluptime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("viplevel"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("vipexp"));
		lb.add(new xdb.logs.ListenableMap().setVarName("currencys"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("gender"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalcostyuanbao"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastaddtilitime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastworldtalktime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("silentendtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("leftreporttime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("bereportedtime"));
		return lb;
	}

	private class Const implements xbean.RoleInfo {
		RoleInfo nThis() {
			return RoleInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleInfo copy() {
			return RoleInfo.this.copy();
		}

		@Override
		public xbean.RoleInfo toData() {
			return RoleInfo.this.toData();
		}

		public xbean.RoleInfo toBean() {
			return RoleInfo.this.toBean();
		}

		@Override
		public xbean.RoleInfo toDataIf() {
			return RoleInfo.this.toDataIf();
		}

		public xbean.RoleInfo toBeanIf() {
			return RoleInfo.this.toBeanIf();
		}

		@Override
		public long getUserid() { // 
			_xdb_verify_unsafe_();
			return userid;
		}

		@Override
		public int getProfession() { // 
			_xdb_verify_unsafe_();
			return profession;
		}

		@Override
		public long getCreatetime() { // 
			_xdb_verify_unsafe_();
			return createtime;
		}

		@Override
		public int getServerid() { // 
			_xdb_verify_unsafe_();
			return serverid;
		}

		@Override
		public long getLastlogintime() { // 
			_xdb_verify_unsafe_();
			return lastlogintime;
		}

		@Override
		public long getTotalonlinetime() { // 
			_xdb_verify_unsafe_();
			return totalonlinetime;
		}

		@Override
		public long getLastlvluptotalonlinetime() { // 
			_xdb_verify_unsafe_();
			return lastlvluptotalonlinetime;
		}

		@Override
		public long getLastlogouttime() { // 用来判断是否0点在线的问题
			_xdb_verify_unsafe_();
			return lastlogouttime;
		}

		@Override
		public int getLogindaycount() { // 总的登录天数
			_xdb_verify_unsafe_();
			return logindaycount;
		}

		@Override
		public long getLastaddlogindaytime() { // 
			_xdb_verify_unsafe_();
			return lastaddlogindaytime;
		}

		@Override
		public int getContinuesloginday() { // 连续登录天数
			_xdb_verify_unsafe_();
			return continuesloginday;
		}

		@Override
		public long getTotaldayonlinetime() { // 每天在线时长，每天登录的时候重置
			_xdb_verify_unsafe_();
			return totaldayonlinetime;
		}

		@Override
		public int getLeftgifttimes() { // 剩余连续登录奖励领取次数,每天登录的时候重置
			_xdb_verify_unsafe_();
			return leftgifttimes;
		}

		@Override
		public int getMonthcardlefttimes() { // 月卡剩余天数
			_xdb_verify_unsafe_();
			return monthcardlefttimes;
		}

		@Override
		public int getBuymonthcardtimes() { // 购买月卡次数
			_xdb_verify_unsafe_();
			return buymonthcardtimes;
		}

		@Override
		public int getBuygrowplan() { // 是否购买过成长计划
			_xdb_verify_unsafe_();
			return buygrowplan;
		}

		@Override
		public int getGrowonetime() { // 购买成长计划1的时间，用登录第几天表示
			_xdb_verify_unsafe_();
			return growonetime;
		}

		@Override
		public int getBuygrowplantwo() { // 购买成长计划2，苹果测试用
			_xdb_verify_unsafe_();
			return buygrowplantwo;
		}

		@Override
		public int getGrowtwotime() { // 购买成长计划2的时间，用登录第几天表示
			_xdb_verify_unsafe_();
			return growtwotime;
		}

		@Override
		public int getBuygrowplanthree() { // 购买成长计划3，苹果测试用
			_xdb_verify_unsafe_();
			return buygrowplanthree;
		}

		@Override
		public int getGrowthreetime() { // 购买成长计划3的时间，用登录第几天表示
			_xdb_verify_unsafe_();
			return growthreetime;
		}

		@Override
		public String getName() { // 
			_xdb_verify_unsafe_();
			return name;
		}

		@Override
		public com.goldhuman.Common.Octets getNameOctets() { // 
			_xdb_verify_unsafe_();
			return RoleInfo.this.getNameOctets();
		}

		@Override
		public int getChangenametime() { // 
			_xdb_verify_unsafe_();
			return changenametime;
		}

		@Override
		public int getLevel() { // 
			_xdb_verify_unsafe_();
			return level;
		}

		@Override
		public long getLastlvluptime() { // 
			_xdb_verify_unsafe_();
			return lastlvluptime;
		}

		@Override
		public int getViplevel() { // 
			_xdb_verify_unsafe_();
			return viplevel;
		}

		@Override
		public long getVipexp() { // 
			_xdb_verify_unsafe_();
			return vipexp;
		}

		@Override
		public java.util.Map<Integer, Long> getCurrencys() { // cfg.currency.CurrencyType -> num
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(currencys);
		}

		@Override
		public java.util.Map<Integer, Long> getCurrencysAsData() { // cfg.currency.CurrencyType -> num
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Long> currencys;
			RoleInfo _o_ = RoleInfo.this;
			currencys = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.currencys.entrySet())
				currencys.put(_e_.getKey(), _e_.getValue());
			return currencys;
		}

		@Override
		public int getGender() { // cfg.role.GenderType
			_xdb_verify_unsafe_();
			return gender;
		}

		@Override
		public int getTotalcostyuanbao() { // 累计花费元宝,不算绑定的
			_xdb_verify_unsafe_();
			return totalcostyuanbao;
		}

		@Override
		public long getLastaddtilitime() { // 上次自动加体力的时间
			_xdb_verify_unsafe_();
			return lastaddtilitime;
		}

		@Override
		public long getLastworldtalktime() { // 上次世界频道发言时间
			_xdb_verify_unsafe_();
			return lastworldtalktime;
		}

		@Override
		public long getSilentendtime() { // 禁言到期时间
			_xdb_verify_unsafe_();
			return silentendtime;
		}

		@Override
		public int getLeftreporttime() { // 剩余举报次数，每天重置
			_xdb_verify_unsafe_();
			return leftreporttime;
		}

		@Override
		public int getBereportedtime() { // 被举报次数，每次被禁言后就清空一次
			_xdb_verify_unsafe_();
			return bereportedtime;
		}

		@Override
		public void setUserid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setProfession(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCreatetime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setServerid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastlogintime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalonlinetime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastlvluptotalonlinetime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastlogouttime(long _v_) { // 用来判断是否0点在线的问题
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLogindaycount(int _v_) { // 总的登录天数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastaddlogindaytime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setContinuesloginday(int _v_) { // 连续登录天数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotaldayonlinetime(long _v_) { // 每天在线时长，每天登录的时候重置
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLeftgifttimes(int _v_) { // 剩余连续登录奖励领取次数,每天登录的时候重置
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMonthcardlefttimes(int _v_) { // 月卡剩余天数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBuymonthcardtimes(int _v_) { // 购买月卡次数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBuygrowplan(int _v_) { // 是否购买过成长计划
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGrowonetime(int _v_) { // 购买成长计划1的时间，用登录第几天表示
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBuygrowplantwo(int _v_) { // 购买成长计划2，苹果测试用
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGrowtwotime(int _v_) { // 购买成长计划2的时间，用登录第几天表示
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBuygrowplanthree(int _v_) { // 购买成长计划3，苹果测试用
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGrowthreetime(int _v_) { // 购买成长计划3的时间，用登录第几天表示
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
		public void setChangenametime(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLevel(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastlvluptime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setViplevel(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setVipexp(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGender(int _v_) { // cfg.role.GenderType
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalcostyuanbao(int _v_) { // 累计花费元宝,不算绑定的
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastaddtilitime(long _v_) { // 上次自动加体力的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastworldtalktime(long _v_) { // 上次世界频道发言时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setSilentendtime(long _v_) { // 禁言到期时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLeftreporttime(int _v_) { // 剩余举报次数，每天重置
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBereportedtime(int _v_) { // 被举报次数，每次被禁言后就清空一次
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
			return RoleInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleInfo.this.toString();
		}

	}

	public static final class Data implements xbean.RoleInfo {
		private long userid; // 
		private int profession; // 
		private long createtime; // 
		private int serverid; // 
		private long lastlogintime; // 
		private long totalonlinetime; // 
		private long lastlvluptotalonlinetime; // 
		private long lastlogouttime; // 用来判断是否0点在线的问题
		private int logindaycount; // 总的登录天数
		private long lastaddlogindaytime; // 
		private int continuesloginday; // 连续登录天数
		private long totaldayonlinetime; // 每天在线时长，每天登录的时候重置
		private int leftgifttimes; // 剩余连续登录奖励领取次数,每天登录的时候重置
		private int monthcardlefttimes; // 月卡剩余天数
		private int buymonthcardtimes; // 购买月卡次数
		private int buygrowplan; // 是否购买过成长计划
		private int growonetime; // 购买成长计划1的时间，用登录第几天表示
		private int buygrowplantwo; // 购买成长计划2，苹果测试用
		private int growtwotime; // 购买成长计划2的时间，用登录第几天表示
		private int buygrowplanthree; // 购买成长计划3，苹果测试用
		private int growthreetime; // 购买成长计划3的时间，用登录第几天表示
		private String name; // 
		private int changenametime; // 
		private int level; // 
		private long lastlvluptime; // 
		private int viplevel; // 
		private long vipexp; // 
		private java.util.HashMap<Integer, Long> currencys; // cfg.currency.CurrencyType -> num
		private int gender; // cfg.role.GenderType
		private int totalcostyuanbao; // 累计花费元宝,不算绑定的
		private long lastaddtilitime; // 上次自动加体力的时间
		private long lastworldtalktime; // 上次世界频道发言时间
		private long silentendtime; // 禁言到期时间
		private int leftreporttime; // 剩余举报次数，每天重置
		private int bereportedtime; // 被举报次数，每次被禁言后就清空一次

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			name = "";
			currencys = new java.util.HashMap<Integer, Long>();
		}

		Data(xbean.RoleInfo _o1_) {
			if (_o1_ instanceof RoleInfo) assign((RoleInfo)_o1_);
			else if (_o1_ instanceof RoleInfo.Data) assign((RoleInfo.Data)_o1_);
			else if (_o1_ instanceof RoleInfo.Const) assign(((RoleInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleInfo _o_) {
			userid = _o_.userid;
			profession = _o_.profession;
			createtime = _o_.createtime;
			serverid = _o_.serverid;
			lastlogintime = _o_.lastlogintime;
			totalonlinetime = _o_.totalonlinetime;
			lastlvluptotalonlinetime = _o_.lastlvluptotalonlinetime;
			lastlogouttime = _o_.lastlogouttime;
			logindaycount = _o_.logindaycount;
			lastaddlogindaytime = _o_.lastaddlogindaytime;
			continuesloginday = _o_.continuesloginday;
			totaldayonlinetime = _o_.totaldayonlinetime;
			leftgifttimes = _o_.leftgifttimes;
			monthcardlefttimes = _o_.monthcardlefttimes;
			buymonthcardtimes = _o_.buymonthcardtimes;
			buygrowplan = _o_.buygrowplan;
			growonetime = _o_.growonetime;
			buygrowplantwo = _o_.buygrowplantwo;
			growtwotime = _o_.growtwotime;
			buygrowplanthree = _o_.buygrowplanthree;
			growthreetime = _o_.growthreetime;
			name = _o_.name;
			changenametime = _o_.changenametime;
			level = _o_.level;
			lastlvluptime = _o_.lastlvluptime;
			viplevel = _o_.viplevel;
			vipexp = _o_.vipexp;
			currencys = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.currencys.entrySet())
				currencys.put(_e_.getKey(), _e_.getValue());
			gender = _o_.gender;
			totalcostyuanbao = _o_.totalcostyuanbao;
			lastaddtilitime = _o_.lastaddtilitime;
			lastworldtalktime = _o_.lastworldtalktime;
			silentendtime = _o_.silentendtime;
			leftreporttime = _o_.leftreporttime;
			bereportedtime = _o_.bereportedtime;
		}

		private void assign(RoleInfo.Data _o_) {
			userid = _o_.userid;
			profession = _o_.profession;
			createtime = _o_.createtime;
			serverid = _o_.serverid;
			lastlogintime = _o_.lastlogintime;
			totalonlinetime = _o_.totalonlinetime;
			lastlvluptotalonlinetime = _o_.lastlvluptotalonlinetime;
			lastlogouttime = _o_.lastlogouttime;
			logindaycount = _o_.logindaycount;
			lastaddlogindaytime = _o_.lastaddlogindaytime;
			continuesloginday = _o_.continuesloginday;
			totaldayonlinetime = _o_.totaldayonlinetime;
			leftgifttimes = _o_.leftgifttimes;
			monthcardlefttimes = _o_.monthcardlefttimes;
			buymonthcardtimes = _o_.buymonthcardtimes;
			buygrowplan = _o_.buygrowplan;
			growonetime = _o_.growonetime;
			buygrowplantwo = _o_.buygrowplantwo;
			growtwotime = _o_.growtwotime;
			buygrowplanthree = _o_.buygrowplanthree;
			growthreetime = _o_.growthreetime;
			name = _o_.name;
			changenametime = _o_.changenametime;
			level = _o_.level;
			lastlvluptime = _o_.lastlvluptime;
			viplevel = _o_.viplevel;
			vipexp = _o_.vipexp;
			currencys = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.currencys.entrySet())
				currencys.put(_e_.getKey(), _e_.getValue());
			gender = _o_.gender;
			totalcostyuanbao = _o_.totalcostyuanbao;
			lastaddtilitime = _o_.lastaddtilitime;
			lastworldtalktime = _o_.lastworldtalktime;
			silentendtime = _o_.silentendtime;
			leftreporttime = _o_.leftreporttime;
			bereportedtime = _o_.bereportedtime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)35);
	_os_.marshal((short)(10240|  1));_os_.marshal(userid);
	_os_.marshal((short)( 8192|  4));_os_.marshal(profession);
	_os_.marshal((short)(10240| 19));_os_.marshal(createtime);
	_os_.marshal((short)( 8192| 34));_os_.marshal(serverid);
	_os_.marshal((short)(10240|  5));_os_.marshal(lastlogintime);
	_os_.marshal((short)(10240|  8));_os_.marshal(totalonlinetime);
	_os_.marshal((short)(10240| 41));_os_.marshal(lastlvluptotalonlinetime);
	_os_.marshal((short)(10240| 25));_os_.marshal(lastlogouttime);
	_os_.marshal((short)( 8192| 20));_os_.marshal(logindaycount);
	_os_.marshal((short)(10240| 42));_os_.marshal(lastaddlogindaytime);
	_os_.marshal((short)( 8192| 21));_os_.marshal(continuesloginday);
	_os_.marshal((short)(10240| 22));_os_.marshal(totaldayonlinetime);
	_os_.marshal((short)( 8192| 23));_os_.marshal(leftgifttimes);
	_os_.marshal((short)( 8192| 24));_os_.marshal(monthcardlefttimes);
	_os_.marshal((short)( 8192| 26));_os_.marshal(buymonthcardtimes);
	_os_.marshal((short)( 8192| 27));_os_.marshal(buygrowplan);
	_os_.marshal((short)( 8192| 45));_os_.marshal(growonetime);
	_os_.marshal((short)( 8192| 43));_os_.marshal(buygrowplantwo);
	_os_.marshal((short)( 8192| 46));_os_.marshal(growtwotime);
	_os_.marshal((short)( 8192| 44));_os_.marshal(buygrowplanthree);
	_os_.marshal((short)( 8192| 47));_os_.marshal(growthreetime);
	_os_.marshal((short)(18432|  2));_os_.marshal(name, xdb.Const.IO_CHARSET);
	_os_.marshal((short)( 8192| 36));_os_.marshal(changenametime);
	_os_.marshal((short)( 8192| 12));_os_.marshal(level);
	_os_.marshal((short)(10240| 35));_os_.marshal(lastlvluptime);
	_os_.marshal((short)( 8192| 14));_os_.marshal(viplevel);
	_os_.marshal((short)(10240| 18));_os_.marshal(vipexp);
	_os_.marshal((short)(24576| 16));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(currencys.size());
for (java.util.Map.Entry<Integer, Long> _e_ : currencys.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192| 28));_os_.marshal(gender);
	_os_.marshal((short)( 8192| 29));_os_.marshal(totalcostyuanbao);
	_os_.marshal((short)(10240| 32));_os_.marshal(lastaddtilitime);
	_os_.marshal((short)(10240| 37));_os_.marshal(lastworldtalktime);
	_os_.marshal((short)(10240| 38));_os_.marshal(silentendtime);
	_os_.marshal((short)( 8192| 39));_os_.marshal(leftreporttime);
	_os_.marshal((short)( 8192| 40));_os_.marshal(bereportedtime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):userid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):userid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):userid = _os_.unmarshal_int();
					break;
					case ( 8192|  4):profession = _os_.unmarshal_int();
					break;
					case ( 6144|  4):profession = _os_.unmarshal_short();
					break;
					case (10240| 19):createtime = _os_.unmarshal_long();
					break;
					case ( 6144| 19):createtime = _os_.unmarshal_short();
					break;
					case ( 8192| 19):createtime = _os_.unmarshal_int();
					break;
					case ( 8192| 34):serverid = _os_.unmarshal_int();
					break;
					case ( 6144| 34):serverid = _os_.unmarshal_short();
					break;
					case (10240|  5):lastlogintime = _os_.unmarshal_long();
					break;
					case ( 6144|  5):lastlogintime = _os_.unmarshal_short();
					break;
					case ( 8192|  5):lastlogintime = _os_.unmarshal_int();
					break;
					case (10240|  8):totalonlinetime = _os_.unmarshal_long();
					break;
					case ( 6144|  8):totalonlinetime = _os_.unmarshal_short();
					break;
					case ( 8192|  8):totalonlinetime = _os_.unmarshal_int();
					break;
					case (10240| 41):lastlvluptotalonlinetime = _os_.unmarshal_long();
					break;
					case ( 6144| 41):lastlvluptotalonlinetime = _os_.unmarshal_short();
					break;
					case ( 8192| 41):lastlvluptotalonlinetime = _os_.unmarshal_int();
					break;
					case (10240| 25):lastlogouttime = _os_.unmarshal_long();
					break;
					case ( 6144| 25):lastlogouttime = _os_.unmarshal_short();
					break;
					case ( 8192| 25):lastlogouttime = _os_.unmarshal_int();
					break;
					case ( 8192| 20):logindaycount = _os_.unmarshal_int();
					break;
					case ( 6144| 20):logindaycount = _os_.unmarshal_short();
					break;
					case (10240| 42):lastaddlogindaytime = _os_.unmarshal_long();
					break;
					case ( 6144| 42):lastaddlogindaytime = _os_.unmarshal_short();
					break;
					case ( 8192| 42):lastaddlogindaytime = _os_.unmarshal_int();
					break;
					case ( 8192| 21):continuesloginday = _os_.unmarshal_int();
					break;
					case ( 6144| 21):continuesloginday = _os_.unmarshal_short();
					break;
					case (10240| 22):totaldayonlinetime = _os_.unmarshal_long();
					break;
					case ( 6144| 22):totaldayonlinetime = _os_.unmarshal_short();
					break;
					case ( 8192| 22):totaldayonlinetime = _os_.unmarshal_int();
					break;
					case ( 8192| 23):leftgifttimes = _os_.unmarshal_int();
					break;
					case ( 6144| 23):leftgifttimes = _os_.unmarshal_short();
					break;
					case ( 8192| 24):monthcardlefttimes = _os_.unmarshal_int();
					break;
					case ( 6144| 24):monthcardlefttimes = _os_.unmarshal_short();
					break;
					case ( 8192| 26):buymonthcardtimes = _os_.unmarshal_int();
					break;
					case ( 6144| 26):buymonthcardtimes = _os_.unmarshal_short();
					break;
					case ( 8192| 27):buygrowplan = _os_.unmarshal_int();
					break;
					case ( 6144| 27):buygrowplan = _os_.unmarshal_short();
					break;
					case ( 8192| 45):growonetime = _os_.unmarshal_int();
					break;
					case ( 6144| 45):growonetime = _os_.unmarshal_short();
					break;
					case ( 8192| 43):buygrowplantwo = _os_.unmarshal_int();
					break;
					case ( 6144| 43):buygrowplantwo = _os_.unmarshal_short();
					break;
					case ( 8192| 46):growtwotime = _os_.unmarshal_int();
					break;
					case ( 6144| 46):growtwotime = _os_.unmarshal_short();
					break;
					case ( 8192| 44):buygrowplanthree = _os_.unmarshal_int();
					break;
					case ( 6144| 44):buygrowplanthree = _os_.unmarshal_short();
					break;
					case ( 8192| 47):growthreetime = _os_.unmarshal_int();
					break;
					case ( 6144| 47):growthreetime = _os_.unmarshal_short();
					break;
					case (18432|  2):name = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case ( 8192| 36):changenametime = _os_.unmarshal_int();
					break;
					case ( 6144| 36):changenametime = _os_.unmarshal_short();
					break;
					case ( 8192| 12):level = _os_.unmarshal_int();
					break;
					case ( 6144| 12):level = _os_.unmarshal_short();
					break;
					case (10240| 35):lastlvluptime = _os_.unmarshal_long();
					break;
					case ( 6144| 35):lastlvluptime = _os_.unmarshal_short();
					break;
					case ( 8192| 35):lastlvluptime = _os_.unmarshal_int();
					break;
					case ( 8192| 14):viplevel = _os_.unmarshal_int();
					break;
					case ( 6144| 14):viplevel = _os_.unmarshal_short();
					break;
					case (10240| 18):vipexp = _os_.unmarshal_long();
					break;
					case ( 6144| 18):vipexp = _os_.unmarshal_short();
					break;
					case ( 8192| 18):vipexp = _os_.unmarshal_int();
					break;
					case (24576| 16):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		currencys = new java.util.HashMap<Integer, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		currencys.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192| 28):gender = _os_.unmarshal_int();
					break;
					case ( 6144| 28):gender = _os_.unmarshal_short();
					break;
					case ( 8192| 29):totalcostyuanbao = _os_.unmarshal_int();
					break;
					case ( 6144| 29):totalcostyuanbao = _os_.unmarshal_short();
					break;
					case (10240| 32):lastaddtilitime = _os_.unmarshal_long();
					break;
					case ( 6144| 32):lastaddtilitime = _os_.unmarshal_short();
					break;
					case ( 8192| 32):lastaddtilitime = _os_.unmarshal_int();
					break;
					case (10240| 37):lastworldtalktime = _os_.unmarshal_long();
					break;
					case ( 6144| 37):lastworldtalktime = _os_.unmarshal_short();
					break;
					case ( 8192| 37):lastworldtalktime = _os_.unmarshal_int();
					break;
					case (10240| 38):silentendtime = _os_.unmarshal_long();
					break;
					case ( 6144| 38):silentendtime = _os_.unmarshal_short();
					break;
					case ( 8192| 38):silentendtime = _os_.unmarshal_int();
					break;
					case ( 8192| 39):leftreporttime = _os_.unmarshal_int();
					break;
					case ( 6144| 39):leftreporttime = _os_.unmarshal_short();
					break;
					case ( 8192| 40):bereportedtime = _os_.unmarshal_int();
					break;
					case ( 6144| 40):bereportedtime = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleInfo toData() {
			return new Data(this);
		}

		public xbean.RoleInfo toBean() {
			return new RoleInfo(this, null, null);
		}

		@Override
		public xbean.RoleInfo toDataIf() {
			return this;
		}

		public xbean.RoleInfo toBeanIf() {
			return new RoleInfo(this, null, null);
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
		public long getUserid() { // 
			return userid;
		}

		@Override
		public int getProfession() { // 
			return profession;
		}

		@Override
		public long getCreatetime() { // 
			return createtime;
		}

		@Override
		public int getServerid() { // 
			return serverid;
		}

		@Override
		public long getLastlogintime() { // 
			return lastlogintime;
		}

		@Override
		public long getTotalonlinetime() { // 
			return totalonlinetime;
		}

		@Override
		public long getLastlvluptotalonlinetime() { // 
			return lastlvluptotalonlinetime;
		}

		@Override
		public long getLastlogouttime() { // 用来判断是否0点在线的问题
			return lastlogouttime;
		}

		@Override
		public int getLogindaycount() { // 总的登录天数
			return logindaycount;
		}

		@Override
		public long getLastaddlogindaytime() { // 
			return lastaddlogindaytime;
		}

		@Override
		public int getContinuesloginday() { // 连续登录天数
			return continuesloginday;
		}

		@Override
		public long getTotaldayonlinetime() { // 每天在线时长，每天登录的时候重置
			return totaldayonlinetime;
		}

		@Override
		public int getLeftgifttimes() { // 剩余连续登录奖励领取次数,每天登录的时候重置
			return leftgifttimes;
		}

		@Override
		public int getMonthcardlefttimes() { // 月卡剩余天数
			return monthcardlefttimes;
		}

		@Override
		public int getBuymonthcardtimes() { // 购买月卡次数
			return buymonthcardtimes;
		}

		@Override
		public int getBuygrowplan() { // 是否购买过成长计划
			return buygrowplan;
		}

		@Override
		public int getGrowonetime() { // 购买成长计划1的时间，用登录第几天表示
			return growonetime;
		}

		@Override
		public int getBuygrowplantwo() { // 购买成长计划2，苹果测试用
			return buygrowplantwo;
		}

		@Override
		public int getGrowtwotime() { // 购买成长计划2的时间，用登录第几天表示
			return growtwotime;
		}

		@Override
		public int getBuygrowplanthree() { // 购买成长计划3，苹果测试用
			return buygrowplanthree;
		}

		@Override
		public int getGrowthreetime() { // 购买成长计划3的时间，用登录第几天表示
			return growthreetime;
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
		public int getChangenametime() { // 
			return changenametime;
		}

		@Override
		public int getLevel() { // 
			return level;
		}

		@Override
		public long getLastlvluptime() { // 
			return lastlvluptime;
		}

		@Override
		public int getViplevel() { // 
			return viplevel;
		}

		@Override
		public long getVipexp() { // 
			return vipexp;
		}

		@Override
		public java.util.Map<Integer, Long> getCurrencys() { // cfg.currency.CurrencyType -> num
			return currencys;
		}

		@Override
		public java.util.Map<Integer, Long> getCurrencysAsData() { // cfg.currency.CurrencyType -> num
			return currencys;
		}

		@Override
		public int getGender() { // cfg.role.GenderType
			return gender;
		}

		@Override
		public int getTotalcostyuanbao() { // 累计花费元宝,不算绑定的
			return totalcostyuanbao;
		}

		@Override
		public long getLastaddtilitime() { // 上次自动加体力的时间
			return lastaddtilitime;
		}

		@Override
		public long getLastworldtalktime() { // 上次世界频道发言时间
			return lastworldtalktime;
		}

		@Override
		public long getSilentendtime() { // 禁言到期时间
			return silentendtime;
		}

		@Override
		public int getLeftreporttime() { // 剩余举报次数，每天重置
			return leftreporttime;
		}

		@Override
		public int getBereportedtime() { // 被举报次数，每次被禁言后就清空一次
			return bereportedtime;
		}

		@Override
		public void setUserid(long _v_) { // 
			userid = _v_;
		}

		@Override
		public void setProfession(int _v_) { // 
			profession = _v_;
		}

		@Override
		public void setCreatetime(long _v_) { // 
			createtime = _v_;
		}

		@Override
		public void setServerid(int _v_) { // 
			serverid = _v_;
		}

		@Override
		public void setLastlogintime(long _v_) { // 
			lastlogintime = _v_;
		}

		@Override
		public void setTotalonlinetime(long _v_) { // 
			totalonlinetime = _v_;
		}

		@Override
		public void setLastlvluptotalonlinetime(long _v_) { // 
			lastlvluptotalonlinetime = _v_;
		}

		@Override
		public void setLastlogouttime(long _v_) { // 用来判断是否0点在线的问题
			lastlogouttime = _v_;
		}

		@Override
		public void setLogindaycount(int _v_) { // 总的登录天数
			logindaycount = _v_;
		}

		@Override
		public void setLastaddlogindaytime(long _v_) { // 
			lastaddlogindaytime = _v_;
		}

		@Override
		public void setContinuesloginday(int _v_) { // 连续登录天数
			continuesloginday = _v_;
		}

		@Override
		public void setTotaldayonlinetime(long _v_) { // 每天在线时长，每天登录的时候重置
			totaldayonlinetime = _v_;
		}

		@Override
		public void setLeftgifttimes(int _v_) { // 剩余连续登录奖励领取次数,每天登录的时候重置
			leftgifttimes = _v_;
		}

		@Override
		public void setMonthcardlefttimes(int _v_) { // 月卡剩余天数
			monthcardlefttimes = _v_;
		}

		@Override
		public void setBuymonthcardtimes(int _v_) { // 购买月卡次数
			buymonthcardtimes = _v_;
		}

		@Override
		public void setBuygrowplan(int _v_) { // 是否购买过成长计划
			buygrowplan = _v_;
		}

		@Override
		public void setGrowonetime(int _v_) { // 购买成长计划1的时间，用登录第几天表示
			growonetime = _v_;
		}

		@Override
		public void setBuygrowplantwo(int _v_) { // 购买成长计划2，苹果测试用
			buygrowplantwo = _v_;
		}

		@Override
		public void setGrowtwotime(int _v_) { // 购买成长计划2的时间，用登录第几天表示
			growtwotime = _v_;
		}

		@Override
		public void setBuygrowplanthree(int _v_) { // 购买成长计划3，苹果测试用
			buygrowplanthree = _v_;
		}

		@Override
		public void setGrowthreetime(int _v_) { // 购买成长计划3的时间，用登录第几天表示
			growthreetime = _v_;
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
		public void setChangenametime(int _v_) { // 
			changenametime = _v_;
		}

		@Override
		public void setLevel(int _v_) { // 
			level = _v_;
		}

		@Override
		public void setLastlvluptime(long _v_) { // 
			lastlvluptime = _v_;
		}

		@Override
		public void setViplevel(int _v_) { // 
			viplevel = _v_;
		}

		@Override
		public void setVipexp(long _v_) { // 
			vipexp = _v_;
		}

		@Override
		public void setGender(int _v_) { // cfg.role.GenderType
			gender = _v_;
		}

		@Override
		public void setTotalcostyuanbao(int _v_) { // 累计花费元宝,不算绑定的
			totalcostyuanbao = _v_;
		}

		@Override
		public void setLastaddtilitime(long _v_) { // 上次自动加体力的时间
			lastaddtilitime = _v_;
		}

		@Override
		public void setLastworldtalktime(long _v_) { // 上次世界频道发言时间
			lastworldtalktime = _v_;
		}

		@Override
		public void setSilentendtime(long _v_) { // 禁言到期时间
			silentendtime = _v_;
		}

		@Override
		public void setLeftreporttime(int _v_) { // 剩余举报次数，每天重置
			leftreporttime = _v_;
		}

		@Override
		public void setBereportedtime(int _v_) { // 被举报次数，每次被禁言后就清空一次
			bereportedtime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleInfo.Data)) return false;
			RoleInfo.Data _o_ = (RoleInfo.Data) _o1_;
			if (userid != _o_.userid) return false;
			if (profession != _o_.profession) return false;
			if (createtime != _o_.createtime) return false;
			if (serverid != _o_.serverid) return false;
			if (lastlogintime != _o_.lastlogintime) return false;
			if (totalonlinetime != _o_.totalonlinetime) return false;
			if (lastlvluptotalonlinetime != _o_.lastlvluptotalonlinetime) return false;
			if (lastlogouttime != _o_.lastlogouttime) return false;
			if (logindaycount != _o_.logindaycount) return false;
			if (lastaddlogindaytime != _o_.lastaddlogindaytime) return false;
			if (continuesloginday != _o_.continuesloginday) return false;
			if (totaldayonlinetime != _o_.totaldayonlinetime) return false;
			if (leftgifttimes != _o_.leftgifttimes) return false;
			if (monthcardlefttimes != _o_.monthcardlefttimes) return false;
			if (buymonthcardtimes != _o_.buymonthcardtimes) return false;
			if (buygrowplan != _o_.buygrowplan) return false;
			if (growonetime != _o_.growonetime) return false;
			if (buygrowplantwo != _o_.buygrowplantwo) return false;
			if (growtwotime != _o_.growtwotime) return false;
			if (buygrowplanthree != _o_.buygrowplanthree) return false;
			if (growthreetime != _o_.growthreetime) return false;
			if (!name.equals(_o_.name)) return false;
			if (changenametime != _o_.changenametime) return false;
			if (level != _o_.level) return false;
			if (lastlvluptime != _o_.lastlvluptime) return false;
			if (viplevel != _o_.viplevel) return false;
			if (vipexp != _o_.vipexp) return false;
			if (!currencys.equals(_o_.currencys)) return false;
			if (gender != _o_.gender) return false;
			if (totalcostyuanbao != _o_.totalcostyuanbao) return false;
			if (lastaddtilitime != _o_.lastaddtilitime) return false;
			if (lastworldtalktime != _o_.lastworldtalktime) return false;
			if (silentendtime != _o_.silentendtime) return false;
			if (leftreporttime != _o_.leftreporttime) return false;
			if (bereportedtime != _o_.bereportedtime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += userid;
			_h_ += profession;
			_h_ += createtime;
			_h_ += serverid;
			_h_ += lastlogintime;
			_h_ += totalonlinetime;
			_h_ += lastlvluptotalonlinetime;
			_h_ += lastlogouttime;
			_h_ += logindaycount;
			_h_ += lastaddlogindaytime;
			_h_ += continuesloginday;
			_h_ += totaldayonlinetime;
			_h_ += leftgifttimes;
			_h_ += monthcardlefttimes;
			_h_ += buymonthcardtimes;
			_h_ += buygrowplan;
			_h_ += growonetime;
			_h_ += buygrowplantwo;
			_h_ += growtwotime;
			_h_ += buygrowplanthree;
			_h_ += growthreetime;
			_h_ += name.hashCode();
			_h_ += changenametime;
			_h_ += level;
			_h_ += lastlvluptime;
			_h_ += viplevel;
			_h_ += vipexp;
			_h_ += currencys.hashCode();
			_h_ += gender;
			_h_ += totalcostyuanbao;
			_h_ += lastaddtilitime;
			_h_ += lastworldtalktime;
			_h_ += silentendtime;
			_h_ += leftreporttime;
			_h_ += bereportedtime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(userid);
			_sb_.append(",");
			_sb_.append(profession);
			_sb_.append(",");
			_sb_.append(createtime);
			_sb_.append(",");
			_sb_.append(serverid);
			_sb_.append(",");
			_sb_.append(lastlogintime);
			_sb_.append(",");
			_sb_.append(totalonlinetime);
			_sb_.append(",");
			_sb_.append(lastlvluptotalonlinetime);
			_sb_.append(",");
			_sb_.append(lastlogouttime);
			_sb_.append(",");
			_sb_.append(logindaycount);
			_sb_.append(",");
			_sb_.append(lastaddlogindaytime);
			_sb_.append(",");
			_sb_.append(continuesloginday);
			_sb_.append(",");
			_sb_.append(totaldayonlinetime);
			_sb_.append(",");
			_sb_.append(leftgifttimes);
			_sb_.append(",");
			_sb_.append(monthcardlefttimes);
			_sb_.append(",");
			_sb_.append(buymonthcardtimes);
			_sb_.append(",");
			_sb_.append(buygrowplan);
			_sb_.append(",");
			_sb_.append(growonetime);
			_sb_.append(",");
			_sb_.append(buygrowplantwo);
			_sb_.append(",");
			_sb_.append(growtwotime);
			_sb_.append(",");
			_sb_.append(buygrowplanthree);
			_sb_.append(",");
			_sb_.append(growthreetime);
			_sb_.append(",");
			_sb_.append("'").append(name).append("'");
			_sb_.append(",");
			_sb_.append(changenametime);
			_sb_.append(",");
			_sb_.append(level);
			_sb_.append(",");
			_sb_.append(lastlvluptime);
			_sb_.append(",");
			_sb_.append(viplevel);
			_sb_.append(",");
			_sb_.append(vipexp);
			_sb_.append(",");
			_sb_.append(currencys);
			_sb_.append(",");
			_sb_.append(gender);
			_sb_.append(",");
			_sb_.append(totalcostyuanbao);
			_sb_.append(",");
			_sb_.append(lastaddtilitime);
			_sb_.append(",");
			_sb_.append(lastworldtalktime);
			_sb_.append(",");
			_sb_.append(silentendtime);
			_sb_.append(",");
			_sb_.append(leftreporttime);
			_sb_.append(",");
			_sb_.append(bereportedtime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
