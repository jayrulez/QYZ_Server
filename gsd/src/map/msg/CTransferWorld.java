
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CTransferWorld__ extends xio.Protocol { }

/** 地图移动
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CTransferWorld extends __CTransferWorld__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6699618;

	public int getType() {
		return 6699618;
	}

	public int portalid;

	public CTransferWorld() {
	}

	public CTransferWorld(int _portalid_) {
		this.portalid = _portalid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(portalid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		portalid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CTransferWorld) {
			CTransferWorld _o_ = (CTransferWorld)_o1_;
			if (portalid != _o_.portalid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += portalid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(portalid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CTransferWorld _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = portalid - _o_.portalid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

