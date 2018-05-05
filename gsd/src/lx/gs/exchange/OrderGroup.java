package lx.gs.exchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lx.gs.exchange.msg.QueryResult;

public final class OrderGroup {
	private final HashSet<Record> records;
	private final ArrayList<Record[]> orderbys = new ArrayList<>();
	
	public OrderGroup(Set<Record> records) {
		this.records = new HashSet<>(records);
		for(int i = 0 ; i < cfg.exchange.OrderByType.TYPE_NUM ; i++)
			this.orderbys.add(null);
	}
	
	public OrderGroup() {
		this(Collections.emptySet());
	}
	
	public void add(Record r) {
		if(records.add(r))
			invalid();
	}
	
	public void remove(Record r) {
		if(records.remove(r))
			invalid();
	}
	
	private void invalid() {
		for(int i = 0 ; i < cfg.exchange.OrderByType.TYPE_NUM ; i++)
			this.orderbys.set(i, null);
	}
	
	private Record[] getOrCreate(int orderByType) {
		Record[] rs = orderbys.get(orderByType);
		if(rs == null) {
			rs = genOrderBy(orderByType);
			orderbys.set(orderByType, rs);
		}
		return rs;
	}
	
	private Record[] genOrderBy(int orderByType) {
		assert(orderByType >= 0 && orderByType < cfg.exchange.OrderByType.TYPE_NUM);

		final Record[] rs = records.toArray(new Record[records.size()]);
		switch(orderByType) {
		case cfg.exchange.OrderByType.LEVEL: Arrays.sort(rs, Record.cmpByLevel); break;
		case cfg.exchange.OrderByType.APRICE: Arrays.sort(rs, Record.cmpByAprice); break;
		case cfg.exchange.OrderByType.TOTAL_PRICE : Arrays.sort(rs, Record.cmpByTotalprice); break;
		default :
			throw new RuntimeException("unknown orderByType:" + orderByType);
		}
		return rs;
	}
	
	public void genRecords(int orderByType, int sortOrder, int startindex, int endindex, QueryResult re) {
		final Record[] rs = getOrCreate(orderByType);
		final int n = rs.length;
		re.totalnum = n;
		if(sortOrder == cfg.exchange.SortOrder.ASC) {
			for(int i = startindex ; i <= Math.min(endindex, n-1) ; i++) {
				re.items.add(FExchange.convert(rs[i].item));
			}
		} else {
			for(int i = n - startindex - 1 ; i >= n - endindex - 1 ; i--) {
				re.items.add(FExchange.convert(rs[i].item));
			}
		}
		
	}
	
	
}
