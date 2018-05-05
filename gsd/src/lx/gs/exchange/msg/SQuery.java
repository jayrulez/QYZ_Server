
package lx.gs.exchange.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SQuery__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SQuery extends __SQuery__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557611;

	public int getType() {
		return 6557611;
	}

	public java.lang.String name;
	public int category; // 目录
	public int orderby; // cfg.exchange.OrderByType
	public int sortorder;
	public int startindex;
	public lx.gs.exchange.msg.QueryResult queryresult;

	public SQuery() {
		name = "";
		queryresult = new lx.gs.exchange.msg.QueryResult();
	}

	public SQuery(java.lang.String _name_, int _category_, int _orderby_, int _sortorder_, int _startindex_, lx.gs.exchange.msg.QueryResult _queryresult_) {
		this.name = _name_;
		this.category = _category_;
		this.orderby = _orderby_;
		this.sortorder = _sortorder_;
		this.startindex = _startindex_;
		this.queryresult = _queryresult_;
	}

	public final boolean _validator_() {
		if (!queryresult._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(category);
		_os_.marshal(orderby);
		_os_.marshal(sortorder);
		_os_.marshal(startindex);
		_os_.marshal(queryresult);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		name = _os_.unmarshal_String("UTF-16LE");
		category = _os_.unmarshal_int();
		orderby = _os_.unmarshal_int();
		sortorder = _os_.unmarshal_int();
		startindex = _os_.unmarshal_int();
		queryresult.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SQuery) {
			SQuery _o_ = (SQuery)_o1_;
			if (!name.equals(_o_.name)) return false;
			if (category != _o_.category) return false;
			if (orderby != _o_.orderby) return false;
			if (sortorder != _o_.sortorder) return false;
			if (startindex != _o_.startindex) return false;
			if (!queryresult.equals(_o_.queryresult)) return false;
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
		_h_ += queryresult.hashCode();
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
		_sb_.append(queryresult).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

