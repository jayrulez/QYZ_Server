package cfg.family;
public final class FamilyInfo extends cfg.CfgObject {
	public final static int TYPEID = 1736215570;
	final public int getTypeId() { return TYPEID; }
	public static final int OPEN_LEVEL = 20;
	public static final int DECLARATION_LENGTH = 18;
	public static final int PUBLICINFO_LENGTH = 50;
	public static final int MAX_FAMILY_LEVEL = 7;
	public static final int MAX_LOG_SIZE = 50;
	public static final int MAX_RANK_NUM = 100;
	public static final int NAME_MAX_LENGTH = 20;
	public static final int NAME_MIN_LENGTH = 1;
	public static final int ONLINE_DAY_NUM = 0;
	public static final int CREATE_REQUIRE_YUANBAO = 250;
	public static final int MIN_CROWD_FUND_YUANBAO = 100;
	public static final int MIN_ADD_FUND_YUANBAO = 10;
	public static final int MIN_ADD_FUND_YUANBAO_STEP = 10;
	public static final float FUND_FAILED_COST = 0.1f;
	public static final int FUNDING_TIME = 43200;
	public static final int MAX_QUIT_NUM = 2;
	public static final int DISALLOW_ACTION_HOUR_AFTER_QUIT = 2;
	public static final int MAX_APPLY_NUM = 10;
	public static final String FAMILY_MEMBER_STR = "教众";
	public static final String DEFAULT_DECLARATION = "欢迎大家加入家族";
	public static final String DEFAULT_PUBLICINFO = "2级后烧香能换好东西，多做家族环和四灵降世，每晚定时开聚宴，记得准时上线！";
	public static final int ANIMAL_ACTIVITY_WEEK_NUM = 2;
	public static final float ACTIVE_TRANSFER_RATE = 2f;
	public final int familylvl;
	public final int requirebuildrate;
	public final int memberamount;
	public final cfg.cmd.condition.MinLevel requirecapitanlevel;
	public FamilyInfo(cfg.DataStream fs) {
		this.familylvl = fs.getInt();
		this.requirebuildrate = fs.getInt();
		this.memberamount = fs.getInt();
		this.requirecapitanlevel = new cfg.cmd.condition.MinLevel(fs);
	}
}