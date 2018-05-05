package lx.gs.logger;

public final class By {
	private final int type;
	private final int param1;
	private final int param2;

	public final static By
			Rmb_Pay = of(1)
			,Cmd = of(2)
			,Arena_Period_ShengWang_Task = of(3)
			,Arena_Cost = of(4)
			,Arena_Challenge_Success = of(5)
			,Arena_DayChallenge_Count = of(6)
			,Arena_Refresh = of(7)
			,Continue_Login = of(8)
			,Grow_Plan = of(9)
			,MonthCard_Reward = of(10)
			,Active_Award = of(11)
			,Pay_And_Cost_Total = of(12)
			,NewerPlayer = of(13)
			,Add_Sign = of(14)
			,Signin = of(15)
			,Equip_Perfuse = of(16)
			,Equip_Anneal = of(17)
			,Equip_Recommand = of(18)
			, Equip_Upgrade = of(19)
			,Equip_Split = of(20)
			,Exchange_Del = of(21)
			,Exchange_Add = of(22)
			,Exchange_Buy = of(23)
			,Exchange_Sell = of(24)
			,Kill_Monster = of(25)
			,Fragment_Add = of(26)
			,Fragment_Sell = of(27)
			,Fragment_Compount = of(28)
			,Gm = of(29)
			,Item_Add = of(30)
			,Item_Sell = of(31)
			,Item_Use = of(32)
			,Pick_Card = of(33)
			,Skill_Upgrade = of(34)
			,Achievement_Reward = of(35)
			,Find_Back = of(36)
			,Mail = of(37)
			,Rank = of(38)
			,Create_Family = of(39)
			,Family_LaunchActivity = of(40)
			,Family_RaiseAnimal = of(41)
			,Family_UpMallLevel = of(42)
			,Family_StudySkill = of(43)
			,Family_UpGradeSkill = of(44)
			,Family_Pray = of(45)
			,Achievement_Evolve = of(46)
			,Family_EvolveAnimal = of(47)
			,Idol_Award = of(48)
			,Pet_Sell = of(48)
			,Pet_Call = of(49)
			,Pet_UpLevel = of(50)
			,Pet_UpLevel_Skill = of(51)
			,Pet_UpLevel_Star = of(52)
			,Pet_Broken = of(53)
			,Pet_BuySkin = of(54)
			,Pet_Study_Talent = of(55)
			,Pet_Wake = of(56)
			,Pet_Wash = of(57)
			, Talisman_Awake =of(80)
			, Talisman_Star_Evolve =of(81)
			, Talisman_Xilian =of(82)
			, Talisman_Recycle =of(83)
			,Depot_Take = of(90)
			,Ectype = of(91)
			,Task = of(92)
			,ClimbTower = of(93)
			, TreasureBowl = of(94)
			,Wish = of(95)
			,GuiYuan = of(96)
			, Talisman_Add_Normal_Exp =of(97)
			,Revive = of(98)
			,Pet_Recycle=of(99)
			,Open_Ectype = of(100)
			,World_Transport = of(101)
			,Pick_Drop = of(102)
			,Accessory_Wash = of(103)
			, Talisman_Change_Luck = of(104)
			,Buy_Dress=of(105)
			,Buy_Ride=of(106)
			,Family_UpLevel_Skill=of(107)
			,Amulet_Wash =of(108)
			,Marry_Buy=of(109)
			,Marry=of(110)
			,Bag_Cell_Open=of(111)
			,Pet_Evolve_Skill=of(112)
			,Pet_Equip_Active=of(123)
			,TiLi_Interval_Add=of(124)
			,Role_Level_Up=of(125)
			,Family_Task=of(126)
			,Enhance_Jade=of(127)
			,Evolve_Jade=of(128)
			,Enhance_Jewelry=of(129)
			,Hunt_Jewelry=of(130)
			,Summon_Hunter=of(131)
			,HuiWu_Enroll =of(132)
			,HuiWu_Worship=of(133)
			,Reset_Daily_Open_Count=of(134)
			,Crowd_Family=of(135)
			,Crowd_Family_Return=of(136)
			,Arena_Rank=of(137)
			,Talisman_Upgrade_Skill =of(138)
			,PersonalBoss=of(139)
			,Reset_Story_Open_Count=of(140)
			,Pet_Equip=of(141)
			,Family_Level_Bonus=of(142)
			,Role_Buy_Tili=of(143)
		    ,Chapter_Reward=of(144)
		    ,From_Map=of(145)
            ,Eat_Baozi=of(146)
		    ,Hero_Change_Ectype=of(147)
		    ,Hero_Refresh_Award =of(148)
		    ,Hero_Gain_Award =of(149)
			,Guard_Tower_Win=of(150)
			,Guard_Tower_Win_Additional=of(151)
            ,Team_Fight_Day_Reward=of(152)
            ,Team_Fight_Week_Reward=of(153)
			,Guard_Tower_Zone_Check=of(154)
			,Eat_in_Ectype=of(155)
            ,Team_Fight_Result=of(156)
            ,Active_Story_Note=of(157)
            ,Attack_City_Begin=of(158)
			,Team_Speed_Reward=of(159)
            ,Shake_Money_Tree=of(160)
            ,Star_Wish=of(161)
            ,Send_Flower=of(162)
            ,Divorce=of(163)
            ,Cost_Caili=of(164)
            ,ReName=of(165)
            ,Operator_Activity=of(166)
            ,ActiveCode=of(167)
            ,Vip_Package_buy=of(168)
            ,Sweep_ClimbTower=of(169)
            ,Pk=of(170)
            ,Pay_Return=of(171)
            ,Chat_Face=of(172)
            ,Kill_Exp_Monster=of(173)
            ,Family_Team=of(174)
            ,Top_Channel_Chat=of(175)
	;

	public By(int type, int param1, int param2) {
		this.type = type;
		this.param1 = param1;
		this.param2 = param2;
	}

	public static By of(int type, int param1) {
		return new By(type, param1, 0);
	}

	public static By of(int type) {
		return new By(type, 0, 0);
	}

	public static By ofCmd(int moduleid, int cmdid) {
		return new By(2, moduleid, cmdid);
	}

	public int getType() {
		return type;
	}

	public int getParam1() {
		return param1;
	}

	public int getParam2() {
		return param2;
	}
}
