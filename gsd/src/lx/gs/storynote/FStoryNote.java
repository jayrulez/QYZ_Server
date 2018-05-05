package lx.gs.storynote;

/**
 * Created by HuangQiang on 2016/5/14.
 */
public class FStoryNote {
    public static xbean.RoleStoryNote get(long roleid) {
        xbean.RoleStoryNote info = xtable.Rolestorynotes.get(roleid);
        if(info == null) {
            info = xbean.Pod.newRoleStoryNote();
            xtable.Rolestorynotes.insert(roleid, info);
        }
        return info;
    }

    public static lx.gs.storynote.msg.SInfo createSInfo(long roleid) {
        final lx.gs.storynote.msg.SInfo re = new lx.gs.storynote.msg.SInfo();
        final xbean.RoleStoryNote info = get(roleid);
        info.getChapters().forEach((id, chapterInfo) -> {
            final lx.gs.storynote.msg.Chapter chapter = new lx.gs.storynote.msg.Chapter();
            chapter.notes.addAll(chapterInfo.getNotes());
            re.chapters.put(id, chapter);
        });

        return re;
    }
}
