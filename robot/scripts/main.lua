require "global"

local LinkClient = luajava.bindClass("robot.LinkClient")
local datastream = require "cfg.datastream"
local gameevent = require "gameevent"

local config_data_root
function create_datastream(path)
	return datastream.new(config_data_root .. "/" .. path)
end

function SendScriptMessage(username, ptype, data)
    --print(string.format("[main:SendScriptMessage] sending username =%s, ptype=%s, data=%s.", username, ptype, data))
	LinkClient:send(username, ptype, data)
end

local evt_update = gameevent.evt_update
local function update(now)
	--print("[main:update] os.time() = ", os.time())
	--print("[main:update] now/1000 = ", now/1000)
	evt_update:trigger(now)	
end

local evt_second_update = gameevent.evt_second_update
local function second_update(now)
	--print("second_update", now)
	evt_second_update:trigger(now)
end

local msg = require "common.message"
local onrecv = msg.receive

local function init(data_root)
	print("[main:init] init", data_root)
	config_data_root = data_root
	require "msg.client_allmsg"
	--require "cfg.configs"
    math.randomseed(os.time())
    	
	for _, module_name in ipairs(require("modules")) do
		local mod = require(module_name)
		if mod then
            print("[main:init] init module:", module_name)
			mod.init()
		else
			logError("[ERROR][main:init] module:%s load fail!", module_name)
		end
	end
end

return {
	init = init,
	update = update,
	second_update = second_update,
	onrecv = onrecv,
}