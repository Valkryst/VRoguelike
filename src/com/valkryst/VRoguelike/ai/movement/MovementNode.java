package com.valkryst.VRoguelike.ai.movement;

import lombok.Getter;
import lombok.NonNull;

import java.awt.Point;

public class MovementNode {
    /** The previous position of the movement. */
    @Getter private final Point previousPosition;
    /** The current position of the movement. */
    @Getter private final Point currentPosition;

    /**
     * Constructs a new MovementNode.
     *
     * @param previousPosition
     *          The previous position of the movement.
     *
     * @param currentPosition
     *          The current position of the movement.
     *
     * @throws NullPointerException
     *          If the currentPosition is null.
     */
    public MovementNode(final Point previousPosition, final @NonNull Point currentPosition) {
        this.previousPosition = previousPosition;
        this.currentPosition = currentPosition;
    }
}
