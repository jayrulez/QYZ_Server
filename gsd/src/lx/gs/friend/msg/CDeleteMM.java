
package lx.gs.friend.msg;

import cfg.role.GenderType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.friend.FFriend;
import lx.gs.friend.msg.SDeleteMM;
import lx.gs.friend.msg.SDeleteMMNotify;
import xbean.RoleRelation;
import xdb.Lockeys;

import java.util.Arrays;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CDeleteMM__ extends xio.Protocol { }

/** 删除脉脉
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CDeleteMM extends __CDeleteMM__ {
	@Override
	protected void process() {
		new AProcedure<CDeleteMM>(this) {

			@Override
			protected boolean doProcess() throws Exception {
			    long myid = roleid;
                long toid = param.roleid;
                lock(xtable.Rolefriendsinfo.getTable(), Arrays.asList(myid, toid));

				SDeleteMM result = new SDeleteMM();
				xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(myid);
				xbean.RoleFriendsInfo toinfo = FFriend.getRoleFriendsInfo(toid);

                final cfg.friend.MaimaiRelationship mcfg = CfgMgr.maimairelationship.get(mmtype);
				if(mcfg.basicrelation == cfg.friend.BasicRelationshipType.Friend){
                    myinfo.getFriends().get(toid).setRelation(0);
                    toinfo.getFriends().get(myid).setRelation(0);
				}

				xbean.RoleInfo myroleinfo = xtable.Roleinfos.get(myid);
				java.util.Map<Integer, xbean.RoleRelation> mymminfo = FFriend.getRoleFriendsInfo(myid).getRelationinfo();
				java.util.Map<Integer, xbean.RoleRelation> tomminfo = FFriend.getRoleFriendsInfo(toid).getRelationinfo();

				RoleRelation roleRelation = mymminfo.get(mmtype);
				if (roleRelation == null || !roleRelation.getRolelist().contains(toid)) {
					return error(ErrorCode.FRIEND_NOT_MM);
				}

				int mymmtype = myroleinfo.getGender() == GenderType.MALE ? mcfg.correspondingrelationshipmale : mcfg.correspondingrelationshipfemale;

				RoleRelation roleRelation2 = tomminfo.get(mymmtype);
				if (roleRelation2 == null || !roleRelation2.getRolelist().contains(myid)) {
					return error(ErrorCode.FRIEND_NOT_MM);
				}

				roleRelation.getRolelist().remove(toid);
				roleRelation2.getRolelist().remove(myid);


				SDeleteMMNotify notify = new SDeleteMMNotify();
				notify.mmtype = mymmtype;
				notify.roleid = myid;
				notify.rolename = myroleinfo.getName();
				tsend(toid, notify);

				result.result = ErrorCode.OK.getErrorId();
				result.mmtype = mmtype;
				result.roleid = toid;
				response(result);
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565051;

	public int getType() {
		return 6565051;
	}

	public long roleid;
	public int mmtype;

	public CDeleteMM() {
	}

	public CDeleteMM(long _roleid_, int _mmtype_) {
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
		if (_o1_ instanceof CDeleteMM) {
			CDeleteMM _o_ = (CDeleteMM)_o1_;
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

	public int compareTo(CDeleteMM _o_) {
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
