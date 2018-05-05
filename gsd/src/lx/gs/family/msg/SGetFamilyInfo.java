
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetFamilyInfo__ extends xio.Protocol { }

/** 结果
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetFamilyInfo extends __SGetFamilyInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572685;

	public int getType() {
		return 6572685;
	}

	public lx.gs.family.msg.FamilyBasicInfo family;
	public lx.gs.family.msg.FamilyMember selfinfo; // 自己的家族信息
	public long selfinitid; // 自己发起众筹的id,为0表示没有

	public SGetFamilyInfo() {
		family = new lx.gs.family.msg.FamilyBasicInfo();
		selfinfo = new lx.gs.family.msg.FamilyMember();
	}

	public SGetFamilyInfo(lx.gs.family.msg.FamilyBasicInfo _family_, lx.gs.family.msg.FamilyMember _selfinfo_, long _selfinitid_) {
		this.family = _family_;
		this.selfinfo = _selfinfo_;
		this.selfinitid = _selfinitid_;
	}

	public final boolean _validator_() {
		if (!family._validator_()) return false;
		if (!selfinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(family);
		_os_.marshal(selfinfo);
		_os_.marshal(selfinitid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		family.unmarshal(_os_);
		selfinfo.unmarshal(_os_);
		selfinitid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetFamilyInfo) {
			SGetFamilyInfo _o_ = (SGetFamilyInfo)_o1_;
			if (!family.equals(_o_.family)) return false;
			if (!selfinfo.equals(_o_.selfinfo)) return false;
			if (selfinitid != _o_.selfinitid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += family.hashCode();
		_h_ += selfinfo.hashCode();
		_h_ += (int)selfinitid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(family).append(",");
		_sb_.append(selfinfo).append(",");
		_sb_.append(selfinitid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

