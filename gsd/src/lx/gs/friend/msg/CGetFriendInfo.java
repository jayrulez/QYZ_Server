
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.friend.FFriend;
import lx.gs.friend.msg.SGetFriendInfo;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetFriendInfo__ extends xio.Protocol { }

/** 第一次打开好友列表时请求，之后不用再请求
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetFriendInfo extends __CGetFriendInfo__ {
	@Override
	protected void process() {
		new AProcedure<CGetFriendInfo>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				SGetFriendInfo result = new SGetFriendInfo();
				xbean.RoleFriendsInfo myinfo = xtable.Rolefriendsinfo.select(roleid);

                if(myinfo != null) {
                    for (java.util.Map.Entry<Long, xbean.RoleFriend> e : myinfo.getFriends().entrySet()) {
                        result.friendinfo.put(e.getKey(), FFriend.makeProtocolBasicFriendInfo(e.getKey(), e.getValue()));
                    }
                    for (java.util.Map.Entry<Long, Long> e : myinfo.getBlacklist().entrySet()) {
                        result.blackinfo.put(e.getKey(), FFriend.makeProtocolRoleShowInfo(e.getKey(), e.getValue()));
                    }
                    for (java.util.Map.Entry<Long, Long> e : myinfo.getRequesting().entrySet()) {
                        result.requestinginfo.put(e.getKey(), FFriend.makeProtocolRoleShowInfo(e.getKey(), e.getValue()));
                    }
                    for (java.util.Map.Entry<Long, xbean.Enemyinfo> e : myinfo.getEnemylist().entrySet()) {
                        result.enemyinfo.put(e.getKey(), FFriend.makeProtocolEnemyShowInfo(e.getKey(), e.getValue()));
                    }

                    for (long idolid : cfg.CfgMgr.idol.keySet()) {
                        lx.gs.friend.msg.IdolInfo idolInfo = new lx.gs.friend.msg.IdolInfo();
                        xtable.Idolcharm.getTable().select(idolid, charmInfo -> {
                                    idolInfo.charm = charmInfo.getCharm();
                                    idolInfo.guardid = charmInfo.getGuardid();
                                    idolInfo.guardtime = charmInfo.getGuardtime();
                                    idolInfo.guarddegree = charmInfo.getGuarddegree();
                                    return charmInfo;
                                });
                        if(idolInfo.guardid > 0) {
                            xtable.Roleinfos.getTable().select(idolInfo.guardid, info -> {
                                idolInfo.guardname = info.getName();
                                return info;
                            });
                        }
                        result.idolcharminfo.put(idolid, idolInfo);
                    }

                    for (java.util.Map.Entry<Long, xbean.IdolAwardClaim> e : myinfo.getIdolawardclaiminfo().entrySet()) {
                        lx.gs.friend.msg.IdolAwardClaim cl = new lx.gs.friend.msg.IdolAwardClaim();
                        cl.claiminfo.addAll(e.getValue().getClaiminfo());
                        result.myinfo.idolawardclaiminfo.put(e.getKey(), cl);
                    }
                    result.myinfo.idolfrienddegree.putAll(myinfo.getIdolfrienddegree());

                    for (java.util.Map.Entry<Integer, xbean.RoleRelation> e : myinfo.getRelationinfo().entrySet()) {
                        lx.gs.friend.msg.MMInfoList info = new lx.gs.friend.msg.MMInfoList();
                        for (long rid : e.getValue().getRolelist()) {
                            info.mmlist.add(FFriend.makeProtocolRoleShowInfo(rid, 0));
                        }
                        result.myinfo.relations.put(e.getKey(), info);
                    }
                    result.myinfo.allowfriendgetmm = myinfo.getIsallowfriendgetmm();
                    result.myinfo.allowstrangergetmm = myinfo.getIsallowstrangergetmm();
                }
				return response(result);
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553901;

	public int getType() {
		return 6553901;
	}


	public CGetFriendInfo() {
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
		if (_o1_ instanceof CGetFriendInfo) {
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

	public int compareTo(CGetFriendInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
