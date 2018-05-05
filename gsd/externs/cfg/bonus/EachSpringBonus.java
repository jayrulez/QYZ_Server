package cfg.bonus;
public final class EachSpringBonus extends cfg.CfgObject {
	public final static int TYPEID = 705730392;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String desc1;
	public final cfg.cmd.condition.YuanBao buymoney;
	public final cfg.cmd.condition.YuanBao totalmoney;
	public final java.util.List<cfg.bonus.SpringBonusDetail> details = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.bonus.SpringBonusDetail> details_id= new java.util.HashMap<>();
	public EachSpringBonus(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.desc1 = fs.getString();
		this.buymoney = new cfg.cmd.condition.YuanBao(fs);
		this.totalmoney = new cfg.cmd.condition.YuanBao(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.bonus.SpringBonusDetail _v = new cfg.bonus.SpringBonusDetail(fs);
			this.details.add(_v);
			this.details_id.put(_v.id, _v);
		}
	}
}