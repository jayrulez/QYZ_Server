
package lx.gs.depot.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSyncGoldCoin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSyncGoldCoin extends __SSyncGoldCoin__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555981;

	public int getType() {
		return 6555981;
	}

	public long depotgoldcoin; // 数量

	public SSyncGoldCoin() {
	}

	public SSyncGoldCoin(long _depotgoldcoin_) {
		this.depotgoldcoin = _depotgoldcoin_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(depotgoldcoin);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		depotgoldcoin = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSyncGoldCoin) {
			SSyncGoldCoin _o_ = (SSyncGoldCoin)_o1_;
			if (depotgoldcoin != _o_.depotgoldcoin) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)depotgoldcoin;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(depotgoldcoin).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SSyncGoldCoin _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(depotgoldcoin - _o_.depotgoldcoin);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

