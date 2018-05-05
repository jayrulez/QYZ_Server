
package map.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreateGuardTowerEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreateGuardTowerEctype extends __XCreateGuardTowerEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6713516;

	public int getType() {
		return 6713516;
	}

	public int ectypeid;
	public int serverid;
	public java.util.HashMap<Long,Integer> roles;
	public int zoneid;

	public XCreateGuardTowerEctype() {
		roles = new java.util.HashMap<Long,Integer>();
	}

	public XCreateGuardTowerEctype(int _ectypeid_, int _serverid_, java.util.HashMap<Long,Integer> _roles_, int _zoneid_) {
		this.ectypeid = _ectypeid_;
		this.serverid = _serverid_;
		this.roles = _roles_;
		this.zoneid = _zoneid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(serverid);
		_os_.compact_uint32(roles.size());
		for (java.util.Map.Entry<Long, Integer> _e_ : roles.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(zoneid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		serverid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			int _v_;
			_v_ = _os_.unmarshal_int();
			roles.put(_k_, _v_);
		}
		zoneid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreateGuardTowerEctype) {
			XCreateGuardTowerEctype _o_ = (XCreateGuardTowerEctype)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (serverid != _o_.serverid) return false;
			if (!roles.equals(_o_.roles)) return false;
			if (zoneid != _o_.zoneid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += serverid;
		_h_ += roles.hashCode();
		_h_ += zoneid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(roles).append(",");
		_sb_.append(zoneid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

