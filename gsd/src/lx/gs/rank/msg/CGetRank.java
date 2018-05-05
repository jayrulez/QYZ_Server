
package lx.gs.rank.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gnet.link.Onlines;
import lx.gs.rank.FRank;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetRank__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetRank extends __CGetRank__ {
	@Override
	protected void process() {
		final Onlines ins = Onlines.getInstance();
		ins.sendResponse(this, FRank.createSGetRank(ins.find(this).getRoleid(), ranktype, rankstart, rankend));
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577970;

	public int getType() {
		return 6577970;
	}

	public int ranktype;
	public int rankstart;
	public int rankend; // 从rankstart到rankend的数据(包括rankend)

	public CGetRank() {
	}

	public CGetRank(int _ranktype_, int _rankstart_, int _rankend_) {
		this.ranktype = _ranktype_;
		this.rankstart = _rankstart_;
		this.rankend = _rankend_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ranktype);
		_os_.marshal(rankstart);
		_os_.marshal(rankend);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ranktype = _os_.unmarshal_int();
		rankstart = _os_.unmarshal_int();
		rankend = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetRank) {
			CGetRank _o_ = (CGetRank)_o1_;
			if (ranktype != _o_.ranktype) return false;
			if (rankstart != _o_.rankstart) return false;
			if (rankend != _o_.rankend) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ranktype;
		_h_ += rankstart;
		_h_ += rankend;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ranktype).append(",");
		_sb_.append(rankstart).append(",");
		_sb_.append(rankend).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetRank _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ranktype - _o_.ranktype;
		if (0 != _c_) return _c_;
		_c_ = rankstart - _o_.rankstart;
		if (0 != _c_) return _c_;
		_c_ = rankend - _o_.rankend;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

