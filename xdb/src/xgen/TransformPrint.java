/**
 * 
 */
package xgen;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cai JiaCheng
 *
 */
public class TransformPrint {
	private List<String> _transformtable = new ArrayList<String>();

	public void printMain()
	{

		PrintStream ps = Main.openTransformMainFile("Main");

		ps.println("import java.io.File;");

		ps.println("import java.io.FileInputStream;");
		ps.println("import java.io.FileNotFoundException;");
		ps.println("import java.io.FileOutputStream;");
		ps.println("import java.io.IOException;");
		ps.println("import java.io.InputStream;");
		ps.println("import java.io.OutputStream;");
		ps.println("import java.util.ArrayList;");
		ps.println("import java.util.List;");

		ps.println("import com.goldhuman.Common.Octets;");
		ps.println("import com.goldhuman.Common.Marshal.OctetsStream;");

		ps.println("import xdb.util.DatabaseMetaData;");
		ps.println("import xdb.util.Dbx;");


		ps.println();

		ps.println("public class Main {");

		ps.println("	public static void main(String[] args) {");
		ps.println("	String libpath = \".\";");
		ps.println("	String srcdir = \""+ Main.getTransformSrcDb() + "\";");
		ps.println("	String destdir = \""+ Main.getTransformDestDb() + "\";");		
		
		ps.println("	//这里不提供 usage()，各个步骤的关联性很强，增加灵活性就会增加出错的机率，用的人自己把握");
		ps.println("	for (int i = 0; i < args.length; ++i) ");
		ps.println("	{");
		ps.println("		if (args[i].equals(\"-srcdb\"))	");
		ps.println("	{");
		ps.println("			srcdir = args[++i];	");
		ps.println("			System.out.println(\"WARN: USE NEW SRCDB:\" + srcdir);	");
		ps.println("	}	");
		ps.println("		else if (args[i].equals(\"-destdb\"))	");
		ps.println("	{");
		ps.println("			destdir = args[++i];	");
		ps.println("			System.out.println(\"WARN: USE NEW DSTDB:\" + destdir);	");
		ps.println("	}	");
		ps.println("		else	");
		ps.println("			libpath = args[i];	");		
		ps.println("	}	");
		
		ps.println("	List<String> dotable = new ArrayList<String>();");

		ps.println("	{");
		for( String tablename : _transformtable )
		{
			ps.println("		dotable.add(\""+tablename+"\");");
		}
		
		ps.println("	}");

		

		ps.println("	File src = new File(srcdir);");
		ps.println("	File dst = new File(destdir);");
		ps.println("	deleteDir(dst);");

		ps.println("	xdb.util.Dbx.start(libpath);");

		ps.println("	Dbx sdb = Dbx.open(src, DatabaseMetaData.getInstance());");
		ps.println("	Dbx ddb = Dbx.open(dst, DatabaseMetaData.getInstance());");
		ps.println("	try{");
		ps.println("		for (DatabaseMetaData.Table tableMetaData : sdb.getMetaData().getTables()) {");

		ps.println("			String tablename = tableMetaData.getName();");
		ps.println("			Dbx.Table stb = sdb.openTable(tablename);");
		ps.println("			Dbx.Table dtb = ddb.openTable(tablename);");

		ps.println("			if( dotable.contains(tablename) )");
		//ps.println("			if( true )//xdb暂时无法直接打开直接copy过去的不带log信息的数据库，这里会产生效率瓶颈");
		ps.println("			{");
		ps.println("				try{");
		ps.println("					System.out.println(\"Begin to transform db: \" + tablename);");
		ps.println("					Walker walker = new Walker(dtb);");
		ps.println("					stb.walk(walker); ");
		ps.println("					walker.finish();");
		ps.println("					walker = null;");
		ps.println("				}finally");
		ps.println("				{");
		ps.println("				stb.close();");
		ps.println("				dtb.close();");
		ps.println("				}");
		ps.println("			}");
		ps.println("			else");
		ps.println("			{");
		ps.println("				try{");
		ps.println("					System.out.println(\"Begin to copy db: \" + tablename);");
		ps.println("					WalkerRaw walker = new WalkerRaw(dtb);");
		ps.println("					stb._walk(walker);"); 
		ps.println("					walker.finish();");
		ps.println("					walker = null;");
		ps.println("					}finally");
		ps.println("					{");
		ps.println("						stb.close();");
		ps.println("						dtb.close();");
		ps.println("					}");
		ps.println("				//stb.close();");
		ps.println("				//dtb.close();");
		
		ps.println("				//System.out.println(\"Begin to copy db: \" + tablename);");
		ps.println("				//try {");
		ps.println("				//	Main.copyFile(new File ( srcdir + \"/dbdata/\" + tablename), new File (destdir + \"/dbdata/\") );");
		ps.println("				//} catch (Exception e) {");
		ps.println("				//e.printStackTrace();}");
		ps.println("				}");
		ps.println("			}");
		
//		ps.println("			//copy the _sys_");
//		ps.println("			String tablename = \"_sys_\";");		
//		ps.println("			System.out.println(\"Begin to copy db: \" + tablename);");
//		ps.println("			try {");
//		ps.println("				Main.copyFile(new File ( srcdir + \"/dbdata/\" + tablename), new File (destdir + \"/dbdata/\") );");
//		ps.println("			} catch (Exception e) {");
//		ps.println("				e.printStackTrace();}");
		ps.println("");
		ps.println("	}finally");
		ps.println("	{");
		ps.println("		sdb.close();");
		ps.println("		ddb.close();");
		ps.println("	}");

		ps.println("	xdb.util.Dbx.stop();");

		ps.println("}");

		ps.println("	static class Walker implements xdb.Storage.IWalk {");

		ps.println("		private Dbx.Table _dtb = null;");
		ps.println("		private int _cnt = 0;");
		ps.println("		final int _checkpoint = 2000;");


		ps.println("		public Walker(Dbx.Table dtb) {");
		ps.println("			_dtb = dtb;");
		ps.println("		}");


		ps.println("		public boolean onRecord(byte[] _key, byte[] _data) {");
		ps.println("			_cnt++;");
		ps.println("			if ( _cnt >= _checkpoint )//保存一下修改，释放一下内存");
		ps.println("			{");
		ps.println("				_cnt = 0;");
		ps.println("				_dtb.save();");
		ps.println("			}");

		ps.println("			OctetsStream key = _dtb.getMetaData().getKeyType().marshal(");
		ps.println("				_dtb.getMetaData().getKeyType().unmarshal(_key));");
		//ps.println("			OctetsStream key = OctetsStream.wrap(Octets.wrap(_key));");	
		ps.println("			OctetsStream value = _dtb.getMetaData().getValueType().marshal(");
		ps.println("				_dtb.getMetaData().getValueType().unmarshal(_data));");

		ps.println("			if ( _dtb.insert(key, value ) == false )");				
		ps.println("				throw new Error(\"onRecord : insert key to etb false???on panic……\");");
		ps.println("			return true;");
		ps.println("		}");

		ps.println("		public void finish() {");

		ps.println("			_dtb = null;");

		ps.println("		}");
		ps.println("}");

		
		ps.println("	static class WalkerRaw implements xdb.Storage.IWalk {");

		ps.println("		private Dbx.Table _dtb = null;");
		ps.println("		private int _cnt = 0;");
		ps.println("		final int _checkpoint = 2000;");


		ps.println("		public WalkerRaw(Dbx.Table dtb) {");
		ps.println("			_dtb = dtb;");
		ps.println("		}");


		ps.println("		public boolean onRecord(byte[] _key, byte[] _data) {");
		ps.println("			_cnt++;");
		ps.println("			if ( _cnt >= _checkpoint )//保存一下修改，释放一下内存");
		ps.println("			{");
		ps.println("				_cnt = 0;");
		ps.println("				_dtb.save();");
		ps.println("			}");
		ps.println("			OctetsStream key = OctetsStream.wrap(Octets.wrap(_key));");
		ps.println("			OctetsStream value = OctetsStream.wrap(Octets.wrap(_data));");
		
		ps.println("			if ( _dtb._insert(key, value ) == false )");				
		ps.println("				throw new Error(\"onRecord : insert key to etb false???on panic……\");");
		ps.println("			return true;");
		ps.println("		}");

		ps.println("		public void finish() {");

		ps.println("			_dtb = null;");

		ps.println("		}");
		ps.println("}");
		
		ps.println("	private static void deleteDir(File dir) {"); 
		ps.println("		if (dir == null || !dir.exists() || !dir.isDirectory())"); 
		ps.println("			return; // 检查参数"); 
		ps.println("		for (File file : dir.listFiles()) { ");
		ps.println("			if (file.isFile()) ");
		ps.println("				file.delete(); // 删除所有文件 ");
		ps.println("			else if (file.isDirectory()) ");
		ps.println("				deleteDir(file); // 递规的方式删除文件夹"); 
		ps.println("		} ");
		ps.println("	}"); 

		ps.println("	private static void copyFile(File src, File destDir) throws FileNotFoundException, IOException {");
		ps.println("		InputStream is = new FileInputStream(src);");
		ps.println("		try {");
		ps.println("			OutputStream os = new FileOutputStream(new File(destDir, src.getName()));");
		ps.println("			try {");
		ps.println("				byte [] buffer = new byte[4096];");
		ps.println("				int rc = 0;");
		ps.println("				while ((rc = is.read(buffer)) > 0) {");
		ps.println("					os.write(buffer, 0, rc);");
		ps.println("			}");
		ps.println("			} finally {");
		ps.println("				os.close();");
		ps.println("			}");
		ps.println("		} finally {");
		ps.println("			is.close();");
		ps.println("		}");
		ps.println("		}");

		ps.println("}");
		
		ps.close();

	}

	public void addModifyTable(String tablename)
	{
		_transformtable.add(tablename);	
	}
	
	public boolean isModifyTable(String tablename)
	{
		return _transformtable.contains(tablename);	
	}

}
