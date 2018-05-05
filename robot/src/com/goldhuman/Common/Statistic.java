
package com.goldhuman.Common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class Statistic
{
	public interface StatCallBack
	{
		public boolean enumerate( String __name, Statistic __stat );
	}

	// stat data
	public int m_max;
	public int m_min;
	public int m_cur;
	public int m_cnt;
	public int m_sum;

	private static HashMap<String,Statistic> m_map = new HashMap<String,Statistic>();

	private Statistic()
	{
		reset();
	}

	public void reset( )
	{
		m_cur = 0;
		m_cnt = 0;
		m_sum = 0;
		m_max = 0;
		m_min = 0;
	}

	public void update( int __delta )
	{
		m_cur = __delta;
		m_cnt ++;
		m_sum += __delta;
		m_max = __delta > m_max ? __delta : m_max;
		m_min = (0 == m_min ? __delta : (__delta<m_min?__delta:m_min));
	}

	public static Statistic GetInstance( String __name )
	{
		synchronized(m_map)
		{
			Statistic p = (Statistic)m_map.get(__name);
			if (p != null)
				return p;
			p = new Statistic();
			m_map.put( __name, p );
			return p;
		}
	}

	public static boolean enumdefault( String __name, Statistic __stat )
	{
		System.out.println( __name );
		System.out.print( " MAX: " + __stat.m_max );
		System.out.print( " MIN: " + __stat.m_min );
		System.out.print( " CUR: " + __stat.m_cur );
		System.out.print( " CNT: " + __stat.m_cnt );
		System.out.println( " SUM: " + __stat.m_sum );
		return true;
	}

	public static void enumerate( StatCallBack cb )
	{
		synchronized(m_map)
		{
			for (Iterator<Map.Entry<String,Statistic>> it = m_map.entrySet().iterator(); it.hasNext(); )
			{
				Map.Entry<String,Statistic> entry = (Map.Entry<String,Statistic>)it.next();
				String name = (String)entry.getKey();
				Statistic stat = (Statistic)entry.getValue();
				cb.enumerate( name, stat );
			}
		}
	}

	public synchronized static void resetall( )
	{
		synchronized(m_map)
		{
			for (Iterator<Map.Entry<String,Statistic>> it = m_map.entrySet().iterator(); it.hasNext(); )
			{
				Statistic p = (Statistic)(((Map.Entry<String,Statistic>)it.next()).getValue());
				p.reset();
			}
		}
	}

}
