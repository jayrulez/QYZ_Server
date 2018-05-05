package cfg.skill;
public abstract class SpawnObject extends cfg.skill.Action {
	public static final int SPAWNTYPE_FLYWEAPON = 0;
	public static final int SPAWNTYPE_BOMB = 1;
	public static final int SPAWNTYPE_OBJECT = 2;
	public final int id;
	public final int spawntype;
	public final float life;
	public SpawnObject(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.spawntype = fs.getInt();
		this.life = fs.getFloat();
	}
}