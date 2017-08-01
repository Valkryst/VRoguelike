package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.item.equipment.EquipmentInventory;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import com.valkryst.VRoguelike.stat.LimitedStatistic;
import com.valkryst.VRoguelike.stat.Statistic;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractCreatureBuilder<B extends AbstractEntityBuilder<B>> extends AbstractEntityBuilder<B> {
    /** The race. */
    @Getter private Race race;

    /** The statistics. */
    @Getter private Map<String, Statistic> statistics;

    /** The equipment inventory. */
    @Getter private EquipmentInventory equipment;

    /** The line of sight radius. */
    @Getter private int lineOfSightRadius;

    @Override
    public void reset() {
        super.reset();
        super.setName("Creature");
        super.setDescription("This is an unnamed Creature.");
        race = null;
        statistics = new HashMap<>();
        equipment = new EquipmentInventory();
        lineOfSightRadius = 4;

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

    @Override
    protected void checkState() {
        super.checkState();
        Objects.requireNonNull(race);
    }

    /**
     * Sets the race.
     *
     * @param race
     *        The race.
     *
     * @return
     *        This.
     */
    public B setRace(final Race race) {
        this.race = race;
        return (B) this;
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

    /**
     * Sets the line of sight radius.
     *
     * @param lineOfSightRadius
     *        The line of sight radius.
     *
     * @return
     *        This.
     */
    public B setLineOfSightRadius(final int lineOfSightRadius) {
        this.lineOfSightRadius = lineOfSightRadius;
        return (B) this;
    }
}
