package cfg.ectype;
public final class FamilyStation extends cfg.CfgObject {
	public final static int TYPEID = -1860561468;
	final public int getTypeId() { return TYPEID; }
	public final int minspellnum;
	public final int maxspellnum;
	public final int refreshinterval;
	public final int maxbonustimes;
	public final cfg.map.Vector2 bornpositions;
	public final cfg.map.Vector2 familymanagerposition;
	public final cfg.map.Vector2 blackmarkeposition;
	public final cfg.map.Vector2 partyposition;
	public final cfg.map.Vector2 godanimalposition;
	public final cfg.ectype.Spell spellmsg;
	public final java.util.List<cfg.ectype.GodMonsterInfo> monsters = new java.util.ArrayList<>();
	public final cfg.cmd.action.MultiBonus godanimalextraBonus;
	public final int godanimaldroprune;
	public final java.util.List<cfg.map.Vector2> godanimalboxpositions = new java.util.ArrayList<>();
	public final int godanimalboxexisttime;
	public final int godanimalboxmaxeat;
	public FamilyStation(cfg.DataStream fs) {
		this.minspellnum = fs.getInt();
		this.maxspellnum = fs.getInt();
		this.refreshinterval = fs.getInt();
		this.maxbonustimes = fs.getInt();
		this.bornpositions = new cfg.map.Vector2(fs);
		this.familymanagerposition = new cfg.map.Vector2(fs);
		this.blackmarkeposition = new cfg.map.Vector2(fs);
		this.partyposition = new cfg.map.Vector2(fs);
		this.godanimalposition = new cfg.map.Vector2(fs);
		this.spellmsg = new cfg.ectype.Spell(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsters.add(new cfg.ectype.GodMonsterInfo(fs));
		}
		this.godanimalextraBonus = new cfg.cmd.action.MultiBonus(fs);
		this.godanimaldroprune = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.godanimalboxpositions.add(new cfg.map.Vector2(fs));
		}
		this.godanimalboxexisttime = fs.getInt();
		this.godanimalboxmaxeat = fs.getInt();
	}
}