package lx.gs.rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public final class RankVector implements Cloneable {
	public final static class Record {
		public final long id;
		public int rank;
		public long value;
		
		public Record(long id, long value, int rank) {
			this.id = id;
			this.value = value;
			this.rank = rank;
		}

		public Record(Record o) {
			this.id = o.id;
			this.value = o.value;
			this.rank = o.rank;
		}
		
		@Override
		public String toString() {
			return String.format("{rank=%d,id=%d,value=%d}", rank, id, value);
		}
	}

	private final ArrayList<Record> records = new ArrayList<>();
	private final HashMap<Long, Record> recordMap = new HashMap<>();
	private final int capacity;
	private final boolean desc;

	public RankVector(int capacity, boolean desc) {
		this.capacity = capacity;
		this.desc = desc;
	}
	
	public RankVector(int capacity, List<Record> rs, boolean desc) {
		this.capacity = capacity;
		this.desc = desc;
		init(rs);
	}
	
	public void init(List<Record> rs) {
		int rank = 0;
		for(Record r : rs) {
			assert(r.rank == ++rank);
			final Record nr = new Record(r.id, r.value, r.rank);
			this.records.add(nr);
			this.recordMap.put(nr.id, nr);
		}
	}
	
	public int getSize() {
		return records.size();
	}
	
	public int getCapacity() {
		return capacity;
	}

	public ArrayList<Record> getRanks() {
		return records;
	}
	
	public Record getRank(long id) {
		return recordMap.get(id);
	}

	private final boolean less(long v1, long v2) {
		return desc ?  (v1 > v2) : (v1 < v2);
	}
	
	@Override
	public RankVector clone() {
		return new RankVector(this.capacity, this.records, desc);
	}

	public void update(long id, long value) {
		final Record r = recordMap.get(id);
		if (r != null) {
			int cur = r.rank - 1;
			Record cr;
			if (less(r.value, value)) {
				for (; cur > 0 && less((cr = records.get(cur - 1)).value, value); cur--) {
					cr.rank++;
					records.set(cur, cr);
				}
			} else {
				for (int maxrank = records.size() - 1 ; cur < maxrank && less(value, (cr = records.get(cur + 1)).value) ; cur++) {
					cr.rank--;
					records.set(cur, cr);
				}
			}
			r.value = value;
			r.rank = cur + 1;
			records.set(cur, r);
		} else {
			final int count = records.size();
			if(count < capacity) {
				records.add(new Record(id, value, count));
			} else {
				final Record last = records.get(count - 1);
				if(less(value, last.value))
					return;
				recordMap.remove(last.id);
				records.set(count - 1, new Record(id, value, count));
			}
			
			int cur = records.size() - 1;
			final Record nr = records.get(cur);
			for (Record cr ; cur > 0 && less((cr = records.get(cur - 1)).value, value) ; cur--) {
				cr.rank++;
				records.set(cur, cr);
			}
			nr.rank = cur + 1;
			records.set(cur, nr);
			recordMap.put(id, nr);
		}
	}

	public void swap(long id1, long id2) {
		Record r1 = recordMap.get(id1);
		Record r2 = recordMap.get(id2);
		if(r1 != null) {
			assert(r1.value == r1.rank);
			if(r2 != null) {
				assert(r2.value == r2.rank);
				final Record nr1 = new Record(r1.id, r2.value, r2.rank);
				final Record nr2 = new Record(r2.id, r1.value, r1.rank);
				records.set(nr1.rank - 1, nr1);
				recordMap.put(id1, nr1);
				records.set(nr2.rank - 1, nr2);
				recordMap.put(id2, nr2);
			} else {
				r2 = new Record(id2, r1.value, r1.rank);
				recordMap.remove(id1);
				recordMap.put(id2, r2);
				records.set(r2.rank - 1, r2);
			}
		} else {
			if(r2 != null) {
				assert(r2.value == r2.rank);
				r1 = new Record(id1, r2.value, r2.rank);
				recordMap.remove(id2);
				recordMap.put(id1, r1);
				records.set(r1.rank - 1, r1);
			}
		}
	}

	public void ajustRankByPrevRank(RankVector prevData) {
		int maxRank = records.size();
		for (int k = 1; k <= maxRank; k++) {
			final int firstRank = k;
			final long value = records.get(k - 1).value;

			while (k < maxRank && records.get(k).value == value)
				k++;

			final int lastRank = k;
			// 依据prevData表对相同value的的id排序
			// 规则 为 在prevData中的id排在不在的之前
			// 如果都在,则rank小的排在大的之前
			for (int i = firstRank; i < lastRank; i++) {
				for (int j = i + 1; j <= lastRank; j++) {
					final Record mi = records.get(i - 1);
					final Record mj = records.get(j - 1);
					final Record ri = prevData.recordMap.get(mi.id);
					final Record rj = prevData.recordMap.get(mj.id);
					boolean swap = false;
					if (ri == null) {
						swap = rj != null;// || mi.id > mj.id;
					} else {
						swap = rj != null && ri.rank > rj.rank;
					}
					if (swap) {
						mj.rank = i;
						mi.rank = j;
						records.set(i - 1, mj);
						records.set(j - 1, mi);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Random r = new Random(1218);
		RankVector v = new RankVector(20, false);
		
		for(int i = 0 ; i < 100 ; i++) {
			//v.update(i, r.nextInt(10000));
			v.update(i, 100-i);
		}
		System.out.println(v.getRanks());
		v.swap(1, 4);
		System.out.println(v.getRanks());
		v.swap(2, 19);
		System.out.println(v.getRanks());
		v.swap(3, 20);
		System.out.println(v.getRanks());
		v.swap(19, 20);
		System.out.println(v.getRanks());
		v.swap(30, 20);
		System.out.println(v.getRanks());
		v.swap(40, 50);
		System.out.println(v.getRanks());
	}

}
