
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEndHeroes__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEndHeroes extends __SEndHeroes__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6717362;

	public int getType() {
		return 6717362;
	}

	public int retcode;
	public int groupid;
	public int ectypeid;
	public map.msg.HeroesAwardInfo awardinfo;

	public SEndHeroes() {
		awardinfo = new map.msg.HeroesAwardInfo();
	}

	public SEndHeroes(int _retcode_, int _groupid_, int _ectypeid_, map.msg.HeroesAwardInfo _awardinfo_) {
		this.retcode = _retcode_;
		this.groupid = _groupid_;
		this.ectypeid = _ectypeid_;
		this.awardinfo = _awardinfo_;
	}

	public final boolean _validator_() {
		if (!awardinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(retcode);
		_os_.marshal(groupid);
		_os_.marshal(ectypeid);
		_os_.marshal(awardinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		retcode = _os_.unmarshal_int();
		groupid = _os_.unmarshal_int();
		ectypeid = _os_.unmarshal_int();
		awardinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEndHeroes) {
			SEndHeroes _o_ = (SEndHeroes)_o1_;
			if (retcode != _o_.retcode) return false;
			if (groupid != _o_.groupid) return false;
			if (ectypeid != _o_.ectypeid) return false;
			if (!awardinfo.equals(_o_.awardinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += retcode;
		_h_ += groupid;
		_h_ += ectypeid;
		_h_ += awardinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(retcode).append(",");
		_sb_.append(groupid).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(awardinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

