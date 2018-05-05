
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSweepChapterAllStoryEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSweepChapterAllStoryEctype extends __SSweepChapterAllStoryEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572999;

	public int getType() {
		return 6572999;
	}

	public int chapterid;
	public java.util.HashMap<Integer,map.msg.Bonus> bonus;

	public SSweepChapterAllStoryEctype() {
		bonus = new java.util.HashMap<Integer,map.msg.Bonus>();
	}

	public SSweepChapterAllStoryEctype(int _chapterid_, java.util.HashMap<Integer,map.msg.Bonus> _bonus_) {
		this.chapterid = _chapterid_;
		this.bonus = _bonus_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, map.msg.Bonus> _e_ : bonus.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(chapterid);
		_os_.compact_uint32(bonus.size());
		for (java.util.Map.Entry<Integer, map.msg.Bonus> _e_ : bonus.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		chapterid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			map.msg.Bonus _v_ = new map.msg.Bonus();
			_v_.unmarshal(_os_);
			bonus.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSweepChapterAllStoryEctype) {
			SSweepChapterAllStoryEctype _o_ = (SSweepChapterAllStoryEctype)_o1_;
			if (chapterid != _o_.chapterid) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += chapterid;
		_h_ += bonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(chapterid).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

