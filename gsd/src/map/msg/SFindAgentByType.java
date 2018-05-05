
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFindAgentByType__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFindAgentByType extends __SFindAgentByType__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6698556;

	public int getType() {
		return 6698556;
	}

	public int errcode; // 比较特殊,S里带错误码
	public int agenttype;
	public map.msg.Vector3 position;

	public SFindAgentByType() {
		position = new map.msg.Vector3();
	}

	public SFindAgentByType(int _errcode_, int _agenttype_, map.msg.Vector3 _position_) {
		this.errcode = _errcode_;
		this.agenttype = _agenttype_;
		this.position = _position_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(errcode);
		_os_.marshal(agenttype);
		_os_.marshal(position);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		errcode = _os_.unmarshal_int();
		agenttype = _os_.unmarshal_int();
		position.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFindAgentByType) {
			SFindAgentByType _o_ = (SFindAgentByType)_o1_;
			if (errcode != _o_.errcode) return false;
			if (agenttype != _o_.agenttype) return false;
			if (!position.equals(_o_.position)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += errcode;
		_h_ += agenttype;
		_h_ += position.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(errcode).append(",");
		_sb_.append(agenttype).append(",");
		_sb_.append(position).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

