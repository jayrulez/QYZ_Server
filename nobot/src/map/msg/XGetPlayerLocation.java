
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XGetPlayerLocation__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XGetPlayerLocation extends __XGetPlayerLocation__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6705093;

	public int getType() {
		return 6705093;
	}

	public long queryroleid; // 谁查询

	public XGetPlayerLocation() {
	}

	public XGetPlayerLocation(long _queryroleid_) {
		this.queryroleid = _queryroleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(queryroleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		queryroleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XGetPlayerLocation) {
			XGetPlayerLocation _o_ = (XGetPlayerLocation)_o1_;
			if (queryroleid != _o_.queryroleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)queryroleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(queryroleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(XGetPlayerLocation _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(queryroleid - _o_.queryroleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

