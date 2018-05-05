package xgen;

import java.io.File;
import java.io.PrintStream;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main {
	private static Xdb xdb;
	public static String outputEncoding = "GBK";
	private static boolean nowarn = false;
	private static String warn = "cfo";
	static boolean noverify = false;
	
	// 检查显示获得锁
	static boolean explicitLockCheck = false;
	static Set<String> explicitLockIgnoreTables = new TreeSet<String>();
	
	//--add for tranform. caijiacheng
	private static boolean transform = false;
	private static String srcdb = null;
	private static String destdb = null;
	//--add for transform check. caijiacheng 2010.08.24
	private static boolean transformCheck = false;
	
	private static void usage() {
		System.out.println("Usage: java -jar xgen.jar [options] xdb.xml");
		System.out.println("	-outputEncoding  encoding.");
		System.out.println("	-output          output dir.");
		System.out.println("	-nowarn          do not print warning message.");
		System.out.println("	-warn [cfo]      select warning message. c=capacity, f=foreign, o=owner.");
		System.out.println("	-noverify        do not generate xbean verify code.");
		System.out.println("	-transform       for Tranform db");
		System.out.println("	-srcdb           tranform src db Dir. ");
		System.out.println("	-destdb          tranform dest db Dir. ");
		System.out.println("	-transformCheck  for check tranform is need to transform or not");
		System.out.println("	-explicitLockCheck [tale1:table2]  生成检查显示获得锁的代码, ");
		System.out.println("	                 其中：[]中冒号分隔的是忽略explicitLockCheck的表");
		System.out.println("	                       检查包括{add(fail),get,delete,remove} 不包括{add(success),insert,select}");
		Runtime.getRuntime().exit(1);
	}

	public static void _xdb_verify_(PrintStream ps, String prefix) {
		if (!noverify)
			ps.println(prefix + "_xdb_verify_unsafe_();");
	}

	public static void main(String args[]) throws Exception {
		System.setProperty("line.separator", "\n");
		String xdbxml = null;
		String xgenOutput = null;

		for (int i = 0; i < args.length; ++i) {
			if      (args[i].equals("-outputEncoding")) outputEncoding = args[++i];
			else if (args[i].equals("-output"))         xgenOutput = args[++i];
			else if (args[i].equals("-nowarn"))         nowarn = true;
			else if (args[i].equals("-warn")) {
				if ((i + 1 < args.length) && !args[i + 1].startsWith("-"))
					warn = args[++i].toLowerCase();
				else
					warn = ""; // select nothing
			}
			else if (args[i].equals("-noverify"))       noverify = true;
			else if (args[i].equals("-transform"))      transform = true;
			else if (args[i].equals("-transformCheck")) transformCheck = true;
			else if (args[i].equals("-srcdb"))       	srcdb = args[++i];
			else if (args[i].equals("-destdb"))       	destdb = args[++i];
			else if (args[i].equals("-explicitLockCheck")) {
				explicitLockCheck = true;
				if ((i + 1 < args.length) && args[i + 1].startsWith("[")) {
					++i;
					if (!args[i].startsWith("[") || !args[i].endsWith("]"))
						usage();
					String[] ignoreTables = args[i].substring(1, args[i].length() - 1).split(":");
					for (String ignoreTable : ignoreTables) {
						if (ignoreTable.equals("")) continue;
						explicitLockIgnoreTables.add(ignoreTable);
					}
				}
			}
			else if (xdbxml == null)
				xdbxml = args[i];
			else
				usage();
		}

		if (null == xdbxml)
			usage();
		
		if ( transform == true )
		{
			if ( srcdb == null )
			{
				System.out.println("-srcdb need to been set!");
				usage();
			}
			if ( destdb == null )
			{
				System.out.println("-destdb need to been set!");
				usage();
			}
		}

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xdbxml);
		Element root = doc.getDocumentElement();
		if (null != xgenOutput)
			root.setAttribute("xgenOutput", xgenOutput);
		xdb = new Xdb(root);
		xdb.compile();
		if ( !transform && !transformCheck)
		{
			xdb.make();
		}
		else
		{
			//xdb.diffMetaData();
			xdb.transformMake();
		}			
		xdb.clean();
	}

	static void warn(String msg, char type) {
		if (! nowarn && warn.indexOf(type) > -1)
			System.err.println("WARN " + msg);
	}

	static PrintStream openBeanFile(String classname) {
		File dir = xdb.getXbeandir();
		return fopen(dir, classname);
	}

	static PrintStream openBean__File(String classname) {
		File dir = xdb.getXbeandir__();
		return fopen(dir, classname);
	}

	static PrintStream openTableFile(String classname) {
		File dir = xdb.getXtabledir();
		return fopen(dir, classname);
	}
	
	static PrintStream fopen(File dir, String classname) {
		try {
			File file = new File(dir, classname + ".java");
			//System.out.println(file);
			return new PrintStream(new CachedFileOutputStream(file), false, outputEncoding);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String toUpper1(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static String quote(String s) {
		return "\"" + s + "\"";
	}

	public static void verifyName(String name) {
		if (name.startsWith("_") && name.endsWith("_"))
			throw new RuntimeException("Invalid Name of '" + name + "'. the name like '_*_' is reserved");
	}
	//add by caijiacheng
	static PrintStream openTransformMainFile(String classname) {
		File dir = xdb.getXgenOutputdir();
		return fopen(dir, classname);
	}
	
	static String getTransformSrcDb()
	{
		return srcdb;
	}
	
	static String getTransformDestDb()
	{
		return destdb;
	}
	//add by caijiacheng 10.08.24
	static void doTransformCheck(boolean isNeedTransform)
	{
		final int NeedTransform = 100;
		final int NotNeedTransform = 101;
		if (transformCheck)
		{
			if (isNeedTransform)
			{
				System.exit(NeedTransform);//暂时返回1代表需要transform
			}
			else
			{
				System.exit(NotNeedTransform);
			}
		}
	}
	

	// 在构建owner的过程中检查并警告。see Type.addOwnerTable Xdb.compile
//	public static void checkAddTypeOwnerTable(Type type, Set<Table> owners, Table newOwner) {
//		/**
//		 * 还没有任何 owner。
//		 */
//		if (owners.isEmpty())
//			return;
//
//		/**
//		 * 数据结构定义时，允许多次包含同一个数据结构.
//		 * 再次加入同一个owner是允许的。
//		 */
//		if (owners.contains(newOwner))
//			return;
//
//		/**
//		 * 只报告自定自定义结构，忽略基本类型的警告，因为：
//		 * 1 可以直接被 table 使用的基本类型允许多个拥有者，不报告检查结果。如，int，long，等。
//		 * 2 不允许 table 直接使用的基本类型，肯定包含在自定义结构中，不重复报告。如，set,list，等。
//		 */
//		if (!(type instanceof CBean) && !(type instanceof XBean))
//			return;
//
//		warn("OWNER bean=" + Main.quote(type.getName()) + " current=" + owners
//				+ " add=" + Main.quote(newOwner.getName()));
//	}
}
