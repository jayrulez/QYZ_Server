
package lx.gs.exchange.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import gnet.link.Onlines;
import lx.gs.exchange.FExchange;

import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CQuery__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CQuery extends __CQuery__ {
	@Override
	protected void process() {
		final SQuery re = FExchange.query(this);
		Onlines.getInstance().sendResponse(this, re);
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557610;

	public int getType() {
		return 6557610;
	}

	public java.lang.String name;
	public int category; // 目录
	public int orderby; // cfg.exchange.OrderByType
	public int sortorder; // cfg.exchange.SortOrder
	public int startindex;
	public int endindex;

	public CQuery() {
		name = "";
	}

	public CQuery(java.lang.String _name_, int _category_, int _orderby_, int _sortorder_, int _startindex_, int _endindex_) {
		this.name = _name_;
		this.category = _category_;
		this.orderby = _orderby_;
		this.sortorder = _sortorder_;
		this.startindex = _startindex_;
		this.endindex = _endindex_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(category);
		_os_.marshal(orderby);
		_os_.marshal(sortorder);
		_os_.marshal(startindex);
		_os_.marshal(endindex);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		name = _os_.unmarshal_String("UTF-16LE");
		category = _os_.unmarshal_int();
		orderby = _os_.unmarshal_int();
		sortorder = _os_.unmarshal_int();
		startindex = _os_.unmarshal_int();
		endindex = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CQuery) {
			CQuery _o_ = (CQuery)_o1_;
			if (!name.equals(_o_.name)) return false;
			if (category != _o_.category) return false;
			if (orderby != _o_.orderby) return false;
			if (sortorder != _o_.sortorder) return false;
			if (startindex != _o_.startindex) return false;
			if (endindex != _o_.endindex) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += name.hashCode();
		_h_ += category;
		_h_ += orderby;
		_h_ += sortorder;
		_h_ += startindex;
		_h_ += endindex;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(category).append(",");
		_sb_.append(orderby).append(",");
		_sb_.append(sortorder).append(",");
		_sb_.append(startindex).append(",");
		_sb_.append(endindex).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

