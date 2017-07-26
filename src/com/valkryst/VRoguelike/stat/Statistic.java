package com.valkryst.VRoguelike.stat;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Statistic {
    /** The name of the statistic. */
    @Getter private final String name;

    /** The value. */
    @Getter @Setter private int value;

    /**
     * Constructs a new Statistic.
     *
     * @param name
     *        The name of the statistic.
     *
     * @param value
     *        The value.
     *
     * @throws NullPointerException
     *        If the name is null.
     *
     * @throws IllegalArgumentException
     *        If the name is empty.
     */
    public Statistic(final String name, final int value) {
        Objects.requireNonNull(name);

        if (name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty.");
        }

        this.name = name;
        this.value = value;
    }
}
