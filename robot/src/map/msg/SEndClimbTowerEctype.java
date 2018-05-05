
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEndClimbTowerEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEndClimbTowerEctype extends __SEndClimbTowerEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6712904;

	public int getType() {
		return 6712904;
	}

	public int errcode;
	public int oldmaxfloorid;
	public int newmaxfloorid;
	public map.msg.Bonus firstbonus;
	public map.msg.Bonus normalbonus;
	public int lastfloorcosttime;

	public SEndClimbTowerEctype() {
		firstbonus = new map.msg.Bonus();
		normalbonus = new map.msg.Bonus();
	}

	public SEndClimbTowerEctype(int _errcode_, int _oldmaxfloorid_, int _newmaxfloorid_, map.msg.Bonus _firstbonus_, map.msg.Bonus _normalbonus_, int _lastfloorcosttime_) {
		this.errcode = _errcode_;
		this.oldmaxfloorid = _oldmaxfloorid_;
		this.newmaxfloorid = _newmaxfloorid_;
		this.firstbonus = _firstbonus_;
		this.normalbonus = _normalbonus_;
		this.lastfloorcosttime = _lastfloorcosttime_;
	}

	public final boolean _validator_() {
		if (!firstbonus._validator_()) return false;
		if (!normalbonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(errcode);
		_os_.marshal(oldmaxfloorid);
		_os_.marshal(newmaxfloorid);
		_os_.marshal(firstbonus);
		_os_.marshal(normalbonus);
		_os_.marshal(lastfloorcosttime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		errcode = _os_.unmarshal_int();
		oldmaxfloorid = _os_.unmarshal_int();
		newmaxfloorid = _os_.unmarshal_int();
		firstbonus.unmarshal(_os_);
		normalbonus.unmarshal(_os_);
		lastfloorcosttime = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEndClimbTowerEctype) {
			SEndClimbTowerEctype _o_ = (SEndClimbTowerEctype)_o1_;
			if (errcode != _o_.errcode) return false;
			if (oldmaxfloorid != _o_.oldmaxfloorid) return false;
			if (newmaxfloorid != _o_.newmaxfloorid) return false;
			if (!firstbonus.equals(_o_.firstbonus)) return false;
			if (!normalbonus.equals(_o_.normalbonus)) return false;
			if (lastfloorcosttime != _o_.lastfloorcosttime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += errcode;
		_h_ += oldmaxfloorid;
		_h_ += newmaxfloorid;
		_h_ += firstbonus.hashCode();
		_h_ += normalbonus.hashCode();
		_h_ += lastfloorcosttime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(errcode).append(",");
		_sb_.append(oldmaxfloorid).append(",");
		_sb_.append(newmaxfloorid).append(",");
		_sb_.append(firstbonus).append(",");
		_sb_.append(normalbonus).append(",");
		_sb_.append(lastfloorcosttime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

