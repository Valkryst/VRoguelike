package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.item.EquipmentInventory;
import com.valkryst.VRoguelike.stat.LimitedStatistic;
import com.valkryst.VRoguelike.stat.Statistic;
import lombok.Getter;

import java.util.*;

public class Creature extends Entity {
    /** The statistics. */
    private final Map<String, Statistic> statistics = new HashMap<>();
    /** The equipment inventory. */
    @Getter private final EquipmentInventory equipment = new EquipmentInventory();

    /**
     * Constructs a new Creature.
     *
     * @param x
     *        The x-axis position.
     *
     * @param y
     *        The y-axis position.
     *
     * @param sprite
     *        The sprite.
     */
    public Creature(final int x, final int y, final Sprite sprite) {
        super(x, y, sprite);

        this.setName("Creature");
        this.setDescription("This is an unnamed creature.");

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
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Creature").append(super.toString().substring(6));
        sb.append("\tStatistics:\n");
        for (final Statistic statistic : statistics.values()) {
            final String temp = "\t\t" + statistic.toString();
            sb.append(temp.replace("\n\t", "\n\t\t\t")).append("\n");
        }

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hashCode(statistics);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Entity == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final Creature otherCreature = (Creature) otherObj;

        boolean isEqual = super.equals(otherObj);
        isEqual &= Objects.equals(statistics, otherCreature.getStatistics());
        return isEqual;
    }

    /**
     * Adds a statistic to the Entity.
     *
     * @param statistic
     *        The statistic.
     *
     * @throws NullPointerException
     *        If the statistic is null.
     */
    public void addStatistic(final Statistic statistic) {
        Objects.requireNonNull(statistic);
        statistics.put(statistic.getName(), statistic);
    }

    /**
     * Retrieves a statistic, if it exists.
     *
     * @param name
     *        The statistic's name.
     *
     * @return
     *        The statistic.
     *
     * @throws NullPointerException
     *        If the name is null.
     */
    public Optional<Statistic> getStatistic(final String name) {
        Objects.requireNonNull(name);
        return Optional.ofNullable(statistics.get(name));
    }

    /** @return An unmodifiable version of the statistics map. */
    public Map<String, Statistic> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
