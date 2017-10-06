package com.valkryst.VRoguelike.ai.movement;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

@EqualsAndHashCode
@ToString
public class MovementAI {
    /** The cache. */
    private static Cache<Integer, ArrayDeque<Point>> PATH_CACHE = Caffeine.newBuilder()
                                                                                    .initialCapacity(128)
                                                                                    .expireAfterAccess(5, TimeUnit.MINUTES)
                                                                                    .build();

    /** The current path being followed. */
    @Setter private ArrayDeque<Point> currentPath = new ArrayDeque<>();

    /**
     * Moves the entity to the next position along the
     * current path being followed.
     *
     * If there are no more points along the current
     * path, then nothing happens.
     *
     * @param entity
     *          The entity to move.
     */
    public void move(final @NonNull Entity entity) {
        if (currentPath.size() == 0) {
            return;
        }

        final Point currentPosition = entity.getPosition();
        final Point newPosition = currentPath.removeLast();

        int dx = 0;
        int dy = 0;

        // Calculate DX
        if (currentPosition.x > newPosition.x) {
            dx = currentPosition.x - newPosition.x;
        } else if (currentPosition.x < newPosition.x) {
            dx = newPosition.x - currentPosition.x;
        }

        // Calculate DY
        if (currentPosition.y > newPosition.y) {
            dy = currentPosition.y - newPosition.y;
        } else if (currentPosition.y < newPosition.y) {
            dy = newPosition.y - currentPosition.y;
        }

        entity.move(dx, dy);
    }

    /**
     * Determines a path to take, in order to move from the
     * start position to the end position.
     *
     * Uses a brute-force algorithm to find the shortest
     * path.
     *
     * @param map
     *          The map.
     *
     * @param start
     *          The start position.
     *
     * @param end
     *          The end position.
     *
     * @return
     *          A path of points to follow, in order to reach
     *          the end position.
     *
     *          If no path could be found, then an empty set
     *          of points is returned.
     *
     * @throws NullPointerException
     *          If the map, start point, or end point is null.
     */
    public ArrayDeque<Point> findPath(final @NonNull Map map, final @NonNull Point start, final @NonNull Point end) {
        // Handle the cases where:
        //      * The starting point is equal to the ending point.
        //      * The path is cached.
        if (start.equals(end)) {
            final ArrayDeque<Point> path = new ArrayDeque<>();
            path.add(start);
            return path;
        } else {
            final int hash = start.hashCode() + end.hashCode();
            final ArrayDeque<Point> path = PATH_CACHE.getIfPresent(hash);

            if (path != null) {
                return path;
            }
        }

        final boolean[][] visitedNodes = new boolean[map.getWidth()][map.getHeight()];
        MovementNode endNode = null;

        final ArrayDeque<MovementNode> nodes = new ArrayDeque<>();
        nodes.add(new MovementNode(map, null, start));

        do {
            final MovementNode currentNode = nodes.removeFirst();
            final Point currentPosition = currentNode.getCurrentPosition();

            visitedNodes[currentPosition.x][currentPosition.y] = true;

            // Add new neighbours to the queue
            for (final Point neighbour : getNeighbours(map, currentPosition)) {
                if (neighbour == null || visitedNodes[neighbour.x][neighbour.y]) {
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
        } while (nodes.size() > 0);

        return constructPath(endNode);
    }

    /**
     * Determines, and sets, a path to take, in order to move
     * from the start position to the end position.
     *
     * @param map
     *          The map.
     *
     * @param start
     *          The start position.
     *
     * @param end
     *          The end position.
     *
     * @throws NullPointerException
     *          If the map, start point, or end point is null.
     */
    public void findAndSetPath(final @NonNull Map map, final @NonNull Point start, final @NonNull Point end) {
        currentPath = findPath(map, start, end);
    }

    /**
     * Retrieves the four positions neighbouring a position.
     *
     * Returned positions may be null if they are not positions
     * contained within the map.
     *
     * @param map
     *          The map.
     *
     * @param position
     *          The center position.
     *
     * @return
     *          The top, bottom, left, and right positions
     *          neighbouring the center position.
     *
     * @throws NullPointerException
     *          If the map or position is null.
     */
    private Point[] getNeighbours(final @NonNull Map map, final @NonNull Point position) {
        final Point top = getNeighbour(map, position.x, position.y - 1);
        final Point bottom = getNeighbour(map, position.x, position.y + 1);
        final Point left = getNeighbour(map, position.x - 1, position.y);
        final Point right = getNeighbour(map, position.x + 1, position.y);

        return new Point[] {top, bottom, left, right};
    }

    /**
     * Retrieves a neighbour position.
     *
     * @param map
     *          The map.
     *
     * @param x
     *          The x-axis coordinate of the neighbour.
     *
     * @param y
     *          The y-axis coordinate of the neighbour.
     *
     * @return
     *          Either the neighbour position, or null if the
     *          position is invalid and is not contained within
     *          the map.
     *
     * @throws NullPointerException
     *          If the map is null.
     */
    private Point getNeighbour(final @NonNull Map map, final int x, final int y) {
        final Point neighbour = new Point(x, y);

        if (map.isPositionFree(neighbour)) {
            return neighbour;
        } else {
            return null;
        }
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
