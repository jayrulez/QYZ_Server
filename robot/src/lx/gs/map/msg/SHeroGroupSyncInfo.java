
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SHeroGroupSyncInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SHeroGroupSyncInfo extends __SHeroGroupSyncInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580497;

	public int getType() {
		return 6580497;
	}

	public int groupid;
	public lx.gs.map.msg.HeroesGroupInfo groupinfo;

	public SHeroGroupSyncInfo() {
		groupinfo = new lx.gs.map.msg.HeroesGroupInfo();
	}

	public SHeroGroupSyncInfo(int _groupid_, lx.gs.map.msg.HeroesGroupInfo _groupinfo_) {
		this.groupid = _groupid_;
		this.groupinfo = _groupinfo_;
	}

	public final boolean _validator_() {
		if (!groupinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(groupid);
		_os_.marshal(groupinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		groupid = _os_.unmarshal_int();
		groupinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SHeroGroupSyncInfo) {
			SHeroGroupSyncInfo _o_ = (SHeroGroupSyncInfo)_o1_;
			if (groupid != _o_.groupid) return false;
			if (!groupinfo.equals(_o_.groupinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += groupid;
		_h_ += groupinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(groupid).append(",");
		_sb_.append(groupinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SHeroGroupSyncInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = groupid - _o_.groupid;
		if (0 != _c_) return _c_;
		_c_ = groupinfo.compareTo(_o_.groupinfo);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

