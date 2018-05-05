
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCrowdSuccessNotify__ extends xio.Protocol { }

/** 众筹家族成功
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCrowdSuccessNotify extends __SCrowdSuccessNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563860;

	public int getType() {
		return 6563860;
	}

	public lx.gs.family.msg.FamilyBasicInfo family; // 众筹成功后的家族id
	public long crowdfamid; // 众筹时的家族id

	public SCrowdSuccessNotify() {
		family = new lx.gs.family.msg.FamilyBasicInfo();
	}

	public SCrowdSuccessNotify(lx.gs.family.msg.FamilyBasicInfo _family_, long _crowdfamid_) {
		this.family = _family_;
		this.crowdfamid = _crowdfamid_;
	}

	public final boolean _validator_() {
		if (!family._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(family);
		_os_.marshal(crowdfamid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		family.unmarshal(_os_);
		crowdfamid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCrowdSuccessNotify) {
			SCrowdSuccessNotify _o_ = (SCrowdSuccessNotify)_o1_;
			if (!family.equals(_o_.family)) return false;
			if (crowdfamid != _o_.crowdfamid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += family.hashCode();
		_h_ += (int)crowdfamid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(family).append(",");
		_sb_.append(crowdfamid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

