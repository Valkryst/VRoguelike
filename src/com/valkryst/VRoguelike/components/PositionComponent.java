package com.valkryst.VRoguelike.components;

import com.valkryst.VECS.Component;
import lombok.Getter;
import lombok.Setter;

public class PositionComponent extends Component {
    /** The x-axis coordinate. */
    @Getter @Setter private int x;
    /** The y-axis coordinate. */
    @Getter @Setter private int y;

    @Override
    public String toString() {
        String res = getClass().getSimpleName() + ":";
        res += "\n\tx:\t" + x;
        res += "\n\ty:\t" + y;
        return res;
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof HealthComponent == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final PositionComponent otherComp = (PositionComponent) otherObj;
        boolean isEqual = x == otherComp.getX();
        isEqual &= y == otherComp.getY();

        return isEqual;
    }

    @Override
    public String toJson() {
        return "{\"x\":" + x + ",\"y\":" + y + "}";
    }
}
