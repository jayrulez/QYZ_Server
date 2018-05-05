
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class TaskHistory extends xdb.XBean implements xbean.TaskHistory {
	private int taskid; // 
	private int count; // 
	private long expiretime; // 

	@Override
	public void _reset_unsafe_() {
		taskid = 0;
		count = 0;
		expiretime = 0L;
	}

	TaskHistory(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public TaskHistory() {
		this(0, null, null);
	}

	public TaskHistory(TaskHistory _o_) {
		this(_o_, null, null);
	}

	TaskHistory(xbean.TaskHistory _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof TaskHistory) assign((TaskHistory)_o1_);
		else if (_o1_ instanceof TaskHistory.Data) assign((TaskHistory.Data)_o1_);
		else if (_o1_ instanceof TaskHistory.Const) assign(((TaskHistory.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(TaskHistory _o_) {
		_o_._xdb_verify_unsafe_();
		taskid = _o_.taskid;
		count = _o_.count;
		expiretime = _o_.expiretime;
	}

	private void assign(TaskHistory.Data _o_) {
		taskid = _o_.taskid;
		count = _o_.count;
		expiretime = _o_.expiretime;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)( 8192|  3));_os_.marshal(taskid);
    _os_.marshal((short)( 8192|  1));_os_.marshal(count);
    _os_.marshal((short)(10240|  2));_os_.marshal(expiretime);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  3):taskid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):taskid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):count = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):count = _os_.unmarshal_short();
    				break;
    				case (10240|  2):expiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  2):expiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):expiretime = _os_.unmarshal_int();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.TaskHistory copy() {
		_xdb_verify_unsafe_();
		return new TaskHistory(this);
	}

	@Override
	public xbean.TaskHistory toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TaskHistory toBean() {
		_xdb_verify_unsafe_();
		return new TaskHistory(this); // same as copy()
	}

	@Override
	public xbean.TaskHistory toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TaskHistory toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getTaskid() { // 
		_xdb_verify_unsafe_();
		return taskid;
	}

	@Override
	public int getCount() { // 
		_xdb_verify_unsafe_();
		return count;
	}

	@Override
	public long getExpiretime() { // 
		_xdb_verify_unsafe_();
		return expiretime;
	}

	@Override
	public void setTaskid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "taskid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, taskid) {
					public void rollback() { taskid = _xdb_saved; }
				};}});
		taskid = _v_;
	}

	@Override
	public void setCount(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "count") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, count) {
					public void rollback() { count = _xdb_saved; }
				};}});
		count = _v_;
	}

	@Override
	public void setExpiretime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "expiretime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, expiretime) {
					public void rollback() { expiretime = _xdb_saved; }
				};}});
		expiretime = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		TaskHistory _o_ = null;
		if ( _o1_ instanceof TaskHistory ) _o_ = (TaskHistory)_o1_;
		else if ( _o1_ instanceof TaskHistory.Const ) _o_ = ((TaskHistory.Const)_o1_).nThis();
		else return false;
		if (taskid != _o_.taskid) return false;
		if (count != _o_.count) return false;
		if (expiretime != _o_.expiretime) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += taskid;
		_h_ += count;
		_h_ += expiretime;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(taskid);
		_sb_.append(",");
		_sb_.append(count);
		_sb_.append(",");
		_sb_.append(expiretime);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("taskid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("count"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("expiretime"));
		return lb;
	}

	private class Const implements xbean.TaskHistory {
		TaskHistory nThis() {
			return TaskHistory.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.TaskHistory copy() {
			return TaskHistory.this.copy();
		}

		@Override
		public xbean.TaskHistory toData() {
			return TaskHistory.this.toData();
		}

		public xbean.TaskHistory toBean() {
			return TaskHistory.this.toBean();
		}

		@Override
		public xbean.TaskHistory toDataIf() {
			return TaskHistory.this.toDataIf();
		}

		public xbean.TaskHistory toBeanIf() {
			return TaskHistory.this.toBeanIf();
		}

		@Override
		public int getTaskid() { // 
			_xdb_verify_unsafe_();
			return taskid;
		}

		@Override
		public int getCount() { // 
			_xdb_verify_unsafe_();
			return count;
		}

		@Override
		public long getExpiretime() { // 
			_xdb_verify_unsafe_();
			return expiretime;
		}

		@Override
		public void setTaskid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCount(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExpiretime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean toConst() {
			_xdb_verify_unsafe_();
			return this;
		}

		@Override
		public boolean isConst() {
			_xdb_verify_unsafe_();
			return true;
		}

		@Override
		public boolean isData() {
			return TaskHistory.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return TaskHistory.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return TaskHistory.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return TaskHistory.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return TaskHistory.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return TaskHistory.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return TaskHistory.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return TaskHistory.this.hashCode();
		}

		@Override
		public String toString() {
			return TaskHistory.this.toString();
		}

	}

	public static final class Data implements xbean.TaskHistory {
		private int taskid; // 
		private int count; // 
		private long expiretime; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.TaskHistory _o1_) {
			if (_o1_ instanceof TaskHistory) assign((TaskHistory)_o1_);
			else if (_o1_ instanceof TaskHistory.Data) assign((TaskHistory.Data)_o1_);
			else if (_o1_ instanceof TaskHistory.Const) assign(((TaskHistory.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(TaskHistory _o_) {
			taskid = _o_.taskid;
			count = _o_.count;
			expiretime = _o_.expiretime;
		}

		private void assign(TaskHistory.Data _o_) {
			taskid = _o_.taskid;
			count = _o_.count;
			expiretime = _o_.expiretime;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)( 8192|  3));_os_.marshal(taskid);
	_os_.marshal((short)( 8192|  1));_os_.marshal(count);
	_os_.marshal((short)(10240|  2));_os_.marshal(expiretime);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  3):taskid = _os_.unmarshal_int();
					break;
					case ( 6144|  3):taskid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):count = _os_.unmarshal_int();
					break;
					case ( 6144|  1):count = _os_.unmarshal_short();
					break;
					case (10240|  2):expiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  2):expiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  2):expiretime = _os_.unmarshal_int();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.TaskHistory copy() {
			return new Data(this);
		}

		@Override
		public xbean.TaskHistory toData() {
			return new Data(this);
		}

		public xbean.TaskHistory toBean() {
			return new TaskHistory(this, null, null);
		}

		@Override
		public xbean.TaskHistory toDataIf() {
			return this;
		}

		public xbean.TaskHistory toBeanIf() {
			return new TaskHistory(this, null, null);
		}

		// xdb.Bean interface. Data Unsupported
		public boolean xdbManaged() { throw new UnsupportedOperationException(); }
		public xdb.Bean xdbParent() { throw new UnsupportedOperationException(); }
		public String xdbVarname()  { throw new UnsupportedOperationException(); }
		public Long    xdbObjId()   { throw new UnsupportedOperationException(); }
		public xdb.Bean toConst()   { throw new UnsupportedOperationException(); }
		public boolean isConst()    { return false; }
		public boolean isData()     { return true; }

		@Override
		public int getTaskid() { // 
			return taskid;
		}

		@Override
		public int getCount() { // 
			return count;
		}

		@Override
		public long getExpiretime() { // 
			return expiretime;
		}

		@Override
		public void setTaskid(int _v_) { // 
			taskid = _v_;
		}

		@Override
		public void setCount(int _v_) { // 
			count = _v_;
		}

		@Override
		public void setExpiretime(long _v_) { // 
			expiretime = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof TaskHistory.Data)) return false;
			TaskHistory.Data _o_ = (TaskHistory.Data) _o1_;
			if (taskid != _o_.taskid) return false;
			if (count != _o_.count) return false;
			if (expiretime != _o_.expiretime) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += taskid;
			_h_ += count;
			_h_ += expiretime;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(taskid);
			_sb_.append(",");
			_sb_.append(count);
			_sb_.append(",");
			_sb_.append(expiretime);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
