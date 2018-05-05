package cfg.pet;
public final class AwakeBackgroundTexture extends cfg.CfgObject {
	public final static int TYPEID = 208404129;
	final public int getTypeId() { return TYPEID; }
	public final int awakelevel;
	public final cfg.pet.QualityTexture backgroundtexture;
	public AwakeBackgroundTexture(cfg.DataStream fs) {
		this.awakelevel = fs.getInt();
		this.backgroundtexture = new cfg.pet.QualityTexture(fs);
	}
}