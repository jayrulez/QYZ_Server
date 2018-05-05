package lx.gs.talisman;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SWuxingWash__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SWuxingWash extends __SWuxingWash__ {
    @Override
    protected void process() {
        // protocol handle
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6567296;

	public int getType() {
		return 6567296;
	}

	public int bagtype;
	public int pos;
	public int criticaltimes; // 洗练出现的暴击次数，如果为1表示没有
	public int wuxingvalue; // 洗练后的值
	public int washtimes; // 当前运势下洗练次数

	public SWuxingWash() {
	}

	public SWuxingWash(int _bagtype_, int _pos_, int _criticaltimes_, int _wuxingvalue_, int _washtimes_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.criticaltimes = _criticaltimes_;
		this.wuxingvalue = _wuxingvalue_;
		this.washtimes = _washtimes_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(criticaltimes);
		_os_.marshal(wuxingvalue);
		_os_.marshal(washtimes);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		criticaltimes = _os_.unmarshal_int();
		wuxingvalue = _os_.unmarshal_int();
		washtimes = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SWuxingWash) {
			SWuxingWash _o_ = (SWuxingWash)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (criticaltimes != _o_.criticaltimes) return false;
			if (wuxingvalue != _o_.wuxingvalue) return false;
			if (washtimes != _o_.washtimes) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += criticaltimes;
		_h_ += wuxingvalue;
		_h_ += washtimes;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(criticaltimes).append(",");
		_sb_.append(wuxingvalue).append(",");
		_sb_.append(washtimes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SWuxingWash _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = criticaltimes - _o_.criticaltimes;
		if (0 != _c_) return _c_;
		_c_ = wuxingvalue - _o_.wuxingvalue;
		if (0 != _c_) return _c_;
		_c_ = washtimes - _o_.washtimes;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

