package cfg.manual;
public final class PetDescribe extends cfg.CfgObject {
	public final static int TYPEID = 1611520140;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String shortdescribe;
	public final String describe;
	public final java.util.List<String> petskilllist = new java.util.ArrayList<>();
	public final java.util.List<String> skilldescribelist = new java.util.ArrayList<>();
	public PetDescribe(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.shortdescribe = fs.getString();
		this.describe = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.petskilllist.add(fs.getString());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.skilldescribelist.add(fs.getString());
		}
	}
}