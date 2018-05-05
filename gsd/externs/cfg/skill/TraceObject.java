package cfg.skill;
public abstract class TraceObject extends cfg.skill.SpawnObject {
	public static final int TRACETYPE_FLY = 0;
	public static final int TRACETYPE_FIXED = 1;
	public static final int BINDTYPE_BODY = 0;
	public static final int BINDTYPE_HEAD = 1;
	public static final int BINDTYPE_FOOT = 2;
	public static final float BODY_CORRECT = 0.7f;
	public static final float HEAD_CORRECT = 1.3f;
	public final int effectid;
	public final boolean totarget;
	public final int tracecurveid;
	public final float offsetx;
	public final float offsety;
	public final float offsetz;
	public final int tracetype;
	public final int casterbindtype;
	public final int targetbindtype;
	public TraceObject(cfg.DataStream fs) {
		super(fs);
		this.effectid = fs.getInt();
		this.totarget = fs.getBool();
		this.tracecurveid = fs.getInt();
		this.offsetx = fs.getFloat();
		this.offsety = fs.getFloat();
		this.offsetz = fs.getFloat();
		this.tracetype = fs.getInt();
		this.casterbindtype = fs.getInt();
		this.targetbindtype = fs.getInt();
	}
}