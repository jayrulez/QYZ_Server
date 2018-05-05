
package lx.gs.pet.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gs.AProcedure;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEvolvePetSkill__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEvolvePetSkill extends __SEvolvePetSkill__ {
	@Override
	protected void process() {
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582727;

	public int getType() {
		return 6582727;
	}

	public int modelid;
	public int oldskillid;
	public int newskillid;
	public int level;

	public SEvolvePetSkill() {
	}

	public SEvolvePetSkill(int _modelid_, int _oldskillid_, int _newskillid_, int _level_) {
		this.modelid = _modelid_;
		this.oldskillid = _oldskillid_;
		this.newskillid = _newskillid_;
		this.level = _level_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(modelid);
		_os_.marshal(oldskillid);
		_os_.marshal(newskillid);
		_os_.marshal(level);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		modelid = _os_.unmarshal_int();
		oldskillid = _os_.unmarshal_int();
		newskillid = _os_.unmarshal_int();
		level = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEvolvePetSkill) {
			SEvolvePetSkill _o_ = (SEvolvePetSkill)_o1_;
			if (modelid != _o_.modelid) return false;
			if (oldskillid != _o_.oldskillid) return false;
			if (newskillid != _o_.newskillid) return false;
			if (level != _o_.level) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += modelid;
		_h_ += oldskillid;
		_h_ += newskillid;
		_h_ += level;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid).append(",");
		_sb_.append(oldskillid).append(",");
		_sb_.append(newskillid).append(",");
		_sb_.append(level).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEvolvePetSkill _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		_c_ = oldskillid - _o_.oldskillid;
		if (0 != _c_) return _c_;
		_c_ = newskillid - _o_.newskillid;
		if (0 != _c_) return _c_;
		_c_ = level - _o_.level;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

