local require = require
local os = require "common.octets"
local utils = require "common.utils"
local event = require "common.event"

local insert = table.insert
local concat = table.concat
local fmt = string.format
local find = string.find
local sub = string.sub
local pairs = pairs
local print = print
local setmetatable = setmetatable
local ipairs = ipairs
local tostring = tostring
local _G = _G
local error = error
local type = type
local unpack = unpack
local dump_table = dump_table
local get_or_create = utils.get_or_create

local metas = {}
local metatable_cache = {}
local id2name = {}
local lastsendtime = {} -- {msg.id,Time.time}
local MinSendInterval = 0.2 --最短发送协议时间间隔
local ProtocolCount
local LogProtocol = false

local function add_type(namespace, ttype)
    local tname = ttype.name
    local full_name = not find(tname, ".", 1, true) and (namespace .. "." .. tname) or tname
    local special_name = full_name:gsub("%.", "_")
    if metatable_cache[full_name] then
        error("duplicate message:" .. full_name)
    end
    os:add_type(namespace, ttype)
    ttype.special_name = special_name
    ttype.full_name = full_name

    local id = ttype.id
    if id then
        if id2name[id] then
            error("duplicate message:" .. id .. " " .. special_name)
        end
        id2name[id] = full_name
    end
		
    local new_type = get_or_create(full_name)
	local new_mt = {}
	setmetatable(new_type, new_mt)
	
	new_mt.__index = new_mt
	
	
	new_mt._id = id
	new_mt._name = full_name
	new_mt._encode = function (self, octs)
		octs["push_" .. special_name](octs, self)
	end
	new_mt._decode = function (self, octs)
		local r = octs["pop_" .. special_name](octs)
		for k, v in pairs(r) do
			self[k] = v
		end
	end
	for k, v in pairs(ttype) do 
		if type(k) == "string" then
			new_type[k] = v
		end
	end	
		
	new_mt.__tostring = function(self)
			local k = dump_table(self)
            return "#" .. full_name .. "#" .. k--dump_table(self)
        end
    new_mt.__call = function(self, o)
		local msg = o or {}
		setmetatable(msg, new_mt)
		return msg
	end
	metatable_cache[full_name] = new_mt

    --print("message.add", id, full_name)
end

local function add_namespace(namespace_define)
    local namespace_name = namespace_define.name
    --namespace_name = namespace_name:gsub("%.", "_")
    for _, new_type in ipairs(namespace_define) do
        add_type(namespace_name, new_type)
    end
end

local function create(fullname, o)
    if not fullname or not metatable_cache[fullname] then
     --   print("unknown message :" .. fullname)
        return
    end
	return get_or_create(fullname)(o)
end

local function create_by_id(id, o)
	local full_name = id2name[id]
	if not full_name then
        print("unknown message id:" .. id)
    end
    return get_or_create(full_name)(o)
end

local msg_event = event:new("message")
local function add_listener(msg_name, func)
    return msg_event:add(msg_name, func)
end

local function remove_listener(event_id)
    return msg_event:remove(event_id)
end

local function add_listeners(listeners)
    local r = {}
    for _, v in ipairs(listeners) do
        insert(r, add_listener(v[1], v[2]))
    end
    return r
end

local function remove_listeners(event_ids)
    for _, v in ipairs(event_ids) do
        remove_listener(v)
    end
end

local function dispatch(msg)
	if ProtocolCount==nil then
		ProtocolCount = 1
	else
		ProtocolCount = ProtocolCount + 1
	end
    msg_event:trigger(msg._name, msg)
end

local ignore_protos = 
{
    --link
	["gnet.Challenge"] = true,
	["gnet.Response"] = true,
	["gnet.KeyExchange"] = true, 
	["gnet.OnlineAnnounce"] = true,
	["gnet.KeepAlive"] = true, 
	["lx.gs.SPing"] = true,
	["lx.gs.CPing"] = true,
      
    --login
	["lx.gs.login.CGetRoleList"] = true,
	["lx.gs.login.SGetRoleList"] = true,
	["lx.gs.login.CRandomName"] = true,
	["lx.gs.login.SRandomName"] = true,
	["lx.gs.login.CCreateRole"] = true,
	["lx.gs.login.SCreateRole"] = true,
    
    --ready
	["map.msg.CReady"] = true,
	["map.msg.SReady"] = true,

    --onrolelogin
	["map.msg.RoleProtocols"] = true,
	["lx.gs.achievement.msg.SInfo"] = true,
	["lx.gs.amulet.SGetAmuletInfo"] = true,
	["lx.gs.arena.msg.SInfo"] = true,
	["lx.gs.bag.msg.SSyncItems"] = true,
	["lx.gs.bag.msg.SSyncCapacity"] = true,
	["lx.gs.depot.msg.SSyncGoldCoin"] = true,
	["lx.gs.bonus.msg.SBonusInfo"] = true,
	["lx.gs.dress.SGetDressInfo"] = true,
	["lx.gs.exchange.msg.SInfo"] = true,
	["lx.gs.family.msg.SGetFamilyInfo"] = true,
	["lx.gs.limit.msg.SLimit"] = true,
	["lx.gs.mail.msg.SMailBox"] = true,
	["lx.gs.map.msg.SEctypeInfo"] = true,
	["lx.gs.marriage.msg.SMarriageInfo"] = true,
	["lx.gs.pet.msg.SPetInfo"] = true,
	["lx.gs.rank.msg.SInfo"] = true,
	["lx.gs.role.msg.SGetConfigures"] = true,
	["lx.gs.skill.msg.SInfo"] = true,
	["lx.gs.storynote.msg.SInfo"] = true,
	["lx.gs.talisman.SLuckyInfo"] = true,
	["lx.gs.task.msg.STask"] = true,
	["lx.gs.activity.huiwu.msg.STermInfo"] = true,

    --world
	["lx.gs.activity.msg.SActivity"] = true,
	["lx.gs.activity.worldboss.msg.SWorldBossNotice"] = true,
	["map.msg.SNearbyAgentLeave"] = true,
	["map.msg.SNearbyAgentEnter"] = true,
	["map.msg.CMove"] = true,
	["map.msg.SMove"] = true,
}

local function receive(username, type_id, encoder_data)
    --print("===[message:receive]receive", username, type_id, encoder_data)

    local octs = encoder_data
    local stream = os:new(octs)
    local msg = create_by_id(type_id)
    if not msg then
        error("[ERROR][message:receive] unknown message. id:" .. type_id .. ", size:" .. #octs)
    end
    msg:_decode(stream)
	msg._username = username

    if LogProtocol and not ignore_protos[id2name[type_id]] then
        print("====== recv.", username, msg)
    end
    dispatch(msg)
end


local function getProtocolCount()
	return ProtocolCount
end

local SendScriptMessage = SendScriptMessage

-- no resend while resend = true or nil only
local function send(username, msg, resend)
    --[[ 由于战斗相关协议是不应该去卡时间的，所以先注释掉
    if lastsendtime[msg._id] and Time.time - lastsendtime[msg._id] < MinSendInterval then 
        print(string.format("MinSendInterval is %s s",MinSendInterval))
        return 
    end
    lastsendtime[msg._id] = Time.time
    --]]
    if LogProtocol and not ignore_protos[msg._name] then
	   print("=== send.", username, msg)
	end
    local stream = os:new()
    msg:_encode(stream)
    local encoder_data = stream:bytes()
    --print("send ", username, msg, msg._id)
    SendScriptMessage(username, msg._id, encoder_data, resend ~= false)
end

local function create_and_send(username, full_name, o, resend)
    local msg = create(full_name, o)
    send(username, msg, resend)
end

--[[
local function dump_messages()
    local s = {}
    for _, meta in pairs(metas) do
        if meta.id then
            insert(s, meta.full_name .. ".TYPE,")
        end
    end
    print(concat(s, "\n"))
end
--]]

local function init()
	add_listeners({
	    --{"map.msg.RoleProtocols", onmsg_RoleProtocols},
	}
	)
end

return {
    init = init,
    msgs = id2name,
    metas = metas,
	getProtocolCount = getProtocolCount,
    add_type = add_type,
    add_namespace = add_namespace,
    
    create_by_id = create_by_id,
--    create = create,
    send = send,
	create_and_send = create_and_send,
	dispatch = dispatch,
    receive = receive,

    add_listener = add_listener,
    remove_listener = remove_listener,
    add_listeners = add_listeners,
    remove_listeners = remove_listeners,
	
}
