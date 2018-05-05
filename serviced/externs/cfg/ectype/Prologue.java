package cfg.ectype;
public final class Prologue extends cfg.CfgObject {
	public final static int TYPEID = 1275471155;
	final public int getTypeId() { return TYPEID; }
	public static final int MAX_BOSS_BODY_RADIUS = 30;
	public final int level;
	public final int id;
	public final java.util.List<cfg.ectype.BeginnerEquip> professionequips = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.BeginnerEquip> professionequips_profession= new java.util.HashMap<>();
	public final float flystartareax;
	public final float flystartareaz;
	public final int flyrouteid;
	public final int flyguideid;
	public final float flyendareax;
	public final float flyendareaz;
	public final int flyregionid;
	public final String cg_create_first_role;
	public final int cg_create_first_role_mode;
	public final String cg_ectype_end;
	public final int cg_ectype_end_mode;
	public final cfg.fight.Attr statusinfo;
	public final java.util.Map<Integer, Integer> banmusic = new java.util.HashMap<>();
	public Prologue(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.BeginnerEquip _v = new cfg.ectype.BeginnerEquip(fs);
			this.professionequips.add(_v);
			this.professionequips_profession.put(_v.profession, _v);
		}
		this.flystartareax = fs.getFloat();
		this.flystartareaz = fs.getFloat();
		this.flyrouteid = fs.getInt();
		this.flyguideid = fs.getInt();
		this.flyendareax = fs.getFloat();
		this.flyendareaz = fs.getFloat();
		this.flyregionid = fs.getInt();
		this.cg_create_first_role = fs.getString();
		this.cg_create_first_role_mode = fs.getInt();
		this.cg_ectype_end = fs.getString();
		this.cg_ectype_end_mode = fs.getInt();
		this.statusinfo = new cfg.fight.Attr(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.banmusic.put(_k, fs.getInt());
		}
	}
}