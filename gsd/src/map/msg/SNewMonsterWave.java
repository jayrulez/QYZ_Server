
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SNewMonsterWave__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SNewMonsterWave extends __SNewMonsterWave__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6699185;

	public int getType() {
		return 6699185;
	}

	public int waveindex;

	public SNewMonsterWave() {
	}

	public SNewMonsterWave(int _waveindex_) {
		this.waveindex = _waveindex_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(waveindex);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		waveindex = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SNewMonsterWave) {
			SNewMonsterWave _o_ = (SNewMonsterWave)_o1_;
			if (waveindex != _o_.waveindex) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += waveindex;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(waveindex).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SNewMonsterWave _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = waveindex - _o_.waveindex;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

