
package xbean;

public interface StoryNoteChapter extends xdb.Bean {
	public StoryNoteChapter copy(); // deep clone
	public StoryNoteChapter toData(); // a Data instance
	public StoryNoteChapter toBean(); // a Bean instance
	public StoryNoteChapter toDataIf(); // a Data instance If need. else return this
	public StoryNoteChapter toBeanIf(); // a Bean instance If need. else return this

	public java.util.Set<Integer> getNotes(); // 
	public java.util.Set<Integer> getNotesAsData(); // 

}
