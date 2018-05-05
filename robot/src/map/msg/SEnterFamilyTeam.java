
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEnterFamilyTeam__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEnterFamilyTeam extends __SEnterFamilyTeam__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6714053;

	public int getType() {
		return 6714053;
	}

	public int ectypeid;
	public long mapid;
	public int monsterwaveindex; // 从1开始

	public SEnterFamilyTeam() {
	}

	public SEnterFamilyTeam(int _ectypeid_, long _mapid_, int _monsterwaveindex_) {
		this.ectypeid = _ectypeid_;
		this.mapid = _mapid_;
		this.monsterwaveindex = _monsterwaveindex_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(mapid);
		_os_.marshal(monsterwaveindex);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		mapid = _os_.unmarshal_long();
		monsterwaveindex = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEnterFamilyTeam) {
			SEnterFamilyTeam _o_ = (SEnterFamilyTeam)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (mapid != _o_.mapid) return false;
			if (monsterwaveindex != _o_.monsterwaveindex) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += (int)mapid;
		_h_ += monsterwaveindex;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(mapid).append(",");
		_sb_.append(monsterwaveindex).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEnterFamilyTeam _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(mapid - _o_.mapid);
		if (0 != _c_) return _c_;
		_c_ = monsterwaveindex - _o_.monsterwaveindex;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

