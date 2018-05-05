
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetDailyBestRecord__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetDailyBestRecord extends __SGetDailyBestRecord__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579621;

	public int getType() {
		return 6579621;
	}

	public int ectypetype;
	public java.lang.String name;
	public int mincosttime; // 秒数
	public int mymincosttime; // 秒数ss

	public SGetDailyBestRecord() {
		name = "";
	}

	public SGetDailyBestRecord(int _ectypetype_, java.lang.String _name_, int _mincosttime_, int _mymincosttime_) {
		this.ectypetype = _ectypetype_;
		this.name = _name_;
		this.mincosttime = _mincosttime_;
		this.mymincosttime = _mymincosttime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypetype);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(mincosttime);
		_os_.marshal(mymincosttime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypetype = _os_.unmarshal_int();
		name = _os_.unmarshal_String("UTF-16LE");
		mincosttime = _os_.unmarshal_int();
		mymincosttime = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetDailyBestRecord) {
			SGetDailyBestRecord _o_ = (SGetDailyBestRecord)_o1_;
			if (ectypetype != _o_.ectypetype) return false;
			if (!name.equals(_o_.name)) return false;
			if (mincosttime != _o_.mincosttime) return false;
			if (mymincosttime != _o_.mymincosttime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypetype;
		_h_ += name.hashCode();
		_h_ += mincosttime;
		_h_ += mymincosttime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypetype).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(mincosttime).append(",");
		_sb_.append(mymincosttime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

