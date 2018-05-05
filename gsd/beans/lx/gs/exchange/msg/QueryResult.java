
package lx.gs.exchange.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class QueryResult implements Marshal {
	public int totalnum;
	public java.util.ArrayList<lx.gs.exchange.msg.ExchangeItem> items;

	public QueryResult() {
		items = new java.util.ArrayList<lx.gs.exchange.msg.ExchangeItem>();
	}

	public QueryResult(int _totalnum_, java.util.ArrayList<lx.gs.exchange.msg.ExchangeItem> _items_) {
		this.totalnum = _totalnum_;
		this.items = _items_;
	}

	public final boolean _validator_() {
		for (lx.gs.exchange.msg.ExchangeItem _v_ : items)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(totalnum);
		_os_.compact_uint32(items.size());
		for (lx.gs.exchange.msg.ExchangeItem _v_ : items) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		totalnum = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.exchange.msg.ExchangeItem _v_ = new lx.gs.exchange.msg.ExchangeItem();
			_v_.unmarshal(_os_);
			items.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof QueryResult) {
			QueryResult _o_ = (QueryResult)_o1_;
			if (totalnum != _o_.totalnum) return false;
			if (!items.equals(_o_.items)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += totalnum;
		_h_ += items.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(totalnum).append(",");
		_sb_.append(items).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

