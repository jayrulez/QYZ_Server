
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.achievement.CounterType;
import cfg.active.EventNum;
import cfg.cmd.ConfigId;
import cfg.ectype.SweepCondition;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.Bonus;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.achievement.FAchievement;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.logger.By;
import lx.gs.map.FMap;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSweepStoryEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSweepStoryEctype extends __CSweepStoryEctype__ {
	@Override
	protected void process() {
		new AProcedure<CSweepStoryEctype>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final Map<Integer, xbean.ChapterInfo> storys = FMap.getEctype(roleid).getChapters();
				final cfg.ectype.StoryEctype secfg = CfgMgr.storyectype.get(ectypeid);
				final ErrorCode err = FCondition.checkByReflection(roleid, secfg, 1, By.Open_Ectype, ConfigId.STORY_ECTYPE, ectypeid);
				if(err.err())
					return error(err);
				final xbean.ChapterInfo ci = storys.get(secfg.chapter);
				final int star = ci.getSectionstars().get(secfg.section - 1);
				if(secfg.sweepcondition == SweepCondition.CLEAR && star < 1)
					return error(ErrorCode.STORY_ECTYPE_SWEEP_NEED_CLEAR);
				if(secfg.sweepcondition == SweepCondition.THREE_STAR && star < 3)
					return error(ErrorCode.STORY_ECTYPE_SWEEP_NEED_3_STAR);

				final SSweepStoryEctype re = new SSweepStoryEctype();
				re.ectypeid = ectypeid;
				if(!FBonus.genAndAddBonus(roleid, secfg.ectypedrop, Bonus.BindType.BIND, re.bonus, By.Open_Ectype)){
					return false;
				}
				FAchievement.addCounter(roleid, CounterType.PASS_STORY_ECTYPE, 1);
				FDailyActivity.addActiveScores(roleid, EventNum.STORYPOINT);
                response(re);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584208;

	public int getType() {
		return 6584208;
	}

	public int ectypeid;

	public CSweepStoryEctype() {
	}

	public CSweepStoryEctype(int _ectypeid_) {
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
		if (_o1_ instanceof CSweepStoryEctype) {
			CSweepStoryEctype _o_ = (CSweepStoryEctype)_o1_;
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

	public int compareTo(CSweepStoryEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

