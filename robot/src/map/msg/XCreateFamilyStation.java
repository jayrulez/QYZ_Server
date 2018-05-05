
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreateFamilyStation__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreateFamilyStation extends __XCreateFamilyStation__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6711355;

	public int getType() {
		return 6711355;
	}

	public long familyid;
	public int serverid;
	public java.util.ArrayList<Long> members;
	public java.util.HashMap<Integer,Integer> godanimallvl;

	public XCreateFamilyStation() {
		members = new java.util.ArrayList<Long>();
		godanimallvl = new java.util.HashMap<Integer,Integer>();
	}

	public XCreateFamilyStation(long _familyid_, int _serverid_, java.util.ArrayList<Long> _members_, java.util.HashMap<Integer,Integer> _godanimallvl_) {
		this.familyid = _familyid_;
		this.serverid = _serverid_;
		this.members = _members_;
		this.godanimallvl = _godanimallvl_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familyid);
		_os_.marshal(serverid);
		_os_.compact_uint32(members.size());
		for (Long _v_ : members) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(godanimallvl.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : godanimallvl.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familyid = _os_.unmarshal_long();
		serverid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			members.add(_v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			godanimallvl.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreateFamilyStation) {
			XCreateFamilyStation _o_ = (XCreateFamilyStation)_o1_;
			if (familyid != _o_.familyid) return false;
			if (serverid != _o_.serverid) return false;
			if (!members.equals(_o_.members)) return false;
			if (!godanimallvl.equals(_o_.godanimallvl)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)familyid;
		_h_ += serverid;
		_h_ += members.hashCode();
		_h_ += godanimallvl.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(familyid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(members).append(",");
		_sb_.append(godanimallvl).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

