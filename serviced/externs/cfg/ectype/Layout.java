package cfg.ectype;
public final class Layout extends cfg.CfgObject {
	public final static int TYPEID = -1290598122;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final cfg.map.Vector2 reviveposition;
	public final int gfxid;
	public final boolean candrag;
	public final int dragcount;
	public final cfg.ectype.Area area;
	public final int type;
	public final java.util.List<cfg.ectype.Passage> enters = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.Passage> enters_id= new java.util.HashMap<>();
	public final java.util.List<cfg.ectype.Passage> exits = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.Passage> exits_id= new java.util.HashMap<>();
	public final java.util.List<cfg.ectype.Action> scripts = new java.util.ArrayList<>();
	public final cfg.map.Vector2 startposition;
	public final float startrotation;
	public Layout(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.reviveposition = new cfg.map.Vector2(fs);
		this.gfxid = fs.getInt();
		this.candrag = fs.getBool();
		this.dragcount = fs.getInt();
		this.area = (cfg.ectype.Area)fs.getObject(fs.getString());
		this.type = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.Passage _v = new cfg.ectype.Passage(fs);
			this.enters.add(_v);
			this.enters_id.put(_v.id, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.Passage _v = new cfg.ectype.Passage(fs);
			this.exits.add(_v);
			this.exits_id.put(_v.id, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.scripts.add((cfg.ectype.Action)fs.getObject(fs.getString()));
		}
		this.startposition = new cfg.map.Vector2(fs);
		this.startrotation = fs.getFloat();
	}
}