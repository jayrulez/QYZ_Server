package xgen;

import java.util.HashMap;
import java.util.HashSet;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Define
{
	private static Define				ms_define		= new Define();
	private HashSet<String>				m_locknameset	= new HashSet<String>();
	private HashMap<String, Integer>	m_cachecapmap	= new HashMap<String, Integer>();
	private HashMap<String, Integer[]>	m_cachepagemap	= new HashMap<String, Integer[]>();
	private boolean						m_parsed		= false;

	public static Define getInstance()
	{
		return ms_define;
	}

	public void parse(Element elem)
	{
		m_locknameset.clear();
		m_cachecapmap.clear();
		m_cachepagemap.clear();
		if(elem == null)
		{
			m_parsed = false;
			return;
		}
		m_parsed = true;

		NodeList nl = elem.getChildNodes();
		for(int i = 0; i < nl.getLength(); ++i)
		{
			try
			{
				Node node = nl.item(i);
				if(node.getNodeType() != Node.ELEMENT_NODE)
					continue;
				Element subelem = (Element)node;
				String elemname = subelem.getNodeName();
				if(elemname.equals("lock"))
				{
					String name = subelem.getAttribute("name");
					if(name == null || name.equals(""))
						continue;
					m_locknameset.add(name);
				}
				else if(elemname.equals("cachecap"))
				{
					String name = subelem.getAttribute("name");
					if(name == null || name.equals(""))
						continue;
					String value = subelem.getAttribute("value");
					if(value == null)
						continue;
					m_cachecapmap.put(name, Integer.parseInt(value));
				}
				else if(elemname.equals("cachepage"))
				{
					String name = subelem.getAttribute("name");
					if(name == null || name.equals(""))
						continue;
					String low = subelem.getAttribute("low");
					if(low == null)
						continue;
					String high = subelem.getAttribute("high");
					if(high == null)
						continue;
					m_cachepagemap.put(name, new Integer[] { Integer.parseInt(low), Integer.parseInt(high) });
				}
			}
			catch(NumberFormatException e)
			{
			}
		}
	}

	public boolean isParsed()
	{
		return m_parsed;
	}

	public boolean isLockname(String name)
	{
		return m_locknameset.contains(name);
	}

	public Integer getCacheCap(String name)
	{
		return m_cachecapmap.get(name);
	}

	public Integer[] getCachePage(String name)
	{
		return m_cachepagemap.get(name);
	}
}
