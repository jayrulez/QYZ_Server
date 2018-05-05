
package lx.gs.bonus.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SShakeMoneyTree__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SShakeMoneyTree extends __SShakeMoneyTree__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574502;

	public int getType() {
		return 6574502;
	}

	public int time; // 当前第几次摇
	public long receinexunibi; // 获得的金币
	public float criticalnum; // 暴击倍数，没有暴击默认是0

	public SShakeMoneyTree() {
	}

	public SShakeMoneyTree(int _time_, long _receinexunibi_, float _criticalnum_) {
		this.time = _time_;
		this.receinexunibi = _receinexunibi_;
		this.criticalnum = _criticalnum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(time);
		_os_.marshal(receinexunibi);
		_os_.marshal(criticalnum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		time = _os_.unmarshal_int();
		receinexunibi = _os_.unmarshal_long();
		criticalnum = _os_.unmarshal_float();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SShakeMoneyTree) {
			SShakeMoneyTree _o_ = (SShakeMoneyTree)_o1_;
			if (time != _o_.time) return false;
			if (receinexunibi != _o_.receinexunibi) return false;
			if (criticalnum != _o_.criticalnum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += time;
		_h_ += (int)receinexunibi;
		_h_ += Float.floatToIntBits(criticalnum);
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(time).append(",");
		_sb_.append(receinexunibi).append(",");
		_sb_.append(criticalnum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

