
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetFamilyAwardClaimInfo__ extends xio.Protocol { }

/** 获取家族奖励领过去的信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetFamilyAwardClaimInfo extends __SGetFamilyAwardClaimInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586290;

	public int getType() {
		return 6586290;
	}

	public java.util.HashMap<Integer,Integer> claiminfo; // 获取家族奖励领过去的信息

	public SGetFamilyAwardClaimInfo() {
		claiminfo = new java.util.HashMap<Integer,Integer>();
	}

	public SGetFamilyAwardClaimInfo(java.util.HashMap<Integer,Integer> _claiminfo_) {
		this.claiminfo = _claiminfo_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(claiminfo.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : claiminfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			claiminfo.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetFamilyAwardClaimInfo) {
			SGetFamilyAwardClaimInfo _o_ = (SGetFamilyAwardClaimInfo)_o1_;
            return claiminfo.equals(_o_.claiminfo);
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += claiminfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(claiminfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

