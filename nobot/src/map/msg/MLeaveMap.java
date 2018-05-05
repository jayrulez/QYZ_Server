
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MLeaveMap__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MLeaveMap extends __MLeaveMap__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6699468;

	public int getType() {
		return 6699468;
	}

	public int ctxid;
	public int retcode;
	public long roleid;
	public byte reserve;
	public int reason;
	public map.msg.RoleMapContext cur;
	public java.util.ArrayList<map.msg.RoleMapContext> news;
	public int rolenum;

	public MLeaveMap() {
		cur = new map.msg.RoleMapContext();
		news = new java.util.ArrayList<map.msg.RoleMapContext>();
	}

	public MLeaveMap(int _ctxid_, int _retcode_, long _roleid_, byte _reserve_, int _reason_, map.msg.RoleMapContext _cur_, java.util.ArrayList<map.msg.RoleMapContext> _news_, int _rolenum_) {
		this.ctxid = _ctxid_;
		this.retcode = _retcode_;
		this.roleid = _roleid_;
		this.reserve = _reserve_;
		this.reason = _reason_;
		this.cur = _cur_;
		this.news = _news_;
		this.rolenum = _rolenum_;
	}

	public final boolean _validator_() {
		if (!cur._validator_()) return false;
		for (map.msg.RoleMapContext _v_ : news)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ctxid);
		_os_.marshal(retcode);
		_os_.marshal(roleid);
		_os_.marshal(reserve);
		_os_.marshal(reason);
		_os_.marshal(cur);
		_os_.compact_uint32(news.size());
		for (map.msg.RoleMapContext _v_ : news) {
			_os_.marshal(_v_);
		}
		_os_.marshal(rolenum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ctxid = _os_.unmarshal_int();
		retcode = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		reserve = _os_.unmarshal_byte();
		reason = _os_.unmarshal_int();
		cur.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.RoleMapContext _v_ = new map.msg.RoleMapContext();
			_v_.unmarshal(_os_);
			news.add(_v_);
		}
		rolenum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MLeaveMap) {
			MLeaveMap _o_ = (MLeaveMap)_o1_;
			if (ctxid != _o_.ctxid) return false;
			if (retcode != _o_.retcode) return false;
			if (roleid != _o_.roleid) return false;
			if (reserve != _o_.reserve) return false;
			if (reason != _o_.reason) return false;
			if (!cur.equals(_o_.cur)) return false;
			if (!news.equals(_o_.news)) return false;
			if (rolenum != _o_.rolenum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ctxid;
		_h_ += retcode;
		_h_ += (int)roleid;
		_h_ += (int)reserve;
		_h_ += reason;
		_h_ += cur.hashCode();
		_h_ += news.hashCode();
		_h_ += rolenum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ctxid).append(",");
		_sb_.append(retcode).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(reserve).append(",");
		_sb_.append(reason).append(",");
		_sb_.append(cur).append(",");
		_sb_.append(news).append(",");
		_sb_.append(rolenum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

