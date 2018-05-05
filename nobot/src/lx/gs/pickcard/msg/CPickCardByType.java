
package lx.gs.pickcard.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CPickCardByType__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CPickCardByType extends __CPickCardByType__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565020;

	public int getType() {
		return 6565020;
	}

	public final static int HUOBAN_HIGH_ONE = 1; // 伙伴高配额单抽
	public final static int HUOBAN_HIGH_TEN = 2; // 伙伴高配额十连抽
	public final static int HUOBAN_HIGH_FREE_ONE = 3; // 伙伴高配额免费单抽,三天刷新一次免费机会，有抽卡券
	public final static int HUOBAN_HIGH_FREE_TEN = 12; // 伙伴高配额免费十抽，只能用抽卡券
	public final static int HUOBAN_LOW_ONE = 4; // 伙伴低配额单抽
	public final static int HUOBAN_LOW_TEN = 5; // 伙伴低配额十连抽
	public final static int HUOBAN_LOW_FREE = 6; // 伙伴低配额免费抽，每天刷新5次
	public final static int FABAO_ONE = 7; // 法宝元宝单抽
	public final static int FABAO_TEN = 8; // 法宝元宝十连抽
	public final static int FABAO_FREE_ONE = 9; // 法宝元宝免费单抽
	public final static int FABAO_FREE_TEN = 13; // 法宝元宝免费十抽,只能用抽卡券
	public final static int FABAO_XUNIBI_ONE = 10; // 法宝虚拟币抽
	public final static int FABAO_XUNIBI_TEN = 11; // 法宝虚拟币十抽

	public int picktype;

	public CPickCardByType() {
	}

	public CPickCardByType(int _picktype_) {
		this.picktype = _picktype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(picktype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		picktype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CPickCardByType) {
			CPickCardByType _o_ = (CPickCardByType)_o1_;
			if (picktype != _o_.picktype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += picktype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(picktype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CPickCardByType _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = picktype - _o_.picktype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

