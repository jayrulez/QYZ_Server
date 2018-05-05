package cfg.timelottery;
public final class Timelottery extends cfg.CfgObject {
	public final static int TYPEID = 295574816;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String desc;
	public final cfg.common.DateTimeRange datetime;
	public final cfg.timelottery.FigureShow figureshow;
	public final java.util.List<cfg.timelottery.VipFree> vipfree = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.timelottery.VipFree> vipfree_level= new java.util.HashMap<>();
	public final cfg.cmd.condition.Currencys costs;
	public final java.util.List<cfg.timelottery.BonusInfo> bonus = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.timelottery.BonusInfo> bonus_pos= new java.util.HashMap<>();
	public final int scorebonus;
	public final java.util.List<cfg.timelottery.ScoreExchange> scoreexchange = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.timelottery.ScoreExchange> scoreexchange_needscore= new java.util.HashMap<>();
	public Timelottery(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.desc = fs.getString();
		this.datetime = new cfg.common.DateTimeRange(fs);
		this.figureshow = new cfg.timelottery.FigureShow(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.timelottery.VipFree _v = new cfg.timelottery.VipFree(fs);
			this.vipfree.add(_v);
			this.vipfree_level.put(_v.level, _v);
		}
		this.costs = new cfg.cmd.condition.Currencys(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.timelottery.BonusInfo _v = new cfg.timelottery.BonusInfo(fs);
			this.bonus.add(_v);
			this.bonus_pos.put(_v.pos, _v);
		}
		this.scorebonus = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.timelottery.ScoreExchange _v = new cfg.timelottery.ScoreExchange(fs);
			this.scoreexchange.add(_v);
			this.scoreexchange_needscore.put(_v.needscore, _v);
		}
	}
}