package cfg.ectype;
public final class StoryLayout extends cfg.CfgObject {
	public final static int TYPEID = -142124525;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final java.util.List<cfg.ectype.Enviroment> enviroments = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.Layout> layouts = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.Layout> layouts_id= new java.util.HashMap<>();
	public final int storyexitscene;
	public final cfg.map.Vector2 storyexitposition;
	public final float exitrotation;
	public StoryLayout(cfg.DataStream fs) {
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.enviroments.add(new cfg.ectype.Enviroment(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.Layout _v = new cfg.ectype.Layout(fs);
			this.layouts.add(_v);
			this.layouts_id.put(_v.id, _v);
		}
		this.storyexitscene = fs.getInt();
		this.storyexitposition = new cfg.map.Vector2(fs);
		this.exitrotation = fs.getFloat();
	}
}