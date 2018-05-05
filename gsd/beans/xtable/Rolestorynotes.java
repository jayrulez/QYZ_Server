package xtable;

// typed table access point
public class Rolestorynotes {
	Rolestorynotes() {
	}

	public static xbean.RoleStoryNote get(Long key) {
		return _Tables_.getInstance().rolestorynotes.get(key);
	}

	public static xbean.RoleStoryNote get(Long key, xbean.RoleStoryNote value) {
		return _Tables_.getInstance().rolestorynotes.get(key, value);
	}

	public static void insert(Long key, xbean.RoleStoryNote value) {
		_Tables_.getInstance().rolestorynotes.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolestorynotes.delete(key);
	}

	public static boolean add(Long key, xbean.RoleStoryNote value) {
		return _Tables_.getInstance().rolestorynotes.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolestorynotes.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleStoryNote> getCache() {
		return _Tables_.getInstance().rolestorynotes.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleStoryNote> getTable() {
		return _Tables_.getInstance().rolestorynotes;
	}

	public static xbean.RoleStoryNote select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleStoryNote, xbean.RoleStoryNote>() {
			public xbean.RoleStoryNote get(xbean.RoleStoryNote v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.StoryNoteChapter> selectChapters(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleStoryNote, java.util.Map<Integer, xbean.StoryNoteChapter>>() {
				public java.util.Map<Integer, xbean.StoryNoteChapter> get(xbean.RoleStoryNote v) { return v.getChaptersAsData(); }
			});
	}

}
