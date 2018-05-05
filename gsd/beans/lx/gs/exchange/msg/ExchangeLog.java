
package lx.gs.exchange.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class ExchangeLog implements Marshal {
	public long seller;
	public long buyer;
	public java.lang.String buyername;
	public lx.gs.exchange.msg.ExchangeItem item;
	public long time;

	public ExchangeLog() {
		buyername = "";
		item = new lx.gs.exchange.msg.ExchangeItem();
	}

	public ExchangeLog(long _seller_, long _buyer_, java.lang.String _buyername_, lx.gs.exchange.msg.ExchangeItem _item_, long _time_) {
		this.seller = _seller_;
		this.buyer = _buyer_;
		this.buyername = _buyername_;
		this.item = _item_;
		this.time = _time_;
	}

	public final boolean _validator_() {
		if (!item._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(seller);
		_os_.marshal(buyer);
		_os_.marshal(buyername, "UTF-16LE");
		_os_.marshal(item);
		_os_.marshal(time);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		seller = _os_.unmarshal_long();
		buyer = _os_.unmarshal_long();
		buyername = _os_.unmarshal_String("UTF-16LE");
		item.unmarshal(_os_);
		time = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ExchangeLog) {
			ExchangeLog _o_ = (ExchangeLog)_o1_;
			if (seller != _o_.seller) return false;
			if (buyer != _o_.buyer) return false;
			if (!buyername.equals(_o_.buyername)) return false;
			if (!item.equals(_o_.item)) return false;
			if (time != _o_.time) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)seller;
		_h_ += (int)buyer;
		_h_ += buyername.hashCode();
		_h_ += item.hashCode();
		_h_ += (int)time;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(seller).append(",");
		_sb_.append(buyer).append(",");
		_sb_.append("T").append(buyername.length()).append(",");
		_sb_.append(item).append(",");
		_sb_.append(time).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

