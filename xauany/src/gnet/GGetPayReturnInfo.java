
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __GGetPayReturnInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class GGetPayReturnInfo extends __GGetPayReturnInfo__ {
	@Override
	protected void process() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final GsToAuany ctx = (GsToAuany)getContext();
                final AGetPayReturnInfo re = new AGetPayReturnInfo();
                // re.roleid 为领取的奖励的roleid
                xbean.UserPayInfo info = xtable.Userpays.get(ctx.userid);
                if(info != null) {
                    re.hasgotpayreturn = (byte)(info.getHasgotreturn() ? 1 : 0);
                    re.getreturnroleid = info.getRoleid();
                    re.totalpay = info.getTotalpay();
                    re.totalyuanbao = info.getTotalyunbao();
                    re.totalbindyuanbao = info.getTotalbindyuanbao();
                    re.totalvipexp = info.getTotalvipexp();
                }
                ctx.reply(re);
                return true;
            }
        }.execute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 152;

	public int getType() {
		return 152;
	}


	public GGetPayReturnInfo() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GGetPayReturnInfo) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(GGetPayReturnInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

