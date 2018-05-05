-- local ConfigManager = require "cfg.configmanager"


local os = require "cfg.structs"
local AllCsvCfgs

local function loadCsv()
    print("[configmanager:loadCsv] loading config...")
--    printyellow("load csv")
    AllCsvCfgs = require "cfg.configs"
    print("[configmanager:loadCsv] load config finished.")
end

local function init()
    AllCsvCfgs = {}
    loadCsv()
end

local function getConfig(configname)
    --printyellow(configname)
    --printt(AllCsvCfgs[configname])
    if AllCsvCfgs then
        return AllCsvCfgs[configname]
    end
    return nil
end

local function getConfigData(configname,index)
    local config = getConfig(configname)
    if config then
        return config[index]
    end
    return nil
end



local function getProfessionData(faction,gender)
    local professions = getConfig("profession")
    for _,data in pairs(professions) do
        if data.faction == faction and data.gender == gender then
            return data
        end
    end
    return nil
end

local function GetHeadIcon(profession, gender)
    local dataprofession = ConfigManager.getConfigData("profession", profession)
    if not dataprofession then return "" end
    local datamodel = ConfigManager.getConfigData("model",
                                                  gender == cfg.role.GenderType.MALE and dataprofession.modelname or dataprofession.modelname2)
    if not datamodel then return "" end
    return datamodel.headicon
end

return
{
    init              = init,
    getConfig         = getConfig,
    getConfigData     = getConfigData,
    getProfessionData = getProfessionData,
    GetHeadIcon       = GetHeadIcon,
}
