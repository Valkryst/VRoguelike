package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.LineOfSight;
import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.entity.builder.AbstractCreatureBuilder;
import com.valkryst.VRoguelike.item.equipment.EquipmentInventory;
import com.valkryst.VRoguelike.stat.Statistic;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Creature extends Entity {
    /** The race. */
    @Getter @Setter private Race race;

    /** The statistics. */
    private final Map<String, Statistic> statistics;

    /** The equipment inventory. */
    @Getter private final EquipmentInventory equipment;

    /** A collection of all tiles that are currently visible to the creature. */
    @Getter private LineOfSight lineOfSight;

    /**
     * Constructs a new Creature.
     *
     * @param builder
     *        The builder.
     */
    public Creature(final AbstractCreatureBuilder builder) {
        super(builder);
        race = builder.getRace();
        statistics = builder.getStatistics();
        equipment = builder.getEquipment();
        lineOfSight = new LineOfSight(this, builder.getLineOfSightRadius());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Creature").append(super.toString().substring(6));
        sb.append("\tRace:\t").append(race.name()).append("\n");

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

    /**
     * Sets the sight radius.
     *
     * @param sightRadius
     *        The sight radius.
     */
    public void setSightRadius(final int sightRadius) {
        lineOfSight = new LineOfSight(this, sightRadius);
    }
}
