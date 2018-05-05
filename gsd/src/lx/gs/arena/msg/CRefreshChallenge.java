
package lx.gs.arena.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gs.AProcedure;
import lx.gs.arena.FArena;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.logger.By;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRefreshChallenge__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRefreshChallenge extends __CRefreshChallenge__ {
	@Override
	protected void process() {
		new AProcedure<CRefreshChallenge>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final ErrorCode err = FCondition.checkA(roleid, CfgMgr.arenaconfig.refreshopponentlimit, 1, By.Arena_Challenge_Success, ConfigId.ARENA_REFRESH_OPPONENT, 0);
				if(err.err())
					return error(err);
				final xbean.RoleArena info = FArena.get(roleid);
				FArena.refreshChallengeRanks(info, FArena.getRank(roleid));
				response(FArena.createSGetChallenge(info));
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568007;

	public int getType() {
		return 6568007;
	}


	public CRefreshChallenge() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRefreshChallenge) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRefreshChallenge _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

