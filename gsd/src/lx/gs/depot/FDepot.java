package lx.gs.depot;

import cfg.bag.BagType;
import lx.gs.bag.*;
import lx.gs.depot.msg.SSyncGoldCoin;
import xdb.Transaction;
import xtable.Roleequipdepot;
import xtable.Rolefragmentdepot;
import xtable.Roleitemdepot;
import xtable.Roletalismandepot;

public class FDepot {

	public static AbstractBag getDepot(long roleId, int depotType) {
		switch (depotType) {
			case BagType.DEPOT_EQUIP:
				return getEquipDepot(roleId);
			case BagType.DEPOT_ITEM:
				return getItemDepot(roleId);
			case BagType.DEPOT_FRAGMENT:
				return getFragmentDepot(roleId);
			case BagType.DEPOT_TALISMAN:
				return getTalismanDepot(roleId);
			default: return null;
		}
	}

	public static EquipBag getEquipDepot(long roleid) {
		return new EquipBag(roleid, BagType.DEPOT_EQUIP, Roleequipdepot.get(roleid).getCapacity());
	}

	public static ItemBag getItemDepot(long roleid) {
		return new ItemBag(roleid, BagType.DEPOT_ITEM, Roleitemdepot.get(roleid).getCapacity());
	}

	public static FragmentBag getFragmentDepot(long roleid) {
		return new FragmentBag(roleid, BagType.DEPOT_FRAGMENT, Rolefragmentdepot.get(roleid).getCapacity());
	}

	public static TalismanBag getTalismanDepot(long roleid) {
		return new TalismanBag(roleid, BagType.DEPOT_TALISMAN, Roletalismandepot.get(roleid).getCapacity());
	}

	public static long getGoldcoinDepot(long roleid) {
		return xtable.Goldcoindepot.get(roleid);
	}

	public static void syncDepotGoldcoin(long roleid) {
		Transaction.tsendWhileCommit(roleid, new SSyncGoldCoin(getGoldcoinDepot(roleid)));
	}

	public static boolean addGoldcoinDepot(long roleid, long num) {
		long goldcoin = getGoldcoinDepot(roleid);
		if(num > 0 && Long.MAX_VALUE - goldcoin < num){
			return false;
		}
		goldcoin += num;
		if (goldcoin < 0) {
			return false;
		}
		xtable.Goldcoindepot.getTable().put(roleid, goldcoin);
		return true;
	}

}
