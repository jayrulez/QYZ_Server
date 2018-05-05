
package lx.gs.rank.msg;

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
	public static final int PROTOCOL_TYPE = 6567203;

	public int getType() {
		return 6567203;
	}

	public java.util.ArrayList<lx.gs.rank.msg.RankInfo> ranks;

	public SInfo() {
		ranks = new java.util.ArrayList<lx.gs.rank.msg.RankInfo>();
	}

	public SInfo(java.util.ArrayList<lx.gs.rank.msg.RankInfo> _ranks_) {
		this.ranks = _ranks_;
	}

	public final boolean _validator_() {
		for (lx.gs.rank.msg.RankInfo _v_ : ranks)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(ranks.size());
		for (lx.gs.rank.msg.RankInfo _v_ : ranks) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.rank.msg.RankInfo _v_ = new lx.gs.rank.msg.RankInfo();
			_v_.unmarshal(_os_);
			ranks.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SInfo) {
			SInfo _o_ = (SInfo)_o1_;
			if (!ranks.equals(_o_.ranks)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ranks.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ranks).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

