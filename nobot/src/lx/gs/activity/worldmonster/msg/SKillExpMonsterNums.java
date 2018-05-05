
package lx.gs.activity.worldmonster.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SKillExpMonsterNums__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SKillExpMonsterNums extends __SKillExpMonsterNums__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583420;

	public int getType() {
		return 6583420;
	}

	public int nums;
	public java.util.ArrayList<Integer> receivedbonus;

	public SKillExpMonsterNums() {
		receivedbonus = new java.util.ArrayList<Integer>();
	}

	public SKillExpMonsterNums(int _nums_, java.util.ArrayList<Integer> _receivedbonus_) {
		this.nums = _nums_;
		this.receivedbonus = _receivedbonus_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(nums);
		_os_.compact_uint32(receivedbonus.size());
		for (Integer _v_ : receivedbonus) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		nums = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			receivedbonus.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SKillExpMonsterNums) {
			SKillExpMonsterNums _o_ = (SKillExpMonsterNums)_o1_;
			if (nums != _o_.nums) return false;
			if (!receivedbonus.equals(_o_.receivedbonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += nums;
		_h_ += receivedbonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(nums).append(",");
		_sb_.append(receivedbonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

