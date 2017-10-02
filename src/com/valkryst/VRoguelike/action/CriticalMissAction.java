package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.stat.BoundedStatistic;
import com.valkryst.VRoguelike.world.Map;
import lombok.Data;
import lombok.NonNull;

@Data
public class CriticalMissAction implements Action {
    /** The damage to deal. */
    private final int damage;

    /**
     * Constructs a new CriticalMissAction.
     *
     * @param damage
     *        The damage to deal.
     */
    public CriticalMissAction(final int damage) {
        this.damage = damage;
    }

    @Override
    public void perform(final @NonNull Map map, final @NonNull Entity entity) {
        if (entity instanceof Creature == false) {
            return;
        }

        final Creature self = (Creature) entity;

        final BoundedStatistic health = self.getStat_health();

        health.setValue(health.getValue() - damage);

        map.getMessageBox().appendText(self.getName() + " missed and attacked itself for " + damage + " damage.");
        map.getMessageBox().appendText(self.getName() + "'s health is now " + health.getValue() + "/" + health.getMaximum());

        if (health.getValue() == health.getMinimum()) {
            new DeathAction().perform(map, self);
        }
    }
}
