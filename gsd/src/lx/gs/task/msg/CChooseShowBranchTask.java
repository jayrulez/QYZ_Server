
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.task.FTask;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CChooseShowBranchTask__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CChooseShowBranchTask extends __CChooseShowBranchTask__ {
	@Override
	protected void process() {
		new AProcedure<CChooseShowBranchTask>(this){
			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleTask taskInfo = FTask.getTask(roleid);
				if(guidebranchtaskid <= 0){
					return error(ErrorCode.PARAM_ERROR);
				}
				taskInfo.setGuidebranchtaskid(guidebranchtaskid);
				SChooseShowBranchTask response = new SChooseShowBranchTask();
				response.guidebranchtaskid = guidebranchtaskid;
				response(response);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554312;

	public int getType() {
		return 6554312;
	}

	public int guidebranchtaskid;

	public CChooseShowBranchTask() {
	}

	public CChooseShowBranchTask(int _guidebranchtaskid_) {
		this.guidebranchtaskid = _guidebranchtaskid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(guidebranchtaskid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		guidebranchtaskid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CChooseShowBranchTask) {
			CChooseShowBranchTask _o_ = (CChooseShowBranchTask)_o1_;
			if (guidebranchtaskid != _o_.guidebranchtaskid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += guidebranchtaskid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(guidebranchtaskid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CChooseShowBranchTask _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = guidebranchtaskid - _o_.guidebranchtaskid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

