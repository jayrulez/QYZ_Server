package xdb;

import org.w3c.dom.*;

public final class TableConf {
	public static final int DEFAULT_CACHE_HIGH = 512;
	public static final int DEFAULT_CACHE_LOW = 256;
	public static final int DEFAULT_CACHE_CAPACITY = 10240;
	public static final int DEFAULT_CACHE_CLEAN_TRY = 256;

	// Storage 相关配置
	private String name;
	private int cachehigh;
	private int cachelow;

	// TTable 相关配置
	private int cacheCapacity;
	private TTable.Persistence persistence;
	// 这个配置在表打开时，会被保存到另外地方。
	private String lockname;

	// Table 其他配置
	private java.util.Map<String, String> otherAttrs = new java.util.HashMap<String, String>();

	// 用于内部系统表构造的时候，只初始化Storage相关配置。
	TableConf(String name, int cachehigh, int cachelow) {
		this.name = name;
		this.cachehigh = cachehigh;
		this.cachelow = cachelow;
	}

	// 从配置中读取。
	TableConf(Element self) {
		name = self.getAttribute("name");

		xgen.Define define = xgen.Define.getInstance();
		if (define.isParsed())
		{
			Integer cachecap = define.getCacheCap(self.getAttribute("cacheCapacity"));
			cacheCapacity = (cachecap != null ? cachecap.intValue() : DEFAULT_CACHE_CAPACITY);
			Integer[] cachepage = define.getCachePage(self.getAttribute("cachePage"));
			cachelow = (cachepage != null ? cachepage[0].intValue() : DEFAULT_CACHE_LOW);
			cachehigh = (cachepage != null ? cachepage[1].intValue() : DEFAULT_CACHE_HIGH);
		}
		else
		{
			cachehigh = XdbConf.getInt(self, "cachehigh", DEFAULT_CACHE_HIGH);
			cachelow = XdbConf.getInt(self, "cachelow", DEFAULT_CACHE_LOW);
			cacheCapacity = XdbConf.getInt(self, "cacheCapacity", DEFAULT_CACHE_CAPACITY);
		}

		String tmp;
		lockname = (tmp = self.getAttribute("lock")).isEmpty() ? name : tmp;
		persistence = (tmp = self.getAttribute("persistence").toUpperCase()).isEmpty()
				? TTable.Persistence.DB : TTable.Persistence.valueOf(tmp);

		{
			NamedNodeMap attrs = self.getAttributes();
			for (int i = 0; i < attrs.getLength(); ++i) {
				Attr attr = (Attr)attrs.item(i);
				otherAttrs.put(attr.getName(), attr.getValue());
			}
			// 删除已知的属性。
			otherAttrs.remove("name");
			otherAttrs.remove("cacheHigh");
			otherAttrs.remove("cacheLow");
			otherAttrs.remove("cacheCapacity");
			otherAttrs.remove("lock");
			otherAttrs.remove("persistence");
		}
	}

	public java.util.Map<String, String> getOtherAttrs() {
		return otherAttrs;
	}

	public String getOtherAttr(String name) {
		String value = otherAttrs.get(name);
		return value == null ? "" : value;
	}

	public int getOtherAttrInt(String name, int def) {
		String value = this.getOtherAttr(name);
		return value.isEmpty() ? def : Integer.valueOf(value);
	}

	public long getOtherAttrLong(String name, long def) {
		String value = this.getOtherAttr(name);
		return value.isEmpty() ? def : Long.valueOf(value);
	}

	public int getCacheCapacity() {
		return cacheCapacity;
	}

	public String getLockName() {
		return lockname;
	}

	public int getCacheHigh() {
		return cachehigh;
	}

	public int getCacheLow() {
		return cachelow;
	}

	public String getName() {
		return name;
	}

	public TTable.Persistence getPersistence() {
		return persistence;
	}
}
