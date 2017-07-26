package com.valkryst.VRoguelike.item;

import com.valkryst.VRoguelike.stat.Statistic;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Item {
    /** The name of the entity. */
    @Getter @Setter private String name = "Item";

    /** A description of the entity. */
    @Getter @Setter private String description = "This is an unnamed Item.";

    /** The statistics. */
    private final Map<String, Statistic> statistics = new HashMap<>();

    /**
     * Constructs a new Item.
     *
     * @param name
     *        The name.
     *
     * @param description
     *        The description.
     *
     * @throws NullPointerException
     *        If the name of description is null.
     *
     * @throws IllegalArgumentException
     *        If the name of description is empty.
     */
    public Item(final String name, final String description) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);

        if (name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty.");
        }

        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description cannot be empty.");
        }

        this.name = name;
        this.description = description;
    }

    /**
     * Constructs a new Item.
     *
     * @param name
     *        The name.
     *
     * @param description
     *        The description.
     *
     * @param statistics
     *        The statistics.
     *
     * @throws NullPointerException
     *        If the statistics list is null.
     */
    public Item(final String name, final String description, final List<Statistic> statistics) {
        this(name, description);

        Objects.requireNonNull(statistics);

        for(final Statistic statistic : statistics) {
            this.statistics.put(statistic.getName(), statistic);
        }
    }

    /**
     * Adds a statistic to the Item.
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
