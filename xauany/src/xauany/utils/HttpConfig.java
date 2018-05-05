package xauany.utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HttpConfig {
	public int 	connecttimeout = 10000;
	public int 	readtimeout = 5000;
	
	public final void findConfig( Element ele) {
		final NodeList subs = ele.getChildNodes(); 
		for( int i = 0; i < subs.getLength(); i ++ ) {
			final Node node = subs.item( i);
			if( Node.ELEMENT_NODE != node.getNodeType())
				continue;
			final Element subele = (Element)node;
			if( 0 != subele.getNodeName().compareToIgnoreCase( "httpconfig"))
				continue;
			connecttimeout = Integer.valueOf( subele.getAttribute( "connecttimeout"));
			readtimeout = Integer.valueOf( subele.getAttribute( "readtimeout"));
			return;
		}
	}

	@Override
	public String toString() {
		return "( connecttimeout = " + connecttimeout + " readtimeout = " + readtimeout + " )";
	}
}

