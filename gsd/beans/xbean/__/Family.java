
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Family extends xdb.XBean implements xbean.Family {
	private long familyid; // 家族id
	private String familyname; // 家族名字
	private int flevel; // 家族等级
	private long money; // 家族资金
	private int curlvlbuilddegree; // 当前等级家族建设度
	private int totalbuilddegree; // 总的家族建设度,排行榜使用
	private long totalbanggong; // 总的家族帮贡
	private String declaration; // 家族宣言
	private String publicinfo; // 家族公告
	private long publictime; // 公告时间
	private int malllevel; // 家族商店级别
	private java.util.LinkedList<xbean.FamilyLogReport> logs; // 家族日志信息
	private java.util.HashMap<Long, Long> requestinglist; // 申请成员列表,key为角色id，value为申请时间
	private xbean.FamilyActivity activity; // 家族神兽信息
	private xbean.FamilyGodAnimalAct beatanimalactivity; // 神兽挑战信息
	private xbean.FamilyWelfare welfare; // 家族福利相关的信息
	private long chiefid; // 组长角色id
	private java.util.HashMap<Integer, xbean.FamilyJobStaffList> jobinfo; // 家族职位信息，key为职位id，value为该职位人员信息允许一个职位多个人
	private java.util.HashMap<Long, xbean.FamilyMember> members; // 家族成员信息
	private long updatetime; // 信息更新时间
	private long createtime; // 家族创建的时间
	private long lastpartyopentime; // 家族聚会上次开启时间
	private long lastpartycalltime; // 上次召集教众时间
	private int issystemfam; // 是否是系统创建的家族，0否，1是
	private java.util.HashMap<Long, Long> invitelist; // 邀请列表
	private long lastresettime; // 

	@Override
	public void _reset_unsafe_() {
		familyid = 0L;
		familyname = "";
		flevel = 0;
		money = 0L;
		curlvlbuilddegree = 0;
		totalbuilddegree = 0;
		totalbanggong = 0L;
		declaration = "";
		publicinfo = "";
		publictime = 0L;
		malllevel = 1;
		logs.clear();
		requestinglist.clear();
		activity._reset_unsafe_();
		beatanimalactivity._reset_unsafe_();
		welfare._reset_unsafe_();
		chiefid = 0L;
		jobinfo.clear();
		members.clear();
		updatetime = 0L;
		createtime = 0L;
		lastpartyopentime = 0L;
		lastpartycalltime = 0L;
		issystemfam = 0;
		invitelist.clear();
		lastresettime = 0L;
	}

	Family(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		familyname = "";
		declaration = "";
		publicinfo = "";
		malllevel = 1;
		logs = new java.util.LinkedList<xbean.FamilyLogReport>();
		requestinglist = new java.util.HashMap<Long, Long>();
		activity = new FamilyActivity(0, this, "activity");
		beatanimalactivity = new FamilyGodAnimalAct(0, this, "beatanimalactivity");
		welfare = new FamilyWelfare(0, this, "welfare");
		jobinfo = new java.util.HashMap<Integer, xbean.FamilyJobStaffList>();
		members = new java.util.HashMap<Long, xbean.FamilyMember>();
		invitelist = new java.util.HashMap<Long, Long>();
	}

	public Family() {
		this(0, null, null);
	}

	public Family(Family _o_) {
		this(_o_, null, null);
	}

	Family(xbean.Family _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Family) assign((Family)_o1_);
		else if (_o1_ instanceof Family.Data) assign((Family.Data)_o1_);
		else if (_o1_ instanceof Family.Const) assign(((Family.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Family _o_) {
		_o_._xdb_verify_unsafe_();
		familyid = _o_.familyid;
		familyname = _o_.familyname;
		flevel = _o_.flevel;
		money = _o_.money;
		curlvlbuilddegree = _o_.curlvlbuilddegree;
		totalbuilddegree = _o_.totalbuilddegree;
		totalbanggong = _o_.totalbanggong;
		declaration = _o_.declaration;
		publicinfo = _o_.publicinfo;
		publictime = _o_.publictime;
		malllevel = _o_.malllevel;
		logs = new java.util.LinkedList<xbean.FamilyLogReport>();
		for (xbean.FamilyLogReport _v_ : _o_.logs)
			logs.add(new FamilyLogReport(_v_, this, "logs"));
		requestinglist = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestinglist.entrySet())
			requestinglist.put(_e_.getKey(), _e_.getValue());
		activity = new FamilyActivity(_o_.activity, this, "activity");
		beatanimalactivity = new FamilyGodAnimalAct(_o_.beatanimalactivity, this, "beatanimalactivity");
		welfare = new FamilyWelfare(_o_.welfare, this, "welfare");
		chiefid = _o_.chiefid;
		jobinfo = new java.util.HashMap<Integer, xbean.FamilyJobStaffList>();
		for (java.util.Map.Entry<Integer, xbean.FamilyJobStaffList> _e_ : _o_.jobinfo.entrySet())
			jobinfo.put(_e_.getKey(), new FamilyJobStaffList(_e_.getValue(), this, "jobinfo"));
		members = new java.util.HashMap<Long, xbean.FamilyMember>();
		for (java.util.Map.Entry<Long, xbean.FamilyMember> _e_ : _o_.members.entrySet())
			members.put(_e_.getKey(), new FamilyMember(_e_.getValue(), this, "members"));
		updatetime = _o_.updatetime;
		createtime = _o_.createtime;
		lastpartyopentime = _o_.lastpartyopentime;
		lastpartycalltime = _o_.lastpartycalltime;
		issystemfam = _o_.issystemfam;
		invitelist = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitelist.entrySet())
			invitelist.put(_e_.getKey(), _e_.getValue());
		lastresettime = _o_.lastresettime;
	}

	private void assign(Family.Data _o_) {
		familyid = _o_.familyid;
		familyname = _o_.familyname;
		flevel = _o_.flevel;
		money = _o_.money;
		curlvlbuilddegree = _o_.curlvlbuilddegree;
		totalbuilddegree = _o_.totalbuilddegree;
		totalbanggong = _o_.totalbanggong;
		declaration = _o_.declaration;
		publicinfo = _o_.publicinfo;
		publictime = _o_.publictime;
		malllevel = _o_.malllevel;
		logs = new java.util.LinkedList<xbean.FamilyLogReport>();
		for (xbean.FamilyLogReport _v_ : _o_.logs)
			logs.add(new FamilyLogReport(_v_, this, "logs"));
		requestinglist = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestinglist.entrySet())
			requestinglist.put(_e_.getKey(), _e_.getValue());
		activity = new FamilyActivity(_o_.activity, this, "activity");
		beatanimalactivity = new FamilyGodAnimalAct(_o_.beatanimalactivity, this, "beatanimalactivity");
		welfare = new FamilyWelfare(_o_.welfare, this, "welfare");
		chiefid = _o_.chiefid;
		jobinfo = new java.util.HashMap<Integer, xbean.FamilyJobStaffList>();
		for (java.util.Map.Entry<Integer, xbean.FamilyJobStaffList> _e_ : _o_.jobinfo.entrySet())
			jobinfo.put(_e_.getKey(), new FamilyJobStaffList(_e_.getValue(), this, "jobinfo"));
		members = new java.util.HashMap<Long, xbean.FamilyMember>();
		for (java.util.Map.Entry<Long, xbean.FamilyMember> _e_ : _o_.members.entrySet())
			members.put(_e_.getKey(), new FamilyMember(_e_.getValue(), this, "members"));
		updatetime = _o_.updatetime;
		createtime = _o_.createtime;
		lastpartyopentime = _o_.lastpartyopentime;
		lastpartycalltime = _o_.lastpartycalltime;
		issystemfam = _o_.issystemfam;
		invitelist = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitelist.entrySet())
			invitelist.put(_e_.getKey(), _e_.getValue());
		lastresettime = _o_.lastresettime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)26);
    _os_.marshal((short)(10240|  1));_os_.marshal(familyid);
    _os_.marshal((short)(18432|  2));_os_.marshal(familyname, xdb.Const.IO_CHARSET);
    _os_.marshal((short)( 8192|  3));_os_.marshal(flevel);
    _os_.marshal((short)(10240|  4));_os_.marshal(money);
    _os_.marshal((short)( 8192|  5));_os_.marshal(curlvlbuilddegree);
    _os_.marshal((short)( 8192| 21));_os_.marshal(totalbuilddegree);
    _os_.marshal((short)(10240| 22));_os_.marshal(totalbanggong);
    _os_.marshal((short)(18432|  6));_os_.marshal(declaration, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(18432|  7));_os_.marshal(publicinfo, xdb.Const.IO_CHARSET);
    _os_.marshal((short)(10240|  8));_os_.marshal(publictime);
    _os_.marshal((short)( 8192|  9));_os_.marshal(malllevel);
    _os_.marshal((short)(22528| 11));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(logs.size());
for (xbean.FamilyLogReport _v_ : logs) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 12));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(requestinglist.size());
for (java.util.Map.Entry<Long, Long> _e_ : requestinglist.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(26624| 13));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
activity.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(26624| 20));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
beatanimalactivity.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(26624| 14));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
welfare.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240| 15));_os_.marshal(chiefid);
    _os_.marshal((short)(24576| 16));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(jobinfo.size());
for (java.util.Map.Entry<Integer, xbean.FamilyJobStaffList> _e_ : jobinfo.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 17));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(members.size());
for (java.util.Map.Entry<Long, xbean.FamilyMember> _e_ : members.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240| 18));_os_.marshal(updatetime);
    _os_.marshal((short)(10240| 19));_os_.marshal(createtime);
    _os_.marshal((short)(10240| 23));_os_.marshal(lastpartyopentime);
    _os_.marshal((short)(10240| 24));_os_.marshal(lastpartycalltime);
    _os_.marshal((short)( 8192| 25));_os_.marshal(issystemfam);
    _os_.marshal((short)(24576| 26));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(invitelist.size());
for (java.util.Map.Entry<Long, Long> _e_ : invitelist.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240| 27));_os_.marshal(lastresettime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):familyid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):familyid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):familyid = _os_.unmarshal_int();
    				break;
    				case (18432|  2):familyname = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case ( 8192|  3):flevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):flevel = _os_.unmarshal_short();
    				break;
    				case (10240|  4):money = _os_.unmarshal_long();
    				break;
    				case ( 6144|  4):money = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):money = _os_.unmarshal_int();
    				break;
    				case ( 8192|  5):curlvlbuilddegree = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):curlvlbuilddegree = _os_.unmarshal_short();
    				break;
    				case ( 8192| 21):totalbuilddegree = _os_.unmarshal_int();
    				break;
    				case ( 6144| 21):totalbuilddegree = _os_.unmarshal_short();
    				break;
    				case (10240| 22):totalbanggong = _os_.unmarshal_long();
    				break;
    				case ( 6144| 22):totalbanggong = _os_.unmarshal_short();
    				break;
    				case ( 8192| 22):totalbanggong = _os_.unmarshal_int();
    				break;
    				case (18432|  6):declaration = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (18432|  7):publicinfo = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
    				break;
    				case (10240|  8):publictime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  8):publictime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  8):publictime = _os_.unmarshal_int();
    				break;
    				case ( 8192|  9):malllevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  9):malllevel = _os_.unmarshal_short();
    				break;
    				case (22528| 11):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.FamilyLogReport _v_ = new FamilyLogReport(0, this, "logs");
	_v_.unmarshal(_os_);
	logs.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (24576| 12):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		requestinglist = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		requestinglist.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (26624| 13):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
activity.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case (26624| 20):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
beatanimalactivity.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case (26624| 14):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
welfare.unmarshal(_os_);
_os_ = _temp_;}
    				break;
    				case (10240| 15):chiefid = _os_.unmarshal_long();
    				break;
    				case ( 6144| 15):chiefid = _os_.unmarshal_short();
    				break;
    				case ( 8192| 15):chiefid = _os_.unmarshal_int();
    				break;
    				case (24576| 16):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		jobinfo = new java.util.HashMap<Integer, xbean.FamilyJobStaffList>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.FamilyJobStaffList _v_ = new FamilyJobStaffList(0, this, "jobinfo");
		_v_.unmarshal(_os_);
		jobinfo.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 17):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		members = new java.util.HashMap<Long, xbean.FamilyMember>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.FamilyMember _v_ = new FamilyMember(0, this, "members");
		_v_.unmarshal(_os_);
		members.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (10240| 18):updatetime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 18):updatetime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 18):updatetime = _os_.unmarshal_int();
    				break;
    				case (10240| 19):createtime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 19):createtime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 19):createtime = _os_.unmarshal_int();
    				break;
    				case (10240| 23):lastpartyopentime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 23):lastpartyopentime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 23):lastpartyopentime = _os_.unmarshal_int();
    				break;
    				case (10240| 24):lastpartycalltime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 24):lastpartycalltime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 24):lastpartycalltime = _os_.unmarshal_int();
    				break;
    				case ( 8192| 25):issystemfam = _os_.unmarshal_int();
    				break;
    				case ( 6144| 25):issystemfam = _os_.unmarshal_short();
    				break;
    				case (24576| 26):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		invitelist = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		invitelist.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (10240| 27):lastresettime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 27):lastresettime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 27):lastresettime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Family copy() {
		_xdb_verify_unsafe_();
		return new Family(this);
	}

	@Override
	public xbean.Family toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Family toBean() {
		_xdb_verify_unsafe_();
		return new Family(this); // same as copy()
	}

	@Override
	public xbean.Family toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Family toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getFamilyid() { // 家族id
		_xdb_verify_unsafe_();
		return familyid;
	}

	@Override
	public String getFamilyname() { // 家族名字
		_xdb_verify_unsafe_();
		return familyname;
	}

	@Override
	public com.goldhuman.Common.Octets getFamilynameOctets() { // 家族名字
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getFamilyname(), xdb.Const.IO_CHARSET);
	}

	@Override
	public int getFlevel() { // 家族等级
		_xdb_verify_unsafe_();
		return flevel;
	}

	@Override
	public long getMoney() { // 家族资金
		_xdb_verify_unsafe_();
		return money;
	}

	@Override
	public int getCurlvlbuilddegree() { // 当前等级家族建设度
		_xdb_verify_unsafe_();
		return curlvlbuilddegree;
	}

	@Override
	public int getTotalbuilddegree() { // 总的家族建设度,排行榜使用
		_xdb_verify_unsafe_();
		return totalbuilddegree;
	}

	@Override
	public long getTotalbanggong() { // 总的家族帮贡
		_xdb_verify_unsafe_();
		return totalbanggong;
	}

	@Override
	public String getDeclaration() { // 家族宣言
		_xdb_verify_unsafe_();
		return declaration;
	}

	@Override
	public com.goldhuman.Common.Octets getDeclarationOctets() { // 家族宣言
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getDeclaration(), xdb.Const.IO_CHARSET);
	}

	@Override
	public String getPublicinfo() { // 家族公告
		_xdb_verify_unsafe_();
		return publicinfo;
	}

	@Override
	public com.goldhuman.Common.Octets getPublicinfoOctets() { // 家族公告
		_xdb_verify_unsafe_();
		return com.goldhuman.Common.Octets.wrap(getPublicinfo(), xdb.Const.IO_CHARSET);
	}

	@Override
	public long getPublictime() { // 公告时间
		_xdb_verify_unsafe_();
		return publictime;
	}

	@Override
	public int getMalllevel() { // 家族商店级别
		_xdb_verify_unsafe_();
		return malllevel;
	}

	@Override
	public java.util.List<xbean.FamilyLogReport> getLogs() { // 家族日志信息
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "logs"), logs);
	}

	public java.util.List<xbean.FamilyLogReport> getLogsAsData() { // 家族日志信息
		_xdb_verify_unsafe_();
		java.util.List<xbean.FamilyLogReport> logs;
		Family _o_ = this;
		logs = new java.util.LinkedList<xbean.FamilyLogReport>();
		for (xbean.FamilyLogReport _v_ : _o_.logs)
			logs.add(new FamilyLogReport.Data(_v_));
		return logs;
	}

	@Override
	public java.util.Map<Long, Long> getRequestinglist() { // 申请成员列表,key为角色id，value为申请时间
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "requestinglist"), requestinglist);
	}

	@Override
	public java.util.Map<Long, Long> getRequestinglistAsData() { // 申请成员列表,key为角色id，value为申请时间
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> requestinglist;
		Family _o_ = this;
		requestinglist = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestinglist.entrySet())
			requestinglist.put(_e_.getKey(), _e_.getValue());
		return requestinglist;
	}

	@Override
	public xbean.FamilyActivity getActivity() { // 家族神兽信息
		_xdb_verify_unsafe_();
		return activity;
	}

	@Override
	public xbean.FamilyGodAnimalAct getBeatanimalactivity() { // 神兽挑战信息
		_xdb_verify_unsafe_();
		return beatanimalactivity;
	}

	@Override
	public xbean.FamilyWelfare getWelfare() { // 家族福利相关的信息
		_xdb_verify_unsafe_();
		return welfare;
	}

	@Override
	public long getChiefid() { // 组长角色id
		_xdb_verify_unsafe_();
		return chiefid;
	}

	@Override
	public java.util.Map<Integer, xbean.FamilyJobStaffList> getJobinfo() { // 家族职位信息，key为职位id，value为该职位人员信息允许一个职位多个人
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "jobinfo"), jobinfo);
	}

	@Override
	public java.util.Map<Integer, xbean.FamilyJobStaffList> getJobinfoAsData() { // 家族职位信息，key为职位id，value为该职位人员信息允许一个职位多个人
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.FamilyJobStaffList> jobinfo;
		Family _o_ = this;
		jobinfo = new java.util.HashMap<Integer, xbean.FamilyJobStaffList>();
		for (java.util.Map.Entry<Integer, xbean.FamilyJobStaffList> _e_ : _o_.jobinfo.entrySet())
			jobinfo.put(_e_.getKey(), new FamilyJobStaffList.Data(_e_.getValue()));
		return jobinfo;
	}

	@Override
	public java.util.Map<Long, xbean.FamilyMember> getMembers() { // 家族成员信息
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "members"), members);
	}

	@Override
	public java.util.Map<Long, xbean.FamilyMember> getMembersAsData() { // 家族成员信息
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.FamilyMember> members;
		Family _o_ = this;
		members = new java.util.HashMap<Long, xbean.FamilyMember>();
		for (java.util.Map.Entry<Long, xbean.FamilyMember> _e_ : _o_.members.entrySet())
			members.put(_e_.getKey(), new FamilyMember.Data(_e_.getValue()));
		return members;
	}

	@Override
	public long getUpdatetime() { // 信息更新时间
		_xdb_verify_unsafe_();
		return updatetime;
	}

	@Override
	public long getCreatetime() { // 家族创建的时间
		_xdb_verify_unsafe_();
		return createtime;
	}

	@Override
	public long getLastpartyopentime() { // 家族聚会上次开启时间
		_xdb_verify_unsafe_();
		return lastpartyopentime;
	}

	@Override
	public long getLastpartycalltime() { // 上次召集教众时间
		_xdb_verify_unsafe_();
		return lastpartycalltime;
	}

	@Override
	public int getIssystemfam() { // 是否是系统创建的家族，0否，1是
		_xdb_verify_unsafe_();
		return issystemfam;
	}

	@Override
	public java.util.Map<Long, Long> getInvitelist() { // 邀请列表
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "invitelist"), invitelist);
	}

	@Override
	public java.util.Map<Long, Long> getInvitelistAsData() { // 邀请列表
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> invitelist;
		Family _o_ = this;
		invitelist = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitelist.entrySet())
			invitelist.put(_e_.getKey(), _e_.getValue());
		return invitelist;
	}

	@Override
	public long getLastresettime() { // 
		_xdb_verify_unsafe_();
		return lastresettime;
	}

	@Override
	public void setFamilyid(long _v_) { // 家族id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "familyid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, familyid) {
					public void rollback() { familyid = _xdb_saved; }
				};}});
		familyid = _v_;
	}

	@Override
	public void setFamilyname(String _v_) { // 家族名字
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "familyname") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, familyname) {
					public void rollback() { familyname = _xdb_saved; }
				};}});
		familyname = _v_;
	}

	@Override
	public void setFamilynameOctets(com.goldhuman.Common.Octets _v_) { // 家族名字
		_xdb_verify_unsafe_();
		this.setFamilyname(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setFlevel(int _v_) { // 家族等级
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "flevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, flevel) {
					public void rollback() { flevel = _xdb_saved; }
				};}});
		flevel = _v_;
	}

	@Override
	public void setMoney(long _v_) { // 家族资金
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "money") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, money) {
					public void rollback() { money = _xdb_saved; }
				};}});
		money = _v_;
	}

	@Override
	public void setCurlvlbuilddegree(int _v_) { // 当前等级家族建设度
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "curlvlbuilddegree") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, curlvlbuilddegree) {
					public void rollback() { curlvlbuilddegree = _xdb_saved; }
				};}});
		curlvlbuilddegree = _v_;
	}

	@Override
	public void setTotalbuilddegree(int _v_) { // 总的家族建设度,排行榜使用
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalbuilddegree") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, totalbuilddegree) {
					public void rollback() { totalbuilddegree = _xdb_saved; }
				};}});
		totalbuilddegree = _v_;
	}

	@Override
	public void setTotalbanggong(long _v_) { // 总的家族帮贡
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalbanggong") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, totalbanggong) {
					public void rollback() { totalbanggong = _xdb_saved; }
				};}});
		totalbanggong = _v_;
	}

	@Override
	public void setDeclaration(String _v_) { // 家族宣言
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "declaration") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, declaration) {
					public void rollback() { declaration = _xdb_saved; }
				};}});
		declaration = _v_;
	}

	@Override
	public void setDeclarationOctets(com.goldhuman.Common.Octets _v_) { // 家族宣言
		_xdb_verify_unsafe_();
		this.setDeclaration(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setPublicinfo(String _v_) { // 家族公告
		_xdb_verify_unsafe_();
		if (null == _v_)
			throw new NullPointerException();
		xdb.Logs.logIf(new xdb.LogKey(this, "publicinfo") {
			protected xdb.Log create() {
				return new xdb.logs.LogString(this, publicinfo) {
					public void rollback() { publicinfo = _xdb_saved; }
				};}});
		publicinfo = _v_;
	}

	@Override
	public void setPublicinfoOctets(com.goldhuman.Common.Octets _v_) { // 家族公告
		_xdb_verify_unsafe_();
		this.setPublicinfo(_v_.getString(xdb.Const.IO_CHARSET));
	}

	@Override
	public void setPublictime(long _v_) { // 公告时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "publictime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, publictime) {
					public void rollback() { publictime = _xdb_saved; }
				};}});
		publictime = _v_;
	}

	@Override
	public void setMalllevel(int _v_) { // 家族商店级别
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "malllevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, malllevel) {
					public void rollback() { malllevel = _xdb_saved; }
				};}});
		malllevel = _v_;
	}

	@Override
	public void setChiefid(long _v_) { // 组长角色id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "chiefid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, chiefid) {
					public void rollback() { chiefid = _xdb_saved; }
				};}});
		chiefid = _v_;
	}

	@Override
	public void setUpdatetime(long _v_) { // 信息更新时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "updatetime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, updatetime) {
					public void rollback() { updatetime = _xdb_saved; }
				};}});
		updatetime = _v_;
	}

	@Override
	public void setCreatetime(long _v_) { // 家族创建的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "createtime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, createtime) {
					public void rollback() { createtime = _xdb_saved; }
				};}});
		createtime = _v_;
	}

	@Override
	public void setLastpartyopentime(long _v_) { // 家族聚会上次开启时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastpartyopentime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastpartyopentime) {
					public void rollback() { lastpartyopentime = _xdb_saved; }
				};}});
		lastpartyopentime = _v_;
	}

	@Override
	public void setLastpartycalltime(long _v_) { // 上次召集教众时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastpartycalltime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastpartycalltime) {
					public void rollback() { lastpartycalltime = _xdb_saved; }
				};}});
		lastpartycalltime = _v_;
	}

	@Override
	public void setIssystemfam(int _v_) { // 是否是系统创建的家族，0否，1是
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "issystemfam") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, issystemfam) {
					public void rollback() { issystemfam = _xdb_saved; }
				};}});
		issystemfam = _v_;
	}

	@Override
	public void setLastresettime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastresettime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastresettime) {
					public void rollback() { lastresettime = _xdb_saved; }
				};}});
		lastresettime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Family _o_ = null;
		if ( _o1_ instanceof Family ) _o_ = (Family)_o1_;
		else if ( _o1_ instanceof Family.Const ) _o_ = ((Family.Const)_o1_).nThis();
		else return false;
		if (familyid != _o_.familyid) return false;
		if (!familyname.equals(_o_.familyname)) return false;
		if (flevel != _o_.flevel) return false;
		if (money != _o_.money) return false;
		if (curlvlbuilddegree != _o_.curlvlbuilddegree) return false;
		if (totalbuilddegree != _o_.totalbuilddegree) return false;
		if (totalbanggong != _o_.totalbanggong) return false;
		if (!declaration.equals(_o_.declaration)) return false;
		if (!publicinfo.equals(_o_.publicinfo)) return false;
		if (publictime != _o_.publictime) return false;
		if (malllevel != _o_.malllevel) return false;
		if (!logs.equals(_o_.logs)) return false;
		if (!requestinglist.equals(_o_.requestinglist)) return false;
		if (!activity.equals(_o_.activity)) return false;
		if (!beatanimalactivity.equals(_o_.beatanimalactivity)) return false;
		if (!welfare.equals(_o_.welfare)) return false;
		if (chiefid != _o_.chiefid) return false;
		if (!jobinfo.equals(_o_.jobinfo)) return false;
		if (!members.equals(_o_.members)) return false;
		if (updatetime != _o_.updatetime) return false;
		if (createtime != _o_.createtime) return false;
		if (lastpartyopentime != _o_.lastpartyopentime) return false;
		if (lastpartycalltime != _o_.lastpartycalltime) return false;
		if (issystemfam != _o_.issystemfam) return false;
		if (!invitelist.equals(_o_.invitelist)) return false;
		if (lastresettime != _o_.lastresettime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += familyid;
		_h_ += familyname.hashCode();
		_h_ += flevel;
		_h_ += money;
		_h_ += curlvlbuilddegree;
		_h_ += totalbuilddegree;
		_h_ += totalbanggong;
		_h_ += declaration.hashCode();
		_h_ += publicinfo.hashCode();
		_h_ += publictime;
		_h_ += malllevel;
		_h_ += logs.hashCode();
		_h_ += requestinglist.hashCode();
		_h_ += activity.hashCode();
		_h_ += beatanimalactivity.hashCode();
		_h_ += welfare.hashCode();
		_h_ += chiefid;
		_h_ += jobinfo.hashCode();
		_h_ += members.hashCode();
		_h_ += updatetime;
		_h_ += createtime;
		_h_ += lastpartyopentime;
		_h_ += lastpartycalltime;
		_h_ += issystemfam;
		_h_ += invitelist.hashCode();
		_h_ += lastresettime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(familyid);
		_sb_.append(",");
		_sb_.append("'").append(familyname).append("'");
		_sb_.append(",");
		_sb_.append(flevel);
		_sb_.append(",");
		_sb_.append(money);
		_sb_.append(",");
		_sb_.append(curlvlbuilddegree);
		_sb_.append(",");
		_sb_.append(totalbuilddegree);
		_sb_.append(",");
		_sb_.append(totalbanggong);
		_sb_.append(",");
		_sb_.append("'").append(declaration).append("'");
		_sb_.append(",");
		_sb_.append("'").append(publicinfo).append("'");
		_sb_.append(",");
		_sb_.append(publictime);
		_sb_.append(",");
		_sb_.append(malllevel);
		_sb_.append(",");
		_sb_.append(logs);
		_sb_.append(",");
		_sb_.append(requestinglist);
		_sb_.append(",");
		_sb_.append(activity);
		_sb_.append(",");
		_sb_.append(beatanimalactivity);
		_sb_.append(",");
		_sb_.append(welfare);
		_sb_.append(",");
		_sb_.append(chiefid);
		_sb_.append(",");
		_sb_.append(jobinfo);
		_sb_.append(",");
		_sb_.append(members);
		_sb_.append(",");
		_sb_.append(updatetime);
		_sb_.append(",");
		_sb_.append(createtime);
		_sb_.append(",");
		_sb_.append(lastpartyopentime);
		_sb_.append(",");
		_sb_.append(lastpartycalltime);
		_sb_.append(",");
		_sb_.append(issystemfam);
		_sb_.append(",");
		_sb_.append(invitelist);
		_sb_.append(",");
		_sb_.append(lastresettime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("familyid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("familyname"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("flevel"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("money"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("curlvlbuilddegree"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalbuilddegree"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalbanggong"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("declaration"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("publicinfo"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("publictime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("malllevel"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("logs"));
		lb.add(new xdb.logs.ListenableMap().setVarName("requestinglist"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("activity"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("beatanimalactivity"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("welfare"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("chiefid"));
		lb.add(new xdb.logs.ListenableMap().setVarName("jobinfo"));
		lb.add(new xdb.logs.ListenableMap().setVarName("members"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("updatetime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("createtime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastpartyopentime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastpartycalltime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("issystemfam"));
		lb.add(new xdb.logs.ListenableMap().setVarName("invitelist"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastresettime"));
		return lb;
	}

	private class Const implements xbean.Family {
		Family nThis() {
			return Family.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Family copy() {
			return Family.this.copy();
		}

		@Override
		public xbean.Family toData() {
			return Family.this.toData();
		}

		public xbean.Family toBean() {
			return Family.this.toBean();
		}

		@Override
		public xbean.Family toDataIf() {
			return Family.this.toDataIf();
		}

		public xbean.Family toBeanIf() {
			return Family.this.toBeanIf();
		}

		@Override
		public long getFamilyid() { // 家族id
			_xdb_verify_unsafe_();
			return familyid;
		}

		@Override
		public String getFamilyname() { // 家族名字
			_xdb_verify_unsafe_();
			return familyname;
		}

		@Override
		public com.goldhuman.Common.Octets getFamilynameOctets() { // 家族名字
			_xdb_verify_unsafe_();
			return Family.this.getFamilynameOctets();
		}

		@Override
		public int getFlevel() { // 家族等级
			_xdb_verify_unsafe_();
			return flevel;
		}

		@Override
		public long getMoney() { // 家族资金
			_xdb_verify_unsafe_();
			return money;
		}

		@Override
		public int getCurlvlbuilddegree() { // 当前等级家族建设度
			_xdb_verify_unsafe_();
			return curlvlbuilddegree;
		}

		@Override
		public int getTotalbuilddegree() { // 总的家族建设度,排行榜使用
			_xdb_verify_unsafe_();
			return totalbuilddegree;
		}

		@Override
		public long getTotalbanggong() { // 总的家族帮贡
			_xdb_verify_unsafe_();
			return totalbanggong;
		}

		@Override
		public String getDeclaration() { // 家族宣言
			_xdb_verify_unsafe_();
			return declaration;
		}

		@Override
		public com.goldhuman.Common.Octets getDeclarationOctets() { // 家族宣言
			_xdb_verify_unsafe_();
			return Family.this.getDeclarationOctets();
		}

		@Override
		public String getPublicinfo() { // 家族公告
			_xdb_verify_unsafe_();
			return publicinfo;
		}

		@Override
		public com.goldhuman.Common.Octets getPublicinfoOctets() { // 家族公告
			_xdb_verify_unsafe_();
			return Family.this.getPublicinfoOctets();
		}

		@Override
		public long getPublictime() { // 公告时间
			_xdb_verify_unsafe_();
			return publictime;
		}

		@Override
		public int getMalllevel() { // 家族商店级别
			_xdb_verify_unsafe_();
			return malllevel;
		}

		@Override
		public java.util.List<xbean.FamilyLogReport> getLogs() { // 家族日志信息
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(logs);
		}

		public java.util.List<xbean.FamilyLogReport> getLogsAsData() { // 家族日志信息
			_xdb_verify_unsafe_();
			java.util.List<xbean.FamilyLogReport> logs;
			Family _o_ = Family.this;
		logs = new java.util.LinkedList<xbean.FamilyLogReport>();
		for (xbean.FamilyLogReport _v_ : _o_.logs)
			logs.add(new FamilyLogReport.Data(_v_));
			return logs;
		}

		@Override
		public java.util.Map<Long, Long> getRequestinglist() { // 申请成员列表,key为角色id，value为申请时间
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(requestinglist);
		}

		@Override
		public java.util.Map<Long, Long> getRequestinglistAsData() { // 申请成员列表,key为角色id，value为申请时间
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> requestinglist;
			Family _o_ = Family.this;
			requestinglist = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestinglist.entrySet())
				requestinglist.put(_e_.getKey(), _e_.getValue());
			return requestinglist;
		}

		@Override
		public xbean.FamilyActivity getActivity() { // 家族神兽信息
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(activity);
		}

		@Override
		public xbean.FamilyGodAnimalAct getBeatanimalactivity() { // 神兽挑战信息
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(beatanimalactivity);
		}

		@Override
		public xbean.FamilyWelfare getWelfare() { // 家族福利相关的信息
			_xdb_verify_unsafe_();
			return xdb.Consts.toConst(welfare);
		}

		@Override
		public long getChiefid() { // 组长角色id
			_xdb_verify_unsafe_();
			return chiefid;
		}

		@Override
		public java.util.Map<Integer, xbean.FamilyJobStaffList> getJobinfo() { // 家族职位信息，key为职位id，value为该职位人员信息允许一个职位多个人
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(jobinfo);
		}

		@Override
		public java.util.Map<Integer, xbean.FamilyJobStaffList> getJobinfoAsData() { // 家族职位信息，key为职位id，value为该职位人员信息允许一个职位多个人
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.FamilyJobStaffList> jobinfo;
			Family _o_ = Family.this;
			jobinfo = new java.util.HashMap<Integer, xbean.FamilyJobStaffList>();
			for (java.util.Map.Entry<Integer, xbean.FamilyJobStaffList> _e_ : _o_.jobinfo.entrySet())
				jobinfo.put(_e_.getKey(), new FamilyJobStaffList.Data(_e_.getValue()));
			return jobinfo;
		}

		@Override
		public java.util.Map<Long, xbean.FamilyMember> getMembers() { // 家族成员信息
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(members);
		}

		@Override
		public java.util.Map<Long, xbean.FamilyMember> getMembersAsData() { // 家族成员信息
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.FamilyMember> members;
			Family _o_ = Family.this;
			members = new java.util.HashMap<Long, xbean.FamilyMember>();
			for (java.util.Map.Entry<Long, xbean.FamilyMember> _e_ : _o_.members.entrySet())
				members.put(_e_.getKey(), new FamilyMember.Data(_e_.getValue()));
			return members;
		}

		@Override
		public long getUpdatetime() { // 信息更新时间
			_xdb_verify_unsafe_();
			return updatetime;
		}

		@Override
		public long getCreatetime() { // 家族创建的时间
			_xdb_verify_unsafe_();
			return createtime;
		}

		@Override
		public long getLastpartyopentime() { // 家族聚会上次开启时间
			_xdb_verify_unsafe_();
			return lastpartyopentime;
		}

		@Override
		public long getLastpartycalltime() { // 上次召集教众时间
			_xdb_verify_unsafe_();
			return lastpartycalltime;
		}

		@Override
		public int getIssystemfam() { // 是否是系统创建的家族，0否，1是
			_xdb_verify_unsafe_();
			return issystemfam;
		}

		@Override
		public java.util.Map<Long, Long> getInvitelist() { // 邀请列表
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(invitelist);
		}

		@Override
		public java.util.Map<Long, Long> getInvitelistAsData() { // 邀请列表
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> invitelist;
			Family _o_ = Family.this;
			invitelist = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitelist.entrySet())
				invitelist.put(_e_.getKey(), _e_.getValue());
			return invitelist;
		}

		@Override
		public long getLastresettime() { // 
			_xdb_verify_unsafe_();
			return lastresettime;
		}

		@Override
		public void setFamilyid(long _v_) { // 家族id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFamilyname(String _v_) { // 家族名字
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFamilynameOctets(com.goldhuman.Common.Octets _v_) { // 家族名字
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFlevel(int _v_) { // 家族等级
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMoney(long _v_) { // 家族资金
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCurlvlbuilddegree(int _v_) { // 当前等级家族建设度
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalbuilddegree(int _v_) { // 总的家族建设度,排行榜使用
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalbanggong(long _v_) { // 总的家族帮贡
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDeclaration(String _v_) { // 家族宣言
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDeclarationOctets(com.goldhuman.Common.Octets _v_) { // 家族宣言
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPublicinfo(String _v_) { // 家族公告
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPublicinfoOctets(com.goldhuman.Common.Octets _v_) { // 家族公告
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPublictime(long _v_) { // 公告时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMalllevel(int _v_) { // 家族商店级别
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setChiefid(long _v_) { // 组长角色id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setUpdatetime(long _v_) { // 信息更新时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCreatetime(long _v_) { // 家族创建的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastpartyopentime(long _v_) { // 家族聚会上次开启时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastpartycalltime(long _v_) { // 上次召集教众时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIssystemfam(int _v_) { // 是否是系统创建的家族，0否，1是
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastresettime(long _v_) { // 
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
			return Family.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Family.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Family.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Family.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Family.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Family.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Family.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Family.this.hashCode();
		}

		@Override
		public String toString() {
			return Family.this.toString();
		}

	}

	public static final class Data implements xbean.Family {
		private long familyid; // 家族id
		private String familyname; // 家族名字
		private int flevel; // 家族等级
		private long money; // 家族资金
		private int curlvlbuilddegree; // 当前等级家族建设度
		private int totalbuilddegree; // 总的家族建设度,排行榜使用
		private long totalbanggong; // 总的家族帮贡
		private String declaration; // 家族宣言
		private String publicinfo; // 家族公告
		private long publictime; // 公告时间
		private int malllevel; // 家族商店级别
		private java.util.LinkedList<xbean.FamilyLogReport> logs; // 家族日志信息
		private java.util.HashMap<Long, Long> requestinglist; // 申请成员列表,key为角色id，value为申请时间
		private xbean.FamilyActivity activity; // 家族神兽信息
		private xbean.FamilyGodAnimalAct beatanimalactivity; // 神兽挑战信息
		private xbean.FamilyWelfare welfare; // 家族福利相关的信息
		private long chiefid; // 组长角色id
		private java.util.HashMap<Integer, xbean.FamilyJobStaffList> jobinfo; // 家族职位信息，key为职位id，value为该职位人员信息允许一个职位多个人
		private java.util.HashMap<Long, xbean.FamilyMember> members; // 家族成员信息
		private long updatetime; // 信息更新时间
		private long createtime; // 家族创建的时间
		private long lastpartyopentime; // 家族聚会上次开启时间
		private long lastpartycalltime; // 上次召集教众时间
		private int issystemfam; // 是否是系统创建的家族，0否，1是
		private java.util.HashMap<Long, Long> invitelist; // 邀请列表
		private long lastresettime; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			familyname = "";
			declaration = "";
			publicinfo = "";
			malllevel = 1;
			logs = new java.util.LinkedList<xbean.FamilyLogReport>();
			requestinglist = new java.util.HashMap<Long, Long>();
			activity = new FamilyActivity.Data();
			beatanimalactivity = new FamilyGodAnimalAct.Data();
			welfare = new FamilyWelfare.Data();
			jobinfo = new java.util.HashMap<Integer, xbean.FamilyJobStaffList>();
			members = new java.util.HashMap<Long, xbean.FamilyMember>();
			invitelist = new java.util.HashMap<Long, Long>();
		}

		Data(xbean.Family _o1_) {
			if (_o1_ instanceof Family) assign((Family)_o1_);
			else if (_o1_ instanceof Family.Data) assign((Family.Data)_o1_);
			else if (_o1_ instanceof Family.Const) assign(((Family.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Family _o_) {
			familyid = _o_.familyid;
			familyname = _o_.familyname;
			flevel = _o_.flevel;
			money = _o_.money;
			curlvlbuilddegree = _o_.curlvlbuilddegree;
			totalbuilddegree = _o_.totalbuilddegree;
			totalbanggong = _o_.totalbanggong;
			declaration = _o_.declaration;
			publicinfo = _o_.publicinfo;
			publictime = _o_.publictime;
			malllevel = _o_.malllevel;
			logs = new java.util.LinkedList<xbean.FamilyLogReport>();
			for (xbean.FamilyLogReport _v_ : _o_.logs)
				logs.add(new FamilyLogReport.Data(_v_));
			requestinglist = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestinglist.entrySet())
				requestinglist.put(_e_.getKey(), _e_.getValue());
			activity = new FamilyActivity.Data(_o_.activity);
			beatanimalactivity = new FamilyGodAnimalAct.Data(_o_.beatanimalactivity);
			welfare = new FamilyWelfare.Data(_o_.welfare);
			chiefid = _o_.chiefid;
			jobinfo = new java.util.HashMap<Integer, xbean.FamilyJobStaffList>();
			for (java.util.Map.Entry<Integer, xbean.FamilyJobStaffList> _e_ : _o_.jobinfo.entrySet())
				jobinfo.put(_e_.getKey(), new FamilyJobStaffList.Data(_e_.getValue()));
			members = new java.util.HashMap<Long, xbean.FamilyMember>();
			for (java.util.Map.Entry<Long, xbean.FamilyMember> _e_ : _o_.members.entrySet())
				members.put(_e_.getKey(), new FamilyMember.Data(_e_.getValue()));
			updatetime = _o_.updatetime;
			createtime = _o_.createtime;
			lastpartyopentime = _o_.lastpartyopentime;
			lastpartycalltime = _o_.lastpartycalltime;
			issystemfam = _o_.issystemfam;
			invitelist = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitelist.entrySet())
				invitelist.put(_e_.getKey(), _e_.getValue());
			lastresettime = _o_.lastresettime;
		}

		private void assign(Family.Data _o_) {
			familyid = _o_.familyid;
			familyname = _o_.familyname;
			flevel = _o_.flevel;
			money = _o_.money;
			curlvlbuilddegree = _o_.curlvlbuilddegree;
			totalbuilddegree = _o_.totalbuilddegree;
			totalbanggong = _o_.totalbanggong;
			declaration = _o_.declaration;
			publicinfo = _o_.publicinfo;
			publictime = _o_.publictime;
			malllevel = _o_.malllevel;
			logs = new java.util.LinkedList<xbean.FamilyLogReport>();
			for (xbean.FamilyLogReport _v_ : _o_.logs)
				logs.add(new FamilyLogReport.Data(_v_));
			requestinglist = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requestinglist.entrySet())
				requestinglist.put(_e_.getKey(), _e_.getValue());
			activity = new FamilyActivity.Data(_o_.activity);
			beatanimalactivity = new FamilyGodAnimalAct.Data(_o_.beatanimalactivity);
			welfare = new FamilyWelfare.Data(_o_.welfare);
			chiefid = _o_.chiefid;
			jobinfo = new java.util.HashMap<Integer, xbean.FamilyJobStaffList>();
			for (java.util.Map.Entry<Integer, xbean.FamilyJobStaffList> _e_ : _o_.jobinfo.entrySet())
				jobinfo.put(_e_.getKey(), new FamilyJobStaffList.Data(_e_.getValue()));
			members = new java.util.HashMap<Long, xbean.FamilyMember>();
			for (java.util.Map.Entry<Long, xbean.FamilyMember> _e_ : _o_.members.entrySet())
				members.put(_e_.getKey(), new FamilyMember.Data(_e_.getValue()));
			updatetime = _o_.updatetime;
			createtime = _o_.createtime;
			lastpartyopentime = _o_.lastpartyopentime;
			lastpartycalltime = _o_.lastpartycalltime;
			issystemfam = _o_.issystemfam;
			invitelist = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.invitelist.entrySet())
				invitelist.put(_e_.getKey(), _e_.getValue());
			lastresettime = _o_.lastresettime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)26);
	_os_.marshal((short)(10240|  1));_os_.marshal(familyid);
	_os_.marshal((short)(18432|  2));_os_.marshal(familyname, xdb.Const.IO_CHARSET);
	_os_.marshal((short)( 8192|  3));_os_.marshal(flevel);
	_os_.marshal((short)(10240|  4));_os_.marshal(money);
	_os_.marshal((short)( 8192|  5));_os_.marshal(curlvlbuilddegree);
	_os_.marshal((short)( 8192| 21));_os_.marshal(totalbuilddegree);
	_os_.marshal((short)(10240| 22));_os_.marshal(totalbanggong);
	_os_.marshal((short)(18432|  6));_os_.marshal(declaration, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(18432|  7));_os_.marshal(publicinfo, xdb.Const.IO_CHARSET);
	_os_.marshal((short)(10240|  8));_os_.marshal(publictime);
	_os_.marshal((short)( 8192|  9));_os_.marshal(malllevel);
	_os_.marshal((short)(22528| 11));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(logs.size());
for (xbean.FamilyLogReport _v_ : logs) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 12));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(requestinglist.size());
for (java.util.Map.Entry<Long, Long> _e_ : requestinglist.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(26624| 13));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
activity.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(26624| 20));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
beatanimalactivity.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(26624| 14));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
welfare.marshal(_os_);
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240| 15));_os_.marshal(chiefid);
	_os_.marshal((short)(24576| 16));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(jobinfo.size());
for (java.util.Map.Entry<Integer, xbean.FamilyJobStaffList> _e_ : jobinfo.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 17));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(members.size());
for (java.util.Map.Entry<Long, xbean.FamilyMember> _e_ : members.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240| 18));_os_.marshal(updatetime);
	_os_.marshal((short)(10240| 19));_os_.marshal(createtime);
	_os_.marshal((short)(10240| 23));_os_.marshal(lastpartyopentime);
	_os_.marshal((short)(10240| 24));_os_.marshal(lastpartycalltime);
	_os_.marshal((short)( 8192| 25));_os_.marshal(issystemfam);
	_os_.marshal((short)(24576| 26));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(invitelist.size());
for (java.util.Map.Entry<Long, Long> _e_ : invitelist.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240| 27));_os_.marshal(lastresettime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):familyid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):familyid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):familyid = _os_.unmarshal_int();
					break;
					case (18432|  2):familyname = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case ( 8192|  3):flevel = _os_.unmarshal_int();
					break;
					case ( 6144|  3):flevel = _os_.unmarshal_short();
					break;
					case (10240|  4):money = _os_.unmarshal_long();
					break;
					case ( 6144|  4):money = _os_.unmarshal_short();
					break;
					case ( 8192|  4):money = _os_.unmarshal_int();
					break;
					case ( 8192|  5):curlvlbuilddegree = _os_.unmarshal_int();
					break;
					case ( 6144|  5):curlvlbuilddegree = _os_.unmarshal_short();
					break;
					case ( 8192| 21):totalbuilddegree = _os_.unmarshal_int();
					break;
					case ( 6144| 21):totalbuilddegree = _os_.unmarshal_short();
					break;
					case (10240| 22):totalbanggong = _os_.unmarshal_long();
					break;
					case ( 6144| 22):totalbanggong = _os_.unmarshal_short();
					break;
					case ( 8192| 22):totalbanggong = _os_.unmarshal_int();
					break;
					case (18432|  6):declaration = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (18432|  7):publicinfo = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
					break;
					case (10240|  8):publictime = _os_.unmarshal_long();
					break;
					case ( 6144|  8):publictime = _os_.unmarshal_short();
					break;
					case ( 8192|  8):publictime = _os_.unmarshal_int();
					break;
					case ( 8192|  9):malllevel = _os_.unmarshal_int();
					break;
					case ( 6144|  9):malllevel = _os_.unmarshal_short();
					break;
					case (22528| 11):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.FamilyLogReport _v_ = xbean.Pod.newFamilyLogReportData();
	_v_.unmarshal(_os_);
	logs.add(_v_);
}
_os_ = _temp_;}
					break;
					case (24576| 12):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		requestinglist = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		requestinglist.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (26624| 13):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
activity.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case (26624| 20):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
beatanimalactivity.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case (26624| 14):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
welfare.unmarshal(_os_);
_os_ = _temp_;}
					break;
					case (10240| 15):chiefid = _os_.unmarshal_long();
					break;
					case ( 6144| 15):chiefid = _os_.unmarshal_short();
					break;
					case ( 8192| 15):chiefid = _os_.unmarshal_int();
					break;
					case (24576| 16):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		jobinfo = new java.util.HashMap<Integer, xbean.FamilyJobStaffList>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.FamilyJobStaffList _v_ = xbean.Pod.newFamilyJobStaffListData();
		_v_.unmarshal(_os_);
		jobinfo.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 17):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		members = new java.util.HashMap<Long, xbean.FamilyMember>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.FamilyMember _v_ = xbean.Pod.newFamilyMemberData();
		_v_.unmarshal(_os_);
		members.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (10240| 18):updatetime = _os_.unmarshal_long();
					break;
					case ( 6144| 18):updatetime = _os_.unmarshal_short();
					break;
					case ( 8192| 18):updatetime = _os_.unmarshal_int();
					break;
					case (10240| 19):createtime = _os_.unmarshal_long();
					break;
					case ( 6144| 19):createtime = _os_.unmarshal_short();
					break;
					case ( 8192| 19):createtime = _os_.unmarshal_int();
					break;
					case (10240| 23):lastpartyopentime = _os_.unmarshal_long();
					break;
					case ( 6144| 23):lastpartyopentime = _os_.unmarshal_short();
					break;
					case ( 8192| 23):lastpartyopentime = _os_.unmarshal_int();
					break;
					case (10240| 24):lastpartycalltime = _os_.unmarshal_long();
					break;
					case ( 6144| 24):lastpartycalltime = _os_.unmarshal_short();
					break;
					case ( 8192| 24):lastpartycalltime = _os_.unmarshal_int();
					break;
					case ( 8192| 25):issystemfam = _os_.unmarshal_int();
					break;
					case ( 6144| 25):issystemfam = _os_.unmarshal_short();
					break;
					case (24576| 26):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		invitelist = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		invitelist.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (10240| 27):lastresettime = _os_.unmarshal_long();
					break;
					case ( 6144| 27):lastresettime = _os_.unmarshal_short();
					break;
					case ( 8192| 27):lastresettime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Family copy() {
			return new Data(this);
		}

		@Override
		public xbean.Family toData() {
			return new Data(this);
		}

		public xbean.Family toBean() {
			return new Family(this, null, null);
		}

		@Override
		public xbean.Family toDataIf() {
			return this;
		}

		public xbean.Family toBeanIf() {
			return new Family(this, null, null);
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
		public long getFamilyid() { // 家族id
			return familyid;
		}

		@Override
		public String getFamilyname() { // 家族名字
			return familyname;
		}

		@Override
		public com.goldhuman.Common.Octets getFamilynameOctets() { // 家族名字
			return com.goldhuman.Common.Octets.wrap(getFamilyname(), xdb.Const.IO_CHARSET);
		}

		@Override
		public int getFlevel() { // 家族等级
			return flevel;
		}

		@Override
		public long getMoney() { // 家族资金
			return money;
		}

		@Override
		public int getCurlvlbuilddegree() { // 当前等级家族建设度
			return curlvlbuilddegree;
		}

		@Override
		public int getTotalbuilddegree() { // 总的家族建设度,排行榜使用
			return totalbuilddegree;
		}

		@Override
		public long getTotalbanggong() { // 总的家族帮贡
			return totalbanggong;
		}

		@Override
		public String getDeclaration() { // 家族宣言
			return declaration;
		}

		@Override
		public com.goldhuman.Common.Octets getDeclarationOctets() { // 家族宣言
			return com.goldhuman.Common.Octets.wrap(getDeclaration(), xdb.Const.IO_CHARSET);
		}

		@Override
		public String getPublicinfo() { // 家族公告
			return publicinfo;
		}

		@Override
		public com.goldhuman.Common.Octets getPublicinfoOctets() { // 家族公告
			return com.goldhuman.Common.Octets.wrap(getPublicinfo(), xdb.Const.IO_CHARSET);
		}

		@Override
		public long getPublictime() { // 公告时间
			return publictime;
		}

		@Override
		public int getMalllevel() { // 家族商店级别
			return malllevel;
		}

		@Override
		public java.util.List<xbean.FamilyLogReport> getLogs() { // 家族日志信息
			return logs;
		}

		@Override
		public java.util.List<xbean.FamilyLogReport> getLogsAsData() { // 家族日志信息
			return logs;
		}

		@Override
		public java.util.Map<Long, Long> getRequestinglist() { // 申请成员列表,key为角色id，value为申请时间
			return requestinglist;
		}

		@Override
		public java.util.Map<Long, Long> getRequestinglistAsData() { // 申请成员列表,key为角色id，value为申请时间
			return requestinglist;
		}

		@Override
		public xbean.FamilyActivity getActivity() { // 家族神兽信息
			return activity;
		}

		@Override
		public xbean.FamilyGodAnimalAct getBeatanimalactivity() { // 神兽挑战信息
			return beatanimalactivity;
		}

		@Override
		public xbean.FamilyWelfare getWelfare() { // 家族福利相关的信息
			return welfare;
		}

		@Override
		public long getChiefid() { // 组长角色id
			return chiefid;
		}

		@Override
		public java.util.Map<Integer, xbean.FamilyJobStaffList> getJobinfo() { // 家族职位信息，key为职位id，value为该职位人员信息允许一个职位多个人
			return jobinfo;
		}

		@Override
		public java.util.Map<Integer, xbean.FamilyJobStaffList> getJobinfoAsData() { // 家族职位信息，key为职位id，value为该职位人员信息允许一个职位多个人
			return jobinfo;
		}

		@Override
		public java.util.Map<Long, xbean.FamilyMember> getMembers() { // 家族成员信息
			return members;
		}

		@Override
		public java.util.Map<Long, xbean.FamilyMember> getMembersAsData() { // 家族成员信息
			return members;
		}

		@Override
		public long getUpdatetime() { // 信息更新时间
			return updatetime;
		}

		@Override
		public long getCreatetime() { // 家族创建的时间
			return createtime;
		}

		@Override
		public long getLastpartyopentime() { // 家族聚会上次开启时间
			return lastpartyopentime;
		}

		@Override
		public long getLastpartycalltime() { // 上次召集教众时间
			return lastpartycalltime;
		}

		@Override
		public int getIssystemfam() { // 是否是系统创建的家族，0否，1是
			return issystemfam;
		}

		@Override
		public java.util.Map<Long, Long> getInvitelist() { // 邀请列表
			return invitelist;
		}

		@Override
		public java.util.Map<Long, Long> getInvitelistAsData() { // 邀请列表
			return invitelist;
		}

		@Override
		public long getLastresettime() { // 
			return lastresettime;
		}

		@Override
		public void setFamilyid(long _v_) { // 家族id
			familyid = _v_;
		}

		@Override
		public void setFamilyname(String _v_) { // 家族名字
			if (null == _v_)
				throw new NullPointerException();
			familyname = _v_;
		}

		@Override
		public void setFamilynameOctets(com.goldhuman.Common.Octets _v_) { // 家族名字
			this.setFamilyname(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setFlevel(int _v_) { // 家族等级
			flevel = _v_;
		}

		@Override
		public void setMoney(long _v_) { // 家族资金
			money = _v_;
		}

		@Override
		public void setCurlvlbuilddegree(int _v_) { // 当前等级家族建设度
			curlvlbuilddegree = _v_;
		}

		@Override
		public void setTotalbuilddegree(int _v_) { // 总的家族建设度,排行榜使用
			totalbuilddegree = _v_;
		}

		@Override
		public void setTotalbanggong(long _v_) { // 总的家族帮贡
			totalbanggong = _v_;
		}

		@Override
		public void setDeclaration(String _v_) { // 家族宣言
			if (null == _v_)
				throw new NullPointerException();
			declaration = _v_;
		}

		@Override
		public void setDeclarationOctets(com.goldhuman.Common.Octets _v_) { // 家族宣言
			this.setDeclaration(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setPublicinfo(String _v_) { // 家族公告
			if (null == _v_)
				throw new NullPointerException();
			publicinfo = _v_;
		}

		@Override
		public void setPublicinfoOctets(com.goldhuman.Common.Octets _v_) { // 家族公告
			this.setPublicinfo(_v_.getString(xdb.Const.IO_CHARSET));
		}

		@Override
		public void setPublictime(long _v_) { // 公告时间
			publictime = _v_;
		}

		@Override
		public void setMalllevel(int _v_) { // 家族商店级别
			malllevel = _v_;
		}

		@Override
		public void setChiefid(long _v_) { // 组长角色id
			chiefid = _v_;
		}

		@Override
		public void setUpdatetime(long _v_) { // 信息更新时间
			updatetime = _v_;
		}

		@Override
		public void setCreatetime(long _v_) { // 家族创建的时间
			createtime = _v_;
		}

		@Override
		public void setLastpartyopentime(long _v_) { // 家族聚会上次开启时间
			lastpartyopentime = _v_;
		}

		@Override
		public void setLastpartycalltime(long _v_) { // 上次召集教众时间
			lastpartycalltime = _v_;
		}

		@Override
		public void setIssystemfam(int _v_) { // 是否是系统创建的家族，0否，1是
			issystemfam = _v_;
		}

		@Override
		public void setLastresettime(long _v_) { // 
			lastresettime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Family.Data)) return false;
			Family.Data _o_ = (Family.Data) _o1_;
			if (familyid != _o_.familyid) return false;
			if (!familyname.equals(_o_.familyname)) return false;
			if (flevel != _o_.flevel) return false;
			if (money != _o_.money) return false;
			if (curlvlbuilddegree != _o_.curlvlbuilddegree) return false;
			if (totalbuilddegree != _o_.totalbuilddegree) return false;
			if (totalbanggong != _o_.totalbanggong) return false;
			if (!declaration.equals(_o_.declaration)) return false;
			if (!publicinfo.equals(_o_.publicinfo)) return false;
			if (publictime != _o_.publictime) return false;
			if (malllevel != _o_.malllevel) return false;
			if (!logs.equals(_o_.logs)) return false;
			if (!requestinglist.equals(_o_.requestinglist)) return false;
			if (!activity.equals(_o_.activity)) return false;
			if (!beatanimalactivity.equals(_o_.beatanimalactivity)) return false;
			if (!welfare.equals(_o_.welfare)) return false;
			if (chiefid != _o_.chiefid) return false;
			if (!jobinfo.equals(_o_.jobinfo)) return false;
			if (!members.equals(_o_.members)) return false;
			if (updatetime != _o_.updatetime) return false;
			if (createtime != _o_.createtime) return false;
			if (lastpartyopentime != _o_.lastpartyopentime) return false;
			if (lastpartycalltime != _o_.lastpartycalltime) return false;
			if (issystemfam != _o_.issystemfam) return false;
			if (!invitelist.equals(_o_.invitelist)) return false;
			if (lastresettime != _o_.lastresettime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += familyid;
			_h_ += familyname.hashCode();
			_h_ += flevel;
			_h_ += money;
			_h_ += curlvlbuilddegree;
			_h_ += totalbuilddegree;
			_h_ += totalbanggong;
			_h_ += declaration.hashCode();
			_h_ += publicinfo.hashCode();
			_h_ += publictime;
			_h_ += malllevel;
			_h_ += logs.hashCode();
			_h_ += requestinglist.hashCode();
			_h_ += activity.hashCode();
			_h_ += beatanimalactivity.hashCode();
			_h_ += welfare.hashCode();
			_h_ += chiefid;
			_h_ += jobinfo.hashCode();
			_h_ += members.hashCode();
			_h_ += updatetime;
			_h_ += createtime;
			_h_ += lastpartyopentime;
			_h_ += lastpartycalltime;
			_h_ += issystemfam;
			_h_ += invitelist.hashCode();
			_h_ += lastresettime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(familyid);
			_sb_.append(",");
			_sb_.append("'").append(familyname).append("'");
			_sb_.append(",");
			_sb_.append(flevel);
			_sb_.append(",");
			_sb_.append(money);
			_sb_.append(",");
			_sb_.append(curlvlbuilddegree);
			_sb_.append(",");
			_sb_.append(totalbuilddegree);
			_sb_.append(",");
			_sb_.append(totalbanggong);
			_sb_.append(",");
			_sb_.append("'").append(declaration).append("'");
			_sb_.append(",");
			_sb_.append("'").append(publicinfo).append("'");
			_sb_.append(",");
			_sb_.append(publictime);
			_sb_.append(",");
			_sb_.append(malllevel);
			_sb_.append(",");
			_sb_.append(logs);
			_sb_.append(",");
			_sb_.append(requestinglist);
			_sb_.append(",");
			_sb_.append(activity);
			_sb_.append(",");
			_sb_.append(beatanimalactivity);
			_sb_.append(",");
			_sb_.append(welfare);
			_sb_.append(",");
			_sb_.append(chiefid);
			_sb_.append(",");
			_sb_.append(jobinfo);
			_sb_.append(",");
			_sb_.append(members);
			_sb_.append(",");
			_sb_.append(updatetime);
			_sb_.append(",");
			_sb_.append(createtime);
			_sb_.append(",");
			_sb_.append(lastpartyopentime);
			_sb_.append(",");
			_sb_.append(lastpartycalltime);
			_sb_.append(",");
			_sb_.append(issystemfam);
			_sb_.append(",");
			_sb_.append(invitelist);
			_sb_.append(",");
			_sb_.append(lastresettime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
