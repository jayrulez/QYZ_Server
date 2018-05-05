package lx.gs.family;

import common.ErrorCode;
import gs.AProcedure;
import lx.gs.family.msg.CUpdateDeclarationOrPublic;
import lx.gs.family.msg.SUpdateDeclarationOrPublic;
import lx.gs.role.FRole;

public class PUpdateDeclarationOrPublic extends AProcedure<CUpdateDeclarationOrPublic> {

	public PUpdateDeclarationOrPublic(CUpdateDeclarationOrPublic p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
        final int updatetype = param.updatetype;

        xbean.Family family = FFamily.getFamilyByRoleId(roleid);
        if(!FFamily.isFamilyLeader(roleid, family)){
            return error(ErrorCode.ONLY_CHIEF_VICECHIEF_CAN_ACTION);
        }

        String newdetail = param.newtext.replaceAll("\r|\n", "");//remove \r \n

        if(FRole.isSenseWord(newdetail)){
            return error(ErrorCode.TEXT_INCLUDE_SENCITIVE_WORDS);
        }

		xbean.FamilyMember opfm = family.getMembers().get(roleid);
		cfg.family.FamilyJob job = cfg.CfgMgr.familyjob.get(opfm.getFamilyjob());

		if(updatetype == lx.gs.family.msg.CUpdateDeclarationOrPublic.UPDATE_DECLARATION){
			if(!job.caneditdeclaration){
				return error(ErrorCode.FAMILY_OPERATION_PRIVILEGE_NOT_ENOUGH);
			}
			if(newdetail.length() > cfg.family.FamilyInfo.DECLARATION_LENGTH)
				return error(ErrorCode.TEXT_OVERLENT);
			family.setDeclaration(newdetail);
		} else if(updatetype == lx.gs.family.msg.CUpdateDeclarationOrPublic.UPDATE_PUBLIC){
			if(!job.caneditannouncement){
				return error(ErrorCode.FAMILY_OPERATION_PRIVILEGE_NOT_ENOUGH);
			}			
			if(newdetail.length() > cfg.family.FamilyInfo.PUBLICINFO_LENGTH)
				return error(ErrorCode.TEXT_OVERLENT);
			family.setPublicinfo(newdetail);
		} else {
		    return false;
        }
		family.setUpdatetime(System.currentTimeMillis());
		
		FFamily.sendFamilyInfoChangeNotify(family);
		
		response(new SUpdateDeclarationOrPublic(updatetype, newdetail));
		return true;
	}

}
