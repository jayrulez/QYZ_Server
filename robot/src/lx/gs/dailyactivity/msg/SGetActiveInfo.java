
package lx.gs.dailyactivity.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetActiveInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetActiveInfo extends __SGetActiveInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556432;

	public int getType() {
		return 6556432;
	}

	public int scores;
	public java.util.HashMap<Integer,Integer> activetimes;
	public java.util.ArrayList<Integer> receivedbonus; // 已领取的奖励

	public SGetActiveInfo() {
		activetimes = new java.util.HashMap<Integer,Integer>();
		receivedbonus = new java.util.ArrayList<Integer>();
	}

	public SGetActiveInfo(int _scores_, java.util.HashMap<Integer,Integer> _activetimes_, java.util.ArrayList<Integer> _receivedbonus_) {
		this.scores = _scores_;
		this.activetimes = _activetimes_;
		this.receivedbonus = _receivedbonus_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(scores);
		_os_.compact_uint32(activetimes.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : activetimes.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(receivedbonus.size());
		for (Integer _v_ : receivedbonus) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		scores = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			activetimes.put(_k_, _v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			receivedbonus.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetActiveInfo) {
			SGetActiveInfo _o_ = (SGetActiveInfo)_o1_;
			if (scores != _o_.scores) return false;
			if (!activetimes.equals(_o_.activetimes)) return false;
			if (!receivedbonus.equals(_o_.receivedbonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += scores;
		_h_ += activetimes.hashCode();
		_h_ += receivedbonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(scores).append(",");
		_sb_.append(activetimes).append(",");
		_sb_.append(receivedbonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

