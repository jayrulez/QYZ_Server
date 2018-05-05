package lx.gs.gm;

import cfg.CfgMgr;
import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.exchange.FExchange;
import lx.gs.idgen.FIdGen;

@Module(comment="交易所")
public final class Exchange {
	@Cmd(comment="上架物品表中所有物品")
	public void addAllItems(@Param(name="num", comment="每个物品上架数目") int num) {
		final long roleid = GmSession.current().getRoleid();
		for(cfg.item.ItemBasic item : CfgMgr.itembasic.values()) {
			final xbean.ExchangeItem ei = xbean.Pod.newExchangeItem();
			ei.setId(FIdGen.allocItemId(roleid));
			ei.setOwner(roleid);
			ei.setModelid(item.id);
			ei.setPrice(item.prize);
			ei.setNum(num);
			xtable.Exchangeitem.insert(ei.getId(), ei);
			FExchange.addItem(ei.toData());
			xdb.Trace.info("GM.addAddItems item:{} num:{}", item.id, num);
		}
	}

}
