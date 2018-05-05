
package lx.gs.exchange.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SInfo extends __SInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557601;

	public int getType() {
		return 6557601;
	}

	public java.util.ArrayList<lx.gs.exchange.msg.ExchangeItem> items;
	public java.util.ArrayList<lx.gs.exchange.msg.ExchangeLog> logs;

	public SInfo() {
		items = new java.util.ArrayList<lx.gs.exchange.msg.ExchangeItem>();
		logs = new java.util.ArrayList<lx.gs.exchange.msg.ExchangeLog>();
	}

	public SInfo(java.util.ArrayList<lx.gs.exchange.msg.ExchangeItem> _items_, java.util.ArrayList<lx.gs.exchange.msg.ExchangeLog> _logs_) {
		this.items = _items_;
		this.logs = _logs_;
	}

	public final boolean _validator_() {
		for (lx.gs.exchange.msg.ExchangeItem _v_ : items)
			if (!_v_._validator_()) return false;
		for (lx.gs.exchange.msg.ExchangeLog _v_ : logs)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(items.size());
		for (lx.gs.exchange.msg.ExchangeItem _v_ : items) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(logs.size());
		for (lx.gs.exchange.msg.ExchangeLog _v_ : logs) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.exchange.msg.ExchangeItem _v_ = new lx.gs.exchange.msg.ExchangeItem();
			_v_.unmarshal(_os_);
			items.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.exchange.msg.ExchangeLog _v_ = new lx.gs.exchange.msg.ExchangeLog();
			_v_.unmarshal(_os_);
			logs.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SInfo) {
			SInfo _o_ = (SInfo)_o1_;
			if (!items.equals(_o_.items)) return false;
			if (!logs.equals(_o_.logs)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += items.hashCode();
		_h_ += logs.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(items).append(",");
		_sb_.append(logs).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

