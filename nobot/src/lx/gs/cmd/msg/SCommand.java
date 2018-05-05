
package lx.gs.cmd.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCommand__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCommand extends __SCommand__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6569865;

	public int getType() {
		return 6569865;
	}

	public int errcode;
	public int errparam;
	public int moduleid;
	public int cmdid;
	public int num;
	public map.msg.Bonus bonus;

	public SCommand() {
		bonus = new map.msg.Bonus();
	}

	public SCommand(int _errcode_, int _errparam_, int _moduleid_, int _cmdid_, int _num_, map.msg.Bonus _bonus_) {
		this.errcode = _errcode_;
		this.errparam = _errparam_;
		this.moduleid = _moduleid_;
		this.cmdid = _cmdid_;
		this.num = _num_;
		this.bonus = _bonus_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(errcode);
		_os_.marshal(errparam);
		_os_.marshal(moduleid);
		_os_.marshal(cmdid);
		_os_.marshal(num);
		_os_.marshal(bonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		errcode = _os_.unmarshal_int();
		errparam = _os_.unmarshal_int();
		moduleid = _os_.unmarshal_int();
		cmdid = _os_.unmarshal_int();
		num = _os_.unmarshal_int();
		bonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCommand) {
			SCommand _o_ = (SCommand)_o1_;
			if (errcode != _o_.errcode) return false;
			if (errparam != _o_.errparam) return false;
			if (moduleid != _o_.moduleid) return false;
			if (cmdid != _o_.cmdid) return false;
			if (num != _o_.num) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += errcode;
		_h_ += errparam;
		_h_ += moduleid;
		_h_ += cmdid;
		_h_ += num;
		_h_ += bonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(errcode).append(",");
		_sb_.append(errparam).append(",");
		_sb_.append(moduleid).append(",");
		_sb_.append(cmdid).append(",");
		_sb_.append(num).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

