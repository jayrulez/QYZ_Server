package cfg.npc;
public final class NPC extends cfg.CfgObject {
	public final static int TYPEID = -1152887126;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final boolean isexclusive;
	public final String describename;
	public final String title;
	public final String scene;
	public final boolean canbattle;
	public final int battleid;
	public final String motion;
	public final String modelname;
	public final float speakrate;
	public final java.util.List<String> opentext = new java.util.ArrayList<>();
	public final boolean cantalk;
	public final String talktext;
	public final boolean hidename;
	public final int camp;
	public final int transids;
	public final int sellids;
	public final boolean guildvisible;
	public final boolean carrervisible;
	public final boolean visibleflag;
	public final boolean playbornaction;
	public NPC(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.isexclusive = fs.getBool();
		this.describename = fs.getString();
		this.title = fs.getString();
		this.scene = fs.getString();
		this.canbattle = fs.getBool();
		this.battleid = fs.getInt();
		this.motion = fs.getString();
		this.modelname = fs.getString();
		this.speakrate = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.opentext.add(fs.getString());
		}
		this.cantalk = fs.getBool();
		this.talktext = fs.getString();
		this.hidename = fs.getBool();
		this.camp = fs.getInt();
		this.transids = fs.getInt();
		this.sellids = fs.getInt();
		this.guildvisible = fs.getBool();
		this.carrervisible = fs.getBool();
		this.visibleflag = fs.getBool();
		this.playbornaction = fs.getBool();
	}
}