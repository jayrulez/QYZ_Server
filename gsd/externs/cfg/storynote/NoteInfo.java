package cfg.storynote;
public final class NoteInfo extends cfg.CfgObject {
	public final static int TYPEID = 1358659921;
	final public int getTypeId() { return TYPEID; }
	public final int noteid;
	public final cfg.cmd.condition.Items requireitemlist;
	public final cfg.equip.EquipPropertyData addproperty;
	public NoteInfo(cfg.DataStream fs) {
		this.noteid = fs.getInt();
		this.requireitemlist = new cfg.cmd.condition.Items(fs);
		this.addproperty = new cfg.equip.EquipPropertyData(fs);
	}
}