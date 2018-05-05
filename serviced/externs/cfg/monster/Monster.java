package cfg.monster;
public final class Monster extends cfg.CfgObject {
	public final static int TYPEID = 817550364;
	final public int getTypeId() { return TYPEID; }
	public static final int DROP_CURRENCY_TYPE = 10200001;
	public static final int TEAM_SHARE_EXP_RADIUS = 50;
	public static final int MONSTER_LEAVE_DELAY_WHEN_DEAD = 5;
	public final int id;
	public final String name;
	public final int level;
	public final String type;
	public final String lockedbywho;
	public final float hpstrength;
	public final float atkstrength;
	public final float defstrength;
	public final String title;
	public final int camp;
	public final String owner;
	public final String introduce;
	public final int monstertype;
	public final java.util.List<String> battletalk = new java.util.ArrayList<>();
	public final float battletalkprobability;
	public final int attackvoice;
	public final int beattackvoice;
	public final int deadvoice;
	public final int patrolvoice;
	public final String modelname;
	public final float scale;
	public final int beattackedlight;
	public final boolean ifxuetiao;
	public final cfg.monster.DefaultAIParam defaultai;
	public final int weight;
	public final boolean playborneffect;
	public final boolean playerbornaction;
	public final int bornprotecttime;
	public final cfg.fight.Attr attr;
	public final float patrolspeed;
	public final int dropexp;
	public final int dropcurrencynum;
	public final java.util.List<cfg.monster.MultiRoundDrop> drops = new java.util.ArrayList<>();
	public Monster(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.level = fs.getInt();
		this.type = fs.getString();
		this.lockedbywho = fs.getString();
		this.hpstrength = fs.getFloat();
		this.atkstrength = fs.getFloat();
		this.defstrength = fs.getFloat();
		this.title = fs.getString();
		this.camp = fs.getInt();
		this.owner = fs.getString();
		this.introduce = fs.getString();
		this.monstertype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.battletalk.add(fs.getString());
		}
		this.battletalkprobability = fs.getFloat();
		this.attackvoice = fs.getInt();
		this.beattackvoice = fs.getInt();
		this.deadvoice = fs.getInt();
		this.patrolvoice = fs.getInt();
		this.modelname = fs.getString();
		this.scale = fs.getFloat();
		this.beattackedlight = fs.getInt();
		this.ifxuetiao = fs.getBool();
		this.defaultai = new cfg.monster.DefaultAIParam(fs);
		this.weight = fs.getInt();
		this.playborneffect = fs.getBool();
		this.playerbornaction = fs.getBool();
		this.bornprotecttime = fs.getInt();
		this.attr = new cfg.fight.Attr(fs);
		this.patrolspeed = fs.getFloat();
		this.dropexp = fs.getInt();
		this.dropcurrencynum = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.drops.add(new cfg.monster.MultiRoundDrop(fs));
		}
	}
}