
package xbean;

public interface ChapterInfo extends xdb.Bean {
	public ChapterInfo copy(); // deep clone
	public ChapterInfo toData(); // a Data instance
	public ChapterInfo toBean(); // a Bean instance
	public ChapterInfo toDataIf(); // a Data instance If need. else return this
	public ChapterInfo toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<Integer> getSectionstars(); // 对应关卡1,2,,,,
	public java.util.List<Integer> getSectionstarsAsData(); // 对应关卡1,2,,,,
	public java.util.List<Integer> getObtainrewardindexs(); // 
	public java.util.List<Integer> getObtainrewardindexsAsData(); // 

}
