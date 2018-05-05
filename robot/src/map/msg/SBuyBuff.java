
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBuyBuff__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBuyBuff extends __SBuyBuff__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6709727;

	public int getType() {
		return 6709727;
	}

	public int buffid;
	public int buynum;
	public int totalscore;

	public SBuyBuff() {
	}

	public SBuyBuff(int _buffid_, int _buynum_, int _totalscore_) {
		this.buffid = _buffid_;
		this.buynum = _buynum_;
		this.totalscore = _totalscore_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(buffid);
		_os_.marshal(buynum);
		_os_.marshal(totalscore);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		buffid = _os_.unmarshal_int();
		buynum = _os_.unmarshal_int();
		totalscore = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBuyBuff) {
			SBuyBuff _o_ = (SBuyBuff)_o1_;
			if (buffid != _o_.buffid) return false;
			if (buynum != _o_.buynum) return false;
			if (totalscore != _o_.totalscore) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += buffid;
		_h_ += buynum;
		_h_ += totalscore;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(buffid).append(",");
		_sb_.append(buynum).append(",");
		_sb_.append(totalscore).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SBuyBuff _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = buffid - _o_.buffid;
		if (0 != _c_) return _c_;
		_c_ = buynum - _o_.buynum;
		if (0 != _c_) return _c_;
		_c_ = totalscore - _o_.totalscore;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

