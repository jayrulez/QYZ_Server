package xtable;


public class _DatabaseMetaData_ extends xdb.util.DatabaseMetaData {
	@Override
	public boolean isVerifyXdb() {
		return true;
	}
	public void DatabaseMetaData1(){
		// xbeans
		{
			Bean bean = new Bean("IntList", false, false);
			super.addVariableFor(bean
				, 1
				, "val"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("LongList", false, false);
			super.addVariableFor(bean
				, 1
				, "val"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("IntSet", false, false);
			super.addVariableFor(bean
				, 1
				, "val"
				, "set", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("LongSet", false, false);
			super.addVariableFor(bean
				, 1
				, "val"
				, "set", "", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("User", false, false);
			super.addVariableFor(bean
				, 1
				, "roleids"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "lastloginrole"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "deletedroles"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "deleteinfo"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("OnlineUser", false, false);
			super.addVariableFor(bean
				, 1
				, "username"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "plattype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "deviceid"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "os"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "peer"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "logintime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "platform"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("DeletedRole", false, false);
			super.addVariableFor(bean
				, 1
				, "roleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "username"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "newusername"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "deletetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("UserDeletedRole", false, false);
			super.addVariableFor(bean
				, 1
				, "roles"
				, "list", "", "DeletedRole", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleMemInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "herogroupmeminfos"
				, "map", "int", "HeroesGroupMemInfo", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "userid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "profession"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 19
				, "createtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 34
				, "serverid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "lastlogintime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "totalonlinetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 41
				, "lastlvluptotalonlinetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 25
				, "lastlogouttime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 20
				, "logindaycount"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 42
				, "lastaddlogindaytime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 21
				, "continuesloginday"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 22
				, "totaldayonlinetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 23
				, "leftgifttimes"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 24
				, "monthcardlefttimes"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 26
				, "buymonthcardtimes"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 27
				, "buygrowplan"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 45
				, "growonetime"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 43
				, "buygrowplantwo"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 46
				, "growtwotime"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 44
				, "buygrowplanthree"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 47
				, "growthreetime"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "name"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 36
				, "changenametime"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 12
				, "level"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 35
				, "lastlvluptime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 14
				, "viplevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 18
				, "vipexp"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 16
				, "currencys"
				, "map", "int", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 28
				, "gender"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 29
				, "totalcostyuanbao"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 32
				, "lastaddtilitime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 37
				, "lastworldtalktime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 38
				, "silentendtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 39
				, "leftreporttime"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 40
				, "bereportedtime"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleConfigure", false, false);
			super.addVariableFor(bean
				, 1
				, "datas"
				, "map", "string", "string", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("TimeRange", false, false);
			super.addVariableFor(bean
				, 2
				, "opentime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "closetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("GlobalActivityOpenInfos", false, false);
			super.addVariableFor(bean
				, 1
				, "infos"
				, "list", "", "TimeRange", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "enable"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("GlobalActivity", false, false);
			super.addVariableFor(bean
				, 1
				, "timestamp"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("GlobalAllActivity", false, false);
			super.addVariableFor(bean
				, 1
				, "openinfos"
				, "map", "int", "GlobalActivityOpenInfos", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "datas"
				, "map", "int", "GlobalActivity", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleActivityStatus", false, false);
			super.addVariableFor(bean
				, 1
				, "timestamp"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "status"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleActivityRecord", false, false);
			super.addVariableFor(bean
				, 1
				, "totalcostyuanbbao"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "killworldboss"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "arenawin"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "teamspeedwin"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "teamfightwin"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleActivityRecordMap", false, false);
			super.addVariableFor(bean
				, 1
				, "records"
				, "map", "long", "RoleActivityRecord", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleAllActivity", false, false);
			super.addVariableFor(bean
				, 1
				, "status"
				, "map", "int", "RoleActivityStatus", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "records"
				, "map", "int", "RoleActivityRecordMap", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("IdGen", false, false);
			super.addVariableFor(bean
				, 1
				, "itemid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Forbid", false, false);
			super.addVariableFor(bean
				, 4
				, "items"
				, "map", "int", "ForbidItem", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ForbidItem", false, false);
			super.addVariableFor(bean
				, 1
				, "forbidtimeinterval"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "forbidrealsetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "desc"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "notifytouser"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("GroupAttr", false, false);
			super.addVariableFor(bean
				, 1
				, "attrs"
				, "map", "int", "float", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleAttr", false, false);
			super.addVariableFor(bean
				, 3
				, "roleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "rolecombatpower"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "rawattrs"
				, "list", "", "float", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "finalattrs"
				, "list", "", "float", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "groupattrs"
				, "map", "string", "GroupAttr", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "hp"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "mp"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "skillcolddowns"
				, "map", "int", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "resethpmp"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "petcombatpower"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 11
				, "totalcombatpower"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Vector3", false, false);
			super.addVariableFor(bean
				, 1
				, "x"
				, "float", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "y"
				, "float", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "z"
				, "float", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleMapInfo", false, false);
			super.addVariableFor(bean
				, 6
				, "mapid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "position"
				, "Vector3", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "orient"
				, "Vector3", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "ridestatus"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleMap", false, false);
			super.addVariableFor(bean
				, 2
				, "pkstates"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "finishprologue"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "hp"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "mp"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "prevs"
				, "list", "", "RoleMapInfo", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "isnew"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RandomBonus", false, false);
			super.addVariableFor(bean
				, 1
				, "bindtype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "items"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("HeroesGroupMemInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "ectypeid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "randomtime"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "bonus"
				, "list", "", "RandomBonus", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("HeroesGroupInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "refreshtime"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "ectypeid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ClimbTowerInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "maxfloorid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "costtime"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ChapterInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "sectionstars"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "obtainrewardindexs"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("DailyInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "value"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("TeamFightInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "lastupdatetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "weekscore"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "todaywinnum"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "obtaintodaywinreward"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "todayfightnum"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "obtainscorerewards"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleEctype", false, false);
			super.addVariableFor(bean
				, 1
				, "climbtowers"
				, "map", "int", "ClimbTowerInfo", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "chapters"
				, "map", "int", "ChapterInfo", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "dailys"
				, "map", "int", "DailyInfo", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "multistory"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "herogroups"
				, "map", "int", "HeroesGroupInfo", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 11
				, "teamfight"
				, "TeamFightInfo", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 14
				, "matchtype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 13
				, "nextmatchtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 15
				, "multiectypid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleChat", false, false);
			super.addVariableFor(bean
				, 1
				, "chatface"
				, "set", "", "string", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("DailyEctypeRecord", false, false);
			super.addVariableFor(bean
				, 1
				, "roleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "value"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("EctypeSingle", false, false);
			super.addVariableFor(bean
				, 1
				, "dailyectypebestrecords"
				, "map", "int", "DailyEctypeRecord", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("AppOrder", false, false);
			super.addVariableFor(bean
				, 1
				, "roleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "productid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "time"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleOrderHistroy", false, false);
			super.addVariableFor(bean
				, 1
				, "succeedorder"
				, "map", "long", "AppOrder", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "timeoutorder"
				, "map", "long", "AppOrder", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RolePay", false, false);
			super.addVariableFor(bean
				, 10
				, "cangetfirstbonus"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "isfirstpayused"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "isentryfirstpay"
				, "map", "int", "boolean", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "totalpay"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "lastrefreshtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "dailypaystatus"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "dailytotalpay"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "dailytotalpaystatus"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 11
				, "roleallpay"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 17
				, "dailyactivepay"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 12
				, "hasgotpayreturn"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 14
				, "totalyunbao"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 15
				, "totalbindyuanbao"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 16
				, "totalvipexp"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleFriend", false, false);
			super.addVariableFor(bean
				, 1
				, "frienddegress"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "relation"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "time"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("IdolAwardClaim", false, false);
			super.addVariableFor(bean
				, 1
				, "claiminfo"
				, "set", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleRelation", false, false);
			super.addVariableFor(bean
				, 1
				, "rolelist"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Enemyinfo", false, false);
			super.addVariableFor(bean
				, 1
				, "bekillnum"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "killnum"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "lastbekilltime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleFriendsInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "friends"
				, "map", "long", "RoleFriend", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "requesting"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "blacklist"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "charmdegree"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "idolfrienddegree"
				, "map", "long", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "idolawardclaiminfo"
				, "map", "long", "IdolAwardClaim", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 13
				, "enemylist"
				, "map", "long", "Enemyinfo", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 14
				, "isallowfriendgetmm"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 15
				, "isallowstrangergetmm"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 16
				, "relationinfo"
				, "map", "int", "RoleRelation", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("IdolCharmInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "charm"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "guardid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "guarddegree"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "guardtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleMarriage", false, false);
			super.addVariableFor(bean
				, 1
				, "coupleroleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "curproposeid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "startproposetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "wishfriendlist"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Item", false, false);
			super.addVariableFor(bean
				, 1
				, "itemid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "modelid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "count"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "isbind"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "position"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "expiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleItemBag", false, false);
			super.addVariableFor(bean
				, 1
				, "capacity"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "items"
				, "map", "int", "Item", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Equip", false, false);
			super.addVariableFor(bean
				, 1
				, "equipid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "modelid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "position"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "expiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "isbind"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "normalequip"
				, "NormalEquip", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "accessory"
				, "Accessory", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("NormalEquip", false, false);
			super.addVariableFor(bean
				, 1
				, "anneallevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "perfuselevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "goldcost"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "annealitemcost"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "perfuseitemcost"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Accessory", false, false);
			super.addVariableFor(bean
				, 1
				, "mainprop"
				, "list", "", "AccessoryProp", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "extraprop"
				, "list", "", "AccessoryProp", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "lastwashrecord"
				, "AccessoryWashResult", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("AccessoryProp", false, false);
			super.addVariableFor(bean
				, 1
				, "key"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "val"
				, "float", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("AccessoryWashResult", false, false);
			super.addVariableFor(bean
				, 1
				, "oldpropindex"
				, "int", "", "", ""
				, "-1", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "newprop"
				, "AccessoryProp", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "needbind"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleEquipBag", false, false);
			super.addVariableFor(bean
				, 1
				, "capacity"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "equipmap"
				, "map", "int", "Equip", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "equiponbodymap"
				, "map", "int", "Equip", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("AdvancedEquip", false, false);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Fragment", false, false);
			super.addVariableFor(bean
				, 1
				, "fragmentid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "modelid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "count"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "isbind"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "position"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "expiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleFragmentBag", false, false);
			super.addVariableFor(bean
				, 1
				, "capacity"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "items"
				, "map", "int", "Fragment", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Talisman", false, false);
			super.addVariableFor(bean
				, 1
				, "talismanid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "modelid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "pos"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "isbind"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "expiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "normalexp"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "normallevel"
				, "int", "", "", ""
				, "1", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "starexp"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "starlevel"
				, "int", "", "", ""
				, "1", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "wuxingtype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 11
				, "wuxingvalue"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 12
				, "awakelevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 13
				, "skills"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleTalismanBag", false, false);
			super.addVariableFor(bean
				, 1
				, "capacity"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "talismans"
				, "map", "int", "Talisman", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "equipedtalismans"
				, "map", "int", "Talisman", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "luckytype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "luckywashtimes"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "maxcombatpower"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("AmuletProperty", false, false);
			super.addVariableFor(bean
				, 1
				, "propindex"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "islock"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "skillid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "professionid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "addlevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("AmuletPage", false, false);
			super.addVariableFor(bean
				, 1
				, "pageindex"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "propmap"
				, "map", "int", "AmuletProperty", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "lastwashresult"
				, "map", "int", "AmuletProperty", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleAmuletInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "pagemap"
				, "map", "int", "AmuletPage", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Jewelry", false, false);
			super.addVariableFor(bean
				, 1
				, "id"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "level"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "exp"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleJadeInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "level"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "bonus"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "holenum"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "jewelry"
				, "map", "int", "Jewelry", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "jewelrybag"
				, "vector", "", "Jewelry", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "jewelrygetlevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Pet", false, false);
			super.addVariableFor(bean
				, 2
				, "modelid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "activeskinid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "skinidlist"
				, "set", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "level"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 15
				, "exp"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "starlevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "skills"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "awakelevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "fixedattrs"
				, "map", "int", "float", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 11
				, "karmaattrs"
				, "map", "int", "float", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 12
				, "growthattrs"
				, "map", "int", "float", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 13
				, "buffids"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 14
				, "lastwashrecord"
				, "map", "int", "float", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 16
				, "washattrs"
				, "map", "int", "float", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RolePet", false, false);
			super.addVariableFor(bean
				, 1
				, "petmap"
				, "map", "int", "Pet", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "fightpets"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "activepetmodelid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "petfragmentmap"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "totalcombatpower"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleEquipDepot", false, false);
			super.addVariableFor(bean
				, 1
				, "capacity"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "equipbag"
				, "treemap", "int", "Equip", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleFragmentDepot", false, false);
			super.addVariableFor(bean
				, 1
				, "capacity"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "fragments"
				, "map", "int", "Fragment", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleItemDepot", false, false);
			super.addVariableFor(bean
				, 1
				, "capacity"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "items"
				, "map", "int", "Item", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleTalismanDepot", false, false);
			super.addVariableFor(bean
				, 1
				, "capacity"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "talismans"
				, "map", "int", "Talisman", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Dress", false, false);
			super.addVariableFor(bean
				, 1
				, "modelid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "expiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleDress", false, false);
			super.addVariableFor(bean
				, 1
				, "dresses"
				, "map", "int", "Dress", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "activedress"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Ride", false, false);
			super.addVariableFor(bean
				, 1
				, "modelid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "expiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleRide", false, false);
			super.addVariableFor(bean
				, 1
				, "rides"
				, "map", "int", "Ride", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "activeride"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ARankInfo", false, false);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleRank", false, false);
			super.addVariableFor(bean
				, 1
				, "ranks"
				, "map", "int", "ARankInfo", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RankRecord", false, false);
			super.addVariableFor(bean
				, 1
				, "value"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "id"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ARank", false, false);
			super.addVariableFor(bean
				, 1
				, "id2rank"
				, "map", "long", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "records"
				, "map", "int", "RankRecord", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "createtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("BoardEntry", false, false);
			super.addVariableFor(bean
				, 1
				, "ranking"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "id"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "name"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "val1"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "val2"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "updatetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("BoardInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "latestboard"
				, "map", "int", "BoardEntry", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "rolerank"
				, "map", "long", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "lastupdatetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "yesterdayrank"
				, "map", "long", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("BoardRecordEntry", false, false);
			super.addVariableFor(bean
				, 1
				, "val1"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "val2"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "updatetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("BoardRecord", false, false);
			super.addVariableFor(bean
				, 1
				, "records"
				, "map", "int", "BoardRecordEntry", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("TaskHistory", false, false);
			super.addVariableFor(bean
				, 3
				, "taskid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "count"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "expiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("TaskData", false, false);
			super.addVariableFor(bean
				, 2
				, "taskid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "counter"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("FamilyTaskDetail", false, false);
			super.addVariableFor(bean
				, 1
				, "taskid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "npcid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleTask", false, false);
			super.addVariableFor(bean
				, 6
				, "roleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "minhistoryexpiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "historys"
				, "map", "int", "TaskHistory", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 15
				, "lastcompletetaskid"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 16
				, "acceptbranchtasks"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 17
				, "shownpcs"
				, "set", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 20
				, "hidemines"
				, "set", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "tasks"
				, "map", "int", "TaskData", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "variables"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "acceptedfamilytasks"
				, "vector", "", "FamilyTaskDetail", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "completednumsinfamtasks"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 13
				, "isuseyuanbao"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 18
				, "iscancletask"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "dailycompletedfamtasks"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 11
				, "weekcompletedsmallfamtasks"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 14
				, "weekspebonus"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 12
				, "lastgiveupfamtasktime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 19
				, "guidebranchtaskid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 23
				, "accepttasktime"
				, "map", "int", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 21
				, "familytaskorder"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 22
				, "familylasttaskorder"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 24
				, "allcandobranch"
				, "set", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Bonus", false, false);
			super.addVariableFor(bean
				, 0
				, "bindtype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "currencys"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "items"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "equips"
				, "list", "", "Equip", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Mail", false, false);
			super.addVariableFor(bean
				, 7
				, "mailid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "sendtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 0
				, "title"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "content"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "read"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "accessory"
				, "Bonus", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "params"
				, "list", "", "string", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("MailBox", false, false);
			super.addVariableFor(bean
				, 0
				, "nextmailid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "mails"
				, "map", "int", "Mail", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "maxsysmail"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("SystemMail", false, false);
			super.addVariableFor(bean
				, 1
				, "id"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "mailid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "sendtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "title"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "content"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "bonus"
				, "Bonus", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "params"
				, "list", "", "string", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "records"
				, "set", "", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleWelfareInfo", false, false);
			super.addVariableFor(bean
				, 14
				, "lastsigntime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "totalsigncount"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "continuesigncount"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "signdate"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "hastodaysign"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "receivedmonthcarddate"
				, "vector", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "receivedonlinegift"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "receivednewgift"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 19
				, "receiveconlogingift"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "wishtimes"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "lastwishtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 16
				, "lastwishpetid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "receivedgrowplangift"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 15
				, "receivedpaycharge"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 17
				, "iseatlunch"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 18
				, "iseatdinner"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 20
				, "threegrowplan"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 21
				, "fivegrowplan"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 22
				, "sevengrowplan"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("DailyActive", false, false);
			super.addVariableFor(bean
				, 1
				, "activetimes"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "receivedbonus"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "activescores"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "undoactive"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "eventdonetimes"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "lastactivelvl"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("PickCardInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "lastfreehuoban"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "huobanhighyuanbao"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "huobanlowyuanbao"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "fabaoyuanbao"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "fabaofree"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "fabaoxunibi"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Limit", false, false);
			super.addVariableFor(bean
				, 0
				, "id"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "typenums"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "lastbuytime"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("CoolDown", false, false);
			super.addVariableFor(bean
				, 1
				, "id"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "expiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleLimit", false, false);
			super.addVariableFor(bean
				, 1
				, "limits"
				, "map", "long", "Limit", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "cooldowns"
				, "map", "long", "CoolDown", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("DailyArena", false, false);
			super.addVariableFor(bean
				, 1
				, "challengesuccnum"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "obtainrewards"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("DailyMonsterExp", false, false);
			super.addVariableFor(bean
				, 2
				, "todaytotaladdmonsterexp"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
	}
	public void DatabaseMetaData2(){
		{
			Bean bean = new Bean("DailyResetData", false, false);
			super.addVariableFor(bean
				, 1
				, "lastupdatedailytime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "arena"
				, "DailyArena", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "monsterexp"
				, "DailyMonsterExp", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "killexpmonsters"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "recexpmonbonus"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Notice", false, false);
			super.addVariableFor(bean
				, 1
				, "data"
				, "binary", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleNotice", false, false);
			super.addVariableFor(bean
				, 0
				, "notices"
				, "list", "", "Notice", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ArenaFightReport", false, false);
			super.addVariableFor(bean
				, 1
				, "fighttime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "challengetype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "succ"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "newrank"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "opponentname"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "oldrank"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleArena", false, false);
			super.addVariableFor(bean
				, 9
				, "open"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "isrobot"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "lastupdateshengwangtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "challengeranks"
				, "list", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 22
				, "challengerobots"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "bechallenging"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "bechallengelockexpiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "fightreports"
				, "list", "", "ArenaFightReport", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "bestrank"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 20
				, "haswin"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 21
				, "fakerank"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleExchange", false, false);
			super.addVariableFor(bean
				, 0
				, "items"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "logs"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ExchangeLog", false, false);
			super.addVariableFor(bean
				, 4
				, "seller"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "buyer"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "item"
				, "ExchangeItem", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "time"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ExchangeItem", false, false);
			super.addVariableFor(bean
				, 1
				, "id"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "owner"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "price"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "modelid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "num"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "expiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "anneallevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "perfuselevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "accessorymainprop"
				, "list", "", "AccessoryProp", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 11
				, "accessoryviceprop"
				, "list", "", "AccessoryProp", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 12
				, "unshelvetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleTeamInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "teamid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "autoacceptrequest"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "autoacceptinvite"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "requesttojoin"
				, "map", "long", "TeamMember", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("TeamMember", false, false);
			super.addVariableFor(bean
				, 1
				, "roleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "jointime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "follow"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "followtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Team", false, false);
			super.addVariableFor(bean
				, 1
				, "teamid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "leaderid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "createtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "members"
				, "map", "long", "TeamMember", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "requestforjoin"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "invitetojoin"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "invitefollow"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "leadertransroleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("SkillInfo", false, false);
			super.addVariableFor(bean
				, 0
				, "level"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleSkill", false, false);
			super.addVariableFor(bean
				, 0
				, "skills"
				, "map", "int", "SkillInfo", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "equipskillpositions"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Title", false, false);
			super.addVariableFor(bean
				, 1
				, "titlekey"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "titletype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "state"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "gettime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "activetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "expiretime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("GroupTitle", false, false);
			super.addVariableFor(bean
				, 1
				, "titles"
				, "map", "int", "Title", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleTitle", false, false);
			super.addVariableFor(bean
				, 1
				, "activekey"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "activetype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "titleinfo"
				, "map", "int", "GroupTitle", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("CounterSet", false, false);
			super.addVariableFor(bean
				, 0
				, "items"
				, "set", "", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleAchievement", false, false);
			super.addVariableFor(bean
				, 0
				, "achievementstates"
				, "map", "int", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "counters"
				, "map", "int", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "countersets"
				, "map", "int", "CounterSet", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("FamilyMember", false, false);
			super.addVariableFor(bean
				, 1
				, "jointime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "familyjob"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "roleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "personalbuild"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "dailybuild"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleFamily", false, false);
			super.addVariableFor(bean
				, 1
				, "currentfid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "requestedfamily"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "lastquittime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "totalquitnum"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "hasjoinpartytoday"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "isfindbackparty"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("FamilyLogReport", false, false);
			super.addVariableFor(bean
				, 1
				, "actiontype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "roleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "actiontime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "number"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("FamilyJobStaffList", false, false);
			super.addVariableFor(bean
				, 1
				, "staffs"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("FamilySkill", false, false);
			super.addVariableFor(bean
				, 1
				, "skillid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "studytime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "level"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "uptime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("FamilyWelfare", false, false);
			super.addVariableFor(bean
				, 1
				, "skills"
				, "map", "int", "FamilySkill", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "maxskilllevel"
				, "int", "", "", ""
				, "1", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("FamilyGodAnimal", false, false);
			super.addVariableFor(bean
				, 1
				, "animalid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "animallevel"
				, "int", "", "", ""
				, "1", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "exp"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("FamilyActivity", false, false);
			super.addVariableFor(bean
				, 1
				, "godanimalinfo"
				, "map", "int", "FamilyGodAnimal", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("FamilyGodAnimalAct", false, false);
			super.addVariableFor(bean
				, 1
				, "lastlaunchtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "starttime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "endtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "weeklaunchnum"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("Family", false, false);
			super.addVariableFor(bean
				, 1
				, "familyid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "familyname"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "flevel"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "money"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "curlvlbuilddegree"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 21
				, "totalbuilddegree"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 22
				, "totalbanggong"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "declaration"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "publicinfo"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "publictime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "malllevel"
				, "int", "", "", ""
				, "1", "", ""
				);
			super.addVariableFor(bean
				, 11
				, "logs"
				, "list", "", "FamilyLogReport", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 12
				, "requestinglist"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 13
				, "activity"
				, "FamilyActivity", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 20
				, "beatanimalactivity"
				, "FamilyGodAnimalAct", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 14
				, "welfare"
				, "FamilyWelfare", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 15
				, "chiefid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 16
				, "jobinfo"
				, "map", "int", "FamilyJobStaffList", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 17
				, "members"
				, "map", "long", "FamilyMember", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 18
				, "updatetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 19
				, "createtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 23
				, "lastpartyopentime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 24
				, "lastpartycalltime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 25
				, "issystemfam"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 26
				, "invitelist"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 27
				, "lastresettime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("IdList", false, false);
			super.addVariableFor(bean
				, 1
				, "lists"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("FNameToID", false, false);
			super.addVariableFor(bean
				, 1
				, "data"
				, "map", "string", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("HuiWuChampion", false, false);
			super.addVariableFor(bean
				, 3
				, "roleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "worshipnum"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "awardword"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("HuiWuPriviousTerm", false, false);
			super.addVariableFor(bean
				, 1
				, "termid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "opentime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "endtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "champions"
				, "map", "int", "HuiWuChampion", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("HuiWuBattle", false, false);
			super.addVariableFor(bean
				, 5
				, "index"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "roleid1"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "roleid2"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "state"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "mapid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("HuiWuRound", false, false);
			super.addVariableFor(bean
				, 1
				, "battles"
				, "list", "", "HuiWuBattle", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("HuiWuProfessionTerm", false, false);
			super.addVariableFor(bean
				, 7
				, "profession"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "enrollroleids"
				, "set", "", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "preselection1roleids"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "preselection2roleids"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "preselection2roleidbeguessnums"
				, "map", "long", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "rounds"
				, "map", "int", "HuiWuRound", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "champion"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("HuiWuCurTerm", false, false);
			super.addVariableFor(bean
				, 1
				, "termid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "opentime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "endtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "guessendtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "stage"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 12
				, "roundindex"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 13
				, "roundstage"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 14
				, "battlebegintime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 11
				, "terminfobyprofession"
				, "map", "int", "HuiWuProfessionTerm", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 9
				, "guess"
				, "map", "long", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 10
				, "continuouschampions"
				, "map", "long", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("StoryNoteChapter", false, false);
			super.addVariableFor(bean
				, 1
				, "notes"
				, "set", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleStoryNote", false, false);
			super.addVariableFor(bean
				, 1
				, "chapters"
				, "map", "int", "StoryNoteChapter", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("System", false, false);
			super.addVariableFor(bean
				, 1
				, "initrobot"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "outrankrobots"
				, "list", "", "long", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "gsdfirststarttime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "rolenumsreach20"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "maxsystemfamnum"
				, "int", "", "", ""
				, "200", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("GMSenseword", false, false);
			super.addVariableFor(bean
				, 1
				, "addwords"
				, "set", "", "string", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "removewords"
				, "set", "", "string", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ActiveCode", false, false);
			super.addVariableFor(bean
				, 1
				, "lastusetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "todayusecount"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "totalusecount"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RoleActiveCode", false, false);
			super.addVariableFor(bean
				, 1
				, "codegroups"
				, "map", "int", "ActiveCode", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		// cbeans
		// tables
		super.addTable("rolemarriageinfo", "DB", "long", false, "RoleMarriage", "", "");
		super.addTable("dress", "DB", "long", false, "RoleDress", "", "");
		super.addTable("roleskill", "DB", "long", false, "RoleSkill", "", "");
		super.addTable("rolerank", "DB", "long", false, "RoleRank", "", "");
		super.addTable("leaderboards", "DB", "int", false, "BoardInfo", "", "");
		super.addTable("familyname2id", "DB", "int", false, "FNameToID", "", "");
		super.addTable("amulet", "DB", "long", false, "RoleAmuletInfo", "", "");
		super.addTable("roleorderhistorys", "DB", "long", false, "RoleOrderHistroy", "", "");
		super.addTable("roletalismandepot", "DB", "long", false, "RoleTalismanDepot", "", "");
		super.addTable("roleequipbag", "DB", "long", false, "RoleEquipBag", "", "");
		super.addTable("rolefamily", "DB", "long", false, "RoleFamily", "", "");
		super.addTable("ectypesingle", "DB", "int", false, "EctypeSingle", "", "");
		super.addTable("leaderboardrecord", "DB", "long", false, "BoardRecord", "", "");
		super.addTable("systemmailbox", "DB", "long", false, "SystemMail", "", "");
		super.addTable("rolefriendsinfo", "DB", "long", false, "RoleFriendsInfo", "", "");
		super.addTable("roleachievement", "DB", "long", false, "RoleAchievement", "", "");
		super.addTable("roleectypes", "DB", "long", false, "RoleEctype", "", "");
		super.addTable("rolename2ids", "DB", "string", false, "long", "", "");
		super.addTable("userdeletedroles", "DB", "long", false, "UserDeletedRole", "", "");
		super.addTable("idgen", "DB", "long", false, "IdGen", "", "");
		super.addTable("exchangelog", "DB", "long", true, "ExchangeLog", "", "");
		super.addTable("rolewelfareinfo", "DB", "long", false, "RoleWelfareInfo", "", "");
		super.addTable("rolemaps", "DB", "long", false, "RoleMap", "", "");
		super.addTable("rolefragmentdepot", "DB", "long", false, "RoleFragmentDepot", "", "");
		super.addTable("huiwucurterms", "DB", "int", false, "HuiWuCurTerm", "", "");
		super.addTable("users", "DB", "long", false, "User", "", "");
		super.addTable("huiwupriviousterms", "DB", "int", false, "HuiWuPriviousTerm", "", "");
		super.addTable("ranks", "DB", "int", false, "ARank", "", "");
		super.addTable("mailbox", "DB", "long", false, "MailBox", "", "");
		super.addTable("system", "DB", "int", false, "System", "", "");
		super.addTable("goldcoindepot", "DB", "long", false, "long", "", "");
		super.addTable("pickcardinfos", "DB", "long", false, "PickCardInfo", "", "");
		super.addTable("rolememinfos", "MEMORY", "long", false, "RoleMemInfo", "", "");
		super.addTable("processingorders", "DB", "long", false, "AppOrder", "", "");
		super.addTable("rolelimit", "DB", "long", false, "RoleLimit", "", "");
		super.addTable("rolefragmentbag", "DB", "long", false, "RoleFragmentBag", "", "");
		super.addTable("roleitemdepot", "DB", "long", false, "RoleItemDepot", "", "");
		super.addTable("globallactivity", "DB", "int", false, "GlobalAllActivity", "", "");
		super.addTable("roledailyresetdata", "DB", "long", false, "DailyResetData", "", "");
		super.addTable("roleexchange", "DB", "long", false, "RoleExchange", "", "");
		super.addTable("forbids", "DB", "long", false, "Forbid", "", "");
		super.addTable("rolestorynotes", "DB", "long", false, "RoleStoryNote", "", "");
		super.addTable("jade", "DB", "long", false, "RoleJadeInfo", "", "");
		super.addTable("onlineusers", "MEMORY", "long", false, "OnlineUser", "", "");
		super.addTable("title", "DB", "long", false, "RoleTitle", "", "");
		super.addTable("ride", "DB", "long", false, "RoleRide", "", "");
		super.addTable("dailyactives", "DB", "long", false, "DailyActive", "", "");
		super.addTable("roleattrs", "DB", "long", false, "RoleAttr", "", "");
		super.addTable("roletask", "DB", "long", false, "RoleTask", "", "");
		super.addTable("roleconfigure", "DB", "long", false, "RoleConfigure", "", "");
		super.addTable("role2team", "DB", "long", false, "RoleTeamInfo", "", "");
		super.addTable("idolcharm", "DB", "long", false, "IdolCharmInfo", "", "");
		super.addTable("roleitembag", "DB", "long", false, "RoleItemBag", "", "");
		super.addTable("gmsenseword", "DB", "int", false, "GMSenseword", "", "");
		super.addTable("roleinfos", "DB", "long", true, "RoleInfo", "", "");
		super.addTable("roletalismanbag", "DB", "long", false, "RoleTalismanBag", "", "");
		super.addTable("roleactivecodes", "DB", "long", false, "RoleActiveCode", "", "");
		super.addTable("rolepays", "DB", "long", false, "RolePay", "", "");
	}
	public void DatabaseMetaData3(){
		super.addTable("team", "MEMORY", "long", true, "Team", "", "");
		super.addTable("rolechat", "DB", "long", false, "RoleChat", "", "");
		super.addTable("roleequipdepot", "DB", "long", false, "RoleEquipDepot", "", "");
		super.addTable("roleallactivity", "DB", "long", false, "RoleAllActivity", "", "");
		super.addTable("rolenotice", "DB", "long", false, "RoleNotice", "", "");
		super.addTable("rolearena", "DB", "long", false, "RoleArena", "", "");
		super.addTable("familyextrinfo", "DB", "long", false, "IdList", "", "");
		super.addTable("exchangeitem", "DB", "long", true, "ExchangeItem", "", "");
		super.addTable("rolepet", "DB", "long", false, "RolePet", "", "");
		super.addTable("family", "DB", "long", true, "Family", "", "");
	}
	public _DatabaseMetaData_() {
		DatabaseMetaData1();
		DatabaseMetaData2();
		DatabaseMetaData3();
	}
}

