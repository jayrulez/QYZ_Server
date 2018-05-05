
package lx.gs.arena.msg;

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
	public static final int PROTOCOL_TYPE = 6577819;

	public int getType() {
		return 6577819;
	}

	public byte isopen;
	public int rank;
	public int challengesuccnum;
	public java.util.ArrayList<Integer> obtainrewards;
	public java.util.ArrayList<lx.gs.arena.msg.FightReport> reports;
	public int bestrank;
	public int lastrewardrank;

	public SInfo() {
		obtainrewards = new java.util.ArrayList<Integer>();
		reports = new java.util.ArrayList<lx.gs.arena.msg.FightReport>();
	}

	public SInfo(byte _isopen_, int _rank_, int _challengesuccnum_, java.util.ArrayList<Integer> _obtainrewards_, java.util.ArrayList<lx.gs.arena.msg.FightReport> _reports_, int _bestrank_, int _lastrewardrank_) {
		this.isopen = _isopen_;
		this.rank = _rank_;
		this.challengesuccnum = _challengesuccnum_;
		this.obtainrewards = _obtainrewards_;
		this.reports = _reports_;
		this.bestrank = _bestrank_;
		this.lastrewardrank = _lastrewardrank_;
	}

	public final boolean _validator_() {
		for (lx.gs.arena.msg.FightReport _v_ : reports)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(isopen);
		_os_.marshal(rank);
		_os_.marshal(challengesuccnum);
		_os_.compact_uint32(obtainrewards.size());
		for (Integer _v_ : obtainrewards) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(reports.size());
		for (lx.gs.arena.msg.FightReport _v_ : reports) {
			_os_.marshal(_v_);
		}
		_os_.marshal(bestrank);
		_os_.marshal(lastrewardrank);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		isopen = _os_.unmarshal_byte();
		rank = _os_.unmarshal_int();
		challengesuccnum = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			obtainrewards.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.arena.msg.FightReport _v_ = new lx.gs.arena.msg.FightReport();
			_v_.unmarshal(_os_);
			reports.add(_v_);
		}
		bestrank = _os_.unmarshal_int();
		lastrewardrank = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SInfo) {
			SInfo _o_ = (SInfo)_o1_;
			if (isopen != _o_.isopen) return false;
			if (rank != _o_.rank) return false;
			if (challengesuccnum != _o_.challengesuccnum) return false;
			if (!obtainrewards.equals(_o_.obtainrewards)) return false;
			if (!reports.equals(_o_.reports)) return false;
			if (bestrank != _o_.bestrank) return false;
			if (lastrewardrank != _o_.lastrewardrank) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)isopen;
		_h_ += rank;
		_h_ += challengesuccnum;
		_h_ += obtainrewards.hashCode();
		_h_ += reports.hashCode();
		_h_ += bestrank;
		_h_ += lastrewardrank;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(isopen).append(",");
		_sb_.append(rank).append(",");
		_sb_.append(challengesuccnum).append(",");
		_sb_.append(obtainrewards).append(",");
		_sb_.append(reports).append(",");
		_sb_.append(bestrank).append(",");
		_sb_.append(lastrewardrank).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

