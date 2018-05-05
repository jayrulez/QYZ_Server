package cfg.ui;
public abstract class ContentUIBase extends cfg.CfgObject {
	public final String path;
	public final java.util.List<Integer> pathlist = new java.util.ArrayList<>();
	public final cfg.ui.Transform transform;
	public ContentUIBase(cfg.DataStream fs) {
		this.path = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.pathlist.add(fs.getInt());
		}
		this.transform = new cfg.ui.Transform(fs);
	}
}