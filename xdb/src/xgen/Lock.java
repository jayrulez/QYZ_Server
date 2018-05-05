package xgen;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Lock {

	static void make(Collection<Table> tables) {
		String classname = "Locks";
		PrintStream ps = Main.openTableFile(classname);

		ps.println("package xtable;");
		ps.println();		
		//ps.println("import xdb.TTable;");
		//ps.println();
		ps.println("public final class Locks {");
		ps.println();
		
		TreeMap<String, ArrayList<String>> locks = new TreeMap<String, ArrayList<String>>();		
		for (Table table : tables) {
			String lock = table.getLock();
			ArrayList<String> a = locks.get(lock);
			if (a == null) {
				a = new ArrayList<String>();
				locks.put(lock, a);
			}
			a.add(table.getName());			
		}
		
		for (Map.Entry<String, ArrayList<String>> e : locks.entrySet()) {
			ArrayList<String> tbl = e.getValue();
			Collections.sort(tbl);
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < tbl.size(); ++i)
				sb.append(tbl.get(i)).append("  ");
			ps.printf("	public static final xdb.TTable<?,?> %s = _Tables_.getInstance().%s; // %s",
					e.getKey().toUpperCase(), tbl.get(0), sb.toString()).println();
		}
		
		ps.println();
		ps.println("}");

		ps.close();
	}
	
}
