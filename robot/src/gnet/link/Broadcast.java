
package gnet.link;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __Broadcast__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class Broadcast extends __Broadcast__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65542;

	public int getType() {
		return 65542;
	}

	public int ptype;
	public com.goldhuman.Common.Octets pdata;
	public int time;

	public Broadcast() {
		pdata = new com.goldhuman.Common.Octets();
	}

	public Broadcast(int _ptype_, com.goldhuman.Common.Octets _pdata_, int _time_) {
		this.ptype = _ptype_;
		this.pdata = _pdata_;
		this.time = _time_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ptype);
		_os_.marshal(pdata);
		_os_.marshal(time);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ptype = _os_.unmarshal_int();
		pdata = _os_.unmarshal_Octets();
		time = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Broadcast) {
			Broadcast _o_ = (Broadcast)_o1_;
			if (ptype != _o_.ptype) return false;
			if (!pdata.equals(_o_.pdata)) return false;
			if (time != _o_.time) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ptype;
		_h_ += pdata.hashCode();
		_h_ += time;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ptype).append(",");
		_sb_.append("B").append(pdata.size()).append(",");
		_sb_.append(time).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

