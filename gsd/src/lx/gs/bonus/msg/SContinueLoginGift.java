
package lx.gs.bonus.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SContinueLoginGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SContinueLoginGift extends __SContinueLoginGift__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556113;

	public int getType() {
		return 6556113;
	}

	public int boxid;
	public map.msg.Bonus logingift;
	public int lefttimes; // 剩余连续登录奖励领取次数

	public SContinueLoginGift() {
		logingift = new map.msg.Bonus();
	}

	public SContinueLoginGift(int _boxid_, map.msg.Bonus _logingift_, int _lefttimes_) {
		this.boxid = _boxid_;
		this.logingift = _logingift_;
		this.lefttimes = _lefttimes_;
	}

	public final boolean _validator_() {
		if (!logingift._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(boxid);
		_os_.marshal(logingift);
		_os_.marshal(lefttimes);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		boxid = _os_.unmarshal_int();
		logingift.unmarshal(_os_);
		lefttimes = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SContinueLoginGift) {
			SContinueLoginGift _o_ = (SContinueLoginGift)_o1_;
			if (boxid != _o_.boxid) return false;
			if (!logingift.equals(_o_.logingift)) return false;
			if (lefttimes != _o_.lefttimes) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += boxid;
		_h_ += logingift.hashCode();
		_h_ += lefttimes;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(boxid).append(",");
		_sb_.append(logingift).append(",");
		_sb_.append(lefttimes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

