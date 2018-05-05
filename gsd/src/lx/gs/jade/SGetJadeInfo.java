
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetJadeInfo__ extends xio.Protocol { }

/** 获取护符信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetJadeInfo extends lx.gs.jade.__SGetJadeInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572085;

	public int getType() {
		return 6572085;
	}

	public lx.gs.jade.RoleJadeInfo jadeinfo;

	public SGetJadeInfo() {
		jadeinfo = new lx.gs.jade.RoleJadeInfo();
	}

	public SGetJadeInfo(lx.gs.jade.RoleJadeInfo _jadeinfo_) {
		this.jadeinfo = _jadeinfo_;
	}

	public final boolean _validator_() {
		if (!jadeinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(jadeinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		jadeinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetJadeInfo) {
			SGetJadeInfo _o_ = (SGetJadeInfo)_o1_;
			if (!jadeinfo.equals(_o_.jadeinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += jadeinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(jadeinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

