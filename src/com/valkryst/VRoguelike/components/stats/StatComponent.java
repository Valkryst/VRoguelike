package com.valkryst.VRoguelike.components.stats;

import com.valkryst.VECS.Component;
import lombok.Getter;
import lombok.Setter;

public class StatComponent<T extends Number> extends Component {
    /** The name of the stat. */
    @Getter private final String name;

    /** The current value. */
    @Getter @Setter private T currValue;

    /**
     * Constructs a new StatComponent.
     *
     * @param name
     *        The name of the stat.
     *
     * @param currValue
     *        The current value.
     */
    public StatComponent(final String name, final T currValue) {
        if (name == null) {
            throw new IllegalArgumentException("A stat cannot have a null name.");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("A stat cannot have an empty name.");
        }

        if (currValue == null) {
            throw new IllegalArgumentException("A stat cannot have null as a current value.");
        }

        this.name = name;
        this.currValue = currValue;
    }

    @Override
    public String toString() {
        String res = getClass().getSimpleName() + ":";
        res += "\n\tName:\t" + name;
        res += "\n\tCurrent Value:\t" + currValue;
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
        isEqual &= currValue.equals(otherComp.getCurrValue());
        return isEqual;
    }

    @Override
    public String toJson() {
        return "{\"name\":" + name + ",\"currValue\":" + currValue + "}";
    }
}
