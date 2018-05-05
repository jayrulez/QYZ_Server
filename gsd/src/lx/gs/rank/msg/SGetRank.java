
package lx.gs.rank.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetRank__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetRank extends __SGetRank__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565446;

	public int getType() {
		return 6565446;
	}

	public int ranktype;
	public int ranksize;
	public int rankstart;
	public com.goldhuman.Common.Octets data;
	public int mycurrank;

	public SGetRank() {
		data = new com.goldhuman.Common.Octets();
	}

	public SGetRank(int _ranktype_, int _ranksize_, int _rankstart_, com.goldhuman.Common.Octets _data_, int _mycurrank_) {
		this.ranktype = _ranktype_;
		this.ranksize = _ranksize_;
		this.rankstart = _rankstart_;
		this.data = _data_;
		this.mycurrank = _mycurrank_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ranktype);
		_os_.marshal(ranksize);
		_os_.marshal(rankstart);
		_os_.marshal(data);
		_os_.marshal(mycurrank);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ranktype = _os_.unmarshal_int();
		ranksize = _os_.unmarshal_int();
		rankstart = _os_.unmarshal_int();
		data = _os_.unmarshal_Octets();
		mycurrank = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetRank) {
			SGetRank _o_ = (SGetRank)_o1_;
			if (ranktype != _o_.ranktype) return false;
			if (ranksize != _o_.ranksize) return false;
			if (rankstart != _o_.rankstart) return false;
			if (!data.equals(_o_.data)) return false;
			if (mycurrank != _o_.mycurrank) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ranktype;
		_h_ += ranksize;
		_h_ += rankstart;
		_h_ += data.hashCode();
		_h_ += mycurrank;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ranktype).append(",");
		_sb_.append(ranksize).append(",");
		_sb_.append(rankstart).append(",");
		_sb_.append("B").append(data.size()).append(",");
		_sb_.append(mycurrank).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

