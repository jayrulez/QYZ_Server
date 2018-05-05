
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CResetStoryEctypeOpenCount__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CResetStoryEctypeOpenCount extends __CResetStoryEctypeOpenCount__ {
	@Override
	protected void process() {
		new AProcedure<CResetStoryEctypeOpenCount>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final ErrorCode err = FCondition.checkA(roleid, CfgMgr.ectypesingle.resetopencountlimit, 1,
						By.Reset_Story_Open_Count, ConfigId.STORY_ECTYPE_RESET_OPEN_COUNT, ectypeid);
				if(err.err())
					return error(err);
				if(!FLimit.clearLimit(roleid, ConfigId.STORY_ECTYPE, ectypeid))
					return error(ErrorCode.NOT_NEED_RESET_OPEN_COUNT);
				response(new SResetDailyEctypeOpenCount(ectypeid));

				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572113;

	public int getType() {
		return 6572113;
	}

	public int ectypeid;

	public CResetStoryEctypeOpenCount() {
	}

	public CResetStoryEctypeOpenCount(int _ectypeid_) {
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
		if (_o1_ instanceof CResetStoryEctypeOpenCount) {
			CResetStoryEctypeOpenCount _o_ = (CResetStoryEctypeOpenCount)_o1_;
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

	public int compareTo(CResetStoryEctypeOpenCount _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

