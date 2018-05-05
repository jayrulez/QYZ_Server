local print = print
local gameevent = require "gameevent"
local message = require "common.message"
--local login = require "login"
local LinkClient = luajava.bindClass("robot.LinkClient")
 
local token = "robot"
local plattype = gnet.PlatType.TEST
local deviceid = "pc-robot"
local platform = "pc"
local oss = "windows 7"

local NEVER_SNED = 1.0e12 
local lastRecKeepaliveTime = 0
local isOnline
local authok
 
 local function onmsg_Challenge(d)
    --print("[auth:onmsg_Challenge] receive Challenge ...")
	version = d.version
	serverload = d.serverload.load
    
    --print("[auth:onmsg_Challenge] send Response ...")
	local re = gnet.Response({
		user_identity = d._username,
		token = token,
		plattype = { plat = plattype },
		deviceid = deviceid,
		os = oss,
		platform = platform
	})
	message.send(d._username, re, false)
 end
 
 local function onmsg_KeyExchange(d)
    --print("[auth:onmsg_KeyExchange] receive KeyExchange ...")
 	local nonce = LinkClient:genKeyExchangeNonceAndSetInOutSecurity(d._username, d._username, token, d.nonce)
 	local kick = 1
    --print("[auth:onmsg_KeyExchange] send KeyExchange ...")
 	message.create_and_send(d._username, "gnet.KeyExchange", {nonce = nonce, kick_olduser = kick}, false)
 end 

 local function onmsg_OnlineAnnounce(d)
    --print(string.format("[auth:onmsg_OnlineAnnounce] onlineannounce username =%s.", d._username))
    message.create_and_send(d._username, "lx.gs.login.CGetRoleList")
    
    --[[
    isOnline = true
	lastRecKeepaliveTime = 0
	if authok then
		print("[auth:onmsg_OnlineAnnounce] auth onlineannounce ok.")
		local roleid = login.get_loginrole()
		if roleid == 0 then 
			--print("[auth:onmsg_OnlineAnnounce] network.create_and_send CGetRoleList.")
            message.create_and_send(d._username, "lx.gs.login.CGetRoleList")
		else
			local count = message.getProtocolCount()
			local re = lx.gs.login.CRoleRelogin(
			{ 
				roleid = roleid,
				receivedmessagecount = count,
			})
			--print("[auth:onmsg_OnlineAnnounce] network.send CGetRoleList.")
			--network.setReconnected(true)
            message.send(d._username, re)
		end
	else
		--print("[auth:onmsg_OnlineAnnounce] auth onlineannounce not ok.")
		--network.setReconnected(false)
		--print("[auth:onmsg_OnlineAnnounce] network.create_and_send CGetRoleList.")
		authok = true
        message.create_and_send(d._username, "lx.gs.login.CGetRoleList")
	end
    --]]
 end
 
 local function onmsg_ErrorInfo(d)
 	--lastKeepaliveTime = NEVER_SNED
 	--network.close()
 end
 
 local function onmsg_KeepAlive(d)
 --[[
	if isOnline then
		lastRecKeepaliveTime = timeutils.GetLocalTime()
	end
    --]]
	--print("[auth:onmsg_KeepAlive] receive keepalive...")
    --link.Callback.OnKeepAlive();
 end
 
 local function init()
	message.add_listeners({
		{"gnet.Challenge", onmsg_Challenge },
		{"gnet.KeyExchange", onmsg_KeyExchange },
		{"gnet.OnlineAnnounce", onmsg_OnlineAnnounce },
		{"gnet.KeepAlive", onmsg_KeepAlive },
		{"gnet.ErrorInfo", onmsg_ErrorInfo },
	})
 end
 
 return {
	init = init,
 }