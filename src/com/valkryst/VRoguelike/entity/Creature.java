package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.stat.ResourceStatistic;
import com.valkryst.VRoguelike.stat.Statistic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Creature extends Entity {
    /** The statistics. */
    private final Map<String, Statistic> statistics = new HashMap<>();

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

        addStatistic(new ResourceStatistic("Health", 0, 100));
        addStatistic(new Statistic("Strength", 1));
        addStatistic(new Statistic("Defense", 1));
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
        return Optional.of(statistics.get(name));
    }
}
