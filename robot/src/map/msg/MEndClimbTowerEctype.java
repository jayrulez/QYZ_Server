
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MEndClimbTowerEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MEndClimbTowerEctype extends __MEndClimbTowerEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6705210;

	public int getType() {
		return 6705210;
	}

	public int retcode;
	public int ectypeid;
	public int newfloorid;
	public int lastfloorcosttime;
	public map.msg.Bonus firstbonus;
	public map.msg.Bonus normalbonus;

	public MEndClimbTowerEctype() {
		firstbonus = new map.msg.Bonus();
		normalbonus = new map.msg.Bonus();
	}

	public MEndClimbTowerEctype(int _retcode_, int _ectypeid_, int _newfloorid_, int _lastfloorcosttime_, map.msg.Bonus _firstbonus_, map.msg.Bonus _normalbonus_) {
		this.retcode = _retcode_;
		this.ectypeid = _ectypeid_;
		this.newfloorid = _newfloorid_;
		this.lastfloorcosttime = _lastfloorcosttime_;
		this.firstbonus = _firstbonus_;
		this.normalbonus = _normalbonus_;
	}

	public final boolean _validator_() {
		if (!firstbonus._validator_()) return false;
		if (!normalbonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(retcode);
		_os_.marshal(ectypeid);
		_os_.marshal(newfloorid);
		_os_.marshal(lastfloorcosttime);
		_os_.marshal(firstbonus);
		_os_.marshal(normalbonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		retcode = _os_.unmarshal_int();
		ectypeid = _os_.unmarshal_int();
		newfloorid = _os_.unmarshal_int();
		lastfloorcosttime = _os_.unmarshal_int();
		firstbonus.unmarshal(_os_);
		normalbonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MEndClimbTowerEctype) {
			MEndClimbTowerEctype _o_ = (MEndClimbTowerEctype)_o1_;
			if (retcode != _o_.retcode) return false;
			if (ectypeid != _o_.ectypeid) return false;
			if (newfloorid != _o_.newfloorid) return false;
			if (lastfloorcosttime != _o_.lastfloorcosttime) return false;
			if (!firstbonus.equals(_o_.firstbonus)) return false;
			if (!normalbonus.equals(_o_.normalbonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += retcode;
		_h_ += ectypeid;
		_h_ += newfloorid;
		_h_ += lastfloorcosttime;
		_h_ += firstbonus.hashCode();
		_h_ += normalbonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(retcode).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(newfloorid).append(",");
		_sb_.append(lastfloorcosttime).append(",");
		_sb_.append(firstbonus).append(",");
		_sb_.append(normalbonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

