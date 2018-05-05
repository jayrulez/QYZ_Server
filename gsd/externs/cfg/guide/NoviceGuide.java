package cfg.guide;
public final class NoviceGuide extends cfg.CfgObject {
	public final static int TYPEID = -1164547606;
	final public int getTypeId() { return TYPEID; }
	public static final int CONTROLLER = 2;
	public static final int TOTALFINDOBJECTTIMES = 80;
	public static final int TRIGGERNEXTGUIDETIME = 300;
	public static final int FINDOBJINTERVAL = 1;
	public static final int NONMANDOTORYTIME = 10;
	public static final int OPENDISPLAYTIME = 3;
	public static final int OPENFORCETIME = 2;
	public static final int TARGETPANELDEPTH = 701;
	public static final int TALISMANGUIDEID = 1210;
	public final int id;
	public final boolean isinectype;
	public final boolean issavepoint;
	public final boolean ismandatory;
	public final boolean isdisskipbutton;
	public final boolean ispause;
	public final boolean islocked;
	public final float delaytime;
	public final int audio;
	public final int overaudio;
	public final java.util.List<cfg.guide.GuideEffect> guideeffect = new java.util.ArrayList<>();
	public final java.util.List<cfg.cmd.condition.Condition> triggerconditions = new java.util.ArrayList<>();
	public final java.util.List<cfg.cmd.condition.Condition> completeconditions = new java.util.ArrayList<>();
	public NoviceGuide(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.isinectype = fs.getBool();
		this.issavepoint = fs.getBool();
		this.ismandatory = fs.getBool();
		this.isdisskipbutton = fs.getBool();
		this.ispause = fs.getBool();
		this.islocked = fs.getBool();
		this.delaytime = fs.getFloat();
		this.audio = fs.getInt();
		this.overaudio = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.guideeffect.add((cfg.guide.GuideEffect)fs.getObject(fs.getString()));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.triggerconditions.add((cfg.cmd.condition.Condition)fs.getObject(fs.getString()));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.completeconditions.add((cfg.cmd.condition.Condition)fs.getObject(fs.getString()));
		}
	}
}