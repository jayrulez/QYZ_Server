
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFindAgent__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFindAgent extends __SFindAgent__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684805;

	public int getType() {
		return 6684805;
	}

	public int errcode; // 比较特殊,S里带错误码
	public int agenttemplateid;
	public map.msg.Vector3 position;

	public SFindAgent() {
		position = new map.msg.Vector3();
	}

	public SFindAgent(int _errcode_, int _agenttemplateid_, map.msg.Vector3 _position_) {
		this.errcode = _errcode_;
		this.agenttemplateid = _agenttemplateid_;
		this.position = _position_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(errcode);
		_os_.marshal(agenttemplateid);
		_os_.marshal(position);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		errcode = _os_.unmarshal_int();
		agenttemplateid = _os_.unmarshal_int();
		position.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFindAgent) {
			SFindAgent _o_ = (SFindAgent)_o1_;
			if (errcode != _o_.errcode) return false;
			if (agenttemplateid != _o_.agenttemplateid) return false;
			if (!position.equals(_o_.position)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += errcode;
		_h_ += agenttemplateid;
		_h_ += position.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(errcode).append(",");
		_sb_.append(agenttemplateid).append(",");
		_sb_.append(position).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

