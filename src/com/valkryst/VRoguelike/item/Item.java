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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Item:");
        sb.append("\n\tName:\t").append(name);
        sb.append("\n\tDescription:\t").append(description);

        for (final Statistic statistic : statistics.values()) {
            final String temp = "\t" + statistic.toString();
            sb.append(temp.replace("\n\t", "\n\t\t"));
        }

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name) + Objects.hashCode(description) + Objects.hashCode(statistics);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Item == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final Item otherItm = (Item) otherObj;

        boolean isEqual = super.equals(otherObj);
        isEqual &= Objects.equals(name, otherItm.getName());
        isEqual &= Objects.equals(description, otherItm.getDescription());
        isEqual &= Objects.equals(statistics, otherItm.getStatistics());
        return isEqual;
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

    /** @return An unmodifiable version of the statistics map. */
    public Map<String, Statistic> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
