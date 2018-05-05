
package lx.gs.chat.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CReportPlayer__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CReportPlayer extends __CReportPlayer__ {
	@Override
	protected void process() {
		new AProcedure<CReportPlayer>(this){
            @Override
            protected boolean doProcess() throws Exception {
                xbean.RoleInfo reportRoleInfo = FBonus.getRoleInfo(roleid);
                if(reportRoleInfo.getLevel() < CfgMgr.roleconfig.minreportlevel){ //report level limit
                    return error(ErrorCode.NOT_ENOUGH_LEVEL);
                }
                int leftTimes = reportRoleInfo.getLeftreporttime();
                if(leftTimes <= 0){
                    return error(ErrorCode.NO_LEFT_REPORT_TIME);
                }
                reportRoleInfo.setLeftreporttime(leftTimes - 1);
                xbean.RoleInfo beReportRoleInfo = FBonus.getRoleInfo(bereportid);
                int hasBeReportTime = beReportRoleInfo.getBereportedtime();
                if(hasBeReportTime + 1 >= CfgMgr.roleconfig.bereportednum.get(beReportRoleInfo.getViplevel())){//if reach the silent limit
                    long endSilentTime = System.currentTimeMillis() + CfgMgr.roleconfig.silenttime.get(beReportRoleInfo.getViplevel()) * 1000L;
                    beReportRoleInfo.setSilentendtime(endSilentTime);
                    beReportRoleInfo.setBereportedtime(0);
                    SBeSilentNotify notify = new SBeSilentNotify();
                    notify.silentendtime = endSilentTime;
                    xdb.Transaction.tsendWhileCommit(bereportid, notify);
                }else {
                    beReportRoleInfo.setBereportedtime(hasBeReportTime + 1);
                }
                SReportPlayer response = new SReportPlayer();
                response.bereportid = bereportid;
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565161;

	public int getType() {
		return 6565161;
	}

	public long bereportid; // 被举报的玩家的roleid

	public CReportPlayer() {
	}

	public CReportPlayer(long _bereportid_) {
		this.bereportid = _bereportid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bereportid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bereportid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CReportPlayer) {
			CReportPlayer _o_ = (CReportPlayer)_o1_;
			if (bereportid != _o_.bereportid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)bereportid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bereportid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CReportPlayer _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(bereportid - _o_.bereportid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

