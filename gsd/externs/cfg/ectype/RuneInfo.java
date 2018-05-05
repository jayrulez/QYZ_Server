package cfg.ectype;
public final class RuneInfo extends cfg.CfgObject {
	public final static int TYPEID = -1202551308;
	final public int getTypeId() { return TYPEID; }
	public final int runeid;
	public final int weight;
	public RuneInfo(cfg.DataStream fs) {
		this.runeid = fs.getInt();
		this.weight = fs.getInt();
	}
}