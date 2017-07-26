package com.valkryst.VRoguelike.stat;

import lombok.Getter;
import lombok.Setter;

public class ResourceStatistic extends Statistic {
    /** The maximum value. */
    @Getter @Setter private int maximum;
    /** The minimum value. */
    @Getter @Setter private int minimum;

    /**
     * Constructs a new ResourceStatistic.
     *
     * @param name
     *        The name of the statistic.
     *
     * @param maximum
     *        The maximum and initial value.
     *
     * @param minimum
     *        The minimum value.
     */
    public ResourceStatistic(final String name, final int maximum, final int minimum) {
        super(name, maximum);

        if (maximum < minimum) {
            throw new IllegalArgumentException("The maximum (" + maximum + ") cannot be less than the minimum("
                                               + minimum + ").");
        }

        this.maximum = maximum;
        this.minimum = minimum;
    }

    @Override
    public void setValue(final int value) {
        if (value <= maximum && value >= minimum) {
            super.setValue(value);
        }
    }
}
