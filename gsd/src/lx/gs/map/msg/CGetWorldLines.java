
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gnet.link.Onlines;
import lx.gs.map.MapModule;

import java.util.List;
import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetWorldLines__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetWorldLines extends __CGetWorldLines__ {
	@Override
	protected void process() {
		final SGetWorldLines re = new SGetWorldLines();
		re.worldid = worldid;
        MapModule.worldsByLines.get(worldid).values().forEach(info -> re.lines.add(new LineInfo(info.lineid, info.playerNum)));
		Onlines.getInstance().sendResponse(this, re);
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573891;

	public int getType() {
		return 6573891;
	}

	public int worldid;

	public CGetWorldLines() {
	}

	public CGetWorldLines(int _worldid_) {
		this.worldid = _worldid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(worldid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		worldid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetWorldLines) {
			CGetWorldLines _o_ = (CGetWorldLines)_o1_;
			if (worldid != _o_.worldid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += worldid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(worldid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetWorldLines _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = worldid - _o_.worldid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

