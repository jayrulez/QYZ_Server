
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SQuitTeamNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SQuitTeamNotify extends __SQuitTeamNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557838;

	public int getType() {
		return 6557838;
	}

	public long leaderid;
	public long quitroleid;
	public java.lang.String quitrolename;

	public SQuitTeamNotify() {
		quitrolename = "";
	}

	public SQuitTeamNotify(long _leaderid_, long _quitroleid_, java.lang.String _quitrolename_) {
		this.leaderid = _leaderid_;
		this.quitroleid = _quitroleid_;
		this.quitrolename = _quitrolename_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(leaderid);
		_os_.marshal(quitroleid);
		_os_.marshal(quitrolename, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		leaderid = _os_.unmarshal_long();
		quitroleid = _os_.unmarshal_long();
		quitrolename = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SQuitTeamNotify) {
			SQuitTeamNotify _o_ = (SQuitTeamNotify)_o1_;
			if (leaderid != _o_.leaderid) return false;
			if (quitroleid != _o_.quitroleid) return false;
            return quitrolename.equals(_o_.quitrolename);
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)leaderid;
		_h_ += (int)quitroleid;
		_h_ += quitrolename.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(leaderid).append(",");
		_sb_.append(quitroleid).append(",");
		_sb_.append("T").append(quitrolename.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

