package cfg.pet;
public final class PetBasicStatus extends cfg.CfgObject {
	public final static int TYPEID = -1117826630;
	final public int getTypeId() { return TYPEID; }
	public static final float TURN_PET_INTO_FRAGMENT_RATE = 0.7f;
	public static final float INITIALIZE_RETURN_RATE = 200f;
	public final int id;
	public final String name;
	public final String introduction;
	public final int displayorder;
	public final String icon;
	public final int ai;
	public final String modelname;
	public final java.util.List<Integer> fashionpath = new java.util.ArrayList<>();
	public final int bindtype;
	public final boolean canwish;
	public final int basiccolor;
	public final int fragmentid;
	public final java.util.List<String> battletalk = new java.util.ArrayList<>();
	public final int pettype;
	public final String feature;
	public final java.util.List<Integer> featurelist = new java.util.ArrayList<>();
	public final cfg.pet.Maturerate matureratelist;
	public final cfg.fight.Attr attr;
	public PetBasicStatus(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.introduction = fs.getString();
		this.displayorder = fs.getInt();
		this.icon = fs.getString();
		this.ai = fs.getInt();
		this.modelname = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.fashionpath.add(fs.getInt());
		}
		this.bindtype = fs.getInt();
		this.canwish = fs.getBool();
		this.basiccolor = fs.getInt();
		this.fragmentid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.battletalk.add(fs.getString());
		}
		this.pettype = fs.getInt();
		this.feature = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.featurelist.add(fs.getInt());
		}
		this.matureratelist = new cfg.pet.Maturerate(fs);
		this.attr = new cfg.fight.Attr(fs);
	}
}