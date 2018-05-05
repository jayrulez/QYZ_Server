package cfg.ectype;
public final class Chapter extends cfg.CfgObject {
	public final static int TYPEID = -573166623;
	final public int getTypeId() { return TYPEID; }
	public final int chapterid;
	public final String chaptername;
	public final String introduction;
	public final java.util.List<cfg.ectype.ChapterAward> bonus = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.ChapterAward> bonus_awardid= new java.util.HashMap<>();
	public final String chapterbgmpic;
	public Chapter(cfg.DataStream fs) {
		this.chapterid = fs.getInt();
		this.chaptername = fs.getString();
		this.introduction = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.ChapterAward _v = new cfg.ectype.ChapterAward(fs);
			this.bonus.add(_v);
			this.bonus_awardid.put(_v.awardid, _v);
		}
		this.chapterbgmpic = fs.getString();
	}
}