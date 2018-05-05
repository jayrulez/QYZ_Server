
package lx.gs.bonus.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBonusInfo__ extends xio.Protocol { }

/** 登录后发送的信息,小红点显示
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBonusInfo extends __SBonusInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583195;

	public int getType() {
		return 6583195;
	}

	public java.util.ArrayList<Integer> receivednewgift; // 已经领取过的新手礼包列表
	public int totallogindays; // 总的登录天数
	public int totaldays; // 当月总计天数
	public java.util.ArrayList<Integer> signedlist;
	public int hastodaysign;
	public int continuedays;
	public int lefttimes;
	public java.util.ArrayList<Integer> receivedconlogingift; // 已经领取过的连续登陆礼包列表
	public java.util.ArrayList<Integer> receivedtype;
	public long dailyonlinetime;
	public int monthcardplayer; // 0表示非月卡用户；1表示买过月卡
	public int monthcardleftday; // 月卡剩余天数
	public java.util.ArrayList<Integer> dates;
	public int buygrowplantype; // 1,2,3，如果为0，表示没有买过
	public int buygrowplantypetwo; // 是否购买过成长计划2，如果为0，表示没有买过
	public int buygrowplantypethr; // 是否购买过成长计划3，如果为0，表示没有买过
	public int growplanonetime; // 第几天登录购买的成长计划1
	public int growplantwotime; // 第几天登录购买的成长计划2
	public int growplanthreetime; // 第几天登录购买的成长计划3
	public java.util.ArrayList<Integer> threegifts; // 已经领取过的成长计划礼包id
	public java.util.ArrayList<Integer> fivegifts; // 已经领取过的成长计划礼包id
	public java.util.ArrayList<Integer> sevengifts; // 已经领取过的成长计划礼包id
	public byte isfirstpayused; // 是否领取过首冲奖励
	public long dailytotalcharge; // 每日累计充值
	public long totalcharge; // 累计充值,单位是人民币分
	public long totalcost; // 总的花费
	public java.util.ArrayList<Integer> hasbuyproduct; // 已经购买过的产品
	public java.util.ArrayList<Integer> receivedchargegifts; // 已领取的奖励类型
	public int isactivepay; // 是否购买过每日超值购,0,没有；1,已购买
	public int wishtimes; // 已许愿的次数,为1时表示使用了免费许愿，此时需要消耗宝碟
	public long lastwishtime; // 上次许愿时间，如果为0表示当前没有许愿
	public long lastwishpetid; // 上次许愿的伙伴id
	public int iseatlunch; // 是否吃了中午的包子；0，否，1是
	public int iseatdinner;

	public SBonusInfo() {
		receivednewgift = new java.util.ArrayList<Integer>();
		signedlist = new java.util.ArrayList<Integer>();
		receivedconlogingift = new java.util.ArrayList<Integer>();
		receivedtype = new java.util.ArrayList<Integer>();
		dates = new java.util.ArrayList<Integer>();
		threegifts = new java.util.ArrayList<Integer>();
		fivegifts = new java.util.ArrayList<Integer>();
		sevengifts = new java.util.ArrayList<Integer>();
		hasbuyproduct = new java.util.ArrayList<Integer>();
		receivedchargegifts = new java.util.ArrayList<Integer>();
	}

	public SBonusInfo(java.util.ArrayList<Integer> _receivednewgift_, int _totallogindays_, int _totaldays_, java.util.ArrayList<Integer> _signedlist_, int _hastodaysign_, int _continuedays_, int _lefttimes_, java.util.ArrayList<Integer> _receivedconlogingift_, java.util.ArrayList<Integer> _receivedtype_, long _dailyonlinetime_, int _monthcardplayer_, int _monthcardleftday_, java.util.ArrayList<Integer> _dates_, int _buygrowplantype_, int _buygrowplantypetwo_, int _buygrowplantypethr_, int _growplanonetime_, int _growplantwotime_, int _growplanthreetime_, java.util.ArrayList<Integer> _threegifts_, java.util.ArrayList<Integer> _fivegifts_, java.util.ArrayList<Integer> _sevengifts_, byte _isfirstpayused_, long _dailytotalcharge_, long _totalcharge_, long _totalcost_, java.util.ArrayList<Integer> _hasbuyproduct_, java.util.ArrayList<Integer> _receivedchargegifts_, int _isactivepay_, int _wishtimes_, long _lastwishtime_, long _lastwishpetid_, int _iseatlunch_, int _iseatdinner_) {
		this.receivednewgift = _receivednewgift_;
		this.totallogindays = _totallogindays_;
		this.totaldays = _totaldays_;
		this.signedlist = _signedlist_;
		this.hastodaysign = _hastodaysign_;
		this.continuedays = _continuedays_;
		this.lefttimes = _lefttimes_;
		this.receivedconlogingift = _receivedconlogingift_;
		this.receivedtype = _receivedtype_;
		this.dailyonlinetime = _dailyonlinetime_;
		this.monthcardplayer = _monthcardplayer_;
		this.monthcardleftday = _monthcardleftday_;
		this.dates = _dates_;
		this.buygrowplantype = _buygrowplantype_;
		this.buygrowplantypetwo = _buygrowplantypetwo_;
		this.buygrowplantypethr = _buygrowplantypethr_;
		this.growplanonetime = _growplanonetime_;
		this.growplantwotime = _growplantwotime_;
		this.growplanthreetime = _growplanthreetime_;
		this.threegifts = _threegifts_;
		this.fivegifts = _fivegifts_;
		this.sevengifts = _sevengifts_;
		this.isfirstpayused = _isfirstpayused_;
		this.dailytotalcharge = _dailytotalcharge_;
		this.totalcharge = _totalcharge_;
		this.totalcost = _totalcost_;
		this.hasbuyproduct = _hasbuyproduct_;
		this.receivedchargegifts = _receivedchargegifts_;
		this.isactivepay = _isactivepay_;
		this.wishtimes = _wishtimes_;
		this.lastwishtime = _lastwishtime_;
		this.lastwishpetid = _lastwishpetid_;
		this.iseatlunch = _iseatlunch_;
		this.iseatdinner = _iseatdinner_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(receivednewgift.size());
		for (Integer _v_ : receivednewgift) {
			_os_.marshal(_v_);
		}
		_os_.marshal(totallogindays);
		_os_.marshal(totaldays);
		_os_.compact_uint32(signedlist.size());
		for (Integer _v_ : signedlist) {
			_os_.marshal(_v_);
		}
		_os_.marshal(hastodaysign);
		_os_.marshal(continuedays);
		_os_.marshal(lefttimes);
		_os_.compact_uint32(receivedconlogingift.size());
		for (Integer _v_ : receivedconlogingift) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(receivedtype.size());
		for (Integer _v_ : receivedtype) {
			_os_.marshal(_v_);
		}
		_os_.marshal(dailyonlinetime);
		_os_.marshal(monthcardplayer);
		_os_.marshal(monthcardleftday);
		_os_.compact_uint32(dates.size());
		for (Integer _v_ : dates) {
			_os_.marshal(_v_);
		}
		_os_.marshal(buygrowplantype);
		_os_.marshal(buygrowplantypetwo);
		_os_.marshal(buygrowplantypethr);
		_os_.marshal(growplanonetime);
		_os_.marshal(growplantwotime);
		_os_.marshal(growplanthreetime);
		_os_.compact_uint32(threegifts.size());
		for (Integer _v_ : threegifts) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(fivegifts.size());
		for (Integer _v_ : fivegifts) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(sevengifts.size());
		for (Integer _v_ : sevengifts) {
			_os_.marshal(_v_);
		}
		_os_.marshal(isfirstpayused);
		_os_.marshal(dailytotalcharge);
		_os_.marshal(totalcharge);
		_os_.marshal(totalcost);
		_os_.compact_uint32(hasbuyproduct.size());
		for (Integer _v_ : hasbuyproduct) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(receivedchargegifts.size());
		for (Integer _v_ : receivedchargegifts) {
			_os_.marshal(_v_);
		}
		_os_.marshal(isactivepay);
		_os_.marshal(wishtimes);
		_os_.marshal(lastwishtime);
		_os_.marshal(lastwishpetid);
		_os_.marshal(iseatlunch);
		_os_.marshal(iseatdinner);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			receivednewgift.add(_v_);
		}
		totallogindays = _os_.unmarshal_int();
		totaldays = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			signedlist.add(_v_);
		}
		hastodaysign = _os_.unmarshal_int();
		continuedays = _os_.unmarshal_int();
		lefttimes = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			receivedconlogingift.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			receivedtype.add(_v_);
		}
		dailyonlinetime = _os_.unmarshal_long();
		monthcardplayer = _os_.unmarshal_int();
		monthcardleftday = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			dates.add(_v_);
		}
		buygrowplantype = _os_.unmarshal_int();
		buygrowplantypetwo = _os_.unmarshal_int();
		buygrowplantypethr = _os_.unmarshal_int();
		growplanonetime = _os_.unmarshal_int();
		growplantwotime = _os_.unmarshal_int();
		growplanthreetime = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			threegifts.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			fivegifts.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			sevengifts.add(_v_);
		}
		isfirstpayused = _os_.unmarshal_byte();
		dailytotalcharge = _os_.unmarshal_long();
		totalcharge = _os_.unmarshal_long();
		totalcost = _os_.unmarshal_long();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			hasbuyproduct.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			receivedchargegifts.add(_v_);
		}
		isactivepay = _os_.unmarshal_int();
		wishtimes = _os_.unmarshal_int();
		lastwishtime = _os_.unmarshal_long();
		lastwishpetid = _os_.unmarshal_long();
		iseatlunch = _os_.unmarshal_int();
		iseatdinner = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBonusInfo) {
			SBonusInfo _o_ = (SBonusInfo)_o1_;
			if (!receivednewgift.equals(_o_.receivednewgift)) return false;
			if (totallogindays != _o_.totallogindays) return false;
			if (totaldays != _o_.totaldays) return false;
			if (!signedlist.equals(_o_.signedlist)) return false;
			if (hastodaysign != _o_.hastodaysign) return false;
			if (continuedays != _o_.continuedays) return false;
			if (lefttimes != _o_.lefttimes) return false;
			if (!receivedconlogingift.equals(_o_.receivedconlogingift)) return false;
			if (!receivedtype.equals(_o_.receivedtype)) return false;
			if (dailyonlinetime != _o_.dailyonlinetime) return false;
			if (monthcardplayer != _o_.monthcardplayer) return false;
			if (monthcardleftday != _o_.monthcardleftday) return false;
			if (!dates.equals(_o_.dates)) return false;
			if (buygrowplantype != _o_.buygrowplantype) return false;
			if (buygrowplantypetwo != _o_.buygrowplantypetwo) return false;
			if (buygrowplantypethr != _o_.buygrowplantypethr) return false;
			if (growplanonetime != _o_.growplanonetime) return false;
			if (growplantwotime != _o_.growplantwotime) return false;
			if (growplanthreetime != _o_.growplanthreetime) return false;
			if (!threegifts.equals(_o_.threegifts)) return false;
			if (!fivegifts.equals(_o_.fivegifts)) return false;
			if (!sevengifts.equals(_o_.sevengifts)) return false;
			if (isfirstpayused != _o_.isfirstpayused) return false;
			if (dailytotalcharge != _o_.dailytotalcharge) return false;
			if (totalcharge != _o_.totalcharge) return false;
			if (totalcost != _o_.totalcost) return false;
			if (!hasbuyproduct.equals(_o_.hasbuyproduct)) return false;
			if (!receivedchargegifts.equals(_o_.receivedchargegifts)) return false;
			if (isactivepay != _o_.isactivepay) return false;
			if (wishtimes != _o_.wishtimes) return false;
			if (lastwishtime != _o_.lastwishtime) return false;
			if (lastwishpetid != _o_.lastwishpetid) return false;
			if (iseatlunch != _o_.iseatlunch) return false;
			if (iseatdinner != _o_.iseatdinner) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += receivednewgift.hashCode();
		_h_ += totallogindays;
		_h_ += totaldays;
		_h_ += signedlist.hashCode();
		_h_ += hastodaysign;
		_h_ += continuedays;
		_h_ += lefttimes;
		_h_ += receivedconlogingift.hashCode();
		_h_ += receivedtype.hashCode();
		_h_ += (int)dailyonlinetime;
		_h_ += monthcardplayer;
		_h_ += monthcardleftday;
		_h_ += dates.hashCode();
		_h_ += buygrowplantype;
		_h_ += buygrowplantypetwo;
		_h_ += buygrowplantypethr;
		_h_ += growplanonetime;
		_h_ += growplantwotime;
		_h_ += growplanthreetime;
		_h_ += threegifts.hashCode();
		_h_ += fivegifts.hashCode();
		_h_ += sevengifts.hashCode();
		_h_ += (int)isfirstpayused;
		_h_ += (int)dailytotalcharge;
		_h_ += (int)totalcharge;
		_h_ += (int)totalcost;
		_h_ += hasbuyproduct.hashCode();
		_h_ += receivedchargegifts.hashCode();
		_h_ += isactivepay;
		_h_ += wishtimes;
		_h_ += (int)lastwishtime;
		_h_ += (int)lastwishpetid;
		_h_ += iseatlunch;
		_h_ += iseatdinner;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(receivednewgift).append(",");
		_sb_.append(totallogindays).append(",");
		_sb_.append(totaldays).append(",");
		_sb_.append(signedlist).append(",");
		_sb_.append(hastodaysign).append(",");
		_sb_.append(continuedays).append(",");
		_sb_.append(lefttimes).append(",");
		_sb_.append(receivedconlogingift).append(",");
		_sb_.append(receivedtype).append(",");
		_sb_.append(dailyonlinetime).append(",");
		_sb_.append(monthcardplayer).append(",");
		_sb_.append(monthcardleftday).append(",");
		_sb_.append(dates).append(",");
		_sb_.append(buygrowplantype).append(",");
		_sb_.append(buygrowplantypetwo).append(",");
		_sb_.append(buygrowplantypethr).append(",");
		_sb_.append(growplanonetime).append(",");
		_sb_.append(growplantwotime).append(",");
		_sb_.append(growplanthreetime).append(",");
		_sb_.append(threegifts).append(",");
		_sb_.append(fivegifts).append(",");
		_sb_.append(sevengifts).append(",");
		_sb_.append(isfirstpayused).append(",");
		_sb_.append(dailytotalcharge).append(",");
		_sb_.append(totalcharge).append(",");
		_sb_.append(totalcost).append(",");
		_sb_.append(hasbuyproduct).append(",");
		_sb_.append(receivedchargegifts).append(",");
		_sb_.append(isactivepay).append(",");
		_sb_.append(wishtimes).append(",");
		_sb_.append(lastwishtime).append(",");
		_sb_.append(lastwishpetid).append(",");
		_sb_.append(iseatlunch).append(",");
		_sb_.append(iseatdinner).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

