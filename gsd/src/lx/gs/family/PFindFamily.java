package lx.gs.family;

import cfg.bonus.RankType;
import gs.AProcedure;
import lx.gs.family.msg.CFindFamily;
import lx.gs.family.msg.SFindFamily;
import lx.gs.leaderboard.FLeaderBoard;
import xbean.BoardEntry;

import java.util.*;

public class PFindFamily extends AProcedure<CFindFamily> {

	public PFindFamily(CFindFamily p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
        if(param.startindex >= 80){
            return true;
        }
        String familyName = param.familyname.trim();
		final SFindFamily re = new SFindFamily();
		re.familyname = familyName;
        re.startindex = param.startindex;
        List<Long> fids = familyName.isEmpty() ? FLeaderBoard.getFamilyIdByRanking(param.startindex, 20) : FFamily.findFamidsByname(familyName);
        for(long fid : fids){
            re.families.add(FFamily.makeProtocolFamilyBasicInfo(fid));
        }
		response(re);
		return true;
	}

}
