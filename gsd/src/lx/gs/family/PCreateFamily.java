package lx.gs.family;

import common.ErrorCode;
import gs.AProcedure;
import lx.gs.event.EventModule;
import lx.gs.event.FamilyCreateEvent;
import lx.gs.family.msg.CCreateFamily;
import lx.gs.family.msg.SCreateFamily;
import lx.gs.logger.By;
import lx.gs.role.FRole;

import java.util.Map;

public class PCreateFamily extends AProcedure<CCreateFamily> {

	public PCreateFamily(CCreateFamily p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
		SCreateFamily result = new SCreateFamily();
		xbean.RoleFamily roleinfo = FFamily.getRoleFamilyInfo(roleid);
        if(roleinfo.getCurrentfid() > 0) {
            return error(ErrorCode.FAMILY_ALREADY_IN_ONE);
        }

		if(!FFamily.isFamilyUnLocked(roleid)) return error(ErrorCode.FAMILY_MODULE_LOCKED);
		if(FFamily.isInQuitTimeLimit(roleinfo, cfg.family.FamilyInfo.DISALLOW_ACTION_HOUR_AFTER_QUIT)) 
			return error(ErrorCode.FAMILY_ACTION_DISALLOWED_AFATER_QUIT);
		//check名字是否可用
		if (param.familyname.length() < cfg.family.FamilyInfo.NAME_MIN_LENGTH) {
			return error(ErrorCode.NAME_SHORTLEN);			
		}
		if (param.familyname.length() > cfg.family.FamilyInfo.NAME_MAX_LENGTH) {
			return error(ErrorCode.NAME_OVERLEN);			
		}
		String fullName = FFamily.makeFullname(param.familyname);
		final Map<String, Long> famiyNam2id = FFamily.getFamilyNames();
        if(famiyNam2id.containsKey(fullName)) {
            return error(ErrorCode.FAMILY_HAS_EXIST);
        }
		
		// 敏感词和唯一名检测
		if (FRole.isSenseWord(param.familyname)) {
			return error(ErrorCode.NAME_SENSE);
		}

        if(!FRole.checkCostYuanBao(roleid, xtable.Roleinfos.get(roleid), cfg.family.FamilyInfo.CREATE_REQUIRE_YUANBAO, By.Create_Family)){
            return error(ErrorCode.YUANBAO_NOT_ENOUGH);
        }

		long curtime = System.currentTimeMillis();
		xbean.Family newf = xbean.Pod.newFamily();
		long id = xtable.Family.insert(newf);
		newf.setFamilyid(id);
		newf.setFamilyname(param.familyname);		
		newf.setUpdatetime(curtime);
		newf.setCreatetime(curtime);
		newf.setFlevel(1);
		newf.setMalllevel(1);
		newf.setDeclaration(cfg.family.FamilyInfo.DEFAULT_DECLARATION);
		newf.setPublicinfo(cfg.family.FamilyInfo.DEFAULT_PUBLICINFO);
			
		famiyNam2id.put(fullName, id);
		
		xbean.FamilyMember member = xbean.Pod.newFamilyMember();
		member.setFamilyjob(cfg.family.FamilyJobEnum.CHIEF);
		member.setJointime(curtime);
		member.setRoleid(roleid);
		
		newf.getMembers().put(roleid, member);
		newf.setChiefid(roleid);

        FFamily.initFamilyAnimals(newf);
		FFamily.initFamilyJobInfo(newf);
		FFamily.addJobStaff(newf, cfg.family.FamilyJobEnum.CHIEF, roleid);

		FFamily.addFamilyLog(newf, roleid,lx.gs.family.msg.FamilyLogReport.TYPE_CREATE_FAMILY, 0);
		
		roleinfo.setCurrentfid(id);

        FFamily.onJoinFamily(newf, member);

		EventModule.INSTANCE.broadcastEvent(new FamilyCreateEvent(roleid, newf.getFamilyid(), fullName));
		result.family = FFamily.makeProtocolFamilyBasicInfo(newf);
		response(result);
		return true;
	}

}
