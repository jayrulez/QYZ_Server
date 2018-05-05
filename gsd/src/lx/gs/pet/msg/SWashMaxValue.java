
package lx.gs.pet.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SWashMaxValue__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SWashMaxValue extends __SWashMaxValue__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6569130;

	public int getType() {
		return 6569130;
	}

	public int modelid;
	public java.util.HashMap<Integer,Float> currvalues;
	public java.util.HashMap<Integer,Float> maxvalues;

	public SWashMaxValue() {
		currvalues = new java.util.HashMap<Integer,Float>();
		maxvalues = new java.util.HashMap<Integer,Float>();
	}

	public SWashMaxValue(int _modelid_, java.util.HashMap<Integer,Float> _currvalues_, java.util.HashMap<Integer,Float> _maxvalues_) {
		this.modelid = _modelid_;
		this.currvalues = _currvalues_;
		this.maxvalues = _maxvalues_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(modelid);
		_os_.compact_uint32(currvalues.size());
		for (java.util.Map.Entry<Integer, Float> _e_ : currvalues.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(maxvalues.size());
		for (java.util.Map.Entry<Integer, Float> _e_ : maxvalues.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		modelid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			float _v_;
			_v_ = _os_.unmarshal_float();
			currvalues.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			float _v_;
			_v_ = _os_.unmarshal_float();
			maxvalues.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SWashMaxValue) {
			SWashMaxValue _o_ = (SWashMaxValue)_o1_;
			if (modelid != _o_.modelid) return false;
			if (!currvalues.equals(_o_.currvalues)) return false;
			if (!maxvalues.equals(_o_.maxvalues)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += modelid;
		_h_ += currvalues.hashCode();
		_h_ += maxvalues.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid).append(",");
		_sb_.append(currvalues).append(",");
		_sb_.append(maxvalues).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

