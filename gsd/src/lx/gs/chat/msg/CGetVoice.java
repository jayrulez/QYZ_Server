
package lx.gs.chat.msg;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.chat.FChat;
import common.ErrorCode;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetVoice__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetVoice extends __CGetVoice__ {
	@Override
	protected void process() {
		new AProcedure<CGetVoice>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				final SGetVoice re = new SGetVoice();
				re.voiceid = voiceid;
				final ErrorCode ret = FChat.getVoice(voiceid, re);
				if(ret.err())
					return error(ret);
				response(re);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554009;

	public int getType() {
		return 6554009;
	}

	public int voiceid;

	public CGetVoice() {
	}

	public CGetVoice(int _voiceid_) {
		this.voiceid = _voiceid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(voiceid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		voiceid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetVoice) {
			CGetVoice _o_ = (CGetVoice)_o1_;
			if (voiceid != _o_.voiceid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += voiceid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(voiceid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetVoice _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = voiceid - _o_.voiceid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

