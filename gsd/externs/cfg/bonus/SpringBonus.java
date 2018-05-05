package cfg.bonus;
public final class SpringBonus extends cfg.CfgObject {
	public final static int TYPEID = 2126251929;
	final public int getTypeId() { return TYPEID; }
	public final cfg.common.DateTimeRange time;
	public final String desc1;
	public final String mailtitle;
	public final String mailcontent;
	public final java.util.List<cfg.bonus.EachSpringBonus> springbonus = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.bonus.EachSpringBonus> springbonus_id= new java.util.HashMap<>();
	public SpringBonus(cfg.DataStream fs) {
		this.time = new cfg.common.DateTimeRange(fs);
		this.desc1 = fs.getString();
		this.mailtitle = fs.getString();
		this.mailcontent = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.bonus.EachSpringBonus _v = new cfg.bonus.EachSpringBonus(fs);
			this.springbonus.add(_v);
			this.springbonus_id.put(_v.id, _v);
		}
	}
}