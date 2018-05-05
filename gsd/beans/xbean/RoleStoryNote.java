
package xbean;

public interface RoleStoryNote extends xdb.Bean {
	public RoleStoryNote copy(); // deep clone
	public RoleStoryNote toData(); // a Data instance
	public RoleStoryNote toBean(); // a Bean instance
	public RoleStoryNote toDataIf(); // a Data instance If need. else return this
	public RoleStoryNote toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.StoryNoteChapter> getChapters(); // 
	public java.util.Map<Integer, xbean.StoryNoteChapter> getChaptersAsData(); // 

}
