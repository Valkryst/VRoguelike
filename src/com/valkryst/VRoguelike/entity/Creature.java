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

        // Set Stats:
        final ResourceStatistic xp = new ResourceStatistic("XP", 0, 83);
        final ResourceStatistic level = new ResourceStatistic("Level", 1, 200);

        addStatistic(xp);
        addStatistic(level);
        addStatistic(new ResourceStatistic("Gold", 0, Integer.MAX_VALUE));

        addStatistic(new ResourceStatistic("Health", 0, 100));
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
}