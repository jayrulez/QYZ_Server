
package lx.gs.friend.msg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import cfg.achievement.CounterType;
import cfg.friend.MaimaiRelationshipType;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.achievement.FAchievement;
import lx.gs.friend.FFriend;
import xbean.RoleFriend;
import xbean.RoleRelation;
import xdb.Lockeys;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAcceptMM__ extends xio.Protocol { }

/** 接受脉脉关系
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAcceptMM extends __CAcceptMM__ {
	@Override
	protected void process() {
		new AProcedure<CAcceptMM>(this) {

			@Override
			protected boolean doProcess() throws Exception {
			    final long myid = this.roleid;
                final long toid = param.roleid;
                Lockeys.lock(xtable.Rolefriendsinfo.getTable(), Arrays.asList(myid, toid));

				SAcceptMM result = new SAcceptMM();
				Map<Long, RoleFriend> myFriends = FFriend.getRoleFriendsInfo(myid).getFriends();
                Map<Long, RoleFriend> toFriends = FFriend.getRoleFriendsInfo(toid).getFriends();

                final cfg.friend.MaimaiRelationship mcfg = CfgMgr.maimairelationship.get(mmtype);

				if(mcfg.basicrelation == cfg.friend.BasicRelationshipType.Friend){
					if (!toFriends.containsKey(myid) || !myFriends.containsKey(toid)) {
						return error(ErrorCode.FRIEND_NOT_FRIEND);
					}
				}

				final int myGender = xtable.Roleinfos.selectGender(myid);
                final int toGender = xtable.Roleinfos.selectGender(toid);

				if (mcfg.reqgender != -1 && mcfg.reqgender != toGender) {
					return error(ErrorCode.FRIEND_MY_ERROR_GENDER);
				}

				if (mcfg.gender != -1 && mcfg.gender != myGender) {
					return error(ErrorCode.FRIEND_PEER_ERROR_GENDER);
				}

				java.util.Map<Integer, xbean.RoleRelation> mymminfo = FFriend.getRoleFriendsInfo(myid)
						.getRelationinfo();
				java.util.Map<Integer, xbean.RoleRelation> tomminfo = FFriend.getRoleFriendsInfo(toid)
						.getRelationinfo();
				
				if(mymminfo.values().stream().anyMatch(m -> m.getRolelist().contains(toid))) {
				    return error(ErrorCode.FRIEND_ALREADY_MM);
                }

                if(tomminfo.values().stream().anyMatch(m -> m.getRolelist().contains(myid))) {
                    return error(ErrorCode.FRIEND_ALREADY_MM);
                }

				RoleRelation roleRelation = tomminfo.get(mmtype);
				if (roleRelation != null) {
					if (roleRelation.getRolelist().size() >= mcfg.maxnum) {
						return error(ErrorCode.FRIEND_PEER_MM_OVERFLOW);
					}
				} else {
					roleRelation = xbean.Pod.newRoleRelation();
					tomminfo.put(mmtype, roleRelation);
				}

				int mymmtype = toGender == cfg.role.GenderType.MALE ? mcfg.correspondingrelationshipmale : mcfg.correspondingrelationshipfemale;

                RoleRelation roleRelation2 = mymminfo.get(mymmtype);
                if (roleRelation2 != null) {
                    if (roleRelation2.getRolelist().size() >= CfgMgr.maimairelationship.get(mymmtype).maxnum) {
                        return error(ErrorCode.FRIEND_PEER_MM_OVERFLOW);
                    }
                }else {
                    roleRelation2 = xbean.Pod.newRoleRelation();
                    mymminfo.put(mymmtype, roleRelation2);
                }

				mymminfo.get(mymmtype).getRolelist().add(toid);
				tomminfo.get(mmtype).getRolelist().add(myid);

				if(mcfg.basicrelation == cfg.friend.BasicRelationshipType.Friend){
                    myFriends.get(toid).setRelation(mymmtype);
                    toFriends.get(myid).setRelation(mmtype);
				}
				
                FFriend.updateRelationAchievement(myid, mymminfo, mymmtype);
                FFriend.updateRelationAchievement(toid, tomminfo, mmtype);

				SAcceptMMNotify notify = new SAcceptMMNotify();
				notify.mmtype = mmtype;
				notify.mmroleinfo = FFriend.makeProtocolRoleShowInfo(myid, 0);
				tsend(toid, notify);

				result.result = ErrorCode.OK.getErrorId();
				result.mmtype = mymmtype;
				result.mmroleinfo = FFriend.makeProtocolRoleShowInfo(toid, 0);
				response(result);

				return true;
			}



		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578966;

	public int getType() {
		return 6578966;
	}

	public long roleid;
	public int reqmmtype;
	public int mmtype;

	public CAcceptMM() {
	}

	public CAcceptMM(long _roleid_, int _reqmmtype_, int _mmtype_) {
		this.roleid = _roleid_;
		this.reqmmtype = _reqmmtype_;
		this.mmtype = _mmtype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(reqmmtype);
		_os_.marshal(mmtype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		reqmmtype = _os_.unmarshal_int();
		mmtype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAcceptMM) {
			CAcceptMM _o_ = (CAcceptMM)_o1_;
			if (roleid != _o_.roleid) return false;
			if (reqmmtype != _o_.reqmmtype) return false;
			if (mmtype != _o_.mmtype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += reqmmtype;
		_h_ += mmtype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(reqmmtype).append(",");
		_sb_.append(mmtype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAcceptMM _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = reqmmtype - _o_.reqmmtype;
		if (0 != _c_) return _c_;
		_c_ = mmtype - _o_.mmtype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
