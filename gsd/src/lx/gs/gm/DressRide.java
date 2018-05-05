package lx.gs.gm;

import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.dress.FDress;
import lx.gs.dress.SDeActiveDress;
import lx.gs.dress.SGetDressInfo;
import lx.gs.mount.FRide;
import xbean.RoleDress;
import xdb.Transaction;

import java.util.HashMap;

@Module(comment="时装和坐骑模块GM命令")
public class DressRide {
	
	@Cmd(comment="获得一个时装")
	public Object unlockDress(@Param(name="fashid", comment="时装id")int fashid){
		final long roleid = GmSession.current().getRoleid();
		HashMap<String, Object> result = new HashMap<String,Object>();
		FDress.unlockDress(roleid, fashid, 0);
		xbean.RoleDress rd = FDress.get(roleid);
		result.put("dresses", rd);
		return result;
	}
	
	@Cmd(comment="获得一个坐骑")
	public Object unlockRide(@Param(name="rideid", comment="坐骑id")int rideid){
		final long roleid = GmSession.current().getRoleid();
		HashMap<String, Object> result = new HashMap<String,Object>();
		FRide.unlockRide(roleid, rideid, 0);
		xbean.RoleRide rr = FRide.getRoleRide(roleid);
		result.put("rides", rr);
		return result;
	}
	
	@Cmd(comment="获得坐骑信息")
	public Object getRide(){
		final long roleid = GmSession.current().getRoleid();
		HashMap<String, Object> result = new HashMap<String,Object>();
		xbean.RoleRide rr = FRide.getRoleRide(roleid);
		result.put("rides", rr);
		return result;
	}
	
	@Cmd(comment="获得时装信息")
	public Object getDress(){
		final long roleid = GmSession.current().getRoleid();
		HashMap<String, Object> result = new HashMap<String,Object>();
		xbean.RoleDress rd = FDress.get(roleid);
		result.put("dresses", rd);
		return result;
	}
	
	@Cmd(comment="激活一个时装")
	public Object activeDress(@Param(name="fashid", comment="时装id")int fashid){
		final long roleid = GmSession.current().getRoleid();
		HashMap<String, Object> result = new HashMap<String,Object>();
		FDress.unlockDress(roleid, fashid, 0);
		xbean.RoleDress rd = FDress.get(roleid);
		result.put("dresses", rd);
		return result;
	}
	
	@Cmd(comment="激活一个坐骑")
	public Object activeRide(@Param(name="rideid", comment="坐骑id")int rideid){
		final long roleid = GmSession.current().getRoleid();
		HashMap<String, Object> result = new HashMap<String,Object>();
		FRide.unlockRide(roleid, rideid, 0);
		xbean.RoleRide rr = FRide.getRoleRide(roleid);
		result.put("rides", rr);
		return result;
	}

	@Cmd(comment="删掉一个时装")
	public Object delDress(@Param(name="dressid", comment="时装id")int dressid){
		final long roleid = GmSession.current().getRoleid();
		RoleDress roleDress = FDress.get(roleid);
		roleDress.setActivedress(0);
		roleDress.getDresses().remove(dressid);
		Transaction.tsendWhileCommit(roleid, new SDeActiveDress());

		SGetDressInfo sGetDressInfo = new SGetDressInfo();
		roleDress.getDresses().values().forEach(dress ->
				sGetDressInfo.dresslist.add(FDress.convert(dress)));
		Transaction.tsendWhileCommit(roleid, sGetDressInfo);
		return true;
	}
	
}
