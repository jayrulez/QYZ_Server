
package gnet.link;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __LinkBroken__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class LinkBroken extends __LinkBroken__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65546;

	public int getType() {
		return 65546;
	}

	public final static int REASON_PEERCLOSE = 0;

	public long userid;
	public int linksid;
	public int reason;

	public LinkBroken() {
	}

	public LinkBroken(long _userid_, int _linksid_, int _reason_) {
		this.userid = _userid_;
		this.linksid = _linksid_;
		this.reason = _reason_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(userid);
		_os_.marshal(linksid);
		_os_.marshal(reason);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		userid = _os_.unmarshal_long();
		linksid = _os_.unmarshal_int();
		reason = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LinkBroken) {
			LinkBroken _o_ = (LinkBroken)_o1_;
			if (userid != _o_.userid) return false;
			if (linksid != _o_.linksid) return false;
			if (reason != _o_.reason) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)userid;
		_h_ += linksid;
		_h_ += reason;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(userid).append(",");
		_sb_.append(linksid).append(",");
		_sb_.append(reason).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(LinkBroken _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(userid - _o_.userid);
		if (0 != _c_) return _c_;
		_c_ = linksid - _o_.linksid;
		if (0 != _c_) return _c_;
		_c_ = reason - _o_.reason;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

