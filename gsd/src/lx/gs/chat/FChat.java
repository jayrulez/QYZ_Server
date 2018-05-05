package lx.gs.chat;

import cfg.CfgMgr;
import cfg.Const;
import cfg.chat.ChannelType;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Octets;
import common.ErrorCode;
import gm.*;
import gnet.link.Onlines;
import gs.Config;
import lx.gs.bag.AbstractBag;
import lx.gs.bag.FBag;
import lx.gs.chat.msg.*;
import lx.gs.family.FFamily;
import lx.gs.friend.FFriend;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.role.FForbid;
import lx.gs.role.FRole;
import lx.gs.team.FTeam;
import xbean.ForbidItem;
import xbean.Pod;
import xbean.RoleChat;
import xdb.Transaction;
import xtable.Rolechat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public final class FChat {
	public static final String FACE_START = "Face_";

	public static ErrorCode send(long roleid, CChat msg) {
		ForbidItem talkForbid = FForbid.getTalkItem(roleid);
        long now = System.currentTimeMillis();
		if(talkForbid != null && talkForbid.getForbidrealsetime() > now){ //global forbid
			return ErrorCode.CHAT_FORBIDDEN;
		}
        final xbean.RoleInfo info = xtable.Roleinfos.get(roleid);
        if(msg.channel == ChannelType.WORLD){ //limit in world channel
            if(info.getLevel() < CfgMgr.roleconfig.minspeaklevel){ //min level limit
                return ErrorCode.NOT_ENOUGH_LEVEL;
            }
            if(info.getSilentendtime() > now){//talk bereport
                return ErrorCode.CHAT_FORBIDDEN;
            }
            long lasttalttime = info.getLastworldtalktime();
            if(now - lasttalttime < getTalkInterval(info))//talk in cool down
                return ErrorCode.TALK_TOO_BUSY;
            info.setLastworldtalktime(now);
        }

		if (msg.text.length() > cfg.chat.Const.MAX_TEXT_SIZE)
			return ErrorCode.EXCCEED_MAX_TEXT_SIZE;
		if (msg.voice.size() > cfg.chat.Const.MAX_VOICE_SIZE)
			return ErrorCode.EXCCEED_MAX_VOICE_SIZE;

		final int voiceid;
		if(msg.voice.size() > 0) {
			voiceid = ChatModule.NEXT_VOICE_ID.getAndIncrement();
            synchronized (ChatModule.cacheVoices) {
                ChatModule.cacheVoices.put(voiceid, msg.voice);
            }
		} else {
			voiceid = Const.NULL;
		}
		if(msg.text.toLowerCase().startsWith(FACE_START.toLowerCase())){
			try {
				String name = msg.text.split("_")[1];
				if(!get(roleid).getChatface().contains(name)){
					return ErrorCode.PARAM_ERROR;
				}
			} catch (Exception e){}
		}

		final Content content = new Content();
		content.senderid = roleid;
		content.sendername = info.getName();
        content.senderprofession = info.getProfession();
        content.sendergender = info.getGender();
		content.text = msg.text;
		content.bagtype = msg.bagtype;
        final AbstractBag<?> bag = FBag.getBag(roleid, msg.bagtype);
        if(bag != null) {
            content.item = bag.serializeByPos(msg.pos);
        }
		content.voiceid = voiceid;
		content.voiceduration = msg.voiceduration;
        final Config conf = Config.getInstance();
		if(conf.isDebug() && conf.isGmFromChat() && msg.text.startsWith("\\")) {
			final String cmd = msg.text.substring(1);
			try {
				GmSession.init();
				GmSession.current().setRoleid(roleid);
				final GmCmdResult result = GmCmd.exec(cmd);
                result.accept(new GmCmdResultVistor() {
                    @Override
                    public void visit(GmCmdResult.Ok ok) {
                        content.text = ok.result.toString();
                    }

                    @Override
                    public void visit(GmCmdResult.Exception e) {
                        content.text = Utils.getExceptionStackTrace(e.exception);
                    }

                    @Override
                    public void visit(GmCmdResult.Error error) {
                        content.text = error.msg;
                    }

                    @Override
                    public void visit(GmCmdResult.CommonError commonError) {
                        content.text = commonError.msg;
                    }

                    @Override
                    public void visit(GmCmdResult.Exit exit) {
                        content.text = "exit";
                    }
                });
			} finally {
				GmSession.destory();
			}
			content.senderid = 0;
			Onlines.getInstance().send(roleid, new SWorldMessage(content));
			return ErrorCode.OK;
		}
        if(content.text.length() > 0) {
            FLogger.chat(roleid, info, content.text, msg.channel);
        }
        content.text = FRole.replaceSenseWordWithStar(content.text);
        content.text = FRole.replaceFuzzySenseWord(content.text);
        switch(msg.channel) {
			case cfg.chat.ChannelType.PRIVATE: {
                if(FFriend.isInBlackList(msg.receiver, roleid)){
                    return ErrorCode.IN_OTHER_BLACK_LIST;
                }
                if(FFriend.isInBlackList(roleid, msg.receiver)){
                    return ErrorCode.IN_YOUR_BLACK_LIST;
                }
                final String receivername = xtable.Roleinfos.selectName(msg.receiver);
                if(receivername != null)
                    content.receivername = receivername;
                    content.receiverid = msg.receiver;
                Onlines.getInstance().multicast(Arrays.asList(roleid, msg.receiver), new SPrivateMessage(content));
				break;
			}
			case cfg.chat.ChannelType.TEAM: {
				FTeam.broadcastTeamByRoleid(roleid, new STeamMessage(content));
				break;
			}
			case cfg.chat.ChannelType.FAMILY: {
				FFamily.multicastAllFamily(FFamily.getFamilyIdByRoleId(roleid), new SFamilyMessage(content));
				break;
			}
			case cfg.chat.ChannelType.WORLD: {
				Onlines.getInstance().broadcast(new SWorldMessage(content));
				break;
			}
			case cfg.chat.ChannelType.SYSTEM:{
				return ErrorCode.CANNOT_CHAT_IN_SYSTEM_CHANEL;
			}
			case ChannelType.INVITE:{
				if(!FLimit.checkCoolDown(roleid, ConfigId.CHAT, ChannelType.INVITE, CfgMgr.roleconfig.invitecooldown)){
					return ErrorCode.COOL_DOWN;
				}
				sendSystemMessage(new SInviteMsg(roleid, info.getName(), msg.invitechannel,Integer.parseInt(content.text)));
				break;
			}
			case ChannelType.TOP:{
				if(!FRole.checkCostYuanBao(roleid, cfg.chat.Const.TOP_CHANNEL_PRICE, By.Top_Channel_Chat)){
					return ErrorCode.YUANBAO_NOT_ENOUGH;
				}
				sendSystemMessage(new STopMessage(content));
				break;
			}
			default: {
				return ErrorCode.UNKNOWN_CHANNEL;
			}
		}
		return ErrorCode.OK;
	}
	
	public static void sendSystemMessage(xio.Protocol content) {
		//xdb.Trace.info("Chat.sendSystemMessage ");
		Onlines.getInstance().broadcast(content);
	}

	public static ErrorCode getVoice(int voiceid, SGetVoice re) {
	    synchronized (ChatModule.cacheVoices) {
            final Octets voice = ChatModule.cacheVoices.get(voiceid);
            if (voice == null)
                return ErrorCode.VOICE_NOT_FIND;
            re.voice = voice;
            return ErrorCode.OK;
        }
	}

    public static long getTalkInterval(xbean.RoleInfo roleInfo){
        long vipReduce = CfgMgr.roleconfig.intervalreducebyvip.get(roleInfo.getViplevel());
        long totalReduce = 0L;
        List<Integer> level = CfgMgr.roleconfig.intervallevel;
        for(int i = 0; i < level.size(); i++){
            if(roleInfo.getLevel() < level.get(i)){
                totalReduce = vipReduce + CfgMgr.roleconfig.intervalreducebylevel.get(i);
                break;
            }
        }
        return CfgMgr.roleconfig.basicspeakinterval - totalReduce;
    }

	public static RoleChat get(long roleId){
		RoleChat roleChat = Rolechat.get(roleId);
		if(roleChat == null){
			roleChat = Pod.newRoleChat();
			Rolechat.add(roleId, roleChat);
		}
		return roleChat;
	}

	public static void syncChatFace(long roleid) {
		Transaction.tsendWhileCommit(roleid, new SSyncChatFace(new HashSet<>(get(roleid).getChatface())));
	}
}
