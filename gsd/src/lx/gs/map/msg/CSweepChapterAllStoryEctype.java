
package lx.gs.map.msg;

import cfg.cmd.ConfigId;
import cfg.ectype.StoryEctype;
import cfg.ectype.SweepCondition;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.map.MapModule;

import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSweepChapterAllStoryEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSweepChapterAllStoryEctype extends __CSweepChapterAllStoryEctype__ {
	@Override
	protected void process() {
		new AProcedure<CSweepChapterAllStoryEctype>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final SSweepChapterAllStoryEctype re = new SSweepChapterAllStoryEctype();
				re.chapterid = chapterid;

				final xbean.ChapterInfo chapter = FMap.getEctype(roleid).getChapters().get(chapterid);
				final List<StoryEctype> storys = MapModule.chapterEctypes.get(chapterid);
				int sectionid = 0;
				for(int star : chapter.getSectionstars()) {
					++sectionid;
					xdb.Trace.info("CSweepChapterAllStoryEctype chapter:{} check sectionid:{} star:{}", chapterid, sectionid, star);
					final cfg.ectype.StoryEctype scfg = storys.get(sectionid - 1);
					if(scfg.sweepcondition == SweepCondition.CLEAR && star < 1)
						continue;
					if(scfg.sweepcondition == SweepCondition.THREE_STAR && star < 3)
						continue;
					xdb.Trace.info("CSweepChapterAllStoryEctype before check finish count");
					if(new xdb.Procedure() {
						@Override
						protected boolean process() {
							return FCondition.checkByReflection(roleid, scfg, 1, By.Open_Ectype, ConfigId.STORY_ECTYPE, scfg.id).ok();
						}
					}.call()) {
						xdb.Trace.info("CSweepChapterAllStoryEctype bonus");
						final map.msg.Bonus bonus = new map.msg.Bonus();
						if(!FBonus.genAndAddBonus(roleid, scfg.ectypedrop, common.Bonus.BindType.BIND, bonus, By.Open_Ectype)){
							return false;
						}
						re.bonus.put(sectionid, bonus);
					}
				}
				response(re);

				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6567169;

	public int getType() {
		return 6567169;
	}

	public int chapterid;

	public CSweepChapterAllStoryEctype() {
	}

	public CSweepChapterAllStoryEctype(int _chapterid_) {
		this.chapterid = _chapterid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(chapterid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		chapterid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSweepChapterAllStoryEctype) {
			CSweepChapterAllStoryEctype _o_ = (CSweepChapterAllStoryEctype)_o1_;
			if (chapterid != _o_.chapterid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += chapterid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(chapterid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSweepChapterAllStoryEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = chapterid - _o_.chapterid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

