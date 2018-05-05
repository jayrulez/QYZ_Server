package cfg.plot;
public final class Single extends cfg.plot.PlotTalk {
	public final static int TYPEID = -1164559477;
	final public int getTypeId() { return TYPEID; }
	public final cfg.plot.TalkInfo talk;
	public Single(cfg.DataStream fs) {
		super(fs);
		this.talk = new cfg.plot.TalkInfo(fs);
	}
}