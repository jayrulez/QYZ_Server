package cfg.role;
public final class RoleConfig extends cfg.CfgObject {
	public final static int TYPEID = -409859482;
	final public int getTypeId() { return TYPEID; }
	public final int bornmap;
	public final cfg.map.Vector2 bornposition;
	public final float bornorientrotation;
	public final int multiplayermapinnersightradius;
	public final int multiplayermapoutersightradius;
	public final int oneplayermapinnersightradius;
	public final int oneplayermapoutersightradius;
	public final int skillperformsightradius;
	public final float worldbonus;
	public final float duegonbonus;
	public final cfg.role.PkInfo pkinfo;
	public final int createrolelevel;
	public final int createroleyuanbao;
	public final int createrolebindyuanbao;
	public final int createrolexnb;
	public final java.util.List<Integer> createroletasks = new java.util.ArrayList<>();
	public final int usermaxrolenum;
	public final int maxtili;
	public final cfg.cmd.action.TiLi levelupgaintili;
	public final int addonetiliinterval;
	public final cfg.cmd.action.TiLi buyaddtili;
	public final int tilidanid;
	public final cfg.cmd.condition.VipLimits buytilicost;
	public final cfg.cmd.condition.VipLimits renamecost;
	public final int reconecttimeout;
	public final int attackeffecttime;
	public final float batchsynckillmonsterdelay;
	public final float hpbroadcastinterval;
	public final float movebroadcastinterval;
	public final cfg.role.BroadcastLimit broadcastlimit;
	public final int maxtotalremainsend;
	public final int maxremainsendpersecond;
	public final int maxaddremainpersecond;
	public final int checkmoveinterval;
	public final float initmovedelta;
	public final float addmovedeltapersecond;
	public final float maxpositiondelta;
	public final int minnamelength;
	public final int maxnamelength;
	public final java.util.List<Integer> playeramount = new java.util.ArrayList<>();
	public final java.util.List<Integer> monsteramount = new java.util.ArrayList<>();
	public final java.util.List<Float> cameraposition = new java.util.ArrayList<>();
	public final java.util.Map<Integer, Integer> autobanskill = new java.util.HashMap<>();
	public final cfg.cmd.condition.CoolDown invitecooldown;
	public final int minspeaklevel;
	public final int minreportlevel;
	public final int everydayreportnum;
	public final int basicspeakinterval;
	public final java.util.List<Integer> bereportednum = new java.util.ArrayList<>();
	public final java.util.List<Integer> silenttime = new java.util.ArrayList<>();
	public final java.util.List<Integer> intervalreducebyvip = new java.util.ArrayList<>();
	public final java.util.List<Integer> intervallevel = new java.util.ArrayList<>();
	public final java.util.List<Integer> intervalreducebylevel = new java.util.ArrayList<>();
	public final int pinyinsensecount;
	public final float pvpdamage;
	public final java.util.List<Float> autobattleradius = new java.util.ArrayList<>();
	public final int defaultradius;
	public final java.util.List<cfg.role.SkillPriorityConfig> aotoskillpriority = new java.util.ArrayList<>();
	public final int offlinecalculatetime;
	public final int offlinemaxtime;
	public final int maxsendflowerlist;
	public final int maxreceiveflowerlist;
	public final int legalwordlevel;
	public final int legalwordviplevel;
	public final int maxillegalvoiceduration;
	public final int minaccumulatedailyvoicesize;
	public final cfg.cmd.condition.DayLimit dailyillegalvoicesize;
	public final int renamecardid;
	public final int familyrenamecardid;
	public final java.util.List<Integer> familyrenamecost = new java.util.ArrayList<>();
	public final int medicalnpcid;
	public final int warehousenpcid;
	public RoleConfig(cfg.DataStream fs) {
		this.bornmap = fs.getInt();
		this.bornposition = new cfg.map.Vector2(fs);
		this.bornorientrotation = fs.getFloat();
		this.multiplayermapinnersightradius = fs.getInt();
		this.multiplayermapoutersightradius = fs.getInt();
		this.oneplayermapinnersightradius = fs.getInt();
		this.oneplayermapoutersightradius = fs.getInt();
		this.skillperformsightradius = fs.getInt();
		this.worldbonus = fs.getFloat();
		this.duegonbonus = fs.getFloat();
		this.pkinfo = new cfg.role.PkInfo(fs);
		this.createrolelevel = fs.getInt();
		this.createroleyuanbao = fs.getInt();
		this.createrolebindyuanbao = fs.getInt();
		this.createrolexnb = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.createroletasks.add(fs.getInt());
		}
		this.usermaxrolenum = fs.getInt();
		this.maxtili = fs.getInt();
		this.levelupgaintili = new cfg.cmd.action.TiLi(fs);
		this.addonetiliinterval = fs.getInt();
		this.buyaddtili = new cfg.cmd.action.TiLi(fs);
		this.tilidanid = fs.getInt();
		this.buytilicost = new cfg.cmd.condition.VipLimits(fs);
		this.renamecost = new cfg.cmd.condition.VipLimits(fs);
		this.reconecttimeout = fs.getInt();
		this.attackeffecttime = fs.getInt();
		this.batchsynckillmonsterdelay = fs.getFloat();
		this.hpbroadcastinterval = fs.getFloat();
		this.movebroadcastinterval = fs.getFloat();
		this.broadcastlimit = new cfg.role.BroadcastLimit(fs);
		this.maxtotalremainsend = fs.getInt();
		this.maxremainsendpersecond = fs.getInt();
		this.maxaddremainpersecond = fs.getInt();
		this.checkmoveinterval = fs.getInt();
		this.initmovedelta = fs.getFloat();
		this.addmovedeltapersecond = fs.getFloat();
		this.maxpositiondelta = fs.getFloat();
		this.minnamelength = fs.getInt();
		this.maxnamelength = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.playeramount.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsteramount.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.cameraposition.add(fs.getFloat());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.autobanskill.put(_k, fs.getInt());
		}
		this.invitecooldown = new cfg.cmd.condition.CoolDown(fs);
		this.minspeaklevel = fs.getInt();
		this.minreportlevel = fs.getInt();
		this.everydayreportnum = fs.getInt();
		this.basicspeakinterval = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bereportednum.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.silenttime.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.intervalreducebyvip.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.intervallevel.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.intervalreducebylevel.add(fs.getInt());
		}
		this.pinyinsensecount = fs.getInt();
		this.pvpdamage = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.autobattleradius.add(fs.getFloat());
		}
		this.defaultradius = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.aotoskillpriority.add(new cfg.role.SkillPriorityConfig(fs));
		}
		this.offlinecalculatetime = fs.getInt();
		this.offlinemaxtime = fs.getInt();
		this.maxsendflowerlist = fs.getInt();
		this.maxreceiveflowerlist = fs.getInt();
		this.legalwordlevel = fs.getInt();
		this.legalwordviplevel = fs.getInt();
		this.maxillegalvoiceduration = fs.getInt();
		this.minaccumulatedailyvoicesize = fs.getInt();
		this.dailyillegalvoicesize = new cfg.cmd.condition.DayLimit(fs);
		this.renamecardid = fs.getInt();
		this.familyrenamecardid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.familyrenamecost.add(fs.getInt());
		}
		this.medicalnpcid = fs.getInt();
		this.warehousenpcid = fs.getInt();
	}
}