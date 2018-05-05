
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.FFamily;
import lx.gs.task.FTask;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetFamilyTask__ extends xio.Protocol { }

/** 环任务协议
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetFamilyTask extends __CGetFamilyTask__ {
	@Override
	protected void process() {
		new AProcedure<CGetFamilyTask>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				if(FFamily.getRoleFamilyInfo(roleid).getCurrentfid() == 0){//没加入家族，不能接取家族任务
					return error(ErrorCode.NOT_IN_FAMILY);
				}
				if(npcid != FTask.FAMILY_NPCID){
					return error(ErrorCode.NOT_FAMILY_NPC);
				}
				xbean.RoleTask taskInfo = FTask.getTask(roleid);
				cfg.family.FamilyTaskConfig conf = CfgMgr.familytaskconfig;
				if(taskInfo.getDailycompletedfamtasks() >= conf.daycirclelimit.num){
					return error(ErrorCode.CAN_NOT_ACCEPT_FAMILY_TASK);
				}
				assert(taskInfo.getAcceptedfamilytasks().size() < conf.circletaskamount);
				final int rolelvl = xtable.Roleinfos.get(roleid).getLevel();
				if(rolelvl < conf.openlevel){
					return error(ErrorCode.NOT_ENOUGH_LEVEL);
				}
                if(taskInfo.getCompletednumsinfamtasks() < taskInfo.getAcceptedfamilytasks().size()){
                    return error(ErrorCode.PRE_TASK_HAS_NOT_COMPLETE);
                }
				if(taskInfo.getIscancletask() == 1) {//如果是取消任务后再来接取的话
                    if (System.currentTimeMillis() - taskInfo.getLastgiveupfamtasktime() < conf.refreshtime * 1000) {
                        return error(ErrorCode.CAN_NOT_ACCEPT_FAMILY_TASK);
                    }
                    taskInfo.setIscancletask(0);
                }
//				}else {//如果是第一次来接取
//					assert(taskInfo.getAcceptedfamilytasks().size() == 0);
//				}
				SGetFamilyTask response = new SGetFamilyTask();
    			xbean.FamilyTaskDetail newtask = FTask.genOneFamilytask(rolelvl, taskInfo);
                if(newtask.getTaskid() == 0 ){
                    return false;
                }
//                xbean.FamilyTaskDetail newtask = FTask.genOneFamilytaskForTest(rolelvl, taskInfo);
				response.npcid = npcid;
				response.taskinfo.npcid = newtask.getNpcid();
				response.taskinfo.taskid = newtask.getTaskid();
				response(response);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572602;

	public int getType() {
		return 6572602;
	}

	public int npcid; // 环任务最开始都是在家族npc处获取，放弃任务后，再接取任务也是去家族npc接取

	public CGetFamilyTask() {
	}

	public CGetFamilyTask(int _npcid_) {
		this.npcid = _npcid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(npcid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		npcid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetFamilyTask) {
			CGetFamilyTask _o_ = (CGetFamilyTask)_o1_;
			if (npcid != _o_.npcid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += npcid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(npcid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetFamilyTask _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = npcid - _o_.npcid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

