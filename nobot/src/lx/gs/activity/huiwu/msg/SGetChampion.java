
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetChampion__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetChampion extends __SGetChampion__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586099;

	public int getType() {
		return 6586099;
	}

	public int termid;
	public int profession;
	public int ctx;
	public lx.gs.activity.huiwu.msg.ChampionInfo championinfo;

	public SGetChampion() {
		championinfo = new lx.gs.activity.huiwu.msg.ChampionInfo();
	}

	public SGetChampion(int _termid_, int _profession_, int _ctx_, lx.gs.activity.huiwu.msg.ChampionInfo _championinfo_) {
		this.termid = _termid_;
		this.profession = _profession_;
		this.ctx = _ctx_;
		this.championinfo = _championinfo_;
	}

	public final boolean _validator_() {
		if (!championinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(termid);
		_os_.marshal(profession);
		_os_.marshal(ctx);
		_os_.marshal(championinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		termid = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		ctx = _os_.unmarshal_int();
		championinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetChampion) {
			SGetChampion _o_ = (SGetChampion)_o1_;
			if (termid != _o_.termid) return false;
			if (profession != _o_.profession) return false;
			if (ctx != _o_.ctx) return false;
			if (!championinfo.equals(_o_.championinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += termid;
		_h_ += profession;
		_h_ += ctx;
		_h_ += championinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(termid).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(ctx).append(",");
		_sb_.append(championinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

