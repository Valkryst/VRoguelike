package com.valkryst.VRoguelike.components;

import com.valkryst.VECS.Component;
import com.valkryst.VRadio.Radio;
import com.valkryst.VRadio.Receiver;
import lombok.Getter;

import java.awt.Point;

public class PositionComponent extends Component implements Receiver<VelocityComponent> {
    /** The position. */
    private Point point;

    /** The radio. */
    @Getter private final Radio<PositionComponent> radio = new Radio<>();

    /**
     * Constructs a new PositionComponent.
     *
     * @param x
     *        The x-axis coordinate.
     *
     * @param y
     *        The y-axis coordinate.
     */
    public PositionComponent(final int x, final int y) {
        point = new Point(x, y);
    }

    @Override
    public String toString() {
        String res = getClass().getSimpleName() + ":";
        res += "\n\tx:\t" + point.getX();
        res += "\n\ty:\t" + point.getY();
        return res;
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof PositionComponent == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final PositionComponent otherComp = (PositionComponent) otherObj;
        boolean isEqual = point.x == otherComp.getX();
        isEqual &= point.y == otherComp.getY();

        return isEqual;
    }

    @Override
    public String toJson() {
        return "{\"x\":" + point.x + ",\"y\":" + point.y + "}";
    }

    @Override
    public void receive(final String event, final VelocityComponent data) {
        if (event.equals("VELOCITY_CHANGED")) {
            final int speed = data.getSpeed();

            switch (data.getDirection()) {
                case NORTH: {
                    setY(getY() - speed);
                    break;
                }
                case SOUTH: {
                    setY(getY() + speed);
                    break;
                }
                case EAST: {
                    setX(getX() + speed);
                    break;
                }
                case WEST: {
                    setX(getX() - speed);
                    break;
                }
            }
        }
    }

    /**
     * Retrieves the x-axis coordinate.
     *
     * @return
     *        The x-axis coordinate.
     */
    public int getX() {
        return point.x;
    }

    /**
     * Retrieves the y-axis coordinate.
     *
     * @return
     *        The y-axis coordinate.
     */
    public int getY() {
        return point.y;
    }

    /**
     * Sets the new x-axis coordinate.
     *
     * @param x
     *        The new coordinate.
     */
    public void setX(final int x) {
        point.setLocation(x, point.y);
        radio.transmit("MOVED", this);
    }

    /**
     * Sets the new y-axis coordinate.
     *
     * @param y
     *        The new coordinate.
     */
    public void setY(final int y) {
        point.setLocation(point.x, y);
        radio.transmit("MOVED", this);
    }
}
