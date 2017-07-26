package com.valkryst.VRoguelike.stat;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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
     *
     * @throws IllegalArgumentException
     *        If the maximum is less than the minimum.
     */
    public ResourceStatistic(final String name, final int minimum, final int maximum) {
        super(name, maximum);

        if (maximum < minimum) {
            throw new IllegalArgumentException("The maximum (" + maximum + ") cannot be less than the minimum("
                                               + minimum + ").");
        }

        this.maximum = maximum;
        this.minimum = minimum;
    }

    /**
     * Constructs a new ResourceStatistic.
     *
     * @param name
     *        The name of the statistic.
     *
     * @param value
     *        The value.
     *
     * @param minimum
     *        The minimum value.
     *
     * @param maximum
     *        The maximum value.
     *
     * @throws IllegalArgumentException
     *        If the maximum is less than the minimum.
     */
    public ResourceStatistic(final String name, final int value, final int minimum, final int maximum) {
        super(name, value);

        if (maximum < minimum) {
            throw new IllegalArgumentException("The maximum (" + maximum + ") cannot be less than the minimum("
                    + minimum + ").");
        }

        this.maximum = maximum;
        this.minimum = minimum;
    }

    @Override
    public String toString() {
        String ret = super.toString();
        ret += "\n\tMaximum Value:\t" + maximum;
        ret += "\n\tMinimum Value:\t" + minimum;
        return ret;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hashCode(maximum) + Objects.hashCode(minimum);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Statistic == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final ResourceStatistic otherSta = (ResourceStatistic) otherObj;

        boolean isEqual = super.equals(otherObj);
        isEqual &= Objects.equals(maximum, otherSta.getMaximum());
        isEqual &= Objects.equals(minimum, otherSta.getMinimum());
        return isEqual;
    }

    @Override
    public void setValue(final int value) {
        if (value <= maximum && value >= minimum) {
            super.setValue(value);
        }
    }
}
