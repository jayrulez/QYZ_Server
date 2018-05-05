
package lx.gs.pay;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetPay__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetPay extends __SGetPay__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555402;

	public int getType() {
		return 6555402;
	}

	public java.util.ArrayList<lx.gs.pay.Product> products;
	public int isfirstpayused;
	public lx.gs.pay.PayAward firstpayaward;
	public lx.gs.pay.PayAward dailypayaward;
	public lx.gs.pay.PayAward dailytotalpayaward;

	public SGetPay() {
		products = new java.util.ArrayList<lx.gs.pay.Product>();
		firstpayaward = new lx.gs.pay.PayAward();
		dailypayaward = new lx.gs.pay.PayAward();
		dailytotalpayaward = new lx.gs.pay.PayAward();
	}

	public SGetPay(java.util.ArrayList<lx.gs.pay.Product> _products_, int _isfirstpayused_, lx.gs.pay.PayAward _firstpayaward_, lx.gs.pay.PayAward _dailypayaward_, lx.gs.pay.PayAward _dailytotalpayaward_) {
		this.products = _products_;
		this.isfirstpayused = _isfirstpayused_;
		this.firstpayaward = _firstpayaward_;
		this.dailypayaward = _dailypayaward_;
		this.dailytotalpayaward = _dailytotalpayaward_;
	}

	public final boolean _validator_() {
		for (lx.gs.pay.Product _v_ : products)
			if (!_v_._validator_()) return false;
		if (!firstpayaward._validator_()) return false;
		if (!dailypayaward._validator_()) return false;
		if (!dailytotalpayaward._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(products.size());
		for (lx.gs.pay.Product _v_ : products) {
			_os_.marshal(_v_);
		}
		_os_.marshal(isfirstpayused);
		_os_.marshal(firstpayaward);
		_os_.marshal(dailypayaward);
		_os_.marshal(dailytotalpayaward);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.pay.Product _v_ = new lx.gs.pay.Product();
			_v_.unmarshal(_os_);
			products.add(_v_);
		}
		isfirstpayused = _os_.unmarshal_int();
		firstpayaward.unmarshal(_os_);
		dailypayaward.unmarshal(_os_);
		dailytotalpayaward.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetPay) {
			SGetPay _o_ = (SGetPay)_o1_;
			if (!products.equals(_o_.products)) return false;
			if (isfirstpayused != _o_.isfirstpayused) return false;
			if (!firstpayaward.equals(_o_.firstpayaward)) return false;
			if (!dailypayaward.equals(_o_.dailypayaward)) return false;
			if (!dailytotalpayaward.equals(_o_.dailytotalpayaward)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += products.hashCode();
		_h_ += isfirstpayused;
		_h_ += firstpayaward.hashCode();
		_h_ += dailypayaward.hashCode();
		_h_ += dailytotalpayaward.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(products).append(",");
		_sb_.append(isfirstpayused).append(",");
		_sb_.append(firstpayaward).append(",");
		_sb_.append(dailypayaward).append(",");
		_sb_.append(dailytotalpayaward).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

