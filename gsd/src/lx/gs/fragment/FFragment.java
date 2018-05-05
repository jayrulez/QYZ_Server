package lx.gs.fragment;

import cfg.CfgMgr;
import cfg.Const;
import cfg.bag.BagType;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.Marshal;
import common.Utils;
import lx.gs.bag.AbstractBag;
import lx.gs.bag.FragmentBag;
import lx.gs.idgen.FIdGen;
import xbean.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 碎片模块
 *
 * @author Jin Shuai
 */
public class FFragment {

    /**
     * 根据角色返回对应的背包
     *
     * @return
     */
    public static FragmentBag getFragmentBag(long roleId) {
        return new FragmentBag(roleId, BagType.FRAGMENT, getDbBag(roleId).getCapacity());
    }

    public static xbean.RoleFragmentBag getDbBag(long roleId) {
        return xtable.Rolefragmentbag.get(roleId);
    }

    public static Marshal convert(Fragment slot) {
        lx.gs.fragment.Fragment fragment = new lx.gs.fragment.Fragment();
        fragment.fragmentid = slot.getFragmentid();
        fragment.modelid = slot.getModelid();
        fragment.count = slot.getCount();
        fragment.isbind = slot.getIsbind() ? Const.TRUE : Const.FALSE;
        fragment.expiretime = slot.getExpiretime();
        fragment.position = slot.getPosition();
        return fragment;
    }

    public static cfg.equip.Fragment getModelById(int modelId) {
        return CfgMgr.fragment.get(modelId);
    }

    private static List<xbean.Fragment> createFragment(int modelId, int num) {
        cfg.equip.Fragment model = getModelById(modelId);
        if(model == null){
            throw new IllegalArgumentException("Fragment config id not found in config. " + modelId);
        }
        List<xbean.Fragment> result = new ArrayList<>();
        if (num <= 0) return result;
        int remain = num;
        int maxStack = getMaxStack(model);
        while (remain > 0) {
            xbean.Fragment newitem = xbean.Pod.newFragment();
            newitem.setModelid(modelId);
            newitem.setIsbind(model.bindtype.bindtype == EItemBindType.BOUND);
            newitem.setPosition(AbstractBag.INVALID_POS);
            if (remain > maxStack) {
                newitem.setCount(maxStack);
            } else {
                newitem.setCount(remain);
            }
            result.add(newitem);
            remain -= newitem.getCount();
        }
        return result;
    }

    public static List<xbean.Fragment> createFragment(long roleId, int modelId, int num, boolean isBind) {
        return createFragment(roleId, modelId, num, isBind, null);
    }

    public static List<xbean.Fragment> createFragment(long roleId, int modelId, int num, Boolean isBind, Long expireTime) {
        List<xbean.Fragment> result = createFragment(modelId, num);
        result.forEach(fragment -> {
            fragment.setFragmentid(FIdGen.allocItemId(roleId));
            if (isBind != null) fragment.setIsbind(isBind);
            if (expireTime != null) fragment.setExpiretime(expireTime);
        });
        return result;
    }

    public static int getMaxStack(cfg.equip.Fragment model) {
        return Utils.selfOrMin(model.maxpile);
    }
}
