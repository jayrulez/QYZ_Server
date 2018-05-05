package xdb.util;

import java.io.IOException;
import java.util.*;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.goldhuman.Common.Octets;
import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/**
 * 包含 xdb 数据库信息。包含所有表格(table)和数据结构(xbean and cbean)。
 * <p>
 * xgen 生成 DatabaseMetaData 子类 xtable._DatabaseMetaData_。
 * <p>
 * 这个子类和xbeans,xtable一起编译发布。通过DatabaseMetaData的方法动态装载它。
 */
public class DatabaseMetaData {
	static interface MarshalXML{
		public void toXML(Element e);		
	}
	
	/**
	 * xdb.xml 中定义的所有表格结构。
	 */
	private final Map<String, Table> tables = new HashMap<String, Table>();

	/**
	 * xdb.xml 中定义的所有 bean，包括 xbean 和 cbean。
	 */
	private final Map<String, Bean> beans = new HashMap<String, Bean>();

	/**
	 * 所有类型，包括自定义 Bean。
	 */
	private final Map<String, Type> types = new HashMap<String, Type>();

	protected DatabaseMetaData() {
		/*
		 * see xgen.Type 支持的所有基本类型。
		 */
		addType(new Boolean_());
		addType(new Short_());
		addType(new Int_());
		addType(new Long_());
		addType(new String_());
		addType(new Float_());
		addType(new List_());
		addType(new Vector_());
		addType(new Set_());
		addType(new Map_());
		addType(new Treemap_());
		addType(new Binary_());

	}
	
	DatabaseMetaData(Element e) {
		this();
		{
			org.w3c.dom.NodeList nlBeans=e.getElementsByTagName("cbean");
			for(int i=0;i!=nlBeans.getLength();++i){
				Element el=(Element)nlBeans.item(i);
				this.addBean(new Bean(el));
			}
		}
		{
			org.w3c.dom.NodeList nlBeans=e.getElementsByTagName("xbean");
			for(int i=0;i!=nlBeans.getLength();++i){
				Element el=(Element)nlBeans.item(i);
				this.addBean(new Bean(el));
			}
		}
		
		org.w3c.dom.NodeList nlTables=e.getElementsByTagName("table");
		for(int i=0;i!=nlTables.getLength();++i){
			Element el=(Element)nlTables.item(i);
			this.addTable(new Table(el));
		}
	}
	
	public void createXML(java.io.File home){
		java.io.File f=new java.io.File(home.getAbsolutePath()+"/metadata.xml");
		//if(!f.exists()){
			this.saveToFile(f);
		//}
	}
	/**
	 * 
	 * @param home dbhome
	 * @return
	 */
	public boolean isSame(java.io.File home){
		java.io.File metadataFile = new java.io.File(home.getAbsolutePath()+"/metadata.xml");		
		return !metadataFile.exists() || DatabaseMetaData.getInstance().isSame(DatabaseMetaData.loadFromFile(metadataFile));
	}
	/**
	 * 判断两个metadata是否相容。
	 * 目前采用的是比较严格的判断。要求表的数量必须相同，beans必须全部都相同。
	 * 忽略capacity和foreign信息
	 * 
	 * @param o 被比较的对象（old）
	 * @return true,相容。false,有较大不同。
	 */
	boolean isSame(DatabaseMetaData o){
		for(Map.Entry<String, Table> e:tables.entrySet()){			
			Table ot=o.tables.get(e.getKey());
			//允许新添表
			if(ot==null) continue;
			if(!e.getValue().isSame(ot)) return false;
		}
		
		for(Map.Entry<String, Table> e:o.tables.entrySet()){			
			if(tables.get(e.getKey())==null && e.getValue().isPersistence()) 
				return false; //删了一张硬盘表
		}
		
		/*if(beans.size()!=o.beans.size()) return false;
		for(Map.Entry<String, Bean> e:beans.entrySet()){
			Bean ot=o.beans.get(e.getKey());
			if(ot==null) return false;
			if(!e.getValue().isSame(ot)) return false;
		}*/
		
		/* if(types.size()!=o.types.size()) return false;
		for(Map.Entry<String, Type> e:types.entrySet()){
			Type ot=o.types.get(e.getKey());
			if(ot==null) return false;
			if(!e.getValue().isSame(ot)) return false;
		} */
		
		return true;
	}
	
	static DatabaseMetaData loadFromFile(java.io.File f){
		final javax.xml.parsers.DocumentBuilder docbuilder;
		try{
			docbuilder = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();			
		}catch(javax.xml.parsers.ParserConfigurationException ex){
			xdb.Trace.error("err", ex);
			return null;
		}
		org.w3c.dom.Document doc;
		try{
			doc=docbuilder.parse(f);
		}catch(SAXException ex){
			return null;
		}catch(IOException ex){
			return null;
		}
		return new DatabaseMetaData(doc.getDocumentElement()).compile();
	}
	
	public void toXML(Element e){
		org.w3c.dom.Document doc=e.getOwnerDocument();
	
		for(Map.Entry<String, Bean> etable:this.beans.entrySet()){
			Element value=doc.createElement(etable.getValue().isConstant()?"cbean":"xbean");
			etable.getValue().toXML(value);
			e.appendChild(value);
		}		
		
		for(Map.Entry<String, Table> etable:this.tables.entrySet()){
			Element value=doc.createElement("table");
			etable.getValue().toXML(value);			
			e.appendChild(value);
		}
		
		
		/* Element types=doc.createElement("types");
		for(Map.Entry<String, Type> etable:this.types.entrySet()){
			Element entry=doc.createElement("entry");
			Element key=doc.createElement("string");
			key.appendChild(doc.createTextNode(etable.getKey()));
			entry.appendChild(key);
			Element value=doc.createElement(etable.getValue().getTypeId().name());
			etable.getValue().toXML(value);			
			entry.appendChild(value);
			types.appendChild(entry);
		}
		e.appendChild(types);*/
	}
	
	void saveToFile(java.io.File f){
		final javax.xml.parsers.DocumentBuilder docbuilder;
		javax.xml.transform.Transformer tf;
		try{
			docbuilder = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();
			tf=javax.xml.transform.TransformerFactory.newInstance().newTransformer();
		}catch(javax.xml.parsers.ParserConfigurationException ex){
			xdb.Trace.error("err", ex);
			return;
		}catch(javax.xml.transform.TransformerConfigurationException ex){
			xdb.Trace.error("err", ex);
			return;
		}
		final org.w3c.dom.Document doc=docbuilder.newDocument();
		Element e=doc.createElement("xdb");
		doc.appendChild(e);
		this.toXML(e);
		tf.setOutputProperty(javax.xml.transform.OutputKeys.METHOD, "xml");
		tf.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING, "utf-8");
		tf.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
		try{
			tf.transform(new javax.xml.transform.dom.DOMSource(doc), new javax.xml.transform.stream.StreamResult(f));
		}catch(javax.xml.transform.TransformerException ex){
			xdb.Trace.error("err", ex);
			return;
		}		
	}

	/**
	 * 编译： 检查错误，建立类型引用，提高效率。
	 * @return this;
	 */
	protected DatabaseMetaData compile() {
		for (int step = 0; step < 2; ++step) {
			for (Bean bean : beans.values())
				bean.compile(step);
			for (Table table : tables.values())
				table.compile(step);
		}
		return this;
	}

	public static DatabaseMetaData load(String providerName) {
		try {
			Class<?> providerClass = Class.forName(providerName);
			Object instance = providerClass.newInstance();
			if (instance instanceof DatabaseMetaData)
				return ((DatabaseMetaData) instance).compile();

			throw new RuntimeException("NOT DatabaseMetaData");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

	private static DatabaseMetaData instance;
	private static Object lock = new Object();

	public static DatabaseMetaData getInstance() {
		synchronized (lock) {
			if (null == instance) {
				// xtable._DatabaseMetaData_ is generate by xgen.
				instance = load("xtable._DatabaseMetaData_");
			}
			return instance;
		}
	}

	/**
	 * 是否启用了 xdb 的校验。
	 * xgen 生成代码时根据选项会生成校验代码，用来检测程序是否扩越事务使用 xbean。
	 * xgen 生成 _DatabaseMetaData_.java 时，重载这个函数。
	 * @return true if xbeans verify enabled.
	 */
	public boolean isVerifyXdb() {
		return false;
	}

	public final Table getTable(String tableName) {
		return tables.get(tableName);
	}

	public final Collection<Table> getTables() {
		return Collections.unmodifiableCollection(tables.values());
	}

	public final Bean getBean(String beanName) {
		return beans.get(beanName);
	}

	public final Collection<Bean> getBeans() {
		return Collections.unmodifiableCollection(beans.values());
	}

	public final Type getType(String typeName) {
		Type type = types.get(typeName);
		if (null != type)
			return type;
		throw new RuntimeException("unkown type. name=" + typeName);
	}

	Type getType(String typeName, boolean isAny) {
		Type type = types.get(typeName);
		if (null != type)
			return type;
		if (typeName.isEmpty()) // 不需要的类型定义。
			return null;
		if (isAny)
			return new Any_(typeName);
		throw new RuntimeException("unkown type. name=" + typeName);
	}

	public final Collection<Type> getTypes() {
		return Collections.unmodifiableCollection(types.values());
	}

	public final Marshal newBean(String beanName) {
		return (Marshal) this.getBean(beanName).newInstance();
	}

	public Object newType(String typeName) {
		return this.getType(typeName).newInstance();
	}

	public Marshal newBean(String beanName, OctetsStream os) {
		return (Marshal) this.getBean(beanName).unmarshal(os);
	}

	public Object newType(String typeName, OctetsStream os) {
		return this.getType(typeName).unmarshal(os);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("beans:\n");
		for (Bean bean : this.getBeans()) {
			sb.append(bean).append("\n");
		}
		sb.append("tables:\n");
		for (Table table : this.getTables()) {
			sb.append(table).append("\n");
		}
		return sb.toString();
	}

	/**
	 * 为 bean 添加变量。
	 */
	protected void addVariableFor(Bean bean, int id,
			String varName, String typeName, String keyTypeName, String valueTypeName,
			String comparatorClassName, String initial, String foreign, String capacity) {
		bean.addVariable(bean.new Variable(id, varName, typeName,
				keyTypeName, valueTypeName, comparatorClassName, initial,
				foreign, capacity));
	}

	protected void addBean(Bean bean) {
		addType(bean);
		if (null != beans.put(bean.getName(), bean))
			throw new RuntimeException("bean name duplicate!" + bean);
	}

	protected void addTable(Table table) {
		if (null != tables.put(table.getName(), table))
			throw new IllegalStateException("table name duplicate!" + table);
	}

	protected void addTable(String name, String persistence,
			String keyName, boolean autoKey, String valueName,
			String foreign, String capacity) {
		addTable(new Table(name, persistence, keyName, autoKey, valueName, foreign, capacity));
	}

	private void addType(Type type) {
		if (null != types.put(type.getName(), type))
			throw new IllegalStateException("type name duplicate!" + type);
	}

	public final class Table implements MarshalXML{
		/** 表名 */
		private final String name;
		private final xdb.Table.Persistence persistence;
		/** key的类型名*/
		private final String keyName;
		/** key是否是自增长。true，是。false,否 */
		private final boolean autoKey;
		/** value的类型名*/
		private final String valueName;
		private final ForeignConf foreign;
		private final CapacityConf capacity; // variable Variable

		private Type keyType;
		private Type valueType;

		/**
		 * 这个判断忽略foreign和capacity信息
		 * @param o
		 * @return
		 */
		boolean isSame(Table o){
			return name.equals(o.name) && persistence.equals(o.persistence) 
			&& keyType.isSame(o.keyType) && autoKey==o.autoKey && valueType.isSame(o.valueType);
		}
		
		void compile(int step) {
			switch (step) {
			case 0:
				this.keyType = DatabaseMetaData.this.getType(this.keyName);
				this.valueType = DatabaseMetaData.this.getType(this.valueName);
				break;
			}
		}

		/**
		 * 
		 * @param name 表名
		 * @param persistence 存储类型。"MEMORY"或"DB"
		 * @param key key的类型名
		 * @param autoKey key是否是自增长。true，是。false,否
		 * @param value value的类型名
		 * @param foreign
		 * @param capacity
		 */
		public Table(String name, String persistence,
				String key, boolean autoKey, String value,
				String foreign, String capacity) {
			this.name = name;
			this.persistence = xdb.Table.Persistence.valueOf(persistence);
			this.keyName = key;
			this.autoKey = autoKey;
			this.valueName = value;
			this.foreign = new ForeignConf(foreign);
			this.capacity = new CapacityConf(capacity);
		}

		public String getName() {
			return name;
		}

		public String getClassName() {
			return "xtable." + name;
		}

		public String getKeyName() {
			return keyName;
		}

		public String getValueName() {
			return valueName;
		}

		public xdb.Table.Persistence getPersistence() {
			return persistence;
		}

		public boolean isPersistence() {
			return persistence == xdb.Table.Persistence.DB;
		}

		public boolean isAutoKey() {
			return autoKey;
		}

		public ForeignConf getForeign() {
			return foreign;
		}

		public CapacityConf getCapacity() {
			return capacity;
		}

		public long sizeof() {
			return this.getKeyType().sizeof(this.capacity.getKey())
				+ this.getValueType().sizeof(this.capacity.getValue());
		}

		public Type getKeyType() {
			return this.keyType;
		}

		public Type getValueType() {
			return this.valueType;
		}

		public DatabaseMetaData getDatabaseMetaData() {
			return DatabaseMetaData.this;
		}

		/**
		 * nameEx = [!]name[+]。
		 * <p> ! is persistence 
		 * <p> + is autokey
		 */
		public String getNameEx() {
			return (this.isPersistence() ? "!" : "") + this.name + (this.autoKey ? "+" : "");
		}

		@Override
		public String toString() {
			return (this.isPersistence() ? "!" : "") + this.name
					+ "<" + this.keyName + (this.autoKey ? "+" : "")
					+ ", " + this.valueName + ">";
		}

		@Override
		public void toXML(Element e) {
			e.setAttribute("name", this.name);
			e.setAttribute("persistence", this.persistence.name());
			e.setAttribute("keyName", this.keyName);
			e.setAttribute("autoKey", this.autoKey?"true":"false");
			e.setAttribute("valueName", this.valueName);			
			e.setAttribute("capacity", this.capacity.toString());
			e.setAttribute("foreign", this.foreign.toString());
		}		

		public Table(Element e) {
			this(e.getAttribute("name"),e.getAttribute("persistence"),
					e.getAttribute("keyName"),e.getAttribute("autoKey").equals("true"),
					e.getAttribute("valueName"),e.getAttribute("foreign"),
					e.getAttribute("capacity"));
		}
	}

	public final class Bean extends Type implements MarshalXML {
		private final String name;
		private final boolean any;
		private final boolean constant;

		private final List<Variable> variables = new ArrayList<Variable>();

		/** 仅用来检查name是否重复，所以不需要被序列化 */
		private final Map<String, Variable> variablesByName = new HashMap<String, Variable>();

		public Bean(String name, boolean any, boolean constant) {
			this.name = name;
			this.any = any;
			this.constant = constant;
		}

		Bean(Element e) {
			this(e.getAttribute("name"),e.getAttribute("any").equals("true"),e.getNodeName().equals("cbean"));		
			org.w3c.dom.NodeList nlVars=e.getElementsByTagName("variable");
			for(int i=0;i!=nlVars.getLength();++i){
				Element v=(Element)nlVars.item(i);
				this.addVariable(new Variable(v));
			}
		}
		
		@Override
		public void toXML(Element e) {
			org.w3c.dom.Document doc=e.getOwnerDocument();
			e.setAttribute("name", this.name);
			if(this.any) e.setAttribute("any", "true");
			for(Variable v:this.variables){
				Element ve=doc.createElement("variable");
				v.toXML(ve);
				e.appendChild(ve);				
			}			
			return ;
		}
		
		@Override
		public boolean isSame(Type o) {
			Bean ot = (Bean)o;
			if(!name.equals(ot.name) || any!=ot.any || constant!=ot.constant) 
				return false;
			if(variables.size()!=ot.variables.size()) 
				return false;
			for(int i=0;i!=variables.size();++i){
				if(!variables.get(i).isSame(ot.variables.get(i)))
					return false;
			}
			return true;
		}


		private Set<Type> depends;

		void compile(int step) {
			for (Variable variable : variables)
				variable.compile(step);

			switch (step) {
			case 1:
				Depends depends = new Depends(this);
				this.depends(depends);
				this.depends = depends.getDepends();
				break;
			}
		}

		@Override
		public Set<Type> depends() {
			return depends;
		}

		@Override
		protected void depends(Depends depends) {
			if (depends.add(this)) {
				for (Variable var : variables)
					var.depends(depends);
			}
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getClassName() {
			return this.constant ? "xbean." + name : "xbean.__." + name + "$Data";
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.BEAN;
		}

		@Override
		public boolean isAny() {
			return any;
		}

		@Override
		public boolean isConstant() {
			return constant;
		}

		@Override
		public long sizeof(Integer capacity) {
			long cap = 0;
			for (Variable var : this.variables)
				cap += var.sizeof();
			return cap;
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			Marshal object = (Marshal) newInstance();
			try {
				object.unmarshal(os);
				return object;
			} catch (MarshalException x) {
				throw new RuntimeException(x);
			}
		}

		@Override
		public Class<?> getClazz() {
			try {
				return Class.forName(this.getClassName());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public OctetsStream marshal(Object obj) {
			getClazz().cast(obj); // type check
			return new OctetsStream().marshal((Marshal)obj);
		}

		public List<Variable> getVariables() {
			return Collections.unmodifiableList(variables);
		}

		public Variable getVariable(String varName) {
			return variablesByName.get(varName);
		}

		public DatabaseMetaData getDatabaseMetaData() {
			return DatabaseMetaData.this;
		}

		void addVariable(Variable variable) {
			if (null != variablesByName.put(variable.getName(), variable))
				throw new RuntimeException("duplicate variable: " + variable + "@" + this);
			variables.add(variable);
		}

		public final class Variable implements MarshalXML{
			private final int id;
			private final String name;

			private final String typeName;
			private final String keyTypeName; // map
			private final String valueTypeName; // list and vector and map
			private final String comparatorClassName; // treemap

			private final String initial; // basic types and cbean

			private final ForeignConf foreign;
			private final CapacityConf capacity; // variable Variable

			private Type typeType;
			private Type keyType;
			private Type valueType;

			void compile(int step) {
				if (step == 0) {
					this.typeType = DatabaseMetaData.this.getType(typeName, Bean.this.any);
					this.keyType = DatabaseMetaData.this.getType(keyTypeName, Bean.this.any);
					this.valueType = DatabaseMetaData.this.getType(valueTypeName, Bean.this.any);
				}
			}

			public boolean isSame(Variable o) {				
				return name.equals(o.name) && typeType.isSame(o.typeType) && (keyType==null || keyType.isSame(o.keyType))
				&& (valueType==null || valueType.isSame(o.valueType)) && comparatorClassName.equals(o.comparatorClassName);
			}

			public void depends(Depends depends) {
				depends.push(this);
				this.getTypeType().depends(depends);
				depends.pop(this);
			}

			@Override
			public String toString() {
				return name + "/" + getTypeType().getClassName();
			}

			public Variable(int id, String name, String typeName, String keyTypeName, String valueTypeName,
					String comparatorClassName, String initial, String foreign, String capacity) {
				this.id = id;
				this.name = name;

				this.typeName = typeName;
				this.keyTypeName = keyTypeName;
				this.valueTypeName = valueTypeName;
				this.comparatorClassName = comparatorClassName;

				this.initial = initial;
				this.foreign = new ForeignConf(foreign);
				this.capacity = new CapacityConf(capacity);
			}

			/**
			 * same as getOwnerBean
			 * @return the bean hold this variable.	
			 */
			public Bean getBean() {
				return Bean.this;
			}

			/**
			 * @return the bean hold this variable.	
			 */
			public Bean getOwnerBean() {
				return Bean.this;
			}
			
			public int getId() {
				return id;
			}

			public String getName() {
				return name;
			}

			public String getTypeName() {
				return typeName;
			}

			public String getKeyTypeName() {
				return keyTypeName;
			}

			public String getValueTypeName() {
				return valueTypeName;
			}

			public String getComparatorClassName() {
				return comparatorClassName;
			}

			public String getInitial() {
				return initial;
			}

			/**
			 * foreign key. 表格名字。格式为： tableName | key:tableName;value:tableName
			 * <p>
			 * 当变量类型是容器时，如果容器内的 key 或者 value 是 foreign 的，那么需要分key,value配置。
			 * 
			 * @return
			 */
			public ForeignConf getForeign() {
				return foreign;
			}

			public CapacityConf getCapacity() {
				return capacity;
			}

			public Type getTypeType() {
				return this.typeType;
			}

			public Type getKeyType() {
				return this.keyType;
			}

			public Type getValueType() {
				return this.valueType;
			}

			public long sizeof() {
				Type type = this.getTypeType();
				Integer capacity = this.capacity.getCapacity();
				switch (type.getTypeId()) {
				case LIST: case VECTOR: case SET:
					return (null != capacity ? capacity : 128)
							* this.getValueType().sizeof(this.capacity.getValue());

				case MAP: case TREEMAP:
					return (null != capacity ? capacity : 128)
							* (this.getKeyType().sizeof(this.capacity.getKey())
								+ this.getValueType().sizeof(this.capacity.getValue()));
				default:
					// 除了容器类型，其他类型都实现这个方法。
					// 新增类型也遵守这个规则，
					return type.sizeof(capacity);
				}
			}

			Variable(Element e) {
				name=e.getAttribute("name");
				try {
					this.id = Integer.parseInt(e.getAttribute("id"));
				} catch(Exception ex) {
					throw new RuntimeException("metadata variable:" + name + ", invalid id");
				}
				keyTypeName=e.getAttribute("keyTypeName");
				typeName=e.getAttribute("typeName");
				valueTypeName=e.getAttribute("valueTypeName");
				comparatorClassName=e.getAttribute("comparatorClassName");
				initial=e.getAttribute("initial");						
				foreign = new ForeignConf(e.getAttribute("foreign"));
				capacity = new CapacityConf(e.getAttribute("capacity"));
			}

			
			@Override
			public void toXML(Element e) {				
				if(!this.name.isEmpty()) e.setAttribute("name", this.name);
				if(!this.keyTypeName.isEmpty()) e.setAttribute("keyTypeName", this.keyTypeName);
				if(!this.typeName.isEmpty()) e.setAttribute("typeName", this.typeName);
				if(!this.valueTypeName.isEmpty()) e.setAttribute("valueTypeName", this.valueTypeName);
				if(!this.comparatorClassName.isEmpty()) e.setAttribute("comparatorClassName", this.comparatorClassName);
				if(!this.initial.isEmpty()) e.setAttribute("initial", this.initial);
				if(!this.foreign.toString().isEmpty()) e.setAttribute("foreign", this.foreign.toString());
				if(!this.capacity.toString().isEmpty()) e.setAttribute("capacity", this.capacity.toString());								
			}
		}
	}

	/**
	 * @see xgen.Type
	 */
	public static abstract class Type implements MarshalXML{
		
		@Override
		public void toXML(Element e){
			org.w3c.dom.Document doc=e.getOwnerDocument();
			Element name=doc.createElement("classname");
			name.appendChild(doc.createTextNode(this.getClassName()));
			e.appendChild(name);
		}
		
		public boolean isSame(Type ot) {
			if(!ot.getTypeId().equals(getTypeId())) return false;
			//其它的让子类去比较吧
			return true;
		}

		/**
		 * 名字。在 xdb.xml 中的配置名字。
		 * 
		 * @return
		 */
		public abstract String getName();

		/**
		 * JAVA 类名。包含全路径。
		 * 
		 * @return
		 */
		public abstract String getClassName();

		/**
		 * 是不是常类型。实例是无法修改的。如,Integer, String, CBean。
		 * 
		 * @return true if it is constant.
		 */
		public abstract boolean isConstant();

		/**
		 * 是否Any类型。这种类型无法被系列化（marshal）。只有自定义类型才可能是Any的。
		 * 
		 * @return true if it is any.
		 */
		public boolean isAny() {
			return false;
		}

		public static enum TypeId {
			BOOLEAN, SHORT, INT, LONG, STRING, FLOAT,
			LIST, VECTOR, SET, MAP, TREEMAP, BINARY,
			BEAN, ANY
		}

		public abstract TypeId getTypeId();

		/**
		 * 根据参数提供的配置，返回类型占用的内存。
		 * 
		 * @param conf
		 * @return
		 */
		public abstract long sizeof(Integer capacity);

		/**
		 * 使用默认配置，得到类型占用的内存。
		 * 
		 * @return
		 */
		public long sizeof() {
			return sizeof(null);
		}

		static public final class Depends {
			private final Type base;
			private Stack<Bean.Variable> variableStack = new Stack<Bean.Variable>();
			private Set<Type> depends = new HashSet<Type>();

			public Depends()
			{
				this.base = null;
			}
			
			Depends(final Type base) {
				this.base = base;
			}

			void push(Bean.Variable variable) {
				this.variableStack.push(variable);
			}

			Bean.Variable top() {
				return variableStack.peek();
			}

			Bean.Variable pop(Bean.Variable variable) {
				if (top() != variable)
					throw new IllegalStateException("pop: It is not top of stack! with "
							+ variable.getOwnerBean() + "." + variable.getName());
				return variableStack.pop();
			}

			boolean add(final Type self) {
				if (depends.add(self))
					return true;
				if (self == base)
					throw new RuntimeException("self-depend found! type=" + self.getName());
				return false;
			}

			public Set<Type> getDepends() {
				return depends;
			}
		}

		protected abstract void depends(Depends depends);

		/**
		 * @return 类型的依赖集合。
		 */
		public abstract Set<Type> depends();

		// /////////////////////////////////////////////////////////
		// 辅助接口

		/**
		 * 是否自定义类型。
		 * 
		 * @return true if it is bean(XBean or CBean).
		 */
		public boolean isBean() {
			return TypeId.BEAN == this.getTypeId();
		}

		/**
		 * 是否字节数组。byte[]
		 * 
		 * @return
		 */
		public boolean isBinary() {
			return TypeId.BINARY == this.getTypeId();
		}

		@Override
		public String toString() {
			return getName()
			// + "," + getClassName() + ",const=" + isConstant() + ",any=" +
			// isAny() + "," + getTypeId()
			;
		}

		/**
		 * 得到java类型信息
		 * @see getClass
		 */
		public abstract Class<?> getClazz();

		/**
		 * 创建类型实例。
		 * 
		 * @return
		 */
		public final Object newInstance() {
			try {
				return getClazz().newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 根据系列化后的数据，创建类型实例。
		 * 
		 * @return
		 */
		public abstract Object unmarshal(OctetsStream os);

		public final Object unmarshal(byte[] bytes) {
			return unmarshal(OctetsStream.wrap(Octets.wrap(bytes)));
		}

		/**
		 * 把对象系列化到二进制流。
		 * @param obj
		 * @return
		 */
		public abstract OctetsStream marshal(Object obj);

		/**
		 * @deprecated replaced by <code>unmarshal(os)</code>
		 */
		@Deprecated
		public final Object newInstance(OctetsStream os) {
			return unmarshal(os);
		}

		/**
		 * @deprecated replaced by <code>unmarshal(bytes)</code>
		 */
		@Deprecated
		public final Object newInstance(byte[] bytes) {
			return unmarshal(OctetsStream.wrap(Octets.wrap(bytes)));
		}
	}

	private static final class Boolean_ extends Type {
		@Override
		protected void depends(Depends depends) {
			depends.add(this);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "boolean";
		}

		@Override
		public String getClassName() {
			return "java.lang.Boolean";
		}

		@Override
		public boolean isConstant() {
			return true;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.BOOLEAN;
		}

		@Override
		public Class<?> getClazz() {
			return java.lang.Boolean.class;
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			try {
				return Boolean.valueOf(os.unmarshal_boolean());
			} catch (MarshalException x) {
				throw new RuntimeException(x);
			}
		}

		@Override
		public OctetsStream marshal(Object obj) {
			return new OctetsStream().marshal((Boolean)obj);
		}

		@Override
		public long sizeof(Integer capacity) {
			return 1L;
		}
	}

	private static final class Short_ extends Type {
		@Override
		protected void depends(Depends depends) {
			depends.add(this);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "short";
		}

		@Override
		public String getClassName() {
			return "java.lang.Short";
		}

		@Override
		public boolean isConstant() {
			return true;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.SHORT;
		}

		@Override
		public Class<?> getClazz() {
			return java.lang.Short.class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			return new OctetsStream().marshal((Short)obj);
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			try {
				return Short.valueOf(os.unmarshal_short());
			} catch (MarshalException x) {
				throw new RuntimeException(x);
			}
		}

		@Override
		public long sizeof(Integer capacity) {
			return 2L;
		}
	}

	private static final class Int_ extends Type {
		@Override
		protected void depends(Depends depends) {
			depends.add(this);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "int";
		}

		@Override
		public String getClassName() {
			return "java.lang.Integer";
		}

		@Override
		public boolean isConstant() {
			return true;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.INT;
		}

		@Override
		public Class<?> getClazz() {
			return java.lang.Integer.class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			return new OctetsStream().marshal((Integer)obj);
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			try {
				return Integer.valueOf(os.unmarshal_int());
			} catch (MarshalException x) {
				throw new RuntimeException(x);
			}
		}

		@Override
		public long sizeof(Integer capacity) {
			return 4L;
		}
	}

	private static final class Long_ extends Type {
		@Override
		protected void depends(Depends depends) {
			depends.add(this);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "long";
		}

		@Override
		public String getClassName() {
			return "java.lang.Long";
		}

		@Override
		public boolean isConstant() {
			return true;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.LONG;
		}

		@Override
		public Class<?> getClazz() {
			return java.lang.Long.class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			return new OctetsStream().marshal((Long)obj);
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			try {
				return os.unmarshal_long();
			} catch (MarshalException x) {
				throw new RuntimeException(x);
			}
		}

		@Override
		public long sizeof(Integer capacity) {
			return 8L;
		}
	}

	private static final class String_ extends Type {
		@Override
		protected void depends(Depends depends) {
			depends.add(this);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "string";
		}

		@Override
		public String getClassName() {
			return "java.lang.String";
		}

		@Override
		public boolean isConstant() {
			return true;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.STRING;
		}

		@Override
		public Class<?> getClazz() {
			return java.lang.String.class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			return new OctetsStream().marshal((String)obj, xdb.Const.IO_CHARSET);
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			try {
				return os.unmarshal_String(xdb.Const.IO_CHARSET);
			} catch (MarshalException x) {
				throw new RuntimeException(x);
			}
		}

		@Override
		public long sizeof(Integer capacity) {
			return 2L * (null != capacity ? capacity : 32);
		}
	}

	private static final class Float_ extends Type {
		@Override
		protected void depends(Depends depends) {
			depends.add(this);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "float";
		}

		@Override
		public String getClassName() {
			return "java.lang.Float";
		}

		@Override
		public boolean isConstant() {
			return true;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.FLOAT;
		}

		@Override
		public Class<?> getClazz() {
			return java.lang.Float.class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			return new OctetsStream().marshal((Float)obj);
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			try {
				return os.unmarshal_float();
			} catch (MarshalException x) {
				throw new RuntimeException(x);
			}
		}

		@Override
		public long sizeof(Integer capacity) {
			return 4L;
		}
	}

	private static final class List_ extends Type {
		@Override
		protected void depends(Depends depends) {
			// Meta 的容器类型不是动态创建的，无法用来判断是否已经加入到depends中。
			depends.add(this);
			depends.top().getValueType().depends(depends);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "list";
		}

		@Override
		public String getClassName() {
			return "java.util.List";
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.LIST;
		}

		@Override
		public Class<?> getClazz() {
			return java.util.LinkedList.class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long sizeof(Integer capacity) {
			throw new UnsupportedOperationException();
		}
	}

	private static final class Vector_ extends Type {
		@Override
		protected void depends(Depends depends) {
			// Meta 的容器类型不是动态创建的，无法用来判断是否已经加入到depends中。
			depends.add(this);
			depends.top().getValueType().depends(depends);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的容器类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "vector";
		}

		@Override
		public String getClassName() {
			return "java.util.Vector";
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.VECTOR;
		}

		@Override
		public Class<?> getClazz() {
			return java.util.ArrayList.class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long sizeof(Integer capacity) {
			throw new UnsupportedOperationException();
		}
	}

	private static final class Set_ extends Type {
		@Override
		protected void depends(Depends depends) {
			// Meta 的容器类型不是动态创建的，无法用来判断是否已经加入到depends中。
			depends.add(this);
			depends.top().getValueType().depends(depends);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的容器类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "set";
		}

		@Override
		public String getClassName() {
			return "java.util.Set";
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.SET;
		}

		@Override
		public Class<?> getClazz() {
			return java.util.HashSet.class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long sizeof(Integer capacity) {
			throw new UnsupportedOperationException();
		}
	}

	private static final class Map_ extends Type {
		@Override
		protected void depends(Depends depends) {
			// Meta 的容器类型不是动态创建的，无法用来判断是否已经加入到depends中。
			depends.add(this);
			depends.top().getValueType().depends(depends);
			depends.top().getKeyType().depends(depends);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的容器类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "map";
		}

		@Override
		public String getClassName() {
			return "java.util.Map";
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.MAP;
		}

		@Override
		public Class<?> getClazz() {
			return java.util.HashMap.class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long sizeof(Integer capacity) {
			throw new UnsupportedOperationException();
		}
	}

	private static final class Treemap_ extends Type {
		@Override
		protected void depends(Depends depends) {
			// Meta 的容器类型不是动态创建的，无法用来判断是否已经加入到depends中。
			depends.add(this);
			depends.top().getValueType().depends(depends);
			depends.top().getKeyType().depends(depends);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的容器类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "treemap";
		}

		@Override
		public String getClassName() {
			return "java.util.NavigableMap";
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.TREEMAP;
		}

		@Override
		public Class<?> getClazz() {
			return java.util.TreeMap.class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long sizeof(Integer capacity) {
			throw new UnsupportedOperationException();
		}
	}

	private static final class Binary_ extends Type {
		@Override
		protected void depends(Depends depends) {
			depends.add(this);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return "binary";
		}

		@Override
		public String getClassName() {
			return "byte[]";
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.BINARY;
		}

		@Override
		public Class<?> getClazz() {
			return byte[].class;
		}

		@Override
		public OctetsStream marshal(Object obj) {
			return new OctetsStream().marshal((byte[])obj);
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long sizeof(Integer capacity) {
			return null != capacity ? capacity : 128;
		}
	}

	private static final class Any_ extends Type {
		@Override
		protected void depends(Depends depends) {
			depends.add(this);
		}

		@Override
		public Set<Type> depends() {
			// Meta 的类型不是动态创建的，不知道自己的定义上下文。
			throw new UnsupportedOperationException();
		}

		private final String name;

		public Any_(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getClassName() {
			return name;
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public TypeId getTypeId() {
			return TypeId.ANY;
		}

		@Override
		public Class<?> getClazz() {
			try {
				return Class.forName(this.getClassName());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public OctetsStream marshal(Object obj) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object unmarshal(OctetsStream os) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long sizeof(Integer capacity) {
			return null != capacity ? capacity : 128;
		}
	}
}
