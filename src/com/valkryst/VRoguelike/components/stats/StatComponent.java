package com.valkryst.VRoguelike.components.stats;

import com.valkryst.VECS.Component;
import lombok.Getter;

public class StatComponent extends Component {
    /** The name of the stat. */
    @Getter private final String name;

    /** The maximum value. */
    @Getter private int maxValue;

    /** The current value. */
    @Getter private int currValue;

    /** The minimum value. */
    @Getter private int minValue;

    /**
     * Constructs a new StatComponent.
     *
     * @param name
     *        The name of the stat.
     *
     * @param maxValue
     *        The maximum value.
     *
     * @param currValue
     *        The current value.
     *
     * @param minValue
     *        The minimum value.
     */
    public StatComponent(final String name, int maxValue, int currValue, int minValue) {
        if (name == null) {
            throw new IllegalArgumentException("A stat cannot have a null name.");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("A stat cannot have an empty name.");
        }

        if (maxValue < minValue) {
            maxValue = minValue;
        }

        if (currValue > maxValue) {
            currValue = maxValue;
        }

        if (currValue < minValue) {
            currValue = minValue;
        }

        this.name = name;
        this.maxValue = maxValue;
        this.currValue = currValue;
        this.minValue = minValue;
    }

    @Override
    public String toString() {
        String res = getClass().getSimpleName() + ":";
        res += "\n\tName:\t" + name;
        res += "\n\tMaximum " + name + ":\t" + currValue;
        res += "\n\tCurrent \" + name + \":\t" + currValue;
        res += "\n\tMinimum \" + name + \":\t" + currValue;
        return res;
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof StatComponent == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final StatComponent otherComp = (StatComponent) otherObj;

        boolean isEqual = name.equals(otherComp.getName());
        isEqual &= maxValue == otherComp.getMaxValue();
        isEqual &= currValue == otherComp.getCurrValue();
        isEqual &= minValue == otherComp.getMinValue();
        return isEqual;
    }

    @Override
    public String toJson() {
        return "{\"name\":" + name + ",\"maxValue\":" + maxValue + ",\"currValue\":" + currValue + ",\"minValue\":" + minValue +"}";
    }
}
