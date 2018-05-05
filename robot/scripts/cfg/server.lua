local serverlist = {		
		{ name="黄强服", host="10.241.69.16", port=10061,  test=1,serverState=1,isNew = true },            --1
		{ name="策划服", host="10.241.69.16", port=10063,  test=1,serverState=1,isNew = true },            --2
		{ name="熊杰庆服", host="10.241.69.16", port=10068, test=1,serverState=2,isNew = true },           --3
		{ name="金帅服", host="10.241.69.16", port=10072, test=1,serverState=2,isNew = true},              --4
		{ name="爆满服", host="10.241.69.16", port=10078, test=1,serverState=2,isNew = true},              --5
        { name="陆程服", host="10.241.69.16", port=10065,  test=1,serverState=2,isNew = false },           --6
		{ name="外网测试服", host="124.193.167.190", port=10062, test=1,serverState=2,isNew = true},       --7
		{ name="文化部测试外网", host="124.193.167.190", port=10060, test=1,serverState=2,isNew = true},   --8
		{ name="郭旭服", host="10.241.69.16",port=10066,test=1,serverState=1,isNew = false },              --9
		{ name="陈少柯服", host="10.241.69.16", port=10067, test=1,serverState=1,isNew = true },           --10
		{ name="张延秀服", host="10.241.69.16", port=10069, test=1,serverState=2,isNew = false},           --11
		{ name="杨默臣服", host="10.241.69.16", port=10070, test=1,serverState=2,isNew = false},           --12
		{ name="测试1服", host="10.241.69.16", port=10071, test=1,serverState=2,isNew = false},            --13
		{ name="潭子服", host="10.241.69.16", port=10073, test=1,serverState=2,isNew = false},             --14
		{ name="康盛泽", host="10.241.69.16", port=10075, test=1,serverState=2,isNew = true},              --15
		{ name="张珽舰", host="10.241.69.16", port=10076, test=1,serverState=2,isNew = true},              --16
		{ name="王永辛服", host="10.241.69.16", port=10077, test=1,serverState=2,isNew = true},            --17
		{ name="文化部测试内网", host="10.12.3.122", port=10060, test=1,serverState=2,isNew = true},       --18
        { name="鲁一服", host="10.241.69.16", port=10064,  test=1,serverState=1,isNew = true },            --19
		{ name="BI测试服", host="124.243.242.31", port=10061,  test=1,serverState=1,isNew = true },        --20
}

local logserver = {host="10.12.3.122", port="10031"}

return
{
    serverlist = serverlist,
	logserver = logserver,
}
