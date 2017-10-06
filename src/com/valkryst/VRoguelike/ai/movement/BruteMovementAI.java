package com.valkryst.VRoguelike.ai.movement;

import com.valkryst.VRoguelike.world.Map;
import lombok.NonNull;

import java.awt.Point;
import java.util.ArrayDeque;

public class BruteMovementAI extends MovementAI {
    @Override
    public ArrayDeque<Point> findPath(final @NonNull Map map, final @NonNull Point start, final @NonNull Point end) {
        // Check if cached path exists:
        final ArrayDeque<Point> path = super.findPath(map, start, end);

        if (path.size() > 0) {
            return path;
        }


        final boolean[][] visitedNodes = new boolean[map.getHeight()][map.getWidth()];
        final ArrayDeque<MovementNode> nodes = new ArrayDeque<>();

        MovementNode endNode = null;

        // Add start node
        nodes.add(new MovementNode(map, null, start));

        while (nodes.size() > 0) {
            final MovementNode currentNode = nodes.removeFirst();
            final Point currentPosition = currentNode.getCurrentPosition();

            // Skip previously visited nodes
            if (visitedNodes[currentPosition.y][currentPosition.x]) {
                continue;
            } else {
                visitedNodes[currentPosition.y][currentPosition.x] = true;
            }

            // Add new neighbours to the queue
            final Point[] neighbours = super.getNeighbours(map, currentPosition);

            for (final Point neighbour : neighbours) {
                if (neighbour == null || visitedNodes[neighbour.y][neighbour.x]) {
                    continue;
                }

                final MovementNode neighbourNode = new MovementNode(map, currentNode, neighbour);
                nodes.add(neighbourNode);

                // Check if the neighbour is the destination
                if (end.equals(neighbour)) {
                    if (endNode == null) {
                        endNode = neighbourNode;
                    } else {
                        if (endNode.getCost() < neighbourNode.getCost()) {
                            endNode = neighbourNode;
                        }
                    }
                }
            }
        }

        return constructPath(endNode);
    }

    /**
     * Constructs a path of points from the end node to
     * the start node.
     *
     * @param endNode
     *          The end node.
     *
     * @return
     *          A FILO queue where the end position is
     *          at the beginning of the queue and the
     *          start position is at the end of the queue.
     */
    private ArrayDeque<Point> constructPath(@NonNull MovementNode endNode) {
        final ArrayDeque<Point> path = new ArrayDeque<>();

        while (endNode != null) {
            path.add(endNode.getCurrentPosition());
            endNode = endNode.getPreviousMovement();
        }

        return path;
    }
}
