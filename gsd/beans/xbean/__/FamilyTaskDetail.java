
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class FamilyTaskDetail extends xdb.XBean implements xbean.FamilyTaskDetail {
	private int taskid; // 环任务中某环的任务id
	private int npcid; // 环任务中某环的npcid，同时也是下一环的任务发放npc

	@Override
	public void _reset_unsafe_() {
		taskid = 0;
		npcid = 0;
	}

	FamilyTaskDetail(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public FamilyTaskDetail() {
		this(0, null, null);
	}

	public FamilyTaskDetail(FamilyTaskDetail _o_) {
		this(_o_, null, null);
	}

	FamilyTaskDetail(xbean.FamilyTaskDetail _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof FamilyTaskDetail) assign((FamilyTaskDetail)_o1_);
		else if (_o1_ instanceof FamilyTaskDetail.Data) assign((FamilyTaskDetail.Data)_o1_);
		else if (_o1_ instanceof FamilyTaskDetail.Const) assign(((FamilyTaskDetail.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(FamilyTaskDetail _o_) {
		_o_._xdb_verify_unsafe_();
		taskid = _o_.taskid;
		npcid = _o_.npcid;
	}

	private void assign(FamilyTaskDetail.Data _o_) {
		taskid = _o_.taskid;
		npcid = _o_.npcid;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)( 8192|  1));_os_.marshal(taskid);
    _os_.marshal((short)( 8192|  2));_os_.marshal(npcid);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case ( 8192|  1):taskid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):taskid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  2):npcid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):npcid = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.FamilyTaskDetail copy() {
		_xdb_verify_unsafe_();
		return new FamilyTaskDetail(this);
	}

	@Override
	public xbean.FamilyTaskDetail toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyTaskDetail toBean() {
		_xdb_verify_unsafe_();
		return new FamilyTaskDetail(this); // same as copy()
	}

	@Override
	public xbean.FamilyTaskDetail toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyTaskDetail toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getTaskid() { // 环任务中某环的任务id
		_xdb_verify_unsafe_();
		return taskid;
	}

	@Override
	public int getNpcid() { // 环任务中某环的npcid，同时也是下一环的任务发放npc
		_xdb_verify_unsafe_();
		return npcid;
	}

	@Override
	public void setTaskid(int _v_) { // 环任务中某环的任务id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "taskid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, taskid) {
					public void rollback() { taskid = _xdb_saved; }
				};}});
		taskid = _v_;
	}

	@Override
	public void setNpcid(int _v_) { // 环任务中某环的npcid，同时也是下一环的任务发放npc
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "npcid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, npcid) {
					public void rollback() { npcid = _xdb_saved; }
				};}});
		npcid = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		FamilyTaskDetail _o_ = null;
		if ( _o1_ instanceof FamilyTaskDetail ) _o_ = (FamilyTaskDetail)_o1_;
		else if ( _o1_ instanceof FamilyTaskDetail.Const ) _o_ = ((FamilyTaskDetail.Const)_o1_).nThis();
		else return false;
		if (taskid != _o_.taskid) return false;
		if (npcid != _o_.npcid) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += taskid;
		_h_ += npcid;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(taskid);
		_sb_.append(",");
		_sb_.append(npcid);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("taskid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("npcid"));
		return lb;
	}

	private class Const implements xbean.FamilyTaskDetail {
		FamilyTaskDetail nThis() {
			return FamilyTaskDetail.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.FamilyTaskDetail copy() {
			return FamilyTaskDetail.this.copy();
		}

		@Override
		public xbean.FamilyTaskDetail toData() {
			return FamilyTaskDetail.this.toData();
		}

		public xbean.FamilyTaskDetail toBean() {
			return FamilyTaskDetail.this.toBean();
		}

		@Override
		public xbean.FamilyTaskDetail toDataIf() {
			return FamilyTaskDetail.this.toDataIf();
		}

		public xbean.FamilyTaskDetail toBeanIf() {
			return FamilyTaskDetail.this.toBeanIf();
		}

		@Override
		public int getTaskid() { // 环任务中某环的任务id
			_xdb_verify_unsafe_();
			return taskid;
		}

		@Override
		public int getNpcid() { // 环任务中某环的npcid，同时也是下一环的任务发放npc
			_xdb_verify_unsafe_();
			return npcid;
		}

		@Override
		public void setTaskid(int _v_) { // 环任务中某环的任务id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNpcid(int _v_) { // 环任务中某环的npcid，同时也是下一环的任务发放npc
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
			return FamilyTaskDetail.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return FamilyTaskDetail.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return FamilyTaskDetail.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return FamilyTaskDetail.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return FamilyTaskDetail.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return FamilyTaskDetail.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return FamilyTaskDetail.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return FamilyTaskDetail.this.hashCode();
		}

		@Override
		public String toString() {
			return FamilyTaskDetail.this.toString();
		}

	}

	public static final class Data implements xbean.FamilyTaskDetail {
		private int taskid; // 环任务中某环的任务id
		private int npcid; // 环任务中某环的npcid，同时也是下一环的任务发放npc

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.FamilyTaskDetail _o1_) {
			if (_o1_ instanceof FamilyTaskDetail) assign((FamilyTaskDetail)_o1_);
			else if (_o1_ instanceof FamilyTaskDetail.Data) assign((FamilyTaskDetail.Data)_o1_);
			else if (_o1_ instanceof FamilyTaskDetail.Const) assign(((FamilyTaskDetail.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(FamilyTaskDetail _o_) {
			taskid = _o_.taskid;
			npcid = _o_.npcid;
		}

		private void assign(FamilyTaskDetail.Data _o_) {
			taskid = _o_.taskid;
			npcid = _o_.npcid;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)( 8192|  1));_os_.marshal(taskid);
	_os_.marshal((short)( 8192|  2));_os_.marshal(npcid);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case ( 8192|  1):taskid = _os_.unmarshal_int();
					break;
					case ( 6144|  1):taskid = _os_.unmarshal_short();
					break;
					case ( 8192|  2):npcid = _os_.unmarshal_int();
					break;
					case ( 6144|  2):npcid = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.FamilyTaskDetail copy() {
			return new Data(this);
		}

		@Override
		public xbean.FamilyTaskDetail toData() {
			return new Data(this);
		}

		public xbean.FamilyTaskDetail toBean() {
			return new FamilyTaskDetail(this, null, null);
		}

		@Override
		public xbean.FamilyTaskDetail toDataIf() {
			return this;
		}

		public xbean.FamilyTaskDetail toBeanIf() {
			return new FamilyTaskDetail(this, null, null);
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
		public int getTaskid() { // 环任务中某环的任务id
			return taskid;
		}

		@Override
		public int getNpcid() { // 环任务中某环的npcid，同时也是下一环的任务发放npc
			return npcid;
		}

		@Override
		public void setTaskid(int _v_) { // 环任务中某环的任务id
			taskid = _v_;
		}

		@Override
		public void setNpcid(int _v_) { // 环任务中某环的npcid，同时也是下一环的任务发放npc
			npcid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof FamilyTaskDetail.Data)) return false;
			FamilyTaskDetail.Data _o_ = (FamilyTaskDetail.Data) _o1_;
			if (taskid != _o_.taskid) return false;
			if (npcid != _o_.npcid) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += taskid;
			_h_ += npcid;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(taskid);
			_sb_.append(",");
			_sb_.append(npcid);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
