
package xbean;

public interface HuiWuCurTerm extends xdb.Bean {
	public HuiWuCurTerm copy(); // deep clone
	public HuiWuCurTerm toData(); // a Data instance
	public HuiWuCurTerm toBean(); // a Bean instance
	public HuiWuCurTerm toDataIf(); // a Data instance If need. else return this
	public HuiWuCurTerm toBeanIf(); // a Bean instance If need. else return this

	public int getTermid(); // 
	public long getOpentime(); // 
	public long getEndtime(); // 
	public long getGuessendtime(); // 
	public int getStage(); // cfg.huiwu.Stage
	public int getRoundindex(); // 
	public int getRoundstage(); // cfg.huiwu.RoundStage
	public long getBattlebegintime(); // 
	public java.util.Map<Integer, xbean.HuiWuProfessionTerm> getTerminfobyprofession(); // 
	public java.util.Map<Integer, xbean.HuiWuProfessionTerm> getTerminfobyprofessionAsData(); // 
	public java.util.Map<Long, Long> getGuess(); // 投票
	public java.util.Map<Long, Long> getGuessAsData(); // 投票
	public java.util.Map<Long, Integer> getContinuouschampions(); // roleid -> continue win counts
	public java.util.Map<Long, Integer> getContinuouschampionsAsData(); // roleid -> continue win counts

	public void setTermid(int _v_); // 
	public void setOpentime(long _v_); // 
	public void setEndtime(long _v_); // 
	public void setGuessendtime(long _v_); // 
	public void setStage(int _v_); // cfg.huiwu.Stage
	public void setRoundindex(int _v_); // 
	public void setRoundstage(int _v_); // cfg.huiwu.RoundStage
	public void setBattlebegintime(long _v_); // 
}
