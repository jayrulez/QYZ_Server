local insert = table.insert
local sbyte = string.byte
local concat = table.concat
local tostring = tostring
loadstring = load

--require "system.wrap"
require "system.math"
require "system.layer"
require "system.list"
--require "system.time"
require "system.vector3"
require "system.vector2"
require "system.quaternion"
require "system.vector4"
require "system.raycast"
require "system.color"
require "system.touch"
require "system.ray"
require "system.plane"
require "system.bounds"
require "system.coroutine"
--require "system.time"
require "system.timer"

function to_readable(s)
    if #s > 32 then
        return "#" .. #s
    else
        local a = {sbyte(s, 1, #s)}
        local all_ascii = true
        for i, b in ipairs(a) do
            if b >= 128 then
                all_ascii = false
                break
            end
        end
        return all_ascii and ("'" .. s .. "'") or ("#" .. #s .. "#" .. concat(a, "."))
    end
end

local to_readable = to_readable

local function dump_atom (x)
    if type(x) == "string" then
        return to_readable(x)
    else
        return tostring(x)
    end
end

local function dump_table_(t)
    local code = {"{"}

    for k, v in pairs(t) do
        if type(v) ~= "table" then
            insert(code, tostring(k) .. "=" .. dump_atom(v) .. ",")
        else
            insert(code, tostring(k) .. "=" .. dump_table_(v) .. ",")
        end
    end
    insert(code, "}")
    return concat(code)
end

dump_table = dump_table_

function printt(t)
    if type(t) == "table" then
        print(dump_table_(t))
    else
        print(t)
    end
end

function newset()
    local reverse = {} 
    local set = {} 
    return setmetatable(set,{__index = {
          insert = function(set,value)
              if not reverse[value] then
                    table.insert(set,value)
                    reverse[value] = table.getn(set)
              end
          end,

          remove = function(set,value)
              local index = reverse[value]
              if index then
                    reverse[value] = nil
                    local top = table.remove(set) 
                    if top ~= value then
                        
                        reverse[top] = index
                        set[index] = top
                    end
              end
          end,

          find = function(set,value)
              local index = reverse[value]
              return (index and true or false)
          end,
		
		  clear = function(set)
			set = {}
			reverse = {}
		  end
		
    }})
end

function enum(t)
    local enumtable = {}
    local enumindex = 0
    local tmp,key,val
    for _,v in ipairs(t) do
        key,val = string.gmatch(v,"([%w_]+)[%s%c]*=[%s%c]*([%w%p%s]+)%c*")()
        if key then
            tmp = "return " .. string.gsub(val,"([%w_]+)",function (x) return enumtable[x] and enumtable[x] or x end)
            enumindex = loadstring(tmp)()
        else
            key = string.gsub(v,"([%w_]+)","%1");
        end
        enumtable[key] = enumindex
        enumindex = enumindex + 1
    end
    return enumtable
end

require "robot.robotenum"
require "robot.robotcfg"
require "map.robotmapconfig"