package cfg.guide;
public abstract class LockObject extends cfg.CfgObject {
	public static final int STRECHOPEN = 106;
	public static final int STRECHCLOSE = 186;
	public static final int JOYSTICK = 101;
	public static final int TASK = 201;
	public final int id;
	public final String controldlg;
	public final String controluiobject;
	public final int handtype;
	public final int arrowtype;
	public final int bordertype;
	public final java.util.List<Float> scale = new java.util.ArrayList<>();
	public final boolean fixorbind;
	public final java.util.List<Float> offset = new java.util.ArrayList<>();
	public final boolean addcomponent;
	public final boolean needclip;
	public final java.util.List<Float> clipoffset = new java.util.ArrayList<>();
	public LockObject(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.controldlg = fs.getString();
		this.controluiobject = fs.getString();
		this.handtype = fs.getInt();
		this.arrowtype = fs.getInt();
		this.bordertype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.scale.add(fs.getFloat());
		}
		this.fixorbind = fs.getBool();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.offset.add(fs.getFloat());
		}
		this.addcomponent = fs.getBool();
		this.needclip = fs.getBool();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.clipoffset.add(fs.getFloat());
		}
	}
}