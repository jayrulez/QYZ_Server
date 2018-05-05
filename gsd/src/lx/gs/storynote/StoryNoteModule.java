package lx.gs.storynote;

import cfg.CfgMgr;
import lx.gs.role.FRoleAttr;

import java.util.HashMap;

/**
 * Created by HuangQiang on 2016/5/14.
 */
public enum StoryNoteModule implements gs.Module, gs.RoleLoginListener, gs.RoleAttrListener {
    INSTANCE;


    @Override
    public void start() {

    }

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        xdb.Transaction.tsendWhileCommit(roleid, FStoryNote.createSInfo(roleid));
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {

    }


    @Override
    public void updateRoleAttr(long roleid) {
        final HashMap<Integer, Float> attrs = new HashMap<>();
        FStoryNote.get(roleid).getChapters().forEach((id, chapterInfo) -> {
            final cfg.storynote.StoryNote scfg = CfgMgr.storynote.get(id);
            for(int noteid : chapterInfo.getNotes()) {
                final cfg.storynote.NoteInfo ncfg = scfg.noteinfo_noteid.get(noteid);
                common.AttrUtils.addAttrs(attrs, ncfg.addproperty);
            }
        });
        FRoleAttr.updateGroup(roleid, "storynote", attrs);
    }
}
