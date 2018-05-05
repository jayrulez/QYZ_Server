package cfg.pet;
public final class PetKarma extends cfg.CfgObject {
	public final static int TYPEID = 1435943914;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String petname;
	public final java.util.List<cfg.pet.Karma> petkarmas = new java.util.ArrayList<>();
	public PetKarma(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.petname = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.petkarmas.add(new cfg.pet.Karma(fs));
		}
	}
}