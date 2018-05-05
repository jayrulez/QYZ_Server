
package gnet;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __AGetRoleInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class AGetRoleInfo extends __AGetRoleInfo__ {
	@Override
	protected void process() {
		new xdb.Procedure() {
            @Override
            protected boolean process() {
            	Long roleid = xtable.Rolename2ids.select(rolename.trim());
            	GGetRoleInfo p = new GGetRoleInfo();
            	p.xid = xid;
            	p.err = 0;
            	if(roleid != null){
            		xbean.RoleInfo roleinfo = xtable.Roleinfos.get(roleid);
            		p.info.put(AGetRoleInfo.LEVEL, roleinfo.getLevel());
            		DeliverClient.getInstance().sendByGsToAuany(roleinfo.getUserid(), roleid, p);
            	}
                return true;
            }
        }.execute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 154;

	public int getType() {
		return 154;
	}

	public final static int LEVEL = 1;

	public long xid;
	public java.lang.String rolename;
	public java.util.LinkedList<Integer> info; // 查询信息

	public AGetRoleInfo() {
		rolename = "";
		info = new java.util.LinkedList<Integer>();
	}

	public AGetRoleInfo(long _xid_, java.lang.String _rolename_, java.util.LinkedList<Integer> _info_) {
		this.xid = _xid_;
		this.rolename = _rolename_;
		this.info = _info_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(xid);
		_os_.marshal(rolename, "UTF-16LE");
		_os_.compact_uint32(info.size());
		for (Integer _v_ : info) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		xid = _os_.unmarshal_long();
		rolename = _os_.unmarshal_String("UTF-16LE");
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			info.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AGetRoleInfo) {
			AGetRoleInfo _o_ = (AGetRoleInfo)_o1_;
			if (xid != _o_.xid) return false;
			if (!rolename.equals(_o_.rolename)) return false;
			if (!info.equals(_o_.info)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)xid;
		_h_ += rolename.hashCode();
		_h_ += info.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(xid).append(",");
		_sb_.append("T").append(rolename.length()).append(",");
		_sb_.append(info).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

