package cfg.map;
public final class LandScape extends cfg.CfgObject {
	public final static int TYPEID = 457200735;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String scenepath;
	public final java.util.List<cfg.map.Controller> controllers = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.map.Controller> controllers_id= new java.util.HashMap<>();
	public final java.util.List<cfg.map.LandscapeEntrance> entrances = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.map.LandscapeEntrance> entrances_id= new java.util.HashMap<>();
	public LandScape(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.scenepath = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.map.Controller _v = new cfg.map.Controller(fs);
			this.controllers.add(_v);
			this.controllers_id.put(_v.id, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.map.LandscapeEntrance _v = new cfg.map.LandscapeEntrance(fs);
			this.entrances.add(_v);
			this.entrances_id.put(_v.id, _v);
		}
	}
}