
package lx.gs.family.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.family.FFamily;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCallAllFamilyMembers__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCallAllFamilyMembers extends __CCallAllFamilyMembers__ {
	@Override
	protected void process() {
		new AProcedure<CCallAllFamilyMembers>(this){
            @Override
            protected boolean doProcess() throws Exception {
                xbean.Family familyInfo = FFamily.getFamilyByRoleId(roleid);
                if(!FFamily.isFamilyLeader(roleid, familyInfo)){
                    return error(ErrorCode.ONLY_CHIEF_VICECHIEF_CAN_ACTION);
                }
                long now = java.lang.System.currentTimeMillis();
                if(now - familyInfo.getLastpartyopentime() >= CfgMgr.familyparty.duration * 1000){
                    return error(ErrorCode.PARTY_TIME_HAS_PAST);
                }
                if(now - familyInfo.getLastpartycalltime() < 60 * 1000){
                    return error(ErrorCode.NOT_THE_CALL_TIME);
                }
                SFamilyPartyOpenNotify notify = new SFamilyPartyOpenNotify();
                notify.openid = roleid;
                FFamily.multicastAllFamily(familyInfo, notify);
                familyInfo.setLastpartycalltime(now);
                SCallAllFamilyMembers response = new SCallAllFamilyMembers();
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584717;

	public int getType() {
		return 6584717;
	}


	public CCallAllFamilyMembers() {
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
		if (_o1_ instanceof CCallAllFamilyMembers) {
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

	public int compareTo(CCallAllFamilyMembers _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

