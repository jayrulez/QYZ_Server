
package lx.gs;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __STips__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class STips extends __STips__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577752;

	public int getType() {
		return 6577752;
	}

	public int location; // 弹出位置 see cfg.tips.LocationType
	public int contentid; // tips索引,优先用这个找tips，如果id小于等于0或者策划数据里没有，就用下面的tips
	public java.lang.String content; // 内容,碰到括弧"{}"需要按顺序去参数列表里取参数
	public java.util.ArrayList<java.lang.String> param; // 参数

	public STips() {
		content = "";
		param = new java.util.ArrayList<java.lang.String>();
	}

	public STips(int _location_, int _contentid_, java.lang.String _content_, java.util.ArrayList<java.lang.String> _param_) {
		this.location = _location_;
		this.contentid = _contentid_;
		this.content = _content_;
		this.param = _param_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(location);
		_os_.marshal(contentid);
		_os_.marshal(content, "UTF-16LE");
		_os_.compact_uint32(param.size());
		for (java.lang.String _v_ : param) {
			_os_.marshal(_v_, "UTF-16LE");
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		location = _os_.unmarshal_int();
		contentid = _os_.unmarshal_int();
		content = _os_.unmarshal_String("UTF-16LE");
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			java.lang.String _v_;
			_v_ = _os_.unmarshal_String("UTF-16LE");
			param.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof STips) {
			STips _o_ = (STips)_o1_;
			if (location != _o_.location) return false;
			if (contentid != _o_.contentid) return false;
			if (!content.equals(_o_.content)) return false;
			if (!param.equals(_o_.param)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += location;
		_h_ += contentid;
		_h_ += content.hashCode();
		_h_ += param.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(location).append(",");
		_sb_.append(contentid).append(",");
		_sb_.append("T").append(content.length()).append(",");
		_sb_.append(param).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

