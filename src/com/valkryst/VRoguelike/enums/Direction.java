package com.valkryst.VRoguelike.enums;

import lombok.Getter;

public enum Direction {
    NONE(0),
    NORTH(1),
    SOUTH(2),
    EAST(3),
    WEST(4);

    /** The numeric representation of a Direction. */
    @Getter private final int value;

    /**
     * Constructs a new Direction enum.
     *
     * @param value
     *        The numeric representation of a Direction.
     */
    Direction(final int value) {
        this.value = value;
    }
}
