package cfg.family.citywar;
public final class CityWarTower extends cfg.CfgObject {
	public final static int TYPEID = 119852573;
	final public int getTypeId() { return TYPEID; }
	public final cfg.map.Vector2 position;
	public final int towerid;
	public CityWarTower(cfg.DataStream fs) {
		this.position = new cfg.map.Vector2(fs);
		this.towerid = fs.getInt();
	}
}