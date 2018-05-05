package lx.gs.family;

import gs.AProcedure;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.family.msg.CEvolveGodAnimal;
import lx.gs.family.msg.SEvolveGodAnimal;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import xbean.Family;

public class PEvolveGodAnimal extends AProcedure<CEvolveGodAnimal> {

	public PEvolveGodAnimal(CEvolveGodAnimal p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SEvolveGodAnimal result = new SEvolveGodAnimal();

		xbean.Family family = FFamily.getFamilyByRoleId(roleid);
		if(family == null)
			return error(ErrorCode.FAMILY_NOT_EXISTED);
		
		if(!FFamily.isFamilyLeader(roleid, family))
			return error(ErrorCode.ONLY_CHIEF_VICECHIEF_CAN_ACTION);
		
		cfg.family.Boss bc = cfg.CfgMgr.boss.get(param.animalid);

		xbean.FamilyGodAnimal animal = family.getActivity().getGodanimalinfo().get(param.animalid);
		int curlevel = animal.getAnimallevel();
		
		if(curlevel >= family.getFlevel()) return error(ErrorCode.FAMILY_LEVEL_TOO_LOW);
		if(curlevel >= bc.bossinfo.size()) return error(ErrorCode.FAMILY_ANIMAL_IS_MAX_LEVEL);
		cfg.family.BossInfo conf = bc.bossinfo.get(curlevel - 1);

		if(animal.getExp() <= conf.requireexp) return error(ErrorCode.FAMILY_GOD_ANIMAL_EXP_NOT_ENOUGH);
		ErrorCode ret = FCondition.checkByReflection(roleid, conf, By.Family_EvolveAnimal);
		if(ret.err()){
            return error(ret);
        }

		animal.setAnimallevel(curlevel + 1);
        animal.setExp(animal.getExp() - conf.requireexp);  //current level total experience
        Long mapid = FFamily.FamilyId2MapId.get(family.getFamilyid());
        if(mapid != null){
            FMap.sendGodAnimalLvlup(mapid, param.animalid, animal.getAnimallevel());
        }

		result.animal = FFamily.makeProtocolFamilyAnimal(animal);
		response(result);
		return true;
	}

}
