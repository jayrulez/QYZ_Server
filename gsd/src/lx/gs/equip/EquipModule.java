package lx.gs.equip;

import cfg.CfgMgr;
import gs.RoleAttrListener;
import lx.gs.jade.FJade;
import xdb.Procedure;
import xdb.logs.Note;

import java.util.*;

/**
 * 装备模块
 * @author 任冬艳
 *
 */
public enum EquipModule implements gs.Module, gs.RoleLoginListener, RoleAttrListener, gs.LevelUpListener {

	INSTANCE;

	private Map<Integer, Integer> equip2Suit = new HashMap<>();
	private Map<Integer, List<Integer>> prfs2AmuletSkillList = new HashMap<>();
	private Map<Integer, List<Integer>> prfs2OtherPrfsSkillList = new HashMap<>();

	public int getEquipSuit(int equipKey) {
		return equip2Suit.getOrDefault(equipKey, 0);
	}

	public List<Integer> getPrfsWashSkills(int prfsId){
		return new ArrayList<>(prfs2AmuletSkillList.getOrDefault(prfsId, Collections.emptyList()));
	}

	public List<Integer> getOtherPrfsWashSkills(int prfsId){
		return new ArrayList<>(prfs2OtherPrfsSkillList.getOrDefault(prfsId, Collections.emptyList()));
	}

	@Override
	public void start() {
		CfgMgr.equipsuits.values().forEach(suits -> {
			suits.includeid.forEach(equipKey -> {
				if(equip2Suit.put(equipKey, suits.id) != null) {
					throw new RuntimeException(String.format("equipKey:%s in duplicate suit", equipKey));
				}
			});
		});
		initAmuletSkillConfig();
		registerListeners();
	}

	private void initAmuletSkillConfig() {
		CfgMgr.amuletskilllvl.values().forEach(amuletSkillLvl -> {
			int prfsId = amuletSkillLvl.carrer;
			if(!prfs2AmuletSkillList.containsKey(prfsId)){
				prfs2AmuletSkillList.put(prfsId, new ArrayList<>());
			}
			if(!prfs2OtherPrfsSkillList.containsKey(prfsId)){
				prfs2OtherPrfsSkillList.put(prfsId, new ArrayList<>());
			}
			prfs2AmuletSkillList.get(prfsId).add(amuletSkillLvl.skillid);
		});
		prfs2OtherPrfsSkillList.forEach((prfsId, skills) ->
				prfs2AmuletSkillList.forEach((prfsId2, skills2) -> {
					if(prfsId.intValue() != prfsId2.intValue()){
						skills.addAll(skills2);
					}
		}));
	}

	private void registerListeners() {
		xtable.Roleequipbag.getTable().addListener(new xdb.logs.Listener() {
			@Override
			public void onChanged(Object key) {}

			@Override
			public void onRemoved(Object key) {}

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				new Procedure() {
					@Override
					protected boolean process() {
						FEquip.onBodyChange((Long)key);
						return true;
					}
				}.execute();
			}
		}, "value", "equiponbodymap");

		xtable.Jade.getTable().addListener(new xdb.logs.Listener() {
			@Override
			public void onChanged(Object key) {}

			@Override
			public void onRemoved(Object key) {}

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				new xdb.Procedure() {
					@Override
					protected boolean process() {
						FJade.onJadeChange((Long)key);
						return true;
					}
				}.execute();
			}
		}, "value", "bonus");

		xtable.Jade.getTable().addListener(new xdb.logs.Listener() {
			@Override
			public void onChanged(Object key) {}

			@Override
			public void onRemoved(Object key) {}

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				new xdb.Procedure() {
					@Override
					protected boolean process() {
						FJade.onJadeChange((Long)key);
						return true;
					}
				}.execute();
			}
		}, "value", "level");
		
		xtable.Jade.getTable().addListener(new xdb.logs.Listener() {
			@Override
			public void onChanged(Object key) {}

			@Override
			public void onRemoved(Object key) {}

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				new xdb.Procedure() {
					@Override
					protected boolean process() {
						FJade.onJewelryChange((Long)key);
						return true;
					}
				}.execute();
			}
		}, "value", "jewelry");
	}

	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		FJade.sendJadeInfo(roleid);
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {
		FEquip.getEquipBag(roleid).getItems().forEach(equip -> FEquip.applyWash(equip));
		FEquip.getBodyBag(roleid).getItems().forEach(equip -> FEquip.applyWash(equip));
	}

	@Override
	public void updateRoleAttr(long roleid) {
		FEquip.updateEquipRoleAttr(roleid);
		FJade.updateRoleAttr(roleid);
	}

	@Override
	public void onLevelUp(long roleid, int oldLevel, int newLevel) {
		FJade.onRoleLevelUp(roleid, oldLevel, newLevel);
	}
}