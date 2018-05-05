package xtable;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class _Tables_ extends xdb.Tables {
	static volatile boolean isExplicitLockCheck = false;

	public static void startExplicitLockCheck() {
		isExplicitLockCheck = true;
	}

	public static _Tables_ getInstance() {
		return (_Tables_)xdb.Xdb.getInstance().getTables();
	}

	public _Tables_() {
		add(rolemarriageinfo);
		add(dress);
		add(roleskill);
		add(rolerank);
		add(leaderboards);
		add(familyname2id);
		add(amulet);
		add(roleorderhistorys);
		add(roletalismandepot);
		add(roleequipbag);
		add(rolefamily);
		add(ectypesingle);
		add(leaderboardrecord);
		add(systemmailbox);
		add(rolefriendsinfo);
		add(roleachievement);
		add(roleectypes);
		add(rolename2ids);
		add(userdeletedroles);
		add(idgen);
		add(exchangelog);
		add(rolewelfareinfo);
		add(rolemaps);
		add(rolefragmentdepot);
		add(huiwucurterms);
		add(users);
		add(huiwupriviousterms);
		add(ranks);
		add(mailbox);
		add(system);
		add(goldcoindepot);
		add(pickcardinfos);
		add(rolememinfos);
		add(processingorders);
		add(rolelimit);
		add(rolefragmentbag);
		add(roleitemdepot);
		add(globallactivity);
		add(roledailyresetdata);
		add(roleexchange);
		add(forbids);
		add(rolestorynotes);
		add(jade);
		add(onlineusers);
		add(title);
		add(ride);
		add(dailyactives);
		add(roleattrs);
		add(roletask);
		add(roleconfigure);
		add(role2team);
		add(idolcharm);
		add(roleitembag);
		add(gmsenseword);
		add(roleinfos);
		add(roletalismanbag);
		add(roleactivecodes);
		add(rolepays);
		add(team);
		add(rolechat);
		add(roleequipdepot);
		add(roleallactivity);
		add(rolenotice);
		add(rolearena);
		add(familyextrinfo);
		add(exchangeitem);
		add(rolepet);
		add(family);
	}

	// visible in package
	xdb.TTable<Long, xbean.RoleMarriage> rolemarriageinfo = new xdb.TTable<Long, xbean.RoleMarriage>() {
		@Override
		public String getName() {
			return "rolemarriageinfo";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleMarriage value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleMarriage unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleMarriage value = xbean.Pod.newRoleMarriage();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleMarriage newValue() {
			xbean.RoleMarriage value = xbean.Pod.newRoleMarriage();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleDress> dress = new xdb.TTable<Long, xbean.RoleDress>() {
		@Override
		public String getName() {
			return "dress";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleDress value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleDress unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleDress value = xbean.Pod.newRoleDress();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleDress newValue() {
			xbean.RoleDress value = xbean.Pod.newRoleDress();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleSkill> roleskill = new xdb.TTable<Long, xbean.RoleSkill>() {
		@Override
		public String getName() {
			return "roleskill";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleSkill value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleSkill unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleSkill value = xbean.Pod.newRoleSkill();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleSkill newValue() {
			xbean.RoleSkill value = xbean.Pod.newRoleSkill();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleRank> rolerank = new xdb.TTable<Long, xbean.RoleRank>() {
		@Override
		public String getName() {
			return "rolerank";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleRank value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleRank unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleRank value = xbean.Pod.newRoleRank();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleRank newValue() {
			xbean.RoleRank value = xbean.Pod.newRoleRank();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.BoardInfo> leaderboards = new xdb.TTable<Integer, xbean.BoardInfo>() {
		@Override
		public String getName() {
			return "leaderboards";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.BoardInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.BoardInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.BoardInfo value = xbean.Pod.newBoardInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.BoardInfo newValue() {
			xbean.BoardInfo value = xbean.Pod.newBoardInfo();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.FNameToID> familyname2id = new xdb.TTable<Integer, xbean.FNameToID>() {
		@Override
		public String getName() {
			return "familyname2id";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.FNameToID value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.FNameToID unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.FNameToID value = xbean.Pod.newFNameToID();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.FNameToID newValue() {
			xbean.FNameToID value = xbean.Pod.newFNameToID();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleAmuletInfo> amulet = new xdb.TTable<Long, xbean.RoleAmuletInfo>() {
		@Override
		public String getName() {
			return "amulet";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleAmuletInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleAmuletInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleAmuletInfo value = xbean.Pod.newRoleAmuletInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleAmuletInfo newValue() {
			xbean.RoleAmuletInfo value = xbean.Pod.newRoleAmuletInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleOrderHistroy> roleorderhistorys = new xdb.TTable<Long, xbean.RoleOrderHistroy>() {
		@Override
		public String getName() {
			return "roleorderhistorys";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleOrderHistroy value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleOrderHistroy unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleOrderHistroy value = xbean.Pod.newRoleOrderHistroy();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleOrderHistroy newValue() {
			xbean.RoleOrderHistroy value = xbean.Pod.newRoleOrderHistroy();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleTalismanDepot> roletalismandepot = new xdb.TTable<Long, xbean.RoleTalismanDepot>() {
		@Override
		public String getName() {
			return "roletalismandepot";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleTalismanDepot value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleTalismanDepot unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleTalismanDepot value = xbean.Pod.newRoleTalismanDepot();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleTalismanDepot newValue() {
			xbean.RoleTalismanDepot value = xbean.Pod.newRoleTalismanDepot();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleEquipBag> roleequipbag = new xdb.TTable<Long, xbean.RoleEquipBag>() {
		@Override
		public String getName() {
			return "roleequipbag";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleEquipBag value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleEquipBag unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleEquipBag value = xbean.Pod.newRoleEquipBag();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleEquipBag newValue() {
			xbean.RoleEquipBag value = xbean.Pod.newRoleEquipBag();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleFamily> rolefamily = new xdb.TTable<Long, xbean.RoleFamily>() {
		@Override
		public String getName() {
			return "rolefamily";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleFamily value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleFamily unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleFamily value = xbean.Pod.newRoleFamily();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleFamily newValue() {
			xbean.RoleFamily value = xbean.Pod.newRoleFamily();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.EctypeSingle> ectypesingle = new xdb.TTable<Integer, xbean.EctypeSingle>() {
		@Override
		public String getName() {
			return "ectypesingle";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.EctypeSingle value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.EctypeSingle unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.EctypeSingle value = xbean.Pod.newEctypeSingle();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.EctypeSingle newValue() {
			xbean.EctypeSingle value = xbean.Pod.newEctypeSingle();
			return value;
		}

	};

	xdb.TTable<Long, xbean.BoardRecord> leaderboardrecord = new xdb.TTable<Long, xbean.BoardRecord>() {
		@Override
		public String getName() {
			return "leaderboardrecord";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.BoardRecord value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.BoardRecord unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.BoardRecord value = xbean.Pod.newBoardRecord();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.BoardRecord newValue() {
			xbean.BoardRecord value = xbean.Pod.newBoardRecord();
			return value;
		}

	};

	xdb.TTable<Long, xbean.SystemMail> systemmailbox = new xdb.TTable<Long, xbean.SystemMail>() {
		@Override
		public String getName() {
			return "systemmailbox";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.SystemMail value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.SystemMail unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.SystemMail value = xbean.Pod.newSystemMail();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.SystemMail newValue() {
			xbean.SystemMail value = xbean.Pod.newSystemMail();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleFriendsInfo> rolefriendsinfo = new xdb.TTable<Long, xbean.RoleFriendsInfo>() {
		@Override
		public String getName() {
			return "rolefriendsinfo";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleFriendsInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleFriendsInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleFriendsInfo value = xbean.Pod.newRoleFriendsInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleFriendsInfo newValue() {
			xbean.RoleFriendsInfo value = xbean.Pod.newRoleFriendsInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleAchievement> roleachievement = new xdb.TTable<Long, xbean.RoleAchievement>() {
		@Override
		public String getName() {
			return "roleachievement";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleAchievement value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleAchievement unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleAchievement value = xbean.Pod.newRoleAchievement();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleAchievement newValue() {
			xbean.RoleAchievement value = xbean.Pod.newRoleAchievement();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleEctype> roleectypes = new xdb.TTable<Long, xbean.RoleEctype>() {
		@Override
		public String getName() {
			return "roleectypes";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleEctype value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleEctype unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleEctype value = xbean.Pod.newRoleEctype();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleEctype newValue() {
			xbean.RoleEctype value = xbean.Pod.newRoleEctype();
			return value;
		}

	};

	xdb.TTable<String, Long> rolename2ids = new xdb.TTable<String, Long>() {
		@Override
		public String getName() {
			return "rolename2ids";
		}

		@Override
		public OctetsStream marshalKey(String key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(Long value) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(value);
			return _os_;
		}

		@Override
		public String unmarshalKey(OctetsStream _os_) throws MarshalException {
			String key = "";
			key = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return key;
		}

		@Override
		public Long unmarshalValue(OctetsStream _os_) throws MarshalException {
			long value = 0;
			value = _os_.unmarshal_long();
			return value;
		}

		@Override
		public Long newValue() {
			long value = 0;
			return value;
		}

	};

	xdb.TTable<Long, xbean.UserDeletedRole> userdeletedroles = new xdb.TTable<Long, xbean.UserDeletedRole>() {
		@Override
		public String getName() {
			return "userdeletedroles";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.UserDeletedRole value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.UserDeletedRole unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.UserDeletedRole value = xbean.Pod.newUserDeletedRole();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.UserDeletedRole newValue() {
			xbean.UserDeletedRole value = xbean.Pod.newUserDeletedRole();
			return value;
		}

	};

	xdb.TTable<Long, xbean.IdGen> idgen = new xdb.TTable<Long, xbean.IdGen>() {
		@Override
		public String getName() {
			return "idgen";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.IdGen value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.IdGen unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.IdGen value = xbean.Pod.newIdGen();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.IdGen newValue() {
			xbean.IdGen value = xbean.Pod.newIdGen();
			return value;
		}

	};

	xdb.TTable<Long, xbean.ExchangeLog> exchangelog = new xdb.TTable<Long, xbean.ExchangeLog>() {
		@Override
		public String getName() {
			return "exchangelog";
		}

		@Override
		protected xdb.util.AutoKey<Long> bindAutoKey() {
			return getInstance().getTableSys().getAutoKeys().getAutoKeyLong(getName());
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.ExchangeLog value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.ExchangeLog unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.ExchangeLog value = xbean.Pod.newExchangeLog();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.ExchangeLog newValue() {
			xbean.ExchangeLog value = xbean.Pod.newExchangeLog();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleWelfareInfo> rolewelfareinfo = new xdb.TTable<Long, xbean.RoleWelfareInfo>() {
		@Override
		public String getName() {
			return "rolewelfareinfo";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleWelfareInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleWelfareInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleWelfareInfo value = xbean.Pod.newRoleWelfareInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleWelfareInfo newValue() {
			xbean.RoleWelfareInfo value = xbean.Pod.newRoleWelfareInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleMap> rolemaps = new xdb.TTable<Long, xbean.RoleMap>() {
		@Override
		public String getName() {
			return "rolemaps";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleMap value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleMap unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleMap value = xbean.Pod.newRoleMap();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleMap newValue() {
			xbean.RoleMap value = xbean.Pod.newRoleMap();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleFragmentDepot> rolefragmentdepot = new xdb.TTable<Long, xbean.RoleFragmentDepot>() {
		@Override
		public String getName() {
			return "rolefragmentdepot";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleFragmentDepot value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleFragmentDepot unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleFragmentDepot value = xbean.Pod.newRoleFragmentDepot();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleFragmentDepot newValue() {
			xbean.RoleFragmentDepot value = xbean.Pod.newRoleFragmentDepot();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.HuiWuCurTerm> huiwucurterms = new xdb.TTable<Integer, xbean.HuiWuCurTerm>() {
		@Override
		public String getName() {
			return "huiwucurterms";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.HuiWuCurTerm value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.HuiWuCurTerm unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.HuiWuCurTerm value = xbean.Pod.newHuiWuCurTerm();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.HuiWuCurTerm newValue() {
			xbean.HuiWuCurTerm value = xbean.Pod.newHuiWuCurTerm();
			return value;
		}

	};

	xdb.TTable<Long, xbean.User> users = new xdb.TTable<Long, xbean.User>() {
		@Override
		public String getName() {
			return "users";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.User value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.User unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.User value = xbean.Pod.newUser();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.User newValue() {
			xbean.User value = xbean.Pod.newUser();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.HuiWuPriviousTerm> huiwupriviousterms = new xdb.TTable<Integer, xbean.HuiWuPriviousTerm>() {
		@Override
		public String getName() {
			return "huiwupriviousterms";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.HuiWuPriviousTerm value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.HuiWuPriviousTerm unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.HuiWuPriviousTerm value = xbean.Pod.newHuiWuPriviousTerm();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.HuiWuPriviousTerm newValue() {
			xbean.HuiWuPriviousTerm value = xbean.Pod.newHuiWuPriviousTerm();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.ARank> ranks = new xdb.TTable<Integer, xbean.ARank>() {
		@Override
		public String getName() {
			return "ranks";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.ARank value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.ARank unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.ARank value = xbean.Pod.newARank();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.ARank newValue() {
			xbean.ARank value = xbean.Pod.newARank();
			return value;
		}

	};

	xdb.TTable<Long, xbean.MailBox> mailbox = new xdb.TTable<Long, xbean.MailBox>() {
		@Override
		public String getName() {
			return "mailbox";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.MailBox value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.MailBox unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.MailBox value = xbean.Pod.newMailBox();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.MailBox newValue() {
			xbean.MailBox value = xbean.Pod.newMailBox();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.System> system = new xdb.TTable<Integer, xbean.System>() {
		@Override
		public String getName() {
			return "system";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.System value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.System unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.System value = xbean.Pod.newSystem();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.System newValue() {
			xbean.System value = xbean.Pod.newSystem();
			return value;
		}

	};

	xdb.TTable<Long, Long> goldcoindepot = new xdb.TTable<Long, Long>() {
		@Override
		public String getName() {
			return "goldcoindepot";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(Long value) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(value);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public Long unmarshalValue(OctetsStream _os_) throws MarshalException {
			long value = 0;
			value = _os_.unmarshal_long();
			return value;
		}

		@Override
		public Long newValue() {
			long value = 0;
			return value;
		}

	};

	xdb.TTable<Long, xbean.PickCardInfo> pickcardinfos = new xdb.TTable<Long, xbean.PickCardInfo>() {
		@Override
		public String getName() {
			return "pickcardinfos";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.PickCardInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.PickCardInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.PickCardInfo value = xbean.Pod.newPickCardInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.PickCardInfo newValue() {
			xbean.PickCardInfo value = xbean.Pod.newPickCardInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleMemInfo> rolememinfos = new xdb.TTable<Long, xbean.RoleMemInfo>() {
		@Override
		public String getName() {
			return "rolememinfos";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleMemInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleMemInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleMemInfo value = xbean.Pod.newRoleMemInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleMemInfo newValue() {
			xbean.RoleMemInfo value = xbean.Pod.newRoleMemInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.AppOrder> processingorders = new xdb.TTable<Long, xbean.AppOrder>() {
		@Override
		public String getName() {
			return "processingorders";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.AppOrder value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.AppOrder unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.AppOrder value = xbean.Pod.newAppOrder();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.AppOrder newValue() {
			xbean.AppOrder value = xbean.Pod.newAppOrder();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleLimit> rolelimit = new xdb.TTable<Long, xbean.RoleLimit>() {
		@Override
		public String getName() {
			return "rolelimit";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleLimit value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleLimit unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleLimit value = xbean.Pod.newRoleLimit();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleLimit newValue() {
			xbean.RoleLimit value = xbean.Pod.newRoleLimit();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleFragmentBag> rolefragmentbag = new xdb.TTable<Long, xbean.RoleFragmentBag>() {
		@Override
		public String getName() {
			return "rolefragmentbag";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleFragmentBag value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleFragmentBag unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleFragmentBag value = xbean.Pod.newRoleFragmentBag();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleFragmentBag newValue() {
			xbean.RoleFragmentBag value = xbean.Pod.newRoleFragmentBag();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleItemDepot> roleitemdepot = new xdb.TTable<Long, xbean.RoleItemDepot>() {
		@Override
		public String getName() {
			return "roleitemdepot";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleItemDepot value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleItemDepot unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleItemDepot value = xbean.Pod.newRoleItemDepot();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleItemDepot newValue() {
			xbean.RoleItemDepot value = xbean.Pod.newRoleItemDepot();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.GlobalAllActivity> globallactivity = new xdb.TTable<Integer, xbean.GlobalAllActivity>() {
		@Override
		public String getName() {
			return "globallactivity";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.GlobalAllActivity value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.GlobalAllActivity unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.GlobalAllActivity value = xbean.Pod.newGlobalAllActivity();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.GlobalAllActivity newValue() {
			xbean.GlobalAllActivity value = xbean.Pod.newGlobalAllActivity();
			return value;
		}

	};

	xdb.TTable<Long, xbean.DailyResetData> roledailyresetdata = new xdb.TTable<Long, xbean.DailyResetData>() {
		@Override
		public String getName() {
			return "roledailyresetdata";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.DailyResetData value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.DailyResetData unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.DailyResetData value = xbean.Pod.newDailyResetData();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.DailyResetData newValue() {
			xbean.DailyResetData value = xbean.Pod.newDailyResetData();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleExchange> roleexchange = new xdb.TTable<Long, xbean.RoleExchange>() {
		@Override
		public String getName() {
			return "roleexchange";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleExchange value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleExchange unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleExchange value = xbean.Pod.newRoleExchange();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleExchange newValue() {
			xbean.RoleExchange value = xbean.Pod.newRoleExchange();
			return value;
		}

	};

	xdb.TTable<Long, xbean.Forbid> forbids = new xdb.TTable<Long, xbean.Forbid>() {
		@Override
		public String getName() {
			return "forbids";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.Forbid value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.Forbid unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.Forbid value = xbean.Pod.newForbid();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.Forbid newValue() {
			xbean.Forbid value = xbean.Pod.newForbid();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleStoryNote> rolestorynotes = new xdb.TTable<Long, xbean.RoleStoryNote>() {
		@Override
		public String getName() {
			return "rolestorynotes";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleStoryNote value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleStoryNote unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleStoryNote value = xbean.Pod.newRoleStoryNote();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleStoryNote newValue() {
			xbean.RoleStoryNote value = xbean.Pod.newRoleStoryNote();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleJadeInfo> jade = new xdb.TTable<Long, xbean.RoleJadeInfo>() {
		@Override
		public String getName() {
			return "jade";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleJadeInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleJadeInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleJadeInfo value = xbean.Pod.newRoleJadeInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleJadeInfo newValue() {
			xbean.RoleJadeInfo value = xbean.Pod.newRoleJadeInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.OnlineUser> onlineusers = new xdb.TTable<Long, xbean.OnlineUser>() {
		@Override
		public String getName() {
			return "onlineusers";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.OnlineUser value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.OnlineUser unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.OnlineUser value = xbean.Pod.newOnlineUser();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.OnlineUser newValue() {
			xbean.OnlineUser value = xbean.Pod.newOnlineUser();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleTitle> title = new xdb.TTable<Long, xbean.RoleTitle>() {
		@Override
		public String getName() {
			return "title";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleTitle value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleTitle unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleTitle value = xbean.Pod.newRoleTitle();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleTitle newValue() {
			xbean.RoleTitle value = xbean.Pod.newRoleTitle();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleRide> ride = new xdb.TTable<Long, xbean.RoleRide>() {
		@Override
		public String getName() {
			return "ride";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleRide value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleRide unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleRide value = xbean.Pod.newRoleRide();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleRide newValue() {
			xbean.RoleRide value = xbean.Pod.newRoleRide();
			return value;
		}

	};

	xdb.TTable<Long, xbean.DailyActive> dailyactives = new xdb.TTable<Long, xbean.DailyActive>() {
		@Override
		public String getName() {
			return "dailyactives";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.DailyActive value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.DailyActive unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.DailyActive value = xbean.Pod.newDailyActive();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.DailyActive newValue() {
			xbean.DailyActive value = xbean.Pod.newDailyActive();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleAttr> roleattrs = new xdb.TTable<Long, xbean.RoleAttr>() {
		@Override
		public String getName() {
			return "roleattrs";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleAttr value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleAttr unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleAttr value = xbean.Pod.newRoleAttr();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleAttr newValue() {
			xbean.RoleAttr value = xbean.Pod.newRoleAttr();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleTask> roletask = new xdb.TTable<Long, xbean.RoleTask>() {
		@Override
		public String getName() {
			return "roletask";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleTask value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleTask unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleTask value = xbean.Pod.newRoleTask();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleTask newValue() {
			xbean.RoleTask value = xbean.Pod.newRoleTask();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleConfigure> roleconfigure = new xdb.TTable<Long, xbean.RoleConfigure>() {
		@Override
		public String getName() {
			return "roleconfigure";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleConfigure value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleConfigure unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleConfigure value = xbean.Pod.newRoleConfigure();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleConfigure newValue() {
			xbean.RoleConfigure value = xbean.Pod.newRoleConfigure();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleTeamInfo> role2team = new xdb.TTable<Long, xbean.RoleTeamInfo>() {
		@Override
		public String getName() {
			return "role2team";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleTeamInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleTeamInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleTeamInfo value = xbean.Pod.newRoleTeamInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleTeamInfo newValue() {
			xbean.RoleTeamInfo value = xbean.Pod.newRoleTeamInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.IdolCharmInfo> idolcharm = new xdb.TTable<Long, xbean.IdolCharmInfo>() {
		@Override
		public String getName() {
			return "idolcharm";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.IdolCharmInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.IdolCharmInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.IdolCharmInfo value = xbean.Pod.newIdolCharmInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.IdolCharmInfo newValue() {
			xbean.IdolCharmInfo value = xbean.Pod.newIdolCharmInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleItemBag> roleitembag = new xdb.TTable<Long, xbean.RoleItemBag>() {
		@Override
		public String getName() {
			return "roleitembag";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleItemBag value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleItemBag unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleItemBag value = xbean.Pod.newRoleItemBag();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleItemBag newValue() {
			xbean.RoleItemBag value = xbean.Pod.newRoleItemBag();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.GMSenseword> gmsenseword = new xdb.TTable<Integer, xbean.GMSenseword>() {
		@Override
		public String getName() {
			return "gmsenseword";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.GMSenseword value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.GMSenseword unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.GMSenseword value = xbean.Pod.newGMSenseword();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.GMSenseword newValue() {
			xbean.GMSenseword value = xbean.Pod.newGMSenseword();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleInfo> roleinfos = new xdb.TTable<Long, xbean.RoleInfo>() {
		@Override
		public String getName() {
			return "roleinfos";
		}

		@Override
		protected xdb.util.AutoKey<Long> bindAutoKey() {
			return getInstance().getTableSys().getAutoKeys().getAutoKeyLong(getName());
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleInfo value = xbean.Pod.newRoleInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleInfo newValue() {
			xbean.RoleInfo value = xbean.Pod.newRoleInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleTalismanBag> roletalismanbag = new xdb.TTable<Long, xbean.RoleTalismanBag>() {
		@Override
		public String getName() {
			return "roletalismanbag";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleTalismanBag value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleTalismanBag unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleTalismanBag value = xbean.Pod.newRoleTalismanBag();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleTalismanBag newValue() {
			xbean.RoleTalismanBag value = xbean.Pod.newRoleTalismanBag();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleActiveCode> roleactivecodes = new xdb.TTable<Long, xbean.RoleActiveCode>() {
		@Override
		public String getName() {
			return "roleactivecodes";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleActiveCode value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleActiveCode unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleActiveCode value = xbean.Pod.newRoleActiveCode();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleActiveCode newValue() {
			xbean.RoleActiveCode value = xbean.Pod.newRoleActiveCode();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RolePay> rolepays = new xdb.TTable<Long, xbean.RolePay>() {
		@Override
		public String getName() {
			return "rolepays";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RolePay value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RolePay unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RolePay value = xbean.Pod.newRolePay();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RolePay newValue() {
			xbean.RolePay value = xbean.Pod.newRolePay();
			return value;
		}

	};

	xdb.TTable<Long, xbean.Team> team = new xdb.TTable<Long, xbean.Team>() {
		@Override
		public String getName() {
			return "team";
		}

		@Override
		protected xdb.util.AutoKey<Long> bindAutoKey() {
			return getInstance().getTableSys().getAutoKeys().getAutoKeyLong(getName());
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.Team value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.Team unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.Team value = xbean.Pod.newTeam();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.Team newValue() {
			xbean.Team value = xbean.Pod.newTeam();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleChat> rolechat = new xdb.TTable<Long, xbean.RoleChat>() {
		@Override
		public String getName() {
			return "rolechat";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleChat value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleChat unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleChat value = xbean.Pod.newRoleChat();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleChat newValue() {
			xbean.RoleChat value = xbean.Pod.newRoleChat();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleEquipDepot> roleequipdepot = new xdb.TTable<Long, xbean.RoleEquipDepot>() {
		@Override
		public String getName() {
			return "roleequipdepot";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleEquipDepot value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleEquipDepot unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleEquipDepot value = xbean.Pod.newRoleEquipDepot();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleEquipDepot newValue() {
			xbean.RoleEquipDepot value = xbean.Pod.newRoleEquipDepot();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleAllActivity> roleallactivity = new xdb.TTable<Long, xbean.RoleAllActivity>() {
		@Override
		public String getName() {
			return "roleallactivity";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleAllActivity value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleAllActivity unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleAllActivity value = xbean.Pod.newRoleAllActivity();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleAllActivity newValue() {
			xbean.RoleAllActivity value = xbean.Pod.newRoleAllActivity();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleNotice> rolenotice = new xdb.TTable<Long, xbean.RoleNotice>() {
		@Override
		public String getName() {
			return "rolenotice";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleNotice value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleNotice unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleNotice value = xbean.Pod.newRoleNotice();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleNotice newValue() {
			xbean.RoleNotice value = xbean.Pod.newRoleNotice();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RoleArena> rolearena = new xdb.TTable<Long, xbean.RoleArena>() {
		@Override
		public String getName() {
			return "rolearena";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RoleArena value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RoleArena unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RoleArena value = xbean.Pod.newRoleArena();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RoleArena newValue() {
			xbean.RoleArena value = xbean.Pod.newRoleArena();
			return value;
		}

	};

	xdb.TTable<Long, xbean.IdList> familyextrinfo = new xdb.TTable<Long, xbean.IdList>() {
		@Override
		public String getName() {
			return "familyextrinfo";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.IdList value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.IdList unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.IdList value = xbean.Pod.newIdList();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.IdList newValue() {
			xbean.IdList value = xbean.Pod.newIdList();
			return value;
		}

	};

	xdb.TTable<Long, xbean.ExchangeItem> exchangeitem = new xdb.TTable<Long, xbean.ExchangeItem>() {
		@Override
		public String getName() {
			return "exchangeitem";
		}

		@Override
		protected xdb.util.AutoKey<Long> bindAutoKey() {
			return getInstance().getTableSys().getAutoKeys().getAutoKeyLong(getName());
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.ExchangeItem value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.ExchangeItem unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.ExchangeItem value = xbean.Pod.newExchangeItem();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.ExchangeItem newValue() {
			xbean.ExchangeItem value = xbean.Pod.newExchangeItem();
			return value;
		}

	};

	xdb.TTable<Long, xbean.RolePet> rolepet = new xdb.TTable<Long, xbean.RolePet>() {
		@Override
		public String getName() {
			return "rolepet";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RolePet value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.RolePet unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RolePet value = xbean.Pod.newRolePet();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RolePet newValue() {
			xbean.RolePet value = xbean.Pod.newRolePet();
			return value;
		}

	};

	xdb.TTable<Long, xbean.Family> family = new xdb.TTable<Long, xbean.Family>() {
		@Override
		public String getName() {
			return "family";
		}

		@Override
		protected xdb.util.AutoKey<Long> bindAutoKey() {
			return getInstance().getTableSys().getAutoKeys().getAutoKeyLong(getName());
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.Family value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.Family unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.Family value = xbean.Pod.newFamily();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.Family newValue() {
			xbean.Family value = xbean.Pod.newFamily();
			return value;
		}

	};


}
