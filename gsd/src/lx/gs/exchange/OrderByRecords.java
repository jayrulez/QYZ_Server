package lx.gs.exchange;

import lx.gs.exchange.msg.QueryResult;

import java.util.*;

public final class OrderByRecords {
	private final HashSet<Record> records;
	private final ArrayList<TreeSet<Record>> orderbys = new ArrayList<>();
	
	public OrderByRecords(Set<Record> records) {
		this.records = new HashSet<>(records);
		this.orderbys.add(new TreeSet<>(Record.cmpByLevel));
		this.orderbys.add(new TreeSet<>(Record.cmpByAprice));
		this.orderbys.add(new TreeSet<>(Record.cmpByTotalprice));
		for(TreeSet<Record> t : orderbys) {
			t.addAll(records);
		}
	}
	
	public OrderByRecords() {
		this(Collections.emptySet());
	}
	
	
	public void add(Record r) {
		if(records.add(r)) {
			for(TreeSet<Record> t : orderbys) {
				t.add(r);
			}
		}
	}
	
	public void remove(Record r) {
		if(records.remove(r)) {
			for(TreeSet<Record> t : orderbys) {
				t.remove(r);
			}
		}
	}
	
	public void genRecords(int orderByType, int sortOrder, int startIndex, int endIndex, QueryResult re) {
		NavigableSet<Record> rs = orderbys.get(orderByType);
		if(sortOrder == cfg.exchange.SortOrder.DESC) {
			rs = rs.descendingSet();
		}
		
		final int n = rs.size();
		re.totalnum = n;

		int start = Math.max(0, startIndex);
		int end = Math.min(endIndex, n - 1);
		if (start <= end) {
			int i = 0;
			for (Record r : rs) {
				if (i < start) {

				} else if (i <= end) {
					re.items.add(FExchange.convert(r.item));
				} else {
					break;
				}
				i++;
			}
		}

	}
}
