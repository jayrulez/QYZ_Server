
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SKillEvent__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SKillEvent extends __SKillEvent__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6712179;

	public int getType() {
		return 6712179;
	}

	public java.lang.String attackername;
	public int attackercamp;
	public java.lang.String defencer;
	public int defencercamp;
	public int petkey;

	public SKillEvent() {
		attackername = "";
		defencer = "";
	}

	public SKillEvent(java.lang.String _attackername_, int _attackercamp_, java.lang.String _defencer_, int _defencercamp_, int _petkey_) {
		this.attackername = _attackername_;
		this.attackercamp = _attackercamp_;
		this.defencer = _defencer_;
		this.defencercamp = _defencercamp_;
		this.petkey = _petkey_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(attackername, "UTF-16LE");
		_os_.marshal(attackercamp);
		_os_.marshal(defencer, "UTF-16LE");
		_os_.marshal(defencercamp);
		_os_.marshal(petkey);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		attackername = _os_.unmarshal_String("UTF-16LE");
		attackercamp = _os_.unmarshal_int();
		defencer = _os_.unmarshal_String("UTF-16LE");
		defencercamp = _os_.unmarshal_int();
		petkey = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SKillEvent) {
			SKillEvent _o_ = (SKillEvent)_o1_;
			if (!attackername.equals(_o_.attackername)) return false;
			if (attackercamp != _o_.attackercamp) return false;
			if (!defencer.equals(_o_.defencer)) return false;
			if (defencercamp != _o_.defencercamp) return false;
			if (petkey != _o_.petkey) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += attackername.hashCode();
		_h_ += attackercamp;
		_h_ += defencer.hashCode();
		_h_ += defencercamp;
		_h_ += petkey;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(attackername.length()).append(",");
		_sb_.append(attackercamp).append(",");
		_sb_.append("T").append(defencer.length()).append(",");
		_sb_.append(defencercamp).append(",");
		_sb_.append(petkey).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

