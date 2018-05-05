package xtable;


public class _DatabaseMetaData_ extends xdb.util.DatabaseMetaData {
	@Override
	public boolean isVerifyXdb() {
		return true;
	}
	public void DatabaseMetaData1(){
		// xbeans
		{
			Bean bean = new Bean("UserInfo", false, false);
			super.addVariableFor(bean
				, 0
				, "plattype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "useridentity"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("UserPayInfo", false, false);
			super.addVariableFor(bean
				, 1
				, "totalpay"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "totalyunbao"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "totalbindyuanbao"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "totalvipexp"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "hasgotreturn"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "roleid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("UncompletedOrderInfo", false, false);
			super.addVariableFor(bean
				, 0
				, "serverid"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "plattype"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "platorderid"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "userid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 4
				, "vars"
				, "binary", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "times"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("GsdErrorOrderInfo", false, false);
			super.addVariableFor(bean
				, 0
				, "order"
				, "UncompletedOrderInfo", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "reason"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("TestUserInfo", false, false);
			super.addVariableFor(bean
				, 0
				, "userid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "userinfoid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("TestOrderInfo", false, false);
			super.addVariableFor(bean
				, 0
				, "createtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "gsorderid"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "useridentity"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "vars"
				, "binary", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("OnesdkUserInfo", false, false);
			super.addVariableFor(bean
				, 0
				, "userid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "userinfoid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("OnesdkOrderInfo", false, false);
			super.addVariableFor(bean
				, 0
				, "createtime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "gsorderid"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "useridentity"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 3
				, "vars"
				, "binary", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ApnsDevice", false, false);
			super.addVariableFor(bean
				, 0
				, "token"
				, "string", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "updatetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("RobotId", false, false);
			super.addVariableFor(bean
				, 0
				, "minuserid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "maxuserid"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("GlobalActivationCode", false, false);
			super.addVariableFor(bean
				, 1
				, "alltypes"
				, "set", "", "int", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ActivationCodeSet", false, false);
			super.addVariableFor(bean
				, 0
				, "type"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "values"
				, "set", "", "long", ""
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
				, "opentime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 5
				, "expiratetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 6
				, "platformset"
				, "set", "", "int", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 7
				, "isshared"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 8
				, "islogin"
				, "boolean", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("ActivationCode", false, false);
			super.addVariableFor(bean
				, 0
				, "type"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 1
				, "status"
				, "int", "", "", ""
				, "", "", ""
				);
			super.addVariableFor(bean
				, 2
				, "usetime"
				, "long", "", "", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		{
			Bean bean = new Bean("UserActivationCode", false, false);
			super.addVariableFor(bean
				, 0
				, "all"
				, "map", "int", "long", ""
				, "", "", ""
				);
			super.addBean(bean);
		}
		// cbeans
		// tables
		super.addTable("gsderrororderinfos", "DB", "string", false, "GsdErrorOrderInfo", "", "");
		super.addTable("testusers", "DB", "string", false, "TestUserInfo", "", "");
		super.addTable("robotids", "DB", "int", false, "RobotId", "", "");
		super.addTable("testordergs2plat", "DB", "string", false, "string", "", "");
		super.addTable("globalactivationcodes", "DB", "int", false, "GlobalActivationCode", "", "");
		super.addTable("users", "DB", "long", true, "UserInfo", "", "");
		super.addTable("uncompletedorderinfos", "DB", "string", false, "UncompletedOrderInfo", "", "");
		super.addTable("onesdkordergs2plat", "DB", "string", false, "string", "", "");
		super.addTable("onesdkorderinfos", "DB", "string", false, "OnesdkOrderInfo", "", "");
		super.addTable("useractivationcodes", "DB", "long", false, "UserActivationCode", "", "");
		super.addTable("testorderinfos", "DB", "string", false, "TestOrderInfo", "", "");
		super.addTable("activationcodesets", "DB", "int", false, "ActivationCodeSet", "", "");
		super.addTable("apnsdevices", "DB", "long", false, "ApnsDevice", "", "");
		super.addTable("userpays", "DB", "long", false, "UserPayInfo", "", "");
		super.addTable("onesdkusers", "DB", "string", false, "OnesdkUserInfo", "", "");
		super.addTable("activationcodes", "DB", "long", false, "ActivationCode", "", "");
	}
	public _DatabaseMetaData_() {
		DatabaseMetaData1();
	}
}

