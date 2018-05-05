package cfg.plot;
public final class Branch extends cfg.plot.PlotTalk {
	public final static int TYPEID = -1643324187;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.plot.TalkInfo> talks = new java.util.ArrayList<>();
	public Branch(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.talks.add(new cfg.plot.TalkInfo(fs));
		}
	}
}