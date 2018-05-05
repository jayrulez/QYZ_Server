package cfg.character;
public final class Model extends cfg.CfgObject {
	public final static int TYPEID = 363136634;
	final public int getTypeId() { return TYPEID; }
	public final int modeltype;
	public final String modelname;
	public final String headicon;
	public final String portrait;
	public final String modelpath;
	public final String avatarid;
	public final float bodyradius;
	public final float bodyheight;
	public final float namehighshift;
	public final float modelscale;
	public final float uimodelscalemodify;
	public final int aperturecolor;
	public final float aperturescale;
	public Model(cfg.DataStream fs) {
		this.modeltype = fs.getInt();
		this.modelname = fs.getString();
		this.headicon = fs.getString();
		this.portrait = fs.getString();
		this.modelpath = fs.getString();
		this.avatarid = fs.getString();
		this.bodyradius = fs.getFloat();
		this.bodyheight = fs.getFloat();
		this.namehighshift = fs.getFloat();
		this.modelscale = fs.getFloat();
		this.uimodelscalemodify = fs.getFloat();
		this.aperturecolor = fs.getInt();
		this.aperturescale = fs.getFloat();
	}
}