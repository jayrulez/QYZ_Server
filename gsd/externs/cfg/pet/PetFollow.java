package cfg.pet;
public final class PetFollow extends cfg.CfgObject {
	public final static int TYPEID = 1434192759;
	final public int getTypeId() { return TYPEID; }
	public final int petamount;
	public final java.util.List<cfg.pet.FollowInfo> followlist = new java.util.ArrayList<>();
	public PetFollow(cfg.DataStream fs) {
		this.petamount = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.followlist.add(new cfg.pet.FollowInfo(fs));
		}
	}
}