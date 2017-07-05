package com.valkryst.VRoguelike.enums;

import lombok.Getter;

public enum Direction {
    NORTH(0),
    SOUTH(1),
    EAST(2),
    WEST(3);

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
