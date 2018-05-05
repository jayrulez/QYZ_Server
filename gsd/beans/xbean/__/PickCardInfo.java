
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class PickCardInfo extends xdb.XBean implements xbean.PickCardInfo {
	private long lastfreehuoban; // 上次伙伴虚拟币免费单抽时间
	private int huobanhighyuanbao; // 伙伴高额元宝抽卡次数
	private int huobanlowyuanbao; // 伙伴低额元宝抽卡次数
	private int fabaoyuanbao; // 法宝元宝抽卡次数
	private int fabaofree; // 法宝元宝免费次数
	private int fabaoxunibi; // 法宝虚拟币抽卡次数，免费次数也统计在内

	@Override
	public void _reset_unsafe_() {
		lastfreehuoban = 0L;
		huobanhighyuanbao = 0;
		huobanlowyuanbao = 0;
		fabaoyuanbao = 0;
		fabaofree = 0;
		fabaoxunibi = 0;
	}

	PickCardInfo(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public PickCardInfo() {
		this(0, null, null);
	}

	public PickCardInfo(PickCardInfo _o_) {
		this(_o_, null, null);
	}

	PickCardInfo(xbean.PickCardInfo _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof PickCardInfo) assign((PickCardInfo)_o1_);
		else if (_o1_ instanceof PickCardInfo.Data) assign((PickCardInfo.Data)_o1_);
		else if (_o1_ instanceof PickCardInfo.Const) assign(((PickCardInfo.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(PickCardInfo _o_) {
		_o_._xdb_verify_unsafe_();
		lastfreehuoban = _o_.lastfreehuoban;
		huobanhighyuanbao = _o_.huobanhighyuanbao;
		huobanlowyuanbao = _o_.huobanlowyuanbao;
		fabaoyuanbao = _o_.fabaoyuanbao;
		fabaofree = _o_.fabaofree;
		fabaoxunibi = _o_.fabaoxunibi;
	}

	private void assign(PickCardInfo.Data _o_) {
		lastfreehuoban = _o_.lastfreehuoban;
		huobanhighyuanbao = _o_.huobanhighyuanbao;
		huobanlowyuanbao = _o_.huobanlowyuanbao;
		fabaoyuanbao = _o_.fabaoyuanbao;
		fabaofree = _o_.fabaofree;
		fabaoxunibi = _o_.fabaoxunibi;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)6);
    _os_.marshal((short)(10240|  1));_os_.marshal(lastfreehuoban);
    _os_.marshal((short)( 8192|  2));_os_.marshal(huobanhighyuanbao);
    _os_.marshal((short)( 8192|  4));_os_.marshal(huobanlowyuanbao);
    _os_.marshal((short)( 8192|  6));_os_.marshal(fabaoyuanbao);
    _os_.marshal((short)( 8192|  7));_os_.marshal(fabaofree);
    _os_.marshal((short)( 8192|  8));_os_.marshal(fabaoxunibi);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):lastfreehuoban = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):lastfreehuoban = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):lastfreehuoban = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):huobanhighyuanbao = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):huobanhighyuanbao = _os_.unmarshal_short();
    				break;
    				case ( 8192|  4):huobanlowyuanbao = _os_.unmarshal_int();
    				break;
    				case ( 6144|  4):huobanlowyuanbao = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):fabaoyuanbao = _os_.unmarshal_int();
    				break;
    				case ( 6144|  6):fabaoyuanbao = _os_.unmarshal_short();
    				break;
    				case ( 8192|  7):fabaofree = _os_.unmarshal_int();
    				break;
    				case ( 6144|  7):fabaofree = _os_.unmarshal_short();
    				break;
    				case ( 8192|  8):fabaoxunibi = _os_.unmarshal_int();
    				break;
    				case ( 6144|  8):fabaoxunibi = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.PickCardInfo copy() {
		_xdb_verify_unsafe_();
		return new PickCardInfo(this);
	}

	@Override
	public xbean.PickCardInfo toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.PickCardInfo toBean() {
		_xdb_verify_unsafe_();
		return new PickCardInfo(this); // same as copy()
	}

	@Override
	public xbean.PickCardInfo toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.PickCardInfo toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getLastfreehuoban() { // 上次伙伴虚拟币免费单抽时间
		_xdb_verify_unsafe_();
		return lastfreehuoban;
	}

	@Override
	public int getHuobanhighyuanbao() { // 伙伴高额元宝抽卡次数
		_xdb_verify_unsafe_();
		return huobanhighyuanbao;
	}

	@Override
	public int getHuobanlowyuanbao() { // 伙伴低额元宝抽卡次数
		_xdb_verify_unsafe_();
		return huobanlowyuanbao;
	}

	@Override
	public int getFabaoyuanbao() { // 法宝元宝抽卡次数
		_xdb_verify_unsafe_();
		return fabaoyuanbao;
	}

	@Override
	public int getFabaofree() { // 法宝元宝免费次数
		_xdb_verify_unsafe_();
		return fabaofree;
	}

	@Override
	public int getFabaoxunibi() { // 法宝虚拟币抽卡次数，免费次数也统计在内
		_xdb_verify_unsafe_();
		return fabaoxunibi;
	}

	@Override
	public void setLastfreehuoban(long _v_) { // 上次伙伴虚拟币免费单抽时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastfreehuoban") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastfreehuoban) {
					public void rollback() { lastfreehuoban = _xdb_saved; }
				};}});
		lastfreehuoban = _v_;
	}

	@Override
	public void setHuobanhighyuanbao(int _v_) { // 伙伴高额元宝抽卡次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "huobanhighyuanbao") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, huobanhighyuanbao) {
					public void rollback() { huobanhighyuanbao = _xdb_saved; }
				};}});
		huobanhighyuanbao = _v_;
	}

	@Override
	public void setHuobanlowyuanbao(int _v_) { // 伙伴低额元宝抽卡次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "huobanlowyuanbao") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, huobanlowyuanbao) {
					public void rollback() { huobanlowyuanbao = _xdb_saved; }
				};}});
		huobanlowyuanbao = _v_;
	}

	@Override
	public void setFabaoyuanbao(int _v_) { // 法宝元宝抽卡次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "fabaoyuanbao") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, fabaoyuanbao) {
					public void rollback() { fabaoyuanbao = _xdb_saved; }
				};}});
		fabaoyuanbao = _v_;
	}

	@Override
	public void setFabaofree(int _v_) { // 法宝元宝免费次数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "fabaofree") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, fabaofree) {
					public void rollback() { fabaofree = _xdb_saved; }
				};}});
		fabaofree = _v_;
	}

	@Override
	public void setFabaoxunibi(int _v_) { // 法宝虚拟币抽卡次数，免费次数也统计在内
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "fabaoxunibi") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, fabaoxunibi) {
					public void rollback() { fabaoxunibi = _xdb_saved; }
				};}});
		fabaoxunibi = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		PickCardInfo _o_ = null;
		if ( _o1_ instanceof PickCardInfo ) _o_ = (PickCardInfo)_o1_;
		else if ( _o1_ instanceof PickCardInfo.Const ) _o_ = ((PickCardInfo.Const)_o1_).nThis();
		else return false;
		if (lastfreehuoban != _o_.lastfreehuoban) return false;
		if (huobanhighyuanbao != _o_.huobanhighyuanbao) return false;
		if (huobanlowyuanbao != _o_.huobanlowyuanbao) return false;
		if (fabaoyuanbao != _o_.fabaoyuanbao) return false;
		if (fabaofree != _o_.fabaofree) return false;
		if (fabaoxunibi != _o_.fabaoxunibi) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += lastfreehuoban;
		_h_ += huobanhighyuanbao;
		_h_ += huobanlowyuanbao;
		_h_ += fabaoyuanbao;
		_h_ += fabaofree;
		_h_ += fabaoxunibi;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lastfreehuoban);
		_sb_.append(",");
		_sb_.append(huobanhighyuanbao);
		_sb_.append(",");
		_sb_.append(huobanlowyuanbao);
		_sb_.append(",");
		_sb_.append(fabaoyuanbao);
		_sb_.append(",");
		_sb_.append(fabaofree);
		_sb_.append(",");
		_sb_.append(fabaoxunibi);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastfreehuoban"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("huobanhighyuanbao"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("huobanlowyuanbao"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("fabaoyuanbao"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("fabaofree"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("fabaoxunibi"));
		return lb;
	}

	private class Const implements xbean.PickCardInfo {
		PickCardInfo nThis() {
			return PickCardInfo.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.PickCardInfo copy() {
			return PickCardInfo.this.copy();
		}

		@Override
		public xbean.PickCardInfo toData() {
			return PickCardInfo.this.toData();
		}

		public xbean.PickCardInfo toBean() {
			return PickCardInfo.this.toBean();
		}

		@Override
		public xbean.PickCardInfo toDataIf() {
			return PickCardInfo.this.toDataIf();
		}

		public xbean.PickCardInfo toBeanIf() {
			return PickCardInfo.this.toBeanIf();
		}

		@Override
		public long getLastfreehuoban() { // 上次伙伴虚拟币免费单抽时间
			_xdb_verify_unsafe_();
			return lastfreehuoban;
		}

		@Override
		public int getHuobanhighyuanbao() { // 伙伴高额元宝抽卡次数
			_xdb_verify_unsafe_();
			return huobanhighyuanbao;
		}

		@Override
		public int getHuobanlowyuanbao() { // 伙伴低额元宝抽卡次数
			_xdb_verify_unsafe_();
			return huobanlowyuanbao;
		}

		@Override
		public int getFabaoyuanbao() { // 法宝元宝抽卡次数
			_xdb_verify_unsafe_();
			return fabaoyuanbao;
		}

		@Override
		public int getFabaofree() { // 法宝元宝免费次数
			_xdb_verify_unsafe_();
			return fabaofree;
		}

		@Override
		public int getFabaoxunibi() { // 法宝虚拟币抽卡次数，免费次数也统计在内
			_xdb_verify_unsafe_();
			return fabaoxunibi;
		}

		@Override
		public void setLastfreehuoban(long _v_) { // 上次伙伴虚拟币免费单抽时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHuobanhighyuanbao(int _v_) { // 伙伴高额元宝抽卡次数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHuobanlowyuanbao(int _v_) { // 伙伴低额元宝抽卡次数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFabaoyuanbao(int _v_) { // 法宝元宝抽卡次数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFabaofree(int _v_) { // 法宝元宝免费次数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setFabaoxunibi(int _v_) { // 法宝虚拟币抽卡次数，免费次数也统计在内
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean toConst() {
			_xdb_verify_unsafe_();
			return this;
		}

		@Override
		public boolean isConst() {
			_xdb_verify_unsafe_();
			return true;
		}

		@Override
		public boolean isData() {
			return PickCardInfo.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return PickCardInfo.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return PickCardInfo.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return PickCardInfo.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return PickCardInfo.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return PickCardInfo.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return PickCardInfo.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return PickCardInfo.this.hashCode();
		}

		@Override
		public String toString() {
			return PickCardInfo.this.toString();
		}

	}

	public static final class Data implements xbean.PickCardInfo {
		private long lastfreehuoban; // 上次伙伴虚拟币免费单抽时间
		private int huobanhighyuanbao; // 伙伴高额元宝抽卡次数
		private int huobanlowyuanbao; // 伙伴低额元宝抽卡次数
		private int fabaoyuanbao; // 法宝元宝抽卡次数
		private int fabaofree; // 法宝元宝免费次数
		private int fabaoxunibi; // 法宝虚拟币抽卡次数，免费次数也统计在内

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.PickCardInfo _o1_) {
			if (_o1_ instanceof PickCardInfo) assign((PickCardInfo)_o1_);
			else if (_o1_ instanceof PickCardInfo.Data) assign((PickCardInfo.Data)_o1_);
			else if (_o1_ instanceof PickCardInfo.Const) assign(((PickCardInfo.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(PickCardInfo _o_) {
			lastfreehuoban = _o_.lastfreehuoban;
			huobanhighyuanbao = _o_.huobanhighyuanbao;
			huobanlowyuanbao = _o_.huobanlowyuanbao;
			fabaoyuanbao = _o_.fabaoyuanbao;
			fabaofree = _o_.fabaofree;
			fabaoxunibi = _o_.fabaoxunibi;
		}

		private void assign(PickCardInfo.Data _o_) {
			lastfreehuoban = _o_.lastfreehuoban;
			huobanhighyuanbao = _o_.huobanhighyuanbao;
			huobanlowyuanbao = _o_.huobanlowyuanbao;
			fabaoyuanbao = _o_.fabaoyuanbao;
			fabaofree = _o_.fabaofree;
			fabaoxunibi = _o_.fabaoxunibi;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)6);
	_os_.marshal((short)(10240|  1));_os_.marshal(lastfreehuoban);
	_os_.marshal((short)( 8192|  2));_os_.marshal(huobanhighyuanbao);
	_os_.marshal((short)( 8192|  4));_os_.marshal(huobanlowyuanbao);
	_os_.marshal((short)( 8192|  6));_os_.marshal(fabaoyuanbao);
	_os_.marshal((short)( 8192|  7));_os_.marshal(fabaofree);
	_os_.marshal((short)( 8192|  8));_os_.marshal(fabaoxunibi);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):lastfreehuoban = _os_.unmarshal_long();
					break;
					case ( 6144|  1):lastfreehuoban = _os_.unmarshal_short();
					break;
					case ( 8192|  1):lastfreehuoban = _os_.unmarshal_int();
					break;
					case ( 8192|  2):huobanhighyuanbao = _os_.unmarshal_int();
					break;
					case ( 6144|  2):huobanhighyuanbao = _os_.unmarshal_short();
					break;
					case ( 8192|  4):huobanlowyuanbao = _os_.unmarshal_int();
					break;
					case ( 6144|  4):huobanlowyuanbao = _os_.unmarshal_short();
					break;
					case ( 8192|  6):fabaoyuanbao = _os_.unmarshal_int();
					break;
					case ( 6144|  6):fabaoyuanbao = _os_.unmarshal_short();
					break;
					case ( 8192|  7):fabaofree = _os_.unmarshal_int();
					break;
					case ( 6144|  7):fabaofree = _os_.unmarshal_short();
					break;
					case ( 8192|  8):fabaoxunibi = _os_.unmarshal_int();
					break;
					case ( 6144|  8):fabaoxunibi = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.PickCardInfo copy() {
			return new Data(this);
		}

		@Override
		public xbean.PickCardInfo toData() {
			return new Data(this);
		}

		public xbean.PickCardInfo toBean() {
			return new PickCardInfo(this, null, null);
		}

		@Override
		public xbean.PickCardInfo toDataIf() {
			return this;
		}

		public xbean.PickCardInfo toBeanIf() {
			return new PickCardInfo(this, null, null);
		}

		// xdb.Bean interface. Data Unsupported
		public boolean xdbManaged() { throw new UnsupportedOperationException(); }
		public xdb.Bean xdbParent() { throw new UnsupportedOperationException(); }
		public String xdbVarname()  { throw new UnsupportedOperationException(); }
		public Long    xdbObjId()   { throw new UnsupportedOperationException(); }
		public xdb.Bean toConst()   { throw new UnsupportedOperationException(); }
		public boolean isConst()    { return false; }
		public boolean isData()     { return true; }

		@Override
		public long getLastfreehuoban() { // 上次伙伴虚拟币免费单抽时间
			return lastfreehuoban;
		}

		@Override
		public int getHuobanhighyuanbao() { // 伙伴高额元宝抽卡次数
			return huobanhighyuanbao;
		}

		@Override
		public int getHuobanlowyuanbao() { // 伙伴低额元宝抽卡次数
			return huobanlowyuanbao;
		}

		@Override
		public int getFabaoyuanbao() { // 法宝元宝抽卡次数
			return fabaoyuanbao;
		}

		@Override
		public int getFabaofree() { // 法宝元宝免费次数
			return fabaofree;
		}

		@Override
		public int getFabaoxunibi() { // 法宝虚拟币抽卡次数，免费次数也统计在内
			return fabaoxunibi;
		}

		@Override
		public void setLastfreehuoban(long _v_) { // 上次伙伴虚拟币免费单抽时间
			lastfreehuoban = _v_;
		}

		@Override
		public void setHuobanhighyuanbao(int _v_) { // 伙伴高额元宝抽卡次数
			huobanhighyuanbao = _v_;
		}

		@Override
		public void setHuobanlowyuanbao(int _v_) { // 伙伴低额元宝抽卡次数
			huobanlowyuanbao = _v_;
		}

		@Override
		public void setFabaoyuanbao(int _v_) { // 法宝元宝抽卡次数
			fabaoyuanbao = _v_;
		}

		@Override
		public void setFabaofree(int _v_) { // 法宝元宝免费次数
			fabaofree = _v_;
		}

		@Override
		public void setFabaoxunibi(int _v_) { // 法宝虚拟币抽卡次数，免费次数也统计在内
			fabaoxunibi = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof PickCardInfo.Data)) return false;
			PickCardInfo.Data _o_ = (PickCardInfo.Data) _o1_;
			if (lastfreehuoban != _o_.lastfreehuoban) return false;
			if (huobanhighyuanbao != _o_.huobanhighyuanbao) return false;
			if (huobanlowyuanbao != _o_.huobanlowyuanbao) return false;
			if (fabaoyuanbao != _o_.fabaoyuanbao) return false;
			if (fabaofree != _o_.fabaofree) return false;
			if (fabaoxunibi != _o_.fabaoxunibi) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += lastfreehuoban;
			_h_ += huobanhighyuanbao;
			_h_ += huobanlowyuanbao;
			_h_ += fabaoyuanbao;
			_h_ += fabaofree;
			_h_ += fabaoxunibi;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(lastfreehuoban);
			_sb_.append(",");
			_sb_.append(huobanhighyuanbao);
			_sb_.append(",");
			_sb_.append(huobanlowyuanbao);
			_sb_.append(",");
			_sb_.append(fabaoyuanbao);
			_sb_.append(",");
			_sb_.append(fabaofree);
			_sb_.append(",");
			_sb_.append(fabaoxunibi);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
