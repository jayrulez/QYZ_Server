
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SMarriageInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SMarriageInfo extends __SMarriageInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570877;

	public int getType() {
		return 6570877;
	}

	public long coupleroleid; // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
	public long curproposeid; // 当前正在求婚对象的id
	public long startproposetime; // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚

	public SMarriageInfo() {
	}

	public SMarriageInfo(long _coupleroleid_, long _curproposeid_, long _startproposetime_) {
		this.coupleroleid = _coupleroleid_;
		this.curproposeid = _curproposeid_;
		this.startproposetime = _startproposetime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(coupleroleid);
		_os_.marshal(curproposeid);
		_os_.marshal(startproposetime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		coupleroleid = _os_.unmarshal_long();
		curproposeid = _os_.unmarshal_long();
		startproposetime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SMarriageInfo) {
			SMarriageInfo _o_ = (SMarriageInfo)_o1_;
			if (coupleroleid != _o_.coupleroleid) return false;
			if (curproposeid != _o_.curproposeid) return false;
			if (startproposetime != _o_.startproposetime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)coupleroleid;
		_h_ += (int)curproposeid;
		_h_ += (int)startproposetime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(coupleroleid).append(",");
		_sb_.append(curproposeid).append(",");
		_sb_.append(startproposetime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SMarriageInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(coupleroleid - _o_.coupleroleid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(curproposeid - _o_.curproposeid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(startproposetime - _o_.startproposetime);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

