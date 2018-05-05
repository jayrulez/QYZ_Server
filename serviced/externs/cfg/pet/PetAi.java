package cfg.pet;
public final class PetAi extends cfg.CfgObject {
	public final static int TYPEID = 1071230702;
	final public int getTypeId() { return TYPEID; }
	public static final int PET_CHECK_AREA_1 = 8;
	public static final int PET_CHECK_AREA_2 = 15;
	public static final int PET_CHECK_TERMINAL = 1;
	public static final int PET_ATK_AREA = 8;
	public static final int PET_ATK_CHECK_TERMINAL = 1;
	public final int id;
	public final java.util.List<Integer> ai = new java.util.ArrayList<>();
	public PetAi(cfg.DataStream fs) {
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.ai.add(fs.getInt());
		}
	}
}