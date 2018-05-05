
package lx.gs.dailyactivity.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetUndoActive__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetUndoActive extends __SGetUndoActive__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556436;

	public int getType() {
		return 6556436;
	}

	public lx.gs.dailyactivity.msg.Undoactiveinfos undoactive;

	public SGetUndoActive() {
		undoactive = new lx.gs.dailyactivity.msg.Undoactiveinfos();
	}

	public SGetUndoActive(lx.gs.dailyactivity.msg.Undoactiveinfos _undoactive_) {
		this.undoactive = _undoactive_;
	}

	public final boolean _validator_() {
		if (!undoactive._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(undoactive);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		undoactive.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetUndoActive) {
			SGetUndoActive _o_ = (SGetUndoActive)_o1_;
			if (!undoactive.equals(_o_.undoactive)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += undoactive.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(undoactive).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

