
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetFamilyActivityInfo__ extends xio.Protocol { }

/** 获取家族活动信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetFamilyActivityInfo extends __SGetFamilyActivityInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570627;

	public int getType() {
		return 6570627;
	}

	public lx.gs.family.msg.FamilyActivity activity; // 家族活动中的信息

	public SGetFamilyActivityInfo() {
		activity = new lx.gs.family.msg.FamilyActivity();
	}

	public SGetFamilyActivityInfo(lx.gs.family.msg.FamilyActivity _activity_) {
		this.activity = _activity_;
	}

	public final boolean _validator_() {
		if (!activity._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(activity);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		activity.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetFamilyActivityInfo) {
			SGetFamilyActivityInfo _o_ = (SGetFamilyActivityInfo)_o1_;
			if (!activity.equals(_o_.activity)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += activity.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(activity).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

