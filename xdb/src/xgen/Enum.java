package xgen;

import org.w3c.dom.*;

import java.io.*;

public class Enum {
	private String name;
	private String value;
	private String comment;

	public String getName() {
		return name;
	}

	public Enum(Element self) {
		name = self.getAttribute("name").trim();
		value = self.getAttribute("value").trim();
		Node c = self.getNextSibling();
		comment = self.getAttribute("comment").trim();
		if (comment.isEmpty()){
			if (c != null && Node.TEXT_NODE == c.getNodeType()) {
				comment = c.getTextContent().trim().replaceAll("[\r\n]", "");
			}
		}
	}

	public void print(PrintStream ps, String prefix) {
		ps.println(prefix + "public final static int " + name + " = " + value + "; // " + comment);
	}
}
