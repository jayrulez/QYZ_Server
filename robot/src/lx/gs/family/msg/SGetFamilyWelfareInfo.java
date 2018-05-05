
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetFamilyWelfareInfo__ extends xio.Protocol { }

/** 获取家族福利信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetFamilyWelfareInfo extends __SGetFamilyWelfareInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568512;

	public int getType() {
		return 6568512;
	}

	public lx.gs.family.msg.FamilyWelfare welfare; // 家族福利中的信息

	public SGetFamilyWelfareInfo() {
		welfare = new lx.gs.family.msg.FamilyWelfare();
	}

	public SGetFamilyWelfareInfo(lx.gs.family.msg.FamilyWelfare _welfare_) {
		this.welfare = _welfare_;
	}

	public final boolean _validator_() {
		if (!welfare._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(welfare);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		welfare.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetFamilyWelfareInfo) {
			SGetFamilyWelfareInfo _o_ = (SGetFamilyWelfareInfo)_o1_;
			if (!welfare.equals(_o_.welfare)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += welfare.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(welfare).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

