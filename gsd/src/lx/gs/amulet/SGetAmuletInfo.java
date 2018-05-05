
package lx.gs.amulet;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetAmuletInfo__ extends xio.Protocol { }

/** 获取护符信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetAmuletInfo extends lx.gs.amulet.__SGetAmuletInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578149;

	public int getType() {
		return 6578149;
	}

	public lx.gs.amulet.RoleAmuletInfo amuletinfo;

	public SGetAmuletInfo() {
		amuletinfo = new lx.gs.amulet.RoleAmuletInfo();
	}

	public SGetAmuletInfo(lx.gs.amulet.RoleAmuletInfo _amuletinfo_) {
		this.amuletinfo = _amuletinfo_;
	}

	public final boolean _validator_() {
		if (!amuletinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(amuletinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		amuletinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetAmuletInfo) {
			SGetAmuletInfo _o_ = (SGetAmuletInfo)_o1_;
			if (!amuletinfo.equals(_o_.amuletinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += amuletinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(amuletinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

