package cfg.equip;
public abstract class Accessory extends cfg.equip.Equip {
	public static final int VICE_PROPERTY_NUM = 5;
	public static final int MAIN_PROP1_KEY = 1;
	public static final int MAIN_PROP2_KEY = 2;
	public final java.util.List<Integer> mainproperty = new java.util.ArrayList<>();
	public final java.util.List<Integer> mainproperty2 = new java.util.ArrayList<>();
	public final java.util.List<Integer> viceproperty = new java.util.ArrayList<>();
	public final java.util.List<Integer> rankweight = new java.util.ArrayList<>();
	public Accessory(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.mainproperty.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.mainproperty2.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.viceproperty.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.rankweight.add(fs.getInt());
		}
	}
}