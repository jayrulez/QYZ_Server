
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCurveFlyBegin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCurveFlyBegin extends __CCurveFlyBegin__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6706212;

	public int getType() {
		return 6706212;
	}

	public int portalid;
	public map.msg.Vector3 curposition;
	public map.msg.Vector3 dstposition;
	public int curveid;

	public CCurveFlyBegin() {
		curposition = new map.msg.Vector3();
		dstposition = new map.msg.Vector3();
	}

	public CCurveFlyBegin(int _portalid_, map.msg.Vector3 _curposition_, map.msg.Vector3 _dstposition_, int _curveid_) {
		this.portalid = _portalid_;
		this.curposition = _curposition_;
		this.dstposition = _dstposition_;
		this.curveid = _curveid_;
	}

	public final boolean _validator_() {
		if (!curposition._validator_()) return false;
		if (!dstposition._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(portalid);
		_os_.marshal(curposition);
		_os_.marshal(dstposition);
		_os_.marshal(curveid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		portalid = _os_.unmarshal_int();
		curposition.unmarshal(_os_);
		dstposition.unmarshal(_os_);
		curveid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CCurveFlyBegin) {
			CCurveFlyBegin _o_ = (CCurveFlyBegin)_o1_;
			if (portalid != _o_.portalid) return false;
			if (!curposition.equals(_o_.curposition)) return false;
			if (!dstposition.equals(_o_.dstposition)) return false;
			if (curveid != _o_.curveid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += portalid;
		_h_ += curposition.hashCode();
		_h_ += dstposition.hashCode();
		_h_ += curveid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(portalid).append(",");
		_sb_.append(curposition).append(",");
		_sb_.append(dstposition).append(",");
		_sb_.append(curveid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

