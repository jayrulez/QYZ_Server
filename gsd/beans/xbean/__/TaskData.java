
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class TaskData extends xdb.XBean implements xbean.TaskData {
	private int taskid; // 
	private java.util.HashMap<Integer, Integer> counter; // 

	@Override
	public void _reset_unsafe_() {
		taskid = 0;
		counter.clear();
	}

	TaskData(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		counter = new java.util.HashMap<Integer, Integer>();
	}

	public TaskData() {
		this(0, null, null);
	}

	public TaskData(TaskData _o_) {
		this(_o_, null, null);
	}

	TaskData(xbean.TaskData _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof TaskData) assign((TaskData)_o1_);
		else if (_o1_ instanceof TaskData.Data) assign((TaskData.Data)_o1_);
		else if (_o1_ instanceof TaskData.Const) assign(((TaskData.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(TaskData _o_) {
		_o_._xdb_verify_unsafe_();
		taskid = _o_.taskid;
		counter = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.counter.entrySet())
			counter.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(TaskData.Data _o_) {
		taskid = _o_.taskid;
		counter = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.counter.entrySet())
			counter.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  2));_os_.marshal(taskid);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(counter.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : counter.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  2):taskid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):taskid = _os_.unmarshal_short();
    				break;
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		counter = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		counter.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.TaskData copy() {
		_xdb_verify_unsafe_();
		return new TaskData(this);
	}

	@Override
	public xbean.TaskData toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TaskData toBean() {
		_xdb_verify_unsafe_();
		return new TaskData(this); // same as copy()
	}

	@Override
	public xbean.TaskData toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.TaskData toBeanIf() {
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
	public java.util.Map<Integer, Integer> getCounter() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "counter"), counter);
	}

	@Override
	public java.util.Map<Integer, Integer> getCounterAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> counter;
		TaskData _o_ = this;
		counter = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.counter.entrySet())
			counter.put(_e_.getKey(), _e_.getValue());
		return counter;
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
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		TaskData _o_ = null;
		if ( _o1_ instanceof TaskData ) _o_ = (TaskData)_o1_;
		else if ( _o1_ instanceof TaskData.Const ) _o_ = ((TaskData.Const)_o1_).nThis();
		else return false;
		if (taskid != _o_.taskid) return false;
		if (!counter.equals(_o_.counter)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += taskid;
		_h_ += counter.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(taskid);
		_sb_.append(",");
		_sb_.append(counter);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("taskid"));
		lb.add(new xdb.logs.ListenableMap().setVarName("counter"));
		return lb;
	}

	private class Const implements xbean.TaskData {
		TaskData nThis() {
			return TaskData.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.TaskData copy() {
			return TaskData.this.copy();
		}

		@Override
		public xbean.TaskData toData() {
			return TaskData.this.toData();
		}

		public xbean.TaskData toBean() {
			return TaskData.this.toBean();
		}

		@Override
		public xbean.TaskData toDataIf() {
			return TaskData.this.toDataIf();
		}

		public xbean.TaskData toBeanIf() {
			return TaskData.this.toBeanIf();
		}

		@Override
		public int getTaskid() { // 
			_xdb_verify_unsafe_();
			return taskid;
		}

		@Override
		public java.util.Map<Integer, Integer> getCounter() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(counter);
		}

		@Override
		public java.util.Map<Integer, Integer> getCounterAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> counter;
			TaskData _o_ = TaskData.this;
			counter = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.counter.entrySet())
				counter.put(_e_.getKey(), _e_.getValue());
			return counter;
		}

		@Override
		public void setTaskid(int _v_) { // 
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
			return TaskData.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return TaskData.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return TaskData.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return TaskData.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return TaskData.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return TaskData.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return TaskData.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return TaskData.this.hashCode();
		}

		@Override
		public String toString() {
			return TaskData.this.toString();
		}

	}

	public static final class Data implements xbean.TaskData {
		private int taskid; // 
		private java.util.HashMap<Integer, Integer> counter; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			counter = new java.util.HashMap<Integer, Integer>();
		}

		Data(xbean.TaskData _o1_) {
			if (_o1_ instanceof TaskData) assign((TaskData)_o1_);
			else if (_o1_ instanceof TaskData.Data) assign((TaskData.Data)_o1_);
			else if (_o1_ instanceof TaskData.Const) assign(((TaskData.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(TaskData _o_) {
			taskid = _o_.taskid;
			counter = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.counter.entrySet())
				counter.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(TaskData.Data _o_) {
			taskid = _o_.taskid;
			counter = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.counter.entrySet())
				counter.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  2));_os_.marshal(taskid);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(counter.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : counter.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  2):taskid = _os_.unmarshal_int();
					break;
					case ( 6144|  2):taskid = _os_.unmarshal_short();
					break;
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		counter = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		counter.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.TaskData copy() {
			return new Data(this);
		}

		@Override
		public xbean.TaskData toData() {
			return new Data(this);
		}

		public xbean.TaskData toBean() {
			return new TaskData(this, null, null);
		}

		@Override
		public xbean.TaskData toDataIf() {
			return this;
		}

		public xbean.TaskData toBeanIf() {
			return new TaskData(this, null, null);
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
		public java.util.Map<Integer, Integer> getCounter() { // 
			return counter;
		}

		@Override
		public java.util.Map<Integer, Integer> getCounterAsData() { // 
			return counter;
		}

		@Override
		public void setTaskid(int _v_) { // 
			taskid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof TaskData.Data)) return false;
			TaskData.Data _o_ = (TaskData.Data) _o1_;
			if (taskid != _o_.taskid) return false;
			if (!counter.equals(_o_.counter)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += taskid;
			_h_ += counter.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(taskid);
			_sb_.append(",");
			_sb_.append(counter);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
