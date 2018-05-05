package xtable;

// typed table access point
public class Mailbox {
	Mailbox() {
	}

	public static xbean.MailBox get(Long key) {
		return _Tables_.getInstance().mailbox.get(key);
	}

	public static xbean.MailBox get(Long key, xbean.MailBox value) {
		return _Tables_.getInstance().mailbox.get(key, value);
	}

	public static void insert(Long key, xbean.MailBox value) {
		_Tables_.getInstance().mailbox.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().mailbox.delete(key);
	}

	public static boolean add(Long key, xbean.MailBox value) {
		return _Tables_.getInstance().mailbox.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().mailbox.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.MailBox> getCache() {
		return _Tables_.getInstance().mailbox.getCache();
	}

	public static xdb.TTable<Long, xbean.MailBox> getTable() {
		return _Tables_.getInstance().mailbox;
	}

	public static xbean.MailBox select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.MailBox, xbean.MailBox>() {
			public xbean.MailBox get(xbean.MailBox v) { return v.toData(); }
		});
	}

	public static Integer selectNextmailid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.MailBox, Integer>() {
				public Integer get(xbean.MailBox v) { return v.getNextmailid(); }
			});
	}

	public static java.util.Map<Integer, xbean.Mail> selectMails(Long key) {
		return getTable().select(key, new xdb.TField<xbean.MailBox, java.util.Map<Integer, xbean.Mail>>() {
				public java.util.Map<Integer, xbean.Mail> get(xbean.MailBox v) { return v.getMailsAsData(); }
			});
	}

	public static Long selectMaxsysmail(Long key) {
		return getTable().select(key, new xdb.TField<xbean.MailBox, Long>() {
				public Long get(xbean.MailBox v) { return v.getMaxsysmail(); }
			});
	}

}
