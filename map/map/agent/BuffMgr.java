package map.agent;

import cfg.CfgMgr;
import map.buff.effect.Effect;
import map.msg.EffectInfo;
import map.msg.SAddEffect;
import map.msg.SRemoveEffect;
import xdb.Trace;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public final class BuffMgr {
	private final Fighter self;

    private boolean dirty = false;
    private final static Effect[] EMPTY_EFFECT = new Effect[0];
    private Effect[] tempEffects = EMPTY_EFFECT;
	private final Map<Integer, Effect> effectsByGroup = new HashMap<>();
	
	public BuffMgr(Fighter self) {
		this.self = self;
	}
	
	public boolean addEffect(Effect newEffect) {
		final int gid = newEffect.getGroupid();
		final Effect oldEffect = effectsByGroup.get(gid);
        if(oldEffect != null) {
            final int newp = newEffect.getPriority();
            final int oldp = oldEffect.getPriority();
            final int newid = newEffect.getId();
            final int oldid = oldEffect.getId();
            if(newp < oldp) {
                // 已有同组高优先级Effect,则直接放弃
                Trace.debug("addEffect fail. because newEffect(effectid:{})'s priority:{} is lower than current priority:{}",
                        newEffect.getId(), newp, oldp);
                return false;
            } else if(newp == oldp) {
                if(newid == oldid) {
                    if (newEffect.addOverlay(oldEffect.getOverlay())) {
                        onReplace(oldEffect, newEffect);
                    } else {
                        if(oldEffect.isEndByTime() && newEffect.isEndByTime() && newEffect.getEndTime() > oldEffect.getEndTime()) {
                            oldEffect.refreshBy(newEffect);
                        } else {
                            return false;
                        }
                    }
                } else {
                    if(oldEffect.isEndByTime() && newEffect.isEndByTime() && newEffect.getEndTime() > oldEffect.getEndTime()) {
                        onReplace(oldEffect, newEffect);
                    } else {
                        Trace.error("addEffect replace fail. oldeffect:{} neweffect:{} aren't both endbytime!", oldid, newid);
                        return false;
                    }
                }
                Trace.debug("addEffect overlay add. effectid:{} new overlay:{}", newid, newEffect.getOverlay());
            } else {
                onReplace(oldEffect, newEffect);
                Trace.debug("addEffect replace.. neweffect:{} overlay:{} oldeffect:{}", newid, newEffect.getOverlay(), oldid);
            }
        } else {
            onInstall(newEffect);
        }
        return true;
	}
	
	private void onInstall(Effect e) {
        e.install(self);
        if(!e.isTransient()) {
            dirty = true;
            effectsByGroup.put(e.getGroupid(), e);
        }
		// 瞬时性effect,也没必要告诉客户端
		if(self.isActive() && e.isNeedNotify()) {
			self.broadcastToNearby(new SAddEffect(e.createInfo()));
		}
	}
	
	private void onUninstall(Effect e) {
		e.uninstall();
		if(self.isActive() && e.isNeedNotify()) {
            self.broadcastToNearby(new SRemoveEffect(e.getId()));
		}
	}

    private void onReplace(Effect oldEffect, Effect newEffect) {
        oldEffect.uninstall();
        effectsByGroup.remove(oldEffect.getGroupid());
        if(self.isActive() && oldEffect.getId() != newEffect.getId()) {
            self.broadcastToNearby(new SRemoveEffect(oldEffect.getId()));
        }
        newEffect.install(self);
        effectsByGroup.put(newEffect.getGroupid(), newEffect);
        if(self.isActive() && oldEffect.isNeedNotify() && (oldEffect.getId() != newEffect.getId() || oldEffect.getOverlay() != newEffect.getOverlay())) {
            self.broadcastToNearby(new SAddEffect(newEffect.createInfo()));
        }
        dirty = true;
    }

    public boolean hasHarmfulEffect() {
        for(Effect e: getEffects()) {
            if(e.isHarmful())
                return true;
        }
        return false;
    }
	
	public void removeEffect(Effect e) {
		if(effectsByGroup.remove(e.getGroupid(), e)) {
		    dirty = true;
			onUninstall(e);
            if(e.decOverlayAndCheckGreaterZero()) {
                onInstall(e);
            }
		}
	}

	public void createEffectInfos(List<EffectInfo> infos) {
	    for(Effect e : getEffects()) {
	        if(e.isNeedNotify())
	            infos.add(e.createInfo());
        }
	}

	private Effect[] getEffects() {
        if(dirty) {
            dirty = false;
            tempEffects = effectsByGroup.values().toArray(EMPTY_EFFECT);
        }
        return tempEffects;
    }

    public void uninstallAll(Predicate<Effect> pred) {
        for (Effect g : getEffects()) {
            if(pred.test(g)) {
                dirty = true;
                effectsByGroup.remove(g.getGroupid());
                onUninstall(g);
            }
        }
    }

	public void update(long now) {
        for (Effect g : getEffects()) {
            if(!g.update(now)) {
                dirty = true;
                effectsByGroup.remove(g.getGroupid());
                onUninstall(g);
            }
        }
    }

	public void onDead() {
        uninstallAll(e -> e.isClearWhileDead());
    }

	public void onRevive() {

	}
}
