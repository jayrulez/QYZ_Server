package cfg.pet;
public final class Karma extends cfg.CfgObject {
	public final static int TYPEID = 1066493367;
	final public int getTypeId() { return TYPEID; }
	public final String karmaname;
	public final String introduction;
	public final java.util.List<Integer> petkeys = new java.util.ArrayList<>();
	public final int carmatype;
	public final java.util.List<cfg.pet.KarmaProp> prop = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pet.KarmaProp> prop_level= new java.util.HashMap<>();
	public Karma(cfg.DataStream fs) {
		this.karmaname = fs.getString();
		this.introduction = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.petkeys.add(fs.getInt());
		}
		this.carmatype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pet.KarmaProp _v = new cfg.pet.KarmaProp(fs);
			this.prop.add(_v);
			this.prop_level.put(_v.level, _v);
		}
	}
}