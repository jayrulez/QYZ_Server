package lx.gs.mail;

import cfg.mail.Consts;
import gnet.link.Onlines;
import gnet.link.Role;
import gs.FixedPriorityQueue;
import xbean.SystemMail;
import xdb.Procedure;
import xtable.Systemmailbox;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author HuangQiang
 * @createtime 2015年11月18日 上午10:38:40
 *
 */

public enum MailModule implements gs.Module, gs.RoleLoginListener, gs.DayOverListener {
	INSTANCE;

	private FixedPriorityQueue<SystemMail> systemMails = null;

	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		final xbean.MailBox mailbox = FMail.get(roleid);
		FMail.checkExpireMail(roleid, false);
		FMail.send(roleid, FMail.createSMailBox(mailbox));
		FMail.sendSystemMailOnLogin(roleid);
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {

	}

	@Override
	public void onDayOver() {
		for(Role role : Onlines.getInstance().roles()) {
			new Procedure() {
				@Override
				protected boolean process() {
					FMail.checkExpireMail(role.getRoleid(), true);
					return true;
				}
			}.execute();
		}
	}

	@Override
	public void start() {
		systemMails = new FixedPriorityQueue<>(Consts.MAX_MAIL_NUM, (o1, o2) -> Long.compare(o1.getId(), o2.getId()));
		Systemmailbox.getTable().walk((aLong, systemMail) -> {
			systemMails.offer(systemMail);
			return true;
		});
	}

	public synchronized List<SystemMail> getSystemMails() {
		return new ArrayList<>(systemMails);
	}

	public synchronized void addSysMail(SystemMail systemMail) {
		systemMails.offer(systemMail);
	}
}
