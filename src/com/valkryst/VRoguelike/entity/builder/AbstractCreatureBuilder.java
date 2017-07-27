package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.item.EquipmentInventory;
import com.valkryst.VRoguelike.item.EquipmentSlot;
import com.valkryst.VRoguelike.item.EquippableItem;
import com.valkryst.VRoguelike.stat.LimitedStatistic;
import com.valkryst.VRoguelike.stat.Statistic;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class AbstractCreatureBuilder<B extends AbstractEntityBuilder<B>> extends AbstractEntityBuilder<B> {
    /** The statistics. */
    @Getter private Map<String, Statistic> statistics;
    /** The equipment inventory. */
    @Getter private EquipmentInventory equipment;

    @Override
    public void reset() {
        super.reset();
        super.setName("Creature");
        super.setDescription("This is an unnamed Creature.");
        statistics = new HashMap<>();
        equipment = new EquipmentInventory();

        // Set Stats:
        final LimitedStatistic xp = new LimitedStatistic("XP", 0, 83);
        final LimitedStatistic level = new LimitedStatistic("Level", 1, 1, 200);

        addStatistic(xp);
        addStatistic(level);
        addStatistic(new LimitedStatistic("Gold", 0, 0, Integer.MAX_VALUE));

        addStatistic(new LimitedStatistic("Health", 0, 100));
        addStatistic(new Statistic("Strength", 1));
        addStatistic(new Statistic("Defense", 1));

        // Level When XP Full:
        xp.setOnChange(() -> {
            if (xp.getValue() == xp.getMaximum()) {
                level.setValue(level.getValue() + 1);
            }
        });

        // Set New XP Goal on Levelup:
        level.setOnChange(() -> {
            double newXp = 100;

            for (int i = 1 ; i < level.getValue() ; i++) {
                newXp += newXp * 0.089;
            }

            xp.setMinimum(0);
            xp.setValue(0);
            xp.setMaximum((int) newXp);
        });
    }

    /**
     * Adds a statistic.
     *
     * @param statistic
     *        The statistic.
     *
     * @return
     *        This.
     */
    public B addStatistic(final Statistic statistic) {
        statistics.put(statistic.getName(), statistic);
        return (B) this;
    }

    /**
     * Adds the item in a specific slot.
     *
     * This will override any equipment already in the slot.
     *
     * @param slot
     *        The slot.
     *
     * @param item
     *        The item.
     *
     * @return
     *        This.
     */
    public B addEquipment(final EquipmentSlot slot, final EquippableItem item) {
        equipment.setItemInSlot(slot, item);
        return (B) this;
    }
}
