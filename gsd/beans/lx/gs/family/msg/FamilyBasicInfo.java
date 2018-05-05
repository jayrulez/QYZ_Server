
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 家族信息
*/
public class FamilyBasicInfo implements Marshal {
	public long familyid; // 家族id
	public java.lang.String familyname; // 家族名字
	public int flevel; // 家族等级
	public long money; // 家族资金
	public int curlvlbuilddegree; // 当前等级家族建设度
	public int totalbuilddegree; // 总的家族建设度
	public long totaobanggong; // 家族帮贡
	public java.lang.String declaration; // 家族宣言
	public java.lang.String publicinfo; // 家族公告
	public long publictime; // 公告时间
	public int malllevel; // 家族商店级别
	public int membernum; // 家族成员数
	public long chiefid; // 族长角色id
	public java.lang.String chiefname; // 族长名字
	public int chieflvl; // 族长等级
	public int rank; // 家族排名，只有在榜内才会有值
	public long godanimalstarttime; // 家族神兽挑战开始时间
	public long godanimalendtime; // 家族神兽挑战结束的时间
	public int familyrequestinfo; // 是否有未处理的家族申请信息(家族小红点)，0：没有；1：有
	public long familypartylastopentime; // 上次开启家族聚宴的时间
	public long familypartylastcalltime; // 上次召集教众时间

	public FamilyBasicInfo() {
		familyname = "";
		declaration = "";
		publicinfo = "";
		malllevel = 1;
		chiefname = "";
	}

	public FamilyBasicInfo(long _familyid_, java.lang.String _familyname_, int _flevel_, long _money_, int _curlvlbuilddegree_, int _totalbuilddegree_, long _totaobanggong_, java.lang.String _declaration_, java.lang.String _publicinfo_, long _publictime_, int _malllevel_, int _membernum_, long _chiefid_, java.lang.String _chiefname_, int _chieflvl_, int _rank_, long _godanimalstarttime_, long _godanimalendtime_, int _familyrequestinfo_, long _familypartylastopentime_, long _familypartylastcalltime_) {
		this.familyid = _familyid_;
		this.familyname = _familyname_;
		this.flevel = _flevel_;
		this.money = _money_;
		this.curlvlbuilddegree = _curlvlbuilddegree_;
		this.totalbuilddegree = _totalbuilddegree_;
		this.totaobanggong = _totaobanggong_;
		this.declaration = _declaration_;
		this.publicinfo = _publicinfo_;
		this.publictime = _publictime_;
		this.malllevel = _malllevel_;
		this.membernum = _membernum_;
		this.chiefid = _chiefid_;
		this.chiefname = _chiefname_;
		this.chieflvl = _chieflvl_;
		this.rank = _rank_;
		this.godanimalstarttime = _godanimalstarttime_;
		this.godanimalendtime = _godanimalendtime_;
		this.familyrequestinfo = _familyrequestinfo_;
		this.familypartylastopentime = _familypartylastopentime_;
		this.familypartylastcalltime = _familypartylastcalltime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familyid);
		_os_.marshal(familyname, "UTF-16LE");
		_os_.marshal(flevel);
		_os_.marshal(money);
		_os_.marshal(curlvlbuilddegree);
		_os_.marshal(totalbuilddegree);
		_os_.marshal(totaobanggong);
		_os_.marshal(declaration, "UTF-16LE");
		_os_.marshal(publicinfo, "UTF-16LE");
		_os_.marshal(publictime);
		_os_.marshal(malllevel);
		_os_.marshal(membernum);
		_os_.marshal(chiefid);
		_os_.marshal(chiefname, "UTF-16LE");
		_os_.marshal(chieflvl);
		_os_.marshal(rank);
		_os_.marshal(godanimalstarttime);
		_os_.marshal(godanimalendtime);
		_os_.marshal(familyrequestinfo);
		_os_.marshal(familypartylastopentime);
		_os_.marshal(familypartylastcalltime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familyid = _os_.unmarshal_long();
		familyname = _os_.unmarshal_String("UTF-16LE");
		flevel = _os_.unmarshal_int();
		money = _os_.unmarshal_long();
		curlvlbuilddegree = _os_.unmarshal_int();
		totalbuilddegree = _os_.unmarshal_int();
		totaobanggong = _os_.unmarshal_long();
		declaration = _os_.unmarshal_String("UTF-16LE");
		publicinfo = _os_.unmarshal_String("UTF-16LE");
		publictime = _os_.unmarshal_long();
		malllevel = _os_.unmarshal_int();
		membernum = _os_.unmarshal_int();
		chiefid = _os_.unmarshal_long();
		chiefname = _os_.unmarshal_String("UTF-16LE");
		chieflvl = _os_.unmarshal_int();
		rank = _os_.unmarshal_int();
		godanimalstarttime = _os_.unmarshal_long();
		godanimalendtime = _os_.unmarshal_long();
		familyrequestinfo = _os_.unmarshal_int();
		familypartylastopentime = _os_.unmarshal_long();
		familypartylastcalltime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FamilyBasicInfo) {
			FamilyBasicInfo _o_ = (FamilyBasicInfo)_o1_;
			if (familyid != _o_.familyid) return false;
			if (!familyname.equals(_o_.familyname)) return false;
			if (flevel != _o_.flevel) return false;
			if (money != _o_.money) return false;
			if (curlvlbuilddegree != _o_.curlvlbuilddegree) return false;
			if (totalbuilddegree != _o_.totalbuilddegree) return false;
			if (totaobanggong != _o_.totaobanggong) return false;
			if (!declaration.equals(_o_.declaration)) return false;
			if (!publicinfo.equals(_o_.publicinfo)) return false;
			if (publictime != _o_.publictime) return false;
			if (malllevel != _o_.malllevel) return false;
			if (membernum != _o_.membernum) return false;
			if (chiefid != _o_.chiefid) return false;
			if (!chiefname.equals(_o_.chiefname)) return false;
			if (chieflvl != _o_.chieflvl) return false;
			if (rank != _o_.rank) return false;
			if (godanimalstarttime != _o_.godanimalstarttime) return false;
			if (godanimalendtime != _o_.godanimalendtime) return false;
			if (familyrequestinfo != _o_.familyrequestinfo) return false;
			if (familypartylastopentime != _o_.familypartylastopentime) return false;
			if (familypartylastcalltime != _o_.familypartylastcalltime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)familyid;
		_h_ += familyname.hashCode();
		_h_ += flevel;
		_h_ += (int)money;
		_h_ += curlvlbuilddegree;
		_h_ += totalbuilddegree;
		_h_ += (int)totaobanggong;
		_h_ += declaration.hashCode();
		_h_ += publicinfo.hashCode();
		_h_ += (int)publictime;
		_h_ += malllevel;
		_h_ += membernum;
		_h_ += (int)chiefid;
		_h_ += chiefname.hashCode();
		_h_ += chieflvl;
		_h_ += rank;
		_h_ += (int)godanimalstarttime;
		_h_ += (int)godanimalendtime;
		_h_ += familyrequestinfo;
		_h_ += (int)familypartylastopentime;
		_h_ += (int)familypartylastcalltime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(familyid).append(",");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(flevel).append(",");
		_sb_.append(money).append(",");
		_sb_.append(curlvlbuilddegree).append(",");
		_sb_.append(totalbuilddegree).append(",");
		_sb_.append(totaobanggong).append(",");
		_sb_.append("T").append(declaration.length()).append(",");
		_sb_.append("T").append(publicinfo.length()).append(",");
		_sb_.append(publictime).append(",");
		_sb_.append(malllevel).append(",");
		_sb_.append(membernum).append(",");
		_sb_.append(chiefid).append(",");
		_sb_.append("T").append(chiefname.length()).append(",");
		_sb_.append(chieflvl).append(",");
		_sb_.append(rank).append(",");
		_sb_.append(godanimalstarttime).append(",");
		_sb_.append(godanimalendtime).append(",");
		_sb_.append(familyrequestinfo).append(",");
		_sb_.append(familypartylastopentime).append(",");
		_sb_.append(familypartylastcalltime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

