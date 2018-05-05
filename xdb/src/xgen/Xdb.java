package xgen;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xdb.util.DatabaseMetaData;


import java.io.File;
import java.util.*;
import java.io.*;

public class Xdb {
	private Set<String> nameset = new HashSet<String>();
	private List<XBean> xbeans = new ArrayList<XBean>();
	private List<CBean> cbeans = new ArrayList<CBean>();
	private Map<String, Table> tables = new HashMap<String, Table>();

	private List<Cache> caches = new ArrayList<Cache>();
	private Set<String>  cacheNames = new HashSet<String>();

	private File xgenOutput;
	private File xtabledir;
	private File xbeandir;
	private File xbeandir__;
	private File xcachedir;

	Table getTable(String name) {
		return tables.get(name);
	}

	void add(Cache cc) {
		if (!cacheNames.add(cc.getName()))
			throw new RuntimeException("duplicate ccache name: " + cc.getName());
		caches.add(cc);
	}

	void add(XBean bean) {
		if (!nameset.add(bean.getName()))
			throw new RuntimeException("duplicate bean name " + bean.getName());
		xbeans.add(bean);
	}

	void add(CBean bean) {
		if (!nameset.add(bean.getName()))
			throw new RuntimeException("duplicate bean name " + bean.getName());
		cbeans.add(bean);
	}

	void add(Table table) {
		if (null != tables.put(table.getName(), table))
			throw new RuntimeException("duplicate table name " + table.getName());
	}

	File getXbeandir() {
		return xbeandir;
	}

	File getXtabledir() {
		return xtabledir;
	}

	File getXcachedir() {
		return xcachedir;
	}

	public File getXbeandir__() {
		return xbeandir__;
	}

	Xdb(Element self) {
		xgenOutput = new File(self.getAttribute("xgenOutput"));
		if (!xgenOutput.isDirectory() || !xgenOutput.exists())
			throw new RuntimeException("'" + xgenOutput + "' (!isDirectory || !exists())");

		xtabledir = new File(xgenOutput, "xtable");
		xbeandir = new File(xgenOutput, "xbean");
		xbeandir__ = new File(xbeandir, "__");
		xcachedir = new File(xgenOutput, "xcache");

		NodeList childnodes = self.getChildNodes();
		for (int i = 0; i < childnodes.getLength(); ++i) {
			Node node = childnodes.item(i);
			if (Node.ELEMENT_NODE != node.getNodeType())
				continue;

			Element e = (Element) node;
			String nodename = e.getNodeName();
			if (nodename.equals("xbean")) {
				add(new XBean(e));
			} else if (nodename.equals("table")) {
				add(new Table(e));
			} else if (nodename.endsWith("Conf")) {
				// skip conf
			} else if (nodename.equals("cbean")) {
				add(new CBean(e));
			} else if (nodename.endsWith("define")) {
				Define.getInstance().parse(e);
			} else if (nodename.equals("cache")) {
				add(new Cache(e));
			} else
				throw new RuntimeException("nodename=" + nodename);
		}
	}

	void compile() {
		// 编译类型
		for (XBean bean : xbeans)
			bean.compile(this);
		for (CBean bean : cbeans)
			bean.compile(this);
		for (Table table : tables.values())
			table.compile(this);
		for (Cache cache : caches)
			cache.compile(this);

		// 验证关系
		for (XBean bean : xbeans) {
			bean.verify(this);
			bean.depends(); // verify self-depend
		}
		for (CBean bean : cbeans) {
			bean.verify(this);
			bean.depends(); // verify self-depend
		}
		for (Table table : tables.values())
			table.verify(this);

		// foreign 环检测
		for (Table table : tables.values()) {
			//System.err.println("detectForeignCircle For " + table.getName());
			detectForeignCircle(table, new Detected(table));
		}

		// report
		for (XBean bean : xbeans) {
			if (bean.getOwnerTables().size() > 1)
				Main.warn("OWNER xbean=" + Main.quote(bean.getName())
						+ " owners=" + bean.getOwnerTables(), 'o');
		}
		for (CBean bean : cbeans) {
			if (bean.getOwnerTables().size() > 1)
				Main.warn("OWNER cbean=" + Main.quote(bean.getName())
						+ " owners=" + bean.getOwnerTables(), 'o');
		}
	}

	// 记住历史信息，定位循环。
	static class Detected {
		private Table origin;
		Detected(Table origin) {
			this.origin = origin;
		}
		// order by add
		private Map<String, Set<String>> detected = new LinkedHashMap<String, Set<String>>();

		Set<String> foreigns(Table table) {
			Set<String> foreigns = table.getForeigns();
			detected.put(table.getName(), foreigns);
			if (foreigns.contains(origin.getName())) {
				for (Map.Entry<String, Set<String>> e : detected.entrySet()) {
					if (false == e.getValue().isEmpty())
						System.err.println(origin.getName() + " " + e);
				}
				throw new IllegalStateException("foreign circle found.");
			}
			return foreigns;
		}

		boolean contains(Table table) {
			return detected.containsKey(table.getName());
		}
	}

	void detectForeignCircle(Table table, Detected detected) {
		if (null == table)
			return; // maybe null. see TypeBinary

		if (detected.contains(table)) // 就像多重继承的基类，Table会重复，判断过了就不用再检查了。
			return;

		Set<String> foreigns = detected.foreigns(table);
		if (foreigns.contains(table.getName()))
			throw new IllegalStateException("foreign to self is not supported!" + "NO null value in XDB. table="
					+ table.getName());

		for (String foreign : foreigns)
			detectForeignCircle(tables.get(foreign), detected);
	}

	void make() {
		if (false == tables.isEmpty())
			xtabledir.mkdirs();

		if (false == xbeans.isEmpty()) {
			xbeandir.mkdirs();
			xbeandir__.mkdirs();
		}

		if (false == caches.isEmpty())
			xcachedir.mkdirs();

		for (XBean bean : xbeans)
			bean.make();
				
		XBean.make(xbeans);
				
		for (CBean bean : cbeans) {
			bean.make();
		}
		for (Table table : tables.values()) {
			table.make();
		}
		for (Cache cache : caches) {
			cache.make();
		}
		Table.make(tables.values());

		Cache.make_ConcurrentCaches_(this, caches);

		Lock.make(tables.values());
		makeMetaData();
	}

	void makeMetaData() {
		PrintStream ps = Main.fopen(this.getXtabledir(), "_DatabaseMetaData_");

		ps.println("package xtable;");
		ps.println("");
		ps.println("");
		ps.println("public class _DatabaseMetaData_ extends xdb.util.DatabaseMetaData {");

		ps.println("	@Override");
		ps.println("	public boolean isVerifyXdb() {");
		ps.println("		return " + (Main.noverify ? "false" : "true") + ";");
		ps.println("	}");

		

		//Split _DatabaseMetaData_ constructor to small ones
		//To fix: the code of constructor _DatabaseMetaData_() is exceeding the 65535 bytes limit
		int loopCounter = 0;
		int methodCounter = 1;
		ps.println("	public void DatabaseMetaData" + methodCounter + "(){");
		ps.println("		// xbeans");
		for (XBean xbean : xbeans) {
			if(checkPrintNewMethod(loopCounter, methodCounter, ps)){
				methodCounter ++;
				loopCounter = 0;
			}
			ps.println("		{");
			xbean.printMeta(ps, "			");
			ps.println("		}");
			loopCounter ++;
		}

		ps.println("		// cbeans");
		for (CBean cbean : cbeans) {
			if(checkPrintNewMethod(loopCounter, methodCounter, ps)){
				methodCounter ++;
				loopCounter = 0;
			}
			ps.println("		{");
			cbean.printMeta(ps, "			");
			ps.println("		}");
			loopCounter ++;
		}

		ps.println("		// tables");
		for (Table table : tables.values()) {
			if(checkPrintNewMethod(loopCounter, methodCounter, ps)){
				methodCounter ++;
				loopCounter = 0;
			}
			table.printMeta(ps, "		");
			loopCounter ++;
		}
		ps.println("	}");

		// construct meta data
		ps.println("	public _DatabaseMetaData_() {");
		for(int i=0; i<methodCounter; i++){
			ps.println("		DatabaseMetaData" + String.valueOf(i+1) + "();");
		}
		//added end
		ps.println("	}");
		ps.println("}");
		ps.println("");
		ps.close();
	}

	private boolean checkPrintNewMethod(int loopCounter, int methodCounter, PrintStream ps){
		if(loopCounter < 100)
			return false;
		
		ps.println("	}");
		ps.println("	public void DatabaseMetaData" + String.valueOf(methodCounter+1) + "(){");
		return true;
	}
	
	void clean() {
		CachedFileOutputStream.removeOtherFiles(xbeandir);
		CachedFileOutputStream.removeOtherFiles(xtabledir);
	}

	////////--------------------------------//////////
	
	/**caijiacheng 2010.08.24
	 * return : true :表示新旧的xdb表内容有更改，需要transform
	 * 			false: 表示新旧的xdb表内容没有更改，不需要xtransform
	 */
	private boolean diffMetaData(Collection<XBean> xbeanDeps, Collection<CBean> cbeanDeps) 
	{
		DatabaseMetaData metadata = DatabaseMetaData.getInstance();
				
		boolean isDiff = false;
		//增删的判断还是需要简化一下……
		for (DatabaseMetaData.Bean bn : metadata.getBeans()) {
			//判断xbean下各个字段的变化.
			for (XBean xn : xbeanDeps) {
				if (bn.getName().equals(xn.getName())) {
					//删除了var
					for (DatabaseMetaData.Bean.Variable db : bn.getVariables()) {
						Variable vb = xn.getVariable(db.getName());
						if (vb == null) //不存在这个var，删除操作
						{
							vb = new Variable(db);
							vb.compile(this);
							xn.getVariables().add(vb);
							vb.setModifyType(Variable.MODIFY_TYPES.REMOVE);
							xn.setHasModify();
							System.out.println("XBean: Var Is removed: " + bn.getName() + "." + vb.getname());
							isDiff = true;
							
							//add xbeandep
						}
					}
					//新添加了var
					for (Variable v : xn.getVariables()) {
						boolean exist = false;
						for (DatabaseMetaData.Bean.Variable xb : bn.getVariables()) {
							if (xb.getName().equals(v.getname()) || v.getModifyType() != Variable.MODIFY_TYPES.NONE)
								exist = true;
						}
						if (exist == false)//新添加的var
						{
							System.out.println("XBean: Var Is added: " + bn.getName() + "." + v.getname());
							v.setModifyType(Variable.MODIFY_TYPES.ADD);
							xn.setHasModify();
							isDiff = true;
						}
					}
				}
			}
			//判断cbean下的各个字段的变化
			for (CBean cn : cbeanDeps )
			{
				if (bn.getName().equals(cn.getName())) {
					//删除了var
					for (DatabaseMetaData.Bean.Variable db : bn.getVariables()) {
						Variable vb = cn.getVariable(db.getName());
						if (vb == null) //不存在这个var，删除操作
						{
							System.out.println("CBean: Var is Removed: " + bn.getName() + "." + db.getName());
							throw new RuntimeException("It is Unsupportment for CBean Remove. it destroy the uniqment of key");
						}
					}
					//新添加了var
					for (Variable v : cn.getVariables()) {
						boolean exist = false;
						for (DatabaseMetaData.Bean.Variable xb : bn.getVariables()) {
							if (xb.getName().equals(v.getname()) || v.getModifyType() != Variable.MODIFY_TYPES.NONE)
								exist = true;
						}
						if (exist == false)//新添加的var
						{
							System.out.println("CBean: Var Is added: " + bn.getName() + "." + v.getname());
							v.setModifyType(Variable.MODIFY_TYPES.ADD);
							cn.setHasModify();
							isDiff = true;
						}
					}
				}
			
			}
		}
		
		return isDiff;
		
	}

	/*
	 * 较验所有table的key,value和所有的xbean的字段没有被修改，确保transform不会出现隐含问题
	 */
	private void transformVerify(Collection<XBean> xbeanDeps, Collection<Table> tableDeps, Collection<CBean> cbeanDeps)
	{
		DatabaseMetaData metadata = DatabaseMetaData.getInstance();

		//verify the table : key ,value		
		for ( xgen.Table xt : tableDeps )		
		{
			DatabaseMetaData.Table tb = metadata.getTable(xt.getName());
			if ( tb != null )
			{
				if ( !xt.getKeyType().getName().equals(tb.getKeyName()) 					
						||!xt.getValueType().getName().equals(tb.getValueName()) )
				{
					throw new RuntimeException("\nthe Table is Modify??? \n" + xt.getName() 
							+ "\n	keytype: " + xt.getKeyType().getName()
							+ "\n	valuetype: "+ xt.getValueType().getName()
							+ "\n	oldkeytype: " +  tb.getKeyName() 
							+ "\n	oldvaluetype: " + tb.getValueName()
					);
				}
			}
		}

		//verify the xbean : type
		for (XBean xn : xbeanDeps) 
		{
			DatabaseMetaData.Bean bn = metadata.getBean(xn.getName());
			if (bn != null) 
			{//相同的xbean
				for (DatabaseMetaData.Bean.Variable db : bn.getVariables()) 
				{
					Variable vb = xn.getVariable(db.getName());
					if ( vb != null )//相同的var
					{//key和value的type是否相同
						if ( 	!transformVerifySkip(vb, db, xn.getName())
								|| !db.getKeyTypeName().equals(vb.getKeyTypeName())
								|| !db.getValueTypeName().equals(vb.getValueTypeName()) )
						{
							throw new RuntimeException("\nthe XBean is Modify??? \n" + xn.getName() + "." + vb.getName()
									+ "\n	Type: " + vb.getVartype().getName()
									+ "\n 	keytype: " + vb.getKeyTypeName()
									+ "\n 	valuetype: "+ vb.getValueTypeName()
									+ "\n	oldType: " + db.getTypeName()
									+ "\n 	oldkeytype: " +  db.getKeyTypeName() 
									+ "\n 	oldvaluetype: " + db.getValueTypeName()
							);
						}

					}
				}
			}
		}
		
		//verify the cbean : type
		for (CBean xn : cbeanDeps) 
		{
			DatabaseMetaData.Bean bn = metadata.getBean(xn.getName());
			if (bn != null) 
			{//相同的xbean
				for (DatabaseMetaData.Bean.Variable db : bn.getVariables()) 
				{
					Variable vb = xn.getVariable(db.getName());
					if ( vb != null )//相同的var
					{//key和value的type是否相同
						if ( 	!transformVerifySkip(vb, db, xn.getName())								
								|| !db.getKeyTypeName().equals(vb.getKeyTypeName())
								|| !db.getValueTypeName().equals(vb.getValueTypeName()) )
						{
							throw new RuntimeException("\nthe CBean is Modify??? \n" + xn.getName() + "." + vb.getName()
									+ "\n	Type: " + vb.getVartype().getName()
									+ "\n 	keytype: " + vb.getKeyTypeName()
									+ "\n 	valuetype: "+ vb.getValueTypeName()
									+ "\n	oldType: " + db.getTypeName()
									+ "\n 	oldkeytype: " +  db.getKeyTypeName() 
									+ "\n 	oldvaluetype: " + db.getValueTypeName()
							);
						}

					}
				}
			}
		}		
	}

	/** skip 掉不需要verify的variable，比如说xbean.map -> xbean.treemap就不需要verify
	 * @param xvar : 新的xml中的xbean
	 * @param dvar : 旧的xml中的xbean
	 * @return
	 */
	private boolean transformVerifySkip(xgen.Variable xvar, DatabaseMetaData.Bean.Variable dvar, String beanname)
	{
		//skip map -> treemap
		if ( dvar.getTypeName().equals("map") && xvar.getVartype().getName().equals("treemap") )
		{
			System.out.println( "Skip Bean : " + beanname + " TYPE: map -> treemap");
			return true;
		}
		//skip treemap -> map
		if ( dvar.getTypeName().equals("treemap") && xvar.getVartype().getName().equals("map") )
		{
			System.out.println( "Skip Bean : " + beanname + " TYPE: treemap -> map");
			return true;
		}
		
		//skip vector -> list -- cjc 2009-12-29
		if ( dvar.getTypeName().equals("vector") && xvar.getVartype().getName().equals("list") )
		{
			System.out.println( "Skip Bean : " + beanname + " TYPE: vector -> list");
			return true;
		}
		//skip list -> vector
		if ( dvar.getTypeName().equals("list") && xvar.getVartype().getName().equals("vector") )
		{
			System.out.println( "Skip Bean : " + beanname + " TYPE: list -> vector");
			return true;
		}
		
		//skip set -> vector
		if ( dvar.getTypeName().equals("set") && xvar.getVartype().getName().equals("vector") )
		{
			System.out.println( "Skip Bean : " + beanname + " TYPE: set -> vector");
			return true;
		}
		
		//skip set -> list
		if ( dvar.getTypeName().equals("set") && xvar.getVartype().getName().equals("list") )
		{
			System.out.println( "Skip Bean : " + beanname + " TYPE: set -> list");
			return true;
		}
		
		return dvar.getTypeName().equals(xvar.getVartype().getName());
		
	}

	void transformMake() {
		xtabledir.mkdirs();
		xbeandir.mkdirs();
		xbeandir__.mkdirs();

		Set<Type> typesDeps = new HashSet<Type>();
		//Set<Type> dmTypes = new HashSet<Type>();
		Set<XBean> xbeanDeps = new HashSet<XBean>();
		Set<XBean> xbeanTemp = new HashSet<XBean>();
		Set<Table> tableDeps = new HashSet<Table>();
		Set<CBean> cbeanDeps = new HashSet<CBean>();
		//List<XBean> cbeanTemp = new ArrayList<XBean>(); // 未用的变量，by lich
		
		//处理依赖关系.
		for (Table tb : tables.values()) {
			if (!tb.isMemory()) 
			{
				tb.depends(typesDeps); //获取所有相关的表结构需要的type信息
				tableDeps.add(tb);
			}
		}
		
		//先把所有相关的xbean全部加进xbeanDep中
		Set<String> metaDataDep = new HashSet<String>(); 
		for (XBean bean : xbeans) 
		{ //遍历所有的xbean
			if (bean.isEnumsOnly()) //for enum used
			{
				xbeanDeps.add(bean); 
				continue;
			}
			for (Type type : typesDeps) //所有的需要的type
			{
				if (bean.getName().equals(type.getName())) 
				{
					xbeanDeps.add(bean); 
					//判断DataMeta中的xbean是否依赖于其它的旧的xbean
					DatabaseMetaData.Bean btemp = DatabaseMetaData.getInstance().getBean(bean.getName());
					if ( btemp != null )
					{
						for ( DatabaseMetaData.Bean.Variable db :
							btemp.getVariables() )
						{
							DatabaseMetaData.Type.Depends deps = new DatabaseMetaData.Type.Depends();
							db.depends(deps);
							for (DatabaseMetaData.Type typedep : deps.getDepends())
							{
								//if ( !Type.isExist(typesDeps, typedep.getName()) )
								if ( !Type.isExist(typedep.getName()) )
								{
									if (typedep.isBean())
									{
										System.out.println("XBean: XBean("+ typedep.getName()+") is Removed. Rebuild from DatabaseMetaData " );
										DatabaseMetaData.Bean nbean = 
											DatabaseMetaData.getInstance().getBean(typedep.getName());
										if (nbean == null)
										{
											throw new RuntimeException("Can't find Info of Bean(" +  typedep.getName() + ") from DatabaseMetaData" );
										}
										XBean newxb = new XBean(nbean);
										xbeanTemp.add(newxb);
									}
								}
								else
								{
									metaDataDep.add(typedep.getName());
								}
							}
						}	
					}									
				}
			}
		}
		
		
		//新添加的XBean，处理		
		for ( XBean bean : xbeanTemp )
		{
			xbeans.add(bean);
			xbeanDeps.add(bean);
			bean.compile(this);
			bean.verify(this);
			
		}
		for ( XBean bean : xbeanTemp )
		{
			bean.depends();
		}
		
		//meta相关的xbean
		for( String unuse : metaDataDep)
		{
			for (XBean bean : xbeans)
			{
				if (unuse.equals(bean.getName()))
				{
					if (!xbeanDeps.contains(bean))
					{
						System.out.println("XBean: XBean("+ bean.getName()+") is Added DEP with Not TABLE REF. \n" );
						xbeanDeps.add(bean);
					}
						
				}
			}
		}

		for (CBean bean : cbeans) {
			for (Type type : typesDeps) {
				if (bean.getName().equals(type.getName())) {
					cbeanDeps.add(bean);
				}
			}
		}

		//如果-transformCheck参数打开，处理transformCheck参数

		
		Main.doTransformCheck(
				this.diffMetaData(xbeanDeps, cbeanDeps));
		//验证一下xml的修改是否符合规则
		transformVerify(xbeanDeps, tableDeps, cbeanDeps);
		
		for (XBean bean : xbeanDeps)
			bean.transformMake();
		XBean.make(xbeanDeps);
		for (CBean bean : cbeanDeps)
			bean.transformMake();
		for (Table table : tableDeps)
			table.make();
		Table.make(tableDeps);
		makeMetaDataTransform(xbeanDeps, cbeanDeps, tableDeps);

		TransformPrint tp = new TransformPrint();
		//区分出哪些表需要transform,其余直接copy
		for (Table tb : tableDeps) {
			typesDeps.clear();
			tb.depends(typesDeps);//找到依赖的所有xbean
			for (Type type : typesDeps) //遍历相关的所有type
			{
				boolean isMark = false;
				for (XBean xn : xbeans) 
				{
					if (type.getName().equals(xn.getName()) && xn.hasModify()) {
						System.out.println("Table " + tb.getName() + " is modify(" + 
								xn.getName() + " modify)");
						if ( !tp.isModifyTable(tb.getName()))
							tp.addModifyTable(tb.getName());
						isMark = true;
					}
				}
				if( isMark )
					break;				
				for (CBean cn : cbeans) 
				{
					if (type.getName().equals(cn.getName()) && cn.hasModify()) {
						System.out.println("Table " + tb.getName() + " is modify(" + 
								cn.getName() + " modify)");
						if ( !tp.isModifyTable(tb.getName()))
							tp.addModifyTable(tb.getName());
					}
				}
				
			}
		}
		//
		tp.printMain();
	}

	void makeMetaDataTransform(Collection<XBean> xbeans, Collection<CBean> cbeans, Collection<Table> tables) {
		PrintStream ps = Main.fopen(this.getXtabledir(), "_DatabaseMetaData_");

		ps.println("package xtable;");
		ps.println("");
		ps.println("");
		ps.println("public class _DatabaseMetaData_ extends xdb.util.DatabaseMetaData {");

		// construct meta data
		ps.println("	public _DatabaseMetaData_() {");

		ps.println("		// xbeans");
		for (XBean xbean : xbeans) {
			ps.println("		{");
			xbean.printMeta(ps, "			");
			ps.println("		}");
		}

		ps.println("		// cbeans");
		for (CBean cbean : cbeans) {
			ps.println("		{");
			cbean.printMeta(ps, "			");
			ps.println("		}");
		}

		ps.println("		// tables");
		for (Table table : tables) {
			table.printMeta(ps, "		");
		}

		ps.println("	super.addTable(\"_sys_\", \"DB\", \"int\", false, \"int\", \"\", \"\");");

		ps.println("	}");
		ps.println("}");
		ps.println("");
		ps.close();
	}

	File getXgenOutputdir() {
		return xgenOutput;
	}

}
