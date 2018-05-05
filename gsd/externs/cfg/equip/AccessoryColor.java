package cfg.equip;
public final class AccessoryColor extends cfg.CfgObject {
	public final static int TYPEID = 1702437977;
	final public int getTypeId() { return TYPEID; }
	public static final double[] JUDGE_COLOR_LIST = {0.2,0.35,0.45,0.6,0.75};
	public static final float JUDGE_COLOR_MAIN = 5f;
	public final int level;
	public final java.util.List<cfg.equip.EquipPropertyData> standard = new java.util.ArrayList<>();
	public AccessoryColor(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.standard.add(new cfg.equip.EquipPropertyData(fs));
		}
	}
}