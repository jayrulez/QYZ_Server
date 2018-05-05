
package lx.gs.pay;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Product implements Marshal , Comparable<Product>{
	public int productid;
	public int rmb;
	public int money;
	public int firstpaymoney;
	public int payreturnmoney;

	public Product() {
	}

	public Product(int _productid_, int _rmb_, int _money_, int _firstpaymoney_, int _payreturnmoney_) {
		this.productid = _productid_;
		this.rmb = _rmb_;
		this.money = _money_;
		this.firstpaymoney = _firstpaymoney_;
		this.payreturnmoney = _payreturnmoney_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(productid);
		_os_.marshal(rmb);
		_os_.marshal(money);
		_os_.marshal(firstpaymoney);
		_os_.marshal(payreturnmoney);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		productid = _os_.unmarshal_int();
		rmb = _os_.unmarshal_int();
		money = _os_.unmarshal_int();
		firstpaymoney = _os_.unmarshal_int();
		payreturnmoney = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Product) {
			Product _o_ = (Product)_o1_;
			if (productid != _o_.productid) return false;
			if (rmb != _o_.rmb) return false;
			if (money != _o_.money) return false;
			if (firstpaymoney != _o_.firstpaymoney) return false;
			if (payreturnmoney != _o_.payreturnmoney) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += productid;
		_h_ += rmb;
		_h_ += money;
		_h_ += firstpaymoney;
		_h_ += payreturnmoney;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(productid).append(",");
		_sb_.append(rmb).append(",");
		_sb_.append(money).append(",");
		_sb_.append(firstpaymoney).append(",");
		_sb_.append(payreturnmoney).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(Product _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = productid - _o_.productid;
		if (0 != _c_) return _c_;
		_c_ = rmb - _o_.rmb;
		if (0 != _c_) return _c_;
		_c_ = money - _o_.money;
		if (0 != _c_) return _c_;
		_c_ = firstpaymoney - _o_.firstpaymoney;
		if (0 != _c_) return _c_;
		_c_ = payreturnmoney - _o_.payreturnmoney;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

