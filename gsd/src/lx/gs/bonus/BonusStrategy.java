package lx.gs.bonus;

/**
 * @author Jin Shuai
 */
public enum BonusStrategy {
    TIPS_DISCARD, // 给出提示,奖励丢弃，不能重新获得
    TIPS_MAIL,    // 给出提示，并发送至邮件
    ALERT,        // 弹出对话框，并使操作失败，针对于可以重新执行的操作，清理包裹后，奖励可以重新获得
    ;
}
