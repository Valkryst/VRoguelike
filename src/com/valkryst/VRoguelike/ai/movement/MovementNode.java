package com.valkryst.VRoguelike.ai.movement;

import lombok.Getter;
import lombok.NonNull;

import java.awt.Point;

public class MovementNode {
    /** The previous movement. */
    @Getter private final MovementNode previousMovement;
    /** The current position. */
    @Getter private final Point currentPosition;

    /**
     * Constructs a new MovementNode.
     *
     * @param previousMovement
     *          The previous movement.
     *
     * @param currentPosition
     *          The current position.
     *
     * @throws NullPointerException
     *          If the currentPosition is null.
     */
    public MovementNode(final MovementNode previousMovement, final @NonNull Point currentPosition) {
        this.previousMovement = previousMovement;
        this.currentPosition = currentPosition;
    }
}
