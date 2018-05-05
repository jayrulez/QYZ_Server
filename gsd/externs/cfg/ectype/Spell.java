package cfg.ectype;
public final class Spell extends cfg.CfgObject {
	public final static int TYPEID = -866024004;
	final public int getTypeId() { return TYPEID; }
	public final int refreshtime;
	public final java.util.List<cfg.map.Vector2> positions = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.RuneInfo> runeinfo = new java.util.ArrayList<>();
	public Spell(cfg.DataStream fs) {
		this.refreshtime = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.positions.add(new cfg.map.Vector2(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.runeinfo.add(new cfg.ectype.RuneInfo(fs));
		}
	}
}