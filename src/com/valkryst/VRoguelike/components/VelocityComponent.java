package com.valkryst.VRoguelike.components;


import com.valkryst.VECS.Component;
import com.valkryst.VRadio.Radio;
import com.valkryst.VRadio.Receiver;
import com.valkryst.VRoguelike.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VelocityComponent extends Component implements Receiver<KeyEvent> {
    /** The speed. */
    @Getter private int speed = 0;
    /** The direction of travel. */
    @Getter private Direction direction = Direction.NONE;

    @Getter private final Radio<VelocityComponent> radio = new Radio<>();

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

    @Override
    public void receive(final String event, final KeyEvent data) {
        // Set the speed:
        switch (event) {
            case "KEY_PRESSED": {
                speed = 1;
                break;
            }
            case "KEY_RELEASED": {
                speed = 0;
                break;
            }
        }

        // Set direction:
        switch (data.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W: {
                direction = Direction.NORTH;
                break;
            }
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S: {
                direction = Direction.SOUTH;
                break;
            }
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A: {
                direction = Direction.WEST;
                break;
            }
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D: {
                direction = Direction.EAST;
                break;
            }
        }
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

        radio.transmit("VELOCITY_CHANGED", this);
    }

    /**
     * Sets the new direction.
     *
     * @param direction
     *        The new direction.
     */
    public void setDirection(final Direction direction) {
        if (direction != null) {
            this.direction = direction;
            radio.transmit("VELOCITY_CHANGED", this);
        }
    }
}
