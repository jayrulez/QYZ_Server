
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleFriendsInfo extends xdb.XBean implements xbean.RoleFriendsInfo {
	private java.util.HashMap<Long, xbean.RoleFriend> friends; // 好友列表
	private java.util.HashMap<Long, Long> requesting; // 申请列表
	private java.util.HashMap<Long, Long> blacklist; // 被屏蔽的
	private int charmdegree; // 个人的魅力值，根据别人送给我的花决定
	private java.util.HashMap<Long, Integer> idolfrienddegree; // 跟偶像的友好度，key为偶像的id
	private java.util.HashMap<Long, xbean.IdolAwardClaim> idolawardclaiminfo; // 偶像奖励的领取情况，key为偶像id，value为领取情况
	private java.util.HashMap<Long, xbean.Enemyinfo> enemylist; // 仇人信息
	private int isallowfriendgetmm; // 是否允许好友查看脉脉
	private int isallowstrangergetmm; // 是否允许陌生人查看脉脉
	private java.util.HashMap<Integer, xbean.RoleRelation> relationinfo; // maimai列表,key为脉脉类型

	@Override
	public void _reset_unsafe_() {
		friends.clear();
		requesting.clear();
		blacklist.clear();
		charmdegree = 0;
		idolfrienddegree.clear();
		idolawardclaiminfo.clear();
		enemylist.clear();
		isallowfriendgetmm = 0;
		isallowstrangergetmm = 0;
		relationinfo.clear();
	}

	RoleFriendsInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		friends = new java.util.HashMap<Long, xbean.RoleFriend>();
		requesting = new java.util.HashMap<Long, Long>();
		blacklist = new java.util.HashMap<Long, Long>();
		idolfrienddegree = new java.util.HashMap<Long, Integer>();
		idolawardclaiminfo = new java.util.HashMap<Long, xbean.IdolAwardClaim>();
		enemylist = new java.util.HashMap<Long, xbean.Enemyinfo>();
		relationinfo = new java.util.HashMap<Integer, xbean.RoleRelation>();
	}

	public RoleFriendsInfo() {
		this(0, null, null);
	}

	public RoleFriendsInfo(RoleFriendsInfo _o_) {
		this(_o_, null, null);
	}

	RoleFriendsInfo(xbean.RoleFriendsInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleFriendsInfo) assign((RoleFriendsInfo)_o1_);
		else if (_o1_ instanceof RoleFriendsInfo.Data) assign((RoleFriendsInfo.Data)_o1_);
		else if (_o1_ instanceof RoleFriendsInfo.Const) assign(((RoleFriendsInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleFriendsInfo _o_) {
		_o_._xdb_verify_unsafe_();
		friends = new java.util.HashMap<Long, xbean.RoleFriend>();
		for (java.util.Map.Entry<Long, xbean.RoleFriend> _e_ : _o_.friends.entrySet())
			friends.put(_e_.getKey(), new RoleFriend(_e_.getValue(), this, "friends"));
		requesting = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requesting.entrySet())
			requesting.put(_e_.getKey(), _e_.getValue());
		blacklist = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.blacklist.entrySet())
			blacklist.put(_e_.getKey(), _e_.getValue());
		charmdegree = _o_.charmdegree;
		idolfrienddegree = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.idolfrienddegree.entrySet())
			idolfrienddegree.put(_e_.getKey(), _e_.getValue());
		idolawardclaiminfo = new java.util.HashMap<Long, xbean.IdolAwardClaim>();
		for (java.util.Map.Entry<Long, xbean.IdolAwardClaim> _e_ : _o_.idolawardclaiminfo.entrySet())
			idolawardclaiminfo.put(_e_.getKey(), new IdolAwardClaim(_e_.getValue(), this, "idolawardclaiminfo"));
		enemylist = new java.util.HashMap<Long, xbean.Enemyinfo>();
		for (java.util.Map.Entry<Long, xbean.Enemyinfo> _e_ : _o_.enemylist.entrySet())
			enemylist.put(_e_.getKey(), new Enemyinfo(_e_.getValue(), this, "enemylist"));
		isallowfriendgetmm = _o_.isallowfriendgetmm;
		isallowstrangergetmm = _o_.isallowstrangergetmm;
		relationinfo = new java.util.HashMap<Integer, xbean.RoleRelation>();
		for (java.util.Map.Entry<Integer, xbean.RoleRelation> _e_ : _o_.relationinfo.entrySet())
			relationinfo.put(_e_.getKey(), new RoleRelation(_e_.getValue(), this, "relationinfo"));
	}

	private void assign(RoleFriendsInfo.Data _o_) {
		friends = new java.util.HashMap<Long, xbean.RoleFriend>();
		for (java.util.Map.Entry<Long, xbean.RoleFriend> _e_ : _o_.friends.entrySet())
			friends.put(_e_.getKey(), new RoleFriend(_e_.getValue(), this, "friends"));
		requesting = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requesting.entrySet())
			requesting.put(_e_.getKey(), _e_.getValue());
		blacklist = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.blacklist.entrySet())
			blacklist.put(_e_.getKey(), _e_.getValue());
		charmdegree = _o_.charmdegree;
		idolfrienddegree = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.idolfrienddegree.entrySet())
			idolfrienddegree.put(_e_.getKey(), _e_.getValue());
		idolawardclaiminfo = new java.util.HashMap<Long, xbean.IdolAwardClaim>();
		for (java.util.Map.Entry<Long, xbean.IdolAwardClaim> _e_ : _o_.idolawardclaiminfo.entrySet())
			idolawardclaiminfo.put(_e_.getKey(), new IdolAwardClaim(_e_.getValue(), this, "idolawardclaiminfo"));
		enemylist = new java.util.HashMap<Long, xbean.Enemyinfo>();
		for (java.util.Map.Entry<Long, xbean.Enemyinfo> _e_ : _o_.enemylist.entrySet())
			enemylist.put(_e_.getKey(), new Enemyinfo(_e_.getValue(), this, "enemylist"));
		isallowfriendgetmm = _o_.isallowfriendgetmm;
		isallowstrangergetmm = _o_.isallowstrangergetmm;
		relationinfo = new java.util.HashMap<Integer, xbean.RoleRelation>();
		for (java.util.Map.Entry<Integer, xbean.RoleRelation> _e_ : _o_.relationinfo.entrySet())
			relationinfo.put(_e_.getKey(), new RoleRelation(_e_.getValue(), this, "relationinfo"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)10);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(friends.size());
for (java.util.Map.Entry<Long, xbean.RoleFriend> _e_ : friends.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(requesting.size());
for (java.util.Map.Entry<Long, Long> _e_ : requesting.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(blacklist.size());
for (java.util.Map.Entry<Long, Long> _e_ : blacklist.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  8));_os_.marshal(charmdegree);
    _os_.marshal((short)(24576|  9));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(idolfrienddegree.size());
for (java.util.Map.Entry<Long, Integer> _e_ : idolfrienddegree.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(idolawardclaiminfo.size());
for (java.util.Map.Entry<Long, xbean.IdolAwardClaim> _e_ : idolawardclaiminfo.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 13));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(enemylist.size());
for (java.util.Map.Entry<Long, xbean.Enemyinfo> _e_ : enemylist.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192| 14));_os_.marshal(isallowfriendgetmm);
    _os_.marshal((short)( 8192| 15));_os_.marshal(isallowstrangergetmm);
    _os_.marshal((short)(24576| 16));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(relationinfo.size());
for (java.util.Map.Entry<Integer, xbean.RoleRelation> _e_ : relationinfo.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
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
		friends = new java.util.HashMap<Long, xbean.RoleFriend>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.RoleFriend _v_ = new RoleFriend(0, this, "friends");
		_v_.unmarshal(_os_);
		friends.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		requesting = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		requesting.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		blacklist = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		blacklist.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  8):charmdegree = _os_.unmarshal_int();
    				break;
    				case ( 6144|  8):charmdegree = _os_.unmarshal_short();
    				break;
    				case (24576|  9):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		idolfrienddegree = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		idolfrienddegree.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		idolawardclaiminfo = new java.util.HashMap<Long, xbean.IdolAwardClaim>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.IdolAwardClaim _v_ = new IdolAwardClaim(0, this, "idolawardclaiminfo");
		_v_.unmarshal(_os_);
		idolawardclaiminfo.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 13):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		enemylist = new java.util.HashMap<Long, xbean.Enemyinfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.Enemyinfo _v_ = new Enemyinfo(0, this, "enemylist");
		_v_.unmarshal(_os_);
		enemylist.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192| 14):isallowfriendgetmm = _os_.unmarshal_int();
    				break;
    				case ( 6144| 14):isallowfriendgetmm = _os_.unmarshal_short();
    				break;
    				case ( 8192| 15):isallowstrangergetmm = _os_.unmarshal_int();
    				break;
    				case ( 6144| 15):isallowstrangergetmm = _os_.unmarshal_short();
    				break;
    				case (24576| 16):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		relationinfo = new java.util.HashMap<Integer, xbean.RoleRelation>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.RoleRelation _v_ = new RoleRelation(0, this, "relationinfo");
		_v_.unmarshal(_os_);
		relationinfo.put(_k_, _v_);
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
	public xbean.RoleFriendsInfo copy() {
		_xdb_verify_unsafe_();
		return new RoleFriendsInfo(this);
	}

	@Override
	public xbean.RoleFriendsInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleFriendsInfo toBean() {
		_xdb_verify_unsafe_();
		return new RoleFriendsInfo(this); // same as copy()
	}

	@Override
	public xbean.RoleFriendsInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleFriendsInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Long, xbean.RoleFriend> getFriends() { // 好友列表
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "friends"), friends);
	}

	@Override
	public java.util.Map<Long, xbean.RoleFriend> getFriendsAsData() { // 好友列表
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.RoleFriend> friends;
		RoleFriendsInfo _o_ = this;
		friends = new java.util.HashMap<Long, xbean.RoleFriend>();
		for (java.util.Map.Entry<Long, xbean.RoleFriend> _e_ : _o_.friends.entrySet())
			friends.put(_e_.getKey(), new RoleFriend.Data(_e_.getValue()));
		return friends;
	}

	@Override
	public java.util.Map<Long, Long> getRequesting() { // 申请列表
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "requesting"), requesting);
	}

	@Override
	public java.util.Map<Long, Long> getRequestingAsData() { // 申请列表
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> requesting;
		RoleFriendsInfo _o_ = this;
		requesting = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.requesting.entrySet())
			requesting.put(_e_.getKey(), _e_.getValue());
		return requesting;
	}

	@Override
	public java.util.Map<Long, Long> getBlacklist() { // 被屏蔽的
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "blacklist"), blacklist);
	}

	@Override
	public java.util.Map<Long, Long> getBlacklistAsData() { // 被屏蔽的
		_xdb_verify_unsafe_();
		java.util.Map<Long, Long> blacklist;
		RoleFriendsInfo _o_ = this;
		blacklist = new java.util.HashMap<Long, Long>();
		for (java.util.Map.Entry<Long, Long> _e_ : _o_.blacklist.entrySet())
			blacklist.put(_e_.getKey(), _e_.getValue());
		return blacklist;
	}

	@Override
	public int getCharmdegree() { // 个人的魅力值，根据别人送给我的花决定
		_xdb_verify_unsafe_();
		return charmdegree;
	}

	@Override
	public java.util.Map<Long, Integer> getIdolfrienddegree() { // 跟偶像的友好度，key为偶像的id
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "idolfrienddegree"), idolfrienddegree);
	}

	@Override
	public java.util.Map<Long, Integer> getIdolfrienddegreeAsData() { // 跟偶像的友好度，key为偶像的id
		_xdb_verify_unsafe_();
		java.util.Map<Long, Integer> idolfrienddegree;
		RoleFriendsInfo _o_ = this;
		idolfrienddegree = new java.util.HashMap<Long, Integer>();
		for (java.util.Map.Entry<Long, Integer> _e_ : _o_.idolfrienddegree.entrySet())
			idolfrienddegree.put(_e_.getKey(), _e_.getValue());
		return idolfrienddegree;
	}

	@Override
	public java.util.Map<Long, xbean.IdolAwardClaim> getIdolawardclaiminfo() { // 偶像奖励的领取情况，key为偶像id，value为领取情况
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "idolawardclaiminfo"), idolawardclaiminfo);
	}

	@Override
	public java.util.Map<Long, xbean.IdolAwardClaim> getIdolawardclaiminfoAsData() { // 偶像奖励的领取情况，key为偶像id，value为领取情况
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.IdolAwardClaim> idolawardclaiminfo;
		RoleFriendsInfo _o_ = this;
		idolawardclaiminfo = new java.util.HashMap<Long, xbean.IdolAwardClaim>();
		for (java.util.Map.Entry<Long, xbean.IdolAwardClaim> _e_ : _o_.idolawardclaiminfo.entrySet())
			idolawardclaiminfo.put(_e_.getKey(), new IdolAwardClaim.Data(_e_.getValue()));
		return idolawardclaiminfo;
	}

	@Override
	public java.util.Map<Long, xbean.Enemyinfo> getEnemylist() { // 仇人信息
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "enemylist"), enemylist);
	}

	@Override
	public java.util.Map<Long, xbean.Enemyinfo> getEnemylistAsData() { // 仇人信息
		_xdb_verify_unsafe_();
		java.util.Map<Long, xbean.Enemyinfo> enemylist;
		RoleFriendsInfo _o_ = this;
		enemylist = new java.util.HashMap<Long, xbean.Enemyinfo>();
		for (java.util.Map.Entry<Long, xbean.Enemyinfo> _e_ : _o_.enemylist.entrySet())
			enemylist.put(_e_.getKey(), new Enemyinfo.Data(_e_.getValue()));
		return enemylist;
	}

	@Override
	public int getIsallowfriendgetmm() { // 是否允许好友查看脉脉
		_xdb_verify_unsafe_();
		return isallowfriendgetmm;
	}

	@Override
	public int getIsallowstrangergetmm() { // 是否允许陌生人查看脉脉
		_xdb_verify_unsafe_();
		return isallowstrangergetmm;
	}

	@Override
	public java.util.Map<Integer, xbean.RoleRelation> getRelationinfo() { // maimai列表,key为脉脉类型
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "relationinfo"), relationinfo);
	}

	@Override
	public java.util.Map<Integer, xbean.RoleRelation> getRelationinfoAsData() { // maimai列表,key为脉脉类型
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.RoleRelation> relationinfo;
		RoleFriendsInfo _o_ = this;
		relationinfo = new java.util.HashMap<Integer, xbean.RoleRelation>();
		for (java.util.Map.Entry<Integer, xbean.RoleRelation> _e_ : _o_.relationinfo.entrySet())
			relationinfo.put(_e_.getKey(), new RoleRelation.Data(_e_.getValue()));
		return relationinfo;
	}

	@Override
	public void setCharmdegree(int _v_) { // 个人的魅力值，根据别人送给我的花决定
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "charmdegree") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, charmdegree) {
					public void rollback() { charmdegree = _xdb_saved; }
				};}});
		charmdegree = _v_;
	}

	@Override
	public void setIsallowfriendgetmm(int _v_) { // 是否允许好友查看脉脉
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "isallowfriendgetmm") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, isallowfriendgetmm) {
					public void rollback() { isallowfriendgetmm = _xdb_saved; }
				};}});
		isallowfriendgetmm = _v_;
	}

	@Override
	public void setIsallowstrangergetmm(int _v_) { // 是否允许陌生人查看脉脉
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "isallowstrangergetmm") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, isallowstrangergetmm) {
					public void rollback() { isallowstrangergetmm = _xdb_saved; }
				};}});
		isallowstrangergetmm = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleFriendsInfo _o_ = null;
		if ( _o1_ instanceof RoleFriendsInfo ) _o_ = (RoleFriendsInfo)_o1_;
		else if ( _o1_ instanceof RoleFriendsInfo.Const ) _o_ = ((RoleFriendsInfo.Const)_o1_).nThis();
		else return false;
		if (!friends.equals(_o_.friends)) return false;
		if (!requesting.equals(_o_.requesting)) return false;
		if (!blacklist.equals(_o_.blacklist)) return false;
		if (charmdegree != _o_.charmdegree) return false;
		if (!idolfrienddegree.equals(_o_.idolfrienddegree)) return false;
		if (!idolawardclaiminfo.equals(_o_.idolawardclaiminfo)) return false;
		if (!enemylist.equals(_o_.enemylist)) return false;
		if (isallowfriendgetmm != _o_.isallowfriendgetmm) return false;
		if (isallowstrangergetmm != _o_.isallowstrangergetmm) return false;
		if (!relationinfo.equals(_o_.relationinfo)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += friends.hashCode();
		_h_ += requesting.hashCode();
		_h_ += blacklist.hashCode();
		_h_ += charmdegree;
		_h_ += idolfrienddegree.hashCode();
		_h_ += idolawardclaiminfo.hashCode();
		_h_ += enemylist.hashCode();
		_h_ += isallowfriendgetmm;
		_h_ += isallowstrangergetmm;
		_h_ += relationinfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(friends);
		_sb_.append(",");
		_sb_.append(requesting);
		_sb_.append(",");
		_sb_.append(blacklist);
		_sb_.append(",");
		_sb_.append(charmdegree);
		_sb_.append(",");
		_sb_.append(idolfrienddegree);
		_sb_.append(",");
		_sb_.append(idolawardclaiminfo);
		_sb_.append(",");
		_sb_.append(enemylist);
		_sb_.append(",");
		_sb_.append(isallowfriendgetmm);
		_sb_.append(",");
		_sb_.append(isallowstrangergetmm);
		_sb_.append(",");
		_sb_.append(relationinfo);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("friends"));
		lb.add(new xdb.logs.ListenableMap().setVarName("requesting"));
		lb.add(new xdb.logs.ListenableMap().setVarName("blacklist"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("charmdegree"));
		lb.add(new xdb.logs.ListenableMap().setVarName("idolfrienddegree"));
		lb.add(new xdb.logs.ListenableMap().setVarName("idolawardclaiminfo"));
		lb.add(new xdb.logs.ListenableMap().setVarName("enemylist"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isallowfriendgetmm"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isallowstrangergetmm"));
		lb.add(new xdb.logs.ListenableMap().setVarName("relationinfo"));
		return lb;
	}

	private class Const implements xbean.RoleFriendsInfo {
		RoleFriendsInfo nThis() {
			return RoleFriendsInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleFriendsInfo copy() {
			return RoleFriendsInfo.this.copy();
		}

		@Override
		public xbean.RoleFriendsInfo toData() {
			return RoleFriendsInfo.this.toData();
		}

		public xbean.RoleFriendsInfo toBean() {
			return RoleFriendsInfo.this.toBean();
		}

		@Override
		public xbean.RoleFriendsInfo toDataIf() {
			return RoleFriendsInfo.this.toDataIf();
		}

		public xbean.RoleFriendsInfo toBeanIf() {
			return RoleFriendsInfo.this.toBeanIf();
		}

		@Override
		public java.util.Map<Long, xbean.RoleFriend> getFriends() { // 好友列表
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(friends);
		}

		@Override
		public java.util.Map<Long, xbean.RoleFriend> getFriendsAsData() { // 好友列表
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.RoleFriend> friends;
			RoleFriendsInfo _o_ = RoleFriendsInfo.this;
			friends = new java.util.HashMap<Long, xbean.RoleFriend>();
			for (java.util.Map.Entry<Long, xbean.RoleFriend> _e_ : _o_.friends.entrySet())
				friends.put(_e_.getKey(), new RoleFriend.Data(_e_.getValue()));
			return friends;
		}

		@Override
		public java.util.Map<Long, Long> getRequesting() { // 申请列表
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(requesting);
		}

		@Override
		public java.util.Map<Long, Long> getRequestingAsData() { // 申请列表
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> requesting;
			RoleFriendsInfo _o_ = RoleFriendsInfo.this;
			requesting = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requesting.entrySet())
				requesting.put(_e_.getKey(), _e_.getValue());
			return requesting;
		}

		@Override
		public java.util.Map<Long, Long> getBlacklist() { // 被屏蔽的
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(blacklist);
		}

		@Override
		public java.util.Map<Long, Long> getBlacklistAsData() { // 被屏蔽的
			_xdb_verify_unsafe_();
			java.util.Map<Long, Long> blacklist;
			RoleFriendsInfo _o_ = RoleFriendsInfo.this;
			blacklist = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.blacklist.entrySet())
				blacklist.put(_e_.getKey(), _e_.getValue());
			return blacklist;
		}

		@Override
		public int getCharmdegree() { // 个人的魅力值，根据别人送给我的花决定
			_xdb_verify_unsafe_();
			return charmdegree;
		}

		@Override
		public java.util.Map<Long, Integer> getIdolfrienddegree() { // 跟偶像的友好度，key为偶像的id
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(idolfrienddegree);
		}

		@Override
		public java.util.Map<Long, Integer> getIdolfrienddegreeAsData() { // 跟偶像的友好度，key为偶像的id
			_xdb_verify_unsafe_();
			java.util.Map<Long, Integer> idolfrienddegree;
			RoleFriendsInfo _o_ = RoleFriendsInfo.this;
			idolfrienddegree = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.idolfrienddegree.entrySet())
				idolfrienddegree.put(_e_.getKey(), _e_.getValue());
			return idolfrienddegree;
		}

		@Override
		public java.util.Map<Long, xbean.IdolAwardClaim> getIdolawardclaiminfo() { // 偶像奖励的领取情况，key为偶像id，value为领取情况
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(idolawardclaiminfo);
		}

		@Override
		public java.util.Map<Long, xbean.IdolAwardClaim> getIdolawardclaiminfoAsData() { // 偶像奖励的领取情况，key为偶像id，value为领取情况
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.IdolAwardClaim> idolawardclaiminfo;
			RoleFriendsInfo _o_ = RoleFriendsInfo.this;
			idolawardclaiminfo = new java.util.HashMap<Long, xbean.IdolAwardClaim>();
			for (java.util.Map.Entry<Long, xbean.IdolAwardClaim> _e_ : _o_.idolawardclaiminfo.entrySet())
				idolawardclaiminfo.put(_e_.getKey(), new IdolAwardClaim.Data(_e_.getValue()));
			return idolawardclaiminfo;
		}

		@Override
		public java.util.Map<Long, xbean.Enemyinfo> getEnemylist() { // 仇人信息
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(enemylist);
		}

		@Override
		public java.util.Map<Long, xbean.Enemyinfo> getEnemylistAsData() { // 仇人信息
			_xdb_verify_unsafe_();
			java.util.Map<Long, xbean.Enemyinfo> enemylist;
			RoleFriendsInfo _o_ = RoleFriendsInfo.this;
			enemylist = new java.util.HashMap<Long, xbean.Enemyinfo>();
			for (java.util.Map.Entry<Long, xbean.Enemyinfo> _e_ : _o_.enemylist.entrySet())
				enemylist.put(_e_.getKey(), new Enemyinfo.Data(_e_.getValue()));
			return enemylist;
		}

		@Override
		public int getIsallowfriendgetmm() { // 是否允许好友查看脉脉
			_xdb_verify_unsafe_();
			return isallowfriendgetmm;
		}

		@Override
		public int getIsallowstrangergetmm() { // 是否允许陌生人查看脉脉
			_xdb_verify_unsafe_();
			return isallowstrangergetmm;
		}

		@Override
		public java.util.Map<Integer, xbean.RoleRelation> getRelationinfo() { // maimai列表,key为脉脉类型
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(relationinfo);
		}

		@Override
		public java.util.Map<Integer, xbean.RoleRelation> getRelationinfoAsData() { // maimai列表,key为脉脉类型
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.RoleRelation> relationinfo;
			RoleFriendsInfo _o_ = RoleFriendsInfo.this;
			relationinfo = new java.util.HashMap<Integer, xbean.RoleRelation>();
			for (java.util.Map.Entry<Integer, xbean.RoleRelation> _e_ : _o_.relationinfo.entrySet())
				relationinfo.put(_e_.getKey(), new RoleRelation.Data(_e_.getValue()));
			return relationinfo;
		}

		@Override
		public void setCharmdegree(int _v_) { // 个人的魅力值，根据别人送给我的花决定
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsallowfriendgetmm(int _v_) { // 是否允许好友查看脉脉
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsallowstrangergetmm(int _v_) { // 是否允许陌生人查看脉脉
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
			return RoleFriendsInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleFriendsInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleFriendsInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleFriendsInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleFriendsInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleFriendsInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleFriendsInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleFriendsInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleFriendsInfo.this.toString();
		}

	}

	public static final class Data implements xbean.RoleFriendsInfo {
		private java.util.HashMap<Long, xbean.RoleFriend> friends; // 好友列表
		private java.util.HashMap<Long, Long> requesting; // 申请列表
		private java.util.HashMap<Long, Long> blacklist; // 被屏蔽的
		private int charmdegree; // 个人的魅力值，根据别人送给我的花决定
		private java.util.HashMap<Long, Integer> idolfrienddegree; // 跟偶像的友好度，key为偶像的id
		private java.util.HashMap<Long, xbean.IdolAwardClaim> idolawardclaiminfo; // 偶像奖励的领取情况，key为偶像id，value为领取情况
		private java.util.HashMap<Long, xbean.Enemyinfo> enemylist; // 仇人信息
		private int isallowfriendgetmm; // 是否允许好友查看脉脉
		private int isallowstrangergetmm; // 是否允许陌生人查看脉脉
		private java.util.HashMap<Integer, xbean.RoleRelation> relationinfo; // maimai列表,key为脉脉类型

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			friends = new java.util.HashMap<Long, xbean.RoleFriend>();
			requesting = new java.util.HashMap<Long, Long>();
			blacklist = new java.util.HashMap<Long, Long>();
			idolfrienddegree = new java.util.HashMap<Long, Integer>();
			idolawardclaiminfo = new java.util.HashMap<Long, xbean.IdolAwardClaim>();
			enemylist = new java.util.HashMap<Long, xbean.Enemyinfo>();
			relationinfo = new java.util.HashMap<Integer, xbean.RoleRelation>();
		}

		Data(xbean.RoleFriendsInfo _o1_) {
			if (_o1_ instanceof RoleFriendsInfo) assign((RoleFriendsInfo)_o1_);
			else if (_o1_ instanceof RoleFriendsInfo.Data) assign((RoleFriendsInfo.Data)_o1_);
			else if (_o1_ instanceof RoleFriendsInfo.Const) assign(((RoleFriendsInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleFriendsInfo _o_) {
			friends = new java.util.HashMap<Long, xbean.RoleFriend>();
			for (java.util.Map.Entry<Long, xbean.RoleFriend> _e_ : _o_.friends.entrySet())
				friends.put(_e_.getKey(), new RoleFriend.Data(_e_.getValue()));
			requesting = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requesting.entrySet())
				requesting.put(_e_.getKey(), _e_.getValue());
			blacklist = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.blacklist.entrySet())
				blacklist.put(_e_.getKey(), _e_.getValue());
			charmdegree = _o_.charmdegree;
			idolfrienddegree = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.idolfrienddegree.entrySet())
				idolfrienddegree.put(_e_.getKey(), _e_.getValue());
			idolawardclaiminfo = new java.util.HashMap<Long, xbean.IdolAwardClaim>();
			for (java.util.Map.Entry<Long, xbean.IdolAwardClaim> _e_ : _o_.idolawardclaiminfo.entrySet())
				idolawardclaiminfo.put(_e_.getKey(), new IdolAwardClaim.Data(_e_.getValue()));
			enemylist = new java.util.HashMap<Long, xbean.Enemyinfo>();
			for (java.util.Map.Entry<Long, xbean.Enemyinfo> _e_ : _o_.enemylist.entrySet())
				enemylist.put(_e_.getKey(), new Enemyinfo.Data(_e_.getValue()));
			isallowfriendgetmm = _o_.isallowfriendgetmm;
			isallowstrangergetmm = _o_.isallowstrangergetmm;
			relationinfo = new java.util.HashMap<Integer, xbean.RoleRelation>();
			for (java.util.Map.Entry<Integer, xbean.RoleRelation> _e_ : _o_.relationinfo.entrySet())
				relationinfo.put(_e_.getKey(), new RoleRelation.Data(_e_.getValue()));
		}

		private void assign(RoleFriendsInfo.Data _o_) {
			friends = new java.util.HashMap<Long, xbean.RoleFriend>();
			for (java.util.Map.Entry<Long, xbean.RoleFriend> _e_ : _o_.friends.entrySet())
				friends.put(_e_.getKey(), new RoleFriend.Data(_e_.getValue()));
			requesting = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.requesting.entrySet())
				requesting.put(_e_.getKey(), _e_.getValue());
			blacklist = new java.util.HashMap<Long, Long>();
			for (java.util.Map.Entry<Long, Long> _e_ : _o_.blacklist.entrySet())
				blacklist.put(_e_.getKey(), _e_.getValue());
			charmdegree = _o_.charmdegree;
			idolfrienddegree = new java.util.HashMap<Long, Integer>();
			for (java.util.Map.Entry<Long, Integer> _e_ : _o_.idolfrienddegree.entrySet())
				idolfrienddegree.put(_e_.getKey(), _e_.getValue());
			idolawardclaiminfo = new java.util.HashMap<Long, xbean.IdolAwardClaim>();
			for (java.util.Map.Entry<Long, xbean.IdolAwardClaim> _e_ : _o_.idolawardclaiminfo.entrySet())
				idolawardclaiminfo.put(_e_.getKey(), new IdolAwardClaim.Data(_e_.getValue()));
			enemylist = new java.util.HashMap<Long, xbean.Enemyinfo>();
			for (java.util.Map.Entry<Long, xbean.Enemyinfo> _e_ : _o_.enemylist.entrySet())
				enemylist.put(_e_.getKey(), new Enemyinfo.Data(_e_.getValue()));
			isallowfriendgetmm = _o_.isallowfriendgetmm;
			isallowstrangergetmm = _o_.isallowstrangergetmm;
			relationinfo = new java.util.HashMap<Integer, xbean.RoleRelation>();
			for (java.util.Map.Entry<Integer, xbean.RoleRelation> _e_ : _o_.relationinfo.entrySet())
				relationinfo.put(_e_.getKey(), new RoleRelation.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)10);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(friends.size());
for (java.util.Map.Entry<Long, xbean.RoleFriend> _e_ : friends.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(requesting.size());
for (java.util.Map.Entry<Long, Long> _e_ : requesting.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(blacklist.size());
for (java.util.Map.Entry<Long, Long> _e_ : blacklist.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  8));_os_.marshal(charmdegree);
	_os_.marshal((short)(24576|  9));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(idolfrienddegree.size());
for (java.util.Map.Entry<Long, Integer> _e_ : idolfrienddegree.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(idolawardclaiminfo.size());
for (java.util.Map.Entry<Long, xbean.IdolAwardClaim> _e_ : idolawardclaiminfo.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 13));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(enemylist.size());
for (java.util.Map.Entry<Long, xbean.Enemyinfo> _e_ : enemylist.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192| 14));_os_.marshal(isallowfriendgetmm);
	_os_.marshal((short)( 8192| 15));_os_.marshal(isallowstrangergetmm);
	_os_.marshal((short)(24576| 16));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(relationinfo.size());
for (java.util.Map.Entry<Integer, xbean.RoleRelation> _e_ : relationinfo.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
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
		friends = new java.util.HashMap<Long, xbean.RoleFriend>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.RoleFriend _v_ = xbean.Pod.newRoleFriendData();
		_v_.unmarshal(_os_);
		friends.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		requesting = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		requesting.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		blacklist = new java.util.HashMap<Long, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		blacklist.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192|  8):charmdegree = _os_.unmarshal_int();
					break;
					case ( 6144|  8):charmdegree = _os_.unmarshal_short();
					break;
					case (24576|  9):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		idolfrienddegree = new java.util.HashMap<Long, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		idolfrienddegree.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		idolawardclaiminfo = new java.util.HashMap<Long, xbean.IdolAwardClaim>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.IdolAwardClaim _v_ = xbean.Pod.newIdolAwardClaimData();
		_v_.unmarshal(_os_);
		idolawardclaiminfo.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 13):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		enemylist = new java.util.HashMap<Long, xbean.Enemyinfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		long _k_ = 0;
		_k_ = _os_.unmarshal_long();
		xbean.Enemyinfo _v_ = xbean.Pod.newEnemyinfoData();
		_v_.unmarshal(_os_);
		enemylist.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192| 14):isallowfriendgetmm = _os_.unmarshal_int();
					break;
					case ( 6144| 14):isallowfriendgetmm = _os_.unmarshal_short();
					break;
					case ( 8192| 15):isallowstrangergetmm = _os_.unmarshal_int();
					break;
					case ( 6144| 15):isallowstrangergetmm = _os_.unmarshal_short();
					break;
					case (24576| 16):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		relationinfo = new java.util.HashMap<Integer, xbean.RoleRelation>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.RoleRelation _v_ = xbean.Pod.newRoleRelationData();
		_v_.unmarshal(_os_);
		relationinfo.put(_k_, _v_);
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
		public xbean.RoleFriendsInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleFriendsInfo toData() {
			return new Data(this);
		}

		public xbean.RoleFriendsInfo toBean() {
			return new RoleFriendsInfo(this, null, null);
		}

		@Override
		public xbean.RoleFriendsInfo toDataIf() {
			return this;
		}

		public xbean.RoleFriendsInfo toBeanIf() {
			return new RoleFriendsInfo(this, null, null);
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
		public java.util.Map<Long, xbean.RoleFriend> getFriends() { // 好友列表
			return friends;
		}

		@Override
		public java.util.Map<Long, xbean.RoleFriend> getFriendsAsData() { // 好友列表
			return friends;
		}

		@Override
		public java.util.Map<Long, Long> getRequesting() { // 申请列表
			return requesting;
		}

		@Override
		public java.util.Map<Long, Long> getRequestingAsData() { // 申请列表
			return requesting;
		}

		@Override
		public java.util.Map<Long, Long> getBlacklist() { // 被屏蔽的
			return blacklist;
		}

		@Override
		public java.util.Map<Long, Long> getBlacklistAsData() { // 被屏蔽的
			return blacklist;
		}

		@Override
		public int getCharmdegree() { // 个人的魅力值，根据别人送给我的花决定
			return charmdegree;
		}

		@Override
		public java.util.Map<Long, Integer> getIdolfrienddegree() { // 跟偶像的友好度，key为偶像的id
			return idolfrienddegree;
		}

		@Override
		public java.util.Map<Long, Integer> getIdolfrienddegreeAsData() { // 跟偶像的友好度，key为偶像的id
			return idolfrienddegree;
		}

		@Override
		public java.util.Map<Long, xbean.IdolAwardClaim> getIdolawardclaiminfo() { // 偶像奖励的领取情况，key为偶像id，value为领取情况
			return idolawardclaiminfo;
		}

		@Override
		public java.util.Map<Long, xbean.IdolAwardClaim> getIdolawardclaiminfoAsData() { // 偶像奖励的领取情况，key为偶像id，value为领取情况
			return idolawardclaiminfo;
		}

		@Override
		public java.util.Map<Long, xbean.Enemyinfo> getEnemylist() { // 仇人信息
			return enemylist;
		}

		@Override
		public java.util.Map<Long, xbean.Enemyinfo> getEnemylistAsData() { // 仇人信息
			return enemylist;
		}

		@Override
		public int getIsallowfriendgetmm() { // 是否允许好友查看脉脉
			return isallowfriendgetmm;
		}

		@Override
		public int getIsallowstrangergetmm() { // 是否允许陌生人查看脉脉
			return isallowstrangergetmm;
		}

		@Override
		public java.util.Map<Integer, xbean.RoleRelation> getRelationinfo() { // maimai列表,key为脉脉类型
			return relationinfo;
		}

		@Override
		public java.util.Map<Integer, xbean.RoleRelation> getRelationinfoAsData() { // maimai列表,key为脉脉类型
			return relationinfo;
		}

		@Override
		public void setCharmdegree(int _v_) { // 个人的魅力值，根据别人送给我的花决定
			charmdegree = _v_;
		}

		@Override
		public void setIsallowfriendgetmm(int _v_) { // 是否允许好友查看脉脉
			isallowfriendgetmm = _v_;
		}

		@Override
		public void setIsallowstrangergetmm(int _v_) { // 是否允许陌生人查看脉脉
			isallowstrangergetmm = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleFriendsInfo.Data)) return false;
			RoleFriendsInfo.Data _o_ = (RoleFriendsInfo.Data) _o1_;
			if (!friends.equals(_o_.friends)) return false;
			if (!requesting.equals(_o_.requesting)) return false;
			if (!blacklist.equals(_o_.blacklist)) return false;
			if (charmdegree != _o_.charmdegree) return false;
			if (!idolfrienddegree.equals(_o_.idolfrienddegree)) return false;
			if (!idolawardclaiminfo.equals(_o_.idolawardclaiminfo)) return false;
			if (!enemylist.equals(_o_.enemylist)) return false;
			if (isallowfriendgetmm != _o_.isallowfriendgetmm) return false;
			if (isallowstrangergetmm != _o_.isallowstrangergetmm) return false;
			if (!relationinfo.equals(_o_.relationinfo)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += friends.hashCode();
			_h_ += requesting.hashCode();
			_h_ += blacklist.hashCode();
			_h_ += charmdegree;
			_h_ += idolfrienddegree.hashCode();
			_h_ += idolawardclaiminfo.hashCode();
			_h_ += enemylist.hashCode();
			_h_ += isallowfriendgetmm;
			_h_ += isallowstrangergetmm;
			_h_ += relationinfo.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(friends);
			_sb_.append(",");
			_sb_.append(requesting);
			_sb_.append(",");
			_sb_.append(blacklist);
			_sb_.append(",");
			_sb_.append(charmdegree);
			_sb_.append(",");
			_sb_.append(idolfrienddegree);
			_sb_.append(",");
			_sb_.append(idolawardclaiminfo);
			_sb_.append(",");
			_sb_.append(enemylist);
			_sb_.append(",");
			_sb_.append(isallowfriendgetmm);
			_sb_.append(",");
			_sb_.append(isallowstrangergetmm);
			_sb_.append(",");
			_sb_.append(relationinfo);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
