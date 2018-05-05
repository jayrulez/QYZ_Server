
package lx.gs.activity.msg;

import cfg.CfgMgr;
import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.OperationStatus;
import cfg.operationalactivity.OperationalActivity;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.activity.ActivityModule;
import lx.gs.activity.FActivity;
import lx.gs.activity.operational.OperationalActivityHandler;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CReceiveActivityBonus__ extends xio.Protocol { }

/** 领取奖励,
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CReceiveActivityBonus extends __CReceiveActivityBonus__ {
	@Override
	protected void process() {
		new AProcedure<CReceiveActivityBonus>(this){
            @Override
            protected boolean doProcess() throws Exception {
				OperationalActivity operationalActivity = CfgMgr.operationalactivity.get(id);
				if(operationalActivity == null
						|| !FActivity.isOpenTime(id, cmdid)
						|| !operationalActivity.activityinfo_id.containsKey(cmdid)
						){
					return error(ErrorCode.ACTIVITY_NOT_EXIST);
				}
				int currStatus = FActivity.currStatus(roleid, id, cmdid);

				if(currStatus == OperationStatus.GETREWARD){
					return error(ErrorCode.ACTIVITY_HAS_GET_REWARD);
				}

				if(currStatus != OperationStatus.COMPLETE){
					return error(ErrorCode.ACTIVITY_NOT_COMPLETE);
				}

				ActivityEntry activityEntry = operationalActivity.activityinfo_id.get(cmdid);
				OperationalActivityHandler handler = ActivityModule.INSTACNE.getOperationalActivityHandler(activityEntry.condition.getClass());

				ErrorCode errorCode = handler.handleCondition(roleid, activityEntry);
				if(errorCode.err()){
					return error(errorCode);
				}

				if(!handler.takeReward(roleid, activityEntry)){
					return false;
				}

				SReceiveActivityBonus response = new SReceiveActivityBonus();
				response.id = id;
				response.cmdid = cmdid;
				response(response);
				return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571688;

	public int getType() {
		return 6571688;
	}

	public int id; // 运营活动id
	public int cmdid; // 具体的配置条目id

	public CReceiveActivityBonus() {
	}

	public CReceiveActivityBonus(int _id_, int _cmdid_) {
		this.id = _id_;
		this.cmdid = _cmdid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(cmdid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_int();
		cmdid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CReceiveActivityBonus) {
			CReceiveActivityBonus _o_ = (CReceiveActivityBonus)_o1_;
			if (id != _o_.id) return false;
			if (cmdid != _o_.cmdid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += id;
		_h_ += cmdid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(cmdid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CReceiveActivityBonus _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = id - _o_.id;
		if (0 != _c_) return _c_;
		_c_ = cmdid - _o_.cmdid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

