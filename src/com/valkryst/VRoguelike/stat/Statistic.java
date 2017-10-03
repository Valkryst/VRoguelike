package com.valkryst.VRoguelike.stat;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

public class Statistic {
    /** The name of the statistic. */
    @Getter private final String name;

    /** The value. */
    @Getter private int value;

    /** The function to run whenever the value is changed. */
    @Getter @Setter private Runnable onChange;

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
    public Statistic(final @NonNull String name, final int value) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty.");
        }

        this.name = name;
        this.value = value;
    }

    /**
     * Constructs a new Statistic.
     *
     * @param name
     *        The name of the statistic.
     *
     * @param value
     *        The value.
     *
     * @param onChange
     *        The function to run whenever the value is changed.
     *
     * @throws NullPointerException
     *        If name or onChange is null.
     */
    public Statistic(final @NonNull String name, final int value, final @NonNull Runnable onChange) {
        this(name, value);
        this.onChange = onChange;
    }

    @Override
    public String toString() {
        return name + ":\n\tValue:\t" + value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name) + Objects.hashCode(value);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Statistic == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final Statistic otherSta = (Statistic) otherObj;

        boolean isEqual = Objects.equals(name, otherSta.getName());
        isEqual &= Objects.equals(value, otherSta.getValue());
        return isEqual;
    }

    /**
     * Sets the new value, then runs the onChange function if
     * it is present.
     *
     * @param value
     *        The new value.
     */
    public void setValue(final int value) {
        this.value = value;

        if (onChange != null) {
            onChange.run();
        }
    }
}
