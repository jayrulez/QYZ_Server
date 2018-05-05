package cfg.fight;
public final class AgentType {
	public final static int NULL = -1;
	public final static int PLAYER = 1;
	public final static int MONSTER = 2;
	public final static int NPC = 4;
	public final static int ITEM = 8;
	public final static int MINE = 16;
	public final static int FAKE_PLAYER = 32;
	public final static int PET = 64;
	public final static int RUNE = 128;
	public final static java.util.List<Integer> enums = java.util.Arrays.asList(1 ,2 ,4 ,8 ,16 ,32 ,64 ,128);
}