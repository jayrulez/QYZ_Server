
package lx.gs.pickcard.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SScoreExchange__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SScoreExchange extends __SScoreExchange__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556010;

	public int getType() {
		return 6556010;
	}

	public int exchangetyep; // 兑换类型
	public int id; // 要兑换的物品id

	public SScoreExchange() {
	}

	public SScoreExchange(int _exchangetyep_, int _id_) {
		this.exchangetyep = _exchangetyep_;
		this.id = _id_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(exchangetyep);
		_os_.marshal(id);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		exchangetyep = _os_.unmarshal_int();
		id = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SScoreExchange) {
			SScoreExchange _o_ = (SScoreExchange)_o1_;
			if (exchangetyep != _o_.exchangetyep) return false;
			if (id != _o_.id) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += exchangetyep;
		_h_ += id;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(exchangetyep).append(",");
		_sb_.append(id).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SScoreExchange _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = exchangetyep - _o_.exchangetyep;
		if (0 != _c_) return _c_;
		_c_ = id - _o_.id;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

