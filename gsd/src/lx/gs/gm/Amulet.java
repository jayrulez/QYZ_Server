package lx.gs.gm;

import cfg.Const;
import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.amulet.FAmulet;

@Module(comment="护符模块GM命令")
public class Amulet {

	@Cmd(comment="获得个人护符信息")
	public Object getHFInfo(){
		final long roleid = GmSession.current().getRoleid();
		return FAmulet.convert(FAmulet.get(roleid));
	}

	@Cmd(comment="洗练护符")
	public Object washHF(@Param(name="pageid", comment="护符页面id,取值1,2,3")int pageid){
		final long roleid = GmSession.current().getRoleid();
		xbean.RoleAmuletInfo info = FAmulet.get(roleid);
		FAmulet.washAmulet(roleid, info.getPagemap().get(pageid));
		return info.getPagemap().get(pageid);
	}

	@Cmd(comment="应用洗练护符")
	public Object applyWashHF(@Param(name="pageid", comment="护符页面id,取值1,2,3")int pageid){
		final long roleid = GmSession.current().getRoleid();
		xbean.RoleAmuletInfo info = FAmulet.get(roleid);
		FAmulet.applyWashResult(info.getPagemap().get(pageid));
		return info.getPagemap().get(pageid);
	}

	@Cmd(comment="取消洗练护符")
	public Object cancelWashHF(@Param(name="pageid", comment="护符页面id,取值1,2,3")int pageid){
		final long roleid = GmSession.current().getRoleid();
		xbean.RoleAmuletInfo info = FAmulet.get(roleid);
		FAmulet.cancelWashResult(info.getPagemap().get(pageid));
		return info.getPagemap().get(pageid);
	}

	@Cmd(comment="锁定护符")
	public Object lockHF(@Param(name="pageid", comment="护符页面id,取值1,2,3")int pageid,
				@Param(name="hufuid", comment="护符id,取值1-6")int hufuid){
		final long roleid = GmSession.current().getRoleid();
		xbean.RoleAmuletInfo info = FAmulet.get(roleid);
		info.getPagemap().get(pageid).getPropmap().get(hufuid).setIslock(Const.TRUE);
		return info.getPagemap().get(pageid).getPropmap().get(hufuid);
	}

	@Cmd(comment="解锁护符")
	public Object unLockHF(@Param(name="pageid", comment="护符页面id,取值1,2,3")int pageid,
				@Param(name="hufuid", comment="护符id,取值1-6")int hufuid){
		final long roleid = GmSession.current().getRoleid();
		xbean.RoleAmuletInfo info = FAmulet.get(roleid);
		info.getPagemap().get(pageid).getPropmap().get(hufuid).setIslock(Const.FALSE);
		return info.getPagemap().get(pageid).getPropmap().get(hufuid);
	}

}