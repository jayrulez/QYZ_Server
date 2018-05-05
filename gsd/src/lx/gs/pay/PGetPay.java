package lx.gs.pay;

import gs.AProcedure;

public class PGetPay extends AProcedure<CGetPay>{

	protected PGetPay(CGetPay param) {
		super(param);
	}

	@Override
	protected boolean doProcess() throws Exception {
//		xbean.RolePay pay = FPay.getRolePay(roleid);
//		
//		SGetPay response = new SGetPay();
//		for(config.Pay payCfg : FPay.getPayCfgs()){
//			Product productProto = new Product(payCfg.getProductID(), payCfg.getRmb(), 
//					payCfg.getMoney(), payCfg.getFirstPayMoney(), payCfg.getPayReturnMoney());
//			
//			response.products.add(productProto);
//		}
//		response.isfirstpayused = pay.getIsfirstpayused();
//		response.firstpayaward.result = PayAward.STATUS_NOT_PAY;
//		if(gs.Utils.toBoolean(pay.getIsfirstpayused())){
//			response.firstpayaward.result = PayAward.STATUS_GET_AWARD;
//		}
//		for(Map.Entry<Integer, Integer> entry : config.Paygiftbag.FIRST_PAY.getItemID2ItemCountMap().entrySet()){
//			int itemid = entry.getKey();
//			int count = entry.getValue();
//			if(itemid == 0 || count <= 0){
//				continue;
//			}
//			Item item = new Item(itemid, count);
//			response.firstpayaward.awarditems.add(item);
//		}
//		
//		response.dailypayaward.result = pay.getDailypaystatus();
//		for(Map.Entry<Integer, Integer> entry : config.Paygiftbag.DAILY_PAY.getItemID2ItemCountMap().entrySet()){
//			int itemid = entry.getKey();
//			int count = entry.getValue();
//			if(itemid == 0 || count <= 0){
//				continue;
//			}
//			Item item = new Item(itemid, count);
//			response.dailypayaward.awarditems.add(item);
//		}
//		
//		response.dailytotalpayaward.result = pay.getDailytotalpaystatus();
//		for(Map.Entry<Integer, Integer> entry : config.Paygiftbag.DAILY_TOTAL_PAY.getItemID2ItemCountMap().entrySet()){
//			int itemid = entry.getKey();
//			int count = entry.getValue();
//			if(itemid == 0 || count <= 0){
//				continue;
//			}
//			Item item = new Item(itemid, count);
//			response.dailytotalpayaward.awarditems.add(item);
//		}
//		
//		return response(response);
		return true;
	}

}
