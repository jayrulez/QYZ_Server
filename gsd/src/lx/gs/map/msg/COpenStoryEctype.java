
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.map.MapModule;
import xdb.Trace;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __COpenStoryEctype__ extends xio.Protocol { }

/** 剧情副本
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class COpenStoryEctype extends __COpenStoryEctype__ {
	@Override
	protected void process() {

			new AProcedure<COpenStoryEctype>(this) {
				@Override
				protected boolean doProcess() {
					if(!FMap.isInWorldOrFamily(roleid))
						return error(ErrorCode.CANNOT_ENTER_ECTYPE_IN_ECTYPE);
					final cfg.ectype.StoryEctype scfg = CfgMgr.storyectype.get(ectypeid);
					final cfg.ectype.StoryEctype pcfg = CfgMgr.storyectype.get(MapModule.storyEctypeid2PrevEctypeid.get(ectypeid));
					final xbean.RoleEctype info = FMap.getEctype(roleid);
					final Map<Integer, xbean.ChapterInfo> chapterInfos = info.getChapters();
					final int maxChapterId = chapterInfos.size();
					final xbean.ChapterInfo maxChapter = chapterInfos.get(maxChapterId);
					final int maxSectionId = maxChapter != null ? maxChapter.getSectionstars().size() : 0;

					if (pcfg != null && (pcfg.chapter > maxChapterId || (pcfg.chapter == maxChapterId && pcfg.section > maxSectionId))) {
						return error(ErrorCode.STORY_ECTYPE_NOT_FINISH_PREV);
					}
					final ErrorCode err = FCondition.checkByReflection(roleid, scfg, 1, By.Open_Ectype, ConfigId.STORY_ECTYPE, ectypeid);
					if (err.err()) {
						return error(err);
					}
					final int lastStar = FMap.getLastStar(chapterInfos.get(scfg.chapter), scfg.section);
					Trace.info("COpenStoryEctype roleid:{} ectypeid:{} lastStar:{}", roleid, ectypeid, lastStar);
					FMap.openOnePlayerEctypeInProcedure(roleid, ectypeid, false, lastStar);
					return true;
				}
			}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571470;

	public int getType() {
		return 6571470;
	}

	public int ectypeid;

	public COpenStoryEctype() {
	}

	public COpenStoryEctype(int _ectypeid_) {
		this.ectypeid = _ectypeid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof COpenStoryEctype) {
			COpenStoryEctype _o_ = (COpenStoryEctype)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(COpenStoryEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

