
package lx.gs.arena.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.arena.ArenaModule;
import lx.gs.arena.FArena;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import map.msg.XCreateArenaEctype;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CChallenge__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CChallenge extends __CChallenge__ {
	@Override
	protected void process() {
		new AProcedure<CChallenge>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				ErrorCode err = FCondition.checkA(roleid, CfgMgr.arenaconfig.challengelimit, 1, By.Arena_Challenge_Success, ConfigId.ARENA_CHALLENGE, 0);
				if(err.err())
					return error(err);
				// 可能会死锁,概率低,无所谓了,真发生时让系统打断吧
				final xbean.RoleArena info = FArena.get(roleid);
				if(!info.getOpen())
					return error("你还不能参与竞技场");
				if(!info.getChallengeranks().contains(rank))
					return error(ErrorCode.ARENA_NOT_IN_CHALLENGE_LIST);
				final long now = System.currentTimeMillis();
				if(info.getBechallenging() > 0 && info.getBechallengelockexpiretime() > now)
					return error(ErrorCode.ARENA_SELF_LOCKED);


				final long opponentid = FArena.getOpponentByRank(info, rank);
				if(opponentid <= 0)
					return error(ErrorCode.INTERNAL_ERR);
				final xbean.RoleArena other = FArena.get(opponentid);
                final long lockExpireTime = now + 300 * 1000;
                info.setBechallenging(1);
                info.setBechallengelockexpiretime(lockExpireTime);

				if(info.getHaswin()) {
                    if(other.getBechallenging() > 0 && other.getBechallengelockexpiretime() > now)
                        return error(ErrorCode.ARENA_CHALLENGER_LOCKED);

                    other.setBechallenging(1);
                    other.setBechallengelockexpiretime(lockExpireTime);
                }

                final XCreateArenaEctype msg = new XCreateArenaEctype();
                msg.ectypeid = FMap.getArenaEctypeid();
                msg.serverid = gs.Utils.getServerId();
                msg.roleid = roleid;
                msg.profession = xtable.Roleinfos.selectProfession(roleid);
				msg.opponent = FArena.createFakePlayerBuilder(opponentid);
                FMap.openEctypeInProcedure(roleid, msg.serverid, msg);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6567929;

	public int getType() {
		return 6567929;
	}

	public int rank;

	public CChallenge() {
	}

	public CChallenge(int _rank_) {
		this.rank = _rank_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(rank);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		rank = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CChallenge) {
			CChallenge _o_ = (CChallenge)_o1_;
			if (rank != _o_.rank) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += rank;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(rank).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CChallenge _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = rank - _o_.rank;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

