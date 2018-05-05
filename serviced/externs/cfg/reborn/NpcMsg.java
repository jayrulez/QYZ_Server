package cfg.reborn;
public final class NpcMsg extends cfg.CfgObject {
	public final static int TYPEID = -1345278168;
	final public int getTypeId() { return TYPEID; }
	public final int npcid;
	public final int mapid;
	public final cfg.map.Vector2 pos;
	public final String givename;
	public final String talkdecs;
	public final String npcname;
	public final String npchead;
	public final cfg.cmd.action.MultiBonus winbonus;
	public final String windecs;
	public final int winnpc;
	public final int winnpcmap;
	public final cfg.map.Vector2 winnpcpos;
	public final String winnpcname;
	public final String winnpchead;
	public final String winnpctalk;
	public NpcMsg(cfg.DataStream fs) {
		this.npcid = fs.getInt();
		this.mapid = fs.getInt();
		this.pos = new cfg.map.Vector2(fs);
		this.givename = fs.getString();
		this.talkdecs = fs.getString();
		this.npcname = fs.getString();
		this.npchead = fs.getString();
		this.winbonus = new cfg.cmd.action.MultiBonus(fs);
		this.windecs = fs.getString();
		this.winnpc = fs.getInt();
		this.winnpcmap = fs.getInt();
		this.winnpcpos = new cfg.map.Vector2(fs);
		this.winnpcname = fs.getString();
		this.winnpchead = fs.getString();
		this.winnpctalk = fs.getString();
	}
}