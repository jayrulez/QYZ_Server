
package lx.gs.arena.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class FightReport implements Marshal {
	public long fighttime;
	public int challengetype;
	public int succ;
	public int oldrank;
	public int newrank;
	public java.lang.String opponentname;

	public FightReport() {
		opponentname = "";
	}

	public FightReport(long _fighttime_, int _challengetype_, int _succ_, int _oldrank_, int _newrank_, java.lang.String _opponentname_) {
		this.fighttime = _fighttime_;
		this.challengetype = _challengetype_;
		this.succ = _succ_;
		this.oldrank = _oldrank_;
		this.newrank = _newrank_;
		this.opponentname = _opponentname_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(fighttime);
		_os_.marshal(challengetype);
		_os_.marshal(succ);
		_os_.marshal(oldrank);
		_os_.marshal(newrank);
		_os_.marshal(opponentname, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		fighttime = _os_.unmarshal_long();
		challengetype = _os_.unmarshal_int();
		succ = _os_.unmarshal_int();
		oldrank = _os_.unmarshal_int();
		newrank = _os_.unmarshal_int();
		opponentname = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FightReport) {
			FightReport _o_ = (FightReport)_o1_;
			if (fighttime != _o_.fighttime) return false;
			if (challengetype != _o_.challengetype) return false;
			if (succ != _o_.succ) return false;
			if (oldrank != _o_.oldrank) return false;
			if (newrank != _o_.newrank) return false;
			if (!opponentname.equals(_o_.opponentname)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)fighttime;
		_h_ += challengetype;
		_h_ += succ;
		_h_ += oldrank;
		_h_ += newrank;
		_h_ += opponentname.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(fighttime).append(",");
		_sb_.append(challengetype).append(",");
		_sb_.append(succ).append(",");
		_sb_.append(oldrank).append(",");
		_sb_.append(newrank).append(",");
		_sb_.append("T").append(opponentname.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

