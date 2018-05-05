
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SPrepareMultiStoryEctypeFailNotify__ extends xio.Protocol { }

/** 在匹配成功后，扣体力或者减次数失败时发送
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SPrepareMultiStoryEctypeFailNotify extends __SPrepareMultiStoryEctypeFailNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571699;

	public int getType() {
		return 6571699;
	}

	public java.lang.String reason;

	public SPrepareMultiStoryEctypeFailNotify() {
		reason = "";
	}

	public SPrepareMultiStoryEctypeFailNotify(java.lang.String _reason_) {
		this.reason = _reason_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(reason, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		reason = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SPrepareMultiStoryEctypeFailNotify) {
			SPrepareMultiStoryEctypeFailNotify _o_ = (SPrepareMultiStoryEctypeFailNotify)_o1_;
			if (!reason.equals(_o_.reason)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += reason.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(reason.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

