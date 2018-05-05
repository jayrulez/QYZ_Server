
package gnet.link;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __AnnounceGsid__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class AnnounceGsid extends __AnnounceGsid__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65706;

	public int getType() {
		return 65706;
	}

	public int serverid;

	public AnnounceGsid() {
	}

	public AnnounceGsid(int _serverid_) {
		this.serverid = _serverid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(serverid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		serverid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AnnounceGsid) {
			AnnounceGsid _o_ = (AnnounceGsid)_o1_;
            return serverid == _o_.serverid;
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += serverid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(serverid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(AnnounceGsid _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = serverid - _o_.serverid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

