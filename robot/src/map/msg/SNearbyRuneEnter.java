
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SNearbyRuneEnter__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SNearbyRuneEnter extends __SNearbyRuneEnter__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684847;

	public int getType() {
		return 6684847;
	}

	public long agentid; // 进入视野的物体(符咒)
	public map.msg.Vector3 position;
	public map.msg.Vector3 orient;
	public int runeid;

	public SNearbyRuneEnter() {
		position = new map.msg.Vector3();
		orient = new map.msg.Vector3();
	}

	public SNearbyRuneEnter(long _agentid_, map.msg.Vector3 _position_, map.msg.Vector3 _orient_, int _runeid_) {
		this.agentid = _agentid_;
		this.position = _position_;
		this.orient = _orient_;
		this.runeid = _runeid_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		if (!orient._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(agentid);
		_os_.marshal(position);
		_os_.marshal(orient);
		_os_.marshal(runeid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		agentid = _os_.unmarshal_long();
		position.unmarshal(_os_);
		orient.unmarshal(_os_);
		runeid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SNearbyRuneEnter) {
			SNearbyRuneEnter _o_ = (SNearbyRuneEnter)_o1_;
			if (agentid != _o_.agentid) return false;
			if (!position.equals(_o_.position)) return false;
			if (!orient.equals(_o_.orient)) return false;
			if (runeid != _o_.runeid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)agentid;
		_h_ += position.hashCode();
		_h_ += orient.hashCode();
		_h_ += runeid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(agentid).append(",");
		_sb_.append(position).append(",");
		_sb_.append(orient).append(",");
		_sb_.append(runeid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

