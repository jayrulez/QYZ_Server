
package lx.gs.task.msg;

import cfg.CfgMgr;
import cfg.ectype.EctypeType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import xdb.Trace;
import gs.AProcedure;
import lx.gs.map.FMap;
import lx.gs.task.FTask;
import lx.gs.task.TaskModule;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __COpenTaskEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class COpenTaskEctype extends __COpenTaskEctype__ {
	@Override
	protected void process() {
		new AProcedure<COpenTaskEctype>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if (!FMap.isInWorldOrFamily(roleid))
					return error(ErrorCode.CANNOT_ENTER_ECTYPE_IN_ECTYPE);
				final lx.gs.task.Task task = TaskModule.tasks.get(taskid);
				final int ectypeid = task.getNeedFinishEctypeCfg().id;
                final cfg.ectype.EctypeBasic bcfg = CfgMgr.ectypebasic.get(ectypeid);
                if(bcfg == null) {
                    return error(ErrorCode.TASK_HAS_NOT_ECTYPEID);
                }
				final xbean.RoleTask info = FTask.getTask(roleid);
				if (!FTask.isAccept(info, taskid))
					return error(ErrorCode.NOT_IN_TASK_LIST);
                if(bcfg.type == EctypeType.STORY) {
                    final cfg.ectype.StoryEctype scfg = CfgMgr.storyectype.get(ectypeid);
                    final int lastStar = FMap.getLastStar(FMap.getChapter(roleid, scfg.chapter), scfg.section);
                    Trace.info("COpenStoryEctype roleid:{} ectypeid:{} lastStar:{}", roleid, ectypeid, lastStar);
                    FMap.openOnePlayerEctypeInProcedure(roleid, ectypeid, true, lastStar);
                } else if(bcfg.type == EctypeType.PLAIN_STORY) {
                    FMap.openOnePlayerEctypeInProcedure(roleid, ectypeid, true, 0);
                } else {
                    return error(ErrorCode.TASK_HAS_NOT_ECTYPEID);
                }

				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6567638;

	public int getType() {
		return 6567638;
	}

	public int taskid;

	public COpenTaskEctype() {
	}

	public COpenTaskEctype(int _taskid_) {
		this.taskid = _taskid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(taskid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		taskid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof COpenTaskEctype) {
			COpenTaskEctype _o_ = (COpenTaskEctype)_o1_;
			if (taskid != _o_.taskid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += taskid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(taskid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(COpenTaskEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = taskid - _o_.taskid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

