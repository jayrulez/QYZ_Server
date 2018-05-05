
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __STransferLeader__ extends xio.Protocol { }

/** 队长身份转移通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class STransferLeader extends __STransferLeader__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557829;

	public int getType() {
		return 6557829;
	}

	public long newleaderid;

	public STransferLeader() {
	}

	public STransferLeader(long _newleaderid_) {
		this.newleaderid = _newleaderid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(newleaderid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		newleaderid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof STransferLeader) {
			STransferLeader _o_ = (STransferLeader)_o1_;
			if (newleaderid != _o_.newleaderid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)newleaderid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(newleaderid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(STransferLeader _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(newleaderid - _o_.newleaderid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

