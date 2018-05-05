local event = require "common.event"

local evt_update = event.new_simple("evt_update")
local evt_late_update = event.new_simple("evt_late_update")
local evt_second_update = event.new_simple("evt_second_update")

local evt_system_message = event:new("evt_system_message")


return {
	evt_update = evt_update,
	evt_late_update = evt_late_update,
	evt_second_update = evt_second_update,
	evt_system_message = evt_system_message,
}