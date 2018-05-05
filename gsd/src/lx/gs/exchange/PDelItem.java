package lx.gs.exchange;

import gs.AProcedure;
import lx.gs.exchange.msg.CDelItem;

public final class PDelItem extends AProcedure<CDelItem> {

	public PDelItem(CDelItem p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
	   return FExchange.delItem(param.exchangeid, false);
	}

}
