
package lx.gs.treasurebowl;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __STreasureBowlBreak__ extends xio.Protocol { }

/** 聚宝盆系统拆解装备，得到灵晶
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class STreasureBowlBreak extends __STreasureBowlBreak__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586017;

	public int getType() {
		return 6586017;
	}

	public long receivedlingjing; // 本次拆解获得的灵晶

	public STreasureBowlBreak() {
	}

	public STreasureBowlBreak(long _receivedlingjing_) {
		this.receivedlingjing = _receivedlingjing_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(receivedlingjing);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		receivedlingjing = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof STreasureBowlBreak) {
			STreasureBowlBreak _o_ = (STreasureBowlBreak)_o1_;
			if (receivedlingjing != _o_.receivedlingjing) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)receivedlingjing;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(receivedlingjing).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(STreasureBowlBreak _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(receivedlingjing - _o_.receivedlingjing);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

