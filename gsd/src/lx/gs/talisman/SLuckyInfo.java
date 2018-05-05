package lx.gs.talisman;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SLuckyInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SLuckyInfo extends __SLuckyInfo__ {
    @Override
    protected void process() {
        // protocol handle
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583777;

	public int getType() {
		return 6583777;
	}

	public int luckytype;
	public int washcount;

	public SLuckyInfo() {
	}

	public SLuckyInfo(int _luckytype_, int _washcount_) {
		this.luckytype = _luckytype_;
		this.washcount = _washcount_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(luckytype);
		_os_.marshal(washcount);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		luckytype = _os_.unmarshal_int();
		washcount = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SLuckyInfo) {
			SLuckyInfo _o_ = (SLuckyInfo)_o1_;
			if (luckytype != _o_.luckytype) return false;
			if (washcount != _o_.washcount) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += luckytype;
		_h_ += washcount;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(luckytype).append(",");
		_sb_.append(washcount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SLuckyInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = luckytype - _o_.luckytype;
		if (0 != _c_) return _c_;
		_c_ = washcount - _o_.washcount;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

