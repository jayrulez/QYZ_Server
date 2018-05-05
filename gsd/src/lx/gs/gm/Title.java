package lx.gs.gm;

import cfg.CfgMgr;
import common.ErrorCode;
import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.role.title.FTitle;

import java.util.HashMap;

@Module(comment="称号模块GM命令")
public class Title {
	@Cmd(comment="获取一个称号.")
	public Object unlockTitle(
			@Param(name="titlekey", comment="称号key：titlekey")int titlekey)
	{
		final long roleid = GmSession.current().getRoleid();
		HashMap<String, Object> result = new HashMap<String,Object>();
		ErrorCode code = FTitle.unlockTitle(roleid, titlekey);
		xbean.RoleTitle info = xtable.Title.get(roleid);
		
		if(info != null){
			result.put("titles", info.getTitleinfo());
			result.put("code", code);
		}
		return result;
	}

	@Cmd(comment="获取全部称号")
	public Object unlockAllTitle() {
		final long roleid = GmSession.current().getRoleid();
		CfgMgr.title.keySet().forEach(key -> FTitle.unlockTitle(roleid, key));
		HashMap<String, Object> result = new HashMap<>();
		result.put("count", CfgMgr.title.size());
		return result;
	}
	
	@Cmd(comment="获取称号信息.")
	public Object getTitle()
	{
		final long roleid = GmSession.current().getRoleid();
		HashMap<String, Object> result = new HashMap<String,Object>();
		xbean.RoleTitle tt = FTitle.getRoleTitle(roleid);
		if(tt != null){
			result.put("titles", tt);
		}
		return result;
	}
	
}