package com.valkryst.VRoguelike.components;


import com.valkryst.VECS.Component;
import com.valkryst.VRoguelike.enums.Direction;
import lombok.Getter;
import lombok.Setter;

public class VelocityComponent extends Component {
    /** The speed. */
    @Getter private int speed = 0;
    /** The direction of travel. */
    @Getter @Setter private Direction direction = Direction.NONE;

    /** Constructs a new VelocityComponent. */
    public VelocityComponent() {}

    /**
     * Constructs a new VelocityComponent.
     *
     * @param speed
     *        The speed.
     *
     * @param direction
     *        The direction of travel.
     */
    public VelocityComponent(final int speed, final Direction direction) {
        if (speed < 0) {
            this.speed = 0;
        } else {
            this.speed = speed;
        }

        this.direction = direction;
    }

    @Override
    public String toString() {
        String res = getClass().getSimpleName() + ":";
        res += "\n\tSpeed:\t" + speed;
        res += "\n\tDirection:\t" + direction.name() + "(" + direction.getValue() + ")";
        return res;
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof VelocityComponent == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final VelocityComponent otherComp = (VelocityComponent) otherObj;
        boolean isEqual = speed == otherComp.getSpeed();
        isEqual &= direction == otherComp.getDirection();

        return isEqual;
    }

    @Override
    public String toJson() {
        return "{\"speed\":" + speed + ",\"direction\":" + direction.getValue() + "}";
    }

    /**
     * Sets the new speed.
     *
     * If the value is negative, then the velocity is set to 0.
     *
     * @param speed
     *        The new speed.
     */
    public void setSpeed(final int speed) {
        if (speed < 0) {
            this.speed = 0;
        } else {
            this.speed = speed;
        }
    }
}
