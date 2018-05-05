
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class TaskInfo implements Marshal {
	public int taskid;
	public java.util.HashMap<Integer,Integer> counter;

	public TaskInfo() {
		counter = new java.util.HashMap<Integer,Integer>();
	}

	public TaskInfo(int _taskid_, java.util.HashMap<Integer,Integer> _counter_) {
		this.taskid = _taskid_;
		this.counter = _counter_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(taskid);
		_os_.compact_uint32(counter.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : counter.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		taskid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			counter.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TaskInfo) {
			TaskInfo _o_ = (TaskInfo)_o1_;
			if (taskid != _o_.taskid) return false;
			if (!counter.equals(_o_.counter)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += taskid;
		_h_ += counter.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(taskid).append(",");
		_sb_.append(counter).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

