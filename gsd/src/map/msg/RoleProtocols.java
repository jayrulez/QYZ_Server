
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __RoleProtocols__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class RoleProtocols extends __RoleProtocols__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684785;

	public int getType() {
		return 6684785;
	}

	public long roleid;
	public int ptype;
	public com.goldhuman.Common.Octets data;

	public RoleProtocols() {
		data = new com.goldhuman.Common.Octets();
	}

	public RoleProtocols(long _roleid_, int _ptype_, com.goldhuman.Common.Octets _data_) {
		this.roleid = _roleid_;
		this.ptype = _ptype_;
		this.data = _data_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(ptype);
		_os_.marshal(data);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		ptype = _os_.unmarshal_int();
		data = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleProtocols) {
			RoleProtocols _o_ = (RoleProtocols)_o1_;
			if (roleid != _o_.roleid) return false;
			if (ptype != _o_.ptype) return false;
			if (!data.equals(_o_.data)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += ptype;
		_h_ += data.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(ptype).append(",");
		_sb_.append("B").append(data.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

