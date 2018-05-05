
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEnterClimbTower__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEnterClimbTower extends __SEnterClimbTower__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6694787;

	public int getType() {
		return 6694787;
	}

	public long id;
	public int ectypeid;
	public java.util.HashMap<Integer,Integer> buffbuynums;
	public int totalscore;
	public int curfloorid;
	public long remaintime;

	public SEnterClimbTower() {
		buffbuynums = new java.util.HashMap<Integer,Integer>();
	}

	public SEnterClimbTower(long _id_, int _ectypeid_, java.util.HashMap<Integer,Integer> _buffbuynums_, int _totalscore_, int _curfloorid_, long _remaintime_) {
		this.id = _id_;
		this.ectypeid = _ectypeid_;
		this.buffbuynums = _buffbuynums_;
		this.totalscore = _totalscore_;
		this.curfloorid = _curfloorid_;
		this.remaintime = _remaintime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(ectypeid);
		_os_.compact_uint32(buffbuynums.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : buffbuynums.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(totalscore);
		_os_.marshal(curfloorid);
		_os_.marshal(remaintime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_long();
		ectypeid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			buffbuynums.put(_k_, _v_);
		}
		totalscore = _os_.unmarshal_int();
		curfloorid = _os_.unmarshal_int();
		remaintime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEnterClimbTower) {
			SEnterClimbTower _o_ = (SEnterClimbTower)_o1_;
			if (id != _o_.id) return false;
			if (ectypeid != _o_.ectypeid) return false;
			if (!buffbuynums.equals(_o_.buffbuynums)) return false;
			if (totalscore != _o_.totalscore) return false;
			if (curfloorid != _o_.curfloorid) return false;
			if (remaintime != _o_.remaintime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)id;
		_h_ += ectypeid;
		_h_ += buffbuynums.hashCode();
		_h_ += totalscore;
		_h_ += curfloorid;
		_h_ += (int)remaintime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(buffbuynums).append(",");
		_sb_.append(totalscore).append(",");
		_sb_.append(curfloorid).append(",");
		_sb_.append(remaintime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

