
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XChangeTalisman__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XChangeTalisman extends __XChangeTalisman__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6700703;

	public int getType() {
		return 6700703;
	}

	public int talismanmodelid;
	public java.util.HashMap<Integer,Integer> skills;
	public java.util.ArrayList<Integer> buffs;

	public XChangeTalisman() {
		skills = new java.util.HashMap<Integer,Integer>();
		buffs = new java.util.ArrayList<Integer>();
	}

	public XChangeTalisman(int _talismanmodelid_, java.util.HashMap<Integer,Integer> _skills_, java.util.ArrayList<Integer> _buffs_) {
		this.talismanmodelid = _talismanmodelid_;
		this.skills = _skills_;
		this.buffs = _buffs_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(talismanmodelid);
		_os_.compact_uint32(skills.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : skills.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(buffs.size());
		for (Integer _v_ : buffs) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		talismanmodelid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			skills.put(_k_, _v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			buffs.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XChangeTalisman) {
			XChangeTalisman _o_ = (XChangeTalisman)_o1_;
			if (talismanmodelid != _o_.talismanmodelid) return false;
			if (!skills.equals(_o_.skills)) return false;
			if (!buffs.equals(_o_.buffs)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += talismanmodelid;
		_h_ += skills.hashCode();
		_h_ += buffs.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(talismanmodelid).append(",");
		_sb_.append(skills).append(",");
		_sb_.append(buffs).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

