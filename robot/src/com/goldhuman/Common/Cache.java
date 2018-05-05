
package com.goldhuman.Common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Timer;
import java.util.TreeMap;

public class Cache
{
	public static int default_size = 100;
	public static int default_timeout = 10;
	private Map<Item,Item>   cache = new HashMap<Item,Item>();
	private LRU   lru   = new LRU();
	private int   nitem;
	private int   maxsize = default_size;
	private int   time_stamp = 0;
	private int   life_time  = default_timeout;
	private int[] key_pos;
	private static Map<String,Cache> all_caches = Collections.synchronizedMap(new HashMap<String,Cache>());

	static
	{
		new Timer(true).schedule
		(
			new java.util.TimerTask ()
			{
				public void run()
				{
					synchronized (all_caches)
					{
						for (Iterator<Map.Entry<String,Cache>> i1 = all_caches.entrySet().iterator(); i1.hasNext(); )
						{
							Cache c = (Cache)((Map.Entry<String,Cache>)i1.next()).getValue();
							synchronized(c)
							{
								for (Iterator<Map.Entry<Item,Item>> i2 = c.cache.entrySet().iterator(); i2.hasNext(); )
								{
									Item item = (Item)((Map.Entry<Item,Item>)i2.next()).getValue();
									if ( -- item.life_time <= 0 )
									{
										item.revoke();
										i2.remove();
										c.lru.remove(item);
									}
								}
							}
						}
					}
				}
			},
			0,
			1000
		);
	}

	private class LRU
	{
		private TreeMap<Integer,LinkedList<Item>> lru = new TreeMap<Integer,LinkedList<Item>>();

		public void add(Item item)
		{
			Integer ac = new Integer(item.access_count);
			LinkedList<Item> ll = (LinkedList<Item>)lru.get(ac);
			if ( ll == null )
				lru.put(ac, ll = new LinkedList<Item>());
			ll.addLast(item);
		}

		public void remove(Item item)
		{
			Integer ac = new Integer(item.access_count);
			LinkedList<Item> ll = (LinkedList<Item>)lru.get(ac);
			ll.remove(item);
			if ( ll.size() == 0 )
				lru.remove(ac);
		}

		public Item last()
		{
			return (Item)((LinkedList<Item>)lru.get(lru.firstKey())).getFirst();
		}

		public void access(Item item)
		{
			remove(item);
			item.access_count++;
			add(item);
		}
	}

	public class Item implements Cloneable
	{
		private final static int CLEAN = 0;
		private final static int DIRTY = 1;
		private Item origin;
		private int time_stamp;
		private int life_time;
		private int access_count = 0;
		private int status;
		private Object[] items;
		private Cache owner;

		protected Object clone()
		{
			try
			{
				Item item   = (Item)super.clone();
				item.items  = new Object[items.length];
				System.arraycopy(items, 0, item.items, 0, items.length);
				item.origin = this;
				return item;
			}
			catch (Exception e)
			{
			}
			return null;
		}

		private Item(Cache owner)
		{
			this.owner = owner;
			items = new Object[owner.nitem];
			status = DIRTY;
			time_stamp = owner.time_stamp ++;
		}

		private void revoke()
		{
		}

		public boolean equals(Object obj)
		{
			for (int i = 0; i < owner.key_pos.length; i++)
				if ( ! items[key_pos[i]].equals(((Item)obj).items[key_pos[i]]) )
					return false;
			return true;
		}

		public int hashCode()
		{
			int hash = 0;
			for (int i = 0; i < owner.key_pos.length; i++)
				hash = ( hash + items[key_pos[i]].hashCode() * 17 ) >> 4;
			return hash;
		}

		public void commit() throws RuntimeException
		{
			if ( status == CLEAN )
				return;

			synchronized (owner)
			{
				if ( origin == null )
				{
					if ( owner.contains(this) )
						throw new RuntimeException("Duplicate Key");
				}
				else
				{
					if ( origin.time_stamp != time_stamp )
						throw new RuntimeException ("TimeStamp Collision");
	
					if ( hashCode() != origin.hashCode() || ! equals(origin) )
						owner.remove(origin);
	
					time_stamp = origin.time_stamp = owner.time_stamp ++;
					origin = null;
				}
				status = CLEAN;
				owner.add(this);
			}	
		}

		public Item set(int pos, Object obj)
		{
			items[pos] = obj;
			status = DIRTY;
			return this;
		}

		public Object get(int pos) { return items[pos]; }
	}

	private void add(Item item)
	{
		item.life_time = life_time;
		if ( cache.size() == maxsize )
			remove(lru.last());
		cache.put(item, item);
		lru.add(item);
	}

	private void remove(Item item)
	{
		item.revoke();
		cache.remove(item);
		lru.remove(item);
	}

	private boolean contains(Item item) { return cache.containsKey(item); }

	private Cache(int nitem, int[] key_pos)
	{
		this.nitem   = nitem;
		this.key_pos = key_pos;
	}

	private Cache(int nitem, int[] key_pos, int maxsize, int life_time)
	{
		this.nitem   = nitem;
		this.key_pos = key_pos;
		this.maxsize = maxsize;
		this.life_time = life_time;
	}

	public static Cache Create(String name, int nitem, int[] key_pos)
	{
		Cache c = new Cache(nitem, key_pos);
		all_caches.put(name, c);
		return c;
	}

	public static Cache Create(String name, int nitem, int[] key_pos, int maxsize, int life_time)
	{
		Cache c = new Cache(nitem, key_pos, maxsize, life_time);
		all_caches.put(name, c);
		return c;
	}

	public static Cache getInstance(String name)
	{
		return (Cache)all_caches.get(name);
	}

	public synchronized int size() { return cache.size(); }

	public synchronized Item find(Item key_item)
	{
		Item item = (Item)cache.get(key_item);
		if ( item == null )
			return null;
		lru.access(item);
		return (Item)item.clone();
	}

	public Item newItem() { return new Item(this); }

	public static void main(String []args)
	{
		Cache cache = Cache.Create("c1", 2, new int[] {0} );

		try
		{
			cache.newItem().set(0, new Integer(1)).set(1, new String("a")).commit();
			cache.newItem().set(0, new Integer(2)).set(1, new String("b")).commit();
			cache.newItem().set(0, new Integer(3)).set(1, new String("c")).commit();
	//		Cache.Item item1 = cache.find(cache.newItem().set(0, new Integer(1)));
			cache.newItem().set(0, new Integer(4)).set(1, new String("d")).commit();
	//		Cache.Item item2 = cache.find(cache.newItem().set(0, new Integer(3)));
			cache.newItem().set(0, new Integer(5)).set(1, new String("e")).commit();

/*
			Cache.Item item1 = cache.find(cache.newItem().set(0, new Integer(1)));
			Cache.Item item2 = cache.find(cache.newItem().set(0, new Integer(1)));

			item1.set(1, new String("b"));
			item1.commit();

			//item2.set(1, new String("c"));
			//item2.commit();

			Cache.Item item = cache.find(cache.newItem().set(0, new Integer(1)));
			System.out.println((String)item.get(1));
*/
			System.out.println("Size = " + cache.size());
			Thread.sleep(1000);
			System.out.println("Size = " + cache.size());
			Thread.sleep(1000);
			System.out.println("Size = " + cache.size());
			Thread.sleep(1000);
			System.out.println("Size = " + cache.size());
			Thread.sleep(1000);
			System.out.println("Size = " + cache.size());
			Thread.sleep(1000);
			System.out.println("Size = " + cache.size());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

