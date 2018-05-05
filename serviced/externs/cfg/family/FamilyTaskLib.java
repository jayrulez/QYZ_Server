package cfg.family;
public final class FamilyTaskLib extends cfg.CfgObject {
	public final static int TYPEID = 119290076;
	final public int getTypeId() { return TYPEID; }
	public final int leve;
	public final java.util.List<cfg.family.FamilyTask> familytasklist = new java.util.ArrayList<>();
	public final java.util.List<cfg.family.FamilyTask> completetasklist = new java.util.ArrayList<>();
	public FamilyTaskLib(cfg.DataStream fs) {
		this.leve = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.familytasklist.add(new cfg.family.FamilyTask(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.completetasklist.add(new cfg.family.FamilyTask(fs));
		}
	}
}