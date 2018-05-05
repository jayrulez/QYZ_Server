package cfg.pet;
public final class PetConfig extends cfg.CfgObject {
	public final static int TYPEID = 1348358920;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> petslotopenlevel = new java.util.ArrayList<>();
	public final cfg.cmd.condition.CoolDown equipcd;
	public final int deadcd;
	public final float attackcd;
	public final float follownearradius;
	public final float followfarradius;
	public final float guardradius;
	public final float followcheckinterval;
	public final float attackspace;
	public final float idlespace;
	public final java.util.List<cfg.pet.PetFollow> follow = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pet.PetFollow> follow_petamount= new java.util.HashMap<>();
	public final java.util.List<cfg.pet.AwakeBackgroundTexture> awaketexture = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.pet.AwakeBackgroundTexture> awaketexture_awakelevel= new java.util.HashMap<>();
	public final java.util.Map<Integer, String> qualitycolor = new java.util.HashMap<>();
	public final cfg.cmd.condition.MinLevel washopenlevel;
	public final float transferrate1;
	public final float transferrate2;
	public final int notskilltimeafterrevive;
	public PetConfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.petslotopenlevel.add(fs.getInt());
		}
		this.equipcd = new cfg.cmd.condition.CoolDown(fs);
		this.deadcd = fs.getInt();
		this.attackcd = fs.getFloat();
		this.follownearradius = fs.getFloat();
		this.followfarradius = fs.getFloat();
		this.guardradius = fs.getFloat();
		this.followcheckinterval = fs.getFloat();
		this.attackspace = fs.getFloat();
		this.idlespace = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pet.PetFollow _v = new cfg.pet.PetFollow(fs);
			this.follow.add(_v);
			this.follow_petamount.put(_v.petamount, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.pet.AwakeBackgroundTexture _v = new cfg.pet.AwakeBackgroundTexture(fs);
			this.awaketexture.add(_v);
			this.awaketexture_awakelevel.put(_v.awakelevel, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.qualitycolor.put(_k, fs.getString());
		}
		this.washopenlevel = new cfg.cmd.condition.MinLevel(fs);
		this.transferrate1 = fs.getFloat();
		this.transferrate2 = fs.getFloat();
		this.notskilltimeafterrevive = fs.getInt();
	}
}