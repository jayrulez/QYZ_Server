
package lx.gs.role.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gnet.ActivationCodeErr;
import gs.AProcedure;
import xdb.Trace;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUseCode__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUseCode extends __CUseCode__ {
	@Override
	protected void process() {

        new AProcedure<CUseCode>(this)   {
            @Override
            protected boolean doProcess() throws Exception {
                Trace.info("CUseCode. userid:{} roleid:{} code:{}", userid, roleid, code);
                gnet.UseActivationCode request = new gnet.UseActivationCode(userid, roleid, code);
                if (!request.send(gnet.DeliverClient.getInstance())) {
                    tsend(new SUseCode(ActivationCodeErr.ERR_NETWORK, code, new map.msg.Bonus()));
                    return false;
                }
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580486;

	public int getType() {
		return 6580486;
	}

	public java.lang.String code;

	public CUseCode() {
		code = "";
	}

	public CUseCode(java.lang.String _code_) {
		this.code = _code_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(code, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		code = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUseCode) {
			CUseCode _o_ = (CUseCode)_o1_;
			if (!code.equals(_o_.code)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += code.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(code.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

