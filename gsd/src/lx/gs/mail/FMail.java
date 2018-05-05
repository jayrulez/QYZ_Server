package lx.gs.mail;

import cfg.CfgMgr;
import cfg.mail.Consts;
import cfg.mail.SenderType;
import common.Bonus;
import common.ErrorCode;
import lx.gs.bonus.FBonus;
import lx.gs.idgen.FIdGen;
import lx.gs.logger.By;
import lx.gs.mail.msg.Mail;
import lx.gs.mail.msg.SDelMail;
import lx.gs.mail.msg.SMailBox;
import lx.gs.mail.msg.SNewMail;
import xbean.MailBox;
import xbean.Pod;
import xbean.SystemMail;
import xtable.Roleinfos;
import xtable.Systemmailbox;

import java.util.*;

public final class FMail {
	public static void send(long roleid, xio.Protocol proto) {
		xdb.Transaction.tsendWhileCommit(roleid, proto);
	}
	
	public static xbean.MailBox get(long roleid) {
		xbean.MailBox mailbox = xtable.Mailbox.get(roleid);
		if(mailbox == null) {
			mailbox = xbean.Pod.newMailBox();
			xtable.Mailbox.add(roleid, mailbox);
		}
		return mailbox;
	}
	
	public static SMailBox createSMailBox(xbean.MailBox mailbox) {
		final SMailBox re = new SMailBox();
		for(Map.Entry<Integer, xbean.Mail> e : mailbox.getMails().entrySet()) {
			final Mail mail = new Mail();
			convert(e.getKey(), e.getValue(), mail);
			re.mails.add(mail);
		}
		return re;
	}
	
	static void convert(int id, xbean.Mail xmail, lx.gs.mail.msg.Mail mail) {
		mail.id = id;
		mail.mailid = xmail.getMailid();
		mail.sendtime = xmail.getSendtime();
		mail.title = xmail.getTitle();
		mail.content = xmail.getContent();
		FBonus.convert(xmail.getAccessory(), mail.accessory);
		mail.read = xmail.getRead();
	}

	public static void checkExpireMail(long roleid, boolean notify) {
		final Map<Integer, xbean.Mail> mails = get(roleid).getMails();
		final ArrayList<Integer> delmailids = new ArrayList<>();
		final long now = System.currentTimeMillis();
		final long accessoryExpireCreateTime = now - Consts.ACCESSORY_MAIL_EXPIRE_TIME * 1000L;
		final long noAccessoryExpireCreateTime = now - Consts.NO_ACCESSORY_MAIL_EXPIRE_TIME * 1000L;
		for(Iterator<xbean.Mail> it = mails.values().iterator() ; it.hasNext() ;) {
			final xbean.Mail mail = it.next();
			final int mailid = mail.getMailid();
			final long sendTime = mail.getSendtime();
			final xbean.Bonus acc = mail.getAccessory();
			if(acc.getCurrencys().isEmpty() && acc.getEquips().isEmpty() && acc.getItems().isEmpty()) {
				if(sendTime < noAccessoryExpireCreateTime) {
					it.remove();
					delmailids.add(mailid);
					xdb.Trace.info("mail.checkExceedMailBoxSize roleid:{}  deleted expire noaccessory mailid:{}", roleid, mailid);
				}
			} else {
				if(sendTime < accessoryExpireCreateTime) {
					it.remove();
					delmailids.add(mailid);
					xdb.Trace.info("mail.checkExceedMailBoxSize roleid:{}  deleted expire accessory mailid:{}", roleid, mailid);
				}
			}
		}
		if(notify && !delmailids.isEmpty()) {
			send(roleid, new SDelMail(delmailids));
		}
	}
	
	public static void checkExceedMailBoxSize(long roleid, Map<Integer, xbean.Mail> mails) {
		if(mails.size() <= cfg.mail.Consts.MAX_MAIL_NUM) return;
		final SDelMail re = new SDelMail();
		// 看似很笨的办法,但除非策划改了邮箱上限,正常情况下一次最多删除一封
		while(mails.size() > cfg.mail.Consts.MAX_MAIL_NUM) {
			final int delOldMailid = Collections.min(mails.keySet());
			xdb.Trace.info("mail.checkExceedMailBoxSize roleid:{} curmailnum:{} maxmailnum:{} deleted old mailid:{}",
				roleid, mails.size(), cfg.mail.Consts.MAX_MAIL_NUM, delOldMailid);
			mails.remove(delOldMailid);
			re.delmailids.add(delOldMailid);
		}
		send(roleid, re);
	}

	public static void addMail(long roleid, int mailid, String title, String content, map.msg.Bonus bonus) {
		final lx.gs.mail.msg.Mail mail = new lx.gs.mail.msg.Mail();
		mail.mailid = mailid;
		mail.title = title;
		mail.content = content;
		mail.accessory = bonus;
		addMail(roleid, mail);
	}

	public static void addMail(long roleid, int mailid) {
		final lx.gs.mail.msg.Mail mail = new lx.gs.mail.msg.Mail();
		mail.mailid = mailid;
		addMail(roleid, mail);
	}

    public static void addMail(long roleid, int mailid, String content){
        final lx.gs.mail.msg.Mail mail = new lx.gs.mail.msg.Mail();
        mail.mailid = mailid;
        mail.content = content;
        addMail(roleid, mail);
    }
	
	public static void addMail(long roleid, int mailid, map.msg.Bonus bonus) {
		final lx.gs.mail.msg.Mail mail = new lx.gs.mail.msg.Mail();
		mail.mailid = mailid;
		mail.accessory = bonus;
		addMail(roleid, mail);
	}

	public static void addMail(long roleid, int mailid, List<String> params) {
		final lx.gs.mail.msg.Mail mail = new lx.gs.mail.msg.Mail();
		mail.mailid = mailid;
		mail.params.addAll(params);
		addMail(roleid, mail);
	}

	public static void addMail(long roleid, int mailid, map.msg.Bonus bonus, List<String> params) {
		final lx.gs.mail.msg.Mail mail = new lx.gs.mail.msg.Mail();
		mail.mailid = mailid;
		mail.accessory = bonus;
		mail.params.addAll(params);
		addMail(roleid, mail);
	}

	public static void addMail(long roleid, SystemMail systemMail) {
		Mail mail = new Mail();
		mail.mailid = systemMail.getMailid();
		mail.title = systemMail.getTitle();
		mail.content = systemMail.getContent();
		mail.params.addAll(systemMail.getParams());
		FBonus.convert(systemMail.getBonus(), mail.accessory);
		addMail(roleid, mail);
		MailBox mailBox = get(roleid);
		if(systemMail.getId() > mailBox.getMaxsysmail()){
			mailBox.setMaxsysmail(systemMail.getId());
		}
	}

	public static void addMail(long roleid, Mail mail) {
		xdb.Trace.info("Mail.addMail roleid:{} mail{}", roleid, mail);
		final int mailid = mail.mailid;
		if(!CfgMgr.mail.containsKey(mailid))
			throw new RuntimeException("addMail. invalid mailid:" + mailid);

		final xbean.MailBox mailbox = get(roleid);
		if(CfgMgr.mail.get(mailid).sender == SenderType.FAMILY) {
			// 删除上一封家族邮件
			mailbox.getMails().values().removeIf(m -> CfgMgr.mail.get(m.getMailid()).sender == SenderType.FAMILY);
		}

		final int nextid = mailbox.getNextmailid();
		mailbox.setNextmailid(nextid + 1);
		mail.id = nextid;
		mail.sendtime = System.currentTimeMillis();
		mail.read = MAIL_UNREAD;
		
		final xbean.Mail xmail = xbean.Pod.newMail();
		xmail.setMailid(mailid);
		xmail.setSendtime(mail.sendtime);
		xmail.setTitle(mail.title);
		xmail.setContent(mail.content);
		xmail.setRead(mail.read);
		FBonus.convert(mail.accessory, xmail.getAccessory());

		final Map<Integer, xbean.Mail> mails = mailbox.getMails();
		mails.put(nextid, xmail);
		checkExceedMailBoxSize(roleid, mails);
		
		final SNewMail re = new SNewMail();
		re.mail = mail;
		send(roleid, re);
	}

	public static SystemMail addSystemMail(int mailid, String title, String content, map.msg.Bonus bonus, List<String> params){
		SystemMail systemMail = Pod.newSystemMail();
		systemMail.setId(FIdGen.nextUniqueId());
		systemMail.setSendtime(System.currentTimeMillis());
		systemMail.setMailid(mailid);
		systemMail.setTitle(title);
		systemMail.setContent(content);
		systemMail.getParams().addAll(params);
		FBonus.convert(bonus, systemMail.getBonus());

		Systemmailbox.add(systemMail.getId(), systemMail);
		MailModule.INSTANCE.addSysMail(systemMail);
		return systemMail;
	}

	public static void systemMailSendRecord(long id, long roleId){
		Systemmailbox.get(id).getRecords().add(roleId);
	}
	
	public static void delMails(long roleid, List<Integer> mailids) {
		final xbean.MailBox mailbox = get(roleid);
		final Map<Integer, xbean.Mail> mails = mailbox.getMails();
		for(Iterator<Integer> it = mailids.iterator() ; it.hasNext() ;) {
			if(mails.remove(it.next()) == null) {
				it.remove();
			}
		}
	}
	
	public static ErrorCode obtainMailAccessory(long roleid, List<Integer> mailids, map.msg.Bonus bonus) {
		final xbean.MailBox mailbox = get(roleid);
		final Map<Integer, xbean.Mail> mails = mailbox.getMails();
		for(Iterator<Integer> it = mailids.iterator() ; it.hasNext() ; ) {
			final int id = it.next();
			final xbean.Mail mail = mails.remove(id);
			if(mail != null) {
				xbean.Bonus mailBonus = mail.getAccessory();
				if(!FBonus.addBonus(roleid, Bonus.BindType.of(mailBonus.getBindtype()), new HashMap<Integer, Integer>(mailBonus.getItems()), By.Mail)){
					return ErrorCode.BAG_FULL;
				}
			} else {
				it.remove();
			}
		}
		return ErrorCode.OK;
	}

	public final static int MAIL_UNREAD = 0;
	public final static int MAIL_READ = 1;
	public static ErrorCode readMail(long roleid, int id) {
		final xbean.MailBox mailbox = get(roleid);
		final xbean.Mail mail = mailbox.getMails().get(id);
		if(mail == null)
			return ErrorCode.MAIL_NOT_EXIST;
		if(mail.getRead() == MAIL_READ)
			return ErrorCode.MAIL_HAS_READ;
		mail.setRead(MAIL_READ);
		return ErrorCode.OK;
	}

	public static void sendSystemMailOnLogin(long roleid) {
		long roleCreateTime = Roleinfos.selectCreatetime(roleid);
		long maxid = get(roleid).getMaxsysmail();
		MailModule.INSTANCE.getSystemMails().stream()
				.filter(mail -> mail.getSendtime() > roleCreateTime && mail.getId() > maxid)
				.forEach(mail -> addMail(roleid, mail));
	}

}
