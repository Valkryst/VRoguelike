package com.valkryst.VRoguelike.ai.movement;

import com.valkryst.VRoguelike.world.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.awt.Point;

@EqualsAndHashCode
@ToString
final class MovementNode {
    /** The cost to reach this node. */
    @Getter private int cost;

    /** The previous movement. */
    @Getter private final MovementNode previousMovement;
    /** The current position. */
    @Getter private final Point currentPosition;

    /**
     * Constructs a new MovementNode.
     *
     * @param map
     *           The map.
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
    MovementNode(final @NonNull Map map, final MovementNode previousMovement, final @NonNull Point currentPosition) {
        this.previousMovement = previousMovement;
        this.currentPosition = currentPosition;

        if (previousMovement != null) {
            cost = previousMovement.getCost();
        } else {
            cost = 0;
        }

        cost += cost = map.getMapTiles()[currentPosition.x][currentPosition.y].getMovementCost();
    }
}
