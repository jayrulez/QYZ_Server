require("common.class")

local ipairs = ipairs
local pairs = pairs
local loadstring = loadstring
local tostring = tostring
local error = error

local insert = table.insert
local concat = table.concat

local schar = string.char
local sbyte = string.byte
local sub = string.sub
local fmt = string.format
local sfind = string.find

local fmod = math.fmod
local floor = math.floor

local unpack = unpack

local os = Class:new()

function os:__new(bytes)
  self.data = bytes or ""
  self.more = {}
  self.cur = 1
end

function os:push_size(x)
    if x < 0x80 then 
        self:push_byte(x)
    elseif x < 0x4000 then
        self:push_short(x + 0x8000)
    elseif x < 0x20000000 then
        self:push_int(x + 0xc0000000)
    else 
        self:push_byte(0xe0)
        self:push_int(x)
    end
end

function os:pop_size()
    local m = sbyte(self.data, self.cur)
    if m >= 0xe0 then 
        self:pop_byte()
        return self:pop_int()
    elseif m >= 0xc0 then
        return self:pop_int() + 0x40000000
    elseif m >= 0x80 then
        return self:pop_short() + 0x8000
    else
        return self:pop_byte()
    end
end

function os:push_bool(x)
  insert(self.more, schar(x and 1 or 0))
end 

function os:push_byte(x)
  insert(self.more, schar(x >= 0 and x or x + 256))
end 

function os:push_short(x)
  if x < 0 then
    x = x + 256 * 256
  end
  insert(self.more, schar(floor(x/256), fmod(x, 256)))
end 

function os:push_int(x)
  if x < 0 then
    x = x + 256 * 256 * 256 * 256
  end
  local more = self.more
  local b1 = fmod(x, 256)
  x = floor(x / 256)
  local b2 = fmod(x, 256)
  x = floor(x / 256)
  local b3 = fmod(x, 256)
  x = floor(x / 256)
  local b4 = x
  insert(more, schar(b4, b3, b2, b1))
end 

-- long 以 8 位大端字符串表示
function os:push_long(x)
    local more = self.more
    if x >= 0 then 
      local b1 = fmod(x, 256)
      x = floor(x / 256)
      local b2 = fmod(x, 256)
      x = floor(x / 256)
      local b3 = fmod(x, 256)
      x = floor(x / 256)
      local b4 = fmod(x, 256)
      x = floor(x/256)
      local b5 = fmod(x, 256)
      x = floor(x/256)
      local b6 = fmod(x, 256)
      x = floor(x/256)
      local b7 = fmod(x, 256)
      x = floor(x/256)
      local b8 = floor(x/256)
      insert(more, schar(b8, b7, b6, b5, b4, b3, b2, b1))
    else
        x = -x - 1
      local b1 = 255 - fmod(x, 256)
      x = floor(x / 256)
      local b2 = 255 - fmod(x, 256)
      x = floor(x / 256)
      local b3 = 255 - fmod(x, 256)
      x = floor(x / 256)
      local b4 = 255 - fmod(x, 256)
      x = floor(x/256)
      local b5 = 255 - fmod(x, 256)
      x = floor(x/256)
      local b6 = 255 - fmod(x, 256)
      x = floor(x/256)
      local b7 = 255 - fmod(x, 256)
      x = floor(x/256)
      local b8 = 255 - floor(x/256)
      insert(more, schar(b8, b7, b6, b5, b4, b3, b2, b1))
    end
end 

function os:pop_bool()
  local cur = self.cur
  self.cur = cur + 1
  local b1 = sbyte(self.data, cur)
  return b1 ~= 0
end 

function os:pop_byte()
  local cur = self.cur
  self.cur = cur + 1
  local b1 = sbyte(self.data, cur)
  return b1 < 128 and b1 or b1 - 256
end 

function os:pop_short()
  local cur = self.cur
  self.cur = cur + 2
  local b1, b2 = sbyte(self.data, cur, cur + 1)
  local x = b1 * 256 + b2
  return x < 128 * 256 and x or x - 256 * 256
end 

function os:pop_int()
  local cur = self.cur
  self.cur = cur + 4
  local b1,b2,b3,b4 = sbyte(self.data, cur, cur + 3)
  if nil==b1 or nil==b2 or nil==b3 or nil==b4 then  
        print("[os:pop_int] b1,b2,b3,b4 nil!!!")
  end
  local x = ((b1 * 256 + b2) * 256 + b3) * 256 + b4
  return x < 128 * 256 * 256 * 256 and x or x - 256 * 256 * 256 * 256
end 

function os:pop_long()
  local cur = self.cur
  self.cur = cur + 8
--  return sub(self.data, cur, cur + 7)
  local b1,b2,b3,b4,b5,b6,b7,b8 = sbyte(self.data, cur, cur+7)
  if b1 < 128 then
    return ((((((b1 * 256 + b2) * 256 + b3) * 256 + b4) * 256 + b5) * 256 + b6) * 256 + b7) * 256 + b8
  else 
    b1 = 255 - b1
    b2 = 255 - b2
    b3 = 255 - b3
    b4 = 255 - b4
    b5 = 255 - b5
    b6 = 255 - b6
    b7 = 255 - b7
    b8 = 256 - b8
    return -(((((((b1 * 256 + b2) * 256 + b3) * 256 + b4) * 256 + b5) * 256 + b6) * 256 + b7) * 256 + b8)
  end
end 

local function bytes_to_string (bytes)
  local d = {}
  for i = 0, bytes.Length - 1 do
    local x = bytes[i]
    insert(d, schar(x >= 0 and x or x + 256))
  end
  return concat(d)
end


local net_marshal_float = marshal.float2int
function os:push_float(x)
  self:push_int(net_marshal_float(x))
end 

local net_unmarshal_float = marshal.int2float
function os:pop_float()
  return net_unmarshal_float(self:pop_int())
end 

local net_marshal_double = marshal.double2intint
function os:push_double(x)
	local low, high = net_marshal_double(x)
	self:push_int(low)
	self:push_int(high)
end

local net_unmarshal_double = marshal.intint2double
function os:pop_double()
  local low = self:pop_int()
  local high = self:pop_int()
  return net_unmarshal_double(low, high)
end 


--[[
function os:push_float(x)
  self:push_int(x)
end 

function os:pop_float()
  return self:pop_int()
end 

function os:push_double(x)
    self:push_long(x)
end

function os:pop_double()
  return self:pop_long()
end 

function os:push_string(x)
  local utf16str =  x
  self:push_size(#utf16str)
  insert(self.more, utf16str)
end

function os:pop_string()
  local size = self:pop_size()
  local cur = self.cur
  self.cur = cur + size
  local x = sub(self.data, cur, cur + size - 1)
  return x
end 
--]]

local utf8toutf16 = marshal.utf8toutf16
function os:push_string(x)
  local utf16str =  utf8toutf16(x)
  --local utf16str = x
  self:push_size(#utf16str)
  insert(self.more, utf16str)
end

local utf16toutf8 = marshal.utf16toutf8
function os:pop_string()
  local size = self:pop_size()
  local cur = self.cur
  self.cur = cur + size
  local x = sub(self.data, cur, cur + size - 1)
  return utf16toutf8(x)
  --return x
end 

function os:push_octets(x)
  self:push_size(#x)
  insert(self.more, x)
end

function os:pop_octets()
  local size = self:pop_size()
  local cur = self.cur
  self.cur = cur + size
  return sub(self.data, cur, cur + size - 1)
end 

function os:push_list(x, valueType)
  local size = #x
  self:push_size(size)
  local fun = os["push_" .. valueType]
  for i = 1, size do 
    fun(self, x[i])
  end
end

function os:pop_list(valueType)
  local size = self:pop_size()
  if size >= #self.data then error("pop_list overflow. size:" .. size) end
  local fun = os["pop_" .. valueType]
  local r = {}
  for i = 1, size do
    insert(r, fun(self))
  end
  return r
end

function os:push_set(x, valueType)
  local size = #x
  self:push_size(size)
  local fun = os["push_" .. valueType]
  for i = 1, size do 
    fun(self, x[i])
  end
end 

function os:pop_set(valueType)
  local size = self:pop_size()
  if size >= #self.data then error("pop_list overflow. size:" .. size) end
  local fun = os["pop_" .. valueType]
  local r = {}
  for i = 1, size do
    insert(r, fun(self))
  end
  return r
end

local function get_table_size(t)
  local n = 0
  for _ in pairs(t) do
    n = n + 1
  end 
  return n
end

function os:push_map(x, keyType, valueType)
  local size = get_table_size(x)
  self:push_size(size)
  local fun_key= os["push_" .. keyType]
  local fun_value= os["push_" .. valueType]
  for k, v in pairs(x) do
    fun_key(self, k)
    fun_value(self, v)
  end
end 

function os:pop_map(keyType, valueType)
  local size = self:pop_size()
  if size * 2 > #self.data then error("pop_map overflow. size:" .. size) end
  local fun_key   = os["pop_" .. keyType]
  local fun_value = os["pop_" .. valueType]
  local x = {}
  for i = 1, size do 
      local k = fun_key(self)
      local v = fun_value(self)
      x[k] = v
  end
  return x
end

function os:flat()
  if self.cur > 1 then
    self.data = sub(self.data, self.cur)
    self.cur = 1
  end
  if #self.more > 0 then 
    self.data = self.data .. concat(self.more)
    self.more = {}
  end
  --print("before flat", self.cur, #self.data, #self.more) 
  --print("after flat", self.cur, #self.data, #self.more)
end

function os:size()
  return #self.data - self.cur + 1
end

function os:bytes()
  self:flat()
  return self.data
end

--[[
local s = os:new("")
local x = 5566.88
local a = "hello,world"
s:push_float(x)
s:push_double(x)
s:push_string(a)

s:flat()


local y = s:pop_float()
print("=== float ", x, y)

y = s:pop_double()
print("==== double ", x, y)


local b = s:pop_string()
print("==== string ", a, b)
--]]
--s:push_bool(true)
--s:push_bool(false)
--s:push_byte(12)
--s:push_byte(-12)
--s:push_short(1000)
--s:push_short(-10000)
--s:push_int(19880824)
--s:push_int(-19871218)
--s:push_long(12345678901234)
--s:push_string("hello,world")

--s:push_list({1, 2, 3, 4}, "int")
--s:push_list({"aa", "bb"}, "string")

--s:push_set({1, 2, 3, 4}, "int")
--s:push_set({"aa", "bb"}, "string")



--s:flat()
--print(s:pop_bool(), s:pop_bool())
--print(s:pop_byte(), s:pop_byte())
--print(s:pop_short(), s:pop_short())
--print(s:pop_int(), s:pop_int())
--print(s:pop_long())
--print(s:pop_string())

--print("========")

--local function dump(a)
--    for _, v in ipairs(a) do
--        print(_, v)
--    end
--end
--dump(s:pop_list("int"))
--dump(s:pop_list("string"))
--dump(s:pop_set("int"))
--dump(s:pop_set("string"))

--for _, v in ipairs({1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000}) do
--    local o = os:new()
--    o:push_size(v)
--    o:flat()
--    print("====", v, o:size())
--    local w = o:pop_size()
--    if w ~= v then 
--        print("error", v, w)
--    end
--end
--local a1 = s:pop_byte()
--local a2 = s:pop_short()
--local a3 = s:pop_int()
--local a4 = s:pop_long()
--local a5 = s:pop_string()
--print(a1, a2, a3, a4, a5)


--[[

以下是自定义类的序列化生成工具

namespace 格式例子.

local namespace = 
{
	name = "gs.shop",
	{ name = "Shop",
		{ name="id1", type="int"},
	},
	{ name="JuanTuanInfo",
		{ name="id1", type="byte",},
		{ name="id2", type="short"},
		{ name="id3", type="int"},
		{ name="id4", type="long"},
		{ name="id5", type="float"},
		{ name="id6", type="double"},
		{ name="id7", type="string"},
		{ name="id8", type="bool"},
		{ name="x1", type="list", value="int"},
		{ name="x2", type="set", value="double"},
		{ name="x3", type="map", key="int", value="string"},
		{ name="y1", type="Shop"},
	},
	{ name="CShop", id = 1, -- 有id为message, 无id为bean. 不过add_namespace与add_type都未使用到此字段.
		{ name="shop", type="Shop"},
		{ name="info", type="JuanTuanInfo"},
	},
}	
	
	
--]]


local raw_types = { bool = "false", byte = 0, short = 0, int = 0, long = 0, float = 0.0, double = 0.0, string = "\"\"", octets="\"\"" }
local collection_types = {list = 1, set = 1, map = 2}

local function get_type_default_value(vtype)
  local default = raw_types[vtype]
  if default ~= nil then
    return default
  else
    return {}
  end
end


local function get_full_type(namespace, vtype)
	if raw_types[vtype] ~= nil or not namespace or namespace == "" or sfind(vtype, ".", 1, true) then 
		return vtype:gsub("%.", "_")
	else
		return namespace:gsub("%.", "_") .. "_" .. vtype
	end
end

function os:default_value(ttype)
  return get_type_default_value(ttype)
end

--[[
	ns : namespace
	new_type : message or bean
--]]
function os:add_type(ns, new_type)
	local c = {}
	local name = new_type.name
	local fullname = get_full_type(ns, name)
	insert(c, "local os = Octets")
	
	insert(c, fmt("function os:push_%s(x)", fullname))
	for _, var in ipairs(new_type) do
		local vname = var.name
		local vtype = var.type
		--print(vname, vtype)
		--insert(c, fmt("before push_%s, cur:%s", vtype, self.cur))
		local typeNum = collection_types[vtype]
		if not typeNum then
			insert(c, fmt("self:push_%s(x.%s or %s)", get_full_type(ns, vtype), vname, tostring(raw_types[vtype] or "{}")))
		elseif typeNum == 1 then 
			insert(c, fmt("self:push_%s(x.%s or {}, '%s')", vtype, vname, get_full_type(ns, var.value)))
		else 
			insert(c, fmt("self:push_%s(x.%s or {}, '%s', '%s')", vtype, vname, get_full_type(ns, var.key), get_full_type(ns, var.value)))
		end
		--insert(c, fmt("after push_%s, cur:%s", vtype, self.cur))
	end	
	insert(c, "end")
	
	insert(c, fmt("function os:pop_%s()", fullname))
	insert(c, "local x = {}")
	for _, var in ipairs(new_type) do
		local vname = var.name
		local vtype = var.type
		local typeNum = collection_types[vtype]
		--insert(c, fmt("print('before pop_%s, cur:', self.cur)", vtype))
		if not typeNum then
			insert(c, fmt("x.%s = self:pop_%s()", vname, get_full_type(ns, vtype)))
		elseif typeNum == 1 then 
			insert(c, fmt("x.%s = self:pop_%s('%s')", vname, vtype, get_full_type(ns, var.value)))
		else 
			insert(c, fmt("x.%s = self:pop_%s('%s', '%s')", vname, vtype, get_full_type(ns, var.key), get_full_type(ns, var.value)))
		end
		--insert(c, fmt("print('after pop_%s, cur:', self.cur)", vtype))
	end	
	insert(c, "return x")
	insert(c, "end")
	local code = concat(c, "\n")
	--print(code)
	local ret ,err = loadstring(code)
	if not ret then
	-- print("add_type:", fullname, "fail.", ret, err)
	else
	 ret()	 
	end	
end
--
--function os:add_namespace(namespace_define)
--	local namespace_name = namespace_define.name
--	namespace_name = namespace_name:gsub("%.", "_")
--	for _, new_type in ipairs(namespace_define) do 
--		os:add_type(namespace_name, new_type)
--	end 
--end 

Octets = os

return os
