package cfg.skill;
public final class SkillAction extends cfg.skill.ModelActionBase {
	public final static int TYPEID = -1358103392;
	final public int getTypeId() { return TYPEID; }
	public static final int TARGET_ENERMY = 1;
	public static final int TARGET_TEAMMATE = 2;
	public static final int TARGET_SELF = 3;
	public static final int TARGET_ALL = 4;
	public static final int RELATE_SELF = 1;
	public static final int RELATE_TARGET = 2;
	public static final float DEFAULT_NEXTSKILLEXPIRETIME = 1.0f;
	public final float nextskillexpiretime;
	public final float endattackingtime;
	public final boolean needtarget;
	public final boolean showprogress;
	public final boolean caninterrupt;
	public final float attackrange;
	public final boolean showattackaera;
	public final boolean canrotate;
	public final boolean canmove;
	public final float startmovetime;
	public final float endmovetime;
	public final int relatetype;
	public final java.util.List<cfg.skill.AttackList> attacklists = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.skill.AttackList> attacklists_id= new java.util.HashMap<>();
	public final java.util.List<cfg.skill.HitZone> hitzones = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.skill.HitZone> hitzones_id= new java.util.HashMap<>();
	public final java.util.List<cfg.skill.BeAttackEffect> beattackeffects = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.skill.BeAttackEffect> beattackeffects_id= new java.util.HashMap<>();
	public SkillAction(cfg.DataStream fs) {
		super(fs);
		this.nextskillexpiretime = fs.getFloat();
		this.endattackingtime = fs.getFloat();
		this.needtarget = fs.getBool();
		this.showprogress = fs.getBool();
		this.caninterrupt = fs.getBool();
		this.attackrange = fs.getFloat();
		this.showattackaera = fs.getBool();
		this.canrotate = fs.getBool();
		this.canmove = fs.getBool();
		this.startmovetime = fs.getFloat();
		this.endmovetime = fs.getFloat();
		this.relatetype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.skill.AttackList _v = new cfg.skill.AttackList(fs);
			this.attacklists.add(_v);
			this.attacklists_id.put(_v.id, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.skill.HitZone _v = new cfg.skill.HitZone(fs);
			this.hitzones.add(_v);
			this.hitzones_id.put(_v.id, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.skill.BeAttackEffect _v = new cfg.skill.BeAttackEffect(fs);
			this.beattackeffects.add(_v);
			this.beattackeffects_id.put(_v.id, _v);
		}
	}
}