local insert = table.insert
local fmt = string.format 
local concat = table.concat
local tostring = tostring
local schar = string.char
local sbyte = string.byte
local find = string.find
local sub = string.sub
local type = type
local pairs = pairs
local ipairs = ipairs
local date = os.date
local print = print
local require = require

local traceback = debug.traceback
local xpcall = xpcall


local function errhandler(e)
    print(traceback())
end

local function my_xpcall(func, data)
    return xpcall(function() func(data) end, errhandler)
end

local function array_to_set(t)
    local r = {}
    for _, v in ipairs(t) do
        r[v] = true
    end
    return r
end

local function tolong(s)
    local n = 0
    for i = #s, 1, -1 do
        n = n * 256 + sbyte(s, i)
    end
    return n
end

local function bytes_to_string (bytes)
  local d = {}
  for i = 0, bytes.Length - 1 do
    local x = bytes[i]
    insert(d, schar(x >= 0 and x or x + 256))
  end
  return concat(d)
end

--local function string_to_bytes(s)
--    local bytes = Byte[#s]
--    for i = 1, #s do
--        bytes[i - 1] = sbyte(s, i)
--    end
--    return bytes
--end

--local _test_string = "abcdefg\50\100\200"
--assert(_test_string == bytes_to_string(string_to_bytes(_test_string)))

local function strtime(fmt, t)
    return date(fmt, t)
end

local function strtime1(t)
    return date("%Y-%m-%d %H:%M:%S", t)
end

-- 返回 hh:ss
local function strtime_inday(t)
    return date("%H:%M", t)
end

local function get_or_create(namespace)
  local t = _G
  local idx = 1
  while true do
    local start, ends = find(namespace, ".", idx, true)
    local subname = sub(namespace, idx, start and start - 1)
    local subt = t[subname]
    if not subt then
      subt = {}
      t[subname] = subt
    end
    t = subt
    if start then 
      idx = ends + 1
    else 
      return t
    end
  end
end

local function deep_copy_to(src, dst)
    for k, v in pairs(src) do
        if type(v) ~= "table" then
            dst[k] = v
        else
            dst[k] = dst[k] or {}
            deep_copy_to(v, dst[k])
        end
    end
end

local function shallow_copy_to(src, dst)
    for k, v in pairs(src) do
        dst[k] = v
    end
end

local function clear_table(t)
  for k, _ in pairs(t) do
    t[k] = nil
  end
end

local function copy_table(src)
	local inst={};
	local k, v;
	for k, v in pairs(src) do
		if type(v) == "table" then
			inst[k] = copy_table(v);
		else
			inst[k] = v;
		end
	end
	local mt = getmetatable(src);
	setmetatable(inst, mt);
	return inst;
end

local function create_obj(template, obj)
	local inst = obj or {};
	local k, v;
	for k, v in pairs(template) do
		if (not inst[k]) and type(v) ~= "function" then
			if type(v)== "table" and v ~= template then
				inst[k] = copy_table(v);
			end
		end
	end
	setmetatable(inst, template);
	template.__index = template;
	return inst;
end

local function Max(a,b)
    if a>=b then
        return a
    else
        return b
    end
end

local function Min(a,b)
    if a<=b then
        return a
    else
        return b
    end
end

local function insidepolygon(polygon,p)
--    printyellow("insidepolygon")
--    printt(polygon)
--    printyellow("polygon.count:",#polygon)
    local N=#polygon
    local counter = 0
    local i
    local xinters
    local p1
    local p2
    p1 = polygon[1]
    for i=2,(N+1) do
        if (i%N==0) then
            p2=polygon[i]
        else
            p2 = polygon[i % N]
        end
        if (p.z > Min(p1.z,p2.z)) then
            if (p.z <= Max(p1.z,p2.z)) then
                if (p.x <= Max(p1.x,p2.x)) then
                    if (p1.z ~= p2.z) then
                        xinters = (p.z-p1.z)*(p2.x-p1.x)/(p2.z-p1.z)+p1.x
                        if ((p1.x == p2.x) or (p.x <= xinters)) then
                            counter=counter+1
                        end
                    end
                end
            end            
        end
        p1 = p2
    end
    if (counter % 2 == 0) then
        return false
    else
        return true
    end
end

--判断一个点是否在一个任意多边形内
local function insideAnyPolygon(polygon,p)
    local count = 0
    local n=#polygon
    local a
    local b
    a=polygon[1]
    for i=2,n+1 do 
        if (i%n==0) then
            b = polygon[i]
        else
            b = polygon[i % n]
        end
        if((a.x <= p.x and p.x <= b.x) or (b.x <= p.x and p.x <= a.x)) then
            local r = (p.x - a.x) * (a.z - b.z) - (p.z - a.z) * (a.x - b.x)
            if (r == 0) then
                -- 在边上
                if (a.x ~= p.x or p.x ~= b.x or (a.z <= p.z and p.z <= b.z) or (b.z <= p.z and p.z <= a.z)) then
                    return true
                end
            elseif (r/(a.x-b.x) > 0) then
                count=count+1
            end
        end
        a=b
    end
    return (count%2==1)
end

--获取枚举名
local function getenumname(enumtype,enumvalue)
    for name,value in pairs(enumtype) do 
        if value == enumvalue then 
            return name
        end 
    end
    return ""
end

return {
  bytes_to_string = bytes_to_string,
  array_to_set = array_to_set,
  strtime = strtime,
  strtime1 = strtime1,
  strtime_inday = strtime_inday,
  deep_copy_to = deep_copy_to,
  shallow_copy_to = shallow_copy_to,
  clear_table = clear_table,
  tolong = tolong,
  get_or_create = get_or_create,
  xpcall = my_xpcall,
  copy_table = copy_table,
  create_obj = create_obj,
  insidepolygon=insidepolygon,
  insideAnyPolygon=insideAnyPolygon,
  getenumname = getenumname,
}