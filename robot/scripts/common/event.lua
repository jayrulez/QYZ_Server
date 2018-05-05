local require = require
local pairs = pairs
local print = print
local logError = logError

local Class = require "common.class"
local utils = require "common.utils"

local event = Class:new()

function event:__new(name)
	self.name = name or "__unknown__"
    self.next_id = 0
    self.id_listener_set_map = {}
    self.name_listener_set_map = {}
end

function event:add(target, func)
    local eid = self.next_id + 1
    self.next_id = eid
    local target_events = self.name_listener_set_map[target]
    if not target_events then
        target_events = {[eid] = func}
        self.name_listener_set_map[target] = target_events
    else
        target_events[eid] = func
    end 
    self.id_listener_set_map[eid] = target_events
    --print("[event:add] add event listener.", self.name, target, eid)
    return eid
end

function event:remove(eid)
    --print("[event:remove]remove event listener.", self.name, eid)
    local sets = self.id_listener_set_map[eid]
    if sets then 
        self.id_listener_set_map[eid] = nil
        sets[eid] = nil
    else
        logError("event.remove %s .can'find eid:%d" , self.name, eid)
    end
end

function event:trigger(target, data)
    local target_events = self.name_listener_set_map[target]
    if not target_events then        
        --print("[event:trigger]event no listeners:", target)
        return 
    end
    for _, func in pairs(target_events) do
        --print("[event:trigger] ", self.name, target, _, func)
        utils.xpcall(func, data)
    end    
end

local global_event = event:new("__global")
-- 如果不指定事件名,则创建一个匿名事件
function event.new_simple(name)
    name = name or {}
    return {
        add = function (self, func)
            return global_event:add(name, func)
        end,
        remove = function (self, eid)
            global_event:remove(eid)
        end,
        trigger = function (self, data)
            global_event:trigger(name, data)
        end
    }
end

return event