
package lx.gs.friend.msg;

import cfg.friend.MaimaiRelationship;
import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.friend.msg.CRequestMM;
import lx.gs.friend.FFriend;
import lx.gs.friend.msg.SRequestMM;
import lx.gs.friend.msg.SRequestMMNotify;
import xbean.RoleRelation;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRequestMM__ extends xio.Protocol { }

/** 申请脉脉关系
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRequestMM extends __CRequestMM__ {
	@Override
	protected void process() {
		new AProcedure<CRequestMM>(this) {

			@Override
			protected boolean doProcess() throws Exception {
			    long myid = roleid;
                long toid = param.roleid;
				gnet.link.Role onlinerole = gnet.link.Onlines.getInstance().find(toid);
				if (null == onlinerole) {
					return error(ErrorCode.ROLE_NOT_ONLINE);
				}
				SRequestMM result = new SRequestMM();
				xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(myid);
				xbean.RoleFriendsInfo toinfo = FFriend.getRoleFriendsInfo(toid);
                cfg.friend.MaimaiRelationship mcfg = CfgMgr.maimairelationship.get(mmtype);
				//如果需要判断好友关系
				if(mcfg.basicrelation == cfg.friend.BasicRelationshipType.Friend){
					if (!toinfo.getFriends().containsKey(myid) || !myinfo.getFriends().containsKey(toid)) {
						return error(ErrorCode.FRIEND_NOT_FRIEND);
					}
				}

				final int myGender = xtable.Roleinfos.selectGender(myid);
                final int toGender = xtable.Roleinfos.selectGender(toid);

				if (mcfg.reqgender != -1 && mcfg.reqgender != myGender) {
					return error(ErrorCode.FRIEND_MY_ERROR_GENDER);
				}

				if (mcfg.gender != -1 && mcfg.gender != toGender) {
					return error(ErrorCode.FRIEND_PEER_ERROR_GENDER);
				}

				RoleRelation roleRelation = myinfo.getRelationinfo().get(mmtype);
				if (roleRelation != null) {
					if (roleRelation.getRolelist().size() >= mcfg.maxnum) {
						return error(ErrorCode.FRIEND_MY_MM_OVERFLOW);
					}
					if (roleRelation.getRolelist().contains(toid)) {
						return error(ErrorCode.FRIEND_ALREADY_MM);
					}
				}

				int cormmtype = myGender == cfg.role.GenderType.MALE ? mcfg.correspondingrelationshipmale : mcfg.correspondingrelationshipfemale;
				if (cormmtype != -1) {
					RoleRelation roleRelation2 = toinfo.getRelationinfo().get(cormmtype);
					if (roleRelation2 != null) {
						if (roleRelation2.getRolelist().size() >= CfgMgr.maimairelationship.get(cormmtype).maxnum) {
							return error(ErrorCode.FRIEND_PEER_MM_OVERFLOW);
						}
					}
				}else{
					return error(ErrorCode.FRIEND_MY_ERROR_GENDER);
				}

				SRequestMMNotify notify = new SRequestMMNotify();
				notify.mmtype = mmtype;
				notify.mmroleinfo = FFriend.makeProtocolRoleShowInfo(myid, 0);
				tsend(toid, notify);

				result.result = ErrorCode.OK.getErrorId();
				result.mmtype = mmtype;
				xdb.Transaction.tsendWhileCommit(myid, result);

				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577475;

	public int getType() {
		return 6577475;
	}

	public long roleid;
	public int mmtype;

	public CRequestMM() {
	}

	public CRequestMM(long _roleid_, int _mmtype_) {
		this.roleid = _roleid_;
		this.mmtype = _mmtype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(mmtype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		mmtype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRequestMM) {
			CRequestMM _o_ = (CRequestMM)_o1_;
			if (roleid != _o_.roleid) return false;
			if (mmtype != _o_.mmtype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += mmtype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(mmtype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRequestMM _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = mmtype - _o_.mmtype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
