
package lx.gs.storynote.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.event.EventModule;
import lx.gs.event.StoryNoteUpEvent;
import lx.gs.logger.By;
import lx.gs.storynote.FStoryNote;
import lx.gs.storynote.StoryNoteModule;
import xbean.StoryNoteChapter;

import java.util.Map;
import java.util.Set;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CActiveNote__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CActiveNote extends __CActiveNote__ {
	@Override
	protected void process() {
		new AProcedure<CActiveNote>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                final Map<Integer, StoryNoteChapter> chapters = FStoryNote.get(roleid).getChapters();
                final cfg.storynote.StoryNote scfg = CfgMgr.storynote.get(chapterid);
                ErrorCode err = FCondition.checkByReflection(roleid, scfg, By.Active_Story_Note);
                if(err.err())
                    return error(err);
                StoryNoteChapter chapter = chapters.get(chapterid);
                if(chapter == null) {
                    chapter = xbean.Pod.newStoryNoteChapter();
                    chapters.put(chapterid, chapter);
					EventModule.INSTANCE.broadcastEvent(new StoryNoteUpEvent(roleid));
                }
                final Set<Integer> notes = chapter.getNotes();
                if(notes.contains(noteid))
                    return error(ErrorCode.STORY_NOTE_HAS_ACTIVED);
                final cfg.storynote.NoteInfo ncfg = scfg.noteinfo_noteid.get(noteid);
                err = FCondition.checkByReflection(roleid, ncfg, By.Active_Story_Note);
                if(err.err())
                    return error(err);
                notes.add(noteid);
                StoryNoteModule.INSTANCE.updateRoleAttr(roleid);
                response(new SActiveNote(chapterid, noteid));
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566129;

	public int getType() {
		return 6566129;
	}

	public int chapterid;
	public int noteid;

	public CActiveNote() {
	}

	public CActiveNote(int _chapterid_, int _noteid_) {
		this.chapterid = _chapterid_;
		this.noteid = _noteid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(chapterid);
		_os_.marshal(noteid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		chapterid = _os_.unmarshal_int();
		noteid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CActiveNote) {
			CActiveNote _o_ = (CActiveNote)_o1_;
			if (chapterid != _o_.chapterid) return false;
			if (noteid != _o_.noteid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += chapterid;
		_h_ += noteid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(chapterid).append(",");
		_sb_.append(noteid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CActiveNote _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = chapterid - _o_.chapterid;
		if (0 != _c_) return _c_;
		_c_ = noteid - _o_.noteid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

