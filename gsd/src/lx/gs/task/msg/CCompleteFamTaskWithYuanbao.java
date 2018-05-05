
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.logger.By;
import lx.gs.task.FTask;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCompleteFamTaskWithYuanbao__ extends xio.Protocol { }

/** 使用元宝快速完成当前小环任务,但是还要去完成npc处提交任务
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCompleteFamTaskWithYuanbao extends __CCompleteFamTaskWithYuanbao__ {
	@Override
	protected void process() {
		new AProcedure<CCompleteFamTaskWithYuanbao>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleTask taskInfo = FTask.getTask(roleid);
				ErrorCode ret = FCondition.checkA(roleid,CfgMgr.familytaskconfig.completecost, 1, By.Family_Task, -1,-1);
				if(ret.err()){
					return error(ret);
				}
				taskInfo.setIsuseyuanbao(1);//用一个标志位来判断
				SCompleteFamTaskWithYuanbao response = new SCompleteFamTaskWithYuanbao();
				response.curtaskorder = curtaskorder;
				response(response);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563919;

	public int getType() {
		return 6563919;
	}

	public int curtaskorder; // 当前正在做第几环

	public CCompleteFamTaskWithYuanbao() {
	}

	public CCompleteFamTaskWithYuanbao(int _curtaskorder_) {
		this.curtaskorder = _curtaskorder_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(curtaskorder);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		curtaskorder = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CCompleteFamTaskWithYuanbao) {
			CCompleteFamTaskWithYuanbao _o_ = (CCompleteFamTaskWithYuanbao)_o1_;
			if (curtaskorder != _o_.curtaskorder) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += curtaskorder;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(curtaskorder).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CCompleteFamTaskWithYuanbao _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = curtaskorder - _o_.curtaskorder;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

