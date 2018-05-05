
package lx.gs.pay;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetAppOrder__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetAppOrder extends __SGetAppOrder__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555404;

	public int getType() {
		return 6555404;
	}

	public final static int OK = 0;
	public final static int PRODUCT_NOT_FOUND = 1;

	public int err;
	public java.lang.String orderid;
	public int productid;

	public SGetAppOrder() {
		orderid = "";
	}

	public SGetAppOrder(int _err_, java.lang.String _orderid_, int _productid_) {
		this.err = _err_;
		this.orderid = _orderid_;
		this.productid = _productid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(err);
		_os_.marshal(orderid, "UTF-16LE");
		_os_.marshal(productid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		err = _os_.unmarshal_int();
		orderid = _os_.unmarshal_String("UTF-16LE");
		productid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetAppOrder) {
			SGetAppOrder _o_ = (SGetAppOrder)_o1_;
			if (err != _o_.err) return false;
			if (!orderid.equals(_o_.orderid)) return false;
			if (productid != _o_.productid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += err;
		_h_ += orderid.hashCode();
		_h_ += productid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(err).append(",");
		_sb_.append("T").append(orderid.length()).append(",");
		_sb_.append(productid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

