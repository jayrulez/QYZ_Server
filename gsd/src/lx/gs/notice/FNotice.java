package lx.gs.notice;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;
import common.TaskQueue;
import gnet.link.Onlines;
import gnet.link.Role;
import lx.gs.notice.msg.SNotice;
import xdb.Trace;
import xio.Protocol;

import java.util.concurrent.TimeUnit;

public final class FNotice {
	
	public static xbean.RoleNotice get(long roleid) {
		xbean.RoleNotice info = xtable.Rolenotice.get(roleid);
		if(info == null) {
			info = xbean.Pod.newRoleNotice();
			xtable.Rolenotice.insert(roleid, info);
		}
		return info;
	}
	
	
	public static void sendNotice(long roleid, xio.Protocol content) {
		final Role role = Onlines.getInstance().find(roleid);
		final OctetsStream os = new OctetsStream();
		os.compact_uint32(content.getType());
		os.marshal(content);
		if(role != null) {
//			final SNotice re = new SNotice();
//
//			re.notices.add(new Notice(os));
			role.send(content);
		} else {
			addOfflineNotice(roleid, os);
		}
	}
	
	public static void addOfflineNotice(long roleid, Octets os) {
		final xbean.RoleNotice info = get(roleid);
		final xbean.Notice notice = xbean.Pod.newNotice();
		notice.setDataCopy(os.getBytes());
		info.getNotices().add(notice);
	}
	
	public static xio.Protocol createSNoticeAndClear(long roleid) throws Exception {//unmarshal and send protocol
		final xbean.RoleNotice info = get(roleid);
		final SNotice re = new SNotice();
		for(xbean.Notice n : info.getNotices()) {
            try {
                OctetsStream data = OctetsStream.wrap(Octets.wrap(n.getDataCopy()));
                int type = data.uncompact_uint32();
                Protocol.Stub pro = ((Protocol.Coder) gnet.link.Onlines.getInstance().getCoder()).getStub(type);
                Protocol p = pro.newInstance();
                p.unmarshal(data);
                TaskQueue.getScheduleExecutor().schedule(() -> gnet.link.Onlines.getInstance().send(roleid, p), 20 , TimeUnit.SECONDS);
//                gnet.link.Onlines.getInstance().send(roleid, p);
            } catch (Exception e) {
                Trace.error("FNotice.createSNoticeAndClear unmarshal fail.", e);
            }
//			re.notices.add(new Notice(Octets.wrap(n.getDataCopy())));
		}
		info.getNotices().clear();
		return re;
	}
}
