package lx.gs.exchange;

import cfg.exchange.Const;
import xdb.Xdb;

import java.util.concurrent.TimeUnit;

public enum ExchangeModule implements gs.Module, gs.RoleLoginListener {
	INSTANCE;

	public final static Groups group = new Groups();
	
	@Override
	public void start() {
        xtable.Exchangeitem.getTable().browse((k, v) -> {
            FExchange.addItem(v.toData());
            return true;
        });

        Xdb.getInstance().getExecutor().scheduleAtFixedRate(() -> {
            final long now = System.currentTimeMillis();
            for(Record item : FExchange.getItems()) {
                if(item.item.getUnshelvetime() < now) {
                    new xdb.Procedure() {
                        @Override
                        protected boolean process() {
                            return FExchange.delItem(item.id, true);
                        }
                    }.execute();
                }
            }
        }, Const.EXCHANGE_UNSHELVE_TIME / 2, Const.EXCHANGE_UNSHELVE_TIME, TimeUnit.SECONDS);
    }

	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		xdb.Transaction.tsend(roleid, FExchange.createSInfo(roleid));
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {
		
	}
	
}
