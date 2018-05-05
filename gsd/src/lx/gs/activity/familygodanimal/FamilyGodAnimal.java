package lx.gs.activity.familygodanimal;


import lx.gs.activity.Activity;
import lx.gs.activity.WeeklyTimeCaculator;
import lx.gs.chat.FChat;
import lx.gs.family.msg.SGodAnimalActivityStartNotify;

public class FamilyGodAnimal extends Activity {
	public static FamilyGodAnimal creat(cfg.family.BossConfig bconf){
		return new FamilyGodAnimal(bconf);
	}
	private final cfg.family.BossConfig bCfg;
	private FamilyGodAnimal(cfg.family.BossConfig bconf) {
		super(100, 0);//id用一个唯一的
		this.bCfg = bconf;
		for(cfg.family.OpenTime ot : bconf.opentime){//现在是每周开启两次
			this.intervalOpenTimeCalculators.add(new WeeklyTimeCaculator(ot.day-1, ot.hour,ot.minute, 0, bconf.battletime));
		}
        this.beforeOpenWorks.add(new Work(bconf.signtime * 1000L, () -> sendNotice(bconf.signtime * 1000L)));
    }
	
	public void sendNotice(long time){
		FChat.sendSystemMessage(new SGodAnimalActivityStartNotify(time));
	}

	@Override
	public void onLoad() {
		
	}

	@Override
	protected void onOpen() {
//        cleargodAnimalSatrtInfo();
	}

	@Override
	protected void onClose() {
		
	}

}
