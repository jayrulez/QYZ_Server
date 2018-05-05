
package lx.gs.pay;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class PayAward implements Marshal {
	public final static int STATUS_NOT_PAY = 0;
	public final static int STATUS_NOT_GET_AWARD = 1;
	public final static int STATUS_GET_AWARD = 2;

	public int status;
	public java.util.ArrayList<lx.gs.pay.Item> awarditems;

	public PayAward() {
		awarditems = new java.util.ArrayList<lx.gs.pay.Item>();
	}

	public PayAward(int _status_, java.util.ArrayList<lx.gs.pay.Item> _awarditems_) {
		this.status = _status_;
		this.awarditems = _awarditems_;
	}

	public final boolean _validator_() {
		for (lx.gs.pay.Item _v_ : awarditems)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(status);
		_os_.compact_uint32(awarditems.size());
		for (lx.gs.pay.Item _v_ : awarditems) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		status = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.pay.Item _v_ = new lx.gs.pay.Item();
			_v_.unmarshal(_os_);
			awarditems.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PayAward) {
			PayAward _o_ = (PayAward)_o1_;
			if (status != _o_.status) return false;
			if (!awarditems.equals(_o_.awarditems)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += status;
		_h_ += awarditems.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(status).append(",");
		_sb_.append(awarditems).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

