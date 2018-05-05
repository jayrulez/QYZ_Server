
package lx.gs.pay;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetAppOrder__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetAppOrder extends __CGetAppOrder__ {
	@Override
	protected void process() {
		new PGetAppOrder(this).execute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555403;

	public int getType() {
		return 6555403;
	}

	public int productid;

	public CGetAppOrder() {
	}

	public CGetAppOrder(int _productid_) {
		this.productid = _productid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(productid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		productid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetAppOrder) {
			CGetAppOrder _o_ = (CGetAppOrder)_o1_;
			if (productid != _o_.productid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += productid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(productid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetAppOrder _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = productid - _o_.productid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

