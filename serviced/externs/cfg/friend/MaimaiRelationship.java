package cfg.friend;
public final class MaimaiRelationship extends cfg.CfgObject {
	public final static int TYPEID = 1793575582;
	final public int getTypeId() { return TYPEID; }
	public final int relationship;
	public final int gender;
	public final String nametext;
	public final String deletetext;
	public final int reqgender;
	public final int maxnum;
	public final int correspondingrelationshipmale;
	public final int correspondingrelationshipfemale;
	public final String icon;
	public final int basicrelation;
	public MaimaiRelationship(cfg.DataStream fs) {
		this.relationship = fs.getInt();
		this.gender = fs.getInt();
		this.nametext = fs.getString();
		this.deletetext = fs.getString();
		this.reqgender = fs.getInt();
		this.maxnum = fs.getInt();
		this.correspondingrelationshipmale = fs.getInt();
		this.correspondingrelationshipfemale = fs.getInt();
		this.icon = fs.getString();
		this.basicrelation = fs.getInt();
	}
}