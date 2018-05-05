
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __STask__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class STask extends __STask__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554300;

	public int getType() {
		return 6554300;
	}

	public java.util.HashMap<Integer,Integer> variables;
	public java.util.HashMap<Integer,Integer> completehistory;
	public java.util.ArrayList<lx.gs.task.msg.TaskInfo> acceptedtasks; // 正在做的任务信息
	public java.util.ArrayList<lx.gs.task.msg.FamilyTaskInfo> curfamtasks; // 当前已经获取的所有环任务
	public int completefamtasknum; // 在一组环任务内已经完成的环数
	public int comdaycycle; // 已经完成的日环任务组数
	public int comweeksmallcycle; // 已经完成的周环任务组数
	public long lastgiveuofamtime; // 上次放弃家族任务的时间
	public java.util.HashSet<Integer> shownpcs; // 需要显示的npc集合
	public java.util.HashSet<Integer> hidemines; // 需要隐藏的矿物集合
	public int isuseyuanbaocomtask; // 标识是否使用元宝完成家族环任务，0：否；1：是
	public int iscanclefamtask; // 标识是否放弃过家族环任务,0:否；1：是
	public java.util.ArrayList<Integer> receivedweekbonus; // 已经领取过的周完成环数奖励情况
	public int guidebranchtaskid; // 主界面上要显示支线任务
	public java.util.ArrayList<Integer> allcandobranch; // 所有满足条件的支线的第一个任务
	public java.util.ArrayList<Integer> unlockcomtasks; // 客户端解锁模块中已经完成的任务

	public STask() {
		variables = new java.util.HashMap<Integer,Integer>();
		completehistory = new java.util.HashMap<Integer,Integer>();
		acceptedtasks = new java.util.ArrayList<lx.gs.task.msg.TaskInfo>();
		curfamtasks = new java.util.ArrayList<lx.gs.task.msg.FamilyTaskInfo>();
		shownpcs = new java.util.HashSet<Integer>();
		hidemines = new java.util.HashSet<Integer>();
		receivedweekbonus = new java.util.ArrayList<Integer>();
		allcandobranch = new java.util.ArrayList<Integer>();
		unlockcomtasks = new java.util.ArrayList<Integer>();
	}

	public STask(java.util.HashMap<Integer,Integer> _variables_, java.util.HashMap<Integer,Integer> _completehistory_, java.util.ArrayList<lx.gs.task.msg.TaskInfo> _acceptedtasks_, java.util.ArrayList<lx.gs.task.msg.FamilyTaskInfo> _curfamtasks_, int _completefamtasknum_, int _comdaycycle_, int _comweeksmallcycle_, long _lastgiveuofamtime_, java.util.HashSet<Integer> _shownpcs_, java.util.HashSet<Integer> _hidemines_, int _isuseyuanbaocomtask_, int _iscanclefamtask_, java.util.ArrayList<Integer> _receivedweekbonus_, int _guidebranchtaskid_, java.util.ArrayList<Integer> _allcandobranch_, java.util.ArrayList<Integer> _unlockcomtasks_) {
		this.variables = _variables_;
		this.completehistory = _completehistory_;
		this.acceptedtasks = _acceptedtasks_;
		this.curfamtasks = _curfamtasks_;
		this.completefamtasknum = _completefamtasknum_;
		this.comdaycycle = _comdaycycle_;
		this.comweeksmallcycle = _comweeksmallcycle_;
		this.lastgiveuofamtime = _lastgiveuofamtime_;
		this.shownpcs = _shownpcs_;
		this.hidemines = _hidemines_;
		this.isuseyuanbaocomtask = _isuseyuanbaocomtask_;
		this.iscanclefamtask = _iscanclefamtask_;
		this.receivedweekbonus = _receivedweekbonus_;
		this.guidebranchtaskid = _guidebranchtaskid_;
		this.allcandobranch = _allcandobranch_;
		this.unlockcomtasks = _unlockcomtasks_;
	}

	public final boolean _validator_() {
		for (lx.gs.task.msg.TaskInfo _v_ : acceptedtasks)
			if (!_v_._validator_()) return false;
		for (lx.gs.task.msg.FamilyTaskInfo _v_ : curfamtasks)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(variables.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : variables.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(completehistory.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : completehistory.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(acceptedtasks.size());
		for (lx.gs.task.msg.TaskInfo _v_ : acceptedtasks) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(curfamtasks.size());
		for (lx.gs.task.msg.FamilyTaskInfo _v_ : curfamtasks) {
			_os_.marshal(_v_);
		}
		_os_.marshal(completefamtasknum);
		_os_.marshal(comdaycycle);
		_os_.marshal(comweeksmallcycle);
		_os_.marshal(lastgiveuofamtime);
		_os_.compact_uint32(shownpcs.size());
		for (Integer _v_ : shownpcs) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(hidemines.size());
		for (Integer _v_ : hidemines) {
			_os_.marshal(_v_);
		}
		_os_.marshal(isuseyuanbaocomtask);
		_os_.marshal(iscanclefamtask);
		_os_.compact_uint32(receivedweekbonus.size());
		for (Integer _v_ : receivedweekbonus) {
			_os_.marshal(_v_);
		}
		_os_.marshal(guidebranchtaskid);
		_os_.compact_uint32(allcandobranch.size());
		for (Integer _v_ : allcandobranch) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(unlockcomtasks.size());
		for (Integer _v_ : unlockcomtasks) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			variables.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			completehistory.put(_k_, _v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.task.msg.TaskInfo _v_ = new lx.gs.task.msg.TaskInfo();
			_v_.unmarshal(_os_);
			acceptedtasks.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.task.msg.FamilyTaskInfo _v_ = new lx.gs.task.msg.FamilyTaskInfo();
			_v_.unmarshal(_os_);
			curfamtasks.add(_v_);
		}
		completefamtasknum = _os_.unmarshal_int();
		comdaycycle = _os_.unmarshal_int();
		comweeksmallcycle = _os_.unmarshal_int();
		lastgiveuofamtime = _os_.unmarshal_long();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			shownpcs.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			hidemines.add(_v_);
		}
		isuseyuanbaocomtask = _os_.unmarshal_int();
		iscanclefamtask = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			receivedweekbonus.add(_v_);
		}
		guidebranchtaskid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			allcandobranch.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			unlockcomtasks.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof STask) {
			STask _o_ = (STask)_o1_;
			if (!variables.equals(_o_.variables)) return false;
			if (!completehistory.equals(_o_.completehistory)) return false;
			if (!acceptedtasks.equals(_o_.acceptedtasks)) return false;
			if (!curfamtasks.equals(_o_.curfamtasks)) return false;
			if (completefamtasknum != _o_.completefamtasknum) return false;
			if (comdaycycle != _o_.comdaycycle) return false;
			if (comweeksmallcycle != _o_.comweeksmallcycle) return false;
			if (lastgiveuofamtime != _o_.lastgiveuofamtime) return false;
			if (!shownpcs.equals(_o_.shownpcs)) return false;
			if (!hidemines.equals(_o_.hidemines)) return false;
			if (isuseyuanbaocomtask != _o_.isuseyuanbaocomtask) return false;
			if (iscanclefamtask != _o_.iscanclefamtask) return false;
			if (!receivedweekbonus.equals(_o_.receivedweekbonus)) return false;
			if (guidebranchtaskid != _o_.guidebranchtaskid) return false;
			if (!allcandobranch.equals(_o_.allcandobranch)) return false;
			if (!unlockcomtasks.equals(_o_.unlockcomtasks)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += variables.hashCode();
		_h_ += completehistory.hashCode();
		_h_ += acceptedtasks.hashCode();
		_h_ += curfamtasks.hashCode();
		_h_ += completefamtasknum;
		_h_ += comdaycycle;
		_h_ += comweeksmallcycle;
		_h_ += (int)lastgiveuofamtime;
		_h_ += shownpcs.hashCode();
		_h_ += hidemines.hashCode();
		_h_ += isuseyuanbaocomtask;
		_h_ += iscanclefamtask;
		_h_ += receivedweekbonus.hashCode();
		_h_ += guidebranchtaskid;
		_h_ += allcandobranch.hashCode();
		_h_ += unlockcomtasks.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(variables).append(",");
		_sb_.append(completehistory).append(",");
		_sb_.append(acceptedtasks).append(",");
		_sb_.append(curfamtasks).append(",");
		_sb_.append(completefamtasknum).append(",");
		_sb_.append(comdaycycle).append(",");
		_sb_.append(comweeksmallcycle).append(",");
		_sb_.append(lastgiveuofamtime).append(",");
		_sb_.append(shownpcs).append(",");
		_sb_.append(hidemines).append(",");
		_sb_.append(isuseyuanbaocomtask).append(",");
		_sb_.append(iscanclefamtask).append(",");
		_sb_.append(receivedweekbonus).append(",");
		_sb_.append(guidebranchtaskid).append(",");
		_sb_.append(allcandobranch).append(",");
		_sb_.append(unlockcomtasks).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

