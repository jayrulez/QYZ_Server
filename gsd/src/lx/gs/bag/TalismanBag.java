package lx.gs.bag;

import cfg.bag.BagType;
import cfg.talisman.TalismanBasic;
import com.goldhuman.Common.Marshal.Marshal;
import lx.gs.talisman.FTalisman;
import xbean.RoleTalismanBag;
import xbean.RoleTalismanDepot;
import xbean.Talisman;
import xtable.Roletalismandepot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jin Shuai
 */
public class TalismanBag extends StorageBag<Talisman> {

    private Comparator<Talisman> SORT_COMPARATOR = (talisman1, talisman2) -> {
        TalismanBasic model1 = FTalisman.getModelById(talisman1.getModelid());
        TalismanBasic model2 = FTalisman.getModelById(talisman2.getModelid());
        if (model1.quality != model2.quality) return Integer.compare(model1.quality, model2.quality) * -1;
        return Integer.compare(talisman1.getNormallevel(), talisman2.getNormallevel()) * -1;
    };

    public TalismanBag(long roleid, int bagType, int capacity) {
        super(roleid, bagType, capacity);
    }

    @Override
    public int getMaxStack(Talisman talisman) {
        return 1;
    }

    @Override
    public int getNum(Talisman item) {
        return 1;
    }

    @Override
    public void addCapacity(int add) {
        switch (bagType){
            case BagType.TALISMAN:{
                RoleTalismanBag bag = FTalisman.getDbBag(roleid);
                bag.setCapacity(bag.getCapacity() + add);
                break;
            }
            case BagType.TALISMAN_BODY:{
                RoleTalismanDepot bag = Roletalismandepot.get(roleid);
                bag.setCapacity(bag.getCapacity() + add);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public long getId(Talisman talisman) {
        return talisman.getTalismanid();
    }

    @Override
    public int getModelId(Talisman talisman) {
        return talisman.getModelid();
    }

    @Override
    public boolean isBind(Talisman talisman) {
        return talisman.getIsbind();
    }

    @Override
    public int getPosition(Talisman talisman) {
        return talisman.getPos();
    }

    @Override
    public void setPosition(Talisman talisman, int pos) {
        talisman.setPos(pos);
    }

    @Override
    protected Comparator<Talisman> getComparator() {
        return SORT_COMPARATOR;
    }

    @Override
    public Marshal convert(Talisman talisman) {
        return FTalisman.convert(talisman);
    }

    @Override
    protected List<Talisman> createItem(int modelId, int num, Boolean isBind, Long expireTime) {
        List<Talisman> ret = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            ret.add(FTalisman.createTalisman(roleid, modelId, isBind, expireTime));
        }
        return ret;
    }

    @Override
    public int getPrice(Talisman talisman) {
        throw new RuntimeException("can't sell talisman");
    }

    @Override
    public long getExpiretime(Talisman talisman) {
        return talisman.getExpiretime();
    }

    @Override
    protected int getQuality(Talisman talisman) {
        return FTalisman.getModelById(talisman.getModelid()).quality;
    }

}
