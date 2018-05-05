package lx.gs.exchange;

import cfg.CfgMgr;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public final class Record {
	private final static AtomicInteger nextId = new AtomicInteger();
	public final int id;
	public final int aprice;
	public final int totalprice;
	public int level;
	public final xbean.ExchangeItem item;
	
	public Record(xbean.ExchangeItem item) {
		this.id = nextId.incrementAndGet();
		this.aprice = item.getPrice();
		this.totalprice = item.getPrice() * item.getNum();
		this.item = item;
		init(item.getModelid());
	}
	
	private void init(int itemid) {
		cfg.item.ItemBasic c = CfgMgr.itembasic.get(itemid);
		if(c != null) {
			level = c.level;
			return;
		}
		cfg.equip.Fragment f = CfgMgr.fragment.get(itemid);
		if(f != null) {
			level = f.level;
			return;
		}
		cfg.equip.Equip e  = CfgMgr.equip.get(itemid);
		if(e != null) {
			level = e.level;
			return;
		}
		if(CfgMgr.talismanbasic.containsKey(itemid)){
			level = CfgMgr.talismanbasic.get(itemid).quality;
			return;
		}
		throw new RuntimeException("unknow itemid:" + itemid);
	}

	public final static Comparator<Record> cmpByLevel = new Comparator<Record>() {
		@Override
		public int compare(Record a, Record b) {
			final int x = a.level - b.level;
			return x != 0 ? x : a.id - b.id;
		}
	};
	
	public final static Comparator<Record> cmpByAprice = new Comparator<Record>() {
		@Override
		public int compare(Record a, Record b) {
			final int x = a.aprice - b.aprice;
			return x != 0 ? x : a.id - b.id;
		}
	};
	
	public final static Comparator<Record> cmpByTotalprice = new Comparator<Record>() {
		@Override
		public int compare(Record a, Record b) {
			final int x = a.totalprice - b.totalprice;
			return x != 0 ? x : a.id - b.id;
		}
	};

}
