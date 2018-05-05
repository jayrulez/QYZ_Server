package cfg.storynote;
public final class StoryNote extends cfg.CfgObject {
	public final static int TYPEID = -881423594;
	final public int getTypeId() { return TYPEID; }
	public final int chapterid;
	public final String chaptername;
	public final cfg.cmd.condition.MinLevel openlevel;
	public final String chapterbgicon;
	public final java.util.List<cfg.storynote.NoteInfo> noteinfo = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.storynote.NoteInfo> noteinfo_noteid= new java.util.HashMap<>();
	public StoryNote(cfg.DataStream fs) {
		this.chapterid = fs.getInt();
		this.chaptername = fs.getString();
		this.openlevel = new cfg.cmd.condition.MinLevel(fs);
		this.chapterbgicon = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.storynote.NoteInfo _v = new cfg.storynote.NoteInfo(fs);
			this.noteinfo.add(_v);
			this.noteinfo_noteid.put(_v.noteid, _v);
		}
	}
}