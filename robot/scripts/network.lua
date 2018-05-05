local print = print
local require = require
local error = error
local os = require "common.octets"
local message = require "common.message"
local allmsg = require "msg.allmsg"
local gameevent = require "gameevent"

--local serverInfos = require "cfg.server"
--local uimanager = require("uimanager")
local create_by_id = message.create_by_id
local dispatch = message.dispatch
local selectedServer
local session
--local serverlist = serverInfos.serverlist
local reconnectCount
local reconnected
local roleLogin
local lastPingTime = 0
local authok
local PING_INTERVAL = 10
local MapContexProtocolIndexSet


local function add_listener(msg_type_name, func)
	return message.add_listener(msg_type_name, func)
end

local function remove_listener(id)
	message.remove_listener(id)
end

local function add_listeners(listeners)
	return message.add_listeners(listeners)
end

local function remove_listeners(ids)
	message.remove_listeners(ids)
end

local function on_connect()
	printyellow("connect succ")
	reconnectCount = 0
end

local function on_disconnect()
	printyellow("on_disconnect")
end

local function onmsg_RoleProtocols(proto)
    local roleid = proto.roleid

    local octs = proto.data
    local stream = os:new(octs)
    local typeid = proto.ptype
    local msg = create_by_id(typeid)
    if not msg then
        error("unknown RoleProtocol message. id" .. typeid .. ", size" .. #octs)
    end
    msg:_decode(stream)
    if msg.roleid then
        --error("RoleProtocol.roleid override type:" .. typeid .. "'s field roleid")
    end
    msg.roleid = roleid
    msg._username = proto._username

    --print("=== recv RoleProtocol.", msg)
    --[[
    if Local and Local.LogProtocol then
        print("=== recv RoleProtocol.", msg)
    end
    --]]
    if string.match(msg._name, "map.msg.SEnter") then
        MapContexProtocolIndexSet:clear()
    end
    dispatch(msg)
end

local function onmsg_MapContexProtocol(proto)
	if MapContexProtocolIndexSet:find(proto.index) then
		return
	end
	local octs = proto.data
	local stream = os:new(octs)
	local typeid = stream:pop_int()
	local msg = create_by_id(typeid)
	if not msg then
		error("unknown MapContexProtocol message. id" .. typeid .. ", size" .. #octs)
	end
	msg:_decode(stream)
	if Local and Local.LogProtocol then
		print("=== recv MapContexProtocol.", msg)
	end
	dispatch(msg)
end

local function onmsg_MapContexProtocols(proto)
	local octs = proto.data
	local stream = os:new(octs)
	for i = 0, proto.count-1 do
		if not MapContexProtocolIndexSet:find(proto.index) then
			local typeid = stream:pop_int()
			local msg = create_by_id(typeid)
			if not msg then
				error("unknown MapContexProtocols message. id" .. typeid .. ", size" .. #octs)
			end
			msg:_decode(stream)
			if Local and Local.LogProtocol then
				print("=== recv MapContexProtocols.", msg)
			end
			dispatch(msg)
			MapContexProtocolIndexSet:insert(i)
		end
	end
end

local function onmsg_SPing(msg)
end

local function start_keepalive()
	--print("start_keepalive")
end

local function init()
	--gameevent.evt_update:add(update)
	add_listener("map.msg.RoleProtocols", onmsg_RoleProtocols)
	add_listener("map.msg.MapContexProtocol", onmsg_MapContexProtocol)
	add_listener("map.msg.MapContexProtocols", onmsg_MapContexProtocols)
	add_listener("lx.gs.SPing", onmsg_SPing)
	--session = Aio.Session.Instance
    --selectedServer = serverlist[UserConfig.DefaultLogin]
	reconnected = false
	reconnectCount = 0
	roleLogin = false
	lastPingTime = 0
	MapContexProtocolIndexSet = newset()
	--connect()
end

local function setReconnected(value)
	reconnected = value
end

local function getReconnected()
	return reconnected
end

local function setAuthOk(value)
	authok = value
end

local function getAuthOk()
	return authok
end

local function setRoleLogin(value)
	roleLogin = value
end

return {
	init = init,
	--send = send,
	--create_and_send = create_and_send,
	add_listener = add_listener,
    remove_listener = remove_listener,
    add_listeners = add_listeners,
    remove_listeners = remove_listeners,
	connect = connect,
	start_keepalive = start_keepalive,
	--close = close,
    --setSelectedServer = setSelectedServer,
    --reconnect = reconnect,
	--getSelectedServer = getSelectedServer,
	--setAutoReconnect = setAutoReconnect,
	setReconnected = setReconnected,
	getReconnected = getReconnected,
	setRoleLogin = setRoleLogin,
	setAuthOk = setAuthOk,
	getAuthOk = getAuthOk,
}

